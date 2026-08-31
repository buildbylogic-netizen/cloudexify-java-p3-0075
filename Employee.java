package com.mycompany.smartemployeemanagementsystem.model;

import com.mycompany.smartemployeemanagementsystem.abstractclass.Person;
import com.mycompany.smartemployeemanagementsystem.enums.EmployeeStatus;
import java.io.Serializable;

public class Employee extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int employeeId;
    private String department;
    private String designation;
    private double salary;
    private EmployeeStatus status;

    public Employee() {
    }

    public Employee(int employeeId, String name, String phone,
                    String email, String department,
                    String designation, double salary,
                    EmployeeStatus status) {

        super(name, phone, email);

        this.employeeId = employeeId;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return employeeId + " - " + getName() + " - "
                + department + " - " + designation;
    }
}
