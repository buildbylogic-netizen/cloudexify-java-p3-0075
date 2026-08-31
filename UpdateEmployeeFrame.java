package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.enums.EmployeeStatus;
import com.mycompany.smartemployeemanagementsystem.model.Employee;
import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeeFrame extends JFrame {

    private final EmployeeManager employeeManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField designationField;
    private JTextField salaryField;

    private JComboBox<EmployeeStatus> statusBox;

    public UpdateEmployeeFrame(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;

        setTitle("Update Employee");
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
                "UPDATE EMPLOYEE",
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

        JButton findButton = new JButton("FIND");
        JButton updateButton = new JButton("UPDATE");
        JButton clearButton = new JButton("CLEAR");
        JButton closeButton = new JButton("CLOSE");

        buttonPanel.add(findButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        findButton.addActionListener(e -> findEmployee());

        updateButton.addActionListener(e -> updateEmployee());

        clearButton.addActionListener(e -> clearFields());

        closeButton.addActionListener(e -> dispose());

        add(mainPanel);
    }

    private void findEmployee() {

        String idText = idField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Employee ID.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int id = Integer.parseInt(idText);

            Employee employee =
                    employeeManager.searchEmployee(id);

            if (employee == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Employee not found.",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            nameField.setText(employee.getName());
            phoneField.setText(employee.getPhone());
            emailField.setText(employee.getEmail());
            departmentField.setText(employee.getDepartment());
            designationField.setText(employee.getDesignation());
            salaryField.setText(
                    String.valueOf(employee.getSalary())
            );

            statusBox.setSelectedItem(employee.getStatus());

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID must be a number.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateEmployee() {

        try {

            String idText = idField.getText().trim();

            if (idText.isEmpty()
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

            int id = Integer.parseInt(idText);

            double salary =
                    Double.parseDouble(
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

            Employee employee =
                    employeeManager.searchEmployee(id);

            if (employee == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Employee not found.",
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            employee.setName(nameField.getText().trim());
            employee.setPhone(phoneField.getText().trim());
            employee.setEmail(emailField.getText().trim());
            employee.setDepartment(
                    departmentField.getText().trim()
            );
            employee.setDesignation(
                    designationField.getText().trim()
            );
            employee.setSalary(salary);

            employee.setStatus(
                    (EmployeeStatus) statusBox.getSelectedItem()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Employee updated successfully!",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID and Salary must be numeric.",
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
