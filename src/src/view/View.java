package src.view;

import src.model.ConnectionStatus;

import java.util.Arrays;

/*  gerar relatório de compras
    > todos
    > por ecommerce
    > por data
    > por agilidade de entrega -> 10 dias
    > por cumprimento de prazo
* */

public class View {

    public View() {}

    public void loadOptions() {
        System.out.println("\nType the number of what you want to do");
        System.out.println("1: Connect to an e-commerce");
        System.out.println("2: Remove an connected e-commerce");
        System.out.println("3. Check all connected e-commerces");
        System.out.println("4: Update e-commerce orders");
        System.out.println("5: Check orders by e-commerce");
        System.out.println("6. Check all orders");
        System.out.println(". Generate report");
        System.out.println(". Exit");
    }

    public void ecommerceConnection(String ecommerce, ConnectionStatus connectionStatus) {
        System.out.println(connectionStatus.toMessage(ecommerce));
        if (connectionStatus == ConnectionStatus.successful) {
            System.out.println(ecommerce + " was added to connected stores.");
        }
    }

    public void printArray(Object[] objects) {
        Arrays.stream(objects).forEach(System.out::println);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
