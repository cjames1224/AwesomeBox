package com.netbuilder.awesomebox.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.netbuilder.awesomebox.services.LoginBean;


@Named
@SessionScoped
public class UserController implements Serializable {

	// attributes
	
	@Inject
	private LoginBean loginBean;

	// getters and setters

	public String getUsername() {
		return loginBean.getUsername();
	}


	public LoginBean getLoginBean() {
		return loginBean;
	}

	public String addCredit() {
		int currentCredits = loginBean.getCredits();
		loginBean.setCredits(currentCredits+1);
		return "profile";
	}


}
