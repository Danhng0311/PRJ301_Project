<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <link href="css/style.css" rel="stylesheet"/>
        <script>
            function Al() {
                if(confirm("Are you sure?")) {
                    alert("Your new PassWord is : 123@123a");
                }
            }
            
        </script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="index.jsp"><img src="img/logo.png"/></a>
                </div>
                <div id="banner">
                    <ul>
                        <li><a href="cart.jsp">Cart: 0</a></li>
                        <li><a href="signin.jsp">SignIn</a></li>
                        <li><a href="signup.jsp">SignUp</a></li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="form">
                    <h3 style="padding: 20px;">Forgot your account password?</h3>
                    <div style="padding: 0px 20px 10px;">
                        Please enter the email address registered with us to create a new password. We will send an email to the email address provided and require verification before we can generate a new password
                    </div>
                    <div id="form-content">
                        <form action="renew_password" method="post">
                            <label>Enter your registered email address<span style="color: red;">*</span></label><br/>
                            <input type="text" name="emailF"/><br/>
                            <%
                            if(request.getAttribute("msgE") != null) {
                            %>              
                            <span class="msg-error">
                                <% out.println(request.getAttribute("msgE"));%>
                            </span><br/>
                            <%}%>
                            <input type="submit" value="GET PASSWORD" onclick="Al()" style="margin-bottom: 30px;"/><br/>
                        </form>
                    </div>
                </div>
            </div>
            <div id="footer">footer</div>
        </div>
    </body>
</html>