package com.bank.DAO;

import com.bank.Entity.Payment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO {
    public Optional<Payment> create(Payment payment);
    public int delete(int id);
    public List<Payment> findByDate(LocalDate date);
}
