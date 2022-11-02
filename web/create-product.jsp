<%@include file="template/header_Admin.jsp" %>
<div id="content-right">
    <div class="path-admin">CREATE A NEW PRODUCT</b></div>
    <div class="content-main">
        <form action="create-product" method="POST" id="content-main-product" >
            <div class="content-main-1">
                <label>Product name (*):</label><br/>
                <input type="text" name="txtProductName" id=""><br/>
                
                <label>Unit price:</label><br/>
                <input type="text" name="txtUnitPrice" id=""><br/>

                <label>Quantity per unit:</label><br/>
                <input type="number" name="txtQuantityPerUnit" id=""><br/>
                <label>Units in stock (*):</label><br/>
                <input type="number" name="txtUnitsInStock" id=""><br/>
                
            </div>
            <div class="content-main-1">
                <label>Category (*):</label><br/>
                <select name="categoryN">
                    <%
            ArrayList<Category> list = new CategoryDAO().getProducts();
            for(Category c: list) {
                    %> 
                    <option value="<%=c.getCategoryID()%>"><%=c.getCategoryName()%></option>
                    <%
                        }
                    %>
                </select>
                <br/>                                
                <label>Reorder level:</label><br/>
                <input type="number" name="txtReorderLevel" id=""><br/>
                <label>Units on order:</label><br/>
                <input type="number" name="txtUnitsOnOrder" id=""><br/>
                <label>Discontinued:</label><br/>
                <input type="checkbox" name="Discontinued" id=""><br/>

                <input type="submit" value="Save" />
            </div>
        </form>
    </div>
</div>
</div>
<%@include file="template/footer_Admin.jsp" %>