package com.ecommerce.shipping.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("spring.kafka.shipping.topic")
public class KafkaShippingTopicProperties {
    private String shippingShipped;
    private String shippingReturned;
    private String shippingCompleted;
}
