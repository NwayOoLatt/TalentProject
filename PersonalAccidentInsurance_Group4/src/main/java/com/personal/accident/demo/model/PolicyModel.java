package com.personal.accident.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.personal.accident.demo.dto.ClaimForm;
import com.personal.accident.demo.dto.Premium;

import lombok.Data;

@Data
public class PolicyModel {

	private String p_no;
	
	@NotBlank(message = "name is required")
	private String firstName;
	
	@NotBlank(message = "name is required")
	private String lastName;
	
	private String name;
	private Date dob;
	private int age;
	private String phone;
	private String email;
	private String occupation;
	private String nrc;
	private String m_status;
	private int count;
	private Date start_date;
	private Date end_date;
	private String address;
	
	private String street;
	private String homeNo;
	private String state;
	private String division;
	
	private String year;
	private String totalamount;
	private String term;
	private Double payamount;
	
	
	private String b_name;
	private String b_address;
	private String b_email;
	private String b_phone;
	private String b_nrc;
	private String relationship;
	
	private String type;
	private String reason;
	private Double amount;
	private Date date;
	private String today;
	private String sdate;
	private String edate;
	private String place;
	
	private String status_checking;
	/* private List<ClaimForm> claim=new ArrayList<ClaimForm>(); */
	private Premium premium;
	
	private int count1;
	private int count2;
	private int claimCount;
	
	public List<ClaimModel> getClaim() {
		return claim;
	}
	public void setClaim(List<ClaimModel> claim) {
		this.claim = claim;
	}
	private List<ClaimModel> claim=new ArrayList<ClaimModel>();
	public int getCount1() {
		return count1;
	}
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	public int getCount2() {
		return count2;
	}
	public void setCount2(int count2) {
		this.count2 = count2;
	}

	
	
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Premium getPremium() {
		return premium;
	}
	public void setPremium(Premium premium) {
		this.premium = premium;
	}

	/*
	 * public List<ClaimForm> getClaim() { return claim; } public void
	 * setClaim(List<ClaimForm> claim) { this.claim = claim; }
	 */
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public Double getPayamount() {
		return payamount;
	}
	public void setPayamount(Double payamount) {
		this.payamount = payamount;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getStatus_checking() {
		return status_checking;
	}
	public void setStatus_checking(String status_checking) {
		this.status_checking = status_checking;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getM_status() {
		return m_status;
	}
	public void setM_status(String m_status) {
		this.m_status = m_status;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHomeNo() {
		return homeNo;
	}
	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_address() {
		return b_address;
	}
	public void setB_address(String b_address) {
		this.b_address = b_address;
	}
	public String getB_email() {
		return b_email;
	}
	public void setB_email(String b_email) {
		this.b_email = b_email;
	}
	public String getB_phone() {
		return b_phone;
	}
	public void setB_phone(String b_phone) {
		this.b_phone = b_phone;
	}
	public String getB_nrc() {
		return b_nrc;
	}
	public void setB_nrc(String b_nrc) {
		this.b_nrc = b_nrc;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}
	
}
