/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Account;
import dal.DBContext;
import jakarta.websocket.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Anh
 */
public class AccountDAO extends DBContext {

    public ArrayList<Account> getAlllAccount() {
        ArrayList<Account> accList = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Accounts";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                // Intinilizial object 
                Account acc = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                // Add p to arraylist
                accList.add(acc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return accList;
    }
    
    public int getCountOfAllAccount() {
        

        // Create : PrepareStatement
        try {
            String sql = "Select count(*) from Accounts";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
               return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public Account getAccount(String email, String password) {
        Account accounts = null;

        // Create : PrepareStatement
        try {
            String sql = "Select * from Accounts where Email=? and Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, email);
            ps.setString(2, password);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                // Intinilizial object 
                accounts = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                // Add p to arraylist

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return accounts;
    }
    
    public Account getAccountByEmail(String email) {
        Account accounts = null;

        // Create : PrepareStatement
        try {
            String sql = "Select * from Accounts where Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, email);
//            ps.setString(2, password);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                // Intinilizial object 
                accounts = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                // Add p to arraylist

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return accounts;
    }
    
    public Account getNewPassByEmail(String email, String password) {
        Account accounts = null;

        // Create : PrepareStatement
        try {
            String sql = "UPDATE [dbo].[Accounts]\n"
                + "   SET [Password] = ?\n"
                + "\n"
                + " WHERE Email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, password);
            ps.setString(2, email);
//            ps.setString(2, password);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                // Intinilizial object 
                accounts = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
                // Add p to arraylist

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return accounts;
    }

    public void updateAcc(Account acc) {
        String sql = "UPDATE [dbo].[Accounts]\n"
                + "   SET [Email] = ?\n"
                + "\n"
                + " WHERE CustomerID = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, acc.getEmail());
            ps.setString(2, acc.getCustomerID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAccounts(Account acc) {
        int rs = 0;

        try {
            String sql = "INSERT INTO Accounts"
                    + "  (Email, Password, CustomerID) VALUES "
                    + " (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getEmail());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getCustomerID());
            ps.executeUpdate();

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        System.out.println(new AccountDAO().getAccount("cust1@gmail.com", "123"));

    }
}
