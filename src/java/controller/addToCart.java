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
public class addToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idx = req.getParameter("id");
        String idc = req.getParameter("idC");
        if (idx != null && idc == null) {
            int id = Integer.parseInt(idx);

            ShoppingCart sc = null;
            Object o = req.getSession().getAttribute("cart");
            if (o != null) {
                sc = (ShoppingCart) o;
            } else {
                sc = new ShoppingCart();
            }

            try {

                ProductDAO pD = new ProductDAO();
                //ArrayList<Product> listP = pD.getProductsByID(id);
                Product p = pD.getProductByID2(id);
                ItemAddToCart i = new ItemAddToCart(p, 1, p.getUnitPrice());
                sc.addItem(i);

                //req.getSession().setAttribute("list", listP);
                List<ItemAddToCart> list = sc.getItems();
                req.getSession().setAttribute("cart", sc);
                double t;
                t = sc.getTotal();
                req.getSession().setAttribute("t", t);
                //req.getRequestDispatcher("cart.jsp").forward(req, resp);
                req.getSession().setAttribute("size", list.size());
                req.getRequestDispatcher("detail?id=" + id).forward(req, resp);
                //resp.sendRedirect("detail.jsp");
            } catch (Exception e) {
            }
        } else if (idx == null && idc != null) {
            ShoppingCart sc = null;
            Object o = req.getSession().getAttribute("cart");
            if (o != null) {
                sc = (ShoppingCart) o;
            } else {
                sc = new ShoppingCart();
            }

            try {
                String numStr = req.getParameter("num");
                int num;
                if (numStr != null && !numStr.isEmpty()) {
                    num = Integer.parseInt(numStr);
                } else {
                    num = 1;
                }
                int idC = Integer.parseInt(idc);
                if (num == 0) {
                    num = 1;
                }
                sc.getItemById(idC).setQuantity(num);
                req.getSession().setAttribute("t", sc.getTotal());
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            } catch (Exception e) {
            }
        }

    }

}
