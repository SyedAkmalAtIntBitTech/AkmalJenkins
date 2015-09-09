<%-- 
    Document   : EmailSent
    Created on : Aug 8, 2015, 4:57:08 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Email Sent</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="basejsp.jsp" />
   </head>
   <body>
        <div class="row">
        <jsp:include page="mainmenu.html"/>
        <div class="col-md-11">
              <img style="position:relative;left:0%;margin-top:9%;" src="images/Mailsent.png" width="150"/>
              <div id="text5"> Email Sent!</div> 
            </div>
    </body>
</html>
