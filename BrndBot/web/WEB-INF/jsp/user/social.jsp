
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <meta name="format-detection" content="telephone=no"/>
                <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
                <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
                <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
                <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
                <link rel="shortcut icon" href="favicon.png"></link> 
                <script src="js/configurations.js" type="text/javascript"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
                <script src="js/alertmessage.js" type="text/javascript"></script>
                <script src="js/tabcontent.js" type="text/javascript"></script>
                <link href="css/tabcontent.css" rel="stylesheet" type="text/css"/>
                <script src="js/socialsettings.js" type="text/javascript"></script>
                <link href="css/popup.css" rel="stylesheet" type="text/css"/>
                <link href="css/social.css" rel="stylesheet" type="text/css"/>
                <script src="js/social.js" type="text/javascript"></script>  
        <title>Brndbot - Marketing Hub</title>  
    </head>    
    <%@ include file="twitterpopupforsocialhub.jsp"%>
    <%@include file="header.jsp" %>
    <%@include file="navbar.jsp" %>
    <body ng-app ng-controller="controllerSocial">
        <div  id="controllerSocial"> 
            <!--SideNav-->


            <!--Top Nav-->   
            <div class="top-nav" ng-init="checkForCode()">
                <div class="page-title-bar col-1of1"> 
                    <!--<div class="exit-button-detail"></div>-->
                    <div class="page-title-regular page-title-font">Your Social Hub</div>
                </div>
                <div class="page-subnav-bar-with-dropdown"> 
                    <div class="subnav-dropdown pushright"  id="socialclick">
                        <span class="hub-dropdown-text">Social</span>
                        <img type="image/svg+xml" src="images/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </img>
                    </div>
                    <div class="top-subnav-tabs-container">
                        <ul class="top-subnav-nav-elements">
                            <li class="top-subnav-link-active" id="fbsetting"> <a class="h3-active-subnav" ng-click="getFacebookDetails()">Facebook Settings</a></li>
                            <li class="top-subnav-links" id="twsetting"> <a class="h3">Twitter Settings</a></li>
                        </ul>
                    </div>
                    <div class="dropdown-hub" id="socialdropdown">
                        <ul class="dropdown-inner">
                            <li class="dropdown-section col-1of1 fleft" id="socialhubli" >
                                <a href="">
                                    <div><img type="image/svg+xml" src="images/Hub-Dropdowns_social.svg" class="dropdown-hub-icon fleft col-2of10" style="cursor:pointer;"></img></div>
                                    <div class="fleft col-6of10 dropdown-label-active">Social Hub</div>
                                </a>
                            </li>
                            <a href="emaillists">
                                <li class="dropdown-section fleft col-1of1 " id="emailhubli">
                                    <div><img type="image/svg+xml" src="images/Hub-Dropdowns_email.svg" class="dropdown-hub-icon fleft col-2of10" style="cursor:pointer;"></img></div>
                                    <div class="fleft col-6of10 dropdown-label">Email Hub</div>
                                </li>
                            </a>                     
                        </ul>
                    </div>
                </div>
            </div>
            <!--Main Content GENERIC--> 
            <div class="page-background">
                <div class="page-content-container email-draft-page">
                    <!--Inner Content Conatiner GENERIC-->
                    <div class="page-inner-content-container">
                        <div class="fleft content">  
                            <div>
                                <div class="row">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div class="col-md-10 ">
                                            <div style="width:500px; margin:0px; padding:120px 0 40px;">
                                                <div class="tabcontents">
                                                    <div id="view1" style="width:550px; height:140px ">
                                                        <div class="slat-title email-list-slat-title col-1of1">Facebook</div>
                                                        <div id="fbpagename" ng-init="getFacebookDetails()">
                                                            <div class="list-column-description col-1of1 sh3 fleft">Profile Name : {{user_profile_page}}</div>
                                                            <div class="list-column-description col-1of1 sh3 fleft">Default Managed Page Name : {{fb_default_page_name}}</div>
                                                            <button id="facebook" class="buttonchange" name="change" ng-click="getManagePage()">Change</button>
                                                            <button id="fbclear" class="buttonchange" name="fbclear" ng-click="clearFacebookDetails()">Clear</button>
                                                        </div>
                                                    </div>
                                                    <div id="view2" ng-init="getTwitterDetails()" style="width:550px; height:140px;" >
                                                        <div class="slat-title email-list-slat-title col-1of1">Twitter</div>
                                                        <div id="twpagename" >
                                                            <div class="list-column-description col-1of1 sh3 fleft">Profile Name : {{twitterProfileName}}</div>
                                                            <button id="twitterLogoutButton" class="twitterLogoutButton" name="change" ng-click="clearTwitterDetails()">Logout From Twitter</button>
                                                            <button id="twitterLogInButton" class="twitterLogoutButton displayNone" onclick="getAuthURLFromSocialHub()" >Login to Twitter</button>
                                                            <!--<button id="twitterclear" class="buttonchange" name="twitterclear">Clear</button>-->
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                        
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>            
                </div>
            </div>
        </div>
        <%@include file="facebookmanagepages.jsp" %>                                  
    </body>
</html>