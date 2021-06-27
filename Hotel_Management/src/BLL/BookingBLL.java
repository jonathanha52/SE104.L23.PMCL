/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.BookingDAL;
import DTO.BookingDTO;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class BookingBLL {
    BookingDAL bookingDAL = new BookingDAL();
    
    public ArrayList<BookingDTO> getAllBooking(){
        return bookingDAL.getAllBooking();
    }
    
    public int insertBooking(BookingDTO booking){
        int result = bookingDAL.insertBooking(booking);
        return result;
    }
    public int deleteBooking(BookingDTO booking){
        int result = bookingDAL.deleteBooking(booking);
        return result;
    }
}
