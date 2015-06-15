<%-- 
    Document   : UserRegistration
    Created on : May 20, 2015, 4:08:23 PM
    Author     : intbit
--%>
<!DOCTYPE html>
<html ng-app>
    <head>
        <script src="js/configurations.js"></script>
        <script src="js/form.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> 
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link rel="stylesheet" href="css/main1.css">
        
        <title>user login</title>
    </head>
    <body class="container">
         <div id="signupdiv">
                
            <img id="logo" src="images/logo.png" class="img-responsive" width="100" height="150"> <br>
                <form class="form-horizontal" id="signform" ng-controller="loginController" ng-submit="checkUser()" >
                     <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                  <p class="text-left">Login</p>
                             </div>
                        </div>
                    
                        <div class="group">
                  
                             <div class="col-md-3 col-md-offset-5">                            
                                 <input id="inputemail" class="form-control simplebox" type="email" required ng-model="user.emailid">
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
                                   <button type="submit"  class="btn btn-info">Login</button><br><br><br>

                               </div>
                        </div>
                </form>
        </div>
            
<!--        <form ng-controller="UserController" ng-submit="createUser()" >
            Email id: <input type="text" id="emailid" name="emailid" placeholder="UserID" ng-model="user.emailid"/><br>
            Password:<input type="password" id="password" name="password" ng-model="user.password" /><br>
            Confirm Password :<input type="password" id="confirmPassword" name="confirmPassword" ng-model="user.confirmPassword"/><br>
            <input id="regType" name="regType" ng-value="User" ng-hide="User"  ng-model="user.regType" ng-update-hidden />
<!--            <input type="submit" value="Submit" /> 
            <button class="btn btn-primary">Submit</button><br>
            <label>{{status}}</label>
        </form>
-->
    </body>
</html>