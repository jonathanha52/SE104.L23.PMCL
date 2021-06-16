/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ServiceBillDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class ServiceBillDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<ServiceBillDTO> getAllServiceBill(){
        ArrayList<ServiceBillDTO> result = new ArrayList<ServiceBillDTO>();
        
        String sqlSelectAll = "select * from servicebill";
        
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlSelectAll);
            rs = pres.executeQuery();
            
            while(rs.next()){
                ServiceBillDTO serviceBill = new ServiceBillDTO();
                serviceBill.setServiceBillID(rs.getInt("servicebillid"));
                serviceBill.setRoomID(rs.getInt("roomid"));
                serviceBill.setStaffID(rs.getInt("staffid"));
                serviceBill.setServiceDate(rs.getDate("servicedate"));
                serviceBill.setServiceID(rs.getInt("serviceid"));
                serviceBill.setQuantity(rs.getInt("quantity"));
                
                result.add(serviceBill);
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
    
    public int insertServiceBill(ServiceBillDTO serviceBill){
        int result = 0;
        String sqlInsert = "insert into servicebill values (?,?,?,?,?,?)";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlInsert);
            pres.setInt(1, serviceBill.getServiceBillID());
            pres.setInt(2, serviceBill.getRoomID());
            pres.setInt(3, serviceBill.getStaffID());
            pres.setInt(4, serviceBill.getServiceID());
            java.util.Date utilDate = serviceBill.getServiceDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pres.setDate(5, sqlDate);
            pres.setInt(6, serviceBill.getQuantity());
            
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
