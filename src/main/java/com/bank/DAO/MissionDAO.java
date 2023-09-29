package com.bank.DAO;

import com.bank.Entity.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionDAO {
    public Optional<Mission> create(Mission mission);
    public int delete(int code);

    public Optional<List<Mission>> findAll();
    public Optional<Mission> findByCode(int code);
}
