<%-- 
    Document   : facebookpreview
    Created on : 8 Dec, 2015, 7:32:55 PM
    Author     : Satyajit Roy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="favicon.png"></link>
    <script src="js/popup.js"></script>
</head>    

<body>
<div class="content">
    <div id="fade" class="black_overlay"></div>
    <div id="emailsection">
        <div class="detail-overlay-content">
        <!--Top Nav Bar-->
        <div class="top-nav-container-detail" id="emailtopnav">
            <div class=" top-navbar-detail" id="emailtopnavdetails">
                <a class=" exit-button-detail link svg close" href="">
                    <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button" style="cursor:pointer;"> </img>
                </a>
                <div  class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Email Action Detail</span></div>
                    <div class="top-nav-cta-container">
                        <div class="approve-button-detail md-button" id="emailapprove" ng-show="email_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_email)">Approve to Send</div>
                        <div class="approve-button-detail md-button" id="emailapprove" ng-show="email_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_email)">Disapprove</div>
                        <div class="delete-button-detail md-button" ng-click="deleteSchedule(schedule_id,'delete', master_email)">Delete Action</div>
                    </div>    
                </div>
            </div>
            <div class="top-subnav-detail" id="emailtopsubnav">
                <div class="top-subnav-tabs-detail">
                     <ul class="top-subnav-nav-elements-detail">
                        <li class="top-subnav-links-detail top-subnav-link-active-detail" id="emailaction" val="{{schedule_id}}"> 
                            <input type="hidden" id="emailaction_id" value="{{schedule_id}}"></input>
<!--                            <input type="hidden" id="emaidesc_value" value="{{schedule_desc}}"></input>-->
                            <a class="h3-subnav-subnav-active">Action Details</a>
                        </li>
                        <li class="top-subnav-links-detail" id="emailpost"> <a class="h3-subnav">Saved Email</a></li>
                        <li class="top-subnav-links-detail  top-subnav-links-detail-last" id="emailnote"> <a class="h3-subnav">Notes</a></li>
                    </ul>
                </div>
            </div>
        </div>
        
        <!--Below Nav-->
        
        <div id="emailactionsection">    
            <div class="below-nav-container-saved-post-detail">
                <div class="inner-content-container-detail">
                <div class="saved-post-header-detail">
                   <div class="h4" ng-show="schedule_desc === ''">Write Notes about this Action</div>                      
                   <div class="h4" ng-show="schedule_desc !== ''">Notes</div>
                   <div class="instruction-text" ng-show="schedule_desc !== ''"><p id="emaildescription{{schedule_id}}">{{schedule_desc}}</p></div>
                </div>
                    <div class="inner-content-detail">
                        <div class="fields-note-detail">

                            <!--SAVED POST GOES HERE-->

                        <div class="input-header-actionDetail" style="">
                                TITLE
                        </div>
                            <input id="email_edit_title" name="email_edit_title" class="input-field-textfield" value="{{schedule_title}}---"/>
                            <input class="inputbox SP1" type="hidden" name="email_scheduleid" id="email_scheduleid" value='{{schedule_id}}' />
                            <input class="inputbox SP1" type="hidden" name="email_schedule_type" id="email_schedule_type" value='{{schedule_type}}'/>
                                        
                        <div class="inlineFlex">
                                <div class="half"> 
                                    <div class="input-header-actionDetail" style="">
                                        Status
                                    </div>
                                    <input type="text" id="mailnotemplate1" value="No Template" class="input-field-textfield width75  " readonly/>
                                    <input type="text" id="mailtemplatesaved1" value="Template Saved" class="input-field-textfield width75 " readonly/>
                                </div>
                                <div class="half">
                                    <div class="input-header-actionDetail" style="">
                                        Marketing Program
                                    </div>
                                    <input type="text" id="emailmarkprog" value="{{marketing_program_name}}" class="input-field-textfield width75" readonly/>
                                </div>
                        </div>
                            <div class="input-header-actionDetail h3" style="">
                                POSTING DETAILS
                            </div>
                            <div class="inlineFlex">
                                <div class="half">
                                    <div class="input-header-actionDetail" style="">
                                        POSTING TO
                                    </div>
                                    <input type="text" value="{{schedule_type}}" class="input-field-textfield width75" readonly/>
                                </div>
                            </div>
                            <div class="input-header-actionDetail h3" style="">
                                 SCHEDULED TO SEND ON
                            </div>
                            <div class="inlineFlex">
                            <div class="half">
                               <div ng-show="user_marketing_program_id > 0">
                                  <div class="input-header-actionDetail " >
                                    DAY
                                  </div>
                                <!--<input type="text" readonly   name="datepickertwitter" id="datepickertwitter1"  class="input-field-textfield width75" value="Sun Jan 01 1970"/>--> 
                                <input type="text" readonly class="input-field-textfield width75" id="emaildays" name="emaildays" value="{{days}}"/>                                                   
                               </div>
                                <div ng-show="user_marketing_program_id == 0">
                                    <div class="input-header-actionDetail " >
                                        DATE
                                    </div>
                                        <input type="hidden" class="textbox" id="emaildays" name="emaildays" value="0"/>
                                        <input type="text"  readonly  name="emaildatetime" id="emaildatetime"  class="input-field-textfield width75" value="{{entities_selected_time| date:'MMM dd yyyy'}}" />                                        
                                        <script>
                                            var picker = new Pikaday(
                                            {
                                                format: 'MMM DD YYYY',
                                                field: document.getElementById('emaildatetime'),
                                                firstDay: 1,
                                                minDate: new Date(2000, 0, 1),
                                                maxDate: new Date(2050, 12, 31),
                                                yearRange: [2000,2050]
                                            });

                                        </script>
                                </div>
                            </div>
                            <div class="half">
                                <div class="input-header-actionDetail" style="">
                                    TIME
                                </div>
                                <input id="timepickeremail" type="text" name="timepickeremail" class="timepickertextbox " value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                                <script>
                                    $('#timepickeremail').timepicki({
                                        show_meridian:true,
                                        min_hour_value:0,
                                        max_hour_value:12,
                                        step_size_minutes:01,
                                        overflow_minutes:true,
                                        increase_direction:'up',
                                        disable_keyboard_mobile: true
                                    });
                                </script>
                            </div>
                        </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="emailpostsection">
            <div class="below-nav-container-saved-post-detail">
            <div class="inner-content-container-detail">
                <div class="saved-post-preview-detail" id="noemailsdiv" ng-show="entitiesdetails.body === undefined">

                                <!--SAVED Email GOES HERE-->

                                <div class="overlay-emptystate-icon">
                                    <img type="image/svg+xml" src="images/Icons/Overlay_EmptyStates_Email-Empty-State.svg" class="overlay-emptystate-icon"/>
                                </div>
                                <div class="empty-state-container">
                                    <div class="h2new textCenter textColor-Graynew">
                                        No emails saved to this action.
                                    </div>
                                    <a class="link" href="dashboard.jsp">
                                        <div class="md-button empty-state-cta">
                                        Go to Dashboard
                                        </div></a>
                                </div>
                </div>
                <div class="below-nav-container-saved-post-detail" id="savedemailsdiv">
                    <div class="inner-content-container-detail fleft">
                        <div class="saved-post-header-detail">

                            <div class="h4">Saved Email</div>

                            <div class="instruction-text">Subject Line: {{entitiesdetails.subject}}</div>
                            <div class="instruction-text">To: {{entitiesdetails.email_list_name}}</div>
                            <div class="instruction-text">From Name: {{entitiesdetails.from_name}}</div>
                            <div class="instruction-text">Reply to Address: {{entitiesdetails.reply_to_email_address}}</div>

                        </div>
                        <div class="inner-content-detail">
                            <div class="saved-email-preview-detail emailcontent">
                                <iframe id="emailcontentiframe" class="emailcontentiframetag"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
<!--                        </div>
                    </div>-->
                </div>
            </div>
        </div>
        
        <div id="emailnotesection">
            <div class="below-nav-container-saved-post-detail">
            <div class="inner-content-container-detail">
                <div class="saved-post-header-detail">
                    <div class="h4">Write Notes about this Action</div>
                    <div class="instruction-text">Text Goes here!</div>
                </div>
                <div class="inner-content-detail">
                    <div class="saved-note-detail">

                        <!--SAVED POST GOES HERE-->

                        <div class="notes-container">
                              <textarea class="notes-container-textarea" id="emailnotes{{schedule_id}}">{{schedule_desc}}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
        
        <!--CTA Bar-->
          
        <div class="" id="emailactionsave">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="updateActionEmail()">Save Action</div>
            </div>
        </div>
         
<!--        <div class="" id="emailpostremove">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft remove-button-detail" style="width:23%;">Remove Saved Email</div>
            </div>
        </div>-->
        
        <div class="" id="emailnotesave">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="updateActionEmailNote(schedule_id)">Save Notes</div>
            </div>
        </div>
        <div class="bottom-cta-bar" id="emailpostremove" ng-show="entitiesdetails.body !== undefined">
             <div class="bottom-cta-button-left-container">
 
                     <div class="remove-button-detail md-button button-text-1 fleft" ng-click="deleteSchedule(schedule_id,'remove',master_email)">Remove Saved Email</div>
            <div class="bottom-cta-button-containernew">
 
                <div class="edit-button-detail md-button button-text-1 fleft" style="left:550px;position:relative;">Edit Email</div>
            </div>
        </div>
        </div>
    </div>
</div>
</body>

