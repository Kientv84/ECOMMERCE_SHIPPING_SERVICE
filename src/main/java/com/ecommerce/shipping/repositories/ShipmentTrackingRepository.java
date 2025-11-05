package com.ecommerce.shipping.repositories;

import com.ecommerce.shipping.entities.ShipmentTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentTrackingRepository extends JpaRepository<ShipmentTrackingEntity, UUID> {
}
