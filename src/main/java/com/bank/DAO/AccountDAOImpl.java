package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Account;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO{
    Connection connection;

    public AccountDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Account> createAccount(Account account) {
        try{
            if(account == null)
                throw new Exception("*****   Impossible d'ajouter un compte vide   *****");
            String query = "INSERT INTO account(balance, creationDate, status, client_code) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, account.getBalance());
            stmt.setDate(2, java.sql.Date.valueOf(account.getCreationDate()));
            stmt.setString(3, account.getStatus().name());
            stmt.setString(4, account.getClient().getCode());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    account.setNumber(generatedId);
                }
                return Optional.of(account);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<SavingAccount> createSavingAccount(SavingAccount account) {
        try{
            if(account == null)
                throw new Exception("*****   Impossible d'ajouter un compte vide   *****");
            String query = "INSERT INTO saving_account(code, account_number, tax) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, account.getCode());
            stmt.setInt(2, account.getNumber());
            stmt.setDouble(3, account.getTax());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            return Optional.of(account);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public Optional<CurrentAccount> createCurrentAccount(CurrentAccount account) {
        try{
            if(account == null)
                throw new Exception("*****   Impossible d'ajouter un compte vide   *****");
            String query = "INSERT INTO current_account(code, account_number, overDraft) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, account.getCode());
            stmt.setInt(2, account.getNumber());
            stmt.setDouble(3, account.getOverDraft());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            return Optional.of(account);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
