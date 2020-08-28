/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nicol
 */
public class EntityTested {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer c1 = new Customer("J.K.", "Rowling");
        Customer c2 = new Customer("Georg", "R. R. Martin");
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
            //Verify that customers are managed and has been given a database id
            System.out.println(c1.getId());
            System.out.println(c2.getId());

        } finally {
            em.close();
        }
        System.out.println(c1.toString());
    }
    
}
