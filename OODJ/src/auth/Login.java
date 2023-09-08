package auth;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import users.*;

public class Login {
    private static final String userFile = "users.txt";
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
                matchedUser.menu(); // Call the menu from the role user is from
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
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
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


class UserFactory {
    public static User createUser(String userID, String password, String role){
        if ("Admin".equals(role)){
            return new Admin(userID, password);
        }   else if ("Purchase Manager".equals(role)) {
            return new PurchaseManager(userID, password);
        }   else if ("Sales Manager".equals(role)) {
            return new SalesManager(userID, password);
        }
        return null;
    }
}