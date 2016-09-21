//************************ @author Tasmiya P.S @ Intbit *************************
factoryApp.factory('franchiseFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var franchiseFactoryObject = {};
    franchiseFactoryObject.getAllFranchises = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllFranchisesURL();
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.getFranchisesForCompanyId = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.getFranchisesForCompanyIdURL()+"?companyId="+companyId;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.getCompaniesForFranchiseId = function (franchiseId, emailTag) {
        var deffered = $q.defer();
        var url = configurationService.getCompaniesForFranchiseIdURL()+"?franchiseId="+franchiseId +"&emailTag="+emailTag;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.getAllPushedEmailsForFranchise = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.getAllPushedEmailsForFranchiseURL()+"?franchiseId="+franchiseId;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    franchiseFactoryObject.getFranchiseHeadquarter = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.getFranchiseHeadquarterURL()+"?franchiseId="+franchiseId;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.getAllNonSelectedCompanies = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.getAllNonSelectedCompaniesURL()+"?franchiseId="+franchiseId;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    /*
     * This is to activate a company as the head quarter.
     */
    franchiseFactoryObject.activateCompanyAsFranchise = function (companyId, franchiseId) {

        var deffered = $q.defer();
        var url = configurationService.activateCompanyAsFranchiseURL()+"?franchiseId="+franchiseId+"&companyId="+companyId;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    /*
     * This is to link a company as the Franchise.
     */
    franchiseFactoryObject.associateCompanyToFranchise = function (companyId, franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.associateCompanyToFranchiseURL()+"?franchiseId="+franchiseId+"&companyId="+companyId;
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.removeCompanyFromFranchise = function (companyId, franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.removeCompanyFromFranchiseURL()+"?franchiseId="+franchiseId+"&companyId="+companyId;
        authenticatedServiceFactory.makeCall("DELETE", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.saveFranchise = function (franchiseName) {
        var deffered = $q.defer();
        var url = configurationService.saveFranchiseURL();
        authenticatedServiceFactory.makeCall("POST", url, franchiseName, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.updateFranchise = function (franchiseId,franchiseName) {
        var deffered = $q.defer();
        var url = configurationService.updateFranchiseURL()+"?franchiseId="+franchiseId;
        authenticatedServiceFactory.makeCall("POST", url, franchiseName, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.deleteFranchise = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.deleteFranchiseURL()+"?franchiseId="+franchiseId;
        var data = '';
        authenticatedServiceFactory.makeCall("DELETE", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return franchiseFactoryObject;
});
