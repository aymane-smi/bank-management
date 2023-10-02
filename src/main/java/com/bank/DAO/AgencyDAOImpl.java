package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Agency;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyDAOImpl implements AgencyDAO{
    Connection connection;

    public AgencyDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Agency> create(Agency agency) {
        try{
            if(agency == null)
                throw new Exception("*****   Impossible d'ajouter une agence vide   *****");
            String query = "INSERT INTO agency(name, address, phone) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, agency.getName());
            stmt.setString(2, agency.getAddress());
            stmt.setString(3, agency.getPhone());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String generatedId = generatedKeys.getString(1);
                    agency.setCode(generatedId);
                }
                return Optional.of(agency);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int delete(String code) {
        try{
            if(code == "")
                throw new Exception("*****   LE CODE DE L'AGENCE NE PEUT PAS ETRE VIDE   *****");
            String query = "DELETE FROM agency WHERE code = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, code);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new Exception("*****   AUCUNNE AGENCE N'EST SUPPRIMER   *****");
            return affectedRows;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Agency> update(Agency agency) {
        try{
            if(agency == null)
                throw new Exception("*****   Impossible d'ajouter une agence vide   *****");
            String query = "UPDATE agency SET name = ?, address = ?, phone = ? WHERE code = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, agency.getName());
            stmt.setString(2, agency.getAddress());
            stmt.setString(3, agency.getPhone());
            stmt.setString(4, agency.getCode());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 1)
                return Optional.of(agency);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Agency> findByCode(String code) {
        try{
            if(code == "")
                throw new Exception("*****   LE CODE DE L'AGENCE NE PEUT PAS ETRE VIDE   *****");
            String query = "SELECT *  FROM agency WHERE code = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, code);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                return Optional.of(
                        new Agency(result.getString("code"), result.getString("name"), result.getString("address"), result.getString("phone"))
                );
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Agency>> find() {
        try{
            List<Agency> list = new ArrayList<>();
            String query = "SELECT *  FROM agency";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                list.add(
                        new Agency(result.getString("code"), result.getString("name"), result.getString("address"), result.getString("phone"))
                );
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
