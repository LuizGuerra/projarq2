package src.controller;

import src.model.Item;
import src.model.User;
import src.utility.ShoppingCartSingleton;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputManager {

    private static Scanner scanner = new Scanner(System.in);

    public static int decisionBranch() {
        return scanner.nextInt();
    }

    public static String next() {
        return scanner.next();
    }

    public static List<String> arrayAsStrings(List<Object> objects) {
        return objects.stream().map(Object::toString).collect(Collectors.toList());
    }

    public static String selectFromList(String message, List<String> list) {
        System.out.println(message);
        int input = 0;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
        System.out.println(list.size() + 1 + ". Exit");
        while(input < 1 || input > list.size() + 1) {
            input = decisionBranch();
            if (input < 1 || input > list.size() + 1) {
                System.out.println("Please, inform a valid number");
            }
        }
        return input == list.size() + 1 ? null : list.get(input-1);
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

    public static Item addProduct() {
        System.out.println("Type the product name:");
        String productName = scanner.next();

        System.out.println("Type the product price:");
        float productPrice = scanner.nextFloat();

        System.out.println("Type the product quantity:");
        int quantity = scanner.nextInt();

        return (new Item(productName, productPrice, quantity));
    }
}
