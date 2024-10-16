package com.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static void main(String[] args){
        Connection connection = null;

        try {
            String url = "jdbc:sqlite:/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db";
            connection = DriverManager.getConnection(url);

            if (connection != null) {
                System.out.println("Veritabanına başarıyla bağlandınız.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
