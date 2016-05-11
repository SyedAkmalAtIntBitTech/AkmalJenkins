/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var x = "";
function changefilename() {
    x = document.getElementById("filevalue").value;




    if (x === "") {
        alert(chooseimage);
        return false;
    }
    if (imagefilevalidation("filevalue")) {
    } else
    {
        $("#filetext1").val(chooseimage);
        return false;
    }
    if (x !== "")
    {
        document.getElementById("filetext1").innerHTML = x;
    } else
    {
        document.getElementById("filetext1").innerHTML = chooseimage;
    }
    //document.getElementById("demo").innerHTML = "You selected: " + x;
}
var attempt = 0;
$(document).ready(function () {
    var change = $("#change").val();
    if (change === "1")
    {
        alert(logochangesuccessfully);
    }

    $("#Servicecontinue").click(function () {
        var filename = $("#filevalue").val();
        if (filename == "")
        {
            $("#filevalue").val("");
            alert(chooseimage);
            return false;
        }
    });


    if ($("#image2").attr('src') == "") {
        $("#image2").hide();
    }
    var x = $("#showpassword").is(':checked');
    if (x === true)
    {
        password = $("#inputpassword1").val();
        confirmpassword = $("#inputreenter1").val();
    } else
    {
        password = $("#inputpassword").val();
        confirmpassword = $("#inputreenter").val();
    }
    $("#showpassword").click(function () {
        var password = "";
        var confirmpassword = "";
        var x = $("#showpassword").is(':checked');
        if (x === true)
        {
            password = $("#inputpassword").val();
            confirmpassword = $("#inputreenter").val();
            $("#inputpassword1").val(password);
            $("#inputreenter1").val(confirmpassword);
            $(".showornot").hide();
            $(".hideornot").show();
        } else
        {
            password = $("#inputpassword1").val();
            confirmpassword = $("#inputreenter1").val();
            $("#inputpassword").val(password);
            $("#inputreenter").val(confirmpassword);
            $(".hideornot").hide();
            $(".showornot").show();
        }
    });
   $("#fromThem").click(function(){
        $("#logocolor").hide();
        $("#custom").hide();
        $("#picktheme").show();
    });
   $("#fromPalette").click(function(){
        $("#picktheme").hide();
        $("#logocolor").hide();
        $("#custom").show();
        
    });
   $("#fromLogo").click(function(){
        $("#picktheme").hide();
        $("#custom").hide();
        $("#logocolor").show();
    });
    
$("form#data").submit(function(){
        alert();
    var formData = new FormData($(this)[0]);

            $.ajax({
                url: getHost() +"settings/changeLogo.do",
                type: 'POST',
                data: formData,
                async: false,
                success: function (data) {
                    alert(JSON.stringify(data));
                },
                cache: false,
                contentType: false,
                processData: false
            });

    return false;
});
    
});

var ElementID;
/*------ get selected element ID -----*/
function getElementID(IDNo) {
    ElementID = IDNo;
    $('.step_wrapper').on('click', '.step_box', function () {
        $(this).parent().find('.step_box').css('width', '').css('height', '').css('border-color', '').css('border-radius', '');
        $(this).css('width', '80px').css('height', '40px').css('border-color', '#FF0000').css('border-radius', '10px');
    });
    $("#sortable").sortable();
    $("#sortable").disableSelection();
}
/*------ pass color into the selected element got by id-----*/
function setSelectedColor(color){
    $("#" + ElementID).css("background-color", color);
}
var id = 1;
var theme_id = 0;
function doSomething(theme_id) {
    var theamNum = parseInt(theme_id.replace("theme", ""));
    theamNum--;
    var num = theamNum * 6;
    $("#themeid").val(theme_id);
    for (var i = 1; i <= 6; i++) {
        var colorid = "color" + (i + num);
        $("#elementToPutStyleInto" + i).css("background-color", $("#" + colorid).css("background-color"));
    }
}
function setThemeColors(color1,color2,color3,color4){
     $("#elementToPutStyleInto1").css("background-color",color1);
     $("#elementToPutStyleInto2").css("background-color",color2);
     $("#elementToPutStyleInto3").css("background-color",color3);
     $("#elementToPutStyleInto4").css("background-color",color4);
} 

var elementid1;
function showLook(lookid) {
    $("#lookid").val(lookid);
    elementid1 = lookid;
    $('.step_wrapper').on('click', '.step_box', function () {
        $(this).parent().find('.step_box').css('background-color', '');
        $(this).css('background-color', '#DCDCDF');
    });
}
function nextLooks() {
    var lt = 0;
    lt = lt + 4;
}


function showBrand(brandid, image_name) {
    $("#image2").show();
    $("#hiddenform").val(brandid);
    $("#image2").attr('src', '/BrndBot/DownloadImage?image_type=BRAND_PERSONALITY&image_name=' + image_name);
}

function validate() {
    var password = "";
    var confirmpassword = "";
    var x = $("#showpassword").is(':checked');
    if (x === true)
    {
        password = $("#inputpassword1").val();
        confirmpassword = $("#inputreenter1").val();
    } else
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
    
    $scope.changePassword = function () {
        var password = "";
        var confirmpassword = "";
        var x = $("#showpassword").is(':checked');
        if (x === true)
        {
            password = $("#inputpassword1").val();
            confirmpassword = $("#inputreenter1").val();
        } else
        {
            password = $("#inputpassword").val();
            confirmpassword = $("#inputreenter").val();
        }

        if (validate()) {

            var password_object = {"password": password, "confirmpassword": confirmpassword, "type": "update"};

            $http({
                method: 'POST',
                url: getHost() + 'signup/resetPassword',
                headers: {'Content-Type': 'application/json'},
                data: password_object
            }).success(function (data)
            {
                $scope.status = data;
                    $("#inputpassword1").val("");
                    $("#inputreenter1").val("");
                    $("#inputpassword").val("");
                    $("#inputreenter").val("");
                    $("#showpassword").prop("checked", false);
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    $scope.showColors = function () {
        $scope.getLogoColors();
        showOverlay();
        $http({
            method: 'GET',
            url: getHost() + 'settings/getColors'
        }).success(function (data) {
            hideOverlay();
            $scope.user_preferences_colors = data.d.details;
        }).error(function (data, status, headers, config) {
            hideOverlay();
            alert(nodataerror);
        });

        $http({
            method: 'GET',
            url: getHost()+'getAllColorThemes'
        }).success(function (data, status, headers, config) {
            $scope.themes = data.d.details;
            hideOverlay();
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            hideOverlay();
            alert(nodataerror);
        });

    };

    $scope.getLogoColors = function () {
        showOverlay();
        $http({
            method: 'GET',
            url: getHost() +'onboarding/getColorsForLogo'
        }).success(function (data, status, headers, config) {
            $scope.color =data.d.details;
            if (data === error) {
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    };

    $scope.createUserPreferences = function ()
    {
        var s1 = $("#elementToPutStyleInto1").css("background-color");
        var s2 = $("#elementToPutStyleInto2").css("background-color");
        var s3 = $("#elementToPutStyleInto3").css("background-color");
        var s4 = $("#elementToPutStyleInto4").css("background-color");
        document.getElementById("finalcolor1").value = s1;
        document.getElementById("finalcolor2").value = s2;
        document.getElementById("finalcolor3").value = s3;
        document.getElementById("finalcolor4").value = s4;
        var color1 = $("#finalcolor1").val();
        var color2 = $("#finalcolor2").val();
        var color3 = $("#finalcolor3").val();
        var color4 = $("#finalcolor4").val();
        if (color1 == "" || color2 == "" || color3 == "" || color4 == "") {
            alert("Please fill all six colors! Click MOST USED to select colors.");
        } else {
            var colorObject = '{"color1":"'+color1+'","color2":"'+color2+'","color3":"'+color3+'","color4":"'+color4+'"}';
            $http({
                method: 'POST',
                url: getHost() + 'settings/setColors',
                data: colorObject
            }).success(function (data) {
                $scope.status = data;
                    alert(detailssaved);
                    $scope.showColors();
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
};
