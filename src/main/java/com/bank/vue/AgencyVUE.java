package com.bank.vue;

import com.bank.Entity.Agency;
import com.bank.Service.AgencyService;

import java.util.Scanner;

public class AgencyVUE {
    private AgencyService agencyService;
    public AgencyVUE(AgencyService agencyService){
        this.agencyService = agencyService;
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
}
