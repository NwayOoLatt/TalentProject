package com.personal.accident.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.service.ProposalService;

@Named
@ViewScoped
public class StatusCheckingController {

	@Autowired 
	ProposalService proposalservice;
	
	Proposal p=new Proposal();

	List<Proposal> plist=new ArrayList<Proposal>();
	
	public String statusCheck() {
		System.out.println("statuscheck");
		
		p.setStatusflag(true);
		p.setPolicyflag(false);
		
		try {
		plist=proposalservice.statusChecking(1);
	
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e);
			
		}
		return "checking.xhtml?faces-redirect=true";
		
	}


	public List<Proposal> getPlist() {
		return plist;
	}

	public void setPlist(List<Proposal> plist) {
		this.plist = plist;
	}
	
	public Proposal getP() {
		return p;
	}


	public void setP(Proposal p) {
		this.p = p;
	}

	
}
