/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;
import itementry.*;
import salesentry.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class MenuUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void createEntry(Item item){
        System.out.println("Enter Item Name: ");
        String itemName = scanner.nextLine();
        
        System.out.println("Enter item quantity");
        int itemQuantity = scanner.nextInt();
        
        item.setItemCode("I002");
        item.setItemName(itemName);
        item.setItemQuantity(itemQuantity);
        
        FileOperations.writeObjectToFile(item, "resources/data/item.txt");
    }
    
    public static void createEntry(Sales sales){
        List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        
        System.out.println("Enter Item Code: ");
        String item = scanner.nextLine();
        Item foundItem = (Item) FileOperations.findDataByCode(item, itemList);
        
//        System.out.println("Enter Sales Amount: ");
//        double salesAmount = scanner.nextDouble();
        
        
//        FileOperations.writeObjectToFile(sales, "resources/data/sales.txt");
    }
}
