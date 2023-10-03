package com.bank.DAO;

import com.bank.Connection.JDBCConnection;
import com.bank.Entity.Agency;
import com.bank.Entity.Employee;
import com.bank.Exception.DeleteException;
import com.bank.Exception.InsertionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO{
    private Connection connection;
    public EmployeeDAOImpl(){
        connection = JDBCConnection.getConnection();
    }
    @Override
    public Optional<Employee> create(Employee employee) {
        try{
            if(employee == null)
                throw new Exception("*****   Impossible d'ajouter un employee vide   *****");
            String query = "INSERT INTO employee(firstName, lastName, birthDay, phone, address, dateOfRecrutment, agency_code) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, java.sql.Date.valueOf(employee.getBirthDay()));
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getAddress());
            stmt.setDate(6, java.sql.Date.valueOf(employee.getDateOfRecrutment()));
            stmt.setString(7, employee.getAgency().getCode());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else{
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    employee.setRegistrationNbr(generatedId);
                }
                return Optional.of(employee);
            }

        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> update(Employee employee) {
        try{
            if(employee == null)
                throw new Exception("*****   Impossible de modifier un employee vide   *****");
            String query = "UPDATE employee SET firstName = ?, lastName = ?, birthDay = ?, phone = ?, address = ?, dateOfRecrutment = ? WHERE registrationnbr = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, java.sql.Date.valueOf(employee.getBirthDay()));
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getAddress());
            stmt.setDate(6, java.sql.Date.valueOf(employee.getDateOfRecrutment()));
            stmt.setInt(7, employee.getRegistrationNbr());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0)
                throw new InsertionException();
            else
                return Optional.of(employee);

        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int delete(int registrationNbr) {
        try{
            if(registrationNbr <= 0)
                throw new Exception("*****   nombre d'ematricule non valide   *****");
            String query = "DELETE FROM employee WHERE registrationnbr = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, registrationNbr);
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
    public Optional<Employee> findByRegistrationNbr(int registrationNbr) {
        try{
            Employee emp = new Employee();
            String query = "SELECT * FROM employee WHERE registrationnbr = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, registrationNbr);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                emp.setRegistrationNbr(result.getInt("registrationnbr"));
                emp.setPhone(result.getString("phone"));
                emp.setAddress(result.getString("address"));
                emp.setBirthDay(result.getDate("birthDay").toLocalDate());
                emp.setFirstName(result.getString("firstName"));
                emp.setLastName(result.getString("lastName"));
                emp.setDateOfRecrutment(result.getDate("dateOfRecrutment").toLocalDate());
            }
            return Optional.of(emp);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Employee>> findAll() {
        try{
            List<Employee> list = new ArrayList<>();
            Employee emp = new Employee();
            String query = "SELECT * FROM employee";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                emp.setRegistrationNbr(result.getInt("registrationnbr"));
                emp.setPhone(result.getString("phone"));
                emp.setAddress(result.getString("address"));
                emp.setBirthDay(result.getDate("birthDay").toLocalDate());
                emp.setFirstName(result.getString("firstName"));
                emp.setLastName(result.getString("lastName"));
                emp.setDateOfRecrutment(result.getDate("dateOfRecrutment").toLocalDate());
                list.add(emp);
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Employee>> find(Employee employee) {
        try{
            List<Employee> list = new ArrayList<>();
            Employee emp = new Employee();
            String query = "SELECT * FROM employee WHERE firstName LIKE ? AND lastName LIKE ? AND phone LIKE ? AND address LIKE ? AND birthDay = ? AND dateOfRecrutment = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%"+employee.getFirstName()+"%");
            stmt.setString(2, "%"+employee.getLastName()+"%");
            stmt.setString(3, "%"+employee.getPhone()+"%");
            stmt.setString(4, "%"+employee.getAddress()+"%");
            stmt.setDate(5, java.sql.Date.valueOf(employee.getBirthDay()));
            stmt.setDate(6, java.sql.Date.valueOf(employee.getDateOfRecrutment()));
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                emp.setRegistrationNbr(result.getInt("registrationnbr"));
                emp.setPhone(result.getString("phone"));
                emp.setAddress(result.getString("address"));
                emp.setBirthDay(result.getDate("birthDay").toLocalDate());
                emp.setFirstName(result.getString("firstName"));
                emp.setLastName(result.getString("lastName"));
                emp.setDateOfRecrutment(result.getDate("dateOfRecrutment").toLocalDate());
                list.add(emp);
            }
            return Optional.of(list);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> changeAgency(Employee emp, String agencyCode) {
        try{
            if(emp.getAgency() == null)
                throw new Exception("*****   LE CODE AGENCE DE L'AGENCE NE DOIT PAS ETRE VIDE    *****");
            String query = "UPDATE employee SET agency_code = ? WHERE registrationNbr";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, emp.getAgency().getCode());
            stmt.setInt(2, emp.getRegistrationNbr());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 1){
                emp.setAgency(new AgencyDAOImpl().findByCode(emp.getAgency().getCode()).get());
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return Optional.empty();
    }
}
