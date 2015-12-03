<%-- 
    Document   : marketingprogram
    Created on : Oct 20, 2015, 3:12:54 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <title>Marketing Programs</title>
        <meta charset="UTF-8">
        <%@ include file="checksession.jsp" %>
        <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/marketingprogramlist.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        <jsp:include page="basejsp.jsp"/>
        <script>
        
        function controllerUserMarketingProgamsByStatus($scope, $http){
            $scope.getUserMarketingProgramsOpen = function(){
                $("#pastprogs").hide();
                $("#curprogs").show();
                $("#email ").show();
                $("#prog").hide();
                var programStatus = { "programType": "Open" };
                
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Open',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.current_programs = data.programs;
                    $(".row").css("display","block");
                }).error(function (data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $(".row").css("display","block");
                });                
            };
            $scope.getUserMarketingProgramsClosed = function(){
                var programStatus = { "programType": "Closed" };
                $("#pastprogs").show();
                $("#prog").show();
                $("#curprogs").hide();
                $("#email").hide();
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Closed',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.past_programs = data.programs;
                    $(".row").css("display","block");
                }).error(function (data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $(".row").css("display","block");
                });                
            };
            
            $scope.sendProgramId = function(program_id){
                window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
            };
            
        }
        </script> 
    </head>
    <body ng-app>
        <div class="row" ng-controller="controllerUserMarketingProgamsByStatus" style="display: none;">
            <div class="col-md-1 col-lg-1 col-sm-1" style="width:45px;">
            <jsp:include page="leftmenu.html"/> 
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 padtab"> 
                <div class="row">
                    <div class="col-lg-1 col-md-1 col-sm-1 "> 
                     <div class="progtabs"> 
                         <ul class="proghislist fontpnr">
                             <li id="crtprogpg"><p>Create <br>New Program</p></li>
                             <li id="lstcurprogs" ng-click="getUserMarketingProgramsOpen()"><p>Current Programs</p></li>
                             <li id="lstpstprogs" ng-click="getUserMarketingProgramsClosed()"><p>Past <br>Programs</p></li>
                         </ul>
                     </div>
                    </div>
                </div>
            </div>
            <div class="col-md-9 col-sm-9 col-lg-9">
                <div class="row">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 ">
                            <div id="curprogs" class="pstprog fontpns">Your Current Programs</div>
                            <div id="pastprogs" class="curprog fontpns">Your Past Programs</div>
                        </div>
                    </div>
                    <div id="prog" ng-init="getUserMarketingProgramsClosed()">
                            <div class="row">
                                <ul class="programsheader">
                                    <li><div class="prognamhead fontpnr">Programs</div></li>
                                    <li><div class="progactlfthead fontpnr">End Date</div></li>
                                </ul>
                            </div>
                            <div class="row">
                                    <hr class="pstprogline">
                            </div>
                        <div class="fontpns" style="margin-left: 15px;" ng-show="past_programs==''">No Records Available</div>
                        <div class="row" ng-repeat="program in past_programs">
                                <ul class="programsheader">
                                    <li><div class="lstprog fontpns">{{program.program_name}}</div>
                                        <div class="lststrtdt fontpnr">Started on {{program.start_date | date: 'MMM dd yyyy'+' on '+'h:mma'}}</div>
                                    </li>
                                    <li>
                                    <ul class="li1">
                                        <li>
                                            <div class="lstlftact fontpnr">{{program.end_date | date: "MMM dd"}}</div>
                                        </li>
                                        <li >
                                            <button class="viewbtn" ng-click="sendProgramId(program.id)">View</button>
                                        </li>
                                    </ul>
                                    </li>
                                 </ul><hr class="pstprogline">
                        </div>
<!--                        <div class="row">
                            <hr class="pstprogline">
                        </div>-->
                    </div>
                    </div>
                    <div id="email" ng-init="getUserMarketingProgramsOpen()">
                        <div class="row">
                                <ul class="programsheader">
                                    <li><div class="prognamhead fontpnr">Programs</div></li>
                                    <li><div class="progcurlfthead fontpnr">End Date</div></li>
                                    <li><div class="progactpsthead fontpnr">Number of Posts Left</div></li>
                                </ul>
                        </div>
                        <div class="row">
                                <hr class="pstprogline">
                        </div>
                        <div class="fontpns" style="margin-left: 15px;" ng-show="current_programs==''" >No records available</div>
                        <ul  class="programsheader" ng-repeat="program in current_programs">
                            <div class="fontpns" style="margin-left:-25px;" ng-show="program.program_name==''">No records available</div>
                            <li><div class="lstprog fontpns">{{program.program_name}}</div>
                                <div class="lststrtdt fontpnr">Started on {{program.start_date | date: 'MMM dd yyyy'+' on '+'h:mma'}}</div>
                            </li>
                            <li>
                                <ul class="li1 lftcur">
                                    <li>
                                        <div class="lstlftactcur fontpnr">{{program.end_date | date: "MMM dd"}}</div>
                                    </li>
                                    <li>
                                         <div class="lstcomp fontpnr">{{program.noofpostleft}}</div>
                                    </li>
                                    <li class="viewbtnli">
                                        <button class="viewbtn button--moema button--text-thick button--text-upper button--size-s fontpnr" ng-click="sendProgramId(program.id)">View</button>
                                    </li>
                                </ul>
                            </li>
                            <hr class="pstprogline padlineleft">
                        </ul>
                    </div>
                    
                </div>
            </div>
         
            <script>
                
                $(document).ready(function() {
                    $("#lstcurprogs").css("color","#3f3f42").css("background-color","#f6f7f7");
                    $("#lstcurprogs").click(function (){
                        $("#lstcurprogs").css("color","#3f3f42").css("background-color","#f6f7f7");
                        $("#lstpstprogs").css("color","#3f3f42").css("background-color","transparent");
                    });
                     $("#lstpstprogs").click(function (){
                        $("#lstpstprogs").css("color","#3f3f42").css("background-color","#f6f7f7");
                        $("#lstcurprogs").css("color","#3f3f42").css("background-color","transparent");
                    });
                    if(window.location.href.indexOf("current") !== -1)
                    {
                         $("#pastprogs").hide();
                         $("#curprogs").show();
                         $("#email").show();
                         $("#prog").hide();
                    }
                    else if(window.location.href.indexOf("past") !== -1)
                    {
                        $("#pastprogs").show();
                        $("#prog").show();
                        $("#curprogs").hide();
                        $("#email").hide();
                        $("#lstpstprogs").css("color","#3f3f42").css("background-color","#f6f7f7");
                        $("#lstcurprogs").css("color","#3f3f42").css("background-color","transparent");
                    }
//                    }
//                        var= curprogs;
//                        curprogs=location.search;
//                        alert(curprogs);
//                    window.location = "marketingprogramlist.jsp?type=curprogs";    
//                    $("#email").hide();
                });
//                $("#lstpstprogs").click(function (){
//                    $("#pastprogs").show();
//                    $("#prog").show();
//                    $("#curprogs").hide();
//                    $("#email").hide();
//                    location.href="marketingprogramlist.jsp?type=pastprog";
//                });
//                $("#lstcurprogs").click(function (){
//                    $("#pastprogs").hide();
//                    $("#curprogs").show();
//                    $("#email ").show();
//                    $("#prog").hide();
//                    location.href="marketingprogramlist.jsp?type=curprog";
//                });
                
                $("#crtprogpg").click(function (){
                    
                   document.location.href="marketingcategory.jsp"; 
                });
            </script>
                
    </body>
</html>