package com.ecommerce.shipping.dtos.responses.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaOrderShipped {
    private String phone;
    private String email;
    private String shippingCode;
    private String paymentStatus;
    private String status;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private List<KafkaOrderItemResponse> items;
}
