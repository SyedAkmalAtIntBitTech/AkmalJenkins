/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module("myapp", [])
        .controller("controllerCategories", function ($scope, $http) {
            $scope.categories = {};
        $(".mindbodyactivationstatus").hide();
            $scope.mindbodyactivated = {};
            $scope.SubCategories = {};
            $http({
                method: 'GET',
                url: 'MindBodyDataServlet?query=isMindBodyActivated'
            }).success(function (data, status, headers, config) {
                var mindbody_data = data;
                if (data.status === "unactivated") {
                    $(".mindbodyactivationstatus").show();
                    $(".message").hide();
                    $(".mindbodyactivationstatus").show();
                    if (data.activation_link === "") {
                        $scope.mindbodyactivationmessage = "Mindbody not activated. Unknown error has occured.";
                    } else {
                        $scope.mindbodyactivationmessage = "Mindbody not activated. Please visit this link to activate mindbody.";
                        $scope.mindbodyactivationlink = mindbody_data.activation_link;
                    }
                } else if (data === error) {
                    alert(data);
                } else {
                    $(".mindbodyactivationstatus").hide();
                    $http({
                        method: 'GET',
                        url: 'GetUserCategories'
                    }).success(function (data, status, headers, config) {
                        $scope.categories = data;
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });


                    $scope.categories = data;
                }
            }).error(function (data, status, headers, config) {
                alert("No data available, problem fetching the data");
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });


            $scope.getSubCategories = function (CatID) {
                var CategoryID = {"CategoryID": CatID.toString()};
                $scope.SubCategories = "";
                $http({
                    method: 'POST',
                    url: getHost() + 'GetSubCategories',
                    headers: {'Content-Type': 'application/json'},
                    data: CategoryID
                }).success(function (data)
                {   
                    var JSONData = data;
                    var i = 0;
                    for(i = 0; i<=JSONData.length; i++){
                        var mindbody_data = JSONData[i];
                        if (mindbody_data.external_source == "null"){
                            window.open(getHost() + 'selectpromotemedia.jsp', "_self");
                        }else {
                            $scope.SubCategories = data;
                        }
                    }
                        
//                    if (data.external_source == null) {
//                        alert("no external source");
//                    }else {
//                        $scope.SubCategories = data;
//                    }
                    
                    if (data === error) {
                        alert(data);
                    }

                }).error(function (data, status) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.

                    alert("request not succesful");
                });
                        
                if (CatID === 1) { 
                    $("#subpromotelist").css("position","relative").css("left", "-80px").css("top", "-10px").css("width","100px");
                }
                else if (CatID === 2) {
                    $("#subpromotelist").css("position","relative").css("left", "30px").css("top", "-10px");
                }
                else if (CatID === 3) {
                    $("#subpromotelist").css("position","relative").css("left", "140px").css("top", "-10px");
                }
                else if (CatID === 4) {
                    $("#subpromotelist").css("position","relative").css("left", "250px").css("top", "-10px");
                }
                else if (CatID === 5) {
                    $("#subpromotelist").css("position","relative").css("left", "370px").css("top", "-10px");
                }
                else if (CatID === 6) {
                    $("#subpromotelist").css("position","relative").css("left", "470px").css("top", "-10px");
                }
                else if (CatID === 7) {
                    $("#subpromotelist").css("position","relative").css("left", "30px").css("top", "-10px");
                }

                else if (CatID === 8) {
                    $("#subpromotelist").css("position","relative").css("left", "150px").css("top", "10px");
                }
                else if (CatID === 9) {
                    $("#subpromotelist").css("position","relative").css("left", "270px").css("top", "10px");
                }
                else if (CatID === 10) {
                    $("#subpromotelist").css("position","relative").css("left", "385px").css("top", "10px");
                }
                else if (CatID === 11) {
                    $("#subpromotelist").css("position","relative").css("left", "510px").css("top", "10px");
                }
                else if (CatID === 12) {
                    $("#subpromotelist").css("position","relative").css("left", "625px").css("top", "10px");
                }
            };
        });

function setSubCategoryID(category_id, id, sub_category_name) {
    var configuration = global_host_address + "selectpromotingcategory.jsp?category_id=" + category_id + "&sub_category_name=" + sub_category_name + "&sub_category_id=" + id;
    window.open(configuration, "_self");
}

