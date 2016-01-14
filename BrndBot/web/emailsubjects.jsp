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
    
     <%! String mindbody_id=""; %>
     <% mindbody_id = request.getParameter("id"); %>
     <jsp:include page="basejsp.jsp" />
     
       <script>
            
            $(document).ready(function () {
                
                $("#emaillist").hide();
                $("#popup").hide();
                $("#emailSubjectContinueButton").click(function () {
                    
 ///////////////////////////// Added by Satyajit Roy on 30th nov 2015 ///////////////////////////
 
                    var email_subject = $("#emailsubject").val();
                    if(email_subject=="")
                    {
                        alert("Please enter email subject.");
                        $("#emailsubject").focus();
                        return false;
                    }
                    else{
                    document.title="BrndBot - Email List Selection"; 
                    }
                    var email_addresses = "";
                    var email_list = "";
                        $.ajax({
                        url: getHost() + "EmailTextDataServlet",
//                        dataType: 'json',
                        data: {
                            email_subject: email_subject,
                            email_addresses: email_addresses,
                            email_list : email_list
                        },
                        success: function(result){
//                            alert("Email sent successfully");
                           
                            if (result == "true"){
                                document.location.href = "emaileditor.jsp?id=<%= mindbody_id %>";
                            }
                        }
                    });
                
 ////////////////////////////////////////// END ////////////////////////////////////////////////
                    
 /////////////////////////////////// Commented on 30th nov 2015 by Satyajit Roy /////////////////
 //
//                    document.title="BrndBot - Email List Selection"; 
//                    $("#chooseEmailList").show();
//                    $("#emailsubjectdiv").hide();
//                    $("#emailaddresses").hide();
//                    $("#drop-zone").hide();
//                    $("#clickHere").hide();
//                    $("#upload").hide();
//                    $("#emaillist").show();
              
 /////////////////////////////////// END of Comment //////////////////////////////////////////////
 
                });
          
                $("#emailIdContinueButton").click(function () {
                    var selectedEmail=$("#chooseEmailList").val();
                    alert("..");
                    if(selectedEmail !== "1")
                    {
                        alert(selectedEmail);
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        
                        if(trim(email_addresses)!=="")
                        {
                            alert(email_addresses);
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#emailSettings").show();
                            $("#emaillistdiv").hide();
                            $("#emailSettings").show();
                            var email_list = $("#chooseEmailList").val();
                            $.ajax({
                                url: getHost() + "EmailTextDataServlet",
                                data: {
                                    email_subject: email_subject,
                                    email_addresses: email_addresses,
                                    email_list : email_list
                                },
                                success: function(result){
                                }
                            });
                        }
                        else
                        {
                            alert("Please select at least one email list or add email manually.");
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                }
                else{
                        
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        if(trim(email_addresses)!=="")
                        {
                            alert(email_addresses);
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#toaddress").val(email_addresses);
                            $("#emailSettings").show();
                            var email_list = $("#chooseEmailList").val();
                            $.ajax({
                                url: getHost() + "EmailTextDataServlet",
                                data: {
                                    email_subject: email_subject,
                                    email_addresses: email_addresses,
                                    email_list : email_list
                                },
                                success: function(result){
                                }
                            });
                        }
                        else
                        {
                            alert("Please select at least one email list or add email manually.");
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                }
            });
              });
            
        </script>
        
         <script>
      
            function selectCsvFile(){
                $("#chooseEmailList").show();
//                 $('#chooseEmailList').val("1").trigger('change');
                 
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var list_name = document.getElementsByTagName("option")[x].value;
                if (list_name == 1){                   
//                    $("#emailIdContinueButton").show();
//                    $("#entertext").show();
//                    $("#dragtext").show();
//                    $("#emailaddresses").show();
//                    $("#emailaddresses").val('');
//                    $("#drop-zone").show();
//                    $("#clickHere").show();
//                    $("#upload").show();
//                    $("#emailIdContinueButton").css("top","-70px");
                    $(function () {

                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";

                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");

                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;

                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }

                    }, true);

                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);

                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;

                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert("File have been added, Click on the upload button to load the csv file.");
    //                    upload();
                    }, true);
                    });

                }else {
                   $("#emailIdContinueButton").show();
                    $("#entertext").show();
                    $("#dragtext").show();
                    $("#emailaddresses").show();
//                    $("#emailaddresses").val('');
                    $("#drop-zone").show();
                    $("#clickHere").show();
                    $("#upload").show();
                    $("#emailIdContinueButton").css("top","-70px");
                    $(function () {

                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";

                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");

                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;

                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }

                    }, true);

                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);

                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;

                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert("The CSV file has been added, click on the upload button to load the CSV file in the form.");
    //                    upload();
                    }, true);
                    });

                }

            }
        </script>
        <script>
            function fileselected(){
             
               var a = document.getElementById('file');
                if(a.value == "")
                {
                fileselect.innerHTML = "Choose file";
                }
                else
                    {
                        var theSplit = a.value.split('\\');
                        fileselect.innerHTML = theSplit[theSplit.length-1];
                    }
            }
              function upload() {
                var fileUpload = document.getElementById("file");
//                alert(fileUpload.value.toLowerCase());
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                             if ($('#emailaddresses').val() == "") {
                                $('#emailaddresses').val(rows);
                            } else {
                                $('#emailaddresses').val($('#emailaddresses').val() + ", " + rows);
                            }
                        }
                        reader.readAsText(fileUpload.files[0]);

                    } else {
                        alert("This browser does not support HTML5.");
                    }
                } else {
                    alert("Please upload a valid CSV file.");
                }
            }
            
                function EmailListController($scope, $http) {

                $scope.addEmailList = function () {
                    var email_list_name = $("#listname").val();
                    var email_list = $("#textArea").val();
                
                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list , "update": "addUpdateEmailList"};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data saved successfully.");
                               window.open(getHost() + 'emailsubject.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                };

                $scope.showEmailList = function () {
                                $(".emaillist").show();
                                $("#email_list_name").hide();

                        var emailids = {"update": "allEmailListNames"};
                        $http({
                            method: 'GET',
                            url: getHost() + 'GetEmailLists?update=allEmailListNames'
                    }).success(function(data, status, headers, config) {
                            $scope.emailLists = data.user;
                            $scope.emailLists_mindbody = data.mindbody;
                            if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                };
                
                $scope.clearfields = function() {
                    $("#email_list_name").val("");
                    $("#emailaddresses").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
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
