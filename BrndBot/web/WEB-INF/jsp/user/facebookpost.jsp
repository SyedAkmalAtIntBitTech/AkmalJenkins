<%-- 
    Document   : socialsequence_facebook_nolink
    Created on : 28 Apr, 2016, 12:48:39 PM
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
                    <span class="topNav--TitleBar--Title fleft h2">Create a Facebook Post</span>
                    <div class="topNav--TitleBar--CTABox fright">
                        <div class="CTA_Button Button--Gray">Help!</div>
                    </div>
                    <div class="topNav--TitleBar--CTABox fright pushRight_30"  onclick="changePostType()">
                            <div class="CTA_Button Button--Gray" id="postType">Change To Link Post</div>
                    </div>
                </div>
            </div>
            <div class="topNav--offset"></div>
            <div class="contentWrapInner">
                <div class="pane pane-Social-Create ">
                    <div class="pane_header clear  pane-Social-Create-padding">
                        <div class="pane_title fleft h2">Facebook Post</div>
                    </div>
                    <div class="pane_content pane-Social-Create-padding ">
                        <div class="inputSection col1of1 pushUp_20">
                            <div class="input_Label">What would you like to share?</div>
                            <textarea class="input_Field_lg removeDrag" placeholder="Type post here"></textarea>
                            <span class="displayNone" id="linkPostFields">
                            <div class="input_Label pushUp_20" >Link Title</div>
                            <textarea class="input_Field removeDrag" placeholder="Type post here"></textarea>
                            <div class="input_Label pushUp_20">Link Description</div>
                            <textarea class="input_Field_Md removeDrag" placeholder="Type post here"></textarea>
                            <div class="input_Label pushUp_20">Link</div>
                            <textarea class="socialLink_attached removeDrag removeBorder" placeholder="http://www.brndbot.com"></textarea>
                            </span>
                        </div>  
                    </div>
                    <div class="Social-Add-Image-Wrap clear" id="addImageToPostButton">
                        <object type="image/svg+xml" data="/Final-Icons/add-image.svg" class="add-image-icon fleft" style="cursor:pointer;"> </object>
                        <div class="Add-Image-Button fleft">
                            Add Image to Post
                        </div>
                    </div>
                    <div class="Social-Add-Image-Wrap clear displayNone" id="addImageDiv">
                        <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="imageAdded pushUp_10 fleft">
                        <div class="col-1of1 clear">
                            <div class="edit-Image-Button fleft">
                                Change Image
                            </div>
                            <div class="edit-Image-Button fleft">
                                Edit Image
                            </div>
                        </div>
                    </div>
                </div>   
            </div>
        </div>
        <div class="bottomNav">
            <a href="Onboarding-Screen2.html">
                <div class="bottom-ContinueButton fright">CONTINUE</div>
            </a>
        </div>
<!--       <div class="popUp_background-dark">
       <div class="popUp_CenterVert ">
            <div class="popUp-top-wide clear">
            <div class="popUp_header clear">
                <div class="pane_title fleft h2 col1of2">Please Select an Image</div>
                <div class="popUp_close fright">
                    <object type="image/svg+xml" data="/Final-Icons/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon" style="cursor:pointer;"> </object>
                </div>
            </div>
            <div class="popUp_subheader clear">
                <a href="/Social_Sequence/SocialSequence-AddImage.html" class="popUp_subheader-tabs-active fleft">From Gallery</a>
                <a href="/Social_Sequence/SocialSequence-AddImage-upload.html" class="popUp_subheader-tabs fleft">Upload Image</a>
            </div>
            <div class=" gallery-padding clear gallery-pane">
                <div class="gallery-item-wrap-selected fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
                <div class="gallery-item-wrap fleft">
                    <div class="gallery-image-wrap">
                         <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                    </div>
                    <div class="gallery-name"> Photo 1</div>
                    <div class="gallery-settings"></div>
                </div>
            </div>
        </div>
         <div class="popUp-bottom-wide">
            <a href="/Social_Sequence/Twitter/SocialSequence-Twitter-imageAdded.html" class="clickable-area">
                <div class="popUp-bottom-link">ADD IMAGE</div>
            </a>
        </div>
    </div>
    </div>-->
    </body>
</html>
