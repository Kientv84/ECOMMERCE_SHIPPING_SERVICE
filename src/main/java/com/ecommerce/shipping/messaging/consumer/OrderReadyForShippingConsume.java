package com.ecommerce.shipping.messaging.consumer;

import com.ecommerce.shipping.dtos.responses.kafka.KafkaOrderShippingResponse;
import com.ecommerce.shipping.services.ShipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderReadyForShippingConsume {
    private final ShipmentService shipmentService;

    @KafkaListener(topics = "${spring.kafka.order.topic.order-ready-for-shipping}", groupId = "spring.kafka.order.group", containerFactory = "kafkaListenerContainerFactory")
    public void onMessageHandler(@Payload String message) {
        try {
            log.info("[onMessageHandler] Start consuming message ...");
            log.info("[onMessageHandler] Received message payload: {}", message);

            KafkaOrderShippingResponse response = new ObjectMapper().readValue(message, KafkaOrderShippingResponse.class);
            shipmentService.handleOrderReadyForShipping(response);
        } catch (Exception e) {
            log.error("[onMessageHandler] Error. Err {}", e.getMessage());
        }
    }
}

