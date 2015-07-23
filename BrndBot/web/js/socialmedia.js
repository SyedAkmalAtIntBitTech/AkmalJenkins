/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var facebookcheck;
var twittercheck;
$(document).ready(function () {

    $("#facebook").click(function () {

        facebookcheck = document.getElementById("facebook").checked;
//               alert(facebookcheck);
        if (facebookcheck)
        {
            document.location.href = "GetFacebookManagePage";

            $("#isFacebook").val(facebookcheck);
        }
        else {
            $("#isFacebook").val(facebookcheck);
        }

        $("#close").click(function () {

            $("#popup").hide();

        });
    });

    $("#twitter").click(function () {
        twittercheck = document.getElementById("twitter").checked;
//            alert(twittercheck);
        $("#submitbutton").prop("disabled", true);
        $("#isTwitter").val(twittercheck);

        if (twittercheck) {
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
//                alert($("#pinTextBox").val());

                $.ajax({
                    url: 'GetTwitterToken',
                    method: 'post',
                    data: {
                        pin: $("#pinTextBox").val()
                    },
                    success: function (responseText) {
//                        $("#tokenHere").html(responseText);
//                        alert(responseText);
                        $("#twaccessTokenSend").val(responseText);
                        $("#submitbutton").prop("disabled", false);
                    }
                });
                $("#twitterpopup").hide();


            });

        }
        else
        {
            $("#twitterpopup").hide();
        }

    });



});