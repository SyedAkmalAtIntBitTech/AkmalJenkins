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
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
         <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
          <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">           
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link href="css/crop.css" rel="stylesheet" type="text/css"/>
        <link href="css/example.css" rel="stylesheet" type="text/css"/>
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
           <script src="js/colpick.js" type="text/javascript"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Dashboard</title>
        <script src="js/crop.js" type="text/javascript"></script>
       
        
        
    </head>
    <body ng-app="myapp">
        <div ng-controller="MyController" class="container" id="container"> 
            <div class="row">
                 <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

      <jsp:include page="submenu.html"/>
    <div class="col-md-8 col-md-offset-2 " ng-app="myapp">
        <div class="col-md-6 col-md-offset-2 bgcol"><p>ANALYTICS</p></div>
        <div class="col-md-6 col-md-offset-1 ba"><p>Blog area? </p></div>
        
    </div>
        </div>
    </body>
</html>