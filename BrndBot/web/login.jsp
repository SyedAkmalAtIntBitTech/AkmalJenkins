<%-- 
    Document   : UserRegistration
    Created on : May 20, 2015, 4:08:23 PM
    Author     : intbit
--%>
<!DOCTYPE html>
<html ng-app>
    <head>
         <%@ include file="fonttypekit.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/alertmessage.js"></script>
        <script src="js/configurations.js"></script>
        <script src="js/form.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> 
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link rel="stylesheet" href="css/main1.css">
        <link rel="stylesheet" href="css/popup.css">
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <title>BrndBot - User Login</title>
        <script>
         $("document").ready(function() {
             if(window.location.href.indexOf("signout.jsp") > -1) {
                window.location = getHost() +"login.jsp";
                
                alert(logoutmsg);
             }
        setTimeout(function(){ $("#inputemail").focus();},10);});  
        </script>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body class="container" >
        <div id="signupdiv">
            <img id="logo" src="images/logo.svg" class="img-responsive" width="100" height="150"> <br>
            <form class="form-horizontal" id="signform" ng-controller="loginController" ng-submit="checkUser()" >
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">                            
                        <p class="MH2">Login</p>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">                            
                        <input id="inputemail" class="form-control simplebox " type="email" required ng-model="user.emailid" >
                        <label>EMAIL</label><br>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <input id="inputpassword" class="form-control simplebox" type="password" required ng-model="user.password">
                        <label>PASSWORD</label><br>
                    </div>
                </div>
                <div  class="form-group">
                    <div class="col-md-5 col-md-offset-5">
                        <button style="left:-10px;" type="submit"  class="button button--moema button--text-thick button--text-upper button--size-s">Login</button></div>
                    <div class="col-md-5 col-md-offset-5"><br><br>
                        <p><a href="<%= application.getContextPath()%>/resetpassword.jsp">Forgot password</a></p>
                        <p class="sgnup"><a href="signup.jsp">Sign up</a></p>
                    </div>
                </div>
            </form>
        </div>                        
    </body>
</html>
    