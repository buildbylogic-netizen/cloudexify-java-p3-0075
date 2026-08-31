package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.model.Employee;
import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewEmployeeFrame extends JFrame {

    private EmployeeManager employeeManager;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public ViewEmployeeFrame(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;

        setTitle("View Employees");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        createView();
        loadEmployees();
    }

    private void createView() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel titleLabel = new JLabel(
                "EMPLOYEE RECORDS",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columns = {
            "ID",
            "Name",
            "Phone",
            "Email",
            "Department",
            "Designation",
            "Salary",
            "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(tableModel);

        employeeTable.setRowHeight(28);
        employeeTable.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        employeeTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton refreshButton = new JButton("REFRESH");
        JButton closeButton = new JButton("CLOSE");

        refreshButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        closeButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        bottomPanel.add(refreshButton);
        bottomPanel.add(closeButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadEmployees());

        closeButton.addActionListener(e -> dispose());

        add(mainPanel);
    }

    private void loadEmployees() {

        tableModel.setRowCount(0);

        for (Employee employee : employeeManager.getEmployees()) {

            Object[] row = {
                employee.getEmployeeId(),
                employee.getName(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getDesignation(),
                String.format("%.0f", employee.getSalary()),
                employee.getStatus()
            };

            tableModel.addRow(row);
        }
    }
}
