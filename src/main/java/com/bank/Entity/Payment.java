package com.bank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Payment {
    @NonNull
    private int id;
    @NonNull
    private double balance;
    @NonNull
    private Account from;
    @NonNull
    private Account to;
    @NonNull
    private Employee employee;
    @NonNull
    private LocalDateTime transaction_time;
}
