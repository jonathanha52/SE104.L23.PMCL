/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author huynh
 */
public class DBUtils {
    private Connection conn;
    
    public DBUtils(){};
    
    public Connection createConn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8888/qlks?userSSL=false","root","0917459476");
            
            if (conn== null)
                System.out.println("Kết nối không thành công");
            else
                System.out.println("Kết nối thành công");
        } catch (Exception e){
            e.printStackTrace();;
        }
        return conn;
    }
}
