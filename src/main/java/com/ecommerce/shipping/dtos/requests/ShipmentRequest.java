package com.ecommerce.shipping.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {
    private UUID id;
    private UUID orderId;
    private UUID userId;
    private String carrier;
    private String trackingCode;
    private String destinationAddress;
    private String senderAddress;
    private String receiverPhone;
    private String status;
}
