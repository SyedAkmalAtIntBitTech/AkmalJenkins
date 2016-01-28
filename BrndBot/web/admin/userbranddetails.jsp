<%-- 
    Document   : colortheme
    Created on : Jul 1, 2015, 9:51:51 AM
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
<!--        <script src="../js/fontstyles.js" type="text/javascript"></script>-->
        <meta name="viewport" content="width=device-width, initial-scaleu=1.0">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <link href="../css/main1.css" rel="stylesheet" type="text/css"/>
        
        <title>font theme</title>
        <style>
            .box{width:80px;}
        </style>
        <script>
            
                function usersChange() {

                    if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                        var response = xmlHttp.responseText;

                        var response1, response2, response3, response4, response5, response6;
                        var len = response.length;
                        var no1 = response.indexOf(",");
                        response1 = response.substr(0, no1);
                        response2 = response.substr(no1 + 1, len);

                        document.getElementById("users").innerHTML = response1;
//                        document.getElementById("categories").innerHTML = response2;
                    }
                }            

                function showUsers(str) {

                    if (typeof XMLHttpRequest !== "undefined") {

                        xmlHttp = new XMLHttpRequest();

                    }
                    else if (window.ActiveXObject) {

                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                    }
                    if (xmlHttp === null) {

                        alert("Browser does not support XMLHTTP Request!");

                        return;
                    }

                    var url = "users.jsp";

                    url += "?org_id=" + str;

                    xmlHttp.onreadystatechange = usersChange;

                    xmlHttp.open("GET", url, true);

                    xmlHttp.send(null);

                }
            
            function colorthemeController($scope, $http){
            $scope.colortheme = {};
                var brand_id;
                var font_name_array = [];
                var font_size_array = [];
                var font_style_array = [];
                
                function validate(){
                    var organization_id = $("#organization").val();
                    var users_id = $("#users").val();
                    var brand_id = $("#brand").val();
                    
                    if (organization_id == 0){
                        alert("No organization selected! please select any one organization.");
                        $("#organization").focus();
                        return false;
                    }

                    if (users_id == 0){
                        alert("No users selected! Please select any one users.");
                        $("#users").focus();
                        return false;
                    }

                    if (brand_id == 0){
                        alert("No brand id selected! Please select any one brand.");
                        $("#brand").focus();
                        return false;
                    }

                    return true;
                }

                $scope.createFontTheme = function(){
                    var organization_id = $("#organization").val();
                    var users_id = $("#users").val();
                    var brand_id = $("#brand").val();
                    var users_email_id = $("#users option:selected").text();
                    var brand_name = $("#brand option:selected").text();
                    if(validate()){
                            var colort = {"users_email_id":users_email_id,
                                          "brand_name":brand_name,
                                          "organization_id":organization_id,
                                          "users_id":users_id,
                                          "brand_id": brand_id,
                                          "type": "add" };
                        
                            $http({
                              method: 'POST',
                              url: getHost() +'ServletUserBrand',
                              headers: {'Content-Type': 'application/json'},
                              data:  colort
                          }).success(function (data) 
                              {
                                $scope.status=data;
                                if(data === "true"){
                                    alert("brand saved successfully");
                                    window.open(getHost() +'admin/userbranddetails.jsp',"_self");
                                }else if (data === "false"){
                                    alert("brand already exist, please select some other brand");
                                }
                              }).error(function(data, status) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
                                alert("Request not successful!");
                              });
                          }
                    };
                    $scope.delete = function(user_brand){
                        var user_delete = {"user_brand": user_brand, "type": "delete" };
                         $http({
                                method: 'POST',
                                url: getHost() +'ServletUserBrand',
                                headers: {'Content-Type': 'application/json'},
                                data:  user_delete
                           }).success(function (data) 
                             {
                                $scope.status=data;
                                 if(data === "true"){
                                     alert("User brand deleted successfully.");
                                     window.open(getHost() +'admin/userbranddetails.jsp',"_self");
                                 }else if(data === error){
                                     alert(data);
                                 } 
                             });
                    };   
                $scope.edit = function(id, user_id, brand_id, organization_id, user_email_id){
                    var configuration = global_host_address + "admin/edituserbranddetails.jsp" + "?user_brand_id="+id+"&user_id="+user_id+"&brand_id="+brand_id+"&organization_id="+organization_id+"&user_email_id="+user_email_id;
                    window.open(configuration, "_self");
                };
            }
    
            
        </script>
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
        <%! 
            PreparedStatement ps;
            ResultSet rs;
            String Query = "";
            Integer id = 0;
            String org_name = "";
            String brand_name = "";
            String font_name = "";
        %>        
        <div class="jumbotron" align="center" ng-controller="colorthemeController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 300px; width: 600px;">
                <form ng-model="colortheme" ng-submit="createFontTheme()">
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center>User Brand</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 166px; padding-top: 0px;">

                Organization : <select name="organization" id="organization" onchange="showUsers(this.value)">
                    <option value="0">-Select-</option>
                    <%
                        Connection conn = null;
                        try {
                            try {
                                conn = ConnectionManager.getInstance().getConnection();
                                Query = "Select * from tbl_organization";
                                ps = conn.prepareStatement(Query);

                                rs = ps.executeQuery();
                                while (rs.next()) {
                                    id = rs.getInt("id");
                                    org_name = rs.getString("organization_name");
                    %>            
                                    <option value="<%=id%>"><%= org_name%></option>
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
                        
                Users : <select id='users' name="users">
                            <option value="0">Select</option>
                       </select>
                    <br><br>
                Brand : <select name="brand" id="brand" onchange="showbrand(this.value)">
                                <option value="0">-Select-</option>

                    <%  
                        try{
                        try {
                            conn = ConnectionManager.getInstance().getConnection();
                            Query = "Select * from tbl_brand_personality";
                            ps = conn.prepareStatement(Query);

                            rs = ps.executeQuery();
                            while (rs.next()) {
                                id = rs.getInt("id");
                                brand_name = rs.getString("brand_name");
                    %>
                                    <option value="<%= id %>"><%= brand_name %></option>
                    <%
                                }
                            } catch (Exception e) {
                                System.out.println(e.getCause());
                                System.out.println(e.getMessage());
                            } finally {
                                rs.close();
                                ps.close();
                            }
                        }finally{
                            ConnectionManager.getInstance().closeConnection(conn);
                        }    
                        
                    %>
                            </select><br><br>
                    </div>

                <div style="position: absolute; float:left; left:550px; top: 270px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
            <br>
            <div  style="margin-top: 0px;">
               
                <div><p>Display User Brand</p><br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>Serial ID</td>
                        <td>User ID</td>
                        <td>Brand ID</td>
                        <td>Organization</td>
                        <td>Brand Name</td>
                        <td>User Email ID</td>
                        <td></td>
                        <td></td>

                    </tr>
                    <%
                        try{
                        try{
                            conn = ConnectionManager.getInstance().getConnection();
                            query_string = "select * from tbl_user_brands Order By id ASC";
                            prepared_statement = conn.prepareStatement(query_string);
                            result_set = prepared_statement.executeQuery();
                            number = 1;

                            while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number %></td>
                        <td><%= result_set.getInt("user_id") %></td>
                        <td><%= result_set.getInt("brand_id") %></td>
                        <td><%= result_set.getInt("organization") %></td>
                        <td><%= result_set.getString("user_email_id") %></td>
                        <td><%= result_set.getString("brand_name") %></td>
                        
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="edit(<%=result_set.getInt("id")%>,'<%=result_set.getInt("user_id")%>','<%=result_set.getInt("brand_id")%>','<%=result_set.getInt("organization")%>', '<%= result_set.getString("user_email_id") %>')">edit</button></td>
                        <td><button class="btn btn-info" id="brand" name="brand" value="delete" ng-click="delete(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                            number = number + 1;
                        }
                     }finally{
                        result_set.close();
                        prepared_statement.close();
                     }
                    } finally {
                        ConnectionManager.getInstance().closeConnection(conn);
                    }
                     
                    %>
                </table>
            </div>
            <br>
        </div>      
    </body>
</html>
