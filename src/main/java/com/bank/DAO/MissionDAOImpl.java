package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

public class MissionDAOImpl implements MissionDAO{

    Connection connection;

    public MissionDAOImpl(){
        connection = JDBCConnection.getConnection();
    }

    @Override
    public Optional<Mission> createMission(Mission mission) {
        try{
            if(mission == null)
                throw new Exception("*****   Impossible d'ajouter un compte vide   *****");
            String query = "INSERT INTO mission(name, description) VALUES(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, mission.getName());
            stmt.setString(2, mission.getDescription());
            stmt
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
