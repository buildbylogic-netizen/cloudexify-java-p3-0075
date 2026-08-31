package com.mycompany.smartemployeemanagementsystem.ui;

import javax.swing.*;
import java.awt.*;

public class ExitConfirmation {

    private ExitConfirmation() {
       
    }

    public static void showExitConfirmation(Component parent) {

        int choice = JOptionPane.showConfirmDialog(
                parent,
                "Are you sure you want to exit the application?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
