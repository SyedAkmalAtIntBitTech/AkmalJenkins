
$(document).ready(function () {
    $("#socialclick").click(function(){
    $("#socialdropdown").css("display","block");
       });
    $("#twsetting").click(function () {
        $("#view1").hide();
        $("#view2").show();

        $("#twsetting").removeClass("top-subnav-links");
        $("#twsetting a").removeClass("h3");
        $("#fbsetting").removeClass("top-subnav-link-active");
        $("#fbsetting a").removeClass("h3-active-subnav");

        $("#twsetting").addClass("top-subnav-link-active");
        $("#twsetting a").addClass("h3-active-subnav");
        $("#fbsetting").addClass("top-subnav-links");
        $("#fbsetting a").addClass("h3");

    });
    $("#fbsetting").click(function () {
        $("#view2").hide();
        $("#view1").show();

        $("#twsetting").removeClass("top-subnav-link-active");
        $("#twsetting a").removeClass("h3-active-subnav");
        $("#fbsetting").removeClass("top-subnav-links");
        $("#fbsetting a").removeClass("h3");

        $("#twsetting").addClass("top-subnav-links");
        $("#twsetting a").addClass("h3");
        $("#fbsetting").addClass("top-subnav-link-active");
        $("#fbsetting a").addClass("h3-active-subnav");
    });
    $("#socialdropdown").mouseout(function () {
        // $("#socialdropdown").hide();
    });
    var mouse_is_inside = false;
    $('#socialdropdown').hover(function () {
        mouse_is_inside = true;
    }, function () {
        mouse_is_inside = false;
    });

    $("body").mouseover(function () {
        if (!mouse_is_inside) {
            $('#socialdropdown').hide();
        }
    });

    $("#close").click(function () {
        $("#fbaccessTokenSend").val("");
        $("#fbdefaultAccessToken").val("");
        $("#isFacebook").val("false");
        $("#facebook").prop("checked", false);
        $("#popup").hide();
        $("#twitterpopup").hide();
        $("#submitbutton").prop("disabled", true);
    });
});

function imgchng() {
    document.getElementById("fb").src = "images/fb_icon.png";

}

function controllerSocial($scope, $http) {
    $scope.getFacebookDetails = function () {
        alert();
//        
//        $http({
//            url: getHost() +'settings/facebookDetails.do',
//            method: "POST",
//            data: JSON.stringify({
//                access_token_method: "getAccessToken"
//            })
//        }).success(function (data, status, headers, config) {alert();
//            var facebookDetails = data.d.message;
//            var facebookDetailsArray = facebookDetails.split(",");
//            if (facebookDetailsArray[1] === "null") {
//                $scope.user_profile_page = " - ";
//                $scope.fb_default_page_name = " - ";
//                $("#facebook").text("Login");
//            } else {
//                $scope.user_profile_page = facebookDetailsArray[1];
//                $scope.fb_default_page_name = facebookDetailsArray[2];
//                $("#fbclear").show();
//            }
//
//        }).error(function (data, status, headers, config) {
//            alert(nodataerror);
//            // called asynchronously if an error occurs
//            // or server returns response with an error status.
//        });
    };

    $scope.getManagePage = function () {
        $scope.facebook = true;
        alert("...");
        alert("zas0");
        $http({
            url: "http://localhost:8080/BrndBot/" + 'settings/fbAuthURL',
            method: "POST",
            data: JSON.stringify({
                redirectUrl: "user/social"
            })
        }).success(function (data) {
            alert("data");
            window.location = data.d.details[0];

        });
    };
    $scope.checkForCode = function () {
        var code = getUrlParameter("code");
        if (typeof code !== "undefined") {
            $http({
                url: getHost() + 'settings/fbGetToken/' + code,
                method: "GET"
            }).success(function (data) {
                $("#fbmanagePagePopUp").show();
                $scope.fbPagesDetails = data.d.details[0].fbPages;
                $scope.fbProfileName = data.d.details[0].user_profile_name;
            });
        }
    };
    $scope.setPageAccessToken = function (accessToken, pageName, profileName) {
        localStorage.setItem("CurrentFbAccessToken", accessToken);
        localStorage.setItem("CurrentFbPageName", pageName);
        localStorage.setItem("FbProfileName", profileName);
    };
    $scope.selectImage = function (id){
        $('.gallery-item-wrap-selected-true').addClass('gallery-item-wrap-selected').removeClass('gallery-item-wrap-selected-true');
        $("."+id).removeClass('gallery-item-wrap-selected').addClass('gallery-item-wrap-selected-true');
    };
    
    $scope.postToSelectedPage = function () {
        var addDafaultmanagePage = $("#setDefaultManagePage").prop('checked');
        if (addDafaultmanagePage) {
            $http({
                url: getHost() + 'settings/facebookDetails.do',
                method: "POST",
                data: JSON.stringify({
                    access_token_method: "setAccessToken",
                    access_token: localStorage.getItem("CurrentFbAccessToken"),
                    default_page_name: localStorage.getItem("CurrentFbPageName"),
                    fb_user_profile_name: localStorage.getItem("FbProfileName")
                })
            }).success(function (data) {
                $("#fbmanagePagePopUp").hide();
                window.location = getHost() + "user/social";
            });
        } else {
            alert("Please select any page and set as Default.");
        }

    };
    $scope.clearFacebookDetails = function () {
        if (confirm(clearconfirm)) {
            $("#fbclear").hide();
            $http({
                method: 'POST',
                url: getHost() + 'settings/facebookDetails.do',
                data: JSON.stringify({
                    access_token_method: "clearFacebookDetails"
                })
            }).success(function (data, status, headers, config) {
                alert(detailsclear);
                $scope.getFacebookDetails();
            }).error(function (data, status, headers, config) {
                alert(nodataerror);
            });
        }
    };

    $scope.getTwitterDetails = function () {
        $http({
            url: getHost() + '/settings/twitterDetails.do',
            method: "POST",
            data: JSON.stringify({
                access_token_method: "getAccessToken"
            })
        }).success(function (data, status, headers, config) {
            var twitterData = data.d.message.split(",");
            var twitterprofileName = twitterData[2];
            if (typeof twitterprofileName === "undefined") {
                $scope.twitterProfileName = " - ";
                $("#twitterLogoutButton").hide();
                $("#twitterLogInButton").show();
            } else {
                $scope.twitterProfileName = twitterprofileName;
                $("#twitterLogoutButton").show();
                $("#twitterLogInButton").hide();
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });
    };

    $scope.clearTwitterDetails = function () {
        if (confirm('Do you want to really clear the details?')) {
            $("#twitterclear").hide();
            $http({
                url: getHost() + '/settings/twitterDetails.do',
                method: "POST",
                data: JSON.stringify({
                    access_token_method: "clearTwitterDetails"
                })
            }).success(function (data, status, headers, config) {
                $scope.getTwitterDetails();
            }).error(function (data, status, headers, config) {
                alert(nodataerror);
            });
            alert(detailsclear);
        }
    };
}
function getAuthURLFromSocialHub() {
    $.ajax({
        url: getHost() + 'settings/twitterAuthURL.do',
        method: 'GET',
        success: function (responseText) {
            $("#twitterSetPinPopUpFromSocialhub").show();
            $("#twitterlink").html("<a href='" + responseText.d.details[0] + "' target='_blank'>get your pin</a>");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + JSON.stringify(jqXHR));
        }
    });
}

function setTwitterAccessTokenFromSocialHub() {
    var pin = $("#pinTextBox").val();
    if (pin.length > 0) {
        $.ajax({
            url: getHost() + 'settings/twitterGetToken/' + pin,
            method: 'GET',
            success: function (responseText) {
                $("#twitterSetPinPopUpFromSocialhub").hide();
                window.location = getHost() + "user/social";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(JSON.stringify(jqXHR));
            }
        });

    } else {
        alert(pinerror);
        $("#pinTextBox").focus();
    }
}
