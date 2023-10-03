package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Payment;
import com.bank.Exception.InsertionException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO{
    private Connection connection;
    public PaymentDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Payment> create(Payment payment) {
        try{
            if(payment == null || payment.getEmployee() == null || payment.getTo() == null || payment.getFrom() == null)
                throw new Exception("*****   VIRMENT OU L'UN DE CES COMPOSANTS EST INVALIDE   *****");
            String query = "INSERT INTO PAYMENT(transaction_time, balance, from_account, to_account, employee_registrationNbr) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(payment.getTransaction_time()));
            stmt.setDouble(2, payment.getBalance());
            stmt.setInt(3, payment.getFrom().getNumber());
            stmt.setInt(4, payment.getTo().getNumber());
            stmt.setInt(5, payment.getEmployee().getRegistrationNbr());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    payment.setId(generatedId);
                }
                return Optional.of(payment);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int delete(int id) {
        try{
            if(id == 0)
                throw new Exception("*****   L'ID DU VIRMENT NE DOIT PAS ETRE 0   *****");
            String query = "DELETE FROM payment WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 1 )
                return 1;
            else
                return 0;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Payment> findByDate(LocalDate date) {
        List<Payment> list = new ArrayList<>();
        try{
            if(date == null)
                throw new Exception("*****   LA DATE DOIT ETRE NON NULLE   *****");
            String query = "SELECT * FROM payment WHERE DATE(transaction_time) = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                Payment payment = new Payment(
                        results.getInt("id"),
                        results.getDouble("balance"),
                        new AccountDAOImpl().findAccountByNbr(results.getInt("from_account")).get(),
                        new AccountDAOImpl().findAccountByNbr(results.getInt("to_account")).get(),
                        new EmployeeDAOImpl().findByRegistrationNbr(results.getInt("employee_registrationnbr")).get(),
                        results.getTimestamp("transaction_time").toLocalDateTime()
                );
                list.add(payment);
            }
            return list;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return list;
    }
}
