package tester;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Employee;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {
private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            Query query = em.createQuery("Select e.salary FROM Employee e WHERE e.salary > 100000");
            List<Employee> result = query.getResultList();
            System.out.println(result);
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            Query query1 = em.createQuery("Select e FROM Employee e WHERE e.id = :id");
            String id = "hyu654";
            query1.setParameter("id", id);
            Employee result2 = (Employee)query1.getSingleResult();
            System.out.println(result2);
            
            //3) Create a query to fetch the highest salary and print the value
            Query query2 = em.createQuery("Select MAX(e.salary) FROM Employee e");
            BigDecimal result3 = (BigDecimal)query2.getSingleResult();
            System.out.println(result3);
                   
            //4) Create a query to fetch the firstName of all Employees and print the names
           Query query3 = em.createQuery("Select e.firstName FROM Employee e");
            List<String> result4 = query3.getResultList();
            System.out.println(result4);
            
            //5 Create a query to calculate the number of employees and print the number
            Query query4 = em.createQuery("Select e.firstName, e.lastName FROM Employee e");
            List<Object[]> result5 = query4.getResultList();
            System.out.println(result5.size());
            
           
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query query5 = em.createQuery("Select MAX(e.salary) FROM Employee e");
            Query query6 = em.createQuery("Select e.firstName, e.lastName FROM Employee e where e.salary = :salary");
            BigDecimal result7 = (BigDecimal)query5.getSingleResult();
            query6.setParameter("salary", result7);
          List<Object[]> result8 = query6.getResultList();
            System.out.println(GSON.toJson(result8));
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
