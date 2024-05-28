package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

import java.util.Objects;

public class ChangeBudgetCommand implements Command{
    private final User user;
    private final UserInterface userInterface;
    public ChangeBudgetCommand(User user, UserInterface userInterface) {
        this.user = user;
        this.userInterface = userInterface;
    }
    @Override
    public void execute() {
        userInterface.showInfo("Enter the immediate changes you want to do with your budget. " +
                "Enter an integer number, like '50' to add 50 or '-300' to subtract 300.");
        int choice = (int) userInterface.askInfo("int");
        int currentBudget = user.getBudget();
        if(currentBudget + choice >= 0) {
            int newBudget = user.getBudget() + choice;
            user.setBudget(newBudget);
            userInterface.showInfo("Budget updated successfully.");
        } else {
            userInterface.showInfo("Your budget will be negative if you change it right now. Are you sure?" +
                    "\nPress '0' to continue, press any other number to go back");
            int choiceQuit = (int) userInterface.askInfo("int");
            if (choiceQuit == 0) {
                int newBudget = user.getBudget() + choice;
                user.setBudget(newBudget);
                userInterface.showInfo("Budget updated successfully.");
            } else {
                userInterface.showInfo("Budget was not updated.");
            }

        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeBudgetCommand that = (ChangeBudgetCommand) o;
        return Objects.equals(user, that.user) && Objects.equals(userInterface, that.userInterface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userInterface);
    }
}