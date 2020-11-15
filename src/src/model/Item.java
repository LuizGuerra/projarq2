package src.model;

import java.util.UUID;

public class Item {
    private final UUID uuid;
    private final String name;
    private final float price;

    public Item(String name, float price) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public String getUUID() {
        return uuid.toString();
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public static Item transform(String itemString) {
        String [] str = itemString.split(":");
        return (new Item(str[0], Float.parseFloat(str[1])));
    }

    @Override
    public String toString() {
        return "Item { " + name + ", U$ " + price + " }";
    }
}
