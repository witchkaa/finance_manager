package userinterface;

import org.example.ui.GraphicUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;


class GraphicUITest {

    private GraphicUI graphicUI;

    @BeforeEach
    void setUp() {
        graphicUI = new GraphicUI();
    }

    @Test
    void testShowInfo() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            // Test the method
            graphicUI.showInfo("Test Info");

            // Verify that JOptionPane.showMessageDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showMessageDialog(null, "Test Info", "Information", JOptionPane.INFORMATION_MESSAGE));
        }
    }

    @Test
    void testAskInfoForInt() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(null, "Enter an integer:", "Input", JOptionPane.QUESTION_MESSAGE))
                    .thenReturn("123");

            // Test the method
            Object result = graphicUI.askInfo("int");

            // Verify that JOptionPane.showInputDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showInputDialog(null, "Enter an integer:", "Input", JOptionPane.QUESTION_MESSAGE));

            // Assert the result is correct
            assertEquals(123, result);
        }
    }

    @Test
    void testAskInfoForString() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(null, "Enter a string:", "Input", JOptionPane.QUESTION_MESSAGE))
                    .thenReturn("test string");

            // Test the method
            Object result = graphicUI.askInfo("string");

            // Verify that JOptionPane.showInputDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showInputDialog(null, "Enter a string:", "Input", JOptionPane.QUESTION_MESSAGE));

            // Assert the result is correct
            assertEquals("test string", result);
        }
    }

    @Test
    void testAskInfoForWord() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(null, "Enter a word:", "Input", JOptionPane.QUESTION_MESSAGE))
                    .thenReturn("word");

            // Test the method
            Object result = graphicUI.askInfo("word");

            // Verify that JOptionPane.showInputDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showInputDialog(null, "Enter a word:", "Input", JOptionPane.QUESTION_MESSAGE));

            // Assert the result is correct
            assertEquals("word", result);
        }
    }

    @Test
    void testAskInfoInvalidInt() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(null, "Enter an integer:", "Input", JOptionPane.QUESTION_MESSAGE))
                    .thenReturn("abc");

            // Test the method
            Object result = graphicUI.askInfo("int");

            // Verify that JOptionPane.showMessageDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showMessageDialog(null, "Wrong input! Try again. Expected an integer value there.", "Error", JOptionPane.ERROR_MESSAGE));

            // Assert the result is correct
            assertEquals(null, result);
        }
    }

    @Test
    void testGreet() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            // Test the method
            graphicUI.greet();

            // Verify that JOptionPane.showMessageDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showMessageDialog(null, "Hello there!", "Greeting", JOptionPane.INFORMATION_MESSAGE));
        }
    }

    @Test
    void testSayBye() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            // Test the method
            graphicUI.sayBye();

            // Verify that JOptionPane.showMessageDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showMessageDialog(null, "Changes have been saved. Bye!", "Goodbye", JOptionPane.INFORMATION_MESSAGE));
        }
    }

    @Test
    void testAskToStop() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(null, "Press 0 to continue, any other number to save and quit:", "Continue or Quit", JOptionPane.QUESTION_MESSAGE))
                    .thenReturn("1");

            // Test the method
            int result = graphicUI.askToStop();

            // Verify that JOptionPane.showInputDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showInputDialog(null, "Press 0 to continue, any other number to save and quit:", "Continue or Quit", JOptionPane.QUESTION_MESSAGE));

            // Assert the result is correct
            assertEquals(1, result);
        }
    }

    @Test
    void testAskToStopInvalidInput() {
        // Mock JOptionPane
        try (MockedStatic<JOptionPane> mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(null, "Press 0 to continue, any other number to save and quit:", "Continue or Quit", JOptionPane.QUESTION_MESSAGE))
                    .thenReturn("abc");

            // Test the method
            int result = graphicUI.askToStop();

            // Verify that JOptionPane.showMessageDialog was called with the correct parameters
            mocked.verify(() -> JOptionPane.showMessageDialog(null, "Wrong input, try again!", "Error", JOptionPane.ERROR_MESSAGE));

            // Assert the result is correct
            assertEquals(0, result);
        }
    }
}
