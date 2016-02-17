<%-- 
    Document   : notepreview.jsp
    Created on : 14 Jan, 2016, 3:56:12 PM
    Author     : Satyajit-Roy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="style_detail_overlay.css">
    <link rel="stylesheet" type="text/css" href="normalize.css">
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel="shortcut icon" href="favicon.png">
    <script src="js/popup.js" type="text/javascript"></script>
    
</head>    
    <body>
        <div id="fade" class="black_overlay" ></div>
        <div class="content" id="notediv">
            <!--MainContent-->
            <div class="detail-overlay-content">
                <input type="hidden" id="remainder_id" value="{{schedule_id}}"/>
                <!--Top Nav Bar-->
                <div class="top-nav-container-detail">
                    <div class=" top-navbar-detail">
                        <a class=" exit-button-detail link svg" id="closenotepopup">
                            <img type="image/svg+xml" src="images/Icons/close.svg" class="exit-button" style="cursor:pointer;"> </img>
                        </a>
                        <div  class="top-navbar-inner-bb-detail">
                            <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Reminder Action Detail</span></div>
                            <div class="top-nav-cta-container">
                                <div class="delete-button-detail md-button" ng-click="deleteSchedule(schedule_id,'delete', getnote())">Delete Reminder</div>
                            </div>    
                        </div>
                    </div>
                    <div class="top-subnav-detail">
                        <div class="top-subnav-tabs-detail">
                            <ul class="top-subnav-nav-elements-detail">
                                <li class="top-subnav-links-detail" id="reminderdetailstab"> <a class="h3-subnav">Reminder Details</a></li>
                                <li class="top-subnav-links-detail top-subnav-link-active-detail" id="savedremindertab"> <a class="h3-subnav-active">Saved Reminder</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--Below Nav-->
                <div class="below-nav-container-saved-post-detail" id="reminderdetailsdiv">
                    <div class="inner-content-container-detail">
<!--                            <div class="saved-post-header-detail">
                                <div id="twtemptyheader" class="h4" ng-show="schedule_desc ===''">Write Notes about this Action</div> 
                                <div id="twtnoteheader" class="h4" ng-show="schedule_desc !==''">Notes</div>-->
                                    <div hidden="" id="note_desc" class="instruction-text">{{schedule_desc}}</div>
                            <!--</div>-->
                                <div class="inner-content-detail">
                                    <div class="fields-note-detail">

                                        <!--SAVED POST GOES HERE-->

                                    <div class="input-header-actionDetail" style="">
                                        TITLE
                                    </div>
                                    <input type="text" id="edit_note_title"  name="edit_note_title" value="{{schedule_title}}" class="input-field-textfield full"/>
                                    <input class="inputbox SP1" type="hidden" name="note_scheduleid" id="note_scheduleid" value='{{schedule_id}}'/>
                                    <input class="inputbox SP1" type="hidden" name="note_action_type" id="note_action_type" value='{{schedule_type}}'/>
                                    <input class="inputbox SP1" type="hidden" name="note_status" id="note_status" value='{{schedule_type}}'/>

                                    
                                    <div class="inlineFlex">
<!--                                        <div class="half"> 
                                            <div class="input-header-actionDetail" style="">
                                                Status
                                            </div>
                                            <input type="text" id="twnotemplate" value="{{note_template_status}}" class="input-field-textfield width75  " readonly/>
                                        </div>-->
                                        <div class="half">
                                            <div class="input-header-actionDetail" style="">
                                                Marketing Program
                                            </div>
                                            <input type="text" id="Generalid" value="{{marketing_program_name}}" class="input-field-textfield width75" readonly/>

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
                                         SCHEDULED TO POST ON
                                    </div>
                                    <div class="inlineFlex">
                                    <div class="half">
                                        <div>
                                            <div class="input-header-actionDetail " >
                                            DATE
                                            </div>
                                                <input type="text"  name="datepickernote" id="datepickernote"  class="input-field-textfield width75" value="{{entities_selected_time| date:'MMM dd yyyy'}}" readonly/>                                        
                                                <input type="hidden" value="{{entities_selected_time| date:'MMM dd yyyy'}}" id="notedateid"/>
                                                <script>
                                                    var picker = new Pikaday(
                                                    {
                                                        format: 'MMM DD YYYY',
                                                        field: document.getElementById('datepickernote'),
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
                                        <input id="timepickernote" type="text" name="timepickernote" class="timepickertextbox " value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                                        <script>
                                            $('#timepickernote').timepicki({
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
<!--                    <div class="bottom-cta-bar">
                        <div class="bottom-cta-button-container1">
                            <div class="edit-button-detail md-button button-text-1 fleft ">Save Reminder</div>
                        </div>
                    </div>-->
                </div>
                
                <div class="below-nav-container-saved-post-detail" id="savedreminderdiv">
                    <div class="below-nav-container-saved-post-detail">
                        <div class="inner-content-container-detail">
                            <div class="saved-post-header-detail" ng-show="schedule_desc ===''">
                                <div class="h4">Write Notes about this Action</div>
                            </div>
                            <div class="saved-post-header-detail" ng-show="schedule_desc !==''">
                                <div class="h4">Notes</div>
                            </div>
                            <div class="inner-content-detail">
                                <div class="saved-note-detail">

                                    <!--SAVED POST GOES HERE-->

                                    <div class="notes-container">
                                        <textarea class="notes-container-textarea" id="reminderdesc{{schedule_id}}" placeholder="Text Goes here!">{{schedule_desc}}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<!--                    <div class="bottom-cta-bar">
                        <div class="bottom-cta-button-container1">
                            <div class="edit-button-detail md-button button-text-1 fleft ">Save Reminder</div>
                        </div>
                    </div>-->
                </div>
                <!--CTA Bar-->
                <div class="" id="reminderactionsave">
                    <div class="bottom-cta-button-container">
                        <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="updateNote()">Save Reminder</div>
                    </div>
                </div>
                <div class="" id="remindernotesave">
                    <div class="bottom-cta-button-container">
                        <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="updateNoteDescription(schedule_id)">Save Note</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>