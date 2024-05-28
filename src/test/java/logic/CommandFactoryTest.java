package logic;

import org.example.logic.*;
import org.example.storage.User;
import org.example.ui.Console;
import org.example.ui.UserInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CommandFactoryTest {
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
    void createCommandTest0() {
        int input = 0;
        User user = new User();
        UserInterface ui = new Console();
        CommandFactory factory = new CommandFactory(ui, user);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Command command = factory.createCommand(input);
        Command check = new ViewBudgetCommand(user, ui);
        assertEquals(command, check);
    }
    @Test
    void createCommandTest1() {
        int input = 1;
        User user = new User();
        UserInterface ui = new Console();
        CommandFactory factory = new CommandFactory(ui, user);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Command command = factory.createCommand(input);
        Command check = new ChangeBudgetCommand(user, ui);
        assertEquals(command, check);
    }
    @Test
    void createCommandTest2() {
        int input = 2;
        User user = new User();
        UserInterface ui = new Console();
        CommandFactory factory = new CommandFactory(ui, user);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Command command = factory.createCommand(input);
        Command check = new ViewExpensesCommand(user, ui);
        assertEquals(command, check);
    }
    @Test
    void createCommandTest3() {
        int input = 3;
        User user = new User();
        UserInterface ui = new Console();
        CommandFactory factory = new CommandFactory(ui, user);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Command command = factory.createCommand(input);
        Command check = new ViewIncomesCommand(user, ui);
        assertEquals(command, check);
    }
    @Test
    void create_Command_Test_4() {
        int input = 4;
        User user = new User();
        UserInterface ui = new Console();
        CommandFactory factory = new CommandFactory(ui, user);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Command command = factory.createCommand(input);
        Command check = new AddExpenseCommand(user, ui);
        assertEquals(command, check);
    }
}
