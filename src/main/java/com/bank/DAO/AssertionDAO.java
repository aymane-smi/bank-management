package com.bank.DAO;

import com.bank.Entity.MissionEmployee;

import java.util.Optional;

public interface AssertionDAO {
    public Optional<MissionEmployee> create(MissionEmployee missionEmployee);
    public int delete(int id);
}
