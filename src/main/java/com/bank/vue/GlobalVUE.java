package com.bank.vue;

import com.bank.Service.EmployeeService;

import java.util.Scanner;

public class GlobalVUE {
    // Méthode pour gérer le menu des employés
    public static void handleEmployeeMenu(EmployeeService employeeService) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des employés
            System.out.println("Menu Employés:");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Supprimer un employé");
            System.out.println("3. Rechercher un employé");
            System.out.println("4. Mettre à jour un employé");
            System.out.println("5. Rechercher des employés par attributs");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            //scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    employeeService.addEmployee();
                    break;
                case 2:
                    employeeService.deleteEmployee();
                    break;
//                case 3:
//                    employeeService.findEmployee();
//                    break;
                case 4:
                    employeeService.updateEmployee();
                    break;
                case 5:
                    employeeService.findEmployeeByAttribute();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    /// Méthode pour gérer le menu des clients
    public static void handleClientMenu(ClientVUE clientVue) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des clients
            System.out.println("Menu Clients:");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Rechercher un client par code");
            System.out.println("4. Mettre à jour un client");
            System.out.println("5. Rechercher des clients par attributs");
            System.out.println("6. Obtenir les comptes d'un client");
            System.out.println("7. List des client");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            //scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    clientVue.addClient();
                    break;
                case 2:
                    clientVue.deleteClient();
                    break;
                case 3:
                    clientVue.findClientByCode();
                    break;
                case 4:
                    clientVue.updateClient();
                    break;
                case 5:
                    clientVue.findClientByAttribute();
                    break;
                case 6:
                    clientVue.getClientAccount();
                    break;
                case 7:
                    clientVue.findAllClients();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handleAccountMenu(AccountVUE accountVue) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des comptes
            System.out.println("Menu Comptes:");
//            System.out.println("1. Ajouter un compte d'épargne avec compte");
//            System.out.println("2. Ajouter un compte courant avec compte");
            System.out.println("1. Ajouter un compte d'épargne");
            System.out.println("2. Ajouter un compte courrant");
//            System.out.println("5. Supprimer un compte");
            System.out.println("3. Supprimer un compte d'épargne");
            System.out.println("4. Supprimer un compte courant");
            System.out.println("5. Mettre à jour le statuts d'un compte");
            System.out.println("6. Obtenir la liste des comptes");
            System.out.println("7. Mettre à jour un compte d'épargne");
            System.out.println("8. Mettre à jour un compte courant");
            System.out.println("9. Obtenir des comptes par statut");
            System.out.println("10. Obtenir des comptes par date de création");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    accountVue.addSaving();
                    break;
                case 2:
                    accountVue.addCurrent();
                    break;
//                case 3:
//                    accountService.addSavingWithoutAccount();
//                    break;
//                case 4:
//                    accountService.addCurrentWithoutAccount();
//                    break;
//                case 3:
//                    accountService.deleteAccount();
//                    break;
                case 3:
                    accountVue.deleteSaving();
                    break;
                case 4:
                    accountVue.deleteCurrent();
                    break;
                case 5:
                    accountVue.updateAccountStatus();
                    break;
                case 6:
                    accountVue.getAllAccount();
                    break;
                case 7:
                    accountVue.updateSavingAccount();
                    break;
                case 8:
                    accountVue.updateCurrentAccount();
                    break;
                case 9:
                    accountVue.getAccountStatus();
                    break;
                case 10:
                    accountVue.getAccountByDate();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handleOperationMenu(OperationVUE operationVue) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des clients
            System.out.println("Menu Operation:");
            System.out.println("1. Ajouter une operation");
            System.out.println("2. Supprimer une operation");
            System.out.println("3. Rechercher une operation par nombre");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            //scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    operationVue.createOperation();
                    break;
                case 2:
                    operationVue.deleteOperation();
                    break;
                case 3:
                    operationVue.findOperation();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handleMissionMenu(MissionVUE missionVue) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des clients
            System.out.println("Menu Operation:");
            System.out.println("1. Ajouter une mission");
            System.out.println("2. Supprimer une mission");
            System.out.println("3. Afficher tous les missions");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            // Lire la nouvelle ligne après le nombre
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    missionVue.addMission();
                    break;
                case 2:
                    missionVue.deleteMission();
                    break;
                case 3:
                    missionVue.findAllMission();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handleAssertionMenu(AssertionVUE assertionVue) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des clients
            System.out.println("Menu Operation:");
            System.out.println("1. Ajouter une affectation");
            System.out.println("2. Supprimer une affectation");
            System.out.println("3. Afficher tous les missions");
            System.out.println("4. Statistiques");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            // Lire la nouvelle ligne après le nombre
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    assertionVue.addAssertion();
                    break;
                case 2:
                    assertionVue.deleteAssertion();
                    break;
                case 3:
                    assertionVue.findAssertionByEmployee();
                    break;
                case 4:
                    assertionVue.findStats();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handleAgencyMenu(AgencyVUE agencyVUE){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des comptes
            System.out.println("Menu Agence:");
            System.out.println("1. Ajouter une agence");
            System.out.println("2. Supprimer une agenec");
            System.out.println("3. Modifer une agence");
            System.out.println("4. Obtenir tous les agences");
            System.out.println("5. Chercher les agences par adresse");
            System.out.println("6. Chercher une agences par employee");
            System.out.println("6. Obtenir l'historiques des agences");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    agencyVUE.createAgency();
                    break;
                case 2:
                    agencyVUE.deleteAgency();
                    break;
                case 3:
                    agencyVUE.updateAgency();
                    break;
                case 4:
                    agencyVUE.findAllAgencies();
                    break;
                case 5:
                    agencyVUE.findByAddress();
                    break;
                case 6:
                    agencyVUE.findByEmployee();
                    break;
                case 7:
                    agencyVUE.findAllHistory();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handleCreditMenu(CreditVUE creditVue){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des comptes
            System.out.println("Menu Agence:");
            System.out.println("1. faire une simulation/ajout du crédit");
            System.out.println("2. Supprimer un credit");
            System.out.println("3. Modifer status du crédit");
            System.out.println("4. Chercher les agences par id");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    creditVue.makeSimulation();
                    break;
                case 2:
                    creditVue.deleteCredit();
                    break;
                case 3:
                    creditVue.updateStatus();
                    break;
                case 4:
                    creditVue.findById();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    public static void handlePaymentMenu(PaymentVUE paymentVue){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Affichez le menu des comptes
            System.out.println("Menu Agence:");
            System.out.println("1. faire un virment");
            System.out.println("2. Supprimer un virment");
            System.out.println("3. Obtenir virments par date");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    paymentVue.createPayment();
                    break;
                case 2:
                    paymentVue.deletePayment();
                    break;
                case 3:
                    paymentVue.findPaymentByDate();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }
}
