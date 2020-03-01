package com.personal.accident.demo.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.model.UserModel;
import com.personal.accident.demo.service.ProposalService;
import com.personal.accident.demo.service.ProposalServiceImpl;

@Named
@ViewScoped
public class ProposalController {

	@Autowired

	private ProposalService proposalservice;

	List<Proposal> plist = new ArrayList<Proposal>();

	private Proposal proposal = new Proposal();
	
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	DecimalFormat decimalFormat = new DecimalFormat("#,##0.0");
	
	Boolean flag = false;
	Boolean pflag = false;
	Boolean confirmflag = false;
	Boolean policyflag = false;
	String confirm;
	
	
	Double total;
	int term;
	Double amount;
	String proid;
	String startdate;
	String enddate;
	String dob;
	String address;
	Boolean check;
	Boolean infoflag;
	String info;
	String premiumamount;

	public int loginUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		int userID = 0;
		try {
		userID = (int) session.getAttribute("id");
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		return userID;
	}

	public String idGenerate() {

		confirmflag = false;
		proposal = new Proposal();
		amount = 0.0;
		premiumamount="";
		String p_no = proposalservice.getProposalID(); // get Max proposal ID
		proposal = new Proposal();

		try {
			if (!p_no.isEmpty()) {

				// Generate ID;
				String idNo = p_no.substring(3, 7);
				System.out.println("splitID= " + idNo);

				int id = Integer.parseInt(idNo);
				id++;
				System.out.println("count= " + id);
				if (id < 10) {

					proid = "P00000" + id;

				} else if (id < 100) {

					proid = "P0000" + id;

				} else if (id < 1000) {

					proid = "P000" + id;

				} else if (id < 10000) {

					proid = "P00" + id;

				} else if (id < 100000) {

					proid = "P0" + id;

				} else if (id < 1000000) {

					proid = "P" + id;

				}

			} else {
				proid = "P00000" + 1;
			}

		} catch (NullPointerException e) {

			proid = "P00000" + 1;
			System.out.println("Empty Id");

		}

		return "proposalInfo.xhtml?faces-redirect=true";

	}

	
	// Calculate Age from BirthDate
	public void getAge(SelectEvent event) {
		
		System.out.println("getAge");
		Date dob=proposal.getDob();
		
		Calendar c = Calendar.getInstance();
		  c.setTime(dob);
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  int date = c.get(Calendar.DATE);
		  LocalDate l1 = LocalDate.of(year, month, date);
		  LocalDate now1 = LocalDate.now();
		  Period diff1 = Period.between(l1, now1);
		  proposal.setAge(diff1.getYears());
		  System.out.println("age:" + diff1.getYears() + "years");
	 
		
		
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	
	public String holderInfo() {

		System.out.println("-------save1-------");
		proposal.setName(proposal.getFirstName() + proposal.getLastName());

		Date d = proposal.getStart_date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		startdate = dateFormat.format(d);
		System.out.println("start date" + startdate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();
		System.out.println("today=" + today + "next Year" + nextYear);
		enddate = dateFormat.format(nextYear);
		proposal.setEnd_date(nextYear);

		dob = dateFormat.format(proposal.getDob());
		address = proposal.getHomeNo() + "/ " + proposal.getStreet() + "/ " + proposal.getState();
		pflag = false;
		
		return "premium.xhtml?faces-redirect=true";

	}

	public String holderPremium() {
		/* "#,##0.000" */
		pflag = false;
		
		System.out.println("-------save2-------");
		try {
			total = Math.abs(proposal.getTotalamount());
			System.out.println(total);
			term = Integer.parseInt(proposal.getTerm());
			System.out.println(term);
			amount = (total * term) / 12;
			System.out.println("----amount---" + decimalFormat.format(amount));
			proposal.setPayamount(amount);
			premiumamount=decimalFormat.format(amount); // invoke decimal format
			
			pflag = true;
		} catch (NullPointerException e) {

		}
		System.out.println("flag" + flag);
		return "premium.xhtml?faces-redirect=true";

	}

	public String benefit() {

		System.out.println("-------save3-------");
		total = Math.abs(proposal.getTotalamount());
		System.out.println(total);
		term = Integer.parseInt(proposal.getTerm());
		System.out.println(term);
		amount = (total * term) / 12;
		proposal.setPayamount(amount);

		proposal.setP_no(proid);
		confirmflag = false;
		return "proposalConfirm.xhtml?faces-redirect=true";

	}

	
	public void confirm() {

		int userID = loginUser();
		System.out.println(userID);
		
		if (check == true) {
			Boolean idflag=proposalservice.searchById(proposal);
			if(idflag==true) {
				confirmflag = true;
				confirm="Your Proposal is already registered!";
			}else {
			Boolean flag = proposalservice.saveProposal(proposal, userID);
			System.out.println("successfully register" + flag);
			confirmflag = true;
			confirm="Your Proposal registration is successfull !";
			}
			

		}

		else {
			confirmflag = true;
			confirm="Can't register without accepting your term and policy";
			System.out.println("register fill");
		}

		System.out.println("successfully register");

	}

	public void updateConfirm() {

		int userID = loginUser();
		System.out.println(userID);

		if (check == true) {
			Boolean flag = proposalservice.updateProposal(proposal, userID);
			confirmflag = true;

			
		} else {

			System.out.println("register fill");

		}

	}

	// status checking
	public String statusCheck() {
		System.out.println("statuscheck");
		infoflag = false;
		int userID = loginUser();
		System.out.println(userID);

		policyflag = true;
		confirmflag = true;
		confirm="Editing your proposal is successful! ";
		System.out.println("----------------" + proposal.getPolicyflag());

		try {
			plist = proposalservice.statusChecking(userID); // invoke status checking service
			System.out.println("status checkkkkkkk");

		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);

		}
		return "checking.xhtml?faces-redirect=true";

	}

	// Update Proposal
	public String editProposal(Proposal pro) throws ParseException {

		if (pro.getStatus_checking().equalsIgnoreCase("pending")) {
			System.out.println("pending");
			proposal = pro;
			proid = proposal.getP_no();
			premiumamount=String.valueOf(proposal.getAmount());
			proposal.setDob(dateFormat.parse(proposal.getDateofbirth())); //set date of birth
			proposal.setStart_date(dateFormat.parse(proposal.getSdate())); // set start date
			
			System.out.println("premium amount:"+proposal.getAmount());
			
			System.out.println("pno" + proid);
			flag = true;

			return "proposalInfo.xhtml?faces-redirect=true";

		} else if (pro.getStatus_checking().equalsIgnoreCase("Approved")) {
			System.out.println("Approved");
			infoflag = true;
			info = "Can't Update Your Proposal ! Your Proposal has been Approved";
			return "checking.xhtml?faces-redirect=true";

		} else if (pro.getStatus_checking().equalsIgnoreCase("delete")) {

			System.out.println("delete");
			infoflag = true;
			info = "Can't Update Your Proposal ! Your Proposal has been Rejected";
			return "checking.xhtml?faces-redirect=true";
		} else {

			System.out.println("Complete");
			infoflag = true;
			info = "Can't Update Your Proposal ! Your Proposal is Completed";
			return "checking.xhtml?faces-redirect=true";
		}

	}

	public String detailProposal(Proposal pro) {

		proposal = pro;
		address = proposal.getHomeNo() + "/ " + proposal.getStreet() + "/ " + proposal.getState();
		proid = proposal.getP_no();
		flag = true;
		return "/proposalDetail.xhtml?faces-redirect=true";

	}

	public String detailPolicy(Proposal pro) {
		System.out.println("policy detail");
		proposal = pro;
		address = proposal.getHomeNo() + "/ " + proposal.getStreet() + "/ " + proposal.getState();
		proid = proposal.getP_no();
		flag = true;
		return "/policyDetail.xhtml?faces-redirect=true";

	}

	// Delete Proposal

	public String deleteProposal(Proposal p) {

		if (p.getStatus_checking().equalsIgnoreCase("pending")) {
			System.out.println("pending");

			Boolean flag = proposalservice.deleteProposal(p); // delete proposal
			statusCheck(); // status check list

			infoflag = true;
			info = "Your Proposal is successfully deleted!";

		} else if (p.getStatus_checking().equalsIgnoreCase("Approved")) {
			infoflag = true;
			info = "Can't delete! Your Proposal has been Approved.";
			System.out.println("Approved");
		} else if (p.getStatus_checking().equalsIgnoreCase("delete")) {
			infoflag = true;
			info = " Your Proposal has been Rejected!";
			System.out.println("delete");
		} else {
			infoflag = true;
			info = "Can't delete! Your Proposal has been Completed.";
		}
		return "checking.xhtml?faces-redirect=true";

	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public Boolean getPflag() {
		return pflag;
	}

	public void setPflag(Boolean pflag) {
		this.pflag = pflag;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public Boolean getConfirmflag() {
		return confirmflag;
	}

	public void setConfirmflag(Boolean confirmflag) {
		this.confirmflag = confirmflag;
	}

	public List<Proposal> getPlist() {
		return plist;
	}

	public void setPlist(List<Proposal> plist) {
		this.plist = plist;
	}

	public Boolean getInfoflag() {
		return infoflag;
	}

	public void setInfoflag(Boolean infoflag) {
		this.infoflag = infoflag;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Boolean getPolicyflag() {
		return policyflag;
	}

	public void setPolicyflag(Boolean policyflag) {
		this.policyflag = policyflag;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getPremiumamount() {
		return premiumamount;
	}

	public void setPremiumamount(String premiumamount) {
		this.premiumamount = premiumamount;
	}

}
