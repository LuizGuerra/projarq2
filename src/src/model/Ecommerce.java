package src.model;

import java.util.*;
import java.util.stream.Collectors;

public class Ecommerce {
    private final String name;
    private List<Item> products;
    private Map<String,Integer> orders;
    private List <Order> packages;

    public Ecommerce(String url) {
        this.name = url;
        this.products = new ArrayList<>();
        this.orders = new HashMap<>();
        this.packages = new ArrayList<>();
    }

    public Ecommerce(String url, List<Item> items) {
        this.name = url;
        this.products = items;
        this.orders = new HashMap<>();
        this.packages = new ArrayList<>();
    }

    public Ecommerce(String name, List<Item> products, Map<String, Integer> orders) {
        this.name = name;
        this.products = products;
        this.orders = orders;
        this.packages = new ArrayList<>();
    }

    public void updatePackagesArray() {
        Random r = new Random();
        packages = orders.keySet().stream()
                .map( k -> new Order(getName(), OrderStatus.payed,
                        asItem(k), orders.get(k), new Date(2020, 12, 10) ))
                .collect(Collectors.toList());
        if (packages.size() > 1) {
            for (int i = 0; i < packages.size()-1; i++) {
                Order o = packages.get(i);
                o.setStatus(OrderStatus.delivered);
                o.setDelivered(new Date(2020, 12, 7));
                o.setPaymentDay(new Date(2020, 12, 1));
                o.setOutForDelivery(new Date(2020, 12, 6));
            }
        }
    }

    public Item asItem(String item) {
        List <Item> items = products.stream()
                .filter( product -> product.getName().equals(item))
                .collect(Collectors.toList());
        if (items.isEmpty()) {
            return null;
        }
        return items.get(0);
    }

    public static Ecommerce transform(String ecommerceString) {
        String [] str = ecommerceString.split(";");
        return (new Ecommerce(
                str[0],
                Arrays.stream(str[1].split("-"))
                        .map(Item::transform).collect(Collectors.toList()),
                mapOrders(str[2].split("-"))
                ));
    }

    public static Map<String,Integer> mapOrders(String[] orders) {
        Map<String, Integer> map = new HashMap<>();
        String [] aux = new String[2];
        for (String str : orders) {
            aux = str.split(":");
            map.put(aux[0], Integer.parseInt(aux[1]));
        }
        return map;
    }

    public String getName() {
        return name;
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Integer> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Ecommerce { " + name + ", products = " + products +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ecommerce ecommerce = (Ecommerce) o;
        return name.equals(ecommerce.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, products, orders);
    }
}
