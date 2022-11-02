/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Customers;
import dal.DBContext;
import dal.Employee;
import dal.ItemAddToCart;
import dal.Orders;
import dal.ShoppingCart;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Anh
 */
public class OrdersDAO extends DBContext {

    public void addOrder(Customers cus, ShoppingCart sc, Orders o) {
        LocalDate curD = java.time.LocalDate.now();
        String date = curD.toString();
        try {

            String sql = "Insert into [Orders] (CustomerID, OrderDate, RequiredDate, Freight) values(?,?,?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(2, date);
            ps.setString(1, cus.getCustomerID());
            ps.setString(3, o.getRequiredDate());
            ps.setDouble(4, 0);
            ps.executeUpdate();

            String sql2 = "select top 1 OrderID from [Orders] order by OrderID desc";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt(1);
                for (ItemAddToCart i : sc.getItems()) {
                    String sql3 = "Insert into [Order Details] (OrderID, ProductID, UnitPrice, Quantity, Discount) values (?, ?, ?, ?, ?)";

                    PreparedStatement ps3 = connection.prepareStatement(sql3);
                    ps3.setInt(1, OrderID);
                    ps3.setInt(2, i.getProduct().getProductID());
                    ps3.setInt(4, i.getQuantity());
                    ps3.setDouble(3, sc.getTotal());
                    ps3.setDouble(5, 0);
                    ps3.executeUpdate();
                    sc.removeItem(OrderID);
                }
            }
        } catch (Exception e) {
        }
    }

    public Orders getOrdersByID(String CustomerId) {

        // Create : PrepareStatement
        try {
            String sql = "Select * from Orders where CustomerId=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, CustomerId);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders ord = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                return ord;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Orders getOrdersByOrderID(int ord) {

        // Create : PrepareStatement
        try {
            String sql = "Select * from Orders where OrderID=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setInt(1, ord);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders ords = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                return ords;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Orders> getOrdersByIDList2(int CustomerId) {
        ArrayList<Orders> ord = new ArrayList<>();
        // Create : PrepareStatement
        try {
            String sql = "Select * from Orders where OrderID=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setInt(1, CustomerId);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders ordd = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                ord.add(ordd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ord;
    }
    
    public ArrayList<Integer> getOrdersByYears() {
        ArrayList<Integer> it = new ArrayList<>();
        // Create : PrepareStatement
        try {
            String sql = "select DISTINCT year(o.OrderDate) from Orders o";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                it.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return it;
    }

    public ArrayList<Orders> getOrdersByDateFromTo(String dateF, String dateT) {
        ArrayList<Orders> ord = new ArrayList<>();
        // Create : PrepareStatement
        try {
            String sql = "select * from Orders\n"
                    + "where OrderDate between ? and ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, dateF);
            ps.setString(2, dateT);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders ordd = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                ord.add(ordd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ord;
    }

    public ArrayList<Orders> getOrdersByIDList(String CustomerId) {
        ArrayList<Orders> ord = new ArrayList<>();
        // Create : PrepareStatement
        try {
            String sql = "Select * from Orders where CustomerID=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, CustomerId);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders ordd = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                ord.add(ordd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return ord;
    }

    public void updateRequiredDateOrders(int oID) {
        String sql = "update Orders\n"
                + "set RequiredDate = null\n"
                + "where OrderID = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, oID);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Employee getEmployeeByOrdID(int EmpID) {
        try {
            String sql = "Select * from Employees where EmployeeID=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setInt(1, EmpID);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo
                int EmployeeID = rs.getInt("EmployeeID");

                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                int DepartmentID = rs.getInt("DepartmentID");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                String Address = rs.getString("Address");

                Employee e = new Employee(EmployeeID, LastName, FirstName, DepartmentID, Title, TitleOfCourtesy, BirthDate, HireDate, Address);
                return e;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Orders> getAllOrders() {
        ArrayList<Orders> Orders = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Orders";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                Orders.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return Orders;
    }

    public int countOrderByMonth(int month, int year) {
        try {
            String sql = "SELECT count(*) from Orders \n"
                    + "where MONTH(OrderDate) = ? and year(OrderDate) = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public int countFilterOrderDList(String dateF, String dateT) {
        try {
            String sql = "select count(*) from Orders\n"
                    + "where OrderDate between ? and ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, dateF);
            ps.setString(2, dateT);
            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public ArrayList<Orders> PageSizeByFilterDO(int index, int size, String dateF, String dateT) {
        ArrayList<Orders> ord = new ArrayList<>();
        int start = index * size - size;
        try {
            String sql = "SELECT * from Orders o\n"
                    + " where OrderDate between ? and ?\n"
                    + " ORDER BY\n"
                    + " OrderID\n"
                    + " OFFSET ? ROWS \n"
                    + " FETCH NEXT ? ROWS ONLY";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, dateF);
            ps.setString(2, dateT);
            ps.setInt(3, start);
            ps.setInt(4, size);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                ord.add(o);
                // Intinilizial object 
            }
        } catch (Exception e) {
        }

        return ord;
    }

    public ArrayList<Orders> PageSize(int index, int size) {
        ArrayList<Orders> ord = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (order by OrderID asc) as y\n"
                    + ",* from Orders)\n"
                    + "select * from x where y between ? * ? - (? - 1) and ? * ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setInt(1, index);
            ps.setInt(2, size);
            ps.setInt(3, size);
            ps.setInt(4, index);
            ps.setInt(5, size);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int OrderID = rs.getInt("OrderID");
                int EmployeeID = rs.getInt("EmployeeID");
                String CustomerID = rs.getString("CustomerID");
                String RequiredDate = rs.getString("RequiredDate");
                String ShippedDate = rs.getString("ShippedDate");
                String OrderDate = rs.getString("OrderDate");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                double Freight = rs.getDouble("Freight");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                // Intinilizial object 
                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                // Add p to arraylist
                ord.add(o);

            }
        } catch (Exception e) {
        }

        return ord;
    }

    public int countOrderList() {
        try {
            String sql = "select count(*) from Orders";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public static void main(String[] args) {
        ArrayList<Integer> odd2 = new OrdersDAO().getOrdersByYears();
        for (Integer a : odd2) {
            System.out.println(a);
        }
//        for (int i = 0; i < odd2.size(); i++) {
//            System.out.println(odd2.get(i).getOrderID());
//        }
    }
}
