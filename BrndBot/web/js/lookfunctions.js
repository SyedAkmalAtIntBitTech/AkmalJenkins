/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validate() {
    var look_name = $("#lookname").val();
    var files = $("#filesToUpload").val();

    if (look_name === "") {
        alert("please enter the look name");
        $("#lookname").focus();
        return false;
    }
    if (files == "") {
        alert("No files selected, please select the file");
        $("#filesToUpload").focus();
        return false;
    }
    return true;
}

function lookController($scope, $http) {

    $scope.deleteLooks = function (look_id) {
        var look = {"look_id": look_id};
        $http({
            method: 'POST',
            url: getHost() + 'ServletDeleteLooks',
            headers: {'Content-Type': 'application/json'},
            data: look
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("look deleted successfully");
                window.open(getHost() + 'admin/looks.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };

    $scope.changeLooks = function (look_id, look_name) {
        var configuration = global_host_address + "admin/editlook.jsp" + "?look_id=" + look_id + "&look_name=" + look_name + "";
        window.open(configuration, "_self");

    }
}

