package com.ecommerce.shipping.mocks;


import com.ecommerce.shipping.mocks.mockdtos.RequestShipmentId;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mock")
@RequiredArgsConstructor
public class CarrierMockController {
    private final CarrierMockService carrierMockService;


    @PostMapping("/status-process")
    public ResponseEntity<String> handleNext(@RequestBody RequestShipmentId requestBody) {
        return ResponseEntity.ok(carrierMockService.handleNext(requestBody));
    }

    @PostMapping("/status-complete")
    public ResponseEntity<String> handleComplete(@RequestBody RequestShipmentId requestBody) {
        return ResponseEntity.ok(carrierMockService.handleComplete(requestBody));
    }

    @PostMapping("/status-fail")
    public ResponseEntity<String> handleFail(@RequestBody RequestShipmentId requestBody) {
        return ResponseEntity.ok(carrierMockService.handleFail(requestBody));
    }

}
