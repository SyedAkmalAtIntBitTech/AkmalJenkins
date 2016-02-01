
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-7.css">
    <link rel="stylesheet" type="text/css" href="css/normalize5.css">
    <link rel="shortcut icon" href="favicon.png">
    <title>BrndBot - Account Settings</title>
    <meta charset="UTF-8" >
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <%@ include file="fonttypekit.jsp"%>
   <%@ include file="checksession.jsp" %>

   <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
   <script type="text/javascript" src="js/angular.min.js"></script>  
   <script src="js/configurations.js" type="text/javascript"></script>

   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
   <link rel="stylesheet" href="/resources/demos/style.css">
   <link href="css/colpick.css" rel="stylesheet" type="text/css">
   <link href="css/popup.css" rel="stylesheet" type="text/css">
   <script src="js/popup.js" type="text/javascript"></script>
   <script src="js/colpick.js" type="text/javascript"></script>
   <link rel="stylesheet" href="css/main1.css">
   <script src="js/prettify.js"></script>
   <script src="js/jquery.bsFormAlerts.js"></script>
   <script type="text/javascript" src="jscolor/jscolor.js"></script>
   <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
   <script src="js/tabcontent.js" type="text/javascript"></script>
   <script src="js/settings.js" type="text/javascript"></script>
   <link href="tabs/tabcontent.css" rel="stylesheet" type="text/css"/>
   <script src="js/settingspalettechooser.js" type="text/javascript"></script>
     
   <jsp:include page="basejsp.jsp" />
</head>    

<body ng-app>    
    <div class="content-main" ng-controller="controllerUserChanges">
        <!--SideNav-->
        <%@include file="navbarv2.jsp" %>  
        <!--Top Nav-->   
        <div class="top-nav">
            <div class="page-title-bar col-1of1"> 
                <div class="page-title-regular page-title-font">User Settings</div>
                <!--<div class="page-cta-container">
                     <a href="" class=" fleft">
                        <div class=" add-button md-button"> Create New Marketing Program</div>    
                    </a>
                </div>-->
            </div>
            <div class="page-subnav-bar-regular"> 
                <div class="top-subnav-tabs-container">
                    <ul class="top-subnav-nav-elements">
                        <li class="top-subnav-link-active" id="accountsettingtab"> <a class="h3-active-subnav">Account Settings</a></li>
                        <li class="top-subnav-links" id="brndsettingtab"> <a class="h3">Brand Settings</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!--Main Content GENERIC--> 
        <div class="sequence-page-background">
            <div class="sequence-page-content-container" id="accountsettingdiv">            
                <!--Inner Content Conatiner GENERIC-->
                <div class="page-inner-content-container ">
                    <div class="fleft content">
                        <!--List Starts Here-->
                       <div class="input-field-container col-1of1 fleft">
                            <div class="input-header"> Change Password </div>
                            <input type="text" placeholder="Enter New Password" id="inputpassword" class="input-field-textfield5 width33"></input>
                            <input type="text" placeholder="Enter Confirm Password" id="inputreenter" class="input-field-textfield5 width33"></input>
                        </div>
                    </div>
                </div>                    
                <div class="" id="savePassword">
                    <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="changePassword()">Save Password</div>
                </div>
            </div>
            <div class="sequence-page-content-container" id="brandsettingdiv">
                <div class="page-inner-content-container ">
                    <div class="fleft content">
                        <!--List Starts Here-->
                        <div class="h4 pushUp-60">
                            Logo
                        </div>
                        <div class="pushUp">
                            <div class="col-2of10 fleft ">
                                <div class="logo-container"></div>
                            </div>
                            <div class="col-1of2 fleft">
                                <div class="pushUp-60 edit-button-detail md-button"> Change Logo </div>
                            </div>
                        </div>
                         <div class="h4 pushUp-60 fleft col-1of1">
                            Palette
                        </div>

                        <div class="h4 pushUp-60 fleft col-1of1">
                            Use existing palette changer
                        </div>

                    </div>
                </div>            
            </div>
        </div>       
            
        <!-- BottomNav -->
    </div>
</body>
</html>