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
        <title>BrndBot - Layout Model</title>
<!--         <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
          <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
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

      var xmlHttp;  
      
            function usersChange(){   

            if (xmlHttp.readyState===4 || xmlHttp.readyState==="complete"){   

                  var response = xmlHttp.responseText;

                  var response1, response2, response3, response4, response5, response6;
                  var len = response.length;
                  var no1 = response.indexOf(",");
                  response1 = response.substr(0, no1);
                  response2 = response.substr(no1+1,len);

                  document.getElementById("users").innerHTML=response1;
                  document.getElementById("categories").innerHTML=response2;
            }   
            }
            
            function showUsers(str){

                if (typeof XMLHttpRequest !== "undefined"){

                xmlHttp= new XMLHttpRequest();

                }
                else if (window.ActiveXObject){

                xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp===null){

                alert("Browser does not support XMLHTTP Request!");

                return;
                } 

                var url="users.jsp";

                url +="?org_id=" +str;

                xmlHttp.onreadystatechange = usersChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

      }

            function showSubCategories(str){

                if (typeof XMLHttpRequest !== "undefined"){

                xmlHttp= new XMLHttpRequest();

                }
                else if (window.ActiveXObject){

                xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");

                }
                if (xmlHttp===null){

                alert("Browser does not support XMLHTTP Request!");

                return;
                } 

                var url="displaysubcategories.jsp";

                url +="?category_id=" +str;

                xmlHttp.onreadystatechange = categoryChange;

                xmlHttp.open("GET", url, true);

                xmlHttp.send(null);

      }
      
      function categoryChange(){

        if (xmlHttp.readyState===4 || xmlHttp.readyState==="complete"){

              var response = xmlHttp.responseText;
              document.getElementById("subcategories").innerHTML=response;
        }
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
        String font_name="";
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
                    <span class="position">Co ordinates: X = 0, Y = 0 </span>
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

<!--                        <% 
                        Connection connection = null;
                        try {
                            connection = ConnectionManager.getInstance().getConnection();
                            Query = "Select * from tbl_brand_font_family where brand_id=";
                            ps = connection.prepareStatement(Query);

                            rs = ps.executeQuery();
                            }catch (Exception e){
                            System.out.println(e. getCause());
                            System.out.println(e.getMessage());
                        }finally{
                            ps.close();
                            rs.close();
                            ConnectionManager.getInstance().closeConnection(connection);
                        }
                            
                            %>
                -->
                
                
                
                
                
                
                <p>
                Font Size: <select id="textSize">
                                 <option value="8">Font Size 1</option>
                                 <option value="12">Font Size 2</option>
                                 <option value="14">Font Size 3</option>
                                 <option value="18">Font Size 4</option>
                                 <option value="22">Font Size 5</option>
                               </select>
                </p>

                <p>
                    Font Family: <select id="textFontFamily">
                        <option value="Arial">Font Family 1</option>
                        <option value="Papyrus">Font Family 2</option>
                        <option value="Montserrat">Font Family 3</option>
                        <option value="Futura">Font Family 4</option>
                        <option value="Times New Roman">Font Family 5</option>
                    </select>
                    
 <!--Font Family: <select name="textFontFamily" id="textFontFamily" >
                        <option value="0"></option>
                    </select>-->
                </p>
                <p>
                    Font Color: <input type="text" class='basic' id="colorPick" value="black" />
                </p>
                <hr>
                <p>
                    Drop shadow:<br /><br />

                    Color: <input type="text" class='basic' id="dropShadowColorPick" value="black" />
                    Blur: <input id="blur" maxlength="2" size="2" value="0" /> px  <br /><br />
                    H Shadow: <input id="hShadow" maxlength="2" size="2" value="0" /> px 
                    V Shadow: <input id="vShadow" maxlength="2" size="2" value="0" /> px
                </p>
                <hr>
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
                <p>
                    Rotate: <input type="text" id="rotate" maxlength="4" size="4" value="0" />deg
                </p>
            </div>
            <div id="tabs-2">
                <p>
                    <span class="selectedElement">Selected Item: None</span>
                </p>
                <p>
                    <span class="position">Co ordinates: X = 0, Y = 0 </span>
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
                <p>
                    File Path:<br /><br />
                    <input type="text" size="35" id="filePath" /> <br /><br />
                    <input type="button" class="fileButton" id="fileButton" value="Submit" />
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
                    <span class="position">Co ordinates: X = 0, Y = 0 </span>
                </p>
                <p>
                    Button Style: <select id="buttonSelect">
                        <option value="1">Button1</option>
                        <option value="2">Button2</option>
                        <option value="3">Button3</option>
                        <option value="4">Button4</option>
                        <option value="5">Button5</option>
                    </select>
                    
                        
                </p>
            </div>
            <div id="tabs-4">
                <p>
                    <span class="selectedElement">Selected Item: None</span>
                </p>
                <p>
                    <span class="position">Co ordinates: X = 0, Y = 0 </span>
                </p>
                <p>
                    Block Size:
                </p>
                <p>
                    Width: <input type="text" id="blockWidth" maxlength="3" size="3" value="80" /> px 
                </p>
                <p>
                    height: <input type="text" id="blockHeight" maxlength="3" size="3" value="40" /> px
                </p>

                <p>
                    Opacity: <input type="text" id="opacityBlock" maxlength="6" size="6" value="1" />px
                </p>
                <p>
                    Block Color: <input type='text' class='basic' id='blockColor' value='black' />
                </p>
                <p>
                    <input type="button" class="blockButton" id="blockButton" value="Apply" />
                </p>
                <hr>
                <p>
                    Drop shadow:<br /><br />

                    Color: <input type="text" class='basic' id="dropShadowColorPickBlock" value="black" />
                    Blur: <input class="dropShadowBlock" id="blurDropShadowBlock" maxlength="2" size="2" value="0" /> px  <br /><br />
                    H Shadow: <input class="dropShadowBlock" id="hShadowDropShadowBlock" maxlength="2" size="2" value="0" /> px 
                    V Shadow: <input class="dropShadowBlock" id="vShadowDropShadowBlock" maxlength="2" size="2" value="0" /> px
                </p>
            </div>
        </div>
        <div id="main">
            <form action="<%= application.getContextPath()%>/Model" method="post">
                
                Organization : <select name="organization" onchange="showUsers(this.value)">
                    <% 
                        Connection connection1 = null;
                        try {
                            connection1 = ConnectionManager.getInstance().getConnection();
                            Query = "Select * from tbl_organization";
                            ps = connection1.prepareStatement(Query);

                            rs = ps.executeQuery();
                            while(rs.next()){
                                id = rs.getInt("id");
                                org_name = rs.getString("organization_name");
                    %>            
                                        <option value="<%= id %>"><%= org_name %></option>
                    <%
                            }
                        }catch (Exception e){
                            System.out.println(e. getCause());
                            System.out.println(e.getMessage());
                        }finally{
                            ps.close();
                            rs.close();
                            ConnectionManager.getInstance().closeConnection(connection1);
                        }

                            
                    %>
                                      </select><br><br>
                Users: <select id='users' name="users">
                            <option value="0"></option>
                         </select>
                Categories: <select id="categories" name="categories" onchange="showSubCategories(this.value)">
                                    <option value="0"></option>
                                </select><br><br>
                Sub Categories: <select id="subcategories" name="subcategories">
                                        <option value="0"></option>
                                        </select><br><br>

                Width: <input id="containerWidth" class="spinner" size="6" value="500"> px Height: <input id="containerHeight" size="6" class="spinner" value="300"> px

                            <input type="hidden" name="containerstyle" id="containerstyle">
                            <input type="hidden" name="textstyle" id="textstyle">
                            <input type="hidden" name="element" id="element">
                            <input type="text" name="mapper" id="mapper">
                            <input type="hidden" name="layout" id="layout" >
                            <input type="button" value="save" onclick="passvaluetoinputfield();">

                            <div id="popup">
                             <div id="content">
                                 Mapper file name<input type="text" id="mapperxml" required><br><br>
                                 Layout file name<input type="text" id="layoutxml" requireds><br>
                                 email <input type="checkbox" name="mail" value="mail"/>
                                 social media<input type="checkbox" name="socialmedia" value="socialmedia" />
                                 <input type="submit" id="popupclose" type="Button" value="Done"/>   
                              </div>   

                             </div>

<!--                            <input type="submit" value="submit">-->
                        </form>
             <div class='col-md-10'>
                        <ul id='list2' class='col-md-3' >
                            <li id="lab"></li>
                        </ul> 
             </div>
            
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
    </div>

    </body>
</html>
