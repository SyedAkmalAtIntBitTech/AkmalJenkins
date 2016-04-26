<%-- 
    Document   : emaileditormindbodypopup
    Created on : Feb 12, 2016, 7:09:05 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="favicon.png"></link>
    <style>
        .close{width:60px !important;}
        .exit-button{margin-left: 4px !important;}
    </style>
</head>    

<div class="content" id="emaileditorexternalpopup">     
    <!--MainContent-->
    <div class="detail-overlay-content" ng-controller="MyController" id="MyController">
        <!--Top Nav Bar-->
        <div class="top-nav-container-detail">
            <div class=" top-navbar-detail topnav-exdata">
                <a class=" exit-button-detail link svg close" href="" id="mindbodyclose">
                    <img src="images/close.svg" class="exit-button" style="cursor:pointer;"></img>
                </a>
                <div  class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Please select which program to use:</span></div>
                    <div class="top-nav-cta-container">
<!--                        <div class="approve-button-detail md-button">Use Default Values</div>                       -->
                    </div>    
                </div>
            </div>
        </div>
        
        <!--Below Nav-->
        <div class="exdata-selection-overlay">
            <div class="inner-content-container-detail">
                <div class="exdata-content-detail" ng-controller="MyController" id="MyController" >
                <img id="loadingGifformindbody" src="images/YogaLoadingGif.gif"/>
                <!--SAVED POST GOES HERE-->
                <ul class="subcategory-list-container col-1of1 fleft scrollydiv">  
                    <li class="subcategory-list-element col-1of1 fleft" ng-repeat="jsonclass in datalists2.mindbody_data" ng-click="select_category_details(jsonclass.id)">
                       <div class="subcat-list col-4of10 fleft padding-right-15 ">{{jsonclass.column1}} 
                       </div>
                        <div class=" col-2of10 fleft ">
                            <div class="slat-column-font list-column-number col-1of1 sh2 fleft margin-top-12">{{jsonclass.column3}}</div>
                            <div class="list-column-description col-1of1 sh3-contact fleft">Date</div>
                        </div>
                        <div class=" col-3of10 fleft slat-attribute-container">
                            <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{jsonclass.column2}}</div>
                            <div class="list-column-description col-1of1 sh3-contact fleft">Instructor</div>
                        </div>
                        <div class="col-21f10 fleft">
                            <img src="images/Icons/nextArrow.svg" class="next-button-icon" ></img>
                        </div>                       
                    </li>
                </ul>
                </div>
            </div>
        </div>
  
        <!--CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
 
                     <div class="edit-button-detail md-button button-text-1 fleft ">Save Changes</div>
            </div>
        </div>-->
    </div>
</div>