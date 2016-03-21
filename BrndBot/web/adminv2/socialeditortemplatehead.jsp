<%-- 
    Document   : adminheader
    Created on : Mar 2, 2016, 2:04:01 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <jsp:include page="adminheader.jsp"/>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href="../css/site.css" rel="stylesheet" type="text/css"/>

        <script src="../js/jquery.blend.min.js" type="text/javascript"></script>
        <script src="../js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.js" type="text/javascript"></script>
        <script src="../js/site.js" type="text/javascript"></script>
        
        <!-- For svg --> 
        <script src="../js/svg.js" type="text/javascript"></script>
        
        <!-- For color picker --> 
        <link href="../css/colorpicker.css" rel="stylesheet" type="text/css"/>
        <script src="../js/colorpicker.js" type="text/javascript"></script>
        <!-- For confirm dialog box -->

        <script src="../js/jquery.easy-confirm-dialog.js" type="text/javascript"></script>

        <!-- For image filter -->
        <script src="../js/colorup_min.js" type="text/javascript"></script>
        <!-- For better color picker --> 

        <script src="../js/spectrum.js" type="text/javascript"></script>

        <link href="../css/spectrum.css" rel="stylesheet" type="text/css"/>
        <script src="../js/configurations.js" type="text/javascript"></script>
        
         <style>
            .popuptable{
                margin-left:70px;
            }
        </style>
        <script language="javascript" type="text/javascript">
            $(document).ready(function () {
                var textSize = 5;

                while (textSize < 155)
                {

                    $("#textSize").append(new Option(textSize + "px", textSize));
                    textSize = textSize + 5;
                }
            });
            var xmlHttp;
            
            function showSelected(str){
                
                if (str == 0){
                   $("#selectedtype").val("non");
                }else {
                   $("#selectedtype").val("selected");
                }
        
            }
            
            function usersChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;

                    var response1, response2, response3, response4, response5, response6;
                    var len = response.length;
                    var no1 = response.indexOf(",");
                    response1 = response.substr(0, no1);
                    response2 = response.substr(no1 + 1, len);

                    document.getElementById("users").innerHTML = response1;
                    document.getElementById("categories").innerHTML = response2;
                }
            }

            function showUsers(str) {

                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "users.jsp";

                url += "?org_id=" + str;

                xmlHttp.onreadystatechange = usersChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function showSubCategories(str) {

                if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "displaysubcategories.jsp";

                url += "?category_id=" + str;

                xmlHttp.onreadystatechange = categoryChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function categoryChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    document.getElementById("subcategories").innerHTML = response;
                }
            }
           function showbrand(Brand){
                    if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request!");

                    return;
                }

                var url = "getfonts.jsp";

                url += "?Brand_id=" + Brand;

                xmlHttp.onreadystatechange =fontChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);
             
           }
              function fontChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    var len = response.length;
                    var no1 = response.indexOf(",");
                    response1 = response.substr(0, no1);
                    response2 = response.substr(no1 + 1, len);
                    document.getElementById("textFontFamily").innerHTML = response1;
                }
            }

    
      </script>          
        <script>
            $(document).ready(function () {
    
            $("#textFontFamily").change(function () {
//            alert($(this).val());
                var text = $("#textFontFamily").find('option:selected').text();
                var font_family_name = $("#textFontFamily").val();
                var font = font_family_name.split("|");
                var google_key_word = font[0].split(' ').join('+')
                
                var ss = document.createElement("link");
                ss.type = "text/css";
                ss.rel = "stylesheet";
                ss.href = "https://fonts.googleapis.com/css?family="+ google_key_word ;
                document.getElementsByTagName("head")[0].appendChild(ss);

                var font_path = global_host_address + "DownloadFonts?file_name="+ font[1];
                var styles = "@font-face {"+
                             "font-family:"+ text + ";"+
                             "src: url("+font_path+");"
                $('<style type="text/css">'+ styles +'</style>').appendTo(document.head);

                $("#" + selectedTextID).css("font-family", font[0]);

            });
            
            $("#hidepopup").click(function(){
                        $('#popup').hide("slow");
            });
            });
          
          
      </script>

        <script>
          
    function validate(){
      var model_name = $("#namexml").val();

      if (model_name === ""){
          alert("Model name not entered!");
          $("#namexml").focus();
          return false;
      }else {
        $.ajax({
            url: global_host_address + 'ServletValidateModel',
            method: 'post',
            data: {
                model_name : model_name
            },
            success: function (responseText) {
        if (responseText === "yes"){
            alert("Name already exist! Please give some other name.");
            $("#namexml").focus();
            return false;
        }else if (responseText === "no") {
            var file_name = $("#namexml").val();
            var mapperxml = file_name + "_" + "mapper";
            var layoutxml = file_name + "_" + "layout";

            $("#mapper").val(mapperxml);
            $("#layout").val(layoutxml);
            $("#model_name").val($("#namexml").val());
            $('#popup').hide("slow");

            var organization = $("#organization").val();
            var brand = $("#brand").val();
            var users = $("#users").val();
            var categories = $("#categories").val();
            var subcategories = $("#subcategories").val();
            var mindbodyquery = $("#mindbodyquery").val();
            var containerstyle = $("#containerstyle").val();
            var textstyle = $("#textstyle").val();
            var element = $("#element").val();

            var model_name = $("#namexml").val();
            var mapperxml = model_name + "_" + "mapper";
            var layoutxml = model_name + "_" + "layout";

            var imagename = $("#imagename").val();
            var social = $("#socialmedia").val();
            var isSocial=$("#socialCheckbox").is(':checked');
            var isPrint=$("#printCheckbox").is(':checked');
            var isDownload=$("#downloadCheckbox").is(':checked');
              $.ajax({
                      url: global_host_address + 'Model',
                      method: 'post',
                      data: {
                          organization : organization,
                          brand : brand,
                          users : users,
                          categories : categories,
                          subcategories : subcategories,
                          mindbodyquery : mindbodyquery,
                          containerstyle : containerstyle,
                          textstyle : textstyle,
                          element : element,
                          mapper : mapperxml,
                          layout : layoutxml,
                          model_name : model_name,
                          imagename : imagename,
                          socialmedia : social,
                          isSocial: isSocial,
                          isPrint: isPrint,
                          isDownload: isDownload
                          
                      },
                      success: function (responseText) {
                        alert("Model saved successfully.");
                        window.open(getHost() + 'admin/sociallayoutmodel.jsp', "_self");
                      }                    
                   });    

                }
            }
        });

      }

  }
  
  
      </script>
<!--       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
       <script type="text/javascript" src="../js/angular.min.js"></script>-->
       
       <title>Admin Header</title>
   </head>
    <body>
       <div class="nav ">
        <div class="logodiv nav_logo fleft">
            <img  src="../images/Icons/Logo_Reverse.svg" class="bb-logo" style="cursor:pointer;height:35px;">
        </div>
        <div class="headernav">
            <a href="allcompanies.jsp"><div class="nav_tab fleft"> Users </div></a>
            <a href="organization.jsp"><div class="nav_tab fleft"> Organizations </div></a>
            <a href="#"><div class="nav_tab fleft"> Email Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Email Blocks</div></a>
            <a href="printtemplates.jsp"><div class="nav_tab fleft"> Print Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Image Templates </div></a>
            <a href="#"><div class="nav_tab fleft"> Marketing Programs </div></a>
            <a href="#"><div class="nav_tab fleft"> Assets </div></a>
            <a href="#"><div class="nav_tab fleft"> Recurring Email</div></a>
        </div>
       </div>
    </body>
</html>
