<%-- 
    Document   : imageuploadpopup
    Created on : 1 Jan, 2016, 5:55:20 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel="shortcut icon" href="favicon.png">
    <script src="js/popup.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="fade" class="black_overlay" ></div>
        <div class="pop-up-background" id="imagepopup">
        <div class="pop-up-container-galleryselect"> 
            <div class="pop-up-header">
                <div class="exit-button-detail" id="closeimguploadpopup">
                   <a class=" exit-button-icon" href="">
                       <img  type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"/>
                   </a>
            </div>
                <div class="pop-up-title-gallery fleft col-9of10"> 
                    <div class="pop-up-title-h1-gallery fleft"> Upload an Image:</div>
                </div>
            </div>
            <div class="pop-up-inner-gallery ">
                <div class="imageGallery-inner-popup">
                    <div class="imageUpload-container">
<!--                        <div class="imageUpload-drag">
                            <div class="imageUpload-drophere drop-instruction" > Drop Image Here</div>
                        </div>
                        <div class="imageUpload-browse drop-instruction"> or Browse your local files:</div> -->
                        <div class="browse-button">
                            <div id="triggerfile" class="md-button gray-button">Browse your Images</div>
                            <input type="file" id="myfile" style="opacity:0;margin-top:-30px;width:100%;height:30px;cursor:pointer;"/>
                        </div>
                    </div>
                     
                </div>
                    <div class="add-action-button md-button button-text-1 uploadimgbtn" id="upload" ng-click="uploadFile()" > Upload Image</div>

            </div>
            </div>
        </div>
    </body>
</html>
