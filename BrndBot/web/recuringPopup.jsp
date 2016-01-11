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
                                <div class="approve-button-detail md-button">Pause</div>
                                <div class="delete-button-detail md-button">Delete Recurring Email</div>
                            </div>    
                        </div>
                    </div>
                    <div class="top-subnav-detail">
                        <div class="top-subnav-tabs-detail">
                             <ul class="top-subnav-nav-elements-detail">
                                <li class="top-subnav-links-detail top-subnav-link-active-detail" id="recuringaction"> <a class="h3-subnav-active">Automation Details</a></li>
                                <li class="top-subnav-links-detail" id="recuringtemplate"> <a class="h3-subnav" >Saved Email</a></li>
                                <li class="top-subnav-links-detail  top-subnav-links-detail-last" id="recuringnote"> <a class="h3-subnav" >Notes</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--Below Nav-->
                <div id="recuringactiondiv">
                    <div class="below-nav-container-saved-post-detail">
                    <div class="inner-content-container-detail">
                            <div class="saved-post-header-detail">
                                <div class="h4 ng-hide" ng-show="schedule_desc === ''">Write Notes about this Action</div>                      
                                <div class="h4" ng-show="schedule_desc !== ''">Notes</div>
                                <div class="instruction-text" ng-show="schedule_desc !== ''"><p id="emaildescription62" class="ng-binding">{{schedule_desc}}</p></div>
                            </div>
                            <div class="inner-content-detail">
                                <div class="fields-note-detail5">

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
                                        <input type="text" id="fbnotemplate" value="No Template" class="input-field-textfield width75" readonly/>
                                        <input type="text" id="fbtemplatesaved" value="Template Saved" class="input-field-textfield width75 " readonly/>
                                    </div>
                                    <div class="half">
                                        <div class="input-header-actionDetail" style="">
                                            Marketing Program
                                        </div>
                                        <input type="text" id="Generalid" value="{{program_name}}" class="input-field-textfield width75" readonly/>

                                    </div>
                                </div>
                                <div class="input-header-actionDetail" style="">
                                    Date
                                </div>
                                <div class="input-field-textfield">
                                    Enter Date of Workshop 
                                </div>
                                <div class="input-header-actionDetail" style="">
                                    Time of Workshop
                                </div>
                                <div class="input-field-textfield">
                                    Enter Time of Workshop 
                                </div>
                                <div class="input-header-actionDetail" style="">
                                    Name of Workshop Instructor
                                </div>
                                <div class="input-field-textfield">
                                    Enter Name of Workshop Instructor
                                </div>
                                </div>
                            </div>



                                </div>
                            </div>
                </div>
                <div id="recuringtemplatediv">
                    <div class="below-nav-container-saved-post-detail">
                    <div class="inner-content-container-detail">
                        <div class="saved-post-preview-detail" id="noemailsdiv" >

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
                <div id="recuringnotediv">
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
                                    <textarea class="notes-container-textarea" id="twtnote">{{schedule_desc}}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
                <!--CTA Bar-->
                <div class="bottom-cta-bar5">
                    <div class="bottom-cta-button-container">

                             <div class="edit-button-detail md-button button-text-1 fleft ">Save Changes</div>
                    </div>
                </div>
                  </div>
        </div>
         
    
    </body>
</html>
