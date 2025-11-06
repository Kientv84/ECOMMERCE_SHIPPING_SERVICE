package com.ecommerce.shipping.repositories;

import com.ecommerce.shipping.entities.ShipmentTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShipmentTrackingRepository extends JpaRepository<ShipmentTrackingEntity, UUID> {

    //For mock carrier api (Giả lập vận chuyển bên thứ 3)
    Optional<ShipmentTrackingEntity> findTopByShipmentIdOrderByEventTimeDesc(UUID shipmentId);


}
