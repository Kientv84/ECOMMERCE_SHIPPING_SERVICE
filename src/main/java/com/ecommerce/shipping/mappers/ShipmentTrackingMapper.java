package com.ecommerce.shipping.mappers;

import com.ecommerce.shipping.dtos.responses.ShipmentTrackingResponse;
import com.ecommerce.shipping.entities.ShipmentTrackingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentTrackingMapper {
    @Mapping(target = "status", source = "status")
    @Mapping(target = "eventTime", source = "eventTime")
    @Mapping(target = "shipment", source = "shipment.id")
    ShipmentTrackingResponse mapToShipmentTrackingResponse(ShipmentTrackingEntity shipmentTrackingEntity);
}
