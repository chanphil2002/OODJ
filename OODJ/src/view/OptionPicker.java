/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import static users.User.userScanner;


public class OptionPicker {
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
                if (inputNum >= i || inputNum <= 0){//input out of range
                    success = false;
                    System.out.println("Check if your input is correct.");
                }
            } catch (NumberFormatException e) {
                success = false;
                System.out.println("Check if your input is correct.");
            }

            if (success){ //no input errors and input refers to an option
                return inputNum;
            }
        }
    }
}







