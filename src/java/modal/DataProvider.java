package modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DataProvider {
    public static Connection Connect() {
         try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "exam";//ten csdl ma ban can ket noi
            Properties info = new Properties();
            info.setProperty("characterEncoding", "utf8");
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            Connection conn = DriverManager.getConnection(url + dbName, info);
          
            return conn;
        } catch (Exception ex) {
            return null;
        }
    }
}
