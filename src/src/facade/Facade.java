package src.facade;

import src.controller.InputManager;
import src.model.ConnectionStatus;
import src.model.Ecommerce;
import src.model.Item;
import src.model.User;
import src.utility.ShoppingCartSingleton;

import java.util.ArrayList;

public class Facade {

    private EcommercesFacade ecommercesFacade;
    private ViewFacade viewFacade;
    private NetworkFacade networkFacade;
    private ReportFacade reportFacade;

    public Facade() {
        networkFacade = new NetworkFacade();
        ecommercesFacade = new EcommercesFacade();
        viewFacade = new ViewFacade();
        reportFacade = new ReportFacade();
    }

    public void loadOptions() {
        viewFacade.view.loadOptions();
    }

    public void connectToEcommerce(User user) {
        if (ecommercesFacade.asAny(user)) {
            print("Already connected to this ecommerce");
            return;
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
        updateEcommerces();
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

    public void addProduct(){
        if (ecommercesFacade.getEcommerces().isEmpty()) {
            print("No connected store yet!");
            return;
        }
        String selected = InputManager.selectFromList(
                "Select a ecommerce to add a product:",
                ecommercesFacade.ecommercesNamesList()
        );
        if (selected == null) { return; }
        Ecommerce e = ecommercesFacade.fromString(selected);
        if (e == null) { return; }

        Item product = InputManager.addProduct();
        ecommercesFacade.addProduct(product , e);
    }

    public void removeProduct(){
        if (ecommercesFacade.getEcommerces().isEmpty()) {
            print("No connected store yet!");
            return;
        }
        String selected = InputManager.selectFromList(
                "Select a ecommerce to remove a product:",
                ecommercesFacade.ecommercesNamesList()
        );
        if (selected == null) { return; }
        Ecommerce e = ecommercesFacade.fromString(selected);

        String productName = InputManager.selectFromList(
                "Select product to be removed:",
                ecommercesFacade.itemsNamesList()
        );
        if (productName == null) {
            print("Exiting");
            return;
        }
        ecommercesFacade.removeProduct(productName, e);
    }

    public void report() {
        System.out.println("Select what report you would like");
        System.out.println("1. Show all orders");
        System.out.println("2. Filter by store");
        System.out.println("3. Filter by agility");
        System.out.println("4. Filter by orders delivered on time");
        System.out.println("5. Exit");
        int input = 0;
        while (input < 1 || input > 5) {
            input = InputManager.decisionBranch();
            if (input < 1 || input > 5) {
                System.out.println("Please inform a valid number.");
            }
        }
        if (input == 5) {
            return;
        }
        if (input == 1) {
            viewFacade.print(ShoppingCartSingleton.synchronised().asArray());
        } else if (input == 2) {
            System.out.println("Inform the store name: ");
            String str = InputManager.next();
            viewFacade.print(reportFacade.filterByStore(
                    InputManager.next(),
                    new ArrayList(ecommercesFacade.getEcommerces())
            ).toArray());
        }
    }

    public void print(String message) {
        viewFacade.print(message);
    }
}
