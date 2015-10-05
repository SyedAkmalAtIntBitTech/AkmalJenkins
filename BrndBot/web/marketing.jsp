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
<!--        <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css"/>
        <link rel="stylesheet" href="css/bootstrap-responsive.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/bootstrap-timepicker.min.css">
        <script type="text/javascript" src="js/bootstrap-timepicker.min.js"></script>-->
<!--        <script type="text/javascript" src="js/jquery.min.js"></script>-->
<!--        <script type="text/javascript" src="js/bootstrap.min.js"></script>-->
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
        <title>marketing campaign</title>

        <style type='text/css'>
            input[type=checkbox],input[type=datetime]{
                cursor:pointer;
            }
            .arrow_top{
                top: 163px;
                left: 20px;
                transform: rotate(180deg);
            }
            .timepicker_wrap{
                  margin-top: -215px;
                    width: 263px;
            }
            div.selectBox
            {
                position:relative;
                display:inline-block;
                cursor:default;
                text-align:left;
                line-height:30px;
                clear:both;
                font-weight:600;
                color: #000000;
                border-top-left-radius: 5px;
                border-bottom-left-radius:  5px;
                background-color: #C6C6C6;
                border:transparent;
            }
            .selectBox{
                position:relative;
                display:inline-block;
                cursor:default;
                text-align:left;
                line-height:30px;
                clear:both;
                font-weight:600;
                color: #000000;
                border-top-left-radius: 5px;
                border-bottom-left-radius:  5px;
                background-color: #C6C6C6;
                border:transparent;

            }
            .foo {   
                float: left;
                width: 50px;
                height: 35px;
                margin: 5px;
                border-width: 0px;
                border-style: solid;
                border-color: rgba(0,0,0,.2);
                border-radius: 0px;
            }
            .foo1 {   
                float: left;
                width: 270px;
                height: 35px;
                margin: 5px;
                border-width: 1px;
                border-style: solid;
                border-color: rgba(0,0,0,.2);
                border-radius: 0px;
            }

            span.selected
            {
                width:167px;
                text-indent:10px;
                border:0px solid #ccc;
                border-top-left-radius: 5px;
                border-bottom-left-radius:  5px;
                border-right:none;
                background-color: #C6C6C6;
                overflow:hidden;
            }
            span.selectArrow
            {
                width:20px;
                text-align:center;
                font-size:22px;
                border-top-right-radius:5px;
                border-bottom-right-radius:5px;
                -webkit-user-select: none;
                -khtml-user-select: none;
                -moz-user-select: none;
                -o-user-select: none;
                user-select: none;
                background-image: url('images/dropdown.png');
                background-repeat: space;
            }

            span.selectArrow,span.selected
            {
                position:relative;
                float:left;
                height:30px;
                z-index:1;
            }

            div.selectOptions
            {
                position:absolute;
                top:35px;
                left:0;
                width:188px;
                border:1px solid #ccc;
                border-radius:7px;
                overflow:hidden;
                background:#f6f6f6;
                padding-top:2px;
                display:none;
            }

            span.selectOption
            {   

                display:block;
                width:100%;
                line-height:20px;
                padding:5px 10%;
            }

            span.selectOption:hover
            {
                color: #000000;
                background:#7ab5d3;

            }	

            #s11{
                position:relative;
                display:inline-block;
                cursor:default;
                text-align:left;
                line-height:30px;
                clear:both;
                color: #000000;
                background: #888;

            }
            .edtbtn{
                font-family: "proxima-nova",sans-serif;
                color: #888;
                width:70px;    
                background-color: transparent;
                border:1px #888 solid;
                border-radius: 5px;   
                position:relative;
            }

            .titrow{ position: relative;
                     margin-left:60px;
            }

            .fo{
                background-color: #C6C6C6;
                width: 220px;
                height: 35px;
            }
            #dvPriorityDialog, #dvFastingDialog {
                position:fixed;
                height:100%;
                background:#ffffff;
                width:600px;
                right:0px;
                margin-right: -600px;
                border: 1px solid #ccc;
            }

            #preview, #previewemail, #emailedit, #editfacebook, #edittwitter,
            #previewfb, #previewNote, #previewtwitter, #editNote{
                position:fixed;
                max-height:100%;
                background:#ffffff;
                width:600px;
                right:0px;
                margin-right: -600px;
                border: 1px solid #ccc;
            }
            #slider-button
            {
                position:fixed;
                width:70px;
                height:50px;
                right:0px;
                background:transparent;
                top: 0px;
            }

            li {
                cursor: default;
                list-style: none;
            }
            .content{
                overflow-y:scroll;
                overflow-x:hidden;
                height:400px;
                max-width:650px;
                position: absolute;
                margin-top:80px;
                margin-left: -70px;
            }
            .editcontent{
                overflow-y:scroll;
                overflow-x:hidden;
                height:400px;
                width:650px;
                position: absolute;
                margin-left: -130px;
            }
            .actiondetails{
                position: relative;
                width:250px;
                margin-top: 50px;
            }
            .actiondet p{
                position:relative;
                left:15px;
                top:10px;


            }

            .inputbox {
                position:relative;
                top:-10px;
                width:150px;
                height: 40px;
                border: none;
                border-bottom: 1px #888 solid; 
                border-radius: 2px;
                font-family: sans-serif;
                font-size: 12px;
            }
            .inputdate {
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                border-radius: 5px;
                width:220px;
                height: 30px;
                font-family: sans-serif;
                font-size: 14px;
                position: relative;
                top:2px;
                bottom:2px;
            }
            .inputtime{
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                border-radius: 5px;
                width:320px;
                height: 30px;
                font-family: sans-serif;
                font-size: 14px;
                position: relative;
                top:2px;
                bottom:2px;
            }
            #popupright_panel{
                left: 255px;
                position: relative;
            }
            .postdet{
                position: relative;
                margin-left:15px;
            }
            .postdetails{
                position: relative;
                width:250px;
                margin-top: 0em;
            }
            .editbutton{
                position: relative;
                margin-top: 10px;
                margin-bottom:10px;
            }
            .savebutton{
                position:relative;
                top:0px;
                left:-5px;
            }
            #planhead{
                position:relative;
                left:0px;
                padding-top:19%;
            }
            .dateinputbox{
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                height:20px;
                width:100px;
                font-size: 16px;
                border-radius: 5px;
                margin-left: 15px;
            }
            .selstatus{
                background-color: #ffffff;
                border: 1px solid #DADADA;
                height:30px;
                width:200px;
                font-size: 18px;
                border-radius: 12px;
                margin-left: 15px;
                padding-left:12px; 
                appearance: none;
                -moz-appearance: none;
                -webkit-appearance: none;
                background: url(images/dropdown.png) no-repeat right center,270px center;
            }
            
/*            .chooseEmailList {
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                height:30px;
                width:100px;
                font-size: 16px;
                border-radius: 5px;
                margin-left: 15px;
            }*/

            #chooseEmailList {
                border: none;
                width: 100px;
                height: 30px;
                -webkit-appearance: none;
                -moz-appearance: none;
                box-shadow: none !important;
                appearance: none;
                padding: 2px 1px 2px 2px;
                border: 2px solid #d0d0d0;
                border-left: none;
                border-right: none;
                border-top: none;
                border-bottom: none;
                border-radius: 2px;
                color: #2b2f35;
                background: url("images/dropdown.png") no-repeat right center;
                background-color: #F2F2F4;
                font-size: 18px;
                color: #3f4042; 
            }
            .fonthead{
                color:#888;
                font-variant:normal;
                font-size:1.8em;
                font-weight: 600;
            }
            .actfnt{
                color:#888;
                font-variant:normal;
                font-size:1.5em;
                font-weight: 600;
            }
            .fntschld{
                color:#888;
                font-variant:normal;
                font-size:1em;
                font-weight: 400;
            }
            .socfnts{
                font-size:1.2em;
                font-weight:400;
                width:150px;
                text-align:center;
                left:0px;
            }
            
            .black_overlay{
                display: none;
                position: absolute;
                top: 0%;
                left: 0%;
                width: 100%;
                height: 1000em;
                background-color: #E3E4E8;
                z-index:1000;
                -moz-opacity: 0.8;
                opacity:.80;
                filter: alpha(opacity=80);
            }
            #actiontype{
                border: none;
                width: 304px;
                height: 30px;
                -webkit-appearance: none;
                -moz-appearance: none;
                box-shadow: none !important;
                appearance: none;
                padding: 2px 1px 2px 2px;
                border: 2px solid #d0d0d0;
                border-left: none;
                border-right: none;
                border-top: none;
                border-bottom: none;
                border-radius: 2px;
                color: #2b2f35;
                background: url("images/dropdown.png") no-repeat right center;
                background-color: #F2F2F4;
                font-size: 18px;
                color: #3f4042; 
             }
             .actiondropdown{
                border: none;
                width: 304px;
                height: 30px;
                -webkit-appearance: none;
                -moz-appearance: none;
                box-shadow: none !important;
                appearance: none;
                padding: 2px 1px 2px 2px;
                border: 2px solid #d0d0d0;
                border-left: none;
                border-right: none;
                border-top: none;
                border-bottom: none;
                border-radius: 2px;
                color: #2b2f35;
                background: url("images/dropdown.png") no-repeat right center;
                background-color: #F2F2F4;
                font-size: 18px;
                color: #3f4042; 
             }
                         
            select#actiontype > option{
                background-color: #fff;
            }

            /*            #mask{  create are mask 
                position:fixed;
                top:0;
                left:0px;
                background:rgba(0,0,0,0.3);
                z-index:500;
                width:51.5%;
                height:100%;
                display:none;
            }
            #overlay:target, #overlay:target + #mask{
                display:block;
                opacity:1;
            }*/

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
                <div class="col-md-6 col-md-offset-1">

                    <div>
                        <p id="planhead" class="MH2">Your Plan</p>
                        <a href = "javascript:void(0)" onclick = "overlay();">
                            <button id="liPriority" class="button button--moema button--text-thick button--text-upper button--size-s" style="z-index:0;background-color:#E65C00;width:120px;position:relative;margin-top:-47px;margin-left:13em;">ADD ACTION</button>
                        </a>
                        <button id="delsel" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:160px;z-index:0;position:relative;margin-top:-47px;margin-left:25em;display:none;" ng-click="deleteSchedule('0','deleteMultiple')">DELETE SELECTED</button> 
                    </div>


                    <div class="col-md-12" style="display: none;" id="default" ng-init="getCampaigns()">
                        <div class="row" style="width:750px;position:relative;top:25px;margin-left:-15px;">

                            <!--<div class='col-md-1 SP2 fonthead'>Today</div>-->
                            <div class='col-md-3' style="width:280px;"></div>
                            <div class='col-md-3 SS2' style="margin-left:90px;">Action Type</div>
                            <div class='col-md-2 SS2' style="margin-left:-20px;">Template Saved</div>
                            <div class='col-md-3' ></div></div> 

                        <div id="daydetails" ng-repeat="entity in entitySet">
                        <div ng-show="entity.date == today_date">
                            <p class="SS2 actfnt">Today</p>
                        </div>
                             
                        <div ng-show='entity.date==tomorrow_date'>
                            <p class="SS2 actfnt">Tomorrow</p>
                        </div>
                        <div ng-show="(entity.date != today_date) && (entity.date!=tomorrow_date)">
                            <p class="SS2 actfnt">{{entity.date}}</p>
                        </div>
                        <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-15px;top:-20px;">
                        <p ng-show="entity.dataArray == ''" class="MH1" id="messagetoday" style="display:block;position:relative;top:-25px;" >{{nodata}}</p>
                        <ul>
                            <li ng-repeat="entitydetails in entity.dataArray">
                                <div class="row" style="width:950px;position:relative;left:-40px;top:-20px;" id="entitydetails" >
                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetodays" id='{{entitydetails.schedule_id}}' style="width:15px;" value="{{entitydetails.schedule_id}}" onclick="setSelectedIds('{{entitydetails.schedule_id}}')"/>
                                    </div>
                                    <div class="col-md-3" style="width:300px;margin-left:-40px;">
                                        <a href = "javascript:void(0)" onclick = "overlay();" style="color:#333;text-decoration: none;">
                                            <p class="MH1" ng-click="getScheduleDetails(entitydetails.schedule_id, entitydetails.template_status, entitydetails.schedule_time, entitydetails.entity_type, entitydetails.schedule_title, entitydetails.schedule_description)">{{entitydetails.schedule_title}}</p></a>
                                        <p class="SP1 fntschld">Scheduled for {{entitydetails.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entitydetails.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entitydetails.template_status}}</div>
                                    <div class="col-md-2" 
                                         style="margin-left:20px;" 
                                         ng-hide="(entitydetails.template_status=='Complete') && (entitydetails.entity_type!='note')">
                                        <a href = "javascript:void(0)" onclick = "overlay();">
                                        <button type="button" 
                                                class="edtbtn" 
                                                ng-click="showScheduleDetails(
                                                            entitydetails.schedule_id, 
                                                            entitydetails.schedule_time, 
                                                            entitydetails.entity_type, 
                                                            entitydetails.schedule_title, 
                                                            entitydetails.schedule_description
                                                            )">EDIT</button>
                                        </a>
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
                <div id="dvPriorityContent" style="position:relative;top:30px;left:100px;"><br>
                    <h1 class="SP2 actfnt">ADD ACTION</h1>
                    <form class="form-horizontal" id="signform" >
                        <div class="group" style="position:relative;margin-left:-50px;">
                             <div class="col-md-3 col-md-offset-1">                            
                                 <input id="title" class="brdr form-control simplebox inputbox MH1" name="title" type="text" required="" style="position:relative;top:0px;line-height:30px;width:300px;font-size:22px;">
                                 <label class="MH2" style="font-size:22px;">TITLE</label><br>

                             </div>
                        </div>
                        
                            <div style="position:absolute;left:15px;top:50px;" class="SH2">
                                Type :  <select id="actiontype" class="SS1" name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 18px;width:100px;border-radius:5px;border:none;">
                                    <option value="0">Select</option>
                                    <option value="facebook">facebook</option>
                                    <option value="twitter">twitter</option>
                                    <option value="email">email</option>
                                    <option value="note">note</option>
                                </select></div>
                            <div style="position:absolute;top:100px;left:15px;" class="SH2">
                                Description <br><textarea cols="28" rows="2" name="description" id="description" class="SS2" style="font-variant:normal;resize: none;"></textarea>
                            </div>
                            <div class="SH2" style="position:absolute;top:190px;left:15px;" >
                                Date 
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
                                        TIME
                                     <input id="timepicker1" type="text" name="timepicker1" class="inputtime MH1 ptr" style="width:150px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker1').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                            </div>
                            
                            <!--                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>-->
                           
                   
                        <div class="row">
                            <div class="col-md-12" style="width:250px;position:absolute;top:380px;left:-15px;">

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

                </div></div>
            <div id="preview" class="pollSlider" style="z-index:1005;overflow-y:scroll;overflow-x:hidden;height:100%;">
                <div>
                    <div id="preview_email" style="display:block;">

                        <div class="actiondet">

                            <div style="background-color:#fff;position:relative;top:25px;left:50px;">

                                <div class="actiondetails actiondet">
                                    <p class="SP2 actfnt">ACTION DETAILS</p>
                                    <p class="MH2" style="width:300px;"> {{schedule_title}}</p>
                                    <!--                                    <p class="MH2" style="width:500px;">Description: {{schedule_desc}}</p>-->
                                </div>
                                <div class="SP1 actfnt" style="position:relative;left:15px;">Saved Email <div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div></div>

                                <div class="content"></div> <br>

                                <div style="position:absolute;margin-top:280px;">
                                    <p class="postdetails SP1 actfnt">SENDING DETAILS</p>
                                    <div class="actiondet">
                                        <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    </div>

                                    <div style="position:relative;
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
                                    <div style="position:relative;
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
                        </div>
                    </div>
                    <div id="edit_email_action" style="position:relative;left:50px;top:70px;height: 100%;">
                        <h1 class="SP1 actfnt" style="font-size:2em;margin-left:45px;">UPDATE ACTION</h1>
                        <form class="form-horizontal" id="signform">

                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">                            
                                    <input id="email_edit_title" class=" inputbox MH2" type="text" name="email_edit_title" value="{{schedule_title}}" style="position:relative;top:7px;line-height:40px;width:400px;font-size:26px;"/>
                                    <p><input class="inputbox SP1" type="hidden" name="email_scheduleid" id="email_scheduleid" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>

                                    <!--                                    <label>TITLE</label>-->
                                </div><br>
                                <div style="position:absolute;left:60px;top:60px;" class="SH2">
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p><input class="inputbox SP1" type="hidden" name="email_schedule_type" id="email_schedule_type" value='{{schedule_type}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                    <!--                                    Type :  <select id="actiontype" class="SS1" name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 15px;width:100px;color:#444;background-color: #fff;border:none;border-bottom: 1px solid #000;">
                                                                            <option value="0">Select</option>
                                                                            <option value="facebook">facebook</option>
                                                                            <option value="twitter">twitter</option>
                                                                            <option value="email">email</option>
                                                                            <option value="note">note</option>
                                                                        </select>-->
                                </div>
                                <div style="position:absolute;top:110px;left:60px;" class="SP1 actfnt">
                                    Description <br><textarea cols="28" rows="2" name="email_description" id="email_description" class="SS2" style="font-variant:normal;resize: none;">{{schedule_desc}}</textarea>
                                </div>
                                <div class="SH2" style="position:absolute; margin-top: 190px; margin-left: 60px;" >
                                    <p class="SP1 actfnt" style="font-weight:400;font-size:1.2em;">Selected Date: {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <p class="SP1 actfnt" style="font-weight:500;font-size:1.2em;">Date</p>
                                    <!--<input type="datetime-local" name="emaildatetime" id="emaildatetime" class="inputdate MH1"/>-->

                                <input type="text" readonly class="inputdate MH1 ptr" name="emaildatetime" id="emaildatetime">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('emaildatetime'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><br><p class="SP1 actfnt" style="font-weight:500;font-size:1.2em;">TIME</p>
                                    <input id="timepickeremail" type="text" name="timepicker_email" class="inputtime MH1 ptr" style="width:150px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepickeremail').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                    
                                </div>

                                <!--                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>-->

                            </div>
                            <div class="row">
                                <div class="col-md-12" style="width:250px;position:absolute;top:430px;left:35px;">

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
                    </div>
                    <div id="edit_email" style="display:none;">

                        <div style="position:relative;left:50px;;">
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
                                    <!--                                        <div><label>subject</label></div>-->
                                    <div><p class="SP2 actfnt" style="font-weight:400;">Subject Line </p><input type="text" class="inputbox" name="email_entitysubject" id="email_entitysubject" value="{{entitiesdetails.subject}}"/></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">To</p><input type="text" class="inputbox" name="email_entitytoaddress" id="email_entitytoaddress" value="{{entitiesdetails.to_email_addresses}}"></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">From</p><input type="text" class="inputbox" name="email_entityfromaddress" id="email_entityfromaddress" value="{{entitiesdetails.from_address}}"></div>
                                    <div><p class="SP2 actfnt" style="font-weight:400;">Reply To</p><input type="text" class="inputbox" name="email_entityreplytoaddress" id="email_entityreplytoaddress" value="{{entitiesdetails.reply_to_email_address}}"></div>
                                </div>
                            </div>
                            <div style="position: relative;margin-left:0px;margin-top:0px;">
                                <p class="postdetails postdet SP1 actfnt">SENDING DETAIL</p>
                                <div class="actiondet" >
                                    <select class="SP1" id="chooseEmailList" class="actiondropdown" name="chooseEmailList" style="position: relative;margin-left:15px">
                                        <option style="background:#fff;" value="0">Select</option>
                                        <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                    </select>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{entitiesdetails.email_list_name}}</p>
                                    <div class="SH2" style="position:absolute;margin-top:10px;left:15px;" >
                                  
                                        <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                <input type="text" readonly  name="email_schedule_datetime" id="email_schedule_datetime"  class="inputdate MH1 ptr"  >
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('email_schedule_datetime'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><br>
                                       <div class="SP1 actfnt"  style="font-size:1.2em;"> TIME</div>
                                     <input id="timepickeremailaction" type="text" name="timepickeremail" class="inputtime MH1 ptr" style="width:150px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepickeremailaction').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                    </div>
                                    <div style="position:relative;margin-top:170px;"> 
                                    <!--<input type="datetime-local" class="inputdate postdet " name="email_schedule_datetime" id="email_schedule_datetime"/>-->
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <div class="editbutton"><button ng-click="deleteSchedule(schedule_id,'remove')" class="button button--moema button--text-thin button--text-upper button--size-s" style="background-color:#444;width:230px;" type="button">REMOVE SAVED TEMPLATE</button> </div>
                                    <div class="editbutton"><button ng-click="updateEmailSchedule()" class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div></div>

            <div id="previewfb" class="pollSlider" style="z-index:1005;height:100%;overflow-y:scroll;overflow-x:hidden;">
                <div>
                    <div id="preview_facebook">


                        <div style="position:relative;left:50px;">
                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:400px;">{{schedule_title}}</p>
                                <p class="SP1 actfnt" style="font-size:15px;font-weight:400;">{{schedule_desc}}</p>
                                <p class="SP1 actfnt">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                            </div>

                            <img id="prevfbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' 
                                 style="position:relative;top:-30px;left:-100px;
                                 transform: scale(0.45, 0.5);
                                 -ms-transform: scale(0.45, 0.5);
                                 -webkit-transform: scale(0.58, 0.7);
                                 display:none;"/>
                            <div style="position:absolute;top:170px;left:300px;">

                                <p class="SP1 actfnt">{{entitiesdetails.metadata.post_text}}</p>
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.url}}</p>
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.description}}</p>
                            </div>
                            <div id="fb_preview_postdet" style="position:absolute;margin-left:15px;margin-top:-60px;">
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{entitiesdetails.metadata.ManagedPage}}</p>
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
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

                            </div>
                        </div>
                    </div>
                    <div id="edit_facebook_action" style="position:relative;left:50px;top:50px;">
                         <h1 class="SP1 actfnt" style="font-size:2em;margin-left:45px;">UPDATE ACTION</h1>
                        <form class="form-horizontal" id="signform">

                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">                            
                                    <input id="fb_action_title" class=" inputbox MH2" type="text" name="fb_action_title" value="{{schedule_title}}" style="position:relative;top:0px;line-height:40px;width:400px;font-size:26px;"/>
                                    <p><input class="inputbox SP1" type="hidden"
                                              name="fb_scheduleid" 
                                              id="fb_scheduleid"
                                              value='{{schedule_id}}' 
                                              style="position:relative;
                                              top:10px;font-size:15px;
                                              font-weight:400;line-height:10px;
                                              width:300px;"/>
                                    </p>
<!--                                    <label>TITLE</label>-->
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
<!--                                Type :  <select id="actiontype" class="SS1" name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 15px;width:100px;color:#444;background-color: #fff;border:none;border-bottom: 1px solid #000;">
                                        <option value="0">Select</option>
                                        <option value="facebook">facebook</option>
                                        <option value="twitter">twitter</option>
                                        <option value="email">email</option>
                                        <option value="note">note</option>-->
                                    <!--</select>-->
                                </div>
                                <div style="position:absolute;top:110px;left:60px;" class="SP1 actfnt">
                                    Description <br><textarea cols="28" rows="2" name="fb_description" id="fb_description" class="SS2" style="font-variant:normal;resize: none;">{{schedule_desc}}</textarea>
                                </div>
                                <div class="SH2" style="position:absolute; margin-top: 180px; margin-left: 60px;" >
                                    <p class="SP1 actfnt" style="font-weight:400;font-size:1.2em;">Selected Date: {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                <input type="text" readonly class="inputdate MH1 ptr" name="fbdatetime" id="datepicker2">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker2'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                    </script><br><div class="SP1 actfnt"  style="font-size:1.2em;"> TIME</div>
                                    <input id="timepicker2" type="text" name="timepicker1" class="inputtime MH1 ptr" style="width:150px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker2').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                </div>

                                <!--                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>-->

                            </div>
                            <div class="row">
                                <div class="col-md-12" style="width:250px;position:absolute;top:420px;left:35px;">

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

                    <div id="edit_facebook">

                        <div style="position:relative;left:50px;top:-10px;">

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
                            <img id="edtfbimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' style="position:relative;top:-60px;left:-120px;transform: scale(0.45, 0.45);-ms-transform: scale(0.45, 0.45);-webkit-transform: scale(0.5, 0.6);display:none;"/>
                            <p><input class="inputbox SP1" type="hidden" name="facebook_image_name" id="facebook_image_name" value='{{entitiesdetails.image_name}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                            <div style="position:absolute;top:130px;left:300px;">
                                <p><input type="text" name="facebook_schedule_posttext" id="facebook_schedule_posttext" value='{{entitiesdetails.metadata.post_text}}' class="actfnt" style="font-weight:300;width:230px;"/></p>
                                <p><input type='text' name="facebook_schedule_url" id="facebook_schedule_url"  value='{{entitiesdetails.metadata.url}}' class="actfnt" style="font-weight:300;width:230px;"/></p>
                                <p><input type='text' name='facebook_schedule_description' id="facebook_schedule_description" value='{{entitiesdetails.metadata.description}}' class="actfnt" style="font-weight:300;width:230px;"/></p>
                            </div>
                            <div style="position:absolute;margin-left:10px;margin-top:-100px;">
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    
                                    
                                    
                                    
                                    <input type='datetime-local' name="facebook_schedule_datetime" id="facebook_schedule_datetime" class="inputdate"/><br>
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <input type="text" name="social_type" id="social_type" value="{{schedule_type}}">
                                </div>
                                <div class="removesavetemplate"><button ng-click="deleteSchedule(schedule_id,'remove')" class="button button--moema button--text-thin button--text-upper button--size-s" style="background-color:#444;width:230px;" type="button">REMOVE SAVED TEMPLATE</button> </div>
                                <div class="savebutton" ng-click="updateSocialSchedule()"><button class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                            </div>
                        </div>
                    </div>                        
                </div>

            </div>

            <div id="previewNote" class="pollSlider" style="z-index:1005;height:100%;overflow-y:scroll;overflow-x:hidden;">
                <div>
                    <div id="noteprev">

                        <div style="position:relative;left:50px;">

                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:400px;"> {{schedule_title}}</p>
                                <p class="SP1 actfnt" style="font-size:15px;font-weight:400;">{{schedule_desc}}</p>
                                <p class="SP1 actfnt">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                            </div>

                            <div style="position:absolute;margin-left:15px;margin-top:50px;">
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                </div>

                                <div style="position:relative;left:-10px;">
                                    <button id="button_edit" ng-click="editScheduleDetails(schedule_id, entities_selected_time, schedule_type, schedule_title, schedule_desc)" class="button button--moema button--text-thick button--text-upper button--size-s" style="background-color:#E65C00;width:120px;" type="button" >EDIT</button> 
                                </div>

                            </div>

                        </div>
                    </div>
                    <div id="noteedit" style="display:none;">

                        <div style="position:relative;left:50px;">

                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:500px;"><input type="text" id="note_title" name="note_title" value="{{schedule_title}}"/></p>
                                <p class="SP1 actfnt" style="font-size:15px;font-weight:400;">Description:<input type="text" id="note_desc" name="note_desc" value="{{schedule_desc}}"/></p>
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
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    
                                    
                                     <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                     <input type="text" readonly="true" name="notedate" class="inputdate MH1 ptr" id="datepicker4" style="width:150px;">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker4'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><br><div class="SP1 actfnt"  style="font-size:1.2em;"> TIME</div>
                                        <input id="timepicker3" type="text" name="timepicker2" class="inputtime MH1 ptr" style="width:150px;"/><br>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker3').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                              
                                    
                                    
                                    
                               
                                    <!--<input type="datetime-local" id="notedate" name="notedate"  class="inputdate"/><br>-->
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                </div>
                                <div class="savebutton" ng-click="updateNote()"><button class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

            <div id="previewtwitter" class="pollSlider" style="z-index:1005;height:100%;overflow-y:scroll;overflow-x:hidden;">
                <div>
                    <div id="preview_twitter">

                        <div style="position:relative;left:50px;">

                            <div class="actiondetails actiondet">
                                <p class="SP2 actfnt">ACTION DETAILS</p>
                                <p class="MH2" style="width:400px;">{{schedule_title}}</p>
                                <p class="SP1 actfnt" style="font-size:15px;font-weight:400;"> {{schedule_desc}}</p>
                            </div>
                            <br><p class="SP1 actfnt" style="margin-left:15px;">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-35px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>

                            <img id="prevtwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' style="position:relative;top:-50px;left:-100px;transform: scale(0.5);-ms-transform: scale(0.5);-webkit-transform: scale(0.6);display:none;"/>
                            <div style="position:relative;top:-230px;left:310px;">
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.text}}</p>
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.shorturl}}</p>
                            </div>
                            <div id="twitter_preview_postdet" style="position:relative;margin-left:15px;margin-top:-100px;">  
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                </div>
                                <div ng-hide="twitter_template_status=='Complete'" style="position:relative;bottom:0px;top:0px;left:15px;" class="editbutton">
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
                            </div>
                        </div>
                    </div>
                    <div id="edit_twitter_action" style="position:relative;top:50px;left:50px;">
                        <h1 class="SP1 actfnt" style="font-size:2em;margin-left:45px;">UPDATE ACTION</h1>
                        <form class="form-horizontal" id="signform">

                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">
                                    <input id="edit_twitter_title" class=" inputbox MH2" type="text" name="edit_twitter_title" value="{{schedule_title}}" style="position:relative;top:0px;line-height:40px;width:400px;font-size:26px;"/>
                                    <p><input class="inputbox SP1" type="hidden" name="twitter_scheduleid" id="twitter_scheduleid" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                    <!--                                    <label>TITLE</label>-->
                                </div><br>
                                <div style="position:absolute;left:60px;top:50px;" class="SH2">
                                    <p class="SP1 actfnt" style="font-weight:400;">{{schedule_type}}</p>
                                    <p><input class="inputbox SP1" type="hidden" name="twitter_action_type" id="twitter_action_type" value='{{schedule_type}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                    <!--                                    Type :  <select id="actiontype" class="SS1" name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 15px;width:100px;color:#444;background-color: #fff;border:none;border-bottom: 1px solid #000;">
                                                                            <option value="0">Select</option>
                                                                            <option value="facebook">facebook</option>
                                                                            <option value="twitter">twitter</option>
                                                                            <option value="email">email</option>
                                                                            <option value="note">note</option>
                                                                        </select>-->
                                </div>
                                <div style="position:absolute;top:110px;left:60px;" class="SP1 actfnt">
                                    Description <br><textarea cols="28" rows="2" name="twitter_description" id="twitter_description" class="SS2" style="font-variant:normal;resize: none;">{{schedule_desc}}</textarea>
                                </div>
                                <div class="SH2" style="position:absolute; margin-top: 190px; margin-left: 60px;" >
                                    <p class="SP1 actfnt" style="font-weight:400;font-size:1.2em;">Selected Date: {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <div class="SP1 actfnt"  style="font-size:1.2em;"> Date</div>
                                <input type="text" readonly="true" class="inputdate MH1 ptr" name="twitterdatetime" id="datepicker3">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('datepicker3'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><br><div class="SP1 actfnt"  style="font-size:1.2em;"> TIME</div>
                                    <input id="timepickertwitter" type="text" name="timepicker1" class="inputtime MH1 ptr" style="width:150px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepickertwitter').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                                </div>

                                <!--                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>-->

                            </div>
                            <div class="row">
                                <div class="col-md-12" style="width:250px;position:absolute;top:420px;left:35px;">

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

                    <div id="edit_twitter">
                        <div style="position:relative;left:50px;top:-10px;">
                            <div class="actiondetails actiondet" >
                                <p class="SP2 actfnt">ACTION DETAILS</p><p class="SP1 ptr" ng-click="deleteSchedule(schedule_id,'delete')" style="position:absolute;left:400px;width:130px;font-size:1.1em;">DELETE ACTION</p>
                                <p><input class="inputbox MH2" type="text" name="twitter_schedule_title" id="twitter_schedule_title" value='{{schedule_title}}' style="position:relative;top:7px;line-height:30px;width:300px;font-size:22px;"/></p>
                                <p><input class="inputbox SP1" type="text" name="twitter_schedule_Description" id="twitter_schedule_Description" value='{{schedule_desc}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="twitter_schedule_id" id="twitter_schedule_id" value='{{schedule_id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="twitter_entity_id" id="twitter_entity_id" value='{{entitiesdetails.id}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="twitter_entity_accesstoken" id="twitter_entity_accesstoken" value='{{entitiesdetails.token_data.access_token}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="twitter_entity_tokensecret" id="twitter_entity_tokensecret" value='{{entitiesdetails.token_data.token_secret}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                                <p><input class="inputbox SP1" type="hidden" name="isTwitter" id="isTwitter" value='false' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>

                            </div>
                            <br><p class="SP1 actfnt" style="margin-left:15px;">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-35px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                            <img id="edttwtimg" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' style="position:relative;top:-60px;left:-100px;transform: scale(0.45, 0.45);-ms-transform: scale(0.45, 0.45);-webkit-transform: scale(0.6, 0.5);display:none;"/>
                            <p><input class="inputbox SP1" type="hidden" name="twitter_image_name" id="twitter_image_name" value='{{entitiesdetails.image_name}}' style="position:relative;top:10px;font-size:15px;font-weight:400;line-height:10px;width:300px;"/></p>
                            <div style="position:relative;top:-270px;left:310px;">
                                <p><input type="text" name="twitter_schedule_post_text" id="twitter_schedule_post_text" value='{{entitiesdetails.metadata.text}}' class="actfnt" style="font-weight:300; width:230px;"/></p>
                                <p><input type="text" name="twitter_schedule_post_url" id="twitter_schedule_post_url" value='{{entitiesdetails.metadata.shorturl}}' class="actfnt" style="font-weight:300; width:230px;"/></p>
                            </div>
                            <div style="position:relative;margin-left:10px;margin-top:-180px;">
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    
                                    
                                    <p class="SP1 actfnt" style="font-weight:500;font-size:1.2em;">Date</p>
                                    <!--<input type="datetime-local" name="emaildatetime" id="emaildatetime" class="inputdate MH1"/>-->

                                <input type="text" readonly class="inputdate MH1 ptr" name="twitter_schedule_date" id="twitter_schedule_date">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('twitter_schedule_date'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });

                                        </script><br><p class="SP1 actfnt" style="font-weight:500;font-size:1.2em;">TIME</p>
                                    <input name="timepicker_twittertime" id="timepicker_twittertime" type="text"  class="inputtime MH1 ptr" style="width:150px;"/>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#timepicker_twittertime').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script><br>
                                    
                                    <!--<input type='datetime-local'  class="inputdate"/><br>-->
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'MMM dd yyyy'+' on '+'h:mma'}}</p>
                                    <input type="text" name="social_type" id="social_type" value="{{schedule_type}}">
                                </div>
                                <div style="margin-left:-10px;" class="removesavetemplate"><button ng-click="deleteSchedule(schedule_id,'remove')" class="button button--moema button--text-thin button--text-upper button--size-s" style="background-color:#444;width:230px;" type="button">REMOVE SAVED TEMPLATE</button> </div>
                                <div class="savebutton" ng-click="updateSocialSchedule()"><button class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

            <div id="light" class="white_content">
                <a href = "javascript:void(0)" onclick = "closeoverlay();" style="text-decoration:none;">
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
<!--        <script type='text/javascript'>
	$('#timepicker1').timepicki({show_meridian:false});
</script>-->

    </body>
</html>