package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.MissionEmployee;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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

    @Override
    public int delete(int code){
        try{
            if(code == 0)
                throw new Exception("*****   IL N'EXISTE AUCUNNE ASSERTION AVEC CODE 0   *****");
            String query = "DELETE FROM mission_employee WHERE id = ?";
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
    @Override
    public Optional<List<MissionEmployee>> findByEmployee(int registrationNbr){
        try{
            List<MissionEmployee> list = new ArrayList<>();
            MissionEmployee tmp = new MissionEmployee();
            if(registrationNbr == 0)
                throw new Exception("*****   IL N'EXISTE AUCUN EMPLOYEE AVEC NOMBRE DE MATRICULE 0   *****");
            String query = "SELECT * FROM mission_employee WHERE employee_registrationNbr = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, registrationNbr);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                tmp.setId(result.getInt("id"));
                tmp.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get());
                tmp.setMission(new MissionDAOImpl().findByCode(result.getInt("mission_code")).get());
                tmp.setStartDate(result.getDate("startDate").toLocalDate());
                tmp.setEndDate(result.getDate("endDate").toLocalDate());
                list.add(tmp);
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<HashMap<Integer, Integer>> getStatistics() {
        try{
            HashMap<Integer, Integer> map = new HashMap<>();
            String query = "SELECT mission_code, COUNT(employee_registrationNbr) AS employee_count FROM mission_employee GROUP BY mission_code;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                map.put(result.getInt("mission_code"), result.getInt("employee_count"));
            }
            return Optional.of(map);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
