<%-- 
    Document   : twitterpopupforsocialhub
    Created on : 9 May, 2016, 5:53:58 PM
    Author     : sandeep
--%>

<div class="popUp_background-dark displayNone" id="twitterSetPinPopUpFromSocialhub">
    <div class="popUp_CenterVert ">
        <div class="popUp-top-wide clear" style="width: 700px;height: 350px;">
            <div class="popUp_header clear">
                <div class="pane_title fleft h2 col1of2">Connect BrndBot to your Twitter</div>
                <div class="popUp_close fright" id="hidepopup">
                    <img type="image/svg+xml" src="../images/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon cursor-shape">
                </div>
            </div>
            <div class="popUp_subheader clear">
            </div>
            <div class=" gallery-padding clear gallery-pane">
                        <p>"You now need to connect BrndBot to your Twitter account. Click 'get your pin' you will be given a 7 digit pin by Twitter. Copy and paste that pin here and you are all set!"</p>
                        <div id="twitterlink">wait...</div>
                        Enter the pin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="pinTextBox" name="pinTextBox" placeholder="Type Pin"><br><br><br>             
                </div>   
            </div>
        <div class="popUp-bottom-wide" style="width: inherit;">
            <span class="clickable-area">
                <div class="popUp-bottom-link" onclick="setTwitterAccessTokenFromSocialHub()">Done</div>
            </span>
        </div>
    </div>
</div>