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
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
         <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
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
                margin-top:80px;
                margin-left: -345px;
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
            li{
                list-style: none;
            }

        </style>
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

        </script>
        <script>
            
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
            
        </script>
        <style>
            .paginationclass li{
                display: table-cell;
                padding: 30px 0px 0px 45px;
                position:relative;
                left:-5em;
                display:inline-block;
                }
                .paginationclass span{
                    margin-left:15px;
                    
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
                .uploadpos > div{
                    position: relative;
                    text-align: center;
                    vertical-align: central;
                }
        </style>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body ng-app="rootApp">
        <div> 
               <div class="row">
                   <jsp:include page="mainmenu.html"/>
    
                    <div class="col-md-12" >
                        <div class="col-md-4 col-md-offset-2 " id="uploadModule">
                            <p style="width:300px;" id="text3" class="MH2"> Your Gallery </p>
                           
                    <div class="col-md-4 col-md-offset-0" style="position:relative;left:-33px;height:390px; width:650px; overflow-y: scroll; overflow-x: hidden;">
                       <div ng-module="imagegallery" >
                            <div   ng-controller="samplecontoller" ng-init="showData()" >

                                    <ul class="paginationclass" style="width:700px;">
                                        <li  ng-repeat="images in datalists | pagination: curPage * pageSize | limitTo: pageSize" >
                                            <div>
                                                        <img id="{{images.id}}" class="lookchooser5 ptr" src="/BrndBot/DownloadImage?image_type=GALLERY&image_name={{images.image_name}}&user_id={{images.user_id}}" onclick="showText({{images.id}})" width="110" height="100" />
                    <!--                                        <img id="{{images.id}}" class="img-responsive lookchooser1" src="images/Gallery/10/10_apple-311246_640.jpeg" onclick="showText({{images.id}})" width=250 height=150 />-->
                                                            <!--<button name="delete"  id="delete" ng-click="deleteImage(images.id, images.user_id, images.image_name)">Delete</button>-->
                                                    </div> 
                                         </li>
                                    </ul> 
                                </div>
                        </div>
                    </div>  
                        
<!--                                <div id="contentdiv">
                                  
                                   
                                  

                                    <div class="group" ng-controller="myCtrl">
                                        <div class="span5 col-md-offset-5 ">
                                            <button id="Servicecontinue" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="uploadFile()">CONTINUE</button><br><br>
                                            <div class="fileUpload btn ">
                                                <span>Click to upload a image</span>
                                                <input type="file" name="filesToUpload[]" id="filesToUpload" class="upload" file-model="myFile" />
                                            </div>
                                        </div>
                                    </div>

                                </div>-->
                          
                            
                         </div>
                        
                        <div  style="position:relative;text-align:center;width:350px;background-color: #e5e5e5;left:66em;right:0em;height:100%;">
                            <p class="MH2" style="position: relative;top:-260px;"> UPLOAD A PHOTO</p>
                         
                            <div class="group " ng-controller="myCtrl" style="position: relative;top:40%;left:100px;">
                                    <div class="fileUpload btn "  >
                                        <span class="SP2">Click to upload a image</span>
                                                <input type="file" name="filesToUpload[]" id="filesToUpload" class="upload" file-model="myFile" />
                                            </div>    
                                <button  id="Servicecontinue" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="uploadFile()">UPLOAD</button>
                                       
                                    </div>
                        </div>
                    </div>
                  
            </div>  
        
          </div>    
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
    </body>
  
</html>
