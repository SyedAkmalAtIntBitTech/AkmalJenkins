<%-- 
    Document   : mindbody
    Created on : Dec 30, 2015, 3:35:37 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>
<%@page import="com.controller.SqlMethods"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <script src="js/angular.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="js/jquery-ui.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="js/configurations.js"></script>
    <script src="js/mindbodyutility.js" type="text/javascript"></script>
    <title>BrndBot - MindBody Selection</title>
    <style>
         #picktheme{display: none;}
    </style>
        <%!
            SqlMethods sql_methods = new SqlMethods();
            String category_id, sub_category_name, sub_category_id;
        %>

        <%
            String title = "";
            try {
                sql_methods.session = request.getSession(true);
                category_id = request.getParameter("category_id");
                sub_category_name = request.getParameter("sub_category_name");
                sub_category_id = request.getParameter("sub_category_id");
                sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                sql_methods.session.setAttribute("category_id", category_id);

//                if (sub_category_name.equalsIgnoreCase("promote todays class")) {
//                    title = " todays class";
//                } else if (sub_category_name.equalsIgnoreCase("promote work shop")) {
//                    title = "work shop";
//                }
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                sql_methods.closeConnection();
            }
        %>
        
        <script>
            angular.module("myapp", [])
                    .controller("controllerGetMindBody", function($scope, $http) {
                         $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","40%").css("left","45%");
                         $(".page-background").css("background-color","#fff");
//                          $("#continuebutton").hide();
                            $scope.showData = function(){
                            $scope.curPage = 0;
                                    $scope.pageSize = 4;
                                    $http({
                                    method : 'GET',
                                    url : 'MindBodyDataServlet'
                                    }).success(function(data, status, headers, config) {
                                        var minddata= JSON.stringify(data.mindbody_data);
                                        if(minddata === undefined){
                                       
                                        $('#loadingGif').remove();
                                        $('#nodata').show();
                                        $('#nodata').css("margin-left","180px");
                                        }
                                        $scope.datalists = data;
//                                    alert(JSON.stringify(data));
                                    $(".page-background").css("background-color","#EFF2F6");
                                    $("#picktheme").css("overflow-y", "auto");
                                    if (data.mindbody_data.length == 0){
                            $("#continuebutton").hide();
                            } else {
                            $("#continuebutton").show();
                            $("#picktheme").css("display","block");
                            }
                            if (data === error){
                            alert(data);
                            }
                             $('#loadingGif').remove();
                            }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                            };
                    });
                    var selected_id;
                    function select_category_details(id){
                                       selected_id = id;
                    }
                     function selected_category(){
                        var configuration = global_host_address + "channelselection.jsp" + "?id=" + selected_id;
                        window.open(configuration, "_self");
                     }
        </script>
       <jsp:include page="basejsp.jsp" />
</head>    

<body ng-app = "myapp">
    <!--SideNav-->
    <div class="content-main" ng-controller="controllerGetMindBody">
         <%@include file="navbarv2.jsp" %>
<!--    <div class="navigation">
        <div class="main-nav-logo">
            <a class=" bb-logo-nav" href="Dashboard.html">
                <object type="image/svg+xml" data="/Icons/Logo_Reverse.svg" class="bb-logo" style="cursor:pointer;"> </object>
            </a>
        </div>
        <ul class="nav-tabs">
            <li class="nav-elements-icon-container">
                <a href="/Newest_Files/YourPlan.html">
                    <object type="image/svg+xml" data="/Icons/yourPlan.svg" class="nav-elements-icon" style="cursor:pointer;"> </object>
                </a>
            </li>
            <li class="nav-elements-icon-container">
                 <a class="" href="/Newest_Files/MarketingProgram_CurrentList.html">
                    <object type="image/svg+xml" data="/Icons/marketingProgram.svg" class="nav-elements-icon" style="cursor:pointer;"> </object>
                </a>
            </li>
            <li class="nav-elements-icon-container">
                 <a  href="/Newest_Files/EmailHub_Lists_clean.html">
                    <object type="image/svg+xml" data="/Icons/yourHubs.svg" class="nav-elements-icon" style="cursor:pointer;"> </object>
                </a>
            </li>
             <li class="nav-elements-icon-container">
                 <a  href="/Newest_Files/UserSettings_AccountSettings.html">
                    <object type="image/svg+xml" data="/Icons/media.svg" class="nav-elements-icon" style="cursor:pointer;"> </object>
                </a>
            </li>
            <li class="nav-elements-icon-container">
                 <a  href="/Newest_Files/EmailHub_Lists_clean.html">
                    <object type="image/svg+xml" data="/Icons/user.svg" class="nav-elements-icon" style="cursor:pointer;"> </object>
                </a>
            </li>
            <li class="nav-elements-icon-container-bottom">
                 <a  href="/Newest_Files/EmailHub_Lists_clean.html">
                    <object type="image/svg+xml" data="/Icons/logout.svg" class="nav-elements-icon" style="cursor:pointer;"> </object>
                </a>
            </li>
        </ul>    
    </div>-->
        
    <!--Top Nav-->   
      <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
           <a class=" exit-button-icon" href="subcategory.jsp?id=<%=category_id%>">
           <div class="exit-button-detail">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
           </div>
            </a>
            <div class="page-title-with-back page-title-font">MINDBODY Selection</div>
        </div>
   
    </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
        <div class="externalData-selection-content">
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container">
                <div id="nodata" hidden="true" class="page-content-title h2">No Mindbody data in selected Category <br>please select some other Category.</div>
                <div class="fleft content" ng-init="showData()" id="picktheme">
                    <div class="page-content-title h2">Please select a workshop to promote:</div>
                    <!--List Starts Here-->
                    <div class="main-container col-1of1  pushUp fleft containerheight">
                    <ul class="subcategory-list-container col-1of1 fleft"  ng-repeat="jsonclass in datalists.mindbody_data">
                   <li id="{{jsonclass.id}}"class="mindbodyOneRowData subcategory-list-element col-1of1 fleft" onclick="select_category_details('{{jsonclass.id}}');selected_category();">
                       <div class="subcat-list col-4of10 fleft padding-right-15 ">{{jsonclass.column1}}
                       </div>
                        <div class=" col-2of10 fleft ">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft margin-top-12">{{jsonclass.column3}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">{{jsonclass.column2}}</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Instructor</div>
                                </div>
                       <div class="col-21f10 fleft">
                           <img type="image/svg+xml" src="images/Icons/nextArrow.svg" class="next-button-icon"></img>
                       </div>
                       
                   </li>
<!--                         <li class="subcategory-list-element col-1of1 fleft ">
                       <div class="subcat-list col-4of10 fleft padding-right-15 ">Name of Workshop Here and 
                       </div>
                        <div class=" col-2of10 fleft ">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft margin-top-12">Nov 27 at 6:30am</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Andy Swansburg</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Instructor</div>
                                </div>
                       <div class="col-21f10 fleft">
                           <object type="image/svg+xml" data="/Icons/nextArrow.svg" class="next-button-icon" > </object>
                       </div>
                       
                   </li>
                         <li class="subcategory-list-element col-1of1 fleft ">
                       <div class="subcat-list col-4of10 fleft padding-right-15 ">Name of Workshop Here and 
                       </div>
                        <div class=" col-2of10 fleft ">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft margin-top-12">Nov 27 at 6:30am</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Andy Swansburg</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Instructor</div>
                                </div>
                       <div class="col-21f10 fleft">
                           <object type="image/svg+xml" data="/Icons/nextArrow.svg" class="next-button-icon" > </object>
                       </div>
                       
                   </li>
                         <li class="subcategory-list-element col-1of1 fleft ">
                       <div class="subcat-list col-4of10 fleft padding-right-15 ">Name of Workshop Here and 
                       </div>
                        <div class=" col-2of10 fleft ">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft margin-top-12">Nov 27 at 6:30am</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">End Date</div>
                                </div>
                                <div class=" col-3of10 fleft slat-attribute-container">
                                    <div class="slat-column-font list-column-number col-1of1 sh2 fleft">Andy Swansburg</div>
                                    <div class="list-column-description col-1of1 sh3-contact fleft">Instructor</div>
                                </div>
                       <div class="col-21f10 fleft">
                           <object type="image/svg+xml" data="/Icons/nextArrow.svg" class="next-button-icon" > </object>
                       </div>
                       
                   </li>
               -->
                   
                   
                </ul>
                </div>
            </div>
        </div>
    </div>
  
        <!--CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
             REMOVE BUTTON HERE
               <div class="remove-button-detail md-button button-text-1">Delete Selected Actions</div>

            </div>-->
        </div>
        </div>
<!--</div>-->
    </body>
</html>