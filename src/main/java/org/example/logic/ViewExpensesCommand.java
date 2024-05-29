package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewExpensesCommand that = (ViewExpensesCommand) o;
        return Objects.equals(user, that.user) && Objects.equals(userInterface, that.userInterface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userInterface);
    }
}