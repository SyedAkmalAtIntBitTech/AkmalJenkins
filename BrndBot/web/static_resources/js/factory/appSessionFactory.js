//************************ @author Syed Ilyas @ Intbit *************************

var kSessionCategoryId = "categoryId";
var kSessionSubCategoryId = "subCategoryId";
var kSessionLookupId = "lookupId";
var kSessionExternalSourceName = "mindbody";
var kSessionMindbodyId = "mindbodyId";
var kSessionDraftId = "draftId";
var kSessionEmailSubject = "emailSubject";
var kSessionEntityScheduleId = "entityScheduleId";
var kSessionEmailScheduleId = "email_Schedule_Id";
var kSessionToEmailAddresses = "to_email_addresses";
var kSessionEmailBody = "body";
var kSessionEmailListName = "email_list_name";
var kSessionFromName = "from_name";
var kSessionReplyToEmailAddress = "reply_to_email_address";
var kSessionFromAddress = "from_address";
var kSessionPreHeader = "preHeader";
var kSessionEmailDraftData= "emailDraftData";
var kSessionHtmlBody="htmlBody";

function getHtmlBody(){
    return kSessionHtmlBody;
}

function getCategoryId(){
    return kSessionCategoryId;
}

function getSubCategoryId(){
    return kSessionSubCategoryId;
}

function getLookupId(){
    return kSessionLookupId;
}

function getExternalSourceName(){
    return kSessionExternalSourceName;
}

function getMindbodyId(){
    return kSessionMindbodyId;
}

function getDraftId(){
    return kSessionDraftId;
}

function getEmailSubject(){
    return kSessionEmailSubject;
}

function getEntityScheduleId() {
    return kSessionEntityScheduleId;
}

function getEmailScheduleId(){
    return kSessionEmailScheduleId;
}
function getPreHeader(){
    return kSessionPreHeader;
}
function getDraftDetails(){
    return kSessionEmailDraftData;
}
function getToEmailAddresses(){
    return kSessionToEmailAddresses;
}

function getEmailBody() {
    return kSessionEmailBody;
}

function getEmailListName() {
    return kSessionEmailListName;
}

function getFromName() {
    return kSessionFromName;
}

function getReplyToEmailAddress() {
    return kSessionReplyToEmailAddress;
}

function getFromAddress() {
    return kSessionFromAddress;
}


factoryApp.factory('appSessionFactory', function ($q) {
    var AppSessionFactoryObject = {};
    
    //namespace details
    var emailObjectName = "email";
    var email = { };
    AppSessionFactoryObject.setEmail = function (key, value) {
        var deffered = $q.defer();
        var data = false;
        if(key && value)
        {
//            var keyString = email+"."+key;
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
                email = emailLocalObject;
            }
            email[key] = value
            localStorage.setItem(emailObjectName,JSON.stringify(email));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.setEmailWithObject = function (object) {
        var deffered = $q.defer();
        var data = false;
        if(object)
        {
//            var keyString = email+"."+key;
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
                email = emailLocalObject;
            }
            email = object;
            localStorage.setItem(emailObjectName,JSON.stringify(email));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getEmail = function (key) {
        var deffered = $q.defer();
        var requestedValue = "";
        if(key)
        {
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
                var value = emailLocalObject[key];
                if(value)
                    requestedValue = value;
            }
        }
        deffered.resolve(requestedValue);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getAllEmail = function () {
        var deffered = $q.defer();
        var requestedValue;
        
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
               requestedValue = emailLocalObject;
            }
        
        deffered.resolve(requestedValue);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.clearAllEmail = function () {
        var deffered = $q.defer();
        var data = true;
        localStorage.removeItem(emailObjectName);
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.clearKeyEmail = function (key) {
        var deffered = $q.defer();
        var data = true;
        var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
        if(emailLocalObject){
            if(emailLocalObject[key])
                emailLocalObject[key] = "";
            localStorage.setItem(emailObjectName,JSON.stringify(emailLocalObject));
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    return AppSessionFactoryObject;
});