/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
import dal.Customers;
import dal.Orders;
import dal.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.AccountDAO;
import models.CustomersDAO;
import models.OrdersDAO;

/**
 *
 * @author Anh
 */
public class signUpTemporary extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Companyname = req.getParameter("comName");
        String Contactname = req.getParameter("contName");
        String Contacttitle = req.getParameter("cTitle");
        String Address = req.getParameter("address");
        String msgCPN = "";
        String msgCTN = "";
        String msgCTT = "";
        String msgADR = "";
        String msgRD = "";
        req.setAttribute("CompanyName", Companyname);
        req.setAttribute("Contactname", Contactname);
        req.setAttribute("Contacttitle", Contacttitle);
        req.setAttribute("Address", Address);

        //ReqDate
        String txtRequiredDate = req.getParameter("txtRequiredDate");
        LocalDate curD = java.time.LocalDate.now();
        String date = curD.toString();

        if (txtRequiredDate.equals("")) {
            msgRD = "txtRequiredDate is required";
            req.setAttribute("msgRD", msgRD);
        } else {
            if (txtRequiredDate.compareTo(date) < 0) {
                msgRD = "txtRequiredDate must be greater than Current Date";
                req.setAttribute("msgRD", msgRD);
            }
        }

        req.getSession().setAttribute("txtRequiredDate", txtRequiredDate);

        ShoppingCart sc = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            sc = (ShoppingCart) o;
        } else {
            sc = new ShoppingCart();
        }

        //Check customer signin or not signin
        Customers cus = null;
        Object a = req.getSession().getAttribute("cusSession");

        //Customer signin
        if (a != null) {
            Orders ord1 = new Orders();
            ord1.setRequiredDate(txtRequiredDate);
            cus = (Customers) a;
            OrdersDAO oD = new OrdersDAO();
            oD.addOrder(cus, sc, ord1);
            req.getSession().removeAttribute("cart");
            req.getSession().setAttribute("size", 0);
            resp.sendRedirect("index.jsp");
        } else {
            if (Companyname.equals("")) {
                msgCPN = "Companyname is required";
                req.setAttribute("msgCPN", msgCPN);
            }
            if (Contactname.equals("")) {
                msgCTN = "Contactname is required";
                req.setAttribute("msgCTN", msgCTN);
            }
            if (Contacttitle.equals("")) {
                msgCTT = "Contacttitle is required";
                req.setAttribute("msgCTT", msgCTT);
            }
            if (Address.equals("")) {
                msgADR = "Address is required";
                req.setAttribute("msgADR", msgADR);
            }
            //Random CustomerID
            Random random = new Random();
            String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (!msgADR.equals("") || !msgCTT.equals("") || !msgCTN.equals("") || !msgCPN.equals("") || !msgRD.equals("")) {
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            } else {
                //Customer not signin
                String ab2 = "";
                String abc2 = "";
                for (int i = 0; i < 5; i++) {
                    ab2 = Character.toString(setOfCharacters.charAt(random.nextInt(setOfCharacters.length())));
                    abc2 += ab2;
                }

                Customers cus1 = new Customers();

                cus1.setCustomerID(abc2);
                cus1.setCompanyName(Companyname);
                cus1.setContactName(Contactname);
                cus1.setContactTitle(Contacttitle);
                cus1.setAddress(Address);
                cus1.setDate(date);

                Orders ord = new Orders();
                ord.setRequiredDate(txtRequiredDate);

                CustomersDAO cusD = new CustomersDAO();
                cusD.getCustomers(cus1);

//            cus1 = (Customers) a;
                OrdersDAO oD = new OrdersDAO();
                oD.addOrder(cus1, sc, ord);
                req.getSession().removeAttribute("cart");
                req.getSession().setAttribute("size", 0);
                resp.sendRedirect("index.jsp");
                //resp.sendRedirect("signin.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get Parameter from Cart.jsp

        ShoppingCart sc = null;
        Object o = req.getSession().getAttribute("cart");
        if (o != null) {
            sc = (ShoppingCart) o;
        } else {
            sc = new ShoppingCart();
        }

        //Check customer signin or not signin
        Customers cus = null;
        Object a = req.getSession().getAttribute("cusSession");
        Object acc = req.getSession().getAttribute("AccSession");
        //Customer signin
        if (acc != null) {
            String Companyname = req.getParameter("comName");
            String Contactname = req.getParameter("contName");
            String Contacttitle = req.getParameter("cTitle");
            String Address = req.getParameter("address");
            String msgCPN = "";
            String msgCTN = "";
            String msgCTT = "";
            String msgADR = "";
            String msgRD = "";
            req.setAttribute("CompanyName", Companyname);
            req.setAttribute("Contactname", Contactname);
            req.setAttribute("Contacttitle", Contacttitle);
            req.setAttribute("Address", Address);

            //ReqDate
            String txtRequiredDate = req.getParameter("txtRequiredDate");
            LocalDate curD = java.time.LocalDate.now();
            String date = curD.toString();
//        String regex = "^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$";

//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(txtRequiredDate);
            if (txtRequiredDate.equals("")) {
                msgRD = "txtRequiredDate is required";
                req.setAttribute("msgRD", msgRD);
            } else {
//            if (matcher.matches() == false) {
//                msgRD = "txtRequiredDate is wrong form";
//                req.setAttribute("msgRD", msgRD);
//            }
                if (txtRequiredDate.compareTo(date) < 0) {
                    msgRD = "txtRequiredDate must be greater than Current Date";
                    req.setAttribute("msgRD", msgRD);
                }
            }

            req.getSession().setAttribute("txtRequiredDate", txtRequiredDate);
            if (!msgRD.equals("")) {
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                return;
            }
            Orders ord1 = new Orders();
            ord1.setRequiredDate(txtRequiredDate);
            cus = (Customers) a;
            OrdersDAO oD = new OrdersDAO();
            oD.addOrder(cus, sc, ord1);
            req.getSession().removeAttribute("cart");
            req.getSession().setAttribute("size", 0);
            //resp.sendRedirect("index.jsp");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            String Companyname = req.getParameter("comName2");
            String Contactname = req.getParameter("contName2");
            String Contacttitle = req.getParameter("cTitle2");
            String Address = req.getParameter("address2");
            String msgCPN = "";
            String msgCTN = "";
            String msgCTT = "";
            String msgADR = "";
            String msgRD = "";
            req.setAttribute("CompanyName", Companyname);
            req.setAttribute("Contactname", Contactname);
            req.setAttribute("Contacttitle", Contacttitle);
            req.setAttribute("Address", Address);

            //ReqDate
            String txtRequiredDate = req.getParameter("txtRequiredDate2");
            LocalDate curD = java.time.LocalDate.now();
            String date = curD.toString();
//        String regex = "^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$";

//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(txtRequiredDate);
            if (txtRequiredDate.equals("")) {
                msgRD = "txtRequiredDate is required";
                req.setAttribute("msgRD", msgRD);
            } else {
//            if (matcher.matches() == false) {
//                msgRD = "txtRequiredDate is wrong form";
//                req.setAttribute("msgRD", msgRD);
//            }
                if (txtRequiredDate.compareTo(date) < 0) {
                    msgRD = "txtRequiredDate must be greater than Current Date";
                    req.setAttribute("msgRD", msgRD);
                }
            }

            req.getSession().setAttribute("txtRequiredDate", txtRequiredDate);
            if (Companyname.equals("")) {
                msgCPN = "Companyname is required";
                req.setAttribute("msgCPN", msgCPN);
            }
            if (Contactname.equals("")) {
                msgCTN = "Contactname is required";
                req.setAttribute("msgCTN", msgCTN);
            }
            if (Contacttitle.equals("")) {
                msgCTT = "Contacttitle is required";
                req.setAttribute("msgCTT", msgCTT);
            }
            if (Address.equals("")) {
                msgADR = "Address is required";
                req.setAttribute("msgADR", msgADR);
            }
            
            //Random CustomerID
            Random random = new Random();
            String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (!msgADR.equals("") || !msgCTT.equals("") || !msgCTN.equals("") || !msgCPN.equals("") || !msgRD.equals("")) {
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                return;
            } else {
                //Customer not signin
                String ab2 = "";
                String abc2 = "";
                for (int i = 0; i < 5; i++) {
                    ab2 = Character.toString(setOfCharacters.charAt(random.nextInt(setOfCharacters.length())));
                    abc2 += ab2;
                }

                Customers cus1 = new Customers();

                cus1.setCustomerID(abc2);
                cus1.setCompanyName(Companyname);
                cus1.setContactName(Contactname);
                cus1.setContactTitle(Contacttitle);
                cus1.setAddress(Address);
                cus1.setDate(date);

                Orders ord = new Orders();
                ord.setRequiredDate(txtRequiredDate);

                CustomersDAO cusD = new CustomersDAO();
                cusD.getCustomers(cus1);

//            cus1 = (Customers) a;
                OrdersDAO oD = new OrdersDAO();
                oD.addOrder(cus1, sc, ord);
                req.getSession().removeAttribute("cart");
                req.getSession().setAttribute("size", 0);
                //resp.sendRedirect("index.jsp");
                //resp.sendRedirect("signin.jsp");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }
}
