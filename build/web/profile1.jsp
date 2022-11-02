<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<%
            String cid = "";
            String ContactName = "";
            String date = "";
            int i = 0;
                    int j = 0;
                    int c = 1;
             if(request.getSession().getAttribute("AccSession") != null) {
                Account acc = (Account)(request.getSession().getAttribute("AccSession"));
                cid = acc.getCustomerID();
                Customers cus = new CustomersDAO().getCustomersByID(cid);
                ContactName = cus.getContactName();
                date = cus.getDate(); 
        }
        request.setAttribute("ProdDAO", new ProductDAO());
            
%>
<div id="content">
    <div id="content-left">
        <h3 style="font-weight: normal;">Welcome <%= ContactName%> </h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<%=request.getContextPath()%>/account/profile"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="<%=request.getContextPath()%>/account/allorder"><li>All orders</li></a>
            <a href="<%=request.getContextPath()%>/cancel_ord_profile.jsp"><li>Canceled order</li></a>
        </ul>
    </div>

    <div id="content-right">
        <div class="path">LIST ORDERS</b></div>
        <div class="content-main">
            <div id="profile-content-order">
                <c:forEach items="${sessionScope.listO}" var="odd">
                    <c:if test="${odd.getRequiredDate() != null}">
                        <div>
                            <div class="profile-order-title">
                                <div class="profile-order-title-left">
                                    <div>Order creation date: ${odd.getOrderDate()}</div>
                                    <div>Order: <a href="#">#${odd.getOrderID()}</a></div>
                                </div>
                                <div class="profile-order-title-right">
                                    <span>Pending /</span>
                                    <span style="color: black"><a href="<%=request.getContextPath()%>/cancel_op?odtID=${odd.getOrderID()}">Cancel</a></span>
                                </div>
                            </div>
                            <c:forEach items="${listOrdd}" var="od2">
                                <c:set value="${p}" var="p"/>
                                <c:if test="${od2.getOrderID() == odd.getOrderID()}">
                                    <div class="profile-order-content">
                                        <div class="profile-order-content-col1">
                                            <a href="#"><img src="<%=request.getContextPath()%>/img/2.jpg" width="100%"/></a>
                                        </div>
                                        <div class="profile-order-content-col2">${ProdDAO.getProductByID(od2.getProductID()).getProductName()}</div>
                                        <div class="profile-order-content-col3">${od2.getQuantity()}</div>
                                        <div class="profile-order-content-col4">${od2.getUnitPrice()}$</div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach items="${sessionScope.listO}" var="odd">
                    <%--<c:set value="${cus}" var="lod"/>--%>
                    <c:if test="${odd.getShippedDate() != null}">
                        <div>
                            <div class="profile-order-title">
                                <div class="profile-order-title-left">
                                    <div>Order creation date: ${odd.getOrderDate()}</div>
                                    <div>Order: <a href="#">#${odd.getOrderID()}</a></div>
                                </div>
                                <div class="profile-order-title-right">
                                    <span style="color: green">Completed</span>
                                </div>

                            </div>
                            <c:forEach items="${listOrdd}" var="od2">
                                <c:set value="${p}" var="p"/>
                                <c:if test="${od2.getOrderID() == odd.getOrderID()}">
                                    <div class="profile-order-content">
                                        <div class="profile-order-content-col1">
                                            <a href="#"><img src="<%=request.getContextPath()%>/img/2.jpg" width="100%"/></a>
                                        </div>
                                        <div class="profile-order-content-col2">${ProdDAO.getProductByID(od2.getProductID()).getProductName()}</div>
                                        <div class="profile-order-content-col3">${od2.getQuantity()}</div>
                                        <div class="profile-order-content-col4">${od2.getUnitPrice()}$</div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
