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
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/slat.css">
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel="shortcut icon" href="favicon.png">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/popup.js" type="text/javascript"></script>
</head>    
<%@page import="com.controller.SqlMethods"%>
<%@ include file="checksession.jsp" %>
<%@ include file="selectimagetype.jsp" %>

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
    String mindbodydata="";
    String twaccessTokenSend="";
    String pagenameSend="";
    String fbaccessTokenSend="";
    String fbdefaultAccessToken="";
    
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
        
        isFacebook = request.getParameter("isFacebook");
        isTwitter = request.getParameter("isTwitter");
        media_type = request.getParameter("media_type");
        mindbodydata = request.getParameter("mindbodydata");
        twaccessTokenSend = request.getParameter("twaccessTokenSend");
        pagenameSend = request.getParameter("pagenameSend");
        fbaccessTokenSend = request.getParameter("fbaccessTokenSend");
        fbdefaultAccessToken = request.getParameter("fbdefaultAccessToken");
        
        if (isFacebook.equalsIgnoreCase("true")) {
            
            accesstoken = request.getParameter("fbaccessTokenSend");
            ManagedPage = request.getParameter("pagenameSend");
        }
        if (isTwitter.equalsIgnoreCase("true")) {
           
            twitteracesstoken = request.getParameter("twaccessTokenSend").split(",");
        }
        sql_methods.session.setAttribute("IsFacebook", isFacebook);
        sql_methods.session.setAttribute("IsTwitter", isTwitter);
        sql_methods.session.setAttribute("MediaType", media_type);
        sql_methods.session.setAttribute("MindbodyData", mindbodydata);
        sql_methods.session.setAttribute("TwitterAccessTokenSend", twaccessTokenSend);
        sql_methods.session.setAttribute("PagenameSend", pagenameSend);
        sql_methods.session.setAttribute("FacebookAccessTokenSend", fbaccessTokenSend);
        sql_methods.session.setAttribute("FacebookDefaultAccessToken", fbdefaultAccessToken);
    } catch (Exception e) {
        System.out.println(e.getCause());
        System.out.println(e.getMessage());
    } finally {
        sql_methods.closeConnection();
    }

%>
<body class="">
    <!--SideNav-->
    <div class="content-main">
    <div class="navigation">
        <div class="main-nav-logo">
            <a class=" bb-logo-nav" href="dashboard.jsp">
                <img type="image/svg+xml" src="images/Icons/Logo_Reverse.svg" class="bb-logo" style="cursor:pointer;"/>
                </a>
        </div>
        <ul class="nav-tabs" style="margin-top:30px;">
            <li class="nav-elements-icon">
                <a  href="dashboard.jsp">
                <img type="image/svg+xml" src="images/Nav_Icons/NavIcon_Home_white.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="marketing.jsp">
                    <img type="image/svg+xml" src="images/Nav_Icons/PlanIconNav.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="marketingcategory.jsp">
                    <img type="image/svg+xml" src="images/Nav_Icons/Hamburger.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="emailhistory.jsp">
                    <img type="image/svg+xml" src="images/Nav_Icons/NavIcon_Email_white.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="social.jsp">
                    <img type="image/svg+xml" src="images/Nav_Icons/NavIcon_Social_white.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="imagegallery.jsp">
                    <img type="image/svg+xml" src="images/Nav_Icons/NavIcon_ImageGallery_white.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="settings.jsp">
                    <img type="image/svg+xml" src="images/Nav_Icons/NavIcon_Settings_white.svg" class="bb-logo" style="cursor:pointer;width:35px;"/>
                </a>
            </li>
            <li class="nav-elements-icon">
                <a  href="signout.jsp" style="color:white;font-size:10px;">
                    LOGOUT
                </a>
            </li>
        </ul>    
    </div>
        
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
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  Change from Link Post Type</div>    
                </a>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="social-page-background">
            <div class="sequence-page-content-container">
                <div class="col-1of2 social-preview fleft">
                    <%
                        if (isFacebook.equalsIgnoreCase("true")) {
                    %>
                    <div class="Facebook-preview">
                        <div class="Facebook-preview-header">
                            <div class="Facebook-preview-profpic"></div>
                            <div class="Facebook-preview-name-container">
                                <div class="Facebook-preview-name">BrndBot Demo</div>
                            </div>
                        </div>
                        <div class="Facebook-preview-usercontent">Demo content goes right here</div>
                        <div class="Facebook-link-container">
                            <div class="Facebook-preview-image">
 <!--                                <div class="changeImage" onclick="fun('facebook','<%=mindbodydata%>');"> Upload Image </div>-->
                               <img class="imgsize" src="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&amp;image_name=9.png"/>
                            </div> 
                            <div class="Facebook-preview-link-container">
                                <div class="Facebook-preview-link-title">Input in Admin-- This Weekend Workshop</div>
                                <div class="Facebook-preview-link-description">This workshop is going to be so awesoem for the new season and get you in really good shape!</div>
                                <div class="Facebook-preview-link-url">This should equal the marketing program link</div>
                            </div>
                        </div>
                    </div>
                    <% } 
                        else{
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
                                <div class="Twitter-preview-profpic"></div>
                            </div>
                        </div>
                        <div class="Twitter-inner fleft">
                            <div class="col-1of1 Twitter-preview-name-container fleft">
                                <div class="Twitter-preview-name fleft"><span>BrndBot Demo</span></div>
                                <div class="Twitter-handle fleft">@BrndBot</div>
                                <div class="Twitter-preview-usercontent fleft col-1of1">Demo content goes right here andklj lkjflkjsdf l;kjasdlfkja slkfjljfal;skd jflkasdjflkasjdflkasjdlkfjlkslksdjaflkjsdlkfj asdfasdfasdf asdfasd fasdfasdf s</div>
                            </div>
                            <div class="Twitter-preview-image fleft">
                                <div class="changeImage" onclick="fun('twitter','<%=mindbodydata%>')"> Upload Image </div>
                            </div>
                        </div>   
                    </div>
                    <% }
                         else{
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
               <div class="bottom-continue-button button-text-1">Post to Social Media</div>
            </div>
        </div>
         </div>
</div>
    </body>
</html>