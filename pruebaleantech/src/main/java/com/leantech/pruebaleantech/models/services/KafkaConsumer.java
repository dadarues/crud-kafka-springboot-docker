package com.leantech.pruebaleantech.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.leantech.pruebaleantech.commons.EmailService;
import com.leantech.pruebaleantech.commons.LeanTechConstants;
import com.leantech.pruebaleantech.models.entity.Reservation;

@Component
public class KafkaConsumer {
	
	@Autowired
	Gson gson;
	
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private KafkaProducer jsonProducer;

	Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = LeanTechConstants.COMMON_STRING_LEAN_TOPIC, groupId = "group_id")
	public void consume(String message) {
		Reservation reservation = gson.fromJson(message, Reservation.class);
		
		try {
			reservationService.saveReservation(reservation);
			emailService.sendEmailTool(LeanTechConstants.COMMON_STRING_SUCCESS_CREATE_RESERVATION_EMAIL, reservation.getReservationHolderEmail(), LeanTechConstants.COMMON_STRING_STATUS_RESERVATION_EMAIL);
			logger.info("Consuming Message {}", reservation);
			
		} catch (Exception e) {
			jsonProducer.sendMessage(reservation, LeanTechConstants.COMMON_STRING_DEAD_LETTER_QUEUE);
			emailService.sendEmailTool(LeanTechConstants.COMMON_STRING_ERROR_CREATE_RESERVATION_EMAIL, reservation.getReservationHolderEmail(), LeanTechConstants.COMMON_STRING_STATUS_RESERVATION_EMAIL);
			logger.info("Error Consuming {}", reservation);
		}
		
	}
	
}