package com.leantech.pruebaleantech.models.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusMessage;
	private String responseMessage;
	private Date date;
	
}
