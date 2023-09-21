package com.bank.DAO;

import com.bank.Entity.Client;

import java.util.Optional;

public interface ClientDAO {
    public Optional<Client> create(Client client);
}
