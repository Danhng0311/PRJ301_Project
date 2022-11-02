/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.ProductDAO;

/**
 *
 * @author Anh
 */
public class SearchProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearch = req.getParameter("txtSearch");
        String search2 = req.getParameter("search2");
        if (txtSearch != null && search2 == null) {
            ProductDAO p = new ProductDAO();
            try {
//            ArrayList<Product> products = new ProductDAO().search(s);    
                int count = p.count(txtSearch);
                if (count == 0) {
                    //resp.sendRedirect("index.jsp");
                    req.setAttribute("msg", "Your information which you SEARCH is not available");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
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

                ArrayList<Product> products = p.searchPageSize(txtSearch, index, sizePage);

                req.setAttribute("product", products);

                req.setAttribute("endPage", endPage);
                req.setAttribute("txtSearch", txtSearch);
                req.getRequestDispatcher("SearchAllProduct.jsp").forward(req, resp);
            } catch (Exception e) {
            }

        } else {
            ProductDAO p = new ProductDAO();
            try {
//            ArrayList<Product> products = new ProductDAO().search(s);    
                int count = p.count(search2);
                if (count == 0) {
                    //resp.sendRedirect("index.jsp");
                    req.setAttribute("msg", "Your information which you SEARCH is not available");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
                int indexS;
                String inSS = req.getParameter("indexS");
                if (inSS == null) {
                    indexS = 1;
                } else {
                    indexS = Integer.parseInt(inSS);
                }

                int sizePage = 8;
                int endPage = 0;
                endPage = count / sizePage;
                if (count % sizePage != 0) {
                    endPage++;
                }

                ArrayList<Product> products = p.searchPageSize(search2, indexS, sizePage);

                req.setAttribute("products", products);

                req.setAttribute("endPage", endPage);
                req.setAttribute("search2", search2);
                req.getRequestDispatcher("ListProSearch.jsp").forward(req, resp);
            } catch (Exception e) {
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("prod") == null) {
            String txtSearch = req.getParameter("txtSearch");
            String search2 = req.getParameter("search");
            String nameCate = req.getParameter("nameCate");
            if (search2 == null && txtSearch != null && nameCate == null) {
                
                ProductDAO p = new ProductDAO();
                try {
//            ArrayList<Product> products = new ProductDAO().search(s);    
                    int count = p.count(txtSearch);
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

                    ArrayList<Product> products = p.searchPageSize(txtSearch, index, sizePage);

                    req.setAttribute("product", products);
                    req.getSession().setAttribute("productsAmin", products);
                    req.setAttribute("endPage", endPage);
                    req.setAttribute("txtSearch", txtSearch);
                    req.getRequestDispatcher("SearchAllProduct.jsp").forward(req, resp);
                } catch (Exception e) {
                }
            } else if (txtSearch == null && search2 != null && nameCate == null) {
                ProductDAO p = new ProductDAO();
                try {
//            ArrayList<Product> products = new ProductDAO().search(s);    
                    int count = p.count(search2);
                    if (count == 0) {
                        req.setAttribute("msg", "0 Produdcts to be found");
                        req.getRequestDispatcher("ListProSearch.jsp").forward(req, resp);
                        //req.getRequestDispatcher("list-product").forward(req, resp);
                    }

                    String inS = req.getParameter("indexS");
                    int index = Integer.parseInt(inS);
                    int sizePage = 8;
                    int endPage = 0;
                    endPage = count / sizePage;
                    if (count % sizePage != 0) {
                        endPage++;
                    }

                    ArrayList<Product> products = p.searchPageSize(search2, index, sizePage);

                    req.setAttribute("products", products);
                    //req.getSession().setAttribute("productsIndex", products);
                    req.setAttribute("endPage", endPage);
                    req.setAttribute("search2", search2);
                    req.getRequestDispatcher("ListProSearch.jsp").forward(req, resp);
                } catch (Exception e) {
                }
            } else if (txtSearch == null && search2 == null && nameCate != null) {
                ProductDAO p = new ProductDAO();
                try {
//            ArrayList<Product> products = new ProductDAO().search(s);    
                    int count = p.count(nameCate);
                    if (count == 0) {
                        req.setAttribute("msg", "Your information which you SEARCH is not available");
                        req.getRequestDispatcher("index.jsp").forward(req, resp);
                        //req.getRequestDispatcher("list-product").forward(req, resp);
                    }
                    int index;
                    String inS = req.getParameter("indexSS");
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

                    ArrayList<Product> products = p.searchPageSize(nameCate, index, sizePage);

                    req.setAttribute("products", products);
                    req.getSession().setAttribute("productsAmin", products);
                    req.setAttribute("endPage", endPage);
                    req.setAttribute("nameCate", nameCate);
                    req.getRequestDispatcher("SearchAllProduct.jsp").forward(req, resp);
                } catch (Exception e) {
                }
            }

        } else {
            
            String filter = (String) req.getSession().getAttribute("ddlCategory");
            String nameCate = req.getParameter("nameCate");
            ArrayList<Product> products = new ProductDAO().searchFilterProduct(filter, nameCate);
            
            req.setAttribute("product", products);
            req.getSession().removeAttribute("prod");
            req.getRequestDispatcher("Filter_Search_Paging.jsp").forward(req, resp);

        }
    }

}
