package src.controller;

import src.model.User;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class InputManager {

    private static Scanner scanner = new Scanner(System.in);

    public static int decisionBranch() {
        return scanner.nextInt();
    }

    public static User connectToEcommerce() {
        System.out.println("Inform the ecommerce you want to connect");
        String ecommerce = scanner.next();

        System.out.println("Type your user name:");
        String userName = scanner.next();

        System.out.println("Type your password:");
        String password = "";
        Console console = System.console();
        password = console == null ? scanner.next() : Arrays.toString(console.readPassword());

        return (new User(ecommerce, userName, password));
    }



}
