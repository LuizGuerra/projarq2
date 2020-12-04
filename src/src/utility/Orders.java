package src.utility;

import src.model.Item;
import src.model.Order;
import src.model.OrderStatus;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Orders {

    private static Comparator<Order> orderDateComparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            if (o1.getDelivered() != null && o2.getDelivered() != null) {
                return o1.getDelivered().compareTo(o2.getDelivered());
            }
            if (nullXor(o1.getDelivered(), o2.getDelivered())) {
                return o1.getDelivered() == null ? 1 : -1;
            }
            if (o1.getPaymentDay() == null && o2.getPaymentDay() == null) { return 0; }
            if (o1.getPaymentDay() == null) { return -1; }
            if (o2.getPaymentDay() == null) { return 1; }
            return o1.getPaymentDay().compareTo(o2.getPaymentDay());
        }
        private boolean nullXor(Date d1, Date d2) {
            return (d1==null && d2!=null) || (d1!=null && d2==null);
        }
    };

    private static LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Filters an order list by given store
     * @param store name
     * @param orders list
     * @return Order list with only one common store
     */
    public static List<Order> filter(String store, List<Order> orders) {
        return orders.stream().filter( order -> order.getStore().equals(store)).collect(Collectors.toList());
    }

    /**
     * Sorts a list by delivery date. If it wasn't delivered yet, it will sort by purchase date
     * @param orders
     * @return sorted list
     */
    public static List<Order> sort(List<Order> orders) {
        return orders.stream()
                .sorted(orderDateComparator)
                .collect(Collectors.toList());
    }

    /**
     * Filters an Order list that were delivered within 10 days of payment
     * @param orders
     * @return Filtered order list
     */
    public static List<Order> filterByAgility(List<Order> orders) {
        return orders
                .stream()
                .filter( o -> o.getStatus() == OrderStatus.delivered && Period.between(
                        convertToLocalDate(o.getPaymentDay()), convertToLocalDate(o.getDelivered())
                        ).getDays() <= 10)
                .collect(Collectors.toList());
    }

    /**
     * Filters an Order List that were delivered withing the delivery forecast
     * @param orders
     * @return Filtered order list
     */
    public static List<Order> filter(List<Order> orders) {
        return orders.stream()
                .filter( o -> o.getDelivered() != null && o.getDeliveryForecast() != null &&
                        !o.getDelivered().before(o.getDeliveryForecast()) )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        TESTS
        List<Order> orderList = Arrays.asList(
                new Order("Amazon", OrderStatus.waitingPayment, new Item("iPhone", 299),
                        1, new Date(2020, 12, 10)),
                new Order("Amazon", OrderStatus.dispatched, new Item("iPhone", 299),
                        1, new Date(2020, 12, 10),
                        new Date(2020, 12, 1), new Date(2020, 12, 1)),

                new Order("Ebay", OrderStatus.delivered, new Item("iPhone", 299),
                        1, new Date(2020, 12, 10),
                        new Date(2020, 12, 1), new Date(2020, 12, 1),
                        new Date(2020, 12, 5)),
                new Order("Ebay", OrderStatus.delivered, new Item("iPhone", 299),
                        1, new Date(2020, 12, 10),
                        new Date(2020, 12, 1), new Date(2020, 12, 1),
                        new Date(2020, 12, 10)),
                new Order("Ebay", OrderStatus.delivered, new Item("iPhone", 299),
                        1, new Date(2020, 12, 10),
                        new Date(2020, 12, 1), new Date(2020, 12, 1),
                        new Date(2020, 12, 15))
                );
    }
}
