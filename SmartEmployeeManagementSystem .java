package com.mycompany.smartemployeemanagementsystem;

import com.mycompany.smartemployeemanagementsystem.ui.WelcomeFrame;

import javax.swing.SwingUtilities;

public class SmartEmployeeManagementSystem {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            WelcomeFrame welcomeFrame = new WelcomeFrame();

            welcomeFrame.setVisible(true);
        });
    }
}
