
<%-- 
    Document   : marketingPrograms
    Created on : Oct 20, 2015, 4:24:23 PM
    Author     : development
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="com.intbit.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<jsp:include page="checksession.jsp" />
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
        <title>categories</title>
        <script src="../js/categoriesfunctions.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            function showFileName() {
                var fil = document.getElementById("myFile");
                var file = fil.value;
                alert(file);
//                fil.setAttribute("name", file)
            }
        </script>
        <script>
            
            function validate(){
                var program_name = $("#program_name").val();
                var user = $("#users").val();
                var category = $("#category").val();
                var program_order = $("#program_order").val();
                var html_data = $("#html_data").val();

                if (program_name == ""){
                    alert("program name not entered, kindly enter the program");
                    $("#program_name").focus();
                    return false;
                }
                
                if (user == ""){
                    alert("user not selected, kindly select an user");
                    $("#users").focus();
                    return false;
                }
                
                if (category == ""){
                    alert("category not entered,kindly enter the category");
                    $("#category").focus();
                    return false;
                }
                
                if (program_order == ""){
                    alert("program_order not entered,kindly enter the program order");
                    $("#program_order").focus();
                    return false;
                }
                if (html_data == ""){
                    alert("html data not entered,kindly enter the html data");
                    $("#html_data").focus();
                    return false;
                }
                
                return true;
            }
            
            function marketingCategoriesController($scope, $http){
                
                $scope.getUsers = function(){
                    $http({
                        method: 'GET',
                        url: getHost() + ''
                    }).success(function (data, status, headers, config) {
                        $scope.Users = data;
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
                
                $scope.getMarketingCategories = function(){
                    
                    var category_details = {"type": "get"};

                    $http({
                        method: 'GET',
                        url: getHost() + 'allmarketingCategory.do'
                    }).success(function (data, status){
                        $scope.marketingcategories = data.marketingData;
                    }).error(function (){
                        alert("No data available, problem fetching the data");
                    });
                };
                
                $scope.saveMarketingPrograms = function(){
                    if (validate()){
                        var program_name = $("#program_name").val();
                        var user = $("#users").val();
                        var category = $("#category").val();
                        var program_order = $("#program_order").val();
                        var html_data = $("#html_data").val();

                        var marketing_program = {"program_name":program_name,
                                                 "user":user, "category":category, 
                                                 "program_order":program_order,
                                                 "html_data":html_data
                                                };
                        $http({
                           method: 'POST',
                           url:getHost() + 'setMarketingPrograms.do',
                           headers: {'Content-Type': 'application/json'},
                           data: JSON.stringify(marketing_program)
                        }).success(function (data, status){
                            window.open(getHost() + 'admin/marketingprogram.jsp', "_self");
                        }).error(function(){
                            alert("No data available, problem fetching the data");
                        });
                    };
                    
                };
                
                $scope.getMarketingPrograms = function(){
                  $http({
                        method: 'GET',
                        url: getHost() + 'getMarketingPrograms.do'
                    }).success(function (data, status, headers, config) {
                        $scope.programs = data;
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });  
                };
                
                $scope.deleteProgram = function(program_id){
                    
                    var program_details = {"program_id": program_id};
                    
                    $http({
                        method: 'POST',
                        url: getHost() + 'deleteMarketingPrograms.do',
                        headers: {'Content-Type': 'application/json'},
                        data: JSON.stringify(program_details)
                    }).success(function (data, status){
                        $scope.data = data;
                        if (data == "true"){
                            alert("details deleted successfully");
                            window.open(getHost() + 'admin/marketingprogram.jsp', "_self");
                        }
                    }).error(function (){
                        alert("No data available, problem fetching the data");
                    });                
                };
                
            }
        </script>
    
    <%!
        PreparedStatement ps;
        ResultSet rs;
        String Query = "";
        Integer id = 0;
        String user_name = "";
        String brand_name = "";
        String font_name = "";
    %>    
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
    </head>
    <body ng-app class="container">
       <%@include file="menus.jsp" %>
        <div class="jumbotron" align="center" ng-controller="marketingCategoriesController" >
            <div  style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 500px; width: 600px;">
                <form name="formPrograms" method="post">

                    <div>
                        <div class="col-md-3 col-md-offset-5">
                            <p>Programs:</p>
                        </div>
                    </div>
                    <div ng-init="getMarketingCategories()" style="float:left; left:20px; padding-left: 166px;">
                        <%= exist1 %>
                Program Name: <input type="text" id="program_name" name="program_name" value=""/><br>
    
                Users : <select name="users" id="users" multiple>
                            <option value="0">-Select-</option>
                    <%
                        Connection conn = null;
                        try {
                            try {
                                conn = ConnectionManager.getInstance().getConnection();
                                Query = "Select * from tbl_user_login_details";
                                ps = conn.prepareStatement(Query);

                                rs = ps.executeQuery();
                                while (rs.next()) {
                                    id = rs.getInt("id");
                                    user_name = rs.getString("user_name");
                    %>            
                                <option value="<%=id%>"><%= user_name%></option>
                    <%
                                }
                        } catch (Exception e) {
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        } finally {
                            ps.close();
                            rs.close();
                        }
                        } finally {
                            ConnectionManager.getInstance().closeConnection(conn);
                        }
                    %>

                </select><br><br>                     
                         
        Select category:<select name="category" id="category" style="width:180px;">
                            <option value="0">--select--</option>
                            <option value="{{category.id}}" ng-repeat="category in marketingcategories">{{category.name}}</option>
                        </select><br><br>
                        
        Program order: <input type="text" id="program_order" name="program_order" />
                    </div><br>
                    <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                   <div>
        HTML Data:  <textarea id="html_data" name="html_data" ></textarea>
                   </div>
                        <br>
                        <div style="float: left; left:20px; margin-top: -110px;">
                            <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="saveMarketingPrograms()">Save</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>
            <div>
                <img src="" id="previewimage" />
            </div>
            <br>
            <div ng-init="getMarketingPrograms()" style="margin-top: 0px;">

                <div>&nbsp;Display Categories<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Program Name</td>
                        <td>Order ID</td>
                        <td>category ID</td>
                        <td>user ID</td>
                        <td></td>
                        <td></td>
                    </tr>
                   
                    <tr ng-repeat = "program in programs">
                        <td>{{program.id}}</td>
                        <td>{{program.name}}</td>
                        <td>{{program.program_order}}</td>
                        <td>{{program.category_id}}</td>
                        <td>{{program.user_id}}</td>
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editCategory()" disabled>edit</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteProgram(program.program_id)">delete</button></td>
                    </tr>
                    
                </table>
            </div>
            <br>

        </div>
    </body>
</html>
