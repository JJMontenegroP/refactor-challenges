package model;

public class Order {
    private final int orderId;
    private final String orderDate;
    private final int itemId;
    private final int quantity;

    public Order(int orderId, String orderDate, int itemId, int quantity) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
