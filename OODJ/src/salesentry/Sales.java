/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesentry;

import itementry.Item;
import itementry.Item;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import utility.*;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap; 
import java.util.TreeMap;
/**
 *
 * @author pc
 */
public class Sales implements IFileFormattable, IDataParser<Sales>, IDataSearchable{
    private LocalDate date;
    private String salesCode;
    private List<Item> items;
    private Map<String, Integer> quantitySold;
    private double salesAmount;
    
    public Sales(String salesCode, List<Item> items, Map<String, Integer> quantitySold, double salesAmount){
        date = LocalDate.now();
        this.salesCode = salesCode;
        this.items = items;
        this.quantitySold = quantitySold;
        this.salesAmount = salesAmount;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    @Override
    public String getCode() {
        return salesCode;
    }
    
    public List<Item> getItems(){
        return items;
    }
    
    public Map<String, Integer> getQuantitySold(){
        return quantitySold;
    }
    
    public double getSalesAmount(){
        return salesAmount;
    }
    
    public void setSalesCode(String salesCode){
        this.salesCode = salesCode;
    }
    
    public void setItems(List<Item> items){
        this.items = items;
    }
    
    public void setQuantitySold(Map<String, Integer> quantitySold){
        this.quantitySold = quantitySold;
    }
    
    public void setSalesAmount(double salesAmount){
        this.salesAmount = salesAmount;
    }

    public void addItem(Item item){
        items.add(item);
    }
    
    @Override
    public String formatForFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sales Code: ").append(salesCode).append("\n");
        sb.append("Sales Amount: ").append(salesAmount).append("\n");
        sb.append("Items:\n");
        for (Item item : items){
            sb.append("- Item Code: ").append(item.getCode())
              .append(", Item Name: ").append(item.getItemName()).append("\n")
              .append(", Quantity Sold: ").append(quantitySold.getOrDefault(item.getCode(),0))
              .append("\n");
        }
        sb.append("Total Sales Amount: ").append(salesAmount).append("\n");
        sb.append("\n");
        return sb.toString();
        
//        return date + "," + items + "," + quantitySold + "," + salesAmount;
    }

    @Override
    public Sales parseData(String line) {
        String[] lines = line.split("\n");
        String salesCode = lines[0].replace("Sales Code: ","");
        double salesAmount = Double.parseDouble(lines[1].replace("Sales Amount: ",""));
        
        List<Item> itemList = new ArrayList<>();
        for (int i = 3; i < lines.length; i++){
            if(lines[i].startsWith("Item Code: ")){
                String[] parts = lines[i].replace("- Item Code: ","").split(", Item Name: ");
                String itemCode = parts[0];
                String itemName = parts[i];
                itemList.add(new Item(itemCode, itemName));
            }
        }
        
        Sales sales = new Sales(salesCode, itemList, quantitySold, salesAmount);
        return sales;
    }
}
