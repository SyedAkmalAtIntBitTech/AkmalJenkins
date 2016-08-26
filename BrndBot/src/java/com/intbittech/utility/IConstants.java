/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

/**
 *
 * @author AR
 */
public interface IConstants {
    
    //This is for user preferences.
    final static String kEmailListMindbodyKey = "mindbody";
    final static String kEmailListUserKey = "user";
    final static String kUserPreferencesTableKey = "company_preferences";
    final static String kUserPreferencesMindbodyKey = "mindbody_email_list";
    final static String kEmailAddressUserPreferenceKey = "emailLists";
    final static String kEmailListNameKey = "emailListName";
    final static String kEmailLastNameKey = "emailLastName";
    final static String kEmailFirstNameKey = "emailFirstName";
    final static String kEmailClientName = "emailClientName";
    final static String kEmailClientFirstName = "&lt;clientFirstName&gt;";
    final static String kEmailClientLastName = "&lt;clientLastName&gt;";
    final static String kEmailClientFullName = "&lt;clientFullName&gt;";
    final static String kEmailListDescription = "listDescription";
    final static String kEmailUIDKey = "emailUID";
    final static String kEmailListDefaultFromName = "defaultFromName";
    final static String kEmailListID = "emailListID";
    final static String kEmailListListDescription = "listDescription";
    final static String kEmailListAddedDate = "emailListAddedDate";
    final static String kEmailAddressesKey = "emailAddresses";
    final static String kEmailMindbodyEmailAddresses = "mindbody_emailAddresses";
    final static String kEmailAddressKey = "emailAddress";
    final static String kEmailAddressAddedKey = "addedDate";
    final static String kEmailKey = "Email";
    final static String kFacebookKey = "Facebook";
    final static String kTwitterKey = "Twitter";
    final static String kEmailSettings = "emailSettings";
    final static String kEmailFromAddress = "from_address";
    final static String kEmailReplyAddress = "reply_email_address";
    final static String kFromName = "from_name";
    final static String kMarketingActionsKey = "actions";
    final static String kColors = "colors";
    final static String kMarketingProgramTemplateTillDate = "tillDate";
    final static String kMarketingProgramTemplateDays = "days";
    final static String kMarketingProgramTemplateDescription = "description";
    final static String kMarketingProgramTemplateTime = "time";
    final static String kMarketingProgramTemplateIsRecurring = "isRecurring";
    final static String kMarketingProgramTemplateTitle = "title";
    final static String kMarketingProgramTemplateEntityType = "type";
    final static String kFacebookDescriptionKey = "description";
    final static String kFacebookPostTextKey = "post_text";
    final static String kFacebookUrlKey = "url";
    final static String kFacebookLinkTitleKey = "title";
    final static String kFacebookManagedPageKey = "ManagedPage";
    final static String kSocialPostCommpleteStatus = "complete";
    final static String kSocialPostTemplateSavedStatus = "template_saved";
    final static String kSocialPostapprovedStatus = "approved";
    final static String kUserMarketingProgramOpenStatus = "open";
    final static String kTwitterTextKey = "text";
    final static String kTwitterURLKey = "shorturl";
    final static String kFooters = "userProfile";
    final static String kUnsubscribeEmails = "unsubscribeEMails";
    
    // for external source 
    final static Integer EXTERNAL_SOURCE_NON_MINDBODY = 0;
    final static Integer EXTERNAL_SOURCE_MINDBODY = 1;
    final static int HTTPSuccessCode = 205;
}
