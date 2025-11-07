package com.ecommerce.shipping.controller;


import com.ecommerce.shipping.dtos.responses.ShipmentResponse;
import com.ecommerce.shipping.entities.ShipmentEntity;
import com.ecommerce.shipping.services.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @GetMapping("/shipments")
    public ResponseEntity<List<ShipmentResponse>> getAllShipment() {
        return ResponseEntity.ok(shipmentService.getAllShipment());
    }

    @GetMapping("/shipment/{id}")
    public ResponseEntity<ShipmentResponse> getShipmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(shipmentService.getShipmentById(id));
    }

    @PostMapping("shipments")
    public String deleteShipment(@RequestBody List<UUID> uuids) {
        return shipmentService.deleteShipment(uuids);
    }
}
