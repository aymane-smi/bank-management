package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Credit;
import com.bank.Exception.InsertionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class CreditDAOImpl implements CreditDAO{
    private Connection connection;
    public CreditDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Credit> create(Credit credit) {
        try{
            if(credit == null)
                throw new Exception("*****   LE CREDIT NE PEUT PAS ETRE VIDE   *****");
            String query = "INSERT INTO Credit(client_code, agency_code, employee_registrationNbr, credit_value, status, duration, remark) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, credit.getClient().getCode());
            stmt.setString(2, credit.getAgency().getCode());
            stmt.setInt(3, credit.getEmployee().getRegistrationNbr());
            stmt.setInt(4, credit.getValue());
            stmt.setString(5, credit.getStatus().name());
            stmt.setInt(6, credit.getDuration());
            stmt.setString(7, credit.getRemark());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    credit.setId(generatedId);
                }
                return Optional.of(credit);
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
