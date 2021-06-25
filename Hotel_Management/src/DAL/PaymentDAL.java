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
                payment.setBookingID(rs.getInt("bookingid"));
                payment.setPaymentTypeName(rs.getString("paymenttypename"));
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
            pres.setInt(2, payment.getBookingID());
            pres.setString(3, payment.getPaymentTypeName());
            java.util.Date utilDate = payment.getPaymentDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pres.setDate(4, sqlDate);
            pres.setBoolean(5, payment.isPaymentStatus());
            pres.setDouble(6, payment.getAmount());
            
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
