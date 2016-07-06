/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
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
function emailBlocksController($scope, $http, fileReader) {
     $scope.imageSrc ="images/uploadPhoto.svg";
      $scope.getFile = function () {
        $scope.progress = 0;
        fileReader.readAsDataUrl($scope.file, $scope)
                      .then(function(result) {
                       var resultScope=  $scope.imageSrc = result;            
                  
                      });
    };
    
    $scope.emailBlockModelId = function () {
        var qs = (function(a) {
            if (a == "") return {};
            var b = {};
            for (var i = 0; i < a.length; ++i)
            {
                var p=a[i].split('=', 2);
                if (p.length == 1)
                    b[p[0]] = "";
                else
                    b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
            }
            return b;
        })(window.location.search.substr(1).split('&'));

        $scope.emailBlockModelId = qs["emailBlockModelId"];    
            
    };
    
    $scope.emailBlocksModel = function () {

        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailBlockModel'
        }).success(function (data, status, headers, config) {
            $scope.emailBlocksModelData = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    };
    
    
     $scope.getEmailModelById = function () {
        var emailBlockIdTag = $("#emailBlockIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getEmailBlockModelById?emailBlockModelId=' + emailBlockIdTag
        }).success(function (data, status, headers, config) {
            $('.fr-element').html(eval(JSON.stringify(data.d.details[0].htmlData)));
            $('#showFileName').text(eval(JSON.stringify(data.d.details[0].imageFileName)));
              $('#imageEditSrc').attr('src','data:image;base64,'+(data.d.details[0].imageFileData));
            $scope.getEmailModels = data.d.details[0];
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };


    $scope.addEmailBlockModel = function () {
        var emailBlockModelName = $("#emailModelName").val();
        var htmlData = $("#edit").froalaEditor('html.get');
        var imgDataObj = getImageData();
        var emailBlockModel = {"emailBlockModelName": emailBlockModelName, "htmlData": htmlData, "imageFileName": imgDataObj.imageFileName, "imageFileData": imgDataObj.base64ImgString};
        var validate = function () {
            if (emailBlockModelName === "") {
                alert("Please enter Template Name!");
                $("#emailModelName").focus();
                return false;
            }
            if (htmlData === "") {
                alert("Please enter Html data!");
                $("#edit").focus();
                return false;
            }
            if (imgDataObj.imageFileName === "") {
                alert("Please Select an Image!");
                $("#imageFileName").focus();
                return false;
            }
            var fileType = imgDataObj.imageFileName.split(".")[1];
            if ((fileType === "png") || (fileType === "gif") || (fileType === "jpg") || (fileType === "jpeg")) {
                return true;
            } else {
                alert("Please Select only Image file!");
                $("#imageFileName").focus();
                return false;
            }
        };
        if (validate()) {
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveBlockModel',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailBlockModel)
            }).success(function (data, status, headers, config)
            {

                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/emailblockmodels', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }

    };


    $scope.deleteEmailBlockModel= function (emailBlockModelId) {
        var deleteEmailBlocks = confirm(deleteEmailBlockPrompt);
        if (deleteEmailBlocks === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteBlockModel?emailBlockModelId=' + emailBlockModelId,
            }).success(function (data, status, headers, config) {
                $scope.getEmailModelById = data.d.details;
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/emailblockmodels', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };


    $scope.updateEmailBlockModel = function () {
        var emailBlockModelId = $("#emailBlockIdTag").val();
        var emailBlockModelName = $("#emailModelNameTag").val();
        var htmlData = $("#edit").froalaEditor('html.get');
        var imgDataObj = getImageData();
         var imageDataMethod= $("#imageEditSrc").attr('src');
        var emailBlockModel = {"modelId":emailBlockModelId, "emailBlockModelName": emailBlockModelName, "htmlData": htmlData, "imageFileName": imgDataObj.imageFileName, "imageFileData": imageDataMethod};
        var validate = function () {
            if (emailBlockModelName === "") {
                alert("Please enter Template Name!");
                $("#emailModelNameTag").focus();
                return false;
            }
            if (htmlData === "") {
                alert("Please enter Html data!");
                $("#edit").focus();
                return false;
            }
            if (imgDataObj.imageFileName === "") {
                return true;
            } else {
                var fileType = imgDataObj.imageFileName.split(".")[1];
                if ((fileType === "png") || (fileType === "gif") || (fileType === "jpg") || (fileType === "jpeg")) {
                    return true;
                } else {
                    alert("Please Select only Image file!");
                    $("#imageFileName").focus();
                    return false;
                }
            }

        };
        if (validate()) {
            $.ajax({
                method: 'POST',
                url: getHost() + '/updateBlockModel',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailBlockModel)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/emailblockmodels', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        } 
    };


}


