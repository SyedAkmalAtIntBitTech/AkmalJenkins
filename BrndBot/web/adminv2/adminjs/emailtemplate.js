/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
    $(document).ready(function () {   
    if(window.location.href.indexOf("edit=yes") > -1) 
    {
        $("#uploadOnEdit").show();
        $("#nameThisTemplate").hide();
        $("#selectOrgranization").show();
        $("#editTitle").show();
        $("#createTitle").hide();
        $("#createNewTemplate").hide();
         $("#saveTemplate").show();
       
    }
    if(window.location.href.indexOf("edit=no") > -1) 
    {
        $("#uploadOnEdit").show();
        $("#nameThisTemplate").show();
        $("#selectOrgranization").hide();  
        $("#editTitle").hide();
        $("#createTitle").show();
         $("#createNewTemplate").show();
         $("#saveTemplate").hide();

       
    }
});
       

