<%-- 
    Document   : twitterpost
    Created on : 28 Apr, 2016, 3:49:40 PM
    Author     : sandeep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Category</title>
    </head>
    <body class="body-normal">
        <%@include file="header.jsp" %>       
        <%@include file="navbar.jsp" %>  
        <div class="contentWrap--withSideNav">
            <div class="topNav topNav-withSubnav clear">
                <div class="topNav--BackButton fleft">
                    <a class="exit-button-icon" href="dashboard">          
                        <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">            
                    </a>
                </div>
                <div class="topNav--TitleBar--withBackButton fleft">
                    <span class="topNav--TitleBar--Title fleft h2">Create a Twitter Post</span>
                    <div class="topNav--TitleBar--CTABox fright">
                        <div class="CTA_Button Button--Gray">Help!</div>
                    </div>
                    <div class="topNav--TitleBar--CTABox fright pushRight_30">
                        <a href="facebooklinkpost">
                            <div class="CTA_Button Button--Gray">Change To Link Post</div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="topNav--offset"></div>
            <div class="contentWrapInner">
                <div class="pane pane-Social-Create ">
                    <div class="pane_header clear  pane-Social-Create-padding">
                        <div class="pane_title fleft h2">Twitter Post</div>
                    </div>
                    <div class="pane_content pane-Social-Create-padding ">
                        <div class="inputSection col1of1 pushUp_20">
                            <div class="input_Label">What would you like to share?</div>
                            <textarea class="input_Field_lg removeDrag" placeholder="Type post here"></textarea>
                        </div>  
                    </div>
                    <div class="Social-Add-Image-Wrap clear">
                        <a href="SocialSequence-AddImage.html" class="clickable-area">
                            <object type="image/svg+xml" data="/Final-Icons/add-image.svg" class="add-image-icon fleft" style="cursor:pointer;"> </object>
                            <div class="Add-Image-Button fleft">
                                Add Image to Post
                            </div>
                        </a>
                    </div>
                </div>   
            </div>
        </div>
        <div class="bottomNav">
            <a href="">
                <div class="bottom-ContinueButton fright">CONTINUE</div>
            </a>
        </div>
    </body>
</html>
