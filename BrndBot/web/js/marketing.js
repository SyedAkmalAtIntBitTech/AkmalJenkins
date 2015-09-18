/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sliderDialog = "";
var prevSliderDialog = "";
$(document).ready(function ()
{
    $("#liPriority").click(function () {
        sliderDialog = "#dvPriorityDialog";
        $('#slider-button').click();
        prevSliderDialog = "#dvPriorityDialog";
    });

    $("#liFasting").click(function () {
        sliderDialog = "#dvFastingDialog";
        $('#slider-button').click();
        prevSliderDialog = "#dvFastingDialog";
    });

//    $("#entitydetails").click(function () {
//        
//        sliderDialog = "#previewfb";
//        $('#slider-button').click();
//        prevSliderDialog = "#previewfb";
//    });

    $('#slider-button').click(function () {
        //To hide the dialog if user click on another node
        if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
            if ($('#slider-button').css("margin-right") == "600px")
            {
                $(prevSliderDialog).animate({"margin-right": '-=600'});
                $('#slider-button').animate({"margin-right": '-=600'});
            }
        }

        if ($('#slider-button').css("margin-right") == "600px")
        {
            $(sliderDialog).animate({"margin-right": '-=600'});
            $('#slider-button').animate({"margin-right": '-=600'});
        }
        else
        {
            $(sliderDialog).animate({"margin-right": '+=600'});
            $('#slider-button').animate({"margin-right": '+=600'});
        }
    });

});


function validateEmail(sEmail) {
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(sEmail)) {
        return true;
    }
    else {
        return false;
    }
}

function validateaction() {
    var title = $("#title").val();
    var actiontype = $("#actiontype").val();
    var description = $("#description").val();
    var actiondate = $("#actiondate").val();
    var status = $("#status").val();

    if (title === "") {
        alert("title not entered, please enter the title");
        $("#title").focus();
        return false;
    }

    if (actiontype === "") {
        alert("actiontype not entered, please enter the actiontype");
        $("#actiontype").focus();
        return false;
    }
    if (description === "") {
        alert("description not entered, please enter the description");
        $("#description").focus();
        return false;
    }
    if (actiondate === "") {
        alert("actiondate not entered, please enter the actiondate");
        $("#actiondate").focus();
        return false;
    }
    if (status === "") {
        alert("status not entered, please enter the status");
        $("#status").focus();
        return false;
    }

    return true;
}


Date.prototype.customFormat = function (formatString) {
    var YYYY, YY, MMMM, MMM, MM, M, DDDD, DDD, DD, D, hhhh, hhh, hh, h, mm, m, ss, s, ampm, AMPM, dMod, th;
    YY = ((YYYY = this.getFullYear()) + "").slice(-2);
    MM = (M = this.getMonth() + 1) < 10 ? ('0' + M) : M;
    MMM = (MMMM = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"][M - 1]).substring(0, 3);
    DD = (D = this.getDate()) < 10 ? ('0' + D) : D;
    DDD = (DDDD = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"][this.getDay()]).substring(0, 3);
    th = (D >= 10 && D <= 20) ? 'th' : ((dMod = D % 10) == 1) ? 'st' : (dMod == 2) ? 'nd' : (dMod == 3) ? 'rd' : 'th';
    formatString = formatString.replace("#YYYY#", YYYY).replace("#YY#", YY).replace("#MMMM#", MMMM).replace("#MMM#", MMM).replace("#MM#", MM).replace("#M#", M).replace("#DDDD#", DDDD).replace("#DDD#", DDD).replace("#DD#", DD).replace("#D#", D).replace("#th#", th);
    h = (hhh = this.getHours());
    if (h == 0)
        h = 24;
    if (h > 12)
        h -= 12;
    hh = h < 10 ? ('0' + h) : h;
    hhhh = h < 10 ? ('0' + hhh) : hhh;
    AMPM = (ampm = hhh < 12 ? 'am' : 'pm').toUpperCase();
    mm = (m = this.getMinutes()) < 10 ? ('0' + m) : m;
    ss = (s = this.getSeconds()) < 10 ? ('0' + s) : s;
    return formatString.replace("#hhhh#", hhhh).replace("#hhh#", hhh).replace("#hh#", hh).replace("#h#", h).replace("#mm#", mm).replace("#m#", m).replace("#ss#", ss).replace("#s#", s).replace("#ampm#", ampm).replace("#AMPM#", AMPM);
};

function addDays(theDate, days) {
    return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
}
var user_selected_date = '';
function setCurrentDate(selected_date) {
    user_selected_date = selected_date;
}

function controllerMarketingCampaign($scope, $http) {
    $scope.entities_selected_time = "";
    $scope.getSelectedCampaigns = function () {

        var curr_date = moment(user_selected_date.date).format('YYYY-MM-DD');
        var tomorrowDate = moment(addDays(user_selected_date.date, 1)).format('YYYY-MM-DD');
        var new_date = moment(addDays(user_selected_date.date, 15)).format('YYYY-MM-DD');
        $http({
            method: 'GET',
            url: getHost() + 'GetScheduledEntities?from=' + curr_date + '&to=' + new_date
        }).success(function (data) {
            var entitySetSelected = {};
            $("#default").hide();
            $("#selected").show();
            $.each(data, function (key, value) {
                /*
                 * the below code is trying to create a model in the below form:
                 * {
                 *   'Today' : [{}, {}],
                 *   'Tomorrow': [{}, {}],
                 *   'Later': [{}, {}]
                 * }
                 */
                if (key == curr_date) {
                    entitySetSelected['Today'] = value;
                } else if (key == tomorrowDate) {
                    entitySetSelected['Tomorrow'] = value;
                } else {
                    if (!('Later' in entitySetSelected)) {
                        entitySetSelected['Later'] = [];
                    }
                    $.each(value, function (key2, value2) {
                        entitySetSelected['Later'].push(value2);
                    });
                }
            });
            $scope.entitySetSelected = entitySetSelected;
            $scope.nodata = "No data";
            //console.log($scope.entitySet);
        }).error(function (data) {
            alert("request not successful");
        });
    };

    $scope.getCampaigns = function () {
        var curr_date = moment(new Date()).format('YYYY-MM-DD');
        var tomorrowDate = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
        var new_date = moment(addDays(new Date(), 15)).format('YYYY-MM-DD');
        $http({
            method: 'GET',
            url: getHost() + 'GetScheduledEntities?from=' + curr_date + '&to=' + new_date
        }).success(function (data) {
            entitySet = {};
            $.each(data, function (key, value) {
                /*
                 * the below code is trying to create a model in the below form:
                 * {
                 *   'Today' : [{}, {}],
                 *   'Tomorrow': [{}, {}],
                 *   'Later': [{}, {}]
                 * }
                 */
                if (key == curr_date) {
                    entitySet['Today'] = value;
                } else if (key == tomorrowDate) {
                    entitySet['Tomorrow'] = value;

                } else {
                    if (!('Later' in entitySet)) {
                        entitySet['Later'] = [];
                    }
                    $.each(value, function (key2, value2) {
                        entitySet['Later'].push(value2);
                    });
                }
            });
            $scope.entitySet = entitySet;
            console.log(entitySet);
        }).error(function (data) {
            alert("request not successful");
        });
    };
    var millisToUTCDate = function (millis) {
        return toUTCDate(new Date(millis));
    };


    $scope.showScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title) {

        if(entity_type == "email"){
            sliderDialog = "#emailedit";
            $('#slider-button').click();
            prevSliderDialog = "#emailedit";
        }else if (entity_type == "facebook"){
            sliderDialog = "#previewfb";
            $('#slider-button').click();
            prevSliderDialog = "#previewfb";
        }else if (entity_type == "twitter"){
            sliderDialog = "#previewtwitter";
            $('#slider-button').click();
            prevSliderDialog = "#previewtwitter";
        }

        $http({
            method: 'GET',
            url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
        }).success(function (data) {
            $scope.entitiesdetails = data;
            var date = new Date(schedule_time);
            $(".content").empty();
            $(".content").append(data.body);
            $scope.entities_selected_time = schedule_time;
            $scope.schedule_title = schedule_title;
            $scope.showEmailList();
        }).error(function (data) {
            alert("request not successful");
        });
    };
    
    $scope.getScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title) {

        if(entity_type == "email"){
            sliderDialog = "#preview";
            $('#slider-button').click();
            prevSliderDialog = "#preview";
        }else if (entity_type == "facebook"){
            sliderDialog = "#previewfb";
            $('#slider-button').click();
            prevSliderDialog = "#previewfb";
        }else if (entity_type == "twitter"){
            sliderDialog = "#previewtwitter";
            $('#slider-button').click();
            prevSliderDialog = "#previewtwitter";
        }

        $http({
            method: 'GET',
            url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
        }).success(function (data) {
            $scope.entitiesdetails = data;
            var date = new Date(schedule_time);
            $(".content").empty();
            $(".content").append(data.body);
            $(".content").css("-webkit-transform"," scale(0.60)").css("left", "-30px").css("top", "-50px");
            $scope.entities_selected_time = schedule_time;
            $scope.schedule_title = schedule_title;
        }).error(function (data) {
            alert("request not successful");
        });
    };


    $scope.AddAction = function () {
        var title = $("#title").val();
        var actiontype = $("#actiontype").val();
        
        var description = $("#description").val();
        var actiondate = $("#actiondate").val();
        console.log("Value selected from Component: " + actiondate);
        var schedule_time = Date.parse(actiondate);
        console.log("Epoch: " + schedule_time);

        var dateObj = new Date(schedule_time);
        console.log(dateObj.getTimezoneOffset());

        var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;

        var newEpoch = schedule_time + tzOffsetInMillis;
        console.log("New Epoch: " + newEpoch);

        if (validateaction()) {
            var action = {"title": title, "type": actiontype, 
                          "description": description, "action_date": newEpoch 
                         };
            $http({
                method: 'POST',
                url: getHost() + 'AddAction',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(action)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != ""){
                    alert("action saved successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");

                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });

        }
    };
    $scope.showEmailList = function () {

        var emailids = {"update": "allEmailListNames"};
        $http({
            method: 'GET',
            url: getHost() + 'GetEmailLists?update=allEmailListNames'
        }).success(function (data, status, headers, config) {
            $scope.emailLists = data.allEmailListNames;
            if (data === "true") {
            } else if (data === error) {
                alert(data);
            }
        });
    };

};
