<%-- 
    Document   : unsubscribeemailspopup
    Created on : 17 June, 2016, 11:12:37 AM
    Author     : ilyas
--%>
<link href="css/normalize.css" rel="stylesheet" type="text/css"/>
<link href="css/general.css" rel="stylesheet" type="text/css"/>
<div class="unsubscribeEmailsPopupDiv">
<div class="popUp_background-dark displayNone" id="unsubscribeEmailsPopup">
    <div class="modal-pane modal-pane-width-reg popUp_CenterVert">
        <div class="modal-pane-header-wrap">
            <h2 class="">Unsubscribe Emails Settings</h2>
            <a class="modal-pane-header-exit">
                <div class="popUp_close " onclick="hideUnsubscribeEmailsPopup()">
                    <img type="image/svg+xml" src="../images/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon cursor-shape">
                </div>
            </a>
        </div>
        <div class="modal-pane-content-wrap" style="overflow-y: scroll;">
            <p class="modal-content-header">Please select a csv file with email addresses to unsubscribe them.</p>
            <div class="input-wrap">
                <!--<label>Choose file </label>-->
                <div class="input">
                    <input type="file" id="fileid" onchange="angular.element(this).scope().uploadEmailListOnClick(this)">
                    <!--<input type="file" id="fileid" file-reader="fileContent" />-->
<!--                    <br><br>
                    <p><center>
                        <a class="small-button slat-button" ng-click="uploadEmailListOnClick()">Upload CSV</a></center>
        </p>-->
                </div>
            </div>
            
        </div>
        <div class="modal-cta-container" ng-click="unsubscribeNowOnClick()">
            <a class="long-button blue-button">Unsubscribe Now</a>
        </div>
    </div>
</div>
</div>