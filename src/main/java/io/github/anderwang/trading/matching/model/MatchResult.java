package io.github.anderwang.trading.matching.model;

public class MatchResult {
    private final String orderId;
    private final long qty;
    private final double price;

    public MatchResult(String orderId, long qty, double price) {
        this.orderId = orderId;
        this.qty = qty;
        this.price = price;
    }

    public String getOrderId() { return orderId; }
    public long getQty() { return qty; }
    public double getPrice() { return price; }
}