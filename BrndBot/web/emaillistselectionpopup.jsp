<%-- 
    Document   : selectimagetype
    Created on : 21 Dec, 2015, 5:14:48 PM
    Author     : satyajit-roy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="shortcut icon" href="favicon.png"/>
    <link rel="stylesheet" href="css/pikaday.css"/>
    <link rel="stylesheet" href="css/datepickerpikaday.css"/>
    <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
    <script src="js/pikaday.js"></script>
<!--    <script src="js/socialimage.js" type="text/javascript"></script>-->
    <script src="js/socialeditor.js" type="text/javascript"></script>
    <script src="js/ajaxfileupload.js" type="text/javascript"></script>
    <style>.arrow_top{display:none;}
        .timepicker_wrap{top:-155px !important;width: 200px;}
        #schedule_time{width:60% !important;}
    </style>
<!--    <script>

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

    </script>  -->
</head>    
    <body>
            <div id="fade" class="black_overlay"></div>
            <input type="hidden" name="selectedtype" id="selectedtype" value=""></input>
            <input type="hidden" name="selectedid" id="selectedid" value=""></input>
            <input type="hidden" name="social" id="social" value="social"></input>
                        
            <div id="sendpopup">
                <div class="pop-up-background">
                    <div class="pop-up-exit-container">
                       <div class="pop-up-exit-icon" id="closesendpopup">
                           <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                       </div>
                    </div> 
                    <div class="pop-up-container1 pop-up-container-Schedule"> 
                        <div class="pop-up-title pop-up-title-h1"> Would you like to send now or schedule for later?</div>
                        <div class="pop-up-inner-imageSelection">
                            <div class="col-8of10  center">
                                <div class="col-4of10 pushright fleft" onclick="sendEmail()">
                                    <div class="image-selection-button" id="posttofb"> 
                                        <img type="image/svg+xml" src="images/Icons/postNow.svg" class="post-button-icon" style="cursor:pointer;"> </img>
                                    </div>
                                    <div class="button-description">Send Now</div>
                                </div>
                                <div class="col-4of10 fright">
                                    <div class="image-selection-button" id="schedule">
                                        <img type="image/svg+xml" src="images/Icons/schedulePost.svg" class="schedule-button-icon" style="cursor:pointer;"> </img>
                                    </div>
                                    <div class="button-description">Schedule for Later</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="schedulepopup" ng-controller="emailSettings" id="emailSettings">
                <div class="pop-up-exit-container">
                    <div class="pop-up-exit-icon" id="closeschedulepopup">
                        <img type="image/svg+xml" src="images/Icons/Close.svg" class="exit-button-icon"></img>
                    </div>
                </div>
                <div class="pop-up-background" ng-init="getProgramNames();">
                    <div class="pop-up-container pop-up-container-newaction1"> 
                        <div class="pop-up-title pop-up-title-h1"> Schedule this Action</div>
                        <div class="pop-up-exit-container">
                            <a href="/Newest_Files/YourPlan.html" class="pop-up-exit-icon">
                                <object type="image/svg+xml" data="/Icons/Close.svg" class="exit-button-icon"> </object>
                            </a>
                        </div> 
                        <div class="pop-up-inner1">
                        <div class="popup-section-header">Save to Existing Action</div>
                        <div class="line-divider-notop"></div>
                        <div class="input-field-container">
                            <div class="input-header"> Choose Marketing Program</div>
                            <div >
                                <select name="programs" id="programs" class="full input-field-textfield2 input-placeholder" onchange="validateact();">
                                    <option value="0">--General--</option>
                                    <option ng-repeat="programs in marketing_programs" value="{{programs.program_id}}">{{programs.name}}</option>
                                </select>
                            </div>
                                <select name="email_actions" id="email_actions" class="full input-field-textfield2 input-placeholder" onchange="validateact();">
                                    <option value="0" >CUSTOM</option>
                                    <option ng-repeat="actions in email_actions" value="{{actions.id}}">{{actions.schedule_title}}</option>
                                </select>
                        </div>
                        <div class="popup-section-header pushUp-15">-Or- Create a New Action</div>
                        <div class="line-divider-notop "></div>
                        <div class="input-header">Name this Action</div>
                        <div class="">
                            <input type="text" class="input-field-textfield input-placeholder full" id="schedule_title" name="schedule_title" placeholder="TITLE" ></input>
                        </div>
                        <div class="cols-2">
                             <div class="input-field-container col-4of10 fleft pushright">
                                <div class="input-header"> Action Date </div>
                                <input type="text" readonly id="schedule_date" name="schedule_date" class="input-field-textfield input-placeholder"  placeholder="Enter Action Date"></input>
                                <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('schedule_date'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });
                                </script>
                            </div>
                             <div class="input-field-container col-4of10 fleft">
                                <div class="input-header"> Action Time </div>
                                <div class="">
                                    <input id="schedule_time" type="text" name="schedule_time" class="input-field-textfield input-placeholder" placeholder="Enter Action Time" readonly/><br>
                                    <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#schedule_time').timepicki({
                                            show_meridian:true,
                                   min_hour_value:0,
                                   max_hour_value:12,
                                   step_size_minutes:01,
                                   overflow_minutes:true,
                                   increase_direction:'up',
                                   disable_keyboard_mobile: true
                                        });
                                    </script>
                                </div>
                            </div>
                        </div>
                        </div>
                        
                    </div>
                    <div class="pop-up-cta-container pop-up-cta-container-newaction1 cur" id ="schedulethepost"  ng-click="setScheduling()">
                        <input type="hidden" value="socialmedia"/>
                        <div class="algnmnt" name="socialscheduleid" id="socialscheduleid" >SCHEDULE</div>
                    </div>
                    </div>
                </div>
            </div>
    </body>
</html>
