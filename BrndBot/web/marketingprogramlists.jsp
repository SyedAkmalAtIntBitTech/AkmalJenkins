<%-- 
    Document   : marketingprogramcurrentlist
    Created on : Dec 17, 2015, 4:42:23 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <title>BrndBot - Marketing Programs lists</title>
    <script src="js/configurations.js"></script>
    <script src="js/alertmessage.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/popup.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/marketinglist.js"></script>
    <jsp:include page="basejsp.jsp"/>
    <style>
        #pastprogs,#delmarkprog{display: none;}
    </style>
    
</head>    

<body ng-app class="">
    <!--SideNav-->
    <div class="content-main" ng-controller="controllerUserMarketingProgamsByStatus">
     <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav"  >
        <div class="page-title-bar col-1of1" ng-init="getUserMarketingProgramsOpen()"> 
            <div class="page-title-regular page-title-font">Your Marketing Programs</div>
            <div class="page-cta-container" >
                <a id="crtmarkprog" href="createmarketingprogram.jsp" class=" fleft">
                    <div class=" add-button md-button"> Create New Marketing Program</div>    
                </a>
                <a id="delmarkprog" href="" class=" fleft">
                    <div class="delete-button md-button"  ng-click="endMarketingProgram();">Delete Marketing Program(s)</div>    
                </a>
            </div>
        </div>
        <div class="page-subnav-bar-regular"> 
            <div class="top-subnav-tabs-container">
                <ul class="top-subnav-nav-elements">
                    <!--<li class="top-subnav-links" id="createnewprogli"><a id="createnewprog" class="h3">Create New Program</a></li>-->
                    <li class="top-subnav-link-active" id="curmarprogli"><a id="curmarprog" class="h3-active-subnav" onclick="currprograms()">Current Marketing Programs</a></li>
                    <li class="top-subnav-links" id="pstmarprogli"> <a class="h3" id="pstmarprog" onclick="pastprograms()">Past Marketing Programs</a></li>
                </ul>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
    <div class="page-background" >
        <div class="page-content-container email-list-contact-page">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content" id="currprogs" ng-init="getUserMarketingProgramsOpen()">
                    <ul class="main-container fleft" ng-show="current_programs==''" id="noprogramsavailable">
                        <li class="slat-container fleft selfclear">
                            <div class="col-1of1 slat-unit fleft " >
                                No Programs Available
                            </div>
                        </li>
                    </ul>
                    <ul  class="main-container fleft" ng-show="current_programs!=''">
                        <li class="slat-container fleft selfclear" ng-repeat="program in current_programs">
                            <div class="col-1of1 slat-unit fleft ">
                                <div class="slat-title-container col-3of10 fleft">
                                    <div class="slat-title  col-1of1 sh1-contact">{{program.program_name}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3-contact">Added on {{program.start_date | date: 'MMM dd yyyy'}}</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" id="program_end_date{{program.id}}">{{program.end_date | date: "MMM dd yyyy"}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3-contact">End Date</div>
                                    <input type="hidden" value="{{program.end_date| date: "MMM dd yyyy"}}" ></input>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" ng-hide="program.noofpostleft=='0'">{{program.noofpostleft}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft"ng-show="program.noofpostleft!='0'">Actions Left</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft" ng-show="program.noofpostleft=='0'">No Actions Left</div>
                                </div>
                                <div class="col-1of4 fleft">
                                    <div class="slat-cta-container">
                                        <a href=""> 
                                            <div class="small-button slat-button detail-button-font" ng-click="sendProgramId(program.id,'0')">View Program</div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="fleft content" id="pastprogs" ng-init="getUserMarketingProgramsClosed()">
                    <ul  class="main-container fleft" ng-show="past_programs!=''">
                        <li class="slat-container fleft selfclear" ng-repeat="program in past_programs">
                            <div class="col-1of1 slat-unit fleft " >
                                <div class="slat-title-container col-3of10 fleft">
                                    <div class="slat-title  col-1of1 sh1-contact">{{program.program_name}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3-contact">Added on {{program.start_date | date: 'MMM dd yyyy'}}</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{program.end_date | date: "MMMM dd, yyyy"}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" ng-hide="program.noofpostleft=='0'">{{program.noofpostleft}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft"ng-show="program.noofpostleft!='0'">Actions Left</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft" ng-show="program.noofpostleft=='0'">No Actions Left</div>
<!--                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{program.noofpostleft}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Actions Left</div>-->
                                </div>
                                <div class="col-1of4 fleft">
                                    <div class="slat-cta-container">
                                        <a href="">
                                            <div class="small-button slat-button detail-button-font"  ng-click="sendProgramId(program.id,'1')">View Program</div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                     <ul class="main-container fleft" ng-show="past_programs==''" id="nopastprogramsavailable">
                        <li class="slat-container fleft selfclear">
                            <div class="col-1of1 slat-unit fleft " >
                                No Past Programs Available
                            </div>
                        </li>
                    </ul>
                </div>
                </div>
            </div>            
        </div>
        </div>
    </div>
</body>
</html>