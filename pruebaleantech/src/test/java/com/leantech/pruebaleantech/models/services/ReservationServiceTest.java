package com.leantech.pruebaleantech.models.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leantech.pruebaleantech.commons.LeanTechConstants;
import com.leantech.pruebaleantech.models.dao.IReservationDao;
import com.leantech.pruebaleantech.models.entity.Reservation;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

	@InjectMocks
	private ReservationServiceImpl reservationServiceImpl;

	@Mock
	private IReservationDao reservationDao;

	@Test
	void getReservationByIdTest() {

		Optional<Reservation> reservationOp = Optional
				.of(Reservation.builder().id(1L).arrivalDate(new Date()).departureDate(new Date()).numberOfMinors(1L)
						.numberOfPeople(3L).numberOfRooms(1L).reservationHolder(LeanTechConstants.COMMON_STRING_MY_NAME)
						.reservationHolderEmail(LeanTechConstants.COMMON_STRING_MY_EMAIL).build());

		Mockito.when(reservationDao.findById(1L)).thenReturn(reservationOp);

		Reservation reservation = reservationServiceImpl.findById(1L);

		verify(reservationDao).findById(1L);
		assertAll(() -> {
			assertNotNull(reservation);
			assertEquals(LeanTechConstants.COMMON_STRING_MY_EMAIL, reservation.getReservationHolderEmail());
			assertEquals(LeanTechConstants.COMMON_STRING_MY_NAME, reservation.getReservationHolder());
		});
	}

	@Test
	void getAllReservationsTest() {

		List<Reservation> reservationsList = new ArrayList<Reservation>();
		reservationsList
				.add(Reservation.builder().id(1L).arrivalDate(new Date()).departureDate(new Date()).numberOfMinors(1L)
						.numberOfPeople(3L).numberOfRooms(1L).reservationHolder(LeanTechConstants.COMMON_STRING_MY_NAME)
						.reservationHolderEmail(LeanTechConstants.COMMON_STRING_MY_EMAIL).build());

		Mockito.when(reservationDao.findAll()).thenReturn(reservationsList);

		List<Reservation> reservations = reservationServiceImpl.findAll();

		verify(reservationDao).findAll();
		assertAll(() -> {
			assertNotNull(reservations);
			assertEquals(LeanTechConstants.COMMON_STRING_MY_EMAIL, reservations.get(0).getReservationHolderEmail());
			assertEquals(LeanTechConstants.COMMON_STRING_MY_NAME, reservations.get(0).getReservationHolder());
		});

	}

	@Test
	void cancelReservationTest() {
		
		Mockito.doNothing().when(reservationDao).deleteById(1L);

		reservationServiceImpl.cancelReservation(1L);
		
		verify(reservationDao).deleteById(1L);

	}
	
	@Test
	void saveReservationTest() {
		
		Reservation reservation = Reservation.builder().id(1L).arrivalDate(new Date()).departureDate(new Date()).numberOfMinors(1L)
				.numberOfPeople(3L).numberOfRooms(1L).reservationHolder(LeanTechConstants.COMMON_STRING_MY_NAME)
				.reservationHolderEmail(LeanTechConstants.COMMON_STRING_MY_EMAIL).build();
				
		Mockito.when(reservationDao.save(Mockito.any(Reservation.class)))
        .thenAnswer(i -> i.getArguments()[0]);
		
		Reservation res = reservationServiceImpl.saveReservation(reservation);
		
		verify(reservationDao).save(reservation);
		
		assertAll(() -> {
			assertNotNull(res);
			assertEquals(LeanTechConstants.COMMON_STRING_MY_EMAIL, res.getReservationHolderEmail());
			assertEquals(LeanTechConstants.COMMON_STRING_MY_NAME, res.getReservationHolder());
		});

	}
	
}
