/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module("myapp", [])
        .controller("controllerCategories", function ($scope, $http) {
            $scope.categories = {};
            $scope.mindbodyactivated = {};
            $scope.SubCategories = {};
            $http({
                method: 'GET',
                url: 'MindBodyDataServlet?query=isMindBodyActivated'
            }).success(function (data, status, headers, config) {
                var mindbody_data = JSON.stringify(data);
                if (mindbody_data.status === "unactivated") {
                    if (mindbody_data.activation_link === "") {
                        $scope.mindbodyactivation = "Mindbody not activated. Unknown error has occured.";
                    } else {
                        $scope.mindbodyactivation = "Mindbody not activated. Please visit this link to activate mindbody." + mindbody_data.activation_link;
                    }
                } else if (data === error) {
                    alert(data);
                } else {

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

                $http({
                    method: 'POST',
                    url: getHost() + 'GetSubCategories',
                    headers: {'Content-Type': 'application/json'},
                    data: CategoryID
                }).success(function (data)
                {
                    $scope.SubCategories = data;
                    if (data === error) {
                        alert(data);
                    }

                })
                        .error(function (data, status) {
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.

                            alert("request not succesful");
                        });

                if (CatID === 1) {
                    $("#subpromotelist").css("left", "30px").css("top", "180px");
                }
                else if (CatID === 2) {
                    $("#subpromotelist").css("left", "150px").css("top", "180px");
                }
                else if (CatID === 3) {
                    $("#subpromotelist").css("left", "270px").css("top", "180px");
                }
                else if (CatID === 4) {
                    $("#subpromotelist").css("left", "385px").css("top", "180px");
                }
                else if (CatID === 5) {
                    $("#subpromotelist").css("left", "510px").css("top", "180px");
                }
                else if (CatID === 6) {
                    $("#subpromotelist").css("left", "625px").css("top", "180px");
                }
                else if (CatID === 7) {
                    $("#subpromotelist").css("left", "30px").css("top", "180px");
                }

                else if (CatID === 8) {
                    $("#subpromotelist").css("left", "150px").css("top", "180px");
                }
                else if (CatID === 9) {
                    $("#subpromotelist").css("left", "270px").css("top", "180px");
                }
                else if (CatID === 10) {
                    $("#subpromotelist").css("left", "385px").css("top", "180px");
                }
                else if (CatID === 11) {
                    $("#subpromotelist").css("left", "510px").css("top", "180px");
                }
                else if (CatID === 12) {
                    $("#subpromotelist").css("left", "625px").css("top", "180px");
                }
            };
        });

function setSubCategoryID(category_id, id, sub_category_name) {
    var configuration = global_host_address + "selectpromotingcategory.jsp?category_id=" + category_id + "&sub_category_name=" + sub_category_name + "&sub_category_id=" + id;
    window.open(configuration, "_self");
}

