package logic;

import org.junit.jupiter.api.Test;
import storage.User;
import userinterface.Console;
import userinterface.UserInterface;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompareAllCommandTest {
    private CompareAllCommand compareAllCommand;
    private User user;
    private UserInterface userInterface;
    private ByteArrayOutputStream outputStream;

    @Test
    void testExecute_BudgetPlusIncomesGreaterThanExpenses() {
        user = new User();
        userInterface = new Console();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HashMap<String, Integer> expenses = createMap(1150, 175);
        HashMap<String, Integer> incomes = createMap(1150, 175);
        user.setBudget(1111110);
        user.setIncomes(incomes);
        user.setExpends(expenses);
        compareAllCommand = new CompareAllCommand(user, userInterface);
        compareAllCommand.execute();
        assertEquals("Your planned incomes plus your budget are more than your planned expends by 1111110! That is good!",
                outputStream.toString().trim());
    }

    @Test
    void testExecute_BudgetPlusIncomesEqualsExpenses() {
        user = new User();
        userInterface = new Console();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HashMap<String, Integer> expenses = createMap(1150, 175);
        HashMap<String, Integer> incomes = createMap(1150, 175);
        user.setBudget(0);
        user.setIncomes(incomes);
        user.setExpends(expenses);
        compareAllCommand = new CompareAllCommand(user, userInterface);
        compareAllCommand.execute();
        assertEquals("Wow!!! Your budget + planned incomes sum and expends are equal! Balance here.",
                outputStream.toString().trim());
    }

    @Test
    void testExecute_ExpensesGreaterThanBudgetPlusIncomes() {
        user = new User();
        userInterface = new Console();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HashMap<String, Integer> expenses = createMap(1150, 175);
        HashMap<String, Integer> incomes = createMap(150, 120);
        user.setBudget(100);
        user.setIncomes(incomes);
        user.setExpends(expenses);
        compareAllCommand = new CompareAllCommand(user, userInterface);
        compareAllCommand.execute();
        assertEquals("Your planned expends are more than your planned incomes plus your current budget by 955. Consider re-watching your budget.",
                outputStream.toString().trim());
    }

    private HashMap<String, Integer> createMap(Integer... amounts) {
        HashMap<String, Integer> expenses = new HashMap<>();
        for (int i = 0; i < amounts.length; i++) {
            expenses.put("Expense" + (i + 1), amounts[i]);
        }
        return expenses;
    }
}