
package com.mycompany.smartemployeemanagementsystem.ui;
import com.mycompany.smartemployeemanagementsystem.service.EmployeeManager;
import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {

        setTitle("Smart Employee Management System");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 60, 60, 60));

        JLabel titleLabel = new JLabel("SMART EMPLOYEE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel2 = new JLabel("MANAGEMENT SYSTEM");
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(
                "Professional Employee Management Application"
        );
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(180, 50));

        startButton.addActionListener(e -> {
            new NameFrame(new EmployeeManager()).setVisible(true);
            dispose();
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(titleLabel2);
        panel.add(Box.createVerticalStrut(20));
        panel.add(subtitleLabel);
        panel.add(Box.createVerticalStrut(50));
        panel.add(startButton);

        add(panel);
    }
    public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {
        EmployeeManager manager = new EmployeeManager();
        new NameFrame(manager).setVisible(true);
    });
}
}
