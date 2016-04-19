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
        <link rel="stylesheet" type="text/css" href="css/basecss.css"/>
        <link rel="stylesheet" type="text/css" href="css/dropdown.css"/>
        <link rel="shortcut icon" href="../images/favicon.png"/> 
        <script src="js/globalimage.js" type="text/javascript"></script>
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
               <a href="organization.jsp"><div class="nav_tab fleft"> Organizations and Groups </div></a>
               <div class="nav_tab fleft">
                   <button onclick="showDropDown('templateDropdown')" class="dropbtn">Templates</button>
                   <div id="templateDropdown" class="dropdown-content">
                       <a href="emailtemplates.jsp">Email Templates</a>
                       <a href="emailblockmodels.jsp">Email Block Templates</a>
                       <a href="recurringemails.jsp">Email Automation Templates</a>
                       <a href="#">Image Templates</a>
                       <a href="printtemplates.jsp">Print Templates</a>
                   </div>
               </div>
               <div class="nav_tab fleft">
                   <button onclick="showDropDown('mpDropdown')" class="dropbtn">Marketing Campaigns</button>
                   <div id="mpDropdown" class="dropdown-content">
                       <a href="marketingprogram.jsp">Campaigns</a>
                       <a href="#">Field Templates COMING SOON</a>
                   </div>
               </div>
               <div class="nav_tab fleft"> 
                   <button onclick="showDropDown('assetDropdown')" class="dropbtn">Assets</button>
                   <div id="assetDropdown" class="dropdown-content">
                       <a href="globalimage.jsp">Images</a>
                       <a href="assets.jsp">Color Themes</a>
                       <a href="globalfonts.jsp">Fonts</a>
                   </div>
               </div>
               <div class="nav_tab-logout fright cursorpointer"> Logout </div>
               <a href="emailblockmodels.jsp"><div class="nav_tab fleft"> Email Blocks</div></a>
           </div>
       </div>
       <div class="nav-offset"></div>
   </body>
</html>
