/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;
import controller.IdGenerator;

public class Admin extends User<Admin>{
    public Admin() {
        super();
    }
    
    public Admin(String password) {
        super.userID = idGenerator.generateID("A");
    }
    
    public Admin(String userID, String password) {
        super(password);
        super.userID = userID;
    }
    

    private String roleName = "Admin";
    public final static String filePath = "resources/data/Admin.txt";
    private final IdGenerator idGenerator = new IdGenerator(filePath);
    
    
    
    
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
            if ("Admin".equals(role)) {
                return new Admin(userID, password);
            }
        }
        return null;
    }

    @Override
    public boolean getDataAvailable() {
        return true;
    }

    @Override
    public String formatForFile() {
        return userID + "," + getPassword() + ",Admin";
    }

    @Override
    public String getCode() {
        return userID;
    }


    @Override
    public void setDataAvailable(boolean deleted) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getFilePath() {
        return Admin.filePath;
    }
   
}

