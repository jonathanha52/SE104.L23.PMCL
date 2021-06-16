/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ServiceBillDAL;
import DTO.ServiceBillDTO;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class ServiceBillBLL {
    ServiceBillDAL serviceBillDAL = new ServiceBillDAL();
    
    public ArrayList<ServiceBillDTO> getAllServiceBill(){
        return serviceBillDAL.getAllServiceBill();
    }
    
    public int insertServiceBillh(ServiceBillDTO serviceBill){
        int result = serviceBillDAL.insertServiceBill(serviceBill);
        return result;
    }
}
