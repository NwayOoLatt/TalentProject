package com.personal.accident.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.PolicyModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.service.PolicyService;


@Named
@ViewScoped
public class PolicyController {
	
	@Autowired 	
	PolicyService policyservice;
	
	@Autowired
	ProposalController proposal;
	
	private User u=new User();
	
	List<Proposal> plist=new ArrayList<Proposal>();
	PolicyModel pmodel=new PolicyModel();
	
	
	public int loginUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance(); HttpSession
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		int userID=(int) session.getAttribute("id");
		
		return userID;
	}

	public String policylisting() {
		
		proposal.setPolicyflag(false);
		System.out.println("----------------"+proposal.getPolicyflag());
		
		int userID=loginUser();
				
		try {
		plist=policyservice.proposalList(userID);
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		System.out.println("aaaaaaaaaaaa");
		/* System.out.println("statuscheck"+plist.get(0).getName()); */
		/* List<ClaimForm> clist=new ArrayList<ClaimForm>(); */
		
		return "policylist.xhtml?faces-redirect=true";
	}
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public List<Proposal> getPlist() {
		return plist;
	}

	public void setPlist(List<Proposal> plist) {
		this.plist = plist;
	}

	
	public ProposalController getProposal() {
		return proposal;
	}

	public void setProposal(ProposalController proposal) {
		this.proposal = proposal;
	}

	public PolicyModel getPmodel() {
		return pmodel;
	}

	public void setPmodel(PolicyModel pmodel) {
		this.pmodel = pmodel;
	}
	
	


}
