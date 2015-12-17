<%-- 
    Document   : marketingprogramactions
    Created on : Dec 17, 2015, 7:46:00 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <title>Marketing Program Actions</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/configurations.js"></script>
    <script src="js/angular.min.js"></script>
    <jsp:include page="basejsp.jsp"/>
     <%!
            String program_id = "";
        %>
        <%
            program_id = request.getParameter("program_id");
        %>
        <script>
            var program = "";
            program = <%= program_id %>;
        </script>
        <script src="js/programactions.js"></script>
</head>    

<body class="" ng-app>
    <!--SideNav-->
    <div class="content-main" ng-controller="programactions">
    <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav" ng-init="getProgramActions()">
        <div class="page-title-bar col-1of1"> 
            <a class="exit-button-icon" href="/Newest_Files/MarketingProgram_CurrentList.html">
                <div class="exit-button-detail"> 
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </div>
            </a>
            <div class="page-title-with-back page-title-font">{{programs.programdetails.programName}}</div>
            <div class="page-cta-container">
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  End Marketing Program</div>    
                </a>
            </div>
        </div>
        <div class="page-subnav-bar-regular"> 
            <div class="top-subnav-tabs-container-with-button">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-links"> <a href="/Newest_Files/MarketingProgram_Overview.html"  class="h3">Overview</a></li>
                    <li class="top-subnav-links"> <a href="/Newest_Files/MarketingProgram_Fields.html" class="h3">Fields</a></li>
                    <li class="top-subnav-link-active"> <a href="/Newest_Files/MarketingProgram_Actions.html" class="h3-active-subnav">Actions</a></li>
                    <!--    <li class="top-subnav-links"> <a class="h3" href="/Newest_Files/MarketingProgram_Notes.html">Notes</a></li>
                    <li class="top-subnav-links"> <a class="h3">Website Integration</a></li>
                    <li class="top-subnav-links"> <a class="h3">Assets</a></li>-->
                </ul>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
        <div class="page-content-container marketingProgram-action-page">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container" >
                <div class="fleft content">
                     <div class="page-content-title-bar fleft col-1of1">
                        <div class="page-content-title h2 fleft">Recurring Email Automations</div>
                        <div class="action-cta-container">
                            <a href="" class="edit-button-detail fleft">
                                <div class=" md-button">  Add Recurring Email Automation</div>    
                            </a>
                        </div>
                    </div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                  <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Nov. 16</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Date</div>
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
                                    <object type="image/svg+xml" data="/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Nov. 16</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Date</div>
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
                                    <object type="image/svg+xml" data="/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Nov. 16</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Date</div>
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
                <div class="fleft content">
                    <div class="page-content-title-bar fleft col-1of1">
                        <div class="page-content-title h2 fleft">One Time Actions</div>
                        <div class="action-cta-container">
                            <a href="" class="edit-button-detail fleft">
                                <div class=" md-button">  Add One Time Action</div>    
                            </a>
                        </div>
                    </div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                  <li class="slat-container fleft selfclear">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon"></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft "> 
                                    <object type="image/svg+xml" data="/Icons/templateSaved.svg" class="status-button"> </object>
                                </div>
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">Upcoming Workshop Post</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Howdy Again</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Nov. 16</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Date</div>
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
            </div>            
        </div>
        </div>
    </div>
  
        <!--CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
             REMOVE BUTTON HERE
               <div class="remove-button-detail md-button button-text-1">Delete Selected Actions</div>

            </div>-->
<!--        </div>
        </div>
</div>-->
    </body>
</html>