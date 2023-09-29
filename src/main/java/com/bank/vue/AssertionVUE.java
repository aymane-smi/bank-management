package com.bank.vue;

import com.bank.DAO.EmployeeDAOImpl;
import com.bank.DAO.MissionDAOImpl;
import com.bank.Entity.Mission;
import com.bank.Entity.MissionEmployee;
import com.bank.Service.AssertionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AssertionVUE {
    private AssertionService assertionService;

    public AssertionVUE(){
        assertionService = new AssertionService();
    }

    public void addAssertion(){
        MissionEmployee tmp = new MissionEmployee();
        Scanner sc = new Scanner(System.in);
        System.out.print("mission code:");
        tmp.setMission(new MissionDAOImpl().findByCode(sc.nextInt()).get());
        sc.nextLine();
        System.out.print("employee code:");
        tmp.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(sc.nextInt()).get());
        sc.nextLine();
        System.out.print("date de debut:");
        String tmp_date = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
        tmp.setStartDate(inputDate);
        System.out.print("date de fin:");
        tmp_date = sc.nextLine();
        inputDate = LocalDate.parse(tmp_date, formatter);
        tmp.setEndDate(inputDate);
        assertionService.createAssertion(tmp);
    }

    public void deleteAssertion(){
        Scanner sc = new Scanner(System.in);
        System.out.print("code:");
        assertionService.deleteAssertion(sc.nextInt());
    }

    public void findAssertionByEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.print("matricule employee:");
        assertionService.findAssertionByEmployee(sc.nextInt());
    }

    public void findStats(){
        assertionService.showStats();
    }
}
