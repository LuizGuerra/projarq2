package src.utility;

import src.model.Ecommerce;
import src.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


final public class ShoppingCartSingleton {

    private Map<Item, Integer> items;
    public ArrayList<ShoppingCartObserver> obs = new ArrayList<>();

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
        notificarObserver();
    }

    public void addObserver(ShoppingCartObserver o) {
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    public void removeObserver(ShoppingCartObserver o) {
        obs.remove(o);
    }

    public Boolean remove(Item item) {
        notificarObserver();
        return items.remove(item) != null;
    }

    public void notificarObserver() {
        for (ShoppingCartObserver o : obs) {
            o.update();
        }
    }

    public void addFrom(Ecommerce ecommerce) {
        System.out.println("Loading items from " + ecommerce.getName());
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
                System.out.println("Load " + item + ".");
                items.put(product, map.get(item));
            }
        }
        if (counter > 0 && !items.isEmpty()) {
            notificarObserver();
        }
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

    public void removeItemFromName(String itemName) {
        Iterator<Item> iter = items.keySet().iterator();
        while (iter.hasNext()) {
            Item p = iter.next();
            if (p.getName().equals(itemName)) {
                iter.remove();
                notificarObserver();
            }
        }
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
