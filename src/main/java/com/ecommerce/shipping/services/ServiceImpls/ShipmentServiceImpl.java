package com.ecommerce.shipping.services.ServiceImpls;

import com.ecommerce.shipping.commons.ShipmentTrackingStatus;
import com.ecommerce.shipping.commons.ShippingStatus;
import com.ecommerce.shipping.dtos.responses.ShipmentResponse;
import com.ecommerce.shipping.dtos.responses.ShipmentTrackingResponse;
import com.ecommerce.shipping.dtos.responses.kafka.KafkaOrderShippingResponse;
import com.ecommerce.shipping.entities.ShipmentEntity;
import com.ecommerce.shipping.entities.ShipmentTrackingEntity;
import com.ecommerce.shipping.exceptions.EnumError;
import com.ecommerce.shipping.exceptions.ServiceException;
import com.ecommerce.shipping.mappers.ShipmentMapper;
import com.ecommerce.shipping.repositories.ShipmentRepository;
import com.ecommerce.shipping.repositories.ShipmentTrackingRepository;
import com.ecommerce.shipping.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final ShipmentTrackingRepository shipmentTrackingRepository;
    private final ShipmentMapper shipmentMapper;

    @Override
    public ShipmentResponse handleOrderReadyForShipping(KafkaOrderShippingResponse event) {
       try {
           if (event == null ) {
               throw new ServiceException(EnumError.KAFKA_NOT_MESSAGE, "kafka.not.message");
           }

           boolean isCollected = false;
           if (event.getPaymentStatus() != null
                   && event.getPaymentStatus().equalsIgnoreCase("PAID")) {
               isCollected = true;
           }

           ShipmentEntity shipment = ShipmentEntity.builder()
                   .orderId(event.getId())
                   .userId(event.getUserId())
                   .receiverPhone(event.getPhone())
                   .destinationAddress(event.getShippingAddress())
                   .carrier(event.getShippingCode())
                   .senderAddress("Kho tổng Hà Nội") // Hardcode tạm
                   .trackingCode(null)  // chưa tích hợp bên thứ 3
                   .status(ShippingStatus.CREATED) // LUÔN MẶC ĐỊNH
                   .isCollected(isCollected)
                   .build();

           shipmentRepository.save(shipment);


           addTracking(shipment.getId(), ShipmentTrackingStatus.PENDING, "Đơn hàng đã sẵn sàng để chuyển đi");


           return shipmentMapper.mapToShipmentResponse(shipment);
       } catch (Exception e) {
           throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
       }
    }

    @Override
    public void addTracking(UUID shipmentId, ShipmentTrackingStatus status, String note) {
        try {

            ShipmentEntity shipment = shipmentRepository.findById(shipmentId).orElseThrow(() -> new ServiceException(EnumError.SHIPMENT_GET_ERROR, "shipment.get.err"));

            ShipmentTrackingEntity tracking = ShipmentTrackingEntity.builder()
                    .shipment(shipment)
                    .status(status)
                    .description(note)
                    .location("Đang ở điểm A")
                    .build();

            shipmentTrackingRepository.save(tracking);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public List<ShipmentResponse> getAllShipment() {
        try {
            List<ShipmentResponse> responses = shipmentRepository.findAll().stream().map(ship -> shipmentMapper.mapToShipmentResponse(ship)).toList();

            return responses;

        } catch (Exception e) {
            throw new ServiceException(EnumError.SHIPMENT_GET_ERROR, "shipment.get.error");
        }
    }

    @Override
    public ShipmentResponse getShipmentById(UUID uuid) {
        try {
            ShipmentEntity shipment = shipmentRepository.findById(uuid).orElseThrow(() -> new ServiceException(EnumError.SHIPMENT_GET_ERROR, "shipment.get.error"));

            return  shipmentMapper.mapToShipmentResponse(shipment);
        } catch (ServiceException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public String deleteShipment(List<UUID> uuids) {
        try {
            if ( uuids == null || uuids.isEmpty()) {
                throw new ServiceException(EnumError.SHIPMENT_ERR_DEL_EM, "shipment.err.em");
            }

            List<ShipmentEntity> foundIds = shipmentRepository.findAllById(uuids);

            System.out.println("Find shipment:" + foundIds.toString());

            if ( foundIds.isEmpty()) {
                throw new ServiceException(EnumError.SHIPMENT_ERR_NOT_FOUND, "shipment.err.not.found");
            }

            shipmentRepository.deleteAllById(uuids);

            return "Deleted shipments successfully: {}" + uuids;

        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }
}
