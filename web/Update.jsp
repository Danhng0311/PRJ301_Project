<%@page import="dal.*" %>
<%@page import="models.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>

<form action="edit" method="post">
<div id="content">
<%
            String cid = "";
            String email = "";
            String CompanyName = "";
            String ContactName = "";
            String ContactTitle = "";
            String Address = "";
            
             if(request.getSession().getAttribute("AccSession") != null) {
                  Account acc = (Account)(request.getSession().getAttribute("AccSession"));
                  cid = acc.getCustomerID();
        
            Customers cus = new CustomersDAO().getCustomersByID(cid);
            email = acc.getEmail();
            CompanyName = cus.getCompanyName();
            ContactName = cus.getContactName();
            ContactTitle = cus.getContactTitle();
            Address = cus.getAddress();
        }
    %>
    
    
        <div id="content-right">
            <div class="path">Personal information</b></div>
            <div class="content-main">
                <div id="profile-content">
                    <div class="profile-content-col">
                        <div>Company name: <br/>
                            <input type="text" name="Companyname" value="<%=CompanyName%>"><br>
                        </div>
                        <div>Contact name: <br/>
                            <input type="text" name="ContactName" value="<%=ContactName%>"><br>
                        </div>
                        <input type="submit" value="UPDATE" />
                    </div>
                    <div class="profile-content-col">
                        <div>Company title: <br/>
                            <input type="text" name="ContactTitle" value="<%=ContactTitle%>"><br>
                        </div>
                        <div>Address: <br/>
                            <input type="text" name="Address" value="<%=Address%>"><br>
                        </div>
                    </div>
                    <div class="profile-content-col">
                        <div>Email: <br/>
                            <input type="text" name="Email" value="<%=email%>"><br>

                        </div>
                    </div>
                    <div>



                    </div>
                </div>
            </div>

        </div>
    
</div>
    </form>

<%@include file="template/footer.jsp" %>
