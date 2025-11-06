package com.ecommerce.shipping.messaging.producer;

import com.ecommerce.shipping.dtos.responses.kafka.KafkaShipmentStatusUpdated;
import com.ecommerce.shipping.utils.KafkaObjectError;

public interface ShipmentProducer {
    void produceShipmentEventUpdateOrderStatus(KafkaShipmentStatusUpdated message);

    void produceMessageError(KafkaObjectError kafkaObject);

}
