package com.netbuilder.awesomebox;

import java.util.List;
import java.util.Scanner;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Stateless
@Named
public class LoginBean {

	private String username;
	private String password;
	private EntityManager em;
	
	public LoginBean(){

	}
	
	public LoginBean(EntityManager em){
		this.em = em;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean login(){
	
		List<User> list = em.createQuery("SELECT u FROM User u WHERE username =\'" + username + "\'",
				User.class).getResultList();
		if(list == null || list.size() == 0){
			throw new ValidationException("Username cannot be found");
		}
		
		for(User u : list){
			if(u.getPassword().equals(this.password)){
				System.out.println("Logged in successfully!");
				return true;
			}
		}
		throw new ValidationException("No such User with the password you've input.");
		
	}
	
	public void logout(){
		
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
