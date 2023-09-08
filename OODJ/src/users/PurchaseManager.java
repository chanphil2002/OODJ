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
public class PurchaseManager extends User{

    public PurchaseManager(String password) {
        super(password);
        super.userID = idGenerator.generateID("PM");
    }
    private final String roleName = "Purchase Manager";
    private final String filePath = "resources/data/purchasemanager.txt";
    private final IdGenerator idGenerator = new IdGenerator(filePath);
   
    @Override
    public String getRoleName() {
        return roleName;
    }
}
