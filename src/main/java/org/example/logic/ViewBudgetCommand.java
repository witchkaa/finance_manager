package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

public class ViewBudgetCommand implements Command{
    private final User user;
    private final UserInterface userInterface;
    public ViewBudgetCommand(User user, UserInterface userInterface) {
        this.user = user;
        this.userInterface = userInterface;
    }
    @Override
    public void execute() {
        userInterface.showInfo("Your current budget: " + user.getBudget());
    }
}