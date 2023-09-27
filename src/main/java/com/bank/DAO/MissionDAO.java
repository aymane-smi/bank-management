package com.bank.DAO;

import com.bank.Entity.Mission;

import java.util.Optional;

public interface MissionDAO {
    public Optional<Mission> createMission(Mission mission);
}
