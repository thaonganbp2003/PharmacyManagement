/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.sql.*;

public class DBUtils {
   
    public DBUtils(){};
    public Connection createConn(){
        Connection conn = null;
        try 
        {
            //Step1: load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Step2: create  the connection object
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "c##seminar";
            String password = "number123456";
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null)
                    System.out.println("Kết nối thành công");
            else
                    System.out.println("Kết nối không thành công");
        } 
        catch(Exception e){
                e.printStackTrace();
              
        }
        return conn;    
        }
 
}



