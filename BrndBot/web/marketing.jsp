<%-- 
    Document   : marketingv2
    Created on : Dec 3, 2015, 7:55:37 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" href="css/version2/hint.css"/>
    <link rel="stylesheet" href="css/pikaday.css">
    <link rel="stylesheet" href="css/datepickerpikaday.css">
        <script src="js/moment.js" type="text/javascript"></script>
    <script src="js/pikaday.js"></script>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/timepicki.js" type="text/javascript"></script>
    <jsp:include page="basejsp.jsp" />
    <%@ include file="checksession.jsp" %>
    <script src="js/marketing.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="images/favicon.png"/>
    <title>BrndBot - Your Plan</title>
</head>    

<body ng-app class="claro">
    <!--SideNav-->
    <div ng-controller="controllerMarketingCampaign" id="controllerMarketingCampaign"  class="container content-main">
    <jsp:include page="AddAction.jsp"/> 
    <jsp:include page="facebookpreview.jsp"/> 
    <jsp:include page="twitterpreview.jsp"/> 
    <jsp:include page="emailpreviewpopup.jsp"/> 
    <%@include file="navbarv2.jsp" %>
    
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <!--<div class="exit-button-detail"></div>-->
            <div class="page-title-regular page-title-font">Your Plan</div>
            <div class="page-cta-container">
                <a href="" class="delete-button button fleft">
                    <div class=" md-button">Delete Action</div>    
                </a>
                <a href="javascript:void(0)">
                    <div id="liPriority" onclick="overlay();" ng-click="ShowAddAction()" class="add-action-button md-button button-text-1"> Add Action</div>    
                </a>
            </div>
        </div>
        <div class="page-subnav-bar-with-dropdown"> 
             <div class="subnav-date-dropdown hint--right" data-hint="Calendar">
                 <img type="image/svg+xml" src="images/Icons/calendar.svg" class="calendar-dropdown  " style="cursor:pointer;"   />
             </div>
             <div class="subnav-date-dropdown-text ">
                 <label class="calendar-dropdown-text " for="jumptodatepicker">Jump to Date</label>
                 <input type="text"  name="datepicker" id="jumptodatepicker" readonly class="hideinput"/>
                 <script>
//                            var picker = new Pikaday(
//                            {
//                                field: document.getElementById('jumptodatepicker'),
//                                format: 'YYYY-MM-DD',
//                                firstDay: 1,
//                                minDate: new Date('2000-01-01'),
//                                maxDate: new Date('2050-12-31'),
//                                yearRange: [2000,2050],
//                                onSelect: function() {
////                                    document.getElementById('selected').appendChild(curdat);
//                                }
//                            });
                 </script>
            </div>
             
                <div class="top-subnav-tabs-container-with-drop">
                    
                </div>
        </div>
        </div>
        <!--Main Content--> 
        <div class="page-background">
        <div class="page-content-container email-list-contact-page" ng-init="getCampaigns()">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container " >
                <div class="fleft content" ng-repeat="entity in entitySet">
                    <div class="page-content-title-bar">
                        <!--<div class="page-content-title h2" ng-show='entity.date==tomorrow_date'>Tomorrow's Actions</div>-->
                        <div class="page-content-title h2" ng-show="entity.date == today_date">Today's Actions</div>
                        <div class="page-content-title h2" ng-show="(entity.date != today_date)">{{entity.date| date: "MMM dd yyyy"}}</div>
                    </div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft" >
                        <li class="slat-container fleft selfclear" ng-repeat="entitydetails in entity.dataArray">
                            <div class="selection-container col-5p"> 
                               <div class="selection-icon" id="{{entitydetails.entity_id}}" onclick="selcheckbox(this.id)"><input type="checkbox" id="entityid{{entitydetails.entity_id}}" value="{{entitydetails.entity_id}}" name="entityname" hidden></input></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft hint--top"  data-hint="Template Saved" ng-show="entitydetails.template_status=='No Template'"> 
                                    <img src="images/Icons/templateSaved.svg" class="status-button" />
                                </div>
                                <div  class="slat-title-container col-1of2 fleft">                                    
                                    <div class="slat-title email-list-slat-title col-1of1 sh1" ng-hide="entitydetails.is_recuring">{{entitydetails.schedule_title}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3" ng-show="entitydetails.user_marketing_program_id > 0">{{entitydetails.marketingName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3" ng-show="entitydetails.user_marketing_program_id == 0">No Program</div>
                                </div>
                                
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft"ng-show="entitydetails.entity_type==master_facebook || entitydetails.entity_type==master_twitter">{{entitydetails.entity_type}}</div>
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft"ng-show="entitydetails.entity_type==master_email || entitydetails.entity_type==master_note">{{entitydetails.entity_type}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font"
                                         ng-click="getScheduleDetails(entitydetails.schedule_id, 
                                                            entitydetails.template_status,
                                                            entitydetails.schedule_time, 
                                                            entitydetails.entity_type, 
                                                            entitydetails.schedule_title, 
                                                            entitydetails.schedule_description,
                                                            entitydetails.marketingName,
                                                            entitydetails.user_marketing_program_id,
                                                            entitydetails.days,
                                                            entitydetails.is_today_active)">Details</div>
                                </div>
                            </div>
                        </li>
                         <li class="slat-container fleft selfclear" ng-show="entity.dataArray == ''">
                            <div class="selection-container col-5p"> 
                                <!--<div class="selection-icon"></div>-->
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft " ng-show="entitydetails.template_status=='No Template'"> 
                                    <object type="image/svg+xml" data="images/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">{{nodata}}</div>
                                </div>
<!--                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft"></div>
                                    <div class="list-column-description col-1of1 sh3 fleft"></div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font">Details</div>
                                </div> -->
                            </div>
                        </li>
<!--
                        <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="images/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Note</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font">Details</div>
                                </div>
                            </div>
                        </li>-->
                    </ul>
                </div>
            </div>
<!--                 <div class="page-inner-content-container fleft">
                <div class="page-content-title-bar">
                    <div class="page-content-title h2">December 25, 2015</div>
                </div>
                <div class="fleft content">
                    List Starts Here
                    <ul class="main-container fleft">
                        <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="images/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Email</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font">Details</div>
                                </div>
                            </div>
                        </li>
                        <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="images/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Email</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font">Details</div>
                                </div>
                            </div>
                        </li>
                        <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="images/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Email</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font">Details</div>
                                </div>
                            </div>
                        </li>
                        <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="images/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Email</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font">Details</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>-->
            
        </div>
        </div>
        <div id="light" class="white_content closepopup">
                <a href = "javascript:void(0)" style="text-decoration:none;">
                    <div id="slider-button" style="font-size:40px;text-align:center;z-index:1006;display:none;">
                        <p style="margin-top:-7px;"><img src="images/Icons/yourPlan.svg" height="25" width="25" /></p>
                    </div>
                </a>
            </div>
    </div>
  
    </body>
</html>