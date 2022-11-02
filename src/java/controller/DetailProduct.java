/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class DetailProduct extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String idd = req.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(idd);
            
        } catch (Exception e) {
            resp.sendError(500);
        }
        
        try {
            ProductDAO pD = new ProductDAO();
            ArrayList<Product> listP = pD.getProductsByID(id);
            
            req.setAttribute("listP", listP);
            
            req.getRequestDispatcher("detail.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }
    
}
