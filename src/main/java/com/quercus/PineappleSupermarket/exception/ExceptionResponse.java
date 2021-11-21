package com.quercus.PineappleSupermarket.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime date;
	private String menssage; 
	private String  details;
	
	
	
	public ExceptionResponse(LocalDateTime date, String menssage, String details) {
		super();
		this.date = date;
		this.menssage = menssage;
		this.details = details;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getMenssage() {
		return menssage;
	}
	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	} 
	
	
}
