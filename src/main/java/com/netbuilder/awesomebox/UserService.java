package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Named
@Stateless
public class UserService implements Serializable {
	
	private static final long serialVersionUID = 5443351151396868724L;
	@Inject
	private EntityManager em;
	
	public List<User> getUserList() {
		return em.createQuery("SELECT u FROM User u",
				User.class).getResultList();
	}


	public void persistUserList(List<User> list) {
		if(list == null){
			throw new ValidationException("Invalid list");
		}
		
		for (User u : list) {
			em.persist(u);
			
		}
	}
	
	public List<User> listUsers() {
		List<User> list = em.createQuery("SELECT u FROM User u",
				User.class).getResultList();
		
		for (User u : list) {
			System.out.println(u.toString());
		}
		
		return list;
	}
	
	public List<User> getUser(long id){
		if(id < 0){
			throw new ValidationException("Invalid user id");
		}
		
		List<User> list = em.createQuery("SELECT u FROM User u WHERE id="+id,
				User.class).getResultList();
		
		if(list == null || list.size() == 0){
			throw new ValidationException("No such user with id = "+id);
		}
		for (User u : list) {
			System.out.println( u.toString() );
		}
		
		return list;
	}
	
	public List<User> getUser(String name){
		if(name == null){
			throw new ValidationException("Invalid user name");
		}
		
		List<User> list = em.createQuery("SELECT u FROM User u WHERE username=\'"+name+"\'",
				User.class).getResultList();
		
		if(list == null || list.size() == 0){
			throw new ValidationException("No such user with name = "+name);
		}
		
		System.out.println( list.get(0).toString() );
		
		return list;
	}
	
	public void updateUser(User user,String password,int credits, boolean isAdmin) {
		if(user == null || password == null){
			throw new ValidationException("Invalid user update");
		}
		
		
		String query = "UPDATE User SET password = \'" + password + "\', credits = " + credits + ", isAdmin = " + isAdmin + " WHERE id = " + user.getId();
		em.createQuery(query);
		user.setPassword(password);
		user.setCredits(credits);
		user.setAdmin(isAdmin);
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
		
		String query = "DELETE FROM User WHERE id = " + user.getId();
		Query q = em.createQuery(query);
		q.executeUpdate();
	}
	


}
