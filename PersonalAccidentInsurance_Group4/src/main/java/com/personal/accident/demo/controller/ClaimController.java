package com.personal.accident.demo.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.personal.accident.demo.dto.Category;
import com.personal.accident.demo.dto.ClaimForm;
import com.personal.accident.demo.model.CategoryModel;
import com.personal.accident.demo.model.ClaimModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.service.CategoryService;
import com.personal.accident.demo.service.ClaimServiceImpl;
import com.personal.accident.demo.service.PolicyService;

@Named
@ViewScoped
public class ClaimController {

	@Autowired
	ClaimServiceImpl claimservice;
	
	@Autowired
	CategoryService categoryservice;
	
	@Autowired 	
	PolicyService policyservice;

	private HashMap<String, Integer> category;
	private HashMap<Integer, Integer> categoryid;
	
	/* private ClaimModel cmodel = new ClaimModel(); */
	private Proposal pmodel = new Proposal();

	List<ClaimModel> clist = new ArrayList<ClaimModel>();
	List<Proposal> plist = new ArrayList<Proposal>();
	
	Boolean flag = false;
	Boolean bflag = false;
	Boolean cflag = false;
	Boolean noflag = false;
	Boolean oflag = false;
	Boolean eflag = false;
	Boolean policyflag=false;
	
	Double sumInsured = 0.0;
	Double currentAmount = 0.0;
	Double total_claimAmount = 0.0;
	Double remainbalance = 0.0;
	
	String sumInsured1="0.0";
	String currentAmount1="0.0";
	String total_claimAmount1="0.0";
	String remainbalance1="0.0";
	String type;
	
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy"); // date format
	DecimalFormat decimalFormat = new DecimalFormat("0.0"); // decimal format
	
	// Claim Category
	
	@PostConstruct
	public void init() {

		category = new HashMap<String, Integer>(); 
		categoryid=new HashMap<Integer, Integer>();
		
		List<CategoryModel> catList=new ArrayList<>();
		catList=categoryservice.allCategory();
		
		for(int i=0;i<catList.size();i++) { // Get Claim Type and percent
			
			category.put(catList.get(i).getType(),catList.get(i).getId());
			categoryid.put(catList.get(i).getId(),catList.get(i).getPercentage());
			System.out.println(catList.get(i).getType()+"\t"+catList.get(i).getId());
		}
		
	}
	
	public List<String> pnoList(String query) {
		
		List<String> results = new ArrayList<>();
		int userID=loginUser();
		
		try {
		plist=policyservice.proposalList(userID);
		
		if(plist.isEmpty()) {
			System.out.println("policy is empty");
		}else {
			
		for(int i = 0; i < plist.size(); i++) {
            results.add(plist.get(i).getP_no());
        }
		}
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
        
         
        return results;
	}
	
	public int loginUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); HttpSession
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		int userID=(int) session.getAttribute("id");
		
		return userID;
	}
	public String claimForm() {
		
		bflag = false;
		cflag = false;
		noflag = false;
		oflag = false;
		eflag = false;
		flag=false;
		policyflag=false;
		pmodel=new Proposal();
		
		return "claimform.xhtml?faces-redirect=true";
	}
	public String claimRequest() {

		bflag = false;
		cflag = false;
		noflag = false;
		oflag = false;
		eflag = false;
		policyflag=false;
		
		int userID=loginUser();
				
		System.out.println("claim request"+pmodel.getP_no());
		
		plist = claimservice.findPolicy(pmodel,userID); // find policy
		
		
		pmodel=new Proposal();
		pmodel.setP_no(null);
		if (plist.isEmpty()) {

			System.out.println("empty proposal");
			flag = false;
			policyflag=true;
			
		} else {

			if (plist.get(0).getStatus_checking().equals("Complete")) {
				flag = true;
				System.out.println("flag" + flag);
				pmodel = plist.get(0);
				Date date = Calendar.getInstance().getTime();

				String strDate = dateFormat.format(date);
				System.out.println("Converted String: " + strDate);
				System.out.println(strDate);
				pmodel.setToday(strDate);

			}
			
		}

		return "claimform.xhtml?faces-redirect=true";

	}

	public String getClaim() throws ParseException {
		System.out.println("**********claim*********");
		try {
			int c=0;
		
			/*
			 * int id= Integer.parseInt(pmodel.getType()); c=getKey(categoryid, id);
			 */
		c=categoryid.get(Integer.parseInt(pmodel.getType()));
		System.out.println("**claim type"+c);
		System.out.println("**claim id"+pmodel.getType());
		type=getKey(category, Integer.parseInt(pmodel.getType())); //get claim type category
		
		sumInsured = pmodel.getTotalamount(); // sum insured
		currentAmount = sumInsured * (Math.abs(c * 0.01)); // request claim amount
		
		sumInsured1 = decimalFormat.format(pmodel.getTotalamount()); // sum insured
		currentAmount1 = decimalFormat.format(sumInsured * (Math.abs(c * 0.01))); // request claim amount
		
		
		System.out.println(c);
			/* pmodel.setType(getKey(category, c)); */
		pmodel.setC_id(Integer.parseInt(pmodel.getType()));
		
		System.out.println("Claim ID"+pmodel.getC_id());
		System.out.println("claim type:"+pmodel.getType());
		
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		String edate = pmodel.getEdate();

		System.out.println(pmodel == null);

		System.out.println(edate + "\t" + pmodel.getToday());

		Date d1 = dateFormat.parse(pmodel.getToday());
		Date d2 = dateFormat.parse(edate);

		if (d1.compareTo(d2) == 0 || d1.compareTo(d2) > 0) { // check expired date of policy

			System.out.println("-------Expired-------");
			eflag = true;
			flag = false;

		} else {

			System.out.println("-------No Expired-------");

			if (plist.get(0).getClaim().isEmpty()) {

				/*
				 * System.out.println("claim----" + c); System.out.println("Claim2----" +
				 * getKey(category, c)); System.out.println("claimamount---" + currentAmount);
				 */
				if (type.equals("death")) {

					System.out.println("------beneficiary--------");// show alert
					pmodel.setAmount(currentAmount);
					remainbalance = pmodel.getTotalamount();
					remainbalance1=decimalFormat.format(remainbalance);
					System.out.println("claim"+pmodel.getType());
					claimservice.saveClaim(pmodel);
					flag = false;
					bflag = true;
					
				} else {

					pmodel.setAmount(currentAmount);
					remainbalance = pmodel.getTotalamount();
					remainbalance1=decimalFormat.format(remainbalance);
					total_claimAmount1="0.0";
					System.out.println("claim"+pmodel.getType());
					claimservice.saveClaim(pmodel);
					flag = false;
					cflag = true;
				}

			} else {

				List<ClaimModel> clist = plist.get(0).getClaim(); // claim
				System.out.println("claim pno"+plist.get(0).getP_no());
				
				total_claimAmount = 0.0;
				total_claimAmount1 = "0.0";
				
				for (int i = 0; i < clist.size(); i++) {

					total_claimAmount += clist.get(i).getAmount(); // spent amount
					total_claimAmount1=decimalFormat.format(total_claimAmount);
					
					System.out.println("((((spent)))" + total_claimAmount);
					remainbalance = pmodel.getTotalamount() - total_claimAmount; // remain balance
					remainbalance1=decimalFormat.format(remainbalance);
					System.out.println(clist.get(i).getType() + "\t" + clist.get(i).getAmount());

					if (clist.get(i).getType().equals("death")) {

						System.out.println("recent claim:" + currentAmount);
						System.out.println("total" + total_claimAmount);
						System.out.println("remainbalance" + remainbalance);
						System.out.println("no more claim1111111");
						flag = false;
						oflag = true;
						return "claimform.xhtml?faces-redirect=true";

					}
				}
				
				if (remainbalance > 0) {

					if (pmodel.getType().equals("death")) {

						System.out.println("------beneficiary2222--------");// show alert
						pmodel.setAmount(remainbalance);
						claimservice.saveClaim(pmodel);
						flag = false;
						bflag = true;

					} else {

						if (remainbalance < currentAmount) {

							System.out.println("recent claim:" + currentAmount);
							System.out.println("total" + total_claimAmount);
							System.out.println("remainbalance" + remainbalance);
							System.out.println("Get Claim");
							
							pmodel.setAmount(remainbalance);
							System.out.println("claim"+pmodel.getType());
							
							claimservice.saveClaim(pmodel);
							flag = false;
							noflag = true;

						} else {

							System.out.println("ok");
							pmodel.setAmount(currentAmount);
							System.out.println("claim"+pmodel.getType());
							claimservice.saveClaim(pmodel);
							flag = false;
							cflag = true;
						}
					}
				} else {
					flag = false;
					oflag = true;
					System.out.println("out of balance");
				}

			}

		}
		/* claimservice.findClaim(pmodel); */

		return "claimform.xhtml?faces-redirect=true";
	}

	// to get map's key from value
	public static <K, V> K getKey(Map<K, V> map, V value) {

		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;

	}

	public Proposal getPmodel() {
		return pmodel;
	}

	public void setPmodel(Proposal pmodel) {
		this.pmodel = pmodel;
	}

	/*
	 * public ClaimModel getCmodel() { return cmodel; }
	 * 
	 * public void setCmodel(ClaimModel cmodel) { this.cmodel = cmodel; }
	 */

	public HashMap<String, Integer> getCategory() {
		return category;
	}

	public void setCategory(HashMap<String, Integer> category) {
		this.category = category;
	}

	public List<ClaimModel> getClist() {
		return clist;
	}

	public void setClist(List<ClaimModel> clist) {
		this.clist = clist;
	}

	public List<Proposal> getPlist() {
		return plist;
	}

	public void setPlist(List<Proposal> plist) {
		this.plist = plist;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getBflag() {
		return bflag;
	}

	public void setBflag(Boolean bflag) {
		this.bflag = bflag;
	}

	public Boolean getCflag() {
		return cflag;
	}

	public void setCflag(Boolean cflag) {
		this.cflag = cflag;
	}

	public Boolean getNoflag() {
		return noflag;
	}

	public void setNoflag(Boolean noflag) {
		this.noflag = noflag;
	}

	public Double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Double getTotal_claimAmount() {
		return total_claimAmount;
	}

	public void setTotal_claimAmount(Double total_claimAmount) {
		this.total_claimAmount = total_claimAmount;
	}

	public Double getRemainbalance() {
		return remainbalance;
	}

	public void setRemainbalance(Double remainbalance) {
		this.remainbalance = remainbalance;
	}

	public Boolean getOflag() {
		return oflag;
	}

	public void setOflag(Boolean oflag) {
		this.oflag = oflag;
	}

	public Boolean getEflag() {
		return eflag;
	}

	public void setEflag(Boolean eflag) {
		this.eflag = eflag;
	}

	public Boolean getPolicyflag() {
		return policyflag;
	}

	public void setPolicyflag(Boolean policyflag) {
		this.policyflag = policyflag;
	}

	public String getSumInsured1() {
		return sumInsured1;
	}

	public void setSumInsured1(String sumInsured1) {
		this.sumInsured1 = sumInsured1;
	}

	public String getCurrentAmount1() {
		return currentAmount1;
	}

	public void setCurrentAmount1(String currentAmount1) {
		this.currentAmount1 = currentAmount1;
	}

	public String getTotal_claimAmount1() {
		return total_claimAmount1;
	}

	public void setTotal_claimAmount1(String total_claimAmount1) {
		this.total_claimAmount1 = total_claimAmount1;
	}

	public String getRemainbalance1() {
		return remainbalance1;
	}

	public void setRemainbalance1(String remainbalance1) {
		this.remainbalance1 = remainbalance1;
	}

	
}
