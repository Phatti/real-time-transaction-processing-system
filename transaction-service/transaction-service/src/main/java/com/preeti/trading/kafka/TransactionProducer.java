package com.preeti.trading.kafka;

import com.preeti.trading.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    private static final String TOPIC = "transactions-topic";

    public void sendTransactionEvent(TransactionEvent event) {
        log.info("Sending transaction event: {}", event);
        kafkaTemplate.send(TOPIC, event);
    }
}