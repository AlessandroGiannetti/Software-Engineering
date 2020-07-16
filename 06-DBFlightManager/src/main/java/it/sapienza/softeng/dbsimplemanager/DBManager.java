/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.dbsimplemanager;

import java.sql.*;

public class DBManager {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("ARG0: Pass the path of the database");
            System.out.println("ARG1: Pass 'create' to initialize the database, 'run' to print the content of the database");
            System.exit(1);
        }

        Class.forName("org.sqlite.JDBC");
        Connection conn
                = DriverManager.getConnection("jdbc:sqlite:" + args[0]);
        Statement stat = conn.createStatement();

        if (args[1].equals("create")) {

            //drop table if exists
            stat.executeUpdate("drop table if exists flight;");
            stat.executeUpdate("drop table if exists passenger;");

            //Create tables
            String tableFlight = "CREATE TABLE flight("
                    + "id INTEGER PRIMARY KEY,"
                    + "name TEXT"
                    + ");";

            String tablePassenger = "CREATE TABLE passenger("
                    + "id INT PRIMARY KEY,"
                    + "name TEXT,"
                    + "flightID INT,"
                    + "FOREIGN KEY (flightID) REFERENCES directors(id)"
                    + ");";

            stat.executeUpdate(tableFlight);
            stat.executeUpdate(tablePassenger);

            PreparedStatement prep = conn.prepareStatement(
                    "insert into flight values (?, ?);");

            prep.setString(1, "1");
            prep.setString(2, "AZ140");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "2");
            prep.setString(2, "LH999");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "3");
            prep.setString(2, "FR123");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "4");
            prep.setString(2, "US666");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep = conn.prepareStatement(
                    "insert into passenger values (?, ?, ?);");

            prep.setString(1, "1");
            prep.setString(2, "Mimmo");
            prep.setString(3, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.setString(1, "2");
            prep.setString(2, "Peppe");
            prep.setString(3, "1");
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
        } else {
            ResultSet rs = stat.executeQuery("select * from flight;");
            while (rs.next()) {
                System.out.print("Flight = " + rs.getString("id") + " is : ");
                System.out.println(rs.getString("name"));
            }
            rs.close();
        }
        conn.close();
    }
}
