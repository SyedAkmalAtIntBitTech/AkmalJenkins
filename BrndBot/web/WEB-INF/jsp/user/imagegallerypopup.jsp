<%-- 
    Document   : imagegallerypopup
    Created on : 29 Apr, 2016, 6:26:26 PM
    Author     : sandeep
--%>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<div class="popUp_background-dark displayNone" id="imagePopUp" ng-app="imageGallery">
    <div class="popUp_CenterVert" ng-controller="displayImageFromGallery">
        <div class="popUp-top-wide lear" >
            <span >
                <div class="popUp_header clear">
                    <div class="pane_title fleft h2 col1of2">Please Select an Image</div>
                    <div class="popUp_close fright" id="hidepopup">
                        <img type="image/svg+xml" src="../images/Pop-Up-Exit-Button.svg" class="fleft popUp_close-icon cursor-shape">
                    </div>
                </div>
                <div class="popUp_subheader clear">
                    <span  class="popUp_subheader-tabs-active fleft cursor-shape" id="showGallery">From Gallery</span>
                    <span  class="popUp_subheader-tabs fleft cursor-shape" id="shoeUploadimage">Upload Image</span>
                </div>
                <div class=" gallery-padding clear gallery-pane">
                    <span id="gallerySpan" ng-init="getUserImaages()"> 
                        <div class="gallery-item-wrap-selected fleft" ng-repeat="images in datalists">
                            <div class="gallery-image-wrap">
                                <img id="{{images.companyImagesId}}"  src="/BrndBot/downloadImage?imageType=GALLERY&imageName={{images.imageName}}&companyId=${companyId}" ng-click="selectImageToPost(images.imageName, 'gallery',${companyId})" alt="alt_text" border="0" align="center" class="gallery-image" />
                            </div>
                            <div class="gallery-name"> Photo {{images.companyImagesId}}</div>
                            <div class="gallery-settings"></div>
                        </div>
                    </span>
                    <span class="displayNone" id="uploadImageSpan">
                        <div class="logoUpload-wrap">
                            <img type="image/svg+xml" src="images/uploadPhoto.svg" class="logoUpload-svg" style="cursor:pointer;">
                        </div>
                        <div class="Add_Button Button--Gray logoUpload--button">Upload Image
                            <input type="file" name="filesToUpload1[]" id="filesToUpload1" class="upload fileupld" onchange="changeimagetext1()" file-model="myFile1">
                        </div>
                    </span>
                </div>
            </span>
        </div>
        <div class="popUp-bottom-wide" id="uploadImageButton" ng-click="addImage()">
            <span class="clickable-area">
                <div class="popUp-bottom-link">ADD IMAGE</div>
            </span>
        </div>
        <div class="popUp-bottom-wide displayNone" id="addImageButton"  ng-click="uploadFile1()">
            <span class="clickable-area">
                <div class="popUp-bottom-link">ADD IMAGE</div>
            </span>
        </div>

    </div>
</div>
