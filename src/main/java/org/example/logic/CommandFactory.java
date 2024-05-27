package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

import java.util.Scanner;

public class CommandFactory {
    private final User user;
    private final UserInterface userInterface;
    public CommandFactory(UserInterface userInterface, User user) {
        this.userInterface = userInterface;
        this.user = user;
    }
    public Command createCommand(int type) {
        Command command;
        switch(type) {
            case 0 -> command = new ViewBudgetCommand(user, userInterface);
            case 1 -> command = new ChangeBudgetCommand(user, userInterface);
            case 2 -> command = new ViewExpensesCommand(user, userInterface);
            case 3 -> command = new ViewIncomesCommand(user, userInterface);
            case 4 -> command = new AddExpenseCommand(user, userInterface);
            case 5 -> command = new AddIncomeCommand(user, userInterface);
            default -> {
                command = null;
                System.out.println("No such command! Type a number 0-5: ");
                Scanner scan = new Scanner(System.in);
                int type2 = scan.nextInt();
                scan.close();
                createCommand(type2);
            }
        }
        return command;
    }
}