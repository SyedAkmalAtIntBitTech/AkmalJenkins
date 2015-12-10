<%-- 
    Document   : emailsubject
    Created on : 29 Jul, 2015, 4:38:08 PM
    Author     : Sandeep kumar
    Edited By  : Satyajit Roy
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/slat.css">
    <link rel="shortcut icon" href="favicon.png">
    
    <script src="js/angular.min.js" type="text/javascript"></script>    
    <script src="js/bootstrap.min.js"></script>
    <script src="js/form.js"></script>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/emaillist.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        
</head>    
<body ng-app>
    <div class="content-main" ng-controller="EmailListController">
        <!--SideNav-->
        <%@include file="navbarv2.jsp" %>
        <!--Top Nav-->   
        <div class="top-nav">
            <div class="page-title-bar col-1of1"> 
                <!--<div class="exit-button-detail"></div>-->
                <div class="page-title-regular page-title-font">Your Email Hub</div>
                <div class="page-cta-container">
                <a href="">
                    <div class="add-action-button md-button button-text-1"> Add Email List</div>
                </a>
                </div>
            </div>
            <div class="page-subnav-bar-with-dropdown"> 
                 <div class="subnav-dropdown pushright">
                     <span class="hub-dropdown-text">Email</span>
                      <img src="images/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </img>
                </div>
                <div class="top-subnav-tabs-container">
                   <ul class="top-subnav-nav-elements">
                        <li class="top-subnav-links"> <a href="/Newest_Files/EmailHub_EmailDrafts_clean.html" class="h3">Email Drafts</a></li>
                        <li class="top-subnav-links"> <a class="h3">Email History and Analytics</a></li>
                        <li class="top-subnav-links"> <a class="h3">Scheduled Emails</a></li>
                        <li class="top-subnav-link-active"> <a href="" class="h3-active-subnav">Email Lists</a></li>
                        <li class="top-subnav-links"> <a class="h3">Email Settings</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
        <div class="page-content-container email-list-contact-page">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content">
                    <div class="page-content-title-bar">
                        <div class="page-content-title pushUp h2">Your Email Lists</div>
                    </div>
                    <!--List Starts Here-->
                    <div ng-init="showEmailListWithContacts()">
                        
                        <ul class="main-container fleft">
                        <li class="slat-container fleft selfclear" ng-repeat="email in emailLists">
                             <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 ">{{email.emailListName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Created on {{email.listAddedDate}}</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.noofcontants}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Number of Contacts</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <a href="">
                                        <div class="small-button slat-button" ng-click="updateList(email.emailListName, 'user')">Manage List</div>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li class="slat-container fleft selfclear" ng-repeat="email in emailListsMindbody">
                             <div class="selection-container col-5p">
                                 <img src="images/Icons/emailConnect.svg" class="emailConnect-icon" style="cursor:pointer;"> </img>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 " ng-click="updateList(email.emailListName, 'mindbody')">{{email.emailListName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3"> </div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.noofcontants}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Number of Contacts</div>
                                </div>             
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                </div>
                            </div>
                        </li>  
                    </ul>
                   </div>
                </div>
            </div>            
        </div>
        </div>
        
    </div>
  
        <!--CTA Bar-->
<!--        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
                REMOVE BUTTON HERE
               <div class="remove-action-detail md-button button-text-1">Remove Selected Action(s)</div>
            </div>
        </div>-->
    </body>
</html>