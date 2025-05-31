package io.github.anderwang.trading.matching.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {
    private String newOrderTopic;
    private String tradeExecutedTopic;
    private String orderBookPrefix;

    public String getNewOrderTopic() { return newOrderTopic; }
    public void setNewOrderTopic(String newOrderTopic) { this.newOrderTopic = newOrderTopic; }
    public String getTradeExecutedTopic() { return tradeExecutedTopic; }
    public void setTradeExecutedTopic(String tradeExecutedTopic) { this.tradeExecutedTopic = tradeExecutedTopic; }
    public String getOrderBookPrefix() { return orderBookPrefix; }
    public void setOrderBookPrefix(String orderBookPrefix) { this.orderBookPrefix = orderBookPrefix; }
}
