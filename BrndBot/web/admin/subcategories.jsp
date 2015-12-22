<%-- 
    Document   : subCategories
    Created on : Jul 3, 2015, 11:35:57 AM
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
        <script>

            function categoryController($scope, $http) {
                var sub_category_name;
                var external_source;
                var category_id;
                var isblock;
                var ismindbody;
                var x, y;

                function validate() {

                    sub_category_name = document.getElementById("sub_category_name").value;
                    
                    if (sub_category_name == "") {
                        alert("Category not entered!  Please enter the category.");
                        $("#sub_category_name").focus();
                        return false;
                    }
                    
                    x = document.getElementById("external_source").selectedIndex;

                    external_source = document.getElementsByTagName("option")[x].value;

                    var cat = document.getElementById("category");
                    y = document.getElementById("category").selectedIndex;

                    category_id = cat.options[y].value;

                    if (category_id == 0) {
                        alert("No category selected, please select the category");
                        document.getElementById("category").focus();
                        return false;
                    }
                    return true;
                }

                $scope.createSubCategory = function () {

                    if (validate()) {

                        var category = {"sub_category_name": sub_category_name, "external_source": external_source, "category": category_id, "type": "add"};

                        $http({
                            method: 'POST',
                            url: getHost() + 'ServletSubCategory',
                            headers: {'Content-Type': 'application/json'},
                            data: category
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "true") {
                                alert("Category added successfully.");
                                window.open(getHost() + 'admin/subcategories.jsp', "_self");
                            } else if (data === "false") {
                                alert("Categories already exist!");
                            } else if (data === error) {
                                alert(data);
                            }
                        })
                            .error(function (data, status) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
                                alert("Request not successful!");
                            });
                    }
                };

                $scope.deleteCategory = function (category_id) {
                    var Category = {"category_id": category_id, "type": "delete"};
                    $http({
                        method: 'POST',
                        url: getHost() + 'ServletSubCategory',
                        headers: {'Content-Type': 'application/json'},
                        data: Category
                    }).success(function (data)
                    {
                        $scope.status = data;
                        if (data === "true") {
                            alert("Category deleted successfully.");
                            window.open(getHost() + 'admin/subcategories.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                };

                $scope.editCategory = function (sub_category_id, category_name, sub_category_name, category_id) {
                    var configuration = global_host_address + "admin/editsubcategory.jsp" + "?sub_category_id=" + sub_category_id + "&category_name="+category_name + "&sub_category_name=" + sub_category_name + "&category_id="+category_id;
                    window.open(configuration, "_self");
                };
            }

        </script>
        <title>sub categories</title>

    </head>
<jsp:include page="checksession.jsp" />
    <jsp:declaration>
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        Integer number = 1;

    </jsp:declaration>

    <body ng-app class="container">
        <%@include file="menus.jsp" %>
        <div class="jumbotron" align="center" ng-controller="categoryController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form ng-controller="categoryController">

                    <div class="group">
                        <div class="col-md-3 col-md-offset-5">
                            <p text-center >Sub Categories:</p>
                        </div>
                    </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
                        <input type="text" id="sub_category_name" name="sub_category_name" value=""/><br>
                        External Source: <select name="external_source" id="external_source">
                            <option value="null">-- Select --</option>
                            <option value="Mindbody">Mindbody</option>
                        </select><br><br>
                        Select Categories: <select name="category" id="category" style="width:180px;">
                            <option value="0">--select--</option>
                            <%
                                Connection connection = null;
                            try {
                                connection = ConnectionManager.getInstance().getConnection();
                                query_string = "select * from tbl_category Order By id ASC";
                                prepared_statement = connection.prepareStatement(query_string);
                                result_set = prepared_statement.executeQuery();

                                while (result_set.next()) {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("category_name")%></option>
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
                    </div>
                    <div style="position: relative; float:left; left:0px; top: 50px;">
                        <div>
                            <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="createSubCategory()">Save</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>
            <br>
            <div  style="margin-top: 0px;">

                <div><p text-center >Display Sub Categories:</p>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>External Source</td>
                        <td>Category Name</td>
                        <td>Sub Category Name</td>
                        <td>Block</td>
                        <td>Mindbody</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        try {
                            connection = ConnectionManager.getInstance().getConnection();
                        query_string = "select * from tbl_sub_category Order By id ASC";
                        prepared_statement = connection.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        number = 1;

                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number%></td>
                        <td><%= result_set.getString("external_source")%></td>
                        <td><%= result_set.getString("external_source_keyword")%></td>
                        <td><%= result_set.getString("sub_category_name")%></td>
                        
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editCategory(<%=result_set.getInt("id")%>, '<%= result_set.getString("external_source_keyword")%>', '<%= result_set.getString("sub_category_name")%>','<%= result_set.getString("category_id")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="brand" name="brand" value="delete" ng-click="deleteCategory(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                            number = number + 1;
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
                </table>
            </div>
            <br>
        </div>

    </body>
</html>
