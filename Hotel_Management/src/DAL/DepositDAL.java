/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DepositDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class DepositDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<DepositDTO> getAllDeposit(){
        ArrayList<DepositDTO> result = new ArrayList<DepositDTO>();
        
        String sqlSelectAll = "select * from deposit";
        
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlSelectAll);
            rs = pres.executeQuery();
            
            while(rs.next()){
                DepositDTO deposit = new DepositDTO();
                deposit.setDepositID(Integer.parseInt(rs.getString("depositid")));
                deposit.setBookingID(Integer.parseInt(rs.getString("bookingid")));
                deposit.setCustomerID(Integer.parseInt(rs.getString("customerid")));
                deposit.setDepositAmount(rs.getInt("depositamount"));
                deposit.setDepositDate(rs.getDate("depositdate"));
                deposit.setExplanation(rs.getString("explanation"));
                result.add(deposit);
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
    
    public int insertDeposit(DepositDTO deposit){
        int result = 0;
        String sqlInsert = "insert into deposit values (?,?,?,?,?,?)";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlInsert);
            pres.setString(1, Integer.toString(deposit.getDepositID()));
            pres.setString(2, Integer.toString(deposit.getBookingID()));
            pres.setString(3, Integer.toString(deposit.getCustomerID()));
            java.util.Date utilDate = deposit.getDepositDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pres.setDate(4, sqlDate);
            pres.setDouble(5, deposit.getDepositAmount());
            pres.setString(6, deposit.getExplanation());
            
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
