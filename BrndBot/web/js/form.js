/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//var host_address = "http://localhost:8084/BrndBot/";

//var registrationURL = 'http://localhost:8084/BrndBot/ServletUserRegistration';
//var organizationURL = 'http://localhost:8084/BrndBot/organization.jsp';
  
function UserController($scope, $http)
{
  $scope.user = {};

    $scope.createUser = function() 
    {

            var password = $("#inputpassword").val();
            var confirmPass = $("#inputreenter").val();

            if(password !== confirmPass){
                alert("Enter the same password");
                $("#inputreenter").focus();
            }else{

                    $http({
                            method: 'POST',
                            url: getHost() +'servletUserRegistration',
                            headers: {'Content-Type': 'application/json'},
                            data:  $scope.user
                  }).success(function (data) 
                    {
                      $scope.status=data;
                            if(data === "false"){
                                alert("User already exist");
                            window.open(getHost()+ 'signup.jsp',"_self");
                            }else {
                            window.open(getHost() +'organization.jsp',"_self");
                        }
                  })
                      .error(function(data, status) {
                      // called asynchronously if an error occurs
                      // or server returns response with an error status.


                      console.log('request not succesful');
                    });
                  }
              
    };

}

function ForgotPassController($scope, $http){
    $scope.user = {};

    $scope.checkEmail= function(){
            
            $http({
              method: 'POST',
              url: getHost() +'sendEmail',
              headers: {'Content-Type': 'application/json'},
              data:  $scope.user
            }).success(function (data) 
              {
                $scope.status=data;
                if(data === "true"){
                    alert("password reset link has been sent to your email id");
                }else {
                    alert("incorrect email id");
                }
              })
                .error(function(data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                alert("request not succesful");
                console.log('request not succesful');
              });
        
    };
    
}
function loginController($scope, $http){
    $scope.user = {};

    $scope.checkUser = function(){
            
            $http({
              method: 'POST',
              url: getHost() +'authentication',
              headers: {'Content-Type': 'application/json'},
              data:  $scope.user
            }).success(function (data) 
              {
                $scope.status=data;
                if(data === "true"){
                    window.open(getHost() +'dashboard.jsp',"_self");
                }else {
                    alert("incorrect username or password");
                    window.open(getHost() +'login.jsp',"_self");
                }
              })
                .error(function(data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                alert("request not succesful");
                window.open(getHost() +'failure.jsp',"_self");
                console.log('request not succesful');
              });
        
    };
    
}

angular.module("myapp", [])
    .controller("MyController", function($scope,$http) {
        $scope.organizations = {};
                $http({
                        method : 'GET',
                        url : 'getOrganizations'
                }).success(function(data, status, headers, config) {
                        $scope.organizations.org_name = data;
                }).error(function(data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                });

                $scope.createOrganization = function() 
                {
                  $http({
                    method: 'POST',
                    url: getHost() + 'addUpdateOrganization',
                    headers: {'Content-Type': 'application/json'},
                    data:  $scope.organizations
                  }).success(function (data) 
                    {
                          $scope.status=data;
                          window.open(getHost() +'services.html',"_self");
                    })
                      .error(function(data, status) {
                      // called asynchronously if an error occurs
                      // or server returns response with an error status.

              //        window.open('http://localhost:8084/BrndBotUsingJson/UploadLogo.jsp');
                      alert("request not succesful");
                      console.log('request not succesful');
                    });
                  };
    });

function UserController1($scope, $http)
{
  $scope.Organization = {};
 
  $scope.createOrganization  = function() 
  {
    $http({
      method: 'POST',
      url: getHost() +'addUpdateOrganization',
      headers: {'Content-Type': 'application/json'},
      data:  $scope.Organization
    }).success(function (data) 
      {
    	$scope.status=data;
      })
        .error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("request not succesful");
        console.log('request not succesful');
      });
    };
}