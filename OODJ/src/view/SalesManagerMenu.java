/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;

import controller.CreateEntry;
import controller.DisplayEntry;
import controller.EditEntry;
import controller.Login;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author pc
 */

public class SalesManagerMenu {
    public void menu(){
        System.out.println("Sales Manager Menu");
        List<String> options  = new ArrayList<>();
        options.add("Manage Item Entry");
        options.add("Manage Supplier Entry");
        options.add("Manage Sales Entry");
        options.add("Manage Purchase Requisition");
        options.add("Display Purchase Orders");
        options.add("Log out");
        int option;
        option = OptionPicker.optionPicker(options);
        switch (option){
            case 1:
                manageItemMenu(); 
                break;
            case 2:
                manageSupplierMenu();
                break;
            case 3:
               manageSEMenu();
                break;
            case 4:
                managePRMenu();
                break;
            case 5:
                DisplayEntry.displayPOEntry();
                break;
            case 6:
                Login.loginInterface();
                break;
            }
        }      
     private void manageItemMenu() {
        DisplayEntry.displayItemsEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Item");
        options.add("Edit/Delete Items");
        options.add("Return to Sales Manager Menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                CreateEntry.createItemEntry();
                manageItemMenu();
                break;
            case 2:
                EditEntry.editItemEntry();
                manageItemMenu();
                break;
            case 3:
                menu();
                break;

        }
        
        
                
        }
        private void manageSupplierMenu() {
        DisplayEntry.displaySupplierEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Supplier");
        options.add("Edit/Delete Suppliers");
        options.add("Return to Sales Manager menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                CreateEntry.createSupplierEntry();
                manageSupplierMenu();
                break;
            case 2:
                EditEntry.editSupplierEntry();
                manageSupplierMenu();
                break;
            case 3:
                menu();
                break;
        }
    }
    private void managePRMenu() {
        DisplayEntry.displayPREntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Purchase Requisition");
        options.add("Edit/Delete Purchase Requisition");
        options.add("Return to Sales Manager menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                CreateEntry.createPREntry();
                managePRMenu();
                break;
            case 2:
                EditEntry.editPREntry();
                managePRMenu();
                break;
            case 3:
                menu();
                break;
        }
    }
    
      private void manageSEMenu() {
        DisplayEntry.displaySalesEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Sales Entry");
        options.add("Edit/Delete Sales Entry");
        options.add("Return to Sales Manager Menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                CreateEntry.createSalesEntry();
                manageSEMenu();
                break;
            case 2:
                EditEntry.editSalesEntry();
                manageSEMenu();
                break;
            case 3:
                menu();
                break;
        }
    }



}
                
    
    



 
