/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.PaymentDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class PaymentDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<PaymentDTO> getAllPayment(){
        ArrayList<PaymentDTO> result = new ArrayList<PaymentDTO>();
        
        String sqlSelectAll = "select * from payment";
        
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlSelectAll);
            rs = pres.executeQuery();
            
            while(rs.next()){
                PaymentDTO payment = new PaymentDTO();
                payment.setPaymentID(rs.getInt("paymentid"));
                payment.setServiceBillID(rs.getInt("servicebillid"));
                payment.setBookingID(rs.getInt("bookingid"));
                payment.setPaymentTypeID(rs.getInt("paymenttypeid"));
                payment.setPaymentDate(rs.getDate("paymentdate"));
                payment.setPaymentStatus(rs.getBoolean("paymentstatus"));
                
                result.add(payment);
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
    
    public int insertPayment(PaymentDTO payment){
        int result = 0;
        String sqlInsert = "insert into payment values (?,?,?,?,?,?)";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlInsert);
            pres.setInt(1, payment.getPaymentID());
            pres.setInt(2, payment.getServiceBillID());
            pres.setInt(3, payment.getBookingID());
            pres.setInt(4, payment.getPaymentTypeID());
            java.util.Date utilDate = payment.getPaymentDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pres.setDate(5, sqlDate);
            pres.setBoolean(6, payment.isPaymentStatus());
            
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
