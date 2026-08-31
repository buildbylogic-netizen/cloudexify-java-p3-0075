package com.mycompany.smartemployeemanagementsystem.ui;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {

    public AboutFrame() {

        setTitle("About - Smart Employee Management System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        JLabel titleLabel = new JLabel(
                "SMART EMPLOYEE MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea aboutText = new JTextArea();

        aboutText.setText(
                "Smart Employee Management System\n\n"
                + "This desktop application is designed to manage "
                + "employee records efficiently.\n\n"
                + "Main Features:\n"
                + "• Add Employee\n"
                + "• View Employees\n"
                + "• Search Employee\n"
                + "• Update Employee\n"
                + "• Remove Employee\n"
                + "• Save Employee Data\n\n"
                + "Technology:\n"
                + "Java + Java Swing\n\n"
                + "The system demonstrates Object-Oriented Programming, "
                + "GUI development, data management, validation, and "
                + "file handling concepts."
        );

        aboutText.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBackground(
                mainPanel.getBackground()
        );

        mainPanel.add(
                new JScrollPane(aboutText),
                BorderLayout.CENTER
        );

        JButton closeButton = new JButton("CLOSE");

        closeButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }
}
