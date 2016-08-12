/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author akrna
 */
public class Item {
    
    public Item(double sp, double ip, int q, String n, String d)
    {
        sellprice = sp;
        invoiceprice = ip;
        quantity = q;
        itemID = IDcounter;
        IDcounter++;
        name = n;
        details = d;
        
        
    }
    
    public Item(Item i)
    {
        sellprice = i.sellprice;
        invoiceprice = i.invoiceprice;
        quantity = i.quantity;
        itemID = i.itemID;
        name = i.name;
        details = i.details;
 
    }
    
    public Item(Item i, int count)
    {
        sellprice = i.sellprice;
        invoiceprice = i.invoiceprice;
        quantity = count;
        itemID = i.itemID;
        name = i.name;
        details = i.details;
    }
    
    
    public void updateItem(double sp, double ip, int q, String n, String d)
    {
        sellprice = sp;
        invoiceprice = ip;
        quantity = q;
        name = n;
        details = d;
    }
    
    public void setQuantity(int q)
    {
        quantity = q;
        
    }
    
    public double getSellPrice()
    {
        return sellprice;
    }
    
    public double getInvoicePrice()
    {
        return invoiceprice;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public int getItemID()
    {
        return itemID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDetails()
    {
        return details;
    }
    
    public int getIDcounter()
    {
        return IDcounter;
    }
    
    public String toString()
    {
        return "Name: " + name +"   Genre: "+ details + "    Price: " + sellprice+ "   # avail: "+quantity;
    }
    
    private double sellprice;
    private double invoiceprice;
    private int quantity;
    private int itemID;
    private String name;
    private String details;
    private static int IDcounter =1;

}
