<%-- 
    Document   : pagination
    Created on : Jul 6, 2015, 2:35:47 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
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

        <title>JSP Page</title>
        <script>
            
            var myapp = angular.module('sampleapp', [ ]);

                myapp.controller('samplecontoller', function ($scope,$http) {

                $scope.showData = function( ){
                     
                 $scope.curPage = 0;
                 $scope.pageSize = 4;

                    $http({
                            method : 'GET',
                            url : 'GetColorPalettes'
                    }).success(function(data, status, headers, config) {
                        
                        $scope.datalists = data;
//                        $scope.datalists = [
//                           { "name": "John","age":"16","designation":"Software Engineer1"},
//                           {"name": "John2","age":"21","designation":"Software Engineer2"},
//                           {"name": "John3","age":"19","designation":"Software Engineer3"},
//                           {"name": "John4","age":"17","designation":"Software Engineer4"},
//                           {"name": "John5","age":"21","designation":"Software Engineer5"},
//                           {"name": "John6","age":"31","designation":"Software Engineer6"},
//                           {"name": "John7","age":"41","designation":"Software Engineer7"},
//                           {"name": "John8","age":"16","designation":"Software Engineer8"},
//                           {"name": "John18","age":"16","designation":"Software Engineer9"},
//                           {"name": "John28","age":"16","designation":"Software Engineer10"},
//                           {"name": "John38","age":"16","designation":"Software Engineer11"},
//                           {"name": "John48","age":"16","designation":"Software Engineer12"},
//                           {"name": "John58","age":"16","designation":"Software Engineer13"},
//                           {"name": "John68","age":"16","designation":"Software Engineer14"},
//                           {"name": "John68","age":"16","designation":"Software Engineer15"}
//                       ]
                        
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

                    }
                                     
//                 $scope.showData = function( ){
//
//                 $scope.curPage = 0;
//                 $scope.pageSize = 3;
//                 $scope.datalists = [
//                    { "name": "John","age":"16","designation":"Software Engineer1"},
//                    {"name": "John2","age":"21","designation":"Software Engineer2"},
//                    {"name": "John3","age":"19","designation":"Software Engineer3"},
//                    {"name": "John4","age":"17","designation":"Software Engineer4"},
//                    {"name": "John5","age":"21","designation":"Software Engineer5"},
//                    {"name": "John6","age":"31","designation":"Software Engineer6"},
//                    {"name": "John7","age":"41","designation":"Software Engineer7"},
//                    {"name": "John8","age":"16","designation":"Software Engineer8"},
//                    {"name": "John18","age":"16","designation":"Software Engineer9"},
//                    {"name": "John28","age":"16","designation":"Software Engineer10"},
//                    {"name": "John38","age":"16","designation":"Software Engineer11"},
//                    {"name": "John48","age":"16","designation":"Software Engineer12"},
//                    {"name": "John58","age":"16","designation":"Software Engineer13"},
//                    {"name": "John68","age":"16","designation":"Software Engineer14"},
//                    {"name": "John68","age":"16","designation":"Software Engineer15"}
//                ]
//                     $scope.numberOfPages = function() {
//                                                return Math.ceil($scope.datalists.length / $scope.pageSize);
//                                        };
//
//                }

                });

                angular.module('sampleapp').filter('pagination', function()
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
    <body>
            <div ng-app="sampleapp">
                <div ng-controller="samplecontoller" ng-init="showData()">

                    <ul>
                         <li class="paginationclass" style="font-weight:bold;"><span>Name</span><span>Age</span><span>Designation</span></li>            
                         <li class="paginationclass" ng-repeat="datalist in datalists | pagination: curPage * pageSize | limitTo: pageSize">
                                <div ng-repeat="colors in datalist">
                                    <div id="{{colors.id}}" onclick="getIDNo('{{colors.id}}')"class="foo col-md-2" style="background-color:{{colors.colorHex}};"></div>
                                    <div><p id="{{colors.theme_id}}">{{colors.theme_name}}</p></div>
                                </div> 
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
             ng-disabled="curPage >= themes.length/pageSize - 1"
             ng-click="curPage = curPage+1">NEXT &gt;</button>
             </li>
            </ul>
            </div>
    
    
    
    </div>
</div>    </body>
</html>
