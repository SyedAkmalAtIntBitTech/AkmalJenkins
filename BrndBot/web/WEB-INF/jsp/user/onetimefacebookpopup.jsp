<%-- 
    Document   : onetimefacebookpopup.jsp
    Created on : 28 Jan, 2016, 1:58:48 PM
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
        <div id="fbpopup">
            <img type="image/svg+xml" src="images/Close.svg" id="closefbpopupa" class="cur fbclose" />
            <div class="pop-up-background">
                <div class="pop-up-container pop-up-container-newaction"> 
                    <div class="pop-up-title pop-up-title-h1"></div>                        
                        <div id="">
                            <center>
                                <table id="fbmanagepages">
                                </table>
                            </center>
                        </div>   
                    <input id="isdefault" name="isdefault" type="checkbox" class="btn" btn-primary="" value="default">&nbsp;&nbsp;&nbsp; Default
                </div>
            </div>
            <div class="pop-up-cta-container pop-up-cta-container-newaction cur" id="facebookok" >
                <a href="javascript:void(0)">
                    <div class="pop-up-cta-button-full" >OK</div>
                </a>
            </div>
        </div>
    </body>
</html>
