package com.leantech.pruebaleantech.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leantech.pruebaleantech.commons.LeanTechConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name=LeanTechConstants.COMMON_STRING_RESERVATION)
public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = LeanTechConstants.COMMON_STRING_ARRIVAL_DATE)
	@JsonFormat(pattern = LeanTechConstants.COMMON_STRING_DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	@Column(name = LeanTechConstants.COMMON_STRING_DEPARTURE_DATE)
	@JsonFormat(pattern = LeanTechConstants.COMMON_STRING_DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	@Column(name = LeanTechConstants.COMMON_STRING_TOTAL_DAYS)
	private Long totalDays;
	@Column(name = LeanTechConstants.COMMON_STRING_NUMBER_OF_PEOPLE)
	private Long numberOfPeople;
	@Column(name = LeanTechConstants.COMMON_STRING_RESERVATION_HOLDER)
	@NotEmpty
	private String reservationHolder;
	@Column(name = LeanTechConstants.COMMON_STRING_NUMBER_OF_ROOMS)
	private Long numberOfRooms;
	@Column(name = LeanTechConstants.COMMON_STRING_NUMBER_OF_MINORS)
	private Long numberOfMinors;
	@Column(name = LeanTechConstants.COMMON_STRING_RESERVATION_HOLDER_EMAIL)
	@Email
	@NotEmpty
	private String reservationHolderEmail;
	
}
