/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.CustomerDAL;
import DTO.CustomerDTO;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class CustomerBLL {
    CustomerDAL customerDAL = new CustomerDAL();
    
    public ArrayList<CustomerDTO> getAllCustomer(){
        return customerDAL.getAllCustomer();
    }
    
    public int insertCustomer(CustomerDTO customer){
        int result = customerDAL.insertCustomer(customer);
        return result;
    }
}
