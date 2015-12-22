
function fontsSizeController($scope, $http) {

    var font_size;
    function validate() {
        font_size = $("#fontsize").val();
        if (font_size === "") {
            alert("Enter the font size");
            $("#fontsize").focus();
            return false;
        }
        return true;
    }

    $scope.createNewFontSize = function ()
    {
        if (validate())
        {
            var fonts = {"font_size": font_size, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletFontsSizes',
                headers: {'Content-Type': 'application/json'},
                data: fonts
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("Font size already exist");
                    $("#fontsize").focus();
                } else if (data === "true") {
                    alert("font saved successfully");
                    window.open(getHost() + 'admin/fontsSize.jsp', "_self");
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

    $scope.editFontSize = function (font_id)
    {
        var org_id = '#' + font_id;
        var fontsize = $(org_id).val();

        function validate() {
            if (fontsize === "") {
                alert("Enter the font size");
                $(org_id).focus();
                return false;
            }
            return true;
        }

        if (validate())
        {
            var fonts = {"font_id": font_id.toString(), "font_size": fontsize, "type": "edit"};
            $http({
                method: 'POST',
                url: getHost() + 'ServletFontsSizes',
                headers: {'Content-Type': 'application/json'},
                data: fonts
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("font size updated successfully");
                    window.open(getHost() + 'admin/fontsSize.jsp', "_self");
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

    $scope.deleteFontSize = function (font_id) {
        var font = {"font_id": font_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletFontsSizes',
            headers: {'Content-Type': 'application/json'},
            data: font
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("Font size deleted successfully");
                window.open(getHost() + 'admin/fontsSize.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };
}








