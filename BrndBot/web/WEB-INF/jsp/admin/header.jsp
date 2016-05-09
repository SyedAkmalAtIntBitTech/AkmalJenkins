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
        <!--<script src="js/freshurl.js" type="text/javascript"></script>-->
   </head>
   <style type="text/css">
  ul {list-style: none;padding: 0px;margin: 0px;}
  ul li {display: block;position: relative;float: left;text-align:center}
  li ul {display: none;}
  ul li a {display: block;background: #FFFFFF;text-decoration: none;
           white-space: nowrap;color: black;height: 34px;}
  ul li a:hover {}
  li:hover ul {display: block; position: absolute;}
  li:hover li {float: none;}
  li:hover a {}
  li:hover li a:hover {background:#f1f1f1;}
  #drop-nav li ul li {border-top: 0px;}
  .hoverClass{width:200px;margin-left: -64px;height: 300px;}
</style>
   <body>
       <div class="nav ">
           <div class="logodiv nav_logo fleft">
               <img  src="images/main-admin-logo.svg" class="bb-logo" >
           </div>
           <div class="headernav">
               <ul id="drop-nav" >
  <li>  <a href="${pageContext.request.contextPath}/admin/allcompanies"><div class="nav_tab fleft"> Users </div></a></li>
  <li>  <a href="${pageContext.request.contextPath}/admin/organization"><div class="nav_tab fleft"> Organizations and Groups </div></a></li>
  <div class="nav_tab fleft">
  <li><a href="#">Templates</a>
    <ul class="hoverClass">
      <li> <a href="${pageContext.request.contextPath}/admin/emailtemplates">Email Templates</a></li>
      <li> <a href="${pageContext.request.contextPath}/admin/emailblockmodels">Email Block Templates</a></li>
      <li> <a href="${pageContext.request.contextPath}/admin/recurringemails">Email Automation Templates</a></li>
      <li><a href="#">Image Templates</a></li>
      <li> <a href="${pageContext.request.contextPath}/admin/printtemplates">Print Templates</a></li>
    </ul>
  </li>
  </div>
   <div class="nav_tab fleft">
  <li><a href="#">Marketing Campaigns</a>
    <ul class="hoverClass">
      <li><a href="${pageContext.request.contextPath}/admin/marketingprogram">Campaigns</a></li>
      <li>  <a href="#">Field Templates COMING SOON</a></li>
      
    </ul>
  </li>
   </div>
  <div class="nav_tab fleft">
  <li><a href="#">Assets</a>
    <ul class="hoverClass">
      <li>  <a href="${pageContext.request.contextPath}/admin/globalimage">Images</a></li>
      <li><a href="${pageContext.request.contextPath}/admin/assets">Color Themes</a></li>
      <li> <a href="${pageContext.request.contextPath}/admin/globalfonts">Fonts</a></li>
    </ul>
  </li>
</ul>
</div>
     <a style="color: black;" href="${pageContext.request.contextPath}/logout"><div class="nav_tab-logout fright cursorpointer"> Logout</div></a>
   
             
           </div>
       </div>
       <div class="nav-offset"></div>
   </body>
   

</html>
