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
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link rel="stylesheet" href="css/popup.css"/>
    <script src="js/alertmessage.js"></script>
    <script src="js/angular.min.js" type="text/javascript"></script>    
    <script src="js/bootstrap.min.js"></script>
    <script src="js/form.js"></script>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script src="js/emaillist.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script src="js/popup.js" type="text/javascript"></script> 
    <link rel="stylesheet" href="css/popup.css"/>
    <style>#deleteEmaildraft,#addemlstbtn,#savesetbtn{display:none;}</style>
    <title>BrndBot - Email List</title>  
</head>    
<body ng-app class="claro1" >
     <%@include file="navbarv2.jsp" %>
    <div id="fade"></div>
    <div class="content-main" ng-controller="EmailListController" >
        <!--SideNav-->
       
        <script src="js/marketing.js" type="text/javascript"></script>
       <jsp:include page="createemaillist.jsp"/>
       <jsp:include page="savedEmailDraftPopup.jsp"/> 
      
        <!--Top Nav-->   
        
        <div class="top-nav">
            <div class="page-title-bar col-1of1"> 
                <!--<div class="exit-button-detail"></div>-->
                <div class="page-title-regular page-title-font">Your Email Hub</div>
                <div class="page-cta-container">
                <a href="">
                    <div class="delete-button md-button button fleft"  id="deleteEmaildraft" ng-click="deletedrafts('deleteMultiple');" > Delete Email Drafts</div>
                </a>
                <a href="">
                    <div id="addemlstbtn" class="add-action-button md-button button-text-1" ng-click="addemaillist()"> Add Email List</div>
                </a>
                <a href="">
                    <div id="savesetbtn" class="add-action-button md-button button-text-1" ng-click="setEmailSettings()">Save</div>
                </a>
                </div>
            </div>
            <div class="page-subnav-bar-with-dropdown">
                <div class="subnav-dropdown pushright" onclick="funshowdropdown()">
                    <span class="hub-dropdown-text" id="emlsclspanid">Email</span>
                 <img type="image/svg+xml" src="images/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"></img>
                </div>
                <div class="top-subnav-tabs-container">
                   <ul class="top-subnav-nav-elements">
                       <li class="top-subnav-links" id="emldrftab"> <a  class="h3">Email Drafts</a></li>
                       <li class="top-subnav-links" id="emlhistab"> <a class="h3">Email History and Analytics</a></li>
<!--                        <li class="top-subnav-links"> <a class="h3">Scheduled Emails</a></li>-->
                        <li class="top-subnav-link-active" id="emllistab"> <a class="h3-active-subnav">Email Lists</a></li>
                        <li class="top-subnav-links" id="emlsettab"> <a class="h3">Email Settings</a></li>
                    </ul>
                </div>
                
            </div>
            <div class="dropdown-hub" id="emaildropdown" style="display:none;">
                <ul class="dropdown-inner">
                    <li class="dropdown-section fleft col-1of1 " id="emailhubli">
                        <a href="emaillists.jsp">
                            <div><img type="image/svg+xml" src="images/Icons/Hub-Dropdowns_email.svg" class="dropdown-hub-icon fleft col-2of10" style="cursor:pointer;"></img></div>
                            <div class="fleft col-6of10 dropdown-label-active">Email Hub</div>
                        </a>                        
                    </li>
                    <a href="social.jsp">
                        <li class="dropdown-section col-1of1 fleft" id="socialhubli" >
                            <div><img type="image/svg+xml" src="images/Icons/Hub-Dropdowns_social.svg" class="dropdown-hub-icon fleft col-2of10" style="cursor:pointer;"></img></div>
                            <div class="fleft col-6of10 dropdown-label">Social Hub</div>
                        </li>
                    </a>
                </ul>
            </div>
        </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
            <div class="page-content-container email-list-contact-page" id="emaillistsdiv" ng-controller="EmailListController">
             <jsp:include page="createemaillist.jsp"/> 
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content">
                    <div class="page-content-title-bar">
                        <div class="page-content-title pushUp h2" ng-show="emailLists.length>0">Your Email Lists </div>
                        <div class="page-content-title pushUp h2" ng-show="emailLists.length==0">No email lists present</div>
                    </div>
                    <!--List Starts Here-->
                    <div ng-init="showEmailListWithContacts()">
                        <ul class="main-container fleft"ng-show="emailLists.length>0">
                        <li class="slat-container fleft selfclear" ng-repeat="email in emailLists">
                             <div class="selection-container col-5p"> 
                                 <div class="selection-icon" id="{{email.emailListID}}" onclick="selemlcheckbox(this.id)"><input type="checkbox" id="{{email.emailListID}}" value="{{email.emailListName}}" name="entityname" hidden></input></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 ">{{email.emailListName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Created on {{email.emailListAddedDate| date: 'MM-dd-yyyy'}}</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.noofcontants}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Number of Contacts</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <a href="emaillistsdetails.jsp?list_name={{email.emailListName}}&type=user">
                                        <div class="small-button slat-button" >Manage List</div>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li class="slat-container fleft selfclear" ng-repeat="email in emailListsMindbody">
                             <div class="selection-container col-5p hint--left emailconnectalign" data-hint="EmailConnect">
                                 <img src="images/Icons/emailConnect.svg" class="emailConnect-icon" style="cursor:pointer;"> </img>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of2 fleft">
                                    <a href="emaillistsdetails.jsp?list_name={{email.emailListName}}&type=mindbody">
                                        <div class="slat-title email-list-slat-title col-1of1 ">{{email.emailListName}}</div>
                                        <div class="action-list-slat-description col-1of1 sh3"> </div>
                                    </a>
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
            <div class="page-content-container email-list-contact-page email-draft-page" id="emaildraftsdiv" >
                <div>
                    <%--<jsp:include page="savedraftemailpopup.jsp"/>--%>
                    
                   
                <div class="fleft content" ng-init="getAllDrafts()">
                    <div class="page-content-title h2">Your Email Drafts</div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                            <div class="col-7of10 slat-unit fleft">
                                <div>
                                    <div ng-show="emaildraftnumber == '0'">{{emaildraftsstatus}}</div>
                                </div>
                            </div>
                        <div  ng-repeat="drafts in emaildrafts">
                            <li class="slat-container fleft selfclear" id="li{{drafts.id}}">
                                <div class="selection-container col-5p" id="deleteids"> 
                                    <div class="selection-icon" id="{{drafts.id}}" onclick="selemldrftcheckbox(this.id)">
                                        <input type="checkbox" id="{{drafts.id}}" value="{{drafts.id}}" name="draftname" style="display:none;"></input>
                                    </div>
                                </div>
                                <div class="col-7of10 slat-unit fleft ">
                                    <div class="slat-title-container col-1of1 fleft">
                                        <div class="slat-title email-list-slat-title col-1of1 sh1">{{drafts.emailsubject}}</div>
                                        <div class="action-list-slat-description col-1of1 sh3">This is an email draft| Last edited on {{drafts.editdate | date: "MMM dd yyyy"}}</div>
                                    </div>
                                </div>
                                <div class="col-1of4 fleft">
                                    <div class="slat-cta-container">
                                        <a>
    <!--                                        <span class="small-button slat-button detail-button-font" ng-click="editDrafts(drafts.id, drafts.categoryid, drafts.emailsubject, drafts.subcategoryid, drafts.subcategoryname)">View and Edit Draft</span>-->
                                            <span class="small-button slat-button detail-button-font" ng-click="showdraftpopup(drafts.id, drafts.categoryid, drafts.emailsubject, drafts.editdate, drafts.subcategoryid, drafts.subcategoryname)">View and Edit Draft</span>
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </div>
                    </ul>
                </div>
            </div> 
            </div>
            <div class="page-content-container email-list-contact-page email-draft-page" id="emailhistorydiv" ng-controller="emailHistory">
                <div class="fleft content"  ng-init="displayemailhistory()">
                    <div class="page-content-title h2" id="nohistorydiv">Your Email History</div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft" id="historydiv" >
                        <li class="slat-container-analytics fleft selfclear" ng-repeat="email in email_history">
                            <div class="col-3of10 slat-unit fleft ">
                                <div class="slat-title-container col-1of1 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">{{email.tag}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">Sent on</div>
                                </div>
                            </div>
                             <div class=" col-analytic-attribute fleft slat-attribute-container-analytics">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.sent}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Number Sent</div>
                                </div>
                             <div class=" col-analytic-attribute fleft slat-attribute-container-analytics">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{(email.opens/email.sent)*100}}%</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Open Rate</div>
                                </div>
                             <div class=" col-analytic-attribute-lg fleft slat-attribute-container-analytics">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{(email.clicks/email.sent)*100}}%</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Click Through Rate</div>
                                </div>
                              <div class=" col-analytic-attribute fleft slat-attribute-container-analytics">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{email.unsubs}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Unsubscribes</div>
                                </div>
                            <div class=" fright">
                                <div class="slat-cta-container">
                                    <a href="">
                                        <div class="small-button slat-button detail-button-font">View Email</div>
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>  
            <div class="page-content-container email-list-contact-page email-draft-page" id="emaisetdiv">
                <div class="fleft content ">
                    <div class="page-content-title h2">Your Email Settings</div>
                    <!--List Starts Here-->
                    <div class="emailSettings-container fleft pushUp-50 main-container main-container50width col-8of10">
                       <div class="col-1of1 fleft">
                            <div class="col-7of10 fleft">
                                <div class="h4" style="">
                                    Default from Address:
                                </div>
                                <input id="from_address" class="input-field-textfield col-8of10" type="text" name="from_address" placeholder="Enter default email address"/>
                                
                            </div>
                        </div>
                        <div class="col-1of1 fleft pushUp">
                            <div class="col-7of10 fleft">
                                <div class="h4" style="">
                                   Reply to Email Address:
                                </div>
                                    <input id="reply_email_address" class="input-field-textfield col-8of10" type="text" name="reply_email_address" placeholder="Enter reply-to-email address"/>
                            </div>
                        </div>
                    </div>                        
                </div>   
            </div>
        </div>
        <div class="bottom-cta-bar padleft" id="removeselactions" >
            <div class="bottom-cta-button-container padright">
               <div class="remove-action-detail md-button button-text-1" id="deleteEmailList" ng-click="deleteEmailList();">Remove Selected Email List(s)</div>
            </div>
        </div>
    </div>
    <div id="light" class="white_content closepopup">
        <a href = "javascript:void(0)" style="text-decoration:none;">
            <div id="slider-button" style="font-size:40px;text-align:center;z-index:1006;display:none;">
                <p style="margin-top:-7px;"><img src="images/Icons/yourPlan.svg" height="25" width="25" /></p>
            </div>
        </a>
    </div>     
        <!--CTA Bar-->
       
    </body>
</html>