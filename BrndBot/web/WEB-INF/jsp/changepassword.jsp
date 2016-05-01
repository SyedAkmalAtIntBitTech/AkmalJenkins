<%-- 
    Document   : forgotPassword
    Created on : Jun 18, 2015, 4:45:52 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
    <head>
       
        <script src="js/configurations.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>

        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script>
            
            var hashURL="";
            function validat(){
                    var password = $("#inputpassword").val();
                    var confirmpassword = $("#inputreenter").val();
                    if(password === ""){
                        alert("Password not entered! Please enter password.");
                        $("#inputpassword").focus();
                        return false;
                    }
                    if(confirmpassword === ""){
                        alert("Confirm password not entered! Please enter the confirm password.");
                        $("#inputreenter").focus();
                         return false;
                    }else
                        if (password !== confirmpassword){
                            alert("Confirm password doesn't match with password!");
                            $("#inputreenter").focus();
                            return false;
                        }
                    return true;
                        
                    }
                    
            $(document).ready(function (){                        
                hashURL = $("#hashURL").val();
                if(hashURL==="null"){
                    window.open(getHost() +'login.jsp',"_self");
                }
            });
       
                    
            function controllerPassword($scope, $http){
                $scope.changePassword = function() 
                {
                      $("#enter").css("pointer-events","none");
                      var password = $("#inputpassword").val();
                      var confirmpassword = $("#inputreenter").val();
                      var type="change";

                    if(validat()){

                            var password_object = {"hashURL":hashURL,"password":password,"confirmpassword":confirmpassword,"type":type};
                            
                            $http({
                              method: 'POST',
                              url: getHost() +'ResetUserPassword',
                              headers: {'Content-Type': 'application/json'},
                              data:  password_object
                            }).success(function (data) 
                              {
                                  alert(data);
                                $scope.status=data;
                                if (data === "false"){
                                    alert("User session has expired! Kindly resubmit a request.");
                                }else if (data === "true"){
                                    alert("Password has been changed successfully.");
                                    window.open(getHost() +'login.jsp',"_self");
//                                     $("#enter").bind('click');
                                }else if(data === error){
                                    alert(data);
                                }
                              })
                                .error(function(data, status) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.

                                alert("Request not successful!");
                              });
                            }
                        };
                        
                    };
                
            
        </script>
        <link rel="stylesheet" href="css/main1.css">
        <title>BrndBot - Password Change</title>
       
    </head>
    <body class="container" >
        <%
            String hashURL = request.getParameter("userid");
        %>
        <div id="signupdiv">
                
            <img id="logo" src="images/logo.png" class="img-responsive" width="100" height="150"> <br>
                <form class="form-horizontal" id="signform" ng-controller="controllerPassword" ng-submit="changePassword()" >
                        
                        <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                  <p class="text-left"></p>
                             </div>
                        </div>
                    
                        <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                 <input id="hashURL" type="hidden" value="<%= hashURL %>" name="hashURL" />  
                                 <input id="inputpassword" class="form-control simplebox" type="password" name="password" />
                                 <label>PASSWORD</label><br>
                             </div>
                        </div>
                        <div class="group">
                             <div class="col-md-3 col-md-offset-5">                            
                                 <input id="inputreenter" class="form-control simplebox" type="password" name="confirmpassword"/>
                                 <label>CONFIRM PASSWORD</label><br>
                             </div>
                        </div>

                        <div  class="form-group">
                               <div class="col-md-5 col-md-offset-5" >
                                   <button type="submit"  class="btn btn-info">Enter</button><br>
                               </div>
                        </div>

                </form> 

        </div>
            
    </body>
</html>
