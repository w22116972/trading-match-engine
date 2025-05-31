package io.github.anderwang.trading.matching.service;

import io.github.anderwang.trading.matching.config.ApplicationConfig;
import io.github.anderwang.trading.matching.model.MatchResult;
import io.github.anderwang.trading.matching.model.OrderDto;
import io.github.anderwang.trading.matching.model.TradeEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchingService {
    private final StringRedisTemplate redisTemplate;
    private final DefaultRedisScript<List<Map<String, Object>>> matchingScript;
    private final KafkaTemplate<String, TradeEvent> kafkaTemplate;
    private final ApplicationConfig props;

    public MatchingService(StringRedisTemplate redisTemplate,
                           DefaultRedisScript<List<Map<String, Object>>> matchingScript,
                           KafkaTemplate<String, TradeEvent> kafkaTemplate,
                           ApplicationConfig props) {
        this.redisTemplate = redisTemplate;
        this.matchingScript = matchingScript;
        this.kafkaTemplate = kafkaTemplate;
        this.props = props;
    }

    public void matchAndPublish(OrderDto order) {
        String bookKey = props.getOrderBookPrefix() + (order.isBuy() ? "buy" : "sell");
        List<String> keys = Collections.singletonList(bookKey);
        List<String> args = Arrays.asList(
            String.valueOf(order.getPrice()),
            String.valueOf(order.getQty()),
            order.getOrderId()
        );

        List<Map<String, Object>> rawResults =
            redisTemplate.execute(matchingScript, keys, args.toArray());
        if (rawResults != null) {
            for (Map<String, Object> res : rawResults) {
                MatchResult mr = new MatchResult(
                    (String) res.get("orderId"),
                    ((Number) res.get("qty")).longValue(),
                    ((Number) res.get("price")).doubleValue()
                );
                TradeEvent evt = new TradeEvent(
                    order.isBuy() ? order.getOrderId() : mr.getOrderId(),
                    order.isBuy() ? mr.getOrderId() : order.getOrderId(),
                    mr.getQty(),
                    mr.getPrice(),
                    System.currentTimeMillis()
                );
                kafkaTemplate.send(props.getTradeExecutedTopic(), evt);
            }
        }
    }
}
