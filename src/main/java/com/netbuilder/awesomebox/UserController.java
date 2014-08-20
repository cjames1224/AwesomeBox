package com.netbuilder.awesomebox;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class UserController implements Serializable {

	// attributes
	
	@Inject
	private LoginBean loginBean;
	
	private boolean isLoggedIn = false;

	// getters and setters

	public String getUsername() {
		return loginBean.getUsername();
	}


	public LoginBean getLoginBean() {
		return loginBean;
	}


	public boolean getIsLoggedIn() {
		return (loginBean.getloginStatus().equals("logged in")) ;
	}

	

	

}
