<!-- 
    Document   : selectpromotesocialmedia.jsp
    Created on : 11 jan, 2016, 12:58:23 PM
    Author     : satyajit-roy
-->
<%@page import="facebook4j.Account" %>
<%@page import="facebook4j.Facebook" %>
<%@page import="facebook4j.FacebookException" %>
<%@page import="facebook4j.FacebookFactory" %>
<%@page import="facebook4j.PostUpdate" %>
<%@page import="facebook4j.ResponseList" %>
<%@page import="facebook4j.auth.AccessToken" %>
<%@page import="facebook4j.Account" %>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/slat.css">
    <link rel="shortcut icon" href="favicon.png">
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <%@ include file="fonttypekit.jsp"%>        
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="js/popup.js" type="text/javascript"></script>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
    <!--<script src="js/foundation.min.js" type="text/javascript"></script>-->
    <script src="js/jquery.reveal.js" type="text/javascript"></script>
    <link href="css/reveal.css" rel="stylesheet" type="text/css"/>
    <!--<script src="js/socialeditor.js" type="text/javascript"></script>-->
    <title>BrndBot - Social Media</title>
    <script src="js/selectpromotesocialmedia.js" type="text/javascript"></script>
    <link href="css/selectpromotesocialmedia.css" rel="stylesheet" type="text/css"/>
    <title>BrndBot - Image Selection</title>
	<%! 
            SqlMethods sql_methods = new SqlMethods();
            Object code = "";
            String mindbody_data_id = "";
            String media_type = "";
            String ImageName="";
        %>
        <% 
        try{
                code = (Object)request.getAttribute("objkey");
                ImageName=request.getParameter("image");
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                
            }
            try {
                sql_methods.session = request.getSession();
                user_id = (Integer)sql_methods.session.getAttribute("UID");
                if (!request.getParameter("id").equals(null)){
                    mindbody_data_id = (String) request.getParameter("id");
                } 
                
                if (!request.getParameter("mediatype").equals(null)){
                     media_type = (String)request.getParameter("mediatype");
                } 
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }
        %>
        <%!
            String category_id, sub_category_name, sub_category_id;
        %>
        <%
            try {
                sql_methods.session = request.getSession(true);
                category_id = request.getParameter("category_id");
                sub_category_name = request.getParameter("sub_category_name");
                sub_category_id = request.getParameter("sub_category_id");
                if(category_id==null)
                {
                    category_id = sql_methods.session.getAttribute("category_id").toString();
                    sub_category_name = sql_methods.session.getAttribute("sub_category_name").toString();
                    sub_category_id = sql_methods.session.getAttribute("sub_category_id").toString();
                }
                else
                {
                    sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                    sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                    sql_methods.session.setAttribute("category_id", category_id);
                }

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        %>
    <%@ include file="onetimetwitterpopup.jsp"%>
    <%@ include file="onetimefacebookpopup.jsp"%>   
    <jsp:include page="basejsp.jsp" />
      <script>
            
            $(document).ready(function () {
                var myVar1 = '<%= code %>';    /* retrieve json from request attribute */
                var mytest = eval('(' + myVar1 + ')');
                //alert(JSON.stringify(myVar1));      // display complete json

                var removecote = myVar1.replace("[", '').replace(/"/g, '').replace(']', '');
                var pages = removecote.split(",");
                       //alert(pages.length);
                if (myVar1 === "null") {
                    //$("#popup").hide();
                    $("#fade").hide();
                    $("#fbpopup").hide();                    
                }
                else {
                    $("#fade").show();
                    $("#fbpopup").show();
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
                    if (check_default == "true"){
                        default_access_token = $("#fbaccessTokenSend").val();
                    }
                    
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
                                    $(".close-reveal-modal").click();
                                     $("#submitbutton").prop("disabled",false);
                                }
                            });
                    }else if((check_default_managed_page == false) && (check_default == "true")) { 
                        //$("#popup").hide(); 
                        $(".close-reveal-modal").click();
                        $("#submitbutton").prop("disabled",false);
                    }else {
                        alert("No default page selected");
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
           
        </script>
</head>    

<body class="">
    <!--SideNav-->
    <div class="content-main">
    <input type="hidden" name="category_id" id="category_id" value="<%=category_id%>"/>
    <input type="hidden" name="sub_category_id" id="sub_category_id" value="<%=sub_category_id%>"/>
    <input type="hidden" name="sub_category_name" id="sub_category_name" value="<%=sub_category_name%>"/>
    <input type="hidden" name="code" id="code" value="<%=code%>"/>
    <input type="hidden" name="code" id="ImageName" name="imageName" value="<%=ImageName%>"/>
    <div class="navigation">
        <div class="main-nav-logo">
            <a class=" bb-logo-nav" href="Dashboard.html">
                <img type="image/svg+xml" src="images/Icons/Logo_Reverse.svg" class="bb-logo" style="cursor:pointer;"> </img>
            </a>
        </div>
    </div>
    <jsp:include page="navbarv2.jsp"/>    
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
           <div class="exit-button-detail">
                <a class=" exit-button-icon" href="channelselection.jsp?category_id=<%=category_id%>&sub_category_name=<%=sub_category_name%>&sub_category_id=<%=sub_category_id%>&external_sourcenull">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" onclick="backtocategory()" class="exit-button-icon" style="cursor:pointer;"> </img>
                </a>
            </div>
            <div class="page-title-with-back page-title-font">Social Channel Selection</div>
            <!--<div class="page-cta-container">
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  End Marketing Program</div>    
                </a>
            </div>-->
        </div>

    </div>
        <!--Main Content GENERIC--> 
            <div class="sequence-page-background">
        <div class="sequence-page-content-container">
            <div class="sequence-page-header">Choose which network(s) to post on:</div>
            <div class="sequence-page-subheader">If you select more than one network you will not be able to schedule as actions.</div>
            <div class="email-list-selection col-1of1 fleft">
               <div class="col-1of1 fleft unit socialNetwork-Selection-Slat mouseunclk" id="mousef">
                    <div class="selection-container col-5p fleft"> 
                        <div class="chooseChannelSelection-icon"> 
                            <img id="fb" class="chooseList-icon" src="">
                            <input type="checkbox" id="facebook" name="social" value="Facebook" hidden="true">
                        </div>
                    </div>
                    <div class="col-4of10 fleft ">
                        <div class="h2 col-1of1 socialNetwork-Name">Facebook</div>
                      
                    </div>
                </div>
                <div class="col-1of1 fleft unit pushUp-15 socialNetwork-Selection-Slat mouseunclk" id="mouset" >
                    <div class="selection-container col-5p fleft"> 
                        <div class="chooseChannelSelection-icon">
                            <img id="twt" class="chooseList-icon" src="">
                            <input type="checkbox" id="twitter" name="social" value="Twitter" hidden="true">
                        </div>
                    </div>
                    
                    <div class="col-4of10 fleft">
                        <div class="h2 col-1of1 socialNetwork-Name">Twitter</div>
                        
                    </div>
                </div>
           
            
            <!--Inner Content Conatiner GENERIC-->
            
        </div>
        </div>
    </div>
  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
                <form action="<%=request.getContextPath()%>/socialimageselection.jsp" method="POST">
                    <input type="hidden" id="media_type" name="media_type" value = '<%=media_type%>' />
                    <input type="hidden" id="mindbodydata" name="mindbodydata" value='<%= mindbody_data_id %>'>
                    <input type="hidden" id="twaccessTokenSend" name="twaccessTokenSend" >
                    <input type="hidden" id="pagenameSend" name="pagenameSend" >
                    <input type="hidden" id="fbaccessTokenSend" name="fbaccessTokenSend">
                    <input type="hidden" id="fbdefaultAccessToken" name="fbdefaultAccessToken">
                    <input type="hidden" id="isFacebook" name="isFacebook" value="false">
                    <input type="hidden" id="isTwitter" name="isTwitter" value="false">
                    <input type="hidden" id="image" name="image" value="">
                    <input type="hidden" id="selectedType" name="selectedType" value="">
                    <input type="hidden" id="mediaType" name="mediaType" value="">
                    <input type="hidden" id="data" name="data" value=",,,,,,">
                        <input type="submit" value="Continue" id="submitbutton" class="bottom-continue-button button-text-1" disabled ></input>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="myModal" class="reveal-modal">
    <div id="content">
        <center>
            <table id="fbmanagepages">
            </table>
        </center>
    </div>   
    <a class="close-reveal-modal">&#215;</a>
</div>
<div id="myModal1" class="reveal-modal">
    
<!--            <a class="close-reveal-modal">&#215;</a>  -->
</div>    
</body>
</html>