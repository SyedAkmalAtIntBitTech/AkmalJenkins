<%-- 
    Document   : displayorganizations
    Created on : Jun 27, 2015, 11:31:52 AM
    Author     : intbit
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
                var category_name = $("#category_name").val();
                var organization = $("#organization").val();
                var category_order = $("#category_order").val();
                var filesToUpload = $("#filesToUpload").val();
                
                if (category_name == ""){
                    alert("category name not entered, kindly enter the category");
                    $("#category_name").focus();
                    return false;
                }
                
                if (organization == ""){
                    alert("organization not selected, kindly select an organization");
                    $("#organization").focus();
                    return false;
                }
                
                if (category_order == ""){
                    alert("order not entered,kindly enter the order");
                    $("#category_order").focus();
                    return false;
                }
                
                if (filesToUpload == ""){
                    alert("no file attached,kindly attach the file");
                    $("#category_order").focus();
                    return false;
                }
                
                
                return true;
            }
            
            function marketingCategoriesController($scope, $http){
                
                $scope.getOrganizations = function(){
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetOrganizations'
                    }).success(function (data, status, headers, config) {
                        $scope.organizations = data;
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
//                        alert(JSON.stringify(data));
                        $scope.marketingcategories = data.marketingData;
                    }).error(function (){
                        alert("No data available, problem fetching the data");
                    });
                };
                
                $scope.deleteCategory = function(category_id){
                    alert(category_id);
                var category_details = {"category_id": category_id};
                    $http({
                        method: 'POST',
                        url: getHost() + 'deleteMarketingCategory.do',
                        headers: {'Content-Type': 'application/json'},
                        data: JSON.stringify(category_details)
                }).success(function (data, status){
                        if (data == "true"){
                            alert("record deleted successfully");
                        }
                        $scope.getMarketingCategories();
                }).error(function (){
                    alert("No data available, problem fetching the data");
                });
                };
            }
        </script>
    </head>    
    <%!     PreparedStatement ps;
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


    <body ng-app class="container">
    <%@include file="menus.jsp" %>
        <div class="jumbotron" align="center" ng-controller="marketingCategoriesController" >
            <div  style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 480px; width: 600px;">
                <form name="formCategories" action="<%= application.getContextPath()%>/setMarketingCategory.do" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                    <div>
                        <div class="col-md-8 col-md-offset-4" style="width:250px;">
                            <p>Marketing Categories:</p>
                        </div>
                    </div>
                    <div ng-init="getOrganizations()" style="float:left; left:20px; padding-left: 166px;">
                        <%= exist1 %>
                         Category Name: <input type="text" id="category_name" name="category_name" value=""/><br>
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
            Select organization: <select name="organization" id="organization" style="width:180px;">
                            <option value="0">--select--</option>
                            <option ng-repeat="org in organizations" value="{{org.id}}">{{org.organization_name}}</option>
                        </select><br><br>
                        Category Order: <input type="text" id="category_order" name="category_order" />
                    </div><br>    
                    <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                        <div>
                            Attach Image:<input type="file" name="filesToUpload"  id="filesToUpload" class="upload" file-model="categories.fileName" />
                        </div>
                        <br>
                        <div style="float: left; left:20px; margin-top: -110px;">
                            <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>
            <div>
                <img src="" id="previewimage" />
            </div>
            <br>
            <div ng-init="getMarketingCategories()" style="margin-top: 0px;">

                <div>&nbsp;Display Categories<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Category Name</td>
                        <td>Category order</td>
                        <td>Organization ID</td>
                        <td></td>
                        <td></td>
                    </tr>
                   
                    <tr ng-repeat = "marketing in marketingcategories">
                        <td>{{marketing.id}}</td>
                        <td>{{marketing.name}}</td>
                        <td>{{marketing.order}}</td>
                        <td>{{marketing.organization_id}}</td>
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editCategory()" disabled></button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteCategory(marketing.category_id)">delete</button></td>
                    </tr>
                    
                </table>
            </div>
            <br>

        </div>
        <script>
                                     
    var fl = document.getElementById('filesToUpload');
                                        
    fl.onchange = function (e) {
    var ext = this.value.match(/\.(.+)$/)[1];
    switch (ext)
        {
                case 'jpg':
                case 'png':
                case 'jpeg':
                case 'JPG':
                case 'PNG':
                case 'JPEG':
                case 'svg':
                case 'SVG':                                  
                break;
                default:
                alert('This type of image is not allowed');
                this.value = '';
        }

    };
    function fileSelect(evt) {
    if (window.File && window.FileReader && window.FileList && window.Blob) {
    var files = evt.target.files;
    var result = '';
    var file;
    for (var i = 0; file = files[i]; i++) {
        // if the file is not an image, continue
        if (!file.type.match('image.*')) {
        continue;
        }
        //            alert("only .png file");

            reader = new FileReader();
            reader.onload = (function (tFile) {
            return function (evt) {
            var div = document.createElement('div');
            div.innerHTML = '<img style="width: 90px;" src="' + evt.target.result + '" />';
            document.getElementById('previewimage').src = evt.target.result;
            };
            }(file));
            reader.readAsDataURL(file);
        }
    } else {
        alert('The File APIs are not fully supported in this browser.');
    }
    }

document.getElementById('filesToUpload').addEventListener('change', fileSelect, false);
    </script>

    </body>
</html>