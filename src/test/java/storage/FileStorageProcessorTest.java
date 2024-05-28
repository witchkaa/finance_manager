package storage;

import org.junit.Before;
import org.junit.Test;
import userinterface.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static org.junit.Assert.*;
public class FileStorageProcessorTest {
    private FileStorageProcessor storageProcessor;
    @Before
    public void setUp() {
        Console userInterface = new Console();
        storageProcessor = new FileStorageProcessor(userInterface);
    }
    @Test
    public void testSerialize() throws IOException, ClassNotFoundException {
        User user = new User();
        storageProcessor.serialize(user);
        // Verify that the file is created and serialized correctly
        File file = new File("test.ser");
        assertTrue(file.exists());
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            User deserializedUser = (User) objectInputStream.readObject();
            assertEquals(user, deserializedUser);
        }
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