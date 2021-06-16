/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.PaymentDTO;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class PaymentBLL {
    PaymentBLL paymentDAL = new PaymentBLL();
    
    public ArrayList<PaymentDTO> getAllPayment(){
        return paymentDAL.getAllPayment();
    }
    
    public int insertPayment(PaymentDTO payment){
        int result = paymentDAL.insertPayment(payment);
        return result;
    }
}
