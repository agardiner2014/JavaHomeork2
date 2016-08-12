/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.*;
/**
 *
 * @author akrna
 */
public class Cart {
    
    public Cart()
    {
        SClist = new LinkedList();
        cost = 0;
        
    }
    
    
    public void add(Item i, int count)
    {
        SClist.addLast(new Item(i,count));
        
        cost = cost + (i.getSellPrice() * count);
    }
    
    
    //count is the number to be updated to
    public void updatequant(Item i, int count)
    {
        cost = cost + ((count - i.getQuantity())*i.getSellPrice());
        i.setQuantity(count);
   
        
    }
    public void Remove(Item i)
    {
       
        cost = cost - (i.getQuantity() * i.getSellPrice());
        SClist.remove(i);
        
    }
    
    public double getCost()
    {
        return cost;
    }
    
    
    
    
    
    public LinkedList<Item> SClist;
    private double cost;
}
