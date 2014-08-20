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

	// getters and setters

	public String getUsername() {
		return loginBean.getUsername();
	}


	public LoginBean getLoginBean() {
		return loginBean;
	}



	

	

}
