package logic;

import org.example.logic.ViewBudgetCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.storage.User;
import org.example.ui.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class ViewBudgetCommandTest {
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
        Integer budget = 5000;
        testUser.setBudget(budget);
        Console mockUserInterface = new Console();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ViewBudgetCommand viewBudgetCommand = new ViewBudgetCommand(testUser, mockUserInterface);
        viewBudgetCommand.execute();
        System.setOut(systemOut);
        String expectedOutput = "Your current budget: 5000";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}