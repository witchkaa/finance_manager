package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

import java.util.Map;

public class ViewExpensesCommand implements Command{
    private final User user;
    private final UserInterface userInterface;
    public ViewExpensesCommand(User user, UserInterface userInterface) {
        this.user = user;
        this.userInterface = userInterface;
    }
    @Override
    public void execute() {
        userInterface.showInfo("Your planned expenses: ");
        Map<String, Integer> expenses = user.getExpences();
        if (expenses.isEmpty()) {
            userInterface.showInfo("No planned expenses added.");
        } else {
            expenses.forEach(
                    (key, value)
                            -> userInterface.showInfo("\tAmount: " + value + ", comment: " + key + "."));
        }
    }

}