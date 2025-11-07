package com.ecommerce.shipping.dtos.responses.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaOrderShippingResponse {
    private UUID id;
    private UUID userId;
    private String orderCode;
    private UUID paymentMethod;
    private String phone;
    private String email;
    private String shippingCode;
    private String paymentStatus;
    private String status;
    private String shippingAddress;
    private BigDecimal totalPrice;
}

