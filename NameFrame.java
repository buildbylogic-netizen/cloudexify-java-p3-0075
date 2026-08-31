package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import java.awt.*;

public class NameFrame extends JFrame {

    private final EmployeeManager employeeManager;

    public NameFrame(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;

        setTitle("Employee Management System - Employee Name");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(
                BorderFactory.createEmptyBorder(60, 70, 50, 70)
        );

        JLabel titleLabel = new JLabel("WELCOME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructionLabel =
                new JLabel("Enter Administrator Name");

        instructionLabel.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        nameField.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );
        nameField.setMaximumSize(
                new Dimension(400, 45)
        );

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );
        continueButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        continueButton.setMaximumSize(
                new Dimension(180, 45)
        );

        JButton backButton = new JButton("BACK");
        backButton.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        backButton.setMaximumSize(
                new Dimension(120, 35)
        );

        continueButton.addActionListener(e -> {

            String name = nameField.getText().trim();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your name.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            DashboardFrame dashboard =
                    new DashboardFrame(
                            employeeManager,
                            name
                    );

            dashboard.setVisible(true);

            dispose();
        });

        backButton.addActionListener(e -> {

            new WelcomeFrame().setVisible(true);
            dispose();
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(25));
        panel.add(instructionLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(30));
        panel.add(continueButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(backButton);

        add(panel);
    }
}
