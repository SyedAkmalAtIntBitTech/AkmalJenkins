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
            
           $("#addOneTimeActionsButton").click(function (){               
              $("#addOrganizationPopupDiv").show();
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#addMarketingProgramsPopup").show();
           });
           
           $("#addTemplateButton").click(function (){
              $("#addEmailTemplatePopUp").show();
              $("#addOrganizationPopupDiv").show();
           });
           
           $("#addSubCategory").click(function (){
              $("#addSubCategoryPopup").show();
              $("#addOrganizationPopupDiv").show();
           });
           
           $("#addCategoryPrint").click(function (){
              $("#addPrintCategoryPopup").show();
              $("#addOrganizationPopupDiv").show();
           });
           
            $("#addCategoryImage").click(function (){
              $("#addImageCategory").show();
              $("#addOrganizationPopupDiv").show();
           });
           
            $("#addEmailBlock").click(function (){
              $("#addEmailBlockPopUp").show();
              $("#addOrganizationPopupDiv").show();
           });
           
           $("#addMarketingCategory").click(function (){
              $("#addMarketingPopUp").show();
              $("#addOrganizationPopupDiv").show();
           });
           $("#addMarketingProgramsPopupDiv").click(function (){
               $("#addMarketingProgramsPopup").hide();
               $("#addMarketingProgramsPopupDiv").hide();
               $(".overflowhide").css("overflow","auto");
            });
           $("#addOrganizationPopupDiv").click(function (){
               $("#addMarketingPopUp").hide();
               $("#addEmailTemplatePopUp").hide();
               $("#addSubCategoryPopup").hide();
               $("#addPrintCategoryPopup").hide();
               $("#addOrganizationPopup").hide();
                $("#addOrganizationPopupDiv").hide();
                $("#addImageCategory").hide();
                 $("#addEmailBlockPopUp").hide();
                 $("#editMarketingProgramsPopup").hide();
                 $("#addMarketingProgramsPopup").hide();
                 $(".overflowhide").css("overflow","auto");
           });
       });
       var emailChannel="email";
       var printChannel="print";
       var imageChannel="image";
       var emailChannelId =3;
       var printChannelId=2;
       var imageChannelId=1;
       
       