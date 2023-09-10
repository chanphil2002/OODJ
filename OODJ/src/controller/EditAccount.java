/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
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
        //List<User> UserList = new ArrayList<User>();
        //UserList.add(0, null);
        List<Admin> AdminList = FileOperations.readObjectsFromFile(Admin.filePath, new Admin());
        List<PurchaseManager> PMList = FileOperations.readObjectsFromFile(PurchaseManager.filePath, new PurchaseManager());
        List<SalesManager> SMList = FileOperations.readObjectsFromFile(SalesManager.filePath, new SalesManager());
        System.out.print("Enter User ID to Edit Password: ");
        String userID = scanner.nextLine();
        User<Admin> foundAdmin = null;
        User<PurchaseManager> foundPM = null;
        User<SalesManager> foundSM = null;
        try {
            foundAdmin = (User<Admin>) FileOperations.findDataByCode(userID, AdminList);
        } catch (Exception e){
            //no admins matches
        }
        try {
            foundPM = (User<PurchaseManager>) FileOperations.findDataByCode(userID, PMList);
        } catch (Exception e){
            //no PM matches
        }
        try {
            foundSM = (User<SalesManager>) FileOperations.findDataByCode(userID, SMList);
        } catch (Exception e){
            //no SM matches
        }
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
                System.out.println("1");
            } else if (foundPM != null) {
                FileOperations.updateObjectInFile((PurchaseManager) foundPM, foundPM.getFilePath(), PMList);
                System.out.println("2");
            } else if (foundSM != null) {
                FileOperations.updateObjectInFile((SalesManager) foundSM, foundSM.getFilePath(), SMList);
                System.out.println("3");
            }
        }
        
        
        }
    
    
    
    public static void displayUsers () {
        List<Admin> AdminList = FileOperations.readObjectsFromFile(Admin.filePath, new Admin());
        List<PurchaseManager> PMList = FileOperations.readObjectsFromFile(PurchaseManager.filePath, new PurchaseManager());
        List<SalesManager> SMList = FileOperations.readObjectsFromFile(SalesManager.filePath, new SalesManager());
        
        System.out.printf("%-17s %-20s%n", "User ID   |", "Role           |   ");
        for(Admin u : AdminList){
            System.out.printf("%-17s %-20s%n", u.getCode(), u.getRoleName());
        }
        for(PurchaseManager u : PMList){
            System.out.printf("%-17s %-20s%n", u.getCode(), u.getRoleName());
        }
        for(SalesManager u : SMList){
            System.out.printf("%-17s %-20s%n", u.getCode(), u.getRoleName());
        }

    }
}
