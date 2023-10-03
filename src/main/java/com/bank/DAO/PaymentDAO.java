package com.bank.DAO;

import com.bank.Entity.Payment;

import java.util.Optional;

public interface PaymentDAO {
    public Optional<Payment> create(Payment payment);
}
