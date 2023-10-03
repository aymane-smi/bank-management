package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Payment;
import com.bank.Exception.InsertionException;

import java.sql.*;
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
}
