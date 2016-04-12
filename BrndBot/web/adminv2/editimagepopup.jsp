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
    <body >
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer" ></div>
        <div class="popUp_background" id="editImage">
            
        <div class="popUp" ng-controller="globalImageController">
            <div class="content-area_header" ng-init="getDeleteId()">
                <div id ="getDeleteId">{{getDeleteId}}</div>
                 
                <div class="popUp_title fleft"> Image Detail </div>
                <div id="deleteGlobalImage" class="CTA_Button Button--Delete fright" ng-click="deleteImage(getGlobalImageDetails.globalImageId)">Delete Image</div>
            </div>
            <div ng-init="getimageData()"> 
              <div class="inputSection col1of2 pushUp_20">
                <div class="input_Label">Edit this Image</div>  
                <input id="editImageName" class="input_Field"/> 
                 <div>
                <img id="imageData" class="imagePreview" ng-src="{{imageData}}"  />
               </div>
               
                <label class="fileContainer">
                <div id="triggerFile" class="CTA_Button Button--Gray fleft pushUp_10 ">Upload</div>
                <input onclick="imageConverter('editImageFileName')" name="fileName" type="file" id="editImageFileName" ng-file-select="onFileSelect($files)" > 
           
            </label>
       
                <div ng-click="updateGlobalImage()"  id="saveGlobalImage" class="CTA_Button Button--Gray pushUp_45 ">Update Image</div>
            </div>
            

        </div>
            
        </div>
            
        </div>
  
    </body>
</html>
