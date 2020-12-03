package src.model;

import java.util.Date;
import java.util.Objects;

public class Order {
    private OrderStatus status;
    private Item item;
    private Integer quantity;
    private Date paymentDay, outForDelivery, delivered;

    public Order(OrderStatus status, Item item, Integer quantity) {
        this.status = status;
        this.item = item;
        this.quantity = quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Date getOutForDelivery() {
        return outForDelivery;
    }

    public void setOutForDelivery(Date outForDelivery) {
        this.outForDelivery = outForDelivery;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return "Item " + item.getName() + ", with status " + status;
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
