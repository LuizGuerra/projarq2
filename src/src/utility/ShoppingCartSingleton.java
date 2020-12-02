package src.utility;

import src.model.Ecommerce;
import src.model.Item;

import java.util.HashMap;
import java.util.Map;

final public class ShoppingCartSingleton {

    private Map<Item, Integer> items;

    private static ShoppingCartSingleton shared;

    private ShoppingCartSingleton() {
        this.items = new HashMap<>();
    }

    public static ShoppingCartSingleton synchronised() {
        if (shared == null) {
            shared = new ShoppingCartSingleton();
        }
        return shared;
    }

    public void addItem(Item item, int amount) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + amount);
        } else {
            items.put(item, amount);
        }
    }

    public Boolean remove(Item item) {
        return items.remove(item) != null;
    }

    public void addFrom(Ecommerce ecommerce) {
        System.out.println("Adding items from " + ecommerce.getName());
        Map<String, Integer> map = ecommerce.getOrders();
        int counter = 0;
        for (String item : map.keySet()) {
            if (items.keySet().stream().anyMatch( a -> a.getName().equals(item))) {
                continue;
            }
            Item product = ecommerce.asItem(item);
            if (product == null) {
                System.out.println("Failed to add " + item + ".");
            } else {
                counter++;
                System.out.println("Adding product named " + item + ".");
                items.put(product, map.get(item));
            }
        }
        System.out.println("Added a total of " + counter + " items.");
    }

    public void removeFrom(Ecommerce ecommerce) {
        Map<String, Integer> map = ecommerce.getOrders();
        for (String item : map.keySet()) {
            Item product = ecommerce.asItem(item);
            if (product != null) {
                items.remove(product);
            }
        }
    }

    public boolean removeItem(Item item) {
        return items.remove(item) != null;
    }

    public float total() {
        float total = 0;
        for (Item key : items.keySet()) {
            total += items.get(key) * key.getPrice();
        }
        return total;
    }

    public Object[] asArray() {
        return items.keySet().stream()
                .map(key -> items.get(key) + " unit" +
                        (items.get(key)==1 ? "" : "s") +
                        " of " + key.getName())
                .toArray();
    }

    public Map<Item, Integer> getItems() {
        return (new HashMap<>(this.items));
    }
}
