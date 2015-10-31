<%-- 
    Document   : emailautomate
    Created on : Oct 14, 2015, 2:56:27 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <title>Email Automation</title>
        <meta charset="UTF-8">
         <%@ include file="fonttypekit.jsp"%>
         
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/emailautomationeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/emailautomation.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/emailautomation.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        
        <script src="js/dashboard.js"></script>
        <script>
            $(document).ready(function (){
            $("#emlautomeditorcontainer").hide();
            $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
            } );
            function validate(){
                var days = $("#days").val();
//                var emaillisttext = $("#emaillist :selected").text();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                
                if (days === "0") {
                    alert("please select the day");
                    $("#days").focus();
                    return false;
                }
//                if (emaillisttext === "") {
//                    alert("please select the email list text");
//                    $("#emaillisttext").focus();
//                    return false;
//                }
                if (emaillist === "0") {
                    alert("please select the email list");
                    $("#emaillist").focus();
                    return false;
                }
                if (subject === "") {
                    alert("Enter the subject");
                    $("#subject").focus();
                    return false;
                }
                if (from_name == ""){
                    alert("Enter the from name");
                    $("#from_name").focus();
                    return false;
                }
                if (reply_to_address == ""){
                    alert("Enter the reply to address");
                    $("#reply_to_address").focus();
                    return false;
                }
                
                return true;
            }
            
            function emailautomation($scope, $http){
                
                $scope.showEmailList = function () {
                            
//                            $(".emaillist").show();
//                            $("#email_list_name").hide();
                    var emailids = {"update": "allEmailListNames"};
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=allEmailListNames'
                    }).success(function(data, status, headers, config) {
//                        alert(JSON.stringify(data.allEmailListNames));
                        $scope.emailLists = data.allEmailListNames;
//                        $scope.emailLists_mindbody = data.mindbody;
                        if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                        
                    });
                };
                
                $scope.saveEmailAutomation = function(){
                    if (validate()){
                        
                        var days = $("#days").val();
                        var emaillist = $("#emaillist").val();
                        var subject = $("#subject").val();
                        var from_name = $("#from_name").val();
                        var reply_to_address = $("#reply_to_address").val();                        
                        $("#emailautomationcontent").hide();
                        $("#emlautomeditorcontainer").show();
                        var emailautomation = {"days":days, "emaillist":emaillist,
                                                "subject":subject, "from_name":from_name,
                                                "reply_to_address":reply_to_address
                                              };
                        $http({
                            method: 'POST',
                            url: 'SetEmailAutomation',
                            headers: {'Content-Type':'application/json'},
                            data: JSON.stringify(emailautomation)
                        }).success(function (data, status, headers, config) {
                            $scope.categories = data;
                            if (data === error) {
                                alert(data);
                            }
                        }).error(function (data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                    }
                };
            }
            
        </script> 
         <jsp:include page="basejsp.jsp"/>
         
    </head>
    <body ng-app>
        <div class="row" ng-controller="emailautomation">
            <div class="col-md-1 col-lg-1 col-sm-2 halfcol" >
                    <jsp:include page="leftmenu.html"/>
                </div>
                <div id="emailautomationcontent">
                <div class="col-md-11 col-lg-11 col-sm-10 col-md-offset-2 col-lg-offset-2">
                    <div class="row">
                        <div class="col-sm-10 col-lg-12 col-md-12">
                            <div class="emlautoact fontpnr">Create a trigger for this email automation action:</div>
                            <div class="emlautocont">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</div>
                        </div>
                    </div>
                    <div class="row " ng-init="showEmailList()">
                        <div class="col-sm-10 col-lg-12 col-md-12 ">
                            <ul class="eventlist autopadlft">
                                <li>
                                    <div class="sndemlrecp fontpnr">Send an email to a recipient</div>
                                </li>
                                <li>
                                    <select id="days" class="eventsel fontpnr">
                                        <option value="0">0</option>
                                    </select>
                                </li>
                                <li>
                                    <p class="daystxt fontpnr">days after they are added to</p>
                                </li>                                
                                <li>
                                    <select id="emaillist" class="emllstdrp fontpnr">
                                        <option value="0">-- Select --</option>
                                        <option  ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                        <!--<option style="background:#fff;" ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>-->
                                    </select>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 col-lg-12 col-md-12">
                         <div class="sublineinp fontpnr">Enter a subject line:</div>
                           <div class="group ">
                                <input id="subject" class="form-control subinp fontpnr" type="text" required  placeholder="Subject Line">
                           </div>
                       </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 col-lg-12 col-md-12">
                         <div class="fromnminp fontpnr">Enter a from name</div>
                           <div class="group ">
                                <input id="from_name" class="form-control subinp fontpnr" type="text" required  placeholder="From Name">
                           </div>
                       </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 col-lg-12 col-md-12">
                         <div class="repltoaddinp fontpnr">Enter a reply-to-address:</div>
                           <div class="group ">
                                <input id="reply_to_address" class="form-control subinp fontpnr" type="text" required  placeholder="Reply-to-address">
                           </div>
                       </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 col-lg-12 col-md-12 padlft">
                            <button  type="submit"  class="emlautombtn button 
                                                           button--moema 
                                                           button--text-thick 
                                                           button--text-upper 
                                                           button--size-s" 
                                                           ng-click="saveEmailAutomation()">
                                Save</button>
                        </div>
                    </div>
                </div>
            </div>
                <div id="emlautomeditorcontainer">
                    <div class="row" ng-controller="MyController">
            <div class="col-sm-7 col-md-7 col-lg-7">
                        <div class="row">
                            <div class="col-sm-12 col-md-12 col-lg-12 bgcolor"> 
                        <style>
                            #edit{
                        position: relative;
                        top:0px;
                        font-family:"proxima-nova";
                        font-weight:500;
                        left: 0em; 
                        color: #2D4444;

                        }
                        </style>
                        
<!--                            <div class="emleditorhead fontpnr">Froala Editor</div> -->
                            <div id="editor">
                                <div id='edit' style="margin-top:0px;">
                                </div>
                            </div>
<!--                            <div class="framediv">
                                <iframe class="frm" src=""></iframe>
                            </div>    -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-lg-6 col-sm-6">
                                <div class="btmdiv">
                                    <div class="row">
                                        <div class="col-lg-7 col-md-7 col-sm-7">
                                            <div class="editemail fontpnr">Edit this Email Automation Action</div>
                                        </div>   
                                        <div class="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1 col-md-offset-1">
                                            <div class="mobileprev fontpnr">Mobile Preview</div>
                                        </div>
                                        <div class="col-lg-1 col-md-1 col-sm-1">
                                            <div class="emledtrsavebtn"><input class="emailedtrsave fontpns button button--moema button--text-thick button--text-upper button--size-s" type="button" value="save"></div>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                        </div> 
            </div>
            <div class="col-sm-3 col-md-3 col-lg-3">
                <div class="blockselection">     
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">
                            <div class="selblock fontpnr">Select a Template</div>
                        </div>
<!--                        <div class="col-md-6 col-lg-6 col-sm-6">
                            <div class="addblkdiv"><input class="addblkbtn fontpns " type="button" value="Add Block"></div>
                        </div>-->
                    </div>
                    <div class="row">
                        <div class="selblklinediv"><hr class="selblkline"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-sm-12">
                            <ul id="blklist" class="blocklist fontpnr">
                                <li> 
                                    <div >New Client Intro - First Email</div>
                                </li>
                               
                            </ul>
<!--                            <ul id="stylelist" class="blocklist fontpnr">
                                <li ng-repeat="styles in datalistsstyles">
                                    <div><img id="{{styles.id}}" class="img-responsive lookchooser5 ptr" src="/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name={{styles.image_file_name}}"  onclick="showText('{{styles.id}}','{{styles.layout_file_name}}')" width="275" /></div>
                                </li>
                               
                            </ul>-->
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-1 col-md-1 col-lg-1">
                <div class="blockstyletab">      
                    <ul class="righttabs fontpnr">
<!--                        <li id="templatetab">
                            <image src='images/sidebar/Icons_styleButton.svg' class="styleimg"/>
                            <p>STYLE</p>
                        </li>-->
                        <li id="templatetab">
                            <image src='images/sidebar/Icons_blockButton.svg' class="blockimg"/>
                            <p>TEMPLATE</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
                </div>
        </div>
                <script>
                $(function(){
                    for(i=1; i<=31; i++){
                    $('#days').append('<option value='+i+'>'+ i + '</option>');
                    }
                });
                 $("#templatetab").click(function (){
                    $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
                });
               
                </script>
    </body>
</html>
