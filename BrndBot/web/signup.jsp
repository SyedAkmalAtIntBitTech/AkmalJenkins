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
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link rel="stylesheet" href="css/main1.css">
        <title>Sign up</title>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body class="container" >
        
        <div id="signupdiv">
                
            <img id="logo" src="images/HelloLogo.svg" class="img-responsive" width="100" height="140"> <br>
                <form class="form-horizontal" id="signform" ng-controller="UserController" ng-submit="createUser()" >
                        <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                  <p class="text-left">Sign Up</p>
                             </div>
                        </div>
                    
                        <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                 <input id="inputemail" class="form-control simplebox" type="text" required ng-model="user.emailid" ng-blur="checkAvailability()" >
                                 <label>EMAIL</label><br>

                             </div>
                        </div>

                        <div class="group">
                            <div class="col-md-3 col-md-offset-5">
                                <input id="inputpassword" class="form-control simplebox" type="password" required ng-model="user.password">
                                <label>PASSWORD</label><br>
                            </div>
                        </div>

                        <div class="group">
                            <div class="col-md-3 col-md-offset-5">
                                <input id="inputreenter" class="form-control simplebox" type="password" required ng-model="user.confirmPassword">
                                <label> CONFIRM PASSWORD</label><br><br>
                            </div>
                        </div>
          
                        <div  class="form-group">
                             <div class="col-md-5 col-md-offset-5">
                                 <button type="submit"  class="button button--moema button--text-thick  button--size-s" style="width:200px;">CREATE MY ACCOUNT</button><br><br><br>
                             </div>
                        </div>

                </form> 

        </div>
          <script>
//            alert($("body").height()+":"+$("body").width() );
          </script>  
    </body>
</html>
