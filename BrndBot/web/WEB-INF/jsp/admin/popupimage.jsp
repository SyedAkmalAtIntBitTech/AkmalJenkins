<%-- 
    Document   : popupimage
    Created on : Apr 5, 2016, 8:04:41 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="uploadImage">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Popup Image</title>
    </head>
    <body>
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer" ></div>
        <div class="popUp_background" id="globalImageId">
            
        <div class="popUp" ng-controller="globalImageController">
             <div class="content-area_header">
                <div class="popUp_title fleft"> Image Detail</div>
            </div>
              <div class="inputSection col1of2 pushUp_20">
                <div class="input_Label">Name this Image</div>
                <input id="imageName" class="input_Field"/> 
                 <div >
                <img class="imagePreview" ng-src="{{imageSrc}}"  />
               </div>
               
                <label class="fileContainer">
                <div id="triggerFile" class="CTA_Button Button--Gray fleft pushUp_10 ">Upload</div>
                <input onclick="imageConverter('imageFileName')" name="fileName" type="file" id="imageFileName" ng-file-select="onFileSelect($files)" > 
           
            </label>
       
                <div ng-click="saveGlobalImage()"  id="saveGlobalImage" class="CTA_Button Button--Gray pushUp_45 ">Save Image</div>
            </div>
            

        </div>
            
        </div>
            
        </div>
  
    </body>
</html>