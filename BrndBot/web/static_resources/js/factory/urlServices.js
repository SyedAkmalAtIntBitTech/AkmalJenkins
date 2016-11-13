/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//This is to get the parameters from URL

//var currentEnvironment = "local";
////var currentEnvironment = "dev";
//
//var environmentLocal = "local";
//var environmentDev = "dev";
//
//var globalurl = "http://development2.brndbot.com/BrndBot/user";
//var localurl = "http://localhost:8080/BrndBot";

factoryApp.service('configurationService', function () {

//    this.getGlobalURL = function () {
//        if (currentEnvironment === environmentLocal)
//            return localurl;
//        if (currentEnvironment === environmentDev)
//            return globalurl;
//    }; 

    //**************** assetsFactory **********************//

    this.downloadImageURL = function () {
        return getHost() + "downloadImage";
    };

    this.imageListURL = function () {
        return getHost() + "getImageList";
    };

    this.globalColorsURL = function () {
        return getHost() + "getColorThemeById";
    };

    this.globalImageURL = function () {
        return getHost() + "getGlobalImageById";
    };

    this.globalFontsURL = function () {
        return getHost() + "getFontById";
    };

    this.allColorThemesURL = function () {
        return getHost() + "getAllColorThemes";
    };

    this.allGlobalImageURL = function () {
        return getHost() + "getAllGlobalImages";
    };

    this.allFontsURL = function () {
        return getHost() + "getAllFonts";
    };
    this.saveFontURL = function () {
        return getHost() + "saveFont";
    };

    this.colorThemeURL = function () {
        return getHost() + "saveColorTheme";
    };

    this.saveGlobalImageURL = function () {
        return getHost() + "saveGlobalImage";
    };

    this.updateGlobalImageURL = function () {
        return getHost() + "updateGlobalImage";
    };

    this.updateFontURL = function () {
        return getHost() + "updateFont";
    };

    this.updateColorThemeURL = function () {
        return getHost() + "updateColorTheme";
    };

    this.deleteFontURL = function () {
        return getHost() + "deleteFont";
    };

    this.deleteGlobalImageURL = function () {
        return getHost() + "deleteGlobalImage";
    };

    this.imageUniquenessURL = function () {
        return getHost() + "checkGlobalImageUniqueness";
    };

    this.deleteColorThemeURL = function () {
        return getHost() + "deleteColorTheme";
    };


    //**************** blockModelFactory **********************//

    this.emailBlockModelLookupURL = function () {
        return getHost() + "getAllEmailBlockModelById";
    };

    this.allEmailBlockModelURL = function () {
        return getHost() + "getAllEmailBlockModelsByBlockId";
    };

    this.getAllRecurringStyleByBlockIdURL = function () {
        return getHost() + "getAllRecuringEmailBlockModelsByBlockId";
    };
    this.emailBlockModelURL = function () {
        return getHost() + "getAllEmailBlockModel";
    };

    this.emailBlockModelIdURL = function () {
        return getHost() + "getEmailBlockModelById";
    };

    this.nonAddedEmailBlockModelURL = function () {
        return getHost() + "getAllNonAddedEmailBlockModel";
    };

    this.saveBlockModelURL = function () {
        return getHost() + "saveBlockModel";
    };

    this.saveEmailBlockModelURL = function () {
        return getHost() + "saveEmailBlockModel";
    };

    this.updateBlockModelURL = function () {
        return getHost() + "updateBlockModel";
    };

    this.deleteBlockModelURL = function () {
        return getHost() + "deleteBlockModel";
    };

    this.deleteEmailBlockModelURL = function () {
        return getHost() + "deleteEmailBlockModel";
    };


    //**************** categoryFactory **********************//

    this.categoryURL = function () {
        return getHost() + "getCategoryByCategoryId";
    };

    this.channelURL = function () {
        return getHost() + "getAllOrganizationCategoryByOrganizationId";
    };

    this.saveCategoryURL = function () {
        return getHost() + "saveCategory";
    };

    this.updateCategoryURL = function () {
        return getHost() + "updateCategory";
    };

    this.deleteCategoryURL = function () {
        return getHost() + "deleteCategory";
    };

    this.allCompanyCategoriesURL = function () {
        return getHost() + "getAllCompanyCategories";
    };

    //**************** emailBlockFactory **********************//

    this.saveEmailBlockURL = function () {
        return getHost() + "saveEmailBlock";
    };

    this.deleteEmailBlockURL = function () {
        return getHost() + "deleteEmailBlock";
    };

    this.allEmailBlocksURL = function () {
        return getHost() + "getAllEmailBlocksByOrganizationId";
    };

    //**************** emailFactory **********************//    

    this.sendEmailPostURL = function () {
        return getHost() + "email/send";
    };

    this.sendEmailGetURL = function () {
        return getHost() + "email/tags";
    };
    
    this.tagsDetailsURL = function () {
        return getHost() + "email/tagsDetails";
    };
    
    this.emailHistoryStatsURL = function () {
        return getHost() + "email/emailHistoryStats";
    };
    
    this.recurringEmailHistoryStatsURL = function () {
        return getHost() + "email/recurringEmailHistoryStats";
    };

    this.previewServletURL = function () {
        return getHost() + "email/previewServlet";
    };

    //**************** externalContentFactory **********************//

    this.activatedGetURL = function () {
        return getHost() + "externalContent/isActivated";
    };

    this.activationLinkURL = function () {
        return getHost() + "externalContent/getActivationLink";
    };

    this.listDataURL = function () {
        return getHost() + "externalContent/getListData";
    };

    this.layoutEmailModelURL = function () {
        return getHost() + "externalContent/getLayoutEmailModelById";
    };

    //**************** modelFactory **********************//

    this.emailModelURL = function () {
        return getHost() + "getAllEmailModelBySubCategory";
    };

    this.emailModelsIdURL = function () {
        return getHost() + "getAllEmailModelsBySubCategoryId";
    };

    this.nonAddedEmailModelsURL = function () {
        return getHost() + "getAllNonAddedEmailModels";
    };

    this.nonAddedImageModelsURL = function () {
        return getHost() + "getAllNonAddedImageModels";
    };

    this.nonAddedPrintModelsURL = function () {
        return getHost() + "getAllNonAddedPrintModels";
    };

    this.printModelURL = function () {
        return getHost() + "getAllPrintModelBySubCategory";
    };

    this.imageModelSubCategoryURL = function () {
        return getHost() + "getAllImageModelBySubCategory";
    };

    this.emailModelURL = function () {
        return getHost() + "getAllEmailModel";
    };

    this.emailModelIdURL = function () {
        return getHost() + "getEmailModelById";
    };

    this.imageModelURL = function () {
        return getHost() + "getAllImageModel";
    };

    this.imageModelIdURL = function () {
        return getHost() + "getImageModelById";
    };

    this.allPrintModelURL = function () {
        return getHost() + "getAllPrintModel";
    };

    this.printModelIdURL = function () {
        return getHost() + "getPrintModelById";
    };

    this.saveEmailModelURL = function () {
        return getHost() + "saveEmailModel";
    };

    this.editEmailModelURL = function () {
        return getHost() + "editEmailModel";
    };

    this.deleteEmailModelURL = function () {
        return getHost() + "deleteEmailModel";
    };

    this.saveCategoryEmailModelURL = function () {
        return getHost() + "saveSubCategoryEmailModel";
    };

    this.deleteCategoryEmailModelURL = function () {
        return getHost() + "deleteSubCategoryEmailModel";
    };

    this.saveCategoryPrintModelURL = function () {
        return getHost() + "saveSubCategoryPrintModel";
    };

    this.deleteCategoryPrintModelURL = function () {
        return getHost() + "deleteSubCategoryPrintModel";
    };

    this.saveCategoryImageModelURL = function () {
        return getHost() + "saveSubCategoryImageModel";
    };

    this.deleteCategoryImageModelURL = function () {
        return getHost() + "deleteSubCategoryImageModel";
    };

    this.savePrintModelURL = function () {
        return getHost() + "savePrintModel";
    };

    this.editPrintModelURL = function () {
        return getHost() + "editPrintModel";
    };

    this.deletePrintModelURL = function () {
        return getHost() + "deletePrintModel";
    };

    this.saveImageModelURL = function () {
        return getHost() + "saveImageModel";
    };

    this.updateImageModelURL = function () {
        return getHost() + "updateImageModel";
    };

    this.deleteImageModelURL = function () {
        return getHost() + "deleteImageModel";
    };

    //**************** organizationFactory **********************//

    this.allOrganizationURL = function () {
        return getHost() + "getAllOrganizations";
    };

    this.organizationURL = function () {
        return getHost() + "onboarding/getAllOnlyOrganizations";
    };

    this.saveOrganizationURL = function () {
        return getHost() + "saveOrganization";
    };

    this.updateOrganizationURL = function () {
        return getHost() + "updateOrganization";
    };

    this.deleteOrganizationURL = function () {
        return getHost() + "deleteOrganization";
    };

    this.getOrganizationURL = function () {
        return getHost() + "getOrganizationById";
    };


    //**************** scheduleActionsFactory **********************//

    this.getActionsURL = function () {
        return getHost() + "actions/getActions";
    };

    this.scheduleEmailURL = function () {
        return getHost() + "actions/scheduleEmail";
    };

    this.scheduleEmailActionsURL = function () {
        return getHost() + "actions/scheduleEmailActions";
    };

    this.scheduleSocialPostActionsURL = function () {
        return getHost() + "actions/scheduleSocialPostActions";
    };

    this.scheduleSocialPostURL = function () {
        return getHost() + "actions/scheduleSocialPost";
    };

    //**************** signupFactory **********************//

    this.signupURL = function () {
        return getHost() + "signup";
    };

    this.forgotPasswordURL = function () {
        return getHost() + "signup/forgotPassword";
    };

    this.resetPasswordURL = function () {
        return getHost() + "signup/resetPassword";
    };


    //**************** socialPostFactory **********************//

    this.postToFacebookURL = function () {
        return getHost() + "socialPost/postToFacebook";
    };

    this.postToTwitterURL = function () {
        return getHost() + "socialPost/postToTwitter";
    };

    this.getShortenUrl = function () {
        return "http://api.bit.ly/v3/shorten";
    };

    //**************** subCategoryFactory **********************//

    this.saveSubCategoryURL = function () {
        return getHost() + "saveSubCategory";
    };

    this.subCategoryURL = function () {
        return getHost() + "getSubCategoryById";
    };

    this.deleteSubCategoryURL = function () {
        return getHost() + "deleteSubCategory";
    };

    this.allSubCategoriesURL = function () {
        return getHost() + "getAllSubCategoriesByCategoryId";
    };

    this.externalSourceURL = function () {
        return getHost() + "getAllExternalSourceKeywordLookups";
    };

    this.allExternalSourcesURL = function () {
        return getHost() + "onboarding/getAllExternalSources";
    };

    //**************** UploadImageFactory **********************//

    this.uploadByAdminURL = function () {
        return getHost() + "UploadByAdmin";
    };

    this.uploadByUserURL = function () {
        return getHost() + "UploadByUser";
    };

    //************** companyFactory ********************//

    this.currentCompanyURL = function () {
        return getHost() + "getCurrentCompany";
    };

    this.companyURL = function () {
        return getHost() + "getAllCompanies";
    };

    this.nonAddedGroupsURL = function () {
        return getHost() + "getNonAddedGroups";
    };

    this.companyDetailsURL = function () {
        return getHost() + "getCompanyDetailsById";
    };

    this.allBlocksForCompanyURL = function () {
        return getHost() + "getAllBlocksForCompany";
    };
    this.allNonMindbodyBlocksForCompanyURL = function () {
        return getHost() + "getAllNonMindBodyBlocksForCompany";
    };
    this.saveGroupURL = function () {
        return getHost() + "saveGroup";
    };

    this.deleteGroupURL = function () {
        return getHost() + "deleteGroup";
    };


    //************** imageFactory ********************//

    this.imageIdURL = function () {
        return getHost() + "images/get";
    };

    this.saveImageURL = function () {
        return getHost() + "images/save";
    };
    this.saveLogoURL = function () {
        return getHost() + "images/uploadLogo";
    };

    //************** emailListFactory ********************//

    this.emailListURL = function () {
        return getHost() + "emaillist/get";
    };

    this.emailListSaveURL = function () {
        return getHost() + "emaillist/save";
    };

    this.emailListTagsForFranchiseURL = function () {
        return getHost() + "emailListTag/getAllEmailListTagForFranchise";
    };

    this.addContactListURL = function () {
        return getHost() + "emaillist/addContactList";
    };

    this.deleteContactListURL = function () {
        return getHost() + "emaillist/deleteContactList";
    };
    this.getAllEmailListWithNoOfContactsForMindBodyURL = function () {
        return getHost() + "emaillist/getAllEmailListWithNoOfContactsForMindBody";
    };

    this.createEmailListURL = function () {
        return getHost() + "emaillist/createEmailList";
    };

    this.addEmailListContactURL = function () {
        return getHost() + "emaillist/addContact";
    };

    this.getContactsOfEmailListURL = function () {
        return getHost() + "emaillist/getContactsOfEmailList";
    };

    this.getAllEmailListNamesURL = function () {
        return getHost() + "emaillist/getAllEmailListNames";
    };

    this.editContactURL = function () {
        return getHost() + "emaillist/editContact";
    };

    this.deleteEmailListURL = function () {
        return getHost() + "emaillist/deleteEmailList";
    };

    this.emailListGetWithNoOfContactsForUserURL = function () {
        return getHost() + "emaillist/getAllEmailListWithNoOfContactsForUser";
    };

    //************** emailListTagsFactory ********************//
    
    this.getAllEmailListTagsURL = function (){
        return getHost() + "emailListTag/getAllEmailListTag";
    };
    
    this.getAllEmailListTagsForFranchiseURL = function(){
        return getHost() + "emailListTag/getAllEmailListTagForFranchise";
    };
    
    this.deleteEmailListTagsForFranchiseURL = function(){
        return getHost() + "emailListTag/deleteEmailListTagsForFranchise";
    };
    
    this.getAllEmailListsAndTagsForFranchiseURL = function(){
        return getHost() + "emailListTag/getAllEmailListsAndTagsForFranchise";
    };
    
    this.saveEmailListTagURL = function(){
        return getHost() + "emailListTag/saveEmailListTag";
    };
    
    this.saveOrUpdateTagAndEmailListURL = function(){
        return getHost() + "emailListTag/saveOrUpdateTagAndEmailList";
    };
    //************** pushedActionsFactory ********************//

    this.saveSchedulePushedActionsCompaniesURL = function () {
        return getHost() + "pushedActions/savePushedScheduledActionCompanies";
    };

    this.getAllPushedEmailsForFranchiseURL = function () {
        return getHost() + "pushedActions/getAllPushedActionForFranchise";
    };

    this.getAllAssociatedAccountForScheduledEntityURL = function () {
        return getHost() + "pushedActions/getAllAssociatedAccountForScheduledEntity";
    };
    this.sendReminderEmailToCreateEmailListURL = function () {
        return getHost() + "pushedActions/sendReminderEmailToCreateEmailList";
    };

    //************** marketingFactory ********************//

    this.marketingProgramsURL = function () {
        return getHost() + "getMarketingProgramsByCategoryId";
    };

    this.nonAddedMarketingURL = function () {
        return getHost() + "getAllNonAddedMarketingPrograms";
    };

    this.allMarketingCategoryURL = function () {
        return getHost() + "getAllMarketingCategoryByOrganizationId";
    };

    this.saveMarketingURL = function () {
        return getHost() + "saveMarketingCategory";
    };

    this.deleteMarketingCategoryURL = function () {
        return getHost() + "deleteMarketingCategory";
    };

    this.marketingCategoryIdURL = function () {
        return getHost() + "getByMarketingCategoryId";
    };

    this.allMarketingProgramsURL = function () {
        return getHost() + "getAllMarketingPrograms";
    };

    this.saveMarketingProgramURL = function () {
        return getHost() + "saveMarketingProgramActions";
    };

    this.updateMarketingProgramURL = function () {
        return getHost() + "updateMarketingProgramActions";
    };

    this.saveMarketingCategoryURL = function () {
        return getHost() + "saveMarketingCategoryProgram";
    };

    this.deleteMarketingCategoryProgramURL = function () {
        return getHost() + "deleteMarketingCategoryProgram";
    };

    this.marketingProgramActionsIdURL = function () {
        return getHost() + "getMarketingProgramActionsById";
    };

    this.companyMarketingCategoriesURL = function () {
        return getHost() + "getCompanyMarketingCategories";
    };

    //************** companyImagesFactory ********************/

    this.companyImagesURL = function () {
        return getHost() + "companyImages/get";
    };

    this.deleteCompanyImagesURL = function () {
        return getHost() + "companyImages/delete";
    };

    this.downloadURL = function () {
        return getHost() + "download/HTML";
    };

    //************** onBoardingFactory ********************/

    this.usersURL = function () {
        return getHost() + "onboarding/isUserUnique";
    };

    this.updateUserURL = function () {
        return getHost() + "signup/updateUser";
    };

    this.saveUserURL = function () {
        return getHost() + "onboarding/saveUser";
    };

    this.saveInvitedUserURL = function () {
        return getHost() + "onboarding/saveInvitedUser";
    };
    this.saveStudioIdURL = function () {
        return getHost() + "onboarding/saveStudioId";
    };

    this.completedActivationURL = function () {
        return getHost() + "onboarding/completedActivation";
    };

    this.saveCompanyURL = function () {
        return getHost() + "onboarding/saveCompany";
    };

    this.saveCompanyAddressURL = function () {
        return getHost() + "/settings/saveAddress";
    };

    this.colorsForLogoURL = function () {
        return getHost() + "onboarding/getColorsForLogo";
    };

    this.saveCompanyLogoURL = function () {
        return getHost() + "onboarding/saveCompanyLogo";
    };

    //************** recurringEmailFactory ********************/    

    this.saveRecurringEmailURL = function () {
        return getHost() + "saveRecurringEmail";
    };

    this.saveOrganizationRecurringEmailURL = function () {
        return getHost() + "saveOrganizationRecurringEmail";
    };

    this.deleteRecurringEmailURL = function () {
        return getHost() + "deleteRecurringEmail";
    };

    this.deleteOrganizationRecurringEmailURL = function () {
        return getHost() + "deleteOrganizationRecurringEmail";
    };

    this.updateRecurringEmailURL = function () {
        return getHost() + "updateRecurringEmail";
    };

    this.recurringEmailTemplateIdURL = function () {
        return getHost() + "getRecurringEmailTemplateById";
    };

    this.allRecurringOrganizationURL = function () {
        return getHost() + "getAllRecurringByOrganizationId";
    };

    this.allNonAddedRecurringEmailURL = function () {
        return getHost() + "getAllNonAddedRecurringEmail";
    };

    this.allRecurringEmailsURL = function () {
        return getHost() + "getAllRecurringEmails";
    };
    
    this.purchaseBehaviorJSONURL = function () {
        return getHost() + "getPurchaseBehaviorJSON";
    };

    //************** settingsFactory ********************/    

    this.getColorsURL = function () {
        return getHost() + "settings/getColors";
    };

    this.unSubscribeEmails = function () {
        return getHost() + "settings/saveUnsubscribeEmails";
    };

    this.inviteUserURL = function () {
        return getHost() + "settings/sendInvitation";
    };

    this.editUserRoleURL = function () {
        return getHost() + "settings/editUserRole";
    };
    this.removeUserURL = function () {
        return getHost() + "settings/removeUser";
    };

    this.resendUserInviteURL = function () {
        return getHost() + "settings/resendInvitation";
    };

    this.getInvitedUsersURL = function () {
        return getHost() + "settings/getInvitedUsers";
    };

    this.setColorsURL = function () {
        return getHost() + "settings/setColors";
    };

    this.saveEmailSettingsURL = function () {
        return getHost() + "settings/saveEmailSettings";
    };

    this.getEmailSettingsURL = function () {
        return getHost() + "settings/getEmailSettings";
    };

    this.getAllPreferencesURL = function () {
        return getHost() + "settings/getAllPreferences";
    };

    this.globalAndUserColorsURL = function () {
        return getHost() + "settings/getGlobalAndUserColors";
    };

    this.changeLogoURL = function () {
        return getHost() + "settings/changeLogo";
    };

    this.facebookURL = function () {
        return getHost() + "settings/facebookDetails";
    };

    this.twitterURL = function () {
        return getHost() + "settings/twitterDetails";
    };

    this.fbLoginURL = function () {
        return getHost() + "settings/fbAuthURL";
    };

    this.fbGetTokenURL = function () {
        return getHost() + "settings/fbGetToken";
    };

    this.twitterLoginURL = function () {
        return getHost() + "settings/twitterAuthURL";
    };

    this.twitterGetTokenURL = function () {
        return getHost() + "settings/twitterGetToken";
    };

    this.setFooterPostURL = function () {
        return getHost() + "settings/setFooter";
    };
    this.getUserProfileColor = function () {
        return getHost() + "settings/getUserProfileColor";
    };

    //************** userFactory ********************/    

    this.userWelcomePageURL = function () {
        return getHost() + "user";
    };

    this.getAllUserCompaniesURL = function () {
        return getHost() + "user/getAllUserCompanyDetails";
    };

    this.getUserCompanyDetailsURL = function () {
        return getHost() + "user/getUserCompanyDetails";
    };
    this.getLoggedInUserId = function () {
        return getHost() + "user/getLoggedInUserId";
    };

    this.getAccountStatusURL = function () {
        return getHost() + "user/checkUserCompanyActivation";
    };

    this.userJspPagesURL = function () {
        return getHost() + "user";
    };
    this.getUserSignUpStatusURL = function () {
        return getHost() + "user/getUserSignupStatus";
    };

    //************** yourPlanFactory ********************/    

    this.scheduledEntitiesURL = function () {
        return getHost() + "GetScheduledEntities";
    };

    this.changeScheduleURL = function () {
        return getHost() + "ChangeSchedule";
    };

    this.addActionCommentURL = function () {
        return getHost() + "comment/saveActionComment";
    };

    this.getActionCommentsURL = function () {
        return getHost() + "comment/getAllCommentByActionId";
    };

    this.removeActionCommentURL = function () {
        return getHost() + "comment/deleteActionComment";
    };
    this.addActionURL = function () {
        return getHost() + "AddAction";
    };
    this.allUsersInCompanyURL = function () {
        return getHost() + "getAllUsersOfCompany";
    };

    this.noOfUsersInCompanyURL = function () {
        return getHost() + "getNumberOfUsersInCompany";
    };

    this.changeAssignedToURL = function () {
        return getHost() + "actions/updateActionAssignedTo";
    };

    this.scheduledEmailURL = function () {
        return getHost() + "GetScheduledEmailDetail";
    };

    this.scheduledSocialPostURL = function () {
        return getHost() + "GetScheduledSocialPostDetail";
    };

    this.postToSocialURL = function () {
        return getHost() + "PostToSocial";
    };

    this.getSentEmailDetailsURL = function () {
        return getHost() + "getSentEmailDetails";
    };

    //*****************loginFactory********************

    this.signinURL = function () {
        return getHost() + "/login";
    };

    this.resetLoginPasswordURL = function () {
        return getHost() + "resetpassword";
    };

    this.changePasswordURL = function () {
        return getHost() + "changepassword";
    };

    this.logoutURL = function () {
        return getHost() + "logout";
    };

    this.access_DeniedURL = function () {
        return getHost() + "Access_Denied";
    };

    //************************companyMarketingFactory*****************

    this.setMarketingProgramURL = function () {
        return getHost() + "setMarketingProgram";
    };

    this.listAllMarketingProgramURL = function () {
        return getHost() + "listAllMarketingProgram";
    };

    this.alluserMarketingProgramURL = function () {
        return getHost() + "alluserMarketingProgramForDisplay";
    };

    this.getAllUserMarketingProgramsURL = function () {
        return getHost() + "getAllUserMarketingPrograms";
    };

    this.getAllUserMarketingProgramsUserIdURL = function () {
        return getHost() + "getAllUserMarketingProgramsByUserId";
    };

    this.getAllUserMarketingProgramsSessionIdURL = function () {
        return getHost() + "getAllUserMarketingProgramsBySessionUserId";
    };

    this.updateUserProgramURL = function () {
        return getHost() + "updateUserProgram";
    };

    this.approveStatusRecurringURL = function () {
        return getHost() + "approveStatusRecurring";
    };

    this.approveStatusURL = function () {
        return getHost() + "approveStatus";
    };

    this.endMarketingProgramURL = function () {
        return getHost() + "endMarketingProgram";
    };

    //******************emailDraftFactory*****************


    this.saveEmailDraftsURL = function () {
        return getHost() + "saveEmailDrafts";
    };

    this.updateEmailDraftURL = function () {
        return getHost() + "updateEmailDraft";
    };

    this.displayAllEmailDraftsURL = function () {
        return getHost() + "displayAllEmailDrafts";
    };

    this.getEmailDraftURL = function () {
        return getHost() + "getEmailDraft";
    };

    this.deleteEmailDraftsURL = function () {
        return getHost() + "deleteEmailDrafts";
    };

    this.deleteEmailDraftURL = function () {
        return getHost() + "deleteEmailDraft";
    };

    //************** marketingProgramNameFactory ********************/  

    this.marketingProgramNameURL = function () {
        return getHost() + "getMarketingProgramName";
    };

    //************** marketingRecurringEmailControllerFactory ********************/  

    this.allRecurringEmailTemplatesURL = function () {
        return getHost() + "getAllRecurringEmailTemplates";
    };

    this.recurringEmailTemplateURL = function () {
        return getHost() + "getRecurringEmailTemplate";
    };

    this.setRecurringEmailTemplateURL = function () {
        return getHost() + "setRecurringEmailTemplate";
    };

    this.deleteRecurringEmailTemplateURL = function () {
        return getHost() + "deleteRecurringEmailTemplate";
    };

    this.updateRecurringEmailTemplateURL = function () {
        return getHost() + "updateRecurringEmailTemplate";
    };

    this.setEmailTemplateRecurringActionURL = function () {
        return getHost() + "setEmailTemplateToRecurringAction";
    };

    this.addRecurringActionURL = function () {
        return getHost() + "addRecurringAction";
    };

    this.addupdateRecurringActionURL = function () {
        return getHost() + "addupdateRecurringAction";
    };

    this.updateRecurringActionURL = function () {
        return getHost() + "updateRecurringAction";
    };

    this.getUserPreferencesURL = function () {
        return getHost() + "getUserPreferences";
    };

    this.getRecurringEntityURL = function () {
        return getHost() + "getRecurringEntity";
    };

    //************** franchiseFactory ********************/  

    this.getAllFranchisesURL = function () {
        return getHost() + "getAllFranchises";
    };

    this.getFranchisesForCompanyIdURL = function () {
        return getHost() + "getFranchisesForCompanyId";
    };

    this.getCompaniesForFranchiseIdURL = function () {
        return getHost() + "getCompaniesForFranchiseId";
    };

    this.getCompaniesForFranchiseIdAndEmailListTagURL = function () {
        return getHost() + "getAllCompanyForFranchise";
    };

    this.getFranchiseHeadquarterURL = function () {
        return getHost() + "getFranchiseHeadquarter";
    };

    this.getAllNonSelectedCompaniesURL = function () {
        return getHost() + "getAllNonSelectedCompanies";
    };

    this.activateCompanyAsFranchiseURL = function () {
        return getHost() + "activateCompanyAsFranchise";
    };

    this.associateCompanyToFranchiseURL = function () {
        return getHost() + "associateCompanyToFranchise";
    };

    this.removeCompanyFromFranchiseURL = function () {
        return getHost() + "removeCompanyFromFranchise";
    };

    this.saveFranchiseURL = function () {
        return getHost() + "saveFranchise";
    };

    this.updateFranchiseURL = function () {
        return getHost() + "updateFranchise";
    };

    this.deleteFranchiseURL = function () {
        return getHost() + "deleteFranchise";
    };

    this.requestToAddCompaniesURL = function () {
        return getHost() + "requestToAddCompanies";
    };
    
    //**************** behaviorFactory **********************//    

    this.revenueCategoryGetURL = function () {
        return getHost() + "behavior/getRevenueCategory";
    };
    
    this.serviceCategoryGetURL = function () {
        return getHost() + "behavior/getServiceCategory";
    };
    
    this.pricingOptionGetURL = function () {
        return getHost() + "behavior/getPricingOption";
    };
    
    this.siteLocationsGetURL = function () {
        return getHost() + "behavior/getSiteLocations";
    };
});



