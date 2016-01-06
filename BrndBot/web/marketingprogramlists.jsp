<%-- 
    Document   : marketingprogramcurrentlist
    Created on : Dec 17, 2015, 4:42:23 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
     <title>Marketing Programs lists</title>
    <script src="js/configurations.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/angular.min.js"></script>
    <jsp:include page="basejsp.jsp"/>
    <style>
        #pastprogs{display: none;}
    </style>
     <script>
         var selected_schedules_to_delete = "";
         
         function pastprograms(){
             document.getElementById("currprogs").style.display = "none";
             document.getElementById("pastprogs").style.display = "block";
             document.getElementById("pstmarprogli").classList.add("top-subnav-link-active");
             document.getElementById("pstmarprog").classList.add("h3-active-subnav");
             document.getElementById("curmarprogli").classList.remove("top-subnav-link-active");
             document.getElementById("curmarprog").classList.remove("h3-active-subnav");
             document.getElementById("curmarprogli").classList.add("top-subnav-links");     
             document.getElementById("curmarprog").classList.add("h3");    
         }
        function currprograms(){
             document.getElementById("pastprogs").style.display = "none";
             document.getElementById("currprogs").style.display = "block";
             document.getElementById("curmarprogli").classList.add("top-subnav-link-active");
             document.getElementById("curmarprog").classList.add("h3-active-subnav");
             document.getElementById("pstmarprogli").classList.remove("top-subnav-link-active");
             document.getElementById("pstmarprog").classList.remove("h3-active-subnav");
             document.getElementById("pstmarprogli").classList.add("top-subnav-links");     
             document.getElementById("pstmarprog").classList.add("h3");
             
         }
        function controllerUserMarketingProgamsByStatus($scope, $http){
          
            $scope.getUserMarketingProgramsOpen = function(){
//                
//                $("#pastprogs").css("display","none");
//                $("#currprogs").show();
//                $("#email ").show();
//                $("#prog").hide();
                var programStatus = { "programType": "Open" };
                
                $http({
                    method: 'GET',
                    url: 'listAllMarketingProgram.do?programType=Open',
                    data: programStatus 
                }).success(function (data, status, headers, config) {
                    $scope.current_programs = data.programs;
//                    $(".row").css("display","block");
                }).error(function (data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
//                    $(".row").css("display","block");
                });                
            };
            $scope.getUserMarketingProgramsClosed = function(){
                var programStatus = { "programType": "Closed" };
//                $("#pastprogs").show();
////                $("#prog").show();
//                $("#currprogs").hide();
//                $("#email").hide();
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
                window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program_id, "_self");
            };
            
        }
        var count=0;
        function selcheckbox(id){
//            alert(id+"--selected");
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
//            alert(content);
//            var a=$("#"+id).text();alert(a);
            var htm=$("#"+id).html();
            
            var selected_schedule_id=id;
            if(htm.contains('class="check-icon"')){
                selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
                count-=1;
                $("#"+id).html(content);
            }
            else
            {
                selected_schedules_to_delete = selected_schedule_id + "," + selected_schedules_to_delete;
//                alert(selected_schedules_to_delete);
                count+=1;
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $(".add-action-button").hide();
                $(".delete-button").show();
            }
            if(count==0)
            {
                $(".add-action-button").show();
                $(".delete-button").hide();
            }
        }
        </script> 
</head>    

<body ng-app class="">
    <!--SideNav-->
    <div class="content-main" ng-controller="controllerUserMarketingProgamsByStatus">
     <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="page-title-regular page-title-font">Your Marketing Programs</div>
            <div class="page-cta-container">
                <a href="createmarketingprogram.jsp" class=" fleft">
                    <div class=" add-button md-button"> Create New Marketing Program</div>    
                </a>
            </div>
        </div>
        <div class="page-subnav-bar-regular"> 
            <div class="top-subnav-tabs-container">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-links" id="createnewprogli"><a id="createnewprog" class="h3">Create New Program</a></li>
                    <li class="top-subnav-link-active" id="curmarprogli"><a id="curmarprog" class="h3-active-subnav" onclick="currprograms()">Current Marketing Programs</a></li>
                    <li class="top-subnav-links" id="pstmarprogli"> <a class="h3" id="pstmarprog" onclick="pastprograms()">Past Marketing Programs</a></li>
                </ul>
            </div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
    <div class="page-background" >
        <div class="page-content-container email-list-contact-page">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content" ng-init="getUserMarketingProgramsOpen()">
                    <!--List Starts Here-->
                    <ul class="main-container fleft" ng-show="current_programs==''">
                        <li class="slat-container fleft selfclear">
                            <div class="col-1of1 slat-unit fleft " >
                                No Programs Available
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="fleft content" id="currprogs" ng-init="getUserMarketingProgramsOpen()">
                    <ul  class="main-container fleft" ng-show="current_programs!=''">
                        <li class="slat-container fleft selfclear" ng-repeat="program in current_programs">
                            <div class="col-1of1 slat-unit fleft ">
                                <div class="selection-container col-5p fleft"> 
                                    <div class="selection-icon" id="{{program.id}}" onclick="selcheckbox(this.id);setSelectedIds(this.id);"><input type="checkbox" id="entityid{{program.id}}" value="{{program.id}}" name="entityname" hidden></input></div>
                                </div>
                                <div class="slat-title-container col-3of10 fleft">
                                    <div class="slat-title  col-1of1 sh1-contact">{{program.program_name}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3-contact">Added on {{program.start_date | date: 'MMM dd yyyy'}}</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{program.end_date | date: "MMMM dd, yyyy"}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft" ng-hide="program.noofpostleft=='0'">{{program.noofpostleft}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft"ng-show="program.noofpostleft!='0'">Actions Left</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft" ng-show="program.noofpostleft=='0'">No Actions Left</div>
                                </div>
                                <div class="col-2of10 fleft">
                                    <div class="slat-cta-container">
                                        <a href="">
                                            <div class="small-button slat-button detail-button-font" ng-click="sendProgramId(program.id)">View Program</div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="fleft content" id="pastprogs" ng-init="getUserMarketingProgramsClosed()">
                    <ul  class="main-container fleft" ng-show="past_programs!=''">
                        <li class="slat-container fleft selfclear" ng-repeat="program in past_programs">
                            <div class="col-1of1 slat-unit fleft " >
                                <div class="selection-container col-5p fleft"> 
                                  <div class="selection-icon" id="{{program.id}}" onclick="selcheckbox(this.id);setSelectedIds(this.id);"><input type="checkbox" id="entityid{{program.id}}" value="{{program.id}}" name="entityname" hidden></input></div>
                                </div>
                                <div class="slat-title-container col-3of10 fleft">
                                    <div class="slat-title  col-1of1 sh1-contact">{{program.program_name}}</div>
                                    <div class="action-list-slat-description col-1of1 sh3-contact">Added on {{program.start_date | date: 'MMM dd yyyy'}}</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{program.end_date | date: "MMMM dd, yyyy"}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-2of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{program.noofpostleft}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Actions Left</div>
                                </div>
                                <div class="col-2of10 fleft">
                                    <div class="slat-cta-container">
                                        <a href="">
                                            <div class="small-button slat-button detail-button-font"  ng-click="sendProgramId(program.id)">View Program</div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                     <ul class="main-container fleft" ng-show="past_programs==''">
                        <li class="slat-container fleft selfclear">
                            <div class="col-1of1 slat-unit fleft " >
                                No Past Programs Available
                            </div>
                        </li>
                    </ul>
                </div>
                </div>
            </div>            
        </div>
        </div>
    </div>
</body>
</html>