/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
       $(document).ready(function () {
           
//           $("#addorg").click(function (){
//              $("#addorganizationpopup").show();
//              $("#addorgpopupdiv").show();
//           });
           
           $("#addcatprint").click(function (){
              $("#addPrintCategoryPopup").show();
              $("#addOrganizationPopupDiv").show();
           });
           
            $("#addCategoryImage").click(function (){
              $("#addImageCategory").show();
              $("#addOrganizationPopupDiv").show();
           });
           
           $("#addOrganiationPopupDiv").click(function (){
               $("#addPrintCategoryPopup").hide();
               $("#addOrganizationPopup").hide();
                $("#addOrganizationPopupDiv").hide();
                $("#addImageCategory").hide();
           });
       });
       var emailChannel="email";
       var printChannel="print";
       var imageChannel="image";
       var emailChannelId =1;
       var printChannelId=2;
       var imageChannelId=3;
       
       