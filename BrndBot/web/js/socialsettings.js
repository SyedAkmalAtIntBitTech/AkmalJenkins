/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var facebookcheck;
var twittercheck;
$(document).ready(function () {

    $("#facebook").click(function () {

//        facebookcheck = document.getElementById("facebook").checked;
//
//        if (facebookcheck) {

            $.ajax({
                url: 'ServletUserPreferencesFacebook',
                method: 'GET',
                data: {
                    access_token_method: "getAccessToken"
                },
                success: function (responseText) {
//                           $("#tokenHere").html(responseText);
//                           alert(responseText);
                        var fb_details = responseText.split(",");
                    
                    if (fb_details[0] == "") {

                        document.location.href = "GetUserFacebookManagePages";

//                        $("#isFacebook").val(facebookcheck);

                    } else {
                        document.location.href = "GetUserFacebookManagePages";
//
////                        var fb_details = responseText.split(",");
//                        
//                        $("#fbaccessTokenSend").val(fb_details[0]);
//                        $("#pagenameSend").val(fb_details[2]);
//                        $("#fbdefaultAccessToken").val("true");
//                        $("#isFacebook").val("true");
//
//                        $("#submitbutton").prop("disabled", false);
                    }
                }
            });

//        } else {
//            $("#isFacebook").val(facebookcheck);
//            $("#submitbutton").prop("disabled", true);
//            $("#fbaccessTokenSend").val("");
//            $("#fbdefaultAccessToken").val("");
//        }


        $("#close").click(function () {

            $("#popup").hide();

        });
    });

    $("#twitter").click(function () {
        twittercheck = document.getElementById("twitter").checked;
//            alert(twittercheck);
        $("#submitbutton").prop("disabled", true);
        $("#isTwitter").val(twittercheck);
        var twitter_access_tokens = "";

//        if (twittercheck) {

            $.ajax({
                url: 'ServletUserPreferencesTwitter',
                method: 'post',
                data: {
                    access_token_method: "getAccessToken"
                },
                success: function (responseText) {
                    if (responseText == "") {

                        $("#twitterpopup").show();

                        $.ajax({
                            url: 'GetTwitterToken',
                            method: 'get',
                            success: function (responseText) {
                                $("#twitterlink").html("<a href='" + responseText + "' target='_blank'>click here</a>");
                                //alert(responseText);
                            }
                        });
                        $('#setPin').click(function () {
                            var pin = $("#pinTextBox").val();

                            if (pin.length > 0) {
                                $.ajax({
                                    url: 'GetTwitterToken',
                                    method: 'post',
                                    data: {
                                        pin: $("#pinTextBox").val()
                                    },
                                    success: function (responseText) {
                                        //                        $("#tokenHere").html(responseText);
                                        $("#twaccessTokenSend").val(responseText);
                                        twitter_access_tokens = responseText;
                                        $.ajax({
                                            url: 'ServletUserPreferencesTwitter',
                                            method: 'post',
                                            data: {
                                                access_token_method: "setAccessToken",
                                                twitter_access_tokens: twitter_access_tokens
                                            },
                                            success: function (responseText) {
                                    angular.element(document.getElementById('controllerSocial')).scope().getTwitterDetails();                                    
                                                
                                            }
                                        });

                                        $("#submitbutton").prop("disabled", false);
                                    }
                                });

                                $("#twitterpopup").hide();


                            } else {
                                alert("Please enter the pin code");
                                $("#pinTextBox").focus();
                            }

                        });

                    } else {
                        $("#twaccessTokenSend").val(responseText);
                        $("#submitbutton").prop("disabled", false);

                    }

                }
            });

//        }
//        else
//        {
//            $("#twaccessTokenSend").val("");
//            $("#twitterpopup").hide();
//            $("#submitbutton").prop("disabled", true);
//        }

    });
    $("#closetwitter").click(function () {

        $("#twitterpopup").hide();
        $("#submitbutton").prop("disabled", true);


    });
});