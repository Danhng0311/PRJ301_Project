<%@include file="template/header_Admin.jsp" %>

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
                    <form action="search_product" method="post">
                        <input type="text" name="nameCate" placeholder="Enter product name to search"/>
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
                            <c:if test="${p.getProductID() == 78}">
                                <td>null</td>
                            </c:if>
                            <c:forEach var="c" items="${list2}">
                                <c:if test="${p.getCategoryID()== c.getCategoryID()}">
                                    <td>${c.getCategoryName()}</td>
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
                        <a href="manage_product?indexF=${i}">${i}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="template/footer_Admin.jsp" %>