/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchaserequisition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import itementry.Item;
import supplierentry.Supplier;
/**
 *
 * @author pc
 */
public class PurchaseRequisition {
    private String PRID;
    private List<Item> PRItemList;
    private List<Integer> PRquantity;
    private LocalDate PRDate;
    private Supplier supplierID;
    private boolean status;
    
}
