package com.bank.DAO;

import com.bank.Entity.Operation;

import java.util.Optional;

public interface OpertionDAO{
    public Optional<Operation> create(Operation operation);
    public int delete(Operation operation);
    public Optional<Operation> findByNumber(Operation operation);
}
