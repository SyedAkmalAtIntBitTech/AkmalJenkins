<%-- 
    Document   : marketingActions
    Created on : Oct 20, 2015, 11:22:12 AM
    Author     : development
--%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="com.intbit.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/configurations.js"></script>
        <script type="text/javascript" src="../js/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link href="../css/main1.css" rel="stylesheet" type="text/css"/>
        <script src="../js/categoriesfunctions.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            function showFileName() {
                var fil = document.getElementById("myFile");
                var file = fil.value;
                alert(file);
//                fil.setAttribute("name", file)
            }
        </script>
        <title>Marketing Actions</title>
    <jsp:include page="checksession.jsp" />
    <jsp:declaration>
        Logger logger = Logger.getLogger("categories.jsp");
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        Integer number = 1;
    </jsp:declaration>
    <style>
        .textbox{width:120px; height:20px;}
        
    </style>
    <%
        try{
            if (exist1 != ""){
                exist1 = "";
            }
            if ((request.getParameter("exist") != null) && (request.getParameter("exist") != "")){
                    exist = request.getParameter("exist");
                    if (exist.equals("exist")){
                        exist1 = "Record already exist";
                    }else if (exist == "" ) {
                        exist1 = "";
                    }
            }else if ((request.getParameter("exist") == null) && (request.getParameter("exist") == "")) {
                    exist1 = "";
            }
        }catch (Exception e){
            out.println(e.getCause());
        }        
    %>
    <script>
            var marketing_actions = {actions:[]};
            
            $(document).ready(function ()
            {
                $("#is_recuring").click(function(){
                   var is_recuring = document.getElementById("is_recuring").checked;
                   if(is_recuring == true){
                       $("#marketingactiontilldate").attr("disabled",false);
                   }else if(is_recuring == false){
                       $("#marketingactiontilldate").attr("disabled",true);
                   }
                });
            });            
            
            function validate(){
                var marketingactiontitle = $("#marketingactiontitle").val();
                var days = $("#days").val();
                var marketingactiontime = $("#marketingactiontime").val();
                var is_recuring = $("#is_recuring").val();
                var marketingactiontilldate = $("#marketingactiontilldate").val();
                var description = $("#description").val();
                var type = $("#type").val();

                if (marketingactiontitle == ""){
                    alert("title not entered, kindly enter the title");
                    $("#marketingactiontitle").focus();
                    return false;
                }
                
                if (days == ""){
                    alert("days not selected, kindly select an days");
                    $("#days").focus();
                    return false;
                }
                
                if (marketingactiontime == ""){
                    alert("time not entered,kindly enter the time");
                    $("#marketingactiontime").focus();
                    return false;
                }
                
                if (description == ""){
                    alert("description not entered,kindly enter the description");
                    $("#description").focus();
                    return false;
                }
                
                if (type == ""){
                    alert("type not entered,kindly enter the type");
                    $("#type").focus();
                    return false;
                }
                
                return true;
            }            
            function marketingActionsController($scope,$http){
                
                $scope.getMarketingCategories = function(){
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetMarketingCategories'
                    }).success(function (data, status, headers, config) {
                        $scope.categories = data;
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
                
                $scope.getMarketingPrograms = function(){
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetMarketingPrograms'
                    }).success(function (data, status, headers, config) {
                        $scope.programs = data;
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
            
                $scope.createMarketingActionsJSON = function(){
                    
                    if (validate()){
                        var marketingactiontitle = $("#marketingactiontitle").val();
                        var days = $("#days").val();
                        var marketingactiontime = $("#marketingactiontime").val();
                        var is_recuring = $("#is_recuring").val();
                        var marketingactiontilldate = $("#marketingactiontilldate").val();
                        var description = $("#description").val();
                        var type = $("#type").val();
                        
                        marketing_actions.actions.push(
                                {"title":marketingactiontitle, "days": days,
                                 "time":marketingactiontime, "is_recuring": is_recuring,
                                 "till date": marketingactiontilldate,"type":type,
                                 "description":description });
                    }
                         
                    console.log(JSON.stringify(marketing_actions));
                };
                
                $scope.validateform = function(){
                    var category = $("#category").val();
                    var programs = $("#programs").val();
                  
                    if (category == "0"){
                        alert("category not selected, kindly select the category");
                        $("#category").focus();
                        return false;
                    }

                    if (programs == "0"){
                        alert("programs not selected, kindly select an one program");
                        $("#programs").focus();
                        return false;
                    }
                  return true;
                };
                
                $scope.saveMarketingActions = function(){
                    
                  if($scope.validateform()){
                    var category = $("#category").val();
                    var programs = $("#programs").val();

                    var marketingactions = {"category": category, "programs": programs, "marketing_actions": marketing_actions};

                    $http({
                        method: 'POST',
                        url: getHost() + 'SetMarketingActions',
                        headers: {'Content-Type': 'application/json'},
                        data: JSON.stringify(marketingactions)
                    }).success(function (data, status){
                          $scope.marketingcategories = data;
                    }).error(function (){
                          alert("No data available, problem fetching the data");
                    });
                      
                  }
                };
                
                $scope.getMarketingActions = function(){
                    
                    $http({
                        method: 'GET',
                        url:getHost() + 'GetMarketingActions'
                    }).success(function (data, status){
                        $scope.marketingActions = data;
                    }).error(function (){
                        alert("No data available, problem fetching the data");
                    });
                };
            };
        
    </script>        
    </head>
    <body ng-app class="container">
    <%@include file="menus.jsp" %>
        <div class="jumbotron" align="center" ng-controller="marketingActionsController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 800px; width: 600px;">
                <form name="formCategories" >

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p>Marketing Actions:</p>
                    </div>
                </div>
                <div style="float:left; left:20px; padding-left: 166px;">
                    <%= exist1 %>
                   Select category:<select name="category" id="category" style="width:180px;">
                                       <option value="0">--select--</option>
                                       <option value="" ng-repeat="category in categories"></option>
                                   </select><br><br>
                   Select program:<select name="programs" id="programs" style="width:180px;">
                                       <option value="0">--select--</option>
                                       <option value="" ng-repeat="program in programs"></option>
                                  </select><br><br>
                </div>
                <div style="float:left; border:1px solid #000; left:0px; padding-left: 6px; margin-left: 150px; position:relative; width: 350px; top:0px;">
                        Title:<input type="text" id="marketingactiontitle" name="marketingactiontitle" />
                        
                        Days:<input type="text" id="days" name="days" />
                        
                        Time:<input type="time" id="marketingactiontime" name="marketingactiontime"/><br>
                        Recurring: <input type="checkbox" id="is_recuring" style="width:25px;"/>
                        Till Date: <input type="date" id="marketingactiontilldate" name="marketingactiontilldate"/><br>
                        Description:<input type="text" name="description" id="description"/><br>
                       
                        Type:<select id="type" name="type">
                            <option value="0">--Select--</option>
                            <option value="email">email</option>
                            <option value="facebook">facebook</option>
                            <option value="twitter">twitter</option>
                            <option value="note">note</option>
                        </select>
                        <br><br>
                        <button type="button" id="saveaction" name="saveaction" class="btn btn-info" ng-click="createMarketingActionsJSON()">Save Action</button>
                        <br>
                </div>
                <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                    <br>
                    <div style="float: left; left:20px; margin-top: -110px;">
                        <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="saveMarketingActions()">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

                </form>
            </div>
            
            <br>
            <div ng-init="getMarketingActions()" style="margin-top: 0px;">

                <div>&nbsp;Display Actions<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Category Name</td>
                        <td>Program Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                   
                    <tr ng-repeat = "marketing in marketingActions">
                        <td>{{marketing.action_id}}</td>
                        <td>{{marketing.category}}</td>
                        <td>{{marketing.program}}</td>
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editActions()">edit</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteActions(marketing.action_id)">delete</button></td>
                    </tr>
                    
                </table>
            </div>
            <br>

        </div>

</html>