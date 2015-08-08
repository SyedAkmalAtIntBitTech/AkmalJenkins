/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validate() {
    var font_name = $("#fontname").val();
    var files = $("#filesToUpload").val();

    if (font_name === "") {
        alert("please enter the font name");
        $("#fontname").focus();
        return false;
    }
    if (files == "") {
        alert("No files selected, please select the file");
        $("#filesToUpload").focus();
        return false;
    }
    return true;
}

function fontController($scope, $http) {

    $scope.deleteFont = function (font_id) {
        var Font = {"font_id": font_id};
        $http({
            method: 'POST',
            url: getHost() + 'ServletDeleteFonts',
            headers: {'Content-Type': 'application/json'},
            data: Font
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("font deleted successfully");
                window.open(getHost() + 'admin/fontsfamily.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        });
    };

    $scope.editFont = function (font_id, font_name) {
        var configuration = global_host_address + "admin/editfontsfamily.jsp" + "?font_id=" + font_id + "&font_name=" + font_name;
        window.open(configuration, "_self");
    };
}
