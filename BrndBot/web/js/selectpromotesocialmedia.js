
$(document).ready(function(){
    var count=0;
    $("#mousef").click(function(){
       var facebookcheck = "true";
       var facebookcheckimg = $("#fb").attr("src");       
       if(facebookcheck === "true")
       {
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
                    } else {
//                      var fb_details = responseText.split(",");
                        $("#fbaccessTokenSend").val(fb_details[0]);
                        $("#pagenameSend").val(fb_details[2]);
                        $("#fbdefaultAccessToken").val("true");
                        $("#isFacebook").val("true");
//                      $("#submitbutton").prop("disabled", false);
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
            //$("#fb").attr("src", "images/white.png");
            //document.getElementById("fb").src="images/white.png";
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
                            alert("Twitter token successfully added to your profile")
                            $("#twpopup").hide();                            
                            $("#fade").hide();
                        }
                    });
                }
            });

        } else {
            alert("Please enter the pin code!");
            $("#pinTextBox").focus();
        }
    });
    $("#mouset").click(function(){
       var twittercheck = "true";
       var twittercheckimg = $("#twt").attr("src");       
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
                        //$("#twitterpopup").show();
                        //$(".clicktwitter").click();
                        $.ajax({
                            url: 'GetTwitterToken',
                            method: 'get',
                            success: function (responseText) {   
                                $("#twitterlink").html("<a href='" + responseText + "' target='_blank'>get your pin</a>");
                            }
                        });
                    } else {
                        $("#twaccessTokenSend").val(responseText);
//                        $("#submitbutton").prop("disabled", false);
                    }

                }
            });
            document.getElementById("twitter").checked=true;
            document.getElementById("twt").src="images/twtButton_lightblue_new.svg";
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
            $("#mouset").css("background-color","#FFFFFF");
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
        //$("#twitterpopup").hide();
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
//     changeImagef();
//     changeImaget();
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                $("#"+id).html(content);
                //alert(id);
            }
            else
            {alert(id);
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
            
            $(document).ready(function () {
//                
//                $("#isdefault").click(function () {
////                    managed_page = $("#isDefault").val();
////                    alert(check_default);
//                    if (check_default == "true"){
//                        default_access_token = $("#fbaccessTokenSend").val();
//                    }
//                    
////                    if (check_default == "true"){
////                        $.ajax({
////                                url: 'SetUserFacebookAccessToken',
////                                method: 'post',
////                                type:"JSON",
////                                data: {
////                                    method: "setAccessToken",
////                                    access_token:default_access_token
////                                },
////                                success: function (responseText) {
////    //                            $("#tokenHere").html(responseText);
////                                    alert("sucess");
////                                }
////                            });
////                    }
//                    });
//
//                $("#facebookok").click(function () {
////                    managed_page = $("#isDefault").val();
//                    document.getElementById("fb").src="images/fbButton_darkblue_new.svg";
//                    check_default_managed_page = document.getElementById("isdefault").checked;
//                    if ((check_default_managed_page == true) && (check_default == "true")){
//                        $.ajax({
//                                url: 'ServletUserPreferencesFacebook',
//                                method: 'post',
////                                type:"JSON",
//                                data: {
//                                   access_token_method: "setAccessToken",
//                                   access_token:default_access_token
//                                },
//                                success: function (responseText) {
//    //                            $("#tokenHere").html(responseText);
//                   
//                                    //$("#popup").hide(); 
//                                    $(".close-reveal-modal").click();
//                                     $("#submitbutton").prop("disabled",false);
//                                }
//                            });
//                    }else if((check_default_managed_page == false) && (check_default == "true")) { 
//                        //$("#popup").hide(); 
//                        $(".close-reveal-modal").click();
//                        $("#submitbutton").prop("disabled",false);
//                    }else {
//                        alert("No default page selected!");
//                    } 
//            });
//            
//                $("#close").click(function(){
//                    $("#fbaccessTokenSend").val("") ;
//                    $("#fbdefaultAccessToken").val("");
//                    $("#isFacebook").val("false");
//                    $("#facebook").prop("checked",false);
//                    //$("#popup").hide();
//                    $(".close-reveal-modal").click();
//                    $("#twitterpopup").hide();
//                    $("#submitbutton").prop("disabled",true);
//            });   
           });
           
           function imgchng(){
               document.getElementById("fb").src="images/fb_icon.png";
               
           }
    