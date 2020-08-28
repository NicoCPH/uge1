/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entitys.customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nicol
 */
public class CustomerFacade {
   private static final EntityManagerFactory emf =  Persistence.createEntityManagerFactory("pu");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        customer CLS = new customer("Hansen", "Lykkevej 1");
        em.getTransaction().begin();
        em.persist(CLS);
        em.getTransaction().commit();
        
        
       
        
    }
 
}
