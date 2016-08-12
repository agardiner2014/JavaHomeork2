/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.LinkedList;

/**
 *
 * @author akrna
 */
public class SellerList {
    
    private SellerList()
    {
        Slist  = new LinkedList();
        Slist.add(new Seller("SAndrew", "coolguy"));
        Slist.add(new Seller("SAldrick","password"));
        Slist.add(new Seller("SSystemUser", "1234"));
        
        hardcodeItems();
        
    }
    public static SellerList getInstance()
    {
        if(instance == null)
        {
            instance = new SellerList();
        }
        return instance;
    }
    
                
    
    public Seller checkUser(Seller seler)
    {

        for (Seller seller : Slist)
        {
            if(seler.getUsername().equals(seller.getUsername()) && seler.getPassword().equals(seller.getPassword()))
           {
                return seller;
             }        
        }
        return null;
        
    }
    
    public LinkedList<Seller> Slist;
    private static SellerList instance = null;
    
    
    
    private void hardcodeItems()
    {
        
        
        Slist.get(0).addItem(new Item(60.00,45.00,100,"FallOut 4","Shooter"));
        Slist.get(0).addItem(new Item(75.00,55.00,100,"CAll of Duty 6", "shooter"));
        Slist.get(0).addItem(new Item(65.00,40.00,300,"uncharted","adventure"));
        Slist.get(1).addItem(new Item(30.00,20,300,"Pokemnon","RPG"));
        Slist.get(1).addItem(new Item(40.00,25.00,100,"Legend of Zelda","adventure"));
        Slist.get(1).addItem(new Item(25.00,10.00,500,"Final Fantasy","RPG"));
        Slist.get(2).addItem(new Item(50.00,40.00,234, "Tony Hawk Pro Skater","sports"));
        Slist.get(2).addItem(new Item(25.00,10.00,300,"Need For Speed","racing"));
        Slist.get(2).addItem(new Item(80.00,50.00,300,"GTA4", "Adventure"));
        Slist.get(2).addItem(new Item(63.00,45.00,130, "FIFA","Sports"));
        Slist.get(2).addItem(new Item(56.00,34.00,100,"Halo","Sci-fi"));
        Slist.get(2).addItem(new Item(30.00, 20.00, 100, "NBA2K15","sports"));
        
        
        
        
    }
}
