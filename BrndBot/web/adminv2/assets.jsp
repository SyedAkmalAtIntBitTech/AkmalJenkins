<%-- 
    Document   : assets
    Created on : Mar 22, 2016, 5:56:09 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Email-Templates</title>
        
    </head>
       
     <jsp:include page="adminheader.jsp"/>
        <jsp:include page="colorpopup.jsp"/>
        <body class="body-normal" ng-app="colors" ng-controller="globalColors"   >
      
        <div class="content-area">
        <div class="content-area_header">
            <div class="header_path fleft"> Color Themes</div>
            
            <div id="addColorPalette" ng-click="addGlobalColors" class="CTA_Button Button--Blue fright">Add Color Theme</div>
        </div>
            <div class="slatSection"  ng-init="displayGlobalcolors()">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Theme Name </span>
                </div>
            </div>
                <ul class="slatArea" >
                <li class="listItem" ng-repeat="colornames in colorname.slice().reverse() ">
                    <div class="listCol col3of4 fleft">
                        <span class="listCol_Text fleft"> {{colornames.colorName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        
                        <div id="updateColorPalette" class="CTA_Button Button--Gray fright" ng-click="openColorPalettePopup(colornames.colorName,colornames.color1,colornames.color2,colornames.color3,colornames.color4,colornames.globalColorsId)">Update Palette</div>
                        
                    </div>
                </li>
            </ul>
        </div>
            
    </div>
  
  
    </body>
</html>