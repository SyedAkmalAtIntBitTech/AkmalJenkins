<%-- 
    Document   : facebookpreview
    Created on : 8 Dec, 2015, 7:32:55 PM
    Author     : Satyajit Roy
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="shortcut icon" href="favicon.png"/>
    <script src="js/popup.js"></script>
    <title>Facebook Preview</title>
</head>    
<%!
    SqlMethods sql_methods = new SqlMethods();
    int number;
    Integer user_id = 0;
%>
<%
    try {
        sql_methods.session = request.getSession();
        user_id = (Integer) sql_methods.session.getAttribute("UID");
    } catch (Exception e) {
        System.out.println(e.getCause());
        System.out.println(e.getMessage());
    } finally {
        sql_methods.closeConnection();
    }

%>
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
                <div class="top-navbar-inner-bb-detail">
                    <div class="top-navbar-title-container"><span class="h4 top-navbar-title"> Facebook Post Action Detail</span></div>
                    <div class="top-nav-cta-container">
                        <div class="approve-button-detail md-button" id="fbapprove" ng-show="facebook_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_facebook)">Approve to Send</div>
                        <div class="approve-button-detail md-button" id="fbapprove" ng-show="facebook_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_facebook)">Disapprove</div>
                        <div class="delete-button-detail md-button" ng-click="deleteSchedule(schedule_id,'delete', master_facebook)">Delete Action</div>
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
                    <div id="emptynoteheader" class="h4" >Write Notes about this Action</div>
                    <div id="notesavedheader" class="h4" >Notes</div>
                    <div class="instruction-text" ><p id="fbdescription">{{schedule_desc}}</p></div>
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
                                <input type="text" id="fbnotemplate" value="No Template" class="input-field-textfield width75" readonly/>
                                <input type="text" id="fbtemplatesaved" value="Template Saved" class="input-field-textfield width75 " readonly/>
                            </div>
                            <div class="half">
                                <div class="input-header-actionDetail" style="">
                                    Marketing Program
                                </div>
                                <input type="text" id="Generalid" value="{{programs.programdetails.programName}}" class="input-field-textfield width75" readonly/>
                                
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
<!--                            <div class="half">
                                <div class="input-header-actionDetail" style="">
                                  MANAGED PAGE
                                </div>
                                <input type="text" value="{{entitiesdetails.metadata.ManagedPage}}" class="input-field-textfield width75" readonly/>
                            </div>-->
                        </div>
                        <div class="inlineFlex">
                           
                            <div class="half" >
                                <div class="input-header-actionDetail half" style="">
                                   Day
                                </div>
                                <input type="text" class="input-field-textfield width75"  id="fbdays" name="fbdays" value="{{days}}"/>
                            </div>

                            <div class="half1">
                            <div class="input-header-actionDetail" style="">
                                TIME
                            </div>
                            <input id="timepickerfb" type="text" name="timepickerfb"  class="input-field-textfield width75" value="{{post_time| date:'hh : mm : a'}}"/> 
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
                
                </div>
                    <div class="inner-content-detail">
                        <div class="saved-post-preview-detail" id="nopostsaveddiv">

                            <!--SAVED Email GOES HERE-->

                            <div class="overlay-emptystate-icon">
                                <img type="image/svg+xml" src="images/Icons/Overlay_EmptyStates_Social-Empty-State.svg" class="overlay-emptystate-icon"/>
                            </div>
                            <div class="empty-state-container">
                                <div class="h2new textCenter textColor-Graynew">
                                    No post saved to this action.
                                </div>
                                 <a class="link" href="dashboard.jsp"><div class="md-button empty-state-cta">
                                   
                                        Go to Dashboard 
                                    
                                </div></a>
                            </div>
                        </div>
                        <div class="saved-post-preview-detail" id="savedpostdiv">

                            <!--SAVED Email GOES HERE-->
                            <div class="saved-post-header-detail" >
                                <div class="h4">Saved Facebook Post</div>
                                <div class="instruction-text">Saved post.</div>
                            <div class="Facebook-preview">
                                <div class="Facebook-preview-header">
                                    <div class="Facebook-preview-profpic"><img id="fb_preview_profpic" src="http://www.woomee.net/img/profile-blank.png"/></div>
                                    <div class="Facebook-preview-name-container">
                                        <div class="Facebook-preview-name">{{schedule_title}}</div>
                                    </div>
                                </div>
                                <div class="Facebook-preview-usercontent">{{entitiesdetails.metadata.post_text}}</div>
                                <div class="Facebook-link-container">
                                    <div class="Facebook-preview-image">
                                        <div ng-show="entitiesdetails.image_type == 'gallery'">
                                            <img id="prevfbimg" src='/BrndBot/DownloadImage?image_type=GALLERY&image_name={{entitiesdetails.image_name}}&user_id=<%= user_id %>'/>
                                        </div>
                                        <div ng-show="entitiesdetails.image_type == 'layout'">
                                            <img id="prevfbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                        </div>
                                        
                                       <!--<img id="prevfbimg" src='/BrndBot/DownloadImage?image_type=GALLERY&image_name={{entitiesdetails.image_name}}'/>-->
                                    </div>
                                    <div class="Facebook-preview-link-container">
                                        <div class="Facebook-preview-link-title">{{entitiesdetails.metadata.title}}</div>
                                        <div class="Facebook-preview-link-description">{{entitiesdetails.metadata.description}}</div>
                                        <div class="Facebook-preview-link-url">{{entitiesdetails.metadata.url}}</div>
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
                            <textarea class="notes-container-textarea" name="fb_description" id="fb_description">{{schedule_desc}}</textarea>
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
                <div class="edit-button-detail md-button button-text-1 fleft removebutton" ng-click="deleteSchedule(schedule_id,'remove',master_facebook)">Remove Saved Post</div>
            </div>
        </div>
        
        <div class="" id="fbnotesave">
            <div class="bottom-cta-button-container">
                <div class="edit-button-detail md-button button-text-1 fleft savebutton" ng-click="updateActionFacebookNote(schedule_id)">Save Notes</div>
            </div>
        </div>
         
        </div>
    </div>
</div>
</body>

