package com.ecommerce.shipping.messaging.producer;

import com.ecommerce.shipping.dtos.responses.kafka.KafkaShipmentStatusUpdated;
import com.ecommerce.shipping.properties.KafkaTopicProperties;
import com.ecommerce.shipping.services.KafkaService;
import com.ecommerce.shipping.utils.KafkaObjectError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentProducerImpl implements  ShipmentProducer{
    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaService kafkaService;

    @Override
    public void produceShipmentEventUpdateOrderStatus(KafkaShipmentStatusUpdated message) {
        var topic = kafkaTopicProperties.getOrderUpdated();
        log.info("[produceEventUpdateOrderStatus] producing order to topic {}", topic);
        kafkaService.send(topic, message);
    }

    @Override
    public void produceMessageError(KafkaObjectError kafkaObject) {
        var topic = kafkaTopicProperties.getErrorOrder();
        log.info("[produceMessageError] producing error to topic {}", topic);
        kafkaService.send(topic, kafkaObject);
    }
}
