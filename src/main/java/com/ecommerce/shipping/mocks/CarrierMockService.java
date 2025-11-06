package com.ecommerce.shipping.mocks;

import com.ecommerce.shipping.commons.ShipmentTrackingStatus;
import com.ecommerce.shipping.entities.ShipmentEntity;
import com.ecommerce.shipping.mocks.mockdtos.RequestShipmentId;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface CarrierMockService {
    //TODO: create new record tracking shipment, update shipment,
    String handleNext(RequestShipmentId requestBody);

    /**
     * Hoàn tất shipment -> mark DELIVERED
     */
    String handleComplete( RequestShipmentId requestBody);

    /**
     * Đánh dấu giao hàng thất bại
     */
    String handleFail( RequestShipmentId requestBody);


    void saveTracking(ShipmentEntity shipment, ShipmentTrackingStatus status);
}
