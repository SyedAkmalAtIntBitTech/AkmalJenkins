function Showimguploadpopup()
{
    document.getElementById("imagetext").value="";
    document.getElementById("filesToUpload").value="";    
    document.getElementById('fade').style.display = 'block';
    document.getElementById('imagepopup').style.display = 'block';
    document.getElementById('addAction').style.display = 'block';
    
    overlay();
}
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
                                alert(requesterror);
                            });
                    };
                }]);

            uploadModule.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {
                    alert();

                    $scope.uploadFile = function () {
                        $("#imagetext").val(imagetext);
                        if ($("#imagetext").val() === "") {
                            alert(chooseimage);
                            return false;
                        }
                        else
                        {
                            var filename=$("#imagetext").val();
                            alert(filename);
                            var array=filename.split('.');
                            var length=array.length;
                            var extenion=array[length-1];
                            var error=1;
                            switch (extenion)
                            {
                                case 'jpg':
                                        error=0;
                                        break;
                                case 'png':
                                        error=0;
                                        break;
                                case 'jpeg':
                                        error=0;
                                        break;
                                case 'JPG':
                                        error=0;
                                        break;
                                case 'PNG':
                                        error=0;
                                        break;
                                case 'JPEG':
                                        error=0;
                                        break;
                                case 'svg':
                                        error=0;
                                        break;
                                case 'SVG':
                                        error=0;
                                        break;
                                case 'bmp':
                                        error=0;
                                        break;
                                case 'BMP':
                                        error=0;
                                        break;
                                case 'TIF':
                                        error=0;
                                        break;
                                case 'tif':
                                        error=0;
                                        break;
                                case 'gif':
                                        error=0;
                                        break;
                                case 'GIF':
                                        error=0;
                                        break;
                                case 'PSD':
                                        error=0;
                                        break;
                                case 'psd':
                                        error=0;
                                        break;
                                case 'yuv':
                                        error=0;
                                        break;
                                case 'YUV':
                                        error=0;
                                        break;
                                case 'THM':
                                        error=0;
                                        break;
                                case 'thm':
                                        error=0;
                                        break;
                                case 'PSPIMAGE':
                                        error=0;
                                        break;
                                case 'pspimage':
                                        error=0;
                                        break;
                            }
                            if(error===1)
                            {
                                $("#imagetext").val("");
                                alert(errorimagefile);
                                return false;
                            } 
                        }
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
                            alert(nodataerror);
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
                                alert(imagedeletesuccess);
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
            