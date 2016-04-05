/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
function onboardingcontroller($scope,$http) {
           
    $scope.getOrganizations = function () {
        
                    $http({
                            method : 'GET',
                            url : getHost()+'/getAllOnlyOrganizations.do'
                        }).success(function(data, status, headers, config) {
                            $scope.organizations=data.d.details;
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });       
    };
    
    
    $scope.saveOrganization = function (){
        
        var companyName= $("#companyName").val();
        var industryName= $("#industryDropDown").val();      
             
        if(companyName ===""){
            alert(companyNameEmpty);
            $("#companyName").focus();
        }
        else if(industryName ==="0"){
            alert(industryNameEmpty);
            $("#industryDropDown").focus();            
        }
        else
        {            
            localStorage.setItem("companyName",companyName);
            localStorage.setItem("industryName",industryName);
            window.open(getHost() + 'user/onboarding3.jsp', "_self");
//            $.ajax({
//                    method: 'POST',
//                    url: getHost() + '/saveUserOrganization.do',
//                    dataType: "json",
//                    contentType: "application/json",
//                    data: JSON.stringify(organization)
//                }).success(function (data, status, headers, config)
//                {  
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
//                    window.open(getHost() + 'adminv2/organization.jsp', "_self");
//                }).error(function(data, status, headers, config){
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
//                });                         
        }
    };
    
    $scope.getServices = function (){
//        alert(localStorage.getItem("companyName")+"\n"+localStorage.getItem("industryName"));
            $http({
                            method : 'GET',
                            url : getHost()+'/getAllExternalSources.do'
                        }).success(function(data, status, headers, config) {
                            $scope.services=data.d.details;
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });            
    };
    
    $scope.saveServices = function (){
        var services= $("#services").val();
        localStorage.setItem("services",services);
        window.open(getHost() + 'user/onboarding4.jsp', "_self");
    };
    
     function validateEmail(emailId) {
        var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if (filter.test(emailId)) {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    function validateSignUp()
    {
        var emailId = $('#emailId').val();
        var password = $("#password").val();
        var rePassword = $("#rePassword").val();

        if ($.trim(emailId).length === 0) {
            alert(emptyemail);
            $("#emailId").focus();
            return false;
        }
        if (!(validateEmail(emailId))) {
            alert(wrongemail);
            $("#emailId").focus();
            return false;
        }
        if (password === "") {
            alert(passworderror);
            $("#password").focus();
            return false;
        }
        if (rePassword === "") {
            alert(repasswordempty);
            $("#rePassword").focus();
            return false;
        }

        if (password !== rePassword) {
            alert(confirmpassworderror);
            $("#rePassword").focus();
            $("#rePassword").val('');
            return false;
        }
        return true;
    };
    
    $scope.signupSuccess=function (){
        if(validateSignUp()){
            
            window.open(getHost() + 'user/onboarding2.jsp', "_self");
//            $http({
//                method: 'POST',
//                url: getHost() + 'ServletUserRegistration',
//                headers: {'Content-Type': 'application/json'},
//                data: $scope.user
//            }).success(function (data)
//            {
//                $scope.status = data;
//                if (data === "false") {
//                    alert(userexits);
//                    window.open(getHost() + 'signup.jsp', "_self");
//                } else if (data === "true") {
//                    window.open(getHost() + 'organization.jsp', "_self");
//                } else if (data === error) {
//                    alert(data);
//                }
//            })
//            .error(function (data, status) {
//                alert(requesterror);
//            });
        }
    };
    $scope.imageValid=function (){
        var logoImageFile=$("#uploadLogo").val();
        if(logoImageFile===""){
            alert("No Logo Uploaded!\t Please upload your Logo.");
        }
        else{
            var logoExtension = logoImageFile.split('.').pop().toUpperCase();
            if((logoExtension==="PNG")||(logoExtension==="JPG")||(logoExtension==="JPEG")){
                alert("Success");
                window.open(getHost() + 'user/onboardinglogouploaded.jsp', "_self");
            }
            else{
                alert("Invalid File Type!\n Please choose valid Image format.");
            }
        }
    };
    
};

