/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
import dal.Email;
import dal.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.AccountDAO;

/**
 *
 * @author Anh
 */
public class Renew_Password extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
        try {
            String emailF = req.getParameter("emailF");
        
        String msgE = "";
        
        if (emailF.equals("")) {
            msgE = "Email is required";
            req.setAttribute("msgE", msgE);
        } else {
            String regex = "[a-zA-Z]\\w+@\\w+(\\.\\w+){1,3}";
            
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailF);
            if (matcher.matches() == false) {
                msgE = "Email is wrong form";
                req.setAttribute("msgE", msgE);
                req.getRequestDispatcher("forgot.jsp").forward(req, resp);
            }
//            ArrayList<Account> listAcc = new AccountDAO().getAlllAccount();
//            for (Account a : listAcc) {
//                if (!(a.getEmail().equals(emailF))) {
//                    msgE = "Email is not already";
//                    req.setAttribute("msgE", msgE);
//                } else {
//                    break;
//                }
//            }
        }
        if (!msgE.equals("")){
            req.getRequestDispatcher("forgot.jsp").forward(req, resp);
        }
        Account aD = new AccountDAO().getAccountByEmail(emailF);
        if(aD != null) {
            Email e = new Email();  
            e.setFrom("ducanhng0311@gmail.com");
            e.setFromPassword("danh142002");
            e.setTo(emailF);
            e.setSubject("Forgot Password - New Password");
            StringBuilder sb = new StringBuilder();
            sb.append("Dear").append(" Friend").append("<br>");
            sb.append("Your new Password here sir: ").append("123@123a").append("<br>");
            aD = new AccountDAO().getNewPassByEmail(emailF, "123@123a");
            e.setContent(sb.toString());
            SendMail.send(e);
            req.setAttribute("message", "New PassWord sent - Pls check it");
        } 
        
        } catch (Exception e) {
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
//            aD.
//        
//        }
        
    }
}
