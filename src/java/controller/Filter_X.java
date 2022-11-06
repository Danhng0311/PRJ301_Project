/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author Anh
 */
public class Filter_X extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int yearF = Integer.parseInt(req.getParameter("yearF"));
        
        req.getSession().setAttribute("yearF", yearF);
//        ArrayList<Integer> odd2 = new OrdersDAO().getOrdersByYears();
//        req.getSession().setAttribute("odd2", odd2);
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
    
}
