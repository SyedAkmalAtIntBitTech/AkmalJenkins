<%-- 
    Document   : emailautomate
    Created on : Oct 14, 2015, 2:56:27 PM
    Author     : Sandeep Kumar at IntBit Technologies.
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

   <title>Email Automation</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="css/emailautomationeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/emailautomation.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>        
        <script src="js/dashboard.js"></script>
        
  <style>

      div#editor {
          width: 100%;
          margin: auto;
          text-align: left;
          margin-top:5px;
      }
    .fr-wrapper{
      max-height:460px !important;              
      }
          
  </style>
         <script>
            $(document).ready(function (){
            $("#emlautomeditorcontainer").hide();
            $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
            } );
            var template_id = "";
            function validate(){
                var days = $("#days").val();
                var emaillisttext = $("#emaillist :selected").text();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                
                if (days === "0") {
                    alert("please select the day");
                    $("#days").focus();
                    return false;
                }
                if (emaillisttext === "") {
                    alert("please select the email list text");
                    $("#emaillisttext").focus();
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
            
            $scope.days = 10;
            /*
             * Bring all the email list from the database
             */
                $scope.showEmailList = function () {
                            
                    var emailids = {"update": "allEmailListNames"};
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=allEmailListNames'
                    }).success(function(data, status, headers, config) {
//                        alert(JSON.stringify(data.allEmailListNames));
                        $scope.emailLists = data.user;
                        $scope.emailLists_mindbody = data.mindbody;
                        if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                        
                    }).error(function(){
                        alert("problem fetching the data");
                    });
                    };
                
                /*
                 * Bring all the recuring email templates form the database
                 */
                 $scope.getEmailTemplates = function(){
                    if (validate()){
                        $("#emailautomationcontent").hide();
                        $("#emlautomeditorcontainer").show();
                        
                        $http({
                            method: 'GET',
                            url: getHost() + 'getAllRecuringEmailTemplates.do'
                        }).success(function(data, status){
                            alert(JSON.stringify(data));
                            $scope.recuring_email_templates = data;
                        }).error(function(){
                            alert("problem fetching the data");
                        });
                    }

                 };
                 
                 $scope.showHTMLData = function(html_data, id){
                        var $iframe = $('.fr-iframe');
                        $iframe.contents().find("body").append(html_data);
                        template_id = id;
//                     alert(html_data);
//                     $(".fr-iframe").empty();
////                     $(".fr-wrapper").appendChild("<div id='html_data'></div>")
//                     $(".fr-iframe").append(html_data);
                 };
                $scope.saveEmailAutomation = function(){
                    if (validate()){
                        
                        var days = $("#days").val();
                        var emaillist = $("#emaillist").val();
                        var subject = $("#subject").val();
                        var from_name = $("#from_name").val();
                        var reply_to_address = $("#reply_to_address").val();
                        var entity_id = 140;
                        var days = 10;
                        
                        var $iframe = $('.fr-iframe');
                        var html_data = $iframe.contents().find("html").html(); 
                        html_data = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">" + html_data + "</html>";
                        console.log(html_data);
                        alert(html_data);
                        var emailautomation = {"entity_id":entity_id, "template_id":template_id,
                                               "days":days, "emaillist":emaillist,
                                               "subject":subject, "from_name":from_name,
                                               "reply_to_address":reply_to_address,
                                               "html_data": html_data
                                              };
                        $http({
                            method: 'POST',
                            url: 'setEmailTemplateToRecuringAction.do',
                            headers: {'Content-Type':'application/json'},
                            data: JSON.stringify(emailautomation)
                        }).success(function (data, status, headers, config) {
                            $scope.categories = data;
                            if (data === "true") {
                                alert("details saved succesfully");
                            }else {
                                alert("problem saving the record");
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
           
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/mode/xml/xml.min.js"></script>
  <script src="js/froala_editor.min.js"></script>
  <script src="js/plugins/align.min.js"></script>
  <script src="js/plugins/code_view.min.js"></script>
  <script src="js/plugins/colors.min.js"></script>
  <script src="js/plugins/emoticons.min.js"></script>
  <script src="js/plugins/font_size.min.js"></script>
  <script src="js/plugins/font_family.min.js"></script>
  <script src="js/plugins/image.min.js"></script>
  <script src="js/plugins/image_manager.min.js"></script>
  <script src="js/plugins/line_breaker.min.js"></script>
  <script src="js/plugins/link.min.js"></script>
  <script src="js/plugins/lists.min.js"></script>
  <script src="js/plugins/paragraph_format.min.js"></script>
  <script src="js/plugins/paragraph_style.min.js"></script>
  <script src="js/plugins/table.min.js"></script>
  <script src="js/plugins/video.min.js"></script>
  <script src="js/plugins/url.min.js"></script>
  <script src="js/plugins/entities.min.js"></script>
  <script src="js/plugins/char_counter.min.js"></script>
  <script src="js/plugins/inline_style.min.js"></script>
  <script src="js/plugins/save.min.js"></script>

  <script>
    $(function(){
      $('#edit').froalaEditor({
        fullPage: true
      });
       $("#templatetab").click(function (){
                    $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
                });
    });
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
                                        <option value="{{days}}">{{days}}</option>
                                    </select>
                                    <script>
                                        $(function(){
                                            for(i=1; i<=31; i++){
                                            $('#days').append('<option value='+i+'>'+ i + '</option>');
                                            }
                                        });
                                    </script>
                                </li>
                                <li>
                                    <p class="daystxt fontpnr">days after they are added to</p>
                                </li>                                
                                <li>
                                    <select id="emaillist" class="emllstdrp fontpnr">
                                        <option value="0">-- Select --</option>
                                        <option  ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                        <option style="background:#fff;" ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>
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
                            <button type="submit" 
                                    class="emlautombtn button 
                                            button--moema 
                                            button--text-thick 
                                            button--text-upper 
                                            button--size-s" 
                                            ng-click="getEmailTemplates()">
                                Save</button>
                        </div>
                    </div>
                </div>
            </div>
                <div id="emlautomeditorcontainer">
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
<!--                            <div class="emleditorhead fontpnr">Froala Editor</div> -->
                            

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
                                            <div class="emledtrsavebtn">
                                                <input class="emailedtrsave fontpns 
                                                       button button--moema 
                                                       button--text-thick 
                                                       button--text-upper 
                                                       button--size-s" 
                                                       type="button" 
                                                       value="save" 
                                                       ng-click="saveEmailAutomation()">
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
    </body>
    
</html>