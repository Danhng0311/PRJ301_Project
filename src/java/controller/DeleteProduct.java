/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class DeleteProduct extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idd = (req.getParameter("id"));
        int id;
        
        try {
            id = Integer.parseInt(idd);
            ProductDAO p = new ProductDAO();
//            p.deleteOrderDetail(id);
            p.deletePro(id);
            //req.getRequestDispatcher("manage_product").forward(req, resp);
            resp.sendRedirect("manage_product");
        } catch (Exception e) {
        }
    }
    
}
