/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
import dal.Customers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.AccountDAO;
import models.CustomersDAO;

/**
 *
 * @author Anh
 */
public class EditInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id= req.getParameter("id");
        try {
            CustomersDAO cusD = new CustomersDAO();
        cusD.getCustomersByID(id);
        req.setAttribute("cusD", cusD);
        req.getSession().setAttribute("idd", id);
        req.getRequestDispatcher("../Update.jsp").forward(req, resp);
        } catch (Exception e) {
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Companyname = req.getParameter("Companyname");
        String Contactname = req.getParameter("ContactName");
        String Contacttitle = req.getParameter("ContactTitle");
        String Address = req.getParameter("Address");
        String Email = req.getParameter("Email");
        
        String id =(String)req.getSession().getAttribute("idd");
        CustomersDAO cusD = new CustomersDAO();
        AccountDAO accD = new AccountDAO();
        try {           
            Customers cus = new Customers();
            cus.setAddress(Address);
            cus.setCompanyName(Companyname);
            cus.setContactName(Contactname);
            cus.setContactTitle(Contacttitle);
            cus.setCustomerID(id);
            //req.getSession().setAttribute("CusSession", cus);
            Account acc = new Account();
            acc.setCustomerID(id);
            acc.setEmail(Email);
            cusD.updateCus(cus);
            accD.updateAcc(acc);
            //req.getRequestDispatcher("../index.jsp").forward(req, resp);
            //req.setAttribute("Customer", cus);
            resp.sendRedirect("../index.jsp");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    

}
