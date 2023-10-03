package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.AgencyEmployee;
import com.bank.Entity.MissionEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgencyEmployeeDAOImpl implements AgencyEmployeeDAO{
    private Connection connection;
    public AgencyEmployeeDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public boolean create(int registrationNbr, String agency_code, LocalDate date) {
        try{
            if(registrationNbr == 0 || agency_code.isEmpty() || date == null)
                throw new Exception("*****   MATRICULE,CODE OU LA DATE SONT INVALIDES");
            String query = "INSERT INTO employee_history(employee_registrationNbr, agency_code, transfer_date) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, registrationNbr);
            stmt.setString(2, agency_code);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            int affectRows = stmt.executeUpdate();
            if(affectRows == 1)
                return true;
            else
                return false;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return false;
    }

    @Override
    public List<AgencyEmployee> find() {
        List<AgencyEmployee> list = new ArrayList<>();
        try{
            String query = "SELECT * FROM employee_history";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                list.add(new AgencyEmployee(
                        result.getString("id"),
                        new EmployeeDAOImpl().findByRegistrationNbr(result.getInt("employee_registrationNbr")).get(),
                        new AgencyDAOImpl().findByCode("agency_code").get(),
                        result.getDate("transfer_date").toLocalDate()
                ));
            }
            return list;
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return list;
    }
}
