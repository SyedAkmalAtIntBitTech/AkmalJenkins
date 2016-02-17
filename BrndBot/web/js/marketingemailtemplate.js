/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validate() {
    var template_name = $("#template_name").val();
    var html_data = $("#html_data").val();

    if (template_name == "") {
        alert("Template name not entered! Kindly enter the template.");
        $("#template_name").focus();
        return false;
    }

    if (html_data == "") {
        alert("Html data not selected! Kindly select an html data.");
        $("#html_data").focus();
        return false;
    }

    return true;
}
function validateUpdate() {
    var template_name = $("#template_update_name").val();
    var html_data = $("#update_html_data").val();

    if (template_name == "") {
        alert("Template name not entered! Kindly enter the template.");
        $("#template_update_name").focus();
        return false;
    }

    if (html_data == "") {
        alert("Html data not selected! Kindly select an html data.");
        $("#update_html_data").focus();
        return false;
    }

    return true;
}
function marketingEmailTemplateController($scope, $http) {

    $scope.saveMarketingEmailTemplate = function () {
        if (validate()) {
            var template_name = $("#template_name").val();
            var html_data = $("#html_data").val();

            var marketing_template = {"template_name": template_name,
                "html_data": html_data
            };
            $http({
                method: 'POST',
                url: getHost() + 'setRecuringEmailTemplate.do',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(marketing_template)
            }).success(function (data, status) {

                if (data == "true") {
                    alert("Record successfully saved.");
                    window.open(getHost() + 'admin/marketingemailtemplate.jsp', "_self");
                } else {
                    alert("Problem saving record!");
                }
            }).error(function () {
                alert("No data available! Problem fetching the data.");
            });
        }
        ;

    };

    $scope.updateMarketingEmailTemplate = function () {
        if (validateUpdate()) {
            var template_id = $("#template_id").val();
            var template_name = $("#template_update_name").val();
            var html_data = $("#update_html_data").val();
            var marketing_template = {"template_id": template_id,
                "template_name": template_name,
                "html_data": html_data
            };
            $http({
                method: 'POST',
                url: getHost() + 'updateRecuringEmailTemplate.do',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(marketing_template)
            }).success(function (data, status) {

                if (data == "true") {
                    alert("Record successfully updated.");
                    window.open(getHost() + 'admin/marketingemailtemplate.jsp', "_self");
                } else {
                    alert("Problem saving record!");
                }
            }).error(function () {
                alert("No data available! Problem fetching the data.");
            });
        }
        ;

    };

    $scope.getMarketingEmailTemplate = function () {
        $http({
            method: 'GET',
            url: getHost() + 'getAllRecuringEmailTemplates.do'
        }).success(function (data, status, headers, config) {
            $scope.email_templates = data;
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };
    $scope.showMarketingEmailTemplate = function (template_id) {

        var email_template = {"template_id": template_id};
        $http({
            method: 'POST',
            url: getHost() + 'getRecuringEmailTemplate.do',
            headers: {'Content-Type': 'application/json'},
            data: JSON.stringify(email_template)
        }).success(function (data, status, headers, config) {
            if (data != "") {
                $("#save").hide();
                $("#update").show();
            }
            $scope.email_template = data;
        }).error(function (data, status, headers, config) {
            alert("No data available! Problem fetching the data.");
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };
    $scope.deleteEmailTemplate = function (template_id) {

        var email_template = {"template_id": template_id};

        $http({
            method: 'POST',
            url: getHost() + 'deleteRecuringEmailTemplate.do',
            headers: {'Content-Type': 'application/json'},
            data: JSON.stringify(email_template)
        }).success(function (data, status) {
            $scope.data = data;
            if (data == "true") {
                alert("Record deleted successfully.");
                window.open(getHost() + 'admin/marketingemailtemplate.jsp', "_self");
            } else {
                alert("Problem deleting record!");
            }
        }).error(function () {
            alert("No data available! Problem fetching the data.");
        });
    };

}