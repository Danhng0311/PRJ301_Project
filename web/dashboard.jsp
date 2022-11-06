<%@include file="template/header_Admin.jsp" %>
<%
    request.setAttribute("cD", new CustomersDAO());
    request.setAttribute("accD", new AccountDAO());
    request.setAttribute("oD", new OrdersDAO());
    ArrayList<Order_Detail> odt = new Order_DetailsDAO().getWeeklySaleByOrdDet();
    double sum =0;
    for(Order_Detail o: odt) {
        sum += (o.getUnitPrice() * o.getQuantity());
    }
    DecimalFormat df = new DecimalFormat("$#0.00"); 

    ArrayList<Order_Detail> odtAll = new Order_DetailsDAO().getAllOrder_Details();
    double sumAll = 0;
    for(Order_Detail oA: odtAll) {
        sumAll += (oA.getUnitPrice() * oA.getQuantity());
    }
    
%>
<div id="content-right">
    <div class="path-admin">DASHBOARD</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="dashboard-1">
                <div id="dashboard-1-container">
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Weekly Sales</div>
                        <div class="dashboard-item-content"><%=df.format(sum)%></div>


                    </div>
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Total Orders</div>
                        <div class="dashboard-item-content"><%=df.format(sumAll)%></div>
                    </div>
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Total Customers</div>
                        <div class="dashboard-item-content">${cD.getCountAllCus()}</div>
                    </div>
                    <div class="dashboard-item">
                        <div class="dashboard-item-title">Total Guest</div>
                        <div class="dashboard-item-content">${cD.getCountAllCus() - accD.getCountOfAllAccount()}</div>
                    </div>
                </div>
            </div>
            <div id="dashboard-2">

                <div id="chart" style="text-align: center;">

                    <div id="chart1">
                        <form action="filterx" method="post">
                            <select name="yearF">
                                <%                             
                                request.setAttribute("odd2", new OrdersDAO());
                                %> 
                                <c:forEach items="${odd2.getOrdersByYears()}" var="a">
                                    <option value="${a}" <c:if test="${a eq yearF}">selected</c:if>>${a}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Filter">
                        </form>
                        <h3>Statistic Orders (Month)</h3>
                        <canvas id="myChart1" style="width: 100%;"></canvas>
                    </div>
                    <div id="chart2">
                        <canvas id="myChart2" style="width: 80%;"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="template/footer_Admin.jsp" %>
<script>
    function OrdersChart() {
        var xValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    <c:if test="${yearF == null}">
        new Chart("myChart1", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                        data: [${oD.countOrderByMonth(1, 1996)}, ${oD.countOrderByMonth(2, 1996)}, ${oD.countOrderByMonth(3, 1996)},
        ${oD.countOrderByMonth(4, 1996)}, ${oD.countOrderByMonth(5, 1996)}, ${oD.countOrderByMonth(6, 1996)},
        ${oD.countOrderByMonth(7, 1996)}, ${oD.countOrderByMonth(8, 1996)}, ${oD.countOrderByMonth(9, 1996)},
        ${oD.countOrderByMonth(10, 1996)}, ${oD.countOrderByMonth(11, 1996)}, ${oD.countOrderByMonth(12, 1996)}],
                        borderColor: "sienna",
                        fill: true
                    }]
            },
            options: {
                legend: {display: false}
            }
        });
    }



    </c:if>
    <c:if test="${yearF != null}">
        <c:set value="${yearF}" var="y"/>
    new Chart("myChart1", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                    data: [${oD.countOrderByMonth(1, y)}, ${oD.countOrderByMonth(2, y)}, ${oD.countOrderByMonth(3, y)},
        ${oD.countOrderByMonth(4, y)}, ${oD.countOrderByMonth(5, y)}, ${oD.countOrderByMonth(6, y)},
        ${oD.countOrderByMonth(7, y)}, ${oD.countOrderByMonth(8, y)}, ${oD.countOrderByMonth(9, y)},
        ${oD.countOrderByMonth(10, y)}, ${oD.countOrderByMonth(11, y)}, ${oD.countOrderByMonth(12, y)}],
                    borderColor: "sienna",
                    fill: true
                }]
        },
        options: {
            legend: {display: false}
        }
    });
    }
    </c:if>
    function CustomersChart() {
        var xValues = ["Total", "New customer"];
        var yValues = [${cD.getCountAllCus()}, ${cD.getCountNewCus()}];
        var barColors = ["green", "red"];
        new Chart("myChart2", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                        backgroundColor: barColors,
                        data: yValues
                    }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "New Customers (30 daily Avg)"
                }
            }
        });
    }

    OrdersChart();
    CustomersChart();
</script>