package com.ecommerce.shipping.repositories;

import com.ecommerce.shipping.entities.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, UUID> {
}
