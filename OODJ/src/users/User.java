/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;
import controller.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public abstract class User<T> implements IFileFormattable, IDataParser<T>, IDataSearchable, IFileStatus{
    protected String userID;
    private String password;
    private String roleName;
    private static Scanner scanner = new Scanner(System.in);

    
    
//    @Override
//    public String formatForFile() {  
//        return userID + password + getRoleName();
//    }
//    
//    @Override
//    public T parseData(String line){
//        
//        
//        return null;
//    } 

    public static Scanner userScanner(){
        return scanner;
    }
    public User(){
        
    }
    
    public User(String password) {
        this.password = password;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getPassword(){
        return this.password;
    }
    

    public void menu(){
        //overriding this
    }
    
    public String getRoleName() {
        return roleName;
    }

    
}
