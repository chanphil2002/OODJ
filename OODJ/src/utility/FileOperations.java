/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author pc
 */
import itementry.Item;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import utility.*;


public class FileOperations {

    //Write Data to File
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
    public static <T> List<T> readObjectsFromFile(String filePath, IDataParser<T> parser){
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
    
    //Update Data into File
    public static <T> void updateFile(String filePath, IDataParser<T> parser, IFileFormattable object){
        List<T> dataList = FileOperations.readObjectsFromFile(filePath, parser);
        
        FileOperations.writeObjectToFile(object, filePath);
    }
    
//    public static void deleteItemFromFile(String itemCodeToDelete) {
//        String filePath = "items.txt";
//        List<String> lines = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (!line.startsWith(itemCodeToDelete)) {
//                    lines.add(line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (FileWriter writer = new FileWriter(filePath, false)) {
//            for (String line : lines) {
//                writer.write(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
