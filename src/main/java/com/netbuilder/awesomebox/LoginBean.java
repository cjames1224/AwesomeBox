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

    private static final long serialVersionUID = 5443351151396868724L;
	private String username;
	private String password;
	private int credits;
	private boolean loginStatus;
	
	@Inject
	private EntityManager em;
	private String login = null;
	
	public LoginBean(){
		//em = Persistence.createEntityManagerFactory("awesomebox").createEntityManager();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCredits() {
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.username =\'" + username + "\'",
				User.class).getResultList();
		return list.get(0).getCredits();
	}

	
	public String getLoginStatus(){
	
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.username =\'" + username + "\'",
				User.class).getResultList();
		if(list == null || list.size() == 0){
			//throw new ValidationException("Username cannot be found");
			setLoginStatus(false);
			return "login";
		}
		
		for(User u : list){
			if(u.getPassword().equals(this.password)){
				setLoginStatus(true);
				return "profile";
			}
		}
		//throw new ValidationException("No such User with the password you've input.");
		setLoginStatus(false);
		return "login";
		
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
