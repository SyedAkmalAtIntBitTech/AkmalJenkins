/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            alert(emailerror);
            $("#inputemail").focus();
            return false;
        }
        if (!(validateEmail(sEmail))) {
            alert(wrongemail);
            $("#inputemail").focus();
            return false;
        }
        if (password === "") {
            alert(passworderror);
            $("#inputpassword").focus();
            return false;
        }
        if (confirmPass === "") {
            alert(confirmpassworderror);
            $("#inputpassword").focus();
            return false;
        }

        if (password !== confirmPass) {
            alert(passwordmatcherror);
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
                    alert(userexits);
                    window.open(getHost() + 'signup.jsp', "_self");
                } else if (data === "true") {
                    window.open(getHost() + 'organization.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            })
            .error(function (data, status) {
                alert(requesterror);
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
            alert(emailerror);
            $("#inputemail").focus();
            return false;
        }
        if (!(validateEmail(sEmail))) {
            alert(wrongemail);
            $("#inputemail").focus();
            return false;
        }
        return true;
    }

    $scope.checkEmail = function () {
        if (validate()) {
            document.getElementById("enter").disabled = true;
            $http({
                method: 'POST',
                url: getHost() + 'SendEmail',
                headers: {'Content-Type': 'application/json'},
                data: $scope.user
            }).success(function (data)
            {
                $scope.status = data;
//                alert(JSON.stringify(status));
                if (data === "true") {
                    alert(passwordresetlinksent);
                } else if (data === "false") {
                    alert(wrongemail);
                } else if (data === error) {
                    alert(data);
                }
            })
            .error(function (data, status) {
                alert(requesterror);
            });
        }
    };
}
function loginController($scope, $http) {
    $scope.user = {};

    $scope.checkUser = function () {
        $http({
            method: 'POST',
            url: global_host_address + 'Authentication',
            headers: {'Content-Type': 'application/json'},
            data: $scope.user
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                window.open(getHost() + 'dashboard.jsp', "_self");
            } else if (data === "false") {
                alert(incorrectpasswordorusername);
                window.open(getHost() + 'login.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
        .error(function (data, status) {
            alert(requesterror);
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
                alert(nodataerror);
            });            
            $scope.validate = function(){
                var company_name = $("#inputcompanyname").val();
                var organization_name = $("#organizationdropdown").val();                
                if (company_name == ""){
                    alert(companynameerror);
                    $("#inputcompanyname").focus();
                    return false;
                }                
                if (organization_name == 0){
                    alert(organizationnameerror);
                    $("#organizationdropdown").focus();
                    return false;
                }
                return true;
            };
            
            $scope.createOrganization = function ()
            {
                if ($scope.validate()){                    
                var company_name = $("#inputcompanyname").val();
                var organization_name = $("#organizationdropdown").val();
                var organization = {"company": company_name, "organization": organization_name}                    
                    $http({
                        method: 'POST',
                        url: getHost() + 'AddUpdateOrganization',
                        headers: {'Content-Type': 'application/json'},
                        data: organization
                    }).success(function (data)
                    {
                        $scope.status = data;
                        if (data === "true") {
                            window.open(getHost() + 'services.html', "_self");
                        } else {
                            alert(data);
                        }
                    }).error(function (data, status) {
                        alert(requesterror);
                    });
                }
            };
        });
