package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	static final long serialVersionUID = -3381107953172847546L;
	private String username;
	private String password, passwordValidation;
	private int credits;
	private boolean loginStatus;
	
	@Inject
	private EntityManager em;
	private String login = null;
	
	@Inject
	private UserService userService;
	
	public LoginBean(){
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public String login() {
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.username =\'" + username + "\'",
				User.class).getResultList();
		if(list == null || list.size() == 0){
			//throw new ValidationException("Username cannot be found");
			loginStatus = false;
			return "register";
		}
		
		for(User u : list){
			if(u.getPassword().equals(this.password)){
				loginStatus = true;
				return "profile";
			}
		}
		//throw new ValidationException("No such User with the password you've input.");
		loginStatus = false;
		return "login";
	}
	
	public String register(){
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.username =\'" + username + "\'",
				User.class).getResultList();
		if(password.equals(passwordValidation) && list.size() == 0){
			userService.createAndPersistUser(username, password);
			return "profile";
		}
		else{
			return "register";
		}
	}
	
	public boolean getLoginStatus(){
		

		return loginStatus;
		
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordValidation() {
		return passwordValidation;
	}

	public void setPasswordValidation(String passwordValidation) {
		this.passwordValidation = passwordValidation;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCredits() {
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.username =\'" + username + "\'",
				User.class).getResultList();
		return list.get(0).getCredits();
	}
	
	public void setCredits(int credits) {
		List<User> users = userService.getUser(username);
		User user = users.get(0);
		//user.setCredits(credits);
		user.setCredits(credits);
		userService.updateUser(user);
		this.credits = credits;
		
		
	}

	

	
	public String logout(){
		username = null;
		password = null;
		loginStatus = false;
		return "logout";
	}
	
	
//	test
//	public static void main(String[] args){
//		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("awesomebox");
//	        
//		 EntityManager em = emf.createEntityManager();
//		 
//		 Scanner scan = new Scanner(System.in);
//		 
//		 System.out.print("Input username\n>  ");
//		 
//		 LoginBean lb = new LoginBean(em);
//		 
//		 lb.setUserName(scan.nextLine());
//		 
//		 System.out.print("Input password\n>  ");
//		 
//		 lb.setPassword(scan.nextLine());
//		 
//		 System.out.println(lb.login());	 
//		 
//		 scan.close();
//		 
//	}
	
}
