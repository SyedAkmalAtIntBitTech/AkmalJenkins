<%-- 
    Document   : history
    Created on : Aug 25, 2015, 6:46:46 PM
    Author     : Syed Akmal at IntBit Technologies
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <link rel="stylesheet" href="css/pikaday.css">
        <link rel="stylesheet" href="css/datepickerpikaday.css">
        <script src="js/pikaday.js"></script>
        <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        <script type="text/javascript" src="js/angular.min.js"></script>-->
        <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <jsp:include page="basejsp.jsp" />
        <%@ include file="checksession.jsp" %>

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://momentjs.com/downloads/moment.min.js"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="css/main1.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/facebook.css" type="text/css"/>       
        <title>BrndBot - Your Plan</title>

        <script>
                 
            function cancelform()
            {   
//                var chng=false;
//                if(chng)
                $("#signform").change(function() {
//                    alert("changed");
                })
        //    $("#"+cancelbtn).click(function(){
                if(confirm("Do you want to cancel the process?")){
                    $('#slider-button').click();

                }
        //    })
            }
            
        </script>
        <style>
            /*#fbpreviewdecond2{display:none;}*/
            #fbpreviewdecond{display:none;}
            #fbremovedtemplate{display:none;}
            #twpreviewdecond{display:none;}
            #twremovedtemplate{display:none;}
            #mailpreviewdecond5{display:none;}
            #mailremovedtemplate{display:none;}
            #mailremovedtemplate6{display:none;}
            #mailpreviewdecond{display:none;}
            #twpreviewdecond{display:none;}
            
            
        </style>
        
    </head>
    <body ng-app class="claro">
        <div class="row"><jsp:include page="mainmenu.html"/></div>
        
        <div ng-controller="controllerMarketingCampaign" id="controllerMarketingCampaign" class="container"> 
            <jsp:include page="AddAction.jsp"/>
            
            <!--/end left column-->

            <div  id="marktng"><jsp:include page="marketingsubmenu.html" /></div>

            <script src="js/marketing_old.js" type="text/javascript"></script>
            <!--<div id="overlay">-->
            <div class="col-md-8 col-md-offset-3 " >
                <div class="col-md-10 col-md-offset-0">

                    <div class="inlineFlex top26">
                        <div>
                            <p id="planhead" class="MH2">Your Plan</p>
                        </div>
                        
                        <a href = "javascript:void(0)" class="topten">
                            <button id="liPriority" onclick = "overlay();" class="addactbtn button button--moema button--text-thick button--text-upper button--size-s1" ng-click="ShowAddAction()">ADD ACTION</button>
                        </a>
                        <div class="topten">
                        <button id="delsel" class="button button--moema button--text-thick button--text-upper button--size-s2" style="display:none;" ng-click="deleteSchedule('0','deleteMultiple')">DELETE SELECTED</button> 
                        </div>
                    </div>


                    <div class="col-md-12" style="display: none;" id="default" ng-init="getCampaigns()">
                        <div class="row">

                            <!--<div class='col-md-1 SP2 fonthead'>Today</div>-->
                            <div class='col-md-3' style="width:280px;"></div>
                            <div class='col-md-3' ></div></div> 

                        <div id="daydetails" ng-repeat="entity in entitySet">
                        <div ng-show="entity.date == today_date">
                            <p class="SS2 actfnt">Today</p>
                        </div>
                             
                        <div ng-show='entity.date==tomorrow_date'>
                            <p class="SS2 actfnt">Tomorrow</p>
                        </div>
                        <div ng-show="(entity.date != today_date) && (entity.date!=tomorrow_date)">

                            <p class="SS2 actfnt">{{entity.date| date: "MMM dd yyyy"}}</p>
                        </div>
                        <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-15px;top:-20px;">
                        <p ng-show="entity.dataArray == ''" class="ypacttxt fontpnr" id="messagetoday" style="display:block;position:relative;top:-25px;" >{{nodata}}</p>
                        <ul>
                            <li ng-repeat="entitydetails in entity.dataArray">
                                <div class="row" style="width:950px;position:relative;left:-40px;top:-20px;" id="entitydetails" >
                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetodays" id='{{entitydetails.schedule_id}}' style="width:15px;" value="{{entitydetails.schedule_id}}" onclick="setSelectedIds('{{entitydetails.schedule_id}}')"/>
                                    </div>
                                    <div class="col-md-3" style="width:300px;margin-left:-40px;">
                                        <a href = "javascript:void(0)" onclick = "overlay();" style="color:#333;text-decoration: none;">
                                            <div ng-show="entitydetails.is_recuring" >
                                                <p class="ypacttxt cur fontpnr" ng-click="getRecuringMailDetails(entitydetails.schedule_id,
                                                                entitydetails.template_status,
                                                                entitydetails.schedule_time,
                                                                entitydetails.entity_type,
                                                                entitydetails.schedule_title,
                                                                entitydetails.schedule_description,
                                                                entitydetails.user_marketing_program_id,
                                                                entitydetails.marketingName,
                                                                entitydetails.days)">{{entitydetails.schedule_title}}</p></a>
                                            </div>
                                            <div ng-hide="entitydetails.is_recuring" >
                                                <p class="ypacttxt cur fontpnr" ng-click="getScheduleDetails(entitydetails.schedule_id, entitydetails.template_status, entitydetails.schedule_time, entitydetails.entity_type, entitydetails.schedule_title, entitydetails.schedule_description,entitydetails.marketingName,entitydetails.user_marketing_program_id,entitydetails.days)">{{entitydetails.schedule_title}}</p></a>
                                            </div>
                                            <div ng-show="entitydetails.user_marketing_program_id > 0">
                                                <p class="SP1 fntschld">Schedule for {{entitydetails.schedule_time| date:"MMM dd yyyy h:mm a"}}<br>{{entitydetails.marketingName}}</p>                                     
                                            </div>
                                        <div ng-show="entitydetails.user_marketing_program_id == 0">
                                            <p class="SP1 fntschld">Schedule for {{entitydetails.schedule_time| date:"MMM dd yyyy h:mm a"}} <br> No Program</p>
                                        </div>

                                    </div>
                                    <div class="col-md-2 MH1 socfnts" ng-show="entitydetails.entity_type==master_facebook || entitydetails.entity_type==master_twitter">{{entitydetails.entity_type}} Post</div>
                                    <div class="col-md-2 MH1 socfnts" ng-show="entitydetails.entity_type==master_email || entitydetails.entity_type==master_note">{{entitydetails.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entitydetails.template_status}}</div>

                                    <div class="col-md-2" style="margin-left:20px;">
                                        <div ng-show="entitydetails.is_recuring" >
                                            <a href = "javascript:void(0)" onclick = "overlay();">
                                                <button type="button" 
                                                class="edtbtn" 
                                                ng-click="getRecuringMailDetails(entitydetails.schedule_id,
                                                                entitydetails.template_status,
                                                                entitydetails.schedule_time,
                                                                entitydetails.entity_type,
                                                                entitydetails.schedule_title,
                                                                entitydetails.schedule_description,
                                                                entitydetails.user_marketing_program_id,
                                                                entitydetails.marketingName,
                                                                entitydetails.days)">DETAILS</button>
                                            </a>
                                        </div>
                                         <div ng-hide="entitydetails.is_recuring" >
                                            <a href = "javascript:void(0)" onclick = "overlay();">
                                                <button type="button" 
                                                class="edtbtn button--moema  
                                                   button--text-upper 
                                                   button--size-s" 
                                                ng-click="getScheduleDetails(entitydetails.schedule_id, 
                                                            entitydetails.template_status,
                                                            entitydetails.schedule_time, 
                                                            entitydetails.entity_type, 
                                                            entitydetails.schedule_title, 
                                                            entitydetails.schedule_description,
                                                            entitydetails.marketingName,
                                                            entitydetails.user_marketing_program_id,
                                                            entitydetails.days,
                                                            entitydetails.is_today_active)">DETAILS</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                            
                        </div>
                        
                    </div>

                </div>
            </div>
            <!--           </div><div id="mask" onclick="document.location='#';">CLICK</div>    -->
            
            <div id="dvPriorityDialog" class="pollSlider" style="z-index:1005;">
                <form class="form-horizontal" id="signfrm" >
                <div class="addactcol">
                    <div class="inlineFlex">
                        <div class="pfont actfnt fontpnr">CREATE A NEW ACTION</div>
                    </div>
                    <div class="top18 fontpns tenpix headcolor">MARKETING PROGRAM</div>
                    <div class="topfive fontpnr">
                        <select disabled id="marketing_program" name="marketing_program_type" >
                            <option value="0">General</option>
                            <option ng-repeat="row in marketprogram" value="{{row.user_program_id}}">{{row.name}}</option>
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
                            <option value="Note">Note</option>
                        </select>
                    </div>
                    <div class="top18 fontpns tenpix headcolor">DESCRIPTION</div>
                    <div class="topten"><textarea class="addactiondesc fontpnr" name="description" id="description" placeholder="Description here"></textarea></div>
<!--                    <div class="toptweenty fontpns tenpix headcolor">STATUS</div>
                    <div class="inlineFlex">
                        <div class="rightfive top11">
                            <div class="redDot"></div>
                        </div>
                        <div class="tenpix fontpnr topnine containe">INCOMPLETE</div>
                    </div>-->
                    <div class="tenpix topten fontpns headcolor ">SCHEDULED TO POST ON</div>
                    <div class="inlineFlex">
                        <div class="half">
                            <div class="containe fontpnr tenpix topten">
                                Day
                            </div>
                            <div class="topsix">
<!--                                <input type="text" readonly  name="datepicker" id="datepicker"  class="inputdate fontpns ptr" />                                        
                                <script>
                            var picker = new Pikaday(
                            {
                                field: document.getElementById('datepicker'),
                                firstDay: 1,
                                minDate: new Date(2000, 0, 1),
                                maxDate: new Date(2050, 12, 31),
                                yearRange: [2000,2050]
                            });

                                </script>-->
                            </div>
                        </div>
                        <div class="half right100">
                            <div class=" containe fontpnr tenpix topten">
                                Time
                            </div>
                            <div class="topsix">
                                
                           </script>
                            </div>
                        </div>
                    </div>
                    <div class="topfourty">
                        <button class="addactcrtbtn fontpns twlvpix button--moema 
                                                   button--text-thick 
                                                   button--text-upper 
                                                   button--size-s" 
                                                   ng-click="AddAction()" 
                                                   >Create</button>
                    </div>
                </div>
                 </form>
                
<!--                <div id="dvPriorityContent" style="position:relative;top:30px;left:100px;"><br>
                    <h1 class="SP2 actfnt">ADD ACTION</h1>
                    <form class="form-horizontal" id="signform" >
                        <div class="group" style="position:relative;">
                            <p class="actionLable SH2" >type of action</p>
                        </div>
                        
                        <div style="position:absolute;left:15px;top:20px;" class="SH2">
                                <select id="actiontype" class="SS1 " name="actiontype" style="margin-top:10%;margin-left:2px;">
                                 <option value="0">Select</option>
                                 <option value="facebook">facebook</option>
                                 <option value="twitter">twitter</option>
                                 <option value="email">email</option>
                                 <option value="note">note</option>
                               </select>
                        </div>
                        <div  style="position:absolute;top:80px;left:15px;">
                            <p class="SH1">TITLE</p><textarea cols="36" rows="2" name="addactiontitle" id="addactiontitle" class="SS2" style="resize:none;font-size:1.3em;height:70px;margin-top:-5px;"></textarea>
                        </div>
                            <div style="position:absolute;top:180px;left:15px;" class="SH2">                              
                                description <br><textarea cols="40" rows="2" name="description" id="description" class="SS2" style="font-variant:normal;resize: none;height:70px;"></textarea>
                            </div>
                            <div class="SH2" style="position:absolute;top:282px;left:15px;" >
                                scheduled to post on<br><br>
                                date 
                                <input type="text" readonly name="actiondatetime"  class="inputdate MH1 ptr" id="datepicker" >
                                        <script>

                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><br>
                                        <div style="position:relative;margin-top:-84px;margin-left:180px;">
                                        time
                                     <input id="timepicker1" type="text" name="timepicker1" class="inputtime MH1 ptr" style="width:110px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker1').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script></div>

                            </div>
                            
                                                                Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>
                           
                   
                        <div class="row">
                            <div class="col-md-12" style="width:250px;position:absolute;top:420px;left:-15px;">

                                <div class="row">
                                    <div>
                                        <div class="col-md-6" id="dvButtonContainer">
                                            <input type="button" value="Save" 
                                                   class="button button--moema 
                                                   button--text-thick 
                                                   button--text-upper 
                                                   button--size-s" 
                                                   ng-click="AddAction()" 
                                                   style="width:100px;" />
                                        </div>
                                        <div class="col-md-6" id="dvButtonContainer">
                                            <input id="addactioncancel" type="reset" value="Cancel" onclick="cancelform();" 
                                                   class="button button--moema 
                                                   button--text-thick 
                                                   button--text-upper 
                                                   button--size-s" 
                                                   style="width:100px;" />
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </form> 

                </div>-->
            </div>
            <div id="preview" class="pollSlider">
                <div>
                    <div id="preview_email"  class="inlineFlex">
                    <div class="half">
                    <div class="borderright">
                        <div class="firstcol">
                            <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">ACTION DETAILS</div>
                                <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_email)" >DELETE ACTION</div>
                            </div>
                            <div class="top26 headcolor twlvpix fontpns">ACTION DETAILS</div>
                            <div class="top8 titlecontain sixtnpix fontpns">
                                {{schedule_title}}
                            </div>
                            <div class="toptweentyone headcolor twlvpix fontpns">
                                DESCRIPTION
                            </div>
                            <div class="height75 scrolly topten">
                                <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                            </div>
                            <div class="inlineFlex toptweenty">
                                <div class="half headcolor fontpns twlvpix">
                                    STATUS
                                </div>
                                <div class="half headcolor fontpns twlvpix">
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
                                    {{marketing_program_name}}
                                </div>
                            </div>
                            <div class="topfourty headtitle pfont actfnt fontpnr">
                                POSTING DETAILS
                            </div>
                            <div class="toptweenty headcolor twlvpix fontpns">
                                SCHEDULED TO POST ON
                            </div>
                            <div class="containe fontpnr tenpix">
                                {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}
                            </div>
                            <div class="inlineFlex top120">
                                <div class="rightthirty">
                                    <input type="button"  ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc,marketing_program_name,is_today_active)" value="Edit" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_button_post">
                                
                                </div>
                                <div class="approve">
                                    <div ng-show="is_today_active">
                                      <div ng-controller="EmpDetCtrl">       
                                        
                                            <div class="EmployeeInfo">
                                                <button ng-show="email_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_email)" class="button approvetosendbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Send</button> 
                                            </div>
                                        <div class=" right EmployeeInfo fulwid">


                                            <div class="EmployeeInfo">

                                                <div>

                                                    <button ng-show="email_template_status=='Approved'" ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;padding:0px;letter-spacing:1px !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 

                                                </div> 
                                            </div>

                                            <div class="EmployeeInfo6">

                                                <div>

                                                    <button id="button1" ng-show="email_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_email)" style="background-color: #e25b5b !important;color: white !important;display:none;padding:0px;letter-spacing:0.5px !important;left:17px;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                                </div> 

                                            </div>

                                       </div>                                
                                    </div>  
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                      
                        <div class="half">
                        <div class="secondcol">
                            <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="mailpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',master_email)">REMOVED SAVED POST</div>
                            </div>
                            <div class="toptweentyone headcolor twlvpix fontpns">
                                PREVIEW
                            </div>
                             <div id="mailremovedtemplate">
                                <div class="rectangle">
                                    <div class="circle"></div>
                                </div>
                                <div class="rightthirty">
                                    <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
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
                                        <img id="mailimgprev" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
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
                                        {{entitiesdetails.email_list_name}}
                                    </div>
                                </div>
                                 <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME
                                    </div>
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME TEXT
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
<!--                    <div id="edit_email_action" >
                        <h1 class="SP1 actfnt">UPDATE ACTION</h1>
                        <form class="form-horizontal" id="signform">

                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">                            
                                    <input id="email_edit_title" class=" inputbox MH2" type="text" name="email_edit_title" value="{{schedule_title}}" style="position:relative;top:7px;line-height:40px;width:400px;font-size:26px;"/>
                                    <p><input class="inputbox SP1" type="hidden" name="email_scheduleid" id="email_scheduleid" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>

                                                                        <label>TITLE</label>
                                </div><br>
                                <div style="position:absolute;left:60px;top:60px;" class="SH2">
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p><input class="inputbox SP1" type="hidden" name="email_schedule_type" id="email_schedule_type" value='{{schedule_type}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                </div>
                                <div style="position:absolute;top:110px;left:60px;" class="SP1 actfnt">
                                    Description <br><textarea cols="28" rows="2" name="email_description" id="email_description" class="SS2" style="font-variant:normal;resize: none;">{{schedule_desc}}</textarea>
                                </div>
                                <div class="SH2" style="position:absolute; margin-top: 190px; margin-left: 60px;" >
                                    <p class="SP1 actfnt" style="font-weight:400;font-size:1.2em;">Selected Date: {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p><br>
                                    <div class="SP1 actfnt" style="font-weight:500;font-size:1.2em;">Date</div>
                                    <input type="datetime-local" name="emaildatetime" id="emaildatetime" class="inputdate MH1"/>

                                    <input type="text" readonly class="inputdate MH1 ptr" name="emaildatetime" id="emaildatetime" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('emaildatetime'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script>
                                        <div class="SP1 actfnt" style="font-weight:500;font-size:1.2em;margin-left:150px;margin-top:-63px;">Time
                                    <input id="timepickeremail" type="text" name="timepicker_email" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepickeremail').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                        </div>
                                        
                                    </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12" style="width:250px;position:absolute;top:380px;left:35px;">

                                    <div class="row">
                                        <div>
                                            <div class="col-md-6" id="dvButtonContainer">
                                                <input type="button" value="Save" 
                                                       class="button button--moema 
                                                       button--text-thick 
                                                       button--text-upper 
                                                       button--size-s" 
                                                       ng-click="updateActionEmail()" 
                                                       style="width:100px;" />
                                            </div>
                                            <div class="col-md-6" id="dvButtonContainer">
                                                <input type="reset" value="Cancel" 
                                                       class="button button--moema 
                                                       button--text-thick 
                                                       button--text-upper 
                                                       button--size-s" 
                                                       style="width:100px;" onclick="cancelform();"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </form>                            
                    </div>-->
                    <div id="edit_email">
                        <div  class="inlineFlex">
                            <div class="half">
                                <div class="borderright" style="height: 578px;">
                                    <div class="firstcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_email)" >DELETE ACTION</div>
                                        </div>
                                        <div class="toptweenty headcolor twlvpix fontpns">ACTION DETAILS</div>
                                        <div class="topten titlecontain sixtnpix fontpns">
                                           <textarea id="email_edit_title" name="email_edit_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                           <input class="inputbox SP1" type="hidden" name="email_scheduleid" id="email_scheduleid" value='{{schedule_id}}' />
                                           <input class="inputbox SP1" type="hidden" name="email_schedule_type" id="email_schedule_type" value='{{schedule_type}}'/></p>
                                        </div>
                                        <div class="top12 headcolor twlvpix fontpns">
                                            DESCRIPTION
                                        </div>
                                        <div class="height75 topten">
                                            <p class="containe twlvpix fontpnr">
                                                <textarea class="actiondetdesc"id="email_description" name="email_description">{{schedule_desc}}</textarea></p>
                                        </div>
                                        <div class="inlineFlex topten">
                                            <div class="half headcolor fontpns twlvpix topten">
                                                STATUS
                                            </div>
                                            <div class="half headcolor fontpns twlvpix topten">
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
                                                {{marketing_program_name}}
                                            </div>
                                        </div>
                                        <div class="top26 pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class="topnineteen twlvpix fontpns postto">  
                                            POSTING TO
                                        </div>
                                        <div class="containe fontpnr twlvpix topten">
                                            {{schedule_type}}
                                        </div>
                                        <div class="toptweenty headcolor twlvpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="inlineFlex">
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Day
                                                </div>
                                                <div ng-show="user_marketing_program_id > 0">
                                                    <div class="topsix">
<!--                                                        <input type="hidden" readonly  name="emaildatetime" id="emaildatetime"  class="inputdate MH1 ptr" value="Sun Jan 01 1970">                                        -->
                                                        <input type="text" class="textbox"  id="emaildays" name="emaildays" value="{{days}}"/>
                                                    </div>
                                                </div>
                                                <div ng-show="user_marketing_program_id == 0">
                                                    <div class="top8">
                                                        <input type="hidden" class="textbox"  id="emaildays" name="emaildays" value="0"/>
                                                        <input type="text" readonly  name="emaildatetime" id="emaildatetime"  class="inputdate MH1 ptr" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">                                        
                                                        <script>
                                                            var picker = new Pikaday(
                                                            {
                                                                field: document.getElementById('emaildatetime'),
                                                                firstDay: 1,
                                                                minDate: new Date(2000, 0, 1),
                                                                maxDate: new Date(2050, 12, 31),
                                                                yearRange: [2000,2050]
                                                            });

                                                        </script>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Time
                                                </div>
                                                <div class="topsix">
                                                    <input id="timepickeremail" type="text" name="timepickeremail" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/> 
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
                                        <div class="topthirty8">
                                            <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns"  ng-click="updateActionEmail()" >Save</button>                                       
                                        </div>
                                        <div class="approve">
<!--                                    <div ng-show="is_today_active">
                                      <div>       
                                            <div class="EmployeeInfo">
                                                <button  ng-click="Approval(schedule_id, 'approved', master_email)" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Send</button> 
                                            </div>
                                            <div>
                                                <button ng-show="email_template_status=='Template Saved'"  ng-click="Approval(schedule_id, 'approved', master_email)" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Send</button> 
                                            </div>
                                          
                                        <div class=" right EmployeeInfo">
                                            <div class="EmployeeInfo">

                                                <div>

                                                    <button ng-show="email_template_status=='Approved'" ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 

                                                </div> 
                                            </div>

                                            <div class="EmployeeInfo6">

                                                <div>

                                                    <button id="button1" ng-show="email_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_email)" style="background-color: #e25b5b !important;color: white !important;display:none;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Unapprove</button> 

                                                </div> 

                                            </div>

                                       </div>                                
                                    </div>
                                </div>-->
                                </div>
                                </div>
                                </div>
                                </div>

                        <div class="half">
                        <div class="secondcol">
                            <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="maileditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',master_email)">REMOVED SAVED POST</div>
                            </div>
                            <div class="toptweentyone headcolor twlvpix fontpns">
                                PREVIEW
                            </div>
                             <div id="mailremovedtemplate2">
                                <div class="rectangle">
                                    <div class="circle"></div>
                                </div>
                                <div class="rightthirty">
                                    <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                </div>
                            </div>
                            <div id="mailpreviewdecond2">
                                <div class="topten"><!-- <div class="content"></div> -->
<!--                                    <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />-->
                                    <div class="content">
                                        <img id="mailimgprev" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
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
                                        {{entitiesdetails.email_list_name}}
                                    </div>
                                </div>
                                 <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME
                                    </div>
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME TEXT
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
                                        <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_facebook)" >DELETE ACTION</div>
                                    </div>
                                    <div class="topthirty headcolor twlvpix fontpns">ACTION DETAILS</div>
                                    <div class="topten titlecontain sixtnpix fontpns">
                                        {{schedule_title}}
                                    </div>
                                    <div class="toptweentyone headcolor twlvpix fontpns">
                                        DESCRIPTION
                                    </div>
                                    <div class="height75 scrolly topten">
                                        <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                    </div>
                                    <div class="inlineFlex toptweenty">
                                        <div class="half headcolor fontpns twlvpix">
                                            STATUS
                                        </div>
                                        <div class="half headcolor fontpns twlvpix">
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
                                            {{marketing_program_name}}
                                        </div>
                                    </div>
                                    <div class="topfourty headtitle pfont actfnt fontpnr">
                                        POSTING DETAILS
                                    </div>
                                    <div class="inlineFlex topnineteen">
                                        <div class="half headcolor fontpns twlvpix">
                                            POSTING TO
                                        </div>
                                        <div class="half headcolor fontpns twlvpix">
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
                                    <div class="toptweenty headcolor twlvpix fontpns">
                                        SCHEDULED TO POST ON
                                    </div>
                                    <div class="containe fontpnr tenpix">
                                            {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}
                                    </div>
                                    <div class="inlineFlex topsixeight">
                                        <div class="rightthirty">
                                            <input type="button" value="Edit" ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc,marketing_program_name,is_today_active)" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="fb_button_post">
                                        </div>
                                        <div class="approve">
                                    <div ng-show="is_today_active">
                                      <div>       
                                            <div>
                                            <div class="EmployeeInfo">
                                                
                                                <button ng-show="facebook_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_facebook)" class="button approvetopostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                            </div>
                                        <div class=" right EmployeeInfo fulwid">


                                            <div class="EmployeeInfo halfwid">

                                                <div>

                                                    <button ng-show="facebook_template_status=='Approved'" ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;padding:0px;letter-spacing:1px !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 

                                                </div> 
                                            </div>

                                            <div class="EmployeeInfo6 halfwid">

                                                <div>

                                                    <button ng-show="facebook_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_facebook)" id="button1"  style="background-color: #e25b5b !important;color: white !important;display:none;padding:0px;letter-spacing:0.5px !important;left:17px;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                                </div> 

                                            </div>

                                       </div>                                
                                    </div>  
                                </div>
                                </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="half">
                                <div class="secondcol">
                                     <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="fbpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',master_facebook)">REMOVED SAVED POST</div>
                                    </div>
                                    <div class="toptweentyone headcolor twlvpix fontpns">
                                        PREVIEW
                                    </div>
                                    <div id="fbremovedtemplate">
                                        <div class="rectangle">
                                            <div class="circle"></div>
                                        </div>
                                        <div class="rightthirty left82">
                                            <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                        </div>
                                    </div>
                                    <div id="fbpreviewdecond">
<!--                                        <div class="topten">
                                            <img id="fbimgprev" class="fbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />   {{entitiesdetails.image_name}}  
                                        </div>-->
                                        <div id="imgcontainer">
                                            <img id="prevfbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
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
                                        <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_facebook)" >DELETE ACTION</div>
                                    </div>
                                    <div class="toptweenty headcolor twlvpix fontpns">ACTION DETAILS</div>
                                    <div class="topten titlecontain sixtnpix fontpns">
                                       <textarea id="fb_action_title" name="fb_action_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                       <input class="inputbox SP1" type="hidden" name="fb_scheduletype" id="fb_scheduletype" value='{{schedule_type}}'/>
                                       <input class="inputbox SP1" type="hidden" name="fb_scheduleid" id="fb_scheduleid" value='{{schedule_id}}'/>
                                    </div>
                                    <div class="top12 headcolor twlvpix fontpns">
                                        DESCRIPTION
                                    </div>
                                    <div class="height75 topten">
                                        <p class="containe twlvpix fontpnr">
                                            <textarea name="fb_description" id="fb_description" class="actiondetdesc">{{schedule_desc}}</textarea></p>
                                    </div>
                                    <div class="inlineFlex topten">
                                        <div class="half headcolor fontpns twlvpix topten">
                                            STATUS
                                        </div>
                                        <div class="half headcolor fontpns twlvpix topten">
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
                                            {{marketing_program_name}}
                                        </div>
                                    </div>
                                     <div class="topthirty pfont actfnt fontpnr">
                                        POSTING DETAILS
                                    </div>
                                     <div class="inlineFlex topten">
                                        <div class="half headcolor fontpns twlvpix topten">
                                            POSTING TO
                                        </div>
                                        <div class="half headcolor fontpns twlvpix topten">
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
                                            CANNOT EDIT
                                        </div>
                                    </div>
                                    
                                    <div class="toptweenty headcolor tenpix fontpns">
                                        <div ng-show="entitydetails.user_marketing_program_id > 0">
                                            Days
                                        </div>
                                        <div ng-show="entitydetails.user_marketing_program_id == 0">
                                            SCHEDULED TO POST ON
                                        </div>
                                    </div>
                                    <div class="inlineFlex">
                                        <div class="half">
                                            <div class=" containe fontpnr tenpix topten">
                                                Day
                                            </div>
                                            <div ng-show="user_marketing_program_id > 0">
                                                    <div class="topsix">
                                                        <input type="text" class="textbox"  id="fbdays" name="fbdays" value="{{days}}"/>
                                                    </div>
                                                </div>
                                                <div ng-show="user_marketing_program_id == 0">
                                                    <div class="top8">
                                                        <input type="text" readonly  name="datepickerfb" id="datepickerfb"  class="inputdate MH1 ptr" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">                                        
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
                                                </div>
                                        </div>
                                        <div class="half">
                                            <div class=" containe fontpnr tenpix topten">
                                                Time
                                            </div>
                                            <div class="topsix">
                                                <input id="timepickerfb" type="text" name="timepickerfb" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/> 
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
                                    <div class="topthirty8">
                                        <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns" ng-click="updateActionFacebook()" >Save</button>                                       
                                    </div>
<!--                                    <div class="approve">
                                    <div ng-show="is_today_active">
                                        
                                        <div>
                                            <div class="EmployeeInfo">
                                                <button  ng-click="Approval(schedule_id, 'approved', master_facebook)" class="button approvetopostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                          
                                            <div>     
                                                <button ng-show="facebook_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_facebook)" class="button approvetopostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                    
                                        <div class=" right EmployeeInfo fulwid">


                                            <div class="EmployeeInfo">

                                                <div>

                                                    <button ng-show="facebook_template_status=='Approved'" ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;padding:0px;letter-spacing:1px !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 

                                                </div> 
                                            </div>

                                            <div class="EmployeeInfo6">

                                                <div>

                                                    <button id="button1" ng-show="facebook_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_facebook)" style="background-color: #e25b5b !important;color: white !important;display:none;padding:0px;letter-spacing:0.5px !important;left:17px;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                                </div> 

                                            </div>

                                       </div>                                
                                    </div>  
                                </div>
                                </div>-->
                                </div>
                            </div>
                            </div>
                        
                        <div class="half">
                                <div class="secondcol">
                                     <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="fbeditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',master_facebook)">REMOVED SAVED POST</div>
                                    </div>
                                    <div class="toptweentyone headcolor twlvpix fontpns">
                                        PREVIEW
                                    </div>
                                    <div id="fbremovedtemplate2">
                                        <div class="rectangle">
                                            <div class="circle"></div>
                                        </div>
                                        <div class="rightthirty left82">
                                            <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                        </div>
                                    </div>
                                    <div id="fbpreviewdecond2">
<!--                                        <div class="topten">
                                            <img id="fbimgprev" class="fbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />   {{entitiesdetails.image_name}}  
                                        </div>-->
                                        <div id="imgcontainer">
                                            <img id="prevfbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
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


            <div id="previewNote" class="pollSlider">
                <div>
                    <div id="noteprev">
                        <div id="noteprev">
                            <div class="firstcol">
                                <div class="inlineFlex">
                                    <div class="headtitle pfont actfnt fontpnr">NOTE DETAILS</div>
                                    <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_note)" >DELETE ACTION</div>
                                </div>
                                <div class="topthirty headcolor twlvpix fontpns">ACTION DETAILS</div>
                                <div class="topten titlecontain sixtnpix fontpns">
                                    {{schedule_title}}
                                </div>
                                <div class="toptweentyone headcolor twlvpix fontpns">
                                    DESCRIPTION
                                </div>
                                <div class="height75 scrolly topten">
                                    <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                </div>
                                <div class="inlineFlex top32">
                                    <div class="headcolor fontpns twlvpix">
                                        STATUS
                                    </div>
                                </div>
                                <div class="toptnine">
                                    <div class="containe fontpnr tenpix inlineFlex" id="noteincomplete">
                                        <div class="rightfive toptwo">
                                            <div class="redDot"></div>
                                        </div>
                                        <div class="containe tenpix fontpnr ">No Template</div>
                                    </div>
                                    <div class="containe fontpnr tenpix inlineFlex" id="notecomplete">
                                        <div class="rightfive toptwo">
                                            <div class="greenDot"></div>
                                         </div>
                                            <div class="containe tenpix fontpnr ">Template Saved</div>
                                    </div>
                                    <div class="containe fontpnr tenpix inlineFlex">
                                        <div class="rightfive toptwo">
                                            <div class="redDot"></div>
                                        </div>
                                        <div class="containe tenpix fontpnr ">INCOMPLETE</div>
                                    </div>
                                </div>
                                <div class="toptweenty headtitle pfont actfnt fontpns">
                                    MARKETING PROGRAM
                                </div>
                                <div class="toptnine headcolor tenpix fontpnr">
                                    {{marketing_program_name}}
                                </div>
                                <div class="top20nhalf headtitle pfont actfnt fontpns">
                                    SCHEDULED TO SEND ON
                                </div>
                                <div class="top10n half headcolor tenpix fontpnr">
                                    {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}
                                </div>
                                <div class="inlineFlex top111">
                                    <div class="right223">
                                        <input type="button" value="Edit" ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="note_button_post">
                                    </div>
                                </div>
                            </div>
                        </div>
<!--                        <div style="position:relative;left:50px;">

                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:400px;"> {{schedule_title}}</p>
                                <p class="SP1 actfnt" style="font-size:15px;font-weight:400;">{{schedule_desc}}</p>
                                <p class="SP1 actfnt">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                            </div>

                            <div style="position:absolute;margin-left:15px;margin-top:50px;">
                                <p class=" SP1 actfnt">Post details</p>
                                <div>
                                    <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                </div>

                                <div style="position:relative;left:-10px;">
                                    <button id="button_edit" ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)" class="button button--moema button--text-thick button--text-upper button--size-s" style="background-color:#E65C00;width:120px;" type="button" >EDIT</button> 
                                </div>

                            </div>

                        </div>-->
                    </div>
                
                    <div id="noteedit" style="display:none;">
                        <div class="firstcol">
                            <div class="inlineFlex">
                                    <div class="headtitle pfont actfnt fontpnr">NOTE DETAILS</div>
                                    <div class="headdeletenote h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_note)" >DELETE ACTION</div>
                            </div>
                            <div class="twlvpix headcolor fontpns topthirty">ACTION NAME</div>
                            <div><textarea class="addactiondetinput top8 fontpns" id="note_title" name="note_title" >{{schedule_title}}</textarea></div>
                            <input class="inputbox SP1" type="hidden" name="note_schedule_id" id="note_schedule_id" value='{{schedule_id}}'/></p>
                            <input class="inputbox SP1" type="hidden" name="note_entity_id" id="note_entity_id" value='{{entitiesdetails.id}}'/></p>
                            <div class="top12 fontpns twlvpix headcolor">DESCRIPTION</div>
                            <div class="topten"><textarea class="addactiondesc fontpnr" id="note_desc" name="note_desc">{{schedule_desc}}</textarea></div>
                            <div class="toptweenty fontpns twlvpix headcolor">STATUS</div>
                            
                            <div class="containe fontpnr tenpix inlineFlex" id="noteincomplete">
                                <div class="rightfive toptwo">
                                    <div class="redDot"></div>
                                </div>
                                <div class="containe tenpix fontpnr ">No Template</div>
                            </div>
                            <div class="containe fontpnr tenpix inlineFlex" id="notecomplete">
                                <div class="rightfive toptwo">
                                    <div class="greenDot"></div>
                                </div>
                                <div class="containe tenpix fontpnr ">Template Saved</div>
                            </div>
                            <input type="hidden" name="status" id="status" value="incomplete"/>
                            <div class="containe fontpnr tenpix inlineFlex">
                                <div class="rightfive toptwo">
                                    <div class="redDot"></div>
                                </div>
                                <div class="containe tenpix fontpnr ">INCOMPLETE</div>
                            </div>
                            
                            
                            <div class="toptweenty fontpns twlvpix headcolor">MARKETING PROGRAM</div>
                            <div class="tenpix fontpnr topnine containe">{{marketing_program_name}}</div>
                            <div class="twlvpix fontpns headcolor toptweenty">SCHEDULED TO POST ON</div>
                            <div class="inlineFlex">
                                <div class="half">
                                    <div class="containe fontpnr tenpix topten">
                                        Day
                                    </div>
                                    <div class="topsix">
                                        <input type="text" readonly name="datepicker4" id="datepicker4"   class="inputdate fontpns ptr" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}"/>                                        
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker4'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script>
                                    </div>
                                </div>
                                <div class="half right100">
                                    <div class=" containe fontpnr tenpix topten">
                                        Time
                                    </div>
                                    <div class="topsix">
                                        <input id="timepicker3" name="timepicker3" type="text"  class="inputtime fontpns ptr"  value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                                        <script>
                                                        $('#timepicker3').timepicki({
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
                                <div class="top80">
                                    <button class="addactcrtbtn btnpad fontpns twlvpix button--moema 
                                                               button--text-thick 
                                                               button--text-upper 
                                                               button--size-s" 
                                                               ng-click="updateNote()" 
                                                               >save</button>
                                </div>
                            </div>
                        

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
                                    <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_twitter)">DELETE ACTION</div>
                                </div>
                                <div class="topthirty headcolor twlvpix fontpns">ACTION DETAILS</div>
                                <div class="topten titlecontain sixtnpix fontpns">
                                    {{schedule_title}}
                                </div>
                                <div class="toptweentyone headcolor twlvpix fontpns">
                                    DESCRIPTION
                                </div>
                                <div class="height75 scrolly topten">
                                    <p class="containe twlvpix fontpnr">{{schedule_desc}}</p>
                                </div>
                                <div id="imgcontainer" style="display: none">
                                    <img id="prevtwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                </div>
                                <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns twlvpix">
                                        STATUS
                                    </div>
                                    <div class="half headcolor fontpns twlvpix">
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
                                        {{marketing_program_name}}
                                    </div>
                                </div>
                                <div class="topfourty headtitle pfont actfnt fontpnr">
                                    POSTING DETAILS
                                </div>
                                 <div class="topnineteen headcolor twlvpix fontpns">
                                    POSTING TO
                                </div>
                                <div class="containe fontpnr tenpix">
                                    {{schedule_type}}
                                </div>
                                <div class="toptweenty headcolor twlvpix fontpns">
                                    SCHEDULED TO POST ON
                                </div>
                                <div class="containe fontpnr tenpix">
                                    {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}
                                </div>
                                <div class="inlineFlex topsixeight">
                                    <div class="rightthirty">
                                        <input type="button" value="Edit" ng-click="editScheduleDetails(
                                            schedule_id, 
                                            entities_selected_time, 
                                            schedule_type, 
                                            schedule_title, 
                                            schedule_desc,
                                            marketing_program_name,
                                            is_today_active)"  class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="twitter_button_post">
                                    </div>
                                    
                                    <div class="approve">
                                    <div ng-show="is_today_active">
                                      <div>       
                                        <div>
                                            <div class="EmployeeInfo">
                                                
                                                <button ng-show="twitter_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_twitter)" class="button approvetopostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                        </div>
                                        <div class=" right EmployeeInfo fulwid" >


                                            <div class="EmployeeInfo">

                                                <div>

                                                    <button ng-show="twitter_template_status=='Approved'" ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;padding:0px;letter-spacing:1px !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 

                                                </div> 
                                            </div>

                                            <div class="EmployeeInfo6">

                                                <div>

                                                    <button ng-show="twitter_template_status=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_twitter)" id="button1"  style="background-color: #e25b5b !important;color: white !important;display:none;padding:0px;letter-spacing:0.5px !important;left:17px;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 

                                                </div> 

                                            </div>

                                       </div>                                
                                    </div>  
                                </div>
                                </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div class="half">
                        <div class="secondcol">
                             <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="twpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',master_twitter)">REMOVED SAVED POST</div>
                            </div>
                            <div class="toptweentyone headcolor twlvpix fontpns">
                                PREVIEW
                            </div>
                            <div id="twremovedtemplate">
                                <div class="rectangle">
                                    <div class="circle"></div>
                                </div>
                                <div class="rightthirty left82">
                                    <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                </div>
                            </div>
                            <div id="twpreviewdecond">
                                <div class="topten">
                                 <!--    <img id="prevtwtimg" class="twtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                   20150907175706740.png-->
                                    <div id="imgcontainer">
                                        <img id="prevtwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                    </div>
                                </div>
                                <div class="toptweenty headcolor tenpix fontpns">
                                    POST TEXT
                                </div>
                                <div class="height75 scrolly topten">
                                    <p class="containe twlvpix fontpnr">{{entitiesdetails.metadata.post_text}}</p>
                                </div>
                                <div class="toptweenty headcolor tenpix fontpns">
                                    LINK NAME
                                </div>
                                <div class="topten containe fontpnr twlvpix">
                                    {{entitiesdetails.metadata.title}}
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
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete', master_twitter)" >DELETE ACTION</div>
                                        </div>
                                        <div class="top26 headcolor twlvpix fontpns">ACTION DETAILS</div>
                                        <div class="topten titlecontain sixtnpix fontpns">
                                           <textarea id="edit_twitter_title" name="edit_twitter_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                           <input class="inputbox SP1" type="hidden" name="twitter_scheduleid" id="twitter_scheduleid" value='{{schedule_id}}'/>
                                           <input class="inputbox SP1" type="hidden" name="twitter_action_type" id="twitter_action_type" value='{{schedule_type}}' />
                                        </div>
                                        <div class="top12 headcolor twlvpix fontpns">
                                            DESCRIPTION
                                        </div>
                                        <div class="height75 topten">
                                            <p class="containe twlvpix fontpnr">
                                                <textarea cols="28" rows="2" name="twitter_description" class="actiondetdesc" id="twitter_description">{{schedule_desc}}</textarea>
                                        </div>
                                        <div class="inlineFlex topten">
                                            <div class="half headcolor fontpns twlvpix topten">
                                                STATUS
                                            </div>
                                            <div class="half headcolor fontpns twlvpix topten">
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
                                                {{marketing_program_name}}
                                            </div>
                                        </div>
                                         <div class="topthirty pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class=" headcolor fontpns twlvpix top19">
                                                POSTING TO
                                        </div>
                                        <div class=" tenpix fontpnr topnine">
                                            {{schedule_type}}
                                        </div>
                                        
                                        <div class="toptweenty headcolor twlvpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="inlineFlex">
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Day
                                                </div>
                                                <div ng-show="user_marketing_program_id > 0">
                                                    <div class="topsix">
                                                        <input type="hidden" readonly  name="datepickertwitter" id="datepickertwitter"  class="inputdate MH1 ptr" value="Sun Jan 01 1970">                                        
                                                        <input type="text" class="textbox"  id="twdays" name="twdays" value="{{days}}"/>
                                                    </div>
                                                </div>
                                                <div ng-show="user_marketing_program_id == 0">
                                                    <div class="top8">
                                                        <input type="hidden" class="textbox"  id="twdays" name="twdays" value="0"/>
                                                        <input type="text" readonly  name="datepickertwitter" id="datepickertwitter"  class="inputdate MH1 ptr" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">                                        
                                                        <script>
                                                            var picker = new Pikaday(
                                                            {
                                                                field: document.getElementById('datepickertwitter'),
                                                                firstDay: 1,
                                                                minDate: new Date(2000, 0, 1),
                                                                maxDate: new Date(2050, 12, 31),
                                                                yearRange: [2000,2050]
                                                            });

                                                        </script>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="half">
                                                <div class=" containe fontpnr tenpix topten">
                                                    Time
                                                </div>
                                                <div class="topsix">
                                                    <input id="timepickertwitter" type="text" name="timepickertwitter" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/> <!-- id="timepickertwitter" name="timepicker1" -->
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
                                        <div class="topthirty7">
                                            <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns" ng-click="updateActionTwitter()">Save</button>                                       
                                        </div>
                                        <div class="approve">
<!--                                    <div ng-show="is_today_active">
                                      <div>       

                                        <div>
                                            <div class="EmployeeInfo">
                                                <button  ng-click="Approval(schedule_id, 'approved', master_twitter)" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                          </div>

                                            <div>
                                            <div class="EmployeeInfo">
                                                <button ng-show="template_saved=='Template Saved'" ng-click="Approval(schedule_id, 'approved', master_twitter)" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                            </div>
                                        <div class=" right EmployeeInfo">
                                            <div class="EmployeeInfo">
                                                <div>
                                                    <button ng-show="template_saved=='Approved'" ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                                </div> 
                                            </div>
                                            <div class="EmployeeInfo6">
                                                <div>
                                                    <button ng-show="template_saved=='Approved'" ng-click="Approval(schedule_id, 'template_saved', master_twitter)" id="button1"  style="background-color: #e25b5b !important;color: white !important;display:none;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Unapprove</button> 
                                                </div> 
                                            </div>
                                       </div>                                
                                    </div>  
                                </div>-->
                                </div>
                                    </div>
                                </div>
                                </div>

                        <div class="half">
                        <div class="secondcol">
                             <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="tweditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',master_twitter)">REMOVED SAVED POST</div>
                            </div>
                            <div class="toptweentyone headcolor twlvpix fontpns">
                                PREVIEW
                            </div>
                            <div id="twremovedtemplate2">
                                <div class="rectangle">
                                    <div class="circle"></div>
                                </div>
                                <div class="rightthirty left82">
                                    <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                </div>
                            </div>
                            <div id="twpreviewdecond2">
                                <div class="topten">
                                 <!--    <img id="prevtwtimg" class="twtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                   20150907175706740.png-->
                                    <div id="imgcontainer">
                                        <img id="prevtwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                    </div>
                                </div>
                                <div class="toptweenty headcolor tenpix fontpns">
                                    POST TEXT
                                </div>
                                <div class="height75 scrolly topten">
                                    <p class="containe twlvpix fontpnr">{{entitiesdetails.metadata.post_text}}</p>
                                </div>
                                <div class="toptweenty headcolor tenpix fontpns">
                                    LINK NAME
                                </div>
                                <div class="topten containe fontpnr twlvpix">
                                    {{entitiesdetails.metadata.title}}
                                </div>
                            </div>
                        </div>
                        </div>
                        
                        </div>
                    </div>
                            
                           <div id="imgcontainer" style="display:none";>
                           <img id="edttwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' 
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
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'delete', master_email, 'true')" >DELETE ACTION</div>
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
                                                    <div class="containe tenpix fontpnr ">{{recuring_template_status}}</div>
                                                </div>
                                            </div>
                                            <div class="half containe fontpnr tenpix">
                                                {{marketingProgramName}}
                                            </div>
                                        </div>
                                        <div class="topfourty headtitle pfont actfnt fontpnr">
                                            POSTING DETAILS
                                        </div>
                                        <div class="toptweenty headcolor tenpix fontpns">
                                            SCHEDULED TO POST ON
                                        </div>
                                        <div class="containe fontpnr tenpix">
                                            {{entities_selected_time| date:'MMM dd yyyy'+' on '+ 'h:mma'}}
                                        </div>
                                        <div class="inlineFlex top120">
                                            <div class="rightthirty">
                                                <input type="button" ng-click="addEditRecuringAction('edit',entitiesdetails.user_marketing_program_id, schedule_id)" value="Edit" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_button_post">
                                            </div>
                                            <div class="approve">
                                                <input type="button" 
                                                       value="Approve to Send" 
                                                       ng-show="recuring_action_status == true && recuring_template_status=='Template Saved'" 
                                                       ng-click="recuringApproval(schedule_id, 'approved')" 
                                                       class="button approvetosendbuttonwidthheightcolor 
                                                                buttonmargin button--moema  
                                                                button--text-thick  
                                                                button--text-upper 
                                                                fontpns" 
                                                                id="mail_approve_button_post">
                                                <button ng-click="SaveData();" ng-show="recuring_template_status == 'Approved'" style="background-color: #19587c !important;color: white !important;padding:0px;letter-spacing:1px !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                                <button id="button1" ng-show="recuring_template_status == 'Approved'" ng-click="recuringApproval(schedule_id, 'template_saved')" style="background-color: #e25b5b !important;color: white !important;display:none;padding:0px;letter-spacing:0.5px !important;left:17px;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Disapprove</button> 
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
                                            <input type="button" value="Create Post" ng-click="addEditRecuringAction('template',entitiesdetails.user_marketing_program_id, schedule_id)"  class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
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
                                                <img id="mailimgprev" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
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
                                                {{entitiesdetails.email_list_name}}
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
                        <p style="margin-top:-7px;"><img src="images/closeIcon.svg" height="25" width="25" /></p>
                    </div>
                </a>
            </div>
        </div>   
        <script>
        $(".cross").hide();
        $(".menu").hide();
        $(".hamburger").click(function () {
            $(".menu").slideToggle("slow", function () {
            $(".hamburger").hide();
                    $(".cross").show();
            });
        });
        $(".cross").click(function () {
            $(".menu").slideToggle("slow", function () {
            $(".cross").hide();
                    $(".hamburger").show();
            });
        });

        </script>

<script type="text/javascript" src="https://tarruda.github.io/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>     
        

    </body>
</html>