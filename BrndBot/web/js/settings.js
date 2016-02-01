/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


       $(document).ready(function (){
           if($("#image2").attr('src')==""){
              $("#image2").hide();
           }
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
    var password = $("#inputpassword").val();
    var confirmpassword = $("#inputreenter").val();
    if (password === "") {
        alert("Password not entered! Please enter the password.");
        $("#inputpassword").focus();
        return false;
    }
    if (confirmpassword === "") {
        alert("Confirm password not entered! Please enter the confirm password.");
        $("#inputreenter").focus();
        return false;
    } else
    if (password !== confirmpassword) {
        alert("Confirm password does't match to password!");
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
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
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
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    };

    $scope.updateLooks = function() {
        
        var look_id = $("#lookid").val();
        
     if (look_id == ""){
        alert('Please select a look');
    }
    else{
        $http({
            method: 'POST',
            url: 'SetLookID?type=update&LookID='+look_id
            
        }).success(function (data, status, headers, config) {
            $scope.status= data;
            if (data === "true"){
                alert("Details updated successfully.");
                $scope.getLooks();
            }else
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
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
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

        $http({
            method: 'GET',
            url: 'GetBrandPersonality?type=all'
        }).success(function (data, status, headers, config) {
            $scope.First = data.first;
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    };

    $scope.updateBrands = function() {
        
    var brandID = $("#hiddenform").val();
    if (brandID == "") {
        alert("Please select a personality!");
    }
    else {
        $http({
            method: 'POST',
            url: 'SubbrandPersonality?brndID=' + brandID +'&type=update'
            
        }).success(function (data, status, headers, config) {
            $scope.status = data;
            if (data === "true"){
                alert("Details updated successfully.");
                $scope.getBrands();
            }else
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    }
        
    };

    $scope.changePassword = function () {
        var password = $("#inputpassword").val();
        var confirmpassword = $("#inputreenter").val();

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
                    alert("User session has expired! Kindly resubmit a request.");
                } else if (data === "true") {
                    alert("password has been changed successfully.");
                    $("#inputpassword").val("");
                    $("#inputreenter").val("");
                } else if (data === error) {
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("Request not successful!");
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
                alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                });

            $http({
                    method : 'GET',
                    url : 'GetColorPalettes'
            }).success(function(data, status, headers, config) {

                $scope.themes= data;

                if (data === error){
                }
            }).error(function(data, status, headers, config) {
                    alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
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
                    alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
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
                    alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
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
                        alert("Details saved successfully.");
                        $scope.showColors();
//                        window.open(getHost() + 'settings.jsp', "_self");
                    }
                }).error(function(data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                        alert("Request not successful!");
                });
        }
        };

};
