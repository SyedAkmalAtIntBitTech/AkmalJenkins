<%-- 
    Document   : onetimetwitterpopup.jsp
    Created on : 28 Jan, 2016, 11:58:16 AM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel="shortcut icon" href="favicon.png">
    <script src="js/popup.js" type="text/javascript"></script>
    
</head>    
    <body>
        <div id="fade" class="black_overlay" ></div>
        <div id="twpopup">
            <div class="pop-up-background">
        <div class="pop-up-container pop-up-container-newaction"> 
            <div class="pop-up-title pop-up-title-h1"></div>
             <div class="pop-up-exit-container"  id="twpopupClose2">
                <a href="" class="pop-up-exit-icon">
                    <img onclick = "closeOverlay();" type="image/svg+xml" src="images/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </a>
            </div> 
            <div class="pop-up-inner">
                <div id="">
                    <p class="pop-up-title-h1 top80">"You now need to connect BrndBot to your Twitter account. Click 'get your pin' you will be given a 7 digit pin by Twitter. Copy and paste that pin here and you are all set!"</p>
                    <div id="twitterlink">wait...</div>
                    Enter the pin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="pinTextBox" name="pinTextBox" placeholder="Type Pin"><br><br><br>
                    <!-- <input id="closetwitter" type="button" class="btn btn-primary" value="cancel">-->
                </div>                 
            </div>            
        </div>
    </div>
    <div class="pop-up-cta-container pop-up-cta-container-newaction cur" id="setPin" >
        <a href="javascript:void(0)">
            <div class="pop-up-cta-button-full" >OK</div>
        </a>
    </div>
    </div>
    </body>
</html>
