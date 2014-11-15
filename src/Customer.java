/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hai
 */
//Stores customer information.
   public class  Customer
{
    int customerId;
    String userName,password,firstName,lastName,address,role,phone;
    
    public Customer()
    {       
    }
    
       public Customer(int customerId, String userName, String password, String firstName, String lastName, String phone, String address, String role)
    {
        this.customerId = customerId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }  
}//end of  Customer class.
       
