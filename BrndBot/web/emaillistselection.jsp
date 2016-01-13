<%-- 
    Document   : emaillistselection
    Created on : Jan 9, 2016, 11:56:23 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@page import="com.controller.SqlMethods"%>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="js/angular.min.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <title>BrndBot - Email List Selection</title>    
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="stylesheet" type="text/css" href="css/popup.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="js/emaillistselection.js" type="text/javascript"></script>
    <jsp:include page="basejsp.jsp" />
    <style>
        #emladdrstxtarea,#clktoupload,#upload,#emaildetailscontbtn,#emaildetailsid,#backemaildetails{display:none;}
    </style>
    <%!
        String emailSubject = "";
        String emailList = "";
        String htmlData = "";
        String emailAddresses = "";
        String iframeName = "";
        String iframeUrl="";
        String draft_id = "0";
    %>
    <%
        sqlmethods.session = request.getSession(true);

        emailSubject = (String) sqlmethods.session.getAttribute("email_subject");
        //emailAddresses = (String) sqlmethods.session.getAttribute("email_addresses");
        htmlData = (String) sqlmethods.session.getAttribute("htmldata");
        iframeName = (String) sqlmethods.session.getAttribute("iframeName");
        iframeUrl="/BrndBot/DownloadHtmlServlet?file_name="+iframeName+".html";
        draft_id = "0";
        if (!request.getParameter("draftid").equals("null")){
                    draft_id = (String)request.getParameter("draftid");
                    out.println();
        }
    %>    
    
</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main" >
    <%@include file="navbarv2.jsp" %>
    <%@include file="emaillistselectionpopup.jsp" %>
    <input type="hidden" value="<%= draft_id %>" name="draft_id" id="draft_id"></input>  
    <input type="hidden" value="<%= iframeName %>" name="iframeName" id="iframeName1"></input>  
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="exit-button-detail">
                <a class=" exit-button-icon" href="" id="backemaillist">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                </a>
                <a class=" exit-button-icon" href="" id="backemaildetails">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                </a>
            </div>
            <div class="page-title-with-back page-title-font">Email List Selection</div>
            <!--<div class="page-cta-container">
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  End Marketing Program</div>    
                </a>
            </div>-->
        </div>
        <!--<div class="page-subnav-bar-with-dropdown"> 
              <div class="subnav-dropdown">
                 <span class="hub-dropdown-text">Email</span>
                  <object type="image/svg+xml" data="/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </object>
            </div>
            <div class="top-subnav-tabs-container">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-link-active"> <a class="h3-active-subnav">Email Drafts</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Analytics</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email History</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Lists</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Settings</a></li>
                </ul>
            </div>
        </div>-->
    </div>
        <!--Main Content GENERIC--> 
        <div class="sequence-page-background" id="emaillistselid" ng-controller="EmailListController" ng-init="showEmailList()">
        <div class="sequence-page-content-container" >
            <div class="sequence-page-header">Who do you want to send this email to?</div>
            <div class="email-list-selection fleft">
               <div class="col-1of1 fleft unit">
                    <div class="col-9of10 fleft ">
                        <div class="h2 col-1of1">
                            <select class="chooseEmailList col-1of1" id="chooseEmailList" name="chooseEmailList">
                                <option value="1">Manual</option>
                                <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                <option style="background:#fff;" ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>
                            </select>
                        </div>
                        <!--<div class="p chooseList-subtext col-1of1">Choose a recipient list</div>-->
                    </div>
                   <div class="col-9of10 fleft pushUp-30">
                       <input type="button" id="addCsvFileButton" onclick="selectCsvFile()" class="md-button add-button22" value="Add CSV or Email Manually"></input>
                   </div>
                   <div class="col-2of4 fleft pushUp-30" id="clktoupload"> 
                       <div class="h3 col-1of1" id="dragtext" hidden="true">Drag and drop a csv file here and click to upload:</div>
                       <!--<div class="h3 col-1of1 "> Drop files here...</div>-->
                       <div id="drop-zone" class="col-1of4 pushUp-30">
                            <div id="clickHere">
                                <label for="fileid" class="h4 clktosel">Click to Select file </label>      
                                       <input type="file" id="fileid" name="file"  class="" style="display:none;" onchange="fileselected()"/>
                                <!--<div id="fileselect" class="h4">Click to Select file</div>-->                          
                            </div>
                        </div>
                    </div>
                   <div  class="col-2of4 fleft pushUp-30 lftpad-10" id="emladdrstxtarea">
                       <div class="h3 col-1of1" id="entertext" hidden="true">Enter Email Addresses manually here:</div>
                       <textarea class="emailaddresstextarea pushUp" id="emailaddresses"></textarea>
                   </div>
                   
                </div>
                <div class="col-15of4 fleft unit pushUp-30">
                      <input type="button" id="upload" value="Upload" onclick="upload()" class="md-button add-action-button"/>      
                   </div>
                    </div>
                </div>
<!--                <div class="col-1of1 fleft unit pushUp-15">
                    <div class="selection-container col-5p fleft"> 
                        <div id="uploademailaddrs" class="chooseList-icon" onclick="selcheckbox(this.id)"></div>
                    </div>
                    <div class="col-9of10 fleft">
                        <div class="h2 col-1of1">Enter or upload email addresses</div>
                        <div class="p chooseList-subtext col-1of1">Choose a recipient list</div>
                    </div>
                </div>-->
           
            
            <!--Inner Content Conatiner GENERIC-->
            
        </div>
        <div class="sequence-page-background" id="emaildetailsid">
            <div class="sequence-page-content-container">
                <div class="sequence-page-header">Email Details</div>
                    <div class="email-detail-selection col-1of1 fleft">
<!--                       <div class="col-1of1 fleft">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                    Enter a name for this Email
                                </div>
                                <input id="emailName" class="input-field-textfield col-8of10" placeholder="Enter Name of email" type="text"></input>
                            </div>
                        </div>-->
                        <div class="col-1of1 fleft pushUp">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                   Enter a subject line for this Email:
                                </div>
                                <input id="subject" class="input-field-textfield col-8of10" placeholder="Enter Subject of email" type="text"></input>
                            </div>
                        </div>
                         <div class="cols-2 fleft col-9of10 pushUp">
                             <div class="input-field-container col-3of10 fleft pushright">
                                <div class="h4"> From Name </div>
                                <input id="name" class="input-field-textfield input-placeholder" placeholder="Enter From Name" type="text"></input>
                            </div>
                             <div class="input-field-container col-3of10 fleft">
                                <div class="h4"> From Email Address </div>
                                <input id="formaddress" class="input-field-textfield input-placeholder" placeholder="Enter From  Email Address" type="text"></input>
                            </div>
                        </div>
                        <div class="cols-2 fleft col-9of10 pushUp">
                             <div class="input-field-container col-3of10 fleft pushright">
                                <div class="h4">To Email Address</div>
                                <input id="toaddress" class="input-field-textfield input-placeholder" placeholder="Enter To Email Address" type="text"></input>
                            </div>
                             <div class="input-field-container col-3of10 fleft">
                                <div class="h4"> Reply To Email Address</div>
                                <input id="email" class="input-field-textfield input-placeholder" placeholder="Enter Reply To Email Address" type="text"></input>
                            </div>
                        </div>
                        <div class="input-field-container col-1of8 fleft pushUp-50">
                        <div class="h2 curpointer">Preview</div>                       
                        </div>
                    <!--Inner Content Conatiner GENERIC-->
                </div>
                 <div class="iphoneshow img-responsive" id="popup" style="background-repeat: no-repeat; -webkit-background-size: contain;">
                            <div class="content">  
                                <iframe id='dynamictable' style='position:relative;background-color:#FFF;' src='<%=iframeUrl%>'></iframe>                   
                            </div>
                        </div>
            </div>
        </div>
  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
                    <div class="bottom-continue-button button-text-1" id="emaildetailscontbtn" onclick="validate();">Continue</div>
                    <div class="bottom-continue-button button-text-1" id="emailIdContinueButton">Continue</div>
            </div>
        </div>
        </div>
    <!--</div>-->
  
        <!--CTA Bar-->
        
    <!--</div>-->
<!--</div>-->
    </body>
</html>