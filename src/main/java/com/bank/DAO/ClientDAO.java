package com.bank.DAO;

import com.bank.Entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    public Optional<Client> create(Client client);
    public int delete(String code);

    public Optional<Client> findByCode(String code);
    public Optional<List<Client>> findAll();
}
