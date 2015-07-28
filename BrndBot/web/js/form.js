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

    function validateEmail(sEmail) {
        var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if (filter.test(sEmail)) {
            return true;
        }
        else {
            return false;
        }
    }

    function validate() {
        var sEmail = $('#inputemail').val();
        var password = $("#inputpassword").val();
        var confirmPass = $("#inputreenter").val();

        if ($.trim(sEmail).length == 0) {
            alert('Please enter valid email address');
            $("#inputemail").focus();
            return false;
        }
        if (!(validateEmail(sEmail))) {
            alert('Invalid Email Address');
            $("#inputemail").focus();
            return false;
        }
        if (password === "") {
            alert("Enter the password");
            $("#inputpassword").focus();
            return false;
        }
        if (confirmPass === "") {
            alert("Enter the password");
            $("#inputpassword").focus();
            return false;
        }

        if (password !== confirmPass) {
            alert("Enter the same password");
            $("#inputreenter").focus();
            return false;
        }
        return true;
    }

    $scope.createUser = function ()
    {
        if (validate())
        {
            $http({
                method: 'POST',
                url: getHost() + 'ServletUserRegistration',
                headers: {'Content-Type': 'application/json'},
                data: $scope.user
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("User already exist");
                    window.open(getHost() + 'signup.jsp', "_self");
                } else if (data === "true") {
                    window.open(getHost() + 'organization.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("request not succesful");
                    });
        }

    };

}

function ForgotPassController($scope, $http) {
    $scope.user = {};

    function validateEmail(sEmail) {
        var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if (filter.test(sEmail)) {
            return true;
        }
        else {
            return false;
        }
    }

    function validate() {
        var sEmail = $('#inputemail').val();

        if ($.trim(sEmail).length == 0) {
            alert('Please enter valid email address');
            $("#inputemail").focus();
            return false;
        }
        if (!(validateEmail(sEmail))) {
            alert('Invalid Email Address');
            $("#inputemail").focus();
            return false;
        }
        return true;
    }

    $scope.checkEmail = function () {

        if (validate()) {
            $http({
                method: 'POST',
                url: getHost() + 'SendEmail',
                headers: {'Content-Type': 'application/json'},
                data: $scope.user
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("password reset link has been sent to your email id");
                } else if (data === "false") {
                    alert("incorrect email id");
                } else if (data === error) {
                    alert(data);
                }
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("request not succesful");
                    });
        }
    };

}
function loginController($scope, $http) {
    $scope.user = {};

    $scope.checkUser = function () {
            alert(getHost());

        $http({
            method: 'POST',
            url: getHost() + 'Authentication',
            headers: {'Content-Type': 'application/json'},
            data: $scope.user
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                window.open(getHost() + 'dashboard.jsp', "_self");
            } else if (data === "false") {
                alert("incorrect username or password");
                window.open(getHost() + 'login.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
                .error(function (data, status) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    alert("request not succesful");
                });

    };

}

angular.module("myapp", [])
        .controller("MyController", function ($scope, $http) {
            $scope.organizations = {};
            $http({
                method: 'GET',
                url: 'GetOrganizations'
            }).success(function (data, status, headers, config) {
                $scope.organizations.org_name = data;
            }).error(function (data, status, headers, config) {
                alert("No data available, problem fetching the data");
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });

            $scope.createOrganization = function ()
            {
                $http({
                    method: 'POST',
                    url: getHost() + 'AddUpdateOrganization',
                    headers: {'Content-Type': 'application/json'},
                    data: $scope.organizations
                }).success(function (data)
                {
                    $scope.status = data;
                    if (data === "true") {
                        window.open(getHost() + 'services.html', "_self");
                    } else {
                        alert(data);
                    }
                })
                        .error(function (data, status) {
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                            alert("request not succesful");
                        });
            };
        });
