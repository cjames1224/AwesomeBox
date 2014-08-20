package com.netbuilder.awesomebox;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Produces
    @PersistenceContext(unitName = "awesomebox")
    private EntityManager em;
    
    
    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
       return FacesContext.getCurrentInstance();
    }
}
