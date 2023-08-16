/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;
import auth.Login;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class Admin extends User{
    
    /**
     * 
     */
    public static void adminMenu() {
        System.out.println("Admin Menu\n");
        List<String> options = new ArrayList<String>();
        options.add("Manage Users");
        options.add("Manage Items");
        options.add("Manage Purchase Orders");
        options.add("Manage Purchase Requisition");
        options.add("Change password");
        options.add("Log out");
        int option = optionPicker(options);
        switch (option) {
            case 1:
                manageUserMenu();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                //TODO change password function under user class
                break;
            case 6:
                //Do nothing here because it will simply proceed back to the login loop
                break;     
        }
    }

    public static void manageUserMenu() {
        //TODO print user list here
        List<String> options = new ArrayList<String>();
        options.add("Add user");
        options.add("Edit user role");
        options.add("Delete user");
        options.add("Return to admin menu");
        int option = optionPicker(options);
        switch (option) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                adminMenu();
                break;
        }
        
    }
    
}

