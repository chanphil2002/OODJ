/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

/**
 *
 * @author pc
 */
public class Admin extends User{
    
    public Admin() {
        //TODO Auto-generated constructor stub
    }

    public void adminMenu() {
        System.out.println("""
                Admin Menu\n
                1. Manage Users\n
                2. Manage Items\n
                3. Manage Purchase Orders\n
                4. Manage Purchase Requisition\n
                5. Change password\n
                6. Log out\n
                """);
        String option = null;
        while (option == null) { //when the user haven't chosen an option
            System.out.println("Please choose an option: ");
            option = userScanner().nextLine();
            switch (option) {
                case "1":
                    manageUserMenu();
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":
                    //TODO change password function under user class
                    break;
                case "6":

                    break;
                default:
                    System.out.println("Please choose a correct option");
                    option = null;
                    break;
            }   
        }
    

    }

    public void manageUserMenu() {
        //TODO print user list here
        System.out.println("""
                \n
                1. Add User\n
                2. Change a user's role\n
                3. Delete User\n
                """);
        
    }
    
}

