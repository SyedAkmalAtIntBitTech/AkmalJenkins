/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */


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
        var imageData=  base64ImgString = data;
          
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
   
var userImageConversion = function ($scope, fileReader, $http) {
    $scope.imageSrc ="images/uploadPhoto.svg";
    
    $scope.getFile = function () {
        $scope.progress = 0;
        fileReader.readAsDataUrl($scope.file, $scope)
                      .then(function(result) {
                       var resultScope=  $scope.imageSrc = result;
                  
                      });
    };
    
    $scope.saveUserImage = function () {
        var imageFileName = $("#imageFileName").val();
        var imageTypeData = imageFileName.split(".").pop().toLowerCase();
        if(imageTypeData===""){
              alert("No logo uploaded, Please upload your Logo.");
        }
        else{
        if((imageTypeData==="png")||(imageTypeData==="jpg")||(imageTypeData==="jpeg")){
        var imgDataObj = getImageData();
        var globalImage = {"imageType": imageTypeData, "imageData": imgDataObj.base64ImgString};
            $.ajax({
                method: 'POST',
                url: getHost() + '/onboarding/saveCompanyLogo',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(globalImage)
            }).success(function (data, status, headers, config) {
                if(JSON.stringify(data.d.message) != "null")
                {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));                           
                window.open(getHost() + 'signup/choosepalette', "_self");
                }
                else
                {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages))); 
                }
                
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
    }
    else{
                alert("Invalid File Type!\n Please choose valid Image format.");
            }
    
        }
    
    };
    };