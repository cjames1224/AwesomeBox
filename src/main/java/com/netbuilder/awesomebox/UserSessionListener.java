package com.netbuilder.awesomebox;

import java.util.Enumeration;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener, ServletContextListener {

	 private static int totalActiveSessions;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		totalActiveSessions++;
		System.out.println("I've noticed the user create the session. Current sessions open: "+totalActiveSessions);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		totalActiveSessions--;
		System.out.println("I've noticed the user destroy the session.");
		//hse.getSession().invalidate();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("I've noticed the user destroy the context.");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	            .getExternalContext().getSession(false);
		session.invalidate();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("I've noticed the user create the context.");
		// TODO Auto-generated method stub
		
	}


}
