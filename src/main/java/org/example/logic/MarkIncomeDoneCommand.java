package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;
public class MarkIncomeDoneCommand implements Command{
    private final UserInterface userInterface;
    private final User user;
    public MarkIncomeDoneCommand(User user, UserInterface userInterface) {
        this.userInterface = userInterface;
        this.user = user;
    }
    @Override
    public void execute() {
        userInterface.showInfo("Type in the comment to the income you want to mark as done. " +
                "Make sure you do it correctly!");
        String choice = (String)userInterface.askInfo("string");
        if(user.getIncomes().containsKey(choice)) {
            int value = user.getIncomes().get(choice);
            int oldBudget = user.getBudget();
            Integer newBudget = oldBudget + value;
            user.setBudget(newBudget);
            user.getHistory().add(value);
            user.getIncomes().remove(choice);
            userInterface.showInfo("Marked as done successfully!");
        } else {
            userInterface.showInfo("Wrong comment! There is no income with this comment.");
        }
    }
}