/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Scanner;

import model.Sales;
import users.*;

/**
 *
 * @author pc
 */
public class EditAccount {
    private static Scanner scanner = new Scanner(System.in);

    public static void editUsers() {
        displayUsers();
        List<User> UserList = null;
        UserList.add(0, null);
        List<Admin> AdminList = FileOperations.readObjectsFromFile("resources/data/admin.txt", new Admin());
        List<PurchaseManager> PMList = FileOperations.readObjectsFromFile("resources/data/purchasemanager.txt", new PurchaseManager());
        List<SalesManager> SMList = FileOperations.readObjectsFromFile("resources/data/salesmanager.txt", new SalesManager());
        System.out.print("Enter User ID to Edit Password: ");
        String userID = scanner.nextLine();
        try {
            User<Admin> foundAdmin = (User<Admin>) FileOperations.findDataByCode(userID, AdminList);
            User<PurchaseManager> foundPM = (User<PurchaseManager>) FileOperations.findDataByCode(userID, PMList);
            User<SalesManager> foundSM = (User<SalesManager>) FileOperations.findDataByCode(userID, SMList);
            User foundUser = (foundAdmin != null) ? foundAdmin : (foundPM != null) ? foundPM : foundSM;
            if (foundUser != null) {
                System.out.print("Change Password: (or Press Enter to skip): ");
                String password = scanner.nextLine();
                if(password.isEmpty()){
                    System.out.println("You didn't change the password.");
                } else {
                    foundUser.setPassword(password);
                }

                if (foundAdmin != null) {
                    FileOperations.updateObjectInFile((Admin) foundAdmin, foundUser.getFilePath(), AdminList);
                } else if (foundPM != null) {
                    FileOperations.updateObjectInFile((PurchaseManager) foundPM, foundPM.getFilePath(), PMList);
                } else if (foundSM != null) {
                    FileOperations.updateObjectInFile((SalesManager) foundSM, foundSM.getFilePath(), SMList);
                }
            }
        } catch (Exception Exception) {
            System.out.println("Error with file reading."); //shouldn't happen on the user's side
        }
        
        }
    
    
    
    public static void displayUsers () {
        List<Admin> AdminList = FileOperations.readObjectsFromFile("resources/data/admin.txt", new Admin());
        List<PurchaseManager> PMList = FileOperations.readObjectsFromFile("resources/data/purchasemanager.txt", new PurchaseManager());
        List<SalesManager> SMList = FileOperations.readObjectsFromFile("resources/data/salesmanager.txt", new SalesManager());
        
        System.out.printf("%-17s %-20s%n", "User ID   |", "Role           |   ");
        for(User u : AdminList){
            if(u.getDataAvailable()==true){
                System.out.printf("%-17s %-20s%n", u.getCode(), u.getRoleName());
            }
        }
    }
}
