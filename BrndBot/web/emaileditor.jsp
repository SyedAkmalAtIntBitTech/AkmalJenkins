
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Email Editor</title>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-6.css"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="css/slat.css"/>
    <link rel="shortcut icon" href="images/favicon.png"/>   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/> 
    <link href="css/emaileditornew.css" rel="stylesheet" type="text/css"/>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <script src="js/configurations.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/dashboard.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="css/froala_editor.css" rel="stylesheet" type="text/css"/>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <link href="css/froala_style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/plugins/code_view.css">
    <link rel="stylesheet" href="css/plugins/colors.css">
    <link rel="stylesheet" href="css/plugins/emoticons.css">
    <link rel="stylesheet" href="css/plugins/image_manager.css">
    <link rel="stylesheet" href="css/plugins/image.css">
    <link rel="stylesheet" href="css/plugins/line_breaker.css">
    <link rel="stylesheet" href="css/plugins/table.css">
    <link rel="stylesheet" href="css/plugins/char_counter.css">
    <link rel="stylesheet" href="css/plugins/video.css">
    <link rel="stylesheet" href="css/plugins/fullscreen.css">
    <link rel="stylesheet" href="css/plugins/file.css">
    <link rel="shortcut icon" href="images/favicon.png"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css"> 
    <link rel="stylesheet" href="css/plugins/image_manager.css">
    <script src="js/froala_editor.min_editor.js" type="text/javascript"></script>
    <!--        <script src="js/plugins/code_view.min.js" type="text/javascript"></script>-->
    <script type="text/javascript" src="js/emaileditor_new.js"></script>
    <script type="text/javascript" src="js/plugins/align.min.js"></script>
    <script type="text/javascript" src="js/plugins/colors.min_editor.js" ></script>
    <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
    <script type="text/javascript" src="js/plugins/font_family.min.js"></script>
    <script src="js/plugins/image.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/plugins/image.min_editor.js"></script>
    <script type="text/javascript" src="js/emaileditor_new.js"></script>
    <script type="text/javascript" src="js/plugins/file.min.js"></script>
    <script type="text/javascript" src="js/plugins/image_manager.min_editor.js"></script>
    <script type="text/javascript" src="js/plugins/table.min_editor.js"></script>
    <script type="text/javascript" src="js/plugins/url.min.js"></script>
    <script type="text/javascript" src="js/plugins/entities.min.js"></script>
    <script type="text/javascript" src="js/plugins/inline_style.min.js"></script>
    <script type="text/javascript" src="js/plugins/save.min.js"></script>
    <script type="text/javascript" src="js/plugins/quote.min.js"></script>
    <script type="text/javascript" src="js/plugins/link.min.js"></script>
    <script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
    <script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
    
    <%!            
        SqlMethods sql_methods = new SqlMethods();
        StringBuffer string_buffer = new StringBuffer();
        String mindbody_data_id = "";
        String logoImageName = null;
        String draft_id = "0";
        String email_subject = "";
    %>
    <% email_subject = request.getParameter("subject"); %>
    <%
        try {
            sql_methods.session = request.getSession(true);
            draft_id = "0";
            user_id = (Integer) sql_methods.session.getAttribute("UID");
            logoImageName = (String) sql_methods.session.getAttribute("ImageFileName");
            if (!request.getParameter("id").equals("null")) {
                mindbody_data_id = (String) request.getParameter("id");
            } else {
                mindbody_data_id = "";
            }
            if (!request.getParameter("draftid").equals("null")) {
                draft_id = (String) request.getParameter("draftid");
                out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

    %>
</head>    
<body ng-app="myapp">
    <input type="hidden" id='userid' value="<%= user_id%>"/>
    <input type="hidden" id='draftid' value="<%= draft_id%>"/>
    <input type="hidden" value="<%=email_subject%>" id="email_subject"/>
    <!--SideNav-->
    <div class="content-main" ng-controller="MyController">        
    <!--Top Nav-->   
    <div class="top-nav-full">
        <div class="page-title-bar col-1of1"> 
           <div class="exit-button-detail">
                <a class=" exit-button-icon" href="/Newest_Files/EmailHub_EmailLists_clean.html">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                </a>
            </div>
            <div class="page-title-with-back page-title-font">Email Editor</div>
            <div class="page-cta-container">
                <a href="" class="gray-button fleft pushright">
                    <div class=" md-button" onclick="show('iphone');">  Mobile Preview</div>    
                </a>
                <a href="" class="gray-button fleft ">
                    <div class=" md-button" id="saveToDraft">  Save as Draft</div>    
                </a>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="emailEditor-page-background fleft">
            <div class="emailEditor-leftCol fleft">
                 <script>
                    $("#addblkbtn").click(function (){
                    $("#tabs-4").css("display", "block");
                            $("#clsbtn").css("display", "none");
                    });
                            $("#boxclose").click(function (){
                    $("#blocktab").click();
                            $("#tabs-4").hide();
                    });

                    $("#styletab").click(function(){
                    $("#addblkbtn").prop('disabled', true);
                            $("#stylelist").css("display", "block");
                            $("#blklist").css("display", "none");
                            $("#styletab").css("background-color", "#ffffff").css("color", "#19587c");
                            $("#blocktab").css("background-color", "transparent").css("color", "#19587c");
                    });
                            $("#blocktab").click(function(){
                    $("#stylelist").css("display", "none");
                            $("#blklist").css("display", "block");
                            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
                            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
                    });        
                            $.FroalaEditor.DEFAULTS.htmlAllowedAttrs = $.merge($.FroalaEditor.DEFAULTS.htmlAllowedAttrs, ['onclick']); 
                            
                            $(function () {
                            var urlList11;
                                    $.ajax({
                                    url:'getAllUserMarketingProgramsBySessionUserId.do',
                                            method:'Get',
                                            dataType: 'json',
                                            contentType: 'application/json',
                                            mimeType: 'application/json',
                                            success: function (responseText) {
                                            urlList11 = responseText
                                                    $('#edit').froalaEditor({key: FroalaLicenseKey, linkList: urlList11});
                                            }
                                    });
                            });        
                            
                            function show(id) {
                            var getId = id;
                                    var dynamicStyle, dynamicWidth, dynamicHeight;
                                    var imageUrl = "images/Phone.svg";
                                    var id = '#dialog';
                                    //Get the screen height and width
                                    var maskHeight = $(document).height();
                                    var maskWidth = $(window).width();
                                    //Set heigth and width to mask to fill up the whole screen
                                    $('#mask').css({'width':maskWidth, 'height':maskHeight});
                                    //transition effect
                                    $('#mask').fadeIn(500);
                                    $('#mask').fadeTo("slow", 0.95);
                                    //Get the window height and width
                                    var winH = $(window).height();
                                    var winW = $(window).width();
                                    //Set the popup window to center
                                    $(id).css('top', winH / 2 - $(id).height() / 2);
                                    $(id).css('left', winW / 2 - $(id).width() / 2);
                                    //transition effect
                                    $(id).fadeIn(2000);
                                    //if close button is clicked
                                    $('.window .close').click(function (e) {
                            //Cancel the link behavior
                            e.preventDefault();
                                    $('#mask').hide();
                                    $('.window').hide();
                            });
                                    //if mask is clicked
                                    $('#mask').click(function () {
                            $(this).hide();
                                    $('.window').hide();
                            });
                                    $.ajax({
                                    url: getHost() + "PreviewServlet",
                                            method: "post",
                                            data: {
                                            htmlString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                    iframeName: rendomIframeFilename
                                            },
                                            success: function (responseText) {
//                                        alert(responseText);
                                            if (getId === "iphone"){
                                            $('.window').css("top", "110px");
                                                    dynamicWidth = "275";
                                                    dynamicHeight = "459";
                                                    $(".window").empty();
                                                    $(".window").append("<div id=imageDivPopup style='width:" + dynamicWidth + "px;height:" + dynamicHeight + "px;'></div>");
                                                    $("#imageDivPopup").css("background-image", "url(" + imageUrl + ")").css("background-size", "100% 100%");
//                                        $("#imageDivPopup").append("<div style='width:"+(dynamicWidth-20)+"px;height:"+(dynamicHeight-60)+"px;margin-left:10px;position:relative;top:28px;overflow:scroll;'>"+responseText+"</div>");
                                                    $("#imageDivPopup").append("<iframe style='width:640px;height:1024px;position:relative;top:-295px;left:-183px;-webkit-transform: scale(0.3495);background-color:#FFF;' src='/BrndBot/DownloadHtmlServlet?file_name=" + rendomIframeFilename + ".html'></iframe>");
                                            }
                                            }
                                    });
                            }
                    
                            function hlt(){
                            var $li = $('#blklistid li').click(function() {
                            $li.removeClass('border-highlight');
                                    $(this).addClass('border-highlight');
                            });
                            };
                $(document).ready(function(){
                                
                    $("#saveToDraft").click(function (){
                    $.ajax({
                    url: getHost() + "PreviewServlet",
                            method: "post",
                            data:{
                            htmlString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                    iframeName: rendomIframeFilename
                            },
                            success: function (responseText) {
                            $("#previewcontent").empty();
                                    $("#previewcontent").append(responseText);
                                    if (draft_id == "0"){
                            $.ajax({
                            url: getHost() + "saveEmailDrafts.do",
                                    method: "post",
                                    data:{
                                    bodyString : $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                    },
                                    success: function (responseText) {
                                    if (responseText != "0"){
                                    alert("Draft saved successfully.");
                                            document.location.href = "dashboard.jsp";
                                    } else {
                                    alert("There was a problem while saving the draft! Please try again later.");
                                    }
                                    }

                            });
                            } else {
                            $.ajax({
                            url: getHost() + "updateEmailDraft.do",
                                    method: "post",
                                    data:{
                                    draftid: draft_id,
                                            bodyString:$('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                    },
                                    success: function (responseText) {
                                    if (responseText == "true"){
                                    alert("Draft updated successfully.");
                                            document.location.href = "dashboard.jsp";
                                    } else {
                                    alert("There was a problem while saving the draft! Please try again later.");
                                    }
                                    }

                            });
                            }
                            }
                    });
                    });
                    $("#saveButton").click(function (){
                        var email_subject = $("#email_subject").val();
                        $.ajax({
                                    url: getHost() + "PreviewServlet",
                                    method: "post",
                                    data:{
                                    htmlString: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                            iframeName: rendomIframeFilename
                                    },
                                    success: function (responseText) {
                                    $("#previewcontent").empty();
                                            $("#previewcontent").append(responseText);
                                            $.ajax({
                                                    url: getHost() + "SaveKeyValueSessionServlet",
                                                    method: "post",
                                                    data:{
                                                            process:"save",
                                                            sessionKey:"htmldata",
                                                            sessionValue: $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                            sessionIframeKey:"iframeName",
                                                            sessionIframevalue:"" + rendomIframeFilename
                                                    },
                                                    success: function (responseText) {
                                                    // added by Syed Ilyas 16 dec 2015 - saves draft
                                                    if (draft_id == "0"){
                                                    $.ajax({
                                                    url: getHost() + "saveEmailDrafts.do",
                                                            method: "post",
                                                            data:{
                                                            bodyString : $('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                            },
                                                            success: function (responseText) {
                                                            if (responseText != "0"){
                                                            document.location.href = "emaillistselection.jsp?draftid=" + responseText + "&subject=" + email_subject;
                                                            } else {
                                                            alert("There was a problem while saving the draft! Please try again later.");
                                                            }
                                                            }

                                                    });
                                                    } else {
                                                    $.ajax({
                                                    url: getHost() + "updateEmailDraft.do",
                                                            method: "post",
                                                            data:{
                                                            draftid: draft_id,
                                                                    bodyString:$('#edit').froalaEditor('html.get'), //$(".fr-element").html(),
                                                            },
                                                            success: function (responseText) {
                                                            if (responseText == "true"){
                                                            document.location.href = "emaillistselection.jsp?draftid=" + draft_id + "&subject=" + email_subject;
                                                            } else {
                                                            alert("There was a problem while saving the draft! Please try again later.");
                                                            }
                                                            }
                                                    });
                                                    }
                                                    }
                                            });
                                    }
                            });
                    });
                });
        </script>   
                <div id="editor">
                    <div id='edit' style="margin-top: 5px;">
                    </div>
                </div>
            </div>
            <div class="emailEditor-rightCol fright">
                <div class="emailSideBar-Header">
                    <div class="col-1of2 fleft">
                        <div class="emailSideBar-tab-active">
                         Add Block
                        </div>
                    </div>
                    <div class="col-1of2 fleft">
                        <div class="emailSideBar-tab">
                         Change Style
                        </div>
                    </div>
                </div>
                <div class="email-Block-Selection">
                    <div class="email-Block-Header">Select a block to add:</div>
                    <div class="block-selection-divider"></div>
                    <ul class="block-list">
                        <li class="block-slat-active">
                            <div class="block-name">Header Block</div>
                            <div class="block-button">Add Block</div>                            
                        </li>
                        <li class="block-slat">
                            <div class="block-name">Header Block</div>                            
                        </li>
                        <li class="block-slat">
                            <div class="block-name">Header Block</div>
                        </li>
                        <li class="block-slat">
                            <div class="block-name">Header Block</div>
                        </li>
                        <li class="block-slat">
                            <div class="block-name">Header Block</div>
                        </li>
                        <li class="block-slat">
                            <div class="block-name">Header Block</div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
               <div class="bottom-continue-button button-text-1" id="saveButton">Continue</div>
            </div>
        </div>
         </div>
</div>
    </body>
</html>