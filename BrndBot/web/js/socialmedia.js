/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var facebookcheck;
var twittercheck;
$(document).ready(function () {

    $("#fb").click(function () {
        $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","300px").css("left","500px");
        facebookcheck = document.getElementById("facebook").checked;

        if (facebookcheck) {

            $.ajax({
                url: 'ServletUserPreferencesFacebook',
                method: 'GET',
                data: {
                    access_token_method: "getAccessToken"
                },
                success: function (responseText) {
//                           $("#tokenHere").html(responseText);
                        
                        var fb_details = responseText.split(",");
                    
                    if (fb_details[0] == "") {

                        document.location.href = "GetFacebookManagePage";

                        $("#isFacebook").val(facebookcheck);
                        $('#loadingGif').remove();

                    } else {
                        
//                        var fb_details = responseText.split(",");
                        
                        $("#fbaccessTokenSend").val(fb_details[0]);
                        $("#pagenameSend").val(fb_details[2]);
                        $("#fbdefaultAccessToken").val("true");
                        $("#isFacebook").val("true");

                        $("#submitbutton").prop("disabled", false);
                        $('#loadingGif').remove();
                    }
                }
            });

        } else {
            $("#isFacebook").val(facebookcheck);
            $("#submitbutton").prop("disabled", true);
            $("#fbaccessTokenSend").val("");
            $("#fbdefaultAccessToken").val("");
        }


        $("#close").click(function () {

            //$("#popup").hide();
            $(".close-reveal-modal").click();
        });
    });

    $("#twt").click(function () {
        $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","300px").css("left","500px");
        twittercheck = document.getElementById("twitter").checked;
           
        $("#submitbutton").prop("disabled", true);
        $("#isTwitter").val(twittercheck);
        var twitter_access_tokens = "";

        if (twittercheck) {

            $.ajax({
                url: 'ServletUserPreferencesTwitter',
                method: 'post',
                data: {
                    access_token_method: "getAccessToken"
                },
                success: function (responseText) {
                    if (responseText == "") {

                        //$("#twitterpopup").show();
                        
                        $(".clicktwitter").click();
                        $.ajax({
                            url: 'GetTwitterToken',
                            method: 'get',
                            success: function (responseText) {
                                $("#twitterlink").html("<a href='" + responseText + "' target='_blank'>click here</a>");
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
                                                $('#loadingGif').remove();
                                            }
                                        });

                                        $("#submitbutton").prop("disabled", false);
                                    }
                                });
                                
                                //$("#twitterpopup").hide();
                                $(".close-reveal-modal").click();

                            } else {
                                alert("Please enter the pin code");
                                $("#pinTextBox").focus();
                            }
                                $('#loadingGif').remove();
                        });

                    } else {
                        $("#twaccessTokenSend").val(responseText);
                        $("#submitbutton").prop("disabled", false);
                         $('#loadingGif').remove();
                    }

                }
            });

        }
        else
        {
            $("#twaccessTokenSend").val("");
            //$("#twitterpopup").hide();
            $(".close-reveal-modal").click();
            $("#submitbutton").prop("disabled", true);
             $('#loadingGif').remove();
        }
       
    });
    $("#closetwitter").click(function () {

        //$("#twitterpopup").hide();
        $(".close-reveal-modal").click();
        $("#submitbutton").prop("disabled", true);


    });
});