
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
        var hours = timeArray[0].replace(/ /g, '');
        var minWithAmPM = timeArray[1].split(" ");
        var mins = minWithAmPM[0].replace(/ /g, '');
        if (minWithAmPM.indexOf("PM") >= 0) {
            if (parseInt(hours) !== 12) {
                hours = parseInt(hours) + 12;
            }
            if (parseInt(hours) >= 24) {
                hours = 00;
            }
        }
        if (minWithAmPM.indexOf("AM") >= 0) {
            if (parseInt(hours) === 12) {
                hours = parseInt(hours) + 12;
            }
            if (parseInt(hours) >= 24) {
                hours = 00;
            }
        }
        var timeFormate = hours + ":" + mins + ":" + "00";
        date = moment(date,"MMM DD YYYY").format(kGlobalDateFormat);
        var dateArray = date.split("-");
        var actiondate = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
        var epoch = new Date(actiondate + " " + timeFormate);
        deffered.resolve(epoch.getTime());
        return deffered.promise;
    };
    UtilFactoryObject.AddDateTimePickerOnId = function (dateId, timeId) {
        var deffered = $q.defer();
        var picker = new Pikaday(
                {
                    field: document.getElementById(dateId),
                    firstDay: 1,
                    format: KGlobalDatePickerFormate,
                    minDate: new Date(2000, 0, 1),
                    maxDate: new Date(2050, 12, 31),
                    yearRange: [2000, 2050]
                });
        $('#' + timeId).timepicki({
            show_meridian: true,
            min_hour_value: 1,
            max_hour_value: 12,
            step_size_minutes: 01,
            overflow_minutes: true,
            increase_direction: 'up',
            disable_keyboard_mobile: true
        });
        deffered.resolve(picker);
        return deffered.promise;
    };

    return UtilFactoryObject;
});
