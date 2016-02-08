<%-- 
    Document   : marketingprogramaddaction
    Created on : Dec 30, 2015, 7:39:20 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/popup.css"></link>
    <script src="js/popup.js" type="text/javascript"></script>
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
</head>    
    <style>.arrow_top{display:none;}</style>
<body>
    <div id="fade" class="black_overlay" ></div>
        <div id="addAction">
    <!--Top Nav-->   
    <div class="pop-up-background">
        <div class="pop-up-container pop-up-container-newaction pop-up-container-newmarketingaction pop-up-container-newaction-overflow"> 
            <div class="pop-up-title pop-up-title-h1"> Create New Action</div>
             <div class="pop-up-exit-container" id="addactionClose">
                <a href="" class="pop-up-exit-icon">
                    <img onclick = "closeOverlay();" type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </a>
            </div> 
            <div class="pop-up-inner">
                <div class="input-field-container ">
                    <div class="input-header"> Marketing Program </div>
                    <select disabled id="marketing_program" name="option" class="input-field-textfield input-placeholder">
                        <!--<option value="0" class="a0">General</option>-->
                        <option class="a<%=program_id%>" value="<%=program_id%>">{{programs.programdetails.programName}}</option>
                    </select>
                </div>
                <div class="input-field-container ">
                    <div class="input-header"> Action Name </div>
                    <input id="addactiontitle" type="text" class="input-field-textfield input-placeholder" placeholder="Enter Action Name"></input>
                </div>
                <div class="line-divider"></div>
                <div class="cols-2">
                     <div class="input-field-container col-4of10 fleft pushright">
                        <div class="input-header"> Action Days </div>
                        <input type="number" name="days" id="days"  class="input-field-textfield input-placeholder" placeholder="Enter days"></input>
                    </div>
                     <div class="input-field-container col-4of10 fleft">
                        <div class="input-header"> Action Time </div>
                         <input id="timepicker1" type="text" name="timepicker1"  class="input-field-textfield input-placeholder" placeholder="Enter Action Time"></input>
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
                    <select id="actiontype" class="input-field-textfield input-placeholder" name="actiontype">
                                    <option  class="input-field-textfield input-placeholder" value="0">Select</option>
                                    <option class="input-field-textfield input-placeholder" value="Facebook">Facebook Post</option>
                                    <option class="input-field-textfield input-placeholder" value="Twitter">Twitter Post</option>
                                    <option class="input-field-textfield input-placeholder" value="Email">Email</option>
                                </select>
                </div>
                <div class="input-field-container col-1of1">
                    <div class="input-header"> Description </div>
                    <textarea class="input-field-textfield input-placeholder mrktextarea" name="description" id="description" placeholder="Description here"></textarea>
                </div>
            </div>
            
        </div>
    </div>
    <div class="pop-up-cta-container pop-up-cta-container-newaction"  ng-click="AddAction()">
        <a href="javascript:void(0)">
            <div class="pop-up-cta-button-full"> Save Action</div>
        </a>
    </div> 
        </div>
    </body>
</html>