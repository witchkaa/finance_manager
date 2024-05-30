package org.example.storage;

import org.example.ui.UserInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class FileStorageProcessor implements StorageProcessor {
    private User user;
    private UserInterface userInterface;
    private static final String DIRECTORY = System.getProperty("user.home") + "/myapp";
    private static final String FILENAME = DIRECTORY + "/user.ser";

    public FileStorageProcessor(UserInterface userInterface) {
        this.userInterface = userInterface;
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY));
        } catch (IOException e) {
            throw new StorageRuntimeException("Failed to create directory: " + DIRECTORY);
        }
    }

    public void serialize(User user) {
        try (FileOutputStream outputStream = new FileOutputStream(FILENAME);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new StorageRuntimeException("Failed to serialize user to file: " + FILENAME);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFileEmpty(File file) {
        return file.length() == 0;
    }

    public User deserialize(String filename) {
        File file = new File(filename);
        if (!file.exists() || isFileEmpty(file)) {
            this.user = createNewUser();
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                User user = (User) objectInputStream.readObject();
                this.user = user;
                userInterface.showInfo("Please enter your password: ");
                String password = (String) userInterface.askInfo("word");
                if (!password.equals(user.getPassword())) {
                    userInterface.showInfo("Please re-enter your password!");
                    password = (String) userInterface.askInfo("word");
                    if (!password.equals(user.getPassword())) {
                        userInterface.showInfo("Wrong password again! Restart the program and try again.");
                        System.exit(0);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new StorageRuntimeException("Couldn't read the file...");
            }
        }
        return user;
    }

    public User createNewUser() {
        user = new User();
        userInterface.showInfo("Seems like you're new to our app! Let's create a user!\n" +
                "Please enter your first name: ");
        String name = (String) userInterface.askInfo("word");
        userInterface.showInfo("Nice to meet you, " + name + "! Now enter your start budget: ");
        Integer budget = (Integer) userInterface.askInfo("int");
        userInterface.showInfo("Please enter a password for your new account: ");
        String password = (String) userInterface.askInfo("word");
        user.setBudget(budget);
        user.setPassword(password);
        user.setExpends(new HashMap<>());
        user.setIncomes(new HashMap<>());
        user.setHistory(new ArrayList<>());
        return user;
    }

    @Override
    public User getUserInfo() {
        User user = deserialize(FILENAME);
        userInterface.greet();
        return user;
    }

    @Override
    public void saveUserInfo() {
        serialize(user);
    }
}
