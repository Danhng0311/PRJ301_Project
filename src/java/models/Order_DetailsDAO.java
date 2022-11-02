/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Order_Detail;
import dal.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Anh
 */
public class Order_DetailsDAO extends DBContext {

    public ArrayList<Order_Detail> getOrder_DetailsByID(int cid) {
        ArrayList<Order_Detail> ordetail = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from [Order Details] WHERE OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            // Implement statement........
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                // Intinilizial object 
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                // Add p to arraylist
                ordetail.add(ordt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ordetail;
    }
    
    public ArrayList<Order_Detail> getAllOrder_Details() {
        ArrayList<Order_Detail> ordetail = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from [Order Details]";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            // Implement statement........
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                // Intinilizial object 
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                // Add p to arraylist
                ordetail.add(ordt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ordetail;
    }
    
    public ArrayList<Order_Detail> getWeeklySaleByOrdDet() {
        ArrayList<Order_Detail> ord = new ArrayList<>();
        LocalDate curD = java.time.LocalDate.now();
        String date = curD.toString();
        // Create : PrepareStatement
        try {
            String sql = "select od.OrderID, od.ProductID, od.UnitPrice, od.Quantity, od.Discount from [Order Details]  od\n"
                    + "join Orders o\n"
                    + "on o.OrderID = od.OrderID\n"
                    + "where ? - o.OrderDate < 7";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, date);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                // Intinilizial object 
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                // Add p to arraylist
                ord.add(ordt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ord;
    }
    
    public Order_Detail getOrder_DetailsByIDNotList(int cid) {
        

        // Create : PrepareStatement
        try {
            String sql = "Select * from [Order Details] WHERE OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            // Implement statement........
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                // Intinilizial object 
                Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                // Add p to arraylist
                return ordt;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Order_Detail> getOrder_DetailsByIDv2(ArrayList<Orders> listO) {
        ArrayList<Order_Detail> ordetail = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from [Order Details] WHERE OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

//            int c = 0;
//            while (listO.size() > c) {
//                for (int i = 0; i < listO.size(); i++) {
                    for (Orders order_Detail : listO) {
                        
                    
                    ps.setInt(1, order_Detail.getOrderID());
                    ResultSet rs = ps.executeQuery();

                    // Progress result returned
                    while (rs.next()) {
                        // Take data from 'rs' to biencucbo
                        int OrderID = rs.getInt("OrderID");
                        int ProductID = rs.getInt("ProductID");
                        double UnitPrice = rs.getDouble("UnitPrice");
                        int Quantity = rs.getInt("Quantity");
                        double Discount = rs.getDouble("Discount");
                        // Intinilizial object 
                        Order_Detail ordt = new Order_Detail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                        // Add p to arraylist
                        ordetail.add(ordt);
                    }
                }
//                c++;
//            }

            // Implement statement........
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ordetail;
    }

//    public static void main(String[] args) {
//        ArrayList<Order_Detail> a = new Order_DetailsDAO().getOrder_DetailsByID(10303);
//        for (Order_Detail i: a) {
//            System.out.println(i.getDiscount());
//        }
//    }
}
