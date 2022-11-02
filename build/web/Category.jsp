<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>


<%@include file="template/header.jsp" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>
        <ul>
            <%
        ArrayList<Category> list = new CategoryDAO().getProducts();
        for(Category c: list) {
            %> 
            <a href="ProductList?CategoryId=<%= c.getCategoryID()%>"><li><%= c.getCategoryName()%></li></a>
                    <%
          
               }
                    %>
        </ul>
    </div>
    <div id="content-right">
        <%
            String nameC = (String)request.getAttribute("name");
            String desC = (String)request.getAttribute("des");
            //ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("ProList2");
            ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("ProList2");
            ArrayList<Product> listP3 = new ProductDAO().getProductsOrderDetail();
        %>
        <div class="path"><%= nameC%></b></div>
        <div class="content-main2">
            <%
           int size = 0;
           for(Product item: productList){             
            %>
            <div class="product">
                <a href="detail?id=<%=item.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail?id=<%=item.getProductID()%>"><%=item.getProductName()%></a></div>
                <div class="price">$<%=item.getUnitPrice()%></div>

                <div class="buyy"><a href="buy?id=<%=item.getProductID()%>">Buy now</a></div>

            </div>
            <%                 
                }
            %>
        </div>
    </div>
</div>
</div>
<%@include file="template/footer.jsp" %>