package com.ecommerce.shipping.services;


public interface KafkaService<K, V> {
    void send(final String topic, V value);

    void send(final String topic, K key, V value);
}
