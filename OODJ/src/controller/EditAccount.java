/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Scanner;
import users.*;

/**
 *
 * @author pc
 */
public class EditAccount {
    private static Scanner scanner = new Scanner(System.in);
    
    
    public void displayUsers () {
        List<Admin> AdminList = FileOperations.readObjectsFromFile("resources/data/user.txt", new Admin());
        List<PurchaseManager> PMList = FileOperations.readObjectsFromFile("resources/data/user.txt", new PurchaseManager());
        List<Admin> AdminList = FileOperations.readObjectsFromFile("resources/data/user.txt", new Admin());
        
        System.out.printf("%-17s %-20s%n", "Supplier ID   |", "Supplier Name       ");
        for(Supplier s : supplierList){
            if(s.getDataAvailable()==true){
                System.out.printf("%-17s %-20s%n", s.getCode(), s.getSupplierName());
            }
        }
    }
}
