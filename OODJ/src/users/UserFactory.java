/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

/**
 *
 * @author pc
 */
public class UserFactory {
    public static User createUser(String password, String role){
        if ("Admin".equals(role)){
            return new Admin(password);
        }   else if ("Purchase Manager".equals(role)) {
            return new PurchaseManager(password);
        }   else if ("Sales Manager".equals(role)) {
            return new SalesManager(password);
        }
        return null;
    }

}

