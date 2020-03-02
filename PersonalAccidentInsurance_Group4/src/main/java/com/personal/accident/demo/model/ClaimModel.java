package com.personal.accident.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class ClaimModel {

	private int id;
	private String type;
	private String reason;
	private Date date;
	private String place;
	private Double amount;
	private String status;
	private String today;
	private int claimCount;
	private String claimamount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public int getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}
	public String getClaimamount() {
		return claimamount;
	}
	public void setClaimamount(String claimamount) {
		this.claimamount = claimamount;
	}
	
}
