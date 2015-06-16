<%-- 
    Document   : UploadLogo
    Created on : May 20, 2015, 9:14:32 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/main1.css">
        <script src="js/configurations.js"></script>
        
        <title>upload logo</title>
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
            var myApp = angular.module('myApp', []);

            myApp.directive('fileModel', ['$parse', function ($parse) {
                return {
                    restrict: 'A',
                    link: function(scope, element, attrs) {
                        var model = $parse(attrs.fileModel);
                        var modelSetter = model.assign;

                        element.bind('change', function(){
                            scope.$apply(function(){
                                modelSetter(scope, element[0].files[0]);
                            });
                        });
                    }
                };
            }]);

            myApp.service('fileUpload', ['$http', function ($http) {
                this.uploadFileToUrl = function(file, uploadUrl){
                    var fd = new FormData();
                    fd.append('file', file);
                    $http.post(uploadUrl, fd, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })
                    .success(function(){
                        window.open('http://localhost:8084/BrndBot/paletteChooser.jsp',"_self");
                    })
                    .error(function(){
                    });
                };
            }]);

            myApp.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
                $scope.uploadFile = function(){
                    var file = $scope.myFile;
                    console.log('file is ' + JSON.stringify(file));
                    var uploadUrl = global_host_address + 'uploadLogo';
                    fileUpload.uploadFileToUrl(file, uploadUrl);
                    alert("Image uploaded successfully");
                };

            }]);
            
        </script>
    </head>
    <body>
<!--        <div ng-controller = "myCtrl">
            <input type="file" file-model="myFile"/>
            <button ng-click="uploadFile()">upload me</button>
        </div> -->
        <div class="container">
           
            <div class="row">
                <div class="span1">
                    <a href="personality.html">go back</a>
                    <a href="palettechooser.html" class="pull-right">next</a>
                </div>
            </div>
                <div id="contentdiv">
                <div class="row">
                    <div class="col-md-4 col-md-offset-5">
                        <p id="comment1"> Upload your logo </p> 
                        <p id="comment2">Please upload in a .PNG, .JPEG,</p> <br><br>
                    </div>
                </div>
                    <div class="row">
                        <div class="col-md-6 col-md-offset-5">
                          
                            <img id="logoimage" src="images/question-mark-black-background.png" alt="logo" class="img-responsive" width="80" height="100"><br><br><br>
                        </div>
                        
                        </div>
                        
                    <div class="group" ng-controller="myCtrl">
                          
                            <div class="span5 col-md-offset-5 ">
                                <button id="Servicecontinue" type="submit"  class="btn btn-info" disabled ng-click="uploadFile()">CONTINUE</button><br><br>
                                <div class="fileUpload btn ">
                                        
                                   <span>Click to upload a logo</span>
                                   <input type="file" name="filesToUpload[]"  id="filesToUpload" class="upload"  file-model="myFile" />
                                 </div>
                            </div>
                        </div> 
                    </div>
          </div> 

</body>
<script>
         var fl = document.getElementById('filesToUpload');

                   fl.onchange = function(e){ 
               var ext = this.value.match(/\.(.+)$/)[1];
               switch(ext)
               {
                   case 'jpg':
                   case 'bmp':
                   case 'png':
                   case 'jpeg':
                   case 'JPG':
                   case 'BMP':
                   case 'PNG':
                   case 'JPEG':
                   case 'tif':
                   case 'TIF':
                   case 'gif':
                   case 'GIF':    
                       alert('allowed');
                       break;
                   default:
                       alert('not allowed');
                       this.value='';
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
                    document.getElementById('logoimage').src= evt.target.result ;
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
