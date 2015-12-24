/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fontsController($scope, $http) {

    $scope.organization = {};
    var font_name;
    function validate() {
        font_name = $("#fontname").val();
        if (font_name === "") {
            alert("Enter the font name.");
            $("#fontname").focus();
            return false;
        }
        return true;
    }

    $scope.createNewFonts = function ()
    {
        if (validate())
        {
            var fonts = {"font_name": font_name, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletAddFonts',
                headers: {'Content-Type': 'application/json'},
                data: fonts
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("Font already exist!");
                    $("#fontname").focus();
                } else if (data === "true") {
                    alert("font saved successfully.");
                    window.open(getHost() + 'admin/fontsfamily.jsp', "_self");
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

    $scope.changeFont = function (font_id)
    {
        var org_id = '#' + font_id;
        var fontname = $(org_id).val();

        function validate() {
            if (fontname === "") {
                alert("Enter the font.");
                $(org_id).focus();
                return false;
            }
            return true;
        }

        if (validate())
        {
            var fonts = {"font_id": font_id.toString(), "font_name": fontname, "type": "edit"};
            $http({
                method: 'POST',
                url: getHost() + 'ServletAddFonts',
                headers: {'Content-Type': 'application/json'},
                data: fonts
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("Font updated successfully.");
                    window.open(getHost() + 'admin/fontsfamily.jsp', "_self");
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

    $scope.deleteFont = function (font_id) {
        var font = {"font_id": font_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletAddFonts',
            headers: {'Content-Type': 'application/json'},
            data: font
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("Fonts deleted successfully.");
                window.open(getHost() + 'admin/fontsfamily.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };
}






