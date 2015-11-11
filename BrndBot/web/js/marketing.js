/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sliderDialog = "";
var prevSliderDialog = "";
var create_button_title = "Edit";

$(document).ready(function ()
{
    $("#liPriority").click(function () {
        //$slider=1;
        //sliderDialog = "#dvPriorityDialog";
        //$('#slider-button').click();
        //prevSliderDialog = "#dvPriorityDialog";
    });

    $("#liFasting").click(function () {
        sliderDialog = "#dvFastingDialog";
        $('#slider-button').click();
        prevSliderDialog = "#dvFastingDialog";
    });
//    $('#datetimepicker3').datetimepicker({
//        pickDate: false
//    });
//    $("#entitydetails").click(function () {
//        
//        sliderDialog = "#previewfb";
//        $('#slider-button').click();
//        prevSliderDialog = "#previewfb";
//    });
//    
//    function cancelform()
//    {
//        
////    $("#"+cancelbtn).click(function(){
//        if(confirm("Do you want to cancel the process?")){
//            $('#slider-button').click();
//            
//        }
////    })
//    }
$a=0;
$edit=0;
    $('#slider-button').click(function () {
        $a+=1;
         //To hide the dialog if user click on another node
        if($a>=2 && $edit==1)
        {   
            if (confirm("Do you want to close it .. !"))
            { 
                $edit=0;  
                if($slider==2)
                {
                    if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
                        if ($('#slider-button').css("margin-right") == "788px")
                        {
                            $(prevSliderDialog).animate({"margin-right": '-=850px'});
                            $('#slider-button').animate({"margin-right": '-=788px'});
                        }
                    }

                    if ($('#slider-button').css("margin-right") == "788px")
                    {
                        $slider=0;
                        $a=0;
                        $(sliderDialog).animate({"margin-right": '-=850px'});
                        $('#slider-button').animate({"margin-right": '-=788px'});
                        closeoverlay();
                    }
                    else
                    {
                        $(sliderDialog).animate({"margin-right": '+=850px'});
                        $('#slider-button').animate({"margin-right": '+=788px'});
                        overlay();
                    }  
                }
                if($slider==1)
                {
                    if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
                        if ($('#slider-button').css("margin-right") == "375px")
                        {
                            $(prevSliderDialog).animate({"margin-right": '-=424px'});
                            $('#slider-button').animate({"margin-right": '-=375px'});
                        }
                    }

                    if ($('#slider-button').css("margin-right") == "375px")
                    {
                        $slider=0;
                        $a=0;
                        $(sliderDialog).animate({"margin-right": '-=424px'});
                        $('#slider-button').animate({"margin-right": '-=375px'});
                        closeoverlay();
                    }
                    else
                    {
                        $(sliderDialog).animate({"margin-right": '+=424px'});
                        $('#slider-button').animate({"margin-right": '+=375px'});
                        overlay();
                    }  
                }
            }
            else
            {
            }
        }
        else
        {
            if($slider==2)
            {
                if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
                    if ($('#slider-button').css("margin-right") == "788px")
                    {
                        $(prevSliderDialog).animate({"margin-right": '-=850px'});
                        $('#slider-button').animate({"margin-right": '-=788px'});
                    }
                }

                if ($('#slider-button').css("margin-right") == "788px")
                {
                    $slider=0;
                    $a=0;
                    $(sliderDialog).animate({"margin-right": '-=850px'});
                    $('#slider-button').animate({"margin-right": '-=788px'});
                    closeoverlay();
                }
                else
                {
                    $(sliderDialog).animate({"margin-right": '+=850px'});
                    $('#slider-button').animate({"margin-right": '+=788px'});
                    overlay();
                }  
            }
            if($slider==1)
            {
                if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
                    if ($('#slider-button').css("margin-right") == "375px")
                    {
                        $(prevSliderDialog).animate({"margin-right": '-=424px'});
                        $('#slider-button').animate({"margin-right": '-=375px'});
                    }
                }

                if ($('#slider-button').css("margin-right") == "375px")
                {
                    $slider=0;
                    $a=0;
                    $(sliderDialog).animate({"margin-right": '-=424px'});
                    $('#slider-button').animate({"margin-right": '-=375px'});
                    closeoverlay();
                }
                else
                {
                    $(sliderDialog).animate({"margin-right": '+=424px'});
                    $('#slider-button').animate({"margin-right": '+=375px'});
                    overlay();
                }  
            }
        }
        
    });

//    $('#button_edit').click(function(){
//        if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
//            if ($('#slider-button').css("margin-right") == "530px")
//            {
//                $(prevSliderDialog).animate({"margin-right": '-=600'});
//                $('#slider-button').animate({"margin-right": '-=530'});
//            }
//        }
//        if ($('#slider-button').css("margin-right") == "530px")
//        {
//            $(sliderDialog).animate({"margin-right": '-=600'});
//            $('#slider-button').animate({"margin-right": '-=530'});
//        }
//        else
//        {
//            $(sliderDialog).animate({"margin-right": '+=600'});
//            $('#slider-button').animate({"margin-right": '+=530'});
//        }
//        
//    });
});

function showEditNote() {
    $("#noteprev").hide();
    $("#noteedit").show();
}

function showEditEmail() {
    $("#previewema").hide();
    $("#editema").show();
}

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
    var title = $("#addactiontitle").val();
    var actiontype = $("#actiontype").val();
    var description = $("#description").val();
    var actiondate = $("#datepicker").val();
    var actiontime = $("#timepicker1").val();
    var marketing_program = $("#marketing_program").val();
    
    if (marketing_program === '0') {
        //alert("Marketing Program not selected, please select any one Program");
        //$("#marketing_program").focus();
        //return false;
    }
    if (actiontype === '0') {
        alert("actiontype not selected, please select any one action");
        $("#actiontype").focus();
        return false;
    }
    if (title === "") {
        alert("title not entered, please enter the title");
        $("#addactiontitle").focus();
        return false;
    }

    if (description === "") {
        alert("description not entered, please enter the description");
        $("#description").focus();
        return false;
    }
    if (actiondate === "") {
        alert("date not selected, please select the date");
        $("#datepicker").focus();
        return false;
    }
    if (actiontime === "") {
        alert("time not selected, please selecet the time");
        $("#timepicker1").focus();
        return false;
    }

    return true;
}
function validateemailaction() {
    var actiontype = $("#email_schedule_type").val();
    var schedule_id = $("#email_scheduleid").val();
    var title = $("#email_edit_title").val();

    var description = $("#email_description").val();
    var actiondate = $("#emaildatetime").val();
    var actionDateTime=$("#timepickeremail").val().replace(/ /g,'');

    if (title === "") {
        alert("title not entered, please enter the title");
        $("#email_edit_title").focus();
        return false;
    }

    if (actiontype === "") {
        alert("actiontype not entered, please enter the actiontype");
        $("#email_schedule_type").focus();
        return false;
    }
    if (description === "") {
        alert("description not entered, please enter the description");
        $("#email_description").focus();
        return false;
    }
    if (actiondate === "") {
        alert("actiondate not entered, please enter the actiondate");
        $("#emaildatetime").focus();
        return false;
    }
    if (actionDateTime === "") {
        alert("actiondate not entered, please enter the actiondate");
        $("#timepickeremail").focus();
        return false;
    }

    return true;
}

function validatefacebookaction() {
    var actiontype = $("#fb_scheduletype").val();
    var schedule_id = $("#fb_scheduleid").val();
    var title = $("#fb_action_title").val();

    var description = $("#fb_description").val();
    var actiondate = $("#datepickerfb").val();
    var actionDateTime=$("#timepickerfb").val().replace(/ /g,'');

    if (title === "") {
        alert("title not entered, please enter the title");
        $("#fb_action_title").focus();
        return false;
    }

    if (actiontype === "") {
        alert("actiontype not entered, please enter the actiontype");
        $("#fb_scheduletype").focus();
        return false;
    }
    if (description === "") {
        alert("description not entered, please enter the description");
        $("#fb_description").focus();
        return false;
    }
    if (actiondate === "") {
        alert("actiondate not entered, please enter the actiondate");
        $("#datepicker2").focus();
        return false;
    }
    if (actionDateTime === "") {
        alert("actiontime not entered, please enter the actiondate");
        $("#timepicker2").focus();
        return false;
    }

    return true;
}

function validatetwitteraction() {
    var actiontype = $("#twitter_action_type").val();
    var schedule_id = $("#twitter_scheduleid").val();
    var title = $("#edit_twitter_title").val();

    var description = $("#twitter_description").val();
        var actiondate = $("#datepickertwitter").val();
        var actionDateTime=$("#timepickertwitter").val().replace(/ /g,'');

    if (title === "") {
        alert("title not entered, please enter the title");
        $("#edit_twitter_title").focus();
        return false;
    }

    if (actiontype === "") {
        alert("actiontype not entered, please enter the actiontype");
        $("#twitter_action_type").focus();
        return false;
    }
    if (description === "") {
        alert("description not entered, please enter the description");
        $("#twitter_description").focus();
        return false;
    }
    if (actiondate === "") {
        alert("actiondate not entered, please enter the actiondate");
        $("#datepicker3").focus();
        return false;
    }
    if (actionDateTime === "") {
        alert("actiondate not entered, please enter the actiondate");
        $("#timepickertwitter").focus();
        return false;
    }

    return true;
}

var selected_schedules_to_delete = "";

function setSelectedIds(selectedid) {

    var checked = document.getElementById(selectedid).checked;

    if (checked) {
        $("#delsel").show();
        var selected_schedule_id = $("#" + selectedid).val();
        selected_schedules_to_delete = selected_schedule_id + "," + selected_schedules_to_delete;
        console.log(selected_schedules_to_delete);
    }
    else {
        var selected_schedule_id = $("#" + selectedid).val();
        selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
        console.log(selected_schedules_to_delete);
        if (selected_schedules_to_delete === "") {
            $("#delsel").hide();
        }
        ;
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
    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getCampaigns();
}

function setTodaysDate() {
    user_selected_date = '';
    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getCampaigns();
}
  function EmpDetCtrl($scope) {

  

      $scope.ShowAddEmployee = function () {
           alert('Approved');
         return $scope.EmployeeInfoDiv = true;
        
     }
   
$scope.SaveData = function () {
          alert('Unapproved');
          $("#button1").css("display", "block");
         return $scope.EmployeeInfoDiv1 = true;
        
     }
    
 }

function controllerMarketingCampaign($scope, $http) {
    $scope.entities_selected_time = "";
    $scope.getCampaigns = function () {
        var curr_date = '';
        var tomorrowDate = '';
        var new_date = '';
        $("#messagetoday").show();
        $("#messagetomorrow").show();

        if (user_selected_date != "") {
            curr_date = moment(user_selected_date.date).format('YYYY-MM-DD');
            tomorrowDate = moment(addDays(user_selected_date.date, 1)).format('YYYY-MM-DD');
            new_date = moment(addDays(user_selected_date.date, 15)).format('YYYY-MM-DD');
        } else {
            curr_date = moment(new Date()).format('YYYY-MM-DD');
            tomorrowDate = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
            new_date = moment(addDays(new Date(), 15)).format('YYYY-MM-DD');
        }
        $http({
            method: 'GET',
            url: getHost() + 'GetScheduledEntities?from=' + curr_date + '&to=' + new_date
        }).success(function (data) {
            var entitySet = {};
            console.log(JSON.stringify(data));
            $scope.entityS = JSON.stringify(data);
//            $("#default").hide();
//            $("#selected").show();
            $scope.today_date = moment(new Date()).format('YYYY-MM-DD');
            $scope.tomorrow_date = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
            $scope.entitySet = data.entitydata;
            $scope.nodata = data.noactionsmessage;
            $("#default").css("display", "block");
//            $("#selected").css("display","none");            
            //console.log($scope.entitySet);
        }).error(function (data) {
            alert("request not successful");
        });
    };

    var millisToUTCDate = function (millis) {
        return toUTCDate(new Date(millis));
    };

    $scope.editScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc,marketingName) {
        $edit=1;  
        if (entity_type == "email") {
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.body == undefined) {
                    $('#maileditremove').hide();
                    $('#mailremovedtemplate2').show();
                    $('#mailpreviewdecond2').hide();
                    $("#preview_email").hide();
                    $("#edit_email").show();
                    $('#mailnotemplate1').show();
                    $('#mailtemplatesaved1').hide();
                    
                    //$("#edit_email_action").hide();
                } else {
                    $('#maileditremove').show();
                    $('#mailremovedtemplate2').hide();
                    $('#mailpreviewdecond2').show();
                    $("#preview_email").hide();
                    $('#mailnotemplate1').hide();
                    $('#mailtemplatesaved1').show();
                    $("#edit_email").show();
                    //$("#edit_email_action").hide();
                }
                //var a="hi..";
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $(".editcontent").empty();
                $(".editcontent").append(data.body);
                $(".editcontent").css("-webkit-transform", "scale(0.5,0.6)").css("margin-left", "-140px").css("margin-top", "-60px").css("margin-bottom", "-220px");
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.marketing_program_name = marketingName;
                $scope.showEmailList();
            }).error(function (data) {
                alert("request not successful");
            });

        } else if (entity_type == "facebook") {

            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $('#fbeditremove').hide();
                    $('#fbremovedtemplate2').show();
                    $('#fbpreviewdecond2').hide();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $('#fbnotemplate1').show();
                    $('#fbtemplatesaved1').hide();
                    //$("#edit_facebook_action").hide();
                    $('#edtfbimg').hide();
                } else {
                    $('#fbeditremove').show();
                    $('#fbremovedtemplate2').hide();
                    $('#fbpreviewdecond2').show();
                    $('#edtfbimg').show();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $('#fbnotemplate1').hide();
                    $('#fbtemplatesaved1').show();
                    //$("#edit_facebook_action").hide();
                }
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

        } else if (entity_type == "twitter") {
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $('#tweditremove').hide();
                    $('#twremovedtemplate2').show();
                    $('#twpreviewdecond2').hide();
                    $('#prevtwtimg').hide();
                    $("#preview_twitter").hide();
                    $("#edit_twitter").show();
                    $('#twnotemplate1').show();
                    $('#twtemplatesaved1').hide();
                    //$("#edit_twitter_action").hide();
                    $('#edttwtimg').hide();

                } else {
                    $('#tweditremove').show();
                    $('#twremovedtemplate2').hide();
                    $('#twpreviewdecond2').show();
                    $('#prevtwtimg').show();
                    $('#edttwtimg').show();
                    $("#preview_twitter").hide();
                    $("#edit_twitter").show();
                    $('#twnotemplate1').hide();
                    $('#twtemplatesaved1').show();
                    //$("#edit_twitter_action").hide();
                }

                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log("twitter description" + schedule_desc);
                console.log("twitter time" + schedule_time);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;

            }).error(function (data) {
                alert("request not successful");
            });

        } else if (entity_type == "note") {
            $("#noteprev").hide();
            $("#noteedit").show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
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

    $scope.showScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc) {

        if (entity_type == "email") {
            sliderDialog = "#preview";
            $('#slider-button').click();
            prevSliderDialog = "#preview";

            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.body == undefined) {
                    $("#preview_email").hide();
                    $("#edit_email").hide();
                    $("#edit_email_action").show();
                } else {
                    $("#preview_email").hide();
                    $("#edit_email").show();
                    $("#edit_email_action").hide();
                }

                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $(".editcontent").empty();
                $(".editcontent").append(data.body);
                $(".editcontent").css("-webkit-transform", "scale(0.5,0.6)").css("margin-left", "-140px").css("margin-top", "-60px").css("margin-bottom", "-220px");
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;

                $scope.showEmailList();
            }).error(function (data) {
                alert("request not successful");
            });

        } else if (entity_type == "facebook") {
            sliderDialog = "#previewfb";
            $('#slider-button').click();
            prevSliderDialog = "#previewfb";
            $('#edtfbimg').show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $("#preview_facebook").hide();
                    $("#edit_facebook").hide();
                    $("#edit_facebook_action").show();
                } else {
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $("#edit_facebook_action").hide();
                }
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

        } else if (entity_type == "twitter") {
            sliderDialog = "#previewtwitter";
            $('#slider-button').click();
            prevSliderDialog = "#previewtwitter";

            $('#edttwtimg').show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $("#preview_twitter").hide();
                    $("#edit_twitter").hide();
                    $("#edit_twitter_action").show();
                } else {
                    $("#preview_twitter").hide();
                    $("#edit_twitter").show();
                    $("#edit_twitter_action").hide();
                }
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

        } else if (entity_type == "note") {
            sliderDialog = "#previewNote";
            $('#slider-button').click();
            prevSliderDialog = "#previewNote";
            $("#noteprev").hide();
            $("#noteedit").show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
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

    $scope.ShowAddAction = function()
    {
        $slider=1;
        $edit=1; 
        sliderDialog = "#dvPriorityDialog";
        $('#slider-button').click();
        prevSliderDialog = "#dvPriorityDialog";
        $http({
                method: 'GET',
                url: getHost() + 'getMarketingProgramName.do'
            }).success(function (data, status) {
                $scope.marketprogram = data.userProgramData;
                if (data.userProgramData.user_program_id == "") {
                    
                } else {
                    $("dvPriorityDialog").show();
                }
                
//                $(".content").css("-webkit-transform", " scale(0.7,0.6)").css("left", "0px").css("top", "-20px");
                
            }).error(function (data) {
                alert("request not successful");
            });
    }

    $scope.getScheduleDetails = function (schedule_id, template_status, schedule_time, entity_type, schedule_title, schedule_desc, marketingName) {
        
        if (entity_type == "email") {
            $slider=2;
            sliderDialog = "#preview";
            $('#slider-button').click();
            prevSliderDialog = "#preview";
            $("#preview_email").show();
            $("#edit_email").hide();
            $("#edit_email_action").hide();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.body == undefined) {
                    $("#mailpreviewremove").hide();
                    $('#mailremovedtemplate').show();
                    $('#mailpreviewdecond').hide();
                    $('.approve').hide();
                    $("#email_button_send").val(create_button_title);
                } else {
                    $('.approve').show();
                    $("#mailpreviewremove").show();
                    $('#mailremovedtemplate').hide();
                    $('#mailpreviewdecond').show();
                    $('.content').show();
                    $('#mailimgprev').show();
                    $("#email_button_send").val("Send");
                }
                if(template_status=="complete")
                {
                    $("#emailgreen").show();
                    $("#emailred").hide();
                }
                else
                {
                    $("#emailgreen").hide();
                    $("#emailred").show();
                }
                var date = new Date(schedule_time);
                $(".content").empty();
                $(".content").append(data.body);
//                $(".content").css("-webkit-transform", " scale(0.7,0.6)").css("left", "0px").css("top", "-20px");
                
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.email_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = marketingName;
            }).error(function (data) {
                alert("request not successful");
            });
        } else if (entity_type == "facebook") {
            $slider=2;
            sliderDialog = "#previewfb";
            $('#slider-button').click();
            prevSliderDialog = "#previewfb";
            $("#preview_facebook").show();
            $("#edit_facebook").hide();
            $("#edit_facebook_action").hide();

            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $('.approve').hide();
                    $('#fbpreviewremove').hide();
                    $('#fbremovedtemplate').show();
                    $('#fbpreviewdecond').hide();
                    $('#imgcontainer').hide();
                    $('#prevfbimg').hide();
                    $('#fbnotemplate').show();
                    $('#fbtemplatesaved').hide();
                    $('#fb_preview_postdet').css("margin-top", 10);
//                    $("#fb_button_post").val(create_button_title);
                } else {
                    $('.approve').show();
                    $('#fbpreviewremove').show();
                    $('#fbremovedtemplate').hide();
                    $('#fbpreviewdecond').show();
                    $('#fbnotemplate').hide();
                    $('#fbtemplatesaved').show();
                    $('#imgcontainer').show();
                    $('#fb_preview_postdet').css("margin-top", 20);
                    $("#fb_button_post").val("Edit");
                    $('#prevfbimg').show();
                    $('#isFacebook').val("true");
                    $('#isTwitter').val("false");
                }
                
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                $scope.schedule_desc = schedule_desc;
                $scope.facebook_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = marketingName;
            }).error(function (data) {
                alert("request not successful");
            });

        } else if (entity_type == "twitter") {
            $slider=2;
            sliderDialog = "#previewtwitter";
            $('#slider-button').click();
            prevSliderDialog = "#previewtwitter";
            $('#prevtwtimg').show();
            $("#preview_twitter").show();
            $("#edit_twitter").hide();
            $("#edit_twitter_action").hide();

            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $('.approve').hide();
                    $('#twpreviewremove').hide();
                    $('#twremovedtemplate').show();
                    $('#twpreviewdecond').hide();
                    $('#prevtwtimg').hide();
                    $('#twnotemplate').show();
                    $('#twtemplatesaved').hide();
                    $('#twitter_preview_postdet').css("margin-top", 10);
//                    $("#twitter_button_post").val(create_button_title);
                } else {
                    $('.approve').show();
                    $('#twpreviewremove').show();
                    $('#twremovedtemplate').hide();
                    $('#twpreviewdecond').show();
                    $('#prevtwtimg').show();
                    $('#twnotemplate').hide();
                    $('#twtemplatesaved').show();
                    $("#twitter_button_post").val("Edit");
                    $('#twitter_preview_postdet').css("margin-top", -250);
                    $('#isFacebook').val("false");
                    $('#isTwitter').val("true");
                }

                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.twitter_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = marketingName;
            }).error(function (data) {
                alert("request not successful");
            });
        } else if (entity_type == "note") {
            $slider=1;
            sliderDialog = "#previewNote";
            $('#slider-button').click();
            prevSliderDialog = "#previewNote";

            $("#noteprev").show();
            $("#noteedit").hide();
            $scope.entities_selected_time = schedule_time;
            $scope.schedule_title = schedule_title;
            $scope.schedule_id = schedule_id;
            $scope.schedule_desc = schedule_desc;
            $scope.schedule_type = entity_type;
            $scope.note_template_status = template_status;
            $scope.marketing_program_name = marketingName;

        }


    };


    $scope.AddAction = function () {
        var title = $("#addactiontitle").val();
        var actiontype = $("#actiontype").val();
        var marketingProgramType=$("#marketing_program").val();
        var description = $("#description").val();
        var actiondate = $("#datepicker").val();
        var actionDateTime=$("#timepicker1").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var myDate = new Date(l); // Your timezone!
        var days=0;
        
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);
        

        var myEpoch = schedule_time;

        console.log("New Epoch: " + myEpoch);

        if (validateaction()) {
            var action = {"title": title, "actiontype": actiontype, "marketingType":marketingProgramType, "type": "save",
                "description": description, "action_date": myEpoch, "days":days
            };
            $http({
                method: 'POST',
                url: getHost() + 'AddAction',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(action)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != "") {
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
    $scope.updateActionEmail = function () {

        var actiontype = $("#email_schedule_type").val();
        console.log("action type" + actiontype);
        var schedule_id = $("#email_scheduleid").val();
        var title = $("#email_edit_title").val();

        var actiondate = $("#emaildatetime").val();
        var actionDateTime=$("#timepickeremail").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);
        var myEpoch = schedule_time;
        console.log("New Epoch: " + myEpoch);

        var description = $("#email_description").val();
        console.log(actiontype + "," + schedule_id + "," + title + "," + description);

        console.log("New Epoch: " + myEpoch);

        if (validateemailaction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch
            };
            $http({
                method: 'POST',
                url: getHost() + 'AddAction',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(action)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != "") {
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
    $scope.updateActionFacebook = function () {

        var actiontype = $("#fb_scheduletype").val();
        console.log("action type" + actiontype);
        var schedule_id = $("#fb_scheduleid").val();
        var title = $("#fb_action_title").val();
        
        var actiondate = $("#datepickerfb").val();
        var actionDateTime=$("#timepickerfb").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);
        var myEpoch = schedule_time;
        console.log("New Epoch: " + myEpoch);

        var description = $("#fb_description").val();
        console.log(actiontype + "," + schedule_id + "," + title + "," + description);
        console.log("New Epoch: " + myEpoch);
      
        if (validatefacebookaction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch
            };
            $http({
                method: 'POST',
                url: getHost() + 'AddAction',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(action)
            }).success(function (data)
            { 
                $scope.status = data;
                if (data != "") {
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
    $scope.updateActionTwitter = function () {

        var actiontype = $("#twitter_action_type").val();
        console.log("action type" + actiontype);
        var schedule_id = $("#twitter_scheduleid").val();
        var title = $("#edit_twitter_title").val();
        

        var actiondate = $("#datepickertwitter").val();
        var actionDateTime=$("#timepickertwitter").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);
        var myEpoch = schedule_time;
        console.log("New Epoch: " + myEpoch);


        var description = $("#twitter_description").val();
        console.log(actiontype + "," + schedule_id + "," + title + "," + description);
        
        console.log("New Epoch: " + myEpoch);

        if (validatetwitteraction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch
            };
            $http({
                method: 'POST',
                url: getHost() + 'AddAction',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(action)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != "") {
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

    $scope.deleteSchedule = function (schedules_to_delete, type, section) {
        var message;
        var requestBody;
        var responseMessage;
        if (type == "deleteMultiple") {
            message = "Are you sure you want to delete these Action(s)?";
            requestBody = {"type": "deleteSelected",
                "schedule_ids": selected_schedules_to_delete};
            responseMessage = "Selected actions were deleted successfully";
        } else if (type == "delete") {
            message = "Are you sure you want to delete this Action?";
            requestBody = {"type": "delete",
                "schedule_ids": schedules_to_delete};
            responseMessage = "Selected actions were deleted successfully";
        } else if (type == "remove") {
            message = "Are you sure you want to remove the template?";
            requestBody = {"type": "removetemplate",
                "schedule_ids": schedules_to_delete};
            responseMessage = "Selected actions were deleted successfully";
        }


        if (confirm(message)) {
            $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== "") {
                    if(section == "facebook")
                    {
                        $("#fbpreviewdecond").hide();
                        $("#fbremovedtemplate").show();                        
                    }
                    if(section == "twitter")
                    {
                        $("#twpreviewdecond").hide();
                        $("#twremovedtemplate").show();                     
                    }
                    if(section == "email")
                    {
                        $("#mailpreviewdecond").hide();
                        $("#mailremovedtemplate").show();                     
                    }
                    alert(responseMessage);
                    window.open(getHost() + 'marketing.jsp', "_self");
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });
        }
    };

    $scope.updateNote = function () {
        var message;
        
        var schedule_id = $("#note_schedule_id").val();
        var entity_id = $("#note_entity_id").val();
        var note_title = $("#note_title").val();
        var status = $("#status").val();
        var note_desc = $("#note_desc").val();
        var message = "Do you wan't to update the record?";
        var actiondate = $("#datepicker4").val();
        var actionDateTime=$("#timepicker3").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);
        var myEpoch = schedule_time;
        console.log("New Epoch: " + myEpoch);

 
        var schedule_details = {
            "type": "note",
            "schedule_id": schedule_id,
            "schedule_title": note_title,
            "schedule_desc": note_desc,
            "status": status,
            "schedule_time": myEpoch
        }
       if(confirm(message)){
            $http({
                method: 'POST',
                url: getHost() + 'ChangeScheduleServlet',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(schedule_details)
            }).success(function (data)
            {
                $scope.status = data;
                if (data != "") {
                    alert("details saved successfully");
                    window.open(getHost() + 'marketing.jsp', "_self");

                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("request not succesful");
            });
            
        }
    };


    $scope.updateEmailSchedule = function () {
        var schedule_id = $("#email_schedule_id").val();
        var entity_id = $("#email_entity_id").val();
        var schedule_title = $("#email_entitytitle").val();
        var email_entitysubject = $("#email_entitysubject").val();
        var email_entitytoaddress = $("#email_entitytoaddress").val();
        var email_entityfromaddress = $("#email_entityfromaddress").val();
        var email_entityreplytoaddress = $("#email_entityreplytoaddress").val();
        
        var actiondate = $("#email_schedule_datetime").val();
        var actionDateTime=$("#timepickeremailaction").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);
        var myEpoch = schedule_time;
        console.log("New Epoch: " + myEpoch);
        
        
        var chooseEmailList = $("#chooseEmailList").val();

        console.log("New Epoch: " + myEpoch);

        var schedule_details = {
            "type": "updateemail",
            "schedule_id": schedule_id,
            "entity_id": entity_id,
            "schedule_title": schedule_title,
            "schedule_time": myEpoch,
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
            if (data != "") {
                alert("details saved successfully");
                window.open(getHost() + 'marketing.jsp', "_self");

            }
        }).error(function (data, status) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.

            alert("request not succesful");
        });

    };

    $scope.updateSocialSchedule = function () {

        var social_type = $("#social_type").val();

        if (social_type == "facebook") {

            if ($scope.validatefacebook()) {

                var schedule_id = $("#facebook_schedule_id").val();
                var entity_id = $("#facebook_entity_id").val();
                var schedule_title = $("#facebook_schedule_title").val();
                var schedule_Description = $("#facebook_schedule_Description").val();
                var facebook_schedule_posttext = $("#facebook_schedule_posttext").val();
                var facebook_schedule_url = $("#facebook_schedule_url").val();
                var facebook_schedule_description = $("#facebook_schedule_description").val();
                
                var actiondate = $("#facebook_schedule_date").val();
                var actionDateTime=$("#facebook_schedule_time").val().replace(/ /g,'');
                var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
               var schedule_time = Date.parse(l);
                console.log("Epoch: " + schedule_time);
                var myEpoch = schedule_time;
                console.log("New Epoch: " + myEpoch);

               
                   var schedule_details = {"type": "updatesocial",
                    "schedule_id": schedule_id,
                    "entity_id": entity_id,
                    "metadata": {
                        description: '"' + facebook_schedule_description + '"',
                        post_text: '"' + facebook_schedule_posttext + '"',
                        url: '"' + facebook_schedule_url + '"'
                    },
                    "schedule_time": myEpoch,
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
                    if (data != "") {
                        alert("details saved successfully");
                        window.open(getHost() + 'marketing.jsp', "_self");

                    }
                }).error(function (data, status) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.

                    alert("request not succesful");
                });

            }
        } else if (social_type == "twitter") {

            if ($scope.validatetwitter()) {

                var schedule_id = $("#twitter_schedule_id").val();
                var entity_id = $("#twitter_entity_id").val();
                var schedule_title = $("#twitter_schedule_title").val();
                var schedule_Description = $("#twitter_schedule_Description").val();
                var schedule_posttext = $("#twitter_schedule_post_text").val();
                


                var actiondate = $("#twitter_schedule_date").val();
                var actionDateTime=$("#timepicker_twittertime").val().replace(/ /g,'');
                var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
                var schedule_time = Date.parse(l);
                console.log("Epoch: " + schedule_time);
                var myEpoch = schedule_time;
                console.log("New Epoch: " + myEpoch);
                
               var schedule_details = {"type": "updatesocial",
                    "schedule_id": schedule_id,
                    "entity_id": entity_id,
                    "metadata": {
                        text: '"' + schedule_posttext + '"',
                        shorturl: '"' + $("#twitter_schedule_post_url").val() + '"'
                    },
                    "schedule_time": myEpoch,
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
                    if (data != "") {
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
    $scope.validatefacebook = function () {

        var facebook_schedule_title = $("#facebook_schedule_title").val();
        var facebook_schedule_Description = $("#facebook_schedule_Description").val();
        var facebook_schedule_posttext = $("#facebook_schedule_posttext").val();
        var facebook_schedule_url = $("#facebook_schedule_url").val();
        var facebook_schedule_description = $("#facebook_schedule_description").val();
        var actiondate = $("#facebook_schedule_date").val();
        var actionDateTime=$("#facebook_schedule_time").val().replace(/ /g,'');

        if (facebook_schedule_title == "") {
            alert("schedule title not entered, please enter the value");
            $("#facebook_schedule_title").focus();
            return false;
        }
        if (facebook_schedule_Description == "") {
            alert("schedule description not entered, please enter the value");
            $("#facebook_schedule_Description").focus();
            return false;
        }
        if (facebook_schedule_posttext == "") {
            alert("schedule post text not entered, please enter the value");
            $("#facebook_schedule_posttext").focus();
            return false;
        }
        if (facebook_schedule_url == "") {
            alert("schedule url not entered, please enter the value");
            $("#facebook_schedule_url").focus();
            return false;
        }
        if (facebook_schedule_description == "") {
            alert("schedule description not entered, please enter the value");
            $("#facebook_schedule_description").focus();
            return false;
        }
        if (actiondate == "") {
            alert("schedule date not entered, please enter the date");
            $("#facebook_schedule_date").focus();
            return false;
        }
        if (actionDateTime == "") {
            alert("schedule time not entered, please enter the time");
            $("#facebook_schedule_time").focus();
            return false;
        }
        return true;

    };
    $scope.validatetwitter = function () {

        var schedule_id = $("#twitter_schedule_id").val();
        var entity_id = $("#twitter_entity_id").val();
        var schedule_title = $("#twitter_schedule_title").val();
        var schedule_Description = $("#twitter_schedule_Description").val();
        var schedule_posttext = $("#twitter_schedule_post_text").val();
        var actiondate = $("#twitter_schedule_date").val();
        var actionDateTime=$("#timepicker_twittertime").val().replace(/ /g,'');

        if (schedule_title == "") {
            alert("schedule title not entered, please enter the value");
            $("#twitter_schedule_title").focus();
            return false;
        }
        if (schedule_Description == "") {
            alert("schedule description not entered, please enter the value");
            $("#twitter_schedule_Description").focus();
            return false;
        }
        if (schedule_posttext == "") {
            alert("schedule post text not entered, please enter the value");
            $("#twitter_schedule_post_text").focus();
            return false;
        }
        if (actiondate == "") {
            alert("schedule date not entered, please enter the value");
            $("#schedule_date").focus();
            return false;
        }
        if (actionDateTime == "") {
            alert("schedule time not entered, please enter the value");
            $("#schedule_time").focus();
            return false;
        }
        return true;

    };
    };
var error_message = "unable to post the schedule";
var error_message_email = "unable to send the email";

function sendEmail() {
    var email_from_name = $("#email_entity_from_name").val();
    var email_entitysubject = $("#email_entitysubject").val();
    var email_entitytoaddress = $("#email_entitytoaddress").val();
    var email_entityfromaddress = $("#email_entityfromaddress").val();
    var email_entityreplytoaddress = $("#email_entityreplytoaddress").val();
    var chooseEmailList = $("#chooseEmailList").val();
    var email_body = $("#email_entity_body").val();
    var scheduleid = $("#email_schedule_id").val();
    var entityid = $("#email_entity_id").val();
    if ($("#email_button_send").val() == "Send") {
        $.ajax({
            url: getHost() + "SendEmailServlet",
            type: "post",
            data: {
                from_name: email_from_name,
                email_subject: email_entitysubject,
                email_addresses: email_entitytoaddress,
                from_email_address: email_entityfromaddress,
                reply_to_email_address: email_entityreplytoaddress,
                htmldata: email_body,
                email_list: chooseEmailList
            },
            success: function (responseText) {
                $('#loadingGif').remove();
                    var schedule_data = {
                                            type: 'updateSchedule',
                                            scheduleid: scheduleid,
                                            entityid: entityid
                                            }
                        //alert(JSON.stringify(schedule_data));
                        $.ajax({
                           url:'ChangeScheduleServlet',
                           method:'Post',
                           dataType: 'json',
                           contentType: 'application/json',
                           mimeType: 'application/json',
                           data:JSON.stringify(schedule_data),
                           success: function (responseText) {
                               alert(responseText);
                               if (responseText == "true"){
                                 alert("Your email has been sent successfully");
                               }
                            }
                        });
                        document.location.href = "marketing.jsp";
            },
            error: function () {
                alert(error_message);
            }

        });

    } else if (checkifcreatebutton($("#email_button_send").val())) {
        document.location.href = "dashboard.jsp";
    }

}

function postSocial() {
    var image_name = "";
    var isFacebook = $("#isFacebook").val();
    var isTwitter = $("#isTwitter").val();
    var scheduleid = '', entityid = '';
    if (($("#fb_button_post").val() == "Post") && ($("#twitter_button_post").val() == "Post")) {
        if (($("#facebook_image_name").val()) != "") {
            image_name = $("#facebook_image_name").val();
            scheduleid = $("#facebook_schedule_id").val();
            entityid = $("#facebook_entity_id").val();
        }
        if (($("#twitter_image_name").val()) != "") {
            image_name = $("#twitter_image_name").val();
            scheduleid = $("#twitter_schedule_id").val();
            entityid = $("#twitter_entity_id").val();
        }

        var link = $("#twitter_schedule_post_url").val();
        var f = link.startsWith("http");
        if (!f)
        {
            link = "http://" + $("#twitter_schedule_post_url").val();
        }
        var url = link;
        var username = "sandeep264328"; // bit.ly username
        var key = "R_63e2f83120b743bc9d9534b841d41be6";
        $.ajax({
            url: "http://api.bit.ly/v3/shorten",
            data: {longUrl: url, apiKey: key, login: username},
            dataType: "jsonp",
            success: function (v)
            {
                var bit_url = v.data.url;
                $.ajax({
                    url: 'PostToSocial',
                    method: 'post',
                    data: {
                        imageToPost: image_name,
                        accesstoken: $("#facebook_accesstoken").val(),
                        postText: $("#facebook_schedule_posttext").val(),
                        title: $("#facebook_schedule_posttext").val(),
                        description: $("#facebook_schedule_description").val(),
                        url: $("#facebook_schedule_url").val(),
                        twittweraccestoken: $("#twitter_entity_accesstoken").val(),
                        twitterTokenSecret: $("#twitter_entity_tokensecret").val(),
                        text: $("#twitter_schedule_post_text").val(),
                        isFacebook: isFacebook,
                        isTwitter: isTwitter,
                        imagePost: image_name,
                        shorturl: bit_url
                    },
                    success: function (responseText) {
                        var schedule_data = {
                                            type: 'updateSchedule',
                                            scheduleid: scheduleid,
                                            entityid: entityid
                                            }
                        //alert(JSON.stringify(schedule_data));
                        $.ajax({
                           url:'ChangeScheduleServlet',
                           method:'Post',
                           dataType: 'json',
                           contentType: 'application/json',
                           mimeType: 'application/json',
                           data:JSON.stringify(schedule_data),
                           success: function (responseText) {
                               alert("Your post has been published successfully");
                               document.location.href = "marketing.jsp";
                            },
                            error: function () {
                                alert(error_message);
                            }
                        });
                        document.location.href = "marketing.jsp";
                    },
                    error: function () {
                        alert(error_message);
                    }
                });
            }

        });

    } else if (checkifcreatebutton($("#fb_button_post").val()) || (checkifcreatebutton($("#twitter_button_post").val()))) {
        document.location.href = "dashboard.jsp";
    }

    function checkifcreatebutton(value) {
        if (value == create_button_title) {
            return true;
        } else {
            return false;
        }
    }

}

