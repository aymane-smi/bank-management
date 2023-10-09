package com.bank.vue;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Employee;
import com.bank.Service.EmployeeService;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeVUE {
    private EmployeeService employeeService;
    private Scanner sc;
    private AgencyDAOImpl agencyDao;
    private EmployeeDAOImpl employeeDAO;
    public EmployeeVUE(EmployeeService employeeService, Scanner sc, AgencyDAOImpl agencyDao, EmployeeDAOImpl employeeDAO){
        this.sc = sc;
        this.employeeService = employeeService;
        this.agencyDao = agencyDao;
        this.employeeDAO = employeeDAO;
    }

    public void addEmployee(){
        try{
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
            System.out.print("agence code");
            emp.setAgency(agencyDao.findByCode(sc.nextLine()).get());
            LocalDate time = LocalDate.parse(tmp_date, formatter);
            if(employeeService.addEmployee(emp, time) != null)
                System.out.println("*****   EMPLOYEE AJOUTER AVEC SUCCESS   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void deleteEmployee(){
        try{
            System.out.print("id:");
            int result = employeeService.deleteEmployee(sc.nextInt());
            if (result == 1)
                System.out.println("*****   EMPLOYEE SUPPRIMER AVEC SUCCESS   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findEmployee(){
        try{
            System.out.println("employee matricule:");
            Employee e = employeeService.findEmployee(sc.nextInt());
            if(e != null)
                System.out.println(String.format("*****   MATRICULE[%d] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] DATE_RECRUTEMENT[%s]   *****", e.getRegistrationNbr(), e.getFirstName(), e.getLastName(), e.getBirthDay().toString(), e.getPhone(), e.getAddress(), e.getDateOfRecrutment().toString()));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void updateEmployee(){
        try{
            System.out.print("matricule:");
            int matricule = Integer.parseInt(sc.nextLine());
            Employee emp = employeeService.findEmployee(matricule);
            //sc.nextLine();
            System.out.print("nom:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setFirstName(tmp_str);
            System.out.print("prenom:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setLastName(tmp_str);
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                    emp.setBirthDay(inputDate);
                else
                    throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR L'EMPLOYEE   *****");
            }
            System.out.print("telephone:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setPhone(tmp_str);
            System.out.print("adresse:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setAddress(tmp_str);
            System.out.print("date de recrutement(aaaa-mm-jj):");
            tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                emp.setDateOfRecrutment(LocalDate.parse(tmp_date, formatter));
            }
            emp = employeeService.updateEmployee(emp);
            if(emp != null)
                System.out.println("*****   EMPLOYEE MODIFIER AVEC SUCCESS   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findEmployeeByAttribute(){
        try{
            Employee emp = new Employee();
            //sc.nextLine();
            System.out.print("nom:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setFirstName(tmp_str);
            System.out.print("prenom:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setLastName(tmp_str);
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                    emp.setBirthDay(inputDate);
                else
                    throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR L'EMPLOYEE   *****");
            }
            System.out.print("telephone:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setPhone(tmp_str);
            System.out.print("adresse:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                emp.setAddress(tmp_str);
            System.out.print("date de recrutement(aaaa-mm-jj):");
            tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                emp.setDateOfRecrutment(LocalDate.parse(tmp_date, formatter));
            }
            List<Employee> result = employeeService.findEmployeeByAttribute(emp);
                for(Employee e:result)
                    System.out.println(String.format("*****   MATRICULE[%d] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] DATE_RECRUTEMENT[%s]   *****", e.getRegistrationNbr(), e.getFirstName(), e.getLastName(), e.getBirthDay().toString(), e.getPhone(), e.getAddress(), e.getDateOfRecrutment().toString()));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void changeEmployeeAgence(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("employee matricule");
            Employee emp = employeeService.findEmployee(sc.nextInt());
            String tmp;
            System.out.print("agence id");
            tmp = sc.nextLine();
            if(!tmp.isEmpty())
                employeeService.changeAgency(emp, tmp);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
