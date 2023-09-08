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
public class SalesManager extends User{

    public SalesManager(String password) {
        super(password);
        super.userID = idGenerator.generateID("SM");
    }
    private final String roleName = "Sales Manager";
    private final String filePath = "resources/data/salesmanager.txt";
    private final IdGenerator idGenerator = new IdGenerator(filePath);

    @Override
    public String getRoleName() {
        return roleName;
    }
}
