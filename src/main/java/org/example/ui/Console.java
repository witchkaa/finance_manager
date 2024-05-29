package org.example.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console implements UserInterface {
    public static final Scanner scan = new Scanner(System.in);
    @Override
    public void showInfo(String info) {
        System.out.println(info);
    }
    @Override
    public Object askInfo(String type) {
        Object res = null;
        switch (type) {
            case "int" -> {
                try {
                    res = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input! Try again. Expected an integer value there.");
                } finally {
                    scan.nextLine();
                }
            }
            case "string" -> {
                try {
                    scan.nextLine();
                    res = scan.nextLine();
                } catch(InputMismatchException e) {
                    System.out.println("Wrong input! Try again. Expected a string line there.");
                }
            }
            case "word" -> {
                try {
                    res = scan.next();
                } catch (InputMismatchException e){
                    System.out.println("Wrong input! Try again. Expected a word there.");
                }
            }
            default -> System.out.println("No such element");
        }
        return res;
    }
    @Override
    public int showSuggestions() {
        System.out.println("""
                Choose what would you like to do:
                \tview current budget - press '0'
                \tchange budget - press '1'
                \tview planned expenses - press '2'
                \tview planned incomes - press '3'
                \tadd expense - press '4'
                \tadd income - press '5'
                \tmark expense as done - press '6'
                \tmark income as done - press '7'
                \tremove expense without completing - press '8'
                \tremove income without completing - press '9'
                \tcompare financial plans(incomes and expends) - press '10'
                \tcompare all finance(incomes, expends and current budget) - press '11'
                \tshow history stats - press '12'
                """);
        return scan.nextInt();
    }

    @Override
    public void greet() {
        System.out.println("Hello there!");
    }
    @Override
    public void sayBye() {
        System.out.println("Changes have been saved. Bye!");
    }
    @Override
    public int askToStop() {
        System.out.println("Press 0 to continue, any other number to save and quit");
        int choice = 0;
        try {
            choice =  scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again!");
        }
        return choice;
    }

}