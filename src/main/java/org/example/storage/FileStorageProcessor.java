package org.example.storage;

import org.example.ui.UserInterface;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileStorageProcessor implements StorageProcessor{
    User user;
    UserInterface userInterface;
    private static final String PATH  = "src/main/java/org/example/storage/user.ser";
    public FileStorageProcessor(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void serialize(User user) {
        try (FileOutputStream outputStream = new FileOutputStream(PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new StorageRuntimeException("Something went wrong!");
        }
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isFileEmpty(File file) {
        return file.length() == 0;
    }
    public User deserialize(String path) {
        File file = new File(path);
        if (isFileEmpty(file)) {
            this.user = createNewUser();
        }
        else {
            try (FileInputStream fileInputStream = new FileInputStream(path);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                User user = (User) objectInputStream.readObject();
                this.user = user;
                return user;
            } catch (IOException| ClassNotFoundException e) {
                throw new StorageRuntimeException("Couldn't read the file...");
            }
        }
        return user;
    }
    public User createNewUser() {
        user = new User();
        userInterface.showInfo("Seems like you're new to our app! Let's create a user!\n" +
                "Please enter your first name: ");
        String name = (String)userInterface.askInfo("word");
        user.setName(name);
        userInterface.showInfo("Nice to meet you, " + name + "! Now enter your start budget: ");
        Integer budget = (Integer) userInterface.askInfo("int");
        user.setBudget(budget);
        user.setExpends(new HashMap<>());
        user.setIncomes(new HashMap<>());
        user.setHistory(new ArrayList<>());
        return user;
    }

    @Override
    public User getUserInfo(){
        User user = deserialize(PATH);
        userInterface.greet();
        return user;
    }
    @Override
    public void saveUserInfo(){
        serialize(user);
    }
}