<%-- 
    Document   : adminheader
    Created on : Mar 2, 2016, 2:04:01 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>       
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="../js/angular.min.js"></script>
        <meta name="format-detection" content="telephone=no">
        <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
        <link rel="stylesheet" type="text/css" href="admincss/normalize.css">
        <link rel="stylesheet" type="text/css" href="admincss/admin.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="admincss/basecss.css"/>
        <link rel="shortcut icon" href="../images/favicon.png"/> 
        <script src="../adminv2/adminjs/organization.js" type="text/javascript"></script>
        <script src="../js/configurations.js" type="text/javascript"></script>   
        <script src="../adminv2/adminjs/basejs.js" type="text/javascript"></script>
         <script src="../adminv2/adminjs/socialtemplates.js" type="text/javascript"></script>
         <script src="../js/alertmessage.js" type="text/javascript"></script>
        <title>Admin Header</title>
         <script src="../adminv2/adminjs/emailtemplate.js" type="text/javascript"></script>
         <script src="../adminv2/adminjs/emailblockmodels.js" type="text/javascript"></script>
          <script src="../adminv2/adminjs/recurringemail.js" type="text/javascript"></script>
          <script src="../adminv2/adminjs/imageToBase64Converter.js" type="text/javascript"></script>
         <script src="../adminv2/adminjs/marketingprogramdetails.js" type="text/javascript"></script>
           <script src="../adminv2/adminjs/allcompanies.js" type="text/javascript"></script>
   </head>
    <body>
       <div class="nav ">
        <div class="logodiv nav_logo fleft">
            <img  src="../images/adminImages/main-admin-logo.svg" class="bb-logo" >
        </div>
        <div class="headernav">
            <a href="allcompanies.jsp"><div class="nav_tab fleft"> Users </div></a>
            <a href="organization.jsp"><div class="nav_tab fleft"> Organizations </div></a>
            <a href="emailtemplates.jsp"><div class="nav_tab fleft"> Email Templates </div></a>
            <a href="emailblockmodels.jsp"><div class="nav_tab fleft"> Email Blocks</div></a>
            <a href="printtemplates.jsp"><div class="nav_tab fleft"> Print Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Image Templates </div></a>
            <a href="marketingprogram.jsp"><div class="nav_tab fleft"> Marketing Programs </div></a>
            <a href="#"><div class="nav_tab fleft"> Assets </div></a>
            <a href="recurringemails.jsp"><div class="nav_tab fleft"> Recurring Email</div></a>
        </div>
       </div>
        <div class="nav-offset"></div>
    </body>
</html>
