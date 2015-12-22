<%-- 
    Document   : displayblockss
    Created on : Jun 27, 2015, 11:31:52 AM
    Author     : intbit
--%>

<%@page import="com.intbit.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../js/configurations.js"></script>
        <script src="../js/blocks.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/angular.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link href="../css/main1.css" rel="stylesheet" type="text/css"/>
        <title>Display blocks</title>
        <script>

            function blocksController($scope, $http) {
                var name, mindbodyquery, brand_id, sub_category_id, block_id
                function validate() {
                    block_id = $("#block_id").val();
                    name = $("#name").val();
                    sub_category_id = $("#sub_categories").val();
                    brand_id = $("#brand").val();
                    mindbodyquery = $("#mindbodyquery").val();

            //        alert(sub_category_id);
            //        var zz = document.getElementById("sub_categories").selectedIndex;
            //        sub_category_id = document.getElementsByTagName("option")[zz].value;

            //        var x = document.getElementById("brand").selectedIndex;
            //        brand_id = document.getElementsByTagName("option")[x].value;
            //
            //        var y = document.getElementById("mindbodyquery").selectedIndex;
            //        mindbodyquery = document.getElementsByTagName("option")[y].value;

                    if (name === "") {
                        alert("Enter the Block name.");
                        $("#name").focus();
                        return false;
                    }

                    if (mindbodyquery == 0) {
                        alert("Select the mindbodyquery.");
                        $("#mindbodyquery").focus();
                        return false;
                    }

                    if (brand_id == 0) {
                        alert("No brand selected! Please select the brand.");
                        document.getElementById("brand").focus();
                        return false;
                    }

                    if (sub_category_id == 0) {
                        alert("No sub category selected! Please select the sub category.");
                        document.getElementById("sub_categories").focus();
                        return false;
                    }

                    return true;
                }

                $scope.editblock = function () {

                    if (validate()) {
                        
                    var block = {"block_id":block_id, "name": name, "mindbodyquery": mindbodyquery, "brand_id": brand_id, "sub_category_id":sub_category_id, "type": "edit"};

                        $http({
                            method: 'POST',
                            url: getHost() + 'ServletBlock',
                            headers: {'Content-Type': 'application/json'},
                            data: block
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "true") {
                                window.open(getHost() + 'admin/blocks.jsp', "_self");
                            }else if (data === "false"){
                                alert("Record cannot be modified.");
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
            }


        </script>
        
    </head>
<jsp:include page="checksession.jsp" />
    <%!
        Integer num = 1;
        String exist = "";
        String exist1 = "";
        
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        Integer number = 1;
        String block_id = "", name="", mindbody_query, brand_id, sub_category_id ;
        Integer brand_selected_id = 0, sub_category_selected_id = 0;
    %>
    <%
        try {
            
            block_id = (String)request.getParameter("block_id");
            mindbody_query = (String)request.getParameter("mindbody_query");
            name = (String)request.getParameter("name");
            brand_id = (String)request.getParameter("brand_id");
            sub_category_id = (String)request.getParameter("sub_category_id");
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        
    %>
    <body ng-app  class="container">
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="blocksController" >
            <div class="jumbotron" style="height: 320px; margin-top: 0px; padding-top: 20px;">
            <form class="form-horizontal" name="formblocks1" ng-controller="blocksController">
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-left">Add block</p>
                    </div>
                </div>
                <div class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <input type="hidden" class="form-control" id="block_id" name="block_id" value="<%= block_id %>"/>
                        <input type="text"  class="form-control simplebox" id="name" name="name" value="<%= name %>"/>
                        <br>
                    Select mindbody query: <select id="mindbodyquery" name="mindbodyquery" >
                        <option value="0">--select--</option>
                        <option value="promote work shop">promote work shop</option>
                        <option value="promote class">promote class</option>
                        <option value="promote event">promote event</option>
                        <option value="promote staff">promote staff</option>
                    </select><br>
                    <%
                        
                    %>
                    Select brand personality: <select name="brand" id="brand" style="width:180px;">
                        <option value="0">--select--</option>
                        <%
                        Connection conn = ConnectionManager.getInstance().getConnection();
                        try {
                            query_string = "select * from tbl_brand_personality Order By id ASC";
                            prepared_statement = conn.prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();

                            while (result_set.next()) {
                                
                                brand_selected_id = result_set.getInt("id");
                                if (brand_selected_id == Integer.parseInt(brand_id)){
                           %>
                                    <option value="<%= result_set.getInt("id")%>" selected><%= result_set.getString("brand_name")%></option>
                           <%
                                }else {
                            %>
                                    <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("brand_name")%></option>
                            <%
                                }
                            %>

                            <%
                            }
                        }catch (Exception e) {
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        }finally {
                            result_set.close();
                            prepared_statement.close();
                            ConnectionManager.getInstance().closeConnection(conn);
                        }
                        %>
                    </select><br>
                    Select sub category: <select name="sub_categories" id="sub_categories" style="width:180px;">
                        <option value="0">--select--</option>
                        <%
                        try {
                            conn = ConnectionManager.getInstance().getConnection();
                            query_string = "select * from tbl_sub_category Order By id ASC";
                            prepared_statement = conn.prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();

                            while (result_set.next()) {
                                
                                sub_category_selected_id = result_set.getInt("id");
                                
                                if (Integer.parseInt(sub_category_id) == sub_category_selected_id){
                            %>
                                <option value="<%= result_set.getInt("id")%>" selected><%= result_set.getString("sub_category_name")%></option>
                            <%
                                }else {
                        %>
                                <option value="<%= result_set.getInt("id")%>"><%= result_set.getString("sub_category_name")%></option>
                        <%
                                }
                            }
                        }catch (Exception e) {
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        }finally {
                            result_set.close();
                            prepared_statement.close();
                            ConnectionManager.getInstance().closeConnection(conn);
                        }
                        %>
                    </select><br>

                    
                    </div>
                </div>
                <div class="group">
                </div>
                
                <br>
                <div  class="group">
                    <div class="col-md-3 col-md-offset-5">
                        <button type="submit" class="btn btn-info" ng-click="editblock()">edit</button>
                        <button type="reset" class="btn btn-info" >reset</button><br><br><br>
                    </div>
                </div>

            </form>
            </div>
            <br>

        </div>
</body>
</html>
