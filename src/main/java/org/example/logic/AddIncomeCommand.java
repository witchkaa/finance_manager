package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

public class AddIncomeCommand implements Command{
    private final User user;
    private final UserInterface userInterface;
    public AddIncomeCommand(User user, UserInterface userInterface) {
        this.user = user;
        this.userInterface = userInterface;
    }
    @Override
    public void execute() {
        userInterface.showInfo("Enter comment: ");
        String comment = (String) userInterface.askInfo("string");
        userInterface.showInfo("Enter amount: ");
        Object info = userInterface.askInfo("int");
        if(info != null) {
            int amount = (int) info;
            user.getIncomes().put(comment, amount);
            userInterface.showInfo("An income (value: " + amount + ", comment: " + comment +  ") was put successfully!");
        }
    }
}