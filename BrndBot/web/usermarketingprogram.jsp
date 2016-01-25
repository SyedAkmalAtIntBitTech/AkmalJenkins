<%-- 
    Document   : usermarketingprogram
    Created on : Jan 25, 2016, 6:00:00 PM
    Author     : Satyajit Roy at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Marketing Program </title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/marketing.js"></script>
        <script src="js/usermarketingprogram.js"></script>
        <link href="css/usermarketingprogram.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/pikaday.css">
        <link rel="stylesheet" href="css/datepickerpikaday.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="format-detection" content="telephone=no">
        <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
        <link rel="stylesheet" type="text/css" href="style_detail_overlay-1.css">
        <link rel="stylesheet" type="text/css" href="normalize.css">
        <link rel="stylesheet" type="text/css" href="slat.css">
        <link rel="shortcut icon" href="favicon.png">
        <script src="js/pikaday.js"></script>
        
        <script src="js/dashboard.js"></script>
        
        <%!
            String marketing_category_id = "";
            String marketing_program_id = "";
         %>
         
         <%
             marketing_category_id = request.getParameter("categoryid");
             marketing_program_id = request.getParameter("programid");
         %>
       
         <jsp:include page="basejsp.jsp"/>
         
    </head>
    <body ng-app>
        <div class="content-main" ng-controller="usermarketingprogram">
            <input type="hidden" value="<%=marketing_category_id%>" id="marketing_category_id"/>
            <input type="hidden" value="<%=marketing_program_id%>" id="marketing_program_id"/>
            <%@include file="navbarv2.jsp" %>
        
            <!--Top Nav-->   
            <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
           <div class="exit-button-detail">
                <a class=" exit-button-icon" href="marketingprogramsnew.jsp?categoryid=<%=marketing_category_id%>">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"> </img>
                </a>
            </div>
            <div class="page-title-with-back page-title-font">Marketing Program Details</div>
            <!--<div class="page-cta-container">
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  End Marketing Program</div>    
                </a>
            </div>-->
        </div>
    </div>
            <!--Main Content GENERIC--> 
            <div class="sequence-page-background">
            <div class="sequence-page-content-container">
                <div class="sequence-page-header">Marketing Program Details</div>
                    <div class="email-detail-selection col-1of1 fleft">
                       <div class="col-1of1 fleft">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                    Enter a name for this Marketing Program
                                </div>
                                <input id="program_name" class="input-field-textfield col-8of10" value="" type="text" required="" placeholder="Enter a name for this Marketing Program">
                            </div>
                        </div>
                        <div class="col-1of1 fleft pushUp">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                   Select a date for the end of this program:
                                </div>
                                <input type="text" class="input-field-textfield col-8of10" name="programdatetime" id="programdatetime" value="" readonly="">
                                <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('programdatetime'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });
                                </script>
                            </div>
                        </div>
                         <div class="cols-2 fleft col-9of10 pushUp link-col">
                           
                             <div class="input-field-container col-3of10 fleft pushright">
                                <div class="h4"> Link </div>
                                <input id="program_url" value="" class="input-field-textfield input-placeholder" type="text" required="" onchange="validateurl()" placeholder="Ex. http://www.google.com">
                            </div>
                            <div class="input-field-container col-3of10 fleft">
                                <div class="h4"> From Email Address </div>
                                <input id="program_url_name" value="" class="input-field-textfield input-placeholder" type="text" required="" placeholder="Enter Name for Link">
                            </div>
                        </div>
                      
                    <!--Inner Content Conatiner GENERIC-->
                </div>
            </div>
        </div>
  
            <!--CTA Bar-->
            <div class="bottom-cta-bar">
                <div class="bottom-cta-button-container-lg">
                    <a href="" ng-click="saveMarketingProgram()">
                        <div class="bottom-continue-button button-text-1">Continue</div>
                    </a>
                </div>
            </div>
        </div>
</div>
    </body>
</html>
