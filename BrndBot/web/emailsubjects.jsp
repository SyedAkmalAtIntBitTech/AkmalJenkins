<%-- 
    Document   : emailsubjects
    Created on : Jan 8, 2016, 7:16:18 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="js/alert_message.js" type="text/javascript"></script>
    <script src="js/angular.min.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <link href="css/popup.css" rel="stylesheet" type="text/css"/>
    <title>BrndBot - Email Subject</title>    
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link rel="stylesheet" href="css/popup.css">
    <script src="js/emailsubject.js" type="text/javascript"></script>
    <%! String mindbody_id=""; %>
    <% mindbody_id = request.getParameter("id"); %>
    <jsp:include page="basejsp.jsp" />
     
       
</head>    
 
<body ng-app>
    <!--SideNav-->
    <div class="content-main">
     <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <input type="hidden" class="input-field-textfield col-8of10" id="mindbodyid" placeholder="Enter Name of email" value="<%=mindbody_id%>"></input>
            <a class=" exit-button-icon" href="channelselection.jsp?id=<%=mindbody_id%>">
                <div class="exit-button-detail">
                     <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                </div>
            </a>
            <div class="page-title-with-back page-title-font">Email Details</div>
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
                <div class="sequence-page-header">Enter Subject Line</div>
                    <div class="email-detail-selection col-1of1 fleft">
                        <div class="col-1of1 fleft">
                            <div class="col-9of10 fleft">
                                <input id="emailsubject" class="input-field-textfield col-8of10" placeholder="Enter Name of email" name="emailsubject" type="text" required/>
                            </div>
                        </div>
                         
                        <div class="col-1of1 pushUp fleft">
                             <div class="fleft">
           
                            </div>
                        </div>
                    <!--Inner Content Conatiner GENERIC-->
                </div>
            </div>
        </div>
  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
                <a id="emailSubjectContinueButton" href="">
                    <div class="bottom-continue-button button-text-1">Continue</div>
                </a>
            </div>
        </div>
    </div>
<!--</div>-->
    </body>
</html>
