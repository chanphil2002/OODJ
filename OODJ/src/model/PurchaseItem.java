/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pc
 */
public class PurchaseItem {
    private Item item; 
    private int quantityRequested;
    private Supplier supplier;
    
    public PurchaseItem(Item item, Supplier supplier, int quantityRequested) {
        this.item = item;
        this.supplier = supplier;
        this.quantityRequested = quantityRequested;
    }
    
    public Item getItem(){
        return item;
    }
    
    public int getQuantityRequested(){
        return quantityRequested;
    }
    
    public Supplier getSupplier(){
        return supplier;
    }
    
    public void setItem(Item item){
        this.item = item;
    }
    
    public void setQuantityRequested(int quantityRequested){
        this.quantityRequested = quantityRequested;
    }
    
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
}
