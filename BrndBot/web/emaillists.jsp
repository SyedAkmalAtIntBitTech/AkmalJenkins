<%-- 
    Document   : emailsubject
    Created on : 29 Jul, 2015, 4:38:08 PM
    Author     : sandeep-kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
         <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <title>Email Lists</title>
        <style>
            #datadiv{
                margin-top:120px;
            }

            .header1{
                font-size: 28px;
            }
            .hideinputborder{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:450px;
                font-size: 35px;
            }
            .hideinputborder:focus{
                outline: none;
            }
            .hideinputEmailId{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:250px;
                margin-left: 150px;
                font-size: 18px;
            }
            .hideinputEmailId{
                outline: none;
            }
            #chooseEmailList {
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                height:50px;
                width:250px;
                font-size: 18px;
                border-radius: 5px;
            }
            #chooseEmailId:focus{
                outline: none;
            }
            #textArea{
                width: 400px;
                height : 300px;
                background-color: #e4e4e4;
            }
            .btn-primary{
                border-radius: 13px;
                position: relative;
                width: 150px;
                background-color: #00A37A;
                border-color: #00A37A;
            }
            .fileUpload {
                position: relative;
                overflow: hidden;
                margin: 10px;
            }
            .fileUpload input.upload {
                position: absolute;
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                font-size: 20px;
                cursor: pointer;
                opacity: 0;
                filter: alpha(opacity=0);
            }
        </style>
        <script>

            var update = "";
            $(document).ready(function () {

                $("#chooseEmailList").change(function () {
                    var x = document.getElementById("chooseEmailList").selectedIndex;
                    var List_name = document.getElementsByTagName("option")[x].value;
                    $("#email_list_name").val(List_name);

                    $.ajax({
                        url: getHost() + "GetEmailLists",
                        data: {
                            update: "emailsForEmailList",
                            list_name: List_name
                        },
                        success: function (result) {
                            var email_addresses = JSON.stringify(result.emailAddresses);
                            var email_add = email_addresses.replace("\"", '');
                            var email_address = email_add.replace("\"", '');
                            $("#textArea").val(email_address);
                        }
                    });

                });
            });

            function showTextBox() {
                $(".emaillist").hide();
                $("#email_list_name").val("");
                $("#email_list_name").show();
                $("#email_list_name").focus();

            }

            function upload() {

                var fileUpload = document.getElementById("fileUpload");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                            if ($('#textArea').val() == "") {
                                $('#textArea').val(rows);
                            } else {
                                $('#textArea').val($('#textArea').val() + ", " + rows);
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

            function showListBox() {
                $(".emaillist").show();
                $("#email_list_name").hide();

            }


            function EmailListController($scope, $http) {

                $scope.addEmailList = function () {
                    var email_list_name = $("#email_list_name").val();
                    var email_list = $("#textArea").val();

                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list, "update": "addUpdateEmailList"};
                    $http({
                        method: 'POST',
                        url: getHost() + 'SetEmailLists',
                        headers: {'Content-Type': 'application/json'},
                        data: Emails
                    }).success(function (data)
                    {
                        if (data === "true") {
                            alert("Data saved successfully");
                            window.open(getHost() + 'emaillists.jsp', "_self");
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
                        url: getHost() + 'GetEmailLists?update=allEmailListNames',
                    }).success(function (data, status, headers, config) {
                        $scope.emailLists = data.allEmailListNames
                        if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                };

                $scope.clearfields = function () {
                    $("#email_list_name").val("");
                    $("#textArea").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
            }


        </script>
    </head>
    <body ng-app>
        <div class="row">
             <jsp:include page="mainmenu.html"/>
            <div id="datadiv" class="col-md-10 col-md-offset-2">
                <div id="emailsubjectdiv" ng-controller="EmailListController">
                    <p class="header1">Email List:</p>
                    <input type="button" name="addlist" value="Add List" class="btn btn-primary" id="addlist" onclick="showTextBox()"/>
                    <input type="button" name="editlist" value="Edit List" class="btn btn-primary" id="editlist" ng-click="showEmailList()"/><br><br>
                    <select id="chooseEmailList" name="chooseEmailList" class="emaillist" hidden="true">
                        <option ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                    </select>
                    <input type="text" class="hideinputborder" id="email_list_name" name="email_list_name" placeholder="Enter Here"/> <br><br><br>
                    <div class="col-md-offset-1">
                        <label id="">Upload CSV</label>
                        <input type="file" class="fileUpload" id="fileUpload" name="fileUpload">
                        <input type="button" id="upload" value="Upload" onclick="upload()" />
                    </div><br>
                    <div id="dvCSV"></div>
                    <textarea width="400" height="500" id="textArea"></textarea><br><br>

                    <input  id="emailSubjectContinueButton" type="button" class="btn btn-primary" value="Update" ng-click="addEmailList()">
                    <input  id="emailSubjectContinueButton" type="button" class="btn btn-primary" value="Clear" ng-click="clearfields()">
                </div>
            </div>
        </div>
        <!--                <div id="emaillist">
                            <p class="header1"> Choose an email list to send to,<br>
                                or enter email addresses.</p>
                            <p class="header2">This can be edited later.</p><br><br>
                            <select id="chooseEmailId">
                                <option>Email List Drop Down</option>
                            </select>
                            <input type="text" class="hideinputEmailId" id="emailId" name="emailsubject" placeholder="Add CSV or Email Manually"> <br><br><br><br><br>
                            <input  id="emailIdContinueButton" type="button" class="btn btn-primary" value="CONTINUE">
                        </div>                   
                    </div><br>
                </div>-->

        <script>
            $(".cross").hide();
            $(".menu").hide();
            $("#emaillist").hide();

            $(".hamburger").click(function () {
                $(".menu").slideToggle("slow", function () {
                    $(".hamburger").hide();
                    $(".cross").show();
                });
            });
            $(".cross").click(function () {
                $(".menu").slideToggle("slow", function () {
                    $(".cross").hide();
                    $(".hamburger").show();
                });
            });

//            $(document).ready(function (){
//                    alert("text");
//                
//            }

            $("#fileUpload").change(function () {

                loadImageFile();
                // resets input file
                $('.fileUpload').wrap('<form>').closest('form').get(0).reset();
                $('.fileUpload').unwrap();
            });

//                    function loadImageFile() {
//                        
////                        var input  = "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..." +
////                                            "All work","and no play","makes Jack","a dull boy..."";
////                                    
////                                    var result = $.csv.toArrays(input);
////                                    alert(result);
////                       if (document.getElementById("uploadfile").files.length === 0) return;
////                               var oFile = document.getElementById("uploadfile").files[0];
////                               if (!rFilter.test(oFile.type)) {
////                               return;
////                              }
////                           oFReader.readAsDataURL(oFile);
////                        alert(oFile);
//       //            $("#emailSubjectContinueButton").click(function () {
//       //                $("#emaillist").show();
//       //                $("#emailsubjectdiv").hide();
//       //            });
//                   }

        </script>
    </body>
</html>
