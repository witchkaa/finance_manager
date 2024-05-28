package logic;

import org.example.logic.ViewIncomesCommand;
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

public class ViewIncomesCommandTest {
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
        Map<String, Integer> incomes = new HashMap<>();
        incomes.put("Salary", 2000);
        incomes.put("Bonus", 500);
        testUser.setIncomes(incomes);
        Console mockUserInterface = new Console();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ViewIncomesCommand viewIncomesCommand = new ViewIncomesCommand(testUser, mockUserInterface);
        viewIncomesCommand.execute();
        System.setOut(systemOut);
        String expectedOutput = "Your planned incomes: \n" +
                "\tAmount: 2000, comment: Salary.\n" +
                "\tAmount: 500, comment: Bonus.";

        String actualOutput = outputStream.toString().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }}