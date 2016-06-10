/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//This is to get the parameters from URL
var currentEnvironment = "local";
//var currentEnvironment = "dev";

var environmentLocal = "local";
var environmentDev = "dev";

var globalurl = "http://development2.brndbot.com/BrndBot/user";
var localurl = "http://localhost:8081/BrndBot";

factoryApp.service('configurationService', function (){
    this.getGlobalURL = function () {
        if (currentEnvironment === environmentLocal)
            return localurl;
        if (currentEnvironment === environmentDev)
            return globalurl;
    }; 
  
  //**************** assetsFactory **********************//
  
    this.downloadImageURL = function () {
        return this.getGlobalURL() + "/downloadImage";
    };
    
    this.imageListURL = function () {
        return this.getGlobalURL() + "/getImageList";
    };
   
    this.globalColorsURL = function () {
        return this.getGlobalURL() + "/getColorThemeById";
    };
    
    this.globalImageURL = function () {
        return this.getGlobalURL() + "/getGlobalImageById";
    };
    
    this.globalFontsURL = function () {
        return this.getGlobalURL() + "/getFontById";
    };
    
    this.allColorThemesURL = function () {
        return this.getGlobalURL() + "/getAllColorThemes";
    };
    
    this.allGlobalImageURL = function () {
        return this.getGlobalURL() + "/getAllGlobalImages";
    };
    
    this.allFontsURL = function () {
        return this.getGlobalURL() + "/getAllFonts";
    };
    this.saveFontURL = function () {
        return this.getGlobalURL() + "/saveFont";
    };
    
    this.colorThemeURL = function () {
        return this.getGlobalURL() + "/saveColorTheme";
    };
    
    this.saveGlobalImageURL = function () {
        return this.getGlobalURL() + "/saveGlobalImage";
    };
    
    this.updateGlobalImageURL = function () {
        return this.getGlobalURL() + "/updateGlobalImage";
    };
    
    this.updateFontURL = function () {
        return this.getGlobalURL() + "/updateFont";
    };
    
    this.updateColorThemeURL = function () {
        return this.getGlobalURL() + "/updateColorTheme";
    };
    
    this.deleteFontURL = function () {
        return this.getGlobalURL() + "/deleteFont";
    };
    
    this.deleteGlobalImageURL = function () {
        return this.getGlobalURL() + "/deleteGlobalImage";
    };
    
    this.imageUniquenessURL = function () {
        return this.getGlobalURL() + "/checkGlobalImageUniqueness";
    };
    
    this.deleteColorThemeURL = function () {
        return this.getGlobalURL() + "/deleteColorTheme";
    };
    
    
      //**************** blockModelFactory **********************//
      
     this.emailBlockModelLookupURL = function () {
        return this.getGlobalURL() + "/getAllEmailBlockModelById";
    };
       
    this.allEmailBlockModelURL = function () {
        return this.getGlobalURL() + "/getAllEmailBlockModelsByBlockId";
    };
        
    this.emailBlockModelURL = function () {
        return this.getGlobalURL() + "/getAllEmailBlockModel";
    };
    
    this.emailBlockModelIdURL = function () {
        return this.getGlobalURL() + "/getEmailBlockModelById";
    };
        
    this.nonAddedEmailBlockModelURL = function () {
        return this.getGlobalURL() + "/getAllNonAddedEmailBlockModel";
    };
    
    this.saveBlockModelURL = function () {
        return this.getGlobalURL() + "/saveBlockModel";
    };
        
    this.saveEmailBlockModelURL = function () {
        return this.getGlobalURL() + "/saveEmailBlockModel";
    };
    
    this.updateBlockModelURL = function () {
        return this.getGlobalURL() + "/updateBlockModel";
    };
    
    this.deleteBlockModelURL = function () {
        return this.getGlobalURL() + "/deleteBlockModel";
    };
    
    this.deleteEmailBlockModelURL = function () {
        return this.getGlobalURL() + "/deleteEmailBlockModel";
    };
    
      
      //**************** categoryFactory **********************//
      
    this.categoryURL = function () {
        return this.getGlobalURL() + "/getCategoryByCategoryId";
    };
    
    this.channelURL = function () {
        return this.getGlobalURL() + "/getAllOrganizationCategoryByOrganizationId";
    };
    
    this.saveCategoryURL = function () {
        return this.getGlobalURL() + "/saveCategory";
    };
    
    this.updateCategoryURL = function () {
        return this.getGlobalURL() + "/updateCategory";
    };
    
    this.deleteCategoryURL = function () {
        return this.getGlobalURL() + "/deleteCategory";
    };
    
    this.allCompanyCategoriesURL = function () {
        return this.getGlobalURL() + "/getAllCompanyCategories";
    };
 
      //**************** emailBlockFactory **********************//
  
    this.saveEmailBlockURL = function () {
        return this.getGlobalURL() + "/saveEmailBlock";
    };
    
    this.deleteEmailBlockURL = function () {
        return this.getGlobalURL() + "/deleteEmailBlock";
    };
    
    this.allEmailBlocksURL = function () {
        return this.getGlobalURL() + "/getAllEmailBlocksByOrganizationId";
    };
    
   //**************** emailFactory **********************//    
    
    this.sendEmailPostURL = function () {
        return this.getGlobalURL() + "/email/send";
    };
    
    this.sendEmailGetURL = function () {
        return this.getGlobalURL() + "/email/tags";
    };
    
    this.previewServletURL = function () {
        return this.getGlobalURL() + "/email/previewServlet";
    };
    
    //**************** externalContentFactory **********************//
    
    this.activatedGetURL = function () {
        return this.getGlobalURL() + "/externalContent/isActivated";
    };
    
    this.activationLinkURL = function () {
        return this.getGlobalURL() + "/externalContent/getActivationLink";
    };
    
    this.listDataURL = function () {
        return this.getGlobalURL() + "/externalContent/getListData";
    };
    
    this.layoutEmailModelURL = function () {
        return this.getGlobalURL() + "/externalContent/getLayoutEmailModelById";
    };
    
    //**************** modelFactory **********************//
    
    this.emailModelURL = function () {
        return this.getGlobalURL() + "/getAllEmailModelBySubCategory";
    };
    
    this.emailModelsIdURL = function () {
        return this.getGlobalURL() + "/getAllEmailModelsBySubCategoryId";
    };
    
    this.nonAddedEmailModelsURL = function () {
        return this.getGlobalURL() + "/getAllNonAddedEmailModels";
    };
    
    this.nonAddedImageModelsURL = function () {
        return this.getGlobalURL() + "/getAllNonAddedImageModels";
    };
    
    this.nonAddedPrintModelsURL = function () {
        return this.getGlobalURL() + "/getAllNonAddedPrintModels";
    };
    
    this.printModelURL = function () {
        return this.getGlobalURL() + "/getAllPrintModelBySubCategory";
    };
    
    this.imageModelSubCategoryURL = function () {
        return this.getGlobalURL() + "/getAllImageModelBySubCategory";
    };
    
    this.emailModelURL = function () {
        return this.getGlobalURL() + "/getAllEmailModel";
    };
    
    this.emailModelIdURL = function () {
        return this.getGlobalURL() + "/getEmailModelById";
    };
    
    this.imageModelURL = function () {
        return this.getGlobalURL() + "/getAllImageModel";
    };
    
    this.imageModelIdURL = function () {
        return this.getGlobalURL() + "/getImageModelById";
    };
    
    this.allPrintModelURL = function () {
        return this.getGlobalURL() + "/getAllPrintModel";
    };
    
    this.printModelIdURL = function () {
        return this.getGlobalURL() + "/getPrintModelById";
    };
    
    this.saveEmailModelURL = function () {
        return this.getGlobalURL() + "/saveEmailModel";
    };
    
    this.editEmailModelURL = function () {
        return this.getGlobalURL() + "/editEmailModel";
    };
    
    this.deleteEmailModelURL = function () {
        return this.getGlobalURL() + "/deleteEmailModel";
    };
    
    this.saveCategoryEmailModelURL = function () {
        return this.getGlobalURL() + "/saveSubCategoryEmailModel";
    };
    
    this.deleteCategoryEmailModelURL = function () {
        return this.getGlobalURL() + "/deleteSubCategoryEmailModel";
    };
    
    this.saveCategoryPrintModelURL = function () {
        return this.getGlobalURL() + "/saveSubCategoryPrintModel";
    };
    
    this.deleteCategoryPrintModelURL = function () {
        return this.getGlobalURL() + "/deleteSubCategoryPrintModel";
    };
    
    this.saveCategoryImageModelURL = function () {
        return this.getGlobalURL() + "/saveSubCategoryImageModel";
    };
    
    this.deleteCategoryImageModelURL = function () {
        return this.getGlobalURL() + "/deleteSubCategoryImageModel";
    };
    
    this.savePrintModelURL = function () {
        return this.getGlobalURL() + "/savePrintModel";
    };
    
    this.editPrintModelURL = function () {
        return this.getGlobalURL() + "/editPrintModel";
    };
    
    this.deletePrintModelURL = function () {
        return this.getGlobalURL() + "/deletePrintModel";
    };
    
    this.saveImageModelURL = function () {
        return this.getGlobalURL() + "/saveImageModel";
    };
    
    this.updateImageModelURL = function () {
        return this.getGlobalURL() + "/updateImageModel";
    };
    
    this.deleteImageModelURL = function () {
        return this.getGlobalURL() + "/deleteImageModel";
    };
    
    //**************** organizationFactory **********************//
    
    this.allOrganizationURL = function () {
        return this.getGlobalURL() + "/getAllOrganizations";
    };
    
    this.organizationURL = function () {
        return this.getGlobalURL() + "/onboarding/getAllOnlyOrganizations";
    };
    
    this.saveOrganizationURL = function () {
        return this.getGlobalURL() + "/saveOrganization";
    };
    
    this.updateOrganizationURL = function () {
        return this.getGlobalURL() + "/updateOrganization";
    };
    
    this.deleteOrganizationURL = function () {
        return this.getGlobalURL() + "/deleteOrganization";
    };
    
    this.getOrganizationURL = function () {
        return this.getGlobalURL() + "/getOrganizationById";
    };
    
    
    //**************** scheduleActionsFactory **********************//
    
    this.getActionsURL = function () {
        return this.getGlobalURL() + "/actions/getActions";
    };
    
    this.scheduleEmailURL = function () {
        return this.getGlobalURL() + "/actions/scheduleEmail";
    };
    
    this.scheduleEmailActionsURL = function () {
        return this.getGlobalURL() + "/actions/scheduleEmailActions";
    };
    
    this.scheduleSocialPostActionsURL = function () {
        return this.getGlobalURL() + "/actions/scheduleSocialPostActions";
    };
    
    this.scheduleSocialPostURL = function () {
        return this.getGlobalURL() + "/actions/scheduleSocialPost";
    };
    
    //**************** signupFactory **********************//
    
    this.signupURL = function () {
        return this.getGlobalURL() + "/signup";
    };
    
    this.forgotPasswordURL = function () {
        return this.getGlobalURL() + "/signup/forgotPassword";
    };
    
    this.resetPasswordURL = function () {
        return this.getGlobalURL() + "/signup/resetPassword";
    };
    
    
    //**************** socialPostFactory **********************//
    
    this.postToFacebookURL = function () {
        return this.getGlobalURL() + "/socialPost/postToFacebook";
    };
    
    this.postToTwitterURL = function () {
        return this.getGlobalURL() + "/socialPost/postToTwitter";
    };
    
    this.getShortenUrl = function () {
        return "http://api.bit.ly/v3/shorten";
    };
    
    //**************** subCategoryFactory **********************//
    
    this.saveSubCategoryURL = function () {
        return this.getGlobalURL() + "/saveSubCategory";
    };
    
    this.subCategoryURL = function () {
        return this.getGlobalURL() + "/getSubCategoryById";
    };
    
    this.deleteSubCategoryURL = function () {
        return this.getGlobalURL() + "/deleteSubCategory";
    };
    
    this.allSubCategoriesURL = function () {
        return this.getGlobalURL() + "/getAllSubCategoriesByCategoryId";
    };
    
    this.externalSourceURL = function () {
        return this.getGlobalURL() + "/getAllExternalSourceKeywordLookups";
    };
    
    this.allExternalSourcesURL = function () {
        return this.getGlobalURL() + "/onboarding/getAllExternalSources";
    };
    
     //**************** UploadImageFactory **********************//
     
    this.uploadByAdminURL = function () {
        return this.getGlobalURL() + "/UploadByAdmin";
    };
    
    this.uploadByUserURL = function () {
        return this.getGlobalURL() + "/UploadByUser";
    };

    //************** companyFactory ********************//
    
    this.currentCompanyURL = function () {
        return this.getGlobalURL() + "/getCurrentCompany";
    };
    
    this.companyURL = function () {
        return this.getGlobalURL() + "/getAllCompanies";
    };
    
    this.nonAddedGroupsURL = function () {
        return this.getGlobalURL() + "/getNonAddedGroups";
    };
    
    this.companyDetailsURL = function () {
        return this.getGlobalURL() + "/getCompanyDetailsById";
    };
    
    this.allBlocksForCompanyURL = function () {
        return this.getGlobalURL() + "/getAllBlocksForCompany";
    };
    
    this.saveGroupURL = function () {
        return this.getGlobalURL() + "/saveGroup";
    };
    
    this.deleteGroupURL = function () {
        return this.getGlobalURL() + "/deleteGroup";
    };
    
    
    //************** imageFactory ********************//

    this.imageIdURL = function () {
        return this.getGlobalURL() + "/images/get";
    };
    
    this.saveImageURL = function () {
        return this.getGlobalURL() + "/images/save";
    };
   this.saveLogoURL = function () {
        return this.getGlobalURL() + "/images/uploadLogo";
    };
    
        //************** emailListFactory ********************//

    this.emailListURL = function () {
        return this.getGlobalURL() + "/emaillist/get";
    };
    
    this.emailListSaveURL = function () {
        return this.getGlobalURL() + "/emaillist/save";
    };
    
        //************** marketingFactory ********************//
     
    this.marketingProgramsURL = function () {
        return this.getGlobalURL() + "/getMarketingProgramsByCategoryId";
    };
    
    this.nonAddedMarketingURL = function () {
        return this.getGlobalURL() + "/getAllNonAddedMarketingPrograms";
    };
    
    this.allMarketingCategoryURL = function () {
        return this.getGlobalURL() + "/getAllMarketingCategoryByOrganizationId";
    };
    
    this.saveMarketingURL = function () {
        return this.getGlobalURL() + "/saveMarketingCategory";
    };
    
    this.deleteMarketingCategoryURL = function () {
        return this.getGlobalURL() + "/deleteMarketingCategory";
    };
    
    this.marketingCategoryIdURL = function () {
        return this.getGlobalURL() + "/getByMarketingCategoryId";
    };
    
    this.allMarketingProgramsURL = function () {
        return this.getGlobalURL() + "/getAllMarketingPrograms";
    };
    
    this.saveMarketingProgramURL = function () {
        return this.getGlobalURL() + "/saveMarketingProgramActions";
    };
    
    this.updateMarketingProgramURL = function () {
        return this.getGlobalURL() + "/updateMarketingProgramActions";
    };
    
    this.saveMarketingCategoryURL = function () {
        return this.getGlobalURL() + "/saveMarketingCategoryProgram";
    };
    
    this.deleteMarketingCategoryProgramURL = function () {
        return this.getGlobalURL() + "/deleteMarketingCategoryProgram";
    };
    
    this.marketingProgramActionsIdURL = function () {
        return this.getGlobalURL() + "/getMarketingProgramActionsById";
    };
    
    this.companyMarketingCategoriesURL = function () {
        return this.getGlobalURL() + "/getCompanyMarketingCategories";
    };
    
        //************** companyImagesFactory ********************/
 
    this.companyImagesURL = function () {
        return this.getGlobalURL() + "/companyImages/get";
    };
    
    this.deleteCompanyImagesURL = function () {
        return this.getGlobalURL() + "/companyImages/delete";
    };
    
    this.downloadURL = function () {
        return this.getGlobalURL() + "/download/HTML";
    };
    
        //************** onBoardingFactory ********************/
    
    this.usersURL = function () {
        return this.getGlobalURL() + "/onboarding/isUserUnique";
    };
    
    this.saveUserURL = function () {
        return this.getGlobalURL() + "/onboarding/saveUser";
    };
    
    this.saveStudioIdURL = function () {
        return this.getGlobalURL() + "/onboarding/saveStudioId";
    };
    
    this.completedActivationURL = function () {
        return this.getGlobalURL() + "/onboarding/completedActivation";
    };
    
    this.saveCompanyURL = function () {
        return this.getGlobalURL() + "/onboarding/saveCompany";
    };
    
    this.colorsForLogoURL = function () {
        return this.getGlobalURL() + "/onboarding/getColorsForLogo";
    };
    
    this.saveCompanyLogoURL = function () {
        return this.getGlobalURL() + "/onboarding/saveCompanyLogo";
    };
    
        //************** recurringEmailFactory ********************/    
    
    this.saveRecurringEmailURL = function () {
        return this.getGlobalURL() + "/saveRecurringEmail";
    };
    
    this.saveOrganizationRecurringEmailURL = function () {
        return this.getGlobalURL() + "/saveOrganizationRecurringEmail";
    };
    
    this.deleteRecurringEmailURL = function () {
        return this.getGlobalURL() + "/deleteRecurringEmail";
    };
    
    this.deleteOrganizationRecurringEmailURL = function () {
        return this.getGlobalURL() + "/deleteOrganizationRecurringEmail";
    };
    
    this.updateRecurringEmailURL = function () {
        return this.getGlobalURL() + "/updateRecurringEmail";
    };
    
    this.recurringEmailTemplateIdURL = function () {
        return this.getGlobalURL() + "/getRecurringEmailTemplateById";
    };
    
    this.allRecurringOrganizationURL = function () {
        return this.getGlobalURL() + "/getAllRecurringByOrganizationId";
    };
    
    this.allNonAddedRecurringEmailURL = function () {
        return this.getGlobalURL() + "/getAllNonAddedRecurringEmail";
    };
    
    this.allRecurringEmailsURL = function () {
        return this.getGlobalURL() + "/getAllRecurringEmails";
    };
    
        //************** settingsFactory ********************/    
    
    this.getColorsURL = function () {
        return this.getGlobalURL() + "/settings/getColors";
    };
    
    this.setColorsURL = function () {
        return this.getGlobalURL() + "/settings/setColors";
    };
    
    this.saveEmailSettingsURL = function () {
        return this.getGlobalURL() + "/settings/saveEmailSettings";
    };
    
    this.getEmailSettingsURL = function () {
        return this.getGlobalURL() + "/settings/getEmailSettings";
    };
    
    this.getAllPreferencesURL = function () {
        return this.getGlobalURL() + "/settings/getAllPreferences";
    };
    
    this.globalAndUserColorsURL = function () {
        return this.getGlobalURL() + "/settings/getGlobalAndUserColors";
    };
    
    this.changeLogoURL = function () {
        return this.getGlobalURL() + "/settings/changeLogo";
    };
    
    this.facebookURL = function () {
        return this.getGlobalURL() + "/settings/facebookDetails";
    };
    
    this.twitterURL = function () {
        return this.getGlobalURL() + "/settings/twitterDetails";
    };
    
    this.fbLoginURL = function () {
        return this.getGlobalURL() + "/settings/fbAuthURL";
    };
    
    this.fbGetTokenURL = function () {
        return this.getGlobalURL() + "/settings/fbGetToken";
    };
    
    this.twitterLoginURL = function () {
        return this.getGlobalURL() + "/settings/twitterAuthURL";
    };
    
    this.twitterGetTokenURL = function () {
        return this.getGlobalURL() + "/settings/twitterGetToken";
    };
    
        //************** userFactory ********************/    
    
    this.userWelcomePageURL = function () {
        return this.getGlobalURL() + "/user";
    };
    
    this.userJspPagesURL = function () {
        return this.getGlobalURL() + "/user";
    };
      
        //************** yourPlanFactory ********************/    
    
    this.scheduledEntitiesURL = function () {
        return this.getGlobalURL() + "/GetScheduledEntities";
    };
    
    this.changeScheduleURL = function () {
        return this.getGlobalURL() + "/ChangeSchedule";
    };
    
    this.addActionURL = function () {
        return this.getGlobalURL() + "/AddAction";
    };
    
    this.scheduledEmailURL = function () {
        return this.getGlobalURL() + "/GetScheduledEmailDetail";
    };
    
    this.scheduledSocialPostURL = function () {
        return this.getGlobalURL() + "/GetScheduledSocialPostDetail";
    };
    
    this.postToSocialURL = function () {
        return this.getGlobalURL() + "/PostToSocial";
    };
    
    //*****************loginFactory********************
    
    this.signinURL = function () {
        return this.getGlobalURL() + "//login";
    };
    
    this.resetLoginPasswordURL = function () {
        return this.getGlobalURL() + "/resetpassword";
    };
    
    this.changePasswordURL = function () {
        return this.getGlobalURL() + "/changepassword";
    };
    
    this.logoutURL = function () {
        return this.getGlobalURL() + "/logout";
    };
    
    this.access_DeniedURL = function () {
        return this.getGlobalURL() + "/Access_Denied";
    };
    
    //************************companyMarketingFactory*****************
    
    this.setMarketingProgramURL = function () {
        return this.getGlobalURL() + "/setMarketingProgram";
    };
    
    this.listAllMarketingProgramURL = function () {
        return this.getGlobalURL() + "/listAllMarketingProgram";
    };
    
    this.alluserMarketingProgramURL = function () {
        return this.getGlobalURL() + "/alluserMarketingProgramForDisplay";
    };
    
    this.getAllUserMarketingProgramsURL = function () {
        return this.getGlobalURL() + "/getAllUserMarketingPrograms";
    };
    
    this.getAllUserMarketingProgramsUserIdURL = function () {
        return this.getGlobalURL() + "/getAllUserMarketingProgramsByUserId";
    };
    
    this.getAllUserMarketingProgramsSessionIdURL = function () {
        return this.getGlobalURL() + "/getAllUserMarketingProgramsBySessionUserId";
    };
    
    this.updateUserProgramURL = function () {
        return this.getGlobalURL() + "/updateUserProgram";
    };
    
    this.approveStatusRecurringURL = function () {
        return this.getGlobalURL() + "/approveStatusRecurring";
    };
    
    this.approveStatusURL = function () {
        return this.getGlobalURL() + "/approveStatus";
    };
    
    this.endMarketingProgramURL = function () {
        return this.getGlobalURL() + "/endMarketingProgram";
    };
    
    //******************emailDraftFactory*****************
    
    
    this.saveEmailDraftsURL = function () {
        return this.getGlobalURL() + "/saveEmailDrafts";
    };
    
    this.updateEmailDraftURL = function () {
        return this.getGlobalURL() + "/updateEmailDraft";
    };
    
    this.displayAllEmailDraftsURL = function () {
        return this.getGlobalURL() + "/displayAllEmailDrafts";
    };
    
    this.getEmailDraftURL = function () {
        return this.getGlobalURL() + "/getEmailDraft";
    };
    
    this.deleteEmailDraftsURL = function () {
        return this.getGlobalURL() + "/deleteEmailDrafts";
    };
    
    this.deleteEmailDraftURL = function () {
        return this.getGlobalURL() + "/deleteEmailDraft";
    };

    //************** marketingProgramNameFactory ********************/  
    
    this.marketingProgramNameURL = function () {
        return this.getGlobalURL() + "/getMarketingProgramName";
    };
    
    //************** marketingRecurringEmailControllerFactory ********************/  
    
    this.allRecurringEmailTemplatesURL = function () {
        return this.getGlobalURL() + "/getAllRecurringEmailTemplates";
    };
    
    this.recurringEmailTemplateURL = function () {
        return this.getGlobalURL() + "/getRecurringEmailTemplate";
    };
    
    this.setRecurringEmailTemplateURL = function () {
        return this.getGlobalURL() + "/setRecurringEmailTemplate";
    };
    
    this.deleteRecurringEmailTemplateURL = function () {
        return this.getGlobalURL() + "/deleteRecurringEmailTemplate";
    };
    
    this.updateRecurringEmailTemplateURL = function () {
        return this.getGlobalURL() + "/updateRecurringEmailTemplate";
    };
    
    this.setEmailTemplateRecurringActionURL = function () {
        return this.getGlobalURL() + "/setEmailTemplateToRecurringAction";
    };
    
    this.addRecurringActionURL = function () {
        return this.getGlobalURL() + "/addRecurringAction";
    };
    
    this.addupdateRecurringActionURL = function () {
        return this.getGlobalURL() + "/addupdateRecurringAction";
    };
    
    this.updateRecurringActionURL = function () {
        return this.getGlobalURL() + "/updateRecurringAction";
    };
    
    this.getUserPreferencesURL = function () {
        return this.getGlobalURL() + "/getUserPreferences";
    };
    
    this.getRecurringEntityURL = function () {
        return this.getGlobalURL() + "/getRecurringEntity";
    };
});



