/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function colorthemeController($scope, $http) {
    $scope.colortheme = {};
    var brand_id;
    var color = [];
    function validate() {

        var x = document.getElementById("brand").selectedIndex;
        brand_id = document.getElementsByTagName("option")[x].value;
        if (brand_id == 0) {
            alert("No brand selected, please select any one brand");
            document.getElementById("brand").focus();
            return false;
        }

        var colors = document.getElementById("color");

        var len = colors.options.length;

        var i = 1;
        var sel = 0;
        for (i = 1; i < colors.options.length; i++) {
            if (colors.options[i].selected === true) {
                color[sel] = colors.options[i].value;
                sel = sel + 1;
            }
        }
//        alert(sel);
        if (sel == 0) {
            alert("No color selected, please select six color");
            document.getElementById("color").focus();
            return false;
        } else if (sel != 6) {
            alert("please select six colors");
            document.getElementById("color").focus();
            return false;
        }
        return true;
    }
    $scope.createColorTheme = function () {

        if (validate()) {
            var colort = {"brand_id": brand_id.toString(), "colors": color, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletColorTheme',
                headers: {'Content-Type': 'application/json'},
                data: colort
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    window.open(getHost() + 'admin/colortheme.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("request not succesful");
                    });
        }
    };
    $scope.delete = function (color_theme) {
        var colortheme = {"color_theme_id": color_theme.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletColorTheme',
            headers: {'Content-Type': 'application/json'},
            data: colortheme
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("color theme deleted successfully");
                window.open(getHost() + 'admin/colortheme.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        });
    };
    $scope.edit = function (theme_id) {
        var configuration = global_host_address + "admin/editcolortheme.jsp" + "?theme_id=" + theme_id;
        window.open(configuration, "_self");

    };
}


