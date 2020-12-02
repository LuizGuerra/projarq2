package src.facade;

import src.model.ConnectionStatus;
import src.view.View;

public class ViewFacade {
    final View view;

    public ViewFacade() {
        this.view = new View();
    }

    public void ecommerceConnection(String ecommerce, ConnectionStatus connectionStatus) {
        view.ecommerceConnection(ecommerce, connectionStatus);
    }

    public void print(Object[] objects) {
        view.printArray(objects);
    }

    public void print(String message) {
        view.printMessage(message);
    }
}
