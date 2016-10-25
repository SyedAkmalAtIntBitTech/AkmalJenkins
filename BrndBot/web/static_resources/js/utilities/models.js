/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
var kEmailFlowObject = {redirect: "",
    categoryId: "",
    subCategoryId: "",
    mindbody: "",
    lookupId: "",
    mindbodyId: "",
    draftId: "",
    emailSubject: "",
    preHeader: "",
    marketingCategoryId: "",
    marketingProgramId: "",
    htmlData: ""
};

var kGlobalEmailObject = {categoryId: "",
    subCategoryId: "",
    lookupId: "",
    mindbody: "",
    mindbodyId: "",
    draftId: "",
    emailSubject: "",
    entityScheduleId: "",
    emailScheduleId: "",
    toEmailAddresses: "",
    body: "",
    emailListName: "",
    fromName: "",
    replyToEmailAddress: "",
    fromAddress: "",
    preheader: "",
    htmlBody: "",
    pushedEmail: false,
    emailTagId: ""
};

var kGlobalCompanyObject = {
    userId: "",
    companyId: "",
    companyName: "",
    roleName: "",
    roleId: "",
    logourl: "",
    accountStatus: "",
    userEmailId: "",
    userFirstName: "",
    userLastName: "",
    userHashId: "",
    franchiseId: "",
    franchiseName: "",
    isHeadquarter: ""
};

var kGlobalFbPostDataObject = {imgNameToPost: "",
    accessToken: "",
    postText: "",
    title: "",
    url: "",
    description: "",
    imageType: ""
};
var kGlobalUserObject = {hasMultipleCompany: false};
var kGlobalPopupFlagsObject = {emailUnsubscribe: false};
var KGlobalAllUserUnderCompanyObject = {userList: []};
