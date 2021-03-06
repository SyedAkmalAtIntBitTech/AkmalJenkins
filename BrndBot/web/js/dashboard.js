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
                    $scope.SubCategories = data;
                    
                    if (data === error) {
                        alert(data);
                    }

                }).error(function (data, status) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.

                    alert("request not succesful");
                });
                        
                if (CatID === 1) { 
                    $("#subpromotelist").css("position","relative").css("left", "-77px").css("top", "-10px").css("width","100px");
                }
                else if (CatID === 2) {
                    $("#subpromotelist").css("position","relative").css("left", "60px").css("top", "-10px");
                }
                else if (CatID === 3) {
                    $("#subpromotelist").css("position","relative").css("left", "195px").css("top", "-10px");
                }
                else if (CatID === 4) {
                    $("#subpromotelist").css("position","relative").css("left", "330px").css("top", "-10px");
                }
                else if (CatID === 5) {
                    $("#subpromotelist").css("position","relative").css("left", "465px").css("top", "-10px");
                }
                else if (CatID === 6) {
                    $("#subpromotelist").css("position","relative").css("left", "600px").css("top", "-10px");
                }
                else if (CatID === 7) {
                    $("#subpromotelist").css("position","relative").css("left", "200px").css("top", "-10px");
                }

                else if (CatID === 8) {
                    $("#subpromotelist").css("position","relative").css("left", "330px").css("top", "-10px");
                }
                else if (CatID === 9) {
                    $("#subpromotelist").css("position","relative").css("left", "460px").css("top", "-10px");
                }
                else if (CatID === 10) {
                    $("#subpromotelist").css("position","relative").css("left", "600px").css("top", "-10px");
                }
                else if (CatID === 11) {
                    $("#subpromotelist").css("position","relative").css("left", "510px").css("top", "10px");
                }
                else if (CatID === 12) {
                    $("#subpromotelist").css("position","relative").css("left", "625px").css("top", "10px");
                }
            };
        });

function setSubCategoryID(category_id, id, sub_category_name, external_source) {
            if (external_source == "null"){
                var sub_category_data_id = id;
                var category_data_id = category_id;
                var sub_category_data_name = sub_category_name;

                var configuration = global_host_address + "selectpromotemedia.jsp?category_id=" + category_data_id + "&sub_category_name=" + sub_category_data_name + "&sub_category_id=" + sub_category_data_id;
                window.open(configuration, "_self");
            }else {

                var configuration = global_host_address + "selectpromotingcategory.jsp?category_id=" + category_id + "&sub_category_name=" + sub_category_name + "&sub_category_id=" + id;
                window.open(configuration, "_self");
            }    
}

