/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
import dal.Customers;

import dal.Order_Detail;
import dal.Orders;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.CustomersDAO;
import models.Order_DetailsDAO;
import models.OrdersDAO;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class Cancel_Order_Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String odtIDad = req.getParameter("odtIDByAd");
        String odt = req.getParameter("odtID");
             
        if (odt != null && odtIDad == null) {
            int odtID = Integer.parseInt(odt);
            try {

//                Orders ords = new OrdersDAO().getOrdersByOrderID(odtID);
                OrdersDAO oD = new OrdersDAO();
                oD.updateRequiredDateOrders(odtID);

                req.getRequestDispatcher("cancel_ord_profile.jsp").forward(req, resp);
            } catch (Exception e) {
            }
        } else if (odt == null && odtIDad != null) {
            int odtIDByAd = Integer.parseInt(odtIDad);
            try {
//                Orders ords2 = new OrdersDAO().getOrdersByOrderID(odtIDByAd);
                OrdersDAO oD2 = new OrdersDAO();
                oD2.updateRequiredDateOrders(odtIDByAd);
                
                req.getRequestDispatcher("order_admin").forward(req, resp);
            } catch (Exception e) {
            }
        }

    }

}
