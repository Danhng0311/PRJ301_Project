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
public class AmountProductInCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart sc = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            sc = (ShoppingCart) o;
        } else {
            sc = new ShoppingCart();
        }
        String tnum = req.getParameter("num");
        String tid = req.getParameter("id");

        int id, num;
        try {
            id = Integer.parseInt(tid);
            num = Integer.parseInt(tnum);

            if ((num == -1) && (sc.getQuantityById(id) <= 1)) {
                sc.removeItem(id);
            } else {
                ProductDAO pD = new ProductDAO();
                Product p = pD.getProductByID(id);
                ItemAddToCart t = new ItemAddToCart(p, num, p.getUnitPrice());
                sc.addItem(t);
            }
            List<ItemAddToCart> list = sc.getItems();

            req.getSession().setAttribute("t", sc.getTotal());
            req.getSession().setAttribute("cast", sc);
            req.getSession().setAttribute("size", list.size());
            if (list.size() == 0) {
                req.getRequestDispatcher("nullCart.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } catch (Exception e) {
        }

    }

}
