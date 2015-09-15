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
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
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
            .fo{
                background-color: #C6C6C6;
                width: 220px;
                height: 35px;
            }

            #dvPriorityDialog, #dvFastingDialog, #preview{
                position:fixed;
                height:100%;
                background:#ffffff;
                width:800px;
                right:0px;
                margin-right: -800px;
                border: 1px solid #ccc;
            }
            #slider-button{
                position:fixed;
                width:80px;
                height:50px;
                right:-0px;
                background:green;
                top: 0px;
            }

            li {
                cursor: default;
                list-style: none;
            }
            .content{
                position: relative;
                width:350px;
                height: 250px;
                margin-top: 0px;
            }
            .actiondetails{
                position: relative;
                width:250px;
                margin-top: 50px;
            }
            .postdetails{
                position: relative;
                width:250px;
                margin-top: 100px;
            }
            .editbutton{
                position: relative;
                margin-top: 20px;
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

                    <p id="hyshead">Your Plan &nbsp;&nbsp;<button id="liPriority">ADD ACTION</button>&nbsp;&nbsp;<br><br><button id="liFasting" ng-click="getSelectedCampaigns()">Get Details</button></p> <p id="hyshead">Marketing Campaign</p>
                    <div class="col-md-12" id="default" ng-init="getCampaigns()">

                        <div class="row" style="width:950px;">
                            <div class='col-md-3'>Today</div>
                            <div class='col-md-3'>Action Type</div>
                            <div class='col-md-3'>Template Saved</div>
                            <div class='col-md-3'></div>
                        </div>
                        <ul>
                            <li ng-repeat="entity in entitySet['Today']">
                                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;">
                                <div class="row" style="width:950px;" id="entitydetails" >
                                    <div class="col-md-3">
                                        <p ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)">{{entity.schedule_title}}</p>
                                        <p>Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-3">{{entity.entity_type}}</div>
                                    <div class="col-md-3">{{entity.template_status}}</div>

                                    <div class="col-md-3"><button type="button">EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;">
                            <div class='col-md-3'>Tomorrow</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>

                        <ul>   
                            <li ng-repeat="entity in entitySet['Tomorrow']">
                                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;">
                                <div class="row" style="width:950px;" id="entitydetails">
                                    <div class="col-md-3">
                                        <p ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)">{{entity.schedule_title}}</p>
                                        <p>Scheduled for {{ entity.schedule_time | date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-3">{{entity.entity_type}}</div>
                                    <div class="col-md-3">{{entity.template_status}}</div>
                                    <div class="col-md-3"><button type="button" >EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;">
                            <div class='col-md-3'>Later</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>
                        <ul>
                            <li ng-repeat="entity in entitySet['Later']">
                                <div>{{entity.schedule_time| date:"MM/dd/yyyy"}}</div>
                                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;">
                                <div class="row" style="width:950px;" id="entitydetails">
                                    <div class="col-md-3">
                                        <p ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)">{{entity.schedule_title}}</p>
                                        <p>Scheduled for {{ entity.schedule_time | date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-3">{{entity.entity_type}}</div>
                                    <div class="col-md-3">{{entity.template_status}}</div>
                                    <div class="col-md-3"><button type="button" >EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-12" id="selected" style="display:none;">

                        <div class="row" style="width:950px;">
                            <div class='col-md-3'>Today</div>
                            <div class='col-md-3'>Action Type</div>
                            <div class='col-md-3'>Template Saved</div>
                            <div class='col-md-3'></div>
                        </div>

                        <ul>
                            <li ng-repeat="entity in entitySetSelected['Today']">
                                <p>{{entity.schedule_time| date:"MM/dd/yyyy"}}</p>
                                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;">
                                <div class="row" style="width:950px;" id="entitydetails">
                                    <div class="col-md-3">
                                        <p ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)">{{entity.schedule_title}}</p>
                                        <p>Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-3">{{entity.entity_type}}</div>
                                    <div class="col-md-3">{{entity.template_status}}</div>

                                    <div class="col-md-3"><button type="button" >EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;">
                            <div class='col-md-3'>Tomorrow</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>

                        <ul>   
                            <li ng-repeat="entity in entitySetSelected['Tomorrow']">
                                <p>{{entity.schedule_time| date:"MM/dd/yyyy"}}</p>
                                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;">
                                <div class="row" style="width:950px;" id="entitydetails">
                                    <div class="col-md-3">
                                        <p ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)">{{entity.schedule_title}}</p>
                                        <p>Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-3">{{entity.entity_type}}</div>
                                    <div class="col-md-3">{{entity.template_status}}</div>
                                    <div class="col-md-3"><button type="button" >EDIT</button> </div>
                                </div>
                            </li>
                        </ul>
                        <div class="row" style="width:950px;">
                            <div class='col-md-3'>Later</div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                            <div class='col-md-3'></div>
                        </div>
                        <ul>
                            <li ng-repeat="entity in entitySetSelected['Later']">
                                <div>{{entity.schedule_time| date:"MM/dd/yyyy"}}</div>
                                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;">
                                <div class="row" style="width:950px;" id="entitydetails">
                                    <div class="col-md-3">
                                        <p ng-click="getScheduleDetails(entity.schedule_id, entity.schedule_time)">{{entity.schedule_title}}</p>
                                        <p>Scheduled for {{entity.schedule_time| date:"h:mma"}}</p>
                                    </div>
                                    <div class="col-md-3">{{entity.entity_type}}</div>
                                    <div class="col-md-3">{{entity.template_status}}</div>
                                    <div class="col-md-3"><button type="button" >EDIT</button> </div>
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
                                    <input type="button" value="Save" class="btn-info" style="width:100px;" />
                                </div>
                                <div class="col-md-6" id="dvButtonContainer">
                                    <input type="button" value="Cancel" class="btn-info" style="width:100px;" />
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
                <div id="dvPriorityDialog" class="pollSlider">
                    <div id="dvPriorityContent"><br>
                        <h1>Add Action</h1>
                        Title : <input type="text" name="title" id="title" /><br>
                        Type :  <select id="actiontype" name="actiontype">
                            <option value="0">Select</option>
                            <option value="Facebook">Facebook</option>
                            <option value="Twitter">Twitter</option>
                            <option value="Email">Email</option>
                            <option value="Note">Note</option>
                        </select>
                        <br><br>
                        Description : <textarea cols="28" rows="4" name="description" id="description"></textarea><br><br>
                        Date : <input type="date" id="actiondate" name="actiondate"/><br>

                    </div>
                    <div class="row">
                        <div class="col-md-12" style="width:250px;">
                            <div class="col-md-6" id="dvButtonContainer">
                                <input type="button" value="Save" class="btn-info" ng-click="AddAction()" style="width:100px;" />
                            </div>
                            <div class="col-md-6" id="dvButtonContainer">
                                <input type="button" value="Cancel" class="btn-info" style="width:100px;" />
                            </div>
                        </div>
                    </div>
                </div>
                <div id="preview" class="pollSlider">
                    <div>
                        <div class="">

                            <div style="border:1px solid #7ab5d3">

                                <div class="actiondetails">
                                    <p>ACTION DETAILS</p>
                                    <p>{{entitiesdetails.subject}}</p>
                                </div>
                                <p>Saved Post &nbsp;&nbsp; Preview</p>
                                <p></p><br>
                                <div class="content"></div>
                                <p class="postdetails">Post details</p>
                                <div>
                                    Scheduled on {{entities_selected_time| date:'h:mma'}}
                                </div>
                                <div class="editbutton"><button type="button">EDIT LIST</button> </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div id="slider-button"></div>
            </div>    


        </div>     
    </body>
</html>