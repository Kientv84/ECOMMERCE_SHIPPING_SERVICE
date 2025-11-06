package com.ecommerce.shipping.commons;

public enum ShipmentTrackingStatus {
    PENDING,        // Mới tạo, chưa gửi
    PICKED_UP,      // Đã lấy hàng
    IN_TRANSIT,     // Đang giao
    DELIVERED,
    OUT_FOR_DELIVERY,// Giao thành công
    FAILED_DELIVERY,         // Giao thất bại
    RETURNED        // Hàng bị trả lại
}
