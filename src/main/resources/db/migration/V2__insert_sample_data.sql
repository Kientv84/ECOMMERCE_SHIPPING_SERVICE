-- ======================================
-- V2__insert_sample_shipping_data.sql
-- ======================================

-- 1. Insert sample shipments
INSERT INTO shipments_entity (
    id, order_id, user_id, carrier, tracking_code, destination_address, sender_address, receiver_phone, is_collected, status, create_date, update_date
) VALUES
(
    '9e8f0e74-5c57-445b-8775-ad799086dbde',
    '99999999-9999-9999-9999-999999999999',
    'c743008e-cd42-467e-bf10-35124b9a28b1',
    'GHN',
    NULL,
    '268/23 Lã Xuân Oai, Long Trường, Quận 9, Hồ Chí Minh',
    'Kho tổng Hà Nội',
    '0968727900',
    TRUE,
    'SHIPPED',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- 2. Insert sample shipment items
INSERT INTO shipment_item_entity (
    id, shipment_id, product_id, product_name, quantity, unit_price, line_total
) VALUES
(
    '93bf22e5-6842-4858-b6cb-7c6e85cbd869',
    '9e8f0e74-5c57-445b-8775-ad799086dbde',
    '6bd45b45-0124-4129-9b8d-a4259421c088',
    'Apex Seamless T-Shirt',
    1,
    499000.00,
    499000.00
);

-- 3. Insert sample shipment tracking
INSERT INTO shipment_tracking_entity (
    id, shipment_id, status, location, description, event_time
) VALUES
(
    gen_random_uuid(),
    '9e8f0e74-5c57-445b-8775-ad799086dbde',
    'SHIPPED',
    'Kho tổng Hà Nội',
    'Hàng đã được giao đến khách',
    CURRENT_TIMESTAMP
);
