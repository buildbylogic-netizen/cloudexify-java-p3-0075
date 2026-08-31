package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final EmployeeManager employeeManager;
    private final String administratorName;

    public DashboardFrame(
            EmployeeManager employeeManager,
            String administratorName) {

        this.employeeManager = employeeManager;
        this.administratorName = administratorName;

        setTitle(
                "Smart Employee Management System - Dashboard"
        );

        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        createDashboard();
    }

    private void createDashboard() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 35, 25, 35
                )
        );

        JLabel titleLabel = new JLabel(
                "SMART EMPLOYEE MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + administratorName,
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        JPanel headerPanel =
                new JPanel(new GridLayout(2, 1));

        headerPanel.add(titleLabel);
        headerPanel.add(welcomeLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(3, 3, 15, 15)
                );

        JButton addButton =
                new JButton("ADD EMPLOYEE");

        JButton viewButton =
                new JButton("VIEW EMPLOYEES");

        JButton searchButton =
                new JButton("SEARCH EMPLOYEE");

        JButton updateButton =
                new JButton("UPDATE EMPLOYEE");

        JButton removeButton =
                new JButton("REMOVE EMPLOYEE");

        JButton aboutButton =
                new JButton("ABOUT");

        JButton saveButton =
                new JButton("SAVE DATA");

        JButton exitButton =
                new JButton("EXIT");

        JButton[] buttons = {
            addButton,
            viewButton,
            searchButton,
            updateButton,
            removeButton,
            aboutButton,
            saveButton,
            exitButton
        };

        for (JButton button : buttons) {

            button.setFont(
                    new Font("Arial", Font.BOLD, 14)
            );

            button.setFocusPainted(false);

            buttonPanel.add(button);
        }

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        addButton.addActionListener(e -> {

            AddEmployeeFrame frame =
                    new AddEmployeeFrame(employeeManager);

            frame.setVisible(true);
        });
        viewButton.addActionListener(e -> {

            ViewEmployeeFrame frame =
                    new ViewEmployeeFrame(employeeManager);

            frame.setVisible(true);
        });

        searchButton.addActionListener(e -> {

            SearchEmployeeFrame frame =
                    new SearchEmployeeFrame(employeeManager);

            frame.setVisible(true);
        });

        updateButton.addActionListener(e -> {

            UpdateEmployeeFrame frame =
                    new UpdateEmployeeFrame(employeeManager);

            frame.setVisible(true);
        });

        removeButton.addActionListener(e -> {

            RemoveEmployeeFrame frame =
                    new RemoveEmployeeFrame(employeeManager);

            frame.setVisible(true);
        });

        aboutButton.addActionListener(e -> {

            AboutFrame frame = new AboutFrame();

            frame.setVisible(true);
        });

        saveButton.addActionListener(e -> {

            employeeManager.saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee data saved successfully!",
                    "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        exitButton.addActionListener(e ->
                ExitConfirmation.showExitConfirmation(this)
        );

        add(mainPanel);
    }
}
