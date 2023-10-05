package com.bank.DAO;

import com.bank.Entity.Credit;

import java.util.Optional;

public interface CreditDAO {
    public Optional<Credit> create(Credit credit);
}
