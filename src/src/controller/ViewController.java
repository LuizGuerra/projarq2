package src.controller;

import src.model.ConnectionStatus;
import src.model.Ecommerce;
import src.model.User;
import src.networking.Network;
import src.utility.ShoppingCartSingleton;
import src.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ViewController {

    private final View view;
    private List<Ecommerce> ecommerces;
    private List<User> connections;

    public ViewController() {
        view = new View();
        connections = new ArrayList<>();
        updateEcommerces();
    }

    public void start() {
        int flag = -1;
        while (flag != 10) {
            view.loadOptions();
            flag = InputManager.decisionBranch();
            if (flag == 0) { continue; }
            switch (flag) {
                case 1 -> connectToEcommerce();
                case 2 -> removeEcommerce();
                case 3 -> showAllEcommerces();
                case 4 -> updateEcommerceInformation();
                case 5 -> checkOrdersByEcommerce();
                case 6 -> showAllOrders();
                case 10 -> view.printMessage("Exiting...");
                default -> System.out.println("Default");
            }
        }
        view.printMessage("Process exited.");
    }

    public void connectToEcommerce() {
//        Get user's input
        User user = InputManager.connectToEcommerce();
//        Verify if is already connected
        if (connections.stream().anyMatch (a -> user.ecommerce.equals(a.ecommerce))) {
            view.printMessage("Already connected to this ecommerce");
            return;
        }
//        Try connection
        ConnectionStatus status = Network.tryLogIn(user);
        view.ecommerceConnection(user.ecommerce, status);
        if (status == ConnectionStatus.successful) {
            connections.add(user);
        }
    }

    private void removeEcommerce() {
//        Verify if there is no e-commerce connected
        if (ecommerces.isEmpty()) {
            System.out.println("You are not connected to any store.");
            return;
        }
//        Select the ecommerce to be removed
        int index = InputManager.selectFromList(
                "Select a store to be removed:",
                ecommerces.stream().map(Ecommerce::getName).collect(Collectors.toList())
                );
        if (index == 0) {
            System.out.println("Exiting...");
            return;
        }
//        If ecommerce was found it was removed, also remove items from singleton
        Ecommerce e = ecommerces.remove(index-1);
        if (e != null && connections.removeIf( a -> a.ecommerce.equals(e.getName()) )) {
            System.out.println("e-commerce removed successfully!");
            ShoppingCartSingleton.synchronised().removeFrom(e);
        } else {
            System.out.println("There was a problem removing the e-commerce");
        }
    }

    public void showAllEcommerces() {
        view.printMessage("Showing all connected e-commerces");
        view.printArray(ecommerces.stream().map(Ecommerce::getName).toArray());
    }

    public void updateEcommerceInformation() {
//        System.out.println("Select a ecommerce to update:");
//        AtomicInteger aux = new AtomicInteger(1);
//        view.printArray(connections.stream().map(a -> "" + (aux.getAndIncrement()) + ". " + a.ecommerce).toArray());
//        System.out.println(aux + ". Exit");
//        int flag = InputManager.decisionBranch();
        view.printMessage("Updating ecommerce information...");
        updateEcommerces();
        view.printMessage("Update completed.");

//        Adding items from e-commerces to the singleton
        ecommerces.forEach( e -> ShoppingCartSingleton.synchronised().addFrom(e));
        System.out.println(ShoppingCartSingleton.synchronised().getItems().keySet());
    }

    public void checkOrdersByEcommerce() {
        if (ecommerces.isEmpty()) {
            view.printMessage("No connected store yet!");
            return;
        }
        System.out.println("Select a ecommerce to check your orders:");
        AtomicInteger aux = new AtomicInteger(1);
        view.printArray(connections.stream().map(a -> "" + (aux.getAndIncrement()) + ". " + a.ecommerce).toArray());
        System.out.println(aux + ". Exit");

        int flag = InputManager.decisionBranch();
        if (flag == aux.intValue()) { return; }
        if (flag > ecommerces.size()) {
            System.out.println("No orders from this e-commerce yet!");
            return;
        }
        Map<String, Integer> map = ecommerces.get(flag-1).getOrders();
        view.printArray(
                map.keySet().stream()
                        .map( a -> a + ": " + map.get(a) + " units.").toArray()
        );
    }

    public void showAllOrders() {
        view.printArray(ShoppingCartSingleton.synchronised().asArray());
    }

    private void updateEcommerces() {
        ecommerces = Network.fetchEcommerces();
        ecommerces = ecommerces.stream()
                .filter( ecommerce -> connections
                        .stream()
                        .anyMatch( user -> ecommerce.getName().equals(user.ecommerce)))
                .collect(Collectors.toList());
    }

}
