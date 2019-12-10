package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfiguration {

	@Bean
	public ProducerFactory<String, Person> producerFactoryObject() {
		Map<String, Object> configs = new HashMap<>();

		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(configs);
	}

	@Bean
	public ProducerFactory<String, String> producerFactoryString() {
		Map<String, Object> configs = new HashMap<>();

		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return new DefaultKafkaProducerFactory<>(configs);
	}

	@Bean(name = "kafkaPersonTemplate")
	public KafkaTemplate<String, Person> getKafkaTemplate() {
		return new KafkaTemplate<String, Person>(producerFactoryObject());
	}

	@Bean(name = "kafkaStringTemplate")
	public KafkaTemplate<String, String> getKafkaStringTemplate() {
		return new KafkaTemplate<String, String>(producerFactoryString());
	}
}
