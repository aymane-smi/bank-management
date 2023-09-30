package com.bank.vue;
import com.bank.DAO.ClientDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Client;
import com.bank.Service.ClientService;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClientVUE {
    private ClientService clientService;
    public ClientVUE(){
        clientService = new ClientService();
    }

    public void addClient(){
        try {
            Scanner sc = new Scanner(System.in);
            Client clt = new Client();
            System.out.print("code:");
            clt.setCode(sc.nextLine());
            System.out.print("nom:");
            clt.setFirstName(sc.nextLine());
            System.out.print("prenom:");
            clt.setLastName(sc.nextLine());
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
            if (Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                clt.setBirthDay(inputDate);
            else
                throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR UN CLIENT   *****");
            System.out.print("telephone:");
            clt.setPhone(sc.nextLine());
            System.out.print("adresse:");
            clt.setAddress(sc.nextLine());
            System.out.print("employee code:");
            String str = sc.nextLine();
            clt.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(Integer.parseInt(str)).get());
            clientService.addClient(clt);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void deleteClient(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("code:");
            clientService.deleteClient(sc.nextLine());
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findAllClients(){
        clientService.findAllClients();
    }

    public void findClientByCode(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("code:");
            clientService.findClientByCode(sc.nextLine());
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void updateClient(){
        try{
            Scanner sc = new Scanner(System.in);
            Client clt;
            System.out.print("code client:");
            clt = new ClientDAOImpl().findByCode(sc.nextLine()).get();
            System.out.print("nom:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setFirstName(tmp_str);
            System.out.print("prenom:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setLastName(tmp_str);
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                    clt.setBirthDay(inputDate);
                else
                    throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR LE CLIENT   *****");
            }
            System.out.print("telephone:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setPhone(tmp_str);
            System.out.print("adresse:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setAddress(tmp_str);
            clientService.updateClient(clt);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findClientByAttribute(){
        try{
            Scanner sc = new Scanner(System.in);
            Client clt = new Client();
            System.out.print("nom:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setFirstName(tmp_str);
            System.out.print("prenom:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setLastName(tmp_str);
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                    clt.setBirthDay(inputDate);
                else
                    throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR LE CLIENT   *****");
            }
            System.out.print("telephone:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setPhone(tmp_str);
            System.out.print("adresse:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setAddress(tmp_str);
            clientService.findClientByAttribute(clt);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void getClientAccount(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("code:");
            Client clt = new ClientDAOImpl().findByCode(sc.nextLine()).get();
            clientService.getClientAccount(clt);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
