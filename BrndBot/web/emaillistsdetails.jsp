<%-- 
    Document   : emaillistsdetails
    Created on : Dec 11, 2015, 1:08:24 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/popup.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/popup.js"></script>
    <script src="js/angular.min.js" type="text/javascript"></script>    
    <script src="js/configurations.js" type="text/javascript"></script>  
    <script src="js/emaillist.js"></script>
    <title>BrndBot - Email List Details</title>    
</head>    
<%
String list_name=request.getParameter("list_name");
String type=request.getParameter("type");
%>
<body ng-app>
    
    <input type="hidden" id="get_list_name" value="<%=list_name%>"/>
    <input type="hidden" id="get_type" value="<%=type%>"/>   
    <!--SideNav-->
    <div class="content-main" ng-controller="EmailListController">
    <%@include file="navbarv2.jsp" %>
     <jsp:include page="addcontact.jsp"/>
        
    <!--Top Nav-->   
    <div class="top-nav" >
        <div class="page-title-bar col-1of1"> 
           <a class=" exit-button-icon" href="emaillists.jsp">
               <div class="exit-button-detail">
                <img src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"/>
               </div>
           </a>
            <div class="page-title-with-back page-title-font"><%=list_name%></div>
            <div class="page-cta-container">
                <a href="" class="gray-button button pushright fleft">
                    <div class=" md-button" onclick="unsel()"> Unselect Contacts</div>    
                </a>
                <a href="" id="delcontact" class="delete-button button fleft">
                    <div class=" md-button" ng-click="deleteSelected()"> Delete Contact</div>    
                </a>
                <a href="javascript:void(0)" class="fleft">
                     <div id="addcontact"  class="add-button md-button" onclick="fun('add','','','','');"> Add Contact</div>    
                </a>
<!--                <a href="javascript:void(0)" class="fleft">
                     <div id="addcontacts"  class="add-button md-button" ng-click="showAddContacts()"> Add Contacts</div>    
                </a>-->
            </div>
        </div>
        <div class="page-subnav-bar-regular"> 
            <div class="top-subnav-tabs-container-with-button">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-link-active" id="emailListli"> <a class="h3-active-subnav" id="emailList" ng-click="updateList()">Email List</a></li>
                    <li class="top-subnav-links" id="importListli" > <a class="h3" id="importList" ng-click="showAddContacts()">Import Contacts to List</a></li>
                </ul>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="page-background" id="showList">
        <div class="page-content-container email-list-contact-page">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content" ng-init="updateList()" >
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                        <li class="slat-container fleft selfclear" ng-repeat="email in user_emailAddresses">
                            <div class="col-1of1 slat-unit fleft ">
                                <a href="">
                                    <div class="selection-container col-5p fleft"> 
                                        <div class="selection-icon" id="{{email.id}}" onclick="selemlcheckbox(this.id);">
                                            <input type="checkbox" id="emailid{{email.emailAddress}}" value="{{email.emailAddress}}" name="emaillistname" hidden></input>
                                        </div>
                                    </div>
                                </a>
                                <div class="slat-title-container col-4of10 fleft">
                                    <div class="slat-title  col-1of1 sh1-contact" onclick="fun('update','{{email.emailAddress}}','{{email.id}}','{{email.firstName}}','{{email.lastName}}');">{{email.emailAddress}}</div>
                                    <div class="slat-title  col-1of1 sh1-contact" ng-show="email.emailAddress == '' && type == 'user'" id='NoContacts'>No contacts available</div>
                                    <div class="action-list-slat-description col-1of1 sh3-contact">Added on {{email.addedDate| date:'  M/dd/yyyy'}}</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container" ng-show="email.firstName !== ''">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.firstName}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">First Name</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container" ng-show="email.lastName !== ''">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.lastName}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Last Name</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>            
        </div>
        </div>
    
        <div id="tab4" class="col-md-10 col-md-offset-2" class="hide">
                <div id="emailsubjectdiv" ng-controller="EmailListController">
                    <p class="header1">Email List:</p>
                    <input type="text" class="hideinputborder" id="email_list_name" name="email_list_name" placeholder="Enter Here"/> <br>
                    <div class="col-md-7 col-md-offset-0">
                        <label>Upload CSV</label><br><br>
                        <input type="file" class="fileUpload" id="fileUpload" name="fileUpload"><br>
                        <input class="button button--moema button--text-thick button--text-upper button--size-s" type="button" id="upload" value="Upload" onclick="upload()" /><br>
                    </div><br>
                    <div id="dvCSV"></div>
                    <textarea width="400" height="500" id="textArea"></textarea><br><br>
                    <input  id="emailSubjectContinueButton" type="button" class="button button--moema button--text-thick button--text-upper button--size-s" value="Update" ng-click="updateEmailList()">
                </div>
        </div>
    </div>

    </div>
</div>
</body>
</html>
