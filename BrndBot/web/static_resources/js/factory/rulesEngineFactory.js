//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('rulesEngineFactory', ['$q','appSessionFactory', function ($q, appSessionFactory) {
    var RulesEngineFactoryObject = {};
    
    //Roles Constants go here
    var creatorRoleConstant = "Creator";
    var managerRoleConstant = "Manager";
    
    RulesEngineFactoryObject.isActionEligibleForUser = function() {
        var deffered = $q.defer();
        var data = true;
        appSessionFactory.getCompany().then(function (kGlobalCompanyObject){
            alert(kGlobalCompanyObject.roleName);
            if(kGlobalCompanyObject.roleName==creatorRoleConstant)
                data = false;
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return RulesEngineFactoryObject;
}]);


