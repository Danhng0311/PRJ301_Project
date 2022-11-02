<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@include file="template/header.jsp" %>
<script>
    console.log("hiem");

    function aebc() {
        console.log("hiemanh");
    }
    function changeQuantityOnBlur(el) {

        let inputs = Array.from(document.getElementsByName("quantityT"));
        let num;
        inputs.forEach(input => {
            if (input.id == el.id) {
                num = input.value;
                return;
            }
        });
        let id = el.id;
        window.location = "addToCart?idC=" + id + "&num=" + num;
    }
</script>
<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART</h3>
        </div>
        <c:choose >
            <c:when test="${sessionScope.AccSession == null}">
                <c:set value="${sessionScope.cart}" var="c"/>
                <c:forEach var="p" items="${c.items}">
                    <div id="cart-content">
                        <div class="cart-item">

                            <div class="cart-item-infor">
                                <div class="cart-item-img">
                                    <img src="img/1.jpg"/>
                                </div>
                                <div class="cart-item-name">
                                    <a href="detail?id=${p.getProduct().getProductID()}" style="text-decoration: none">${p.getProduct().getProductName()}</a>
                                </div>
                                <div class="cart-item-price">
                                    ${p.getProduct().getUnitPrice() * p.quantity}$
                                </div>
                                <div class="cart-item-button">
                                    <!--<form action="amount" method="post">-->
                                    <a href="remove?id=${p.getProduct().getProductID()}">Remove</a>

                                    <!--</form>-->
                                </div>
                            </div>
                            <div class="cart-item-function">
                                <a href="amount?num=-1&id=${p.getProduct().getProductID()}">-</a>  
                                <a href="amount?num=1&id=${p.getProduct().getProductID()}">+</a>
                                <input id="${p.getProduct().getProductID()}" type="number" name="quantityT" onblur="changeQuantityOnBlur(this)" value="${p.quantity}"/>

                            </div>
                        </div>

                    </div>
                </c:forEach>
            </c:when>

            <c:when test="${sessionScope.AccSession != null}">

                <c:set value="${sessionScope.cart}" var="c"/>
                <c:forEach var="p" items="${c.items}">
                    <div id="cart-content">
                        <div class="cart-item">

                            <div class="cart-item-infor">
                                <div class="cart-item-img">
                                    <img src="img/1.jpg"/>
                                </div>
                                <div class="cart-item-name">
                                    <a href="detail?id=${p.getProduct().getProductID()}" style="text-decoration: none">${p.getProduct().getProductName()}</a>
                                </div>
                                <div class="cart-item-price">
                                    ${p.getProduct().getUnitPrice() * p.quantity}$
                                </div>
                                <div class="cart-item-button">
                                    <!--<form action="amount" method="post">-->
                                    <a href="remove?id=${p.getProduct().getProductID()}">Remove</a>

                                    <!--</form>-->
                                </div>
                            </div>
                            <div class="cart-item-function">
                                <a href="amount?num=-1&id=${p.getProduct().getProductID()}">-</a>  
                                <a href="amount?num=1&id=${p.getProduct().getProductID()}">+</a>
                                <div>
                                    <input id="${p.getProduct().getProductID()}" type="number" name="quantityT" onblur="changeQuantityOnBlur(this)" value="${p.quantity}"/>

                                </div>
                            </div>
                        </div>

                    </div>
                </c:forEach>
            </c:when>

        </c:choose>
        <div id="cart-summary">
            <div id="cart-summary-content">Total price: <span style="color:red">
                    <c:out value="${t}"/>
                </span></div>
        </div>
        <form action="signup_temporary" method="post">
            <div id="customer-info">
                <div id="customer-info-content">
                    <h3>CUSTOMER INFORMATION:</h3>
                    <div id="customer-info-detail">
                        <c:choose >
                            <c:when test="${sessionScope.AccSession != null}">
                                <%
                            String cid = "";
                            String CompanyName = "";
                            String ContactName = "";
                            String ContactTitle = "";
                            String Address = "";
                            String date = "";

                             if(request.getSession().getAttribute("AccSession") != null) {
                                  Account acc = (Account)(request.getSession().getAttribute("AccSession"));
                                  cid = acc.getCustomerID();

                            Customers cus = new CustomersDAO().getCustomersByID(cid);

                            CompanyName = cus.getCompanyName();
                            ContactName = cus.getContactName();
                            ContactTitle = cus.getContactTitle();
                            Address = cus.getAddress();
                            date = cus.getDate();
                                %>
                                <div id="customer-info-left">
                                    <input type="text" name="comName" value="<%=CompanyName%>" disabled/><br/>
                                    <input type="text" name="contName" value="<%=ContactName%>" disabled/><br/>

                                    <input type="date" name="txtRequiredDate" /><br/>
                                    <%
                                    if(request.getAttribute("msgRD") != null) {
                                    %>              
                                    <span class="msg-error">
                                        <% out.println(request.getAttribute("msgRD"));%>
                                    </span><br/>
                                    <%}%>

                                </div>
                                <div id="customer-info-right">
                                    <input type="text" name="cTitle" value="<%=ContactTitle%>" disabled/><br/>
                                    <input type="text" name="address" value="<%=Address%>" disabled/><br/>
                                </div>
                                <%
                            }
                                %>
                            </c:when>
                            <c:when test="${sessionScope.AccSession == null}">
                                <div id="customer-info-left">
                                    <input type="text" placeholder="Company name *" name="comName2" value="${CompanyName}"/><br/>
                                    <%
                                    if(request.getAttribute("msgCPN") != null) {
                                    %>              
                                    <span class="msg-error">
                                        <% out.println(request.getAttribute("msgCPN"));%>
                                    </span><br/>
                                    <%}%>
                                    <input type="text" placeholder="Contact name *" name="contName2" value="${Contactname}"/><br/>
                                    <%
                                    if(request.getAttribute("msgCTN") != null) {
                                    %>              
                                    <span class="msg-error">
                                        <% out.println(request.getAttribute("msgCTN"));%>
                                    </span><br/>
                                    <%}%>
                                    <input type="date" name="txtRequiredDate2"/><br/>
                                    <%
                                    if(request.getAttribute("msgRD") != null) {
                                    %>              
                                    <span class="msg-error">
                                        <% out.println(request.getAttribute("msgRD"));%>
                                    </span><br/>
                                    <%}%>
                                </div>
                                <div id="customer-info-right">
                                    <input type="text" placeholder="Contact title *" name="cTitle2" value="${Contacttitle}"/><br/>
                                    <%
                                    if(request.getAttribute("msgCTT") != null) {
                                    %>              
                                    <span class="msg-error">
                                        <% out.println(request.getAttribute("msgCTT"));%>
                                    </span><br/>
                                    <%}%>
                                    <input type="text" placeholder="Address *" name="address2" value="${Address}"/><br/>
                                    <%
                                    if(request.getAttribute("msgADR") != null) {
                                    %>              
                                    <span class="msg-error">
                                        <% out.println(request.getAttribute("msgADR"));%>
                                    </span><br/>
                                    <%}%>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div id="customer-info">
                <div id="customer-info-content">
                    <h3>PAYMENT METHODS:</h3>
                    <div id="customer-info-payment">
                        <div>
                            <input type="radio" name="rbPaymentMethod" checked/>
                            Payment C.O.D - Payment on delivery
                        </div>
                        <div>
                            <input type="radio" name="rbPaymentMethod" disabled/>
                            Payment via online payment gateway
                        </div>
                    </div>
                </div>
            </div>

            <div id="cart-order">
                <input type="submit" value="ORDER"/>
            </div>
        </form>

    </div>
</div>


<%@include file="template/footer.jsp" %>


