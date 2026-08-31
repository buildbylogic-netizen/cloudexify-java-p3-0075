package com.mycompany.smartemployeemanagementsystem.util;

import com.mycompany.smartemployeemanagementsystem.model.Employee;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "employees.dat";

    public static void saveEmployees(ArrayList<Employee> employees) {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            output.writeObject(employees);

        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> loadEmployees() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (ArrayList<Employee>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
