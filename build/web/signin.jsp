<%@include file="template/header.jsp" %>
<%@page import="dal.*" %>
<div id="content">
    <div id="form">

        <div id="form-title"
             <span><a href="signup" style="color: black; text-decoration: none; font-weight: bold" >SIGN UP</a></span>
            <span><a href="signin" style="color: red;">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <center style="color:red">
                <%
                    if(request.getAttribute("msg") != null) {
                        out.println(request.getAttribute("msg"));
                    }
                %>
            </center>
            <form action="signin" method="post">
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtMail" id="mail"/><br/>
                
                <%
                    if(request.getAttribute("msgE") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgE"));%>
                </span><br/>
                <%}%>
                
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtPass" id="pass"/><br/>
                <%
                    if(request.getAttribute("msgP") != null) {
                %>              
                <span class="msg-error">
                    <% out.println(request.getAttribute("msgP"));%>
                </span><br/>
                <%}%>

                <div><a href="<%=request.getContextPath()%>/forgot.jsp">Forgot password?</a></div>
                <input type="submit" value="SIGN IN"/><br/>
                <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
            </form>

        </div>


    </div>

</div>

<%@include file="template/footer.jsp" %>