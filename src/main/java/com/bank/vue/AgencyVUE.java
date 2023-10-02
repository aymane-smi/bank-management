package com.bank.vue;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Service.AgencyService;

import java.util.Scanner;

public class AgencyVUE {
    private AgencyService agencyService;
    private AgencyDAOImpl agencyDAO;
    public AgencyVUE(AgencyService agencyService, AgencyDAOImpl agencyDAO){
        this.agencyService = agencyService;
        this.agencyDAO = agencyDAO;
    }

    public void createAgency(){
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        agencyService.delete(sc.nextLine());
    }

    public void updateAgency(){
        Scanner sc = new Scanner(System.in);
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
}
