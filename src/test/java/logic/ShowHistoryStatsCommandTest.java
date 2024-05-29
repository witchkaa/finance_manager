package logic;

import org.example.logic.ShowHistoryStatsCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.storage.User;
import org.example.ui.Console;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ShowHistoryStatsCommandTest {
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
    public void testExecute_WithHistory() {
        User testUser = new User();
        List<Integer> history = new ArrayList<>();
        history.add(100);
        history.add(200);
        history.add(300);
        testUser.setHistory(history);
        Console mockUserInterface = new Console();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ShowHistoryStatsCommand showHistoryStatsCommand = new ShowHistoryStatsCommand(testUser, mockUserInterface);
        showHistoryStatsCommand.execute();
        System.setOut(systemOut);
        String expectedOutput = "History of transactions stats: incomes-expenses = 600";
        String actualOutput = outputStream.toString().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    @Test
    public void testExecute_WithoutHistory() {
        User testUser = new User();
        List<Integer> history = new ArrayList<>();
        testUser.setHistory(history);
        Console mockUserInterface = new Console();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ShowHistoryStatsCommand showHistoryStatsCommand = new ShowHistoryStatsCommand(testUser, mockUserInterface);
        showHistoryStatsCommand.execute();
        System.setOut(systemOut);
        String expectedOutput = "No archived transactions available.";
        String actualOutput = outputStream.toString().replace("\r\n", "\n");
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}