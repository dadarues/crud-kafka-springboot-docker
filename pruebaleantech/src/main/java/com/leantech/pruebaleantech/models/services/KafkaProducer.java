package com.leantech.pruebaleantech.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.leantech.pruebaleantech.models.entity.Reservation;

@Component
public class KafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	private final KafkaTemplate<String, Reservation> kafkaTemplate;

	public KafkaProducer(@Qualifier("kafkaTemplate") KafkaTemplate<String, Reservation> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(Reservation message, String topic) {
		LOGGER.info("Producing message {}", message);
		this.kafkaTemplate.send(topic, message);
	}

}