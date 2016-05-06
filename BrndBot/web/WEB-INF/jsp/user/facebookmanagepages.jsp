<%-- 
    Document   : facebookmanagepages
    Created on : 3 May, 2016, 4:29:01 PM
    Author     : sandeep
--%>
<div class="popUp_background-dark displayNone" id="fbmanagePagePopUp">
            <div class="popUp_CenterVert ">
                <div class="popUp-top-wide clear">
                    <div class="popUp_header clear">
                        <div class="pane_title fleft h2 col1of2">Please Select an Image</div>
                        <div class="popUp_close fright" onclick="hideFbPopup()">
                            <img type="image/svg+xml" src="../images/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon cursor-shape">
                        </div>
                    </div>
                    <div class="popUp_subheader clear">
                        <span>
                            <input type="checkbox" name="defaultManagePage" id="setDefaultManagePage"> Set Selected page as default<br>
                        </span>
                    </div>
                    <div class=" gallery-padding clear gallery-pane">
                        <span ng-repeat="fbDetails in fbPagesDetails">
                            <div class="gallery-item-wrap-selected fleft" ng-click="setPageAccessToken(fbDetails.pageAccessToken,fbDetails.profileName)">
                                <div class="gallery-image-wrap">
                                    <img src="{{fbDetails.profilPicture}}" alt="alt_text" border="0" align="center" class="gallery-image" style="width: 218px;">
                                </div>
                                <div class="gallery-name"> {{fbDetails.profileName}}</div>
                                <div class="gallery-settings"></div>
                            </div>
                        </span>
                    </div>
                </div>
                <div class="popUp-bottom-wide">
                    <span class="clickable-area" ng-click="postToSelectedPage()">
                        <div class="popUp-bottom-link">Done</div>
                    </span>
                </div>
            </div>
        </div>
