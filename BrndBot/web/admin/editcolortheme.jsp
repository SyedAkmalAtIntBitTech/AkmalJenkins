<%-- 
    Document   : editcolortheme
    Created on : Jul 1, 2015, 4:01:37 PM
    Author     : intbit
--%>
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
        <script src="../js/colorthemefunctions.js" type="text/javascript"></script>
        
        <title>JSP Page</title>
        <style>
            .box{width:80px;}
            
        </style>
        <script>
            
            function colorthemeController($scope, $http){
            $scope.colortheme = {};
                var theme_id;
                var brand_id;
                var color = [];
                function validate(){
                    theme_id = document.getElementById("themeid").value;
                    var x = document.getElementById("brand").selectedIndex;
                    brand_id = document.getElementsByTagName("option")[x].value;
                
                if (brand_id == 0){
                    alert("No brand selected, please select any one brand");
                    document.getElementById("brand").focus();
                    return false;
                }
                
                var colors = document.getElementById("color");

                var len = colors.options.length;
                var sel = 0;
                for (i = 1; i < colors.options.length; i++) {
                    if (colors.options[i].selected === true) {
                        color[sel] = colors.options[i].value;
                        sel = sel + 1;
                    }
                }
                alert(sel);
                if (sel == 0) {
                    alert("No color selected, please select six color");
                    document.getElementById("color").focus();
                    return false;
                } else if (sel != 6) {
                    alert("please select six colors");
                    document.getElementById("color").focus();
                    return false;
                }
                return true;
            }

            $scope.updateColorTheme = function(){
                
                    if(validate()){
                            var colort = {"theme_id":theme_id,"brand_id": brand_id.toString(), "colors":color, "type": "update" };
                        
                            $http({
                              method: 'POST',
                              url: getHost() +'ServletColorTheme',
                              headers: {'Content-Type': 'application/json'},
                              data:  colort
                          }).success(function (data) 
                              {
                                $scope.status=data;
                                if(data === "true"){
                                    window.open(getHost() +'admin/colortheme.jsp',"_self");
                                }else if (data === error){
                                    alert(data);
                                }
                              })
                                .error(function(data, status) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
                                alert("request not succesful");
                              });
                          }
                    };

            }
    
            
        </script>
    </head>
<jsp:include page="checksession.jsp" />
    <jsp:declaration>
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        //SqlMethods sql_methods = new SqlMethods();
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        Integer number = 1;
    </jsp:declaration>

    
    <%
        String color_theme_id = request.getParameter("theme_id");
    %>
    <body ng-app>
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="colorthemeController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form ng-model="colorthemeController">
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center >Color Theme</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        <input type="hidden" name="themeid" id="themeid" value="<%= color_theme_id %>"/>
                        Select brand: <select name="brand" id="brand" style="width:180px; margin-top: 20px;">
                                                <option value="0">--select--</option>
                    <%
                        Connection connection = null;
                        try {
                        connection = ConnectionManager.getInstance().getConnection();
                        
                        query_string = "select * from tbl_brand_personality Order By id ASC";
                        prepared_statement = connection.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("brand_name") %></option>
                    <%
                        }
                        }catch (Exception e){
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        }finally {
                            result_set.close();
                            prepared_statement.close();
                            ConnectionManager.getInstance().closeConnection(connection);
                        }
                    %>
                                            </select><br>
                    </div><br>    

                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select colors: <select name="color" id="color" style="width:180px;" multiple="true" ng-model="colortheme.color">
                                                <option value="0">--select--</option>
                    <%
                        try {
                        connection = ConnectionManager.getInstance().getConnection();
                        query_string = "select * from tbl_colors Order By id ASC";
                        prepared_statement = connection.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("color_name") %> </option>
                    <%
                        }
                        }catch (Exception e){
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        }finally{
                            result_set.close();
                            prepared_statement.close();
                            ConnectionManager.getInstance().closeConnection(connection);
                        }
                    %>
                                            </select><br>
                    </div><br>    
                    
                <div style="position: absolute; float:left; left:550px; top: 300px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="updateColorTheme()">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
            <br>
    </body>
</html>
