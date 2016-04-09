/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
    function getColorID(idNo,color){
        $('.palette-colorswab-selected' ).css("background-color",color);
    }
    
    function getThemeColors(c1,c2,c3,c4){
        $("#color1").css("background-color",c1);
        $("#color2").css("background-color",c2);
        $("#color3").css("background-color",c3);
        $("#color4").css("background-color",c4);
        $(".selected-palette-colorswab").removeClass('selected-palette-colorswab').addClass('select-palette-colorswab');
        $(".palette-colorswab-selected").removeClass('palette-colorswab-selected').addClass('palette-colorswab');
    }
    
$(document).ready(function (){
    
    $("#logoColorsDiv").click(function (){
        $("#picker").hide();
        $("#logocolor").show();
        $("#themeColorsDiv").hide();
        $("#themeColors,#customColors").removeClass('colorOptions-tab-active').addClass('colorOptions-tab');
        $("#logoColorsDiv").removeClass('colorOptions-tab').addClass('colorOptions-tab-active');
    });
    
    $("#themeColors").click(function (){
        $("#themeColorsDiv").show();
        $("#picker").hide();
        $("#logocolor").hide();
        $("#logoColorsDiv,#customColors").removeClass('colorOptions-tab-active').addClass('colorOptions-tab');
        $("#themeColors").removeClass('colorOptions-tab').addClass('colorOptions-tab-active');
    });
    
    $("#customColors").click(function (){
        $("#themeColorsDiv").hide();
        $("#picker").show();
        $("#logocolor").hide();
        $("#themeColors,#logoColorsDiv").removeClass('colorOptions-tab-active').addClass('colorOptions-tab');
        $("#customColors").removeClass('colorOptions-tab').addClass('colorOptions-tab-active');
    });
    
   var globalColorPaletteDivId="";
    $(".palette-colorswab , .palette-colorswab-selected").click(function (){
        
//        $(".palette-colorswab").removeClass('palette-colorswab-selected');
//        $(".palette-colorswab").addClass('palette-coloswab');
        var colorPaletteDivId=$(this).attr('id');
        globalColorPaletteDivId=colorPaletteDivId;   
        $(this).toggleClass('palette-colorswab-selected').toggleClass('palette-colorswab');    
    });
    
    $(".select-palette-colorswab").click(function (){
        $(this).toggleClass('selected-palette-colorswab').toggleClass('select-palette-colorswab');            
    });
    
     $('#picker').colpick({
        flat: true,
        layout: 'hex',
        onSubmit: function (hsb, hex, rgb, el) {
            //for haking hex value alert(hex);
            $('.palette-colorswab-selected' ).css("background-color", "#" + hex);
        }
    });
    
    
    
    
});

    
            
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
            window.open(getHost() + 'v2/signup/onboarding3.jsp', "_self");
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
        window.open(getHost() + 'v2/signup/onboarding4.jsp', "_self");
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
            
            window.open(getHost() + 'v2/signup/onboarding2.jsp', "_self");
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
                window.open(getHost() + 'v2/signup/onboardinglogouploaded.jsp', "_self");
            }
            else{
                alert("Invalid File Type!\n Please choose valid Image format.");
            }
        }
    };
    
    $scope.getLogoColors = function () {
        $http({
            method: 'GET',
            url: getHost() +'/GetColorsFromLogo'
        }).success(function (data, status, headers, config) {
            $scope.color = data.Colors;
            if (data === error) {
                alert(data);
            }
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
        });
    };
    
     $scope.showThemeColors = function( ){
             $scope.curPage = 0;
             $scope.pageSize = 10;
                $http({
                        method : 'GET',
                        url : getHost() +'/getAllColorThemes.do'
                }).success(function(data, status, headers, config) {
                    $scope.themeColors = data.d.details;
                }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });
    };
    
    $scope.clearColorPalette = function (){
        var bgColor="background-color";
        $("#color1").css(bgColor,"");
        $("#color2").css(bgColor,"");
        $("#color3").css(bgColor,"");
        $("#color4").css(bgColor,"");
    };
    
};

