package src.facade;

import src.controller.InputManager;
import src.model.ConnectionStatus;
import src.model.Ecommerce;
import src.model.User;
import src.utility.ShoppingCartSingleton;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Facade {

    private EcommercesFacade ecommercesFacade;
    private ViewFacade viewFacade;
    private NetworkFacade networkFacade;

    public Facade() {
        networkFacade = new NetworkFacade();
        ecommercesFacade = new EcommercesFacade();
        viewFacade = new ViewFacade();
    }

    public void loadOptions() {
        viewFacade.view.loadOptions();
    }

    public void connectToEcommerce(User user) {
        if (ecommercesFacade.asAny(user)) {
            print("Already connected to this ecommerce");
        }
        ConnectionStatus status = networkFacade.tryLogIn(user);
        viewFacade.ecommerceConnection(user.ecommerce, status);
        if (status == ConnectionStatus.successful) {
            ecommercesFacade.addConnection(user);
            updateEcommerces();
        }
    }

    public void removeEcommerce() {
        if (ecommercesFacade.getEcommerces().isEmpty()) {
            print("You are not connected to any store.");
            return;
        }
        String store = InputManager.selectFromList(
                "Select a store to be removed:",
                ecommercesFacade.ecommercesNamesList()
        );
        if (store == null) {
            print("Exiting");
            return;
        }
        Ecommerce e = ecommercesFacade.removeEcommerce(store);
        if (e != null && ecommercesFacade.removeLogIn(store) != null) {
            print("e-commerce removed successfully!");
            ShoppingCartSingleton.synchronised().removeFrom(e);
        } else {
            print("There was a problem removing the e-commerce");
        }
    }

    public void showAllEcommerces() {
        if (ecommercesFacade.getEcommerces().isEmpty()) {
            print("You are not connected to any store yet.");
            return;
        }
        print("Showing all connected e-commerces");
        viewFacade.print(ecommercesFacade.ecommercesNamesArray());
    }

    public void updateEcommerceInformation() {
//        print("Select a ecommerce to update:");
//        AtomicInteger aux = new AtomicInteger(1);
//        view.printArray(connections.stream().map(a -> "" + (aux.getAndIncrement()) + ". " + a.ecommerce).toArray());
//        System.out.println(aux + ". Exit");
//        int flag = InputManager.decisionBranch();
        print("Updating ecommerce information...");
        updateEcommerces();
        print("Update completed.");
        ecommercesFacade.getEcommerces().forEach(ShoppingCartSingleton.synchronised()::addFrom);
    }

    public void checkOrdersByEcommerce() {
        if (ecommercesFacade.getEcommerces().isEmpty()) {
            print("No connected store yet!");
            return;
        }
        print("Select a ecommerce to check your orders:");
        String selected = InputManager.selectFromList(
                "Select a ecommerce to check your orders:",
                ecommercesFacade.ecommercesNamesList()
        );
        if (selected == null) { return; }
        Ecommerce e = ecommercesFacade.fromString(selected);
        if (e == null) { return; }
        viewFacade.print(e.getOrders().keySet()
                .stream()
                .map(a -> a + ": " + e.getOrders().get(a) + " units.")
                .toArray());
    }

    public void showAllOrders() {
        Object[] array = ShoppingCartSingleton.synchronised().asArray();
        if (array.length == 0) {
            print("You don't have any registered order");
            return;
        }
        viewFacade.print(ShoppingCartSingleton.synchronised().asArray());
    }

    public void updateEcommerces() {
        ecommercesFacade.update(networkFacade.fetchEcommerces());
    }

    public void print(String message) {
        viewFacade.print(message);
    }
}