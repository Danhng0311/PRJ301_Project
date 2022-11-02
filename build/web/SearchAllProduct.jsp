<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <link href="css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo-admin">
                    Ecommerce Admin
                </div>
                <div id="banner-admin">
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/account/signin">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="content-left">
                    <ul>
                        <a href="dashboard.jsp"><li>Dashboard</li></a>
                        <a href="order.jsp"><li>Orders</li></a>
                        <a href="product.jsp"><li>Products</li></a>
                        <a href="#"><li>Customers</li></a>
                    </ul>
                </div>
                <div id="content-right">
                    <div class="path-admin">PRODUCTS LIST</b></div>
                    <div class="content-main">
                        <div id="content-main-dashboard">
                            <div id="product-title-header">
                                <div id="product-title-1" style="width: 25%;">
                                    <b>Filter by Catetory:</b>

                                    <form action="manage_product" method="post">
                                        <select name="ddlCategory">
                                            <%
                                            ArrayList<Category> list = new CategoryDAO().getProducts();
                                            for(Category c: list) {
                                            %> 
                                            <option value="<%=c.getCategoryName()%>"><%=c.getCategoryName()%></option>
                                            <%
                                        }
                                            %>
                                        </select>
                                        <input type="submit" value="Filter">
                                    </form>

                                </div>

                                <div id="product-title-2" style="width: 55%;">
                                    <form action="search_product">
                                        <input type="text" name="txtSearch" placeholder="Enter product name to search"/>
                                        <input type="submit" value="Search"/>
                                    </form>


                                </div>
                                <div id="product-title-3" style="width: 20%;">
                                    <a href="create-product">Create a new Product</a>
                                    <form action="">
                                        <label for="upload-file">Import .xls or .xlsx file</label>
                                        <input type="file" name="file" id="upload-file" />
                                    </form>
                                </div>
                            </div>
                            <div id="order-table-admin">
                                <table id="orders">
                                    <tr>
                                        <th>ProductID</th>
                                        <th>ProductName</th>
                                        <th>UnitPrice</th>
                                        <th>Unit</th>
                                        <th>UnitsInStock</th>
                                        <th>Category</th>
                                        <th>Discontinued</th>
                                        <th></th>
                                    </tr>
                                    <c:forEach var="p" items="${product}">
                                        <tr>
                                            <td><a href="order-detail.html?id=5">${p.getProductID()}</a></td>
                                            <td>${p.getProductName()}</td>
                                            <td>${p.getUnitPrice()}</td>
                                            <td>pieces</td>
                                            <td>${p.getUnitsInStock()}</td>
                                            <c:forEach var="c" items="${list2}">
                                                <c:if test="${p.getCategoryID()== c.getCategoryID()}">
                                                    <td>     ${c.getCategoryName()}</td>
                                                </c:if>
                                            </c:forEach>
                                            <td>${p.isDiscontinued()}</td>
                                            <td>
                                                <a href="edit_product?id=${p.getProductID()}">Edit</a> &nbsp; | &nbsp; 
                                                <a href="delete_product?id=${p.getProductID()}">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div id="paging">
                                <div class="pagination">
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <a href="search_product?index=${i}&txtSearch=${txtSearch}">${i}</a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="footer-admin">footer</div>
        </div>
    </body>
</html>