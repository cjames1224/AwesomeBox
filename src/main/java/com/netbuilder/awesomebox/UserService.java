package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserService {
	private EntityManager em;
	
	public UserService(EntityManager em) {
		this.em = em;
	}
	
	public void persistUserList(List<User> list) {
		if(list == null){
			throw new ValidationException("Invalid list");
		}
		
		em.getTransaction().begin();
		
		for (User u : list) {
			em.persist(u);
			
		}
		em.getTransaction().commit();
	}
	
	public void listUsers() {
		List<User> list = em.createQuery("SELECT u FROM User u",
				User.class).getResultList();
		
		for (User u : list) {
			System.out.println(u.toString());
		}
	}
	
	public void updateUser(User user,String password,int credits, boolean isAdmin) {
		if(user == null || password == null){
			throw new ValidationException("Invalid user update");
		}
		
		
		em.getTransaction().begin();
		String query = "UPDATE User SET password = \'" + password + "\', credits = " + credits + ", isAdmin = " + isAdmin + " WHERE id = " + user.getId();
		em.createQuery(query);
		user.setPassword(password);
		user.setCredits(credits);
		user.setAdmin(isAdmin);
		em.getTransaction().commit();
	}
	
	public void updateUserPassword(User user, String password) {
		updateUser(user, password, user.getCredits(), user.isAdmin());
	}
	
	public void updateUserCredits(User user, int credits) {
		updateUser(user, user.getPassword(), credits, user.isAdmin());
	}
	
	public void updateUserIsAdmin(User user, boolean isAdmin) {
		updateUser(user, user.getPassword(), user.getCredits(), isAdmin);
	}
	
	public void deleteUser(User user) {
		if(user == null){
			throw new ValidationException("Invalid user delete");
		}
		
		em.getTransaction().begin();
		String query = "DELETE FROM User WHERE id = " + user.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}

}
