<%-- 
    Document   : selectimagetype
    Created on : 21 Dec, 2015, 5:14:48 PM
    Author     : satyajit-roy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="shortcut icon" href="favicon.png"/>
    <link rel="stylesheet" href="css/pikaday.css"/>
    <link rel="stylesheet" href="css/datepickerpikaday.css"/>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
    <script src="js/pikaday.js"></script>
<!--    <script src="js/socialimage.js" type="text/javascript"></script>-->
    <script src="js/socialeditor.js" type="text/javascript"></script>
    <script src="js/ajaxfileupload.js" type="text/javascript"></script>
    <style>.arrow_top{display:none;}
        .timepicker_wrap{top:-155px !important;width: 200px;}
        #schedule_time{width:60% !important;}
    </style>

</head>    
    <body>
            <div id="fade" class="black_overlay"></div>
            <input type="hidden" name="selectedtype" id="selectedtype" value=""></input>
            <input type="hidden" name="selectedid" id="selectedid" value=""></input>
            <input type="hidden" name="social" id="social" value="social"></input>
                        
            <div id="sendpopup">
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                       <div class="pop-up-exit-icon" id="closesendpopup">
                           <img type="image/svg+xml" src="images/Close.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                       </div>
                    </div> 
                    <div class="pop-up-container1 pop-up-container-Schedule"> 
                        <div class="pop-up-title pop-up-title-h1"> Would you like to send now or schedule for later?</div>
                        <div class="pop-up-inner-imageSelection">
                            <div class="col-8of10  center">
                                <div class="col-4of10 pushright fleft" onclick="sendEmail()">
                                    <div class="image-selection-button" id="posttofb"> 
                                        <img type="image/svg+xml" src="images/postNow.svg" class="post-button-icon" style="cursor:pointer;"> </img>
                                    </div>
                                    <div class="button-description">Send Now</div>
                                </div>
                                <div class="col-4of10 fright">
                                    <div class="image-selection-button" id="schedule">
                                        <img type="image/svg+xml" src="images/schedulePost.svg" class="schedule-button-icon" style="cursor:pointer;"> </img>
                                    </div>
                                    <div class="button-description">Schedule for Later</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="schedulepopup" ng-controller="emailSettings" id="emailSettings">
                <div class="pop-up-exit-container">
                    <div class="pop-up-exit-icon" id="closeschedulepopup">
                        <img type="image/svg+xml" src="images/Close.svg" class="exit-button-icon"></img>
                    </div>
                </div>
                <div class="pop-up-background" ng-init="getProgramNames();">
                    <div class="pop-up-container pop-up-container-newaction1"> 
                        <div class="pop-up-title pop-up-title-h1"> Schedule this Action</div>
                        <div class="pop-up-exit-container">
                            <a href="/Newest_Files/YourPlan.html" class="pop-up-exit-icon">
                                <object type="image/svg+xml" data="/Close.svg" class="exit-button-icon"> </object>
                            </a>
                        </div> 
                        <div class="pop-up-inner1">
                        <div class="popup-section-header">Save to Existing Action</div>
                        <div class="line-divider-notop"></div>
                        <div class="input-field-container">
                            <div class="input-header"> Choose Marketing Program</div>
                            <div >
                                <select name="programs" id="programs" class="full input-field-textfield2 input-placeholder" onchange="validateact();">
                                    <option value="0">--General--</option>
                                    <option ng-repeat="programs in marketing_programs" value="{{programs.program_id}}">{{programs.name}}</option>
                                </select>
                            </div>
                                <select name="email_actions" id="email_actions" class="full input-field-textfield2 input-placeholder" onchange="validateact();">
                                    <option value="0" >CUSTOM</option>
                                    <option ng-repeat="actions in email_actions" value="{{actions.id}}">{{actions.schedule_title}}</option>
                                </select>
                        </div>
                        <div class="popup-section-header pushUp-15">-Or- Create a New Action</div>
                        <div class="line-divider-notop "></div>
                        <div class="input-header">Name this Action</div>
                        <div class="">
                            <input type="text" class="input-field-textfield input-placeholder full" id="schedule_title" name="schedule_title" placeholder="TITLE" ></input>
                        </div>
                        <div class="cols-2">
                             <div class="input-field-container col-4of10 fleft pushright">
                                <div class="input-header"> Action Date </div>
                                <input type="text" readonly id="schedule_date" name="schedule_date" class="input-field-textfield input-placeholder"  placeholder="Enter Action Date"></input>
                                <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('schedule_date'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });
                                </script>
                            </div>
                             <div class="input-field-container col-4of10 fleft">
                                <div class="input-header"> Action Time </div>
                                <div class="">
                                    <input id="schedule_time" type="text" name="schedule_time" class="input-field-textfield input-placeholder" placeholder="Enter Action Time" readonly/><br>
                                    <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#schedule_time').timepicki({
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
                    <div class="pop-up-cta-container pop-up-cta-container-newaction1 cur" id ="schedulethepost"  ng-click="setScheduling()">
                        <input type="hidden" value="socialmedia"/>
                        <div class="algnmnt" name="socialscheduleid" id="socialscheduleid" >SCHEDULE</div>
                    </div>
                    </div>
                </div>
            </div>
    </body>
</html>