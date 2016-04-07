/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
$(document).ready(function () {
    if (window.location.href.indexOf("edit=yes") > -1)
    {
        $("#uploadOnEdit").show();
        $("#nameThisTemplate").hide();
        $("#selectOrgranization").show();
        $("#editTitle").show();
        $("#createTitle").hide();
        $("#createNewTemplate").hide();
        $("#saveTemplate").show();

    }
    if (window.location.href.indexOf("edit=no") > -1)
    {
        $("#uploadOnEdit").show();
        $("#nameThisTemplate").show();
        $("#selectOrgranization").hide();
        $("#editTitle").hide();
        $("#createTitle").show();
        $("#createNewTemplate").show();
        $("#saveTemplate").hide();
        $("#updateTemplate").hide();
        $("#deleteTemplate").hide();

    }
});

function emailTemplateController($scope, $http) {
    $scope.emailTemplateModel = function () {

        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailModel.do'
        }).success(function (data, status, headers, config) {
            $scope.emailTemplates = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    };

    $scope.addEmailTemplate = function () {
        var emailModelName = $("#emailModelName").val();
        var htmlData = $("#edit").froalaEditor('html.get');
        var imgDataObj = getImageData();

        var emailModel = {"emailModelName": emailModelName, "htmlData": htmlData, "imageFileName": imgDataObj.imageFileName, "imageFileData": imgDataObj.base64ImgString};
        var validate = function () {
            if (emailModelName === "") {
                alert("Please enter Template Name!");
                $("#emailModelName").focus();
                return false;
            }
            if (htmlData === "") {
                alert("Please enter Html data!");
                $("#edit").focus();
                return false;
            }
            if (imgDataObj.imageFileName === "") {
                alert("Please Select an Image!");
                $("#imageFileName").focus();
                return false;
            }
            var fileType = imgDataObj.imageFileName.split(".")[1];
            if ((fileType === "png") || (fileType === "gif") || (fileType === "jpg") || (fileType === "jpeg")) {
                return true;
            } else {
                alert("Please Select only Image file!");
                $("#imageFileName").focus();
                return false;
            }
        };
        if (validate()) {
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveEmailModel.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailModel)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'v2/admin/emailtemplates.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }

    };

    $scope.editEmailTemplate = function () {
        var emailModelId = $("#emailModelIdTag").val();
        var emailModelName = $("#emailModelNameTag").val();
        var htmlData = $("#edit").froalaEditor('html.get');
        var imgDataObj = getImageData();
//        alert("emailmodel : " + emailModelId + "," + emailModelName + htmlData + "," + imgDataObj.imageFileName);
        var emailModel = {"emailModelId": emailModelId, "emailModelName": emailModelName, "htmlData": htmlData, "imageFileName": imgDataObj.imageFileName, "imageFileData": imgDataObj.base64ImgString};
        var validate = function () {
            if (emailModelName === "") {
                alert("Please enter Template Name!");
                $("#emailModelNameTag").focus();
                return false;
            }
            if (htmlData === "") {
                alert("Please enter Html data!");
                $("#edit").focus();
                return false;
            }
            if (imgDataObj.imageFileName === "") {
                return true;
            } else {
                var fileType = imgDataObj.imageFileName.split(".")[1];
                if ((fileType === "png") || (fileType === "gif") || (fileType === "jpg") || (fileType === "jpeg")) {
                    return true;
                } else {
                    alert("Please Select only Image file!");
                    $("#imageFileName").focus();
                    return false;
                }
            }

        };
        if (validate()) {
            $.ajax({
                method: 'POST',
                url: getHost() + '/editEmailModel.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailModel)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'v2/admin/emailtemplates.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };


    $scope.getEmailModelById = function () {
        var emailModelId = $("#emailModelIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getEmailModelById.do?emailModelId=' + emailModelId
        }).success(function (data, status, headers, config) {
            $('.fr-element').html(eval(JSON.stringify(data.d.details[0].htmlData)));
            $scope.emailModelById = data.d.details[0];
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };


    $scope.deleteEmailTemplate = function (emailModelId) {
        var emailModelId = $("#emailModelIdTag").val();
        var deleteEmailTemplate = confirm("Do you want to delete this Template?");
        if (deleteEmailTemplate === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteEmailModel.do?emailModelId=' + emailModelId,
            }).success(function (data, status, headers, config) {
                $scope.emailModelById = data.d.details;
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'v2/admin/emailtemplates.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    }

}


