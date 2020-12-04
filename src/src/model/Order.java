package src.model;

import java.util.Date;
import java.util.Objects;

// package
public class Order {
    private String store;
    private OrderStatus status;
    private Item item;
    private Integer quantity;
    private Date deliveryForecast, paymentDay, outForDelivery, delivered;

    public Order(String store, OrderStatus status, Item item, Integer quantity, Date deliveryForecast) {
        this.store = store;
        this.deliveryForecast = deliveryForecast;
        this.status = status;
        this.item = item;
        this.quantity = quantity;
    }

    public Order(String store, OrderStatus status, Item item, Integer quantity, Date deliveryForecast, Date paymentDay) {
        this.store = store;
        this.status = status;
        this.item = item;
        this.quantity = quantity;
        this.deliveryForecast = deliveryForecast;
        this.paymentDay = paymentDay;
    }

    public Order(String store, OrderStatus status, Item item, Integer quantity, Date deliveryForecast, Date paymentDay, Date outForDelivery) {
        this.store = store;
        this.status = status;
        this.item = item;
        this.quantity = quantity;
        this.deliveryForecast = deliveryForecast;
        this.paymentDay = paymentDay;
        this.outForDelivery = outForDelivery;
    }

    public Order(String store, OrderStatus status, Item item, Integer quantity, Date deliveryForecast, Date paymentDay, Date outForDelivery, Date delivered) {
        this.store = store;
        this.status = status;
        this.item = item;
        this.quantity = quantity;
        this.deliveryForecast = deliveryForecast;
        this.paymentDay = paymentDay;
        this.outForDelivery = outForDelivery;
        this.delivered = delivered;
    }

    public String getStore() {
        return store;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getPaymentDay() {
        return paymentDay;
    }

    public Date getOutForDelivery() {
        return outForDelivery;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
    }

    public void setOutForDelivery(Date outForDelivery) {
        this.outForDelivery = outForDelivery;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }

    @Override
    public String toString() {
        return "Item { " + item.getName() + ", with status " + status + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return status == order.status &&
                Objects.equals(item, order.item) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(paymentDay, order.paymentDay) &&
                Objects.equals(outForDelivery, order.outForDelivery) &&
                Objects.equals(delivered, order.delivered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, item, quantity, paymentDay, outForDelivery, delivered);
    }
}
