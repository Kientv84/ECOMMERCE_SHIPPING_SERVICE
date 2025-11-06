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
           ShipmentEntity shipment = ShipmentEntity.builder()
                   .orderId(event.getId())
                   .userId(event.getUserId())
                   .receiverPhone(event.getPhone())
                   .destinationAddress(event.getShippingAddress())
                   .carrier(event.getShippingCode())
                   .senderAddress("Kho tổng Hà Nội") // Hardcode tạm
                   .trackingCode(null)  // chưa tích hợp bên thứ 3
                   .status(ShippingStatus.CREATED) // LUÔN MẶC ĐỊNH
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
        return List.of();
    }

    @Override
    public ShipmentResponse getShipmentById(UUID uuid) {
        return null;
    }

    @Override
    public String deleteShipment(List<UUID> uuids) {
        return "";
    }
}
