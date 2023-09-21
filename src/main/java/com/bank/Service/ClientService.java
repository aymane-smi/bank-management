package com.bank.Service;

import com.bank.DAO.ClientDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Client;
import com.bank.Entity.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {
    private ClientDAOImpl ClientDao;
    public ClientService(){
        ClientDao = new ClientDAOImpl();
    }

    public void addClient(){
        try{
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
            if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                clt.setBirthDay(inputDate);
            else
                throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR UN CLIENT   *****");
            System.out.print("telephone:");
            clt.setPhone(sc.nextLine());
            System.out.print("adresse:");
            clt.setAddress(sc.nextLine());
            Optional<Client> optionalClt = ClientDao.create(clt);
            optionalClt.ifPresent(val->System.out.println(String.format("*****   AJOUT D'UN CLIENT AVEC CODE[%s]   *****", val.getCode())));
            sc.close();
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
