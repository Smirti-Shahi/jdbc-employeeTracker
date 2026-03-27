package com.smirti.employeeTaskTracker.utilies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConfig {

    private static final String DB_Name ="employeeTask";
    private static final String URL="jdbc:mysql://localhost:3306/"+DB_Name;
    private static final String BASE_URL="jdbc:mysql://localhost:3306/";
    private static final String USERNAME ="root";
    private static final String PASSWORD="";

    public static Connection getdbConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void initializedDatabase(){

        try(Connection conn = DriverManager.getConnection(BASE_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement()){
            stmt.executeUpdate("CREATE  DATABSE IF NOT EXISTS "+DB_Name);
            stmt.execute("USE "+DB_Name);
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employee(" +
                    "id int AUTO_INCREMENT PRIMARY KEY, "+
                    "name VARCHAR(100) NOT NULL,"+
                    "department VARCHAR(100) NOT NULL"+" )");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS task("+
                    "id int AUTO_INCREMENT PRIMARY KEY,"+
                    "employeeId int,"+
                    "task_name varchar(100) not null,"+
                    "status varchar(100) not null,"+
                    "FOREIGN KEY (employeeId) REFERENCES employpee(id)"+")");

        } catch (Exception e) {
            System.out.println("DB error: "+ e.getMessage());


        }
    }

    public static void closeConnection(Connection con) {
        // Write your code here

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
