<%-- 
    Document   : emailpreviewpopup
    Created on : Apr 6, 2016, 4:33:28 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Email Preview Popup</title>
    </head>
    <body class="body-normal">
        
        
<!--       <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <div class="topNav--BackButton fleft">
                <object type="image/svg+xml" data="/Final-Icons/BackButton.svg" class="backButton-svg" style="cursor:pointer;"> </object>
            </div>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Create a Twitter Post</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
                <div class="topNav--TitleBar--CTABox fright pushRight_30">
                    <div class="CTA_Button Button--Gray">Change To Link Post</div>
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
                        <div class="input_Field_lg">Type post here</div>
                    </div>  
                </div>
                <div class="Social-Add-Image-Wrap clear">
                    <a href="" class="clickable-area">
                         <object type="image/svg+xml" data="/Final-Icons/add-image.svg" class="add-image-icon fleft" style="cursor:pointer;"> </object>
                        <div class="Add-Image-Button fleft">
                            Add Image to Post
                        </div>
                    </a>
                </div>
            </div>   
        </div>
    </div>-->
<!--    <div class="bottomNav">
        <a href="">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>-->
    <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
    
    <div class="popUp_background-dark" id="emailPreviewPopUp">
        <div class="popUp_fullWidth popUp_topMargin_3"  >
          <div class="popUp popUp_CenterVert">
            <div class="popUp_header clear">
                <div class="pane_title fleft h2 col1of2">Email Preview</div>
                <a href=""><div class="popUp_close fright">
                    <object type="image/svg+xml" data="/Final-Icons/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon" style="cursor:pointer;"> </object>
                </div></a>
            </div>
            <div class="col-1of1 clear">
                <div class="fleft popUp_subheader-emailDes-col">
                    <div class="col-1of1 popUp_subheader-emailDes-header">Desktop Preview</div>
                    <div class="col-1of1 popUp_subheader-emailDes-email"></div>
                </div>
                <div class="fright popUp_subheader-emailMob-col">
                    <div class="col-1of1 popUp_subheader-emailMob-header">Mobile Preview</div>
                    <div class="col-1of1 popUp_subheader-emailMob-email"></div>
                </div>
            </div>
        </div>  
    </div>
    </div>
    
    </body>
</html>
