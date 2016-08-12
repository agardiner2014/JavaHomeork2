/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author akrna
 */
public class SCapp {
    
    public static Customer loginwindow()
    {
        final JFrame loginwindow = new JFrame();
        loginwindow.setLayout(new GridLayout(0,1));
        JPanel username = new JPanel(); 
        JPanel password = new JPanel();
        JPanel loginbutton = new JPanel();
        JButton lbutt = new JButton("login");
        loginbutton.add(lbutt);
        final JTextField usern = new JTextField("",15);
        final JTextField passw = new JTextField("",15);
        
        username.add(new JLabel("Username:"));
        username.add(usern);
        password.add(new JLabel("Password:"));
        password.add(passw);
        
        loginwindow.add(username);
        loginwindow.add(password);
        loginwindow.add(loginbutton);
        
        lbutt.addActionListener(new ActionListener(){
            
           public void actionPerformed(ActionEvent e)
           {
               /*
               System.out.println("Lbutt pressed");
               System.out.println("Username is: " + usern.getText());
               System.out.println("Password is: "+ passw.getText());
                 */
               
               
               CustomerList custDB = CustomerList.getInstance();
               Customer checkcust = custDB.checkUser(new Customer(usern.getText(),passw.getText()));
               
               if(null == checkcust )//checkuser exists
               {
                   SellerList sellerDB = SellerList.getInstance();
                   Seller checkseller = sellerDB.checkUser(new Seller(usern.getText(),passw.getText()));
                   
                   
                   if(null == checkseller)
                   {
                       final JFrame incorrectlogin= new JFrame();
                       JPanel text = new JPanel(new GridLayout(0,1));
                       JButton closebutt = new JButton("Close");
                       closebutt.addActionListener(new ActionListener(){
                           
                          public void actionPerformed(ActionEvent e){
                              
                              incorrectlogin.setVisible(false);
                          } 
                           
                           
                       });
  
                       text.add(new JLabel("Incorrect Passwor/Username"));
                       text.add(closebutt);
                   
                   
                       incorrectlogin.add(text);
                       incorrectlogin.pack();
                       incorrectlogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                       incorrectlogin.setVisible(true);
                   }
                   else 
                   {
                       //Procede to seller mainwindow
                       loginwindow.setVisible(false);
                       sellermain(checkseller);
                   }
               }
               else
               {
                   //Procede customer main window
                   loginwindow.setVisible(false);
                   mainwindow(checkcust);
               }
           }
            
            
        });
        
        
        loginwindow.pack();
        loginwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginwindow.setVisible(true);
        
        return null;
        
        
    }
    
    
    public static void mainwindow(final Customer c)
    {
        final JFrame maincustomer = new JFrame();
        maincustomer.setLayout(new GridLayout(0,1));
        
        JPanel user = new JPanel();
        final JComboBox itemlist = new JComboBox();
        
        JPanel bottemButtons = new JPanel();
        JPanel itemList = new JPanel();
        
        for(Seller s: SellerList.getInstance().Slist)
        {
            for(Item i: s.prodlist.Plist)
            {
                itemlist.addItem(i);
            }
        }
        itemList.add(itemlist); 
        
        user.add(new JLabel("USER: "+c.getUsername())); 
        
        
        
        
        JButton scart = new JButton("Shopping Cart");
        JButton viewdetails = new JButton("View Item Details");
        JButton addItem = new JButton("Add Item");
        final JTextField quant = new JTextField("",5);
        
        viewdetails.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e)
            {
                itemdetailwindow((Item) itemlist.getSelectedItem(), c);
            }
            
            
        });
        addItem.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e)
            {
                c.shoppingCart.add((Item)itemlist.getSelectedItem(),Integer.parseInt(quant.getText()));
            }
            
            
        });
        scart.addActionListener(new ActionListener(){
            
           public void actionPerformed(ActionEvent e )
           {
               ShoppingCartWindow(c);
               maincustomer.setVisible(false);
           }
            
            
        });
        
        
        
        bottemButtons.add(scart);
        bottemButtons.add(viewdetails);
        bottemButtons.add(addItem);
        bottemButtons.add(quant);
        
        maincustomer.add(user);
        maincustomer.add(itemList);
        maincustomer.add(bottemButtons);
        
        maincustomer.pack();
        maincustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maincustomer.setVisible(true);
    }
    
    
    
    
    
    public static void sellermain(final Seller s)
    {
        JFrame sellmain = new JFrame();
        sellmain.setLayout(new GridLayout(0,1));
        JPanel itemlist = new JPanel();
        JPanel sellername = new JPanel();
        final JComboBox itemList = new JComboBox();
        
        for(Item i: s.prodlist.Plist)
        {
            itemList.addItem(i);
        }
        
        
        sellername.add(new JLabel("SELLER: "+s.getUsername()));
        itemlist.add(itemList);
        
        JPanel buttonbar = new JPanel();
        
        JButton additem = new JButton("Add an Item");
        JButton checkmoney = new JButton("Check finaces");
        JButton updateItem = new JButton("Update Item");
        
        additem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
                sellerAddItem(s,itemList);
            }
        });
        updateItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                sellerupdateitem(s,(Item)itemList.getSelectedItem());
            }
        });
        
        buttonbar.add(updateItem);
        buttonbar.add(additem);
        buttonbar.add(checkmoney);
        
        sellmain.add(sellername);
        sellmain.add(itemlist);
        sellmain.add(buttonbar);
        
        
        sellmain.pack();
        sellmain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sellmain.setVisible(true);
        
    }
    
    public static void itemdetailwindow(final Item i, final Customer c)
    {
        final JFrame itemwindow = new JFrame();
        JButton closewindow = new JButton("Close");
        itemwindow.setLayout(new GridLayout(0,1));
        JPanel details = new JPanel();
        details.setLayout(new GridLayout(0,2));
        final JTextField quant = new JTextField(5);
        JButton add = new JButton("Add");
        JPanel buttonbar = new JPanel();
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                Item icopy = new Item(i);
                
                c.shoppingCart.add(icopy, Integer.parseInt(quant.getText()));
                
            }
                 
        });
        
        
        closewindow.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent E)
            {
                itemwindow.setVisible(false);
            }
            
        });
        
        Border detailborder = BorderFactory.createLineBorder(Color.BLUE, 2);
        
        JLabel name = new JLabel("Name: "+ i.getName());
        JLabel price = new JLabel("Price: $"+i.getSellPrice());
        JLabel genre = new JLabel("Genre: " + i.getDetails());
        JLabel avail = new JLabel("# available: "+ i.getQuantity());
        
        name.setBorder(detailborder);
        price.setBorder(detailborder);
        genre.setBorder(detailborder);
        avail.setBorder(detailborder);
        
        buttonbar.add(closewindow);
        buttonbar.add(add);
        buttonbar.add(quant);
        
        details.add(name);
        details.add(price);
        details.add(genre);
        details.add(avail);
        itemwindow.add(details);
        itemwindow.add(buttonbar);
        
        itemwindow.pack();
        itemwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        itemwindow.setVisible(true);
        
        
    }
    
    
    
    public static void ShoppingCartWindow(final Customer c)
    {
        final JFrame scwindow = new JFrame();
        scwindow.setLayout(new GridLayout(0,1));
        final JComboBox itemlist = new JComboBox();
        
        JPanel total = new JPanel();
        JPanel itemList = new JPanel();
        JPanel buttonbar = new JPanel();
        final JLabel totallabel = new JLabel("Total:     $"+c.shoppingCart.getCost());
      
        
        
        JButton remove = new JButton("Remove Item");
        JButton update = new JButton("Update #");
        JButton checkout = new JButton("Checkout");
        JButton keepshopping = new JButton("Keep Shopping");
        final JTextField quant = new JTextField(5);
        
        buttonbar.add(keepshopping);
        buttonbar.add(checkout);
        buttonbar.add(remove);
        buttonbar.add(update);
        buttonbar.add(quant);
        keepshopping.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                mainwindow(c);
                scwindow.setVisible(false);
            }
        });
        remove.addActionListener(new ActionListener(){
          
            public void actionPerformed(ActionEvent e)
            {
                c.shoppingCart.Remove((Item)itemlist.getSelectedItem());
                totallabel.setText("Total:    $"+c.shoppingCart.getCost());
                itemlist.removeItem((Item) itemlist.getSelectedItem());
            }
        });
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                c.shoppingCart.updatequant((Item) itemlist.getSelectedItem(), Integer.parseInt(quant.getText()));
                totallabel.setText("Total:    $"+c.shoppingCart.getCost());
            }
        });
        
        
        for(Item i: c.shoppingCart.SClist)
        {
            itemlist.addItem(i);
        }
        
        itemList.add(itemlist);
        total.add(totallabel);
        
        scwindow.add(total);
        scwindow.add(itemList);
        scwindow.add(buttonbar);
        
        
        
        
        
        
        scwindow.pack();
        scwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scwindow.setVisible(true);
        
        
    
    }
    
    public static void sellerAddItem(final Seller s, final JComboBox box)
    {
        final JFrame additemwindow = new JFrame();
        additemwindow.setLayout(new GridLayout(0,1));
        JPanel itemdeets = new JPanel();
        JPanel title = new JPanel();
        title.add(new JLabel("ADD AN ITEM"));
        itemdeets.setLayout(new GridLayout(0,2));
        JPanel buttonbar = new JPanel();
        JButton add = new JButton("Add");
        JButton closewindow = new JButton("Close");
        closewindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                additemwindow.setVisible(false);
            }
        });
        
        final JTextField namefield = new JTextField(10);
        final JTextField sellpricefield = new JTextField(10);
        final JTextField invoicepricefield = new JTextField(10);
        final JTextField quantityfield = new JTextField(10);
        final JTextField detailsfield = new JTextField(10);
        
        JLabel namelabel = new JLabel("Name");
        JLabel sellpricelabel = new JLabel("Sell Price");
        JLabel invoicepricelabel = new JLabel("Invoice price");
        JLabel quantitylabel = new JLabel("Quantity");
        JLabel detailslabel = new JLabel("Details/Genre");
        
        itemdeets.add(namelabel);
        itemdeets.add(namefield);
        itemdeets.add(sellpricelabel);
        itemdeets.add(sellpricefield);
        itemdeets.add(invoicepricelabel);
        itemdeets.add(invoicepricefield);
        itemdeets.add(quantitylabel);
        itemdeets.add(quantityfield);
        itemdeets.add(detailslabel);
        itemdeets.add(detailsfield);
        buttonbar.add(closewindow);
        buttonbar.add(add);
        additemwindow.add(title);
        additemwindow.add(itemdeets);
        additemwindow.add(buttonbar);
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                Item createditem = new Item(
                        Double.parseDouble(sellpricefield.getText()),
                        Double.parseDouble(invoicepricefield.getText()),
                        Integer.parseInt(quantityfield.getText()),
                        namefield.getText(),
                        detailsfield.getText());
                s.prodlist.Plist.add(createditem);
                additemwindow.setVisible(false);
                box.addItem(createditem);
                
                
               
            }
        });
        
        additemwindow.pack();
        additemwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        additemwindow.setVisible(true);
        
                
        
    }
    public static void sellerupdateitem(final Seller s,final Item i)
    {
        final JFrame updateitemwindow = new JFrame();
        updateitemwindow.setLayout(new GridLayout(0,1));
        JPanel itemdeets = new JPanel();
        itemdeets.setLayout(new GridLayout(0,2));
        JPanel title = new JPanel();
        JPanel buttonbar = new JPanel();
        JButton closewindow = new JButton("Close");
        JButton updatebutt = new JButton("Update Item");
        closewindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                updateitemwindow.setVisible(false);
            }
        });
        
        
        final JTextField namefield = new JTextField(i.getName(),10);
        final JTextField sellpricefield = new JTextField(Double.toString(i.getSellPrice()),10);
        final JTextField invoicepricefield = new JTextField(Double.toString(i.getInvoicePrice()),10);
        final JTextField quantityfield = new JTextField(Integer.toString(i.getQuantity()),10);
        final JTextField detailsfield = new JTextField(i.getDetails(),10);
        
        JLabel namelabel = new JLabel("Name");
        JLabel sellpricelabel = new JLabel("Sell Price");
        JLabel invoicepricelabel = new JLabel("Invoice price");
        JLabel quantitylabel = new JLabel("Quantity");
        JLabel detailslabel = new JLabel("Details/Genre");
        
        updatebutt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                i.updateItem(
                        Double.parseDouble(sellpricefield.getText()),
                        Double.parseDouble(invoicepricefield.getText()),
                        Integer.parseInt(quantityfield.getText()),
                        namefield.getText(),
                        detailsfield.getText()
                );
                updateitemwindow.setVisible(false);
            }
        });
        title.add(new JLabel("UPDATE AN ITEM"));
        itemdeets.add(namelabel);
        itemdeets.add(namefield);
        itemdeets.add(sellpricelabel);
        itemdeets.add(sellpricefield);
        itemdeets.add(invoicepricelabel);
        itemdeets.add(invoicepricefield);
        itemdeets.add(quantitylabel);
        itemdeets.add(quantityfield);
        itemdeets.add(detailslabel);
        itemdeets.add(detailsfield);
        
        buttonbar.add(closewindow);
        buttonbar.add(updatebutt);
        
        updateitemwindow.add(title);
        updateitemwindow.add(itemdeets);
        updateitemwindow.add(buttonbar);
        
        updateitemwindow.pack();
        updateitemwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateitemwindow.setVisible(true);
               
    }
    
    
    
    
    public static void main(String[] args)
    {
        
        //SellerList sellerDB = SellerList.getInstance();
        //CustomerList customerDB = CustomerList.getInstance();
        
        Customer blah;
        
        blah = loginwindow();
        
        
   
       
 
        
  
    }
    
    
    
    
    
    
    
}
