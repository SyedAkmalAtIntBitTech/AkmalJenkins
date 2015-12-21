<%-- 
    Document   : selectimagetype
    Created on : 21 Dec, 2015, 5:14:48 PM
    Author     : satyajit-roy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
      <link rel="stylesheet" type="text/css" href="style_detail_overlay-1.css">
     <link rel="stylesheet" type="text/css" href="normalize.css">
    <link rel="shortcut icon" href="favicon.png">
</head>    

    <body>
        <div id="fade" class="black_overlay"></div>
        <input type="hidden" name="selectedtype" id="selectedtype" value=""></input>
        <div id="addContact">
            <div class="pop-up-background">
                <div class="pop-up-exit-container">
                    <div class="pop-up-exit-icon" id="close"> 
                        <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                    </div>
                </div> 
                <div class="pop-up-container pop-up-container-imageSelection"> 
            <div class="pop-up-title pop-up-title-h1"> Select an Image Type</div>
            <div class="pop-up-inner-imageSelection">
                <div class="col-8of10  center">
                    <div class="col-4of10 pushright fleft">
                        <div class="image-selection-button" id="uploadimage">Upload an Image or from gallery</div>
                    </div>
                    <div class="col-4of10 fright">
                        <div class="image-selection-button" id="gotoimageeditor">Image Editor</div>
                    </div>
                </div>
            </div>
            
        </div>
            </div>
            <div class="pop-up-cta-container pop-up-cta-container-imageSelection">
        <a id="continue">
            <div class="pop-up-cta-button-full"> Continue</div>
        </a>
    </div> 
        </div>
    </body>
</html>
