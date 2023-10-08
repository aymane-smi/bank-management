package com.bank;

import com.bank.DAO.*;
import com.bank.Entity.Credit;
import com.bank.Entity.Payment;
import com.bank.Service.*;
import com.bank.vue.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgencyDAOImpl agencyDAO = new AgencyDAOImpl();
        AgencyEmployeeDAOImpl agencyEmployee = new AgencyEmployeeDAOImpl();
        AgencyService agencyService = new AgencyService(agencyDAO, agencyEmployee);
        AccountService accountService = new AccountService();
        AccountDAOImpl accountDao = new AccountDAOImpl();
        EmployeeService employeeService = new EmployeeService();
        EmployeeDAOImpl employeeDao = new EmployeeDAOImpl();
        ClientService clientService = new ClientService();
        ClientVUE clientVue = new ClientVUE();
        AccountVUE accountVue = new AccountVUE(
                agencyDAO,
                accountService
        );
        OperationVUE operationVue = new OperationVUE();
        MissionVUE missionVue = new MissionVUE();
        AssertionVUE assertionVue = new AssertionVUE();
        AgencyVUE agencyVue = new AgencyVUE(
                agencyService,
                agencyDAO,
                employeeService,
                scanner
        );
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        PaymentService paymentService = new PaymentService(paymentDAO);
        Payment payment = new Payment();
        PaymentVUE paymentVue = new PaymentVUE(
                paymentService,
                scanner,
                accountDao,
                employeeDao,
                payment
        );
        CreditDAOImpl creditDao = new CreditDAOImpl();
        CreditService creditService = new CreditService(
                creditDao
        );
        Credit credit = new Credit();
        CreditVUE creditVue = new CreditVUE(
                creditService,
                scanner,
                clientService,
                employeeService,
                credit
        );

        int choice;

        do {
            // Affichez le menu
            System.out.println("Menu:");
            System.out.println("1. Gérer les employés");
            System.out.println("2. Gérer les clients");
            System.out.println("3. Gérer les comptes");
            System.out.println("4. Gérer les opérations");
            System.out.println("5. Gérer les missions");
            System.out.println("6. Gérer les affectations");
            System.out.println("7. Gérer les agences");
            System.out.println("8. Gérer les crédits");
            System.out.println("9. Gérer les virments");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    // Menu pour gérer les employés
                    GlobalVUE.handleEmployeeMenu(employeeService);
                    break;
                case 2:
                    // Menu pour gérer les clients
                    GlobalVUE.handleClientMenu(clientVue);
                    break;
                case 3:
                    // Menu pour gérer les comptes
                    GlobalVUE.handleAccountMenu(accountVue);
                    break;
                case 4:
                    // Menu pour gérer les comptes
                    GlobalVUE.handleOperationMenu(operationVue);
                    break;
                case 5:
                    GlobalVUE.handleMissionMenu(missionVue);
                    break;
                case 6:
                    GlobalVUE.handleAssertionMenu(assertionVue);
                    break;
                case 7:
                    GlobalVUE.handleAgencyMenu(agencyVue);
                    break;
                case 8:
                    GlobalVUE.handleCreditMenu(creditVue);
                    break;
                case 9:
                    GlobalVUE.handlePaymentMenu(paymentVue);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);

        scanner.close();
    }
}