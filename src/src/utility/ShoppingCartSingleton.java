package src.utility;

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

    public Map<Item, Integer> getItems() {
        return (new HashMap<>(this.items));
    }
}
