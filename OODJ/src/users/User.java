/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;
import java.io.*;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author pc
 */
public abstract class User implements Serializable{
    private String userID;
    private String password;
    private String role;
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner userScanner(){
        return scanner;
    }

    public static int optionPicker(List<String> options){
        int i = 1;
        for (String option: options) {
            System.out.println(Integer.toString(i) + ". " + option);
            i++;
        }
        while (true){
            System.out.println("Please select an option: ");
            String input = userScanner().nextLine();
            boolean success = true;
            int inputNum = 0;
            try {
                inputNum = Integer.parseInt(input);
            } catch (Exception NumberFormatException) {
                success = false;
                System.out.println("Check if your input is correct.");
            } 
            if (inputNum >= i || inputNum <= 0){//input out of range
                success = false;
                System.out.println("Check if your input is correct.");
            } 

            if (success){ //no input errors and input refers to an option
                return inputNum;
            }
            
        }
        

    }
}
