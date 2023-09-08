/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

import controller.IdGenerator;

/**
 *
 * @author pc
 */
public class SalesManager extends User<SalesManager>{
    public SalesManager() {

    }
    public SalesManager(String password) {
        super(password);
        super.userID = idGenerator.generateID("SM");
    }

    public SalesManager(String userID, String password, boolean dataAvailable) {
        super(password);
        super.userID = userID;
        this.dataAvailable = dataAvailable;
    }

    private String roleName = "Sales Manager";
    public final static String filePath = "resources/data/salesmanager.txt";
    private final IdGenerator idGenerator = new IdGenerator(filePath);
    private boolean dataAvailable;

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public String formatForFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'formatForFile'");
    }

    @Override
    public SalesManager parseData(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) { // Assuming 2 attributes in the data
            String userID = parts[0];
            String password = parts[1];
            String role = parts[2];
            boolean dataAvailable = Boolean.parseBoolean(parts[3]);

            return new SalesManager(userID, password, dataAvailable);
        }
        return null;
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCode'");
    }

    @Override
    public boolean getDataAvailable() {
        return dataAvailable;
    }

    @Override
    public void setDataAvailable(boolean deleted) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDataAvailable'");
    }
    @Override
    public String getFilePath() {
        return this.filePath;
    }
}
