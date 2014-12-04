/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hai
 */

import java.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class Database
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   static final String database = "warehouse";

   static Connection conn = null;
   static Statement stmt = null;

   //this method connect java application to database.
   public static void connection()
   {
       try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL+database, USER, PASS);


   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();

   }//end try
   }//end connection

   //this method handle user/admin log in.
   public static String login(String name, String pass)
   {
       String username = "'" + name + "'";
       String userpass = "'" + pass + "'";
       String result = "";
        try {
            connection();
            String myStament = "SELECT role "
                    + "FROM USERTABLE"
                    + " WHERE username = " + username + " AND password = " + userpass;
            stmt = conn.createStatement();
            stmt.execute(myStament);
            ResultSet rs = stmt.getResultSet();
            rs.first();
            result = rs.getString("role");
            }
        catch (SQLException ex) {

            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadProg();
       return result;
   }

   //this method add new customer to database.
   public static boolean addCustomer(ArrayList infor)
   {
        try {
            connection();
            String myStament = "INSERT INTO usertable (userName,password,firstName,lastName,phone,address,role)"
                    + " Values ("
                    + infor.get(0).toString() +"," + infor.get(1).toString() +"," + infor.get(2).toString()+","
                    + infor.get(3).toString() +"," + infor.get(4).toString() +"," + infor.get(5).toString()+","
                    + infor.get(6).toString()+")";
      
            stmt = conn.createStatement();
            stmt.execute(myStament);

            if(stmt== null)
                 return false;
             return true;
        }

        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
   }//and of addCustomer.

   //this method add new item to ventory.
   public static boolean addItem(ArrayList item)
   {
        try {
            connection();
        
             String set = "SET @itemName = '"+item.get(2)+"';";
             String myCategoryId = "SET @myIdOut = 0;";
             String callGetCategoryId = "Call getCategoryId(@itemName,@myIdOut);";
             String set1 = "SELECT @myIdOut";
             stmt.execute(set);
             stmt.execute(myCategoryId);
             stmt.execute(callGetCategoryId);
             stmt.execute(set1);
             ResultSet rs = stmt.getResultSet();
            rs.next();
            //check if category is already in database, if not then insert.
             
            System.out.print(rs.getString("@myIdOut"));
            if(rs.wasNull())
            {
                System.out.print("here");
                String myStatement0 = "INSERT INTO categorytable (categoryName) Values ('"+item.get(2)+"');";
                stmt.execute(myStatement0);
                stmt.execute(callGetCategoryId);
                       stmt.execute(set);
             stmt.execute(myCategoryId);
             stmt.execute(callGetCategoryId);
             stmt.execute(set1);
              ResultSet rs1 = stmt.getResultSet();
            rs1.next();
            //check if category is already in database, if not then insert.
            System.out.print("SS" + rs1.getString("@myIdOut"));
            }
              
            String myStament = "INSERT INTO inventory (productname,description,categoryid,quantity,price)"
                    + " Values ("
                    + item.get(0).toString() +"," +  item.get(1).toString() +",@myIdOut,"
                    + Integer.parseInt(item.get(3).toString()) +"," + Double.parseDouble(item.get(4).toString()) +")";
            System.out.print(myStament);
  
            
 
            stmt.execute(myStament);

            if(stmt== null)
                 return false;
             return true;
        }

        catch (SQLException ex) {

            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
   }

   //this method search for customer.
   public static Customer searchCustomer(int myId,Customer myCustomer)
   {
        try {
            connection();
            String myStament = "SELECT customerid,userName,password,firstName,lastName,phone,address,role "
                    + "FROM usertable "
                    + "WHERE customerid = " + myId ;

            stmt = conn.createStatement();
            stmt.execute(myStament);

            ResultSet rs = stmt.getResultSet();
            if(!rs.first())
            {
                myCustomer= null;
            }
            else{
                rs.beforeFirst();
            while(rs.next())
            {
                myCustomer.customerId = rs.getInt("customerid");
                myCustomer.userName = rs.getString("userName");
                myCustomer.password = rs.getString("password");
                myCustomer.firstName = rs.getString("firstName");
                myCustomer.lastName = rs.getString("lastName");
                myCustomer.phone = rs.getString("phone");
                myCustomer.address = rs.getString("address");
                myCustomer.role = rs.getString("role");
            }
            }
        }

        catch (SQLException ex) {

            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myCustomer;
   }//end of searchCustomer method.

   //Updates existing customer using myCustomer's information.
   public static boolean updateCustomer(int id,Customer myCustomer)
   {
       boolean success = false;
       try {
            connection();
            String myStament = "UPDATE usertable "
                    + "SET userName = '"+myCustomer.userName+"', " + "password = '"+myCustomer.password+"', "
                            +"firstName ='"+myCustomer.firstName+"', " + "lastName ='" + myCustomer.lastName+ "', "
                            + "phone ='" + myCustomer.phone+"', "+ "address = '"+myCustomer.address+"', "+ "role ='"+myCustomer.role+"'"
                    + "WHERE customerid = " + id ;

            stmt = conn.createStatement();
            stmt.execute(myStament);

            if(stmt.getUpdateCount() >0)
            {
                success = true;
            }
        }

        catch (SQLException ex) {

            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
   }

   //this method delete item from inventory using item id.
   public static boolean deleteItem(int id)
   {
       boolean sucess = false;
         try {
            connection();
            String myStament = "DELETE "+
                        "FROM inventory "
                    + "WHERE productid = " + id;
            stmt = conn.createStatement();
            stmt.execute(myStament);

            //check rows affected
            if(stmt.getUpdateCount() > 0)
            {
                sucess =  true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sucess;
   } //end of deleteItem method.

   //handles quantity update by admin.
   public static boolean updateQuantity(int id, int quantity)
   {
       boolean sucess = false;
         try {
            connection();
            String myStament = "UPDATE inventory "+
                        " SET quantity = "+quantity
                    + " WHERE productid = " + id;
            stmt = conn.createStatement();
            stmt.execute(myStament);

            //check rows affected
            if(stmt.getUpdateCount() > 0)
            {
                sucess =  true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sucess;
   } //end of updateQuantity

 
          
   //this method delete customer from customer table using id.
   public static boolean deleteCustomer(int id)
   {
       boolean sucess = false;
         try {
            connection();
            String myStament = "DELETE "+
                        " FROM usertable "
                    + " WHERE customerid = " + id
                    + " AND role <> 'Admin'";
            stmt = conn.createStatement();
            stmt.execute(myStament);

           
            //check rows affected
            if(stmt.getUpdateCount() >0)
            {
                sucess =  true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sucess;
   }//end of deletecustomer method.

   //populates data to table and return
   public static JTable displayTable(JTable myTable, String option)
   {
     try
    {
        connection();
        Statement stmt = conn.createStatement();
     String myStatement = option;
     //String myStatement = "SELECT productid, productname, description, categoryid, quantity, price FROM inventory";
    
          ResultSet rs = stmt.executeQuery(myStatement);

        //To remove previously added rows
        while(myTable.getRowCount() > 0)
        {
            ((DefaultTableModel) myTable.getModel()).removeRow(0);
        }
        int numColumns = rs.getMetaData().getColumnCount();
        while(rs.next())
        {
            Object[] row = new Object[numColumns];
            for (int i = 1; i <= numColumns; i++)
            {
                row[i - 1] = rs.getObject(i);
            }
            ((DefaultTableModel) myTable.getModel()).insertRow(rs.getRow()-1,row);
        }

       }
      catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
   }
     return myTable;
   }//end of displayTable

   //handles customer check out, update inventory table.
   public static boolean checkOut(String currentName,String itemName,int quantityBought)
   {
       boolean success = false;
         try {
            connection();
            String myStament = "UPDATE inventory "+
                        " SET inventory.quantity = inventory.quantity - " +quantityBought
                    + " WHERE inventory.productname = '" + itemName +"'"
                    ;
          
            stmt = conn.createStatement();
            stmt.execute(myStament);
               if(!(stmt.getUpdateCount() > 0))
            {
                return success;
            }
               String set = "SET @customerName = '"+currentName +"'";
               stmt.execute(set);
               String out = "SET @mycustomerID = 0";
               stmt.execute(out);
               String call = "CALL getCustomerId(@customerName,@mycustomerID);";
               stmt.execute(call);

               //insert into transaction table.
            String secondStatement = "INSERT INTO transaction (customerId) Values (@mycustomerID);";
            stmt.execute(secondStatement);
            

             if(!(stmt.getUpdateCount() > 0))
            {
                return success;
            }
            //insert into sales record
                 String setName = "SET @itemName = '"+itemName+"';";
               String setoutId = "Set @itemId = 0;";
               String setTransactionId = "SET @mytransactionID = (select MAX(transactionId) from transaction where transaction.customerId = @mycustomerID);";
               String callGetIdMethod = "CALL getItemId(@itemName,@itemId);";
               String getPrice = "Set @itemPrice = (SELECT price FROM inventory WHERE inventory.productid = @itemId);";
            String thirdStatement = "INSERT INTO salerecord (transactionId,productId,quantity,price) Values(@mytransactionID,@itemId,"+quantityBought+",@itemPrice);";
            stmt.execute(setName);
            stmt.execute(setoutId);
            stmt.execute(setTransactionId);
            stmt.execute(callGetIdMethod);
            stmt.execute(getPrice);
            stmt.execute(thirdStatement);
              if(!(stmt.getUpdateCount() > 0))
            {
                return success;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         //check if 3 tables has been updated.
        success =true;
        return success;
   }//end of checkout

   //loads mysql procedures/trigger.
   public static void loadProg()
   {
       String getcustomerIdMethod0 = "DROP PROCEDURE IF EXISTS getCustomerId;";
       String getcustomerIdMethod = " CREATE PROCEDURE getCustomerId (IN myUserName VARCHAR(50),OUT customerIdOUT INT(4)) BEGIN  SELECT customerId INTO customerIdOUT  FROM usertable  WHERE usertable.username = myUserName;  END  ";
       String getItemIdMethod0 = "DROP PROCEDURE IF EXISTS getItemId;";
       String getItemIdMethod1 = "CREATE PROCEDURE getItemId(IN itemName VARCHAR(50), OUT itemID INT(10))\n" +
"BEGIN\n" +
"SELECT productid INTO itemID \n" +
"FROM inventory \n" +
"WHERE inventory.productname = itemName;\n" +
"END";
       
       String checkReturnTrigger0 = "DROP TRIGGER IF EXISTS checkreturn;";
       String checkReturnTrigger1 = "CREATE TRIGGER checkReturn BEFORE INSERT ON returntable\n" +
"FOR EACH ROW\n" +
"BEGIN\n" +
"DECLARE myError INTEGER;\n" +
"IF EXISTS \n" +
"(SELECT * FROM salerecord WHERE salerecord.transactionId = new.transactionId AND salerecord.productId = new.productId AND salerecord.quantity >= new.quantity)\n" +
"THEN \n" + 
"UPDATE salerecord\n" +
"SET salerecord.quantity = salerecord.quantity - new.quantity WHERE salerecord.productId = new.productId;\n"+
"UPDATE inventory\n" +
"SET inventory.quantity = inventory.quantity + new.quantity WHERE inventory.productid = new.productId;\n" +
"ELSE\n" +
"UPDATE insert_failed SET xzd = 0;\n" +
"END IF;\n" +
"END";
        String getCategoryId0 = "DROP PROCEDURE IF EXISTS getCategoryId;";
        String getCategoryId1 = " CREATE PROCEDURE getCategoryId (IN itemName VARCHAR(50),OUT myCategoryId INT(4)) \n" +
"BEGIN\n" +
"SELECT categoryId INTO myCategoryId \n" +
"FROM categorytable\n" +
"WHERE categorytable.categoryName = itemName;\n" +
"END  ";
       
        try {
            connection();
            stmt = conn.createStatement();
            stmt.execute(getcustomerIdMethod0);
            stmt.execute(getcustomerIdMethod);
            stmt.execute(getItemIdMethod0);
            stmt.execute(getItemIdMethod1);
            stmt.execute(checkReturnTrigger0);
            stmt.execute(checkReturnTrigger1);
            stmt.execute(getCategoryId0);
            stmt.execute(getCategoryId1);
        }
         catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
   } //end of method
   
   //handles customer's returns.
   public static boolean addReturnRecord(int transactionId,String userName,String productName, int quantityReturn)
   {
       boolean success = true;
         try {
            connection();
            String getProductID = "SET @procID = (SELECT productId FROM inventory WHERE inventory.productname= '"+productName+"');";
            String myStatement = "INSERT INTO returntable (transactionId,productId,quantity) Values("+transactionId+",@procId,"+quantityReturn+");";
                    
             
            stmt = conn.createStatement();
            stmt.execute(getProductID);
            stmt.execute(myStatement);
 
         }
        catch (SQLException ex) {
            success = false;
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            return success;       
   }//end of method.
   
   //returns customerid. 
   public static int getCustomerId(String userName)
   {
       int result = -99;
        try {
            connection();
            stmt = conn.createStatement();
       String set = "SET @customerName = '"+userName +"'";
               stmt.execute(set);
               String out = "SET @mycustomerID = -99";
               stmt.execute(out);
               String call = "CALL getCustomerId(@customerName,@mycustomerID);";
               stmt.execute(call);
               String mySelect = "SELECT @mycustomerID;";
               stmt.execute(mySelect);
               ResultSet rs = stmt.getResultSet();
               if(rs.next())
                result = rs.getInt("@mycustomerID");           
             }
        
         
        catch (SQLException ex) {
     
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
           return result;
   }//end of method.
   
   //handles archive fucnction
   public static boolean archieve(Date abc)
   {
       boolean sucess = true;
       try {
            connection();
            PreparedStatement myStatement = null;
            Timestamp timestamp = new Timestamp(abc.getTime());
            Timestamp currentTime = new Timestamp(new Date().getTime());
           
            ArrayList<String> myTables = new ArrayList<String>();
            myTables.add("usertable");
            myTables.add("transaction");
            myTables.add("categorytable");
            myTables.add("salerecord");
            myTables.add("inventory");
            myTables.add(("salerecord"));
            ArrayList<String> myBackUpTables = new ArrayList<String>();
            myBackUpTables.add("usertable_backup");
            myBackUpTables.add("transaction_backup");
            myBackUpTables.add("categorytable_backup");
            myBackUpTables.add("salerecord_backup");
            myBackUpTables.add("inventory_backup");
            myBackUpTables.add(("salerecord_backup"));
            
            for(int i = 0;i < myTables.size(); i++)
            {
                myStatement = conn.prepareStatement("INSERT INTO "+myBackUpTables.get(i)+" SELECT * FROM "+myTables.get(i)
                        +" WHERE updatedAt <= ?");
                myStatement.setTimestamp(1, timestamp);
                myStatement.execute();
                myStatement.execute("UPDATE "+myBackUpTables.get(i) +" SET updatedAt =now();");
                myStatement.execute();
            }
            
       }
       catch (SQLException ex) {
            sucess = false;
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       return sucess;
   }
   
}


