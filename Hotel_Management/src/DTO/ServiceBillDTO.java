/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author huynh
 */
public class ServiceBillDTO {
    private int serviceBillID, bookingID, staffID, serviceID, quantity;
    private java.util.Date serviceDate;

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public int getStaffID() {
        return staffID;
    }
    
    public void setServiceBillID(int serviceBillID) {
        this.serviceBillID = serviceBillID;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getServiceBillID() {
        return serviceBillID;
    }

    public Date getServiceDate() {
        return serviceDate;
    }
}
