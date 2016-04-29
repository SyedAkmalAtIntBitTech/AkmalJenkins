<%-- 
    Document   : imagegallerypopup
    Created on : 29 Apr, 2016, 6:26:26 PM
    Author     : sandeep
--%>

<div class="popUp_background-dark displayNone" id="imagePopUp" ng-controller="displayUserImages">
            <div class="popUp_CenterVert ">
                <div class="popUp-top-wide clear">
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
                        <span id="gallerySpan">
                            <div class="gallery-item-wrap-selected fleft" ng-repeat="">
                                <div class="gallery-image-wrap">
                                    <!--<img id="{{images.companyImagesId}}"  src="/BrndBot/downloadImage?imageType=GALLERY&imageName={{images.imageName}}&companyId=${companyId}" alt="alt_text" border="0" align="center" class="gallery-image" />-->
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                            <div class="gallery-item-wrap fleft">
                                <div class="gallery-image-wrap">
                                    <img src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940906817e9a2bbb57ca_Deafult-Image_680x330.jpg" alt="alt_text" border="0" align="center" class="gallery-image">
                                </div>
                                <div class="gallery-name"> Photo 1</div>
                                <div class="gallery-settings"></div>
                            </div>
                        </span>
                        <span class="displayNone" id="uploadImageSpan">
                            <div class="logoUpload-wrap">
                                <img type="image/svg+xml" src="images/uploadPhoto.svg" class="logoUpload-svg" style="cursor:pointer;">
                            </div>
                            <a href=".html">
                                <div class="Add_Button Button--Gray logoUpload--button">Upload Image</div>
                            </a>
                        </span>
                    </div>
                </div>
                <div class="popUp-bottom-wide">
                    <a href="/Social_Sequence/Twitter/SocialSequence-Twitter-imageAdded.html" class="clickable-area">
                        <div class="popUp-bottom-link">ADD IMAGE</div>
                    </a>
                </div>
            </div>
        </div>
