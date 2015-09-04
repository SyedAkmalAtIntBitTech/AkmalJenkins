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
         <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
         <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <title>Email Lists</title>
        <style>
            
            .emlhisdata li{
                display: table-cell;
                position: relative;
                left:56em;
                bottom:0px;
                top:0px;
            }
            .emlist {
                overflow-y: scroll;
                position: relative;
                width: 900px;
                left:300px;
                font-family: "proxima-nova",sans-serif;
                font-weight: 300;
                color: #2d4355;
                font-style: normal;
                text-align: left;
                line-height: 25.9px;
                letter-spacing: 0em;
            }
            .emlOneRowData li{
                    font-family: "proxima-nova",sans-serif;
                    font-weight: 400;
                    color: #2d4355;
                    vertical-align:middle; 
                    left:30px;
                    height: 10%;
                    display: table-cell;
                    position: relative;
                    padding-top: 3%;
                    padding-bottom: 3%;
            }
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
                border-radius: 10px;
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
                bottom: 10px;
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
            var selectedlistname = "";
            var selectedemailids = "";
            
            function selectEmailId(id){
                
                var selectedid  = document.getElementById(id).checked;
                
                if (selectedid){
                    emailid = $("#"+id).val();
                    selectedemailids = emailid + "," + selectedemailids;
                }else{
                    emailid = $("#"+id).val();
                    selectedemailids = selectedemailids.replace(emailid+",","");
                }
            }
            
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
            
            function setSelectedlistName(listname){
                alert(listname);
                $("#email_list_name").val(listname);
            }
            
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
                                $('#textArea').val($('#textArea').val() + rows);
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
            function validate() {
                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                
                if (emailListName === "") {
                    alert("email list name not entered, please enter the from email list");
                    $("#list_name").focus();
                    return false;
                }
                
                if ($.trim(defaultFromName).length == 0) {
                    alert('Please enter default from name');
                    $("#default_from_name").focus();
                    return false;
                }
                

                if (listDescription === "") {
                    alert("list description not entered, please enter the list description");
                    $("#reply_email_address").focus();
                    return false;
                }
                
                return true;
            }

            function EmailListController($scope, $http) {


                $scope.createEmailList = function () {
                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                    if (validate()){
                        var Emails = {"emailListName": emailListName, "defaultFromName": defaultFromName, "listDescription":listDescription, "update": "addEmailList"};
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
                        
                    }
                    
                };
                $scope.updateEmailList = function () {
                    var email_list_name = $("#email_list_name").val();
                    var email_list = $("#textArea").val();

                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list, "update": "UpdateEmailList"};
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
                        $scope.emailLists1 = data.allEmailListNames
                        if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                };

                $scope.showEmailListWithContacts = function () {
                    $(".emaillist").show();
                    $("#email_list_name").hide();

                    var emailids = {"update": "allEmailListNames"};
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=allEmailListWithNoOfContacts',
                    }).success(function (data, status, headers, config) {
                        $scope.emailLists = data.allEmailListWithNoOfContacts;
                        
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
                
                $scope.updateList = function (list_name) {
//                    alert(list_name);
                    $("#email_list_name").val(list_name);
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=emailsForEmailList&list_name='+list_name
                    }).success(function (data, status, headers, config) {
                        $scope.emailAddresses = data.emailAddresses;
                        
                        if (data.emailAddresses !== "") {
                            $("#tab3").show();
                            $("#tab1").hide();
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                    
                };
                
                $scope.getEmailList = function () {

                    var list_name = $("#email_list_name").val();
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=emailsForEmailList&list_name='+list_name
                    }).success(function (data, status, headers, config) {
                        
                        if (data.emailAddresses !== "") {
                            $("#tab4").show();
                            $("#tab1").hide();
                            var i = 0;
                            var emails = "";
                            for(i=0; i<data.emailAddresses.length; i++){
                                if (data.emailAddresses[i].emailid != ""){
                                    emails = data.emailAddresses[i].emailid + "," + emails;
                                }
                            }
                            $("#textArea").val(emails);
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                    
                };
                
                $scope.selectCheckBox = function (){
                    var selectAll = document.getElementById("selectAll").checked;
                    if (selectAll){
                         $(".email").attr("checked", true);
                    }else {
                         $(".email").attr("checked", false);
                    }

                };
                
                $scope.deleteSelected = function (){
                    var selectAll = document.getElementById("selectAll").checked;
                    var email_list_name = "";
                    if (selectAll){
                        
                        email_list_name = $("#email_list_name").val();
                        var Emails = {"update": "deleteAllEmailsFromList", "emailListName":email_list_name};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data deleted successfully");
//                                $scope.updateList(email_list_name);
                                
                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                    }else{
                        email_list_name = $("#email_list_name").val();
                        var Emails = {"update": "deleteEmailInEmailList", "emailListName":email_list_name, "emailAddresses":selectedemailids};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data deleted successfully");
                                $scope.updateList(email_list_name);
                                
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                    }
                };
                $scope.showAddContacts = function (){
                    $("#tab3").hide();
                    $("#tab4").show();
                };
                
                $scope.showCreateContacts = function(){
                    $("#tab2").show();
                    $("#tab1").hide();
                };
            }

        </script>
    </head>
    <body ng-app>
        
        <div class="row" ng-controller="EmailListController">
             <jsp:include page="leftmenu.html"/><!--/end left column-->
              <jsp:include page="emailsubmenu.html"/>
              
                <div class="col-md-8 col-md-offset-2 " >
                
                <div class="col-md-6 col-md-offset-0">
                    <p id="hyshead" class="MH2">Email Lists</p>
                    <div id="email_headings"  class="col-md-4 col-md-offset-0 ">
                        <ul class="emlhisdata  FL2">
                            <li><button class="button button--moema button--text-thick button--text-upper button--size-s" style="width:150px;" type="button" name="createNew" id="createNew" value="CREATE NEW" ng-click="showCreateContacts()">CREATE NEW</button></li>
                        </ul>
                    </div>
                    
                    <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;left:5px;">

                    <div id="scrl" class="col-md-6" ng-init="showEmailListWithContacts()">
                        <ul class="emlOneRowData L2 LE2" ng-repeat="email in emailLists">                            
                            <li style="width: 450px;text-align:left;left:-35px;" onclick="setSelectedlistName('{{email.emailListName}}')">{{email.emailListName}}</li>
                            <li style="width: 250px">{{email.listDescription}}</li>
                            <li style="width: 250px">{{email.noofcontants}}</li>
                            <li style="width: 250px">contacts</li>
                            <li style="width: 250px"><button type="button" ng-click="updateList(email.emailListName)">edit</button> </li>
                        </ul>
                       
                    </div><br><br>

                </div>
              <div id="tab2" class="col-md-8 col-md-offset-2 " style="display:none">
                  <div class="col-md-6 col-md-offset-0"><p id="hyshead">Create a new list(MH2)</p></div><br><br><br><br>
                <p>Create a new email list. After you hit save, you will then be
                able to add new contacts. (SS2)</p>
                <div class="col-md-6 col-md-offset-0 bgcols">
                    <div id="view1" style="width:550px; height:100px ">

                        <form class="form-horizontal" id="signform" >

                            <div class="group">
                                <div class="col-md-3 col-md-offset-5">                            
                                    <p class="text-left"></p>
                                </div>
                            </div>

                            <div class="group">
                                <div class="col-md-3 col-md-offset-5">                            
                                    <label>LIST NAME</label><br>
                                    <input id="list_name" class="form-control simplebox" type="text" name="list_name" />
                                </div>
                            </div>
                            <div class="group">
                                <div class="col-md-3 col-md-offset-5">                            
                                    <label>DEFAULT FROM NAME    </label><br>
                                    <input id="default_from_name" class="form-control simplebox" type="text" name="default_from_name"/>
                                </div>
                            </div>
                            <div class="group">
                                <div class="col-md-3 col-md-offset-5">                            
                                    <label>LIST DESCRIPTION</label><br>
                                    <input id="list_description" class="form-control simplebox" type="text" name="list_description"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-5 col-md-offset-4">
                                    <br><button type="submit" class="button" ng-click="createEmailList()">Enter</button><br>
                                </div>
                            </div>

                        </form> 
                    </div>


                </div>
            </div>
            
            <div id="tab3" class="col-md-8 col-md-offset-2" style="display:none">
                <div ng-controller="EmailListController">
                    
                    <div class="col-md-6 col-md-offset-0"><p id="hyshead">Manage "List Name" List</p></div><br>
                    
                <div>
                    <button type="button" ng-click="getEmailList()">ADD CONTACTS</button>
                    <ul><li><p ng-click="deleteSelected()">delete selected</p></li></ul>
                    <ul><li><input id="selectAll" type="checkbox" ng-click="selectCheckBox()">email address</li></ul>
                </div>
                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;left:5px;">

                <div id="scrl" class="col-md-6">
                    <ul class="emlOneRowData L2 LE2" ng-repeat="email in emailAddresses">
                        <li><input id="{{email.id}}" class="email" type="checkbox" value="{{email.emailid}}" onclick="selectEmailId('{{email.id}}')">{{email.emailid}}</li>
                    </ul>
                </div>
                </div>

            </div>
              
              <div id="tab4" class="col-md-10 col-md-offset-2" style="display:none">
                <div id="emailsubjectdiv" ng-controller="EmailListController">
                    <p class="header1">Email List:</p>
                    <input type="text" class="hideinputborder" id="email_list_name" name="email_list_name" placeholder="Enter Here"/> <br><br><br>
                    <div class="col-md-offset-1">
                        <label id="">Upload CSV</label>
                        <input type="file" class="fileUpload" id="fileUpload" name="fileUpload">
                        <input type="button" id="upload" value="Upload" onclick="upload()" />
                    </div><br>
                    <div id="dvCSV"></div>
                    <textarea width="400" height="500" id="textArea"></textarea><br><br>

                    <input  id="emailSubjectContinueButton" type="button" class="btn btn-primary" value="Update" ng-click="updateEmailList()">
                </div>
            </div>
        </div>
                <!--    <div id="emaillist">
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

           

            $("#fileUpload").change(function () {

                loadImageFile();
                // resets input file
                $('.fileUpload').wrap('<form>').closest('form').get(0).reset();
                $('.fileUpload').unwrap();
            });

        </script>
    </body>
</html>
