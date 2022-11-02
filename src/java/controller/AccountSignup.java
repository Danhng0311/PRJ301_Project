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
import static java.lang.Math.random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.AccountDAO;
import models.CustomersDAO;

/**
 *
 * @author Anh
 */
public class AccountSignup extends HttpServlet {
//    private Customers cus = new Customers();
//private int cID = Integer.parseInt(cus.getCustomerID());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {

            req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Companyname = req.getParameter("CompanyName");
        String Contactname = req.getParameter("Contactname");
        String Contacttitle = req.getParameter("Contacttitle");
        String Address = req.getParameter("Address");
        String Email = req.getParameter("Email");
        String Password = req.getParameter("Password");
        String RePassword = req.getParameter("Re-Password");
        
        
        LocalDate curD = java.time.LocalDate.now();
        String date = curD.toString();

       
        CustomersDAO cusD = new CustomersDAO();
        AccountDAO accD = new AccountDAO();

        String msgCPN = "";
        String msgCTN = "";
        String msgCTT = "";
        String msgADR = "";
        String msgE = "";
        String msgP = "";
        String msgRP = "";

        req.setAttribute("CompanyName", Companyname);
        req.setAttribute("Contactname", Contactname);
        req.setAttribute("Contacttitle", Contacttitle);
        req.setAttribute("Address", Address);
        req.setAttribute("Email", Email);
        req.setAttribute("Password", Password);

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
        if (Email.equals("")) {
            msgE = "Email is required";
            req.setAttribute("msgE", msgE);
        } else {
            String regex = "[a-zA-Z]\\w+@\\w+(\\.\\w+){1,3}";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(Email);
            if (matcher.matches() == false) {
                msgE = "Email is wrong form";
                req.setAttribute("msgE", msgE);
            }
            ArrayList<Account> listAcc = new AccountDAO().getAlllAccount();
            for (Account a : listAcc) {
                if (a.getEmail().equals(Email)) {
                    msgE = "Email is already";
                    req.setAttribute("msgE", msgE);
                }
            }
        }
        if (Password.equals("")) {
            msgP = "Password is required";
            req.setAttribute("msgP", msgP);
        }
        if (RePassword.equals("")) {
            msgRP = "RePassword is required";
            req.setAttribute("msgRP", msgRP);
        } else {
            if (!RePassword.equals(Password)) {
                msgRP = "RePassword is not same Password";
                req.setAttribute("msgRP", msgRP);
            }
        }
        if (!msgE.equals("") || !msgP.equals("") || !msgRP.equals("") || !msgADR.equals("") || !msgCTT.equals("") || !msgCTN.equals("") || !msgCPN.equals("")) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {

            Random random = new Random();
            String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            String ab = "";
            String abc = "";
            for (int i = 0; i < 5; i++) {
                ab = Character.toString(setOfCharacters.charAt(random.nextInt(setOfCharacters.length())));
                abc += ab;
            }

            Account acc = new Account();
            acc.setEmail(Email);
            acc.setPassword(Password);
            acc.setCustomerID(abc);

            Customers cus = new Customers();
            cus.setCustomerID(abc);
            cus.setCompanyName(Companyname);
            cus.setContactName(Contactname);
            cus.setContactTitle(Contacttitle);
            cus.setAddress(Address);
            cus.setDate(date);
            
            req.setAttribute("test", Companyname);

            try {
                cusD.getCustomers(cus);
                accD.getAccounts(acc);
                req.getSession().setAttribute("cusSession", cus);
                req.getSession().setAttribute("AccSession", acc);
                //customersDAO.getCustomers(cus);
                resp.sendRedirect("../index.jsp");
                //req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
