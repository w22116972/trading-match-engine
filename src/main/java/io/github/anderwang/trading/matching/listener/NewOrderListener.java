package io.github.anderwang.trading.matching.listener;

import io.github.anderwang.trading.matching.model.OrderDto;
import io.github.anderwang.trading.matching.service.MatchingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderListener {
    private final MatchingService matchingService;

    public NewOrderListener(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @KafkaListener(topics = "${app.newOrderTopic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(OrderDto order) {
        matchingService.matchAndPublish(order);
    }
}
