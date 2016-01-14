$(document).ready(function(){
    var count=0;
    $("#mousef").click(function(){
       $("#loadingGif").show();
       var facebookcheck = document.getElementById("facebook").checked;
       if(facebookcheck === false){
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

//                        var fb_details = responseText.split(",");

                        $("#fbaccessTokenSend").val(fb_details[0]);
                        $("#pagenameSend").val(fb_details[2]);
                        $("#fbdefaultAccessToken").val("true");
                        $("#isFacebook").val("true");

//                        $("#submitbutton").prop("disabled", false);
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
   });
    $("#mouset").click(function(){
       var twittercheck = document.getElementById("twitter").checked;
       if(twittercheck == false){
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
                                $("#twitterlink").html("<a href='" + responseText + "' target='_blank'>get your pin</a>");
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
                                            }
                                        });

//                                        $("#submitbutton").prop("disabled", false);
                                    }
                                });

                                //$("#twitterpopup").hide();
                                $(".close-reveal-modal").click();

                            } else {
                                alert("Please enter the pin code!");
                                $("#pinTextBox").focus();
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
                alert(id);
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
                $("#submitbutton").click(function(){
                    //alert($("#isFacebook").val());
                    //alert($("#isTwitter").val());
                    
                });
                $("#loadingGif").hide();
                // $('#myModal').trigger('reveal:open');
                
//                $("#abc").click(function () {
//                    alert("close already!");
//                   
//                    $('#myModal').foundation('reveal', 'close');
//                });
                var myVar1 = '<%= code %>';    /* retrieve json from request attribute */
                var mytest = eval('(' + myVar1 + ')');
                //alert(JSON.stringify(myVar1));      // display complete json

                var removecote = myVar1.replace("[", '').replace(/"/g, '').replace(']', '');
                var pages = removecote.split(",");
                //       alert(pages.length);

                if (myVar1 === "null") {
                    //$("#popup").hide();
                    $(".close-reveal-modal").click();
                }
                else {
                    $(".clickthis").click();
                    if(pages.length==1)
                    {
                        $("#fbmanagepages").append("<tr><td><strong>Please create atleast one <a href='https://www.facebook.com/help/104002523024878' target='_blank'>facebook page</a></strong></td></tr>");
                    }
                    else
                    {
                    for (var i = 0; i < pages.length; i = i + 3) {
                        $("#fbmanagepages").append("<tr  id=page#" + i + "><td>" + pages[i] + "</td><td><input type=hidden id=access" + i + " value=" + pages[i + 1] + "></td><td><img src=" + pages[i + 2] + "></td></tr>");
                    }

                    $("#content").append(" <br><center><input id=isdefault name=isdefault type=checkbox class=btn btn-primary value=default>Default</input></center>");
                    }
                    
                    $("#content").append(" <br><center><input id=facebookok name=facebookok type=button class=btn btn-primary value=ok>&nbsp;&nbsp;<input id=close name=close type=button class=btn btn-primary value=cancel></center>");
                }
 
                var managed_page = "";
                var default_access_token;
                var check_default = "false";
                var check_default_managed_page;
                $("tr").click(function () {
                    var id = this.id.split("#");
                   var selected = $(this).hasClass("red-cell");
                    $("tr").removeClass("red-cell");
                    if(!selected){
                            $(this).addClass("red-cell");}
                    var page = $("#" + this.id).text();
                    var accessToken = $("#access" + id[1]).val();
                    $("#access" + id[1]).css("background-color","red");
                    $("#pagenameSend").val(page);
                    $("#fbaccessTokenSend").val(accessToken);
                    $("#fbdefaultAccessToken").val("true");
                    check_default = $("#fbdefaultAccessToken").val();
                    $("#facebook").prop("checked", true);
                    $("#isFacebook").val("true");

                });

                $("#isdefault").click(function () {
//                    managed_page = $("#isDefault").val();
//                    alert(check_default);
                    if (check_default == "true"){
                        default_access_token = $("#fbaccessTokenSend").val();
                    }
                    
//                    if (check_default == "true"){
//                        $.ajax({
//                                url: 'SetUserFacebookAccessToken',
//                                method: 'post',
//                                type:"JSON",
//                                data: {
//                                    method: "setAccessToken",
//                                    access_token:default_access_token
//                                },
//                                success: function (responseText) {
//    //                            $("#tokenHere").html(responseText);
//                                    alert("sucess");
//                                }
//                            });
//                    }
                    });

                $("#facebookok").click(function () {
//                    managed_page = $("#isDefault").val();
                    document.getElementById("fb").src="images/fbButton_darkblue_new.svg";
                    check_default_managed_page = document.getElementById("isdefault").checked;
                    if ((check_default_managed_page == true) && (check_default == "true")){
                        $.ajax({
                                url: 'ServletUserPreferencesFacebook',
                                method: 'post',
//                                type:"JSON",
                                data: {
                                   access_token_method: "setAccessToken",
                                   access_token:default_access_token
                                },
                                success: function (responseText) {
    //                            $("#tokenHere").html(responseText);
                   
                                    //$("#popup").hide(); 
                                    $(".close-reveal-modal").click();
                                     $("#submitbutton").prop("disabled",false);
                                }
                            });
                    }else if((check_default_managed_page == false) && (check_default == "true")) { 
                        //$("#popup").hide(); 
                        $(".close-reveal-modal").click();
                        $("#submitbutton").prop("disabled",false);
                    }else {
                        alert("No default page selected!");
                    } 
            });
            
            $("#close").click(function(){
                    $("#fbaccessTokenSend").val("") ;
                    $("#fbdefaultAccessToken").val("");
                    $("#isFacebook").val("false");
                    $("#facebook").prop("checked",false);
                    //$("#popup").hide();
                    $(".close-reveal-modal").click();
                    $("#twitterpopup").hide();
                    $("#submitbutton").prop("disabled",true);
            });   
           });
           
           function imgchng(){
               document.getElementById("fb").src="images/fb_icon.png";
               
           }
    