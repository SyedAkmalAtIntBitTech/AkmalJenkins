<%-- 
    Document   : emaildashboard
    Created on : Aug 21, 2015, 3:12:51 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>      
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Dashboard</title>
    </head>
    <body>
       <div class="row">
                 <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>
      
        <div id="divMenu">
    <ul>
        <li><a href="#">Home1</a>
        <ul>
            <li><a href="#">Homea</a></li>
            <li><a href="#">Homeb</a></li>
            <li><a href="#">Homec</a></li>
        </ul>
            </li>
    </ul>
</div>
    </body>
</html>
<style>
       #divMenu li li {
            list-style: none;
            position: relative;
            background: #641b1b;
            left: 148px;
            top: -27px;
        }
 
 
    #divMenu ul li a {
        width: 148px;
        height: 25px;
        display: block;
    }
 
    #divMenu ul ul {
        position: fixed;
        visibility: hidden;
        top: 27px;
        height: 100%;
    }
 
    #divMenu ul li:hover ul {
        visibility: visible;
    }
 
    #divMenu li:hover {
        background-color:transparent;
    }
 
    #divMenu ul li:hover ul li a:hover {
        background-color: transparent;
    }
 
    #divMenu a:hover {
        font-weight: bold;
    }
</style>