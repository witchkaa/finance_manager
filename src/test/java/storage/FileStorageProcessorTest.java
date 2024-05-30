package storage;

import org.example.storage.FileStorageProcessor;
import org.example.storage.User;
import org.example.ui.UserInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileStorageProcessorTest {

    @Mock
    private UserInterface ui;
    private FileStorageProcessor storageProcessor;
    private final String testFilePath = "test.ser";
    private FileStorageProcessor proc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        proc = new FileStorageProcessor(ui);
        storageProcessor = new FileStorageProcessor(ui);
    }

    @After
    public void tearDown() throws IOException {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testDeserializeWithEmptyFile() throws IOException {
        // Create an empty file
        new FileOutputStream(testFilePath).close();

        when(ui.askInfo("word")).thenReturn("John", "1234");
        when(ui.askInfo("int")).thenReturn(1000);

        User result = proc.deserialize(testFilePath);

        assertNotNull(result);
        assertEquals(Optional.of(1000), Optional.of(result.getBudget()));
        assertEquals("1234", result.getPassword());
    }

    @Test
    public void testDeserializeWithValidUser() throws IOException, ClassNotFoundException {
        User testUser = new User();
        testUser.setPassword("password");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(testFilePath))) {
            oos.writeObject(testUser);
        }

        when(ui.askInfo("word")).thenReturn("password");

        User result = proc.deserialize(testFilePath);

        assertNotNull(result);
        assertEquals("password", result.getPassword());
    }

    @Test
    public void testSerialize() {
        User user = new User();
        user.setBudget(1000);
        storageProcessor.serialize(user);
        // Verify that the file is created
        File file = new File("test.ser");
        assertFalse(file.exists());
    }

    @Test
    public void testCreateNewUser() {
        when(ui.askInfo("word")).thenReturn("John", "1234");
        when(ui.askInfo("int")).thenReturn(1000);

        User newUser = proc.createNewUser();

        assertNotNull(newUser);
        assertEquals(Optional.of(1000), Optional.of(newUser.getBudget()));
        assertEquals("1234", newUser.getPassword());
        assertTrue(newUser.getExpences().isEmpty());
        assertTrue(newUser.getIncomes().isEmpty());
        assertTrue(newUser.getHistory().isEmpty());
    }



    @Test
    public void testIsFileEmpty() throws IOException {
        File file = createEmptyFile();
        assertTrue(storageProcessor.isFileEmpty(file));
    }

    private File createEmptyFile() throws IOException {
        File file = new File("test.ser");
        file.createNewFile();
        return file;
    }
}
