/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.*;
import java.util.List;

/**
 *
 * @author pc
 */
public class Supplier implements IFileFormattable, IDataParser<Supplier>, IDataSearchable, IFileStatus{
    private String supplierID;
    private String supplierName;
    public static final String filePath = "resources/data/supplier.txt";
    private IdGenerator idGenerator = new IdGenerator(filePath);
    private boolean dataAvailable;
    
    public Supplier(){
    }
    
    public Supplier(String supplierName){
        supplierID = idGenerator.generateID("SP");
        this.supplierName = supplierName;
        dataAvailable = true;
    } 
    
    public Supplier(String supplierID, String supplierName, boolean dataAvailable){
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.dataAvailable = dataAvailable;
    }
    
    @Override
    public String getCode(){
        return supplierID;
    }
    
    public String getSupplierName(){
        return supplierName;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public void setSupplierID(String supplierID){
        this.supplierID = supplierID;
    }
    
    public void setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }
    
    @Override
    public boolean getDataAvailable() {
        return dataAvailable;
    }

    @Override
    public void setDataAvailable(boolean deleted) {
        this.dataAvailable = deleted;
    }

    @Override
    public String formatForFile() {
        return supplierID + "," + supplierName + "," + dataAvailable;
    }

    @Override
    public Supplier parseData(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            String supplierID = parts[0];
            String supplierName = parts[1];
            boolean dataAvailable = Boolean.parseBoolean(parts[2]);

            return new Supplier(supplierID, supplierName, dataAvailable);
        }
        return null;
    }


}
