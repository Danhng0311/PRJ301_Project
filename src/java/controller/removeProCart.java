/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.ItemAddToCart;
import dal.Product;
import dal.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class removeProCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart sc = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            sc = (ShoppingCart) o;
        } else {
            sc = new ShoppingCart();
        }

        String tid = req.getParameter("id");

        int id;
        try {
            id = Integer.parseInt(tid);
            sc.removeItem(id);

            List<ItemAddToCart> list = sc.getItems();
            if(list.size() == 0){
                req.getRequestDispatcher("nullCart.jsp").forward(req, resp);
            }
            req.getSession().setAttribute("cast", sc);
            req.getSession().setAttribute("size", list.size());
            req.getSession().setAttribute("t", sc.getTotal());
            
            req.getRequestDispatcher("cart.jsp").forward(req, resp);

        } catch (Exception e) {
        }
    }

}
