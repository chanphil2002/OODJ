package controller;


import controller.FileOperations;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import view.OptionPicker;
import users.*;



/**
 *
 * @author pc
 */
public class CreateAccount {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void CreateAccount() {
        System.out.println("Enter New Username: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("Select a role for the user:");
        List<String> options = new ArrayList<String>();
        options.add("Admin");
        options.add("Purchase Manager");
        options.add("Sales Manager");
        int option = OptionPicker.optionPicker(options);
        switch (option) {
            case 1:
                FileOperations.writeObjectToFile(UserFactory.createUser(password, "Admin"), "resources/data/admin.txt");
                break;
            case 2:
                FileOperations.writeObjectToFile(UserFactory.createUser(password, "Purchase Manager"), "resources/data/purchasemanager.txt");
                break;
            case 3:
                FileOperations.writeObjectToFile(UserFactory.createUser(password, "Sales"), "resources/data/salesmanager.txt");
                break;
        }
        

        
    }
}            