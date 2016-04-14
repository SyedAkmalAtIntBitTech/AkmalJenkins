/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

$(document).ready(function () {
    $("#addOrganizationPopupDiv").click(function () {

        $("#addSubCategoryPopup").hide();
        $("#addPrintCategoryPopup").hide();
        $("#addOrganizationPopup").hide();
        $("#addOrganizationPopupDiv").hide();
        $("#addImageCategory").hide();
        $("#addEmailBlockPopUp").hide();
        $("#createColorCode").hide();
        $("#addcolorpalette").hide();
        $("#fontPopup").hide();
        $("#addEmailTemplatePopUp").hide();
        $("#addMarketingPopUp").hide();
        $("#editMarketingProgramsPopup").hide();
        $("#addMarketingProgramsPopup").hide();
        $("#addRecurringEmailTemplatePopUp").hide();
        $(".overflowhide").css("overflow", "auto");
        $("#addRecurringPopUp").hide();
        $("#addMarketingTemplatePopUp").hide();
        $("#addGroup").hide();
        $("#globalImageId").hide();
        $("#editImage").hide();
        
    
    });


$("#addRecurringEmailTemplateButton").click(function () {
    $("#addRecurringEmailTemplatePopUp").show();
    $("#addOrganizationPopupDiv").show();
});
$("#addNewTemplate").click(function () {
    $("#addRecurringPopUp").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addGlobalImage").click(function () {
    $("#globalImageId").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addOneTimeActionsButton").click(function () {
    $("#addOrganizationPopupDiv").show();
    $(".overflowhide").css("overflow", "hidden").animate({scrollTop: 0}, '10', 'swing');
    $("#addMarketingProgramsPopup").show();
});

$("#addTemplateButton").click(function () {
    $("#addEmailTemplatePopUp").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addSubCategory").click(function () {
    $("#addSubCategoryPopup").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addCategoryPrint").click(function () {
    $("#addPrintCategoryPopup").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addCategoryImage").click(function () {
    $("#addImageCategory").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addEmailBlock").click(function () {
    $("#addEmailBlockPopUp").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addMarketingCategory").click(function () {
    $("#addMarketingPopUp").show();
    $("#addOrganizationPopupDiv").show();
});


$("#addMarketingTemplate").click(function () {
    $("#addMarketingTemplatePopUp").show();
    $("#addOrganizationPopupDiv").show();
});

$("#addEmailTemplate").click(function () {
    $("#EmailBlockPopUp").show();
    $("#addOrganizationPopupDiv").show();
});


$("#addCompanyGroup").click(function () {
    $("#addGroup").show();
    $("#addOrganizationPopupDiv").show();
});



$("#addMarketingProgramsPopupDiv").click(function () {
    $("#addMarketingProgramsPopup").hide();
    $("#addMarketingProgramsPopupDiv").hide();
    $(".overflowhide").css("overflow", "auto");
});


});