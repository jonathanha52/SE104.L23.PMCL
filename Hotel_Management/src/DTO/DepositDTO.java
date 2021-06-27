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
public class DepositDTO {
    private int depositID, bookingID, customerID;
    private java.util.Date depositDate;
    private String  explanation;
    private double depositAmount;
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }
    
    public DepositDTO(){}

    
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getBookingID() {
        return bookingID;
    }
    public void setDepositID(int depositID) {
        this.depositID = depositID;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }


    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getDepositID() {
        return depositID;
    }

    public Date getDepositDate() {
        return depositDate;
    }
    
    public String getExplanation() {
        return explanation;
    }

    public double getDepositAmount() {
        return depositAmount;
    }
}
