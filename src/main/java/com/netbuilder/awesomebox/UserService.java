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
	
	public void updateUser(User User,String password,int credits, boolean isAdmin) {
		em.getTransaction().begin();
		String query = "UPDATE User SET password = \'" + password + "\', credits = " + credits + ", isAdmin = " + isAdmin + " WHERE id = " + User.getId();
		em.createQuery(query);
		User.setPassword(password);
		User.setCredits(credits);
		User.setAdmin(isAdmin);
		em.getTransaction().commit();
	}
	
	public void deleteUser(User User) {
		em.getTransaction().begin();
		String query = "DELETE FROM User WHERE id = " + User.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
		em.getTransaction().commit();
	}

}
