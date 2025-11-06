package com.ecommerce.shipping.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("spring.kafka.order.topic")
public class KafkaTopicProperties {
    private String orderUpdated;
    private String errorOrder;

}
