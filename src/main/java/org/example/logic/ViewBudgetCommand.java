package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewBudgetCommand that = (ViewBudgetCommand) o;
        return Objects.equals(user, that.user) && Objects.equals(userInterface, that.userInterface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userInterface);
    }
}