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
    <script src="js/socialimage.js" type="text/javascript"></script>
    <script>
        $("#Servicecontinue").hide();
        var rootApp = angular.module('rootApp', ['uploadModule','imagegallery']);
    
        var uploadModule = angular.module('uploadModule', []);
        
            uploadModule.directive('fileModel', ['$parse', function ($parse) {
                    return {
                        restrict: 'A',
                        link: function (scope, element, attrs) {
                            var model = $parse(attrs.fileModel);
                            var modelSetter = model.assign;

                            element.bind('change', function () {
                                scope.$apply(function () {
                                    modelSetter(scope, element[0].files[0]);
                                });
                            });
                        }
                    };
                }]);

            uploadModule.service('fileUpload', ['$http', function ($http) {
                    this.uploadFileToUrl = function (file, uploadUrl) {
                        var fd = new FormData();
                        fd.append('file', file);
                        $http.post(uploadUrl, fd, {
                            transformRequest: angular.identity,
                            headers: {'Content-Type': undefined}
                        })
                            .success(function (data) {
                                window.open(global_host_address + 'imagegallery.jsp', "_self");
                            })
                            .error(function () {
                                alert("request unsuccessful");
                            });
                    };
                }]);

            uploadModule.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {

                    $scope.uploadFile = function () {
                        var file = $scope.myFile;
                        console.log('file is ' + JSON.stringify(file));
                        var uploadUrl = global_host_address + 'UploadImages';
                        fileUpload.uploadFileToUrl(file, uploadUrl);
                    };

              }]);
          
           var uploadModule = document.getElementById('uploadModule');

            angular.element(document).ready(function() {
                   angular.bootstrap(uploadModule, ['uploadModule']);
            });
           
            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {

                $scope.showData = function(){
//                     alert("showData");
                 $scope.curPage = 0;
                 $scope.pageSize = 1000;

                    $http({
                            method : 'GET',
                            url : 'GetImageGallery'
                    }).success(function(data, status, headers, config) {
                        $scope.datalists = data;
                        
                    $scope.numberOfPages = function() {
                                            return Math.ceil($scope.datalists.length / $scope.pageSize);
                                    };
                        if (data === error){
                            alert(data);
                        }
                    }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });

                    };
                    
                    $scope.deleteImage = function (image_id, user_id, image_name) {
                        var image = {"image_id": image_id, "user_id": user_id, "image_name": image_name};
                        
                        $http({
                            method: 'POST',
                            url: getHost() + 'DeleteGalleryImages',
                            headers: {'Content-Type': 'application/json'},
                            data: image
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "true") {
                                alert("Image deleted successfully");
                                window.open(getHost() + 'imagegallery.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        })
                    };

                });
                
                angular.module('imagegallery').filter('pagination', function()
                {
                 return function(input, start)
                 {
                  start = +start;
                  return input.slice(start);
                 };
                });
            
        var fl = document.getElementById('filesToUpload');

        fl.onchange = function (e) {
            var ext = this.value.match(/\.(.+)$/)[1];
            switch (ext)
            {
                case 'jpg':
                case 'png':
                case 'jpeg':
                case 'JPG':
                case 'PNG':
                case 'JPEG':
                case 'svg':
                case 'SVG':
                break;
                default:
                    alert('This type of image is not allowed');
                    this.value = '';
            }
        };

        function fileSelect(evt) {
            if (window.File && window.FileReader && window.FileList && window.Blob) {
                var files = evt.target.files;

                var result = '';
                var file;
                for (var i = 0; file = files[i]; i++) {
                    // if the file is not an image, continue
                    if (!file.type.match('image.*')) {
                        continue;

                    }
                    //            alert("only .png file");

                    reader = new FileReader();
                    reader.onload = (function (tFile) {
                        return function (evt) {
                            var div = document.createElement('div');
                            div.innerHTML = '<img style="width: 90px;" src="' + evt.target.result + '" />';
                            document.getElementById('logoimage').src = evt.target.result;
//                            document.getElementById("Servicecontinue").disabled = false;
                            $("#Servicecontinue").show();
                        };
                    }(file));
                    reader.readAsDataURL(file);
                }
            } else {
                alert('The File APIs are not fully supported in this browser.');
            }
        }

        document.getElementById('filesToUpload').addEventListener('change', fileSelect, false);
    </script>  
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
                                <div class=" add-button md-button" id="galleryupload"> Upload an Image</div>                                
                            </a>
                            
<!--                            <div class="fileUpload btn " ng-controller="myCtrl">
                                <span class="SP2" style="margin-left: 130px;">Click to upload a image</span>
                                <input type="file" name="filesToUpload[]" id="filesToUpload" class="upload" file-model="myFile">
                            </div>-->
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
               
            <div id="fileuploaddiv">
                <div class="pop-up-exit-icon" id="closefileuploadpopup"> 
                    <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;">
                </div>
                <div class="pop-up-background">
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
                                <input class="input-field-textfield input-placeholder" id="uploadFile" placeholder="Choose a file"></input>
                                <input type="file" id="uploadBtn" class="file-input"></input>
                            </div>
                        </div>
                        
<!--                    <input id="uploadFile" placeholder="Choose File" disabled="disabled" />
                        <div class="fileUpload btn btn-primary">
                            <span>Upload</span>
                            <input id="uploadBtn" type="file" class="upload" />
                        </div>-->
                        
                    </div>
                </div>
                <div class="pop-up-cta-container pop-up-cta-container-newaction">
                    <a href="/Newest_Files/MarketingProgram_Actions.html">
                        <div class="pop-up-cta-button-full"> Add To Image Gallery</div>
                    </a>
                </div> 
            </div> 
            
            <div id="fade1" class="black_overlay1"></div>
            
<!--            <a href="#" data-reveal-id="fileupload_popup" class="fileclick" style="display:none;">Click Me For A Modal</a>
            <div id="fileupload_popup" class="reveal-modal" style="left:90%;">
                <a class="close-reveal-modal">abc&#215;</a>
                <center>
                    <input id="myFile" type="file" name="myFile">
                    <input type="button" id="upload" style="margin-left: 35%;" class="button button--moema button--text-thick button--text-upper button--size-s" value="upload">
                    <input type="button" id="image1" ng-click="showImages()" value="image1" style="display: none;"><br />
                </center>
            </div>-->
    </body>
</html>
