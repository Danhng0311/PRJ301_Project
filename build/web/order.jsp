<%@include file="template/header_Admin.jsp" %>
<c:if test="${requestScope.msgC != null}">
    <script>
        alert("msgC");
    </script>
</c:if>

<%
    request.setAttribute("OrdDAO", new OrdersDAO());
    request.setAttribute("CusDAO", new CustomersDAO());
%>
<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                <b>Filter by Order date:</b>
                <form action="order_admin" method="post">
                    From: <input type="date" name="txtStartOrderDate"/>
                    To: <input type="date" name="txtEndOrderDate"/>
                    <input type="submit" value="Filter">
                </form>
            </div>
            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>Employee</th>
                        <th>Customer</th>
                        <th>Freight($)</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${ordD}" var="ord">
                        <tr>
                            <td><a href="order-detail.html?id=5">${ord.getOrderID()}</a></td>
                            <td>${ord.getOrderDate()}</td>
                            <td>${ord.getRequiredDate()}</td>
                            <td>${ord.getShippedDate()}</td>
                            <td>${OrdDAO.getEmployeeByOrdID(ord.getEmployeeID()).getFirstName()}</td>
                            <td>${CusDAO.getCustomersByID(ord.getCustomerID()).getContactName()}</td>
                            <td>${ord.getFreight()}</td>
                            <td style="color: green;">
                                <c:choose>
                                    <c:when test="${ord.getRequiredDate() == null}">
                                        <div style="color: red">Canceled</div>
                                    </c:when>
                                    <c:when test="${ord.getShippedDate() != null}">
                                        Completed
                                    </c:when>
                                    <c:otherwise>
                                        <div style="color: blue">
                                            <span>Pending /</span>
                                            <span style="color: black"><a href="<%=request.getContextPath()%>/cancel_op?odtIDByAd=${ord.getOrderID()}">Cancel</a></span>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>


                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <c:if test="${current == i}">
                            <a class="active" href="order_admin?indexO=${i}">${i}</a>
                        </c:if>

                        <c:if test="${current != i}">
                            <a  href="order_admin?indexO=${i}">${i}</a>
                        </c:if>

                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="template/footer_Admin.jsp" %>