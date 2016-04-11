/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

/* global angular */

var app = angular.module('uploadImage', []); 
 
       (function (module) {
     
    var fileReader = function ($q, $log) {
 
        var onLoad = function(reader, deferred, scope) {
            return function () {
                scope.$apply(function () {
                    deferred.resolve(reader.result);
                });
            };
        };
 
        var onError = function (reader, deferred, scope) {
            return function () {
                scope.$apply(function () {
                    deferred.reject(reader.result);
                });
            };
        };
 
        var onProgress = function(reader, scope) {
            return function (event) {
                scope.$broadcast("fileProgress",
                    {
                        total: event.total,
                        loaded: event.loaded
                    });
            };
        };
 
        var getReader = function(deferred, scope) {
            var reader = new FileReader();
            reader.onload = onLoad(reader, deferred, scope);
            reader.onerror = onError(reader, deferred, scope);
            reader.onprogress = onProgress(reader, scope);
            return reader;
        };
 
        var readAsDataURL = function (file, scope) {
            var deferred = $q.defer();
             
            var reader = getReader(deferred, scope);         
            reader.readAsDataURL(file);
             
            return deferred.promise;
        };
 
        return {
            readAsDataUrl: readAsDataURL  
        };
    };
 
    module.factory("fileReader",
                   ["$q", "$log", fileReader]);
 
}

(angular.module("uploadImage")));
     
    

app.directive("ngFileSelect",function(){

  return {
    link: function($scope,el){
      
      el.bind("change", function(e){
      
        $scope.file = (e.srcElement || e.target).files[0];
        $scope.getFile();
      });
      
    }
    
  };
  
  
});
  

var globalImageController = function ($scope, fileReader, $http) {
     console.log(fileReader);
    $scope.getFile = function () {
        $scope.progress = 0;
        fileReader.readAsDataUrl($scope.file, $scope)
                      .then(function(result) {
                          $scope.imageSrc = result;
                      });
    };
    
    $scope.saveGlobalImage = function () {
        
        var imageName = $("#imageName").val();
        var imageData = $("#imageData").val();
        var imageFIleNameData = $("#imageFileName").val();
        var imageTypeData = imageFIleNameData.split(".").pop().toLowerCase();
        var imgDataObj = getImageData();
        var globalImage = {"imageName": imageName, "imageType": imageTypeData, "imageData": imgDataObj.base64ImgString};
        if (imageName === "") {
            alert("Please enter the image Name");
            $("#imageName").focus();
        } else {
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveGlobalImage.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(globalImage)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                window.open(getHost() + 'adminv2/globalimage.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };
    
    
     $scope.deleteImage=function (){
            var deleteImageData=confirm(deleteGlobalImage);
            if(deleteImageData===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+'/deleteGlobalImage.do?globalImageId='+2
                }).success(function(data, status, headers, config) {
                    
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                   
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
        }
    
    $scope.getImageById= function (){
           var globalImageId=$("#globalImageId").val();
  
               $http({
                    method : 'GET',
                    url : getHost()+ '/getAllEmailBlocksByOrganizationId.do?globalImageId='+globalImageId,
                }).success(function(data, status, headers, config) {                  
                $scope.emailBlocks= data.d.details;
                }).error(function(data, status, headers, config) {
               alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    
    var base64ImgString = "";
    var imageFileName = "";

};
function imageConverter(id) {
    var obj =  document.getElementById(id);
   
    obj.addEventListener("change", readFile, false);
}

function readFile() {
    if (this.files.length === 1) {
        var reader = new FileReader();
        var file = this.files[0];
        var imageFileName = file.name;     
        reader.addEventListener("load", function () {
            var data = reader.result;
          var base64ImgString1=  base64ImgString = data;
          
        }, false);
        reader.readAsDataURL(file);
    }

}

function getImageData(){
 
    return {
        "imageFileName" : imageFileName,
        "base64ImgString" : base64ImgString,
       
    };
}