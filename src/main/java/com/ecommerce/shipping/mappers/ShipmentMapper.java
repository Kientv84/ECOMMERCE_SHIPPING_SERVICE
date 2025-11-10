package com.ecommerce.shipping.mappers;

import com.ecommerce.shipping.dtos.responses.ShipmentResponse;
import com.ecommerce.shipping.dtos.responses.kafka.KafkaEventInventory;
import com.ecommerce.shipping.dtos.responses.kafka.KafkaShipmentStatusUpdated;
import com.ecommerce.shipping.entities.ShipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {
    @Mapping(target = "items", source = "items")
    @Mapping(target = "status", source = "status")
    ShipmentResponse mapToShipmentResponse(ShipmentEntity shipment);

    @Mapping(target = "shipmentId", source = "id")
    @Mapping(target = "newStatus", source = "status")
    KafkaShipmentStatusUpdated mapToKafkaShipmentStatusUpdated(ShipmentEntity shipment);

    @Mapping(target = "items", source = "items")
    KafkaEventInventory mapToKafkaEventInventory(ShipmentEntity shipment);
}
