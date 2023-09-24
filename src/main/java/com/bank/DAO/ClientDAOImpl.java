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
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Optional<Client> update(Client client) {
        try{
            if(client == null)
                throw new Exception("*****   Impossible de modifier un client vide   *****");
            String query = "UPDATE client SET firstName = ?, lastName = ?, birthDay = ?, phone = ?, address = ? WHERE code = ?";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setDate(3, java.sql.Date.valueOf(client.getBirthDay()));
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getCode());
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
    public Optional<Client> findByCode(String code) {
        try{
            Client clt = new Client();
            String query = "SELECT * FROM Client WHERE code = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, code);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                clt.setCode(result.getString("code"));
                clt.setPhone(result.getString("phone"));
                clt.setAddress(result.getString("address"));
                clt.setBirthDay(result.getDate("birthDay").toLocalDate());
                clt.setFirstName(result.getString("firstName"));
                clt.setLastName(result.getString("lastName"));
            }
            return Optional.of(clt);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> findAll() {
        try{
            Client clt = new Client();
            List<Client> list = new ArrayList<>();
            String query = "SELECT * FROM Client";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                clt.setCode(result.getString("code"));
                clt.setPhone(result.getString("phone"));
                clt.setAddress(result.getString("address"));
                clt.setBirthDay(result.getDate("birthDay").toLocalDate());
                clt.setFirstName(result.getString("firstName"));
                clt.setLastName(result.getString("lastName"));
                list.add(clt);
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
