<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
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
        <c:if test="${sessionScope.role != 1 or sessionScope.role == null}">
            <%
                response.sendError(404);
            %>
        </c:if>
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
                        <a href="<%=request.getContextPath()%>/dashboard.jsp"><li>Dashboard</li></a>
                        <a href="<%=request.getContextPath()%>/order_admin"><li>Orders</li></a>
                        <a href="<%=request.getContextPath()%>/manage_product"><li>Products</li></a>
                        <a href="#" ><li>Customers</li></a>
                    </ul>
                </div>