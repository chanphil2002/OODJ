/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
}
