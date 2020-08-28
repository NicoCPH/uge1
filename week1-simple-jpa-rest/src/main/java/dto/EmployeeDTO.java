/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Nicol
 */
public class EmployeeDTO {
    private Long id;
    private String name;
    private String adress;
    private int salary;

    public EmployeeDTO(entities.Employee e1) {
        this.id = e1.getId();
        this.name = e1.getName();
        this.adress = e1.getAdress();
        this.salary = e1.getSalary();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
            
}
