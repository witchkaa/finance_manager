package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;

public class RemoveExpenseCommand implements Command{
    private final User user;
    private final UserInterface userInterface;
    public RemoveExpenseCommand(User user, UserInterface userInterface) {
        this.user = user;
        this.userInterface = userInterface;
    }
    @Override
    public void execute() {
        userInterface.showInfo("Type in the comment to the expense you want to mark as done. " +
                "Make sure you do it correctly!");
        String choice = (String)userInterface.askInfo("string");
        if(user.getExpences().containsKey(choice)) {
            user.getExpences().remove(choice);
            userInterface.showInfo("Removed successfully!");
        } else {
            userInterface.showInfo("Wrong comment! There is no expense with this comment.");
        }
    }
}