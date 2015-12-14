<%-- 
    Document   : forgotPassword
    Created on : Jun 18, 2015, 4:45:52 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
    <head>
         <%@ include file="fonttypekit.jsp"%>

        <script src="js/configurations.js"></script>
        <script src="js/form.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
     
        <link rel="stylesheet" href="css/main1.css">
        <title>change password</title>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body class="container">
         <div id="signupdiv">
                
            <img id="logo" src="images/logo.png" class="img-responsive" width="100" height="150"> <br>
                <form class="form-horizontal" id="signform" ng-controller="ForgotPassController" ng-submit="checkEmail()" >
                        <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                  <p class=" MH2">Enter  your Email id</p>
                             </div>
                        </div>
                        
                        <div class="group">
                    <div class="col-md-3 col-md-offset-5">                            
                        <input id="inputemail" class="form-control simplebox " type="email" required ng-model="user.emailid">
                        <label>EMAIL</label><br>
                    </div>
                </div>
                        

                        <div  class="form-group">
                               <div class="col-md-5 col-md-offset-5">
                                   <button type="submit" class="button button--moema button--text-thick button--text-upper button--size-s">Enter</button><br><br>
                               </div>
                        </div>
                </form>
        </div>
            
    </body>
</html>
