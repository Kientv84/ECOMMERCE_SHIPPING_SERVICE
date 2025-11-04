package com.ecommerce.shipping.commons;

public enum ShippingStatus {
    PENDING,        // Mới tạo, chưa gửi
    PICKED_UP,      // Đã lấy hàng
    IN_TRANSIT,     // Đang giao
    DELIVERED,      // Giao thành công
    FAILED,         // Giao thất bại
    RETURNED        // Hàng bị trả lại
}
