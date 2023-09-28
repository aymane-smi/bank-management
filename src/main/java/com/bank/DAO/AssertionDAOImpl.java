package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.MissionEmployee;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class AssertionDAOImpl implements AssertionDAO {
    private Connection connection;

    public AssertionDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<MissionEmployee> create(MissionEmployee missionEmployee) {
        try{
            if(missionEmployee == null)
                throw new Exception("*****   Impossible d'ajouter des informations vide   *****");
            String query = "INSERT INTO mission_employee(mission_code, employee_registrationNbr, startDate, endDate) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, missionEmployee.getMission().getCode());
            stmt.setInt(2, missionEmployee.getEmployee().getRegistrationNbr());
            stmt.setDate(3, java.sql.Date.valueOf(missionEmployee.getStartDate()));
            stmt.setDate(4, java.sql.Date.valueOf(missionEmployee.getEndDate()));
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    missionEmployee.setId(generatedId);
                }
                return Optional.of(missionEmployee);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
