package src.controller;

import src.facade.Facade;
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
    private Facade facade;

    public ViewController() {
        facade = new Facade();
        facade.updateEcommerces();
    }

    public void start() {
        int flag = -1;
        while (flag != 10) {
            facade.loadOptions();
            flag = InputManager.decisionBranch();
            if (flag == 0) { continue; }
            switch (flag) {
                case 1 -> facade.connectToEcommerce(InputManager.connectToEcommerce());
                case 2 -> facade.removeEcommerce();
                case 3 -> facade.showAllEcommerces();
                case 4 -> facade.updateEcommerceInformation();
                case 5 -> facade.checkOrdersByEcommerce();
                case 6 -> facade.showAllOrders();
                case 10 -> facade.print("Exiting...");
                default -> facade.print("Default reached.");
            }
        }
        facade.print("Process exited.");
    }

}
