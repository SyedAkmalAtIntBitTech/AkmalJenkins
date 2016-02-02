
$(document).ready(function(){
    var count=0;
    $("#mousef").click(function(){
        var facebookcheck = "true";
        var facebookcheckimg = $("#fb").attr("src");     
        if(facebookcheckimg ==="images/white.png"){}
        else
        {
            facebookcheck = "false";
        }
        if(facebookcheck === "true")
        {
            $.ajax({
                url: 'ServletUserPreferencesFacebook',
                method: 'GET',
                data: {
                    access_token_method: "getAccessToken"
                },
                success: function (responseText) {
                    var fb_details = responseText.split(",");
                    if (fb_details[0] == "") {    
                        document.location.href = "GetFacebookManagePage";
                        $("#isFacebook").val(facebookcheck);
                    } else {
                        $("#fbaccessTokenSend").val(fb_details[0]);
                        $("#pagenameSend").val(fb_details[2]);
                        $("#fbdefaultAccessToken").val("true");
                        $("#isFacebook").val("true");
                        $('#loadingGif').hide();
                    }
                }
            });
            document.getElementById("facebook").checked=true;
            document.getElementById("fb").src="images/fbButton_darkblue_new.svg"; 
            $("#mousef").css("background-color","#F9F9F9");
            $("#isFacebook").val("true");
            count++;
        }
        else
        {
            $("#isFacebook").val(facebookcheck);
            $("#fbaccessTokenSend").val("");
            $("#fbdefaultAccessToken").val("");
            $('#loadingGif').hide();
            document.getElementById("fb").src=""; 
            document.getElementById("facebook").checked=false;   
            $("#isFacebook").val("false");
            $("#mousef").css("background-color","#FFFFFF");
            count--;
        }
        if(count >0)
        {
            $("#submitbutton").prop("disabled",false);
        }
        else
        {
            $("#submitbutton").prop("disabled",true);
        }
        if(facebookcheckimg ==="images/white.png")
        {
            document.getElementById("facebook").checked=true;
        }
        else
        {
            document.getElementById("facebook").checked=false;
            $("#fbimgd").html('<img id="fb" class="chooseList-icon" src="images/white.png">');
            $("#mousef").css("background-color","#ffffff");
        }
   });
   
    $("#twpopupClose2").click(function(){
        $("#fade").hide();
        $("#twpopup").hide();
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
                            alert(twittertokenseccess)
                            $("#twpopup").hide();                            
                            $("#fade").hide();
                        }
                    });
                }
            });

        } else {
            alert(pinerror);
            $("#pinTextBox").focus();
        }
    });
    $("#mouset").click(function(){
        var twittercheck = "true";
        var twittercheckimg = $("#twt").attr("src"); 
        if(twittercheckimg==="images/white.png")
        {
            document.getElementById("twitter").checked=true;
        }       
        else
        {
            twittercheck="false";
        }
        if(twittercheck === "true")
        {
           $.ajax({
                url: 'ServletUserPreferencesTwitter',
                method: 'post',
                data: {
                    access_token_method: "getAccessToken"
                },
                success: function (responseText) {
                    if (responseText == "") {                             
                        $("#fade").show();
                        $("#twpopup").show();
                        $.ajax({
                            url: 'GetTwitterToken',
                            method: 'get',
                            success: function (responseText) {   
                                $("#twitterlink").html("<a href='" + responseText + "' target='_blank'>get your pin</a>");
                            }
                        });
                    } else {
                        $("#twaccessTokenSend").val(responseText);
                    }
                }
            });
            document.getElementById("twitter").checked=true;
            document.getElementById("twt").src="images/twtButton_lightblue_new.svg";
            $("#mouset").css("background-color","#ffffff");
            $("#isTwitter").val("true");
            $("#mouset").css("background-color","#F9F9F9");
            count++;
        }
        else
        {
            $("#twaccessTokenSend").val("");
            $(".close-reveal-modal").click();
            $('#loadingGif').hide();
            document.getElementById("twt").src="";
            document.getElementById("twitter").checked=false;
            $("#isTwitter").val("false");
            $("#mouset").css("background-color","#F9F9F9");
            count--;
        }
        if(count >0)
        {
            $("#submitbutton").prop("disabled",false);
        }
        else
        {
            $("#submitbutton").prop("disabled",true);
        }
        if(twittercheckimg==="images/white.png")
        {
            document.getElementById("twitter").checked=true;
        }       
        else
        {
            document.getElementById("twitter").checked=false;
            $("#twimgd").html('<img id="twt" class="chooseList-icon" src="images/white.png">');
            $("#mouset").css("background-color","#ffffff");
        }
    });
});
  
$("#closetwitter").click(function () {
    $(".close-reveal-modal").click();
    $("#submitbutton").prop("disabled", true);
});

var category_id=$("#category_id").val();
var sub_category_id=$("#sub_category_id").val();
var sub_category_name=$("#sub_category_name").val();

 function backtocategory() {
            var configuration = global_host_address + "channelselection.jsp" + "?id=mindbodyid" + "&mediatype=social" + "&category_id=" +category_id+ "&sub_category_id="+sub_category_id+"&sub_category_name="+sub_category_name;
            window.open(configuration, "_self");
        }
 function selcheckbox(id){
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                $("#"+id).html(content);
            }
            else
            {
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
                alert(id+"1");
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
        }
 $( window ).load(function() {
                $("#isFacebook").val("false");
                $("#isTwitter").val("false");
                $('#facebook').prop('checked', false); 
                $('#twitter').prop('checked', false); 
            });
           function imgchng(){
               document.getElementById("fb").src="images/fb_icon.png";
               
           }
    