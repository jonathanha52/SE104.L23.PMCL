/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DepositDAL;
import DTO.DepositDTO;
import java.util.ArrayList;

/**
 *
 * @author huynh
 */
public class DepositBLL {
    DepositDAL depositDAL = new DepositDAL();
    
    public ArrayList<DepositDTO> getAllDeposit(){
        return depositDAL.getAllDeposit();
    }
    
    public int insertDeposith(DepositDTO deposit){
        int result = depositDAL.insertDeposit(deposit);
        return result;
    }
}
