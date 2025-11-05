package com.ecommerce.shipping.entities;

import com.ecommerce.shipping.commons.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "shipments_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ShipmentEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "carrier", length = 50, nullable = false)
    private String carrier;

    @Column(name = "tracking_code", length = 100)
    private String trackingCode;

    @Column(name = "destination_address", columnDefinition = "TEXT", nullable = false)
    private String destinationAddress;

    @Column(name = "sender_address", columnDefinition = "TEXT", nullable = false)
    private String senderAddress;

    @Column(name = "receiver_phone", length = 20, nullable = false)
    private String receiverPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private ShippingStatus status;
    // ====== Metadata ======
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="create_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name ="update_date")
    private Date updatedDate;

    @CreatedBy
    @Column(name ="created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name ="updated_by")
    private String updatedBy;
}
