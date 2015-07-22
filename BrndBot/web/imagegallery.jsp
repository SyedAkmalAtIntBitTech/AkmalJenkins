
<%-- 
    Document   : imagegallery
    Created on : Jul 6, 2015, 2:35:47 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/colpick.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
        <script src="js/prettify.js"></script>
        <script src="js/jquery.bsFormAlerts.js"></script>
        <script type="text/javascript" src="jscolor/jscolor.js"></script>

        <title>Image Gallery</title>
        <script>
            
            var myapp = angular.module('imagegallery', [ ]);

                myapp.controller('samplecontoller', function ($scope,$http) {

                $scope.showData = function( ){
                     
                 $scope.curPage = 0;
                 $scope.pageSize = 4;

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
                    }

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
            .paginationclass{
    
                margin: 19px 28px;    
                }
                .paginationclass span{
                    margin-left:15px;
                    display:inline-block;
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
            
        </style>
    </head>
    <body ng-app="imagegallery">
    <div>
            <div ng-controller="samplecontoller" ng-init="showData()">

                <ul style="width:800px;">
                     <li class="paginationclass" style="font-weight:bold;">Image Gallery</li>            
                     <li class="paginationclass" ng-repeat="images in datalists | pagination: curPage * pageSize | limitTo: pageSize">
                                <div>
                                    <img id="{{images.id}}" class="img-responsive lookchooser5" src="images/Gallery/{{images.user_id}}/{{images.image_name}}"  onclick="showText({{images.id}})" width=250 height=150 />
<!--                                        <img id="{{images.id}}" class="img-responsive lookchooser1" src="images/Gallery/10/10_apple-311246_640.jpeg" onclick="showText({{images.id}})" width=250 height=150 />-->
                                    <button name="delete" id="delete" ng-click="deleteImage(images.id, images.user_id, images.image_name)">Delete</button>
                                </div> 
                                <div><p id=''></p></div>
                            <div></div><p>&nbsp;</p>
                     </li>
                </ul> 

            <div class="pagination pagination-centered" ng-show="datalists.length">
            <ul class="pagination-controle pagination">
             <li>
              <button type="button" class="btn btn-primary" ng-disabled="curPage == 0"
             ng-click="curPage=curPage-1"> &lt; PREV</button>
             </li>
             <li>
             <span>Page {{curPage + 1}} of {{ numberOfPages() }}</span>
             </li>
             <li>
             <button type="button" class="btn btn-primary"
             ng-disabled="curPage >= datalists.length/pageSize - 1"
             ng-click="curPage = curPage+1">NEXT &gt;</button>
             </li>
            </ul>
            </div>
    
    </div>
</div>    </body>
</html>
