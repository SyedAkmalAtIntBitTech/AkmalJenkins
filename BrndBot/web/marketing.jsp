<%-- 
    Document   : history
    Created on : Aug 25, 2015, 6:46:46 PM
    Author     : Syed Akmal at IntBit Technologies
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://momentjs.com/downloads/moment.min.js"></script>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>marketing campaign</title>

        <style type='text/css'>
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
            .fo{
                background-color: #C6C6C6;
                width: 220px;
                height: 35px;
            }
            
            #dvPriorityDialog, #dvFastingDialog, #preview{
                position:fixed;
                height:100%;
                background:cyan;
                width:600px;
                right:0px;
                margin-right: -610px;
            }
            #slider-button{
                position:fixed;
                width:20px;
                height:50px;
                right:-20px;
                background:green;
                top: 40px;
            }

            li {
                cursor: default;
            }
            
        </style>
        
    </head>
    <body ng-app class="claro">
        <div ng-controller="controllerMarketingCampaign" class="container" id="container"> 
            <div class="row">
                <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

            <jsp:include page="marketingsubmenu.html"/>

<script>

            $(document).ready(function()
            {
                var sliderDialog = "#dvPriorityDialog";
                var prevSliderDialog = "";
                $("#liPriority").click(function() {
                    sliderDialog = "#dvPriorityDialog";
                    $('#slider-button').click();
                    prevSliderDialog = "#dvPriorityDialog";
                });

                $("#liFasting").click(function() {
                    sliderDialog = "#dvFastingDialog";
                    $('#slider-button').click();
                    prevSliderDialog = "#dvFastingDialog";
                });

              $('#slider-button').click(function() {
                  //To hide the dialog if user click on another node
                  if (prevSliderDialog != "" && prevSliderDialog != sliderDialog){
                      if($('#slider-button').css("margin-right") == "400px")
                      {
                          $(prevSliderDialog).animate({"margin-right": '-=400'});
                          $('#slider-button').animate({"margin-right": '-=400'});
                      }
                  }

                if($('#slider-button').css("margin-right") == "400px")
                {
                    $(sliderDialog).animate({"margin-right": '-=400'});
                    $('#slider-button').animate({"margin-right": '-=400'});
                }
                else
                {
                    $(sliderDialog).animate({"margin-right": '+=400'});
                    $('#slider-button').animate({"margin-right": '+=400'});
                }
              });
             });     

            function validateEmail(sEmail) {
                var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
                if (filter.test(sEmail)) {
                    return true;
                }
                else {
                    return false;
                }
            }

            function validate() {
                var from_address = $("#from_address").val();
                var reply_email_address = $("#reply_email_address").val();
                
                if (from_address === "") {
                    alert("from address not entered, please enter the from address");
                    $("#from_address").focus();
                    return false;
                }
                
                if ($.trim(from_address).length == 0) {
                    alert('Please enter valid email address');
                    $("#from_address").focus();
                    return false;
                }
                
                if (!(validateEmail(from_address))) {
                    alert('Invalid Email Address');
                    $("#from_address").focus();
                    return false;
                }

                if (reply_email_address === "") {
                    alert("reply email not entered, please enter the reply email address");
                    $("#reply_email_address").focus();
                    return false;
                }
                
                if ($.trim(reply_email_address).length == 0) {
                    alert('Please enter valid email address');
                    $("#reply_email_address").focus();
                    return false;
                }
                
                if (!(validateEmail(reply_email_address))) {
                    alert('Invalid Email Address');
                    $("#reply_email_address").focus();
                    return false;
                }
                
                return true;
            }
            
            Date.prototype.customFormat = function(formatString){
              var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
              YY = ((YYYY=this.getFullYear())+"").slice(-2);
              MM = (M=this.getMonth()+1)<10?('0'+M):M;
              MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
              DD = (D=this.getDate())<10?('0'+D):D;
              DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
              th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
              formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
              h=(hhh=this.getHours());
              if (h==0) h=24;
              if (h>12) h-=12;
              hh = h<10?('0'+h):h;
              hhhh = h<10?('0'+hhh):hhh;
              AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
              mm=(m=this.getMinutes())<10?('0'+m):m;
              ss=(s=this.getSeconds())<10?('0'+s):s;
              return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
            };
            
            function addDays(theDate, days) {
                return new Date(theDate.getTime() + days*24*60*60*1000);
            }


            function controllerMarketingCampaign($scope, $http) {

                $scope.getCampaigns = function (){
//                    var picker = $("#some_datepicker").data('datetimepicker');
//                    
//                    var localDate = picker.getLocalDate(); // localDate === 2000-01-17 07:00
//                    var utcDate = picker.getDate(); // utcDate === 2000-01-17 10:00
//                    //
//                    alert(utcDate);
                    var curr_date = moment(new Date()).format('YYYY-MM-DD');
                    var tomorrowDate = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
                    var new_date = moment(addDays(new Date(), 15)).format('YYYY-MM-DD');
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledEntities?from='+curr_date+'&to='+new_date
                    }).success(function (data){
                        console.log(data);
                        entitySet = {};
                        $.each(data, function(key, value){
                            /*
                             * the below code is trying to create a model in the below form:
                             * {
                             *   'Today' : [{}, {}],
                             *   'Tomorrow': [{}, {}],
                             *   'Later': [{}, {}]
                             * }
                             */
                            if ( key == curr_date){
                                entitySet['Today'] = value;
                            }else if ( key == tomorrowDate){
                                entitySet['Tomorrow'] = value;
                            }else{
                                if (!('Later' in entitySet)){
                                    entitySet['Later'] = [];
                                }
                                $.each(value, function(key2, value2){
                                    entitySet['Later'].push(value2);
                                });
                            }
                        });
                        $scope.entitySet = entitySet;
                        console.log(entitySet);
                    }).error(function (data){
                       alert("request not successful"); 
                    });
                };
                
                $scope.getScheduleDetails = function (schedule_id,schedule_time){
                    
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledEmailDetail?schedule_id='+schedule_id
                    }).success(function (data){
                        $scope.entitiesdetails = data;
                        var date = new Date(schedule_time);
                        $scope.entities_time = date.toUTCString();
                        $(".content").empty();
                        $(".content").append(data.body);

                    }).error(function (data){
                       alert("request not successful"); 
                    });
                };
                
                
                $scope.setEmailSettings = function () {
                    var from_address = $("#from_address").val();
                    var reply_email_address = $("#reply_email_address").val();
                    if (validate()) {
                        var email_settings = {"from_address": from_address, "reply_email_address": reply_email_address, "type": "add"};

                        $http({
                            method: 'POST',
                            url: getHost() + 'EmailSettingsServlet',
                            headers: {'Content-Type': 'application/json'},
                            data: email_settings
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "false") {
                                alert("user session has expired, kindly resubmit a request");
                            } else if (data === "true") {
                                alert("settings saved successfully");
                                $("#from_address").val("");
                                $("#reply_email_address").val("");
                            } else if (data === error) {
                                alert(data);
                            }
                        }).error(function (data, status) {
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.

                            alert("request not succesful");
                        });

                    }
                };
            }
//                    var picker = $("#datetimepicker12").data('datetimepicker');
//                    alert(picker.getDate());
//
//                    $('#datetimepicker12').datetimepicker().on('changeDate', function (ev) {
//                        alert("test");
//                        var picker = $("#datetimepicker12").data('datetimepicker');
//                        alert(picker.getDate());
//                    });
//                    
//                    var localDate = picker.getLocalDate(); // localDate === 2000-01-17 07:00
//                    var utcDate = picker.getDate(); // utcDate === 2000-01-17 10:00

        </script>
            
            <div class="col-md-8 col-md-offset-3 " ng-init="getCampaigns()">
                <div class="col-md-6 col-md-offset-0"><p id="hyshead">Your Plan</p><button id="liPriority">ADD ACTION</button>&nbsp;&nbsp;<button id="liFasting">ADD Note</button> <p id="hyshead">Marketing Campaign</p>
                <div class="col-md-5 ">
                    
                        <ul>
                            <p>Today</p>
                            <li ng-repeat="entity in entitySet['Today']">
                                <div style="background-color:{{entity.color}};">
                                    <div>{{entity.schedule_title}}</div>
                                    <div>{{entity.schedule_time}}</div>
                                    <div>{{entity.entity_type}}</div>
                                </div>
                                <div class="foo col-md-1 col-md-offset-2" style="background-color:{{entity.color}};">
                                    <div class="fo col-md-2 col-md-offset-2" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)" >{{entity.schedule_title}}</div>
                                </div>
                            </li>
                            <p>Tomorrow</p>
                            <li ng-repeat="entity in entitySet['Tomorrow']">
                                <div style="background-color:{{entity.color}};">
                                    <div>{{entity.schedule_title}}</div>
                                    <div>{{entity.schedule_time}}</div>
                                    <div>{{entity.entity_type}}</div>
                                </div>
                            </li>
                            <p>Later</p>
                            <li ng-repeat="entity in entitySet['Later']">
                                <div style="background-color:{{entity.color}};">
                                    <div>{{entity.schedule_title}}</div>
                                    <div>{{entity.schedule_time}}</div>
                                    <div>{{entity.entity_type}}</div>
                                </div>
                                <div class="foo col-md-1 col-md-offset-2" style="background-color:{{entity.color}};">
                                    <div class="fo col-md-2 col-md-offset-2" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)" >{{entity.schedule_title}}</div>
                                </div>
                                
                            </li>
                        </ul>
                </div>
                </div>
            </div>
        <div id="dvSliderDialog">
            <div id="dvFastingDialog" class="pollSlider"><br>
                    Title : <input type="text" name="title" id="title"/><br>
                    <br/>
                    Description : <textarea cols="28" rows="4"></textarea><br>
                    Action Date : <input type="date" id="actiondate" name="actiondate"/><br>
                    Status : <select id="status" name="status">
                                <option value="0" >Select</option>
                                <option value="No template">No template</option>
                                <option value="Template saved">Template saved</option>
                                <option value="N/A">N/A</option>
                            </select>
                <div id="dvButtonContainer">
                        <input type="button" value="Save" />
                        <input type="button" value="Cancel" />
                </div>
            </div>
            <div id="dvPriorityDialog" class="pollSlider">
                <div id="dvPriorityContent"><br>
                    Title : <input type="text" name="title" id="title"/><br>
                    Type :  <select id="actiontype" name="actiontype">
                                <option value="0">Select</option>
                                <option value="Facebook">Facebook</option>
                                <option value="Twitter">Twitter</option>
                                <option value="Email">Email</option>
                                <option value="Note">Note</option>
                            </select>
                            <br/>
                            Description : <textarea cols="28" rows="4"></textarea><br>
                            Date : <input type="date" id="actiondate" name="actiondate"/><br>
                    Status : <select id="status" name="status">
                                <option value="0" >Select</option>
                                <option value="No template">No template</option>
                                <option value="Template saved">Template saved</option>
                                <option value="N/A">N/A</option>
                            </select>
                </div>
                <div id="dvButtonContainer">
                    <input type="button" value="Save" />
                    <input type="button" value="Cancel" />
                </div>
            </div>
            <div id="preview" class="pollSlider">
                <div >
                <div class="">

                    <div style="border:1px solid #7ab5d3">
                        <div>
                            <p>{{entitiesdetails.subject}}</p>
                        </div>
                        <p>Saved Post</p>
                        <p>Preview</p><br>
                        <div class="content"></div>
                        <p>Post details</p>
                        <div>
                            {{entities_time}}
                        </div>
                    </div>
                    
                    <!--                        <ul>
                            <li ng-repeat="entity in entities">
                                <div class=" col-md-1 col-md-offset-2" style="background-color:{{entity.color}};">
                                    <div class="fo col-md-2 col-md-offset-2" ng-click="getScheduleDetails(entity.schedule_id)" >{{entity.schedule_title}}</div>
                                </div>
                                
                            </li>
                        </ul>-->
                </div>
            </div>
                
            </div>
            <div id="slider-button"></div>
        </div>    
            
            
        </div>     
    </body>
</html>