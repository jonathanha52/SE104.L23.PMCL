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
public class PaymentDTO {
    private int paymentID, bookingID, serviceBillID, paymentTypeID;
    private java.util.Date paymentDate;
    private boolean paymentStatus;

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }

    public int getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setServiceBillID(int serviceBillID) {
        this.serviceBillID = serviceBillID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getServiceBillID() {
        return serviceBillID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public int getPaymentID() {
        return paymentID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }   
}
