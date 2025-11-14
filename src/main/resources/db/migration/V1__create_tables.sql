-- ======================================
-- V1__create_shipping_tables.sql
-- ======================================

-- 1. Create table shipments_entity
CREATE TABLE shipments_entity (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    user_id UUID NOT NULL,
    carrier VARCHAR(50) NOT NULL,
    tracking_code VARCHAR(100),
    destination_address TEXT NOT NULL,
    sender_address TEXT NOT NULL,
    receiver_phone VARCHAR(20) NOT NULL,
    is_collected BOOLEAN NOT NULL DEFAULT FALSE,
    status VARCHAR(30) NOT NULL,
    create_date TIMESTAMP,
    update_date TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- 2. Create table shipment_item_entity
CREATE TABLE shipment_item_entity (
    id UUID PRIMARY KEY,
    shipment_id UUID NOT NULL REFERENCES shipments_entity(id) ON DELETE CASCADE,
    product_id UUID NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    unit_price NUMERIC(19,2),
    line_total NUMERIC(19,2)
);

-- 3. Create table shipment_tracking_entity
CREATE TABLE shipment_tracking_entity (
    id UUID PRIMARY KEY,
    shipment_id UUID NOT NULL REFERENCES shipments_entity(id) ON DELETE CASCADE,
    status VARCHAR(30) NOT NULL,
    location VARCHAR(255),
    description TEXT,
    event_time TIMESTAMP NOT NULL
);
