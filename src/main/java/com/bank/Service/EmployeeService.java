package com.bank.Service;

import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeService {
    private EmployeeDAOImpl EmployeeDao;
    public EmployeeService(){
        EmployeeDao = new EmployeeDAOImpl();
    }

    public void addEmployee(){
        try{
            Scanner sc = new Scanner(System.in);
            Employee emp = new Employee();
            System.out.print("nom:");
            emp.setFirstName(sc.nextLine());
            System.out.print("prenom:");
            emp.setLastName(sc.nextLine());
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
            if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                emp.setBirthDay(inputDate);
            else
                throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR L'EMPLOYEE   *****");
            System.out.print("telephone:");
            emp.setPhone(sc.nextLine());
            System.out.print("adresse:");
            emp.setAddress(sc.nextLine());
            System.out.print("date de recrutement(aaaa-mm-jj):");
            tmp_date = sc.nextLine();
            emp.setDateOfRecrutment(LocalDate.parse(tmp_date, formatter));
            Optional<Employee> optionalEmp = EmployeeDao.create(emp);
            optionalEmp.ifPresent(val->System.out.println(String.format("*****   AJOUT D'UNEMPLOI AVEC ID[%d]   *****", val.getRegistrationNbr())));
            sc.close();
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void deleteEmployee(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("id:");
            int result = EmployeeDao.delete(sc.nextInt());
            System.out.println(String.format("*****   NOMBRE DES ELEMENTS SUPPRIMEES EST:%d   *****", result));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findEmployee(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("id:");
            Optional<Employee> emp = EmployeeDao.findByRegistrationNbr(sc.nextInt());
            if(!emp.isPresent())
                System.out.println(String.format("*****   EMPLOYEE INEXISTANT   *****"));
            else
                emp.ifPresent(e->{
                    System.out.println(String.format("*****   MATRICULE[%d] NOM[%S] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%S] DATE_RECRUTEMENT[%S]   *****", e.getRegistrationNbr(), e.getFirstName(), e.getLastName(), e.getBirthDay().toString(), e.getPhone(), e.getAddress(), e.getDateOfRecrutment().toString()));
                });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

}
