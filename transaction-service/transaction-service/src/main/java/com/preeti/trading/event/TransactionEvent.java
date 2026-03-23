package com.preeti.trading.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEvent {

    private String referenceId;
    private Double amount;
    private String currency;
    private String status;
}