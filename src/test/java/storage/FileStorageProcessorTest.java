package storage;

import org.example.storage.FileStorageProcessor;
import org.example.storage.User;
import org.junit.Before;
import org.junit.Test;
import org.example.ui.Console;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
public class FileStorageProcessorTest {
    private FileStorageProcessor storageProcessor;
    @Before
    public void setUp() {
        Console userInterface = new Console();
        storageProcessor = new FileStorageProcessor(userInterface);
    }
    @Test
    public void testSerialize(){
        User user = new User();
        user.setBudget(1000);
        storageProcessor.serialize(user);
        // Verify that the file is created
        File file = new File("test.ser");
        assertTrue(file.exists());
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