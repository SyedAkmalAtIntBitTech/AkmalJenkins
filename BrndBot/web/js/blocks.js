/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function blocksController($scope, $http) {

//    $scope.organization = {};
    var name, mindbodyquery, brand_id;
    function validate() {
        name = $("#name").val();
        mindbodyquery = $("#mindbodyquery").val();
        
        var x = document.getElementById("brandname").selectedIndex;

        brand_id = document.getElementsByTagName("option")[x].value;
        
        if (name === "") {
            alert("Enter the block name");
            $("#name").focus();
            return false;
        }
        
        if (mindbodyquery === "") {
            alert("Enter the mindbodyquery");
            $("#mindbodyquery").focus();
            return false;
        }

        if (brand_id == 0) {
            alert("No brand selected, please select the brand");
            document.getElementById("brandname").focus();
            return false;
        }
        
        return true;
    }

    $scope.createNewblock = function ()
    {
        if (validate())
        {
            var block = {"name": name, "mindbodyquery": mindbodyquery, "brand_id": brand_id, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletBlock',
                headers: {'Content-Type': 'application/json'},
                data: block
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("block already exist");
                    $("#blockname").focus();
                } else if (data === "true") {
                    alert("block saved successfully");
                    window.open(getHost() + 'admin/blocks.jsp', "_self");
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

    $scope.changeblock = function (organization_id)
    {
        var org_id = '#' + organization_id;
        var organizationname = $(org_id).val();

        function validate() {
            if (organizationname === "") {
                alert("Enter the organization");
                $(org_id).focus();
                return false;
            }
            return true;
        }

        if (validate())
        {
            var organization = {"organization_id": organization_id.toString(), "organization_name": organizationname, "type": "edit"};
            $http({
                method: 'POST',
                url: getHost() + 'ServletBlock',
                headers: {'Content-Type': 'application/json'},
                data: organization
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("block updated successfully");
                    window.open(getHost() + 'admin/organizations.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                } else if (data === "false") {
                    alert("block already exist");
                    $("#organizationname").focus();
                }    
            })
                    .error(function (data, status) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("request not succesful");
                    });
        }
    };

    $scope.deleteblock = function (organization_id) {
        var organization = {"organization_id": organization_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletBlock',
            headers: {'Content-Type': 'application/json'},
            data: organization
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("block deleted successfully");
                window.open(getHost() + 'admin/organizations.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };
}





