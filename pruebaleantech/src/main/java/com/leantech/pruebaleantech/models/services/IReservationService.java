package com.leantech.pruebaleantech.models.services;

import java.util.List;

import com.leantech.pruebaleantech.models.entity.Reservation;

public interface IReservationService {
	
	public List<Reservation> findAll();
	
	public Reservation findById(Long id);
	
	public Reservation findByReservationHolderEmail(String reservationHolderEmail);
	
	public Reservation saveReservation(Reservation reservation);
	
	public void cancelReservation(Long id);
}
