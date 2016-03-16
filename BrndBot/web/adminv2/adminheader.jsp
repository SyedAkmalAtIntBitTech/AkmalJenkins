<%-- 
    Document   : adminheader
    Created on : Mar 2, 2016, 2:04:01 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="shortcut icon" href="../images/favicon.png"/>        
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
       <script type="text/javascript" src="../js/angular.min.js"></script>
       <meta name="format-detection" content="telephone=no">
       <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
       <link rel="stylesheet" type="text/css" href="admincss/normalize.css">
       <link rel="stylesheet" type="text/css" href="admincss/admin.css">
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" type="text/css" href="admincss/basecss.css"/>
       <script src="../adminv2/adminjs/organization.js" type="text/javascript"></script>
       <script src="../js/configurations.js" type="text/javascript"></script>   
        <script src="../adminv2/adminjs/basejs.js" type="text/javascript"></script>
         <script src="../adminv2/adminjs/emailtemplate.js" type="text/javascript"></script>
        
       <title>Admin Header</title>
   </head>
    <body>
       <div class="nav ">
        <div class="logodiv nav_logo fleft">
            <img  src="../images/Icons/Logo_Reverse.svg" class="bb-logo" style="cursor:pointer;height:35px;">
        </div>
        <div class="headernav">
            <a href="allcompanies.jsp"><div class="nav_tab fleft"> Users </div></a>
            <a href="organization.jsp"><div class="nav_tab fleft"> Organizations </div></a>
            <a href="emailtemplates.jsp"><div class="nav_tab fleft"> Email Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Email Blocks</div></a>
            <a href="#"><div class="nav_tab fleft"> Print Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Image Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Marketing Programs </div></a>
            <a href="#"><div class="nav_tab fleft"> Assets </div></a>
            <a href="#"><div class="nav_tab fleft"> Recurring Email</div></a>
        </div>
       </div>
    </body>
</html>
