package com.preeti.processing.service;

import com.preeti.processing.model.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class ProcessingService {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public void processTransaction(TransactionEvent event) {

        log.info("Processing transaction: {}", event.getReferenceId());

        // 🔥 Simulate failure
        if (event.getAmount() > 500) {
            throw new RuntimeException("Simulated failure!");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("Transaction processed successfully: {}", event.getReferenceId());

    }
}