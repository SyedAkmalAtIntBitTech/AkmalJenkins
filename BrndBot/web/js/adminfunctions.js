/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function organizationController($scope, $http) {

    $scope.organization = {};
    var organizationname;
    function validate() {
        organizationname = $("#organizationname").val();
        if (organizationname === "") {
            alert("Enter the organization");
            $("#organizationname").focus();
            return false;
        }
        return true;
    }

    $scope.createNewOrganization = function ()
    {
        if (validate())
        {
            var organization = {"organization_name": organizationname, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletOrganization',
                headers: {'Content-Type': 'application/json'},
                data: organization
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("Organization already exist");
                    $("#organizationname").focus();
                } else if (data === "true") {
                    alert("Organization saved successfully");
                    window.open(getHost() + 'admin/organizations.jsp', "_self");
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

    $scope.changeOrganization = function (organization_id)
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
                url: getHost() + 'ServletOrganization',
                headers: {'Content-Type': 'application/json'},
                data: organization
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "true") {
                    alert("Organization updated successfully");
                    window.open(getHost() + 'admin/organizations.jsp', "_self");
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

    $scope.deleteOrganization = function (organization_id) {
        var organization = {"organization_id": organization_id.toString(), "type": "delete"};
        $http({
            method: 'POST',
            url: getHost() + 'ServletOrganization',
            headers: {'Content-Type': 'application/json'},
            data: organization
        }).success(function (data)
        {
            $scope.status = data;
            if (data === "true") {
                alert("Organization deleted successfully");
                window.open(getHost() + 'admin/organizations.jsp', "_self");
            } else if (data === error) {
                alert(data);
            }
        })
    };
}





