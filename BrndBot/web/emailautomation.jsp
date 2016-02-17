<%-- 
    Document   : emailautomation_new
    Created on : Jan 11, 2016, 12:31:58 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/pikaday.css"></link>
    <link rel="stylesheet" href="css/datepickerpikaday.css"></link>
    <script src="js/pikaday.js"></script>   
    <title>Email Automation</title>
  
    <%@ include file="fonttypekit.jsp"%>
    <jsp:include page="basejsp.jsp"/>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
    <link href="css/emailautomationeditor.css" rel="stylesheet" type="text/css"/>
    <!--<link href="css/emailautomation.css" rel="stylesheet" type="text/css"/>-->
    <script src="js/configurations.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay_new.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link href="css/emailautomationeditor.css" rel="stylesheet" type="text/css"/>
    <style>
        .arrow_top,#emlautomeditorcontainer,#emailautomationcontent,#emailautomation,#textdiv,#myModal,#editpreviewtemplatebottom{display:none;}
    </style>
    <%! 
        String entity_id = "";
        String program_id = "";
        String type = "";
        String daysleft="";
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
    
    $(document).ready(function () {
//         for(var i=1; i<=31; i++){
//                if ( i == days){
//                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
//                }else {
//                    $('#days').append('<option value='+i+'>'+ i + '</option>');
//                }
//
//            }
    $("#templatetab").click(function (){
           $("#textdiv").hide();
           $("#templatediv").show();
           $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
           $("#texttab").css("background-color","transparent").css("color","#19587c");
       });
       $("#texttab").click(function (){
           $("#templatediv").hide();
           $("#textdiv").show();
           $("#texttab").css("background-color","#ffffff").css("color","#19587c");
           $("#templatetab").css("background-color","transparent").css("color","#19587c");
       });  
    });
    
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
        $("#back").click(function (){
            window.open(getHost() + 'marketingprogramactions.jsp?past=0&program_id='+program_id+'&program_date='+program_end_date, "_self");
        });
        $scope.getEntityDetails = function (){
            $scope.showEmailList();
/* uncomment the function to check the userPreferences */            
//            $scope.checkUserPreferences();
//            if (entity_id != "0"){
            var entity_details = {"entity_id": entity_id};
                $http({
                    method: 'POST',
                    url: getHost() + 'getRecuringEntity.do',
                    headers: {'Content-Type':'application/json'},
                    data: JSON.stringify(entity_details)
                }).success(function(data, status){
//                    alert(JSON.stringify(data.recuring_email_time));
                    $scope.entity_details = data;
                    days = data.recuring_email_days;
                    $("#emaillist").val(email_list_name);
                    email_list_name=data.recuring_email_email_list_name;
                    $('#days').val(days);
                   setTimeout(function (){$("#emaillist").val(email_list_name);},10);
                    if (data.recuring_email_template_id != null){
                        template_id = data.recuring_email_template_id;
                        entity_no_email_template = "false";
                    }else {
                        entity_no_email_template = "true";
                    }
                    html_data = data.recuring_email_body;
                    $('#edit').froalaEditor('html.set',''+html_data+'');
//                    alert(data.recuring_email_email_list_name);
                    showEmailListName(data.recuring_email_email_list_name);
                    
                }).error(function(){
                    alert("Problem fetching the data!");
                });
                 
//            }
        };
        /*
        * Bring all the email list from the database
        */
       $scope.checkUserPreferences = function(){
           
            $http({
                method: 'GET',
                url: getHost() + 'getUserPreferences.do'
            }).success(function(data, status, headers, config) {
                if (data != ""){
                    alert("Please enter from address and reply to email address in email settings.");
                }
            }).error(function(){
                alert("Problem fetching the data!");
            });
           
       };
       
        $scope.showEmailList = function () {
            

            var emailids = {"update": "allEmailListNames"};
            $http({
                method: 'GET',
                url: getHost() + 'GetEmailLists?update=allEmailListNames'
            }).success(function(data, status, headers, config) {
                $scope.emailLists_user = data.user;
                $scope.emailLists_mindbody = data.mindbody;
            }).error(function(){
                alert("Problem fetching the data!");
            });
        };

        /*
         * Bring all the recuring email templates from the database
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
                    alert("Problem fetching the data!");
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
                var program_end_date=$("#program_end_date").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
//                        var schedule_time=$("#timepicker1").val();
                var till_date_epoch = Date.parse(till_date);
                var schedule_time_epoch = Date.parse(schedule_time);
//                var $iframe = $('.fr-iframe');
                var html_data = $('#edit').froalaEditor('html.get');
//                var html_data = $iframe.contents().find("html").html();
//                html_data = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">" + html_data + "</html>";
//                alert(emails);
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
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'marketingprogramactions.jsp?program_id='+ program_id + '&past=0&program_date='+program_end_date, "_self");
                        }else {
                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
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
                            alert("Details saved succesfully.");
                            $("#emailautomationcontent").hide();
                            entity_no_email_template = "false";
                            $("#emlautomeditorcontainer").show();
                        }else {
                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
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
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'marketingprogramactions.jsp?program_id='+ program_id + '&past=0&program_date='+program_end_date, "_self");
                        }else {
                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                    
                }else if(((type == 'template') && (entity_no_email_template == 'false')) 
                        || ((type = 'edit') && (entity_no_email_template == 'false'))){
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
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program_id+'&past=0&program_date='+program_end_date, "_self");
                        }else {
                            alert("Problem saving the record!");
                        }
                        
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                }
            }
            
             function showEmailListName(email_list_name){
                    setTimeout(function() 
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
       for(var i=1; i<=31; i++){
                if ( i == days){
                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
                }else {
                    $('#days').append('<option value='+i+'>'+ i + '</option>');
                }

            }
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
       $("#emailautomation").hide();
       $("#editpreviewtemplatebottom").hide();
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
                            $("#emailautomation").show();
                            $("#emlautomeditorcontainer").show();
                            $("#editpreviewtemplatebottom").show();
                            entity_no_email_template = "false";
                        }else {
                            entity_no_email_template = "true";
                            $("#emailautomationcontent").show();
                            $("#emlautomeditorcontainer").hide();
                            $("#emailautomation").hide();
                            $("#editpreviewtemplatebottom").hide();
                        }
                    }, 1000);
                
                
            }else if (type == 'add'){
                $("#emailautomationcontent").show();
                $("#emlautomeditorcontainer").hide();
                $("#emailautomation").hide();
                $("#editpreviewtemplatebottom").hide();
            }
            
            
            });
            function validate(){

                var emlval = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
                
                var days = $("#days").val();
//                var emaillisttext = $("#emaillist :selected").text();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();
                
                var till_date = $("#datepicker").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
                if (recuring_email_title === ""){
                    alert("Enter the title.");
                    $("#recuring_email_title").focus();
                    return false;
                }
                
                if (recuring_email_description === ""){
                    alert("Enter the description.");
                    $("#recuring_email_description").focus();
                    return false;
                }
                if (days === "0") {
                    alert("Please select the day.");
                    $("#days").focus();
                    return false;
                }
//                if (emaillisttext === "") {
//                    alert("Please select the email list text.");
//                    emaillisttext.focus();
//                    return false;
//                }
                if (schedule_time === ""){
                    alert("Select the time.");
                    $("#timepicker1").focus();
                    return false;
                }
                if (till_date === ""){
                    alert("Till date not selected! Please select the date.");
                    $("#datepicker").focus();
                    return false;
                }
               
                if (emaillist === "0") {
                    alert("Please select the email list.");
                    $("#emaillist").focus();
                    return false;
                }

                if (subject === "") {
                    alert("Enter the subject.");
                    $("#subject").focus();
                    return false;
                }
                if (from_name === ""){
                    alert("Enter the from name.");
                    $("#from_name").focus();
                    return false;
                }        
                
                if((reply_to_address === "")||(!emlval.test(reply_to_address))){
                    alert("Please Enter Valid reply-to-address.");
                    $("#reply_to_address").focus();
                    return false;
                }
                return true;
            }


   

</script> 
    <!-----------------------EMAIL AUTOMATION EDITOR HEADER------------------>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"/>
  <link rel="stylesheet" href="css/froala_editor.css"/>
  <link rel="stylesheet" href="css/froala_style.css"/>
  <link rel="stylesheet" href="css/plugins/code_view.css"/>
  <link rel="stylesheet" href="css/plugins/colors.css"/>
  <link rel="stylesheet" href="css/plugins/emoticons.css"/>
  <link rel="stylesheet" href="css/plugins/image_manager.css"/>
  <link rel="stylesheet" href="css/plugins/image.css"/>
  <link rel="stylesheet" href="css/plugins/line_breaker.css"/>
  <link rel="stylesheet" href="css/plugins/table.css"/>
  <link rel="stylesheet" href="css/plugins/char_counter.css"/>
  <link rel="stylesheet" href="css/plugins/video.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <link href="css/emailautomation.css" rel="stylesheet" type="text/css"/>
  <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
  <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
  <script src="js/configurations.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/mode/xml/xml.min.js"></script>
  <script type="text/javascript" src="js/froala_editor.min_editor.js" ></script>

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
  <script>
     var rendomIframeFilename="";
       $(document).ready(function () {
            rendomIframeFilename=event.timeStamp;
        });
    
        function show(id) {
                var getId = id;
                var dynamicStyle, dynamicWidth, dynamicHeight;
                var imageUrl = "images/Phone.svg";
                var id = '#dialog';
                //Get the screen height and width
                var maskHeight = $(document).height();
                var maskWidth = $(window).width();
                //Set heigth and width to mask to fill up the whole screen
                $('#mask').css({'width':maskWidth, 'height':maskHeight});
                //transition effect
                $('#mask').fadeIn(500);
                $('#mask').fadeTo("slow", 0.95);
                //Get the window height and width
                var winH = $(window).height();
                var winW = $(window).width();
                //Set the popup window to center
                $(id).css('top', winH / 2 - $(id).height() / 2);
                $(id).css('left', winW / 2 - $(id).width() / 2);
                //transition effect
                $(id).fadeIn(2000);
                //if close button is clicked
                $('.window .close').click(function (e) {
        //Cancel the link behavior
                e.preventDefault();
                        $('#mask').hide();
                        $('.window').hide();
                });
                //if mask is clicked
                $('#mask').click(function () {
                    $(this).hide();
                    $('.window').hide();
                });

                $.ajax({
                    url: getHost() + "PreviewServlet",
                    method: "post",
                    data: {
                        htmlString: $('#edit').froalaEditor('html.get'),//$(".fr-element").html(),
                        iframeName: rendomIframeFilename
                      },
                    success: function (responseText) {
//                                        alert(responseText);
                     if(getId === "iphone"){
                        $('.window').css("top","110px");
                        dynamicWidth="275";
                        dynamicHeight="439";

                        $(".window").empty();
                        $(".window").append("<div id=imageDivPopup style='width:"+dynamicWidth+"px;height:"+dynamicHeight+"px;'></div>");
                        $("#imageDivPopup").css("background-image","url("+imageUrl+")").css("background-size","100% 100%");
//                                        $("#imageDivPopup").append("<div style='width:"+(dynamicWidth-20)+"px;height:"+(dynamicHeight-60)+"px;margin-left:10px;position:relative;top:28px;overflow:scroll;'>"+responseText+"</div>");
                        $("#imageDivPopup").append("<iframe style='width:320px;height:509px;position:relative;top:-47px;left:-22px;-webkit-transform: scale(0.696);background-color:#FFF;' src='/BrndBot/DownloadHtmlServlet?file_name="+rendomIframeFilename+".html'></iframe>");

                        $('.window').show();
                    }
               }
            }); 
            
    }       
                    
               
</script>     
         <%!
            String program_date="";
    %>
    <%
        program_date = request.getParameter("program_date");
    %>
</head>    

<body ng-app ng-controller="emailautomation">
    <input type="hidden" name="program_end_date" id="program_end_date" value="<%= program_date %>"/>
     <div id="boxes">
            <div id="dialog" class="window">
            </div>
            <div id="mask"></div>
        </div>
    <!--SideNav-->
    <div class="content-main" >
    <%@include file="navbarv2.jsp" %>
    <!--Top Nav-->   
    <div id="emailautomationcontent">
       <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
           <div class="exit-button-detail" id="back">
                   <a class="exit-button-icon" href="">
                       <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                   </a>
            </div>
            <div class="page-title-with-back page-title-font"> Create and Email Automation</div>
         
        </div>
           
   
      </div>
        <!--Main Content--> 
        <div class="page-background" >
        <div class="page-content-container email-list-contact-page" ng-init="getEntityDetails();">
            
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container ">
                <div class="fleft content">
                    <div class="page-content-title-bar">
                        <div class="page-content-title sequenceTitleBar ">Email Automation Details</div>
                    </div>
                    <!--List Starts Here-->
                    <div class="sequence-container fleft">
                         <div class="col-6of10 fleft">
                                <div class="h4" style="">
                                    Enter a name for this recurring email:
                                </div>
                             <input id="recuring_email_title" class="input-field-textfield col-8of10" type="text" required placeholder="Enter Name of email" value="{{entity_details.recuring_email_title}}"></input>
                             
                            </div>
                         <div class="col-6of10 fleft pushUp">
                                <div class="h4" style="">
                                    Enter a description for this recurring email:
                                </div>
                             <input id="recuring_email_description" class="input-field-textfield col-8of10" type="text" required  
                                       placeholder="Enter description of email" value="{{entity_details.recuring_email_description}}"></input>
                            </div>
                    </div>
                </div>
            </div>
             <div class="page-inner-content-container ">
                <div class="fleft content">
                    <div class="page-content-title-bar">
                        <div class="page-content-title sequenceTitleBar ">Create a trigger for when to send this email</div>
                    </div>
                    <!--List Starts Here-->
                    <div class="sequence-container fleft">
                        <div class="col-3of10 fleft ">
                                <div class="h4 pushUp-10 ">
                                    Send an email to a recipient
                                </div>
                        </div>
                         <div class="col-1of10 fleft">
                             <select id="days" class="input-field-textfield "></select>
                         </div>
                        <div class="col-3of10 fleft">
                             <div class="h4 pushUp-10 lftpad-10">
                                    days after they are added to
                             </div>
                         </div>
                        <div class="col-3of10 fleft">
                              <select id="emaillist" name="emaillist" class="input-field-textfield">
                                   <option value="0">-- Select --</option>
                                   <option ng-repeat ="Lists in emailLists_user" value="{{Lists}}">{{Lists}}</option>
                                   <option ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>
                              </select>
                        </div>
                        <div class="col-4of10 fleft pushUp-30">
                                <div class="h4" style="">
                                     Select a Time:
                                </div>
                            <input id="timepicker1" readonly type="text" name="timepicker1" class="input-field-textfield col-8of10" 
                                  value="{{entity_details.recuring_email_time |date: 'hh : mm : a'}}" /> 
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
                        </div>
                        <div class="col-4of10 fleft pushUp-30 lftpad-5">
                                <div class="h4" style="">
                                     Select a till date:
                                </div>
                            <input type="text" readonly  name="datepicker" 
                                    id="datepicker"  
                                    class="input-field-textfield col-1of1" 
                                    value="{{entity_details.recuring_email_till_date| date:'MMM dd yyyy'}}" />  
                            <style>
                                .timepicker_wrap{
                                        width: 52%;
                                 }
                            </style>
                                    <script>
                                        var picker = new Pikaday(
                                        {
                                            format:('MM DD YYYY'),
                                            field: document.getElementById('datepicker'),
                                            firstDay: 1,
                                            minDate: new Date(2000, 0, 1),
                                            maxDate: new Date(2050, 12, 31),
                                            yearRange: [2000,2050]
                                        });
                                    </script>
                        </div>
                        
                    </div>
                </div>
            </div>
             <div class="page-inner-content-container ">
                <div class="fleft content">
                    <div class="page-content-title-bar">
                        <div class="page-content-title sequenceTitleBar ">Email Details</div>
                    </div>
                    <!--List Starts Here-->
                    <div class="sequence-container fleft">
                            <div class="col-6of10 fleft ">
                                <div class="h4" style="">
                                    Enter a subject line for the email:
                                </div>
                                <input id="subject" class="input-field-textfield col-8of10" type="text" required  
                                        placeholder="Enter subject line" 
                                        value="{{entity_details.recuring_email_subject}}"></input>
                            </div>
                            <div class="col-6of10 fleft pushUp">
                                <div class="h4" style="">
                                    Enter a from name:
                                </div>
                                <input id="from_name" class="input-field-textfield col-8of10" type="text" required  placeholder="Enter from name" 
                                      value="{{entity_details.recuring_email_from_name}}"></input>
                            </div>    
                            <div class="col-6of10 fleft pushUp">
                                <div class="h4" style="">
                                    Enter a reply-to-address:
                                </div>
                                <input id="reply_to_address" class="input-field-textfield col-8of10" type="text" 
                                     required  placeholder="Enter reply-to-address" 
                                     value="{{entity_details.recuring_email_reply_to_email_address}}"></input>  
                            </div>
                    </div>
                </div>
            </div>
            
        </div>
        </div>
        
    <!--</div>-->
  <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
               <div class="bottom-continue-button button-text-1" ng-click="addUpdateRecuringAction()">Continue</div>
            </div>
  </div>
   </div>
   <div id="emailautomation" class="page-background" ng-controller="emailautomation">
<!--           <div class="col-md-1 col-lg-1 col-sm-2 halfcol" >
               <%--<jsp:include page="leftmenu.html"/>--%>
           </div>-->
               <div id="emlautomeditorcontainer" class="page-content-container" ng-init="getEmailTemplates()">
               <!--<div class="col-1of1">-->
               <!--<div class="">-->
                   <div class="col-emaileditor">
                       <div class="col-emaileditordiv">
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
                            <div id='edit' style="margin-top:0px;"></div>
                        </div>
<!--                            <div class="btmdiv">
                               <div class="col-1of1">
                                       <div class="editemail fontpnr">Edit this Email Automation Action</div>
                                 
                                   <div class="col-1of4">
                                       <div class="mobileprev fontpnr" id="iphone" class="img-responsive ptr" onclick="show('iphone');">Mobile Preview</div>
                                       <p id="button"></p>
                                   </div>
                                   <div class="col-3of4">
                                       <div class="emledtrsavebtn">
                                           <input class="emailedtrsave fontpns 
                                                  button button--moema 
                                                  button--text-thick 
                                                  button--text-upper 
                                                  button--size-s" 
                                                  type="button" 
                                                  value="save" 
                                                  ng-click="addUpdateRecuringAction()"></input>
                                       </div>
                                   </div>
                               </div>
                           </div> -->
                       </div>
                        <div class="col-templatelist">
                            <div class="blockselection" id="templatediv">     
                                    <div class="col-1of1">
                                        <div class="">
                                            <div class="selblocknew fontpnr">Select a Template</div>
                                        </div>
                                            <div class="selblklinediv"><hr class="selblkline"></div>
                     <!--                        <div class="col-md-6 col-lg-6 col-sm-6">
                                            <div class="addblkdiv"><input class="addblkbtn fontpns " type="button" value="Add Block"></div>
                                        </div>-->
                                    </div>
                                    
                                    
                                    <div class="col-1of1">
                                            <ul id="blklist" class="blocklistnew fontpnr">
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
                                <div class="blockselection" id="textdiv">     
                                    <div class="col-1of1">
                                            <div class="selblock fontpnr">Select a Text</div>
                                           <div class="selblklinediv"><hr class="selblkline"></div>
                                     </div>
                                    <div class="col-1of1">
                                         <div class="textstyle">To add client name please use these options</div>
                                         <div class="textstyle">Ex: Hi &LT;clientFirstName&GT; </div>
                                         <div class="textstyle">Hi &LT;clientLastName&GT; </div>
                                         <div class="textstyle">Hi &LT;clientFullName&GT; </div>
                                     </div>
                                    </div>

                                </div>
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
                                        <li id="texttab">
                                            <image src='images/sidebar/Icons_editButton_blue_new.svg' class="blockimg"/>
                                            <p>TEXT</p>
                                        </li>
                                    </ul>
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

//            for(i=1; i<=31; i++){
//                if ( i == days){
//                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
//                }else {
//                    $('#days').append('<option value='+i+'>'+ i + '</option>');
//                }
//
//            }
        }, 1000);

    }

</script>    
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

           Modal content
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
          </div>

        </div>
    </div>
   </div>
        <div class="bottom-cta-bar" id="editpreviewtemplatebottom">
         <div class="bottom-cta-button-container col-inlineflex">
             <div class="editemail fontpnr">Edit this Email Automation Action</div>   
             <div class="mobileprev fontpnr" id="iphone" class="img-responsive ptr" onclick="show('iphone');">Mobile Preview</div>
             <div class="add-action-button md-button button-text-1 paddingperfectbtn" type="button" ng-click="addUpdateRecuringAction()">save</div>
         </div>
        </div>
        <!--</div>-->
     <!--</div>-->
<!--</div>-->
    </body>
</html>