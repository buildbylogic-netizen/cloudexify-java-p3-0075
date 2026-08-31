package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.enums.EmployeeStatus;
import com.mycompany.smartemployeemanagementsystem.model.Employee;
import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import java.awt.*;

public class AddEmployeeFrame extends JFrame {

    private EmployeeManager employeeManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField designationField;
    private JTextField salaryField;

    private JComboBox<EmployeeStatus> statusBox;

    public AddEmployeeFrame(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;

        setTitle("Add Employee");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        createForm();
    }

    private void createForm() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        JLabel titleLabel = new JLabel(
                "ADD NEW EMPLOYEE",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(
                new GridLayout(8, 2, 10, 12)
        );

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        departmentField = new JTextField();
        designationField = new JTextField();
        salaryField = new JTextField();

        statusBox = new JComboBox<>(
                EmployeeStatus.values()
        );

        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentField);

        formPanel.add(new JLabel("Designation:"));
        formPanel.add(designationField);

        formPanel.add(new JLabel("Salary:"));
        formPanel.add(salaryField);

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusBox);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("ADD EMPLOYEE");
        JButton clearButton = new JButton("CLEAR");
        JButton closeButton = new JButton("CLOSE");

        addButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        clearButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        closeButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addEmployee());

        clearButton.addActionListener(e -> clearFields());

        closeButton.addActionListener(e -> dispose());

        add(mainPanel);
    }

    private void addEmployee() {

        try {

            if (idField.getText().trim().isEmpty()
                    || nameField.getText().trim().isEmpty()
                    || phoneField.getText().trim().isEmpty()
                    || emailField.getText().trim().isEmpty()
                    || departmentField.getText().trim().isEmpty()
                    || designationField.getText().trim().isEmpty()
                    || salaryField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int employeeId = Integer.parseInt(
                    idField.getText().trim()
            );

            double salary = Double.parseDouble(
                    salaryField.getText().trim()
            );

            if (salary < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Salary cannot be negative.",
                        "Invalid Salary",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (employeeManager.searchEmployee(employeeId) != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Employee ID already exists.",
                        "Duplicate ID",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Employee employee = new Employee(
                    employeeId,
                    nameField.getText().trim(),
                    phoneField.getText().trim(),
                    emailField.getText().trim(),
                    departmentField.getText().trim(),
                    designationField.getText().trim(),
                    salary,
                    (EmployeeStatus) statusBox.getSelectedItem()
            );

            employeeManager.addEmployee(employee);

            JOptionPane.showMessageDialog(
                    this,
                    "Employee added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID must be a number and salary must be numeric.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        departmentField.setText("");
        designationField.setText("");
        salaryField.setText("");

        statusBox.setSelectedIndex(0);
    }
}
