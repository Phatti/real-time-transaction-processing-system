package com.preeti.trading.service;

import com.preeti.trading.entity.Transaction;
import com.preeti.trading.event.TransactionEvent;
import com.preeti.trading.kafka.TransactionProducer;
import com.preeti.trading.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionProducer producer;

//    public TransactionService(TransactionRepository repository) {
//        this.repository = repository;
//    }

    public Transaction createTransaction(Transaction request) {
	request.setStatus("PENDING");
	request.setCreatedAt(LocalDateTime.now());

	Transaction saved = repository.save(request);

	// 🔥 Publish event
	TransactionEvent event = TransactionEvent.builder()
						 .referenceId(saved.getReferenceId())
						 .amount(saved.getAmount())
						 .currency(saved.getCurrency())
						 .status(saved.getStatus())
						 .build();

	producer.sendTransactionEvent(event);

	return saved;
    }
}