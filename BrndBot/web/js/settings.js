/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var x="";
function changefilename() {
    x = document.getElementById("filevalue").value;
    if (x === "") {
        alert(chooseimage);
        return false;
    }
    else
    {
        var filename=x;
        var array=filename.split('.');
        var length=array.length;
        var extenion=array[length-1];
        var error=1;
        switch (extenion)
        {
            case 'jpg':
                    error=0;
                    break;
            case 'png':
                    error=0;
                    break;
            case 'jpeg':
                    error=0;
                    break;
            case 'JPG':
                    error=0;
                    break;
            case 'PNG':
                    error=0;
                    break;
            case 'JPEG':
                    error=0;
                    break;
            case 'svg':
                    error=0;
                    break;
            case 'SVG':
                    error=0;
                    break;
            case 'bmp':
                    error=0;
                    break;
            case 'BMP':
                    error=0;
                    break;
            case 'TIF':
                    error=0;
                    break;
            case 'tif':
                    error=0;
                    break;
            case 'gif':
                    error=0;
                    break;
            case 'GIF':
                    error=0;
                    break;
            case 'PSD':
                    error=0;
                    break;
            case 'psd':
                    error=0;
                    break;
            case 'yuv':
                    error=0;
                    break;
            case 'YUV':
                    error=0;
                    break;
            case 'THM':
                    error=0;
                    break;
            case 'thm':
                    error=0;
                    break;
            case 'PSPIMAGE':
                    error=0;
                    break;
            case 'pspimage':
                    error=0;
                    break;
        }
        if(error===1)
        {
            $("#imagetext").val("");
            $("#filevalue").val("");            
            alert(errorimagefile);
            return false;
        } 
    }
    if(x!=="")
    { 
        document.getElementById("filetext1").innerHTML = x;
    }
    else
    {
        document.getElementById("filetext1").innerHTML = chooseimage;
    }
    //document.getElementById("demo").innerHTML = "You selected: " + x;
}
$(document).ready(function (){
    
    var change=$("#change").val();
    if(change==="1")
    {
        alert(logochangesuccessfully);
    }
    
    $("#Servicecontinue").click(function (){
        var filename=$("#filevalue").val();
        if(filename=="")
        {
            $("#filevalue").val("");
            alert(chooseimage);
            return false;
        }        
    });    
    
   
    if($("#image2").attr('src')==""){
       $("#image2").hide();
    }
        var x=$("#showpassword").is(':checked'); 
        if(x===true)
        {            
            password = $("#inputpassword1").val();
            confirmpassword = $("#inputreenter1").val();
        }
        else
        {
            password = $("#inputpassword").val();
            confirmpassword = $("#inputreenter").val();
        } 
    $("#showpassword").click(function(){
        var password ="";
        var confirmpassword="";
        var x=$("#showpassword").is(':checked'); 
        if(x===true)
        {       
            password = $("#inputpassword").val();
            confirmpassword = $("#inputreenter").val();
            $("#inputpassword1").val(password);
            $("#inputreenter1").val(confirmpassword);
            $(".showornot").hide();
            $(".hideornot").show();
        }
        else
        {
            password = $("#inputpassword1").val();
            confirmpassword = $("#inputreenter1").val();
            $("#inputpassword").val(password);
            $("#inputreenter").val(confirmpassword);
            $(".hideornot").hide();
            $(".showornot").show();
        }
    });   
});
       
               var ElementID;
               /*------ get selected element ID -----*/
               function getElementID(IDNo){
               ElementID = IDNo;
                       $('.step_wrapper').on('click', '.step_box', function () {
               $(this).parent().find('.step_box').css('width', '').css('height', '').css('border-color', '').css('border-radius', '');
                       $(this).css('width', '80px').css('height', '40px').css('border-color', '#FF0000').css('border-radius', '10px');
               });
                       $("#sortable").sortable();
                       $("#sortable").disableSelection();
               }
               /*------ pass color into the selected element got by id-----*/
               function getIDNo(IDNo){
                       var s = $("#" + IDNo).attr("style");
                       var s1 = s.split(":");
                       $("#" + ElementID).css("background-color", s1[1].replace(";", " "));
               }
               var id = 1;
               var theme_id = 0;
               function doSomething(theme_id){
               var theamNum = parseInt(theme_id.replace("theme", ""));
                       theamNum--;
                       var num = theamNum * 6;
                       $("#themeid").val(theme_id);
                       for (var i = 1; i <= 6; i++){
               var colorid = "color" + (i + num);
                       $("#elementToPutStyleInto" + i).css("background-color", $("#" + colorid).css("background-color"));
               }
               }

var elementid1;
function showLook(lookid){
        $("#lookid").val(lookid);
        elementid1 = lookid;
        $('.step_wrapper').on('click', '.step_box', function () {
            $(this).parent().find('.step_box').css('background-color', '');
            $(this).css('background-color', '#DCDCDF');
        });
}
function nextLooks(){
    var lt = 0;
            lt = lt + 4;
}


function showBrand(brandid, image_name) {
    $("#image2").show();
    $("#hiddenform").val(brandid);
    $("#image2").attr('src', '/BrndBot/DownloadImage?image_type=BRAND_PERSONALITY&image_name=' + image_name);
}

function validate() {
    var password ="";
    var confirmpassword="";
    var x=$("#showpassword").is(':checked'); 
    if(x===true)
    {            
        password = $("#inputpassword1").val();
        confirmpassword = $("#inputreenter1").val();
    }
    else
    {
        password = $("#inputpassword").val();
        confirmpassword = $("#inputreenter").val();
    } 
    if (password === "") {
        alert(passwordemptyerror);
        $("#inputpassword").focus();
        return false;
    }
    if (confirmpassword === "") {
        alert(confirmpasswordemptyerror);
        $("#inputreenter").focus();
        return false;
    } else
    if (password !== confirmpassword) {
        alert(confirmpassworderror);
        $("#inputreenter").focus();
        return false;
    }
    return true;

}

function controllerUserChanges($scope, $http) {
    $scope.getLooks = function () {

        $http({
            method: 'GET',
            url: 'GetLooks?type=selected'
        }).success(function (data, status, headers, config) {
            $scope.UserLooks = data;
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });

        $http({
            method: 'GET',
            url: 'GetLooks'
        }).success(function (data, status, headers, config) {
            $scope.First = data.first;
            $scope.Second = data.second;
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    };

    $scope.updateLooks = function() {        
    var look_id = $("#lookid").val();        
    if (look_id == ""){
        alert(lockerror);
    }
    else{
        $http({
            method: 'POST',
            url: 'SetLookID?type=update&LookID='+look_id
            
        }).success(function (data, status, headers, config) {
            $scope.status= data;
            if (data === "true"){
                alert(detailssaveds);
                $scope.getLooks();
            }else
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    }        
    };
    
    $scope.getBrands = function () {

        $http({
            method: 'GET',
            url: 'GetBrandPersonality?type=selected'
        }).success(function (data, status, headers, config) {
            $scope.UserBrand = data;
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });

        $http({
            method: 'GET',
            url: 'GetBrandPersonality?type=all'
        }).success(function (data, status, headers, config) {
            $scope.First = data.first;
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    };

    $scope.updateBrands = function() {
        
    var brandID = $("#hiddenform").val();
    if (brandID == "") {
        alert(personalityerror);
    }
    else {
        $http({
            method: 'POST',
            url: 'SubbrandPersonality?brndID=' + brandID +'&type=update'
            
        }).success(function (data, status, headers, config) {
            $scope.status = data;
            if (data === "true"){
                alert(detailssaved);
                $scope.getBrands();
            }else
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    }        
    };

    $scope.changePassword = function () {
        var password ="";
        var confirmpassword="";
        var x=$("#showpassword").is(':checked'); 
        if(x===true)
        {            
            password = $("#inputpassword1").val();
            confirmpassword = $("#inputreenter1").val();
        }
        else
        {
            password = $("#inputpassword").val();
            confirmpassword = $("#inputreenter").val();
        }       

        if (validate()) {

            var password_object = {"password": password, "confirmpassword": confirmpassword, "type": "update"};

            $http({
                method: 'POST',
                url: getHost() + 'ResetUserPassword',
                headers: {'Content-Type': 'application/json'},
                data: password_object
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert(sessionexpire);
                } else if (data === "true") {
                    alert(passwordchanged);
                    $("#inputpassword1").val("");
                    $("#inputreenter1").val("");
                    $("#inputpassword").val("");
                    $("#inputreenter").val("");
                    $("#showpassword").prop( "checked", false );
                } else if (data === error) {
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
            $scope.showColors = function() {

            $http({
                        method : 'GET',
                        url : 'GetUserPreferences'
                }).success(function(data, status, headers, config) {
                        $scope.user_preferences_colors = data.user_colors;
                        $scope.user_preferences_font_names = data.user_font_names;
                        $scope.user_preferences_font_sizes = data.user_font_sizes;
                        if (data === error){
                        }
                }).error(function(data, status, headers, config) {
                    alert(nodataerror);
                });

            $http({
                    method : 'GET',
                    url : 'GetColorPalettes'
            }).success(function(data, status, headers, config) {

                $scope.themes= data;

                if (data === error){
                }
            }).error(function(data, status, headers, config) {
                    alert(nodataerror);
            });

            };
                    
            $scope.getLogoColors = function() {
                
                $http({
                    method : 'GET',
                    url : 'GetColorsFromLogo'
            }).success(function(data, status, headers, config) {
                    $scope.color = data.Colors;
                    if (data === error ){
                    }
            }).error(function(data, status, headers, config) {
                    alert(nodataerror);
            });
            };

            $scope.getLogo = function() {
                
                $http({
                    method : 'GET',
                    url : 'GetColorsFromLogo'
            }).success(function(data, status, headers, config) {
                    $scope.color = data.Colors;
                    if (data === error ){
                    }
            }).error(function(data, status, headers, config) {
                    alert(nodataerror);
            });
            };
    
    
        $scope.createUserPreferences = function()
        {
            
            var s1 =$("#elementToPutStyleInto1").css("background-color");                    
            var s2 =$("#elementToPutStyleInto2").css("background-color");
            var s3 = $("#elementToPutStyleInto3").css("background-color");                    
            var s4 = $("#elementToPutStyleInto4").css("background-color");                     
            var s5 = $("#elementToPutStyleInto5").css("background-color");                       
            var s6 = $("#elementToPutStyleInto6").css("background-color");
            document.getElementById("finalcolor1").value = s1;
            document.getElementById("finalcolor2").value = s2;
            document.getElementById("finalcolor3").value = s3;
            document.getElementById("finalcolor4").value = s4;
            document.getElementById("finalcolor5").value = s5;
            document.getElementById("finalcolor6").value = s6;

            var color1 = $("#finalcolor1").val();
            var color2 = $("#finalcolor2").val();
            var color3 = $("#finalcolor3").val();
            var color4 = $("#finalcolor4").val();
            var color5 = $("#finalcolor5").val();
            var color6 = $("#finalcolor6").val();
            if (color1 == "" || color2 == "" || color3 == "" || color4 == "" || color5 == "" || color6 == ""){
                    alert("Please fill all six colors! Click MOST USED to select colors.");
                }
           else{

                var colorObject ="{&quot;"+getColor1()+"&quot;:&quot;"+color1+"&quot;, &quot;"+getColor2()+"&quot;:&quot;"+color2+"&quot;, &quot;"+getColor3()+"&quot;:&quot;"+color3+"&quot;, &quot;"+getColor4()+"&quot;:&quot;"+color4+"&quot;, &quot;"+getColor5()+"&quot;:&quot;"+color5+"&quot;, &quot;"+getColor6()+"&quot;:&quot;"+color6+"&quot;, &quot;type&quot;:&quot;update&quot;}";

                $http({
                        method: 'POST',
                        url: getHost() + 'SetUserPreferences',
                        headers: {'Content-Type': 'application/json'},
                        data:  colorObject
                }).success(function (data){
                    $scope.status = data;
                    
                    if(data === error){
                    }else{
                        alert(detailssaved);
                        $scope.showColors();
                    }
                }).error(function(data, status) {
                        alert(requesterror);
                });
            }
        };
};