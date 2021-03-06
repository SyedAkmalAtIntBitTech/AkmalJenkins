/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function htmlController($scope, $http) {

    $scope.createhtmltemplate = {};
    var model_id, model_name, html_content;
    
    function validate() {
        model_id = $("#modelname").val();
        model_name = $("#modelname").find('option:selected').text();
        html_content = $("#html_content").val();
        
        if (model_name === 0) {
            alert("select the model name");
            $("#modelname").focus();
            return false;
        }
        
        if (html_content === "") {
            alert("html content is empty, please select the html content");
            $("#html_content").focus();
            return false;
        }
        
        return true;
    }

    $scope.createNewtemplate = function ()
    {
        if (validate())
        {
            var html_template = {"model_id":model_id, "model_name":model_name, "html_content":html_content, "type": "add"};

            $http({
                method: 'POST',
                url: getHost() + 'ServletAddHtmlTemplate',
                headers: {'Content-Type': 'application/json'},
                data: html_template
            }).success(function (data)
            {
                $scope.status = data;
                if (data === "false") {
                    alert("HTML already exist");
                    $("#organizationname").focus();
                } else if (data === "true") {
                    alert("HTML saved successfully");
                    window.open(getHost() + 'admin/addhtmltemplate.jsp', "_self");
                } else if (data === error) {
                    alert(data);
                }
            }).error(function (data, status) {
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
                } else if (data === "false") {
                    alert("Organization already exist");
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

$(function(){
   $("#modelname").change(function()
   {
       var selectedmodelname = $("#modelname").find('option:selected').text()
       $.ajax({
       type: 'GET',
       url: "/BrndBot/ServletDisplayLayoutHTML",
       dataType: 'json',
       data : { modelname : selectedmodelname },
       success: function (data) {
           
           $("#html_div_content").val(data[0].div);
           $("#html_content").val(data[0].table);
           
       },
       error: function(xhr, ajaxOptions, thrownError){
           
        alert(thrownError);
       }
       
    });
    
   });
});






