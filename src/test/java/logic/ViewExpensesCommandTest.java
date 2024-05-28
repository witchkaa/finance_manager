package logic;

import org.example.logic.ViewExpensesCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.storage.User;
import org.example.ui.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class ViewExpensesCommandTest {
    private final PrintStream systemOut = System.out;

    @Before
    public void setUp() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void testExecute() {
        User testUser = new User();

        // Add expenses to the User object using addExpense()
        Map<String, Integer> expenses = new HashMap<>();
        expenses.put("Rent", 1000);
        expenses.put("Utilities", 200);
        testUser.setExpends(expenses);
        Console mockUserInterface = new Console();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ViewExpensesCommand viewExpensesCommand = new ViewExpensesCommand(testUser, mockUserInterface);
        viewExpensesCommand.execute();

        System.setOut(systemOut);

        String expectedOutput = """
                Your planned expenses:\s
                \tAmount: 200, comment: Utilities.
                \tAmount: 1000, comment: Rent.""";

        String actualOutput = outputStream.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}