<%-- 
    Document   : globalfonts
    Created on : Mar 26, 2016, 11:54:42 AM
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
      <jsp:include page="fontpopup.jsp"/>
      <body class="body-normal" ng-app="colors" ng-controller="globalColors">
      
          <div class="content-area" ng-init="getGlobalFonts()">
        <div class="content-area_header">
            <div class="header_path fleft"> Global Fonts</div>
            <div  ng-click="addFontContent()" class="CTA_Button Button--Blue fright">Add Fonts</div>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Font Name </span>
                </div>
            </div>
            <ul class="slatArea" ng-repeat="fontName in fontNames.slice().reverse()">
                <li class="listItem">
                    <div class="listCol col3of4 fleft">
                        <span class="listCol_Text fleft"> {{fontName.fontName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a>
                            <div ng-click="updateFontContent(fontName.fontName,fontName.fontFamilyName,fontName.globalFontsId,fontName.fileName)" id="updateFont" class="CTA_Button Button--Gray fright">Update Font</div>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
  
  
    </body>
</html>