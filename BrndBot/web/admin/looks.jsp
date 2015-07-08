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
        <script src="../js/lookfunctions.js" type="text/javascript"></script>
        <title>Looks</title>
        
    </head>
<%@include file="checksession.jsp" %>
    
    <%!
        Integer num = 1;
    %>
    <body ng-app  >
        <%@include file="menus.jsp" %>
        <div align="center" ng-controller="lookController" >
            <div style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 200px;">
                <form name="formorganization1" action="<%= application.getContextPath() %>/ServletAddLooks" enctype="multipart/form-data" method="post" onsubmit="return validate()">

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <p class="text-center">Add New Looks</p>
                    </div>
                </div>
                    
                <div>
                    <div class="col-md-3 col-md-offset-5">
                        Look Name: <input type="text"  class="form-control simplebox" id="lookname" name="lookname"/>
                        Attach Image:<input type="file" name="filesToUpload"  id="filesToUpload" class="upload"  file-model="looks.fileName" />
                        <!--  <label>Organization Name:</label>-->
                    </div><br>
                </div>

                <div>
                    <div class="col-md-3 col-md-offset-5">
                        <button id="Servicecontinue" type="submit" class="btn btn-info">Save</button>
                        <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                    </div>
                </div>

            </form>
            </div>
            <br>
            <div  style="margin-top: 0px;">
               
                <div>&nbsp;Display Looks<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Look Name</td>
                        <td>Look Image</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                    try{
                        query_string = "select * from tbl_look Order By id ASC";
                        sqlmethods.setDatabaseConnection();
                        prepared_statement = sqlmethods.con.prepareStatement(query_string);
                        result_set = prepared_statement.executeQuery();
                        
                        while (result_set.next()) {

                    %>
                    <tr>
                        <td><%= result_set.getInt("id")%></td>
                        <td><input class="simplebox" type="text" name="<%= result_set.getInt("id")%>" id="<%= result_set.getInt("id")%>" value="<%= result_set.getString("look_name")%>" /></td>
                        <td><a href="../images/Lookimages/<%= result_set.getString("file_name")%>"><%= result_set.getString("file_name")%></a></td>
                        <td><button class="btn btn-info" id="change" name="change" value="edit" ng-click="changeLooks(<%=result_set.getInt("id")%>,'<%=result_set.getString("look_name")%>')">edit</button></td>
                        <td><button class="btn btn-info" id="organization" name="organization" value="delete" ng-click="deleteLooks(<%=result_set.getInt("id")%>)">delete</button></td>
                    </tr>
                    <%
                        }
                    }catch (Exception e){
                        out.println(sqlmethods.error);
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
