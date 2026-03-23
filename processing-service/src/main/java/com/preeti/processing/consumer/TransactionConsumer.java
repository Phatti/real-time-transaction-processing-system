package com.preeti.processing.consumer;

import com.preeti.processing.model.TransactionEvent;
import com.preeti.processing.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionConsumer {

    private final ProcessingService processingService;

    @KafkaListener(topics = "transactions-topic", groupId = "transaction-group")
    public void consume(TransactionEvent event) {
        log.info("Received event: {}", event);

        processingService.processTransaction(event);
    }

}