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
       <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
       <link rel="stylesheet" href="css/facebook.css" type="text/css"/>
        <title>BrndBot - Your Plan</title>

        <style type='text/css'>
           
          
            
        </style>
        <script>
                 function overlay(){
                        document.getElementById('light').style.display = 'block';
                            document.getElementById('fade').style.display = 'block';
                             document.getElementById('blk').style.display = 'block';
                            document.getElementById('slider-button').style.display = 'block';
                            document.body.style.overflow = 'hidden';
                            $("#calendar").css("pointer-events","none");
                    }
            function closeoverlay(){
                    document.getElementById('light').style.display = 'none';
                    $("#calendar").css("pointer-events","auto");
                    document.getElementById('fade').style.display = 'none';
                    document.body.style.overflow = 'scroll';
                     document.getElementById('blk').style.display = 'none';
                    document.getElementById('edtfbimg').style.display = 'none';
                    document.getElementById('prevtwtimg').style.display = 'none';
                    document.getElementById('edttwtimg').style.display = 'none';
                    document.getElementById('prevfbimg').style.display = 'none';
            }
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
    </head>
    <body ng-app class="claro">
        <div class="row"><jsp:include page="mainmenu.html"/></div>
        <div ng-controller="controllerMarketingCampaign" id="controllerMarketingCampaign" class="container"> 
            <div id="fade" class="black_overlay"></div>
            <!--/end left column-->

            <div  id="marktng"><jsp:include page="marketingsubmenu.html" /></div>

            <script src="js/marketing.js" type="text/javascript"></script>
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
                                                <p class="SP1 fntschld">Schedule for {{entitydetails.schedule_time| date:"h:mma"}}&nbsp;&nbsp;&nbsp;&nbsp;{{entitydetails.marketingName}}</p>                                      </p>
                                            </div>
                                        <div ng-show="entitydetails.user_marketing_program_id == 0">
                                            <p class="SP1 fntschld">Schedule for {{entitydetails.schedule_time| date:"h:mma"}} &nbsp;&nbsp;&nbsp;&nbsp; No Program</p>
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
                                                            entitydetails.days)">DETAILS</button>
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
                        <select disabled id="marketing_program" name="marketing_program_type">
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
                                <input type="text" readonly  name="datepicker" id="datepicker"  class="inputdate fontpns ptr" />                                        
                                <script>
                            var picker = new Pikaday(
                            {
                                field: document.getElementById('datepicker'),
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
                                <input id="timepicker1" type="text" name="timepicker1" class="inputtime fontpns ptr"  /> 
                                <script src="js/timepicki.js" type="text/javascript"></script>
                                <script>
                                    $('#timepicker1').timepicki();
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
                                <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                    <input type="button"  ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc,marketing_program_name)" value="Edit" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_button_post">
                                
                                </div>
                                <div class="approve">
                                      <div ng-controller="EmpDetCtrl">       
                                        <div ng-model="EmpDetTable"   ng-hide="EmployeeInfoDiv">


                                            <div class="EmployeeInfo">

                                                <button  ng-click="ShowAddEmployee()" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approve to Post</button> 
                                            </div>
                                          </div>
                                        <div class=" right EmployeeInfo"   ng-show="EmployeeInfoDiv" >


                                            <div class="EmployeeInfo"  ng-hide="EmployeeInfoDiv1" >

                                                <div ng-model="botton_container">

                                                    <button ng-click="SaveData();" style="background-color: #19587c !important;color: white !important;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 

                                                </div> 
                                            </div>

                                            <div class="EmployeeInfo6"  ng-hide="EmployeeInfoDiv2">

                                                <div ng-model="botton_container">

                                                    <button id="button1"  style="background-color: #e25b5b !important;color: white !important;display:none;" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Unapprove</button> 

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
                                <div id="mailpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',getemail())">REMOVED SAVED POST</div>
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
                                    <div class="half headcolor fontpns tenpix">
                                        ADDITIONAL EMAIL LIST
                                    </div>
                                </div>
                                <div class="inlineFlex toptnine">
                                    <div class="half containe fontpnr tenpix">
                                        {{entitiesdetails.to_email_addresses}}
                                    </div>
                                    <div class="half containe fontpnr tenpix">
                                        {{entitiesdetails.email_list_name}}
                                    </div>
                                </div>
                                 <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME
                                    </div>
                                    <div class="half headcolor fontpns tenpix">
                                        From name text
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
                        
                        
<!--                    <div class="half">
                        <div class="secondcol">
                            <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove','email')">REMOVED SAVED POST</div>
                            </div>
                            <div class="toptweentyone headcolor tenpix fontpns">
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
                                <div id="imgcontainer"  style="display:none;">
                                            <img id="mailimgprev" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                        </div> entitiesdetails
                                <div class="content"> <div class="content"></div> 
                                    <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />
                                </div>
                                <div class="top27 headcolor tenpix fontpns">
                                    SUBJECT LINE
                                </div>
                                <div class="containe fontpnr tenpix">
                                    September 5, 2015 at 2:02 PM
                                </div>
                                <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns tenpix">
                                        TO EMAIL LIST
                                    </div>
                                    <div class="half headcolor fontpns tenpix">
                                        ADDITIONAL EMAIL LIST
                                    </div>
                                </div>
                                <div class="inlineFlex toptnine">
                                    <div class="half containe fontpnr tenpix">
                                        To mail list text
                                    </div>
                                    <div class="half containe fontpnr tenpix">
                                        Additional mail list text
                                    </div>
                                </div>
                                 <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME
                                    </div>
                                    <div class="half headcolor fontpns tenpix">
                                        From name text
                                    </div>
                                </div>
                                <div class="inlineFlex toptnine">
                                    <div class="half containe fontpnr tenpix">
                                        FROM EMAIL ADDRESS
                                    </div>
                                    <div class="half containe fontpnr tenpix">
                                        From email address
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->
                        
<!--                        <div class="actiondet">

                            <div style="background-color:#fff;position:relative;top:25px;left:50px;">

                                <div class="actiondetails actiondet">
                                    <p class="SP2 actfnt">ACTION DETAILS</p>
                                    <p class="MH2" style="width:300px;"> {{schedule_title}}</p>
                                                                        <p class="MH2" style="width:500px;">Description: {{schedule_desc}}</p>
                                </div>
                                <div class="SP1 actfnt" style="position:relative;left:15px;">Saved Email <div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div></div>

                                <div class="content"></div> <br>

                                <div style="position:absolute;margin-top:280px;">
                                    <p class=" SP1 actfnt">SENDING DETAILS</p>
                                    <div class="actiondet">
                                        <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    </div>

                                    <div ng-hide="email_template_status=='Complete'" style="position:relative;
                                         left:10px;bottom:0px;top:0px;">
                                        <button id="button_edit" 
                                                ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)" 
                                                class="button button--moema 
                                                button--text-thick 
                                                button--text-upper 
                                                button--size-s" 
                                                style="background-color:#E65C00;
                                                width:120px;" type="button">
                                            EDIT</button> 
                                    </div>
                                    <div ng-hide="email_template_status=='Complete'" style="position:relative;
                                         left:10px;bottom:0px;
                                         top:0px;">
                                        <input type="button" id="email_button_send"
                                               name="email_button_send"
                                               class="button button--moema 
                                                        button--text-thick 
                                                        button--text-upper 
                                                        button--size-s" 
                                                        style="width:120px;"
                                                        onclick="sendEmail()"
                                                value="Send"/>
                                    </div>
                                </div>
                            </div>
                        </div>-->
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
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                                        <input type="hidden" readonly  name="emaildatetime" id="emaildatetime"  class="inputdate MH1 ptr" value="Sun Jan 01 1970">                                        
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
                                                    <script src="js/timepicki.js" type="text/javascript"></script>
                                                    <script>
                                                        $('#timepickeremail').timepicki();
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="topthirty8">
                                            <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns"  ng-click="updateActionEmail()" >Save</button>                                       
                                        </div>
                                    </div>
                                </div>
                                </div>

                        <div class="half">
                        <div class="secondcol">
                            <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="maileditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',getemail())">REMOVED SAVED POST</div>
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
                                    <div class="half headcolor fontpns tenpix">
                                        ADDITIONAL EMAIL LIST
                                    </div>
                                </div>
                                <div class="inlineFlex toptnine">
                                    <div class="half containe fontpnr tenpix">
                                        {{entitiesdetails.to_email_addresses}}
                                    </div>
                                    <div class="half containe fontpnr tenpix">
                                        {{entitiesdetails.email_list_name}}
                                    </div>
                                </div>
                                 <div class="inlineFlex toptweenty">
                                    <div class="half headcolor fontpns tenpix">
                                        FROM NAME
                                    </div>
                                    <div class="half headcolor fontpns tenpix">
                                        From name text
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
                        
<!--                        <div style="position:relative;left:50px;;">
                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p><input type="text" class="inputbox MH2" id="email_entitytitle" name="email_entitytitle" value="{{schedule_title}}" style="position:relative;top:7px;line-height:30px;width:300px;font-size:20px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="email_schedule_id" id="email_schedule_id" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="email_entity_id" id="email_entity_id" value='{{entitiesdetails.schedule_email_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="email_entity_body" id="email_entity_body" value='{{entitiesdetails.body}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="email_entity_from_name" id="email_entity_from_name" value='{{entitiesdetails.from_name}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>

                                <br><p class="SP1 actfnt">Saved Email </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                            </div>
                            <div class="row" style="width:400px;">
                                <div class="col-md-3">
                                    <div class="editcontent"></div>
                                </div>

                                <div class="col-md-6" id="popupright_panel" >
                                                                            <div><label>subject</label></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">Subject Line </p><input type="text" class="inputbox SS2" name="email_entitysubject" id="email_entitysubject" value="{{entitiesdetails.subject}}"/></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">To</p><input type="text" class="inputbox" name="email_entitytoaddress" id="email_entitytoaddress" value="{{entitiesdetails.to_email_addresses}}"></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">From</p><input type="text" class="inputbox" name="email_entityfromaddress" id="email_entityfromaddress" value="{{entitiesdetails.from_address}}"></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">Reply To</p><input type="text" class="inputbox" name="email_entityreplytoaddress" id="email_entityreplytoaddress" value="{{entitiesdetails.reply_to_email_address}}"></div>
                                </div>
                            </div>
                            <div style="position: relative;margin-left:0px;margin-top:0px;">
                                <p class=" postdet SP1 actfnt">SENDING DETAIL</p>
                                <div class="actiondet" >
                                    <select class="SP1" id="chooseEmailList" class="actiondropdown" name="chooseEmailList" style="position: relative;margin-left:15px">
                                        <option style="background:#fff;" value="0">Select</option>
                                        <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                    </select>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{entitiesdetails.email_list_name}}</p>
                                    <div class="SH2" style="position:absolute;margin-top:10px;left:15px;" >
                                  
                                        <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                <input type="text" readonly  name="email_schedule_datetime" id="email_schedule_datetime"  class="inputdate MH1 ptr" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('email_schedule_datetime'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script>
                                       <div class="SP1 actfnt"  style="font-size:1.2em;margin-left:150px;margin-top:-63px;"> Time
                                           <input id="timepickeremailaction" type="text" name="timepickeremail" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepickeremailaction').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                    </div></div>
                                    <div style="position:relative;margin-top: 100px;"> 
                                    <input type="datetime-local" class="inputdate postdet " name="email_schedule_datetime" id="email_schedule_datetime"/>
                                    <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <div class="editbutton"><button ng-click="deleteSchedule(schedule_id,'remove')" class="button button--moema button--text-thin button--text-upper button--size-s" style="background-color:#444;width:230px;" type="button">REMOVE SAVED TEMPLATE</button> </div>
                                    <div class="editbutton"><button ng-click="updateEmailSchedule()" class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                                    </div>
                                </div>

                            </div>
                        </div>-->
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
                                        <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                            <input type="button" value="Edit" ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)" class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="fb_button_post">
                                        </div>
                                        <div class="approve">
                                            <input type="button" value="Approve to Post" onclick="postSocial()" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="fb_approve_button_post">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="half">
                                <div class="secondcol">
                                     <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="fbpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',getfacebook())">REMOVED SAVED POST</div>
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
                                    
<!--                                    <div id="fb_preview_postdet" style="position:absolute;margin-left:15px;">
                                        <p class=" SP1 actfnt">Post details</p>
                                        <div>
                                            <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                            <p class="SP1 actfnt" style="font-weight:400;">{{entitiesdetails.metadata.ManagedPage}}</p>
                                            <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                        </div>

                                        <div ng-hide="facebook_template_status=='Complete'" style="position:relative;bottom:0px;top:0px;left:-10px;">
                                        <button id="button_edit" 
                                                    class="button button--moema 
                                                    button--text-thick 
                                                    button--text-upper 
                                                    button--size-s" 
                                                    style="background-color:#E65C00;
                                                    width:120px;" type="button" 
                                                    ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)">EDIT
                                        </button>
                                        <input type="button" id="fb_button_post"
                                               class="button button--moema 
                                                        button--text-thick 
                                                        button--text-upper 
                                                        button--size-s" 
                                                        style="width:120px;"
                                                        onclick="postSocial()"
                                                value="Post"/>
                                        </div>
                                    </div>-->
                                </div>
                            </div>
                        </div>
<!--                    <div id="edit_facebook_action">

                         <div id="edit_email" class="inlineFlex">
                        <div class="half">
                            <div class="borderright" style="height: 533.7px;">
                                <div class="firstcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                        <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
                                    </div>
                                    <div class="topthirty headcolor tenpix fontpns">ACTION DETAILS</div>
                                    <div class="topten titlecontain sixtnpix fontpns">
                                       <textarea id="facebook_schedule_title" name="facebook_schedule_title" class="actiondetinput fontpns">{{schedule_title}}</textarea>
                                    </div>
                                    <div class="top12 headcolor tenpix fontpns">
                                        DESCRIPTION
                                    </div>
                                    <div class="height75 topten">
                                        <p class="containe twlvpix fontpnr">
                                            <textarea name="facebook_schedule_Description" id="facebook_schedule_Description" class="actiondetdesc">{{schedule_desc}}</textarea></p>
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
                                            <div class="inlineFlex">
                                                <div class="rightfive topten">
                                                    <div class="redDot"></div>
                                                </div>
                                                <div class=" tenpix fontpnr topten">CANNOT EDIT</div>
                                            </div>
                                        </div>
                                        <div class="half containe fontpnr tenpix topten">
                                            CANNOT EDIT
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
                                                <div class="rightfive topten">
                                                    
                                                </div>
                                                <div class=" tenpix fontpnr topten">CANNOT EDIT</div>
                                            </div>
                                        </div>
                                        <div class="half containe fontpnr tenpix topten">
                                            CANNOT EDIT
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
                                                <input type="text" readonly  name="facebook_schedule_date" id="facebook_schedule_date"  class="inputdate MH1 ptr" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">                                        
                                                <script>
                                            var picker = new Pikaday(
                                            {
                                                field: document.getElementById('facebook_schedule_date'),
                                                firstDay: 1,
                                                minDate: new Date(2000, 0, 1),
                                                maxDate: new Date(2050, 12, 31),
                                                yearRange: [2000,2050]
                                            });

                                                </script>
                                            </div>
                                        </div>
                                        <div class="half">
                                            <div class=" containe fontpnr tenpix topten">
                                                Time
                                            </div>
                                            <div class="topsix">
                                                <input id="facebook_schedule_time" type="text" name="facebook_schedule_time" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/> 
                                                <script src="js/timepicki.js" type="text/javascript"></script>
                                                <script>
                                                    $('#facebook_schedule_time').timepicki();
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="topthirty8">
                                        <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns">Save</button>                                       
                                    </div>
                                </div>
                            </div>
                            </div>
                        
                        <div class="half">
                        <div class="secondcol">
                            <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove','email')">REMOVED SAVED POST</div>
                            </div>
                            <div class="toptweentyone headcolor tenpix fontpns">
                                PREVIEW
                            </div>
                             <div id="facebookremovedtemplate">
                                <div class="rectangle">
                                    <div class="circle"></div>
                                </div>
                                <div class="rightthirty">
                                    <input type="button" value="Create Post" onclick="postSocial()" class="button createPostbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="create_Post">
                                </div>
                            </div>
                            <div id="facebookpreviewdecond">
                                <div class="topten"> <div class="content"></div> 
                                    <img id="mailimgprev" class="mailimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=20150829115244349.png' />
                                </div>
                                <div class="top27 headcolor tenpix fontpns">
                                    POST TEXT
                                </div>
                                <div class="height75 topten">
                                        <p class="containe twlvpix fontpnr">
                                            <textarea class="actiondetdesc">{{schedule_desc}}</textarea></p>
                                    </div>
                               
                                 <div class="top27 headcolor tenpix fontpns">
                                    lINK TITLE
                                </div>
                                 <div class="top27 headcolor tenpix fontpns">
                                    lINK DESCRIPTION
                                </div>
                                <div class="top27 headcolor tenpix fontpns">
                                    lINK URL
                                </div>
                            </div>
                        </div>
                    </div>
                        
              
                    </div>
                        
                        <div style="position:relative;left:50px;top:-10px;">
                            <div id="imgcontainer">
                            <img style="display:none" id="edtfbimg"/> 
       
                           </div>     
                           
                            
                        </div>

                        
                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">                            
                                    <input id="fb_action_title" class=" inputbox MH2" type="text" name="fb_action_title" value="{{schedule_title}}" style="position:relative;top:0px;line-height:40px;width:400px;font-size:26px;"/>
                                    <p><input class="inputbox SP1" type="hidden" name="fb_scheduleid" id="fb_scheduleid" value='{{schedule_id}}'/>
                                    </p>
                                    <label>TITLE</label>
                                </div><br>
                                <div style="position:absolute;left:60px;top:60px;" class="SH2">
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p><input class="inputbox SP1" type="hidden"
                                              name="fb_scheduletype" 
                                              id="fb_scheduletype"
                                              value='{{schedule_type}}' 
                                              style="position:relative;
                                              top:10px;font-size:15px;
                                              font-weight:400;line-height:10px;
                                              width:300px;"/>
                                    </p>
                                Type :  <select id="actiontype" class="SS1" name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 15px;width:100px;color:#444;background-color: #fff;border:none;border-bottom: 1px solid #000;">
                                        <option value="0">Select</option>
                                        <option value="facebook">facebook</option>
                                        <option value="twitter">twitter</option>
                                        <option value="email">email</option>
                                        <option value="note">note</option>
                                    </select>
                                </div>
                                <div style="position:absolute;top:110px;left:60px;" class="SP1 actfnt">
                                    Description <br><textarea cols="28" rows="2" name="fb_description" id="fb_description" class="SS2" style="font-variant:normal;resize: none;">{{schedule_desc}}</textarea>
                                </div>
                                <div class="SH2" style="position:absolute; margin-top: 200px; margin-left: 60px;" >
                                    <p class="SP1 actfnt" style="font-weight:400;font-size:1.2em;">Selected Date: {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                <input type="text" readonly class="inputdate MH1 ptr" name="fbdatetime" id="datepicker2" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker2'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                    </script>
                                    <div class="SP1 actfnt"  style="font-size:1.2em;margin-left:170px;margin-top:-63px;"> Time
                                    <input id="timepicker2" type="text" name="timepicker1" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker2').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                </div>

                                                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>

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
                                                       ng-click="updateActionFacebook()" 
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
                    </div>
                    </div>-->

                    <div id="edit_facebook">
                        <div class="inlineFlex">
                        <div class="half">
                            <div class="borderright" style="height: 578px;">
                                <div class="firstcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                        <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                                <script src="js/timepicki.js" type="text/javascript"></script>
                                                <script>
                                                    $('#timepickerfb').timepicki();
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="topthirty8">
                                        <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns" ng-click="updateActionFacebook()" >Save</button>                                       
                                    </div>
                                </div>
                            </div>
                            </div>
                        
                        <div class="half">
                                <div class="secondcol">
                                     <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="fbeditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',getfacebook())">REMOVED SAVED POST</div>
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
                                    
<!--                                    <div id="fb_preview_postdet" style="position:absolute;margin-left:15px;">
                                        <p class=" SP1 actfnt">Post details</p>
                                        <div>
                                            <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                            <p class="SP1 actfnt" style="font-weight:400;">{{entitiesdetails.metadata.ManagedPage}}</p>
                                            <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                        </div>

                                        <div ng-hide="facebook_template_status=='Complete'" style="position:relative;bottom:0px;top:0px;left:-10px;">
                                        <button id="button_edit" 
                                                    class="button button--moema 
                                                    button--text-thick 
                                                    button--text-upper 
                                                    button--size-s" 
                                                    style="background-color:#E65C00;
                                                    width:120px;" type="button" 
                                                    ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)">EDIT
                                        </button>
                                        <input type="button" id="fb_button_post"
                                               class="button button--moema 
                                                        button--text-thick 
                                                        button--text-upper 
                                                        button--size-s" 
                                                        style="width:120px;"
                                                        onclick="postSocial()"
                                                value="Post"/>
                                        </div>
                                    </div>-->
                                </div>
                            </div>
                        
              
                    </div>
                    <div id="imgcontainer" style="display:none">
                        <img style="display:none" id="edtfbimg"/> 
                    </div>      
                        
<!--                        <div style="position:relative;left:50px;top:-10px;">

                            <div class="actiondetails actiondet" >
                                <p class="SP2 actfnt">ACTION DETAILS</p><p class="SP1 ptr" ng-click="deleteSchedule(schedule_id,'delete')" style="position:absolute;left:400px;width:130px;font-size:1.1em;">DELETE ACTION</p>
                                <p><input class="inputbox MH2" type="text" name="facebook_schedule_title" id="facebook_schedule_title" value='{{schedule_title}}' style="position:relative;top:7px;line-height:30px;width:270px;font-size:22px;"/></p>
                                <p><input class="inputbox SP1" type="text" name="facebook_schedule_Description" id="facebook_schedule_Description" value='{{schedule_desc}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:270px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="facebook_schedule_id" id="facebook_schedule_id" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="facebook_entity_id" id="facebook_entity_id" value='{{entitiesdetails.id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="facebook_accesstoken" id="facebook_accesstoken" value='{{entitiesdetails.token_data.access_token}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="isFacebook" id="isFacebook" value='false' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                            </div>
                            <br><p class="SP1 actfnt" style="margin-left:15px;">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-35px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                           <div id="imgcontainer">
                           <img id="edtfbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' 
                                style="display:none;"/>
                           </div>     
                            
                            <p><input class="inputbox SP1" type="hidden" name="facebook_image_name" id="facebook_image_name" value='{{entitiesdetails.image_name}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                            <div style="position:absolute;top:160px;left:300px;">
                                <p><input type="text" name="facebook_schedule_posttext" id="facebook_schedule_posttext" value='{{entitiesdetails.metadata.post_text}}' class="actfnt" style="color:#888;width:230px;font-weight:400;"/></p>
                                <p><input type='text' name="facebook_schedule_url" id="facebook_schedule_url"  value='{{entitiesdetails.metadata.url}}' class="actfnt" style="color:#888;width:230px;font-weight:400;"/></p>
                                <p><input type='text' name='facebook_schedule_description' id="facebook_schedule_description" value='{{entitiesdetails.metadata.description}}' class="actfnt" style="color:#888;width:230px;font-weight:400;"/></p>
                            </div>
                            <div style="position:absolute;margin-left:15px;margin-top: 0px;">
                                <p class=" SP1 actfnt">Post details</p>
                                <div>
                                    <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                     <input type="text" readonly name="facebook_schedule_date" class="inputdate MH1 ptr" id="facebook_schedule_date" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}" style="width:150px;">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('facebook_schedule_date'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><div class="SP1 actfnt"  style="font-size:1.2em;margin-left:170px;margin-top:-55px;"> Time
                                        <input id="facebook_schedule_time" type="text" name="facebook_schedule_time" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/><br>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#facebook_schedule_time').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                    </div>                                   
                                    
                                    
                                    <input type='datetime-local' name="facebook_schedule_datetime" id="facebook_schedule_datetime" class="inputdate"/><br>
                                    <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <input type="text" readonly class="SH1" name="social_type" id="social_type" value="{{schedule_type}}">
                                </div>
                                <div class="editbutton"><button ng-click="deleteSchedule(schedule_id,'remove')" class="button button--moema button--text-thin button--text-upper button--size-s" style="background-color:#444;width:230px;" type="button">REMOVE SAVED TEMPLATE</button> </div>
                                <div class="editbutton" ng-click="updateSocialSchedule()"><button class="button button--moema button--text-thin button--text-upper button--size-s" style="margin-left:300px;margin-top:-48px;" type="button">save</button> </div>
                            </div>
                        </div>-->
                    </div>                        
            </div>


            <div id="previewNote" class="pollSlider">
                <div>
                    <div id="noteprev">
                        <div id="noteprev">
                            <div class="firstcol">
                                <div class="inlineFlex">
                                    <div class="headtitle pfont actfnt fontpnr">NOTE DETAILS</div>
                                    <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                    <div class="headdeletenote h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                        <script src="js/timepicki.js" type="text/javascript"></script>
                                        <script>
                                            $('#timepicker3').timepicki();
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
                        

<!--                        <div style="position:relative;left:50px;">

                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:500px;"><input type="text" id="note_title" name="note_title" value="{{schedule_title}}"/></p>
                                <p class="SP1 actfnt" style="font-size:17px;font-weight:400;color:#888;"><input type="text" id="note_desc" name="note_desc" value="{{schedule_desc}}"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="note_schedule_id" id="note_schedule_id" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="note_entity_id" id="note_entity_id" value='{{entitiesdetails.id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                            </div>       
                            <br><p class="SP1 actfnt" style="margin-left:15px;">Saved Post </p>
                            <div class="SP2 actfnt" style="margin-left:150px;margin-top:-35px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div><br>
                            <p class="SP1 actfnt" style="margin-left:15px;">SET STATUS</p> <div >
                            <select id="status" class="SP2 selstatus" name="status">
                                        <option style="background:#fff;" value="incomplete">incomplete</option>
                                        <option style="background:#fff;" value="complete">complete</option>
                            </select></div>
                            <div style="position:absolute;margin-left:16px;margin-top:10px;">
                                <p class=" SP1 actfnt">Post details</p>
                                <div>
                                     <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                     <input type="text" readonly name="notedate" class="inputdate MH1 ptr" id="datepicker4" style="width:150px;" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker4'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><div class="SP1 actfnt"  style="font-size:1.2em;margin-left:170px;margin-top:-55px;">  Time
                                        <input id="timepicker3" type="text" name="timepicker2" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/><br>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker3').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                    </div>
                              
                                    <input type="datetime-local" id="notedate" name="notedate"  class="inputdate"/><br>
                                    <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                </div>
                                <div class="savebutton" ng-click="updateNote()"><button class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                            </div>
                        </div>-->
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
                                    <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')">DELETE ACTION</div>
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
                                            schedule_desc)"  class="button editbuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="twitter_button_post">
                                    </div>
                                    <div class="approve">
                                        <input type="button" value="Approve to Post" onclick="postSocial()" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="ftwitter_approve_button_post">
                                    </div>
                                </div>
                            </div>
                            
<!--                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:400px;">{{schedule_title}}</p>
                                <p class="SP1 actfnt" style="font-size:15px;font-weight:400;"> {{schedule_desc}}</p>
                            </div>
                            <br><p class="SP1 actfnt" style="margin-left:15px;">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-35px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                            <div id="imgcontainer">
                           <img id="prevtwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' 
                                style="display:none;"/>
                           </div>
                            
                            <div style="position:relative;top:-200px;left:300px;">
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.text}}</p>
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.shorturl}}</p>
                            </div>
                            <div id="twitter_preview_postdet" style="position:relative;margin-left:15px;">  
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p class="SP1 actfnt" style="font-weight:400;">Schedule for {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                </div>
                                <div ng-hide="twitter_template_status=='Complete'" style="position:relative;bottom:0px;top:0px;left:-5px;" class="editbutton">
                                <button ng-click="editScheduleDetails(
                                            schedule_id, 
                                            entities_selected_time, 
                                            schedule_type, 
                                            schedule_title, 
                                            schedule_desc)" 
                                            class="button 
                                            button--moema 
                                            button--text-thick 
                                            button--text-upper 
                                            button--size-s" 
                                            style="background-color:#E65C00;
                                            width:120px;" 
                                            type="button">EDIT</button>
                                <input type="button" id="twitter_button_post"
                                       class="button button--moema 
                                                button--text-thick 
                                                button--text-upper 
                                                button--size-s" 
                                                style="width:120px;"
                                                onclick="postSocial()"
                                        value="Post"/>                                
                                </div>
                            </div>-->
                        </div>
                    </div>
                    <div class="half">
                        <div class="secondcol">
                             <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="twpreviewremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',gettwitter())">REMOVED SAVED POST</div>
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
<!--                    <div id="edit_twitter_action">
                        <h1 class="SP1 actfnt" style="font-size:2em;margin-left:45px;">UPDATE ACTION</h1>
                        <form class="form-horizontal" id="signform">

                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">
                                    <input id="edit_twitter_title" class=" inputbox MH2" type="text" name="edit_twitter_title" value="{{schedule_title}}" style="position:relative;top:0px;line-height:40px;width:400px;font-size:26px;"/>
                                    <p><input class="inputbox SP1" type="hidden" name="twitter_scheduleid" id="twitter_scheduleid" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                                                        <label>TITLE</label>
                                </div><br>
                                <div style="position:absolute;left:60px;top:50px;" class="SH2">
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p><input class="inputbox SP1" type="hidden" name="twitter_action_type" id="twitter_action_type" value='{{schedule_type}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                                                        Type :  <select id="actiontype" class="SS1" name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 15px;width:100px;color:#444;background-color: #fff;border:none;border-bottom: 1px solid #000;">
                                                                            <option value="0">Select</option>
                                                                            <option value="facebook">facebook</option>
                                                                            <option value="twitter">twitter</option>
                                                                            <option value="email">email</option>
                                                                            <option value="note">note</option>
                                                                        </select>
                                </div>
                                <div style="position:absolute;top:110px;left:60px;" class="SP1 actfnt">
                                    Description <br><textarea cols="28" rows="2" name="twitter_description" id="twitter_description" class="SS2" style="font-variant:normal;resize: none;">{{schedule_desc}}</textarea>
                                </div>
                                <div class="SH2" style="position:absolute; margin-top: 190px; margin-left: 60px;" >
                                    <p class="SP1 actfnt" style="font-weight:400;font-size:1.2em;">Selected Date: {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p><br>

                                    <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                    <input type="text" readonly class="inputdate MH1 ptr" name="twitterdatetime" id="datepicker3" value="{{entities_selected_time| date:'EEE MMM dd yyyy'}}"/>

                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker3'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script>
                                        <div class="SP1 actfnt"  style="font-size:1.2em;margin-left:170px;margin-top:-63px;"> Time
                                    <input id="timepickertwitter" type="text" name="timepicker1" class="inputtime MH1 ptr" style="width:150px;" value="{{entities_selected_time| date:'h : mm : a'}}"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepickertwitter').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                </div>

                                                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>

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
                                                       ng-click="updateActionTwitter()" 
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
                    </div>
                    </div>-->
                    <div id="edit_twitter"> 
                        <div class="inlineFlex">
                            
                            <div class="half">
                                <div class="borderright" style="height: 578px;">
                                    <div class="firstcol">
                                        <div class="inlineFlex">
                                            <div class="headtitle pfont actfnt fontpnr">EDIT ACTION DETAILS</div>
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'delete')" >DELETE ACTION</div>
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
                                                    <script src="js/timepicki.js" type="text/javascript"></script>
                                                    <script>
                                                        $('#timepickertwitter').timepicki();
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="topthirty7">
                                            <button class="emailsavebtn button button--moema button--text-thick button--text-upper button--size-s fontpns" ng-click="updateActionTwitter()">Save</button>                                       
                                        </div>
                                    </div>
                                </div>
                                </div>

                        <div class="half">
                        <div class="secondcol">
                             <div class="inlineFlex">
                                <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                <div id="tweditremove" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id,'remove',gettwitter())">REMOVED SAVED POST</div>
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
                                            <div class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'delete')" >DELETE ACTION</div>
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
                                                <input type="button" value="Approve to Post" ng-show="recuring_action_status == true && recuring_template_status=='Template Saved'" ng-click="Approval(schedule_id, 'approved')" class="button approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns" id="mail_approve_button_post">
                                                <button ng-click="SaveData();" ng-show="recuring_template_status == 'Approved'" style="background-color: #19587c !important;color: white !important;" class="button hide1 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Approved</button> 
                                                <button id="button1" ng-show="recuring_template_status == 'Approved'" ng-click="Approval(schedule_id, 'template_saved')" style="background-color: #e25b5b !important;color: white !important;display:none;" class="button hide2 approvebuttonwidthheightcolor buttonmargin button--moema  button--text-thick  button--text-upper fontpns">Unapprove</button> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="half">
                                <div class="secondcol">
                                    <div class="inlineFlex">
                                        <div class="headtitle pfont actfnt fontpnr">SAVED POST</div>
                                        <div id="mailpreviewremove6" class="headdelete h5font fontpnr" ng-click="deleteSchedule(schedule_id, 'remove', getemail())">REMOVED SAVED POST</div>
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
                                            <div class="half headcolor fontpns tenpix">
                                                ADDITIONAL EMAIL LIST
                                            </div>
                                        </div>
                                        <div class="inlineFlex toptnine">
                                            <div class="half containe fontpnr tenpix">
                                                {{entitiesdetails.email_list_name}}
                                            </div>
                                            <div class="half containe fontpnr tenpix">
                                                ??
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