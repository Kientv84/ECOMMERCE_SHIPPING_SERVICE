package com.ecommerce.shipping.entities;

import com.ecommerce.shipping.commons.ShipmentTrackingStatus;
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
public class ShipmentTrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private ShipmentEntity shipment;              // Liên kết về Shipment

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private ShipmentTrackingStatus status;          // Trạng thái tại thời điểm này

    @Column(name = "location", length = 255)
    private String location;                // Ví dụ: "Kho Hà Nội", "Kho TP.HCM"

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;             // Mô tả chi tiết trạng thái

    @Column(name = "event_time", nullable = false)
    private LocalDateTime eventTime;        // Thời gian ghi nhận trạng thái

    @PrePersist
    public void onCreate() {
        if (eventTime == null)
            eventTime = LocalDateTime.now();
    }
}