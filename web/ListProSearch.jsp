<%-- 
    Document   : ListProSearch
    Created on : Oct 19, 2022, 7:29:58 PM
    Author     : Anh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>

<!--<div id="content">-->
<div id="content-right">
    <div class="path">List of Products after Search</div>

    <div class="content-main2">
        <%
        if(request.getAttribute("msg") != null) {
        %> 
        <span class="msg-error">
            <img style="width: 100%" src="img/product_not_found2.png" alt=""/>
            
        </span><br/>
        <%}%>
        <c:forEach items="${products}" var="p">
            <div class="product">
                <a href="detail?id=${p.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                <div class="name" ><a href="detail?id=${p.getProductID()}">${p.getProductName()}</a></div>
                <div class="price" >${p.getUnitPrice()}$</div>
                <div >
                    <div><a href="buy?id=${p.getProductID()}">Buy now</a></div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="paging">
        <div class="pagination">
            <c:forEach begin="1" end="${endPage}" var="i">
                <a href="search_product?indexS=${i}&search2=${search2}">${i}</a>
            </c:forEach>
        </div>
    </div>
</div>
<!--</div>-->

<%@include file="template/footer.jsp" %>
