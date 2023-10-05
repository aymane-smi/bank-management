package com.bank.vue;

import com.bank.Service.CreditService;

import java.util.Scanner;

public class CreditVUE {
    private CreditService creditService;
    private Scanner sc;
    public CreditVUE(CreditService creditService, Scanner sc){
        this.creditService = creditService;
        this.sc = sc;
    }

    public void makeSimulation(){
        try{
            System.out.print("montant de credit:");
            double montant = sc.nextDouble();
            System.out.print("nombre de mensualite:");
            int mensualite = sc.nextInt();
            double result = creditService.makeSimulation(montant, mensualite);
            System.out.print(String.format("*****   LA RESULTAT EST: %f", result));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

}
