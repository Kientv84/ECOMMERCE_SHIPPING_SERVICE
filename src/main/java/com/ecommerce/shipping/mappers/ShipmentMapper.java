package com.ecommerce.shipping.mappers;

import com.ecommerce.shipping.dtos.responses.ShipmentResponse;
import com.ecommerce.shipping.dtos.responses.kafka.KafkaShipmentStatusUpdated;
import com.ecommerce.shipping.entities.ShipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {
    @Mapping(target = "status", source = "status")
    ShipmentResponse mapToShipmentResponse(ShipmentEntity shipment);

    KafkaShipmentStatusUpdated mapToKafkaShipmentStatusUpdated(ShipmentEntity shipment);
}
