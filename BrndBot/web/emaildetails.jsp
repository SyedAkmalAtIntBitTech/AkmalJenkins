<%-- 
    Document   : emaildetails
    Created on : Jan 9, 2016, 12:33:34 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="js/angular.min.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <title>BrndBot - Email Details</title>    
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <jsp:include page="basejsp.jsp" />
    <script>
        function validate() {
                    var regex=/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;

                    var emailName = $("#emailName").val();
                    var subject = $("#subject").val();
                    var fromName = $("#fromName").val();
                    var fromAddress = $("#fromAddress").val();
                
                if (emailName === "") {
                    alert("Email name not entered!  Please enter the Name of email.");
                    $("#emailName").focus();
                    return false;
                }
                 if (subject === "") {
                    alert("Email Subject not entered! Please enter the Subject.");
                    $("#subject").focus();
                    return false;
                }
                if (fromName === "") {
                    alert("From name not entered! Please enter From name.");
                    $("#fromName").focus();
                    return false;
                }
                if ($.trim(fromAddress).length === 0) {
                    alert('Please enter from Address');
                    $("#fromAddress").focus();
                    return false;
                }
                if(regex.test(fromAddress)===false){
                    alert("Invalid Email Address!");
                    $("#fromAddress").focus();
                    return false;
                }
                else{
                    alert("Success.");
                }
                return true;
            }
    </script>
</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main">
        <%@include file="navbarv2.jsp" %>
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
           <div class="exit-button-detail">
                   <a class=" exit-button-icon" href="">
                        <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                   </a>
            </div>
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
                <div class="sequence-page-header">Email Details</div>
                    <div class="email-detail-selection col-1of1 fleft">
                       <div class="col-1of1 fleft">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                    Enter a name for this Email
                                </div>
                                <input id="emailName" class="input-field-textfield col-8of10" placeholder="Enter Name of email" type="text"></input>
                            </div>
                        </div>
                        <div class="col-1of1 fleft pushUp">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                   Enter a subject line for this Email:
                                </div>
                                <input id="subject" class="input-field-textfield col-8of10" placeholder="Enter Subject of email" type="text"></input>
                            </div>
                        </div>
                         <div class="cols-2 fleft col-9of10 pushUp">
                             <div class="input-field-container col-3of10 fleft pushright">
                                <div class="h4"> From Name </div>
                                <input id="fromName" class="input-field-textfield input-placeholder" placeholder="Enter From Name" type="text"></input>
                            </div>
                             <div class="input-field-container col-3of10 fleft">
                                <div class="h4"> From Email Address </div>
                                <input id="fromAddress" class="input-field-textfield input-placeholder" placeholder="Enter From  Email Address" type="text"></input>
                            </div>
                        </div>
                    <!--Inner Content Conatiner GENERIC-->
                </div>
            </div>
        </div>
  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
                <a href="">
                    <div class="bottom-continue-button button-text-1" onclick="validate();">Continue</div>
                </a>
            </div>
        </div>
         </div>
</div>
    </body>
</html>