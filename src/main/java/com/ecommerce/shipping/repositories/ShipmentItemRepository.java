package com.ecommerce.shipping.repositories;

import com.ecommerce.shipping.entities.ShipmentItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItemEntity, UUID> {
}
