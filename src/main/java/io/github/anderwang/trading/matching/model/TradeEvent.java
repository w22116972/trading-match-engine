package io.github.anderwang.trading.matching.model;

public class TradeEvent {
    private String buyOrderId;
    private String sellOrderId;
    private long qty;
    private double price;
    private long timestamp;

    public TradeEvent() {}

    public TradeEvent(String buyOrderId, String sellOrderId, long qty, double price, long timestamp) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.qty = qty;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getBuyOrderId() { return buyOrderId; }
    public void setBuyOrderId(String buyOrderId) { this.buyOrderId = buyOrderId; }
    public String getSellOrderId() { return sellOrderId; }
    public void setSellOrderId(String sellOrderId) { this.sellOrderId = sellOrderId; }
    public long getQty() { return qty; }
    public void setQty(long qty) { this.qty = qty; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
