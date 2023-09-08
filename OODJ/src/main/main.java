/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import auth.Login;
/**
 *
 * @author pc
 */

import controller.FileOperations;
import controller.*;
import model.*;
import java.util.ArrayList;
import java.util.List;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        CreateEntry.createSupplierEntry();
//        CreateEntry.createItemEntry();
//        CreateEntry.createSalesEntry();
//        CreateEntry.createPREntry();
//        CreateEntry.createPOEntry();
        
        DisplayEntry.displaySupplierEntry();
        DisplayEntry.displayItemsEntry();
        DisplayEntry.displaySalesEntry();
        DisplayEntry.displayPREntry();
        DisplayEntry.displayPOEntry();
        
//        EditEntry.editSupplierEntry();
//        EditEntry.editItemEntry();
//        EditEntry.editSalesEntry();
//        EditEntry.editPREntry();
//        EditEntry.editPOEntry();
    }
    

    
}
