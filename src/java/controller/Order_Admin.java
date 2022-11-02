/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.OrdersDAO;

/**
 *
 * @author Anh
 */
public class Order_Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Orders> ordD = new OrdersDAO().getAllOrders();
        String in = req.getParameter("indexOF");
        String inSS = req.getParameter("indexO");

        if (inSS == null && in != null) {

            OrdersDAO ordDao = new OrdersDAO();
//            ArrayList<Product> products = new ProductDAO().getProducts();
            //req.getSession().setAttribute("product", products);
            String dF = (String) req.getSession().getAttribute("dateFrom");
            String dT = (String) req.getSession().getAttribute("dateTo");
            ArrayList<Orders> ordDn = (ArrayList<Orders>) req.getSession().getAttribute("ordD");
            int count = ordDao.countFilterOrderDList(dF, dT);
            int index;

            index = Integer.parseInt(in);
            int sizeP = 8;
            int endPage = 0;
            endPage = count / sizeP;
            if (count % sizeP != 0) {
                endPage++;
            }

            ordDn = ordDao.PageSizeByFilterDO(index, sizeP, dF, dT);
            req.setAttribute("endPage", endPage);
            req.setAttribute("ordD", ordDn);
            req.setAttribute("current", in);
            req.getRequestDispatcher("FilterByDateOrder.jsp").forward(req, resp);
        }

        OrdersDAO ordDao = new OrdersDAO();
//            ArrayList<Product> products = new ProductDAO().getProducts();
        //req.getSession().setAttribute("product", products);
        int count = ordDao.countOrderList();

        int sizeP = 8;
        int endPage = 0;
        endPage = count / sizeP;
        if (count % sizeP != 0) {
            endPage++;
        }
        int index;

        if (inSS == null) {
            index = 1;
        } else {
            index = Integer.parseInt(inSS);
        }
        ordD = ordDao.PageSize(index, sizeP);
        req.setAttribute("endPage", endPage);
        req.setAttribute("ordD", ordD);
        req.setAttribute("current", inSS);
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateFrom = req.getParameter("txtStartOrderDate");
        String dateTo = req.getParameter("txtEndOrderDate");
        OrdersDAO od = new OrdersDAO();
        ArrayList<Orders> ordD = new OrdersDAO().getOrdersByDateFromTo(dateFrom, dateTo);

        try {
            int count = od.countFilterOrderDList(dateFrom, dateTo);
//            if (count == 0) {
//                req.setAttribute("msgC", "Your information which you SEARCH is not available");
//                req.getRequestDispatcher("order_admin").forward(req, resp);
//            }
            int index;
            String inS = req.getParameter("indexOF");
            if (inS == null) {
                index = 1;
            } else {
                index = Integer.parseInt(inS);
            }

            int sizePage = 8;
            int endPage = 0;
            endPage = count / sizePage;
            if (count % sizePage != 0) {
                endPage++;
            }
            ordD = od.PageSizeByFilterDO(index, sizePage, dateFrom, dateTo);
            req.getSession().setAttribute("ordD", ordD);
            req.setAttribute("endPage", endPage);
            req.setAttribute("current", inS);
            req.getSession().setAttribute("dateFrom", dateFrom);
            req.getSession().setAttribute("dateTo", dateTo);
            req.getRequestDispatcher("FilterByDateOrder.jsp").forward(req, resp);
        } catch (Exception e) {
        }

    }

}
