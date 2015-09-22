<%-- 
    Document   : history
    Created on : Aug 25, 2015, 6:46:46 PM
    Author     : Syed Akmal at IntBit Technologies
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
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
            #previewfb, #previewNote, #previewtwitter{
                position:fixed;
                height:100%;
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
                right:-0px;
                background:#7ab5d3;
                top: 0px;
            }

            li {
                cursor: default;
                list-style: none;
            }
            .content{
                position: relative;
                max-width:200px;
                height: 250px;
                margin-top: 0px;
               
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
             height: 30px;
             border: none;
             border-bottom: 1px #444 solid; 
             border-radius: 2px;
             font-family: sans-serif;
             font-size: 12px;
         }
         .inputdate {
             width:200px;
             height: 20px;
             border: 1px solid #888;
             border-radius: 3px;
             font-family: sans-serif;
             font-size: 12px;
         }
         #popupright_panel{
             left: 275px;
             position: relative;
         }
         .postdet{
             position: relative;
             margin-left:15px;
         }
            .postdetails{
                position: relative;
                width:250px;
                margin-top: 8em;
            }
            .editbutton{
                position: relative;
                margin-top: 10px;
                margin-bottom:10px;
            }
            #planhead{
                position:relative;
                left:15px;
                padding-top:19%;
            }
             #chooseEmailList {
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                height:20px;
                width:100px;
                font-size: 16px;
                border-radius: 5px;
                margin-left: 15px;
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
           
        </style>

    </head>
    <body ng-app class="claro">
        <div ng-controller="controllerMarketingCampaign" id="controllerMarketingCampaign" class="container"> 
            <div class="row">
                <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

            <jsp:include page="marketingsubmenu.html"/>

            <script src="js/marketing.js" type="text/javascript"></script>
            <div class="col-md-8 col-md-offset-3 " >
                <div class="col-md-6 col-md-offset-0">

   
                    <p id="planhead" class="MH2">Your Plan</p><button id="liPriority" class="button button--moema button--text-thick button--text-upper button--size-s" style="z-index:0;background-color:#E65C00;width:120px;position:relative;margin-top:-47px;margin-left:13em;">ADD ACTION</button>
                    <button ng-click="getSelectedCampaigns()" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:160px;z-index:0;position:relative;margin-top:-47px;margin-left:25em;">DELETE SELECTED</button> 
                    

                   
                    <div class="col-md-12" id="default" ng-init="getCampaigns()">
                         
                        <div class="row" style="width:950px;margin-top:30px;margin-left:-15px;">
                            <div class='col-md-1 SP2 fonthead'>Today</div>
                            <div class='col-md-3' style="width:230px;"></div>
                            <div class='col-md-2 SS2'>Action Type</div>
                            <div class='col-md-3 SS2'>Template Saved</div>
                            <div class='col-md-3' ></div>
                        </div>
                        <ul>
                            <li ng-repeat="entity in entitySet['Today']">
                                <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-40px;top:-20px;">
                                <div class="row" style="width:950px;position:relative;left:-50px;top:-20px;" id="entitydetails" >
                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetodays" id='{{entity.schedule_id}}' style="width:15px;" value="{{entity.schedule_id}}" onclick="setSelectedIds('{{entity.schedule_id}}')"/>
                                    </div>
                                    <div class="col-md-3" style="width:300px;margin-left:-40px;">
                                        <p class="MH1" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">{{entity.schedule_title}}</p>
                                        <p class="SP1 fntschld">Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entity.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entity.template_status}}</div>
                                    <div class="col-md-2" style="margin-left:40px;"><button type="button" class="edtbtn" ng-click="showScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;margin-top:30px;margin-left:-15px;">
                            <div class='col-md-3 SP2 fonthead'>Tomorrow</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>

                        <ul>   
                            <li ng-repeat="entity in entitySet['Tomorrow']">
                                <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-40px;top:-20px;">
                                <div class="row" style="width:950px;position:relative;left:-50px;top:-20px;" id="entitydetails">
                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetomorrow" id='{{entity.schedule_id}}' style="width:15px;" value="{{entity.schedule_id}}" onclick="setSelectedIds('{{entity.schedule_id}}')"/>
                                    </div>

                                    <div class="col-md-3"  style="width:300px;margin-left:-40px;">

                                        <p class="MH1" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">{{entity.schedule_title}}</p>
                                        <p class="SP1 fntschld">Scheduled for {{ entity.schedule_time | date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entity.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entity.template_status}}</div>
                                    <div class="col-md-2" style="margin-left:40px;"><button type="button" class="edtbtn" ng-click="showScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)" >EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;margin-top:30px;margin-left:-15px;">
                            <div class='col-md-3 SP2 fonthead'>Later</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>
                        <ul>
                            <li ng-repeat="entity in entitySet['Later']">
                                <div>{{entity.schedule_time| date:"MM/dd/yyyy"}}</div>
                                <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-40px;top:-20px;">
                                <div class="row" style="width:950px;position:relative;left:-50px;top:-20px;" id="entitydetails">

                                    <div class="col-md-1">
                                        <input type="checkbox" name="removeLater" id='{{entity.schedule_id}}' style="width:15px;" value="{{entity.schedule_id}}" onclick="setSelectedIds('{{entity.schedule_id}}')"/>
                                    </div>
                                    <div class="col-md-3"  style="width:300px;margin-left:-40px;">
                                        <p class="MH1" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">{{entity.schedule_title}}</p>
                                        <p class="SP1 fntschld">Scheduled for {{ entity.schedule_time | date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entity.entity_type}}</div>

                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entity.template_status}}</div>
                                    <div class="col-md-2" style="margin-left:40px;"><button type="button" class="edtbtn" ng-click="showScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)" >EDIT</button> </div>

                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-12" id="selected" style="display:none;">
                        
                        <div class="row" style="width:950px;margin-top:30px;margin-left:130px;">
                            <div class='col-md-3' style="width:230px;"></div>
                            <div class='col-md-2 SS2'>Action Type</div>
                            <div class='col-md-3 SS2'>Template Saved</div>
                            <div class='col-md-3'></div>
                        </div>

                        <ul>
                            <li ng-repeat="entity in entitySetSelected['Today']">
                                <p>{{entity.schedule_time| date:"MM/dd/yyyy"}}</p>
                                <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-40px;top:-20px;">
                                <div class="row" style="width:950px;position:relative;left:-50px;top:-20px;" id="entitydetails">

                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetodays" id='{{entity.schedule_id}}' style="width:15px;" value="{{entity.schedule_id}}" onclick="setSelectedIds('{{entity.schedule_id}}')"/>
                                    </div>
                                    <div class="col-md-3"  style="width:300px;margin-left:-40px;">

                                        <p  class="MH1" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">{{entity.schedule_title}}</p>
                                        <p  class="SP1 fntschld">Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>

                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entity.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entity.template_status}}</div>


                                    <div class="col-md-2" style="margin-left:40px;"><button type="button" class="edtbtn" ng-click="showScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)" >EDIT</button> </div>

                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;margin-top:30px;margin-left:-15px;">
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>

                        <ul>   
                            <li ng-repeat="entity in entitySetSelected['Tomorrow']">
                                <p>{{entity.schedule_time| date:"MM/dd/yyyy"}}</p>
                                <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-40px;top:-20px;">
                                <div class="row" style="width:950px;position:relative;left:-50px;top:-20px;" id="entitydetails">

                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetodays" id='{{entity.schedule_id}}' style="width:15px;" value="{{entity.schedule_id}}" onclick="setSelectedIds('{{entity.schedule_id}}')"/>
                                    </div>
                                    <div class="col-md-3"  style="width:300px;margin-left:-40px;">

                                        <p class="MH1" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">{{entity.schedule_title}}</p>
                                        <p class="SP1 fntschld">Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entity.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entity.template_status}}</div>
                                    <div class="col-md-2" style="margin-left:40px;"><button type="button" class="edtbtn" ng-click="showScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)" >EDIT</button> </div>

                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;margin-top:30px;margin-left:-15px;">
                            <div class='col-md-3 SP2 fonthead'>Later</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>
                        <ul>
                            <li ng-repeat="entity in entitySetSelected['Later']">
                                <div>{{entity.schedule_time| date:"MM/dd/yyyy"}}</div>
                                <hr id="line" style="width:800px;height:1px;background-color:#888;position:relative;left:-40px;top:-20px;">
                                <div class="row" style="width:950px;position:relative;left:-50px;top:-20px;" id="entitydetails">

                                    <div class="col-md-1">
                                        <input type="checkbox" name="removetodays" id='{{entity.schedule_id}}' style="width:15px;" value="{{entity.schedule_id}}" onclick="setSelectedIds('{{entity.schedule_id}}')"/>
                                    </div>

                                    <div class="col-md-3"  style="width:300px;margin-left:-40px;">
                                        <p class="MH1" ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)">{{entity.schedule_title}}</p>
                                        <p class="SP1 fntschld">Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-2 MH1 socfnts">{{entity.entity_type}}</div>
                                    <div class="col-md-2 MH1 socfnts" style="margin-left:20px;">{{entity.template_status}}</div>
                                    <div class="col-md-2" style="margin-left:40px;"><button type="button" class="edtbtn" ng-click="showScheduleDetails(entity.schedule_id, entity.schedule_time, entity.entity_type, entity.schedule_title, entity.schedule_description)" >EDIT</button> </div>

                                </div>

                            </li>
                        </ul>
                    </div>                    
                </div>
            </div>
            <div id="dvSliderDialog">
                <div id="dvFastingDialog" class="pollSlider"><br>
                    <form class="form-horizontal" id="signform">

                        <div class="row">
                            <div class="col-md-12" style="width:250px;">
                                <div class="col-md-6" id="dvButtonContainer">
                                    <input type="button" value="Save" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:100px;" />
                                </div>
                                <div class="col-md-6" id="dvButtonContainer">
                                    <input type="button" value="Cancel" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:100px;" />
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
                <div id="dvPriorityDialog" class="pollSlider">
                    <div id="dvPriorityContent"><br>
                        <h1>&nbsp;Add Action</h1>
                        <form class="form-horizontal" id="signform" >


                            <div class="group">
                                <div class="col-md-4 col-md-offset-1 ">                            
                                    <input id="title" class="brdr form-control simplebox" type="text" name="title" />
                                    <label>TITLE</label>
                                </div><br>
                                <div style="position: relative;left:-170px;top:30px;">
                                    Type :  <select id="actiontype"  name="actiontype" style="margin-left:-350px;margin: 10px;font-size: 15px;width:100px;color:#444;background-color: #fff;border:none;border-bottom: 1px solid #000;">
                                        <option value="0">Select</option>
                                        <option value="facebook">facebook</option>
                                        <option value="twitter">twitter</option>
                                        <option value="email">email</option>
                                        <option value="note">note</option>
                                    </select></div>
                                <div style="position:relative;top:40px;left:80px;">
                                    Description : <br><textarea cols="28" rows="2" name="description" id="description"></textarea>
                                </div>
                                <div class="" style="position:relative; margin-top: 50px; margin-left: 50px;">
                                    Date : <input type="datetime-local" name="actiondatetime" id="actiondatetime" />
                                </div>
                                <div class="row" style="position: relative; width: 600px; height: 20px; margin-left: 20px; margin-top: 80px;">
                                    <div class="col-md-4" style="width:120px;">
                                    Day: <select name="actionday" id="actionday">
                                            <option value="1">01</option>
                                            <option value="2">02</option>
                                            <option value="3">03</option>
                                            <option value="4">04</option>
                                            <option value="5">05</option>
                                            <option value="6">06</option>
                                            <option value="7">07</option>
                                            <option value="8">08</option>
                                            <option value="9">09</option>
                                            <option value="10">10</option>
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                            <option value="13">13</option>
                                            <option value="14">14</option>
                                            <option value="15">15</option>
                                            <option value="16">16</option>
                                            <option value="17">17</option>
                                            <option value="18">18</option>
                                            <option value="19">19</option>
                                            <option value="20">20</option>
                                            <option value="21">21</option>
                                            <option value="22">22</option>
                                            <option value="23">23</option>
                                            <option value="24">24</option>
                                            <option value="25">25</option>
                                            <option value="26">26</option>
                                            <option value="27">27</option>
                                            <option value="28">28</option>
                                            <option value="29">29</option>
                                            <option value="30">30</option>
                                            <option value="31">31</option>
                                        </select>
                                        
                                    </div>
                                    <div class="col-md-4" style="width:180px;">
                                    Month : <select name="actionmonth" id="actionmonth">
                                                <option value="January">January</option>
                                                <option value="February">February</option>
                                                <option value="March">March</option>
                                                <option value="April">April</option>
                                                <option value="May">May</option>
                                                <option value="June">June</option>
                                                <option value="July">July</option>
                                                <option value="August">August</option>
                                                <option value="September">September</option>
                                                <option value="October">October</option>
                                                <option value="November">November</option>
                                                <option value="December">December</option>
                                            </select>
                                    </div>
                                    <div class="col-md-4" style="width:300px; margin-top:0px;">
                                    <input type="time" id="actiontime" name="actiontime" style="width:120px;"/>
                                    </div>
                                </div>
<!--                                    Date : <input type="datetime-local" id="actiondate" name="actiondate" style="position:relative;left:50px;top:-60px;"/>-->
                                
                            </div>
                    <div class="row">
                        <div class="col-md-12" style="width:250px;top:50px;">
                            <div class="col-md-6" id="dvButtonContainer">
                                <input type="button" value="Save" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="AddAction()" style="width:100px;" />
                            </div>
                            <div class="col-md-6" id="dvButtonContainer">
                                <input type="reset" value="Cancel" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:100px;" />
                            </div>
                        </div>
                    </div>
                        </form> 
                    </div>
                </div>
                <div id="preview" class="pollSlider">
                    <div>

                        <div>

                        <div class="actiondet">

                            <div style="background-color:#fff; border:1px solid #7ab5d3;height:670px;overflow-y: scroll;overflow-x:hidden;">

                                <div class="actiondetails actiondet">
                                    <p class="SP2 actfnt">ACTION DETAILS</p>
                                    <p class="MH2" style="width:300px;">Title: {{schedule_title}}</p>
                                    <p class="MH2" >Description: {{schedule_desc}}</p>
                                </div>
                                <p class="SP1 actfnt">Saved Post <div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div></p>
                                <p></p><br>
                                <div class="content"></div> 
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div class="actiondet">
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'h:mma'}}</p>
                                </div>
                                <div style="position:relative;left:10px;bottom:10px;top:15px;"><button  class="button button--moema button--text-thick button--text-upper button--size-s" style="background-color:#E65C00;width:120px;" type="button">EDIT</button> </div>
                            </div>
                        </div>
                    </div>

                    </div></div>
                <div id="emailedit" class="pollSlider">
                    <div>
                        <div>

                            <div style="border:1px solid #7ab5d3;height:620px;overflow-y:scroll;overflow-x:hidden;">

                                <div class="actiondetails actiondet">
                                    <p class="SP2 actfnt">ACTION DETAILS</p>
                                    <p><input type="text" class="inputbox MH2" id="email_entitytitle" name="email_entitytitle" value="{{schedule_title}}" style="position:relative;top:7px;line-height:30px;width:300px;font-size:22px;"/></p>
                                    <br><p class="SP1 actfnt">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                                   
                                </div>
                                

                                <div class="row" style="width:400px;">
                                    <div class="col-md-4">
                                        <div class="editcontent"></div>
                                    </div>
                                    <div class="col-md-6" id="popupright_panel" >
<!--                                        <div><label>subject</label></div>-->

                                        <div><p class="SP2 actfnt" style="font-weight:400;">Subject</p><input type="text" class="inputbox" name="email_entitysubject" id="email_entitysubject" value="{{entitiesdetails.subject}}"/></div>
                                        <div><p class="SP2 actfnt" style="font-weight:400;">To address</p><input type="text" class="inputbox" name="email_entitytoaddress" id="email_entitytoaddress" value="{{entitiesdetails.to_email_addresses}}"></div>
                                        <div><p class="SP2 actfnt" style="font-weight:400;">From address</p><input type="text" class="inputbox" name="email_entityfromaddress" id="email_entityfromaddress" value="{{entitiesdetails.from_address}}"></div>
                                        <div><p class="SP2 actfnt" style="font-weight:400;">Reply to address</p><input type="text" class="inputbox" name="email_entityreplytoaddress" id="email_entityreplytoaddress" value="{{entitiesdetails.reply_to_email_address}}"></div>

                                        
                                    </div>
                                    
                                </div>
                                <div style="position: relative;margin-left:20px;margin-top:-50px;">
                                <p class="postdetails postdet SP1 actfnt">Post details</p>
                                <div class="actiondet">
                                    
                                <select id="chooseEmailList" name="chooseEmailList">
                                    <option value="0">Select</option>
                                    <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                </select>
                                    <p class="SP1 actfnt" style="font-weight:400;">{{entitiesdetails.email_list_name}}</p>
                                    <input type="datetime-local" class="inputdate postdet" name="email_schedule_datetime" id="email_schedule_datetime"/>
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'h:mma'}}</p>
                                     <div class="editbutton"><button class="button button--moema button--text-thin button--text-upper button--size-s" style="background-color:#444;width:200px;" type="button">remove the schedule</button> </div>
                                <div class="editbutton"><button class="button button--moema button--text-thin button--text-upper button--size-s" type="button">save</button> </div>
                                </div>
                               
                            </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div id="previewfb" class="pollSlider" style="overflow-y: scroll;overflow-x: hidden;">
                    <div>
                        <div>


                            <div style="">

                                <div class="actiondetails actiondet">
                                    <p class="SP2 actfnt">ACTION DETAILS</p>
                                    <p class="MH2">Title: {{schedule_title}}</p>
                                    <p class="SP1">Description: {{schedule_desc}}</p>
                                    <br><p class="SP1 actfnt">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-25px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                                </div>
                               
                                <img src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' style="position:relative;top:-150px;left:-170px;transform: scale(0.5, 0.9);-ms-transform: scale(0.5, 0.9);-webkit-transform: scale(0.5, 0.7);"/>
                                <div style="position:relative;top:-470px;left:330px;">
                                <p class="postdet SP1 actfnt">{{entitiesdetails.metadata.post_text}}</p>
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.url}}</p>
                                <p class="actfnt" style="font-weight:300;">{{entitiesdetails.metadata.description}}</p>
                                </div>
                                  <div style="position: relative;margin-left:20px;margin-top:-100px;">
                                <p class="postdetails SP1 actfnt">Post details</p>
                                <div>
                                    <p class="SP1 actfnt" style="font-weight:400;">Scheduled on {{entities_selected_time| date:'h:mma'}}</p>
                                </div>
                                <div style="position:relative;bottom:10px;top:15px;"><button  class="button button--moema button--text-thick button--text-upper button--size-s" style="background-color:#E65C00;width:120px;" type="button">EDIT</button> </div>
                                
                                  </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div id="editfacebook" class="pollSlider" style="overflow-y: scroll;overflow-x:hidden;">
                    <div>
                        <div>


                            <div style="border:1px solid #7ab5d3;">

                                <div class="actiondetails actiondet" >
                                    <p class="SP2 actfnt">ACTION DETAILS</p>
                                    <p><input type="text" name="facebook_schedule_title" id="twitter_schedule_title" value='{{schedule_title}}'/></p>
                                    <p><input type="text" name="facebook_schedule_Description" id="twitter_schedule_Description" value='{{schedule_desc}}'/> </p>
                                </div>
                                <br><p class="SP1 actfnt" style="margin-left:15px;">Saved Post </p><div class="SP2 actfnt" style="margin-left:150px;margin-top:-35px;font-size:14px;font-weight:500;color:#444;">PREVIEW</div>
                                <img src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}' style="margin-top:-100px;" />
                                
                                <p><input type="text" name="facebook_schedule_posttext" id="facebook_schedule_posttext" value='{{entitiesdetails.metadata.post_text}}'/></p>
                                <p><input type='text' name="facebook_schedule_url" id="facebook_schedule_url"  value='{{entitiesdetails.metadata.url}}' /></p>
                                <p><input type='text' name='facebook_schedule_description' id="facebook_schedule_description" value='{{entitiesdetails.metadata.description}}' /></p>
                                

                                <p class="postdetails">Post details</p>
                                <div>
                                    <input type='datetime-local' name="facebook_schedule_datetime" id="facebook_schedule_datetime" />
                                    <p>Scheduled on {{entities_selected_time| date:'h:mma'}}</p>
                                </div>
                                <div class="editbutton"><button type="button">EDIT</button> </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div id="previewNote" class="pollSlider">
                    <div>
                        <div>

                            <div style="border:1px solid #7ab5d3">

                                <div class="actiondetails">
                                    <p>ACTION DETAILS</p>
                                    <p>Title: {{schedule_title}}</p>
                                    <p>Description: {{schedule_desc}}</p>
                                </div>
                                <p>Saved Post &nbsp;&nbsp; Preview</p>
                                <p></p><br>

                                <p class="postdetails">Post details</p>
                                <div>
                                    Scheduled on {{entities_selected_time| date:'h:mma'}}
                                </div>
                                <div class="editbutton"><button type="button">EDIT</button> </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div id="previewtwitter" class="pollSlider">
                    <div>
                        <div>

                            <div style="border:1px solid #7ab5d3">

                                <div class="actiondetails">
                                    <p>ACTION DETAILS</p>
                                    <p>Title: {{schedule_title}}</p>
                                    <p>Description: {{schedule_desc}}</p>
                                </div>
                                <p>Saved Post &nbsp;&nbsp; Preview</p>
                                <p></p><br>
                                <img src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>

                                
                                <p>{{entitiesdetails.metadata.post_text}}</p>
                                <p class="postdetails">Post details</p>
                                <div>
                                    Scheduled on {{entities_selected_time| date:'h:mma'}}
                                </div>
                                <div class="editbutton"><button type="button">EDIT</button> </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div id="edittwitter" class="pollSlider">
                    <div>
                        <div>

                            <div style="border:1px solid #7ab5d3">

                                <div class="actiondetails">
                                    <p>ACTION DETAILS</p>
                                    <p><input type="text" name="twitter_schedule_title" id="twitter_schedule_title" value='{{schedule_title}}'/></p>
                                    <p><input type="text" name="twitter_schedule_Description" id="twitter_schedule_Description" value='{{schedule_desc}}'/> </p>
                                </div>
                                <p>Saved Post &nbsp;&nbsp; Preview</p>
                                <p></p><br>
                                <img src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{entitiesdetails.image_name}}'/>
                                
                                <p><input type="text" name="twitter_schedule_post_text" id="twitter_schedule_post_text" value='{{entitiesdetails.metadata.post_text}}'/></p>
                                <p class="postdetails">Post details</p>
                                <div>
                                    <input type='datetime-local' name="twitter_schedule_datetime" id="twitter_schedule_datetime"/>
                                    Scheduled on {{entities_selected_time| date:'h:mma'}}
                                </div>
                                <div class="editbutton"><button type="button">EDIT</button> </div>
                            </div>
                        </div>
                    </div>

                </div>
                
                <div id="slider-button" style="font-size:45px;text-align:center;"><p style="margin-top:-7px;">&#x2715;</p></div>
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
    </body>
</html>