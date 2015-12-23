/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function colorsController($scope, $http) {

    var color_hex, color_name;

    function validate() {
        color_hex = $("#color_hex").val();
        color_name = $("#color_name").val();
        if (color_hex === "") {
            alert("Enter the color hex value.");
            $("#color_hex").focus();
            return false;
        }
        if (color_name === "") {
            alert("Enter the color name value.");
            $("#color_name").focus();
            return false;
        }
        return true;
    }

    $scope.createColors = function ()
    {
        if (validate())
        {
            var color = {"color_hex": color_hex, "color_name": color_name, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletColors',
                headers: {'Content-Type': 'application/json'},
                data: color
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("Color already exist!");
                    $("#color_hex").focus();
                } else if (data === "true") {
                    alert("Color saved successfully.");
                    window.open(getHost() + 'admin/colors.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("Request not successful!");
                    });
        }
    };

    $scope.editColor = function(id, hex, name)
    {
        var color_id = id;
        var color_hex = '#' + hex;
        var color_name = '#' + name;
        var color_hex_value = $(color_hex).val();
        var color_name_value = $(color_name).val();

        function validate() {
            if (color_hex_value === "") {
                alert("Enter the hex value.");
                $(color_hex).focus();
                return false;
            }
            if (color_name_value === "") {
                alert("Enter the color name.");
                $(color_name).focus();
                return false;
            }
            return true;
        }

        if (validate())
        {
            var color = {"color_id": color_id.toString(), "color_hex": color_hex_value, "color_name": color_name_value, "type": "edit"};
            $http({
                method: 'POST',
                url: getHost() + 'ServletColors',
                headers: {'Content-Type': 'application/json'},
                data: color
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("Color updated successfully.");
                    window.open(getHost() + 'admin/colors.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("Request not successful!");
                    });
        }
    };

    $scope.deleteColor = function(color_id) {
        var color = {"color_id": color_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletColors',
            headers: {'Content-Type': 'application/json'},
            data: color
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("Color deleted successfully.");
                window.open(getHost() + 'admin/colors.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };
}








