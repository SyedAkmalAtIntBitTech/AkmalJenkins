/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validate() {
    var brand_name = $("#brandname").val();
    var look = $("#look").find('option:selected').val();
    var files = $("#filesToUpload").val();

    if (brand_name === "") {
        alert("please enter the brand name");
        $("#brandname").focus();
        return false;
    }
    if (parseInt(look) === 0) {
        alert("No look selected. please select the look");
        $("#look").focus();
        return false;
    }
    if (files == "") {
        alert("No files selected, please select the file");
        $("#filesToUpload").focus();
        return false;
    }
    return true;
}

function brandController($scope, $http) {
    $scope.deleteBrand = function (brand_id) {
        var Brand = {"brand_id": brand_id};
        $http({
            method: 'POST',
            url: getHost() + 'ServletDeleteBrands',
            headers: {'Content-Type': 'application/json'},
            data: Brand
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("brand deleted successfully");
                window.open(getHost() + 'admin/brandpersonality.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        });
    };

    $scope.editBrand = function (brand_id, brand_name, look_id) {
        var configuration = global_host_address + "admin/editpersonality.jsp" + "?brand_id=" + brand_id + "&brand_name=" + brand_name + "&look_id=" + look_id + "";
        window.open(configuration, "_self");

    };
}

