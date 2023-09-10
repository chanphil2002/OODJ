/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import controller.*;

/**
 *
 * @author pc
 */
public class AdminMenu {
    public void menu() {
        System.out.println("Admin Menu\n");
        List<String> options = new ArrayList<String>();
        options.add("Manage Users");
        options.add("Manage Items");
        options.add("Manage Purchase Orders");
        options.add("Manage Purchase Requisition");
        options.add("Manage Sales Orders");
        options.add("Manage Suppliers");
        options.add("Log out");
        int option;
        option = OptionPicker.optionPicker(options);
        switch (option) {
            case 1:
                manageUserMenu();
                break;
            case 2:
                manageItemMenu();
                break;
            case 3:
                managePOMenu();
                break;
            case 4:
                managePRMenu();
                break;
            case 5:
                manageSEMenu();
                break;
            case 6:
                manageSupplierMenu();
                break;
            case 7:
                //Do nothing here because it will simply proceed back to the login loop
                break;     
        }
    }

    private void manageSupplierMenu() {
        DisplayEntry.displaySupplierEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Supplier");
        options.add("Edit/Delete Suppliers");
        options.add("Return to admin menu");
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

    private void manageSEMenu() {
        DisplayEntry.displaySalesEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Sales Entry");
        options.add("Edit/Delete Sales Entry");
        options.add("Return to admin menu");
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

    private void managePRMenu() {
        DisplayEntry.displayPREntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Purchase Requisition");
        options.add("Edit/Delete Purchase Requisition");
        options.add("Return to admin menu");
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

    private void managePOMenu() {
        DisplayEntry.displayPOEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Purchase Order");
        options.add("Edit/Delete Purchase Order");
        options.add("Return to admin menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                CreateEntry.createPOEntry();
                managePOMenu();
                break;
            case 2:
                EditEntry.editPOEntry();
                managePOMenu();
                break;
            case 3:
                menu();
                break;
        }
    }

    private void manageItemMenu() {
        DisplayEntry.displayItemsEntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Item");
        options.add("Edit/Delete Items");
        options.add("Return to admin menu");
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

    private void manageUserMenu() {
        EditAccount.displayUsers();
        List<String> options = new ArrayList<String>();
        options.add("Add user");
        options.add("Edit user password");
        options.add("Return to admin menu");
        int option = OptionPicker.optionPicker(options);
        switch (option) {
            case 1:
                CreateAccount.CreateAccount();
                manageUserMenu();
                break;
            case 2:
                EditAccount.editUsers();
                manageUserMenu();
                break;
            case 3:
                menu();
                break;
        }
        
    }

    
}
