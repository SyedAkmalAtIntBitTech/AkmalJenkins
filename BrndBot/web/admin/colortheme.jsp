<%-- 
    Document   : colortheme
    Created on : Jul 1, 2015, 9:51:51 AM
    Author     : intbit
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/configurations.js"></script>
<!--        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>-->
        <script type="text/javascript" src="../js/angular.min.js"></script>
        
        <title>JSP Page</title>
        <style>
            .box{width:80px;}
            
        </style>
        <script>
            
            function colorthemeController($scope, $http){
            $scope.colortheme = {};
                var brand_id;
                var color = [];
                function validate(){
                
                var x = document.getElementById("brand").selectedIndex;
                brand_id = document.getElementsByTagName("option")[x].value;
                if (brand_id == 0){
                    alert("No brand selected, please select any one brand");
                    document.getElementById("brand").focus();
                    return false;
                }
                
                var colors = document.getElementById("color");

                var len = colors.options.length;
                
                var i = 1;
                var sel = 0;
                for( i = 1; i < colors.options.length; i++){
                    if(colors.options[i].selected === true){
                        color[sel] = colors.options[i].value;
                        sel = sel +1;
                    }
                }
                alert(sel);
                if (sel == 0){
                    alert("No color selected, please select any one color");
                    document.getElementById("color").focus();
                    return false;
                }else if(sel != 6){
                    alert("please select six colors");
                    document.getElementById("color").focus();
                    return false;
                }
                return true;
            }
            $scope.createColorTheme = function(){
                
                    if(validate()){
                            var colort = {"brand_id": brand_id.toString(), "colors":color, "type": "add" };
                        
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
                    $scope.delete = function(color_theme){
                               var colortheme = {"color_theme_id": color_theme.toString(), "type": "delete" };
                                $http({
                                            method: 'POST',
                                            url: getHost() +'ServletColorTheme',
                                            headers: {'Content-Type': 'application/json'},
                                            data:  colortheme
                                  }).success(function (data) 
                                    {
                                      $scope.status=data;
                                            if(data === "true"){
                                                alert("color theme deleted successfully");
                                                window.open(getHost() +'admin/colortheme.jsp',"_self");
                                            }else if(data === error){
                                                alert(data);
                                            } 
                                    });
                    };   
                $scope.edit = function(theme_id){
                    var configuration = global_host_address + "admin/editcolortheme.jsp" + "?theme_id=" + theme_id;
                    window.open(configuration, "_self");
    
                };
            }
    
            
        </script>
    </head>
    <%!
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        SqlMethods sqlmethods = new SqlMethods();
        
        Integer number = 1;
    %>
    
    <body ng-app>
        <div align="center" ng-controller="colorthemeController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form ng-model="colortheme">
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center >Color Theme</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select brand: <select name="brand" id="brand" style="width:180px; margin-top: 20px;">
                                                <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_brand_personality Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("brand_name") %></option>
                    <%
                        }
                    %>
                                            </select><br>
                    </div><br>    

                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 20px;">
                        Select colors: <select name="color" id="color" style="width:180px;" multiple="true" ng-model="colortheme.color">
                                                <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_colors Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                                <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("color_name") %> </option>
                    <%
                        }
                    %>
                                            </select><br>
                    </div><br>    
                    
                <div style="position: absolute; float:left; left:550px; top: 300px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="createColorTheme()">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
            <br>
            <div  style="margin-top: 0px;">
               
                <div>&nbsp;Display Brand Personality<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Brand ID</td>
                        <td>color1</td>
                        <td>color2</td>
                        <td>color3</td>
                        <td>color4</td>
                        <td>color5</td>
                        <td>color6</td>
                        <td>Predefined</td>
                        <td>theme</td>
                        <td></td>
                    </tr>
                    <%
                        query_string = "select * from tbl_brand_color_theme Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        number = 1;
                        
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number %></td>
                        <td><%= result_set.getString("brand_id")%></td>
                        <td><%= result_set.getString("color1")%></td>
                        <td><%= result_set.getString("color2")%></td>
                        <td><%= result_set.getString("color3")%></td>
                        <td><%= result_set.getString("color4")%></td>
                        <td><%= result_set.getString("color5")%></td>
                        <td><%= result_set.getString("color6")%></td>
                        <td><%= result_set.getBoolean("predefined")%></td>
                        <td><%= result_set.getString("theme_name")%></td>
                        
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="edit(<%=result_set.getInt("id")%>,'<%=result_set.getString("brand_id")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="brand" name="brand" value="delete" ng-click="delete(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                            number = number + 1;
                        }
                        result_set.close();
                        prepared_statement.close();
                        sqlmethods.con.close();
                    %>
                </table>
            </div>
            <br>

        </div>
        
    </body>
</html>
