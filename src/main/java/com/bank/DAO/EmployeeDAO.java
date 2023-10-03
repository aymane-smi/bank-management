package com.bank.DAO;

import com.bank.Entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    public Optional<Employee> create(Employee employee, LocalDate date);
    public Optional<Employee> update(Employee employee);
    public int delete(int registrationNbr);
    public Optional<Employee> findByRegistrationNbr(int registrationNbr);
    public Optional<List<Employee>> findAll();
    public Optional<List<Employee>> find(Employee employee);
    public Optional<Employee> changeAgency(Employee emp, String agencyCode);
}
