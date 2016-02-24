
<%-- 
    Document   : AddAction.jsp
    Created on : 4 Dec, 2015, 11:58:16 AM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel="shortcut icon" href="favicon.png">
    <script src="js/popup.js" type="text/javascript"></script>
    
</head>    
    <body>
        <div id="fade" class="black_overlay" ></div>
        <div class="content" id="recuringPopup">
        <!--MainContent-->
            <div class="detail-overlay-content">
                <!--Top Nav Bar-->
                <div class="top-nav-container-detail">
                    <div class=" top-navbar-detail">
                        <a class=" exit-button-detail link svg" href="" id="closerecuringpopup">
                            <img type="image/svg+xml" src="images/Icons/close.svg" class="exit-button" style="cursor:pointer;"> </img>
                        </a>
                        <div  class="top-navbar-inner-bb-detail">
                            <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Recurring Email Automation Detail</span></div>
                            <div class="top-nav-cta-container">
                                <div class="approve-button-detail md-button" id="emailapprove" ng-show="recuring_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_email)">Approve</div>
                                <div class="approve-button-detail md-button" id="emailapprove" ng-show="recuring_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_email)">Disapprove</div>
                                <div class="delete-button-detail md-button" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="deleteAutomationSchedule(schedule_id, 'delete', master_email, 'true')">Delete Recurring Email</div>
                            </div>    
                        </div>
                    </div>
                    <div class="top-subnav-detail">
                        <div class="top-subnav-tabs-detail">
                             <ul class="top-subnav-nav-elements-detail">
                                <li class="top-subnav-links-detail top-subnav-link-active-detail" id="recuringaction"> <a class="h3-subnav-active">Automation Details</a></li>
                                <li class="top-subnav-links-detail" id="recuringtemplate"> <a class="h3-subnav" >Saved Email</a></li>
                                <!--<li class="top-subnav-links-detail  top-subnav-links-detail-last" id="recuringnote"> <a class="h3-subnav" >Notes</a></li>-->
                            </ul>
                        </div>
                    </div>
                </div>

                <!--Below Nav-->
                <div id="recuringactiondiv">
                    <div class="below-nav-container-saved-post-detail bgwhite">
                        <div class="inner-content-container-detail">
                            <div class="inner-content-detail">
                            <div class="fields-note-detail" >

                            <!--SAVED POST GOES HERE-->
                            <div class="fleft">
                                <div class="input-header-actionDetail" style="">
                                Name of Email
                                </div>
                                <div class="fleft input-field-textfield">
                                    {{schedule_title}}
                                </div>
                                <input type="hidden"  id="recuring_email_title" value="{{schedule_title}}" class="input-field-textfield" readonly/>
                                <input class="inputbox SP1" type="hidden" name="fb_scheduletype" id="fb_scheduletype" value='{{schedule_type}}'/>
                                <input class="inputbox SP1" type="hidden" name="fb_scheduleid" id="fb_scheduleid" value='{{schedule_id}}'/>
                            
                            <div class="input-header-actionDetail pushUp-50" style="">
                                Description of Email
                            </div>
                                <div class="fleft input-field-textfield">
                                    {{schedule_desc}}
                                </div>
                                <!--<input id="recuring_email_description" class="input-field-textfield" type="text" readonly  value="{{schedule_desc}}"/>-->
                            </div>
                        <div class="fleft">
                        <div class="input-header-actionDetail pushUp-30" style="">
                            Email Sending Details
                        </div>
                        <div class="sendingDetails-row fleft">
                            <div class="fleft input-supportText">
                                Send an email
                            </div>
                                <div class="fleft  input-field-date">{{days}}
                            </div>
                                <div class="fleft input-supportText">
                                days after added to
                            </div>
                            <input class="fleft input-field-date" readonly value="{{entities_list_name}}"/>
                            
                            <div class="fleft input-supportText">
                               email list.
                            </div>
                        </div>    
                        <div class="sendingDetails-row fleft">
                            <div class="fleft input-supportText">
                                Send the email at
                            </div>
                                <div class="fleft  input-field-date">
                                {{entities_selected_time| date:'HH:mm a'}}
                            </div>
                        </div>  
                        <div class="sendingDetails-row fleft">
                            <div class="fleft input-supportText">
                                Have this email automation recur until:
                            </div>
                            <div class="fleft  input-field-date">
                               {{entities_till_date| date:'MMMM dd yyyy'}}
                            </div>
                        </div> 
                        </div>    
                        <div class="fleft pushUp-">
                            <div class="input-header-actionDetail" style="">
                                Subject Line of Email:
                            </div>
                            <div class="input-field-textfield">
                                {{entities_subject}}
                            </div>
                            <div class="input-header-actionDetail" style="">
                                From name:
                            </div>
                            <div class="input-field-textfield">
                                {{entities_from_name}}
                            </div>
                            <div class="input-header-actionDetail" style="">
                                Reply-to-address:
                            </div>
                            <div class="input-field-textfield">
                                {{entities_reply_to_email_address}}
                            </div>
                        </div>
                        </div>
                            </div>
                        </div>
                    </div>
                    <div class="bottom-cta-bar5">
                        <div class="bottom-cta-button-container">
                            <div class="edit-button-detail md-button button-text-1 fright edit-button-detailrecemail" ng-click="calltoeditrecurring(program_id,schedule_id)">Edit Automation Details</div>
                        </div>
                    </div>
                </div>
                <div id="recuringtemplatediv">
                    <div class="below-nav-container-saved-post-detail emailcontentbordertop" >
                    <div class="inner-content-container-detail">
                        <div class="saved-post-preview-detail" id="noemailsdiv" ng-show="recuring_template_status === 'No Template'">
                            <!--SAVED Email GOES HERE-->
                            <div class="overlay-emptystate-icon">
                                <img type="image/svg+xml" src="images/Icons/Overlay_EmptyStates_Email-Empty-State.svg" class="overlay-emptystate-icon"/>
                            </div>
                            <div class="empty-state-container">
                                <div class="h2new textCenter textColor-Graynew">
                                    No emails saved to this action.
                                </div>
                                <!--<a class="link" href="">-->
                                    <a href="emailautomation.jsp?type=template&program_id={{program_id}}&entity_id={{schedule_id}}">
                                        <div class="md-button empty-state-cta">
                                            Save an email to this action
                                        </div>
                                    </a>
                                <!--</a>-->
                            </div>
                        </div>
                        <div class="below-nav-container-saved-post-detail fixingpos" id="savedemailsdiv" ng-show="recuring_template_status !== 'No Template'">
                            <div class="inner-content-container-detail fleft">
                                <div class="saved-post-header-detail1">

<!--                                    <div class="h4">Saved Email</div>

                                    <div class="instruction-text">Subject Line: {{entitiesdetails.subject}}</div>
                                    <div class="instruction-text">To: {{entitiesdetails.email_list_name}}</div>
                                    <div class="instruction-text">From Name: {{entitiesdetails.from_name}}</div>-->
                                    <div class="instruction-text1"></div>

                                </div>
                                <div class="inner-content-detail">
                                    <div class="saved-email-preview-detail ">
                                        <iframe id="recuringemailcontentiframe" class="content  emailcontentiframetag"></iframe>
                                    </div>
                                </div>
                            </div>
                        </div>
        <!--                        </div>
                            </div>-->
                        </div>
                    </div>
                    <div class="bottom-cta-bar"  id="recuringremovediv" >
                        <div class=" fixtobotom">
                            <div class="remove-button-detail md-button button-text-1 fleft fixbotomremovebtn" ng-click="deleteAutomationSchedule(schedule_id, 'removetemplate', master_email, 'true')">Remove saved Template</div>
                        </div>
                    </div>
                </div>
                <div id="recuringnotediv">
                    <div class="below-nav-container-saved-post-detail">
                    <div class="inner-content-container-detail">
<!--                        <div class="saved-post-header-detail">
                            <div class="h4">Write Notes about this Action</div>
                            <div class="instruction-text">Text Goes here!</div>
                        </div>-->
                        <div class="inner-content-detail">
                            <div class="saved-note-detail">

                                <!--SAVED POST GOES HERE-->

                                <div class="notes-container">
                                    <textarea class="notes-container-textarea" id="twtnote">{{schedule_desc}}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <div class="bottom-cta-bar5">
                    <div class="bottom-cta-button-container">
                        <div class="edit-button-detail md-button button-text-1 fleft">Save Changes</div>
                    </div>
                </div>
                </div>
                <!--CTA Bar-->
                
                  </div>
        </div>
         
    
    </body>
</html>
