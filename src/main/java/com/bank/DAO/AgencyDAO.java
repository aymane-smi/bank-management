package com.bank.DAO;

import com.bank.Entity.Agency;

import java.util.Optional;

public interface AgencyDAO {
    public Optional<Agency> create(Agency agency);
    public int delete(String code);

    public Optional<Agency> update(Agency agency);
    public Optional<Agency> findByCode(String code);
}
