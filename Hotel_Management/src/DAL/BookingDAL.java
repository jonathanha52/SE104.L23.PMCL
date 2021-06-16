/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.BookingDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class BookingDAL {
    private DBUtils dbu = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;
    
    public ArrayList<BookingDTO> getAllBooking(){
        ArrayList<BookingDTO> result = new ArrayList<BookingDTO>();
        
        String sqlSelectAll = "select * from booking";
        
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlSelectAll);
            rs = pres.executeQuery();
            
            while(rs.next()){
                BookingDTO booking = new BookingDTO();
                booking.setBookingID(Integer.parseInt(rs.getString("bookingid")));
                booking.setCustomerID(Integer.parseInt(rs.getString("customerid")));
                booking.setRoomID(Integer.parseInt(rs.getString("roomid")));
                booking.setStartDate(rs.getDate("startdate"));
                booking.setEndDate(rs.getDate("enddate"));
                
                result.add(booking);
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
    
    public int insertBooking(BookingDTO booking){
        int result = 0;
        String sqlInsert = "insert into booking values (?,?,?,?,?)";
        try{
            dbu = new DBUtils();
            conn = dbu.createConn();
            pres = conn.prepareStatement(sqlInsert);
            pres.setString(1, Integer.toString(booking.getBookingID()));
            pres.setString(2, Integer.toString(booking.getRoomID()));
            pres.setString(3, Integer.toString(booking.getCustomerID()));
            java.util.Date utilDate = booking.getStartDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pres.setDate(4, sqlDate);
            utilDate = booking.getEndDate();
            sqlDate = new java.sql.Date(utilDate.getTime());
            pres.setDate(5, sqlDate);
            
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
