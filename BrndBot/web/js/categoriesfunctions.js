/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validate() {
    var category_name = $("#category_name").val();
    var organization = $("#organization").find('option:selected').val();
    var files = $("#filesToUpload").val();

    if (category_name === "") {
        alert("please enter the brand name");
        $("#category_name").focus();
        return false;
    }
    if (parseInt(organization) === 0) {
        alert("No look selected. please select the look");
        $("#organization").focus();
        return false;
    }
    if (files == "") {
        alert("No files selected, please select the file");
        $("#filesToUpload").focus();
        return false;
    }
    return true;
}

function categoryController($scope, $http) {

    $scope.deleteCategory = function (category_id) {
        var Category = {"category_id": category_id};
        $http({
            method: 'POST',
            url: getHost() + 'ServletDeleteCategories',
            headers: {'Content-Type': 'application/json'},
            data: Category
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("category deleted successfully");
                window.open(getHost() + 'admin/categories.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        });
    };

    $scope.editCategory = function (category_id, category_name, organization_id) {
        var configuration = global_host_address + "admin/editcategory.jsp" + "?category_id=" + category_id + "&category_name=" + category_name + " &organization_id=" + organization_id;
        window.open(configuration, "_self");
    };
}

