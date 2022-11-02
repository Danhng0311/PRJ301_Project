<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <% String path = request.getContextPath();%>
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
        <style>

            /*Clearing Floats*/
            .cf:before, .cf:after{
                content:"";
                display:table;
            }

            .cf:after{
                clear:both;
            }

            .cf{
                zoom:1;
            }
            /* Form wrapper styling */

            .search-wrapper {
                width: 650px;
                margin: 45px auto 50px 10px;
                border-radius: 20px;
                background: transparent;
                box-shadow: 0 4px 20px -2px #e9e9e9;

            }

            /* Form text input */

            .search-wrapper input {
                padding-left: 20px;
                width: 500px;
                height: 20px;
                padding: 10px 20px;
                float: left;
                font: bold 13px 'lucida sans', 'trebuchet MS', 'Tahoma';
                border: 0;
                background: #fff;
                border-radius: 40px;
                border-top-style: none;

            }

            .search-wrapper input:focus {
                outline: 0;
                background: #fff;
                box-shadow: 0 0 2px rgba(0,0,0,0.8) inset;
            }

            .search-wrapper input::-webkit-input-placeholder {
                color: #999;
                font-weight: normal;
                font-style: italic;
                padding-left: 20px;
            }

            .search-wrapper input:-moz-placeholder {

                color: #999;
                font-weight: normal;
                font-style: italic;
            }

            .search-wrapper input:-ms-input-placeholder {
                color: #999;
                font-weight: normal;
                font-style: italic;
                border-style: none;
            }

            /* Form submit button */
            .search-wrapper button {
                overflow: visible;
                position: relative;
                float: right;
                border: 0;
                padding: 0;
                cursor: pointer;
                height: 40px;
                width: 110px;
                font: 13px/40px 'lucida sans', 'trebuchet MS', 'Tahoma';
                color: #fff;
                text-transform: uppercase;
                background: #198cff;
                border-radius: 40px;
                text-shadow: 0 -1px 0 rgba(0, 0 ,0, .3);

            }

            .search-wrapper button:hover{
                /*     background: #e54040; */
            }

            .search-wrapper button:active,
            .search-wrapper button:focus{
                background: #198cff;
                outline: 0;
            }

            .search-wrapper button:focus:before,
            .search-wrapper button:active:before{
                border-right-color: #c42f2f;
            }

            .search-wrapper button::-moz-focus-inner { /* remove extra button spacing for Mozilla Firefox */
                border: 0;
                padding: 0;
            }
            h1 a{
                text-decoration:none;
            }
            .pagination {
                display: inline-block;

            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border-radius: 5px;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%=path%>/index.jsp"><img src="<%=path%>/img/logo.png"/></a>
                </div>
                <div>
                    <form action="search_product?indexS=1" method="post" class="search-wrapper cf">
                        <div>
                            <input type="text" name="search" size="40" width="100" placeholder="Text..." style="box-shadow: none"/>
                            <button type="submit">SEARCH</button>     
                        </div>
                    </form>
                    
                </div>
                <div id="banner">
                    <ul>
                        <c:set var="size" value="${sessionScope.size}"/>
                        <%
                            if(session.getAttribute("size")==null){
                        %>

                        <li><a href="<%=path%>/nullCart.jsp">Cart: ${size}</a></li>
                            <% }else{
                            int aC = (int)request.getSession().getAttribute("size");
                            if(aC != 0) {
                            %>        
                        <li><a href="<%=path%>/cart.jsp">Cart: ${size}</a></li>

                        <% } else {
                           
                        %>
                        <li><a href="<%=path%>/nullCart.jsp">Cart: ${size}</a></li>
                            <% } 
                            }
                            %>

                        <%
                            if(session.getAttribute("AccSession")==null){
                        %>
                        <li><a href="<%=path%>/account/signin" >SignIn</a></li>
                        <li><a href="<%=path%>/account/signup" >SignUp</a></li>
                            <% }else{
                           
                            %>
                        <li><a href="<%=path%>/account/profile">Profile</a></li>
                        <li><a href="<%=path%>/account/signin">SignOut</a></li>
                            <%
                                         }
                            %>
                    </ul>
                </div>
            </div>
