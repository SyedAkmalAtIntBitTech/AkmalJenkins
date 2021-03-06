<%-- 
    Document   : model
    Created on : Jun 24, 2015, 1:59:24 PM
    Author     : intbit
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
        <title>Email Layout Model</title>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!--        <link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>-->
        <link href="../css/site.css" rel="stylesheet" type="text/css"/>

        <script src="../js/jquery.blend.min.js" type="text/javascript"></script>
        <script src="../js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.js" type="text/javascript"></script>
        <script src="../js/site.js" type="text/javascript"></script>
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

                    alert("Browser does not support XMLHTTP Request");

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

                    alert("Browser does not support XMLHTTP Request");

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

                    alert("Browser does not support XMLHTTP Request");

                    return;
                }

                var url = "displaysubcategories.jsp";

                url += "?category_id=" + str;

                xmlHttp.onreadystatechange = categoryChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

            }

            function showmindbodyquery(str) {


             if (str == 0){
               $("#categories").attr("disabled", false);
               $("#subcategories").attr("disabled", false);
               $("#selectedtype").val("non");
             }else {
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

                    alert("Browser does not support XMLHTTP Request");

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
             function showbrand(Brand){
                    if (typeof XMLHttpRequest !== "undefined") {

                    xmlHttp = new XMLHttpRequest();

                }
                else if (window.ActiveXObject) {

                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp === null) {

                    alert("Browser does not support XMLHTTP Request");

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
                    document.getElementById("textFontFamily").innerHTML = response;
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
                ss.href = "https://fonts.googleapis.com/css?family="+ google_key_word;
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

        if (model_name == ""){
            alert("model name not entered");
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
                if (responseText == "yes"){
                    alert("name already exist, please give some other name");
                    $("#namexml").focus();
                    return false;
                }else if (responseText == "no") {
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
                                  blocks : blocks,
                                  model_name : model_name,
                                  imagename : imagename,
                                  mail : mail
                              },
                              success: function (responseText) {
                                  alert("Model saved successfully");
                                  window.open(getHost() + 'admin/emaillayoutmodel.jsp', "_self");
                              }                    
                           });    

                  }
              }
          });

        }
  //      return true;          
  }

          
      </script>

    </head>
    <body>
        <%@include file="menus.jsp" %>
        <%! 
            PreparedStatement ps;
            ResultSet rs;
            String Query = "";
            Integer id = 0;
            String org_name = "";
            String brand_name = "";
            String name = "";
            String font_name = "";
        %>

        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">Text</a></li>
                <li><a href="#tabs-2">Image</a></li>
                <li><a href="#tabs-3">Button</a></li>
                <li><a href="#tabs-4">Color Block</a></li>
            </ul>
            <div id="tabs-1">
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
            <div id="tabs-2">
                <p>
                    <span class="selectedElement">Selected Item: None</span>
                </p>
                <p>
                    <!--<span class="position">Co ordinates: X = 0, Y = 0 </span>-->
                    <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="imageX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="imageY" value="0" /></span>
                    <input type="button" class="coordButton" value="done">
                </p>
                <p>
                    <span class="size">Height=0px, Width=0px</span>
                </p>
                <p>
                    Opacity: <input type="text" id="opacityImage" maxlength="6" size="6" value="1" />px
                </p>
                <p>
                    Blend Color: 
                    <select id="blendColorSelect" >
                        <option value="normal">normal</option>
                        <option value="multiply">multiply</option>
                        <option value="screen">screen</option>
                        <option value="overlay">overlay</option>
                        <option value="darken">darken</option>
                        <option value="lighten">lighten</option>
                        <option value="color-dodge">color-dodge</option>
                        <option value="color-burn">color-burn</option>
                        <option value="hard-light">hard-light</option>
                        <option value="soft-light">soft-light</option>
                        <option value="difference">difference</option>
                        <option value="exclusion">exclusion</option>
                        <option value="hue">hue</option>
                        <option value="saturation">saturation</option>
                        <option value="color">color</option>
                        <option value="luminosity">luminosity</option>
                    </select>
                    <input type='text' class='basic' id='blendColorPick' value='black' />
                </p>  
                <hr>
<!--                <p>
                    File Path:<br /><br />
                    <input type="text" size="35" id="filePath" /> <br /><br />
                    <input type="button" class="fileButton" id="fileButton" value="Submit" />
                </p>-->
               <p>
                    <label id="selectImage" title="Please upload all your images using FTP client in the following folder: admin/AdminLayoutBackgroundImages">Please Select Image:</label><br /><br />
                    <select id="adminBackgroundImage"  title="Please upload all your images using FTP client in the following folder: admin/AdminLayoutBackgroundImages"><option value="none">---select---</option></select>
                    
                </p>
                <hr>
                <p>
                    Filters:
                </p>   

                <table>
                    <tr>
                        <td>
                            Blur:</td><td> <input id="blurFilter" class="filters" maxlength="2" size="2" value="0" /> px  </td></tr><tr><td>
                            Grayscale:</td><td> <input id="grayscaleFilter" class="filters" maxlength="2" size="2" value="0" /> %  </td></tr><tr><td>
                            Sepia:</td><td> <input id="sepiaFilter" class="filters" maxlength="2" size="2" value="0" /> %  </td></tr><tr><td>
                            Saturate:</td><td> <input id="saturateFilter" class="moreFilters" maxlength="2" size="2" value="100" /> %  </td></tr><tr><td>
                            Hue Rotate:</td><td> <input id="hueRotateFilter" class="someMoreFilters" maxlength="2" size="2" value="0" /> deg  </td></tr><tr><td>
                            Invert:</td><td> <input id="invertFilter" class="filters" maxlength="2" size="2" value="0" /> %  </td></tr><tr><td>
                            Brightness:</td><td> <input id="brightnessFilter" class="moreFilters" maxlength="2" size="2" value="100" /> %  </td></tr><tr><td>
                            Contrast:</td><td> <input id="contrastFilter" class="moreFilters" maxlength="2" size="2" value="100" /> %  </td></tr></table>

                <p>
                    Drop shadow:<br /><br />

                    Color: <input type="text" class='basic' id="dropShadowFilterColorPick" value="black" />
                    Blur: <input class="dropShadowFilters" id="blurDropShadowFilter" maxlength="2" size="2" value="0" /> px  <br /><br />
                    H Shadow: <input class="dropShadowFilters" id="hShadowDropShadowFilter" maxlength="2" size="2" value="0" /> px 
                    V Shadow: <input class="dropShadowFilters" id="vShadowDropShadowFilter" maxlength="2" size="2" value="0" /> px
                </p>


            </div>
            <div id="tabs-3">
                <p>
                    <span class="selectedElement">Selected Item: None</span>
                </p>
                <p>
                    <!--<span class="position">Co ordinates: X = 0, Y = 0 </span>-->
                    <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="buttonX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="buttonY" value="0" /></span>
                    <input type="button" class="coordButton" value="done">
                </p>
                <p>
                    Button Style: <select id="buttonSelect">
                        <option value="FilledButton_White.png">FilledButton_White</option>
                        <option value="FilledButton_dark.png">FilledButton_dark</option>
                        <option value="OutlineButton_dark.png">OutlineButton_dark</option>
                        <option value="OutlineButton_white.png">OutlineButton_white</option>
                        <option value="TextButton_Dark.png">TextButton_Dark</option>
                        <option value="TextButton_White.png">TextButton_White</option>
                    </select>

                </p>
            </div>
            <div id="tabs-4">
                <p>
                    <span class="selectedElement">Selected Item: None</span>
                </p>
                <p>
                    <!--<span class="position">Co ordinates: X = 0, Y = 0 </span>-->
                    <span class="newposition">Co ordinates: X = <input type="text" maxlength="4" size="4" id="blockX" value="0" /> Y = <input type="text" maxlength="4" size="4" id="blockY" value="0" /></span>
                    <input type="button" class="coordButton" value="done">
                </p>
                <p>
                    <input type="checkbox" id="circleCheckBox"> Circle
                </p>
                <p>
                    Block Size:
                </p>
                <p>
                    Width: <input type="text" id="blockWidth" maxlength="6" size="6" value="80" /> px 
                </p>
                <p>
                    height: <input type="text" id="blockHeight" maxlength="6" size="6" value="40" /> px
                </p>

                <p>
                    Opacity: <input type="text" id="opacityBlock" maxlength="6" size="6" value="1" />px
                </p>
                <p>
                    Block Color: <input type='text' class='basic' id='blockColor' value='black' />
                </p>
                <p>
                    <select id="blockColorFromDropDown">
                                    <option>Color-1</option>
                                    <option>Color-2</option>
                                    <option>Color-3</option>
                                    <option>Color-4</option>
                                    <option>Color-5</option>
                                    <option>Color-6</option>
                        
                    </select>
                </p>
                
                <p>
                    <input type="button" class="blockButton" id="blockButton" value="Apply" />
                </p>
                <hr>
                <!--                <p>
                                    Drop shadow:<br /><br />
                
                                    Color: <input type="text" class='basic' id="dropShadowColorPickBlock" value="black" />
                                    Blur: <input class="dropShadowBlock" id="blurDropShadowBlock" maxlength="2" size="2" value="0" /> px  <br /><br />
                                    H Shadow: <input class="dropShadowBlock" id="hShadowDropShadowBlock" maxlength="2" size="2" value="0" /> px 
                                    V Shadow: <input class="dropShadowBlock" id="vShadowDropShadowBlock" maxlength="2" size="2" value="0" /> px
                                </p>-->
            </div>
        </div>
        <div id="main">
            <h3>Email Layout Model</h3>
            <form>
                
                Organization : <select name="organization" id="organization" onchange="showUsers(this.value)">

                    <option value="0">-Select</option>
                    <%
                        Connection connection = null;
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
                            </select><br><br>
                            Sub Categories: <select id="subcategories" name="subcategories" onchange="showSelected(this.value)">
                                        <option value="0">Select</option>
                                </select>
                    Blocks : <select name="blocks" id="blocks" onchange="showmindbodyquery(this.value)">
                                        <option value="0">Select</option>
                             </select><br><br>
                                
                Width: <input id="containerWidth" class="spinner" size="6" value="500"> px Height: <input id="containerHeight" size="6" class="spinner" value="300"> px
                            <input type="hidden" name="mindbodyquery" id="mindbodyquery">
                            <input type="hidden" name="containerstyle" id="containerstyle">
                            <input type="hidden" name="textstyle" id="textstyle">
                            <input type="hidden" name="element" id="element">
                            <input type="hidden" name="mapper" id="mapper">
                            <input type="hidden" name="layout" id="layout" >
                            <input type="hidden" name="model_name" id="model_name" >
                            <input type="hidden" name="selectedtype" id="selectedtype" value="non" >
                            
                            <input type="button" value="save" onclick="passvaluetoinputfield();">

                            <div id="popup">
                             <div id="content">
                                 Model Name : <input type="text" id="namexml" required><br>
                                 <input type="hidden" id="mail" name="mail" value="mail"/>
                                 <input type="button" onclick="validate()" value="Done"/>   
                                 <input type="button" id="hidepopup" value="Close"/>   
                              </div>   

                             </div>

                        </form>
            

            <!-- Added by Syed Ilyas on 24/08/2015 -->
            <!-- This adds zoom functionality -->
            <br />
            <div id="slider" title="Please don`t slide after element is placed"></div>

            <script>
                $(function () {
                    $(function () {
                        $(document).tooltip();
                    });
                    $("#slider").slider({
                        min: 1,
                        max: 1000,
                        value: 500,
                        range: "min",
                        slide: function (event, ui) {
                            var divZoom = ui.value / 1000;
                            $(".container").css("zoom", "" + divZoom);
                        }
                    });
                });
            </script>
            <!-- End -->
            <div class="container">

            </div>

        </div>

        <div id="right">

            <center>
                <p>
                    Select Element: <select id="elementText">
                    </select>
                </p>
                <p>
                    <input type="button" class="rightButton" id="addTextButton" value="Add Text" />
                    <input type="button" class="rightButton" id="deleteTextButton" value="Delete Text" />
                </p>

                <p>
                    <input type="button" class="rightButton" id="addImageButton" value="Add Image" />
                    <input type="button" class="rightButton" id="deleteImageButton" value="Delete Image" />
                </p>

                <p>
                    <input type="button" class="rightButton" id="addButton" value="Add Button" />
                    <input type="button" class="rightButton" id="deleteButton" value="Delete Button" />
                </p>

                <p>
                    <input type="button" class="rightButton" id="addBlockButton" value="Add Block" />
                    <input type="button" class="rightButton" id="deleteBlockButton" value="Delete Block" />
                </p>
                <p>
                    <input type="button" class="rightButton" id="addLogoButton" value="Add Logo" />
                    <input type="button" class="rightButton" id="deleteLogoButton" value="Delete Logo" />
                </p>
                <div class='col-md-10' style="position: absolute; left: 1050px; top:70px; width: 600px; ">
                    <ul id='list2' class='col-md-10' >
                        <li id="lab"></li>
                    </ul> 
                </div>

        </div>

    </body>
</html>
