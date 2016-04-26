<%-- 
    Document   : navbarv2
    Created on : Dec 10, 2015, 5:26:17 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="css/hint.css"/>
    </head>
    <body>
       <div class="navigation">
        <div class="main-nav-logo hint--right" data-hint="DASHBOARD" >
         <a class=" bb-logo-nav" href="dashboard.jsp" >

            <img  type="image/svg+xml" src="images/Logo_Reverse.svg" class="bb-logo " style="cursor:pointer;" />

         </a>
        </div>
        <ul class="nav-tabs1">
            <li class="nav-elements-icon-container hint--right"  data-hint="YOUR PLAN">
                <a href="marketing.jsp"> 

                    <img type="image/svg+xml" src="images/yourPlan.svg" class="nav-elements-icon" style="cursor:pointer;"/>

                </a>
            </li>
            <li class="nav-elements-icon-container hint--right"  data-hint="MARKETING PROGRAM">
                 <a class="" href="marketingprogramlists.jsp">

                    <img type="image/svg+xml" src="images/marketingProgram.svg" class="nav-elements-icon" style="cursor:pointer;"/>

                </a>
            </li>
            <li class="nav-elements-icon-container hint--right" data-hint="MARKETING HUB">
                <a  href="emaillists" >

                    <img type="image/svg+xml" src="images/yourHubs.svg" class="nav-elements-icon " style="cursor:pointer;"/>

                </a>
            </li>
            <li class="nav-elements-icon-container hint--right" data-hint="MEDIA">
                <a  href="imagegallery.jsp" >

                    <img type="image/svg+xml" src="images/media.svg" class="nav-elements-icon " style="cursor:pointer;"/>

                </a>
            </li>
            <li class="nav-elements-icon-container hint--right" data-hint="USER">
                <a  href="settings.jsp?change=0" >

                    <img type="image/svg+xml" src="images/user.svg" class="nav-elements-icon " style="cursor:pointer;"/>

                </a>
            </li>
             <li class="nav-elements-icon-container hint--right" id="logoutid" data-hint="LOGOUT" style="position: absolute;bottom: 0%;left: 13%;">

                 <a  href="signout.jsp" >

                    <img type="image/svg+xml" src="images/logout.svg" class="nav-elements-icon " style="cursor:pointer;"/>

                </a>
            </li>            
        </ul>    
    </div>
    </body>
</html>
