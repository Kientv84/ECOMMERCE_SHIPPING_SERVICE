package com.ecommerce.shipping.dtos.responses.kafka;

import com.ecommerce.shipping.dtos.responses.ShipmentItemResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaEventInventory {
    private UUID orderId;
    private List<ShipmentItemResponse> items;
}
