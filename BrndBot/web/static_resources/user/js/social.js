
$(document).ready(function () {
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

    $("#socialclick").click(function () {
        $("#socialdropdown").css("display", "block");
    });
    var myVar1 = '<%= code %>';    /* retrieve json from request attribute */
    var mytest = eval('(' + myVar1 + ')');

    var removecote = myVar1.replace("[", '').replace(/"/g, '').replace(']', '');
    var pages = removecote.split(",");

    if (myVar1 === "null") {
        $("#popup").hide();
    } else {
        $("#popup").show();

        for (var i = 0; i < pages.length; i = i + 3) {
            $("#fbmanagepages").append("<tr  id=page#" + i + "><td><p id=p" + i + ">" + pages[i] + "</p></td><td><input type=hidden id=access" + i + " value=" + pages[i + 1] + "></td><td><img src=" + pages[i + 2] + "></td> </tr>");
        }

        $("#content").append(" <br><center><input id=isdefault name=isdefault type=checkbox class=btn btn-primary value=default>Default</input></center>");
        $("#content").append(" <br><center><input id='facebookok' name='facebookok' type='button' class='btn btn-primary' value='ok' ng-click='getfacebookdetails()' >&nbsp;&nbsp;<input id=close name=close type=button class=btn btn-primary value=cancel></center>");
    }

    var managed_page = "";
    var default_access_token = "";
    var fb_user_profile_name = "";
    var check_default = "false";
    var check_default_managed_page;
    $("tr").click(function () {
        var id = this.id.split("#");

        var page = $("#p" + id[1]).text();
        var accessToken = $("#access" + id[1]).val();
        $("#pagenameSend").val(page);
        $("#fbaccessTokenSend").val(accessToken);
        $("#fbdefaultAccessToken").val("true");
        check_default = $("#fbdefaultAccessToken").val();

    });

    $("#isdefault").click(function () {
        if (check_default == "true") {
            default_access_token = $("#fbaccessTokenSend").val();
            managed_page = $("#pagenameSend").val();
            fb_user_profile_name = $("#fbusername").val();
        }

    });

    $("#facebookok").click(function () {
        check_default_managed_page = document.getElementById("isdefault").checked;

        if ((check_default_managed_page === true) && (check_default === "true")) {
            $.ajax({
                url: getHost() + '/settings/facebookDetails',
                method: 'post',
                data: {
                    default_page_name: managed_page,
                    fb_user_profile_name: fb_user_profile_name,
                    access_token_method: "setAccessToken",
                    access_token: default_access_token
                },
                success: function (responseText) {
                    alert("sucess");
                    $("#popup").hide();
                    $("#submitbutton").prop("disabled", false);
                    angular.element(document.getElementById('controllerSocial')).scope().getFacebookDetails();
                }
            });
        } else if ((check_default_managed_page === false) && (check_default === "true")) {
            $("#popup").hide();
            $("#submitbutton").prop("disabled", false);
        } else {
            alert(nodefaultpage);
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
        $http({
            url: getHost() + 'settings/facebookDetails.do',
            method: "POST",
            data: JSON.stringify({
                access_token_method: "getAccessToken"
            })
        }).success(function (data, status, headers, config) {
            var facebookDetails = data.d.message;
            var facebookDetailsArray = facebookDetails.split(",");
            $scope.user_profile_page = facebookDetailsArray[1];
            $scope.fb_default_page_name = facebookDetailsArray[2];

        }).error(function (data, status, headers, config) {
            alert(nodataerror);
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.getManagePage = function () {
        $http({
            url: getHost() + 'settings/fbAuthURL',
            method: "Post",
            data: JSON.stringify({
                redirectUrl: "user/social"
            })
        }).success(function (data) {
            alert(JSON.stringify(data.d.details[0]));
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
                alert("da"+JSON.stringify(data));
                alert(JSON.stringify(data.d.details[0].user_profile_name));
                $("#fbmanagePagePopUp").show();
                $scope.fbPagesDetails = data.d.details[0].fbPages;
                 $scope.fbProfileName = data.d.details[0].user_profile_name;
            });
        }
    };
    $scope.setPageAccessToken = function (accessToken, pageName,profileName) {
        localStorage.setItem("CurrentFbAccessToken", accessToken);
        localStorage.setItem("CurrentFbPageName", pageName);
        localStorage.setItem("FbProfileName",profileName);
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
                    fb_user_profile_name:localStorage.getItem("FbProfileName")
                })
            }).success(function (data) {
                $("#fbmanagePagePopUp").hide();
                window.location = getHost() + "user/social";
            });
        }
        else{
            alert("Plese select a page");
        }
 
    };
    $scope.clearFacebookDetails = function () {

        if (confirm(clearconfirm)) {
            $("#fbclear").hide();
            $http({
                method: 'POST',
                url: getHost() + 'settings/facebookDetails?access_token_method=clearFacebookDetails'
            }).success(function (data, status, headers, config) {
                alert(detailsclear);
                $scope.getfacebookdetails();
            }).error(function (data, status, headers, config) {
                alert(nodataerror);
            });
        }
    };

    $scope.getTwitterDetails = function () {

        var access_token_method = "";
        var twitter_access_tokens = "";
        var setting = "";

        var settings = {"access_token_method": "setAccessToken", "twitter_access_tokens": "getAccessToken", "settings": setting};

        $http({
            method: 'POST',
            url: getHost() + '/settings/twitterDetails',
            data: JSON.stringify(settings)
        }).success(function (data, status, headers, config) {
            alert(JSON.stringify(data));
            $scope.twitterPage = data;

            if (data.TwitterLoggedIn == "true") {
                $("#twitterclear").show();
            } else {
                $("#twitterclear").hide();
            }
        }).error(function (data, status, headers, config) {
            alert(data);
            alert(nodataerror);
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.clearTwitterDetails = function () {

        if (confirm('Do you want to really clear the details?')) {
            $("#twitterclear").hide();
            $http({
                method: 'POST',
                url: getHost() + '/settings/twitterDetails?access_token_method=clearTwitterDetails'
            }).success(function (data, status, headers, config) {
                $scope.getTwitterDetails();
            }).error(function (data, status, headers, config) {
                alert(nodataerror);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
            alert(detailsclear);
        }
    };
}    
