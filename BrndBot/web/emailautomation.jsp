<%-- 
    Document   : emailautomate
    Created on : Oct 14, 2015, 2:56:27 PM
    Author     : IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/froala_editor.css">
  <link rel="stylesheet" href="css/froala_style.css">
  <link rel="stylesheet" href="css/plugins/code_view.css">
  <link rel="stylesheet" href="css/plugins/colors.css">
  <link rel="stylesheet" href="css/plugins/emoticons.css">
  <link rel="stylesheet" href="css/plugins/image_manager.css">
  <link rel="stylesheet" href="css/plugins/image.css">
  <link rel="stylesheet" href="css/plugins/line_breaker.css">
  <link rel="stylesheet" href="css/plugins/table.css">
  <link rel="stylesheet" href="css/plugins/char_counter.css">
  <link rel="stylesheet" href="css/plugins/video.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">
  <link rel="stylesheet" href="css/pikaday.css">
  <link rel="stylesheet" href="css/datepickerpikaday.css">
  <script src="js/pikaday.js"></script>

   <title>Email Automation</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
        <link href="css/emailautomationeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/emailautomation.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
   
  <style>

      div#editor {
          width: 100%;
          margin: auto;
          text-align: left;
          margin-top:5px;
      }
    .fr-wrapper{
        max-height:85% !important;              
      /*max-height:570px !important;*/              
      }
      
      .fr-iframe{
          min-height:90%;
         /*min-height: 570px;*/
      }    
  </style>
<%! 
    String entity_id = "";
    String program_id = "";
    String type = "";
%>        
<%
    if (request.getParameter("entity_id") != null){
        entity_id = request.getParameter("entity_id");
    }
    if (request.getParameter("program_id") != null){
        program_id = request.getParameter("program_id");
    }
    if (request.getParameter("type") != null){
        type = request.getParameter("type");
    }
%>
<script src="js/angular.min.js"></script>
<script>
    
    var emails = "";
    var schedule_time = "";
    var schedule_date = "";
    var email_list_name = "";
    var template_id = 0;
    var days = 0;
    var entity_no_email_template = "";
    var entity_id = 0;
    var type = "";
    var program_id = "";
    
//    setTimeout(
//        function() 
//        {
          //do something special
         // alert("delay");
          //$("#select option").filter(".a0").attr('selected','selected');
    var entity_id = '<%= entity_id %>';
    var type = '<%= type %>';
    var program_id = '<%= program_id %>';

//        }, 1000);
        
    
    function emailautomation($scope, $http){

        $scope.getEntityDetails = function (){
            
            var entity_details = {"entity_id": entity_id};

            $http({
                method: 'POST',
                url: getHost() + 'getRecuringEntity.do',
                headers: {'Content-Type':'application/json'},
                data: JSON.stringify(entity_details)
            }).success(function(data, status){
                $scope.entity_details = data;
                if (data.recuring_email_template_id != null){
                    template_id = data.recuring_email_template_id;
                }else {
                    entity_no_email_template = "true";
                }
                html_data = data.recuring_email_body;
                $('#edit').froalaEditor('html.set',''+html_data+'');
                
                $scope.showEmailList();
                showEmailListName(data.recuring_email_email_list_name);
                days = data.recuring_email_days;
            }).error(function(){
                alert("problem fetching the data");
            });
        };
        /*
        * Bring all the email list from the database
        */
        $scope.showEmailList = function () {

            var emailids = {"update": "allEmailListNames"};
            $http({
                method: 'GET',
                url: getHost() + 'GetEmailLists?update=allEmailListNames'
            }).success(function(data, status, headers, config) {
                $scope.emailLists_user = data.user;
                $scope.emailLists_mindbody = data.mindbody;
            }).error(function(){
                alert("problem fetching the data");
            });
        };

        /*
         * Bring all the recuring email templates form the database
         */
         $scope.getEmailTemplates = function(){
               
                $("#emailautomationcontent").hide();
                $("#emlautomeditorcontainer").show();
                $http({
                    method: 'GET',
                    url: getHost() + 'getAllRecuringEmailTemplates.do'
                }).success(function(data, status){
                    $scope.recuring_email_templates = data;
                }).error(function(){
                    alert("problem fetching the data");
                });

        };

        $scope.addUpdateRecuringAction = function(){
            if (validate()){
                var days = $("#days").val();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();

                var till_date = $("#datepicker").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
//                        var schedule_time=$("#timepicker1").val();
                var till_date_epoch = Date.parse(till_date);
//                        var schedule_time_epoch = Date.parse(schedule_time);
//                        alert(schedule_time_epoch);
                var $iframe = $('.fr-iframe');
                var html_data = $('#edit').froalaEditor('html.get');
                
//                var html_data = $iframe.contents().find("html").html();
//                html_data = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">" + html_data + "</html>";

                if ( type == 'add'){
                    var recuring_action = {
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };

                    $http({
                        method: 'POST',
                        url: 'addRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if (data === "true") {
                            alert("details saved succesfully");
                            window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
                        }else {
                            alert("problem saving the record");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                }else if((type == 'template') && (entity_no_email_template == "true")){

                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };

                    $http({
                        method: 'POST',
                        url: 'addupdateRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if((data == "true") && (entity_no_email_template == "true")) {
                            alert("details saved succesfully");
                            $("#emailautomationcontent").hide();
                            entity_no_email_template = "false";
                            $("#emlautomeditorcontainer").show();
                        }else {
                            alert("problem saving the record");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                }else if ((type == 'edit') && (entity_no_email_template == "true")){
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };

                    $http({
                        method: 'POST',
                        url: 'addupdateRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if ((data === "true")) {
                            alert("details saved succesfully");
                            window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
                        }else {
                            alert("problem saving the record");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                    
                }else if((type == 'edit')||(type == 'template')){
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "template_id" : template_id, "html_data": html_data,
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({
                        method: 'POST',
                        url: 'updateRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if ((data === "true")) {
                            alert("details saved succesfully");
                            window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
                        }else {
                            alert("problem saving the record");
                        }
                        
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                }


            }
        };

        $scope.showHTMLData = function(html_data, id){
                var $iframe = $('.fr-iframe');
//                         $(".fr-iframe").empty();
                $('#edit').froalaEditor('html.set',''+html_data+'');
//                $iframe.contents().find("body").empty();
//                $iframe.contents().find("body").append(html_data);
                template_id = id;
        };

    }

</script> 

<script>

   $(document).ready(function (){
       $(".row").css("display","block");
       $("#emlautomeditorcontainer").hide();
       $("#templatetab").css("background-color","#ffffff").css("color","#19587c");

       $("#emaillist").change(function () {

           var List_name = $("#emaillist").val();
           $.ajax({
               url: getHost() + "GetEmailLists",
               method: 'POST',                    
               data: {
                   update: "emailsForEmailList",
                   list_name: List_name
               },
               success: function(result){
                   var i = 0;
                   emails = result.user_emailAddresses;
               }
           });

       });

   if (type == 'edit'){
       var entity_details = {"entity_id": entity_id};                    
       $("#emailautomationcontent").show();
       $("#emlautomeditorcontainer").hide();

       //                
//                $.ajax({
//                    url: getHost() + "getRecuringEntity.do",
//                    method: 'POST',
//                    dataType: 'json',
//                    contentType: 'application/json',
//                    mimeType: 'application/json',
//                    data: JSON.stringify(entity_details),
//                    success: function(result){
//                        
//                        $("#days").val(result.recuring_email_days);
////                        $("#emaillist :selected").text(result.recuring_email_email_list_name);
//                        
//        //                        $("#emaillist").val();
//                        $("#subject").val(result.recuring_email_subject);
//                        $("#from_name").val(result.recuring_email_from_name);
//                        $("#reply_to_address").val(result.recuring_email_reply_to_email_address);
//                        $("#recuring_email_title").val(result.recuring_email_title);
//                        $("#recuring_email_description").val(result.recuring_email_description);
////                        $("#datepicker").val(result.recuring_email_time);
//
//                        $("#emaillist").change();                        
//                        angular.element(document.getElementById('emailautomation')).scope().setDateNTime(result.recuring_email_time, result.recuring_email_till_date, result.recuring_email_email_list_name);
//                    }
//                });

                
            }else if (type == 'template'){
                
                    setTimeout(
                        function() 
                        {
                          //do something special
                         // alert("delay");
                          //$("#select option").filter(".a0").attr('selected','selected');
                        
                        if (validate()){
                            $("#emailautomationcontent").hide();
                            $("#emlautomeditorcontainer").show();
                            entity_no_email_template = "false";
                        }else {
                            
                            entity_no_email_template = "true";
                            $("#emailautomationcontent").show();
                            $("#emlautomeditorcontainer").hide();
                        }
                    }, 1000);
                
                
            }else if (type == 'add'){
                $("#emailautomationcontent").show();
                $("#emlautomeditorcontainer").hide();
            }
            });
            function validate(){

                var emlval = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
                
                var days = $("#days").val();
                var emaillisttext = $("#emaillist :selected").text();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();

                
                
                var till_date = $("#datepicker").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
                if (recuring_email_title === ""){
                    alert("Enter the title");
                    $("#recuring_email_title").focus();
                    return false;
                }
                
                if (recuring_email_description === ""){
                    alert("Enter the description");
                    $("#recuring_email_description").focus();
                    return false;
                }
                if (days === "0") {
                    alert("please select the day");
                    $("#days").focus();
                    return false;
                }
                if (emaillisttext === "") {
                    alert("please select the email list text");
                    emaillisttext.focus();
                    return false;
                }
                 if (schedule_time === ""){
                    alert("select the time");
                    $("#timepicker1").focus();
                    return false;
                }
                 if (till_date === ""){
                    alert("till date not selected,please select the date");
                    $("#datepicker").focus();
                    return false;
                }
               
//                if (emaillist === "0") {
//                    alert("please select the email list");
//                    $("#emaillist").focus();
//                    return false;
//                }

                if (subject === "") {
                    alert("Enter the subject");
                    $("#subject").focus();
                    return false;
                }
                if (from_name === ""){
                    alert("Enter the from name");
                    $("#from_name").focus();
                    return false;
                }        
                
                if((reply_to_address === "")||(!emlval.test(reply_to_address))){
                    alert("Enter Valid reply-to-address ");
                    $("#reply_to_address").focus();
                    return false;
                }
                return true;
            }

</script>
           
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/mode/xml/xml.min.js"></script>
  <script type="text/javascript" src="js/froala_editor.min_Email.js" ></script>
        <script type="text/javascript" src="js/plugins/align.min.js"></script>
        <script type="text/javascript" src="js/plugins/colors.min_Editor.js" ></script>
        <script type="text/javascript" src="js/plugins/font_size.min.js"></script>
        <script type="text/javascript" src="js/plugins/font_family.min.js"></script>
        <script type="text/javascript" src="js/plugins/image.min_editor.js"></script>
        <script type="text/javascript" src="js/plugins/file.min.js"></script>
        <script type="text/javascript" src="js/plugins/image_manager.min_editor.js"></script>
        <script type="text/javascript" src="js/plugins/table.min_editor.js"></script>
        <script type="text/javascript" src="js/plugins/url.min.js"></script>
        <script type="text/javascript" src="js/plugins/entities.min.js"></script>
        <script type="text/javascript" src="js/plugins/inline_style.min.js"></script>
        <script type="text/javascript" src="js/plugins/save.min.js"></script>
        <script type="text/javascript" src="js/plugins/quote.min.js"></script>

  <script>
    $(function(){
      $('#edit').froalaEditor({
        key: FroalaLicenseKey
      });
       $("#templatetab").click(function (){
       $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
       });
    });
  </script>
  
<jsp:include page="basejsp.jsp"/>
      
</head>
<body ng-app>
   <div id="emailautomation" class="row" ng-controller="emailautomation" style="display: none;">
           <div class="col-md-1 col-lg-1 col-sm-2 halfcol" >
               <jsp:include page="leftmenu.html"/>
           </div>
           <div id="emailautomationcontent" ng-init="getEntityDetails();">
           <div class="col-md-11 col-lg-11 col-sm-10 col-md-offset-2 col-lg-offset-2">

               <div class="row">
               <div class="row">
                   <div class="col-sm-12 col-lg-12 col-md-12">
                    <div class="sublineinp fontpnr">Enter a name for this Recuring Email Automation:</div>
                      <div class="group">
                           <input id="recuring_email_title" 
                                  class="form-control subinp fontpnr" 
                                  type="text" required  
                                  placeholder="Name" value="{{entity_details.recuring_email_title}}">
                      </div>
                  </div>
               </div>
               <div class="row">
                   <div class="col-sm-12 col-lg-12 col-md-12">
                    <div class="sublineinp brfdesc fontpnr">Enter a brief description:</div>
                      <div class="group ">
                           <input id="recuring_email_description" 
                                  class="form-control subinp fontpnr" 
                                  type="text" required  
                                  placeholder="Description" value="{{entity_details.recuring_email_description}}">
                      </div>
                  </div>
               </div>
               <div class="row">
                   <div class="col-sm-10 col-lg-12 col-md-12">
                       <div class="emlautoact fontpnr">Create a trigger for this email automation action:</div>
                       <!--<div class="emlautocont"></div>-->
                   </div>
               </div>
                   <div class="col-sm-10 col-lg-12 col-md-12 ">
                       <ul class="eventlist autopadlft">
                           <li>
                               <div class="sndemlrecp fontpnr">Send an email to a recipient</div>
                           </li>                                
                           <li>
                           <select id="days" class="eventsel fontpnr"></select>
                               <script>

                               </script>
                           </li>
                           <li>
                               <p class="daystxt fontpnr">days after they are added to</p>
                           </li>                                
                           <li>
                               <select id="emaillist" name="emaillist" class="emllstdrp fontpnr">
                                   <option value="0">-- Select --</option>
                                   <option ng-repeat ="Lists in emailLists_user" value="{{Lists}}">{{Lists}}</option>
                                   <option ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>
                               </select>
                           </li>
                       </ul>
                   </div>
               </div>
               <div class="row">
                   <ul class="eventlist autopadlft">
                       <li>
                           <div class="selatime fontpnr">Select a time:</div>
                       </li>
                       <li>
                           <input id="timepicker1" readonly 
                                  type="text" 
                                  name="timepicker1" 
                                  class="timpkr form-control fontpnr" 
                                  value="{{entity_details.recuring_email_time | date:'hh : mm : a'}}" /> 
                           <script src="js/timepicki.js" type="text/javascript"></script>
                           <script>
                               $('#timepicker1').timepicki({
                                   show_meridian:true,
                                   min_hour_value:0,
                                   max_hour_value:12,
                                   step_size_minutes:01,
                                   overflow_minutes:true,
                                   increase_direction:'up',
                                   disable_keyboard_mobile: true
                               });
                           </script>
                       </li>
                       <li>
                           <p class="daystxt fontpnr">Select a till date:</p>
                       </li>
                       <li>
                           <input type="text" readonly  name="datepicker" 
                                  id="datepicker"  
                                  class="datepkr form-control fontpnr" 
                                  value="{{entity_details.recuring_email_till_date| date:'EEE MMM dd yyyy'}}" />                                        
                           <script>
                               var picker = new Pikaday(
                               {
                                   field: document.getElementById('datepicker'),
                                   firstDay: 1,
                                   minDate: new Date(2000, 0, 1),
                                   maxDate: new Date(2050, 12, 31),
                                   yearRange: [2000,2050]
                               });
                           </script>
                       </li>
                   </ul>
               </div>
               <div class="row">

               </div>                    
               <div class="row">
                   <div class="col-sm-12 col-lg-12 col-md-12">
                    <div class="sublineinp fontpnr">Enter a subject line:</div>
                      <div class="group">
                           <input id="subject" 
                                  class="form-control subinp fontpnr" 
                                  type="text" 
                                  required  
                                  placeholder="Subject Line" 
                                  value="{{entity_details.recuring_email_subject}}">
                      </div>
                  </div>
               </div>
               <div class="row">
                   <div class="col-sm-12 col-lg-12 col-md-12">
                    <div class="fromnminp fontpnr">Enter a from name:</div>
                      <div class="group">
                           <input id="from_name" 
                                  class="form-control subinp fontpnr" 
                                  type="text" 
                                  required  placeholder="From Name" 
                                  value="{{entity_details.recuring_email_from_name}}">
                      </div>
                  </div>
               </div>
               <div class="row">
                   <div class="col-sm-12 col-lg-12 col-md-12">
                    <div class="repltoaddinp fontpnr">Enter a reply-to-address:</div>
                      <div class="group">
                           <input id="reply_to_address" 
                                  class="form-control subinp fontpnr" 
                                  type="text" 
                                  required  placeholder="Reply-to-address" 
                                  value="{{entity_details.recuring_email_reply_to_email_address}}">
                      </div>
                  </div>
               </div>
               <div class="row">
                   <div class="col-sm-12 col-lg-12 col-md-12 padlft">
                       <button type="submit" 
                               class="emlautombtn button 
                                       button--moema 
                                       button--text-thick 
                                       button--text-upper 
                                       button--size-s" 
                                       ng-click="addUpdateRecuringAction()">
                           Save</button>
                   </div>
               </div>
           </div>
           </div>
           <div id="emlautomeditorcontainer" ng-init="getEmailTemplates()">
               <div class="row">
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

                   <div id="editor">
                       <div id='edit' style="margin-top:0px;">
                       </div>
                   </div>
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
                                       <div class="emledtrsavebtn">
                                           <input class="emailedtrsave fontpns 
                                                  button button--moema 
                                                  button--text-thick 
                                                  button--text-upper 
                                                  button--size-s" 
                                                  type="button" 
                                                  value="save" 
                                                  ng-click="addUpdateRecuringAction()">
                                       </div>
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
                           <li ng-repeat="email_template in recuring_email_templates"> 
                               <div ng-click="showHTMLData(email_template.html_data, email_template.template_id)">{{email_template.template_name}}</div>
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

    function showEmailListName(email_list_name){
        setTimeout(
        function() 
        {
          //do something special
         // alert("delay");
          //$("#select option").filter(".a0").attr('selected','selected');
          $('#emaillist option[value='+email_list_name+']').attr("selected", "selected");
          $("#emaillist").change();

            for(i=1; i<=31; i++){
                if ( i == days){
                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
                }else {
                    $('#days').append('<option value='+i+'>'+ i + '</option>');
                }

            }
        }, 500);

    }

</script>                
</body>
    
</html>