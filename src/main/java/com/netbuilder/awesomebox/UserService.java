package com.netbuilder.awesomebox;

import java.util.List;

import javax.persistence.EntityManager;

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

}
