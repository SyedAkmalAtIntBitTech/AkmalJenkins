<%-- 
    Document   : globalimage
    Created on : Apr 5, 2016, 7:59:13 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Global Image</title>
         <link rel="shortcut icon" href="images/favicon.png"/>  
    </head>
    <body class="body-normal">
        <jsp:include page="header.jsp"/>
        <jsp:include page="popupimage.jsp"/>
        <jsp:include page="editimagepopup.jsp"/>
        <div class="content-area" ng-app="uploadImage" ng-controller="globalImageController">
        <div class="content-area_header">
            <div class="header_path fleft"> Global Images</div>
            <div id="addGlobalImage" class="CTA_Button Button--Blue fright">Add Image</div>
        </div>
            <div class="slatSection" ng-init="getGlobalImage()">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Image Name</span>
                     
                </div>
                
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Date Added</span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem" ng-repeat="getAllGlobalImage in getAllGlobalImages.slice().reverse()" >
                    <input hidden="" id="globalImageName" value="{{getAllGlobalImage.imageName}}"/>
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{getAllGlobalImage.imageName.split('.')[0]}}</span>
                        <input id="sd" value="{{url}}{{getAllGlobalImage.imageName}}" />
                    </div>
                    <div class="listCol col1of4 fleft">
                    <span class="listCol_Text fleft">{{getAllGlobalImage.createdDate | date : format : timezone}} </span>
                    
                </div>
                    <div class="listCol col1of4 fleft">
                        <a>
                            <div id="editImagePopUp" ng-click="editImagePopUp(getAllGlobalImage.globalImageId)" class="CTA_Button Button--Gray fright">View / Edit</div>
                        </a>
                    </div>
                    
                </li>
            </ul>
            
        </div>
    </div>
         
         
         
    </body>
</html>
