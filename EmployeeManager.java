package com.mycompany.smartemployeemanagementsystem.service;

import com.mycompany.smartemployeemanagementsystem.model.Employee;

import java.io.*;
import java.util.ArrayList;

public class EmployeeManager {

    private ArrayList<Employee> employees;

    private static final String FILE_NAME = "employees.dat";

    public EmployeeManager() {
        employees = new ArrayList<>();
        loadData();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveData();
    }

    public Employee searchEmployee(int employeeId) {

        for (Employee employee : employees) {

            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }

        return null;
    }
    public boolean removeEmployee(int employeeId) {

        Employee employee = searchEmployee(employeeId);

        if (employee != null) {

            employees.remove(employee);
            saveData();

            return true;
        }

        return false;
    }

    public boolean updateEmployee(Employee updatedEmployee) {

        Employee existingEmployee =
                searchEmployee(updatedEmployee.getEmployeeId());

        if (existingEmployee != null) {

            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setPhone(updatedEmployee.getPhone());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDepartment(
                    updatedEmployee.getDepartment()
            );
            existingEmployee.setDesignation(
                    updatedEmployee.getDesignation()
            );
            existingEmployee.setSalary(
                    updatedEmployee.getSalary()
            );
            existingEmployee.setStatus(
                    updatedEmployee.getStatus()
            );

            saveData();

            return true;
        }

        return false;
    }

    public void saveData() {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            output.writeObject(employees);

        } catch (IOException e) {

            System.out.println(
                    "Error saving employee data: "
                    + e.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            employees =
                    (ArrayList<Employee>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {

            employees = new ArrayList<>();

            System.out.println(
                    "Error loading employee data: "
                    + e.getMessage()
            );
        }
    }
}
