package com.mycompany.smartemployeemanagementsystem.ui;

import com.mycompany.smartemployeemanagementsystem.model.Employee;
import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchEmployeeFrame extends JFrame {

    private EmployeeManager employeeManager;
    private JTextField searchField;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public SearchEmployeeFrame(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;

        setTitle("Search Employee");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        createSearchScreen();
        loadAllEmployees();
    }

    private void createSearchScreen() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel titleLabel = new JLabel(
                "SEARCH EMPLOYEE",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());

        JLabel searchLabel = new JLabel("Search by ID or Name:");

        searchField = new JTextField(25);
        searchField.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        JButton searchButton = new JButton("SEARCH");
        JButton showAllButton = new JButton("SHOW ALL");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        mainPanel.add(searchPanel, BorderLayout.CENTER);

        // Table
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

        JScrollPane scrollPane =
                new JScrollPane(employeeTable);

        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Because BorderLayout CENTER is already used by search panel,
        // use a separate container for search + table.
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));

        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.remove(searchPanel);
        mainPanel.remove(scrollPane);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel();

        JButton closeButton = new JButton("CLOSE");

        bottomPanel.add(closeButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchEmployee());

        showAllButton.addActionListener(e -> loadAllEmployees());

        searchField.addActionListener(e -> searchEmployee());

        closeButton.addActionListener(e -> dispose());

        add(mainPanel);
    }

    private void searchEmployee() {

        String searchText = searchField.getText().trim();

        if (searchText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter an Employee ID or Name.",
                    "Search Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        tableModel.setRowCount(0);

        boolean found = false;

        for (Employee employee :
                employeeManager.getEmployees()) {

            boolean matchesName =
                    employee.getName()
                            .toLowerCase()
                            .contains(searchText.toLowerCase());

            boolean matchesId =
                    String.valueOf(employee.getEmployeeId())
                            .equals(searchText);

            if (matchesName || matchesId) {

                addEmployeeToTable(employee);
                found = true;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "No employee found.",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void loadAllEmployees() {

        tableModel.setRowCount(0);

        for (Employee employee :
                employeeManager.getEmployees()) {

            addEmployeeToTable(employee);
        }
    }

    private void addEmployeeToTable(Employee employee) {

        Object[] row = {
            employee.getEmployeeId(),
            employee.getName(),
            employee.getPhone(),
            employee.getEmail(),
            employee.getDepartment(),
            employee.getDesignation(),
            String.format("%.2f", employee.getSalary()),
            employee.getStatus()
        };

        tableModel.addRow(row);
    }
}
