package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.*;
import com.bank.Enum.AccountStatus;
import com.bank.Exception.DeleteException;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
            String query = "INSERT INTO client(code, firstName, lastName, birthDay, phone, address, employee_registrationNbr, agency_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(client.getAgency() == null);
            System.out.println(client.getEmployee() == null);
            stmt.setString(1, client.getCode());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getLastName());
            stmt.setDate(4, java.sql.Date.valueOf(client.getBirthDay()));
            stmt.setString(5, client.getPhone());
            stmt.setString(6, client.getAddress());
            stmt.setInt(7, client.getEmployee().getRegistrationNbr());
            stmt.setString(8, client.getAgency().getCode());
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
                clt.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get());
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
                clt.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get());
                list.add(clt);
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> find(Client client) {
        try{
            List<Client> list = new ArrayList<>();
            Client clt = new Client();
            String query = "SELECT * FROM client WHERE firstName LIKE ? AND lastName LIKE ? AND phone LIKE ? AND address LIKE ? AND birthDay = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%"+client.getFirstName()+"%");
            stmt.setString(2, "%"+client.getLastName()+"%");
            stmt.setString(3, "%"+client.getPhone()+"%");
            stmt.setString(4, "%"+client.getAddress()+"%");
            stmt.setDate(5, java.sql.Date.valueOf(client.getBirthDay()));
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                System.out.println(result.getString("code"));
                clt.setCode(result.getString("code"));
                clt.setPhone(result.getString("phone"));
                clt.setAddress(result.getString("address"));
                clt.setBirthDay(result.getDate("birthDay").toLocalDate());
                clt.setFirstName(result.getString("firstName"));
                clt.setLastName(result.getString("lastName"));
                clt.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get());
                list.add(clt);
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> getAccounts(Client client) {
        try{
            //get all saving
            String query = "SELECT * from getClientSavingAccounts(?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, client.getCode());
            ResultSet result = stmt.executeQuery();
            HashMap<String, List> accounts = new HashMap();
            List<SavingAccount> savings = new ArrayList<>();
            while(result.next()){
                Account account = new Account(0, result.getDouble("balance"), result.getDate("creationDate").toLocalDate(), AccountStatus.valueOf(result.getString("status")), null);
                savings.add(new SavingAccount(account, result.getDouble("tax"), result.getString("code")));
            }
            accounts.put("saving", savings);
            //get all current
            query = "SELECT * from getClientCurrentAccounts(?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, client.getCode());
            result = stmt.executeQuery();
            List<CurrentAccount> current = new ArrayList<>();
            while(result.next()){
                Account account = new Account(0, result.getDouble("balance"), result.getDate("creationDate").toLocalDate(), AccountStatus.valueOf(result.getString("status")), null);
                current.add(new CurrentAccount(account, result.getDouble("overDraft"), result.getString("code")));
            }
            accounts.put("current", current);
            client.setAccounts(accounts);
            return Optional.of(client);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

}
