/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.LinkedList;

/**
 *
 * @author Ace
 */
public class ProductList {
    
    public ProductList()
    {
        Plist  = new LinkedList();
        
    }
    public void add(Item i)
    {
        Plist.addLast(i);
        
    }
  
    
    
    public LinkedList<Item> Plist;
}