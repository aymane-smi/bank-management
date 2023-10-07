package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;
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

    @Override
    public int delete(int id) {
        try{
            if(id == 0)
                throw new Exception("*****   ID DU CREDIT NE PEUT PAS ETRE 0   *****");
            String query = "DELETE FROM credit WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                return 0;
            else
                return affectedRows;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Credit> updateStatus(int id, CreditStatus status) {
        try{
            if(id <=0 || status == null)
                throw new Exception("*****   STATUS|ID EST INVALIDE    *****");
            String query = "UPDATE credit SET status = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, status.name());
            stmt.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Credit> findById(int id){
        try{
            if(id <=0)
                throw new Exception("*****   ID EST INVALIDE    *****");
            String query = "SELECT * FROM credit WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                Credit credit = new Credit();
                credit.setId(result.getInt("id"));
                credit.setValue(result.getInt("credit_value"));
                credit.setDuration(result.getInt("duration"));
                credit.setRemark(result.getString("remark"));
                credit.setAgency(new AgencyDAOImpl().findByCode(result.getString("agency_code")).get());
                credit.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get());
                credit.setClient(new ClientDAOImpl().findByCode(result.getString("client_code")).get());
                credit.setStatus(CreditStatus.valueOf(result.getString("status")));
                return Optional.of(credit);
            }
            return Optional.empty();
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
