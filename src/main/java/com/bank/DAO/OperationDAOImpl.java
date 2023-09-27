package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Operation;
import com.bank.Enum.OperationType;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class OperationDAOImpl implements OpertionDAO{
    Connection connection;
    public OperationDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Operation> create(Operation operation) {
        try{
            if(operation == null)
                throw new Exception("*****   Impossible d'ajouter une operation vide   *****");
            String query = "INSERT INTO operation(creationDate, amount, type, account_number, employee_registrationNbr) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, java.sql.Date.valueOf(operation.getCreationDate()));
            stmt.setDouble(2, operation.getAmount());
            stmt.setString(3, operation.getType().name());
            stmt.setInt(4, operation.getAccount().getNumber());
            stmt.setInt(5, operation.getEmployee().getRegistrationNbr());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    operation.setNumber(generatedId);
                }
                return Optional.of(operation);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int delete(Operation operation) {
        try{
            if(operation == null)
                throw new Exception("*****   Impossible de supprimer une operation vide   *****");
            String query = "DELETE FROM operation WHERE number = ?";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, operation.getNumber());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                return affectedRows;
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Operation> findByNumber(Operation operation) {
        try{
            if(operation == null)
                throw new Exception("*****   Impossible de supprimer une operation vide   *****");
            String query = "select * FROM operation WHERE number = ?";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, operation.getNumber());
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                System.out.println("inside next");
                operation.setNumber(result.getInt("number"));
                operation.setCreationDate(result.getDate("creationDate").toLocalDate());
                operation.setAmount(result.getDouble("amount"));
                operation.setType(OperationType.valueOf(result.getString("type")));
                operation.setAccount(new AccountDAOImpl().findAccountByNbr(result.getInt("account_number")).get());
                operation.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get());
                return Optional.of(operation);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

}
