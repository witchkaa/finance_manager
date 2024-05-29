package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicUI implements UserInterface {

    @Override
    public void showInfo(String info) {
        JOptionPane.showMessageDialog(null, info, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Object askInfo(String type) {
        Object res = null;
        String input;
        try {
            switch (type) {
                case "int" -> {
                    input = JOptionPane.showInputDialog(null, "Enter an integer:", "Input", JOptionPane.QUESTION_MESSAGE);
                    res = Integer.parseInt(input);
                }
                case "string" -> {
                    input = JOptionPane.showInputDialog(null, "Enter a string:", "Input", JOptionPane.QUESTION_MESSAGE);
                    res = input;
                }
                case "word" -> {
                    input = JOptionPane.showInputDialog(null, "Enter a word:", "Input", JOptionPane.QUESTION_MESSAGE);
                    res = input;
                }
                default -> JOptionPane.showMessageDialog(null, "No such element", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong input! Try again. Expected an integer value there.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    @Override
    public int showSuggestions() {
        String[] options = {
                "View current budget",
                "Change budget",
                "View planned expenses",
                "View planned incomes",
                "Add expense",
                "Add income",
                "Mark expense as done",
                "Mark income as done",
                "Remove expense without completing",
                "Remove income without completing",
                "Compare financial plans (incomes and expenses)",
                "Compare all finance (incomes, expenses and current budget)",
                "Show history stats"
        };

        JPanel panel = new JPanel(new GridLayout(0, 3)); // 0 rows means variable number of rows, 3 columns
        ButtonGroup buttonGroup = new ButtonGroup();
        ActionListener buttonListener = new ButtonListener();
        for (int i = 0; i < options.length; i++) {
            JRadioButton button = new JRadioButton(options[i]);
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(buttonListener);
            buttonGroup.add(button);
            panel.add(button);
        }

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Choose what would you like to do:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            ButtonModel selectedModel = buttonGroup.getSelection();
            if (selectedModel != null) {
                return Integer.parseInt(selectedModel.getActionCommand());
            }
        }
        return -1; // If cancel was pressed or no button was selected
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Just a placeholder to ensure buttons are clickable
        }
    }

    @Override
    public void greet() {
        JOptionPane.showMessageDialog(null, "Hello there!", "Greeting", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void sayBye() {
        JOptionPane.showMessageDialog(null, "Changes have been saved. Bye!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public int askToStop() {
        String input = JOptionPane.showInputDialog(null, "Press 0 to continue, any other number to save and quit:", "Continue or Quit", JOptionPane.QUESTION_MESSAGE);
        int choice = 0;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong input, try again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return choice;
    }
}