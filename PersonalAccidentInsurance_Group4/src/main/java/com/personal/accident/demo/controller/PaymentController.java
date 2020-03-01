package com.personal.accident.demo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.personal.accident.demo.model.PaymentModel;
import com.personal.accident.demo.model.Proposal;
import com.personal.accident.demo.service.PaymentServiceImpl;

@Named
@ViewScoped
public class PaymentController {

	@Autowired
	PaymentServiceImpl payservice;
	
	@Autowired
	ProposalController pControl;
	
	private PaymentModel paymodel = new PaymentModel();	
	private ProposalController p=new ProposalController();

	public String getPayment(Proposal pro) {
		try {
			paymodel.setP_no(pro.getP_no());
			
			paymodel.setAmount(pro.getAmount());
			
			}catch(NullPointerException e) {
				
				System.out.println(e);
				return "paymentform.xhtml?faces-redirect=true";
				
			}
		return "paymentform.xhtml?faces-redirect=true";
	}
	public String addPayment(Proposal pro) {
		
		try {
		paymodel.setP_no(pro.getP_no());
		paymodel.setAmount(pro.getAmount());
		
		}catch(NullPointerException e) {
			System.out.println(e);
			return "paymentform.xhtml?faces-redirect=true";
		}
		if (pro.getStatus_checking().equalsIgnoreCase("Approved")) {
			
			return "paymentform.xhtml?faces-redirect=true";
			
		}
		if(pro.getStatus_checking().equalsIgnoreCase("Complete")) {
			
			return "paymentform.xhtml?faces-redirect=true";
		}
		
		else {
				
			pControl.setInfoflag(true);
			pControl.setInfo("Can't add your payment at this time");
			
			System.out.println("can't pay");
			return "checking.xhtml?faces-redirect=true";
			
		}
		
		
	}
	public void paymentInfo() {
		
		System.out.println("-------savepay-------");
		System.out.println("pno"+paymodel.getP_no());
		
		Boolean flag=payservice.savePayment(paymodel);
		
		if(flag==true) {
			
			Boolean flag1=payservice.completeProposal(paymodel);
					
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Payment!", "info Messages"));

	}
	public void reset() {
		
		 paymodel = new PaymentModel();
	}
	
	 public PaymentModel getPaymodel() {
			return paymodel;
		}

		public void setPaymodel(PaymentModel paymodel) {
			this.paymodel = paymodel;
		}
		
		
}
