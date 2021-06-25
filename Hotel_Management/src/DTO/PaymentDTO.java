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
    private int paymentID, bookingID;
    private double amount;
    private String paymentTypeName;
    private java.util.Date paymentDate;
    private boolean paymentStatus;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getBookingID() {
        return bookingID;
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
