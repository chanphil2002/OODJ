package oodj.Auth;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import users.*;

public class Login {
    private static final String userFile = "users.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = loadAccounts();

        System.out.println("Welcome to the Login Interface!");

        boolean loggingIn = true;
        String userRole = null;
        while (loggingIn) {
            System.out.print("User ID: ");
            String inputUserID = scanner.nextLine();

            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();

            Account matchedUser = null;
            for (Account account : accounts) {
                if (account.getUserID().equals(inputUserID) && account.getPassword().equals(inputPassword)) {
                    matchedUser = account;
                    break;
                }
            }

            if (matchedUser != null) {
                loggingIn = false;
                if (userRole == "Admin") {
                    Admin admin = new Admin();
                    admin.adminMenu();
                } else if (userRole == "Purchase Manager") {

                } else if (userRole == "Sales Manager") {

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

    private static List<Account> loadAccounts() {
        List<Account> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String userID = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    users.add(new Account(userID, password, role));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}

class Account {
    private String userID;
    private String password;
    private String role;

    public Account(String userID, String password, String role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}