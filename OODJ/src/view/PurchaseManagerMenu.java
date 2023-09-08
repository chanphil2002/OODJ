/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.CreateEntry;
import controller.DisplayEntry;
import controller.EditEntry;
import java.util.ArrayList;
import java.util.List;


public class PurchaseManagerMenu {
    public void menu(){
        System.out.println("Puchase Manager Menu");
        List<String> options  = new ArrayList<>();
        options.add("List of items");
        options.add("List of suppliers");
        options.add("Display requisition");
        options.add("Generate purchase order");
        options.add ("List of purchaser order");
        options.add("Log out");
        int option;
        option = OptionPicker.optionPicker(options);
        switch (option){
            case 1:
                DisplayEntry.displayItemsEntry();
                break;       
            case 2:
                DisplayEntry.displaySupplierEntry();
                break;
            case 3:
                DisplayEntry.displayPREntry();
                break;
            case 4:
                managePOMenu();
                break;
            case 5:
                break;
            }                      
        }

    private void managePOMenu() {
        DisplayEntry.displayPREntry();
        List<String> options = new ArrayList<String>();
        options.add("Create Purchase Requisition");
        options.add("Edit/Delete Purchase Requisition");
        options.add("Return to Purchase Manager Menu");
        int option = OptionPicker.optionPicker(options);
        switch(option){
            case 1:
                CreateEntry.createPREntry();
                managePOMenu();
                break;
            case 2:
                EditEntry.editPREntry();
                managePOMenu();
                break;
            case 3 :
                menu();
                break;
        }
        
    }

    }
 