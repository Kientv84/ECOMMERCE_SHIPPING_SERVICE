package com.ecommerce.shipping.services;

import com.ecommerce.shipping.commons.ShipmentTrackingStatus;
import com.ecommerce.shipping.dtos.responses.ShipmentResponse;
import com.ecommerce.shipping.dtos.responses.ShipmentTrackingResponse;
import com.ecommerce.shipping.dtos.responses.kafka.KafkaOrderShippingResponse;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {
    ShipmentResponse  handleOrderReadyForShipping(KafkaOrderShippingResponse event);
    void addTracking(UUID shipmentId, ShipmentTrackingStatus status, String note);
    List<ShipmentResponse> getAllShipment();
    ShipmentResponse getShipmentById(UUID uuid);
    String deleteShipment(List<UUID> uuids);
}
