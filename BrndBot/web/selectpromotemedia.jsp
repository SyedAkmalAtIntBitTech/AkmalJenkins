<%-- 
    Document   : selectpromotemedia
    Created on : 29 Jul, 2015, 11:48:01 AM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String minbodyid;%>
<% minbodyid = request.getParameter("id");%>
<!DOCTYPE html>
<html>
    <head>

        <title>promote media</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        <%!
            SqlMethods sql_methods = new SqlMethods();
            String category_id, sub_category_name, sub_category_id;
        %>

        <%
            try {
                sql_methods.session = request.getSession(true);
                
                if (request.getParameter("category_id") != null){
                    category_id = request.getParameter("category_id");
                    sql_methods.session.setAttribute("category_id", category_id);
                }
                if (request.getParameter("sub_category_name") != null){
                    sub_category_name = request.getParameter("sub_category_name");
                    sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                }
                if (request.getParameter("sub_category_name") != null){
                    sub_category_id = request.getParameter("sub_category_id");
                    sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                }

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        %>
        
        <script>
            function selected_media(selectedmedia) {
                var configuration = global_host_address + selectedmedia + ".jsp" + "?id=" +<%= minbodyid%>;
                window.open(configuration, "_self");
            }
        </script>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body>

        <div> 
            <div class="row">
                <jsp:include page="mainmenu.html"/><!--/end left column-->

                <div class="col-md-10 col-md-offset-2">
                    <p id="text3" class="MH2">How would you like to promote it?</p> 
                    <ul id="promotebuttonlist"> 
                        <li><a onclick="selected_media('socialeditor')"><img src="images/NavIcon_Social-white.svg" id="social" class="glyphicon glyphicon-comment"/></a><p id="soceml">Social</p></li>
                        <li><a onclick="selected_media('emailsubject')"><img src="images/NavIcon_Email-white.svg" id="email" class="glyphicon glyphicon-envelope" style="padding-bottom:7%;"/></a><p id="soceml">Email</p></li>
                        <!--                                <li><a ><span id="print" class="glyphicon glyphicon-print"></span></a><p id="promotebutton">Print</p></li>-->
                    </ul>    
                </div>

            </div>      

        </div>   
    </body>
</html>
