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
            if ($('#slider-button').css("margin-right") == "530px")
            {
                $(prevSliderDialog).animate({"margin-right": '-=600'});
                $('#slider-button').animate({"margin-right": '-=530'});
            }
        }

        if ($('#slider-button').css("margin-right") == "530px")
        {
            $(sliderDialog).animate({"margin-right": '-=600'});
            $('#slider-button').animate({"margin-right": '-=530'});
        }
        else
        {
            $(sliderDialog).animate({"margin-right": '+=600'});
            $('#slider-button').animate({"margin-right": '+=530'});
        }
    });
    
    $('#button_edit').click(function(){
        if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
            if ($('#slider-button').css("margin-right") == "530px")
            {
                $(prevSliderDialog).animate({"margin-right": '-=600'});
                $('#slider-button').animate({"margin-right": '-=530'});
            }
        }
        if ($('#slider-button').css("margin-right") == "530px")
        {
            $(sliderDialog).animate({"margin-right": '-=600'});
            $('#slider-button').animate({"margin-right": '-=530'});
        }
        else
        {
            $(sliderDialog).animate({"margin-right": '+=600'});
            $('#slider-button').animate({"margin-right": '+=530'});
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
var selected_schedules_to_delete = "";

function setSelectedIds(selectedid){
    
    var checked  = document.getElementById(selectedid).checked;

    if (checked){
        $("#delsel").show();
        var selected_schedule_id = $("#"+selectedid).val();
        selected_schedules_to_delete = selected_schedule_id + "," + selected_schedules_to_delete;
        console.log(selected_schedules_to_delete);
    }
    else{
        var selected_schedule_id = $("#"+selectedid).val();
        selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id+",","");
        console.log(selected_schedules_to_delete);
        if(selected_schedules_to_delete === ""){
           $("#delsel").hide();
        };
    }
    
    
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


    $scope.showScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc) {

        if(entity_type == "email"){
            sliderDialog = "#emailedit";
            $('#slider-button').click();
            prevSliderDialog = "#emailedit";
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $(".editcontent").empty();
                $(".editcontent").append(data.body);
                $(".editcontent").css("-webkit-transform","scale(0.5,0.6)").css("margin-left", "-140px").css("margin-top", "-60px").css("margin-bottom", "-220px");
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.showEmailList();
            }).error(function (data) {
                alert("request not successful");
            });
            
        }else if (entity_type == "facebook"){
            sliderDialog = "#editfacebook";
            $('#slider-button').click();
            prevSliderDialog = "#editfacebook";
            $('#edtfbimg').show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id='+ schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert("request not successful");
            });
            
        }else if (entity_type == "twitter"){
            sliderDialog = "#edittwitter";
            $('#slider-button').click();
            prevSliderDialog = "#edittwitter";
            $('#edttwtimg').show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id='+ schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert("request not successful");
            });
            
        }

    };
    
    $scope.getScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc) {

        if(entity_type == "email"){
            sliderDialog = "#preview";
            $('#slider-button').click();
            prevSliderDialog = "#preview";
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                var date = new Date(schedule_time);
                $(".content").empty();
            $(".content").append(data.body);
            $(".content").css("-webkit-transform"," scale(0.7,0.6)").css("left", "0px").css("top", "-20px");
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
            }).error(function (data) {
                alert("request not successful");
            });
        }else if (entity_type == "facebook"){
            sliderDialog = "#previewfb";
            $('#slider-button').click();
            prevSliderDialog = "#previewfb";
            $('#prevfbimg').show(); 
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id='+ schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert("request not successful");
            });

        }else if (entity_type == "twitter"){
            sliderDialog = "#previewtwitter";
            $('#slider-button').click();
            prevSliderDialog = "#previewtwitter";
            $('#prevtwtimg').show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id='+ schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                var date = new Date(schedule_time);
                $(".content").empty();
                $(".content").append(data.body);
                $(".content").css("-webkit-transform"," scale(0.60)").css("left", "-30px").css("top", "-50px");
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert("request not successful");
            });
        }else if (entity_type == "note"){
            sliderDialog = "#previewNote";
            $('#slider-button').click();
            prevSliderDialog = "#previewNote";
            $scope.entities_selected_time = schedule_time;
            $scope.schedule_title = schedule_title;
            console.log(schedule_desc);
            $scope.schedule_desc = schedule_desc;
            
        }


    };


    $scope.AddAction = function () {
        var title = $("#title").val();
        var actiontype = $("#actiontype").val();
        
        var description = $("#description").val();
        var actiondate = $("#actiondatetime").val();
        
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
    
    $scope.deleteSchedule = function(){
        var schedule_details = {"type": "deleteSelected", 
            "schedule_ids": selected_schedules_to_delete};

        $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: schedule_details
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== ""){
                    alert("schedule deleted successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });
    };

    $scope.deleteAction = function(schedule_id){
        
        var schedule_details = {"type": "delete", 
            "schedule_ids": schedule_id};

        $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: schedule_details
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== ""){
                    alert("schedule deleted successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });
    };    

    $scope.removeTemplate = function(schedule_id){
        
        var schedule_details = {"type": "removetemplate", 
            "schedule_ids": schedule_id };

        $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: schedule_details
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== ""){
                    alert("schedule deleted successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });
    };    
    
    $scope.updateEmailSchedule = function(){
            var schedule_id = $("#email_schedule_id").val();
            var entity_id = $("#email_entity_id").val();
            var schedule_title = $("#email_entitytitle").val();
            var email_entitysubject = $("#email_entitysubject").val();
            var email_entitytoaddress = $("#email_entitytoaddress").val();
            var email_entityfromaddress = $("#email_entityfromaddress").val();
            var email_entityreplytoaddress = $("#email_entityreplytoaddress").val();
            var chooseEmailList = $("#chooseEmailList").val();
            var schedule_datetime = $("#email_schedule_datetime").val();
            
            console.log("Value selected from Component: " + schedule_datetime);
            var schedule_time = Date.parse(schedule_datetime);
            console.log("Epoch: " + schedule_time);

            var dateObj = new Date(schedule_time);
            console.log(dateObj.getTimezoneOffset());

            var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;

            var newEpoch = schedule_time + tzOffsetInMillis;
            console.log("New Epoch: " + newEpoch);
            
            var schedule_details = {
                "type": "updateemail", 
                "schedule_id": schedule_id,
                "entity_id": entity_id,
                "schedule_title": schedule_title,
                "schedule_time": newEpoch,
                "email_subject": email_entitysubject, 
                "to_email_addresses": email_entitytoaddress, 
                "from_email_address": email_entityfromaddress, 
                "reply_to_email_address": email_entityreplytoaddress, 
                "email_list": chooseEmailList
            };
            $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(schedule_details)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != ""){
                    alert("details saved successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");

                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });            
            
    };

    $scope.updateSocialSchedule = function(){
        
        var social_type = $("#social_type").val();

        if (social_type == "facebook"){
            
            if ($scope.validate()){
            
            var schedule_id = $("#facebook_schedule_id").val();
            var entity_id = $("#facebook_entity_id").val();
            var schedule_title = $("#facebook_schedule_title").val();
            var schedule_Description = $("#facebook_schedule_Description").val();
            var facebook_schedule_posttext = $("#facebook_schedule_posttext").val();
            var facebook_schedule_url = $("#facebook_schedule_url").val();
            var facebook_schedule_description = $("#facebook_schedule_description").val();
            var schedule_datetime = $("#facebook_schedule_datetime").val();

            console.log("Value selected from Component: " + schedule_datetime);
            var schedule_time = Date.parse(schedule_datetime);
            console.log("Epoch: " + schedule_time);

            var dateObj = new Date(schedule_time);
            console.log(dateObj.getTimezoneOffset());

            var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;

            var newEpoch = schedule_time + tzOffsetInMillis;
            console.log("New Epoch: " + newEpoch);

            var schedule_details = {"type": "updatesocial", 
                "schedule_id": schedule_id,
                "entity_id": entity_id,
                "metadata": {
                            description: '"'+facebook_schedule_description+'"',
                            post_text: '"'+facebook_schedule_posttext+'"',
                            url: '"'+facebook_schedule_url+'"'
                          },
                "schedule_time": newEpoch,
                "schedule_title": schedule_title,
                "schedule_desc": schedule_Description        
            };
            $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(schedule_details)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != ""){
                    alert("details saved successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");

                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });            

            }
        }else if (social_type == "twitter"){

            if ($scope.validate()){
            var schedule_id = $("#twitter_schedule_id").val();
            var entity_id = $("#twitter_entity_id").val();
            var schedule_title = $("#twitter_schedule_title").val();
            var schedule_Description = $("#twitter_schedule_Description").val();
            var schedule_posttext = $("#twitter_schedule_post_text").val();
            var schedule_datetime = $("#twitter_schedule_datetime").val();

            console.log("Value selected from Component: " + schedule_datetime);
            var schedule_time = Date.parse(schedule_datetime);
            console.log("Epoch: " + schedule_time);

            var dateObj = new Date(schedule_time);
            console.log(dateObj.getTimezoneOffset());

            var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;

            var newEpoch = schedule_time + tzOffsetInMillis;
            console.log("New Epoch: " + newEpoch);

            var schedule_details = {"type": "updatesocial", 
                "schedule_id": schedule_id,
                "entity_id": entity_id,
                "metadata": {
                        text: '"'+schedule_posttext+'"'
                            },
                "schedule_time": newEpoch,
                "schedule_title": schedule_title,
                "schedule_desc": schedule_Description        
            };
            $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(schedule_details)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != ""){
                    alert("details saved successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");

                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });
                
            }
        }
    };
        $scope.validate = function(){
        
        var facebook_schedule_title = $("#facebook_schedule_title").val();
        var facebook_schedule_Description = $("#facebook_schedule_Description").val();
        var facebook_schedule_posttext = $("#facebook_schedule_posttext").val();
        var facebook_schedule_url = $("#facebook_schedule_url").val();
        var facebook_schedule_description = $("#facebook_schedule_description").val();
        var facebook_schedule_datetime = $("#facebook_schedule_datetime").val();
        
        if (facebook_schedule_title == ""){
            alert("schedule title not entered, please enter the value");
            $("#facebook_schedule_title").focus();
            return false;
        }
        if (facebook_schedule_Description == ""){
            alert("schedule description not entered, please enter the value");
            $("#facebook_schedule_Description").focus();
            return false;
        }
        if (facebook_schedule_posttext == ""){
            alert("schedule post text not entered, please enter the value");
            $("#facebook_schedule_posttext").focus();
            return false;
        }
        if (facebook_schedule_url == ""){
            alert("schedule url not entered, please enter the value");
            $("#facebook_schedule_url").focus();
            return false;
        }
        if (facebook_schedule_description == ""){
            alert("schedule description not entered, please enter the value");
            $("#facebook_schedule_description").focus();
            return false;
        }
        if (facebook_schedule_datetime == ""){
            alert("schedule date time not entered, please enter the value");
            $("#facebook_schedule_datetime").focus();
            return false;
        }
        return true;
        
    };
};
