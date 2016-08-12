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
public class Seller {
    private String password;
    private String username;

    
    public Seller(String user, String paswrd)
    {
        username = user;
        password = paswrd;
        prodlist = new ProductList();
    }
    public void addItem(Item i)
    {
       prodlist.add(i);
    }
    
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    
    
    
    
    private double Cost;
    private double revenue;
    private double profit;
    public ProductList prodlist;
      
    
}
