package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

import java.util.Objects;

public class AddExpenseCommand implements Command{
    private final User user;
    private final UserInterface userInterface;
    public AddExpenseCommand(User user, UserInterface userInterface) {
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
            user.getExpences().put(comment, amount);
            userInterface.showInfo("An expense (value: " + amount + ", comment: " + comment +  ") was put successfully!");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddExpenseCommand that = (AddExpenseCommand) o;
        return Objects.equals(user, that.user) && Objects.equals(userInterface, that.userInterface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, userInterface);
    }
}