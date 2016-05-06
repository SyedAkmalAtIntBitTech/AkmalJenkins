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
                                $("#upload").unbind('click');
                                window.open(global_host_address + 'imagegallery.jsp', "_self");
                                $("#upload").bind('click');
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
                        var id=$("#imagetext").val();
                        if(imagefilevalidation("imagetext"))
                        {
                            var file = $scope.myFile;
                            console.log('file is ' + JSON.stringify(file));
                            var uploadUrl = global_host_address + 'UploadImages';
                            fileUpload.uploadFileToUrl(file, uploadUrl);
                        }
                    };

              }]);
          
           var uploadModule = document.getElementById('uploadModule');

            angular.element(document).ready(function() {
                   angular.bootstrap(uploadModule, ['uploadModule']);
            });

            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {

                $scope.showData = function(){
                    showOverlay();
//                     alert("showData");
                 $scope.curPage = 0;
                 $scope.pageSize = 1000;

                    $http({
                            method : 'GET',
                            url : 'GetImageGallery'
                    }).success(function(data, status, headers, config) {
                        $scope.datalists = data;
                        hideOverlay();
                        
                    $scope.numberOfPages = function() {
                                            return Math.ceil($scope.datalists.length / $scope.pageSize);
                                    };
                        if (data === error){
                            alert(data);
                        }
                    }).error(function(data, status, headers, config) {
                        hideOverlay();
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
            