/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.*;

/**
 *
 * @author Anh
 */
public class AccountProfileAllOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = "";
        String ContactName = "";
        String date = "";
        if (req.getSession().getAttribute("AccSession") != null) {
            Account acc = (Account) (req.getSession().getAttribute("AccSession"));
            cid = acc.getCustomerID();
            Customers cus = new CustomersDAO().getCustomersByID(cid);
            ContactName = cus.getContactName();
            date = cus.getDate();
        }

        ArrayList<Orders> listO = new OrdersDAO().getOrdersByIDList(cid);
        ArrayList<Order_Detail> listOrdd = new Order_DetailsDAO().getOrder_DetailsByIDv2(listO);

        ArrayList<Product> listPrd = new ProductDAO().getProductsByIDList(listOrdd);
        Product p = new ProductDAO().getProducts2();
        req.getSession().setAttribute("p", p);
        req.getSession().setAttribute("listO", listO);
        req.getSession().setAttribute("listOrdd", listOrdd);

        req.getSession().setAttribute("listPrd", listPrd);
        req.getRequestDispatcher(req.getContextPath() + "/../profile1.jsp").forward(req, resp);
    }
}
