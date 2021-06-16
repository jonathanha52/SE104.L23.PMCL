/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.CustomerDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class CustomerDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<CustomerDTO> getAllCustomer(){
        ArrayList<CustomerDTO> result = new ArrayList<CustomerDTO>();
        
        String sqlSelectAll = "select * from customer";
        
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlSelectAll);
            rs = pres.executeQuery();
            
            while(rs.next()){
                CustomerDTO customer = new CustomerDTO();
                customer.setCustomerID(Integer.parseInt(rs.getString("customerid")));
                customer.setCustomerName(rs.getString("customername"));
                
                result.add(customer);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                pres.close();
                rs.close();

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public int insertCustomer(CustomerDTO customer){
        int result = 0;
        String sqlInsert = "insert into customer values (?,?)";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlInsert);
            pres.setString(1, Integer.toString(customer.getCustomerID()));
            pres.setString(2, customer.getCustomerName());
            
            result = pres.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                pres.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
