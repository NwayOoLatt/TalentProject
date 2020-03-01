package com.personal.accident.demo.controller;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.personal.accident.demo.model.UserModel;
import com.personal.accident.demo.service.UserServiceImpl;

@Named
@ViewScoped
public class UserController {

	@Autowired
	public UserServiceImpl userservice;

	@Autowired
	private ProposalController proposal;
	
	private UserModel userModel = new UserModel();

	List<UserModel> usrList = new ArrayList<UserModel>();
	
	Boolean infoflag;
	String info;
	
	

	public void save() {
		
		System.out.println("--------Name-----------" + userModel.getName());

		/* userModel.setUserId(userID); */
		Boolean flag=userservice.findMail(userModel);
		if(flag==true) {
			
			System.out.println("duplicate record");
			infoflag = true;
			info="Your Email account has been used!";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Email account has been used!", "info Messages"));
		}
		else {
			
		userservice.saveUser(userModel);
		infoflag = true;
		info="Successfully Registeration!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Registeration!", "info Messages"));
		}
		
		userModel = new UserModel();

	}
	
	public String requestLogin() {
		System.out.println("requestLoign");
		infoflag = false;
		return "/loginform.xhtml?faces-redirect=true";
	}
	
	public String userLogin() {

		System.out.println("----login----" + userModel.getName());
		System.out.println("----password----" + userModel.getPassword());
		
		usrList = new ArrayList<UserModel>();

		usrList = userservice.searchUser(userModel);

		
		System.out.println("userlist" + usrList);

		if (usrList.isEmpty()) {
			System.out.println("user doesn't exit");
			infoflag = true;
			info="Create Account! User doesn't exit.";
			
			
			return "/loginform.xhtml?faces-redirect=true";
		}

		else if (usrList.get(0).getPassword().equals(userModel.getPassword())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SuccessFul Login!", "info Messages"));
			System.out.println("success");

			System.out.println("---name---" + usrList.get(0).getName());

			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			
			  HttpSession session = (HttpSession)
			  facesContext.getExternalContext().getSession(true);
			  
			  session.setAttribute("user", usrList.get(0).getName());
			  session.setAttribute("id", usrList.get(0).getId());
			  
			
			
			userModel = new UserModel();
			proposal.idGenerate();
			return "/proposalInfo.xhtml?faces-redirect=true";

		} else {
			System.out.println("wrong password");
			infoflag = true;
			info="wrong password!";
			
			return "/loginform.xhtml?faces-redirect=true";

		}

	}
	
	public String userLogout() {
		
		try {
			  FacesContext fc = FacesContext.getCurrentInstance();
			  HttpSession session =
			  (HttpSession) fc.getExternalContext().getSession(true);
			  session.removeAttribute("user");
			  session.removeAttribute("id");
				
			  System.out.println("Logout"+session.getAttribute("user"));
			  
			}catch(ConcurrentModificationException e) {
				
				System.out.println("session fill"+e);
			}
		
		return "/home.xhtml?faces-redirect=true";
	}
	
	
	
	public ProposalController getProposal() {
		return proposal;
	}

	public void setProposal(ProposalController proposal) {
		this.proposal = proposal;
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
	public UserServiceImpl getUserservice() {
		return userservice;
	}

	public void setUserservice(UserServiceImpl userservice) {
		this.userservice = userservice;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public List<UserModel> getUsrList() {
		return usrList;
	}

	public void setUsrList(List<UserModel> usrList) {
		this.usrList = usrList;
	}
}
