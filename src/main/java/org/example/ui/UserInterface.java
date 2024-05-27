package org.example.ui;

public interface UserInterface {
    void showInfo(String info);
    Object askInfo(String type);
    int showSuggestions();
    void greet();
    void sayBye();
    int askToStop();
}