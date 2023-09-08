/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.*;

public class IdGenerator {
    private final String filePath;

    public IdGenerator(String filePath) {
        this.filePath = filePath;
    }

    public String generateID(String prefix) {
        String lastId = readLastIDFromFile();
        int lastIdNumber = lastId.isEmpty() ? 0 : Integer.parseInt(lastId.substring(prefix.length()));
        int newIdNumber = lastIdNumber + 1;
        return prefix + String.format("%03d", newIdNumber);
    }
    
    private String readLastIDFromFile(){
        String lastId = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                lastId = extractIDFromLine(line);
            }
        } catch (IOException ignored){
            
        }
        return lastId;
    }
    
    private String extractIDFromLine(String line){
        String[] parts = line.split(",");
        if(parts.length > 0){
            String idPart = parts[0].trim();
            return idPart;
        }
        return "";
    }
}
