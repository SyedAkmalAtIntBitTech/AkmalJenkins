<%-- 
    Document   : history
    Created on : Aug 25, 2015, 6:46:46 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
         <script src="js/form.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<!--        <script src="js/jquery.js" type="text/javascript"></script>-->
<!--        <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>-->
<!--        <script src="js/jquery.min.js" type="text/javascript"></script>-->
        <title>BrndBot - Email Settings</title>

        <style type='text/css'>
            
            div.selectBox
            {
                position:relative;
                display:inline-block;
                cursor:default;
                text-align:left;
                line-height:30px;
                clear:both;
                font-weight:600;
                color: #000000;
                border-top-left-radius: 5px;
                border-bottom-left-radius:  5px;
                background-color: #C6C6C6;
                border:transparent;
            }
            .selectBox{
                position:relative;
                display:inline-block;
                cursor:default;
                text-align:left;
                line-height:30px;
                clear:both;
                font-weight:600;
                color: #000000;
                border-top-left-radius: 5px;
                border-bottom-left-radius:  5px;
                background-color: #C6C6C6;
                border:transparent;

            }
            span.selected
            {
                width:167px;
                text-indent:10px;
                border:0px solid #ccc;
                border-top-left-radius: 5px;
                border-bottom-left-radius:  5px;
                border-right:none;
                background-color: #C6C6C6;
                overflow:hidden;
            }
            span.selectArrow
            {
                width:20px;
                text-align:center;
                font-size:22px;
                border-top-right-radius:5px;
                border-bottom-right-radius:5px;
                -webkit-user-select: none;
                -khtml-user-select: none;
                -moz-user-select: none;
                -o-user-select: none;
                user-select: none;
                background-image: url('images/dropdown.png');
                background-repeat: space;
            }

            span.selectArrow,span.selected
            {
                position:relative;
                float:left;
                height:30px;
                z-index:1;
            }

            div.selectOptions
            {
                position:absolute;
                top:35px;
                left:0;
                width:188px;
                border:1px solid #ccc;
                border-radius:7px;
                overflow:hidden;
                background:#f6f6f6;
                padding-top:2px;
                display:none;
            }

            span.selectOption
            {   

                display:block;
                width:100%;
                line-height:20px;
                padding:5px 10%;
            }

            span.selectOption:hover
            {
                color: #000000;
                background:#7ab5d3;

            }	

            #s11{
                position:relative;
                display:inline-block;
                cursor:default;
                text-align:left;
                line-height:30px;
                clear:both;
                color: #000000;
                background: #888;

            }
        </style>
        <script>
$(document).ready(function () {
                $("#from_address").focus(function (){$("#frmadr").css("left","-145px").css("font-size","13px").css("color","#999");});
                $("#from_address").focusout(function (){var emllist=$("#from_address").val();if(emllist===""){$("#frmadr").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#frmadr").css("left","-145px");}});
                 $("#reply_email_address").focus(function (){$("#rpltoadr").css("left","-175px").css("font-size","13px").css("color","#999");});
                $("#reply_email_address").focusout(function (){var emllist=$("#reply_email_address").val();if(emllist===""){$("#rpltoadr").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#rpltoadr").css("left","-175px");}});
            });
            function validateEmail(sEmail) {
                var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
                if (filter.test(sEmail)) {
                    return true;
                }
                else {
                    return false;
                }
            }

            function validate() {
                var from_address = $("#from_address").val();
                var reply_email_address = $("#reply_email_address").val();
                
                if (from_address === "") {
                    alert("From address not entered! Please enter the from address.");
                    $("#from_address").focus();
                    return false;
                }
                
                if ($.trim(from_address).length == 0) {
                    alert('Please enter valid email address.');
                    $("#from_address").focus();
                    return false;
                }
                
                if (!(validateEmail(from_address))) {
                    alert('Invalid Email Address.');
                    $("#from_address").focus();
                    return false;
                }

                if (reply_email_address === "") {
                    alert("Reply email not entered! Please enter the reply email address.");
                    $("#reply_email_address").focus();
                    return false;
                }
                
                if ($.trim(reply_email_address).length == 0) {
                    alert('Please enter valid email address.');
                    $("#reply_email_address").focus();
                    return false;
                }
                
                if (!(validateEmail(reply_email_address))) {
                    alert('Invalid Email Address');
                    $("#reply_email_address").focus();
                    return false;
                }
                
                return true;
            }

            function controllerUserChanges($scope, $http) {

                $scope.setEmailSettings = function () {
                    var from_address = $("#from_address").val();
                    var reply_email_address = $("#reply_email_address").val();
                    if (validate()) {
                        var email_settings = {"from_address": from_address, "reply_email_address": reply_email_address, "type": "add"};

                        $http({
                            method: 'POST',
                            url: getHost() + 'EmailSettingsServlet',
                            headers: {'Content-Type': 'application/json'},
                            data: email_settings
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "false") {
                                alert("User session has expired! Kindly resubmit a request.");
                            } else if (data === "true") {
                                alert("Settings saved successfully.");
                                $("#from_address").val("");
                                $("#reply_email_address").val("");
                            } else if (data === error) {
                                alert(data);
                            }
                        }).error(function (data, status) {
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.

                            alert("Request not successful!");
                        });

                    }
                };
            }

        </script>
    <jsp:include page="basejsp.jsp" />
    </head>
    <body ng-app class="claro">
        <div ng-controller="controllerUserChanges" class="container" id="container"> 
            <div class="row">
                <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

            <jsp:include page="emailsubmenu.html"/>
            <div class="col-md-8 col-md-offset-2 ">
                <div class="col-md-6 col-md-offset-0"><p id="hyshead" class="MH2">Email Settings</p></div>
                <div class="col-md-6 col-md-offset-0 bgcols">
                    <div id="view1" style="width:550px; height:230px;">

                        <form class="form-horizontal" id="signform" >

                         <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;top:-70px;">
                          
                         <div class="col-md-5 col-md-offset-1" style="left:100px;">              

                         <div class="group">
                                <div class="">                            
                                    <input id="from_address" class="form-control simplebox" type="text" name="from_address" />
                                    <label id="frmadr">FROM ADDRESS</label>
                                </div>
                            </div>
                            <div class="group">
                                <div class="">   
                                    <input id="reply_email_address" class="form-control simplebox" type="text" name="reply_email_address"/>
                                    <label id="rpltoadr">REPLY-TO EMAIL ADDRESS</label>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="">
                                    <br><button type="submit" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="setEmailSettings()">Enter</button><br>
                                </div>
                            </div>
                         </div>
                        </form> 
                    </div>
                </div>
            </div>
        </div>     
    </body>
</html>