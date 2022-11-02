/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Category;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.CategoryDAO;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class CreateNewProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("index.jsp");

        String pName = req.getParameter("txtProductName");
        double pPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
        String pQuantity = req.getParameter("txtQuantityPerUnit");
        int pStock = Integer.parseInt(req.getParameter("txtUnitsInStock"));
        int UnitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
        int ReorderLevel = Integer.parseInt(req.getParameter("txtReorderLevel"));
        boolean Discontinued = Boolean.parseBoolean(req.getParameter("txtDiscontinued"));
        int pCate = Integer.parseInt(req.getParameter("categoryN"));
        
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
        String msgN = "";
        String msgP = "";
        String msgQ = "";
        String msgS = "";
        String msgO = "";
        String msgL = "";
        String msgD = "";
//        Category cate = new CategoryDAO().getCategoriesByName(pCate);
        req.setAttribute("pName", pName);
        req.setAttribute("pPrice", pPrice);
        req.setAttribute("pQuantity", pQuantity);
        req.setAttribute("pStock", pStock);
        req.setAttribute("UnitsOnOrder", UnitsOnOrder);
        req.setAttribute("ReorderLevel", ReorderLevel);
        req.setAttribute("Discontinued", Discontinued);

        if (pName.equals("")) {
            msgN = "pName is required";
            req.setAttribute("msgN", msgN);
        }
        if (pPrice == 0) {
            msgP = "pPrice is required";
            req.setAttribute("msgP", msgP);
        }
        if (pQuantity.equals("")) {
            msgQ = "pQuantity is required";
            req.setAttribute("msgQ", msgQ);
        }
//        Integer pStock2 = new Integer(pStock);
        if (pStock == 0) {
            msgS = "pStock is required";
            req.setAttribute("msgS", msgS);
        }
        if (UnitsOnOrder == 0) {
            msgO = "UnitsOnOrder is required";
            req.setAttribute("msgO", msgO);
        }
        if (ReorderLevel == 0) {
            msgL = "ReorderLevel is required";
            req.setAttribute("msgL", msgL);
        }

        if (!msgL.equals("") || !msgO.equals("") || !msgS.equals("") || !msgQ.equals("") || !msgP.equals("") || !msgN.equals("")) {
            req.getRequestDispatcher("create-product").forward(req, resp);
        } else {
            ProductDAO pD = new ProductDAO();
            Product p = new Product();
            p.setProductName(pName);
            p.setUnitPrice(pPrice);
            p.setQuantityPerUnit(pQuantity);
            p.setUnitsInStock(pStock);
            p.setUnitsOnOrder(UnitsOnOrder);
            p.setDiscontinued(Discontinued);
            p.setReorderLevel(ReorderLevel);
            p.setCategoryID(pCate);
            
            try {
                pD.insertProduct(p);
                req.getSession().setAttribute("p", p);
                //customersDAO.getCustomers(cus);
                resp.sendRedirect("manage_product");
//                req.getRequestDispatcher(req.getContextPath()+"/manage_product").forward(req, resp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
