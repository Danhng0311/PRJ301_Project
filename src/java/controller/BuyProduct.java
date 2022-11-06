/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.ItemAddToCart;
import dal.Product;
import dal.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class BuyProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idx = req.getParameter("id");
        int id;

        try {
            id = Integer.parseInt(idx);
        } catch (Exception e) {
            id = 0;
        }
        if (id == 0) {
            req.getRequestDispatcher("Error.jsp").forward(req, resp);
        }

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
            req.getSession().setAttribute("list", list);
            double t;
            t = sc.getTotal();
            req.getSession().setAttribute("t", t);

            //req.getRequestDispatcher("cart.jsp").forward(req, resp);
            req.getSession().setAttribute("size", list.size());
            req.getRequestDispatcher("cart.jsp").forward(req, resp);

        } catch (Exception e) {
        }

//        ProductDAO pD = new ProductDAO();
//        List<Product> list = pD.getProducts();
//        Cookie[] arr = req.getCookies();
//        String txt = "";
//        if (arr != null) {
//            for (Cookie o : arr) {
//                if (o.getName().equals("cart")) {
//                    txt += o.getValue();
//                    o.setMaxAge(0);
//                    resp.addCookie(o);
//                }
//            }
//        }
//        String idx = req.getParameter("id");
//        int id = Integer.parseInt(idx);
//        //ArrayList<Product> listP = pD.getProductsByID(id);
//
//        String numx = req.getParameter("num");
//        int num = Integer.parseInt(idx);
//        if (txt.isEmpty()) {
//            txt = idx + ":" + numx;
//        } else {
//            txt = txt + "," + idx + ":" + numx;
//        }
//        Cookie c = new Cookie("cart", txt);
//        c.setMaxAge(2 * 24);
//        resp.addCookie(c);
//
//        ShoppingCart cart = new ShoppingCart(txt, list);
//        int size = cart.getItems().size();
//        req.setAttribute("size", size);
//
//        req.setAttribute("cart", cart);
//        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ProductDAO pD = new ProductDAO();
//        List<Product> list = pD.getProducts();
//        Cookie[] arr = req.getCookies();
//        String txt = "";
//        if (arr != null) {
//            for (Cookie o : arr) {
//                if (o.getName().equals("cart")) {
//                    txt += o.getValue();
//                    o.setMaxAge(0);
//                    resp.addCookie(o);
//                }
//            }
//        }
//        String id = req.getParameter("id");
////        int id = Integer.parseInt(idx);
//        //ArrayList<Product> listP = pD.getProductsByID(id);
//
//        String num = req.getParameter("num");
//        //int num = Integer.parseInt(numx);
//        if (txt.isEmpty()) {
//            txt = id + ":" + num;
//        } else {
//            txt = txt + "," + id + ":" + num;
//        }
//        Cookie c = new Cookie("cart", txt);
//        c.setMaxAge(2 * 24);
//        resp.addCookie(c);
//
//        ShoppingCart cart = new ShoppingCart(txt, list);
//        int size = cart.getItems().size();
//        req.setAttribute("size", size);
//
//        req.setAttribute("cart", cart);
//        req.getRequestDispatcher("cart.jsp").forward(req, resp);
//    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idx = req.getParameter("id");
        String iddx = req.getParameter("idd");

        String idb = req.getParameter("idB");

        if (iddx != null && idx == null && idb == null) {

            int id2 = Integer.parseInt(iddx);

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
                Product p = pD.getProductByID2(id2);
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
                //req.getRequestDispatcher("detail?id="+id).forward(req, resp);

                resp.sendRedirect("detail?id=" + id2);
            } catch (Exception e) {
            }
        } else if (iddx == null && idx != null && idb == null) {
            int id;
            try {
                id = Integer.parseInt(idx);
            } catch (Exception e) {
                id = 78;
            }

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
                req.getSession().setAttribute("list", list);
                double t;
                t = sc.getTotal();
                req.getSession().setAttribute("t", t);
//                req.getSession().setMaxInactiveInterval(100);
                req.getSession().setAttribute("size", list.size());
                resp.sendRedirect("detail?id=" + id);
//                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            } catch (Exception e) {
            }
        } else {
            int idB;
            idB = Integer.parseInt(idb);

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
                Product p = pD.getProductByID2(idB);
                ItemAddToCart i = new ItemAddToCart(p, 1, p.getUnitPrice());
                sc.addItem(i);

                //req.getSession().setAttribute("list", listP);
                List<ItemAddToCart> list = sc.getItems();
                req.getSession().setAttribute("cart", sc);
                req.getSession().setAttribute("list", list);
                double t;
                t = sc.getTotal();
                req.getSession().setAttribute("t", t);

                //req.getRequestDispatcher("cart.jsp").forward(req, resp);
                req.getSession().setAttribute("size", list.size());
                resp.sendRedirect("cart.jsp");
//                req.getRequestDispatcher("cart.jsp").forward(req, resp);

            } catch (Exception e) {
            }
        }

    }

}
