package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {
   private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");     
    private static EmployeeFacade instance;
   private static EmployeeFacade facade = EmployeeFacade.getFacadeExample(emf);
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    public static void main(String[] args) {
    Employee b1 = facade.creatEmployee("lange", "kvarter", 123);
    Employee b2 = facade.creatEmployee("lang", "kvarter", 1266);
    //Find Employee by ID
    System.out.println("employee1: "+facade.findEmployee(b1.getId()).getName());
    System.out.println("employee2: "+facade.findEmployee(b2.getId()).getName());
    //find empolyee by name
     System.out.println("employee2: "+facade.findEmploye(b2.getName()).getAdress() + b2.getName());
    //Find all Employees
    System.out.println("Number of Employees: "+facade.getAllEmployees().size());
    
     System.out.println("ID: " + facade.findhHigstSalary().getId() + " - Firstname: " + facade.findhHigstSalary().getName() + " - Salery: " + facade.findhHigstSalary().getSalary());
    

    }
 
    
  

    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
 public Employee creatEmployee(String name, String adress, int salary){
        Employee e1 = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(e1);
            em.getTransaction().commit();
            return e1;
        }finally {
            em.close();
        }
    }
    
    public EmployeeDTO findEmployee(Long id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee e2 = em.find(Employee.class,id);
             EmployeeDTO e1 = new EmployeeDTO(e2);
            return e1;
        }finally {
            em.close();
        }
    }
      public EmployeeDTO findEmploye(String name){
      EntityManager em = emf.createEntityManager();
        try{
            Query query1 = em.createQuery("Select e FROM Employee e WHERE e.Name = :NAME");
            query1.setParameter("NAME", name);
             query1.setMaxResults(1);
            Employee result2 = (Employee)query1.getSingleResult();
             EmployeeDTO e2 = new EmployeeDTO(result2);
            return e2;
        }finally {
            em.close();
        }
    }
    public List<EmployeeDTO> getAllEmployees(){
         EntityManager em = emf.createEntityManager(); 
        
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select e FROM Employee e",Employee.class);
             List<EmployeeDTO> pdtolist = new ArrayList();
             List<Employee> employees =  query.getResultList();
            employees.stream().forEach(p->{pdtolist.add(new EmployeeDTO(p));});
            return pdtolist;
        }finally {
            em.close();
        }
    }
    
  public EmployeeDTO findhHigstSalary(){
      EntityManager em = emf.createEntityManager();
        try{
            Query query6 = em.createQuery("Select e FROM Employee e ORDER BY e.salary DESC");
            query6.setMaxResults(1);
            Employee result6 =(Employee)query6.getSingleResult();
            EmployeeDTO e1 = new EmployeeDTO(result6);
            return e1;
        }finally {
            em.close();
        }
    }
}