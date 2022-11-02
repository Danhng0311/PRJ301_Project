<%@include file="template/header.jsp" %>
<div id="content">

    <div id="form">
        <div id="form-title">
            <span><a href="signup" style="color: red;">SIGN UP</a></span>
            <span><a href="signin">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <form action="signup" method="post">
                <label>Company name<span style="color: red;">*</span></label><br/>
                <input type="text" name="CompanyName" id="CompanyName" value="${CompanyName}"/><br/>
                <%
                    if(request.getAttribute("msgCPN") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgCPN"));%>
                </span><br/>
                <%}%>
                <label>Contact name<span style="color: red;">*</span></label><br/>
                <input type="text" name="Contactname" id="Contactname" value="${Contactname}"/><br/>
                <%
                    if(request.getAttribute("msgCTN") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgCTN"));%>
                </span><br/>
                <%}%>
                <label>Contact title<span style="color: red;">*</span></label><br/>
                <input type="text" name="Contacttitle" id="Contacttitle" value="${Contacttitle}"/><br/>
                <%
                    if(request.getAttribute("msgCTT") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgCTT"));%>
                </span><br/>
                <%}%>
                <label>Address<span style="color: red;">*</span></label><br/>
                <input type="text" name="Address" id="Address" value="${Address}"/><br/>
                <%
                    if(request.getAttribute("msgADR") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgADR"));%>
                </span><br/>
                <%}%>
                
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="Email" id="Email" value="${Email}"/><br/>
                <%
                    if(request.getAttribute("msgE") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgE"));%>
                </span><br/>
                <%}%>
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="Password" id="Password" value="${Password}"/><br/>
                <%
                    if(request.getAttribute("msgP") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgP"));%>
                </span><br/>
                <%}%>
                <label>Re-Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="Re-Password" id="Re-Password"/><br/>
                <%
                    if(request.getAttribute("msgRP") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgRP"));%>
                </span><br/>
                <%}%>
                <div></div>
                <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
                <script src="../js/signupAcc.js" type="text/javascript"></script>
</div>
<%@include file="template/footer.jsp" %>



