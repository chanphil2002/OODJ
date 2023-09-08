/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;
import controller.FileOperations;
import controller.IDataParser;
import controller.IdGenerator;
import controller.Login;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import view.OptionPicker;

public class Admin extends User<Admin>{
    public Admin() {
        super();
    }
    
    public Admin(String password) {
        super(password);
        super.userID = idGenerator.generateID("A");
    }
    
    public Admin(String userID, String password, boolean dataAvailable) {
        super(password);
        super.userID = userID;
        this.dataAvailable = dataAvailable;
    }
    

    private String roleName = "Admin";
    public final static String filePath = "resources/data/admin.txt";
    private final IdGenerator idGenerator = new IdGenerator(filePath);
    private boolean dataAvailable;
    
    
    

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public Admin parseData(String line){
        String[] parts = line.split(",");
        if (parts.length == 3) { // Assuming 2 attributes in the data
            String userID = parts[0];
            String password = parts[1];
            String role = parts[2];
            boolean dataAvailable = Boolean.parseBoolean(parts[3]);

            return new Admin(userID, password, dataAvailable);
        }
        return null;
    }

    @Override
    public boolean getDataAvailable() {
        return dataAvailable;
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
    public void setDataAvailable(boolean deleted) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }
   
}

