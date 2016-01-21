<%-- 
    Document   : emailsubject
    Created on : 29 Jul, 2015, 4:38:08 PM
    Author     : sandeep-kumar 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
<!--        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <link href="css/popup.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/main1.css">
        <link rel="stylesheet" href="css/popup.css">
        <script src="js/jquery.csv-0.71.js" type="text/javascript"></script>
        <script src="js/emailsubject.js" type="text/javascript"></script>
        <title>BrndBot - Email Subject</title>
        
        <%! String mindbody_id=""; %>
        <% mindbody_id = request.getParameter("id"); %>
        
        <jsp:include page="basejsp.jsp" />
    </head>
    <%! 
        String html_text = "";
    %>
    <%
        try{
//            html_text = request.getParameter("htmlString");
        }catch (Exception e){
            
        }
     %>
     <body style="overflow-x: hidden;" ng-app>
            <div class="row">
            <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div><!--/end left column-->
            <div id="datadiv" class="col-md-8 col-md-offset-2">
                <intput type="hidden" id="mindbody_id" value="<%=mindbody_id%>"></intput>
                <div id="emailsubjectdiv">
                    <p class="header1 MH2"> Enter the Subject Line of the Email:</p>
                   <div class="sublingrp">
                   <div class="col-md-5 col-md-offset-0">                            
                       <input id="emailsubject" class="inputsubline" name="emailsubject" type="text" required/>
                       <label>SUBJECT LINE</label><br>
                    </div>
                    </div>
                    <input id="emailSubjectContinueButton" type="button" class="button button--moema button--text-thick button--text-upper button--size-s btn-prim" value="CONTINUE"/>
                </div>
            </div>      
<!--        </div>-->
    </body>
</html>