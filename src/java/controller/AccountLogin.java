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

public class AccountLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Send request -> login.jsp yearF
        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("cart");
            req.getSession().removeAttribute("yearF");
            req.getSession().removeAttribute("role");
            req.getSession().setAttribute("size", 0);
            req.getSession().setAttribute("t", 0);
            resp.sendRedirect("../index.jsp");
        } else {

            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Receive data form login
        String email = req.getParameter("txtMail");
        String pass = req.getParameter("txtPass");
        String msgE = "";
        String msgP = "";
        if (email.equals("")) {
            msgE = "Email is required";
            req.setAttribute("msgE", msgE);
        }
        if (pass.equals("")) {
            msgP = "Password is required";
            req.setAttribute("msgP", msgP);
        }
        if (!msgE.equals("") || !msgP.equals("")) {
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            Account acc = new AccountDAO().getAccount(email, pass);
//            req.getSession().setAttribute("role", 0);
            if (acc != null) {
                //cap session
                if (acc.getRole() == 1) {
                    req.getSession().setAttribute("role", acc.getRole());
                    req.getSession().setAttribute("AccSession", acc);
                    resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                } else {
                    req.getSession().setAttribute("role", acc.getRole());
                    req.getSession().removeAttribute("cart");
                    req.getSession().setAttribute("size", 0);
                    req.getSession().setAttribute("t", 0);
                    req.getSession().setAttribute("AccSession", acc);
                    Customers cD = new CustomersDAO().getCustomersByID(acc.getCustomerID());
                    req.getSession().setAttribute("cusSession", cD);

                    //dieu huong toi index
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
                }

            } else {
                // else thi gui thong diep error ve doGet(login.jsps)
                req.setAttribute("msg", "Account not exist");
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
    }

}
