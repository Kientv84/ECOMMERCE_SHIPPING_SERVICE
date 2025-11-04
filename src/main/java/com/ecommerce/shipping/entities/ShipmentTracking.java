package com.ecommerce.shipping.entities;

import com.ecommerce.shipping.commons.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipment_tracking_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;     // Ví dụ: IN_TRANSIT, ARRIVED_AT_HUB, DELIVERED

    private String location;           // Ví dụ: "Kho Hà Nội", "Kho TP.HCM"
    private String description;        // Mô tả chi tiết (VD: "Đã rời kho trung chuyển")

    private LocalDateTime eventTime;   // Thời gian sự kiện (time A, time B,...)

    @PrePersist
    public void onCreate() {
        if (eventTime == null)
            eventTime = LocalDateTime.now();
    }
}