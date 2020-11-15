package src.controller;

import src.model.ConnectionStatus;
import src.model.Ecommerce;
import src.model.User;
import src.networking.Network;
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
        while (flag != 6) {
            view.loadOptions();
            flag = InputManager.decisionBranch();
            if (flag == 0) { continue; }
            switch (flag) {
                case 1 -> connectToEcommerce();
                case 2 -> updateEcommerceInformation();
                case 3 -> checkEcommerceOrders();
                case 6 -> view.printMessage("Exiting...");
                default -> System.out.println("Default");
            }
        }
        view.printMessage("Process exited.");
    }

    public void connectToEcommerce() {
        User user = InputManager.connectToEcommerce();
        if (connections.stream().anyMatch (a -> user.ecommerce.equals(a.ecommerce))) {
            view.printMessage("Already connected to this ecommerce");
            return;
        }
        ConnectionStatus status = Network.tryLogIn(user);
        view.ecommerceConnection(user.ecommerce, status);
        if (status == ConnectionStatus.successful) {
            connections.add(user);
        }
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
        view.printMessage("Printing e-commerces information:");
        view.printArray(ecommerces.toArray());
    }

    public void checkEcommerceOrders() {
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
        Map<String, Integer> map = ecommerces.get(flag-1).getOrders();
        view.printArray(
                map.keySet().stream()
                        .map( a -> a + ": " + map.get(a) + " units.").toArray()
        );
    }

    private void updateEcommerces() {
        ecommerces = Network.fetchEcommerces();
        ecommerces = ecommerces.stream()
                .filter( a ->connections.stream().anyMatch( b -> a.getName().equals(b.ecommerce)))
                .collect(Collectors.toList());
    }

}
