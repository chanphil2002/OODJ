/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author pc
 */
import model.Item;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;


public class FileOperations {

    //Write Single Data to File
    public static <T> void writeObjectToFile(IFileFormattable object, String filePath){
        try (FileWriter writer = new FileWriter(filePath, true)) {
            String formattedData = object.formatForFile();
            writer.write(formattedData + "\n");
            System.out.println("Data added to file: " + formattedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Read Lines From File and Parse Data Into List
    public static <T extends IFileStatus> List<T> readObjectsFromFile(String filePath, IDataParser<T> parser){
        List<String> lines = new ArrayList<>();
        List<T> dataList = new ArrayList<>();
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        for (String line : lines){
            T data = parser.parseData(line);
                dataList.add(data); 
        }
        
        return dataList;
    }
    
    //Search Data based on Code
    public static IDataSearchable findDataByCode(String code, List<? extends IDataSearchable> dataList) throws Exception{
        for(IDataSearchable data : dataList){
            if(data.getCode().equals(code)){
                return data;
            }
        throw new Exception("Data not found");
        }

        return null;
    }
   
   
    public static <T extends IFileFormattable & IDataSearchable> String formatListForFile(List<T> dataList, String filePath) {
        StringBuilder formattedData = new StringBuilder();

        for (T data : dataList) {
            formattedData.append(data.formatForFile()).append("\n");
        }

        return formattedData.toString();
    }
    
    public static <T extends IFileFormattable & IDataSearchable> void updateObjectInFile(T updatedObject, String filePath, List<T> dataList) {
        int indexOfObjectToUpdate = findIndexOfObjectToUpdate(dataList, updatedObject);
    
        if(indexOfObjectToUpdate >= 0){
            // Update the object in the list with the new data
            dataList.set(indexOfObjectToUpdate, updatedObject);
            
            // Format the updated list of objects as a single string
            String formattedData = formatListForFile(dataList, filePath);
            // Write the updated data back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.print(formattedData);
                
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error while updating object");
            }
            
        } else {
            System.out.println("Object not found or cannot be updated.");
        }
        
    }
    
        private static <T extends IDataSearchable> int findIndexOfObjectToUpdate(List<T> dataList, T updatedObject) {
        String updatedObjectCode = updatedObject.getCode();
        
        for (int i = 0; i < dataList.size(); i++) {
            T existingObject = dataList.get(i);
            
            if (existingObject.getCode().equals(updatedObjectCode)) {
                return i; // Found a matching object
            }
        }
        return -1; // Object not found
    }
}
