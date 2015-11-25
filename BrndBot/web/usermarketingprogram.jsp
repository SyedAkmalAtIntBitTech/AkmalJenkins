<%-- 
    Document   : marketingsubjectline
    Created on : Oct 12, 2015, 3:34:37 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Marketing Program </title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/marketing.js"></script>
        <link href="css/usermarketingprogram.css" rel="stylesheet" type="text/css"/>
        <link href="css/facebook.css" rel="stylesheet" type="text/css"/>
        <link href="css/facebook.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/pikaday.css">
        <link rel="stylesheet" href="css/datepickerpikaday.css">
        <script src="js/pikaday.js"></script>
        
        <script src="js/dashboard.js"></script>
        
        <%!
            String marketing_category_id = "";
            String marketing_program_id = "";
         %>
         
         <%
             marketing_category_id = request.getParameter("categoryid");
             marketing_program_id = request.getParameter("programid");
         %>
        <script>
            function validateurl(){
                var myRegExp =/^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;
                    if (!myRegExp.test($("#program_url").val())) {
                        alert("Please enter valid Program URL");
//                        alert($("#program_url").val().indexOf("http://"));
                        if($("#program_url").val().indexOf("http://")<0) 
                        {
                           $("#program_url").val('http://'+$("#program_url").val());
//                           alert("index");
                        }
                        if(!myRegExp.test($("#program_url").val())){
                            $("#program_url").val('http://');
//                            alert("notvalid");
                        }
                        return false;
                        $("#program_url").focus();
                        $("#program_url").click();   
                    }
//                    else
//                    {
//                        if(!$("#program_url").val().indexOf("http://") >= 0)
//                           $("#program_url").val('http://'+$("#program_url").val()); 
//                    }
                    return true;
            }
            function validate(){
                var program_name = $("#program_name").val();
                var program_date_time = $("#programdatetime").val();
                var program_url = $("#program_url").val();
                var program_urlname = $("#program_url_name").val();
                if (program_name.length === 0){
                    alert("Enter the program name");
                    $("#program_name").focus();
                    return false;
                }
                if (program_date_time.length === 0){
                    alert("Enter the Program Date");
                    $("#programdatetime").focus();
                    return false;
                }
               
//                if(program_url.length !== 0){
//                    alert("link URL not entered please enter valid Link");
//                    $("#program_url").focus();
//                    return false;
//                }
//                
                if((program_url === "http://")&&(program_urlname.length !== 0)){
                    alert("Please Enter Valid Link_Url");
                    $("#program_url").focus();
                    return false;
                }
                if((program_url.length !== 0)&&(program_urlname.length === 0)){
                    alert("link Name not entered Please enter Link Name");
                    $("#program_url_name").focus();
                    return false;
                }
                 if((program_urlname.length === 0)&&(program_url.length === 0)){
//                    alert("optional");
                    return true;
                }
//                if (program_url === "") {
//                    alert("Enter the program url");
//                    $("#program_url").focus();
//                    return false;
//                }
                return true;
            }
            
            function usermarketingprogram($scope, $http){
                
                $scope.saveMarketingProgram = function(){
                    
                if (validate()){
//                    alert("enter");
                    var program_name = $("#program_name").val();
                    var program_date_time = $("#programdatetime").val();
                    
                    var ss = program_date_time.split(" ");
                    var count=0;
                    var month="";
                    var day="";
                    var year="";
                    for (var i in ss) {
                        if(count == 0){}
                        if(count == 1)
                        {
                            var value=ss[i];
                            switch (value)
                            {
                               case 'Jan': month="01";
                               break;

                               case 'Feb': month="02";
                               break;

                               case 'Mar': month="03";
                               break;

                               case 'Apr': month="04";
                               break;

                               case 'May': month="05";
                               break;
                               
                               case 'Jun': month="06";
                               break;

                               case 'Jul': month="07";
                               break;

                               case 'Aug': month="08";
                               break;

                               case 'Sep': month="09";
                               break;

                               case 'Oct': month="10";
                               break;
                               
                               case 'Nov': month="11";
                               break;

                               case 'Dec': month="12";
                               break;

                               default:  month="00";
                            }
                        }
                        if(count == 2)
                        {
                            day=ss[i];;
                        }
                        if(count == 3)
                        {
                            year=ss[i];
                        }
                        count++;
                    }
                    var d=year+"-"+month+"-"+day;
                     var program_url = $("#program_url").val();
                    var program_url_name = $("#program_url_name").val();
                    if(program_url === "http://" || program_url_name === "")
                    {
                        program_url="";
                        program_url_name="";
                    }
                    var schedule_time = Date.parse(program_date_time);
                    
                     if (program_name === "") {
                        alert("Please enter the Program Name");
                        $("#program_name").focus();
                        return false;
                    }
                    //alert("program date time ...."+program_date_time);
                    //alert("date... "+d);
                    if (program_date_time === "") {
                        alert("Please select the date");
                        $("#programdatetime").focus();
                        return false;
                    }
                    
                    
                   
                    console.log("Epoch: " + schedule_time);

                    var myEpoch = schedule_time;

                    console.log("New Epoch: " + myEpoch);
        
                    var marketing_category_id = <%= marketing_category_id %>;
                    var marketing_program_id = <%= marketing_program_id %>;
                    
                    var program_details = {"program_name": program_name, 
                                           "program_date_time": d,
                                           "program_url":program_url,
                                           "program_url_name":program_url_name,
                                           "marketing_category_id":marketing_category_id,
                                           "marketing_program_id":marketing_program_id
                                          };
                     $http({
                        method: 'POST',
                        url: getHost() + 'setMarketingProgram.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(program_details)
                    }).success(function (data, status, headers, config) {
                        if (data !== null){
                            alert("details saved successfully");
                            var redirect="programactions.jsp?program_id="+data;
                            window.open(getHost() + redirect, "_self");
                        }else 
                        {
                            alert("problem saving the record");
                        }    
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                }
                };
            }
            
        </script>
         <jsp:include page="basejsp.jsp"/>
         
    </head>
    <body ng-app>
        <div class="row" ng-controller="usermarketingprogram">
                <div class="col-md-1 col-lg-1 col-sm-2" style="margin-left:-15px;">
                     <jsp:include page="leftmenu.html"/>
                </div>
                <div class="col-md-11 col-lg-11 col-sm-10 col-lg-offset-2 col-md-offset-2">
                    <div class="row" >
                        <div class="col-sm-12 col-lg-12 col-md-12">
                            <div class="markprog fontpnr">Please enter a name for this marketing program:</div>
                            <!--<div class="marksubline fontpnr">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</div>-->
                            <div class="inputname">
                                <input id="program_name" class="form-control fontpnr subinput" value="" type="text" required  placeholder="Enter Name Here">
                            </div>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col-sm-5 col-lg-5 col-md-5 ">
                            <div class="datevent fontpnr">Please select a date for the event:</div>
                            <!--<p class="datepara">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>-->
                            <div class="row padlft">
                                <div class="col-md-5 col-lg-5 col-sm-6">
                                    <div class="inputday">
                                        <input type="text" class="inputdate fontpns ptr" name="programdatetime" id="programdatetime" value="" readonly="">
                                        <script>
                                            var picker = new Pikaday(
                                            {
                                                field: document.getElementById('programdatetime'),
                                                firstDay: 1,
                                                minDate: new Date(2000, 0, 1),
                                                maxDate: new Date(2050, 12, 31),
                                                yearRange: [2000,2050]
                                            });
                                        </script>
                                        <!--                                        <input id="programdatetime" type="date" name="programdatetime"/>-->
<!--                                        <input id="" class="form-control subinputday fontpnr" type="text" required  placeholder="Day">-->
                                    </div>
                                </div>
<!--                            <div class="col-md-2 col-lg-2 col-sm-6">
                                    <div class="inputmonth">
                                        <input id="" class="form-control subinputmonth fontpnr" type="text" required  placeholder="Month">
                                    </div>
                                </div>-->
                            </div>
                        </div> 
                        <div class="col-sm-4 col-lg-4 col-md-4 ">
                            <div class="worklink fontpnr">Give a link to this workshop:</div>
                            <!--<p class="workpara">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>-->
                         
                                <div class="inputlink form-group">

                                    <input id="program_url" value="" class="form-control subinputlink fontpnr" type="text" required onchange="validateurl()" placeholder="Enter Link Ex. http://www.google.com">
                                    <input id="program_url_name" value="" class="top20nhalf form-control subinputlink fontpnr" type="text" required  placeholder="Enter Name for Link">
                                </div> 
                            
                        </div> 
                    </div>
                    <div class="row">
                         <div class="col-sm-12 col-lg-12 col-md-12">
                             <a href="">
                                 <button  type="submit"  
                                                 ng-click="saveMarketingProgram()"
                                                 class="subcont button 
                                                 button--moema button--text-thick 
                                                 button--text-upper button--size-s">
                                     Save</button>
                             </a>
                         </div>
                    </div>
                    
                </div>
               
                            
        </div>
    </body>
</html>
