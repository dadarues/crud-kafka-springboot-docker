package com.leantech.pruebaleantech.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.leantech.pruebaleantech.models.entity.Reservation;

public interface IReservationDao extends CrudRepository<Reservation, Long>{
	
	public Reservation findByReservationHolderEmail(String reservationHolderEmail);

}
