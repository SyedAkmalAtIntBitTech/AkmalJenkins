<%-- 
    Document   : facebookpreview
    Created on : 8 Dec, 2015, 7:32:55 PM
    Author     : Satyajit Roy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css">
    <link rel="shortcut icon" href="favicon.png">
    <link rel="stylesheet" href="css/popup.css"/>
    <script src="js/popup.js"></script>
</head>    

<body>
<div class="content">
    <div id="fade" class="black_overlay"></div>
    <div id="facebooksection">
        <div class="detail-overlay-content">
        <!--Top Nav Bar-->
        
        <div class="top-nav-container-detail">
            <div class=" top-navbar-detail">
                <a class=" exit-button-detail link svg close" href="">
                    <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button" style="cursor:pointer;"> </img>
                </a>
                <div  class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Facebook Post Action Detail</span></div>
                    <div class="top-nav-cta-container">
                        <div class="approve-button-detail md-button">Approve</div>
                        <div class="delete-button-detail md-button">Delete Action</div>
                    </div>    
                </div>
            </div>
            <div class="top-subnav-detail">
                <div class="top-subnav-tabs-detail">
                     <ul class="top-subnav-nav-elements-detail">
                        <li class="top-subnav-links-detail top-subnav-link-active-detail "> <a class="h3-subnav-subnav-active" id="facebookaction">Action Details</a></li>
                        <li class="top-subnav-links-detail "> <a class="h3-subnav" id="facebookpost">Saved Post</a></li>
                        <li class="top-subnav-links-detail  top-subnav-links-detail-last"> <a class="h3-subnav" id="facebooknote">Notes</a></li>
                    </ul>
                </div>
            </div>
        </div>
        
        <div id="facebookactionsection">
            
            <!--Below Nav-->
            
            <div class="below-nav-container-saved-post-detail">
            <div class="inner-content-container-detail">
                <div class="saved-post-header-detail">
                    <div class="h4">Write Notes about this Action</div>
                    <div class="instruction-text">Text Goes here!</div>
                </div>
                    <div class="inner-content-detail">
                        <div class="fields-note-detail">

                            <!--SAVED POST GOES HERE-->

                            <div class="input-header-actionDetail" style="">
                    Name of Workshop
                        </div>
                        <div class="input-field-textfield">
                            Enter Name of Workshop 
                        </div>
                        <div class="input-header-actionDetail" style="">
                            Date
                        </div>
                        <div class="input-field-textfield">
                            Enter Date of Workshop 
                        </div>
                        <div class="input-header-actionDetail" style="">
                            Time of Workshop
                        </div>
                        <div class="input-field-textfield">
                            Enter Time of Workshop 
                        </div>
                        <div class="input-header-actionDetail" style="">
                            Name of Workshop Instructor
                        </div>
                        <div class="input-field-textfield">
                            Enter Name of Workshop Instructor
                        </div>
                        </div>
                    </div>
                 
                            
                         
                        </div>
                    </div>
  
            <!--CTA Bar-->
            
            <div class="bottom-cta-bar">
                <div class="bottom-cta-button-container">

                         <div class="edit-button-detail md-button button-text-1 fleft ">Save Notes</div>
                </div>
            </div>
            
        </div>
        
        </div>
    </div>
</div>
</body>

