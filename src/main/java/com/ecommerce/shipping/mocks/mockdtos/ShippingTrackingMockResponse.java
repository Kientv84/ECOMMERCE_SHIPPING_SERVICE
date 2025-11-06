package com.ecommerce.shipping.mocks.mockdtos;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShippingTrackingMockResponse {
    private String shippingCode;
    private String status;
}
