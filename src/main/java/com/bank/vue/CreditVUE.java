package com.bank.vue;

import com.bank.Entity.Client;
import com.bank.Entity.Credit;
import com.bank.Entity.Employee;
import com.bank.Enum.CreditStatus;
import com.bank.Service.ClientService;
import com.bank.Service.CreditService;
import com.bank.Service.EmployeeService;

import java.util.Scanner;

public class CreditVUE {
    private CreditService creditService;
    private ClientService clientService;
    private EmployeeService employeeService;
    private Credit credit;
    private Scanner sc;
    public CreditVUE(CreditService creditService, Scanner sc, ClientService clientService, EmployeeService employeeService, Credit credit){
        this.creditService = creditService;
        this.sc = sc;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.credit = credit;
    }

    public void makeSimulation(){
        try{
            System.out.print("montant de credit:");
            int montant = sc.nextInt();
            System.out.print("nombre de mensualite:");
            int mensualite = sc.nextInt();
            double result = creditService.makeSimulation(montant, mensualite);
            System.out.print(String.format("*****   LA RESULTAT EST: %f", result));
            System.out.print("Valider la simulation pour le credit? (O/n):");
            String choice = sc.nextLine();
            if(choice.equals("O")){
                System.out.print("code client:");
                Client clt = clientService.findClientByCode(sc.nextLine());
                System.out.print("matricule employee:");
                Employee emp = employeeService.findEmployee(sc.nextInt());
                sc.nextLine();
                credit.setAgency(emp.getAgency());
                credit.setEmployee(emp);
                credit.setClient(clt);
                credit.setDuration(mensualite);
                credit.setStatus(CreditStatus.PENDING);
                credit.setValue(montant);
                if(creditService.addCredit(credit) != null){
                    System.out.print("*****   CREDIT AJOUTER AVEC SUCCESS POUR LE CLIENT   *****");
                }
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void deleteCredit(){
        System.out.print("id du credit:");
        int id = sc.nextInt();
        try {
            if (creditService.deletecredit(id) == 1)
                System.out.println("*****   CREDIT SUPPRIMER   *****");
        }catch (Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void updateStatus(){
        System.out.print("id du credit:");
        int id = sc.nextInt();
        System.out.print("operation(PENDING|ACCEPTED|REFUSED):");
        CreditStatus status = CreditStatus.valueOf(sc.nextLine());
        try{
            if(creditService.updateStatus(id, status) != null)
                System.out.println("*****   STATUS CHANGER AVEC SUCCESS   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
    public void findById(){
        System.out.print("id du credit:");
        int id = sc.nextInt();
        try{
            credit = creditService.findById(id);
            if(credit != null)
                System.out.println(String.format("***** ID[%d] MONTANT[%d] STATUS[%s] REMARK[%s] AGENCE_CODE[%s] EMPLOYEE_MATRICULE[%d] CLIENT_CODE[%s]", credit.getId(), credit.getValue(), credit.getStatus().name(), credit.getRemark(), credit.getAgency().getCode(), credit.getEmployee().getRegistrationNbr(), credit.getClient().getCode()));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

}
