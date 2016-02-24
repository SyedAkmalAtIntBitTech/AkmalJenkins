<%-- 
    Document   : marketingemailtemplate
    Created on : Oct 29, 2015, 8:26:43 PM
    Author     : development
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
        <title>BrndBot - Marketing Email Template</title>
        <script src="../js/categoriesfunctions.js" type="text/javascript"></script>
        <script src="../js/marketingemailtemplate.js" type="text/javascript"></script>
        <script type="text/javascript">
            function showFileName() {
                var fil = document.getElementById("myFile");
                var file = fil.value;
                alert(file);
//                fil.setAttribute("name", file)
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
        Logger logger = Logger.getLogger("marketingemailtemplate.jsp");
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
        <div class="jumbotron" align="center" ng-controller="marketingEmailTemplateController" >
            <div id="save" style="margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 400px; width: 600px;">
                <form name="formPrograms" method="post">

                    <div>
                        <div class="col-md-3 col-md-offset-5">
                            <p>Email Templates</p>
                        </div>
                    </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
        Email Template Name: <input type="text" id="template_name" name="template_name" value=""/><br>
                    </div><br>
                    <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                    <div>
             HTML Data:  <textarea id="html_data" name="html_data" style="height: 120px; width: 240px; resize: none;" ></textarea>
                    </div>
                        <br>
                        <div style="float: left; left:20px; margin-top: -110px;">
                            <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="saveMarketingEmailTemplate()">Save</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>
            <div id="update" style="display:none; margin-top: 20px; margin-bottom: 10px; border: 1px solid; height: 400px; width: 600px;">
                <form name="formPrograms" method="post">

                    <div>
                        <div class="col-md-9 col-md-offset-3">
                            <p>Update Email Template</p>
                        </div>
                    </div>
                    <div style="float:left; left:20px; padding-left: 166px;">
                    <input type="hidden" id="template_id" name="template_id" value="{{email_template.template_id}}"/><br>
        Email Template Name: <input type="text" id="template_update_name" name="template_update_name" value="{{email_template.template_name}}"/><br>
                    </div><br>
                    <div style="float:left; left:0px; padding-left: 166px; padding-top: 20px;">
                    <div>
             HTML Data:  <textarea id="update_html_data" name="update_html_data" style="height: 120px; width: 240px; resize: none;" >{{email_template.html_data}}</textarea>
                    </div>
                        <br>
                        <div style="float: left; left:20px; margin-top: -110px;">
                            <button id="Servicecontinue" type="submit" class="btn btn-info" ng-click="updateMarketingEmailTemplate()">Update</button>
                            <button id="Servicecontinue" type="reset" value="Reset" class="btn btn-info">Reset</button><br>
                        </div>
                    </div>

                </form>
            </div>
            <div>
                <img src="" id="previewimage" />
            </div>
            <br>
            <div ng-init="getMarketingEmailTemplate()" style="margin-top: 0px;">

                <div>&nbsp;Display Templates<br></div>
                <table border="1" style="margin-top: 20px;">
                    <tr>
                        <td>ID Number </td>
                        <td>Template Name</td>
                        <td></td>
                        <td></td>
                    </tr>
                   
                    <tr ng-repeat = "template in email_templates">
                        <td>{{template.id}}</td>
                        <td>{{template.template_name}}</td>
                        <td><button class="btn btn-info" id="update" name="update" value="update" ng-click="showMarketingEmailTemplate(template.template_id)">update</button></td>
                        <td><button class="btn btn-info" id="delete" name="delete" value="delete" ng-click="deleteEmailTemplate(template.template_id)">delete</button></td>
                    </tr>
                    
                </table>
            </div>
            <br>

        </div>
    </body>
</html>
