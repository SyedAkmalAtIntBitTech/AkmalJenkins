/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fontsStyleController($scope, $http) {

    var font_style;
    function validate() {
        font_style = $("#fontstyle").val();
        if (font_style === "") {
            alert("Enter the font style");
            $("#fontstyle").focus();
            return false;
        }
        return true;
    }

    $scope.createNewFontStyle = function ()
    {
        if (validate())
        {
            var fonts = {"font_style": font_style, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletFontStyles',
                headers: {'Content-Type': 'application/json'},
                data: fonts
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("Font style already exist");
                    $("#fontsize").focus();
                } else if (data === "true") {
                    alert("font saved successfully");
                    window.open(getHost() + 'admin/fontsstyle.jsp', "_self");
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

    $scope.editFontStyle = function (font_id)
    {
        var org_id = '#' + font_id;
        var font_style = $(org_id).val();

        function validate() {
            if (font_style === "") {
                alert("Enter the font size");
                $(org_id).focus();
                return false;
            }
            return true;
        }

        if (validate())
        {
            var fonts = {"font_id": font_id.toString(), "font_style": font_style, "type": "edit"};
            $http({
                method: 'POST',
                url: getHost() + 'ServletFontStyles',
                headers: {'Content-Type': 'application/json'},
                data: fonts
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("font style updated successfully");
                    window.open(getHost() + 'admin/fontsstyle.jsp', "_self");
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

    $scope.deleteFontStyle = function (font_id) {
        var font = {"font_id": font_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletFontStyles',
            headers: {'Content-Type': 'application/json'},
            data: font
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("Font style deleted successfully");
                window.open(getHost() + 'admin/fontsstyle.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };
}








