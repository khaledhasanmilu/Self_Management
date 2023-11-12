package com.self.management.self_management;
import java.sql.*;
public class DB {

    public static Connection getConnection(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
            return con;
        }catch (Exception e){
            System.out.println("Can't connect to Database");
        }

        return null;
    }
}
