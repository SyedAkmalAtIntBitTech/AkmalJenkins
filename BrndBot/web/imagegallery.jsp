
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay_new.css"/>
    <link rel="stylesheet" type="text/css" href="css/normalize_new.css"/>    
    <link href="css/style_detail_overlay-1.css" rel="stylesheet" type="text/css"/>    
    <meta charset="UTF-8">
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="js/angular.min.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/alert_message.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"></link>
    <script src="js/popup.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="js/imagegallery2.js"></script>
    <script src="js/socialimageselection.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="favicon.png"/>
    <title>BrndBot - Library</title>
</head>    
    <style>#imagepopup{display: none;
    position: fixed;
    z-index: 999999;
        width:100%;}</style>

<body ng-app="rootApp"  class="claro1" ng-controller="controllerMarketingCampaign">
    <!--SideNav-->
    <div class="content-main">
    <jsp:include page="imageuploadpopup.jsp"/>
    <jsp:include page="navbarv2.jsp"/>  
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="page-title-regular page-title-font">Image Gallery</div>
            <div class="page-cta-container">
                 <a href="" class=" fleft">
                     <div id="liPriority" class=" add-button md-button" onclick="Showimguploadpopup()" > Upload an Image</div>    
                </a>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
    <div class="gallery-page-background">
        <div class="gallery-page-content-container fleft">      
        <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container fleft" ng-module="imagegallery" >
                <div class="fleft content" ng-controller="samplecontoller" ng-init="showData()">
                <!--List Starts Here-->
                    <div class="imageGallery-container  fleft"ng-repeat="images in datalists | pagination: curPage * pageSize | limitTo: pageSize">
                        <div class="imageGallery-card">
                            <div class="galCard-image col-1of1">
                                <img id="{{images.id}}" class="lookchooser5 ptr imagesize" src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}" onclick="showText('{{images.id}}','{{images.image_name}}')" />
                            </div>
                            <div class="galCard-content col-1of1"> 
<!--                                <div class="galImage-name">Image Name</div>
                                <div class="galImage-description">Added on Nov 26 2015</div>-->
                                <div class="galCard-divider"></div>
                                <!--<img type="image/svg+xml" src="images/Icons/settings.svg" class="galCard-settingsicon fleft"> </img>-->
                                <li class="nav-elements-icon-container hint--top"  data-hint="Delete">
                                    <img type="image/svg+xml" id="delete" src="images/Icons/trash.svg" class="galCard-trashicon fleft cur" ng-click="deleteImage(images.id, images.user_id, images.image_name)"/>
                                </li>                                 
                                 <!--<button name="delete"  id="delete" ng-click="deleteImage(images.id, images.user_id, images.image_name)">Delete</button>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        
<!--        <div id="light" class="white_content closepopup">
                <a href = "javascript:void(0)" style="text-decoration:none;">
                    <div id="slider-button" style="font-size:40px;text-align:center;z-index:1006;display:none;">
                        <p style="margin-top:-7px;"><img src="images/Icons/yourPlan.svg" height="25" width="25" /></p>
                    </div>
                </a>
        </div>-->
            </div>            
    
    
    
    
<!--        </div>
        </div>
    </div>

    </div>-->
</div>
</body>
</html>