package com.leantech.pruebaleantech.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leantech.pruebaleantech.commons.CommonUtil;
import com.leantech.pruebaleantech.models.dao.IReservationDao;
import com.leantech.pruebaleantech.models.entity.Reservation;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	private IReservationDao reservationDao;

	@Override
	@Transactional(readOnly = true)
	public List<Reservation> findAll() {
		return (List<Reservation>) reservationDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Reservation findById(Long id) {
		return reservationDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Reservation findByReservationHolderEmail(String reservationHolderEmail) {
		return reservationDao.findByReservationHolderEmail(reservationHolderEmail);
	}

	@Override
	@Transactional
	public Reservation saveReservation(Reservation reservation) {
		reservation.setTotalDays(CommonUtil.getDaysOfDifferenceBetweenTwoDates(reservation.getArrivalDate(),
				reservation.getDepartureDate()));
		return reservationDao.save(reservation);
	}

	@Override
	@Transactional
	public void cancelReservation(Long id) {
		reservationDao.deleteById(id);
	}

}
