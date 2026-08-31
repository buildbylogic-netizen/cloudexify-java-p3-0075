package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.model.Employee;
import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import java.awt.*;

public class RemoveEmployeeFrame extends JFrame {

    private final EmployeeManager employeeManager;
    private JTextField idField;

    public RemoveEmployeeFrame(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;

        setTitle("Remove Employee");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        createScreen();
    }

    private void createScreen() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        );

        JLabel titleLabel = new JLabel("REMOVE EMPLOYEE");
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructionLabel =
                new JLabel("Enter Employee ID to remove");
        instructionLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        idField = new JTextField();
        idField.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );
        idField.setMaximumSize(
                new Dimension(300, 40)
        );

        JButton removeButton =
                new JButton("REMOVE EMPLOYEE");

        JButton clearButton =
                new JButton("CLEAR");

        JButton closeButton =
                new JButton("CLOSE");

        removeButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeButton.addActionListener(e -> removeEmployee());

        clearButton.addActionListener(e ->
                idField.setText("")
        );

        closeButton.addActionListener(e ->
                dispose()
        );

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(25));

        mainPanel.add(instructionLabel);
        mainPanel.add(Box.createVerticalStrut(12));

        mainPanel.add(idField);
        mainPanel.add(Box.createVerticalStrut(25));

        mainPanel.add(removeButton);
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(clearButton);
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(closeButton);

        add(mainPanel);
    }

    private void removeEmployee() {

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
                        "Employee with ID " + id + " was not found.",
                        "Employee Not Found",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to remove:\n\n"
                    + "ID: " + employee.getEmployeeId()
                    + "\nName: " + employee.getName(),
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {

                boolean removed =
                        employeeManager.removeEmployee(id);

                if (removed) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Employee removed successfully.",
                            "Removal Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    idField.setText("");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Employee could not be removed.",
                            "Removal Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Employee ID must be a valid number.",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
