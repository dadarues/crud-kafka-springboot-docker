package com.leantech.pruebaleantech.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leantech.pruebaleantech.commons.CommonUtil;
import com.leantech.pruebaleantech.commons.LeanTechConstants;
import com.leantech.pruebaleantech.models.entity.Reservation;
import com.leantech.pruebaleantech.models.entity.ResponseMessage;
import com.leantech.pruebaleantech.models.services.IReservationService;
import com.leantech.pruebaleantech.models.services.KafkaProducer;

@RestController
@RequestMapping(LeanTechConstants.COMMON_STRING_REQUEST_MAPPING)
public class ReservationRestController {

	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private KafkaProducer jsonProducer;

	@GetMapping(LeanTechConstants.COMMON_STRING_GET_ALL_RESERVATIONS_ENDPOINT)
	public List<Reservation> findAll() {
		return reservationService.findAll();
	}

	@GetMapping(LeanTechConstants.COMMON_STRING_GET_RESERVATION_BY_ID_ENDPOINT)
	public ResponseEntity<?> findById(@PathVariable Long id) {

		Reservation reservation = null;
		Map<String, Object> response = new HashMap<>();

		try {

			reservation = reservationService.findById(id);

		} catch (DataAccessException e) {
			response.put(LeanTechConstants.COMMON_STRING_MESSAGE, LeanTechConstants.COMMON_STRING_ERROR_DB_CONECTION);
			response.put(LeanTechConstants.COMMON_STRING_ERROR,
					CommonUtil.generateString(e.getMessage(), LeanTechConstants.COMMON_STRING_COLON,
							LeanTechConstants.COMMON_STRING_SPACE, e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		if (null == reservation) {
			response.put(LeanTechConstants.COMMON_STRING_MESSAGE,
					CommonUtil.generateString(LeanTechConstants.COMMON_STRING_ERROR_NOT_FOUND_BY_ID, id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}

	@PostMapping(LeanTechConstants.COMMON_STRING_CREATE_RESERVATION_ENDPOINT)
	public ResponseEntity<?> createReservation(@Valid @RequestBody Reservation reservation, BindingResult result) {

		ResponseMessage response = new ResponseMessage();
		response.setDate(new Date());

		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().stream().map(err -> 
				CommonUtil.generateString("'",err.getField(),"'",err.getDefaultMessage())
			).collect(Collectors.toList());
			
			Map<String, Object> res = new HashMap<>();
			res.put(LeanTechConstants.COMMON_STRING_ERRORS, errors);
			
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.BAD_REQUEST);
		} else {
			try {
				jsonProducer.sendMessage(reservation, LeanTechConstants.COMMON_STRING_LEAN_TOPIC);
				response.setStatusCode(LeanTechConstants.COMMON_STRING_SUCCESS_CODE);
				response.setResponseMessage(LeanTechConstants.COMMON_STRING_SUCCESS_CREATE_RESERVATION);
				response.setStatusMessage(LeanTechConstants.COMMON_STRING_SUCCESS_MESSAGE);

			} catch (Exception e) {
				response.setStatusCode(LeanTechConstants.COMMON_STRING_ERROR_CODE);
				response.setResponseMessage(LeanTechConstants.COMMON_STRING_ERROR_DONT_CREATE_RESERVATION_MESSAGE);
				response.setStatusMessage(LeanTechConstants.COMMON_STRING_ERROR_CREATE_RESERVATION_MESSAGE);
				return new ResponseEntity<ResponseMessage>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

	@DeleteMapping(LeanTechConstants.COMMON_STRING_CANCEL_RESERVATION_ENDPOINT)
	public ResponseEntity<?> cancelReservation(@PathVariable Long id) {

		ResponseMessage response = new ResponseMessage();
		response.setDate(new Date());
		try {
			reservationService.cancelReservation(id);
			response.setStatusCode(LeanTechConstants.COMMON_STRING_SUCCESS_CODE);
			response.setResponseMessage(LeanTechConstants.COMMON_STRING_SUCCESS_CANCEL_RESERVATION);
			response.setStatusMessage(LeanTechConstants.COMMON_STRING_SUCCESS_MESSAGE);

		} catch (DataAccessException e) {
			response.setStatusCode(LeanTechConstants.COMMON_STRING_ERROR_CODE);
			response.setResponseMessage(LeanTechConstants.COMMON_STRING_ERROR_DONT_CANCEL_RESERVATION_MESSAGE);
			response.setStatusMessage(LeanTechConstants.COMMON_STRING_ERROR_CANCEL_RESERVATION_MESSAGE);
			
			return new ResponseEntity<ResponseMessage>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}

}
