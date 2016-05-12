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
     
    $scope.getFile = function () {
        $scope.progress = 0;
        fileReader.readAsDataUrl($scope.file, $scope)
                      .then(function(result) {
                       var resultScope=  $scope.imageSrc = result;
                  
                      });
    };
   var editImageTypeGlobal = "";
   $scope.editImagePopUp = function (globalImageId) {
       var editImageName="";
    $("#editImage").show();
    $("#addOrganizationPopupDivEditImage").show();
       
               $http({
                    method : 'GET',
                    url : getHost()+ '/getGlobalImageById?globalImageId='+globalImageId,
                }).success(function(data, status, headers, config) {     
                 $scope.getGlobalImageDetails= data.d.details[0];
                 $("#editImageName").val(data.d.details[0].imageName);
                 var imageTypeData = (eval(JSON.stringify(data.d.details[0].imageName.split('.')[0]))); 
                 var imageType = (eval(JSON.stringify(data.d.details[0].imageName.split('.')[1]))); 
                 editImageName= $("#editImageName").val(imageTypeData);
                 editImageNameGlobal =editImageName;
                  $scope.getDeleteId= data.d.details[0].globalImageId;
                   $("#getDeleteId").val(data.d.details[0].globalImageId);
                  $scope.getimageData= data.d.details[0].imageData;                
                 $("#imageData").attr('src','data:image;base64,'+data.d.details[0].imageData);
                }).error(function(data, status, headers, config) {
               alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    
};
   
    $scope.saveGlobalImage = function () {
       $("#saveGlobalImage").unbind();
        var imageName = $("#imageName").val();
        var imageData = $("#imageData").val();
        var imageFIleNameData = $("#imageFileName").val();
        var imageTypeData = imageFIleNameData.split(".").pop().toLowerCase();
         if (imageName === "") {
            alert("Please enter the image Name");
            $("#imageName").focus();
            return false;
        }
        if(imageTypeData=="")
        {
            alert("No logo uploaded, Please upload your Logo.");
        }
        else{
         if((imageTypeData==="png")||(imageTypeData==="jpg")||(imageTypeData==="jpeg")){
        var imgDataObj = getImageData();
        var globalImage = {"imageName": imageName, "imageType": imageTypeData, "imageData": imgDataObj.base64ImgString};
       
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveGlobalImage',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(globalImage)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));                           
                window.open(getHost() + 'admin/globalimage', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
     
    }
    else{
                alert("Invalid File Type!\n Please choose valid Image format.");
            }
        }
    };
    
    
     $scope.updateGlobalImage = function () {
        var globalImageId=  $("#getDeleteId").val();
        var imageName = $("#editImageName").val();
        var imageFileData = $("#editImageFileName").val();
        var imageTypeData = imageFileData.split(".").pop().toLowerCase();
        var imageData =   $("#imageData").attr('src');
        var globalImageUpdate = {"globalImageId":globalImageId,"imageName": imageName, "imageType": "png" || "jpg" || "gif", "imageData": imageData};
        
         $.ajax({
                method: 'POST',
                url: getHost() + '/updateGlobalImage',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(globalImageUpdate)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));                      
                window.open(getHost() + 'admin/globalimage', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
       
    };
    
    
     $scope.deleteImage=function (){
         var deleteGLobalImageId=  $("#getDeleteId").val();
            var deleteImageData=confirm(deleteGlobalImage);
            if(deleteImageData===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+'/deleteGlobalImage?globalImageId='+deleteGLobalImageId
                }).success(function(data, status, headers, config) {
                    
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'admin/globalimage', "_self");
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
        }
    
   
    $scope.getGlobalImage= function (){
        
               $http({
                    method : 'GET',
                    url : getHost()+ '/getAllGlobalImages'
                }).success(function(data, status, headers, config) { 
                    for(i=0;i<=data.d.details.length;i++)
                    {
                        var imageName = data.d.details[i].imageName;
                        var globalImageName= $("#globalImageName").val(imageName);
                         $("#sd").val("asdsad");
                      
                    $scope.getAllGlobalImages= data.d.details;
                    $scope.url= getHost()+"downloadImage?imageType=GLOBAL_IMAGE&companyId=1&imageName=";
                    }
                    
                    //"getHost()+ '/downloadImage?imageType=GLOBAL_IMAGE&imageName='+globalImageName+'&companyId=1'"
                
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
          base64ImgString = data;
          
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
