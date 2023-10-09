package com.bank.Service;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeService {
    private EmployeeDAOImpl EmployeeDao;
    public EmployeeService(EmployeeDAOImpl EmployeeDao){
        this.EmployeeDao = EmployeeDao;
    }

    public Employee addEmployee(Employee emp, LocalDate date) throws Exception{
            if(emp == null || date == null)
                throw new Exception("*****   EMPLOYEE|DATE EST INVALIDE   *****");
            Optional<Employee> optionalEmp = EmployeeDao.create(emp, date);
            return optionalEmp.get();
    }

    public int deleteEmployee(int registrationNbr) throws Exception{
        if(registrationNbr <= 0)
            throw new Exception("*****   MATRICULE INVALIDE   *****");
        int result = EmployeeDao.delete(registrationNbr);
        return result;
    }

    public Employee findEmployee(int registrationNbr) throws Exception{
        if(registrationNbr == 0)
            throw new Exception("***** EMPLOYEE MATRICULE NE PEUT PAS ETRE 0");
        Optional<Employee> emp = EmployeeDao.findByRegistrationNbr(registrationNbr);
        return emp.get();
    }

    public Employee updateEmployee(Employee emp) throws Exception{
        if (emp == null)
            throw new Exception("*****   EMPLOYEE EST INVALIDE   *****");
        return EmployeeDao.update(emp).get();
    }

    public List<Employee> findEmployeeByAttribute(Employee emp) throws Exception{
        if (emp == null)
            throw new Exception("*****   EMPLOYEE INVALIDE   *****");
        return EmployeeDao.find(emp).get();
    }

    public Employee changeAgency(Employee emp, String agencyCode) throws Exception{
        if(emp == null || emp.getAgency() == null)
            throw new Exception("*****   L'AGENCE/EMPLOYEE NE PEUT PAS ETRE NULL OU CODE D'AGENCE EST VIDE   *****");
        return EmployeeDao.changeAgency(emp, agencyCode).get();
    }

}
