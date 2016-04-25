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
        });

function setSubCategoryID(category_id, id, sub_category_name, external_source) {
    if (external_source == "null"){
        var sub_category_data_id = id;
        var category_data_id = category_id;
        var sub_category_data_name = sub_category_name;
        var configuration = global_host_address + "channelselection.jsp?id=" + category_data_id  + "&category_id=" + category_data_id + "&sub_category_name=" + sub_category_data_name + "&sub_category_id=" + sub_category_data_id + "&external_source=" + external_source;
        window.open(configuration, "_self");
    }else {

        var configuration = global_host_address + "mindbody.jsp?category_id=" + category_id + "&sub_category_name=" + sub_category_name + "&sub_category_id=" + id + "&external_source=" + external_source;
        window.open(configuration, "_self");
    }    
}
