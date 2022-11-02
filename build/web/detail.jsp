<%@include file="template/header.jsp" %>
<div id="content">
    <div id="content-detail">
        <div id="content-title">
            <a href="index.jsp">Home</a> >
            <%
            ArrayList<Category> list = new CategoryDAO().getProducts();
            String productID = request.getParameter("model");
                        
            String proN = "";
            for(Category c: list) {
                        
            %> 
            <a href="ProductList?CategoryId=<%= c.getCategoryID()%>">Category 1</a>
            <%
                break;
                }
            %>
            Model: 1
        </div>
        <div id="product">
            <div id="product-name">
                <c:forEach var="p" items="${listP}">
                    <h2>${p.getProductName()}</h2>
                    <div id="product-detail">
                        <div id="product-detail-left">
                            <div id="product-img">
                                <img src="img/1.jpg"/>
                            </div>
                            <div id="product-img-items">
                                <div><a href="#"><img src="img/1.jpg"/></a></div>
                                <div><a href="#"><img src="img/1.jpg"/></a></div>
                                <div><a href="#"><img src="img/1.jpg"/></a></div>
                                <div><a href="#"><img src="img/1.jpg"/></a></div>
                            </div>
                        </div>



                        <div id="product-detail-right">
                            <div id="product-detail-right-content">
                                <div id="product-price">${p.getUnitPrice()}$</div>
                                <div id="product-status">
                                    <c:choose>
                                        <c:when test="${p.getUnitsInStock() > 0}">
                                            In Stock
                                        </c:when>
                                        <c:when test="${p.getUnitsInStock() == 0}">
                                            Out Of Stock
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${p.getUnitsInStock() > 0}">
                                    <div id="product-detail-buttons">
                                        <div id="product-detail-button">
                                            <form action="buy?id=${p.getProductID()}" method="post">
                                                <input type="submit" value="ADD TO CART"  style="background-color: orange; color:white;border: 1px solid gray;padding: 10px 20px;">

                                            </form>
                                        </div> 
                                        <div id="product-detail-button">
                                            <input type="submit" value="BUY NOW" onclick="buynow('${p.getProductID()}')" style="background-color: white; color:orange;border: 1px solid gray;padding: 10px 20px;">
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${p.getUnitsInStock() == 0}">
                                    <div id="product-detail-buttons">
                                        <div id="product-detail-button">
                                            <form >
                                                <input type="submit" value="ADD TO CART"  style="background-color: orange; color:white;border: 1px solid gray;padding: 10px 20px;" disabled>

                                            </form>
                                        </div> 
                                        <div id="product-detail-button">
                                            <input type="submit" value="BUY NOW" disabled style="background-color: white; color:orange;border: 1px solid gray;padding: 10px 20px;">
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>   

                        </div
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div id="info-detail">
        <div id="info-detail-title">
            <h2>Information deltail</h2>
            <div style="margin:10px auto;">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Illum, debitis. Asperiores soluta eveniet eos accusantium doloremque cum suscipit ducimus enim at sapiente mollitia consequuntur dicta quaerat, sunt voluptates autem. Quam!
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem illum autem veritatis maxime corporis quod quibusdam nostrum eaque laborum numquam quos unde eveniet aut, exercitationem voluptatum veniam fugiat, debitis esse?
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function buynow(id) {
        if (confirm("Do you wanna to buy")) {
            window.location = "buy?id=" + id;
        }
    }
</script>
<%@include file="template/footer.jsp" %>