package com.ecommerce.shipping.mocks.mockdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingCreateMockRequest {
    private String orderCode;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
}