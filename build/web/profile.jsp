<%@page import="dal.*" %>
<%@page import="models.*" %>
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

        <script type="text/javascript">

            function updateInfo(id) {
                if (confirm("Do you wanna to edit in4mation ?")) {
                    window.location = "edit?id=" + id;
                }
            }


        </script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%=path%>/index.jsp"><img src="<%=path%>/img/logo.png"/></a>
                </div>
                <div id="banner">
                    <ul>
                        <li><a href="cart.jsp">Cart: 0</a></li>
                            <%
                                    if(session.getAttribute("AccSession")==null){
                            %>
                        <li><a href="<%=path%>/account/signin">SignIn</a></li>
                        <li><a href="<%=path%>/account/signup">SignUp</a></li>
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

            <%
            String cid = "";

            String CompanyName = "";
            String ContactName = "";
            String ContactTitle = "";
            String Address = "";

             if(request.getSession().getAttribute("AccSession") != null) {
                  Account acc = (Account)(request.getSession().getAttribute("AccSession"));
                  cid = acc.getCustomerID();
        
            Customers cus = new CustomersDAO().getCustomersByID(cid);
        
            CompanyName = cus.getCompanyName();
            ContactName = cus.getContactName();
            ContactTitle = cus.getContactTitle();
            Address = cus.getAddress();
        }
            %>
            <div id="content">
                <div id="content-left">
                    <h3 style="font-weight: normal;">Welcome <%= ContactName%></h3>
                    <h3>Account Management</h3>
                    <ul>
                        <a href="<%=path%>/account/profile"><li>Personal information</li></a>
                    </ul>
                    <h3>My order</h3>
                    <ul>
                        <a href="<%=path%>/account/allorder"><li>All orders</li></a>
                        <a href="<%=request.getContextPath()%>/cancel_ord_profile.jsp"><li>Canceled order</li></a>
                    </ul>
                </div>
                <div id="content-right">

                    <div class="path">Personal information</b></div>
                    <div class="content-main">
                        <div id="profile-content">
                            <div class="profile-content-col">
                                <div>Company name: <br/><%= CompanyName%></div>
                                <div>Contact name: <br/><%= ContactName%></div>
                                <div>

                                    <input type="submit" value="Edit info" onclick="updateInfo('<%=cid%>')"/>


                                </div>
                            </div>
                            <div class="profile-content-col">
                                <div>Company title: <br/><%= ContactTitle%></div>
                                <div>Address: <br/><%= Address%></div>
                            </div>
                            <div class="profile-content-col">
                                <%    if(request.getSession().getAttribute("AccSession") != null) {
        Account acc = (Account)(request.getSession().getAttribute("AccSession"));%>
                                <div>Email: <br/><%= acc.getEmail()%></div>
                                    <%
                                        }
                                    %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <%@include file="template/footer.jsp" %>