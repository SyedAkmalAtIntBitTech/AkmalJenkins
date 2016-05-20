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
    <body class="body-normal" ng-app="imageGallery"  ng-controller="displayImageFromGallery">
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
                    <div class="topNav--TitleBar--CTABox fright pushRight_30" ng-click="changeTwitterPostType()">
                        <div class="CTA_Button Button--Gray" id="postType">Change To Link Post</div>
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
                            <span>
                                <div class="input_Label">What would you like to share?</div>
                                <textarea class="input_Field_lg removeDrag" placeholder="Type post here" id="twitterShareText" maxlength="140"></textarea>
                            </span>
                            <span class="displayNone" id='urlDropDownSpan'>
                                <div class="input_Label pushUp_20">Choose from your Existing Links:</div>
                                <select id='dropdownurl' class="select_Field_lg removeDrag" ng-init="getUrls()" ng-change="getSelectedUrl()">
                                    <option value="0" class="caret">LINK URL<lable></lable></option>
                                    <option ng-repeat="url in urls" value="{{url.url}}--{{url.link_name}}">{{url.prigram_name}} - {{url.link_name}} - {{url.url}}</option>
                                </select>
                            </span>
                            <span class="displayNone" id="twitterLinkPost">
                                <div class="input_Label pushUp_20">Write Custom Link</div>
                                <textarea class="socialLink_attached removeDrag removeBorder" placeholder="http://www.brndbot.com" id="linkUrl"></textarea>
                            </span>
                        </div>  
                    </div>
                    <div class="Social-Add-Image-Wrap clear" id="addImageToTwitterPost">
                        <span class="clickable-area">
                            <img type="image/svg+xml" src="../static_resources/user/images/add-Image.svg" class="add-image-icon fleft" style="cursor:pointer;">
                            <div class="Add-Image-Button fleft">
                                Add Image to Post
                            </div>
                        </span>
                    </div>
                    <div class="Social-Add-Image-Wrap clear displayNone" id="addImageDivToTwitterPost">
                        <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" id="selectedImage" class="imageAdded pushUp_10 fleft">
                        <div class="col-1of1 clear">
                            <div class="edit-Image-Button fleft" id="changeImage">
                                Change Image
                            </div>
                            <!--                            <div class="edit-Image-Button fleft">
                                                            Edit Image
                                                        </div>-->
                        </div>
                    </div>
                </div>   
            </div>
        </div>
        <div class="bottomNav">
            <div class="bottom-ContinueButton fright" onclick="postTwitter()">CONTINUE</div>
        </div>
        <%@include file="sociallistselection.jsp" %> 
        <%@include file="imagegallerypopup.jsp" %>
        <%@include file="twitterpostcuccesspopup.jsp" %> 
    </body>
</html>
