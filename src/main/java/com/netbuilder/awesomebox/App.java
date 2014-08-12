package com.netbuilder.awesomebox;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Awesome Box!" );
        
        System.out.println("Creating an entity manager.");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("awesomebox");
        
        EntityManager em = emf.createEntityManager();
        
        ArtistService as = new ArtistService(em);
        
        List<Artist> list = populateCategoryList();
        
        as.persistArtistList(list);
        
        as.listArtists();
        
        if(em != null){
        	System.out.println("Entity manager created successfully");
        }
        
        
        
        em.close();
        
        System.out.println("Finished");
    }
    
    private static List<Artist> populateCategoryList(){
    	List<Artist> list = new ArrayList<Artist>();
    	
    	list.add(new Artist( "Bon Jovi", 5 ));
    	list.add(new Artist( "MAGIC!", 4 ));
    	list.add(new Artist( "Dragonforce", 4 ));
    	list.add(new Artist( "Green Day", 2 ));
    	list.add(new Artist( "Lady Gaga", 2 ));
    	list.add(new Artist( "Britbrit", 5 ));
    	
    	return list;
    }
}
