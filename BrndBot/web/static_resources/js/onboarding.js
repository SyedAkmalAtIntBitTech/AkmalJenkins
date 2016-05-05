/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

    function getColorID(idNo,color){
        $('.palette-colorswab-selected').css("background-color",color);
    }
//    
    function getThemeColors(c1,c2,c3,c4){
        $("#color1").css("background-color",c1);
        $("#color2").css("background-color",c2);
        $("#color3").css("background-color",c3);
        $("#color4").css("background-color",c4);
        $(".selected-palette-colorswab").removeClass('selected-palette-colorswab').addClass('select-palette-colorswab');
        $(".palette-colorswab-selected").removeClass('palette-colorswab-selected').addClass('palette-colorswab');
    }
    
$(document).ready(function (){
    
//    $("#color1,#color2,#color3,#color4").css("background-color","rgb(255, 255, 255)");

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
          $(".palette-colorswab-selected").removeClass('palette-colorswab-selected');
          $(this).addClass('palette-colorswab-selected');   
    });
    
     $('#picker').colpick({
        flat: true,
        layout: 'hex',
        onSubmit: function (hsb, hex, rgb, el) {
            //for haking hex value alert(hex);
            $('.palette-colorswab-selected' ).css("background-color", "#" + hex);
        }
    });
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
    $("#emailId").blur(function (){
                validateEmail();
                var emailId=$("#emailId").val();
                var userEmailId={"userName":emailId};
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/onboarding/isUserUnique',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(userEmailId)
                }).success(function (data, status, headers, config)
                {   
                  
                    var isUserUnique=eval(JSON.stringify(data.d.message));
                    if(isUserUnique === 'false'){
                        alert("Sorry, that email Id already exists!");
                        $("#emailId").focus();
                    }
//                    alert(eval(JSON.stringify(data.d.message)));
//                    window.open(getHost() + 'adminv2/organization.jsp', "_self");
                }).error(function(data, status, headers, config){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    $("#emailId").focus();
                });     
    });
    
    
});

    
            
function onboardingcontroller($scope,$http) {
        var globalLogoImageSrc="";    
    $scope.getOrganizations = function () {
        
                    $http({
                            method : 'GET',
                            url : getHost()+'/onboarding/getAllOnlyOrganizations'
                        }).success(function(data, status, headers, config) {
                            $scope.organizations=data.d.details;
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });       
    };
    
    
    $scope.saveCompany = function (){
        
        var companyName= $("#companyName").val();
        var organizationId= $("#industryDropDown").val();      
        if(companyName ===""){
            alert(companyNameEmpty);
            $("#companyName").focus();
        }
        else if(organizationId ==="0"){
            alert(industryNameEmpty);
            $("#industryDropDown").focus();            
        }
        else
        {            
            var saveCompanyDetails={"companyName":companyName,"organizationId":organizationId};
//            window.open(getHost() + 'v2/signup/onboarding3.jsp', "_self");
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/onboarding/saveCompany',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(saveCompanyDetails)
                }).success(function (data, status, headers, config)
                {  
                    var message= data.d.message;
                    if(message==="true")
                    {             
                        window.open(getHost() + 'signup/datasource', "_self");
                    }
                }).error(function(data, status, headers, config){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
        }
    };
    
    $scope.getServices = function (){
         
//        alert(localStorage.getItem("companyName")+"\n"+localStorage.getItem("industryName"));
            $http({
                            method : 'GET',
                            url : getHost()+'/onboarding/getAllExternalSources'
                        }).success(function(data, status, headers, config) {
                           
                            $scope.services=data.d.details;
                        }).error(function(data, status, headers, config) {
                           
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });            
    };
    
//    $scope.getMindbodyText = function (){
//        var serviceValue=$("#services").val();
//        if(serviceValue !== '0'){
//            $("#serviceContinueButton").css("pointer-events","none");
//            $("#mindbodyStudioDiv").show();
//        }
//        if(serviceValue === '0'){
//            $("#mindbodyStudioDiv").hide();
//            $("#serviceContinueButton").css("pointer-events","auto");
//        }        
//    };
    
    $scope.getActivationLink = function (){
        var studioId=$("#mindbodyStudioId").val();
        if(studioId===""){
            alert("Please Enter Studio Id.");
            $("#mindbodyStudioId").focus();
        }else{
            $http({
                method: 'POST',
                url: getHost() +'/onboarding/saveStudioId?studioId='+studioId
            }).success(function (data, status, headers, config) {
            var studioIdSaved = eval(JSON.stringify(data.d.message));
            if(studioIdSaved === "true") {
                $http({
                    method: 'GET',
                    url: getHost() +'/externalContent/getActivationLink'
                }).success(function (data, status, headers, config) {
                    var actiovationLink=eval(JSON.stringify(data.d.details[0]));
                    $("#actiovationLink").attr('href',actiovationLink);
                    $("#activationLinkDiv").empty().append(actiovationLink);
                    $("#serviceContinueButton").css("pointer-events","auto");
        //            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function (data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });
            }
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
        }
    };
    $scope.saveServices = function (){
        var services= $("#services").val();
        localStorage.setItem("services",services);
        window.open(getHost() + 'signup/uploadlogo', "_self");
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
    
    $scope.saveUser=function (){
        if(validateSignUp()){
            var emailId = $('#emailId').val();
            var userPassword = $("#password").val();
            var userDetails={"userName":emailId,"userPassword":userPassword};
            $.ajax({
                method: 'POST',
                url: getHost() + '/onboarding/saveUser',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(userDetails)
            }).success(function (data)
            {
                
               var message= data.d.message;
                if(message==="true")
                {
                   $("#username").val(emailId);
                   $("#userpassword").val(userPassword);
                   $("#signform").submit();
                }
            })
            .error(function (data, status) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };
    
    $("#uploadLogo").change(function (){
        $("#uploadLogoLabel").empty().append('Change Logo');
    });
    $("#services").change(function (){
       var serviceValue=$("#services").val();
        if(serviceValue !== '0'){
            $("#serviceContinueButton").css("pointer-events","none");
            $("#mindbodyStudioDiv").show();
        }
        if(serviceValue === '0'){
            $("#mindbodyStudioDiv").hide();
            $("#serviceContinueButton").css("pointer-events","auto");
        }       
        
    });
    $scope.imageValid=function (){
        var logoImageFile=$("#uploadLogo").val();
        globalLogoImageSrc=logoImageFile;
        if(logoImageFile===""){
            alert("No logo uploaded, Please upload your Logo.");
        }
        else{
            var logoExtension = logoImageFile.split('.').pop().toUpperCase();
            if((logoExtension==="PNG")||(logoExtension==="JPG")||(logoExtension==="JPEG")){
//                alert("Success");
//                $("#uploadLogoDiv,#uploadLogoContinueButton").hide();
//                $("#uploadedLogoDiv,#uploadedLogoContinueButton").show();
                
                $("#uploadedLogo").attr('src',globalLogoImageSrc);
                window.open(getHost() + 'signup/choosepalette', "_self");
            }
            else{
                alert("Invalid File Type!\n Please choose valid Image format.");
            }
        }
    };
    
    
    $("#uploadLogo").change(function (){
        var logoImageSrc=$("#uploadLogo").val();
        var logoImage=$("#uploadLogo").val().split(/[\\\/]/).pop();
        localStorage.setItem("logo",logoImage);
//        alert(localStorage.getItem("logo"));
    });
    
            var base64ImgString = "";
            var imageFileName = "";
 
        function imageConverter(id) {alert(id);
            var obj = document.getElementById(id);
            obj.addEventListener("change", readFile, false);
        }

        function readFile() {
            if (this.files.length === 1) {
                var reader = new FileReader();
                var file = this.files[0];
                var imageFileName = file.name;
                reader.addEventListener("load", function () {
                    var data = reader.result;
                    base64ImgString = data;
                    alert(base64ImgString);

                }, false);
                reader.readAsDataURL(file);
            }

        }

        function getImageData() {

            return {
                "imageFileName": imageFileName,
                "base64ImgString": base64ImgString,
            };
        }
    
        
    
    $scope.getLogoColors = function () {
         var companyId=localStorage.getItem("companyId");
        $http({
            method: 'GET',
            url: getHost() +'/onboarding/getColorsForLogo'
        }).success(function (data, status, headers, config) {
            $scope.color =data.d.details;
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
        });
    };
    
     $scope.showThemeColors = function( ){
             $scope.curPage = 0;
             $scope.pageSize = 10;
                $http({
                        method : 'GET',
                        url : getHost() +'getAllColorThemes'
                }).success(function(data, status, headers, config) {
                    
                    $scope.themeColors = data.d.details;
                }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });
    };
    
    $scope.clearColorPalette = function (){
        $(".palette-colorswab-selected").removeClass('palette-colorswab-selected');
        var bgColor="background-color";
        $("#color1").css(bgColor,"");
        $("#color2").css(bgColor,"");
        $("#color3").css(bgColor,"");
        $("#color4").css(bgColor,"");
    };
    
    $scope.saveColorPalette = function (){
         var color1=$("#color1").css("backgroundColor");
         var color2=$("#color2").css("backgroundColor");
         var color3=$("#color3").css("backgroundColor");
         var color4=$("#color4").css("backgroundColor");
         var companyColors={"color1":color1,"color2":color2,"color3":color3,"color4":color4};
        if((color1=="rgba(0, 0, 0, 0)")||(color2=="rgba(0, 0, 0, 0)")||(color3=="rgba(0, 0, 0, 0)")||(color4=="rgba(0, 0, 0, 0)")){
        alert("Please choose all 4 colors.");   
        }
        else{
            $.ajax({
                method: 'POST',
                url: getHost() + '/settings/setColors',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(companyColors)
            }).success(function (data)
            {
                
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'user/dashboard', "_self");
            })
            .error(function (data, status) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };
};

