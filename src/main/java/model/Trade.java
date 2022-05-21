package model;

import java.math.BigDecimal;

public class Trade {
    private final String buyOrderId;
    private final BigDecimal sellPrice;
    private final int quantity;
    private final String sellOrderId;

    public Trade(String buyOrderId, BigDecimal sellPrice, int quantity, String sellOrderId) {
        this.buyOrderId = buyOrderId;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.sellOrderId = sellOrderId;
    }

    @Override
    public String toString() {
        return "" + buyOrderId + " " + sellPrice + " " + quantity + " " + sellOrderId;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }
}
