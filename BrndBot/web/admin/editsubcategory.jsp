<%-- 
    Document   : editsubcategory
    Created on : Jul 3, 2015, 3:07:24 PM
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
        <script>

            function categoryController($scope, $http) {
                var sub_category_name;
                var external_source;
                var sub_category_id;
                var category_id;
                var x,y;

                function validate(){
                
                sub_category_name = document.getElementById("sub_category_name") .value;
                sub_category_id = document.getElementById("sub_category_id") .value;

                if(sub_category_name == ""){
                    alert("category not entered, please enter the category");
                    $("#sub_category_name").focus();
                    return false;
                }
                
                x = document.getElementById("external_source").selectedIndex;

                external_source = document.getElementsByTagName("option")[x].value;
                alert(external_source);
                
                if (external_source == 0){
                    alert("No external source selected, please select the external source");
                    document.getElementById("external_source").focus();
                    return false;
                }
                 var cat = document.getElementById("category");
                 y = document.getElementById("category").selectedIndex;

                category_id =  cat.options[y].value;

                if (category_id == 0){
                    alert("No category selected, please select the category");
                    document.getElementById("category").focus();
                    return false;
                }
                    return true;
                }

                $scope.updateCategory = function(){
                        
                    if(validate()){
                        
                            var category = {"sub_category_id":sub_category_id, "sub_category_name": sub_category_name, "external_source":external_source, "category":category_id, "type": "update" };
                        
                            $http({
                              method: 'POST',
                              url: getHost() +'ServletSubCategory',
                              headers: {'Content-Type': 'application/json'},
                              data:  category
                          }).success(function (data) 
                              {
                                $scope.status=data;
                                if(data === "true"){
                                    alert("category updated successfully");
                                    window.open(getHost() +'admin/subcategories.jsp',"_self");
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
        <title>categories</title>

    </head>
    <%!
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string, category_id_1, category_name_1, sub_category_id, category_name, sub_category_name, category_id;
        SqlMethods sqlmethods = new SqlMethods();

        Integer number = 1;
    %>
    <%
        sub_category_id = request.getParameter("sub_category_id");
        category_name = request.getParameter("category_name");
        sub_category_name = request.getParameter("sub_category_name");
        category_id = request.getParameter("category_id");
     %>
    <body ng-app>
        <div align="center" ng-controller="categoryController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form ng-controller="categoryController">

                    <div>
                        <div class="col-md-3 col-md-offset-5">
                            <p text-center >Sub Categories</p>
                        </div>
                    </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
                        <input type="hidden" id="sub_category_id" name="sub_category_id" value="<%= category_id %>"/>
                        Sub Category Name:<input type="text" id="sub_category_name" name="sub_category_name" value="<%= sub_category_name %>"/><br>
                        External Source: <select name="external_source" id="external_source">
                                                        <option value="0">-- Select --</option>
                                                        <option value="Mindbody">Mindbody</option>
                                                </select><br><br>
                        Select Categories: <select name="category" id="category" style="width:180px;">
                                                            <option value="0">--select--</option>
                                                            <%
                                                                query_string = "select * from tbl_category Order By id ASC";
                                                                sqlmethods.setDatabaseConnection();
                                                                prepared_statement = sqlmethods.con.prepareStatement(query_string);
                                                                result_set = prepared_statement.executeQuery();

                                                                while (result_set.next()) {
                                                                    
                                                                    category_id_1 = result_set.getString("id");
                                                                    category_name_1 = result_set.getString("category_name");
                                                                    
                                                                    if (category_id_1.equals(category_id)){
                                                              %>
                                                                      <option value="<%= result_set.getInt("id")%>" selected><%= result_set.getString("category_name")%></option>
                                                              
                                                              <%
                                                                    }else{
                                                             %>
                                                                      <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("category_name")%></option>
                                                            <%
                                                                    }
                                                                }
                                                            %>
                                                </select><br>
                    </div><br>    

                    <div style="position: absolute; float:left; left:550px; top: 300px;">
                        <div>
                            <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="updateCategory()">Save</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>

    </body>
</html>
