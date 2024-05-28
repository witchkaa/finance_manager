package logic;

import org.junit.Before;
import org.junit.Test;
import org.example.storage.User;
import org.example.ui.UserInterface;
import org.example.logic.ComparePlansCommand;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class ComparePlansCommandTest {
    private User user;
    private UserInterfaceMock userInterface;
    private ComparePlansCommand comparePlansCommand;

    @Before
    public void setup() {
        user = new User();
        userInterface = new UserInterfaceMock();
        comparePlansCommand = new ComparePlansCommand(user, userInterface);
    }

    @Test
    public void testExecute_MoreIncomesThanExpenses() {
        Map<String, Integer> incomes = new HashMap<>();
        incomes.put("Salary", 2000);
        incomes.put("Bonus", 500);
        user.setIncomes(incomes);

        Map<String, Integer> expenses = new HashMap<>();
        expenses.put("Rent", 1000);
        expenses.put("Groceries", 300);
        user.setExpends(expenses);

        comparePlansCommand.execute();

        assertEquals("Your planned incomes are more than your planned expends by 1200! That is good!", userInterface.getShownInfo());
    }

    @Test
    public void testExecute_EqualIncomesAndExpenses() {
        Map<String, Integer> incomes = new HashMap<>();
        incomes.put("Salary", 2000);
        incomes.put("Bonus", 500);
        user.setIncomes(incomes);

        Map<String, Integer> expenses = new HashMap<>();
        expenses.put("Rent", 1500);
        expenses.put("Groceries", 1000);
        user.setExpends(expenses);

        comparePlansCommand.execute();

        assertEquals("Wow!!! Your planned incomes and expends are equal! Balance here.", userInterface.getShownInfo());
    }

    @Test
    public void testExecute_MoreExpensesThanIncomes() {
        Map<String, Integer> incomes = new HashMap<>();
        incomes.put("Salary", 2000);
        incomes.put("Bonus", 500);
        user.setIncomes(incomes);

        Map<String, Integer> expenses = new HashMap<>();
        expenses.put("Rent", 2500);
        expenses.put("Groceries", 800);
        user.setExpends(expenses);

        comparePlansCommand.execute();

        assertEquals("Your planned expends are more than your planned incomes by 800. Consider re-watching your budget.", userInterface.getShownInfo());
    }

    private static class UserInterfaceMock implements UserInterface {
        private String shownInfo;

        @Override
        public void showInfo(String info) {
            this.shownInfo = info;
        }

        @Override
        public Object askInfo(String type) {
            return null;
        }

        @Override
        public int showSuggestions() {
            return 0;
        }

        @Override
        public void greet() {

        }

        @Override
        public void sayBye() {

        }

        @Override
        public int askToStop() {
            return 0;
        }

        public String getShownInfo() {
            return shownInfo;
        }
    }
}