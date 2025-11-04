package com.ecommerce.shipping.entities;

import com.ecommerce.shipping.commons.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "shipments_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID orderId;                  // Id đơn hàng từ Order Service
    private UUID userId;                   // Người đặt hàng
    private String carrier;                // Hãng vận chuyển (VD: "GHTK", "GHN", "VNPOST", "INTERNAL")
    private String trackingCode;           // Mã tracking từ bên thứ 3 (VD: GHTK12345)
    private String destinationAddress;     // Địa chỉ giao hàng
    private String senderAddress;          // Địa chỉ gửi (kho)
    private String receiverPhone;          // SĐT người nhận

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;         // Trạng thái vận chuyển

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
