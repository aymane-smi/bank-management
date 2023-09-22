package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Client;
import com.bank.Entity.Employee;
import com.bank.Exception.DeleteException;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO{
    private Connection connection;

    public ClientDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Client> create(Client client) {
        try{
            if(client == null)
                throw new Exception("*****   Impossible d'ajouter un client vide   *****");
            String query = "INSERT INTO client(code, firstName, lastName, birthDay, phone, address) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getCode());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getLastName());
            stmt.setDate(4, java.sql.Date.valueOf(client.getBirthDay()));
            stmt.setString(5, client.getPhone());
            stmt.setString(6, client.getAddress());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            return Optional.of(client);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int delete(String code) {
        try{
            if(code.isEmpty())
                throw new Exception("*****   CODE CLIENT EST VIDE   *****");
            String query = "DELETE FROM client WHERE code = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, code);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new DeleteException();
            else{
                return affectedRows;
            }

        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return 0;
    }
}
