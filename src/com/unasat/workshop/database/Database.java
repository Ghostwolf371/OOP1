package com.unasat.workshop.database;

import  java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/oop1";
        String username = "root";
        String password = "";


        try {
            // Step 1: Load the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a Connection
            Connection conn = DriverManager.getConnection(url, username, password);

            // Step 3: Create a PreparedStatement
            String sql = "INSERT INTO student (age, first_name, last_name) VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            System.out.println("How old are you: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("What is your first name: ");
            String first_name = scanner.nextLine();
            System.out.println("What is your last name: ");
            String last_name = scanner.nextLine();


            // Step 4: Set Parameters
            pstmt.setInt(1, age);
            pstmt.setString(2, first_name);
            pstmt.setString(3,last_name);

            // Step 5: Execute the Query
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected + " row inserted.");

            // Step 6: Close the Connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading the JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }
}
