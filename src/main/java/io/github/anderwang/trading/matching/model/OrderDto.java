package io.github.anderwang.trading.matching.model;

public class OrderDto {
    private String orderId;
    private String symbol;
    private double price;
    private long qty;
    private boolean buy;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public long getQty() { return qty; }
    public void setQty(long qty) { this.qty = qty; }
    public boolean isBuy() { return buy; }
    public void setBuy(boolean buy) { this.buy = buy; }
}