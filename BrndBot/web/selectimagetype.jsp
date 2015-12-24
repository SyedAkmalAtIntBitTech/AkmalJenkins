<%-- 
    Document   : selectimagetype
    Created on : 21 Dec, 2015, 5:14:48 PM
    Author     : satyajit-roy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="style_detail_overlay-1.css">
    <link rel="stylesheet" type="text/css" href="normalize.css">
    <link rel="shortcut icon" href="favicon.png">
</head>    
    <body>
        
            <div id="fade" class="black_overlay"></div>
            <input type="hidden" name="selectedtype" id="selectedtype" value=""></input>
            <input type="hidden" name="selectedid" id="selectedid" value=""></input>
            <input type="hidden" name="social" id="social" value="social"></input>
            <div id="addContact">
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                        <div class="pop-up-exit-icon" id="close"> 
                            <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                        </div>
                    </div> 
                    <div class="pop-up-container pop-up-container-imageSelection"> 
                <div class="pop-up-title pop-up-title-h1"> Select an Image Type</div>
                <div class="pop-up-inner-imageSelection">
                    <div class="col-8of10  center">
                        <div class="col-4of10 pushright fleft">
                            <div class="image-selection-button" id="uploadimage" ng-click="showImages()"><p class="algn">Upload an Image or from gallery</p></div>
                        </div>
                        <div class="col-4of10 fright">
                            <div class="image-selection-button" id="gotoimageeditor"><p class="algn">Image Editor</p></div>
                        </div>
                    </div>
                </div>

            </div>
            </div>
            </div>
            
            <div id="imageGalleryDiv">
                  <div class="top-nav1">
                    <div class="page-title-bar col-1of1"> 
                        <div class="page-title-regular page-title-font">Image Gallery</div>
                        <div class="page-cta-container">
                             <a href="" class="space-right fleft">
                                <div class=" add-button22 md-button" id="addimage"> Add Image</div>    
                            </a>
                            <a href="" class=" fleft">
                                <div class=" add-button md-button"> Upload an Image</div>                                
                            </a>
                        </div>
                    </div>
                </div>
                <div class="gallery-page-background">
                    <div class="pop-up-exit-container">
                        <div class="pop-up-exit-icon" id="closeimagegallerydiv"> 
                            <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                        </div>
                    </div> 
                    <div class="gallery-page-content-container fleft">      
                    <!--Inner Content Conatiner GENERIC-->
                        <div class="page-inner-content-container fleft">
                            <div class="fleft content">
                            <!--List Starts Here-->
                            
                                <div class="imageGallery-container  fleft" ng-repeat="images in datalistimages" >
                                    <div class="imageGallery-card" id="div{{images.id}}">
                                        <div class="galCard-image col-1of1">
                                            <img id="{{images.id}}-{{images.image_name}}" value="{{images.image_name}}" class="banner galleryImge" ng-src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}"  onclick="getImageId(this.id)" alt="image"/>                                                            
<!--                                            <img class="" id="{{images.id}}" onclick="getImageId(this.id)" alt="image" src="http://wp.nootheme.com/yogi/wp-content/uploads/2015/04/blog_10.jpg"/>-->
                                        </div>
                                        <div class="galCard-content col-1of1"> 
                                            <div class="galImage-name">Image Name</div>
                                            <div class="galImage-description">Added on Nov 26 2015</div>
                                            <div class="galCard-divider"></div>
                                              <img type="image/svg+xml" data="/Icons/settings.svg" class="galCard-settingsicon fleft galleryImge" style="cursor:pointer;"/>
                                             <img type="image/svg+xml" data="/Icons/trash.svg" class="galCard-trashicon fleft galleryImge" style="cursor:pointer;"/>

                                        </div>
                                    </div>
                                </div>
                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
            <div id="imageUploadDiv2">
                
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                        <div class="pop-up-exit-icon" id="close"> 
                            <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"/>
                        </div>
                    </div> 
                <div class="pop-up-container pop-up-container-newaction"> 
                    <div class="pop-up-title pop-up-title-h1">Upload an Image</div>
                     <div class="pop-up-exit-container">
                        <a href="/Newest_Files/YourPlan.html" class="pop-up-exit-icon">
                            <object type="image/svg+xml" data="/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"> </object>
                        </a>
                    </div> 
                    <div class="pop-up-inner">
                        <div class="input-field-container ">
                            <div class="input-header "> Click to browse your photos:</div>
                            <div class="input-field-textfield input-placeholder">Dropdown of Links</div>
                        </div>
                    </div>
                </div>
            </div>
            
                <div class="pop-up-cta-container pop-up-cta-container-newaction">
                <a href="/Newest_Files/MarketingProgram_Actions.html">
                    <div class="pop-up-cta-button-full"> Add Link</div>
                </a>
            </div> 
                
            </div>
            
            
            
<!--            <li id="imageGalleryDiv">
            <ul id="imageGallerySection" style="height: 500px;width: 300px;position: relative;right: 80px;left:0px;">
                <p class="SH1">PLEASE SELECT AN IMAGE FROM THE GALLERY</p>
                <a class="boxclose" id="boxclose"></a>
                <p class="BT2 ptr" id="galleryupload">upload image</p>
                <li class="paginationclass" ng-repeat="images in datalistimages| pagination: curPage * pageSize | limitTo: pageSize">                                                          
                    <img id="{{images.id}}" class="img-responsive lookchooser5 ptr" ng-src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}"  onclick="showImageName('{{images.user_id}}','{{images.image_name}}')" width="200px"/>                                                            
                </li>
            </ul>
                                               <input id="closeimagespopup" type="Button" value="close"/>  
        </li>-->
            
    
    </body>
</html>
