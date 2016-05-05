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
    <script src="js/pikaday.js"></script>
    <script src="js/pikaday.js"></script>
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
    <style>.arrow_top{display:none;}.timepicker_wrap {width: 113% !important;}</style>
<body>
    <div id="fade" class="black_overlay" ></div>
        <div id="addAction">
    <!--Top Nav-->   
    <div class="pop-up-background">
        <div class="pop-up-container pop-up-container-newaction pop-up-container-newmarketingaction pop-up-container-newaction-overflow"> 
            <div class="pop-up-title pop-up-title-h1"> Create New Action</div>
             <div class="pop-up-exit-container" id="addactionClose">
                <a href="" class="pop-up-exit-icon">
                    <img onclick = "closeOverlay();" type="image/svg+xml" src="images/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                </a>
            </div> 
            <div class="pop-up-inner">
                <div class="input-field-container ">
                    <!--<div class="input-header"> Marketing Program </div>-->
                    <select disabled id="marketing_program" hidden name="option" class="input-field-textfield input-placeholder">
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
                        <div class="input-header"> Action Date </div>
                        <input type="hidden" name="days" id="days"  class="input-field-textfield input-placeholder" placeholder="Enter days"></input>
                        <input type="text"  name="datepicker" id="jumptodatepicker" readonly  class="input-field-textfield input-placeholder" placeholder="Enter Action Date"/>
                        <script>
                            var max="";
                            var min="";
                            $(document).ready(function ()
                            {  
                                
                                end_date=$("#program_end_date").val();
                                var res = end_date.split(" ");
                                var month="";
                                var monthchar="";
                                var date="";
                                var year="";
                                if(res.length==3){monthchar=res[0];date=res[1];year=res[2];}
                                else{monthchar=res[1];date=res[2];year=res[3];}
                                
                                switch (monthchar) {
                                    case 'Jan':
                                        month = "01";
                                        break;
                                    case 'Feb':
                                        month = "02";
                                        break;
                                    case 'Mar':
                                        month = "03";
                                        break;
                                    case 'Apr':
                                        month = "04";
                                        break;
                                    case 'May':
                                        month = "05";
                                        break;
                                    case 'Jun':
                                        month = "06";
                                        break;
                                    case 'Jul':
                                        month = "07";
                                        break;
                                    case 'Aug':
                                        month = "08";
                                        break;
                                    case 'Sep':
                                        month = "09";
                                        break;
                                    case 'Oct':
                                        month = "10";
                                        break;
                                    case 'Nov':
                                        month = "11";
                                        break;
                                    case 'Dec':
                                        month = "12";
                                        break;                                    
                                }                                
                                max=year+"-"+month+"-"+date;
                                var monthNames = ["January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"];
                                var d = new Date();
                                var monthnow1=d.getMonth()+1;
                                var monthnow="";
                                var datenow=d.getDate();
                                var yearnow=d.getFullYear();
                                if(monthnow1<10){monthnow="0"+monthnow1;}
                                else{monthnow=monthnow1;}
                                min=yearnow+"-"+monthnow+"-"+datenow;          
                                var picker = new Pikaday(
                                {
                                    field: document.getElementById('jumptodatepicker'),
                                    format: 'YYYY-MM-DD',
                                    firstDay: 1,
                                    minDate: new Date(min),
                                    maxDate: new Date(max),
                                    yearRange: [2000,2050],
                                    onSelect: function() {
                                        document.getElementById('selected').appendChild(curdat);
                                    }
                                });
                                $( "#jumptodatepicker" ).change(function() {
                                    var date1_ms= new Date($("#jumptodatepicker").val()).getTime();
                                    var date2_ms = new Date(end_date).getTime();
                                    var difference_ms = date2_ms - date1_ms;
                                    var one_day=1000*60*60*24;
                                    var diffdays=Math.round(difference_ms/one_day);
                                    $("#days").val(diffdays);
                                });                                    
                            });
                        </script>
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
                <input type="hidden" name="description" id="description" value="" placeholder="Description here"></input>
                
            </div>
            
        </div>
    </div>
    <div id="addActionMarketingProgram" class="pop-up-cta-container pop-up-cta-container-newaction cur"  ng-click="AddAction()">
        <a href="javascript:void(0)">
            <div class="pop-up-cta-button-full"> Save Action</div>
        </a>
    </div> 
        </div>
    </body>
</html>