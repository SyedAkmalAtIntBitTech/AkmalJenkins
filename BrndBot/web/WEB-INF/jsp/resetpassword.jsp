<%-- 
    Document   : forgotPassword
    Created on : Jun 18, 2015, 4:45:52 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
    <head>
    
        <title>BrndBot - Change Password</title>
       
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
                           <button type="submit" id="enter" class="button button--moema button--text-thick button--text-upper button--size-s">Enter</button><br><br>
                       </div>
                </div>
                </form>
        </div>
            
    </body>
</html>
