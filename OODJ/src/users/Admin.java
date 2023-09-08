/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;
import auth.Login;
import controller.FileOperations;
import controller.IDataParser;
import controller.IdGenerator;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import view.OptionPicker;

public class Admin extends User<Admin>{
    public Admin(){
        super();
    }
    
    public Admin(String password) {
        super(password);
        super.userID = idGenerator.generateID("A");
    }
    
    
    private final String roleName = "Admin";
    private final String filePath = "resources/data/admin.txt";
    private final IdGenerator idGenerator = new IdGenerator(filePath);
    
    /**
     * 
     */
    public void menu() {
        System.out.println("Admin Menu\n");
        List<String> options = new ArrayList<String>();
        options.add("Manage Users");
        options.add("Manage Items");
        options.add("Manage Purchase Orders");
        options.add("Manage Purchase Requisition");
        options.add("Change password");
        options.add("Log out");
        int option;
        option = OptionPicker.optionPicker(options);
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

    public void manageUserMenu() {
        //TODO print user list here
        List<String> options = new ArrayList<String>();
        options.add("Add user");
        options.add("Edit user role");
        options.add("Delete user");
        options.add("Return to admin menu");
        int option = OptionPicker.optionPicker(options);
        switch (option) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                this.menu();
                break;
        }
        
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public Admin parseData(String line){
        String[] parts = line.split(",");
        if (parts.length == 6) { // Assuming 2 attributes in the data
            String userID = parts[0];
            String password = parts[1];
            String role = parts[2];

            Admin admin = (Admin) FileOperations.findDataByCode(userID, adminList);

            return new Item(itemCode, itemName, quantity, price, supplier, dataAvailable);
        }
        return null;
    } 

    @Override
    public String formatForFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean getDataAvailable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDataAvailable(boolean deleted) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}

