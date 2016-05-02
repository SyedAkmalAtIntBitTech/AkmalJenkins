/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("mindbody", [])
    .controller("controllerGetMindBody", function($scope, $http) {
         $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","40%").css("left","45%");
         $(".page-background").css("background-color","#fff");
//                          $("#continuebutton").hide();
            $scope.showData = function(LookupId){
                    $scope.curPage = 0;
                    $scope.pageSize = 4;
                    $http({
                    method : 'GET',
                    url :getHost()+ '/externalContent/isActivated?externalSourceKeywordLookupId='+LookupId
                    }).success(function(data, status, headers, config) {
                        var minddata= JSON.stringify(data.d.details);
                        if(minddata === "[true]"){
                            
                        $http({
                            method : 'GET',
                            url :getHost()+ '/externalContent/getListData/'+LookupId
                        }).success(function(data, status, headers, config) {
                            var parseData=JSON.parse(data.d.details);
//                            alert(JSON.stringify(parseData));
                            $scope.mindbodylist=parseData.mindbody_data;
                            $('#loadingGif').remove();
                        }).error(function(data, status, headers, config) {

                        });             
//                            $('#loadingGif').remove();
                            $('#nodata').show();
                            $('#nodata').css("margin-left","180px");
                        }
                        else
                        {
                            
                        }
                        $scope.datalists = data;
//                                    alert(JSON.stringify(data));
                    $(".page-background").css("background-color","#EFF2F6");
                    $("#picktheme").css("overflow-y", "auto");
                    if (data.mindbody_data.length == 0){
            $("#continuebutton").hide();
            } else {
            $("#continuebutton").show();
            $("#picktheme").css("display","block");
            }
            if (data === error){
            alert(data);
            }
             $('#loadingGif').remove();
            }).error(function(data, status, headers, config) {
                alert("No data available, problem fetching the data");
                //called asynchronously if an error occurs
                //or server returns response with an error status.
            });
            };
    });
    var selected_id;
    function select_category_details(id){
                       selected_id = id;
    }
     function selected_category(){
        var configuration = global_host_address + "channelselection.jsp" + "?id=" + selected_id;
        window.open(configuration, "_self");
     }