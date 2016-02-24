<%-- 
    Document   : emaillayout
    Created on : 19 Oct, 2015, 4:41:28 PM
    Author     : sandeep-kumar
--%>

<%@page import="com.intbit.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.controller.SqlMethods"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="checksession.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BrndBot - Email Layout</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
         <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="../js/angular.min.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<!--        <link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>-->
        <link href="../css/site.css" rel="stylesheet" type="text/css"/>
        <script src="../js/site.js" type="text/javascript"></script>
        <!-- For color picker --> 
        <link href="../css/colorpicker.css" rel="stylesheet" type="text/css"/>
        <script src="../js/colorpicker.js" type="text/javascript"></script>
        <!-- For confirm dialog box -->

<!--        <script src="../js/jquery.easy-confirm-dialog.js" type="text/javascript"></script>-->

        <!-- For image filter -->
        <script src="../js/colorup_min.js" type="text/javascript"></script>
        <!-- For better color picker --> 

        <script src="../js/spectrum.js" type="text/javascript"></script>

        <link href="../css/spectrum.css" rel="stylesheet" type="text/css"/>
        <script src="../js/configurations.js" type="text/javascript"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <style>
            #main {
                float: left;
                height: 600px;
                width: 100%;
                background-color: white;
            }
        </style>
        <style>
            body {
                text-align: center;
            }

            div#editor {
                width: 81%;
                margin: auto;
                text-align: left;
            }
        </style>
          <script>
       $("#Servicecontinue").hide();
        var rootApp = angular.module('rootApp', ['uploadModule','imagegallery']);
        var uploadModule = angular.module('uploadModule', []);
        
            uploadModule.directive('fileModel', ['$parse', function ($parse) {
                    return {
                        restrict: 'A',
                        link: function (scope, element, attrs) {
                            var model = $parse(attrs.fileModel);
                            var modelSetter = model.assign;

                            element.bind('change', function () {
                                scope.$apply(function () {
                                    modelSetter(scope, element[0].files[0]);
                                });
                            });
                        }
                    };
                }]);

            uploadModule.service('fileUpload', ['$http', function ($http) {
                    this.uploadFileToUrl = function (file, uploadUrl) {
                        var fd = new FormData();
                        fd.append('file', file);
                        $http.post(uploadUrl, fd, {
                            transformRequest: angular.identity,
                            headers: {'Content-Type': undefined}
                        })
                            .success(function (data) {
                                validate(data.link);
                            })
                            .error(function () {
                                alert("Request unsuccessful!");
                            });
                    };
                }]);

            uploadModule.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {

                    $scope.uploadFile = function () {                        
                        var file = $scope.myFile;
                        console.log('file is ' + JSON.stringify(file));
                        var uploadUrl = global_host_address + 'UploadImages';
                        fileUpload.uploadFileToUrl(file, uploadUrl);
                    };

              }]);
          
           var uploadModule = document.getElementById('uploadModule');

            angular.element(document).ready(function() {
                   angular.bootstrap(uploadModule, ['uploadModule']);
            });

        </script>
         <script>
            
            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {


                   

                });
                
                angular.module('imagegallery').filter('pagination', function()
                {
                 return function(input, start)
                 {
                  start = +start;
                  return input.slice(start);
                 };
                });
            
        </script>
        <script language="javascript" type="text/javascript">
//            $(document).ready(function(){
//                var textSize = 5;
//                while (textSize < 155)
//                {
//                    $("#textSize").append(new Option(textSize + "px", textSize));
//                    textSize = textSize + 5;
//                }
//                
//
//            });
            
            var xmlHttp;

            function showSelected(str) {

                if (str == 0) {
                    $("#selectedtype").val("non");
                } else {
                    $("#selectedtype").val("selected");
                }

            }

            function brandChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    var len = response.length;
                    var no1 = response.indexOf(",");
                    response1 = response.substr(0, no1);
                    response2 = response.substr(no1 + 1, len);
                    document.getElementById("textFontFamily").innerHTML = response1;
                    document.getElementById("blocks").innerHTML = response2;

                }
            }

            function showblocks(str) {

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

                url += "?Brand_id=" + str;

                xmlHttp.onreadystatechange = brandChange;


                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

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


                if (str == 0) {
                    $("#blocks").attr("disabled", false);
                } else {
                    $("#blocks").attr("disabled", true);
                }

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

            function showmindbodyquery(str) {


                if (str == 0) {
                    $("#categories").attr("disabled", false);
                    $("#subcategories").attr("disabled", false);
                    $("#selectedtype").val("non");
                } else {
                    $("#categories").attr("disabled", true);
                    $("#subcategories").attr("disabled", true);
                    $("#selectedtype").val("selected");
                }

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

                var url = "showmindbodyquery.jsp";

                url += "?block_id=" + str;

                xmlHttp.onreadystatechange = blockChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function blockChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    $("#mindbodyquery").val(response.trim());

                    //              document.getElementById("subcategories").innerHTML=response;
                }
            }

            function categoryChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    document.getElementById("subcategories").innerHTML = response;
                }
            }
            function showbrand(Brand) {
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

                xmlHttp.onreadystatechange = fontChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }
            function fontChange() {

                if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {

                    var response = xmlHttp.responseText;
                    document.getElementById("textFontFamily").innerHTML = response;
                }

            }



        </script>  
        <script>
           $(document).ready(function(){
                $("#textFontFamily").change(function () {
                    var text = $("#textFontFamily").find('option:selected').text();
                    var font_family_name = $("#textFontFamily").val();
                    var font = font_family_name.split("|");
                    var google_key_word = font[0].split(' ').join('+');
                    var ss = document.createElement("link");
                    ss.type = "text/css";
                    ss.rel = "stylesheet";
                    ss.href = "https://fonts.googleapis.com/css?family=" + google_key_word;
                    document.getElementsByTagName("head")[0].appendChild(ss);

                    var font_path = global_host_address + "DownloadFonts?file_name=" + font[1];
                    var styles = "@font-face {" +
                            "font-family:" + text + ";" +
                            "src: url(" + font_path + ");"
                    $('<style type="text/css">' + styles + '</style>').appendTo(document.head);

                    $("#" + selectedTextID).css("font-family", font[0]);

                });


            });


        </script>
        <script>
            function validate(imageUrl) {                
                var UploadImage=imageUrl.replace(""+global_host_address+"DownloadImage?image_type=ADMIN_LAYOUT_BACKGROUNDIMAGES&user_id=null&image_name=","");
                var model_name = $("#namexml").val();
                var htmldata=$('#edit').froalaEditor('html.get');//$(".fr-wrapper").children().html();

                if (model_name === ""){
                    alert("Model name not entered!");
                    $("#namexml").focus();
                    return false;
                }
                else if(UploadImage === "null")
                    {
                    alert("Please select a image!");
                    HidePopUp();
                    $("#filesToUpload").focus();
                    return false;
                } 
                else if(htmldata === "<p><br></p>"){
                    alert("Please add html data!");
                    HidePopUp();
                    return false;
                    
                }        
                else {
                    $.ajax({
                        url: global_host_address + 'ServletValidateModel',
                        method: 'post',
                        data: {
                            model_name: model_name
                        },
                        success: function (responseText) {
                            if (responseText == "yes") {
                                alert("Name already exist! Please give some other name.");
                                $("#namexml").focus();
                                return false;
                            } else if (responseText == "no") {
                                var file_name = $("#namexml").val();
                                var mapperxml = file_name + "_" + "mapper";
                                var layoutxml = file_name + "_" + "layout";

                                $("#mapper").val(mapperxml);
                                $("#layout").val(layoutxml);
                                $("#model_name").val($("#namexml").val());
                                //                    $("#tabs *").attr("disabled", false);
                                //                    $("#main *").attr("disabled", false);
                                //                    $("#right *").attr("disabled", false);
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
                                var blocks = $("#blocks").val();
                                var model_name = $("#namexml").val();
                                var mapperxml = model_name + "_" + "mapper";
                                var layoutxml = model_name + "_" + "layout";

                                var imagename = $("#imagename").val();
                                var mail = $("#mail").val();
                                $.ajax({
                                    url: global_host_address + 'Model',
                                    method: 'post',
                                    data: {
                                        organization: organization,
                                        brand: brand,
                                        users: users,
                                        categories: categories,
                                        subcategories: subcategories,
                                        mindbodyquery: mindbodyquery,
                                        containerstyle: containerstyle,
                                        textstyle: textstyle,
                                        element: element,
                                        mapper: mapperxml,
                                        layout: layoutxml,
                                        blocks: blocks,
                                        model_name: model_name,
                                        imagename: imagename,
                                        mail: mail,
                                        htmldata:htmldata,
                                        UploadImage:UploadImage
                                    },
                                    success: function (responseText) {
                                        alert("Model saved successfully.");
                                        window.open(getHost() + 'admin/emaillayout.jsp', "_self");
                                    }
                                });



                            }
                        }
                    });

                }
                //      return true;          
            }


        </script>
                <!--   new css      -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="../css/froala_editor.css">
        <link rel="stylesheet" href="../css/froala_style.css">
        <link rel="stylesheet" href="../css/plugins/code_view.css">
        <link rel="stylesheet" href="../css/plugins/colors.css">
        <link rel="stylesheet" href="../css/plugins/emoticons.css">
        <link rel="stylesheet" href="../css/plugins/image_manager.css">
        <link rel="stylesheet" href="../css/plugins/image.css">
        <link rel="stylesheet" href="../css/plugins/line_breaker.css">
        <link rel="stylesheet" href="../css/plugins/table.css">
        <link rel="stylesheet" href="../css/plugins/char_counter.css">
        <link rel="stylesheet" href="../css/plugins/video.css">
        <link rel="stylesheet" href="../css/plugins/fullscreen.css">
        <link rel="stylesheet" href="../css/plugins/file.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">


    </head>
    <body  ng-app="rootApp">
        <%@include file="menus.jsp" %>
        <%!            PreparedStatement ps;
            ResultSet rs;
            String Query = "";
            Integer id = 0;
            String org_name = "";
            String brand_name = "";
            String name = "";
            String font_name = "";
        %>
        
        <div id="main">
            <h3>Email Layout Model</h3>
            <form>

                Organization : <select name="organization" id="organization" onchange="showUsers(this.value)">

                    <option value="0">-Select</option>
                    <%  Connection connection = null;
                        try {
                            connection = ConnectionManager.getInstance().getConnection();

                            Query = "Select * from tbl_organization";
                            ps = connection.prepareStatement(Query);

                            rs = ps.executeQuery();
                            while (rs.next()) {
                                id = rs.getInt("id");
                                org_name = rs.getString("organization_name");
                    %>            
                    <option value="<%= id%>"><%= org_name%></option>
                    <%
                            }
                        } catch (Exception e) {
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        } finally {
                            ps.close();
                            rs.close();
                            ConnectionManager.getInstance().closeConnection(connection);
                        }

                    %>

                </select>

                Brand : <select name="brand" id="brand" onchange="showblocks(this.value)">
                    <option value="0">-Select-</option>
                    <% 
                        try {
                            connection = ConnectionManager.getInstance().getConnection();
                            Query = "Select * from tbl_brand_personality";
                            ps = connection.prepareStatement(Query);

                            rs = ps.executeQuery();
                            while (rs.next()) {
                                id = rs.getInt("id");
                                brand_name = rs.getString("brand_name");
                    %>
                    <option value="<%= id%>"><%= brand_name%></option>
                    <%
                            }
                        } catch (Exception e) {
                            System.out.println(e.getCause());
                            System.out.println(e.getMessage());
                        } finally {
                            rs.close();
                            ps.close();
                            ConnectionManager.getInstance().closeConnection(connection);
                        }

                    %>
                </select><br><br>

                Users: <select id='users' name="users">
                    <option value="0">Select</option>
                </select>
                Categories: <select id="categories" name="categories" onchange="showSubCategories(this.value)">
                    <option value="0">Select</option>
                </select>
                Sub Categories: <select id="subcategories" name="subcategories" onchange="showSelected(this.value)">
                    <option value="0">Select</option>
                </select>
                Blocks : <select name="blocks" id="blocks" onchange="showmindbodyquery(this.value)">
                            <option value="0">Select</option>
                         </select><br><br>
<!--                <input type="file"   name="imageFile" id="imageFile">-->
                <input type="hidden" name="customcolorsArray" id="customcolorsArray">
                <input type="hidden" name="mindbodyquery" id="mindbodyquery">
                <input type="hidden" name="containerstyle" id="containerstyle">
                <input type="hidden" name="textstyle" id="textstyle">
                <input type="hidden" name="element" id="element">
                <input type="hidden" name="mapper" id="mapper">
                <input type="hidden" name="layout" id="layout" >
                <input type="hidden" name="model_name" id="model_name" >
                <input type="hidden" name="selectedtype" id="selectedtype" value="non" >
                <input type="button" value="save" onclick="popupwindow();">

            </form>
                <div class="group " ng-controller="myCtrl">
                 <div id="popup">
                    <div id="content">
                        Model Name : <input type="text" id="namexml" required><br>
                        <input type="hidden" id="mail" name="mail" value="mail"/>
<!--                        <input type="button" onclick="validate()" value="Done"/> -->
                         <button  id="Servicecontinue" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="uploadFile()" style="margin-left: 100px;">Done</button>
                        <input type="button" id="hidepopup" onclick="HidePopUp()" value="Close"/>   
                    </div>   
                </div>
                   
                    <div class="fileUpload btn ">
                        <input type="file" name="filesToUpload[]" id="filesToUpload" class="upload" file-model="myFile" />
                    </div>    
                </div>
            <div id="editor">
                <div id='edit' style="margin-top: 30px;">
                </div>
            </div>
        </div>

            <script type="text/javascript" src="../js/froala_editor.min.js" ></script>
            <script type="text/javascript" src="../js/plugins/align.min.js"></script>
            <script type="text/javascript" src="../js/plugins/code_view.min.js"></script>
            <script type="text/javascript" src="../js/plugins/colors.min.js" ></script>
            <script type="text/javascript" src="../js/plugins/emoticons.min.js"></script>
            <script type="text/javascript" src="../js/plugins/font_size.min.js"></script>
            <script type="text/javascript" src="../js/plugins/font_family.min.js"></script>
            <script type="text/javascript" src="../js/plugins/image.min.js"></script>
            <script type="text/javascript" src="../js/plugins/file.min.js"></script>
            <script type="text/javascript" src="../js/plugins/image_manager.min.js"></script>
            <script type="text/javascript" src="../js/plugins/line_breaker.min.js"></script>
            <script type="text/javascript" src="../js/plugins/link.min.js"></script>
            <script type="text/javascript" src="../js/plugins/lists.min.js"></script>
            <script type="text/javascript" src="../js/plugins/paragraph_format.min.js"></script>
            <script type="text/javascript" src="../js/plugins/paragraph_style.min.js"></script>
            <script type="text/javascript" src="../js/plugins/video.min.js"></script>
            <script type="text/javascript" src="../js/plugins/table.min.js"></script>
            <script type="text/javascript" src="../js/plugins/url.min.js"></script>
            <script type="text/javascript" src="../js/plugins/entities.min.js"></script>
            <script type="text/javascript" src="../js/plugins/char_counter.min.js"></script>
            <script type="text/javascript" src="../js/plugins/inline_style.min.js"></script>
            <script type="text/javascript" src="../js/plugins/save.min.js"></script>
            <script type="text/javascript" src="../js/plugins/fullscreen.min.js"></script>
            <script type="text/javascript" src="../js/plugins/quote.min.js"></script>

            <script> 
                $(function () {
                       $('#edit').froalaEditor({key: FroalaLicenseKey});                    
                    });
//                $('.selector').froalaEditor({
//                    imageUploadURL: '/UploadImages'
//                    });
                function HidePopUp(){
                      $('#popup').hide("slow");
                 }
                   // call for upload imageGallery  
//                 $.ajax({
//                    url: global_host_address+"GetFilesListServlet?image_type=ADMIN_LAYOUT_BACKGROUNDIMAGES&user_id=null",
//                    success: function(){
//                        $(this).addClass("done");
//                          }
 //                   });

            </script>

            <div id="tabs-1" style="display: none;">
                <p>
                    <span class="selectedElement">Selected Item: None</span>
                </p>
                <p>
                    <!--<span class="position">Co ordinates: X = 0, Y = 0 </span>-->
                    <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="textX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="textY" value="0" /></span>
                    <input type="button" class="coordButton" value="done">
                </p>
                <p>
                    Enter Text: <br /><textarea id="textArea"></textarea>
                </p>
                <hr>
                <p>
                    <input type="button" class="textButton" id="boldButton" value="bold" />
                    <input type="button" class="textButton" id="italicButton" value="italic" /><br />
                    <input type="button" class="alignButton" id="leftButton" value="left" />
                    <input type="button" class="alignButton" id="centerButton" value="center" />
                    <input type="button" class="alignButton" id="rightButton" value="right" />
                </p>

                <p>
                    Font Size: <select id="textSize">
                        <!--                                 <option value="8">8px</option>
                                                         <option value="12">12px</option>
                                                         <option value="14">14px</option>
                                                         <option value="18">18px</option>
                                                         <option value="22">22px</option>
                                                         <option value="26">26px</option>
                                                         <option value="30">30px</option>
                                                         <option value="34">34px</option>                           -->
                    </select>
                </p>

                <p>
                    Font Family: <select id="textFontFamily">
                        <option>select</option>
                    </select>

                    <!--Font Family: <select name="textFontFamily" id="textFontFamily" >
                                           <option value="0"></option>
                                       </select>-->
                </p>
                <p>
                    <!--                    Font Color: <input type="text" class='basic' id="colorPick" value="black" />-->
                    Font Color: <select id="fontColor">
                                    <option value="#F27821">Font-Color-1</option>
                                    <option value="#00A8BD">Font-Color-2</option>
                                    <option value="#EE7766">Font-Color-3</option>
                                    <option value="#EEEEEE">Font-Color-4</option>
                                    <option value="#FFFFFF">Font-Color-5</option>
                                    <option value="#353333">Font-Color-6</option>
                                </select>
                </p>
                <hr>
                <!--                <p>
                                    Drop shadow:<br /><br />
                
                                    Color: <input type="text" class='basic' id="dropShadowColorPick" value="black" />
                                    Blur: <input id="blur" maxlength="2" size="2" value="0" /> px  <br /><br />
                                    H Shadow: <input id="hShadow" maxlength="2" size="2" value="0" /> px 
                                    V Shadow: <input id="vShadow" maxlength="2" size="2" value="0" /> px
                                </p>-->
                <!--                <hr>-->
                <p>
                    Leading / Line Height: 
                    <input maxlength="3" size="3" value="15" id="lineHeight" /> px
                </p>
                <p>
                    Kerning/ Letter Space: 

                    <input maxlength="3" size="3" value="0" id="letterSpace" /> px
                </p>
                <hr>
                <p>
                    Opacity: <input type="text" id="opacity" maxlength="6" size="6" value="1" />px
                </p>
                <!--                <p>
                                    Rotate: <input type="text" id="rotate" maxlength="4" size="4" value="0" />deg
                                </p>-->
            </div>
    </body>
</html>