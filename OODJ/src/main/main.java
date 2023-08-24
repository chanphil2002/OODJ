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

import users.*;
import purchaserequisition.*;
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login.loginInterface();
        SalesManager sm = new SalesManager(null, null);
        
        sm.CreatePurchaseRequisition();
    }
    
}


//hello