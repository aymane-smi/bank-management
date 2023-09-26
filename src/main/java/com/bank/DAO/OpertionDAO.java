package com.bank.DAO;

import com.bank.Entity.Operation;

import java.util.Optional;

public interface OpertionDAO{
    public Optional<Operation> create(Operation operation);
}
