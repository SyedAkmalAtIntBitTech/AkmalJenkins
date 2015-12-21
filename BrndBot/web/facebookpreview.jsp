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
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="shortcut icon" href="favicon.png">
    <script src="js/popup.js"></script>
</head>    

<body>
<div class="content">
    <div id="fade" class="black_overlay"></div>
    <div id="facebooksection">
        <div class="detail-overlay-content">
        <!--Top Nav Bar-->
        
        <div class="top-nav-container-detail" id="fbtopnav">
            <div class=" top-navbar-detail" id="fbtopnavdetails">
                <a class=" exit-button-detail link svg close" href="">
                    <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button" style="cursor:pointer;"> </img>
                </a>
                <div  class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Facebook Post Action Detail</span></div>
                    <div class="top-nav-cta-container">
                        <div class="approve-button-detail md-button" id="fbapprove">Approve</div>
                        <div class="delete-button-detail md-button">Delete Action</div>
                    </div>    
                </div>
            </div>
            <div class="top-subnav-detail" id="fbtopsubnav">
                <div class="top-subnav-tabs-detail">
                     <ul class="top-subnav-nav-elements-detail">
                        <li class="top-subnav-links-detail top-subnav-link-active-detail" id="facebookaction"> <a class="h3-subnav-subnav-active">Action Details</a></li>
                        <li class="top-subnav-links-detail" id="facebookpost"> <a class="h3-subnav" >Saved Post</a></li>
                        <li class="top-subnav-links-detail  top-subnav-links-detail-last" id="facebooknote"> <a class="h3-subnav" >Notes</a></li>
                    </ul>
                </div>
            </div>
        </div>
         
        <!--Below Nav-->
        
        <div id="facebookactionsection">    
            <div class="below-nav-container-saved-post-detail">
            <div class="inner-content-container-detail">
                <div class="saved-post-header-detail">
                    <div class="h4" ng-show="schedule_desc === ''">Write Notes about this Action</div>
                    <div class="h4" ng-show="schedule_desc !== ''">Notes</div>
                    <div class="instruction-text" ng-show="schedule_desc !==''">{{schedule_desc}}</div>
                </div>
                    <div class="inner-content-detail">
                        <div class="fields-note-detail">

                            <!--SAVED POST GOES HERE-->

                        <div class="input-header-actionDetail" style="">
                            TITLE
                        </div>
                        <input type="text" id="fb_action_title" value="{{schedule_title}}" class="input-field-textfield full"/>
                        <input class="inputbox SP1" type="hidden" name="fb_scheduletype" id="fb_scheduletype" value='{{schedule_type}}'/>
                        <input class="inputbox SP1" type="hidden" name="fb_scheduleid" id="fb_scheduleid" value='{{schedule_id}}'/>
                        <div class="inlineFlex">
                            <div class="half"> 
                                <div class="input-header-actionDetail" style="">
                                    Status
                                </div>
                                <input type="text" id="fbnotemplate" value="No Template" class="input-field-textfield width75  " readonly/>
                                <input type="text" id="fbtemplatesaved" value="Template Saved" class="input-field-textfield width75 " readonly/>
                            </div>
                            <div class="half">
                                <div class="input-header-actionDetail" style="">
                                    Marketing Program
                                </div>
                                <input type="text" id="Generalid" value="{{marketing_program_name}}" class="input-field-textfield width75" readonly/>
                                
                            </div>
                        </div>
                        
                        <div class="h3 input-header-actionDetail" style="">
                            POSTING DETAILS
                        </div>
                        <div class="inlineFlex">
                            <div class="half">
                                <div class="input-header-actionDetail" style="">
                                    POSTING TO
                                </div>
                                <input type="text" value="{{schedule_type}}" class="input-field-textfield width75" readonly/>
                            </div>
                            <div class="half">
                                <div class="input-header-actionDetail" style="">
                                  MANAGED PAGE
                                </div>
                                <input type="text" value="Cannot Edit" class="input-field-textfield width75" readonly/>
                            </div>
                        </div>
                        <div class="inlineFlex">
                            <div class="half">
                                <div class="input-header-actionDetail half" style="">
                                    DATE
                                </div>

                                <input type="text" readonly  name="datepickerfb" id="datepickerfb"  class="datepickertextbox" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">                                        
                                <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepickerfb'),
                                        firstDay: 1,
                                        minDate: new Date(1970, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [1970,2050]
                                    });

                                </script>

                            </div>

                            <div class="half1">
                            <div class="input-header-actionDetail" style="">
                                TIME
                            </div>
                            <input id="timepickerfb" type="text" name="timepickerfb" class="timepickertextbox" value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                            <script>
                                $('#timepickerfb').timepicki({
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
        
        <div id="facebookpostsection">
            <div class="below-nav-container-saved-post-detail">
            <div class="inner-content-container-detail">
                <div class="saved-post-header-detail">
                    <div class="h4">Saved Facebook Post</div>
                    <div class="instruction-text">Saved post.</div>
                </div>
                    <div class="inner-content-detail">
                        <div class="saved-post-preview-detail">

                            <!--SAVED Email GOES HERE-->

          <div class="Facebook-preview">
                                <div class="Facebook-preview-header">
                                    <div class="Facebook-preview-profpic"></div>
                                    <div class="Facebook-preview-name-container">
                                        <div class="Facebook-preview-name">BrndBot Demo</div>
                                    </div>
                                </div>
                                <div class="Facebook-preview-usercontent">Demo content goes right here</div>
                                <div class="Facebook-link-container">
                                    <div class="Facebook-preview-image"></div>
                                    <div class="Facebook-preview-link-container">
                                        <div class="Facebook-preview-link-title">Demo Link Copy Goes Here and Wraps Around</div>
                                        <div class="Facebook-preview-link-description">This workshop is going to be so awesoem for the new season and get you in really good shape!</div>
                                        <div class="Facebook-preview-link-url">Demo Description goes here but cuts off</div>
                                    </div>
                                </div>
                            </div>
                            
                            
                         
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="facebooknotesection">
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
                            <textarea class="notes-container-textarea">{{schedule_desc}}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
        
        <!--CTA Bar-->
            
        <div class="" id="fbactionsave">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="updateActionFacebook()">Save Action</div>
            </div>
        </div>
         
        <div class="" id="fbpostremove">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft removebutton">Remove Saved Post</div>
            </div>
        </div>
        
        <div class="" id="fbnotesave">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft savebutton">Save Notes</div>
            </div>
        </div>
         
        </div>
    </div>
</div>
</body>

