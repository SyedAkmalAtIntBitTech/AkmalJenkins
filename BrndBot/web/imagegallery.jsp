<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>image gallery</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <style>
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
               font-size:20px;
              position: relative;
                left:-15px;
                padding: 7px;
            }
        </style>
     <style>
            .fileUpload {
                position: relative;
                overflow: hidden;
                margin: 10px;
            }
            .fileUpload input.upload {
                position: absolute;
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                font-size: 20px;
                cursor: pointer;
                opacity: 0;
                filter: alpha(opacity=0);
            }

        </style>
        <script>
            
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

        </script>
        <script>
            
            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {

                $scope.showData = function(){
//                     alert("showData");
                 $scope.curPage = 0;
                 $scope.pageSize = 4;

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
            
        </script>
        <style>
            .paginationclass{
    
                margin: 19px 28px;    
                }
                .paginationclass span{
                    margin-left:15px;
                    display:inline-block;
                }
                .pagination-controle li{
                    display: inline-block;
                }
                .pagination-controle button{
                    font-size: 12px;
                    margin-top: -26px;
                    cursor:pointer;

                }
                .pagination{
                    margin:5px 12px !important;
                }
            
        </style>
    </head>
    <body ng-app="rootApp">
        <div> 
               <div class="row">
                   <jsp:include page="mainmenu.html"/>
    
                    <div class="col-md-10" >
                        <div class="col-md-10 " id="uploadModule">
                            <p id="text3"> Image Gallery </p>
                            <div>

                                <div class="row">
                                    <div class="span1"></div>
                                </div>
                                <div id="contentdiv">
                                    <div class="row">
                                        <div class="col-md-4 col-md-offset-5">
                                            <p id="comment1"> Upload Images </p>
                                            <p id="comment2">Please upload a .PNG, .JPEG,</p> <br><br>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-5">
                                            <img id="logoimage" src="images/question-mark-black-background.png" alt="logo" class="img-responsive" width="80" height="100"><br><br><br>
                                        </div>
                                    </div>

                                    <div class="group" ng-controller="myCtrl">
                                        <div class="span5 col-md-offset-5 ">
                                            <button id="Servicecontinue" class="btn btn-info" disabled ng-click="uploadFile()">CONTINUE</button><br><br>
                                            <div class="fileUpload btn ">
                                                <span>Click to upload a image</span>
                                                <input type="file" name="filesToUpload[]"  id="filesToUpload" class="upload"  file-model="myFile" />
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            
                         </div>
                    </div>
                    <div class="col-md-6">
                        
                        <div ng-module="imagegallery">
                                <div ng-controller="samplecontoller" ng-init="showData()">

                                    <ul style="width:800px;">
                                         <li class="paginationclass" style="font-weight:bold;">Image Gallery</li>            
                                         <li class="paginationclass" ng-repeat="images in datalists | pagination: curPage * pageSize | limitTo: pageSize">
                                                    <div>
                                                        <img id="{{images.id}}" class="img-responsive lookchooser5" src="images/Gallery/{{images.user_id}}/{{images.image_name}}"  onclick="showText({{images.id}})" width=100 height=120 />
                    <!--                                        <img id="{{images.id}}" class="img-responsive lookchooser1" src="images/Gallery/10/10_apple-311246_640.jpeg" onclick="showText({{images.id}})" width=250 height=150 />-->
                                                        <button name="delete" id="delete" ng-click="deleteImage(images.id, images.user_id, images.image_name)">Delete</button>
                                                    </div> 
                                                    <div><p id=''></p></div>
                                                <div></div><p>&nbsp;</p>
                                         </li>
                                    </ul> 

                                <div class="pagination pagination-centered" ng-show="datalists.length">
                                <ul class="pagination-controle pagination">
                                 <li>
                                  <button type="button" class="btn btn-primary" ng-disabled="curPage == 0"
                                 ng-click="curPage=curPage-1"> &lt; PREV</button>
                                 </li>
                                 <li>
                                 <span>Page {{curPage + 1}} of {{ numberOfPages() }}</span>
                                 </li>
                                 <li>
                                 <button type="button" class="btn btn-primary"
                                 ng-disabled="curPage >= datalists.length/pageSize - 1"
                                 ng-click="curPage = curPage+1">NEXT &gt;</button>
                                 </li>
                                </ul>
                                </div>

                        </div>
                        
                    </div>
                    
               </div>      
            </div>  
        
          </div>    
        
    </body>
<script>
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
                            document.getElementById("Servicecontinue").disabled = false;
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
</html>
