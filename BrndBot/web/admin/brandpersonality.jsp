<%-- 
    Document   : displayorganizations
    Created on : Jun 27, 2015, 11:31:52 AM
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
            
            function brandController($scope,$http){
                
                $scope.deleteBrand = function(brand_id){
                    var Brand = {"brand_id": brand_id };
                     $http({
                                 method: 'POST',
                                 url: getHost() +'ServletDeleteBrands',
                                 headers: {'Content-Type': 'application/json'},
                                 data:  Brand
                       }).success(function (data) 
                         {
                           $scope.status=data;
                                 if(data === "true"){
                                     alert("brand deleted successfully");
                                     window.open(getHost() +'admin/brandpersonality.jsp',"_self");
                                 }else if(data === error){
                                     alert(data);
                                 } 
                         });
                };
                
                $scope.editBrand = function(brand_id, brand_name, look_id){
                    var configuration = global_host_address + "admin/editpersonality.jsp" + "?brand_id=" + brand_id +"&brand_name="+brand_name+"&look_id="+look_id+"";
                    window.open(configuration, "_self");
    
                };
            }
            
        </script>
        <title>brand personality</title>
        
    </head>
    <%!
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String query_string;
        SqlMethods sqlmethods = new SqlMethods();
        
        Integer number = 1;
    %>
    <body ng-app  >
        <div align="center" ng-controller="brandController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 350px; width: 600px;">
                <form name="formpersonality" action="<%= application.getContextPath() %>/ServletAddPersonality" enctype="multipart/form-data" method="post">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p text-center >Add New Brand Personality</p>
                    </div>
                </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
                        Brand:<input type="text" id="brandname" name="brandname" value=""/><br>
                        Select Look: <select name="look" id="look" style="width:180px;">
                                    <option value="0">--select--</option>
                    <%
                        query_string = "select * from tbl_look Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {
                    %>
                                    <option value="<%= result_set.getInt("id") %>"><%= result_set.getString("look_name") %></option>
                    <%
                        }
                    %>
                        </select><br>
                    </div><br>    
                <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                    <div>
                        Attach Image:<input type="file" name="filesToUpload[]"  id="filesToUpload" class="upload"  file-model="looks.fileName" />
                    </div><br>
                </div>

                <div style="position: absolute; float:left; left:550px; top: 300px;">
                    <div>
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
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
                        <td>Brand Name</td>
                        <td>Brand Image</td>
                        <td></td>
                    </tr>
                    <%
                        query_string = "select * from tbl_brand_personality Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        number = 1;
                        
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= number %></td>
                        <td><input class="simplebox" type="text" name="<%= result_set.getInt("id")%>" id="<%= result_set.getInt("id")%>" value="<%= result_set.getString("brand_name")%>" /></td>
                        <td><a href="../images/Brandimages/<%= result_set.getString("image")%>"><%= result_set.getString("image")%></a></td>
                        <td><button class="btn btn-info" id="edit" name="edit" value="edit" ng-click="editBrand(<%=result_set.getInt("id")%>,'<%=result_set.getString("brand_name")%>', '<%=result_set.getString("look_id")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="brand" name="brand" value="delete" ng-click="deleteBrand(<%=result_set.getInt("id")%>)">delete</button></td>
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
                            document.getElementById('logoimage').src = evt.target.result;
                            document.getElementById("Servicecontinue").disabled = false;
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
