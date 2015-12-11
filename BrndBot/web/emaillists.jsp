<%-- 
    Document   : emailsubject
    Created on : 29 Jul, 2015, 4:38:08 PM
    Author     : Sandeep kumar
    Edited By  : Satyajit Roy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/angular.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/form.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <script src="js/emaillist.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link href="css/emaillist.css" rel="stylesheet" type="text/css"/>
        <title>BrndBot - Email List</title>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body ng-app>
        <div class="row" ng-controller="EmailListController">
             <jsp:include page="leftmenu.html"/><!--/end left column-->
             <jsp:include page="emailsubmenu.html"/>
             <jsp:include page="addcontact.jsp"/>
                <div class="col-md-8 col-md-offset-2 " >
                    <div id="tab1" class="col-md-6 col-md-offset-0">
                    <p id="hyshead" class="MH2">Email Lists</p>
                    <div id="email_headings">
                        <ul class="emlhisdata FL2 ">
                            <li><button class=" button button--moema button--text-thick button--text-upper button--size-s" style="width:135px;" type="button" name="createNew" id="createNew" value="CREATE NEW" ng-click="showCreateContacts()">CREATE NEW</button></li>
                        </ul>
                    </div>
                    <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;top:-60px;">
                    <div id="scrl" class="col-md-6"  ng-init="showEmailListWithContacts()">
                        <ul class="emlOneRowDatalst L2 " ng-repeat="email in emailLists"> 
                            <li style=" left:-50px;" onclick="setSelectedlistName('{{email.emailListName}}')"><p class="emlOneRowDatalst L2" style="width:300px;">{{email.emailListName}}</p><p class="BC1" style="width:200px;">{{email.listDescription}}</p></li>
                            <li style="width:300px;text-align:center;left:100px;">{{email.noofcontants}}<br><p class="BC1">contacts</p></li>
                            <li style="width:300px;text-align:center;left:430px;"><button type="button" ng-click="updateList1(email.emailListName, 'user')">EDIT LIST</button> </li>
                        </ul>
                        <ul class="emlOneRowDatalst L2 " ng-repeat="email in emailListsMindbody">
                            <li style=" left:-50px;" onclick="setSelectedlistName('{{email.emailListName}}')"><p class="emlOneRowDatalst L2" style="width:300px;">{{email.emailListName}}</p><p class="BC1" style="width:200px;">{{email.listDescription}}</p></li>
                            <li style="width:300px;text-align:center;left:100px;">{{email.noofcontants}}<br><p class="BC1">contacts</p></li>
                            <li style="width:300px;text-align:center;left:430px;"><button type="button" ng-click="updateList1(email.emailListName, 'mindbody')">DETAILS</button> </li>
                        </ul>
                    </div><br><br>
                </div>
              <div id="tab2" class="col-md-8 col-md-offset-2 " class="hide" style="padding-top:5%;">
                  <div class="col-md-6 col-md-offset-0"><p id="hyshead" class="MH2">Create a new list</p></div>
                <div class="col-md-6 col-md-offset-0">  <p class="SS2" style="width:400px;left:-245px;position:relative;top:80px;"> Create a new email list. After you hit save, you will then be
                able to add new contacts.</p></div>
                <div class="col-md-6 col-md-offset-0 bgcols">
                    <div id="view1" style="position:relative;left:-350px;padding-top:8%;" >
                        <form class="form-horizontal" id="signform" >
                            <div class="group">
                                <div class="col-md-3 col-md-offset-5 ">                            
                                    <input id="list_name" class="brdr form-control simplebox" type="text" name="list_name" />
                                    <label>LIST NAME</label><br>
                                </div>
                            </div>
                            <div class="group">
                                <div class="col-md-3 col-md-offset-5">                            
                                    <input id="default_from_name" class="brdr form-control simplebox" type="text" name="default_from_name"/>
                                    <label>DEFAULT FROM NAME </label><br>
                                </div>
                            </div>
                            <div class="group">
                                <div class="col-md-3 col-md-offset-5">                            
                                    <input id="list_description" class="brdr form-control simplebox" type="text" name="list_description"/>
                                    <label>LIST DESCRIPTION</label><br>
                                    <p class="BC1" style="width:300px;">Keep this short and sweet! Like “People Interested in Events”</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-5 col-md-offset-5">
                                    <br><button type="submit" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="createEmailList()">Enter</button><br>
                                </div>
                            </div>
                        </form> 
                    </div>
                </div>
            </div>
            <div id="tab3" class="col-md-10 col-md-offset-0" class="hide" style="">
                <div ng-controller="EmailListController">
                    <div class="col-md-6 col-md-offset-0" style="width:400px;">
                        <p id="hyshead" class="MH2 head">Manage {{selected_email_listname}}</p>
                        <button type="button" id="addcontacts" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:140px;position:relative;left:780px;top:-68px;" ng-click="getEmailList()">ADD CONTACTS</button>
                    </div>
                    <br>
                    <div class="col-md-5 col-lg-10 col-md-offset-0">
                        <ul class="delsel L2 ">
                            <li style="left:-50px;"><p  style="width:200px;" id="deleteSelected" ng-click="deleteSelected()">delete selected</p></li>
                            <li> <input style="top:17px;position:relative;left:-160px;" id="selectAll" type="checkbox" ng-click="selectCheckBox()" ><p id="emailaddrs">email address</p></li>
                        </ul>
                    </div>
                <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;left:5px;top:-70px;">
                    <div id="scrl" class="col-md-6" >
                        <ul id="uluseremails" class="emlOneRowDatalst L2 LE2" ng-repeat="email in user_emailAddresses">
                            <li id="liemailid1" style="width:300px;left:-30px;top:-80px;"><input style="top:17px;position:relative;left:-160px;" id="{{email.emailAddress}}" class="email" type="checkbox" value="{{email.emailid}}" onclick="fun('{{email.emailid}}'),selectEmailId('{{email.id}}')">{{email.emailAddress}}</li>
                            <p ng-show="email.emailAddress == '' && type == 'user'" id='NoContacts' style="margin-top:-80px;">No contacts available</p>
                        </ul>
                        <ul class="emlOneRowDatalst L2 LE2" ng-repeat="email in mindbody_emailAddresses">
                            <li style="width:300px;left:-30px;top:-80px;">{{email.emailAddress}}</li>
                        </ul>
                        <p ng-show="mindbody_emailAddresses.length == 0 && type == 'mindbody'" style="margin-top:-80px;">No contacts available</p>
                    </div>
                </div>
            </div>
              <div id="tab4" class="col-md-10 col-md-offset-2" class="hide">
                <div id="emailsubjectdiv" ng-controller="EmailListController">
                    <p class="header1">Email List:</p>
                    <input type="text" class="hideinputborder" id="email_list_name" name="email_list_name" placeholder="Enter Here"/> <br>
                    <div class="col-md-7 col-md-offset-0">
                        <label>Upload CSV</label><br><br>
                        <input type="file" class="fileUpload" id="fileUpload" name="fileUpload"><br>
                        <input class="button button--moema button--text-thick button--text-upper button--size-s" type="button" id="upload" value="Upload" onclick="upload()" /><br>
                    </div><br>
                    <div id="dvCSV"></div>
                    <textarea width="400" height="500" id="textArea"></textarea><br><br>
                    <input  id="emailSubjectContinueButton" type="button" class="button button--moema button--text-thick button--text-upper button--size-s" value="Update" ng-click="updateEmailList()">
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
