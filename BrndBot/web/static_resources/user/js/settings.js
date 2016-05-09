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
function getIDNo(IDNo) {
    var s = $("#" + IDNo).attr("style");
    var s1 = s.split(":");
    $("#" + ElementID).css("background-color", s1[1].replace(";", " "));
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
                if (data === "false") {
                    alert(sessionexpire);
                } else if (data === "true") {
                    alert(passwordchanged);
                    $("#inputpassword1").val("");
                    $("#inputreenter1").val("");
                    $("#inputpassword").val("");
                    $("#inputreenter").val("");
                    $("#showpassword").prop("checked", false);
                } else if (data === error) {
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    $scope.showColors = function () {
        showOverlay();
        $http({
            method: 'GET',
            url: getHost() + 'settings/getColors'
        }).success(function (data, status, headers, config) {
            hideOverlay();
            var parseData = JSON.parse(data.d.details[0]);
            $scope.user_preferences_colors = JSON.parse(JSON.stringify(parseData));
        }).error(function (data, status, headers, config) {
            hideOverlay();
            alert(JSON.stringify(data));
//            alert(nodataerror);
        });

//        $http({
//            method: 'GET',
//            url: getHost()+'GetColorPalettes'
//        }).success(function (data, status, headers, config) {
//
//            $scope.themes = data;
//            hideOverlay();
//            if (data === error) {
//            }
//        }).error(function (data, status, headers, config) {
//            hideOverlay();
//            alert(nodataerror);
//        });

    };

    $scope.getLogoColors = function () {
        showOverlay();
        $http({
            method: 'GET',
            url: getHost() +'onboarding/getColorsForLogo'
        }).success(function (data, status, headers, config) {
//          alert(JSON.stringify(data.d.details));
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

            var colorObject = '{"color1":'+color1+',"color2":'+color2+',"color3":'+color3+',"color4":'+color4+'}';

            $http({
                method: 'POST',
                url: getHost() + 'settings/setColors',
                headers: {'Content-Type': 'application/json'},
                data: colorObject
            }).success(function (data) {
                $scope.status = data;

                if (data === error) {
                } else {
                    alert(detailssaved);
                    $scope.showColors();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
}
;