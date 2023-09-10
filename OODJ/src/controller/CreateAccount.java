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
        System.out.println("Select a role for the user:");
        List<String> options = new ArrayList<String>();
        options.add("Purchase Manager");
        options.add("Sales Manager");
        int option = OptionPicker.optionPicker(options);
        System.out.println("Enter Password for the user: ");
        String password = scanner.nextLine();
        switch (option) {
            case 1:
                FileOperations.writeObjectToFile(new PurchaseManager(password), PurchaseManager.filePath);
                break;
            case 2:
                FileOperations.writeObjectToFile(new SalesManager(password), SalesManager.filePath);
                break;
        }
        System.out.println("User has been created.");

        
    }
}            