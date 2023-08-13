/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

/**
 *
 * @author pc
 */
import itementry.Item;
import supplierentry.Supplier;
import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SalesManager extends User implements Serializable{
    public static void createItemEntry() {

        System.out.println("Sales Manager: Create Item Entry");
        
        System.out.print("Item Code: ");
        String itemCode = userScanner().nextLine();

        System.out.print("Item Name: ");
        String itemName = userScanner().nextLine();

        System.out.print("Quantity: ");
        int itemQuantity = userScanner().nextInt();
        
        System.out.print("Selling Price: ");
        float price = userScanner().nextInt();
        
        System.out.print("Supplier ID: ");
        String supplierID = userScanner().nextLine();
        Supplier supplier = new Supplier(supplierID);

        userScanner().close();

        Item newItem = new Item();
        newItem.setItemCode(itemCode);
        newItem.setItemName(itemName);
        newItem.setItemQuantity(itemQuantity);
        newItem.setPrice(price);
        newItem.setSupplierID(supplier);


        // Serialize the Item Entry to a text file
        try {
            String filePath = "C:/Users/pc/OneDrive - Asia Pacific University/Degree/OODJ/Assignment/OODJ/resources/data/item_entry.ser";
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newItem);
            out.close();
            fileOut.close();
            System.out.println("Item Entry serialized and saved as 'item_entry.ser'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
