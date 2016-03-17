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
    <link rel="stylesheet" href="css/popup.css"/>
    <link rel="stylesheet" href="css/pikaday.css"/>
    <link rel="stylesheet" href="css/datepickerpikaday.css"/>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="js/pikaday.js"></script>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <title>BrndBot - Marketing Program Actions</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/configurations.js"></script>
    <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
    <script src="js/popup.js" type="text/javascript"></script>      
    <script src="js/timepicki.js" type="text/javascript"></script>  
    <style>#progname,#instancehidden,#fbpostremove{display:none;}</style>
    <jsp:include page="basejsp.jsp"/>
    <%!
            String program_id = "";
            String program_date="";
            int past = 0;
    %>
    <%
        program_id = request.getParameter("program_id");
        past = Integer.parseInt(request.getParameter("past"));
        program_date = request.getParameter("program_date");
    %>
    <script>
        var program = "";
        program = <%= program_id %>;
    </script>      
</head>    

<body class="" ng-app ng-controller="programactions">
    <!--SideNav-->
    <div class="content-main" ng-init="getProgramActions()">
    <input type="hidden" name="program_id" id="program_id" value="<%= program_id %>"/>
    <input type="hidden" name="program_end_date" id="program_end_date" value="<%= program_date %>"/>
    <input type="hidden" name="change" id="change" value="0"/>
    <script src="js/programactions.js"></script>
    <jsp:include page="facebookpreview_marketing.jsp"/> 
    <jsp:include page="twitterpreview_marketing.jsp"/> 
    <jsp:include page="emailpreviewpopup_marketing.jsp"/>
    <jsp:include page="marketingprogramaddaction.jsp"/>
    <jsp:include page="recuringPopup.jsp"/>
    <%@include file="navbarv2.jsp" %>   
    <!--Top Nav-->   
    <div class="top-nav" >
        <div class="page-title-bar col-1of1"> 
            <a class="exit-button-icon" href="marketingprogramlists.jsp">
                <div class="exit-button-detail"> 
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </div>
            </a>
            <div id="progname" class="page-title-with-back page-title-font">{{programs.programdetails.programName}}</div>
            <input type="hidden" name="program_name2" id="program_name2" value="{{programs.programdetails.programName}}"></input>
            <div class="page-cta-container">
                <% if(past!=1) {%>
                <a href="" class="gray-button fleft">
                    <div class="md-button"  ng-click="endMarketingProgram()" id="endMarketingProgram"> End Marketing Program</div>    
                </a>
                <% } %>
            </div>
        </div>
        <div class="page-subnav-bar-regular"> 
            <div class="top-subnav-tabs-container-with-button">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-links" id="ovrviewli" ng-hide="checkProgramStatus()"> <a href="" id="overview" class="h3" id="overview" >Overview</a></li>
<!--                    <li class="top-subnav-links" id="fieldsli"> <a href="" id="fields" class="h3" ng-click="showfieldstab()">Fields</a></li>-->
                    <li class="top-subnav-link-active" id="actionsli"> <a href="" id="actions" class="h3-active-subnav" ng-click="showactionstab()">Actions</a></li>
<!--                        <li class="top-subnav-links"> <a class="h3" href="/Newest_Files/MarketingProgram_Notes.html">Notes</a></li>
                    <li class="top-subnav-links"> <a class="h3">Website Integration</a></li>
                    <li class="top-subnav-links"> <a class="h3">Assets</a></li>-->
                </ul>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
            <div class="page-content-container marketingProgram-action-page" id="instancehidden">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container" id="actionstab">
                <div class="fleft content">
                     <div class="page-content-title-bar fleft col-1of1">
                        <div id="noemailautomation" class="page-content-title h2 fleft">Recurring Email Automations</div>
                        <div class="action-cta-container">
                            <% if(past!=1){%>
                            <a href="" id="addrecemail" class="edit-button-detail fleft">
                                <div class=" md-button" ng-click="addEditRecuringAction('add',<%=program_id%>, '0')">Add Recurring Email Automation</div>    
                            </a>
                            <a href="" id="deleterecurringemail" class="delete-button-detail fleft">
                                <div class="md-button delrecemlbtn" ng-click="deleteSchedule('0', 'deleteMultiple','recurring')">Delete Selected Recurring Email(s)</div>    
                            </a>
                            <%}%>
                        </div>
                    </div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                  <li class="slat-container fleft selfclear" ng-repeat="emailautomation in programs.emailautomation">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon" ng-hide="checkProgramStatus()" id="{{emailautomation.scheduledEntityListId}}" onclick="selcheckboxrecemail(this.id);setSelectedRecuringIds('{{emailautomation.scheduledEntityListId}}');"><input type="checkbox" ng-disabled="checkProgramStatus()" id="{{emailautomation.scheduledEntityListId}}"   value="{{emailautomation.scheduledEntityListId}}" hidden/></div>
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft hint--top" ng-show="emailautomation.status === 'Template Saved'"  data-hint="Template Saved" > 
                                    <img src="images/Icons/templateSaved.svg" class="status-button" />
                                </div>
                                <div class="icon-container fleft hint--top" ng-show="emailautomation.status === 'No Template'"  data-hint="No Template" > 
                                    <img src="images/Icons/needTemplate.svg" class="status-button"/>
                                </div>
                                <div class="icon-container fleft hint--top" ng-show="emailautomation.status === 'Approved'"  data-hint="Approved" > 
                                    <img src="images/Icons/ActionApproved.svg" class="status-button"/>
                                </div>
                                <div class="icon-container fleft hint--top" ng-show="emailautomation.status === 'Complete'"  data-hint="Complete" > 
                                    <img src="images/Icons/ActionComplete.svg" class="status-button"/>
                                </div>
                                <div class="slat-title-container col-1of2  fleft">
                                    <div  class="slat-title email-list-slat-title col-1of1 sh1"></div>
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">{{emailautomation.programTemplateName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">{{emailautomation.description}}</div>
                                </div>
                                <div class=" col-2of10  fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{emailautomation.dateTime| date:'MMM dd'}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Date</div>
                                </div>
                                <div class=" col-2of10  fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" >{{actionType}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Status</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font"
                                         ng-click="getRecuringMailDetails(emailautomation.scheduledEntityListId,
                                                                emailautomation.status, emailautomation.tillDate,
                                                                emailautomation.dateTime,
                                                                emailautomation.actionType,
                                                                emailautomation.programTemplateName,
                                                                emailautomation.description,
                                                                emailautomation.postDateStatus,
                                                                emailautomation.days)">
                                                                           Details</div> 
                                </div>
                            </div>
                        </li>
                </ul>
                </div>
                <div class="fleft content">
                    <div class="page-content-title-bar fleft col-1of1">
                        <div id="noota" class="page-content-title h2 fleft">One Time Actions</div>
                        <div class="action-cta-container">
                            <% if(past!=1){%>
                            <a id="onetimeactbtn" href="" class="edit-button-detail fleft">
                                <div class=" md-button" ng-click="ShowAddAction()">  Add One Time Action</div>    
                            </a>
                            <a href="" id="deleteonetimeact" class="delete-button-detail fleft">
                                <div class="md-button delrecemlbtn" ng-click="deleteSchedule('1', 'deleteMultiple','nonrecurring')">Delete Selected Action(s)</div>
                            </a>
                            <%}%>
                        </div>
                    </div>
                    <!--List Starts Here-->
                    <ul class="main-container fleft">
                    <li class="slat-container fleft selfclear"  ng-repeat="programaction in programs.programactions">
                            <div class="selection-container col-5p"> 
                                <div class="selection-icon" ng-hide="checkProgramStatus()" id="{{programaction.scheduledEntityListId}}" onclick="selcheckboxonetimeact(this.id);setSelectedIds('{{programaction.scheduledEntityListId}}');"><input type="checkbox" id="entityid{{programaction.scheduledEntityListId}}"  value="{{programaction.scheduledEntityListId}}" name="entityname" hidden></input></div>
                                <!--<div class="selection-icon">-->    
                                    <!--<input type="checkbox" ng-disabled="checkProgramStatus()" id="{{programaction.scheduledEntityListId}}" class="delchckbx" onclick="setSelectedIds('{{programaction.scheduledEntityListId}}')" value="{{programaction.scheduledEntityListId}}" hidden />-->
                                <!--</div>-->
                            </div>
                            <div class="col-7of10 slat-unit fleft ">
                                <div class="icon-container fleft hint--top" ng-show="programaction.status === 'Template Saved'"  data-hint="Template Saved" > 
                                    <img src="images/Icons/templateSaved.svg" class="status-button" />
                                </div>
                                <div class="icon-container fleft hint--top" ng-show="programaction.status === 'No Template'"  data-hint="No Template" > 
                                    <img src="images/Icons/needTemplate.svg" class="status-button"/>
                                </div>
                                <div class="icon-container fleft hint--top" ng-show="programaction.status === 'Approved'"  data-hint="Approved" > 
                                    <img src="images/Icons/ActionApproved.svg" class="status-button"/>
                                </div>
                                <div class="icon-container fleft hint--top" ng-show="programaction.status === 'Complete'"  data-hint="Complete" > 
                                    <img src="images/Icons/ActionComplete.svg" class="status-button"/>
                                </div>
                                <div class="slat-title-container  col-1of2 fleft">
                                    <div class="slat-title email-list-slat-title col-1of1 sh1">{{programaction.programTemplateName}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3">{{programaction.description}}</div>
                                </div>
                                <div class=" col-2of10 fleft width20 slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{programaction.postDate| date:'MMM dd yyyy'}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Date</div>
                                </div>
                                <div class=" col-2of10 fleft width20 slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" ng-show="programaction.actionType==master_facebook || programaction.actionType==master_twitter">{{programaction.actionType}}</div>
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" ng-show="programaction.actionType==master_note || programaction.actionType==master_email">{{programaction.actionType}}</div>
                                    <div class="list-column-description col-1of1 sh3 fleft">Action Type</div>
                                </div>
                            </div>
                            <div class="col-1of4 fleft">
                                <div class="slat-cta-container">
                                    <div class="small-button slat-button detail-button-font" ng-click="getScheduleDetails(programaction.scheduledEntityListId,
                                                                        programaction.status,
                                                                        programaction.postDate,
                                                                        programaction.actionType,
                                                                        programaction.programTemplateName,
                                                                        programaction.description,
                                                                        programaction.postTime,
                                                                        programaction.postDateStatus,
                                                                        programaction.days,
                                                                        programs.programdetails.programName,
                                                                        <%=program_id%>)">Details</div>
                                </div>
                            </div>
                        </li>

                    </ul>
                </div>
               </div>      
            <div class="page-inner-content-container" id="fieldstab" hidden>
                <div class="fleft content">
                    <div class="page-inner-content-container ">
                        <div class="fleft content">
                            <div class="main-container fleft">
                                <!--Content Starts Here-->
                                <div class="input-header" style="">
                                    Name of Workshop
                                </div>
                                <input type="text" class="input-field-textfield" placeholder="Enter Name of Workshop"/>

                                
                                <div class="input-header" style="">
                                    Date
                                </div>
                                <input type="text" class="input-field-textfield" placeholder="Enter Date of Workshop "/>
                                
                                <div class="input-header" style="">
                                    Time of Workshop
                                </div>
                                <input type="text" class="input-field-textfield" placeholder=" Enter Time of Workshop "/>
                               
                                
                                <div class="input-header" style="">
                                    Name of Workshop Instructor
                                </div>
                                <input type="text" class="input-field-textfield" placeholder="Enter Name of Workshop Instructor"/>
                                   
                                
                                <div class="input-header">
                                    Details
                                </div>
                                <div class="input-header-subtext">
                                    Keep this to 1-2 sentences
                                </div>
                                <input type="text" class="input-field-textfield" placeholder="Enter Details of Workshop "/>
                                    
                                <div class="input-header">
                                    Description of Workshop
                                </div>
                                <div class="input-header-subtext">
                                    Put all details in this area.
                                </div>
                                <input type="text" class="input-field-textfield" placeholder="Enter Description of Workshop "/>
                                    
                                
                            </div>
                        </div>
                    </div>       
                  </div>           
             </div>       
            <div class="page-inner-content-container" id="overviewtab">
                <div class="fleft content">
                    <div class="main-container fleft">
                        <!--Content Starts Here-->
                         <div class="col-6of10 fleft">
                            <div class="h4" style="">
                                Marketing Program Name:
                            </div>
                            <div class="">
                                <input id="program_name" name="program_name" value="{{programs.programdetails.programName}}" class="input-field-textfield col-8of10"/>
                            </div>
                            <div class="h4 pushUp" style="">
                               End Date:
                            </div>
                            <div class="">
                                <input type="text" readonly name="datepicker" id="progactdatepicker"  class="input-field-textfield col-4of10" value="<%=program_date%>"/>                                        
                                <script>
                                            var picker = new Pikaday(
                                            {
                                            field: document.getElementById('progactdatepicker'),
                                                    firstDay: 1,
                                                    minDate: new Date(2000, 0, 1),
                                                    maxDate: new Date(2050, 12, 31),
                                                    yearRange: [2000, 2050]
                                            });                                
                                </script>
                            </div>
                                <div class="h4 pushUp" style="">
                                Enter a URL for this marketing program:
                            </div>
                            <div class="">
                                <input id="link_url" name="link_url" class="input-field-textfield col-7of10" value="{{programs.programdetails.linktodestination}}"/>
                            </div>
                           
                                <div class="h4 pushUp" style="">
                                Enter a name for this Marketing URL:
                            </div>
                            <div class="">
                                <input id="link_name" name="link_name" class="input-field-textfield col-7of10" value="{{programs.programdetails.link_name}}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
        <div class="bottom-cta-bar" id="saveprogram">
            <div class="bottom-cta-button-container1 bottomheight">
               <div class="add-action-button md-button button-text-1" ng-click="updateUserProgram()">Save changes</div>
            </div>
        </div>                     
        <div class="bottom-cta-bar bordertop" id="removeactionbutton" >
            <div class="bottom-cta-button-container bottomheight">
                <!--<div id="deleteonetimeact" class="remove-button-detail md-button button-text-1" ng-click="deleteSchedule('0', 'deleteMultiple')">Delete Selected Action(s)</div>-->
<!--               <div id="deleterecurringemail" class="remove-button-detail md-button button-text-1" ng-click="deleteSchedule('0', 'deleteMultiple')">Delete Selected Recurring Email(s)</div>-->
            </div>
        </div>   
        <div id="light" class="white_content closepopup">
                <a href = "javascript:void(0)" style="text-decoration:none;">
                    <div id="slider-button" style="font-size:40px;text-align:center;z-index:1006;display:none;">
                        <p style="margin-top:-7px;"><img src="images/Icons/yourPlan.svg" height="25" width="25" /></p>
                    </div>
                </a>
        </div>
<!--        CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
             REMOVE BUTTON HERE
               <div class="remove-button-detail md-button button-text-1">Delete Selected Actions</div>

            </div>
        </div>
        </div>
</div>-->
    </body>
</html>