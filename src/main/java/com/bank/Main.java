package com.bank;

import com.bank.Service.AccountService;
import com.bank.Service.ClientService;
import com.bank.Service.EmployeeService;
import com.bank.Service.OperationService;
import com.bank.vue.AssertionVUE;
import com.bank.vue.MissionVUE;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService(); // Ajout de la nouvelle classe AccountService
        OperationService operationService = new OperationService();
        MissionVUE missionVue = new MissionVUE();
        AssertionVUE assertionVue = new AssertionVUE();
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            // Lisez le choix de l'utilisateur
            choice = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après le nombre

            switch (choice) {
                case 1:
                    // Menu pour gérer les employés
                    handleEmployeeMenu(employeeService);
                    break;
                case 2:
                    // Menu pour gérer les clients
                    handleClientMenu(clientService);
                    break;
                case 3:
                    // Menu pour gérer les comptes
                    handleAccountMenu(accountService);
                    break;
                case 4:
                    // Menu pour gérer les comptes
                    handleOperationMenu(operationService);
                    break;
                case 5:
                    handleMissionMenu(missionVue);
                    break;
                case 6:
                    handleAssertionMenu(assertionVue);
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

    // Méthode pour gérer le menu des employés
    private static void handleEmployeeMenu(EmployeeService employeeService) {
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
                case 3:
                    employeeService.findEmployee();
                    break;
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

    // Méthode pour gérer le menu des clients
    private static void handleClientMenu(ClientService clientService) {
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
                    clientService.addClient();
                    break;
                case 2:
                    clientService.deleteClient();
                    break;
                case 3:
                    clientService.findClientByCode();
                    break;
                case 4:
                    clientService.updateClient();
                    break;
                case 5:
                    clientService.findClientByAttribute();
                    break;
                case 6:
                    clientService.getClientAccount();
                    break;
                case 7:
                    clientService.findAllClients();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }
    private static void handleAccountMenu(AccountService accountService) {
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
                    accountService.addSavingWithAccount();
                    break;
                case 2:
                    accountService.addCurrentWithAccount();
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
                    accountService.deleteSavingAccount();
                    break;
                case 4:
                    accountService.deleteCurrentAccount();
                    break;
                case 5:
                    accountService.updateStatusAccount();
                    break;
                case 6:
                    accountService.getAllAccount();
                    break;
                case 7:
                    accountService.updateSavingAccount();
                    break;
                case 8:
                    accountService.updateCurrentAccount();
                    break;
                case 9:
                    accountService.getAccountStatus();
                    break;
                case 10:
                    accountService.getAccountByDate();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    private static void handleOperationMenu(OperationService operationService) {
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
                    operationService.createOperation();
                    break;
                case 2:
                    operationService.deleteOperation();
                    break;
                case 3:
                    operationService.findOperation();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choice != 0);
    }

    private static void handleMissionMenu(MissionVUE missionVue) {
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

    private static void handleAssertionMenu(AssertionVUE assertionVue) {
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
}
