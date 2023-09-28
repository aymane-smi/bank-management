package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Mission;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class MissionDAOImpl implements MissionDAO{

    Connection connection;

    public MissionDAOImpl(){
        connection = JDBCConnection.getConnection();
    }

    @Override
    public Optional<Mission> create(Mission mission) {
        try{
            if(mission == null)
                throw new Exception("*****   Impossible d'ajouter un compte vide   *****");
            String query = "INSERT INTO mission(name, description) VALUES(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, mission.getName());
            stmt.setString(2, mission.getDescription());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    mission.setCode(generatedId);
                }
                return Optional.of(mission);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int delete(int code) {
        try{
            if(code == 0)
                throw new Exception("*****   IL N'EXISTE AUCUNNE MISSION AVEC CODE 0   *****");
            String query = "DELETE FROM mission WHERE code = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, code);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            return affectedRows;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return 0;
    }
}
