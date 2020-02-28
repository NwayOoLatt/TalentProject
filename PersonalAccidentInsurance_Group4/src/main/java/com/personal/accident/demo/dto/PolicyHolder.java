package com.personal.accident.demo.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class PolicyHolder {

	@Id
	private String p_no;
	private String firstName;
	private String lastName;
	private String dob;
	private int age;
	private String start_date;
	private String end_date;


	private String nrc;
	private String martialstatus;
	private String phone;
	private String email;
	private String occupation;
	private String gender;
	
	private String street;
	private String homeNo;
	private String state;
	
	
	private String status_checking;
	private String reject;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "policyholder")
	private List<ClaimForm> claim;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	@JoinColumn(name = "premium_id", referencedColumnName = "id")
	private Premium premium;

	@OneToOne
	private Beneficiary beneficiary;	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "policyholder")
	private List<Payment> payment;	

	
	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public List<ClaimForm> getClaim() {
		return claim;
	}

	public void setClaim(List<ClaimForm> claim) {
		this.claim = claim;
	}

	

	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Premium getPremium() {
		return premium;
	}

	public void setPremium(Premium premium) {
		this.premium = premium;
	}

	public String getStatus_checking() {
		return status_checking;
	}

	public void setStatus_checking(String status_checking) {
		this.status_checking = status_checking;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getReject() {
		return reject;
	}

	public void setReject(String reject) {
		this.reject = reject;
	}
	
	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getMartialstatus() {
		return martialstatus;
	}

	public void setMartialstatus(String martialstatus) {
		this.martialstatus = martialstatus;
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

	

	public PolicyHolder() {
		super();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		return "PolicyHolder [p_no=" + p_no + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", age=" + age + ", occupation=" + occupation + "]";
	}

	public PolicyHolder(String p_no, String firstName, String lastName, String dob, int age,
			String occupation) {
		
		super();
		this.p_no = p_no;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.age = age;
		this.occupation = occupation;
		
	}

}
