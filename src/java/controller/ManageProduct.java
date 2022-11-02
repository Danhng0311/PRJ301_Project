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
import java.util.ArrayList;
import models.CategoryDAO;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class ManageProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("prod");
        String inF = req.getParameter("indexF");
        String inSS = req.getParameter("indexS");
        String inS = req.getParameter("index");
        if (inSS != null && inS == null && inF == null) {
            inSS = req.getParameter("indexS");
            ProductDAO pDao = new ProductDAO();
            ArrayList<Product> products = new ProductDAO().getProducts();
            //req.getSession().setAttribute("product", products);
            int count = pDao.countProductList();

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
            ArrayList<Product> product = pDao.PageSize(index, sizeP);
            ArrayList<Category> list2 = new CategoryDAO().getProducts();
            req.setAttribute("product", product);
            req.setAttribute("index", index);
            req.setAttribute("sizePage", sizeP);
            req.setAttribute("endPage", endPage);
            //req.setAttribute("search", search);
            req.setAttribute("products", products);
            req.setAttribute("list2", list2);
            //req.getSession().getAttribute("msg");
            req.getRequestDispatcher("ListProSearch.jsp").forward(req, resp);

        } else if (inSS == null && inS == null && inF != null) {
            ProductDAO pDao = new ProductDAO();
            ArrayList<Product> products = (ArrayList<Product>) req.getSession().getAttribute("prod");
            String filter = (String) req.getSession().getAttribute("ddlCategory");
            //req.getSession().setAttribute("product", products);
            int count = pDao.countFilterProductList(filter);
            if (count == 0) {
                req.setAttribute("msg", "Your information which you SEARCH is not available");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                //req.getRequestDispatcher("list-product").forward(req, resp);
            }

//            req.getSession().getAttribute("prod");
            int sizePage = 4;
            int endPage = 0;
            endPage = count / sizePage;
            if (count % sizePage != 0) {
                endPage++;
            }
            int index;

            index = Integer.parseInt(inF);
            
            products = pDao.PageSizeByFilter(index, sizePage, filter);
//            ArrayList<Product> product = pDao.PageSize(index, sizePage);
            ArrayList<Category> list2 = new CategoryDAO().getProducts();
            req.setAttribute("product", products);
            req.setAttribute("indexF", index);
            req.setAttribute("sizePage", sizePage);
            req.setAttribute("endPage", endPage);
            //req.setAttribute("txtSearch", txtSearch);
//            req.setAttribute("products", products);
            req.getSession().setAttribute("list2", list2);
            //req.getSession().getAttribute("msg");
            req.getRequestDispatcher("Filter_Search_Paging.jsp").forward(req, resp);

        }
        ProductDAO pDao = new ProductDAO();
        ArrayList<Product> products = new ProductDAO().getProducts();
        //req.getSession().setAttribute("product", products);
        int count = pDao.countProductList();

        int sizePage = 4;
        int endPage = 0;
        endPage = count / sizePage;
        if (count % sizePage != 0) {
            endPage++;
        }
        int index;

        if (inS == null) {
            index = 1;
        } else {
            index = Integer.parseInt(inS);
        }
        ArrayList<Product> product = pDao.PageSize(index, sizePage);
        ArrayList<Category> list2 = new CategoryDAO().getProducts();
        req.setAttribute("product", product);
        req.setAttribute("index", index);
        req.setAttribute("sizePage", sizePage);
        req.setAttribute("endPage", endPage);
        //req.setAttribute("txtSearch", txtSearch);
        req.setAttribute("products", products);
        req.getSession().setAttribute("list2", list2);
        //req.getSession().getAttribute("msg");
        req.getRequestDispatcher("product.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ddlCategory = req.getParameter("ddlCategory");
        Category cate = new CategoryDAO().getCategoriesByName(ddlCategory);
        ArrayList<Product> prod = new ProductDAO().getProductsByCateID(cate.getCategoryID());
        ProductDAO p = new ProductDAO();
        try {
//            ArrayList<Product> products = new ProductDAO().search(s);    
            int count = p.countFilterProductList(ddlCategory);
            if (count == 0) {
                req.setAttribute("msg", "Your information which you SEARCH is not available");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                //req.getRequestDispatcher("list-product").forward(req, resp);
            }
            int index;
            String inS = req.getParameter("index");
            if (inS == null) {
                index = 1;
            } else {
                index = Integer.parseInt(inS);
            }

            int sizePage = 4;
            int endPage = 0;
            endPage = count / sizePage;
            if (count % sizePage != 0) {
                endPage++;
            }
            prod = p.PageSizeByFilter(index, sizePage, ddlCategory);
            //ArrayList<Product> products = p.searchPageSizeList(prod, index, sizePage);

            req.setAttribute("product", prod);
//            req.setAttribute("count", count);
            req.getSession().setAttribute("productsAmin", prod);
            req.getSession().setAttribute("prod", prod);
            req.getSession().setAttribute("count", count);
            req.getSession().setAttribute("ddlCategory", ddlCategory);
            req.setAttribute("endPage", endPage);
            req.setAttribute("txtSearch", ddlCategory);
            req.getRequestDispatcher("Filter_Search_Paging.jsp").forward(req, resp);
//                req.getRequestDispatcher("SearchAllProduct.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }

}
