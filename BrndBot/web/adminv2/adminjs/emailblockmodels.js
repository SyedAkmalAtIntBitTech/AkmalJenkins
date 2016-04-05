/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

function emailBlocksController($scope, $http) {
    $scope.emailBlocksModel = function () {

        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailBlockModel.do'
        }).success(function (data, status, headers, config) {
            $scope.emailBlocksModelData = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    };
    
    
     $scope.getEmailModelById = function () {
        var emailBlockIdTag = $("#emailBlockIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getEmailBlockModelById.do?emailBlockModelId=' + emailBlockIdTag
        }).success(function (data, status, headers, config) {
            $('.fr-element').html(eval(JSON.stringify(data.d.details[0].htmlData)));
            $scope.getEmailModels = data.d.details[0];
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };


    $scope.addEmailBlockModel = function () {
        var emailBlockModelName = $("#emailModelName").val();
        var htmlData = $("#edit").froalaEditor('html.get');
        var imgDataObj = getImageData();
        var emailBlockModel = {"emailBlockModelName": emailBlockModelName, "htmlData": htmlData, "imageFileName": imgDataObj.imageFileName, "imageFileData": imgDataObj.base64ImgString};
        var validate = function () {
            if (emailBlockModelName === "") {
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
                url: getHost() + '/saveBlockModel.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailBlockModel)
            }).success(function (data, status, headers, config)
            {

                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'adminv2/emailblockmodels.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }

    };


    $scope.deleteEmailBlockModel= function (emailBlockModelId) {
        var deleteEmailBlocks = confirm(deleteEmailBlockPrompt);
        if (deleteEmailBlocks === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteBlockModel.do?emailBlockModelId=' + emailBlockModelId,
            }).success(function (data, status, headers, config) {
                $scope.getEmailModelById = data.d.details;
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'adminv2/emailblockmodels.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };


    $scope.updateEmailBlockModel = function () {
        var emailBlockModelId = $("#emailBlockIdTag").val();
        var emailBlockModelName = $("#emailModelNameTag").val();
        var htmlData = $("#edit").froalaEditor('html.get');
        var imgDataObj = getImageData();
        var emailBlockModel = {"emailBlockModelId":emailBlockModelId, "emailBlockModelName": emailBlockModelName, "htmlData": htmlData, "imageFileName": imgDataObj.imageFileName, "imageFileData": imgDataObj.base64ImgString};
        var validate = function () {
            if (emailBlockModelName === "") {
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
                url: getHost() + '/updateBlockModel.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailBlockModel)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'adminv2/emailblockmodels.jsp', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        } 
    };


}


