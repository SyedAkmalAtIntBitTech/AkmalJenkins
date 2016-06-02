<%-- 
    Document   : onboarding4logo
    Created on : Mar 31, 2016, 8:17:10 PM
    Author     : development
--%>
<div class="body-normal"> 
    <!--SideNav-->
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <a href="#">
                <div class="topNav--BackButton fleft">
                    <img  src="images/backbutton.svg" class="backButton-svg" style="cursor:pointer;">
                </div>
            </a>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 4 of 5</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner" id="uploadLogoDiv">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Upload your logo</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Upload a hi-res version of your logo. A .PNG format works the best, but you may use a .JPEG as well.</div>
                    <div class="logoUpload-wrap imageLogo">
                        <img id="imageUpload" src="{{imageSrc}}" class="imagePreview" />
                    </div>
                    <label class="fileContainer">
                        <div id="triggerFile" class="Add_Button Button--Gray logoUpload--button ">Upload Logo</div>
                        <input  onclick="imageConverter('imageFileName')" name="fileName" type="file" id="imageFileName" ng-file-select="onFileSelect($files)" > 
                    </label>
                </div>
            </div>
        </div> 
        <div class="bottomNav">
            <a href="#/choosepalette">
                <div id="uploadLogoContinueButton" class="bottom-ContinueButton fright">CONTINUE</div>
            </a>
        </div>
    </div>
