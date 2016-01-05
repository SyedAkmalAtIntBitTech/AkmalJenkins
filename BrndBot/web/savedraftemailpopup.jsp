<%-- 
    Document   : savedraftemailpopup
    Created on : Jan 5, 2016, 6:00:32 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="favicon.png"></link>
    <script src="js/popup.js"></script>
</head>    

<body >
<div class="content">
    <div id="fade" class="black_overlay"></div>
    <!--MainContent-->
    <div class="detail-overlay-content" id="emaildraftsection">
        <!--Top Nav Bar-->
        <div class="top-nav-container-detail">
            <div class=" top-navbar-detail">
                <a class=" exit-button-detail link svg close" href="">
                    <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button" style="cursor:pointer;"> </img>
                </a>
                <div  class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Saved Draft</span></div>
                    <div class="top-nav-cta-container">
                        <div class="approve-button-detail md-button">Edit this Draft</div>
                        <div class="delete-button-detail md-button">Delete this Draft</div>
                    </div>    
                </div>
            </div>
        </div>
        
        <!--Below Nav-->
        <div class="below-nav-container-saved-draft-detail">
            <div class="inner-content-container-detail">
                   <div class="saved-post-header-detail">
                    <div class="h4">Saved Draft Name</div>
                    <div class="instruction-text">Last edited on November 28th at 1:03 PM</div>
                </div>
                    <div class="inner-content-detail">
                        <div class="saved-email-preview-detail emailcontent">
                             <iframe id="emailcontentiframe" class="emailcontentiframetag"></iframe>
                        </div>
                    </div>
                </div>
            </div>
          
        

        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
               <!--<div class="remove-button-detail md-button button-text-1">Remove Saved Email</div>-->
            </div>
        </div>
          </div>
</div>
</body>
</html>
