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
public class ProductList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String IDs = req.getParameter("CategoryId");
        int IDc;
        try {
            IDc = Integer.parseInt(IDs);
        } catch (Exception e) {
            IDc = 0;
        }
        if(IDc == 0) {
            req.getRequestDispatcher("Error.jsp").forward(req, resp);
        }
        String name = "";
        String des = "";
        ArrayList<Category> listC = new CategoryDAO().getProducts();
        ArrayList<Product> listP = new ProductDAO().getProducts();
        ArrayList<Product> listP2 = new ArrayList<Product>();
        ArrayList<Category> listC3 = new ArrayList<>();

        for (Category c : listC) {
            if (c.getCategoryID() == IDc) {
                name = c.getCategoryName();
                des = c.getDescription();
            }
        }
        for (Product p : listP) {
            if (p.getCategoryID() == IDc) {
                listP2.add(p);
            }
        }

        req.setAttribute("name", name);
        req.setAttribute("des", des);
        req.setAttribute("ProList2", listP2);
        ArrayList<Product> listP3 = new ProductDAO().getProductsOrderDetail();
        double m = 0;
        for (Product p3 : listP3) {
            m = p3.getUnitPrice();
        }

        req.getSession().setAttribute("listP3", listP3);
        req.getRequestDispatcher("Category.jsp").forward(req, resp);
    }

}
