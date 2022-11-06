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
        <div class="path">Hot</b></div>
        <div class="content-main2">


            <%
                ArrayList<Product> listP = new ProductDAO().getProductsOrderDetail();
                for(Product p: listP) {
            %> 

            <div class="product">

                <a href="detail?id=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail?id=<%=p.getProductID()%>"  style="text-decoration: none"><%=p.getProductName()%></a></div>
                <div class="price" >$<%=p.getUnitPrice()%></div>
                <div>                   
                    <div>
                        <form action="buy?idB=<%=p.getProductID()%>" method="post">
                            <button type="submit" class="btn btn-primary" style="border-color: #f5f5f5; background-color: #f5f5f5">Buy Now</button>
                        </form>

                    </div>
                </div>
            </div>

            <%
          
       }
            %> 


        </div>
        <div class="path">Best Sale</b></div>
        <div class="content-main2">
            <%
    ArrayList<Product> listPB = new ProductDAO().getProductsOrderDetailBestSale();
    for(Product p: listPB) {
            %> 
            <div class="product">
                <a href="detail?id=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail?id=<%=p.getProductID()%>"  style="text-decoration: none"><%=p.getProductName()%></a></div>
                <div class="price">$<%=p.getUnitPrice()%></div>
                <form action="buy?idB=<%=p.getProductID()%>" method="post">
                    <button type="submit" class="btn btn-primary" style="border-color: #f5f5f5; background-color: #f5f5f5">Buy Now</button>
                </form>
            </div>
            <%
          
       }
            %>
        </div>
        <div class="path">New Product</b></div>
        <div class="content-main">
            <%
    ArrayList<Product> listN = new ProductDAO().getProductsNew();
    for(Product p: listN) {
            %>
            <div class="product">
                <a href="detail?id=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail?id=<%=p.getProductID()%>"  style="text-decoration: none"><%=p.getProductName()%></a></div>
                <div class="price">$<%=p.getUnitPrice()%></div>
                <form action="buy?idB=<%=p.getProductID()%>" method="post">
                    <button type="submit" class="btn btn-primary" style="border-color: #f5f5f5; background-color: #f5f5f5">Buy Now</button>
                </form>
            </div>
            <%
          
                  }
            %>
        </div>
    </div>
</div>
<div id="footer">footer</div>
</div>
</body>

</html>

