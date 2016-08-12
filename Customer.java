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
public class Customer {
    private String password;
    private String username;
    public Cart shoppingCart;
    
    public Customer(String user, String paswrd)
    {
        username = user;
        password = paswrd;
        shoppingCart = new Cart();
        
    }
    
    public String getPassword()
    {
        
        return password;
    }
    public String getUsername()
    {
        return username;
    }
    
      
}