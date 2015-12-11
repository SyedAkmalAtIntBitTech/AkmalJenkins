<%-- 
    Document   : channelselectionv2
    Created on : Dec 11, 2015, 11:57:11 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>
<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String minbodyid;%>
<% minbodyid = request.getParameter("id");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>BrndBot - Channel Selection</title>
    <meta charset="UTF-8"/>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="css/favicon.png"></link>
    <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
      <%!
            SqlMethods sql_methods = new SqlMethods();
            String category_id, sub_category_name, sub_category_id;
        %>

        <%
            try {
                sql_methods.session = request.getSession(true);
                category_id = request.getParameter("category_id");
                sub_category_name = request.getParameter("sub_category_name");
                sub_category_id = request.getParameter("sub_category_id");
                if(category_id==null)
                {
                    category_id = sql_methods.session.getAttribute("category_id").toString();
                    sub_category_name = sql_methods.session.getAttribute("sub_category_name").toString();
                    sub_category_id = sql_methods.session.getAttribute("sub_category_id").toString();
                }
                else
                {
                    sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                    sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                    sql_methods.session.setAttribute("category_id", category_id);
                }

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        %>
        <script>
            $(document).ready(function (){
                $("#soc,#eml,#prnt,#dwnld").hide();
            });
            var print = "print";
            var download = "image";
            function selected_media(selectedmedia) {
                
                if (selectedmedia == print){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id=" +<%= minbodyid %>+"&mediatype=print";
                    window.open(configuration, "_self");
                }else if (selectedmedia == download){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id=" +<%= minbodyid %>+"&mediatype=image";
                    window.open(configuration, "_self");
                }else if (selectedmedia == 'social'){
                    var configuration = global_host_address + "socialeditor.jsp" + "?id=" +<%= minbodyid %>+"&mediatype=social";
                    window.open(configuration, "_self");
                }else if (selectedmedia == 'emailsubject'){
                    var configuration = global_host_address + "emailsubject.jsp" + "?id=" +<%= minbodyid %>;
                    window.open(configuration, "_self");
                }    
            }
            
            function selectPromoteMediaController($scope, $http){
                
                $scope.checkTemplateAvailability = function(){
                    var category_id = <%=category_id%>;
                    var sub_category_id = <%=sub_category_id%>;
                    
                    var category = {"category_id": category_id, "sub_category_id": sub_category_id};
                    
                    $http({
                        method: 'POST',
                        url: 'GetTemplates',
                        headers: {'Content-Type': 'application/json'},
                        data: category
                    }).success(function (data, status, headers, config) {
                        $scope.email_templates = data.email_template_availability;
                        $scope.social_templates = data.social_template_availability;
                        $scope.social_temlates_print=data.social_template_print;
                        $scope.social_temlates_download=data.social_template_download;
                        
                        if($scope.email_templates !== 0){$("#eml").show();}
                        if($scope.social_templates !== 0){$("#soc").show();}
                        if($scope.social_temlates_print !== 0){$("#prnt").show();}
                        if($scope.social_temlates_download !== 0){$("#dwnld").show();}
                                     
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
        <jsp:include page="basejsp.jsp" />
</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main">
    <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="page-title-regular page-title-font">Channel Selection</div>
            <div class="page-cta-container"></div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="dashboard-background" ng-controller="selectPromoteMediaController">
            <div class="dashboard-content" ng-init="checkTemplateAvailability()">
                <div class="h1"> What would you like to do today?</div>
                <div class="button-row col-1of1">
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="social_templates != 0"  data-hint="Social" >
                        <a  onclick="selected_media('social')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/NavIcon_Social-white.svg" id="social" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                    <div class="button-column fleft col-1of10 pushright hint--left" ng-show="email_templates != 0" data-hint="Email" >
                        <a  onclick="selected_media('emailsubject')" class="fleft" style="height:100px; width:100px;">
                            <img src="images/NavIcon_Email-white.svg" id="email" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="social_temlates_print != 0" data-hint="Print">
                        <a  onclick="selected_media('print')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/NavIcon_Social-white.svg" id="print" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-show="social_temlates_download != 0" data-hint="Download">
                        <a  onclick="selected_media('image')" class="fleft" style="height:100%; width:100%;">
                            <img src="images/NavIcon_Social-white.svg" id="download" class="big-selection-button" style="cursor:pointer;"/>
                        </a>
                    </div>
                 
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
<!--        </div>
        </div>
</div>-->
    </body>
</html>