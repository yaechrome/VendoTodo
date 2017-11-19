/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author yaechrome
 */
public class Conexion {
    public static Connection getConexion() {

        Connection connection = null;
        try {
           String driverClassName = "com.mysql.jdbc.Driver";
           String driverUrl="jdbc:mysql://127.0.0.1/vendotodo";
           Class.forName(driverClassName);
           connection = DriverManager.getConnection(driverUrl, "root","");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
