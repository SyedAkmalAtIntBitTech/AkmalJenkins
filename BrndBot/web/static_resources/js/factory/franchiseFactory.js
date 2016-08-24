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
    franchiseFactoryObject.getCompaniesForFranchiseId = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.getCompaniesForFranchiseIdURL()+"?franchiseId="+franchiseId;
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
        //TODO Send Franchise Details
//        var data = JSON.stringify({
//                        franchiseDetails: {
//                            franchiseName: franchiseName
//                        }
//                    });
//                    data = JSON.stringify(data);
        var url = configurationService.saveFranchiseURL();
        authenticatedServiceFactory.makeCall("POST", url, franchiseName, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.updateFranchise = function (franchiseId,franchiseName) {
        var deffered = $q.defer();
        var url = configurationService.updateFranchiseURL()+"?franchiseId="+franchiseId;
                //TODO Send Franchise Details
//        var data = '{franchiseDetails: {franchiseName:' + franchiseName + '}}';
//        data = JSON.stringify(data);
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
