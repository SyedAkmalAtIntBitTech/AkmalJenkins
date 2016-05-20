<%-- 
    Document   : imagegallerypopup
    Created on : 29 Apr, 2016, 6:26:26 PM
    Author     : sandeep
--%>
<link href="css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="css/general.css" rel="stylesheet" type="text/css"/>
<div class="emailFooterPopupDiv">
<div class="popUp_background-dark displayNone" id="emailFooterPopup">
    <div class="modal-pane modal-pane-width-reg popUp_CenterVert">
        <div class="modal-pane-header-wrap">
            <h2 class="">Email Footer Settings</h2>
            <a class="modal-pane-header-exit"></a>
            <div class="popUp_close fright" onclick="hideEmailFooterPopup()">
                <img type="image/svg+xml" src="../images/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon cursor-shape">
            </div>
        </div>
        <div class="modal-pane-content-wrap" style="overflow-y: scroll;">
            <p class="modal-content-header">Change the settings of the email footer. A company address must be entered. All fields that are left blank will not show up as links in the footer.</p>
            <div class="input-wrap">
                <label>Company Address </label>
                <div class="input">
                    <input id="footerAddress" value="{{footerDetails.address}}"></input>
                    <p> We need this to comply with email spam (CANSPAM) laws</p>
                </div>
            </div>
            <div class="input-wrap">
                <label> Website URL </label>
                <div class="input">
                    <input id="footerWebsiteUrl" value="{{footerDetails.websiteUrl}}"></input>
                    <p> This can be copied from the browser <a class="input-help" href="#">Click to learn how</a></p>
                </div>
            </div>
            <div class="input-wrap">
                <label> Facebook URL </label>
                <div class="input">
                    <input id="footerFacebookUrl" value="{{footerDetails.facebookUrl}}"></input>
                    <p> Log into your company's Facebook page and copy the URL<a class="input-help" href="#">Click to learn how</a></p>
                </div>
            </div>
            <div class="input-wrap">
                <label> Twitter URL </label>
                <div class="input">
                    <input id="footerTwitterUrl" value="{{footerDetails.twitterUrl}}"></input>
                    <p> Log into your company's Twitter page and copy the URL<a class="input-help" href="#">Click to learn how</a></p>
                </div>
            </div>
            <div class="input-wrap">
                <label> Instagram URL </label>
                <div class="input">
                    <input id="footerInstagramUrl" value="{{footerDetails.instagramUrl}}"></input>
                    <p> Log into your company's Instagram page and copy the URL<a class="input-help" href="#">Click to learn how</a></p>
                </div>
            </div>
        </div>
        <div class="modal-cta-container" ng-click="changeFooterDetails()">
            <a class="long-button blue-button">Save Email Footer Settings</a>
        </div>
    </div>
</div>
</div>