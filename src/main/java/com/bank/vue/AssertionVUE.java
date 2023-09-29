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
        System.out.println("mission code:");
        tmp.setMission(new MissionDAOImpl().findByCode(sc.nextInt()).get());
        sc.nextLine();
        System.out.println("employee code:");
        tmp.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(sc.nextInt()).get());
        sc.nextLine();
        System.out.println("date de création:");
        String tmp_date = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
        tmp.setStartDate(inputDate);
        System.out.println("date de création:");
        tmp_date = sc.nextLine();
        inputDate = LocalDate.parse(tmp_date, formatter);
        tmp.setEndDate(inputDate);
        assertionService.createAssertion(tmp);
        sc.close();
    }

    public void deleteAssertion(){
        Scanner sc = new Scanner(System.in);
        System.out.print("code:");
        assertionService.deleteAssertion(sc.nextInt());
        sc.close();
    }

    public void findAssertionByEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.print("matricule employee:");
        assertionService.findAssertionByEmployee(sc.nextInt());
        sc.close();
    }
}
