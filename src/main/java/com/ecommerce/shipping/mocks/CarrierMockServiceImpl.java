package com.ecommerce.shipping.mocks;

import com.ecommerce.shipping.commons.ShipmentTrackingStatus;
import com.ecommerce.shipping.commons.ShippingStatus;
import com.ecommerce.shipping.entities.ShipmentEntity;
import com.ecommerce.shipping.entities.ShipmentTrackingEntity;
import com.ecommerce.shipping.exceptions.EnumError;
import com.ecommerce.shipping.exceptions.ServiceException;
import com.ecommerce.shipping.mappers.ShipmentMapper;
import com.ecommerce.shipping.messaging.producer.ShipmentProducer;
import com.ecommerce.shipping.mocks.mockdtos.RequestShipmentId;
import com.ecommerce.shipping.repositories.ShipmentRepository;
import com.ecommerce.shipping.repositories.ShipmentTrackingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class CarrierMockServiceImpl implements CarrierMockService{
    private final ShipmentRepository shipmentRepository;
    private final ShipmentTrackingRepository shipmentTrackingRepository;
    private final CarrierMockMapper carrierMockMapper;
    private final ShipmentProducer shipmentProducer;
    private final ShipmentMapper shipmentMapper;


    @Override
    public String handleNext( RequestShipmentId requestBody) {
        try {
            ShipmentEntity shipment = shipmentRepository.findById(requestBody.getId()).orElseThrow(() ->new ServiceException(EnumError.SHIPMENT_GET_ERROR, "shipment.get.err"));

            var lastTracking = shipmentTrackingRepository
                    .findTopByShipmentIdOrderByEventTimeDesc(requestBody.getId())
                    .orElse(null);

            ShipmentTrackingStatus current = lastTracking == null
                    ? ShipmentTrackingStatus.PENDING
                    : lastTracking.getStatus();

            ShipmentTrackingStatus next = switch (current) {
                case PENDING -> ShipmentTrackingStatus.PICKED_UP;
                case PICKED_UP -> ShipmentTrackingStatus.IN_TRANSIT;
                case IN_TRANSIT -> ShipmentTrackingStatus.OUT_FOR_DELIVERY;
                default -> null;
            };

            if (next == null) return "No more steps. Current: " + current;

            saveTracking(shipment, next);

            // Update ShipmentEntity status
            shipment.setStatus(carrierMockMapper.mapShippingStatus(next));
            shipmentRepository.save(shipment);

            // Produce kafka order updated status
            shipmentProducer.produceShipmentEventUpdateOrderStatus(shipmentMapper.mapToKafkaShipmentStatusUpdated(shipment));

            return "Updated to: " + next;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "Mock fail");
        }
    }

    @Override
    public String handleComplete( RequestShipmentId requestBody) {
        try {
            ShipmentEntity shipment = shipmentRepository.findById(requestBody.getId())
                    .orElseThrow(() -> new ServiceException(EnumError.SHIPMENT_GET_ERROR));

            saveTracking(shipment, ShipmentTrackingStatus.DELIVERED);
            shipment.setStatus(ShippingStatus.DELIVERED);
            shipment.setCollected(true);
            shipmentRepository.save(shipment);

            // Produce kafka order updated status
            shipmentProducer.produceShipmentEventUpdateOrderStatus(shipmentMapper.mapToKafkaShipmentStatusUpdated(shipment));

            return "Shipment marked as DELIVERED";

        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "Mock fail");
        }
    }

    @Override
    public String handleFail( RequestShipmentId requestBody) {
        try {
            var shipment = shipmentRepository.findById(requestBody.getId())
                    .orElseThrow(() -> new ServiceException(EnumError.SHIPMENT_GET_ERROR));

            // For failed_delivery
            saveTracking(shipment, ShipmentTrackingStatus.FAILED_DELIVERY);
            shipment.setStatus(ShippingStatus.FAILED_DELIVERY);
            shipmentRepository.save(shipment);

            // For return
            saveTracking(shipment, ShipmentTrackingStatus.RETURNED);
            shipment.setStatus(ShippingStatus.RETURNED);
            shipmentRepository.save(shipment);

            // Produce kafka order updated status
            shipmentProducer.produceShipmentEventUpdateOrderStatus(shipmentMapper.mapToKafkaShipmentStatusUpdated(shipment));

            return "Shipment marked as RETURNED";

        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "Mock fail");
        }
    }

    @Override
    public void saveTracking(ShipmentEntity shipment, ShipmentTrackingStatus status) {
        // Create new tracking record
        ShipmentTrackingEntity tracking = ShipmentTrackingEntity.builder()
                .shipment(shipment)
                .status(status)
                .description("Mock updated to " + status)
                .location("Mock Location Hub")
                .build();

        shipmentTrackingRepository.save(tracking);
    }
}
