
function Showimguploadpopup()
{
    document.getElementById('fade').style.display = 'block';
    document.getElementById('imagepopup').style.display = 'block';
    document.getElementById('addAction').style.display = 'block';
    overlay();
}
//$("#triggerfile").click(function (){
//    $('input[type=file]').click();
//    return false;
//});
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

//            uploadModule.service('fileUpload', ['$http', function ($http) {
//                    this.uploadFileToUrl = function (file, uploadUrl) {
//                        var fd = new FormData();
//                        fd.append('file', file);
//                        $http.post(uploadUrl, fd, {
//                            transformRequest: angular.identity,
//                            headers: {'Content-Type': undefined}
//                        })
//                            .success(function (data) {
//                                window.open(global_host_address + 'imagegallery.jsp', "_self");
//                            })
//                            .error(function () {
//                                alert("request unsuccessful");
//                            });
//                    };
//                }]);
//
//            uploadModule.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {
//                    
//                    $scope.uploadFile = function () {
//                        var file = $scope.myFile;
//                        console.log('file is ' + JSON.stringify(file));
//                        var uploadUrl = global_host_address + 'UploadImages';
//                        fileUpload.uploadFileToUrl(file, uploadUrl);
//                    };
//
//              }]);
//          
           var uploadModule = document.getElementById('uploadModule');

            angular.element(document).ready(function() {
                   angular.bootstrap(uploadModule, ['uploadModule']);
            });

            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {
                    
                $scope.showData = function(){
                     alert("showData");
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
                        
                 var del = confirm("Do you really want to delete this image?");
                    if (del === true) {
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
                        });
                }
                else {

                }
                        
                    };
                $scope.uploadFile = function(){
                    alert("ok");
                    $("#myFile").upload("UploadImages",function(success){
                       $("#fileuploaddiv").hide();
                       $scope.showImages();
                   });
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
            