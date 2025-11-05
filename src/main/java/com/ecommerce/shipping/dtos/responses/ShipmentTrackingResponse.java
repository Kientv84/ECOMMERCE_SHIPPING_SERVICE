package com.ecommerce.shipping.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentTrackingResponse {
    private UUID id;
    private UUID shipment;
    private String status;
    private String location;
    private String description;
    private Date eventTime;
}
