<%@page isErrorPage="true" %>
<!doctype html>
<html>
    <head>
        <meta charset ="utf-8">
        <meta name ="viewport" content="width=device-width, intial-scale=1">
        
        <link href="<%=request.getContextPath()%>/css/erCSS.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>404 Error Page #2</h1>
        <p class="zoom-area"><b>CSS</b> animations to make a cool 404 page. </p>
        <section class="error-container">
            <span class="four"><span class="screen-reader-text">4</span></span>
            <span class="zero"><span class="screen-reader-text">0</span></span>
            <span class="four"><span class="screen-reader-text">4</span></span>
        </section>
        <div class="link-container">
            <a href="<%=request.getContextPath()%>/index.jsp" class="more-link">Visit the original page</a>
        </div>
    </body>
</html>