<%-- 
    Document   : twitterpreview
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
    <!--<style>.time_pick{width:100%;}</style>-->
</head>    

<body>
<div class="content">
    <div id="fade" class="black_overlay"></div>
    <div id="twittersection">
        <div class="detail-overlay-content">
        <!--Top Nav Bar-->
        
            <div class="top-nav-container-detail" id="twtopnav">
                <div class=" top-navbar-detail" id="fbtopnavdetails">
                    <a class=" exit-button-detail link svg close" href="">
                        <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button" style="cursor:pointer;"> </img>
                    </a>
                    <div  class="top-navbar-inner-bb-detail">
                        <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Twitter Post Action Detail</span></div>
                        <div class="top-nav-cta-container">
                            <div class="approve-button-detail md-button" id="fbapprove">Approve</div>
                            <div class="delete-button-detail md-button">Delete Action</div>
                        </div>    
                    </div>
                </div>
                <div class="top-subnav-detail" id="twtopsubnav">
                    <div class="top-subnav-tabs-detail">
                         <ul class="top-subnav-nav-elements-detail">
                            <li class="top-subnav-links-detail top-subnav-link-active-detail" id="twitteraction"> <a class="h3-subnav-subnav-active">Action Details</a></li>
                            <li class="top-subnav-links-detail" id="twitterpost"> <a class="h3-subnav">Saved Post</a></li>
                            <li class="top-subnav-links-detail  top-subnav-links-detail-last" id="twitternote"> <a class="h3-subnav">Notes</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <!--Below Nav-->

            <div id="twitteractionsection">    
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
                            <input type="text" id="edit_twitter_title"  name="edit_twitter_title" value="{{schedule_title}}" class="input-field-textfield full"/>
                            <input class="inputbox SP1" type="hidden" name="twitter_scheduleid" id="twitter_scheduleid" value='{{schedule_id}}'/>
                            <input class="inputbox SP1" type="hidden" name="twitter_action_type" id="twitter_action_type" value='{{schedule_type}}'/>
                            
                            <div class="inlineFlex">
                                <div class="half"> 
                                    <div class="input-header-actionDetail" style="">
                                        Status
                                    </div>
                                    <input type="text" id="twnotemplate" value="No Template" class="input-field-textfield width75  " readonly/>
                                    <input type="text" id="twtemplatesaved" value="Template Saved" class="input-field-textfield width75 " readonly/>
                                </div>
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
                                <div class="input-header-actionDetail " >
                                    DAY
                                </div>
                                <div ng-show="user_marketing_program_id > 0">
                                <input type="text" readonly   name="datepickertwitter" id="datepickertwitter1"  class="input-field-textfield width75" value="Sun Jan 01 1970"/> 
                                <input type="text" readonly class="input-field-textfield width75" id="twdays" name="twdays" value="{{days}}"/>                                                   
                               </div>
                                <div ng-show="user_marketing_program_id == 0">
                                        <input type="hidden" class="textbox" id="twdays" name="twdays" value="0"/>
                                        <input type="text"  name="datepickertwitter" id="datepickertwitter"  class="input-field-textfield width75" value="{{entities_selected_time| date:'MMM dd yyyy'}}" readonly/>                                        
                                        <script>
                                            var picker = new Pikaday(
                                            {
                                                format: 'MMM DD YYYY',
                                                field: document.getElementById('datepickertwitter'),
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
                                <input id="timepickertwitter" type="text" name="timepickertwitter" class="timepickertextbox " value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                                <script>
                                    $('#timepickertwitter').timepicki({
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

            <div id="twitterpostsection">
                <div class="below-nav-container-saved-post-detail">
                <div class="inner-content-container-detail">
                    <div class="saved-post-header-detail">
                        <div class="h4">Saved Twitter Post</div>
                        <div class="instruction-text">Saved post.</div>
                    </div>
                        <div class="inner-content-detail">
                            <div class="saved-post-preview-detail">

                                <!--SAVED Email GOES HERE-->

              <div class="twitter-post-preview">
                                    <div class="Facebook-preview-header">
                                        <div class="Facebook-preview-profpic"><img id="twitter_preview_profpic" src="http://www.adweek.com/socialtimes/files/2013/11/alltwitter-twitter-bird-logo-white-on-blue2.png"/></div>
                                        <div class="Facebook-preview-name-container">
                                            <div class="Facebook-preview-name">{{entitiesdetails.metadata.text}}</div>
                                        </div>
                                    </div>
                                    <div class="Facebook-preview-usercontent">{{entitiesdetails.metadata.shorturl}}</div>
                                    <div class="Facebook-link-container">
                                        <div class="Facebook-preview-image"><img id="prevtwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/></div>
<!--                                        <div class="Facebook-preview-link-container">
                                            <div class="Facebook-preview-link-title">{{entitiesdetails.metadata.title}}</div>
                                            <div class="Facebook-preview-link-description">This workshop is going to be so awesoem for the new season and get you in really good shape!</div>
                                            <div class="Facebook-preview-link-url">Demo Description goes here but cuts off</div>
                                        </div>-->
                                    </div>
                                </div>



                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="twitternotesection">
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

            <div class="" id="twactionsave">
                <div class="bottom-cta-button-container">
                    <div class="edit-button-detail md-button button-text-1 fleft savebutton">Save Action</div>
                </div>
            </div>

            <div class="" id="twpostremove">
                <div class="bottom-cta-button-container">
                    <div class="edit-button-detail md-button button-text-1 fleft removebutton">Remove Saved Post</div>
                </div>
            </div>

            <div class="" id="twnotesave">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft savebutton">Save Notes</div>
            </div>
        </div>
        
        </div>
    </div>
</div>
</body>

