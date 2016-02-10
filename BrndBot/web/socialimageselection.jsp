<%-- 
    Document   : socialimageselection
    Created on : 21 Dec, 2015, 1:30:07 PM
    Author     : Satyajit-Roy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="css/slat.css"/>
    <link rel="stylesheet" type="text/css" href="css/popup.css"/>
    <link rel="shortcut icon" href="favicon.png"/>
    <script src="js/alert_message.js" type="text/javascript"></script>
    <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/popup.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
    <script src="js/imageeditor.js" type="text/javascript"></script>
    <script src="js/socialimageselection.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="images/favicon.png"/>            
    <title>socialimageselection</title>
</head>
<%@page import="com.controller.SqlMethods"%>
<%@include file="checksession.jsp" %>

<%!
    SqlMethods sql_methods = new SqlMethods();
    String imageName = "";
    String logoImageName = null;
    String companyName = "";
    String isFacebook = "";
    String isTwitter = "";
    String accesstoken = "";
    String ManagedPage = "";
    String media_type="";
//    String mindbodydata="";
    String twaccessTokenSend="";
    String pagenameSend="";
    String fbaccessTokenSend="";
    String fbdefaultAccessToken="";
    String imageid="";
    String selectedType="";
    String data="";

    String gallery="";
    String[] twitteracesstoken = {"", ""};
    int number;
%>
<%
    try {
        
        sql_methods.session = request.getSession();
        imageName = (String) sql_methods.session.getAttribute("image_file_name");
        user_id = (Integer) sql_methods.session.getAttribute("UID");
        logoImageName = (String) sql_methods.session.getAttribute("ImageFileName");
        companyName = (String) sql_methods.session.getAttribute("company");

        gallery = request.getParameter("gallery");
        isFacebook = request.getParameter("isFacebook");
        isTwitter = request.getParameter("isTwitter");
        media_type = request.getParameter("media_type");
//        mindbodydata = request.getParameter("mindbodydata");
        twaccessTokenSend = request.getParameter("twaccessTokenSend");
        pagenameSend = request.getParameter("pagenameSend");
        fbaccessTokenSend = request.getParameter("fbaccessTokenSend");
        fbdefaultAccessToken = request.getParameter("fbdefaultAccessToken");
        imageid = request.getParameter("image");
        selectedType = request.getParameter("selectedType");
        data = request.getParameter("data");
        
        if (isFacebook.equalsIgnoreCase("true")) {
            
            accesstoken = request.getParameter("fbaccessTokenSend");
            if(accesstoken==null)
            {
                accesstoken=(String)session.getAttribute("FacebookAccessTokenSend");
            }
            ManagedPage = request.getParameter("pagenameSend");
        }
        if (isTwitter.equalsIgnoreCase("true")) {
           
            twitteracesstoken = request.getParameter("twaccessTokenSend").split(",");
            if(twitteracesstoken== null)
            {
                twitteracesstoken=(String[])session.getAttribute("TwitterAccessTokenSend");
            }
        }
        session.setAttribute("FacebookAccessTokenSend", fbaccessTokenSend);
        session.setAttribute("TwitterAccessTokenSend", twitteracesstoken);
        sql_methods.session.setAttribute("IsFacebook", isFacebook);
        sql_methods.session.setAttribute("IsTwitter", isTwitter);
        sql_methods.session.setAttribute("MediaType", media_type);
        sql_methods.session.setAttribute("MindbodyData", mindbodydata);
        sql_methods.session.setAttribute("TwitterAccessTokenSend", twaccessTokenSend);
        sql_methods.session.setAttribute("PagenameSend", pagenameSend);
        //sql_methods.session.setAttribute("FacebookAccessTokenSend", fbaccessTokenSend);
        sql_methods.session.setAttribute("FacebookDefaultAccessToken", fbdefaultAccessToken);
    } catch (Exception e) {
        System.out.println(e.getCause());
        System.out.println(e.getMessage());
    } finally {
        sql_methods.closeConnection();
    }

%>
<body ng-app>
   <div ng-controller="controllerMarketingCampaign" id="controllerMarketingCampaign">
    <%@ include file="selectimagetype.jsp"%>
    <!--SideNav-->
    <input type="hidden" id="number" value=""/>
    <input type="hidden" id="isFacebook" value="<%=isFacebook%>"/>
    <input type="hidden" id="isTwitter" value="<%=isTwitter%>"/>
    <input type="hidden" id="imagen" value="<%=imageid%>"/>
    <input type="hidden" id="data" value="<%=data%>"/>
    <input type="hidden" id="selectedimagename" value=""/>
    <input type="hidden" id="selectedimageid" value=""/>
    <input type="hidden" id="selectedid" value="<%=mindbodydata%>" />
    <input type="hidden" id="gallery" name="gallery" value="<%=gallery%>"/>
    <input type="hidden" id="imageToPost" name="imageToPost" value="<%=imageid%>"/> 
    <input type="hidden" id="accesstoken" name="accesstoken" value="<%=accesstoken%>"/>
    <input type="hidden" id="twittweraccestoken" name="twittweraccestoken" value="<%=twitteracesstoken[0]%>"/>
    <input type="hidden" id="twitterTokenSecret" name="twitterTokenSecret" value="<%=twitteracesstoken[1]%>"/>
    <input type="hidden" id="sortLengthurl" name="sortLengthurl"/>
    <div class="content-main">
    <%@ include file="navbarv2.jsp"%>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <a class="exit-button-icon" href="selectpromotesocialmedia.jsp?id=<%=mindbodydata%>&mediatype=<%=media_type%>"> 
                <div class="exit-button-detail">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </div>
            </a>
            <div class="page-title-with-back page-title-font">Social Selection</div>
            <div class="page-cta-container">
                <a href="" class="gray-button fleft rightfive">
                    <div class=" md-button" id="editLink">  Edit Link</div>    
                </a>
                <a href="" class="gray-button fleft" id="">
                    <div class=" md-button" id="changeLink">  Change to Link Post</div>    
                </a>
                <a href="" class="gray-button fleft" id="">
                    <div class=" md-button" id="removeLink">  Remove Link Post</div>    
                </a>
            </div>
        </div>
    </div>
        <div class="social-page-background">
            <div class="sequence-page-content-container">
                <div class="col-1of2 social-preview fleft">
                    <%
                        if (isFacebook.equalsIgnoreCase("true")) {
                    %>
                    <div class="Facebook-preview">
                        <div class="Facebook-preview-header">
                            <div class="Facebook-preview-profpic"><img id="companyimage" class="companyimage" src="/BrndBot/DownloadImage?image_type=USER_LOGO&user_id=<%= user_id%>&image_name=<%= logoImageName%>"></div>
                            <div class="Facebook-preview-name-container">
                                <div class="Facebook-preview-name"><%=companyName%></div>
                            </div>
                        </div>
                        <div class="">
                            <textarea type="text" id="posttext" class="width100 noborder" placeholder="Demo content goes right here"></textarea>
                        </div>
                        <div class="Facebook-link-container">

                                <%
                                    if (imageid.equalsIgnoreCase("")){
                                %>
                                <div class="Facebook-preview-image">
                                    <div class="changeImage" onclick="fun('facebook',null,'<%=mindbodydata%>',null,null);">Upload Image</div>
                                </div>
                                <%} else{%>
                                <div class="Facebook-preview-image1">
                                     <% 
                                        if(gallery.equalsIgnoreCase("gallery")) {
                                     %>
                                    <div class="fchangeImage1" onclick="fun('facebook','<%=mindbodydata%>');">Change Image</div>
                                    <div id="facebookimg" class="feditImage">Edit Image</div>
                                    <img class="imgsize" id="facebookpreviewimage" value="/BrndBot/DownloadImage?image_type=GALLERY&amp;image_name=<%=imageid%>" src="/BrndBot/DownloadImage?image_type=GALLERY&amp;image_name=<%=imageid%>&user_id=<%=user_id%>"/>
                                    <%} else{%>
                                    <div class="fchangeImage1" onclick="fun('facebook','<%=mindbodydata%>');">Change Image</div>
                                    <div id="facebookimg" class="feditImage">Edit Image</div>
                                    <img class="imgsize" id="facebookpreviewimage" value="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&amp;image_name=<%=imageid%>" src="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&amp;image_name=<%=imageid%>"/>
                                    <%}%>
                                </div> 
                                <%}%>
                            <div class="Facebook-preview-link-container">
                                <div class="Facebook-preview-link-title">
                                <textarea type="text" id="link_title" class="width100 noborder noresize" placeholder="Input in Admin-- This Weekend Workshop"></textarea></div>
                                <div class="Facebook-preview-link-description">
                                <textarea type="text" id="link_description" class="width100 noborder noresize" placeholder="This workshop is going to be so awesoem for the new season and get you in really good shape!"></textarea></div>
                                <div class="Facebook-preview-link-url">
                                <input type="text" readonly id="Linkurl" class="full99 noborder" placeholder="This should equal the marketing program link"></input></div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                         else
                      {
                    %>  
                    <div class="Blank_Facebook-preview">
                        <div class="Blank_Facebook">
                            <p class="textalgn">
                                You did not select facebook
                            </p>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="col-1of2 social-preview fleft">
                    <%
                         if (isTwitter.equalsIgnoreCase("true")) {
                    %>
                    <div class="Twitter-preview">
                        <div class="Twitter-left-content fleft">
                            <div class="Twitter-preview-header fleft">
                                <div class="Twitter-preview-profpic"><img id="companyimage" class="companyimage" src="/BrndBot/DownloadImage?image_type=USER_LOGO&user_id=<%= user_id%>&image_name=<%= logoImageName%>"></div>
                            </div>
                        </div>
                        <div class="Twitter-inner fleft">
                            
                            <div class="col-1of1 Twitter-preview-name-container fleft">
                                <div class="Twitter-preview-name fleft"><span><%=companyName%></span></div>
                                <div class="Twitter-handle fleft">@<%=companyName%></div>
                                <textarea id="twittertext" maxlength="140" style="resize: none;  margin-bottom: 5px;" class="noborder" placeholder="Demo content goes right here for twitter"></textarea>
                                <div id="charlimit" class="fright"><span id="chars">140</span> characters remaining</div>
                            </div>

                                <%if 
                                    (imageid.equalsIgnoreCase("")){
                                %>
                                <div class="Twitter-preview-image fleft">
                                    <div class="changeImage" onclick="fun('twitter',null,'<%=mindbodydata%>',null,null);"> Upload Image </div>
                                </div>
                                <%
                                    } else{
                                %>
                                <div class="Twitter-preview-image1 fleft">
                                    <% if(gallery.equalsIgnoreCase("gallery")) {%>
                                    <div class="changeImage1" onclick="fun('twitter','<%=mindbodydata%>');"> Change Image </div>
                                    <div id="twitterimg" class="feditImage" > Edit Image </div>
                                    <img class="imgsize img-responsive" id="twitterpreviewimage" value="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&amp;image_name=<%=imageid%>" src="/BrndBot/DownloadImage?image_type=GALLERY&amp;image_name=<%=imageid%>&user_id=<%=user_id%>">
                                    </img>
                                    <%} else{%>
                                    <div class="changeImage1" onclick="fun('twitter','<%=mindbodydata%>');"> Change Image </div>
                                    <div id="twitterimg" class="teditImage"> Edit Image </div>
                                    <img class="imgsize .img-responsive" id="twitterpreviewimage" value="/BrndBot/DownloadImage?image_type=GALLERY&amp;image_name=<%=imageid%>" src="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&amp;image_name=<%=imageid%>">
                                    </img>
                                    <%}%>
                                </div>
                                <%}%>
                            <input type="text" hidden id="link" class="noborder top8" placeholder="This should equal the marketing program link"></input></div>
                        </div>   
                    </div>
                    <%
                       }
                       else
                       {
                    %>      
                    <div class="Blank_Twitter-preview">
                        <div class="Twitter_Facebook">
                            <p class="textalgn">
                                You did not select twitter
                            </p>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
               <div class="bottom-continue-button button-text-1" id="postorschedule">Post to Social Media</div>
            </div>
        </div>
<!--         </div>
</div>
</div>-->
  
    </body>
</html>