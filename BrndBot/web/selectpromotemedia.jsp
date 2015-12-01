<%-- 
    Document   : selectpromotemedia
    Created on : 29 Jul, 2015, 11:48:01 AM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String minbodyid;%>
<% minbodyid = request.getParameter("id");%>
<!DOCTYPE html>
<html>
    <head>

        <title>BrndBot - Channel Selection</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
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

        <div ng-controller="selectPromoteMediaController"> 
            <div class="row" ng-init="checkTemplateAvailability()">
                <jsp:include page="mainmenu.html"/><!--/end left column-->

                <div class="col-md-10 col-md-offset-2">
                    <p id="text3"  class="MH2" style="padding-bottom: 2%;">How would you like to promote it?</p> 

                    <ul id="promotebuttonlist">
                        <li id="soc"  ng-show="social_templates != 0"><a onclick="selected_media('social')"><img src="images/NavIcon_Social-white.svg" id="social" class="glyphicon glyphicon-comment"/></a><p id="soceml">Social</p></li>
                        <li id="eml" ng-show="email_templates != 0"><a onclick="selected_media('emailsubject')"><img src="images/NavIcon_Email-white.svg" id="email" class="glyphicon glyphicon-envelope" style="padding-bottom:7%;"/></a><p id="soceml">Email</p></li>
                        <li id="prnt" ng-show="social_temlates_print != 0"><a onclick="selected_media('print')"><img src="images/NavIcon_Social-white.svg" id="print" class="glyphicon glyphicon-comment"/></a><p id="soceml">Print</p></li>
                        <li id="dwnld" ng-show="social_temlates_download != 0"><a onclick="selected_media('image')"><img src="images/NavIcon_Social-white.svg" id="download" class="glyphicon glyphicon-comment"/></a><p id="soceml">Image</p></li>
<!--                                <li><a ><span id="print" class="glyphicon glyphicon-print"></span></a><p id="promotebutton">Print</p></li>-->
                    </ul>    
                </div>

            </div>      

        </div>   
    </body>
</html>
