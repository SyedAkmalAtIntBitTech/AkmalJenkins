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
        <link href="css/usermarketingprogram.css" rel="stylesheet" type="text/css"/>
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
            
            function validate(){
                var program_name = $("#program_name").val();
                var program_date_time = $("#programdatetime").val();
                var program_url = $("#program_url").val();
                
                if (program_name === "") {
                    alert("Enter the program name");
                    $("#program_name").focus();
                    return false;
                }
                if (program_date_time === "") {
                    alert("Enter the program date time");
                    $("#programdatetime").focus();
                    return false;
                }
                if (program_url === "") {
                    alert("Enter the program url");
                    $("#program_url").focus();
                    return false;
                }
                return true;
            }
            
            function usermarketingprogram($scope, $http){
                
                $scope.saveMarketingProgram = function(){
                    
                if (validate()){
                    var program_name = $("#program_name").val();
                    var program_date_time = $("#programdatetime").val();
                    var program_url = $("#program_url").val();
                    var schedule_time = Date.parse(program_date_time);
                    console.log("Epoch: " + schedule_time);

                    var myEpoch = schedule_time;

                    console.log("New Epoch: " + myEpoch);
        
                    var marketing_category_id = <%= marketing_category_id %>;
                    var marketing_program_id = <%= marketing_program_id %>;

                    var program_details = {"program_name": program_name, 
                                           "program_date_time": program_date_time,
                                           "program_url":program_url,
                                           "marketing_category_id":marketing_category_id,
                                           "marketing_program_id":marketing_program_id
                                          };
                     $http({
                        method: 'POST',
                        url: getHost() + 'setMarketingProgram.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(program_details)
                    }).success(function (data, status, headers, config) {
                        if (data == "success"){
                            alert("details saved successfully");
                            window.open(getHost() + 'usermarketingprogram.jsp', "_self");
                        }else {
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
                            <div class="marksubline fontpnr">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</div>
                            <div class="inputname">
                                <input id="program_name" class="form-control fontpnr subinput" type="text" required  placeholder="Enter Name Here">
                            </div>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col-sm-5 col-lg-5 col-md-5 ">
                            <div class="datevent fontpnr">Please select a date for the event:</div>
                            <p class="datepara">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <div class="row padlft">
                                <div class="col-md-5 col-lg-5 col-sm-6">
                                    <div class="inputday">
                                        <input id="programdatetime" type="date" name="programdatetime"/>
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
                            <p class="workpara">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <div class="inputlink">
                                <input id="program_url" class="form-control subinputlink fontpnr" type="text" required  placeholder="Enter Link">
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
                                     continue</button>
                             </a>
                         </div>
                    </div>
                    
                </div>
               
                            
        </div>
    </body>
</html>
