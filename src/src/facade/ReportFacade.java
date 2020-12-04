package src.facade;

import src.model.Order;
import src.utility.Orders;

import java.util.ArrayList;
import java.util.List;

public class ReportFacade {

    public ReportFacade() { }

    public List<Order> filterByStore(String store, List<Order> orders) {
        if (store == null || store.isEmpty() || orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return Orders.filter(store, orders);
    }

    public List<Order> sort(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return Orders.sort(orders);
    }

    public List<Order> byAgility(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return Orders.filterByAgility(orders);
    }

    public List<Order> inTime(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return Orders.filter(orders);
    }
}
