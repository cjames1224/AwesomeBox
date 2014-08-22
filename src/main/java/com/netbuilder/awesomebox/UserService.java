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
	
	public void removeUserList(List<User> list){
		if (list == null) {
			throw new ValidationException("Invalid List");
		}
		for(User a : list){
			em.remove(em.merge(a));
		}
	}


	public void persistUserList(List<User> list) {
		if(list == null){
			throw new ValidationException("Invalid list");
		}
		
		for (User u : list) {
			em.persist(u);
			
		}
	}
	
	public void createAndPersistUser(String username, String password){
		User user = new User(username, password);
		em.persist(em.merge(user));
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
		
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.username=\'"+name+"\'",
				User.class).getResultList();
		
		if(list == null || list.size() == 0){
			throw new ValidationException("No such user with name = "+name);
		}
		
		//System.out.println( list.get(0).toString() );
		
		return list;
	}
	
	public void updateUser(User user){
		em.persist(em.merge(user));
	}
	
	public void deleteUser(User user) {
		if(user == null){
			throw new ValidationException("Invalid user delete");
		}
		
		em.remove(em.merge(user));
	}

}
