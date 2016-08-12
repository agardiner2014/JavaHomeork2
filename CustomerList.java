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
public class CustomerList {
    
    private CustomerList()
    {
        Clist  = new LinkedList();
        Clist.add(new Customer("Andrew", "coolguy"));
        Clist.add(new Customer("Aldrick","password"));
        Clist.add(new Customer("SystemUser", "1234"));
        
    }
    public static CustomerList getInstance()
    {
        if(instance == null)
        {
            instance = new CustomerList();
        }
        return instance;
    }
    
                
    
    public Customer checkUser(Customer cust)
    {

        for (Customer customer : Clist)
        {
            if(cust.getUsername().equals(customer.getUsername()) && cust.getPassword().equals(customer.getPassword()))
            {
                return customer;
            }
            
        }        
        return null;
        
    }
    
    private LinkedList<Customer> Clist;
    private static CustomerList instance = null;
}



