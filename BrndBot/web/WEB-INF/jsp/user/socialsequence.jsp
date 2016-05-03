<%-- 
    Document   : socialsequence
    Created on : 28 Apr, 2016, 12:21:20 PM
    Author     : sandeep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Category</title>
    </head>
    <body class="body-normal" ng-app="socialMedia" ng-controller="isDefaultFbPageSet">
        <%@include file="header.jsp" %>       
        <%@include file="navbar.jsp" %>  

        <div class="contentWrap--withSideNav noScroll">
            <div class="topNav topNav-withSubnav clear">
                <div class="topNav--BackButton fleft">
                    <a class="exit-button-icon" href="dashboard">          
                        <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">            
                    </a>
                </div>
                <div class="topNav--TitleBar--withBackButton fleft">
                    <span class="topNav--TitleBar--Title fleft h2">Create a Social Post</span>
                    <div class="topNav--TitleBar--CTABox fright">
                        <div class="CTA_Button Button--Gray">Help!</div>
                    </div>
                </div>
            </div>
            <div class="topNav--offset"></div>
            <div class="contentWrapInner">
                <div class="pane pane-onboarding">
                    <div class="pane_header clear">
                        <div class="pane_title fleft h2">Select a Social Channel</div>
                    </div>
                    <div class="pane_content">
                        <div class="cat-list"  ng-init="checkForCode()">
                            <!--<a href="facebookpost">--> 
                            <div class="cat-slat clear" onclick="" ng-click="getmanage()" >
                                    <div class="fleft col-1of10" >
                                        <img type="image/svg+xml" src="images/F_icon.svg" class="social-channel-icon" style="cursor:pointer;"> </object>
                                    </div>
                                    <div class="cat-slat-title col-8of10 fleft">Facebook</div>

                                </div>
                            <!--</a>-->
                            <!--<a href="">--> 
                            <div class="cat-slat clear" onclick="isDefaultTwitterSet()">
                                    <div class="fleft col-1of10" >
                                        <img type="image/svg+xml" src="images/Twitter_icon.svg" class="social-channel-icon" style="cursor:pointer;"> </object>
                                    </div>
                                    <div class="cat-slat-title col-8of10 fleft">Twitter</div>

                                </div>
                    <!--</a>-->
                        </div>

                    </div>
                </div> 
            </div>
        </div>
           <%@include file="twitterpopup.jsp" %>
           <%@include file="facebookmanagepages.jsp" %>
           
    </body>
</html>
