package com.ecommerce.shipping.mocks;

import com.ecommerce.shipping.commons.ShipmentTrackingStatus;
import com.ecommerce.shipping.commons.ShippingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface CarrierMockMapper {
    @ValueMapping(source = "PENDING", target = "CREATED")
    @ValueMapping(source = "PICKED_UP", target = "SHIPPED")
    @ValueMapping(source = "IN_TRANSIT", target = "IN_TRANSIT")
    @ValueMapping(source = "OUT_FOR_DELIVERY", target = "OUT_FOR_DELIVERY")
    @ValueMapping(source = "DELIVERED", target = "DELIVERED")
    @ValueMapping(source = "FAILED_DELIVERY", target = "FAILED_DELIVERY")
    @ValueMapping(source = "RETURNED", target = "RETURNED")
    ShippingStatus mapShippingStatus(ShipmentTrackingStatus status);
}
