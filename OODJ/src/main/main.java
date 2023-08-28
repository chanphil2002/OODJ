/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author pc
 */

import users.*;
import purchaserequisition.*;
import utility.*;
import supplierentry.*;
import purchaseorder.*;
import purchaserequisition.*;
import itementry.*;
import salesentry.*;
import java.util.ArrayList;
import java.util.List;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    //Use Create Function 
    Item item = new Item("I001", "Berry Cola", 10);
    FileOperations.writeObjectToFile(item, "resources/data/item.txt");
    
    //Use Read Function
     List<Item> itemList = FileOperations.readObjectsFromFile("resources/data/item.txt", new Item());
        for (Item i : itemList){
            System.out.println("itemCode: " + i.getItemCode());
            System.out.println("ItemName: " + i.getItemName());
        }
        Sales s = new Sales();
        MenuUtils m = new MenuUtils();
        m.createEntry()
    }
    

    
}