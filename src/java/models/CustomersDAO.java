/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Account;
import dal.Customers;
import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Anh
 */
public class CustomersDAO extends DBContext {

    public void getCustomers(Customers cus) {
        try {
            String sql = "INSERT INTO Customers"
                    + "  (CustomerID, CompanyName, ContactName, ContactTitle, Address, Date) VALUES "
                    + " (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cus.getCustomerID());
            ps.setString(2, cus.getCompanyName());
            ps.setString(3, cus.getContactName());
            ps.setString(4, cus.getContactTitle());
            ps.setString(5, cus.getAddress());
            ps.setString(6, cus.getDate());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public int getCountAllCus() {
        // Create : PrepareStatement
        try {
            String sql = "Select count(*) from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public int getCountNewCus() {
        // Create : PrepareStatement
        LocalDate curD = java.time.LocalDate.now();
        String date = curD.toString();
        try {
            String sql = "select count(*) from Customers\n"
                    + "where ? - Date < 30";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, date);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public Customers getAllCustomers() {

        // Create : PrepareStatement
        try {
            String sql = "Select * from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Date = rs.getString("Date");
                String Address = rs.getString("Address");

                // Intinilizial object 
                Customers o = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, Date);
                // Add p to arraylist
                return o;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Customers> getAllCustomersList() {
        ArrayList<Customers> customers = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Date = rs.getString("Date");
                String Address = rs.getString("Address");

                // Intinilizial object 
                Customers o = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, Date);
                // Add p to arraylist
                customers.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return customers;
    }

    public ArrayList<Customers> getCustomersByIDList(String CustomerId) {
        ArrayList<Customers> cuss = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Customers where CustomerId=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, CustomerId);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo

                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Date = rs.getString("Date");
                String Address = rs.getString("Address");

                // Intinilizial object 
                Customers cus = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, Date);
                // Add p to arraylist
                cuss.add(cus);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cuss;
    }

    //tim 1 
    public Customers getCustomersByID(String CustomerId) {

        // Create : PrepareStatement
        try {
            String sql = "Select * from Customers where CustomerId=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, CustomerId);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo

                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Date = rs.getString("Date");
                String Address = rs.getString("Address");

                // Intinilizial object 
                Customers cus = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, Date);
                // Add p to arraylist
                return cus;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateCus(Customers cus) {
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + " WHERE CustomerID =?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cus.getCompanyName());
            ps.setString(2, cus.getContactName());
            ps.setString(3, cus.getContactTitle());
            ps.setString(4, cus.getAddress());
            ps.setString(5, cus.getCustomerID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Customers getCustomers() {

        // Create : PrepareStatement
        try {
            String sql = "Select * from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo

                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String Date = rs.getString("Date");

                // Intinilizial object 
                Customers cus = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, Date);
                // Add p to arraylist
                return cus;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Customers getCustomersCart(Customers cus) {
        Customers cust = null;

        // Create : PrepareStatement
        try {
            String sql = "Select * from Customers where CompanyName=? and ContactName=? and ContactTitle=? and Address=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, cus.getCompanyName());
            ps.setString(2, cus.getContactName());
            ps.setString(3, cus.getContactTitle());
            ps.setString(4, cus.getAddress());
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String Date = rs.getString("Date");
                cust = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, Date);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cust;
    }

//    public static void main(String[] args) {
//        Customers cD = new CustomersDAO().getCustomersByID("DLFMK");
//        String date = cD.getDate();
//        SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date6 = null;
//        try {
//             date6 = (Date) formatter6.parse(date);
//        } catch (Exception e) {
//        }
//        System.out.println(date);
//        System.out.println(date6);
//        
//
//    }
}
