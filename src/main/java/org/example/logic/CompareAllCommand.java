package org.example.logic;

import org.example.storage.User;
import org.example.ui.UserInterface;
import java.util.Map;

public class CompareAllCommand implements Command {
    private final User user;
    private final UserInterface userInterface;
    public CompareAllCommand(User user, UserInterface userInterface) {
        this.user = user;
        this.userInterface = userInterface;
    }
    @Override
    public void execute() {
        Map<String, Integer> expends = user.getExpences();
        Map<String, Integer> incomes = user.getIncomes();
        int budget = user.getBudget();
        int incomesSum = 0;
        int expensesSum = 0;
        for(Map.Entry<String, Integer> entry : expends.entrySet()) {
            expensesSum += entry.getValue();
        }
        for(Map.Entry<String, Integer> entry : incomes.entrySet()) {
            incomesSum += entry.getValue();
        }
        int comparison = budget + incomesSum - expensesSum;
        if (comparison > 0) {
            userInterface.showInfo("Your planned incomes plus your budget are more than your planned expends by " + comparison +
                    "! That is good!"
            );
        } else if (comparison == 0) {
            userInterface.showInfo("Wow!!! Your budget + planned incomes sum and expends are equal! Balance here.");
        } else {
            userInterface.showInfo("Your planned expends are more than your planned incomes " +
                    "plus your current budget by " + (comparison*(-1)) +
                    ". Consider re-watching your budget.");
        }
    }
}