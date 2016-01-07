<%-- 
    Document   : programdetail
    Created on : Oct 14, 2015, 11:43:14 AM
    Author     : Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Email Program Action</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/programactions.css" rel="stylesheet" type="text/css"/>
        <link href="css/marketingactions.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/pikaday.css">
        <link rel="stylesheet" href="css/datepickerpikaday.css">
        <script src="http://momentjs.com/downloads/moment.min.js"></script>
        <script src="js/pikaday.js"></script>
        <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js" type="text/javascript"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <%!
            String program_id = "";
        %>
        <%
            program_id = request.getParameter("program_id");
        %>
        <script>
            var program = "";
            program = <%= program_id %>;
        </script>
        <script src="js/programactions.js"></script>
        <script>
        $(document).ready(function (){

                $("#delactbtn").hide();
                $("#delemlactbtn").hide();
                $("#saveprogdet").hide();
                $("#dateofevntedt").hide();
                $("#assoctdlnkedt").hide();
                $("#editprogdet").click(function (){
            var ok = confirm("Do you really want to Edit?");
            if (ok == true){
                $("#link_name").show();
                //$("#link_url").val("http://");
                $("#link_url").show();
                $("#editprogdet").hide();
                $("#dateofevntedt").show();
                $("#dateofevntprv").hide();
                $("#assoctdlnkprv").hide();
                $("#assoctdnameprv").hide();
                $("#associated_link").show();
                $("#associated_name").show();
                $("#assoctdlnkedt").show();
                $("#assoctdnameedt").show();
                $("#saveprogdet").show();
            }
            });
            });
            function checkUrl()
            {
                var url=$("#link_url").val();
                if(url.contains("http://"))
                {}
                else
                {
                    $("#link_url").val("http://");
                }
            }
            function overlay(){
                document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
                document.getElementById('slider-button').style.display = 'block';
                document.body.style.overflow = 'hidden';
            }
            function closeoverlay(){
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none';
                document.body.style.overflow = 'scroll';
            }
        </script>
        <style>
            #twremovedtemplate{display:none;}
            #twpreviewdecond{display:none;}
            #mailremovedtemplate{display:none;}
            #mailremovedtemplate6{display: none;}
            #fbremovedtemplate{display:none;}
            #mailpreviewdecond5{display:none;}
            #fbpreviewdecond{display:none;}
            #mailpreviewdecond{display:none;}
        </style>
        <jsp:include page="basejsp.jsp"/>
    </head>
    <body id="proghide" ng-app>
        <div class="row" ng-controller="programactions" style="display: none;">
            <div id="program_actions" class="row" ng-init="getProgramActions()">
                <div class="col-lg-1 col-md-1 col-sm-2">
                    <jsp:include page="leftmenu.html"/> 
                </div>
                <div id="fade" class="black_overlay"></div>
                <div class="col-md-11 col-sm-10 col-lg-11 col-lg-offset-2 col-md-offset-2">
                    <div class="row">
                        <div class="col-lg-7 col-md-7 col-sm-7">
                            <div class="markprog fontpns">{{programs.programdetails.programName}}</div>
                            <div id="editprogdet" class="edtprog fontpnr" ng-hide="programs.programdetails.program_status == 'Closed'">Edit Program Details</div>
                            <div id="saveprogdet" class="edtprog fontpnr" ng-click="updateUserProgram()">Save Program Details</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="endmrkprogbtndiv fontpnr">
                                <button type="button" class="endmrkprogbtn button 
                                        button--moema 
                                        button--text-thick 
                                        button--text-upper 
                                        fontpnr" ng-click="endMarketingProgram()">
                                    End Marketing Program
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="noofact fontpnr">Number of actions</div>
                            <div class="actno fontpns">{{programs.programdetails.noOfActions}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="dtofevnt fontpnr">Date of Event</div>
                            <div id="dateofevntprv" class="evntdt fontpns">{{programs.programdetails.dateOfEvent| date:'MMM dd yyyy'}}</div>
                            <div id="dateofevntedt" class="evntdt fontpns"><input type="text" readonly name="datepicker" id="progactdatepicker"  class="progactinputdate fontpns ptr" />                                        
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
                        </div>
                        <div id="associated_name" class="col-lg-3 col-md-3 col-sm-4" > 
                            <div class="asclink fontpnr">Associated Name</div>
                            <div id="assoctdlnkprv" class="evntdt fontpns">{{programs.programdetails.link_name}}</div>
                            <div id="assoctdlnkedt" class="evntdt  fontpns"><input id="link_name" name="link_name" class="progactinputdate fontpns ptr"/></div>
                        </div>
                        <div id="associated_link" class="col-lg-3 col-md-3 col-sm-4" style="display:none;margin-left: -90px;">
                            <div class="asclink fontpnr">Associated Link</div>
                            <div id="assoctdnameprv" class="evntdt fontpns">{{programs.programdetails.linktodestination}}</div>
                            <div id="assoctdnameedt" class="evntdt fontpns" style="display: none;"><input id="link_url" name="link_url" class="progactinputdate fontpns ptr" onkeyup="checkUrl();"/></div>
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-sm-12 ">
                            <div class="progdesc">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <div class=" offrpromo fontpnr" ng-bind-html-unsafe="programs.programdetails.description"></div>
                                        <!--                                     <div class=" promosubhead fontpnr">This is a subheader</div>-->
                                        <!--                                     <p class="prompara fontpnr">
                                                                             </p>-->
                                        <!--                                     <p class="helplink fontpnr">Link to help you out</p>-->
                                    </div>
                                </div>                                  
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="recuremlautom fontpns">Recurring Email Automation</div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="addemlautomdiv ">
                                <button type="button" 
                                        id="addemlactbtn" 
                                        class="addemlautombtn 
                                        button 
                                        button--moema 
                                        button--text-thick 
                                        button--text-upper 
                                        button--size-s 
                                        fontpnr"  
                                        ng-click="addEditRecuringAction('add',<%=program_id%>, '0')">
                                    Add Email Automation
                                </button>
                            </div>
                            <div class="delemlautomdiv  ">
                                <button type="button" id="delemlactbtn" class="delemlautombtn 
                                        button button--moema 
                                        button--text-thick 
                                        button--text-upper 
                                        button--size-s 

                                        fontpnr"  ng-click="deleteAutomationSchedule('0', 'deleteMultiple')">
                                    Delete Email Automation</button>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-lg-offset-1">
                            <div class="emlstats fontpnr">Status</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="emlautomline">   
                        </div>
                    </div>
                    <div class="row" ng-repeat="emailautomation in programs.emailautomation">
                        <div class="col-lg-1 col-md-1 col-sm-1 checkBox" >
                            <div class="emlchkbox"><input type="checkbox" 
                                                          ng-disabled="checkProgramStatus()" 
                                                          id="{{emailautomation.scheduledEntityListId}}" 
                                                          class="chckbox" 
                                                          onclick="setSelectedRecuringIds('{{emailautomation.scheduledEntityListId}}')" value="{{emailautomation.scheduledEntityListId}}" /></div>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                            <div class="listelem fontpnr">{{emailautomation.programTemplateName}}</div>
                            <div class="sublst fontpnr">Started on {{emailautomation.dateTime| date:'  d/M/yy'+' At '+'hh:mm a'}}&nbsp;&nbsp;&nbsp;&nbsp;{{emailautomation.emailRecuringTemplateName}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1">
                            <div class="stslst fontpnr">{{emailautomation.status}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <button type="button" class="emledtbtn button button--moema button--text-thick button--text-upper button--size-s  fontpnr" 
                                    ng-click="getRecuringMailDetails(emailautomation.scheduledEntityListId,
                                                                emailautomation.status,
                                                                emailautomation.dateTime,
                                                                emailautomation.actionType,
                                                                emailautomation.programTemplateName,
                                                                emailautomation.description,
                                                                emailautomation.postDateStatus)">Details
                            </button>
                            <!--                         <button type="button" 
                                                                ng-click="setEntityId(emailautomation.scheduledEntityListId, emailautomation.days)" 
                                                                class="emledtbtn button 
                                                                       button--moema button--text-thick 
                                                                       button--text-upper 
                                                                       button--size-s 
                                                                       fontpnr">programactions
                                                            Details
                                                        </button>-->
                                         
                        </div>
                        <hr class="emlautombtmline padlineleftreceml">
                    </div>

                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="otact fontpns">One Time Actions</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="addactdiv" >
                                <a href = "javascript:void(0)" onclick = "overlay();">
                                    <button id="liPriority" onclick = "overlay();" class="otaddactbtn  button button--moema button--text-thick button--text-upper button--size-s fontpnr" ng-click="ShowAddAction()">Add Action</button>
                                </a> 
                                <button id="delactbtn" type="button" class="otaddactbtn delactbtn widthdelactbtn button button--moema button--text-thick button--text-upper button--size-s fontpnr" ng-click="deleteSchedule('0', 'deleteMultiple')">Delete Action</button>
                            </div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="otactdt fontpnr">Date</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="otacttype fontpnr">Action Type</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="otactstats fontpnr">Status</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="otactline">
                        </div>
                    </div>
                    <div class="row" ng-repeat="programaction in programs.programactions">
                            
                        <div class="col-lg-1 col-md-1 col-sm-1"  >
                            <div class="emlchkbox"><input type="checkbox" ng-disabled="checkProgramStatus()" id="{{programaction.scheduledEntityListId}}" class="delchckbx" onclick="setSelectedIds('{{programaction.scheduledEntityListId}}')" value="{{programaction.scheduledEntityListId}}" /></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="listelem txtwidth fontpnr">{{programaction.programTemplateName}}</div>
                            <div class="otasublst fontpnr">Scheduled for {{programaction.postTime| date:'hh:mm a'}}</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="oatlstdt fontpnr">{{programaction.postDate| date:'MMM dd'}}</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="oatlstpost fontpnr" ng-show="programaction.actionType==master_facebook || programaction.actionType==master_twitter">{{programaction.actionType}} Post</div>
                            <div class="oatlstpost fontpnr" ng-show="programaction.actionType==master_note || programaction.actionType==master_email">{{programaction.actionType}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="oatlststat fontpnr">{{programaction.status}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1" >
                            <button type="button" class="emledtbtn button button--moema button--text-thick button--text-upper button--size-s  fontpnr" 
                                    ng-click="getScheduleDetails(programaction.scheduledEntityListId,
                                                                        programaction.status,
                                                                        programaction.postDate,
                                                                        programaction.actionType,
                                                                        programaction.programTemplateName,
                                                                        programaction.description,
                                                                        programaction.postTime,
                                                                        programaction.postDateStatus,
                                                                        programaction.days)">Details</button>
                        </div>
                        <div class="otactlstlinediv">
                            <hr class="otactlstline">
                        </div>
                    </div>
                    <!--                    <div class="row">
                                            <div class="col-lg-12 col-md-12 col-sm-12">
                                                <hr class="otactlstline">
                                            </div>
                                        </div>-->

                </div>


                <div id="dvPriorityDialog" class="pollSlider" style="z-index:1005;" >
                    <form class="form-horizontal" id="signfrm" >
                        <div class="addactcol">
                            <div class="inlineFlex">
                                <div class="pfont actfnt fontpnr">CREATE A NEW ACTION</div>
                            </div>
                            <div class="top18 fontpns tenpix headcolor">MARKETING PROGRAM</div>
                            <div class="topfive fontpnr">
                                <!--<textarea name="option" disabled id="marketing_program" value="0"  class="a0">General</textarea>-->
                                <select disabled id="marketing_program" name="option">
                                    <option value="0" class="a0">General</option>
                                    <option ng-repeat="row in marketprogram" class="a{{row.user_program_id}}" value="{{row.user_program_id}}">{{row.name}}</option>
                                </select>
                            </div>
                            <div class="top18 tenpix headcolor fontpns ">ACTION NAME</div>
                            <div><textarea class="addactiondetinput top8 fontpns" name="addactiontitle" id="addactiontitle" placeholder="Title here"></textarea></div>
                            <div class="top26 fontpns tenpix headcolor">ACTION TYPE</div>
                            <div class="topfive fontpnr">
                                <select id="actiontype" name="actiontype">
                                    <option value="0">Select</option>
                                    <option value="Facebook">Facebook Post</option>
                                    <option value="Twitter">Twitter Post</option>
                                    <option value="Email">Email</option>
                                </select>
                            </div>
                            <div class="top18 fontpns tenpix headcolor">DESCRIPTION</div>
                            <div class="topten">
                                <textarea class="addactiondesc fontpnr" name="description" id="description" placeholder="Description here"></textarea>
                            </div>
                            <!--                    <div class="toptweenty fontpns tenpix headcolor">STATUS</div>
                                                <div class="inlineFlex">
                                                    <div class="rightfive top11">
                                                        <div class="redDot"></div>
                                                    </div>
                                                    <div class="tenpix fontpnr topnine containe">INCOMPLETE</div>
                                                </div>-->
                            <div class="tenpix fontpns headcolor ">SCHEDULED TO POST ON</div>
                            <div class="inlineFlex">
                                <div class="half">
                                    <div class="containe fontpnr tenpix topten">
                                        Days
                                    </div>
                                    <div class="topsix">
                                        <input type="hidden" name="programdate" id="programdate" value="{{programs.programdetails.dateOfEvent| date:'yyyy-MM-dd HH:mm:ss Z'}}" placeholder="Enter days" class="inputday fontpns ptr" />
                                        <input type="text" name="days" id="days" value=" " placeholder="Enter days"  class="inputday fontpns ptr" />   
                                    </div>
                                </div>
                                <div class="half right100">
                                    <div class=" containe fontpnr tenpix topten">
                                        Time
                                    </div>
                                    <div class="topsix">
                                        <input id="timepicker1" type="text" name="timepicker1" class="inputtime fontpns ptr"  /> 
                                        <script src="js/timepicki.js" type="text/javascript"></script>
                                        <script>
                                                $('#timepicker1').timepicki({
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
                            <div class="topfourty" >
                                <button class="addactcrtbtn fontpns twlvpix button--moema createMargine
                                        button--text-thick 
                                        button--size-s" 
                                        ng-click="AddAction()">Create
                                </button>
                            </div>

                        </div>
                    </form>

                </div>

                <div id="preview" class="pollSlider" >
                    <div>
                        <div id="preview_email"  class="inlineFlex">
                            <div class="half">
                                <div class="borderright">
                                    <div class="firstcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">ACTION DETAILS</div>
                                            <div class="headdelete h5font fontpnr" ng-hide="programs.programdetails.program_status == 'Closed'"  ng-click="deleteSchedule(schedule_id, 'delete', master_email)" >DELETE ACTION</div>
                                        </div>
                                        <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>

                                        <div class="topten titlecontain sixtnpix fontpns">
                                            {{schedule_title}}
                                        </div>
                                        <div class="toptweentyone headcolor tenpix fontpns">
                                            DESCRIPTION
                                        </div>
                                        <div class="height75 scrolly topten">
                                            <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                        </div>
                                        <div class="inlineFlex toptweenty">
                                            <div class="half headcolor fontpns tenpix">
                                                STATUS
                                            </div>
                                            <div class="half headcolor fontpns tenpix">
                                                MARKETING PROGRAM
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div class="inlineFlex">
                                                    <div class="rightfive toptwo" id="emailred">
                                                        <div class="redDot"></div>
                                                    </div>
                                                    <div class="rightfive toptwo" id="emailgreen">
                                                        <div class="greenDot"></div>
                                                    </div>
                                                    <div class="containe tenpix fontpnr ">{{email_template_status}}</div>
                                                </div>
                                            </div>
                                            <div class="half containe fontpnr tenpix">

                                                {{programs.programdetails.programName}}
                                            </div>
                                        </div>
                                        <div class="topfourty headtitle pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="containe fontpnr tenpix">

                                            {{entities_selected_date| date:'MMM dd yyyy'}}
                                            {{post_time| date:'h :mma'}}

                                        </div>
                                        <div class="inlineFlex top120">
                                            <div class="rightthirty left5">
                                                <input type="button" ng-hide="programs.programdetails.program_status == 'Closed'"  ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc, programs.programdetails.programName,days)" value="Edit" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_button_post">
                                            </div>
                                            <div class="approve">
                                            <input type="button" value="Approve to Send" 
                                                   ng-show="email_action_status == true && email_template_status=='Template Saved'" 
                                                   ng-click="Approval(schedule_id, 'approved', master_email)"
                                                   class="button approvetopostbuttonwidthheightcolor 
                                                   buttonmargin button--moema  
                                                   button--text-thick  
                                                   button--text-upper 
                                                   fontpns" id="fb_approve_button_post">
                                        <button ng-click="SaveData();" ng-show="email_template_status == 'Approved'" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                        <button id="button1" ng-show="email_template_status == 'Approved'" ng-click="Approval(schedule_id, 'template_saved', master_email)" style="background-color: #e25b5b !important;color: white !important;display:none;margin-left:16px !important;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="half">
                                <div class="secondcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="mailpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'remove', master_email)">REMOVED SAVED POST</div>
                                    </div>
                                    <div class="toptweentyone headcolor tenpix fontpns">
                                        PREVIEW
                                    </div>
                                    <div id="mailremovedtemplate">
                                        <div class="rectangle">
                                            <div class="circle"></div>
                                        </div>
                                        <div class="rightthirty">
                                            <input ng-hide="programs.programdetails.program_status == 'Closed'" 
                                                   type="button" value="Create Post" 
                                                   ng-click="createPost()" 
                                                   class="button createPostbuttonwidthheightcolor 
                                                          buttonmargin button--moema  
                                                          button--text-thick  
                                                          button--text-upper 
                                                          fontpns" 
                                                   id="create_Post">
                                        </div>
                                    </div>
                                    <div id="mailpreviewdecond">
                                        <!--                                <div id="imgcontainer"  style="display:none;">
                                                                                    <img id="mailimgprev" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                                                                </div> entitiesdetails-->
                                        <!-- <div class="content"><!-- <div class="content"></div> -->
                                        <!--    <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />
                                        </div> -->
                                        <div class="topten"><!-- <div class="content"></div> -->
                                            <!--                                <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />-->
                                            <div class="content">
                                                <img id="mailimgprev" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                            </div>
                                        </div>
                                        <div class="top27 headcolor tenpix fontpns">
                                            SUBJECT LINE
                                        </div>
                                        <div class="containe fontpnr tenpix">
                                            {{entitiesdetails.subject}}
                                        </div>
                                        <div class="inlineFlex toptweenty">
                                            <div class="half headcolor fontpns tenpix">
                                                TO EMAIL LIST
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div ng-show="entitiesdetails.email_list_name == 1">
                                                    No Email List
                                                </div>
                                                <div ng-show="entitiesdetails.email_list_name != 1">
                                                    {{entitiesdetails.email_list_name}}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptweenty">
                                            <div class="half headcolor fontpns tenpix">
                                                FROM NAME
                                            </div>
                                            <div class="half headcolor fontpns tenpix">
                                                FROM EMAIl ADDRESS
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                {{entitiesdetails.from_name}}
                                            </div>
                                            <div class="half containe fontpnr tenpix">
                                                {{entitiesdetails.from_address}}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="edit_email">
                            <div  class="inlineFlex">
                                <div class="half">
                                    <div class="borderright" style="height: 578px;">
                                        <div class="firstcol">
                                            <div class="inlineFlex">
                                                <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                                <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'delete', master_email)" >DELETE ACTION</div>
                                            </div>
                                            <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>
                                            <div class="topten titlecontain sixtnpix fontpns">
                                                <textarea id="email_edit_title" name="email_edit_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                                <input class="inputbox SP1" type="hidden" name="email_scheduleid" id="email_scheduleid" value='{{schedule_id}}' />
                                                <input class="inputbox SP1" type="hidden" name="email_schedule_type" id="email_schedule_type" value='{{schedule_type}}'/>
                                            </div>
                                            <div class="top12 headcolor tenpix fontpns">
                                                DESCRIPTION
                                            </div>
                                            <div class="height75 topten">
                                                <p class="containe twlvpix fontpnr">
                                                    <textarea class="actiondetdesc" id="email_description" name="email_description">{{schedule_desc}}</textarea></p>
                                            </div>
                                            <div class="inlineFlex topten">
                                                <div class="half headcolor fontpns tenpix topten">
                                                    STATUS
                                                </div>
                                                <div class="half headcolor fontpns tenpix topten">
                                                    MARKETING PROGRAM
                                                </div>
                                            </div>
                                            <div class="inlineFlex toptnine">
                                                <div class="half containe fontpnr tenpix">
                                                    <div class="inlineFlex" id="mailnotemplate1">
                                                        <div class="rightfive toptwo">
                                                            <div class="redDot"></div>
                                                        </div>
                                                        <div class="containe tenpix fontpnr ">No Template</div>
                                                    </div>
                                                    <div class="inlineFlex" id="mailtemplatesaved1">
                                                        <div class="rightfive toptwo">
                                                            <div class="greenDot"></div>
                                                        </div>
                                                        <div class="containe tenpix fontpnr ">Template Saved</div>
                                                    </div>
                                                </div>
                                                <div class="half containe fontpnr tenpix topten">
                                                    {{programs.programdetails.programName}}
                                                </div>
                                            </div>
                                            <div class="topthirty8 pfont actfnt fontpnr">
                                                POSTING DETAILS
                                            </div>
                                            <div class="topnineteen tenpix fontpns postto">  
                                                POSTING TO
                                            </div>
                                            <div class="containe fontpnr twlvpix topten">
                                                {{schedule_type}}
                                            </div>
                                            <div class="toptweenty headcolor tenpix fontpns">
                                                SCHEDULED TO POST ON
                                            </div>
                                            <div class="inlineFlex">
                                                <div class="half" ng-show="user_marketing_program_id > 0">
                                                    <div class=" containe fontpnr tenpix topten">
                                                        Day
                                                    </div>
                                                    <div class="topsix">
                                                        <input type="hidden" readonly  name="emaildatetime" id="emaildatetime"  class="inputdate MH1 ptr" value="Sun Jan 01 1970">                                        
                                                        <input type="text"id="emaildays" name="emaildays" value="{{days}}" class="textbox">
                                                    </div>
                                                </div>
                                                <div class="half" ng-show="user_marketing_program_id == 0">
                                                    <div class=" containe fontpnr tenpix topten">
                                                        DATE
                                                    </div>
                                                    <div class="topsix">
                                                        <input type="hidden" readonly  name="emaildatetime" id="emaildatetime"  class="inputdate MH1 ptr" value="Sun Jan 01 1970">                                        
                                                        <input type="text"id="emaildays" name="emaildays" value="{{days}}" class="textbox">
                                                    </div>
                                                </div>
                                                <div class="half">
                                                    <div class=" containe fontpnr tenpix topten">
                                                        Time
                                                    </div>
                                                    <div class="topsix">
                                                        <input id="timepickeremail" type="text" name="timepickeremail" class="inputtime ptr" style="width:150px;" value="{{post_time| date:'hh : mm : a'}}"/> 
                                                        <script src="js/timepicki.js" type="text/javascript"></script>
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
                                            <div class="topthirty8 left5">
                                                <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns"  ng-click="updateActionEmail()" >Save</button>                                       
                                                <!--                                            <button ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> -->
                                                <button id="button1"  style="background-color: #e25b5b !important;color: white !important;display:none;margin-left:16px !important;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="half">
                                    <div class="secondcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                            <div id="maileditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'remove', master_email)">REMOVED SAVED POST</div>
                                        </div>
                                        <div class="toptweentyone headcolor tenpix fontpns">
                                            PREVIEW
                                        </div>
                                        <div id="mailremovedtemplate2">
                                            <div class="rectangle">
                                                <div class="circle"></div>
                                            </div>
                                            <div class="rightthirty">
                                                <input type="button" value="Create Post" ng-click="createPost()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                            </div>
                                        </div>
                                        <div id="mailpreviewdecond2">
                                            <div class="topten"><!-- <div class="content"></div> -->
                                                <!--                                    <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />-->
                                                <div class="content">
                                                    <img id="mailimgprev" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                                </div>
                                            </div>
                                            <div class="top27 headcolor tenpix fontpns">
                                                SUBJECT LINE
                                            </div>
                                            <div class="containe fontpnr tenpix">
                                                {{entitiesdetails.subject}}
                                            </div>
                                            <div class="inlineFlex toptweenty">
                                                <div class="half headcolor fontpns tenpix">
                                                    TO EMAIL LIST
                                                </div>
                                            </div>
                                            <div class="inlineFlex toptnine">
                                                <div class="half containe fontpnr tenpix">
                                                    <div ng-show="entitiesdetails.email_list_name == 1">
                                                        No Email List
                                                    </div>
                                                    <div ng-show="entitiesdetails.email_list_name != 1">
                                                        {{entitiesdetails.email_list_name}}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="inlineFlex toptweenty">
                                                <div class="half headcolor fontpns tenpix">
                                                    FROM NAME
                                                </div>
                                                <div class="half headcolor fontpns tenpix">
                                                    FROM EMAIl ADDRESS
                                                </div>
                                            </div>
                                            <div class="inlineFlex toptnine">
                                                <div class="half containe fontpnr tenpix">
                                                    {{entitiesdetails.from_name}}
                                                </div>
                                                <div class="half containe fontpnr tenpix">
                                                    {{entitiesdetails.from_address}}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div id="previewfb" class="pollSlider">
                    <div id="preview_facebook" class="inlineFlex">
                        <div class="half">
                            <div class="borderright">
                                <div class="firstcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">ACTION DETAILS</div>
                                        <div class="headdelete h5font fontpnr" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="deleteSchedule(schedule_id, 'delete', master_facebook)" >DELETE ACTION</div>
                                    </div>
                                    <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>
                                    <div class="topten titlecontain sixtnpix fontpns">
                                        {{schedule_title}}
                                    </div>
                                    <div class="toptweentyone headcolor tenpix fontpns">
                                        DESCRIPTION
                                    </div>
                                    <div class="height75 scrolly topten">
                                        <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                    </div>
                                    <div class="inlineFlex toptweenty">
                                        <div class="half headcolor fontpns tenpix">
                                            STATUS
                                        </div>
                                        <div class="half headcolor fontpns tenpix">
                                            MARKETING PROGRAM
                                        </div>
                                    </div>
                                    <div class="inlineFlex toptnine">
                                        <div class="half containe fontpnr tenpix">
                                            <div class="inlineFlex" id="fbnotemplate">
                                                <div class="rightfive toptwo">
                                                    <div class="redDot"></div>
                                                </div>
                                                <div class="containe tenpix fontpnr ">No Template</div>
                                            </div>
                                            <div class="inlineFlex" id="fbtemplatesaved">
                                                <div class="rightfive toptwo">
                                                    <div class="greenDot"></div>
                                                </div>
                                                <div class="containe tenpix fontpnr ">Template Saved</div>
                                            </div>
                                        </div>
                                        <div class="half containe fontpnr tenpix">
                                            {{programs.programdetails.programName}}
                                        </div>
                                    </div>
                                    <div class="topfourty headtitle pfont actfnt fontpnr">
                                        POSTING DETAILS
                                    </div>
                                    <div class="inlineFlex topnineteen">
                                        <div class="half headcolor fontpns tenpix">
                                            POSTING TO
                                        </div>
                                        <div class="half headcolor fontpns tenpix">
                                            MANAGED PAGE
                                        </div>
                                    </div>
                                    <div class="inlineFlex toptnine">
                                        <div class="half containe fontpnr tenpix">
                                            {{schedule_type}}
                                        </div>
                                        <div class="half containe fontpnr tenpix">
                                            {{entitiesdetails.metadata.ManagedPage}}
                                        </div>
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        SCHEDULED TO POST ON
                                    </div>
                                    <div class="containe fontpnr tenpix">
                                        {{entities_selected_date| date:'MMM dd yyyy'}}
                                        {{post_time| date:'hh:mm a'}}
                                    </div>
                                    <div class="inlineFlex topsixeight">
                                        <div class="rightthirty left5">
                                            <input type="button" value="Edit" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc,programs.programdetails.programName, days)" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="fb_button_post">
                                        </div>
                                        <div class="approve">
                                            
                                            <input type="button" value="Approve to Post" 
                                                   ng-show="facebook_action_status == true && facebook_template_status=='Template Saved'" 
                                                   ng-click="Approval(schedule_id, 'approved', master_facebook)"
                                                   class="button approvetopostbuttonwidthheightcolor 
                                                   buttonmargin button--moema  
                                                   button--text-thick  
                                                   button--text-upper 
                                                   fontpns" id="fb_approve_button_post">
                                        </div>
<!--                                        <button ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> -->
                                        <button ng-click="SaveData();" ng-show="facebook_template_status == 'Approved'" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                        <button id="button1" ng-show="facebook_template_status == 'Approved'" ng-click="Approval(schedule_id, 'template_saved', master_facebook)" style="background-color: #e25b5b !important;color: white !important;display:none;margin-left:16px !important;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 
                                         
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="half">
                            <div class="secondcol">
                                <div class="inlineFlex">
                                    <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                    <div id="fbpreviewremove" 
                                         class="headdelete h5font fontpnr" 
                                         ng-click="deleteSchedule(schedule_id, 'remove',master_facebook)">
                                        REMOVED SAVED POST</div>
                                </div>
                                <div class="toptweentyone headcolor tenpix fontpns">
                                    PREVIEW
                                </div>
                                <div id="fbremovedtemplate">
                                    <div class="rectangle">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="rightthirty left82">
                                        <input type="button" value="Create Post" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="createPost()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                    </div>
                                </div>
                                <div id="fbpreviewdecond">
                                    <!--                                        <div class="topten">
                                                                                <img id="fbimgprev" class="fbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />   {{entitiesdetails.image_name}}  
                                                                            </div>-->
                                    <div id="imgcontainer">
                                        <img id="prevfbimg" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        POST TEXT
                                    </div>
                                    <div class="height75 scrolly topten">
                                        <p class="containe twlvpix fontpnr">{{entitiesdetails.metadata.post_text}}</p>
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        LINK TITLE
                                    </div>
                                    <div class="topten containe fontpnr twlvpix">
                                        {{entitiesdetails.metadata.title}}
                                    </div>
                                    <div class="toptweentyfive headcolor tenpix fontpns">
                                        LINK DESCRIPTION
                                    </div>
                                    <div class="topten containe fontpnr twlvpix">
                                        {{entitiesdetails.metadata.description}}
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        LINK URL NAME
                                    </div>
                                    <div class="topten containe fontpnr twlvpix">
                                        {{entitiesdetails.metadata.url}}
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div id="edit_facebook">
                        <div class="inlineFlex">
                            <div class="half">
                                <div class="borderright" style="height: 578px;">
                                    <div class="firstcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'delete', master_facebook)" >DELETE ACTION</div>
                                        </div>
                                        <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>
                                        <div class="topten titlecontain sixtnpix fontpns">
                                            <textarea id="fb_action_title" name="fb_action_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                            <input class="inputbox SP1" type="hidden" name="fb_scheduletype" id="fb_scheduletype" value='{{schedule_type}}'/>
                                            <input class="inputbox SP1" type="hidden" name="fb_scheduleid" id="fb_scheduleid" value='{{schedule_id}}'/>
                                        </div>
                                        <div class="top12 headcolor tenpix fontpns">
                                            DESCRIPTION
                                        </div>
                                        <div class="height75 topten">
                                            <p class="containe twlvpix fontpnr">
                                                <textarea name="fb_description" id="fb_description" class="actiondetdesc">{{schedule_desc}}</textarea></p>
                                        </div>
                                        <div class="inlineFlex topten">
                                            <div class="half headcolor fontpns tenpix topten">
                                                STATUS
                                            </div>
                                            <div class="half headcolor fontpns tenpix topten">
                                                MARKETING PROGRAM
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div class="inlineFlex" id="fbnotemplate1">
                                                    <div class="rightfive toptwo">
                                                        <div class="redDot"></div>
                                                    </div>
                                                    <div class="containe tenpix fontpnr ">No Template</div>
                                                </div>
                                                <div class="inlineFlex" id="fbtemplatesaved1">
                                                    <div class="rightfive toptwo">
                                                        <div class="greenDot"></div>
                                                    </div>
                                                    <div class="containe tenpix fontpnr ">Template Saved</div>
                                                </div>
                                            </div>
                                            <div class="half containe fontpnr tenpix topten">
                                                {{programs.programdetails.programName}}
                                            </div>
                                        </div>
                                        <div class="topthirty8 pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class="inlineFlex topten">
                                            <div class="half headcolor fontpns tenpix topten">
                                                POSTING TO
                                            </div>
                                            <div class="half headcolor fontpns tenpix topten">
                                                MANAGED PAGE
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div class="inlineFlex">
                                                    <div class=" tenpix fontpnr topten">{{schedule_type}}</div>
                                                </div>
                                            </div>
                                            <div class="half containe fontpnr tenpix topten">
                                                {{entitiesdetails.metadata.ManagedPage}}
                                            </div>
                                        </div>

                                        <div class="toptweenty headcolor tenpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="inlineFlex">
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Day
                                                </div>
                                                <div class="topsix">
                                                   <input type="text" value="{{days}}" name="fbdays" id="fbdays" class="textbox">                                              
                                                </div>
                                            </div>
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Time
                                                </div>
                                                <div class="topsix">
                                                    <input id="timepickerfb" type="text" name="timepickerfb" class="inputtime ptr" style="width:150px;" value="{{post_time| date:'hh : mm : a'}}"/> 
                                                    <script src="js/timepicki.js" type="text/javascript"></script>
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
                                        <div class="topthirty8 left5">
                                            <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns" ng-click="updateActionFacebook()" >Save</button>                                       
                                            <!--                                        <button ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> -->
                                            <button id="button1"  style="background-color: #e25b5b !important;color: white !important;display:none;margin-left:16px !important;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="half">
                                <div class="secondcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="fbeditremove" class="headdelete h5font fontpnr" ng-hide="programs.programdetails.program_status == 'Closed'"   ng-click="deleteSchedule(schedule_id, 'remove', master_facebook)">REMOVED SAVED POST</div>
                                    </div>
                                    <div class="toptweentyone headcolor tenpix fontpns">
                                        PREVIEW
                                    </div>
                                    <div id="fbremovedtemplate2">
                                        <div class="rectangle">
                                            <div class="circle"></div>
                                        </div>
                                        <div class="rightthirty left82">
                                            <input type="button" value="Create Post" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="createPost()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                        </div>
                                    </div>
                                    <div id="fbpreviewdecond2">
                                        <!--                                        <div class="topten">
                                                                                    <img id="fbimgprev" class="fbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />   {{entitiesdetails.image_name}}  
                                                                                </div>-->
                                        <div id="imgcontainer">
                                            <img id="prevfbimg" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            POST TEXT
                                        </div>
                                        <div class="height75 scrolly topten">
                                            <p class="containe twlvpix fontpnr">{{entitiesdetails.metadata.post_text}}</p>
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            LINK TITLE
                                        </div>
                                        <div class="topten containe fontpnr twlvpix">
                                            {{entitiesdetails.metadata.title}}
                                        </div>
                                        <div class="toptweentyfive headcolor tenpix fontpns">
                                            LINK DESCRIPTION
                                        </div>
                                        <div class="topten containe fontpnr twlvpix">
                                            {{entitiesdetails.metadata.description}}
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            LINK URL NAME
                                        </div>
                                        <div class="topten containe fontpnr twlvpix">
                                            {{entitiesdetails.metadata.url}}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="imgcontainer" style="display:none">
                            <img style="display:none" id="edtfbimg"/> 
                        </div>      
                    </div>                        
                </div>

                <div id="previewtwitter" class="pollSlider">
                    <div id="preview_twitter" class="inlineFlex">
                        <div class="half">
                            <div class="borderright">
                                <div class="firstcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">ACTION DETAILS</div>
                                        <div class="headdelete h5font fontpnr" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="deleteSchedule(schedule_id, 'delete', master_facebook)">DELETE ACTION</div>
                                    </div>
                                    <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>
                                    <div class="topten titlecontain sixtnpix fontpns">
                                        {{schedule_title}}
                                    </div>
                                    <div class="toptweentyone headcolor tenpix fontpns">
                                        DESCRIPTION
                                    </div>
                                    <div class="height75 scrolly topten">
                                        <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                    </div>
                                    <div id="imgcontainer" style="display: none">
                                        <img id="prevtwtimg" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                    </div>
                                    <div class="inlineFlex toptweenty">
                                        <div class="half headcolor fontpns tenpix">
                                            STATUS
                                        </div>
                                        <div class="half headcolor fontpns tenpix">
                                            MARKETING PROGRAM
                                        </div>
                                    </div>
                                    <div class="inlineFlex toptnine">
                                        <div class="half containe fontpnr tenpix">

                                            <div class="inlineFlex" id="twnotemplate">
                                                <div class="rightfive toptwo">
                                                    <div class="redDot"></div>
                                                </div>
                                                <div class="containe tenpix fontpnr ">No Template</div>
                                            </div>
                                            <div class="inlineFlex" id="twtemplatesaved">
                                                <div class="rightfive toptwo">
                                                    <div class="greenDot"></div>
                                                </div>
                                                <div class="containe tenpix fontpnr ">Template Saved</div>
                                            </div>
                                        </div>
                                        <div class="half containe fontpnr tenpix">
                                            {{programs.programdetails.programName}}
                                        </div>
                                    </div>
                                    <div class="topfourty headtitle pfont actfnt fontpnr">
                                        POSTING DETAILS
                                    </div>
                                    <div class="topnineteen headcolor tenpix fontpns">
                                        POSTING TO
                                    </div>
                                    <div class="containe fontpnr tenpix">
                                        {{schedule_type}}
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        SCHEDULED TO POST ON
                                    </div>
                                    <div class="containe fontpnr tenpix">
                                        {{entities_selected_date| date:'MMM dd yyyy'}}
                                        {{post_time| date:'hh:mm a'}}
                                    </div>
                                    <div class="inlineFlex topsixeight">
                                        <div class="rightthirty left5">
                                            <input type="button" ng-hide="programs.programdetails.program_status == 'Closed'" value="Edit" ng-click="editScheduleDetails(
                                                                                schedule_id,
                                                                                entities_selected_time,
                                                                                schedule_type,
                                                                                schedule_title,
                                                                                schedule_desc,
                                                                                programs.programdetails.programName,
                                                                                days)"  class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="twitter_button_post">
                                        </div>
                                        <div class="approve">
                                            <input type="button" 
                                                   ng-show="twitter_action_status == true && twitter_template_status=='Template Saved'" 
                                                   ng-click="Approval(schedule_id, 'approved', master_twitter)"
                                                   value="Approve to Post" 
                                                   class="button approvetopostbuttonwidthheightcolor 
                                                          buttonmargin 
                                                          button--moema  
                                                          button--text-thick  
                                                          button--text-upper fontpns" 
                                                   id="ftwitter_approve_button_post">
                                         
                                                <button ng-click="SaveData();" ng-show="twitter_template_status == 'Approved'" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                                <button id="button1" ng-show="twitter_template_status == 'Approved'" ng-click="Approval(schedule_id, 'template_saved', master_twitter)" style="background-color: #e25b5b !important;color: white !important;display:none;margin-left:16px !important;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="half">
                            <div class="secondcol">
                                <div class="inlineFlex">
                                    <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                    <div id="twpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'remove', master_twitter)">REMOVED SAVED POST</div>
                                </div>
                                <div class="toptweentyone headcolor tenpix fontpns">
                                    PREVIEW
                                </div>
                                <div id="twremovedtemplate">
                                    <div class="rectangle">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="rightthirty left82">
                                        <input type="button" ng-hide="programs.programdetails.program_status == 'Closed'" value="Create Post" ng-click="createPost()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                    </div>
                                </div>
                                <div id="twpreviewdecond">
                                    <div class="topten">
                                        <!--    <img id="prevtwtimg" class="twtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                          20150907175706740.png-->
                                        <div id="imgcontainer">
                                            <img id="prevtwtimg" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                        </div>
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        POST TEXT
                                    </div>
                                    <div class="height75 scrolly topten">
                                        <p class="containe twlvpix fontpnr">{{entitiesdetails.metadata.text}}</p>
                                    </div>
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        LINK NAME
                                    </div>
                                    <div class="topten containe fontpnr twlvpix">
                                        {{entitiesdetails.metadata.shorturl}}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="edit_twitter"> 
                        <div class="inlineFlex">

                            <div class="half">
                                <div class="borderright" style="height: 578px;">
                                    <div class="firstcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'delete', master_twitter)" >DELETE ACTION</div>
                                        </div>
                                        <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>
                                        <div class="topten titlecontain sixtnpix fontpns">
                                            <textarea id="edit_twitter_title" name="edit_twitter_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                            <input class="inputbox SP1" type="hidden" name="twitter_scheduleid" id="twitter_scheduleid" value='{{schedule_id}}'/>
                                            <input class="inputbox SP1" type="hidden" name="twitter_action_type" id="twitter_action_type" value='{{schedule_type}}'/>
                                        </div>
                                        <div class="top12 headcolor tenpix fontpns">
                                            DESCRIPTION
                                        </div>
                                        <div class="height75 topten">
                                            <p class="containe twlvpix fontpnr">
                                                <textarea cols="28" rows="2" name="twitter_description" class="actiondetdesc" id="twitter_description">{{schedule_desc}}</textarea>
                                        </div>
                                        <div class="inlineFlex topten">
                                            <div class="half headcolor fontpns tenpix topten">
                                                STATUS
                                            </div>
                                            <div class="half headcolor fontpns tenpix topten">
                                                MARKETING PROGRAM
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div class="inlineFlex" id="twnotemplate1">
                                                    <div class="rightfive toptwo">
                                                        <div class="redDot"></div>
                                                    </div>
                                                    <div class="containe tenpix fontpnr ">No Template</div>
                                                </div>
                                                <div class="inlineFlex" id="twtemplatesaved1">
                                                    <div class="rightfive toptwo">
                                                        <div class="greenDot"></div>
                                                    </div>
                                                    <div class="containe tenpix fontpnr ">Template Saved</div>
                                                </div>
                                            </div>
                                            <div class="half containe fontpnr tenpix topten">
                                                {{programs.programdetails.programName}}
                                            </div>
                                        </div>
                                        <div class="topthirty8 pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class=" headcolor fontpns tenpix top19">
                                            POSTING TO
                                        </div>
                                        <div class=" tenpix fontpnr topnine">
                                            {{schedule_typedays}}
                                        </div>

                                        <div class="toptweenty headcolor tenpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="inlineFlex">
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Day
                                                </div>
                                                <div class="topsix">
                                                    <input type="text" value="{{days}}" name="twdays" id="twdays" class="textbox">                                                   
                                                </div>
                                            </div>
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Time
                                                </div>
                                                <div class="topsix">
                                                    <input id="timepickertw" type="text" name="timepickertw" class="inputtime ptr" style="width:150px;" value="{{post_time| date:'hh : mm : a'}}"/> <!-- id="timepickertwitter" name="timepicker1" -->

                                                    <script src="js/timepicki.js" type="text/javascript"></script>
                                                    <script>
                                                        $('#timepickertw').timepicki({
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
                                        <div class="topthirty7 left5">
                                            <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns" ng-click="updateActionTwitter()">Save</button>                                       
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="half">
                                <div class="secondcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="tweditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'remove', master_twitter)">REMOVED SAVED POST</div>
                                    </div>
                                    <div class="toptweentyone headcolor tenpix fontpns">
                                        PREVIEW
                                    </div>
                                    <div id="twremovedtemplate2">
                                        <div class="rectangle">
                                            <div class="circle"></div>
                                        </div>
                                        <div class="rightthirty left82">
                                            <input type="button" value="Create Post" ng-click="createPost()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                        </div>
                                    </div>
                                    <div id="twpreviewdecond2">
                                        <div class="topten">
                                            <!--    <img id="prevtwtimg" class="twtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                              20150907175706740.png-->
                                            <div id="imgcontainer">
                                                <img id="prevtwtimg" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                            </div>
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            POST TEXT
                                        </div>
                                        <div class="height75 scrolly topten">
                                            <p class="containe twlvpix fontpnr">{{entitiesdetails.metadata.text}}</p>
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            LINK NAME
                                        </div>
                                        <div class="topten containe fontpnr twlvpix">
                                            {{entitiesdetails.metadata.shorturl}}
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div id="imgcontainer" style="display:none;">
                        <img id="edttwtimg" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' 
                             style="display:none;"/>
                    </div> 
                </div>

                <div id="recuring_preview">

                    <div id="recuring_preview_email">
                        <div class="inlineFlex">
                            <div class="half">
                                <div class="borderright">
                                    <div class="firstcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">ACTION DETAILS</div>
                                            <div class="headdelete h5font fontpnr" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="deleteSchedule(schedule_id, 'delete', master_email, 'true')" >DELETE ACTION</div>
                                        </div>
                                        <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>

                                        <div class="topten titlecontain sixtnpix fontpns">
                                            {{schedule_title}}
                                        </div>
                                        <div class="toptweentyone headcolor tenpix fontpns">
                                            DESCRIPTION
                                        </div>
                                        <div class="height75 scrolly topten">
                                            <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                        </div>
                                        <div class="inlineFlex toptweenty">
                                            <div class="half headcolor fontpns tenpix">
                                                STATUS
                                            </div>
                                            <div class="half headcolor fontpns tenpix">
                                                MARKETING PROGRAM
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div class="inlineFlex">
                                                    <div class="rightfive toptwo" id="recuringemailred">
                                                        <div class="redDot"></div>
                                                    </div>
                                                    <div class="rightfive toptwo" id="recuringemailgreen">
                                                        <div class="greenDot"></div>
                                                    </div>
                                                    <div class="containe tenpix fontpnr ">{{email_template_status}}</div>
                                                </div>
                                            </div>
                                            <div class="half containe fontpnr tenpix">
                                                {{programs.programdetails.programName}}
                                            </div>
                                        </div>
                                        <div class="topfourty headtitle pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="containe fontpnr tenpix">
                                            {{entities_selected_time| date:'MMM dd yyyy'+' on '+ 'hh : mm a'}}
                                        </div>
                                        <div class="inlineFlex top120">
                                            <div class="rightthirty left5">
                                                <input type="button" ng-hide="programs.programdetails.program_status == 'Closed'" ng-click="addEditRecuringAction('edit',<%=program_id%>, schedule_id)" value="Edit" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_button_post">
                                            </div>
                                            <div class="approve">
                                                <input type="button" value="Approve to Send" ng-show="recuring_action_status == true && recuring_template_status=='Template Saved'" ng-click="recuringApproval(schedule_id, 'approved')" class="button approvetopostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_approve_button_post">
                                                <button ng-click="SaveData();" ng-show="recuring_template_status == 'Approved'" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                                <button id="button1" ng-show="recuring_template_status == 'Approved'" ng-click="recuringApproval(schedule_id, 'template_saved')" style="background-color: #e25b5b !important;color: white !important;display:none;margin-left:16px !important;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="half">
                                <div class="secondcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="mailpreviewremove6" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'remove', master_email, 'true')">REMOVED SAVED POST</div>
                                    </div>
                                    <div class="toptweentyone headcolor tenpix fontpns">
                                        PREVIEW
                                    </div>
                                    <div id="mailremovedtemplate6">
                                        <div class="rectangle">
                                            <div class="circle"></div>
                                        </div>
                                        <div class="rightthirty">
                                            <input type="button" value="Create Post" ng-hide="programs.programdetails.program_status == 'Closed'"  ng-click="addEditRecuringAction('template',<%=program_id%>, schedule_id)"  class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                        </div>
                                    </div>
                                    <div id="mailpreviewdecond5">
                                        <!--                                <div id="imgcontainer"  style="display:none;">
                                                                                    <img id="mailimgprev" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                                                                </div> entitiesdetails-->
                                        <!-- <div class="content"><!-- <div class="content"></div> -->
                                        <!--    <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />
                                        </div> -->
                                        <div class="topten"><!-- <div class="content"></div> -->
                                            <!--                                <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />-->
                                            <div class="content">
                                                <img id="mailimgprev" ng-src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                            </div>
                                        </div>
                                        <div class="top27 headcolor tenpix fontpns">
                                            SUBJECT LINE
                                        </div>
                                        <div class="containe fontpnr tenpix">
                                            {{entitiesdetails.subject}}
                                        </div>
                                        <div class="inlineFlex toptweenty">
                                            <div class="half headcolor fontpns tenpix">
                                                TO EMAIL LIST
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                <div ng-show="entitiesdetails.email_list_name == 1">
                                                    No Email List
                                                </div>
                                                <div ng-show="entitiesdetails.email_list_name != 1">
                                                    {{entitiesdetails.email_list_name}}
                                                </div>
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptweenty">
                                            <div class="half headcolor fontpns tenpix">
                                                FROM NAME
                                            </div>
                                            <div class="half headcolor fontpns tenpix">
                                                FROM EMAIl ADDRESS
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                {{entitiesdetails.from_name}}
                                            </div>
                                            <div class="half containe fontpnr tenpix">
                                                {{entitiesdetails.from_address}}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div id="light" class="white_content closepopup">
                    <a href = "javascript:void(0)" style="text-decoration:none;">
                        <div id="slider-button" style="font-size:40px;text-align:center;z-index:1006;display:none;">
                            <p style="margin-top:-7px;"><img src="images/CloseIcon.svg" height="25" width="25" /></p>
                        </div>
                    </a>
                </div>

            </div>
        </div>
    </body>
</html>