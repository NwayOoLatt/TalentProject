package com.personal.accident.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Premium {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String year;
	private Double lampSum;
	private String term;
	private Double payamount;
	
	private String status;
	
	@OneToOne(mappedBy = "premium")
	private PolicyHolder policyholder;

	
	public PolicyHolder getPolicyholder() {
		return policyholder;
	}

	public void setPolicyholder(PolicyHolder policyholder) {
		this.policyholder = policyholder;

	}
	
	public Double getPayamount() {
		return payamount;
	}

	public void setPayamount(Double payamount) {
		this.payamount = payamount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getLampSum() {
		return lampSum;
	}

	public void setLampSum(Double lampSum) {
		this.lampSum = lampSum;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
