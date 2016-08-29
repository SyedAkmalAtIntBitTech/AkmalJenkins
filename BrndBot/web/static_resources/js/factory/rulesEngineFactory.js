//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('rulesEngineFactory', ['$q', 'appSessionFactory', function ($q, appSessionFactory) {
        var RulesEngineFactoryObject = {};

        //Roles Constants go here
        var CONSTANT_CREATOR_ROLE = "Creator";
        var CONSTANT_OWNER_ROLE = "Owner";
        var CONSTANT_MANAGER_ROLE = "Manager";

        RulesEngineFactoryObject.isActionEligibleForUser = function () {
            var deffered = $q.defer();
            var data = true;
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                if (kGlobalCompanyObject.roleName === CONSTANT_CREATOR_ROLE)
                    data = false;
                deffered.resolve(data);
            });
            return deffered.promise;
        };
        return RulesEngineFactoryObject;
    }]);


