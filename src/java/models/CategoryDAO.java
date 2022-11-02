package models;

import dal.Category;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO extends DBContext {

    // Action: Read all Product
    public ArrayList<Category> getProducts() {
        ArrayList<Category> categories = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "Select * from Categories";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Implement statement........
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                // Intinilizial object 
                Category p = new Category(CategoryID, CategoryName, Description, Picture);

                // Add p to arraylist
                categories.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return categories;
    }
//    public static void main(String[] args) {
//        ArrayList<Category> list = new CategoryDAO().getProducts();
//        for (Category p : list) {
//            System.out.println(p.toString());
//        }
//    }

    public ArrayList<Category> getProductsByID(int cid) {
        ArrayList<Category> categories = new ArrayList<>();

        // Create : PrepareStatement
        try {
            String sql = "SELECT [CategoryID]\n"
                    + "      ,[CategoryName]\n"
                    + "      ,[Description]\n"
                    + "      ,[Picture]\n"
                    + "  FROM [dbo].[Categories]\n"
                    + "  where CategoryID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            // Implement statement........
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                // Intinilizial object 
                Category p = new Category(CategoryID, CategoryName, Description, Picture);

                // Add p to arraylist
                categories.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return categories;
    }
    
    public Category getCategoriesByName(String cateN) {
       

        // Create : PrepareStatement
        try {
            String sql = "SELECT * from Categories where CategoryName = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cateN);
            // Implement statement........
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                // Take data from 'rs' to biencucbo
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                // Intinilizial object 
                Category p = new Category(CategoryID, CategoryName, Description, Picture);

                // Add p to arraylist
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    public void updateCate(Category cate) {
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [CategoryName] = ?\n"

                + " WHERE ProductID =?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cate.getCategoryName());
            

            ps.setInt(2, cate.getCategoryID());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
   

}
