package model;

public class Sale {
    private final int saleId;
    private final String saleDate;
    private final int itemId;
    private final int quantity;

    public Sale(int saleId, String saleDate, int itemId, int quantity) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getSaleId() {
        return saleId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}