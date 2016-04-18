<%-- 
    Document   : adminheader
    Created on : Mar 2, 2016, 2:04:01 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>       
        <link rel="shortcut icon" href="images/favicon.png"/> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <meta name="format-detection" content="telephone=no">
        <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
        <link rel="stylesheet" type="text/css" href="css/normalize.css">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="admincss/basecss.css"/>
        <link rel="shortcut icon" href="../images/favicon.png"/> 
        <script src="js/globalimage.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/basecss.css"/>
        <script src="js/organization.js" type="text/javascript"></script>
        <script src="js/configurations.js" type="text/javascript"></script>   
        <script src="js/basejs.js" type="text/javascript"></script>
         <script src="js/socialtemplates.js" type="text/javascript"></script>
         <script src="js/alertmessage.js" type="text/javascript"></script>
         <script src="js/emailtemplate.js" type="text/javascript"></script>
         <script src="js/emailblockmodels.js" type="text/javascript"></script>
           <script src="js/assets.js" type="text/javascript"></script>
          <script src="js/recurringemail.js" type="text/javascript"></script>
          <script src="js/imageToBase64Converter.js" type="text/javascript"></script>
         <script src="js/marketingprogramdetails.js" type="text/javascript"></script>
           <script src="js/allcompanies.js" type="text/javascript"></script>

   </head>
    <body>
       <div class="nav ">
        <div class="logodiv nav_logo fleft">
            <img  src="images/main-admin-logo.svg" class="bb-logo" >
        </div>
        <div class="headernav">
            <a href="allcompanies.jsp"><div class="nav_tab fleft"> Users </div></a>
            <a href="organization.jsp"><div class="nav_tab fleft"> Organizations </div></a>
            <a href="emailtemplates.jsp"><div class="nav_tab fleft"> Email Templates </div></a>
            <a href="emailblockmodels.jsp"><div class="nav_tab fleft"> Email Blocks</div></a>
            <a href="printtemplates.jsp"><div class="nav_tab fleft"> Print Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Image Templates </div></a>
            <a href="marketingprogram.jsp"><div class="nav_tab fleft"> Marketing Programs </div></a>
            <a href="assets.jsp"><div class="nav_tab fleft"> Assets </div></a>
            <a href="recurringemails.jsp"><div class="nav_tab fleft"> Recurring Email</div></a>
        </div>
       </div>
        <div class="nav-offset"></div>
    </body>
</html>
