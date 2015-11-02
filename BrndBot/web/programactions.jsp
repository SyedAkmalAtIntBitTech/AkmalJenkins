<%-- 
    Document   : programdetail
    Created on : Oct 14, 2015, 11:43:14 AM
    Author     : Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <title>Email Action</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/marketingactions.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/dashboard.js"></script>
        
<%! 
    String program_id = "";
%>        
<% 
    program_id = request.getParameter("program_id");
    out.println(program_id);
%>
<script>
            var program = "";
            program = <%= program_id %>;
            function programactions($scope, $http, $window){
                
                $scope.getProgramActions = function(){
                     $http({
                        method: 'GET',
                        url: 'alluserMarketingProgramForDisplay.do?program_id='+program
                    }).success(function (data, status, headers, config) {
                        $scope.programs = data;
//                        $scope.programs_emailautomation = data.emailautomation;
//                        $scope.programs_programactions = data.programactions;
                        alert(JSON.stringify(data));
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
            };
            
        </script>
        <jsp:include page="basejsp.jsp"/>
    </head>
    <body ng-app>
        <div class="row" ng-controller="programactions">
            <div class="row" ng-init="getProgramActions()">
                <div class="col-lg-1 col-md-1 col-sm-2">
                   <jsp:include page="leftmenu.html"/> 
                </div>
                <div class="col-md-11 col-sm-10 col-lg-11 col-lg-offset-2 col-md-offset-2">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 ">
                            <div class="markprog fontpns">{{programs.programdetails.programName}}</div>
                            <div class="edtprog fontpnr">Edit Program Details</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="noofact fontpnr">Number of actions</div>
                            <div class="actno fontpns">{{programs.programdetails.noOfActions}}</div>
                        </div>
                         <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="dtofevnt fontpnr">Date of Event</div>
                            <div class="evntdt fontpns">{{programs.programdetails.dateOfEvent}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="asclink fontpnr">Associated Link</div>
                            <div class="evntdt fontpns">{{programs.programdetails.linktodestination}}</div>
                        </div>
                        
                    </div>
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-sm-12 ">
                            <div class="progdesc">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                     <div class=" offrpromo fontpnr">{{programs.programdetails.description}}</div>
<!--                                     <div class=" promosubhead fontpnr">This is a subheader</div>-->
                                     <p class="prompara fontpnr">
                                     </p>
<!--                                     <p class="helplink fontpnr">Link to help you out</p>-->
                                     </div>
                                </div>                                  
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                             <div class="recuremlautom fontpns">Recurring Email Automation</div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                             <div class="emlstats fontpnr">Status</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="emlautomline">
                        </div>
                    </div>
                    <div class="row" ng-repeat="emailautomation in programs.emailautomation">
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="emlchkbox"><input type="checkbox"  id=""  value="" /></div>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5">
                             <div class="listelem fontpnr">{{emailautomation.programTemplateName}}</div>
                             <div class="sublst fontpnr">{{emailautomation.dateTime}}</div>
                              <div class="sublst fontpnr">{{emailautomation.emailRecuringTemplateName}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="stslst fontpnr">{{emailautomation.status}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <button type="button" class="emledtbtn fontpnr">Edit</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="emlautombtmline">
                        </div>
                    </div>
                    
                   <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="emlautombtmline">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <button type="button" class="addemlautombtn fontpnr">Add Email Automation</button>
                             <hr class="addemlautomline">
                        </div>
                    </div>
                     <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4">
                             <div class="otact fontpns">One Time Actions</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                             <div class="otactdt fontpnr">Date</div>
                        </div>
                         <div class="col-lg-1 col-md-1 col-sm-1">
                             <div class="otacttype fontpnr">Action Type</div>
                        </div>
                         <div class="col-lg-2 col-md-2 col-sm-2">
                             <div class="otactstats fontpnr">Status</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="otactline">
                        </div>
                    </div>
                    <div class="row" ng-repeat="programaction in programs.programactions">
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="emlchkbox"><input type="checkbox"  id=""  value="" /></div>
                        </div>
                         <div class="col-lg-3 col-md-3 col-sm-3">
                             <div class="listelem fontpnr">{{programaction.programTemplateName}}</div>
                             <div class="otasublst fontpnr"> {{programaction.postTime}}</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="oatlstdt fontpnr">{{programaction.postDate}}</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="oatlstpost fontpnr">{{programaction.actionType}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="oatlststat"></div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <button type="button" class="emledtbtn fontpnr">Edit</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="otactlstline">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <button type="button" class="otaddactbtn fontpnr">Add Action</button>
                             <hr class="addemlautomline">
                        </div>
                    </div>
                            
                    </div>
                </div>
            </div>
    </body>
</html>