package com.bank.DAO;

import com.bank.Entity.Agency;
import com.bank.Entity.AgencyEmployee;
import com.bank.Entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface AgencyEmployeeDAO {
    public boolean create(int registrationNbr, String agency_code, LocalDate date);
    public List<AgencyEmployee> find();
}
