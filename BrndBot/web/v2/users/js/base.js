/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

   $(document).ready(function () {
    $("#emailschedule").click(function () {
    
    $("#emailSchedulePopUp").show();
    $("#addOrganizationPopupDiv").show();
    
    });
    
    $("#previewEmailsPopUp").click(function () {
        $("#emailPreviewPopUp").show();
         $("#addOrganizationPopupDiv").show();
        
    });
    
    $("#addOrganizationPopupDiv").click(function ()
    {
        $("#addOrganizationPopupDiv").hide();
         $("#emailSchedulePopUp").hide();
         $("#emailPreviewPopUp").hide();
        
    });
});