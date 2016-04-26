<%-- 
    Document   : savedEmailDraftPopup.jsp
    Created on : 6 Jan, 2016, 4:05:16 PM
    Author     : Satyajit Roy
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
        <div id="emaildraftpopup">
        <div class="detail-overlay-content">
        <!--Top Nav Bar-->
        <div class="top-nav-container-detail">
            <div class="top-navbar_draft">
                <a class=" exit-button-detail link svg" href="" id="closedraftpopup">
                    <img type="image/svg+xml" src="images/close.svg" class="exit-button" style="cursor:pointer;"> </img>
                </a>
                <div class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Saved Draft</span></div>
                    <div class="top-nav-cta-container">
                        <div class="approve-button-detail md-button" ng-click="editDrafts(id, categoryid, emailsubject, subcategoryid, subcategoryname)">Edit this Draft</div>
                        <div class="delete-button-detail md-button" ng-click="deletedrafts('delete',id);">Delete this Draft</div>
                    </div>    
                </div>
            </div>
        </div>
        <!--Below Nav-->
        <div class="below-nav-container-saved-draft-detail">
            <div class="inner-content-container-detail">
                   <div class="saved-post-header-detail">
                    <div class="h4">{{emailsubject}}</div>
                    <div class="instruction-text">Last edited on {{editdate|date: "MMMM dd yyyy"}} at {{editdate|date: "hh:mm a"}}</div>
                </div>
                    <div class="inner-content-detail">
                        <div class="saved-draftemail-preview-detail">
                            <!--SAVED Email GOES HERE-->
                         <div id="draftshow"></div>   
                        </div>
                    </div>
                </div>
            </div>
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            
        </div>
          </div>
        </div>
         
    
    </body>
</html>
