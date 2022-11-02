/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Category;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoryDAO;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class EditProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idd = (req.getParameter("id"));
        int id;
        try {
            id = Integer.parseInt(idd);
            Product p = new ProductDAO().getProductByID(id);
            ArrayList<Category> listC = new CategoryDAO().getProducts();
            req.getSession().setAttribute("id", id);
            req.getSession().setAttribute("listC", listC);
            req.getSession().setAttribute("proD", p);

            req.getRequestDispatcher("Edit_Product_Admin.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Name = req.getParameter("Name");
        double Price = Double.parseDouble(req.getParameter("Price"));
        int Stock = Integer.parseInt(req.getParameter("Stock"));
        int id = (int) req.getSession().getAttribute("id");
        String CategoryName = req.getParameter("CategoryName");
        boolean Dcontinued = Boolean.parseBoolean("txtDiscontinued");
        ProductDAO proD = new ProductDAO();
        try {
            Product p2 = new ProductDAO().getProductByID(id);
            
            Product p = new Product();
            Category c = new Category();
            //req.getSession().setAttribute("CusSession", cus);
            p.setProductID(id);
            p.setProductName(Name);
            p.setUnitPrice(Price);
            p.setUnitsInStock(Stock);
            p.setDiscontinued(Dcontinued);
            c.setCategoryName(CategoryName);
            c.setCategoryID(p2.getCategoryID());
            proD.updatePro(p);
            //req.getRequestDispatcher("list-product").forward(req, resp);
            //req.setAttribute("Customer", cus);
            resp.sendRedirect("manage_product");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
