
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('socialPostFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var socialPostFactoryObject = {};
    socialPostFactoryObject.facebookPost = function () {
        var deffered = $q.defer();
        var url = configurationService.postToFacebookURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    socialPostFactoryObject.twitterPost = function () {
        var deffered = $q.defer();
        var url = configurationService.postToTwitterURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    socialPostFactoryObject.shortenUrl = function (data1) {
        var deffered = $q.defer();
//        var url = configurationService.getShortenUrl();
//        alert(url);
//        alert(data);
        var username = "sandeep264328";
        var key = "R_63e2f83120b743bc9d9534b841d41be6";
       var url= "http://api.bit.ly/v3/shorten";
         var data= {longUrl: "https://www.w3.org/", apiKey: key, login: username};
                authenticatedServiceFactory.makeCall("GET", url, JSON.stringify(data), "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return socialPostFactoryObject;
});
