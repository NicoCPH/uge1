/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicol
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
     public static CustomerFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
     
    public Customer addCustomer(String firstName, String lastName) {
        Customer customer = new Customer("J-P", "L-M");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    
    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }
    
    public Customer findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, lastName);
            return customer;
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createQuery("Select COUNT(c) from Customer c", Customer.class);
            return query.getMaxResults();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getBookFacade(emf);
        Customer c1 = facade.addCustomer("From facade", "1");
        Customer c2 = facade.addCustomer("From facade", "2");
        System.out.println(facade.getNumberOfCustomers());
        //Find customer by ID
       // System.out.println("Customer 1: " + facade.findByID(c1.getId()));
        //Find all customers
        //System.out.println("Number of customers: " + facade.getNumberOfCustomers());

    }


}
