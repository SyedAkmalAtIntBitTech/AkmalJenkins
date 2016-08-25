//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('rulesEngineFactory', function ($q) {
    var RulesEngineFactoryObject = {};
    RulesEngineFactoryObject.isActionEligibleForUser = function() {
        var deffered = $q.defer();
        var data = false;
        
        deffered.resolve(data);
        return deffered.promise;
    };
});


