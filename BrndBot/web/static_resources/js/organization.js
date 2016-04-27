/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

$(document).ready(function () {
    
//    location.href = URL_add_parameter(location.href, 'param', 'value');
//    alert(location.href);
    
    $("#addOrganization").click(function () {
        $("#addOrganizationPopup").show();
        $("#addOrganizationPopupDiv").show();
        $("#addEmailPopupDiv").show();
    });


    $("#addOrganizationPopupDiv").click(function () {
        $("#addOrganizationPopup").hide();
        $("#addOrganizationPopupDiv").hide();


    });
});

function organizationcontroller($scope, $http) {

    $scope.organization = function () {

        $http({
            method: 'GET',
            url: getHost() + '/getAllOrganizations.do'
        }).success(function (data, status, headers, config) {
            $scope.organizationDetails = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    };

    $scope.addOrganization = function () {

        var organizationName = $("#organizationName").val();
        var organizationType = $("#organizationType").val();
        var organization = {"organizationName": organizationName, "organizationTypeId": organizationType};
        if (organizationName === "") {

            alert(enterOrganizationName);
            $("#organizationName").focus();
        } else {
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveOrganization.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(organization)
            }).success(function (data, status, headers, config)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                window.open(getHost() + 'admin/organization', "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };





    $scope.organizationdetails = function () {

        var organizationId = $("#organizationIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getOrganizationById.do?organizationId=' + organizationId
        }).success(function (data, status, headers, config) {
            var organizationTypeId = JSON.stringify(data.d.details[0].organizationTypeId);
            $("#organizationDetailsTypeId > [value=" + organizationTypeId + "]").attr("selected", "true");
            $scope.organizationDetails = data.d.details[0];
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });


        $scope.deleteOrganization = function (organizationId) {
            var deleteOrganization = confirm(deleteOrganizationPrompt);
            if (deleteOrganization === true)
            {
                $http({
                    method: 'GET',
                    url: getHost() + '/deleteOrganization.do?organizationId=' + organizationId
                }).success(function (data, status, headers, config) {
                    $scope.organizationDetails = data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'admin/organization', "_self");
                }).error(function (data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });
            }
        }


        $scope.updateOrganization = function () {
            var organizationId = $("#organizationIdTag").val();
            var organizationName = $("#organizationNameDiv").text();
            var organizationTypeId = $("#organizationDetailsTypeId").val();
            var updateorg = {
                "organizationId": organizationId,
                "organizationName": organizationName,
                "organizationTypeId": organizationTypeId
            };
            $http({
                method: 'POST',
                url: getHost() + '/updateOrganization.do',
                dataType: "json",
                contentType: "application/json",
                data: updateorg
            }).success(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }





    };


    $scope.emailcategories = function () {

        var organizationId = $("#organizationId").val();

        $http({
            method: 'GET',
            url: getHost() + '/getAllOrganizationCategoryByOrganizationId.do?organizationId=' + organizationId
        }).success(function (data, status, headers, config) {
            for (var i = 0; i <= data.d.channelDetailsList.length; i++) {
                var obj = data.d.channelDetailsList[i];
                if (data.d.channelDetailsList[i].channelName === emailChannel) {
                    $scope.emailDetails = data.d.channelDetailsList[i].categoryDetailsList;
                }
                if (data.d.channelDetailsList[i].channelName === printChannel) {
                    $scope.printDetails = data.d.channelDetailsList[i].categoryDetailsList;
                }
                if (data.d.channelDetailsList[i].channelName === imageChannel) {
                    $scope.imageDetails = data.d.channelDetailsList[i].categoryDetailsList;
                    //alert(JSON.stringify(data.d.channelDetailsList[i].categoryDetailsList));
                }
            }
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };


    $scope.addEmailCategory = function () {
        var organizationId = $("#organizationId").val();
        var categoryName = $("#categoryName").val();
        var emailCategory = {"categoryName": categoryName, "channelId": parseInt(emailChannelId), "organizationId": parseInt(organizationId)}
        if (categoryName === "") {
            alert(enterCategoryName);
            $("#categoryName").focus();
        } else {
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveCategory.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailCategory)
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));

                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };

    $scope.addPrintCategory = function () {

        var organizationId = $("#organizationId").val();
        var printCategory = $("#printCategory").val();
        var category = {"categoryName": printCategory, "channelId": printChannelId, "organizationId": organizationId};
        if (printCategory === "") {
            alert(enterCategoryName);
            $("#printCategory").focus();
        } else {
            $.ajax({
                method: 'POST',
                url: getHost() + '/saveCategory.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(category)
            }).success(function (data)
            {
                alert(printName + "\t" + eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };

    $scope.addImageCategory = function () {


        var organizationId = $("#organizationId").val();
        var imageCategory = $("#imageCategory").val();
        var imageCategory = {"categoryName": imageCategory, "channelId": imageChannelId, "organizationId": organizationId}
        if (imageCategory === "") {
            alert(enterCategoryName);
            $("#imagecategory").focus();
        } else {

            $.ajax({
                method: 'POST',
                url: getHost() + '/saveCategory.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(imageCategory)
            }).success(function (data)
            {
                alert(imageName + "\t" + eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };


    $scope.getAllCategoryDetails = function () {
        var categoryId = $("#categoryIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getCategoryByCategoryId.do?categoryId=' + categoryId,
        }).success(function (data)
        {
            $scope.categoryDetails = data.d.categoryDetails;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
        getAllSubCategories();
    }

    $scope.deleteCategory = function (categoryId) {
        var organizationId = $("#organizationIdTag").val();
        var deleteCategory = confirm(deleteCategoryPrompt);
        if (deleteCategory === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteCategory.do?categoryId=' + categoryId,
            }).success(function (data, status, headers, config) {
                $scope.categoryDetails = data.d.categoryDetails;
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    }



    $scope.getAllSubCategories = function () {

        var categoryId = $("#categoryIdTag").val();
        var subCategoryId = $("#subCategoryIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllSubCategoriesByCategoryId.do?categoryId=' + categoryId,
        }).success(function (data, status, headers, config) {
            for (var i = 0; i < data.d.details.length; i++) {
                if (data.d.details[i].subCategoryId == subCategoryId)
                {
                    $scope.subCategoryDetailsTitle = data.d.details[i];
                }
            }
            $scope.subCategoryDetails = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }

    $scope.deleteSubCategory = function (subCategoryId) {
        var organizationId = $("#organizationIdTag").val();
        var categoryId = $("#categoryIdTag").val();
        var deleteSubCategory = confirm(deleteSubCategoryPrompt);
        if (deleteSubCategory === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteSubCategory.do?subCategoryId=' + subCategoryId,
            }).success(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                if (window.location.href.indexOf("emailsubcategorydetails") > -1) {
                    window.open(getHost() + 'admin/emailsubcategory?organizationId=' + organizationId + '&categoryId=' + categoryId, "_self");
                }
                if (window.location.href.indexOf("printsubcategorydetails") > -1) {
                    window.open(getHost() + 'admin/printsubcategory?organizationId=' + organizationId + '&categoryId=' + categoryId, "_self");
                }
                if (window.location.href.indexOf("imagesubcategorydetails") > -1) {
                    window.open(getHost() + 'admin/imagesubcategory?organizationId=' + organizationId + '&categoryId=' + categoryId, "_self");
                }
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    }


    $scope.getAllExternalSourceKeywordLookups = function () {
        $http({
            method: 'GET',
            url: getHost() + '/getAllExternalSourceKeywordLookups.do',
        }).success(function (data, status, headers, config) {
            for (var i = 0; i < data.d.details.length; i++)
            {
                $scope.ExternalSourceKeywordLookups = data.d.details[i];
            }
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }


    $scope.addSubCategory = function () {
        var organizationId = $("#organizationIdTag").val();
        var subCategoryName = $("#subCategoryName").val();
        var categoryId = $("#categoryIdTag").val();
        var externalSourceKeywordLookupId = $("#optionalExternalSource").val();
        var subCategory = {"subCategoryId": 0, "subCategoryName": subCategoryName, "categoryId": categoryId, "externalSourceKeywordLookupId": externalSourceKeywordLookupId}
        if (subCategoryName === "") {
            alert(enterSubCategoryName);
            $("#subCategoryName").focus();
        } else {

            $.ajax({
                method: 'POST',
                url: getHost() + '/saveSubCategory.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(subCategory)
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                if (window.location.href.indexOf("emailsubcategory") > -1) {
                    window.open(getHost() + 'admin/emailsubcategory?organizationId=' + organizationId + '&categoryId=' + categoryId, "_self");
                }
                if (window.location.href.indexOf("printsubcategory") > -1) {
                    window.open(getHost() + 'admin/printsubcategory?organizationId=' + organizationId + '&categoryId=' + categoryId, "_self");
                }
                if (window.location.href.indexOf("imagesubcategory") > -1) {
                    window.open(getHost() + 'admin/imagesubcategory?organizationId=' + organizationId + '&categoryId=' + categoryId, "_self");
                }

            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };

    $scope.getAllExternalSourceKeywordLookups = function () {
        $http({
            method: 'GET',
            url: getHost() + '/getAllExternalSourceKeywordLookups.do',
        }).success(function (data, status, headers, config) {
            for (var i = 0; i < data.d.details.length; i++)
            {
                $scope.ExternalSourceKeywordLookups = data.d.details[i];
            }
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }
    $scope.getAllEmailBlocks = function () {
        var organizationId = $("#organizationId").val();

        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailBlocksByOrganizationId.do?organizationId=' + organizationId,
        }).success(function (data, status, headers, config) {
            $scope.emailBlocks = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }


    $scope.addEmailBlock = function () {
        var organizationId = $("#organizationIdTag").val();
        var emailBlockName = $("#emailBlockName").val();
        var externalSourceKeywordLookupId = $("#optionalExternalSource").val();
        var emailCategory = {"emailBlockName": emailBlockName, "externalSourceKeywordLookupId": externalSourceKeywordLookupId, "organizationId": organizationId}
        if (emailBlockName === "") {
            alert(enterEmailBlock);
            $("#emailBlockName").focus();
        } else {

            $.ajax({
                method: 'POST',
                url: getHost() + '/saveEmailBlock.do',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(emailCategory)
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };

    $scope.getEmailBlock = function () {
        var emailBlockId = $("#emailBlockId").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailBlocksById.do?emailBlockId=' + emailBlockId
        }).success(function (data)
        {
            for (var i = 0; i < data.d.details.length; i++)
            {
                $scope.emailBlocksTitle = data.d.details[i];
            }
            $scope.emailBlockDetails = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    }

    $scope.getAllNonAddedEmailBlock = function () {

        var emailBlockId = $("#emailBlockId").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllNonAddedEmailBlockModel.do?emailBlockId=' + emailBlockId
        }).success(function (data)
        {

            $scope.nonAddedEmailBlockDetails = data.d.details;
            if (JSON.stringify($scope.nonAddedEmailBlockDetails) == "null") {
                $("#noEmailTemplatesMessage").show();
                $("#noEmailMessage").empty().append(eval(JSON.stringify(data.d.operationStatus.messages)));
            }
            else {
                $("#noEmailTemplatesMessage").hide();
            }
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    }

    $scope.deleteEmailBlock = function () {
        var emailBlockId = $("#emailBlockId").val();
        var organizationId = $("#organizationIdTag").val();
        var deleteEmailBlock = confirm(deleteEmailBlockPrompt);
        if (deleteEmailBlock === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteEmailBlock.do?emailBlockId=' + emailBlockId,
            }).success(function (data, status, headers, config) {
                $scope.getEmailBlock = data.d.details;
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");
            }).error(function (data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    }


    $scope.getEmailModelBySubCategoryId = function () {
        var subCategoryId = $("#subCategoryIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailModelBySubCategory.do?subCategoryId=' + subCategoryId
        }).success(function (data)
        {
            $scope.emailModels = data.d.details;
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    }
    $scope.getImageModelBySubCategoryId = function () {
        var subCategoryId = $("#subCategoryIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllImageModelBySubCategory.do?subCategoryId=' + subCategoryId
        }).success(function (data)
        {
            $scope.imageModels = data.d.details;
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    }
    $scope.getPrintModelBySubCategoryId = function () {
        var subCategoryId = $("#subCategoryIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllPrintModelBySubCategory.do?subCategoryId=' + subCategoryId
        }).success(function (data)
        {
            $scope.printModels = data.d.details;
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    }

    $scope.getAllNonAddedEmailModelsBySubCategoryId = function (subCategoryID) {
        $http({
            method: 'GET',
            url: getHost() + '/getAllNonAddedEmailModels.do?subCategoryId=' + subCategoryID
        }).success(function (data)
        {
            $("#relateEmailTemplateAddButton").show().css('pointer-events', 'none');
            $scope.nonAddedEmailModelsBySubCategory = data.d.details;
            if (JSON.stringify($scope.nonAddedEmailModelsBySubCategory) == "null") {
                $("#noTemplatesMessage").show();
                $("#noTemplatesMessageSpan").empty().append(eval(JSON.stringify(data.d.operationStatus.messages)));
            }
            else {
                $("#noTemplatesMessage").hide();
            }
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }

    $scope.getAllNonAddedImageModelsBySubCategoryId = function (subCategoryID) {
        $http({
            method: 'GET',
            url: getHost() + '/getAllNonAddedImageModels.do?subCategoryId=' + subCategoryID
        }).success(function (data)
        {
            $("#relateImageTemplateAddButton").show();
            $scope.nonAddedImageModelsBySubCategory = data.d.details;
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }
    $scope.getAllNonAddedPrintModelsBySubCategoryId = function (subCategoryID) {
        $http({
            method: 'GET',
            url: getHost() + '/getAllNonAddedPrintModels.do?subCategoryId=' + subCategoryID
        }).success(function (data)
        {
            $("#relatePrintTemplateAddButton").show();
            $scope.nonAddedPrintModelsBySubCategory = data.d.details;
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }
    var selectedList = "";
    $scope.select = function (item) {
        var selectedItem = "";
        $scope.selected = item;
        selectedItem = item;
        selectedList = selectedItem;
        $("#relateEmailTemplateAddButton").css('pointer-events', 'auto');
        $("#relateRecurringEmailTemplateAddButton").css('pointer-events', 'auto');
    };
    $scope.isActive = function (item) {
        return $scope.selected === item;
    };

    $scope.relateEmailTemplateSubCategory = function () {
        var subCategoryId = $("#subCategoryIdTag").val();
        var categoryId = $("#categoryIdTag").val();
        var organizationId = $("#organizationIdTag").val();
        var emailModelId = eval(JSON.stringify(selectedList.emailModelId));
        var emailTemplateRelate = {"emailModelId": emailModelId, "subCategoryId": parseInt(subCategoryId)}

        $.ajax({
            method: 'POST',
            url: getHost() + '/saveSubCategoryEmailModel.do',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(emailTemplateRelate)
        }).success(function (data)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            window.open(getHost() + 'admin/emailsubcategorydetails?organizationId=' + organizationId + '&categoryId=' + categoryId + '&subCategoryId=' + subCategoryId, "_self");

        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });


    };

    $scope.relatePrintTemplateSubCategory = function () {
        var subCategoryId = $("#subCategoryIdTag").val();
        var categoryId = $("#categoryIdTag").val();
        var organizationId = $("#organizationIdTag").val();
        var printModelId = eval(JSON.stringify(selectedList.printModelId));
        var printTemplateRelate = {"printModelId": printModelId, "subCategoryId": parseInt(subCategoryId)}

        $.ajax({
            method: 'POST',
            url: getHost() + '/saveSubCategoryPrintModel.do',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(printTemplateRelate)
        }).success(function (data)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            window.open(getHost() + 'admin/printsubcategorydetails?organizationId=' + organizationId + '&categoryId=' + categoryId + '&subCategoryId=' + subCategoryId, "_self");

        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };
    $scope.deleteSubCategoryEmailModel = function (subCategoryEmailModelId) {
        var subCategoryId = $("#subCategoryIdTag").val();
        var categoryId = $("#categoryIdTag").val();
        var organizationId = $("#organizationIdTag").val();

        var deleteEmailTemplate = confirm(deleteSubCategoryRelationPrompt);
        if (deleteEmailTemplate === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteSubCategoryEmailModel.do?subCategoryEmailModelID=' + subCategoryEmailModelId
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/emailsubcategorydetails?organizationId=' + organizationId + '&categoryId=' + categoryId + '&subCategoryId=' + subCategoryId, "_self");
            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };


    $scope.getAllRecurringByOrganizationId = function () {

        var organizationId = $("#organizationIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllRecurringByOrganizationId.do?organizationId=' + organizationId
        }).success(function (data)
        {
            $scope.allRecurringEmails = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };

    $scope.getAllNonAddedRecurringEmail = function () {
        var organizationId = $("#organizationIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllNonAddedRecurringEmail.do?organizationId=' + organizationId
        }).success(function (data)
        {
            $("#relateRecurringEmailTemplateAddButton").show().css('pointer-events', 'none');
            $scope.nonAddedRecurringEmails = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    };


    $scope.saveOrganizationRecurringEmail = function () {
        var organizationId = $("#organizationIdTag").val();
        var recurringEmailTemplateId = selectedList.recurringEmailTemplateId;
        var saveOrganizationRecurringEmailById = {"recurringEmailTemplateId": recurringEmailTemplateId, "organizationId": organizationId};

        $.ajax({
            method: 'POST',
            url: getHost() + '/saveOrganizationRecurringEmail.do',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(saveOrganizationRecurringEmailById)
        }).success(function (data)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };

    $scope.deleteOrganizationRecurringEmail = function (organizationRecurringEmailLookupId) {
        var organizationId = $("#organizationIdTag").val();
        var deleteRecurring = confirm(removeRecurringEmailPrompt);
        if (deleteRecurring == true) {
            $http({
                method: 'GET',
                url: getHost() + '/deleteOrganizationRecurringEmail.do?organizationRecurringEmailLookupId=' + organizationRecurringEmailLookupId
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
//                $scope.deleteRecurringEmails=data.d.details;
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");
            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };

    $scope.getAllMarketingCategory = function () {

        var organizationId = $("#organizationIdTag").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllMarketingCategoryByOrganizationId.do?organizationId=' + organizationId
        }).success(function (data)
        {
            $scope.marketingCategories = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }
    $scope.getAllMarketingCategoryById = function () {

        var organizationId = $("#organizationIdTag").val();
        var marketingCategoryId = $("#marketingCategoryId").val();
        $http({
            method: 'GET',
            url: getHost() + '/getByMarketingCategoryId.do?marketingCategoryId=' + marketingCategoryId
        }).success(function (data)
        {
            for (var i = 0; i < data.d.details.length; i++)
            {
                $scope.marketingCategoryTitle = data.d.details[i];
            }
            $scope.getMarketingCategories = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }

    $scope.addMarketingCategory = function () {

        var marketingCategoryName = $("#marketingCategoryName").val();
        var organizationId = $("#organizationIdTag").val();
        var categoryName = (eval(JSON.stringify(marketingCategoryName)));
        var organizationIdNew = (eval(JSON.stringify(organizationId)));
        if (categoryName == "")
        {
            alert(enterCategoryName);
            $("#marketingCategoryName").focus();
            return false;
        }
        var marketingCategory = {"marketingCategoryName": categoryName, "organizationId": organizationIdNew}
        $.ajax({
            method: 'POST',
            url: getHost() + '/saveMarketingCategory.do',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(marketingCategory)
        }).success(function (data)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");

        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };

    $scope.deleteMarketingCategory = function () {
        var organizationId = $("#organizationIdTag").val();
        var marketingCategoryId = $("#marketingCategoryId").val();
        var deleteEmailTemplate = confirm(deleteCategoryPrompt);
        if (deleteEmailTemplate === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteMarketingCategory.do?marketingCategoryId=' + marketingCategoryId
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");
            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    }


    $scope.gellAllMarketingProgramList = function () {

        $http({
            method: 'GET',
            url: getHost() + '/getAllMarketingPrograms.do'
        }).success(function (data)
        {
            $scope.marketingProgramLists = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }

    $scope.getEmailBlocks = function () {

        var emailBlockId = $("#emailBlockId").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllEmailBlockModelById.do?emailBlockId=' + emailBlockId
        }).success(function (data)
        {
            $scope.getEmailBlockTemplate = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });

    }

    $scope.deleteEmailBlockModel = function (emailBlockModelLookupId) {

        var organizationId = $("#organizationIdTag").val();
        var deleteEmailBlocks = confirm(deleteTemplateRelationPrompt);
        if (deleteEmailBlocks === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteEmailBlockModel.do?emailBlockModelLookupId=' + emailBlockModelLookupId
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/organizationdetails?organizationId=' + organizationId, "_self");
            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };


    $scope.marketingProgramsById = function () {

        var organizationId = $("#organizationIdTag").val();
        var marketingCategoryId = $("#marketingCategoryId").val();

        $http({
            method: 'GET',
            url: getHost() + '/getMarketingProgramsByCategoryId.do?marketingCategoryId=' + marketingCategoryId
        }).success(function (data)
        {
            $scope.getMarketingPrograms = data.d.details;
        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }

    $scope.deleteMarketingCategoryProgram = function (marketingProgramId) {
        var marketingCategoryId = $("#marketingCategoryId").val();
        var organizationId = $("#organizationIdTag").val();
        var deleteMarketingProgram = confirm(marketingTemplateRelationPrompt);
        if (deleteMarketingProgram === true)
        {
            $http({
                method: 'GET',
                url: getHost() + '/deleteMarketingCategoryProgram.do?marketingCategoryProgramId=' + marketingProgramId
            }).success(function (data)
            {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/marketingcategory?organizationId=' + organizationId + '&marketingCategoryId=' + marketingCategoryId, "_self");
            }).error(function (data) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });
        }
    };

    $scope.getAllNonAddedMarketingProgram = function () {

        var marketingCategoryId = $("#marketingCategoryId").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllNonAddedMarketingPrograms.do?marketingCategoryId=' + marketingCategoryId
        }).success(function (data)
        {

            $scope.getNonAddedMarketingPrograms = data.d.details;
            if (JSON.stringify($scope.getNonAddedMarketingPrograms) == "null") {
                $("#noMarketingTemplatesMessage").show();
                $("#noMarketingMessage").empty().append(eval(JSON.stringify(data.d.operationStatus.messages)));
            }
            else {
                $("#noMarketingTemplatesMessage").hide();
            }


        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    }

    var selectedListItems = "";
    $scope.selectedItems = function (item) {
        var selectedItemList = "";
        $scope.selected = item;
        selectedItemList = item;
        selectedListItems = selectedItemList;

        $("#relateNonAddedMarketingPrograms").css('pointer-events', 'auto');
    };
    $scope.isActiveMode = function (item) {
        return $scope.selected === item;
    };


    $scope.addMarketingtemplate = function () {
        var organizationId = $("#organizationIdTag").val();
        var marketingCategoryId = $("#marketingCategoryId").val();
        var marketingProgramId = eval(JSON.stringify(selectedListItems.marketingProgramId));
        var marketingAddTemplate = {"marketingProgramId": marketingProgramId, "marketingCategoryId": marketingCategoryId}

        $.ajax({
            method: 'POST',
            url: getHost() + '/saveMarketingCategoryProgram.do',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(marketingAddTemplate)
        }).success(function (data)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            window.open(getHost() + 'admin/marketingcategory?organizationId=' + organizationId + '&marketingCategoryId=' + marketingCategoryId, "_self");

        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });


    };

    var emailBlockModel = "";
    $scope.selectedBlockItems = function (item) {
        var BlockselectedItemList = "";
        $scope.selected = item;
        BlockselectedItemList = item;
        emailBlockModel = BlockselectedItemList;

        $("#relateNonAddedMarketingPrograms").css('pointer-events', 'auto');
    };
    $scope.isActiveBlockMode = function (item) {
        return $scope.selected === item;
    };



    $scope.addEmailBlocktemplate = function () {
        var emailBlockId = $("#emailBlockId").val();
        var organizationId = $("#organizationIdTag").val();
        var marketingCategoryId = $("#marketingCategoryId").val();
        var emailBlockModelId = eval(JSON.stringify(emailBlockModel.emailBlockModelId));
        var blockEmailTemplate = {"emailBlockModelId": emailBlockModelId, "emailBlockId": emailBlockId}

        $.ajax({
            method: 'POST',
            url: getHost() + '/saveEmailBlockModel.do',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(blockEmailTemplate)
        }).success(function (data)
        {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            window.open(getHost() + 'admin/emailblock?organizationId=' + organizationId + '&emailBlockId=' + emailBlockId, "_self");

        }).error(function (data) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });


    };

}
