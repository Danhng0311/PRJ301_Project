package models;

import dal.DBContext;
import dal.Order_Detail;
import dal.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends DBContext {

    // Action: Read all Product
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public Product getProducts2() {
//        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public int countProductList() {
        try {
            String sql = "select count(*) from Products";
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

    public ArrayList<Product> PageSize(int index, int size) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (order by ProductID desc) as y\n"
                    + ",* from Products)\n"
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
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);

            }
        } catch (Exception e) {
        }

        return products;
    }

    public ArrayList<Product> PageSizeByFilter(int index, int size, String filter) {
        ArrayList<Product> products = new ArrayList<>();
        int start = index * size - size;
        try {
            String sql = "SELECT * from Products p\n"
                    + "join Categories c\n"
                    + "on p.CategoryID = c.CategoryID where c.CategoryName = ?\n"
                    + "ORDER BY\n"
                    + "ProductID\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, filter);
            ps.setInt(2, start);
            ps.setInt(3, size);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);

            }
        } catch (Exception e) {
        }

        return products;
    }

    public Product getProductByID(int ProductId) {

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products where ProductID=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setInt(1, ProductId);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            if (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product pro = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                // Add p to arraylist
                return pro;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void updatePro(Product pro) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[UnitsInStock] = ?\n"
                + "      ,[Discontinued] = ?\n"
                + " WHERE ProductID =?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, pro.getProductName());
            ps.setDouble(2, pro.getUnitPrice());
            ps.setInt(3, pro.getUnitsInStock());
            ps.setBoolean(4, pro.isDiscontinued());
            ps.setInt(5, pro.getProductID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePro(int id) {
        String sql1 = "ALTER TABLE [dbo].[Order Details] DROP CONSTRAINT [FK_Order_Details_Products]";
        String sql2 = "delete Products\n"
                + "where ProductID =?\n";
        String sql3 = "ALTER TABLE [dbo].[Order Details]  WITH NOCHECK ADD  CONSTRAINT [FK_Order_Details_Products] FOREIGN KEY([ProductID])\n"
                + "REFERENCES [dbo].[Products] ([ProductID])";
        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setInt(1, id);
            ps2.executeUpdate();
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps3.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int countFilterProductList(String filter) {
        try {
            String sql = new ProductDAO().getQueryByFilterAll(filter);
            PreparedStatement ps = connection.prepareStatement(sql);

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

    public ArrayList<Product> searchFilterProduct(String filter, String textSearch) {

        ArrayList<Product> products = new ArrayList<>();

        try {
            String sql = new ProductDAO().getQueryByFilter(filter, textSearch);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
        } catch (Exception e) {
        }
        return products;
    }

    private String getQueryByFilter(String filter, String textSearch) {
        String query = "";
        switch (filter) {
            case "Beverages":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Beverages' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Condiments":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Condiments' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Confections":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Confections' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Dairy Products":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Dairy Products' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Grains/Cereals":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Grains/Cereals' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Meat/Poultry":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Meat/Poultry' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Produce":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Produce' and p.ProductName like '%" + textSearch + "%'";
                break;
            case "Seafood":
                query = "select * from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Seafood' and p.ProductName like '%" + textSearch + "%'";
                break;
            default:
                break;
        }

        return query;
    }

    private String getQueryByFilterAll(String filter) {
        String query = "";
        switch (filter) {
            case "Beverages":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Beverages'";
                break;
            case "Condiments":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Condiments'";
                break;
            case "Confections":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Confections'";
                break;
            case "Dairy Products":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Dairy Products'";
                break;
            case "Grains/Cereals":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Grains/Cereals'";
                break;
            case "Meat/Poultry":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Meat/Poultry'";
                break;
            case "Produce":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Produce'";
                break;
            case "Seafood":
                query = "select count(*) from Products p\n"
                        + "join Categories c\n"
                        + "on p.CategoryID = c.CategoryID\n"
                        + "where c.CategoryName = 'Seafood'";
                break;
            default:
                break;
        }

        return query;
    }

    public int count(String searchName) {
        try {
            String sql = "select count(*) from Products where ProductName like ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, "%" + searchName + "%");

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

    public int countList(ArrayList<Product> products) {
        try {
            String sql = "select count(*) from Products where ProductName like ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            for (Product p : products) {
                ps.setString(1, "%" + p.getProductName() + "%");
                ResultSet rs = ps.executeQuery();

                // Progress result returned
                if (rs.next()) {

                    return rs.getInt(1);
                }
            }

            // Implement statement
        } catch (Exception e) {
        }

        return 0;
    }

    public ArrayList<Product> searchPageSize(String searchName, int index, int size) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (order by ProductID asc) as y\n"
                    + ",* from Products where ProductName like ?)\n"
                    + "select * from x where y between ? * ? - (? - 1) and ? * ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            ps.setString(1, "%" + searchName + "%");
            ps.setInt(2, index);
            ps.setInt(3, size);
            ps.setInt(4, size);
            ps.setInt(5, index);
            ps.setInt(6, size);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);

            }
        } catch (Exception e) {
        }

        return products;
    }

    public ArrayList<Product> searchPageSizeList(ArrayList<Product> pro, int index, int size) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "with x as(select ROW_NUMBER() over (order by ProductID asc) as y\n"
                    + ",* from Products where ProductName like ?)\n"
                    + "select * from x where y between ? * ? - (? - 1) and ? * ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Set value
            for (Product p : pro) {
                ps.setString(1, "%" + p.getProductName() + "%");
                ps.setInt(2, index);
                ps.setInt(3, size);
                ps.setInt(4, size);
                ps.setInt(5, index);
                ps.setInt(6, size);
                // Implement statement
                ResultSet rs = ps.executeQuery();

                // Progress result returned
                while (rs.next()) {
                    // Take data from 'rs' to biencucbo
                    int ProductID = rs.getInt("ProductID");
                    String ProductName = rs.getString("ProductName");
                    int CategoryID = rs.getInt("CategoryID");
                    String QuantityPerUnit = rs.getString("QuantityPerUnit");
                    double UnitPrice = rs.getDouble("UnitPrice");
                    int UnitsInStock = rs.getInt("UnitsInStock");
                    int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                    int ReorderLevel = rs.getInt("ReorderLevel");
                    boolean Discontinued = rs.getBoolean("Discontinued");

                    // Intinilizial object 
                    Product pr = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                    // Add p to arraylist
                    products.add(pr);

                }
            }

        } catch (Exception e) {
        }

        return products;
    }

    public void deleteOrderDetail(int id) {
        String sql = "ALTER TABLE [dbo].[Order Details]\n"
                + "drop Constraint PK_Order_Details";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Product> getProductsOrderDetail() {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "select top 4 * from [Order Details] od\n"
                    + "inner join Products p\n"
                    + "on p.ProductID = od.ProductID\n"
                    + "order by Discount desc";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public ArrayList<Product> getProductsOrderDetailBestSale() {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "select top 4 * from [Order Details] od\n"
                    + "inner join Products p\n"
                    + "on p.ProductID = od.ProductID\n"
                    + "order by UnitsOnOrder desc";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public ArrayList<Product> getProductsNew() {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "select top 4 * from Products \n"
                    + "order by ProductID desc";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public Product getProductByID2(int id) {
        //ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Product> getProductsByCateID(int id) {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public ArrayList<Product> getProductsByID(int id) {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                // Intinilizial object 
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                // Add p to arraylist
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public ArrayList<Product> getProductsByIDList(ArrayList<Order_Detail> listOrd) {
        ArrayList<Product> products = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < listOrd.size(); i++) {
                ps.setInt(1, listOrd.get(i).getProductID());
                ResultSet rs = ps.executeQuery();

                // Progress result returned
                while (rs.next()) {
                    // Take data from 'rs' to biencucbo
                    int ProductID = rs.getInt("ProductID");
                    String ProductName = rs.getString("ProductName");
                    int CategoryID = rs.getInt("CategoryID");
                    String QuantityPerUnit = rs.getString("QuantityPerUnit");
                    double UnitPrice = rs.getDouble("UnitPrice");
                    int UnitsInStock = rs.getInt("UnitsInStock");
                    int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                    int ReorderLevel = rs.getInt("ReorderLevel");
                    boolean Discontinued = rs.getBoolean("Discontinued");

                    // Intinilizial object 
                    Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                    // Add p to arraylist
                    products.add(p);
                }
            }

            // Implement statement
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public void insertProduct(Product pro) {
        try {
            String sql = "INSERT INTO [dbo].[Products]\n"
                    + "           ([ProductName]\n"
                    + "           ,[CategoryID]\n"
                    + "           ,[QuantityPerUnit]\n"
                    + "           ,[UnitPrice]\n"
                    + "           ,[UnitsInStock]\n"
                    + "           ,[UnitsOnOrder]\n"
                    + "           ,[ReorderLevel]\n"
                    + "           ,[Discontinued])\n"
                    + "     VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pro.getProductName());
            ps.setInt(2, pro.getCategoryID());
            ps.setString(3, pro.getQuantityPerUnit());
            ps.setDouble(4, pro.getUnitPrice());

            ps.setInt(5, pro.getUnitsInStock());
            ps.setInt(6, pro.getUnitsOnOrder());
            ps.setInt(7, pro.getReorderLevel());
            ps.setBoolean(8, pro.isDiscontinued());
//            ps.setInt(1, pro.getProductID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();
        System.out.println(p.countFilterProductList("Beverages"));

    }
}
