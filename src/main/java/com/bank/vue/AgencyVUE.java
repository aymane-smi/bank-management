package com.bank.vue;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.AgencyEmployee;
import com.bank.Service.AgencyService;
import com.bank.Service.EmployeeService;

import java.util.Scanner;

public class AgencyVUE {
    private AgencyService agencyService;
    private EmployeeService employeeService;
    private AgencyDAOImpl agencyDAO;
    private Scanner sc;
    public AgencyVUE(AgencyService agencyService, AgencyDAOImpl agencyDAO, EmployeeService employeeService, Scanner sc){
        this.agencyService = agencyService;
        this.agencyDAO = agencyDAO;
        this.sc = sc;
    }

    public void createAgency(){
        Agency agency = new Agency();
        System.out.print("name:");
        agency.setName(sc.nextLine());
        System.out.print("adresse:");
        agency.setAddress(sc.nextLine());
        System.out.print("telephone");
        agency.setPhone(sc.nextLine());
        agency.setPhone(sc.nextLine());
        agencyService.create(agency);
    }

    public void deleteAgency(){
        agencyService.delete(sc.nextLine());
    }

    public void updateAgency(){
        Agency agency;
        System.out.print("code:");
        String tmp = sc.nextLine();
        if(!tmp.isEmpty())
            agency = agencyDAO.findByCode(tmp).get();
        else{
            System.out.println("*****   AGENCE INROUVABLE   *****");
            return;
        }
        System.out.print("name:");
        tmp = sc.nextLine();
        if(!tmp.isEmpty())
            agency.setName(sc.nextLine());
        System.out.print("adresse:");
        tmp = sc.nextLine();
        if(!tmp.isEmpty())
            agency.setAddress(sc.nextLine());
        System.out.print("telephone");
        tmp = sc.nextLine();
        if(!tmp.isEmpty())
            agency.setPhone(sc.nextLine());
        agencyService.update(agency);
    }

    public void findAllAgencies(){
        System.out.println("*****   AGENCES INFO   *****");
        for(Agency agency: agencyService.find())
            System.out.println(String.format("***** CODE[%s] NOME[%s] ADRESSE[%s] TELEPHONE[%s]", agency.getCode(), agency.getName(), agency.getAddress(), agency.getPhone()));
    }

    public void findByAddress(){
        try{
            System.out.print("adresse:");
            Agency agency = agencyService.findByAddress(sc.nextLine());
            System.out.println(String.format("***** CODE[%s] NOME[%s] ADRESSE[%s] TELEPHONE[%s]", agency.getCode(), agency.getName(), agency.getAddress(), agency.getPhone()));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findByEmployee(){
        try{
            System.out.print("employee matricule:");
            Agency agency = employeeService.findEmployee(sc.nextInt()).getAgency();
            System.out.println(String.format("***** CODE[%s] NOME[%s] ADRESSE[%s] TELEPHONE[%s]", agency.getCode(), agency.getName(), agency.getAddress(), agency.getPhone()));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findAllHistory(){
        try{
            for(AgencyEmployee tmp:agencyService.findHistory()){
                System.out.println(String.format("\n\n***** CODE[%s] NOME[%s] ADRESSE[%s] TELEPHONE[%s]", tmp.getAgency().getCode(), tmp.getAgency().getName(), tmp.getAgency().getAddress(), tmp.getAgency().getPhone()));
                System.out.println(String.format("*****   MATRICULE[%d] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] DATE_RECRUTEMENT[%s]   *****", tmp.getEmployee().getRegistrationNbr(), tmp.getEmployee().getFirstName(), tmp.getEmployee().getLastName(), tmp.getEmployee().getBirthDay().toString(), tmp.getEmployee().getPhone(), tmp.getEmployee().getAddress(), tmp.getEmployee().getDateOfRecrutment().toString()));
                System.out.println(String.format("*****   DATE DE TRANSFER: %s\n", tmp.getTransfer_date()));
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
