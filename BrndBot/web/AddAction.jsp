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
    <script src="js/pikaday.js"></script>
    
</head>    
    <body>
        <div id="fade" class="black_overlay" ></div>
        <div id="addAction">
            <div class="pop-up-background">
        <div class="pop-up-container pop-up-container-newaction"> 
            <div class="pop-up-title pop-up-title-h1"> Create New Action</div>
             <div class="pop-up-exit-container"  id="addactionClose">
                <a href="" class="pop-up-exit-icon">
                    <img onclick = "closeOverlay();" type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </a>
            </div> 
            <div class="pop-up-inner">
                <div class="input-field-container ">
                    <div class="input-header"> Action Name </div>
                    <input type="text" name="addactiontitle" id="addactiontitle" class="input-field-textfield input-placeholder full" placeholder="Enter Title" value=""/>
                </div>
                <div class="line-divider"></div>
                <div class="cols-2">
                     <div class="input-field-container col-4of10 fleft pushright">
                        <div class="input-header"> Action Date </div>
                        <input type="text" name="datepicker" id="datepicker" readonly class="input-field-textfield input-placeholder full" placeholder="Enter Action Date" />
                        <script>
                            var picker = new Pikaday(
                            {
                                format:('MM-DD-YYYY'),
                                field: document.getElementById('datepicker'),
                                firstDay: 1,
                                minDate: new Date(2000, 0, 1),
                                maxDate: new Date(2050, 12, 31),
                                yearRange: [2000,2050]
                            });
                        </script>
                     </div>
                     <div class="input-field-container col-4of10 fleft">
                        <div class="input-header"> Action Time </div>
                        <input id="timepicker1" type="text" name="timepicker1" class="input-field-textfield input-placeholder percentage99" placeholder="Enter Action Time"  /> 
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
                 <div class="input-field-container col-1of1">
                    <div class="input-header"> Action Type </div>
                    <select id="actiontype" class="full topten" name="actiontype">
                        <option value="0">Select</option>
                        <option value="Facebook">Facebook Post</option>
                        <option value="Twitter">Twitter Post</option>
                        <option value="Email">Email</option>
                        <option value="Reminder">Reminder</option>
                    </select>
                </div>
                <input type="hidden" id="marketing_program" value="0" name="marketing_program_type"/>
<!--                <div class="input-field-container col-1of1">
                    <div class="input-header"> Marketing Program Association </div>
                    <select disabled id="marketing_program" class="full topten" name="marketing_program_type" hidden >
                        <option value="0">General</option>
                        <option ng-repeat="row in marketprogram" value="{{row.user_program_id}}">{{row.name}}</option>
                    </select>
                </div>-->
                
            </div>
            
        </div>
    </div>
    <div class="pop-up-cta-container pop-up-cta-container-newaction cur" ng-click="AddAction()" >
        <a href="javascript:void(0)">
            <div class="pop-up-cta-button-full"> Save Action</div>
        </a>
    </div>
    </div>
    </body>
</html>
