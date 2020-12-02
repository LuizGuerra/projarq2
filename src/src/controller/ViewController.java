package src.controller;

import src.facade.Facade;

public class ViewController {
    private final Facade facade;

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
                case 1: facade.connectToEcommerce(InputManager.connectToEcommerce()); break;
                case 2: facade.removeEcommerce(); break;
                case 3: facade.showAllEcommerces(); break;
                case 4: facade.updateEcommerceInformation(); break;
                case 5: facade.checkOrdersByEcommerce(); break;
                case 6: facade.showAllOrders(); break;
                case 10: facade.print("Exiting..."); break;
                default: facade.print("Default reached.");
            }
        }
        facade.print("Process exited.");
    }

}
