package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Driver;
public class DatabaseConnection 
{
    public static void main(String[] args) {
        String url = "jdbc:mysql://database-bf.c16eyimu6m6g.eu-north-1.rds.amazonaws.com:3306/bilkentForum";
        String userName = "admin";
        String password = "admin321";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery("select * from users");

            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }

            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}