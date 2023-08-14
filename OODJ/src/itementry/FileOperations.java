/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itementry;

/**
 *
 * @author pc
 */
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class FileOperations {
    public static void addItemToFile(Item item){
        String filePath = "items.txt";
        
        try(FileWriter writer = new FileWriter(filePath, true)){
            String itemData = item.getItemCode() + "," +
                              item.getItemName() + "," +
                              item.getItemQuantity() + "," + 
                              item.getPrice() + "," +
                              item.getSupplierID() + "\n";
            writer.write(itemData);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void ReadItemsFromFile() {
        String filePath = "items.txt";
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] itemData = line.split(",");
                if(itemData.length == 5){
                    String itemCode = itemData[0];
                    String itemName = itemData[1];
                    int itemQuantity = Integer.parseInt(itemData[2]);
                    double price = Double.parseDouble(itemData[3]);
                    String supplierID = itemData[4];
                    
                    System.out.println("Item Code: " + itemCode);
                    System.out.println("Item Name: " + itemName);
                    System.out.println("Item Quantity: " + itemQuantity);
                    System.out.println("Price: " + price);
                    System.out.println("Supplier ID: " + supplierID);
                    System.out.println("---------------------");
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void updateItemtoFile(String itemCodetoUpdate, String updatedData){
        String filePath = "items.txt";
        List<String> lines = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                if(!line.startsWith(itemCodetoUpdate)){
                    lines.add(updatedData);
                } else {
                    lines.add(line);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        
        try(FileWriter writer = new FileWriter(filePath, false)){
            for(String line : lines){
                writer.write(line + "\n");
            } 
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteItemFromFile(String itemCodeToDelete) {
        String filePath = "items.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(itemCodeToDelete)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
