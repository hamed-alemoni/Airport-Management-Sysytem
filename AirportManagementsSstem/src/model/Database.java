package model;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Database {
    //here we load database and connect to it
    static String url = "jdbc:sqlserver://HappyDead:1433;databaseName=Airport Management System;user=sa;password=abh123";
    Manager manager = null;
    private boolean isManager = false;

    public Database(Object object) {

        if (object instanceof Manager) {
            manager = new Manager();
            this.manager = (Manager) object;
            isManager = true;
        }

    }


    private static Connection connection;

    {
        try {
            connection = getConnection(url);
        } catch (SQLException e) {
            System.out.println("we cannot connect to database" + " " + e.getMessage());
            e.printStackTrace();
        }
    }

    //this method get information from program and insert it to database and array list from specified Class
    public void setInformation() {
        String insertStr = "";
        if (isManager) {

            insertStr = "INSERT INTO Manager (FirstName,LastName,Username,Password,Email,PhoneNumber,Address,Salary) VALUES(?,?,?,?,?,?,?,?)";

            try (PreparedStatement pst = connection.prepareStatement(insertStr)) {
                //here we get information from program and insert it to database and array list from Manager Class
                pst.setString(1, this.manager.getName());
                pst.setString(2, this.manager.getLastName());
                pst.setString(3, this.manager.getUserName());
                pst.setString(4, this.manager.getPassword());
                pst.setString(5, this.manager.getEmail());
                pst.setString(6, this.manager.getPhoneNumber());
                pst.setString(7, this.manager.getAddress());
                pst.setString(8, this.manager.getSalary());
                int i = pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println("You cannot insert information into Manager table" + " " + e.getMessage());
            }
        }
    }

//    public static void getInformation() throws SQLException {
//        Connection connection1 = DriverManager.getConnection(url);
//        String manager = "";
//        Statement statement = connection1.createStatement();
//        String sql = "SELECT * FROM Manager";
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()){
//            manager = Integer.toString(resultSet.getInt(1)) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9);
//            String[] managerProperties = manager.split(" ");
//            Manager.managers.add(new Manager((managerProperties[0]), managerProperties[1], managerProperties[2], managerProperties[3], managerProperties[4], managerProperties[5], managerProperties[6], managerProperties[7], managerProperties[8]));
//
//        }
//    }

    //this method will remove a manager from database
    public static void removeInformation(String username){
        Connection connection1 = null;
        try {
            connection1 = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("it's happened in remove information method and connection has problem");
        }
        PreparedStatement statement = null;
        try {
             statement = connection1.prepareStatement("DELETE FROM Manager WHERE Username = ?");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("it's happened in remove information method and statement has problem");
        }
        try {
            statement.setString(1,username);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



   // here we get information from database and insert it to array list from Manager class
    public static void getInformation() {
        Manager.managers.clear();
        Connection connection1 = null;
        try {
             connection1 = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //for save information that insert into Manager table
            String manager = "";


            Statement s = null;
            try {
                s = connection1.createStatement();
            } catch (SQLException e) {
                System.out.println("we cannot create statement" + " " + e.getMessage());
            }


            String sql = "SELECT * FROM Manager";


            ResultSet resultSet = null;
            try {
                resultSet = s.executeQuery(sql);
            } catch (SQLException e) {
                System.out.println("we cannot execute query" + " " + e.getMessage());
            }


            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    System.out.println("this error is in while for get information from database" + " " + e.getMessage());
                }


                try {
                    manager = Integer.toString(resultSet.getInt(1)) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9);
                    //add information from program to array list from Manager class
                    String[] managerProperties = manager.split(" ");
                    Manager.managers.add(new Manager((managerProperties[0]), managerProperties[1], managerProperties[2], managerProperties[3], managerProperties[4], managerProperties[5], managerProperties[6], managerProperties[7], managerProperties[8]));

                } catch (SQLException e) {
                    System.out.println("we cannot get information from Manager table" + " " + e.getMessage());
                }
            }


    }


}
