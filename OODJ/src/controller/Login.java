package controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import users.*;
import view.AdminMenu;
import view.PurchaseManagerMenu;
import view.SalesManagerMenu;

public class Login {
    public static void loginInterface() {
        Scanner scanner = new Scanner(System.in);
        List<User> users = loadUsers();

        System.out.println("Welcome to the Login Interface!");

        boolean loggingIn = true;
        User matchedUser = null;
        while (loggingIn) {
            System.out.print("User ID: ");
            String inputUserID = scanner.nextLine();

            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();

            
            for (User user : users) {
                if (user.getUserID().equals(inputUserID) && user.getPassword().equals(inputPassword)) {
                    matchedUser = user;
                    break;
                }
            }
            
            if (matchedUser != null) {
                loggingIn = false;
                if (matchedUser instanceof Admin) {
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.menu();
                } else if (matchedUser instanceof PurchaseManager) {
                    PurchaseManagerMenu purchaseManagerMenu = new PurchaseManagerMenu();
                    purchaseManagerMenu.menu();
                } else if (matchedUser instanceof SalesManager) {
                    SalesManagerMenu salesManagerMenu = new SalesManagerMenu();
                    salesManagerMenu.menu();
                }

            } else {
                System.out.println("Login failed. Invalid username or password.");
                System.out.print("Do you want to retry? (yes/no): ");
                String retryChoice = scanner.nextLine();
                if (!retryChoice.equalsIgnoreCase("yes")) {
                    loggingIn = false; // Exit the loop if the user chooses not to retry
                }
            }
        }

        scanner.close();
    }

    private static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Admin.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String userID = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    users.add(UserFactory.createUser(userID, password, role));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(PurchaseManager.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String userID = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    users.add(UserFactory.createUser(userID, password, role));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(SalesManager.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String userID = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    users.add(UserFactory.createUser(userID, password, role));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}


