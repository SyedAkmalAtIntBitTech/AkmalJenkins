
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('utilFactory', function ($q) {
    var UtilFactoryObject = {};
    UtilFactoryObject.checkForNull = function (variable) {
        var deffered = $q.defer();
        var data = false;
        if (!variable)
            data = true;
        deffered.resolve(data);
        return deffered.promise;
    };
    UtilFactoryObject.getEpoch = function (date, time) {
        var deffered = $q.defer();
        var timeArray = time.split(":");
        var hours = timeArray[0];
        var mins = timeArray[1].replace(" AM", "").replace(" PM", "");
        if (timeArray.indexOf("PM") >= 0) {
            if (parseInt(hours) !== 12) {
                hours = parseInt(hours) + 12;
            }
            if (hours >= 24) {
                hours = 00;
            }
        }
        if (timeArray.indexOf("AM") >= 0) {
            if (parseInt(hours) === 12) {
                hours = parseInt(hours) + 12;
            }
            if (hours >= 24) {
                hours = 00;
            }
        }
        var timeFormate = hours + ":" + mins + ":" + "00";
        var dateArray = date.split("-");
        var actiondate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
        var epoch = new Date(actiondate + " " + timeFormate);
        deffered.resolve(epoch.getTime());
        return deffered.promise;
    };

    return UtilFactoryObject;
});
