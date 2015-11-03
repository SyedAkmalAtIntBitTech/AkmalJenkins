<%-- 
    Document   : programdetail
    Created on : Oct 14, 2015, 11:43:14 AM
    Author     : Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <title>Email Program Action</title>
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
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
                $scope.setEntityId = function(entity_list_Id, days){
                    window.open(getHost() + 'emailautomation.jsp?entitylistid='+entity_list_Id+'&days='+days, "_self");
                };
            };
            
    </script>
    <script>
        function overlay(){
                    document.getElementById('light').style.display = 'block';
                        document.getElementById('fade').style.display = 'block';
                        document.getElementById('slider-button').style.display = 'block';
                        document.body.style.overflow = 'hidden';
                }
        function closeoverlay(){
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none';
                document.body.style.overflow = 'scroll';
        }
    </script>
        <jsp:include page="basejsp.jsp"/>
    </head>
    <body ng-app>
        <div class="row" ng-controller="programactions">
            <div class="row" ng-init="getProgramActions()" >
                <div class="col-lg-1 col-md-1 col-sm-2">
                   <jsp:include page="leftmenu.html"/> 
                </div>
                <div id="fade" class="black_overlay"></div>
                <div class="col-md-11 col-sm-10 col-lg-11 col-lg-offset-2 col-md-offset-2">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div class="markprog fontpns">{{programs.programdetails.programName}}</div>
                            <div class="edtprog fontpnr">Edit Program Details</div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div class="endmrkprogbtndiv fontpnr"><button type="button" class="endmrkprogbtn button button--moema button--text-thick button--text-upper fontpnr">
                                End Marketing Program</button></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="noofact fontpnr">Number of actions</div>
                            <div class="actno fontpns">{{programs.programdetails.noOfActions}}</div>
                        </div>
                         <div class="col-lg-2 col-md-2 col-sm-4">
                            <div class="dtofevnt fontpnr">Date of Event</div>
                            <div class="evntdt fontpns">{{programs.programdetails.dateOfEvent| date:'MMM dd yyyy'}}</div>
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
                        <div class="col-lg-3 col-md-3 col-sm-3">
                             <div class="recuremlautom fontpns">Recurring Email Automation</div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                             <div class="addemlautomdiv "><button type="button" class="addemlautombtn button button--moema button--text-thick button--text-upper button--size-s fontpnr">
                                Add Email Automation</button></div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-lg-offset-1">
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
                             <div class="sublst fontpnr">Started on {{emailautomation.dateTime| date:'  d/M/yy'+' At '+'h:mm a'}}&nbsp;&nbsp;&nbsp;&nbsp;{{emailautomation.emailRecuringTemplateName}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1">
                            <div class="stslst fontpnr">{{emailautomation.status}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <button type="button" 
                                    ng-click="setEntityId(emailautomation.scheduledEntityListId, emailautomation.days)" 
                                    class="emledtbtn button 
                                           button--moema button--text-thick 
                                           button--text-upper 
                                           button--size-s 
                                           fontpnr">
                                Details
                            </button>
                        </div>
                        <hr class="emlautombtmline padlineleftreceml">
                    </div>
<!--                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="emlautombtmline">
                        </div>
                    </div>-->
                
                     <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-2">
                             <div class="otact fontpns">One Time Actions</div>
                        </div>
                          <div class="col-lg-2 col-md-2 col-sm-2">
                              <div class="addactdiv">
                                  <a href = "javascript:void(0)" onclick = "overlay();">
                                        <button type="button" class="otaddactbtn  button button--moema button--text-thick button--text-upper button--size-s fontpnr">Add Action</button>
                                  </a>      
                                 </div>
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
                             <div class="otasublst fontpnr">Scheduled for {{programaction.postTime| date:'h a'}}</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="oatlstdt fontpnr">{{programaction.postDate| date:'MMM yy'}}</div>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1">
                            <div class="oatlstpost fontpnr">{{programaction.actionType}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="oatlststat fontpnr">{{programaction.status}}</div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1">
                            <button type="button" class="emledtbtn button button--moema button--text-thick button--text-upper button--size-s  fontpnr">Details</button>
                        </div>
                        <div class="otactlstlinediv">
                        <hr class="otactlstline">
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <hr class="otactlstline">
                        </div>
                    </div>-->
                 
                    </div>
                      <div id="light" class="white_content">
                        <a href = "javascript:void(0)" onclick = "closeoverlay();" style="text-decoration:none;">
                            <div id="slider-button" style="z-index:1006;display:none;">
                                <img src="images/CloseIcon.svg" height="25" width="25" />
                            </div>
                        </a>
                    </div>      
                </div>
        </div>
    </body>
</html>