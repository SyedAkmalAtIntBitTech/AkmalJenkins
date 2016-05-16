/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var latest_date="";
var sliderDialog = "";
var prevSliderDialog = "";
var create_button_title = "Edit";

$(document).ready(function ()
{
  
    $(".arrow_top").hide();  
    $(".delete-button").hide();
    
    $(".calendar-dropdown").click(function (){$("#jumptodatepicker").trigger( "click" );});
    
    var picker = new Pikaday(
                            {
                                field: document.getElementById('jumptodatepicker'),
                                firstDay: 1,
                                minDate: new Date('2000-01-01'),
                                maxDate: new Date('2050-12-31'),
                                yearRange: [2000,2050],
                                onSelect: function() {
                                    var mydate=this.getMoment();
                                   var mydt=mydate.toLocaleString();
                                    var myDate = new Date(mydt);
                                    setCurrentDate(myDate);
                                }                                
                            });               
    var d = new Date();
    var c_day = d.getDate();
    var c_month = d.getMonth() + 1;
    var c_year = d.getFullYear();
    $("#liPriority").click(function () {
    });

    $("#liFasting").click(function () {
        sliderDialog = "#dvFastingDialog";
        $('#slider-button').click();
        prevSliderDialog = "#dvFastingDialog";
    });
$a=0;
$edit=0;
    $('#slider-button').click(function () {
        $a+=1;
        if($a>=2 && $edit==1)
        {   
                $edit=0;  
                if($slider==2)
                {
                    if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
                        if ($('#slider-button').css("margin-right") == "788px")
                        {
                            $(prevSliderDialog).animate({"margin-right": '-=900px'});
                            $('#slider-button').animate({"margin-right": '-=788px'});
                        }
                    }

                    if ($('#slider-button').css("margin-right") == "788px")
                    {
                        $slider=0;
                        $a=0;
                        $(sliderDialog).animate({"margin-right": '-=900px'});
                        $('#slider-button').animate({"margin-right": '-=788px'});
                        closeoverlay();
                    }
                    else
                    {
                        $(sliderDialog).animate({"margin-right": '+=900px'});
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
            if($slider==2)
            {
                if (prevSliderDialog != "" && prevSliderDialog != sliderDialog) {
                    if ($('#slider-button').css("margin-right") == "788px")
                    {
                        $(prevSliderDialog).animate({"margin-right": '-=900px'});
                        $('#slider-button').animate({"margin-right": '-=788px'});
                    }
                }
                if ($('#slider-button').css("margin-right") == "788px")
                {
                    $slider=0;
                    $a=0;
                    $(sliderDialog).animate({"margin-right": '-=900px'});
                    $('#slider-button').animate({"margin-right": '-=788px'});
                    closeoverlay();
                }
                else
                {
                    $(sliderDialog).animate({"margin-right": '+=900px'});
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

function reSet()
{
   $("#addactiontitle").val("");
   $("#description").val("");
   $("#datepicker").val("");
   $("#timepicker1").val("");
   $("#actiontype").val("0");
   return true;
    
}

  $(".close").click(function(){
        
        var change=$("#change").val();
        if( change === "0")
        {
            closeoverlay();
            $('#slider-button').click();
            $('.bottom-cta-bar').hide();
        }
        if( change !== "0")
        {
            setTimeout(function (){
            window.open(getHost() + 'user/marketing', "_self");
               },430);
            $("#change").val("0");
            closeoverlay();
            $('#slider-button').click();
            $('.bottom-cta-bar').hide();
        }
    });

function validateaction() {
    var title = $("#addactiontitle").val();
    var actiontype = $("#actiontype").val();
    var description = $("#description").val();
    var actiondate = $("#datepicker").val();
    var actiontime = $("#timepicker1").val();
    var marketing_program = $("#marketing_program").val();
    if (marketing_program === '0') {
    }
    if (actiontype === '0') {
        alert(actiontypeerror);
        $("#actiontype").focus();
        return false;
    }
    if (title === "") {
        alert(titleerror);
        $("#addactiontitle").focus();
        return false;
    }

    if (actiondate === "") {
        alert(dateerror);
        return false;
    }else {
        var current_date = new Date();
        var format_curr_date = moment(current_date.date).format('YYYY-MM-DD');
        console.log(format_curr_date);
        var current_date = Date.parse(format_curr_date.toString());
        console.log(current_date);
        console.log(actiondate);
        var selected_date = new Date(actiondate);
        console.log(selected_date);
        var format_selected_date = moment(selected_date).format('YYYY-MM-DD');
        console.log(format_selected_date);
        var epoch_selected_date = Date.parse(format_selected_date.toString());
        console.log(epoch_selected_date);
        if (parseInt(epoch_selected_date) < parseInt(current_date)){
            alert(wrongdate);
            $("#datepicker").focus();
            return false;
        }
        
    }

    if (actiontime === "") {
        alert(timeerror);
        $("#timepicker1").focus();
        return false;
    }

    return true;
}
function validateemailDescription()
{
    var description = $("#emailnotes").val();
     if (description === "") {
        alert(decsriptionerror);
        $("#emailnotes").focus();
        return false;
    }
    return true;
}
function validateemailaction() {
    var actiontype = $("#email_schedule_type").val();
    var schedule_id = $("#email_scheduleid").val();
    var title = $("#email_edit_title").val();
    var days=$("#emaildays").val();
    var description = $("#email_description").val();
    var actiondate = $("#emaildatetime").val();
    var actionDateTime=$("#timepickeremail").val().replace(/ /g,'');

    if (title === "") {
        alert(titleerror);
        $("#email_edit_title").focus();
        return false;
    }

    if (actiontype === "") {
        alert(actiontypeerror);
        $("#email_schedule_type").focus();
        return false;
    }
    if (actiondate === "" || days =="") {
        if(days=="")
        {
            alert(dayserror);
            $("#twdays").focus();
        }
        if(!isNaN(days))
        {
            alert(wrongdays);
            $("#twdays").focus();
        }
        if(actiondate=="")
        {
            alert(dateerror);
            $("#datepicker3").focus();
        }
        return false;
    }
    if (actionDateTime === "") {
        alert("Actiondate not entered! Please enter the actiondate.");
        $("#timepickeremail").focus();
        return false;
    }
    return true;
}

function validatefacebookaction() {
    var actiontype = $("#fb_scheduletype").val();
    var schedule_id = $("#fb_scheduleid").val();
    var title = $("#fb_action_title").val();
    var days=$("#fbdays").val();
    var description = $("#fb_description").val();
    var actiondate = $("#datepickerfb").val();
    var actionDateTime=$("#timepickerfb").val().replace(/ /g,'');
    if (title === "") {
        alert(titleerror);
        $("#fb_action_title").focus();
        return false;
    }

    if (actiontype === "") {
        alert("Actiontype not entered! Please enter the actiontype.");
        $("#fb_scheduletype").focus();
        return false;
    }
    if (actiondate === "" || days =="") {
        if(days=="")
        {
            alert(dayserror);
            $("#twdays").focus();
        }
        if(!isNaN(days))
        {
            alert(wrongdays);
            $("#twdays").focus();
        }
        if(actiondate=="")
        {
            alert(dateerror);
            $("#datepicker3").focus();
        }
        return false;
    }
    if (actionDateTime === "") {
        alert(timeerror);
        $("#timepicker2").focus();
        return false;
    }

    return true;
}

function validatetwitteraction() {
    var actiontype = $("#twitter_action_type").val();
    var schedule_id = $("#twitter_scheduleid").val();
    var title = $("#edit_twitter_title").val();
    var days=$("#twdays").val();
    var description = $("#twitter_description").val();
    var actiondate = $("#datepickertwitter").val();
    var actionDateTime=$("#timepickertwitter").val().replace(/ /g,'');

    if (title === "") {
        alert("Title not entered! Please enter the title.");
        $("#edit_twitter_title").focus();
        return false;
    }

    if (actiontype === "") {
        alert(actiontypeerror);
        $("#twitter_action_type").focus();
        return false;
    }
    if (description === "") {
        alert(decsriptionerror);
        $("#twitter_description").focus();
        return false;
    }
    if (actiondate === "" || days =="") {
        if(days=="")
        {
            alert(dayserror);
            $("#twdays").focus();
        }
        if(!isNaN(days))
        {
            alert(wrongdays);
            $("#twdays").focus();
        }
        if(actiondate=="")
        {
            alert(dateerror);
            $("#datepicker3").focus();
        }
        return false;
    }
    if (actionDateTime === "") {
        alert(timeerror);
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
    }
    else {
        var selected_schedule_id = $("#" + selectedid).val();
        selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
        if (selected_schedules_to_delete === "") {
            $("#delsel").hide();
        }        
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
    $(".delete-button").hide();
    $("#liPriority").show();
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

var count=0;
        function selcheckbox(id){ 
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();
            var selected_schedule_id=id;
            if(htm.contains('class="check-icon"')){
                selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
                count-=1;
                $("#"+id).html(content);
            }
            else
            {
                selected_schedules_to_delete = selected_schedule_id + "," + selected_schedules_to_delete;
                count+=1;
                $("#"+id).html(content+'<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $(".add-action-button").hide();
                $(".delete-button").show();
            }
            if(count==0)
            {
                $(".add-action-button").show();
                $(".delete-button").hide();
            }
        }

function controllerMarketingCampaign($scope, $http) {
    
    
    $scope.entities_selected_time = "";
    $scope.master_facebook = getfacebook();
    $scope.master_twitter = gettwitter();
    $scope.master_email = getemail();
    $scope.master_note = getnote();
    $scope.mySplit = function(string, nb) {
      var array = string.split('-');
      return array[nb];
    }
    $scope.myYear = function(string) {
        var month="";
        if(string=='01')
            return month="Jan";
        if(string=='02')
            return month="Feb";
        if(string=='03')
            return month="Mar";
        if(string=='04')
            return month="Apr";
        if(string=='05')
            return month="May";
        if(string=='06')
            return month="Jun";
        if(string=='07')
            return month="Jul";
        if(string=='08')
            return month="Aug";
        if(string=='09')
            return month="Sept";
        if(string=='10')
            return month="Oct";
        if(string=='11')
            return month="Nov";
        if(string=='12')
            return month="Dec";
    }
    $scope.getCampaigns = function () {
        showOverlay();
        var curr_date = '';
        var tomorrowDate = '';
        var new_date = '';
        if (user_selected_date !== "") {
            curr_date = moment(user_selected_date).format('YYYY-MM-DD');
            tomorrowDate = moment(addDays(user_selected_date, 1)).format('YYYY-MM-DD');
            new_date = moment(addDays(user_selected_date, 15)).format('YYYY-MM-DD');
        } else {
            curr_date = moment(new Date()).format('YYYY-MM-DD');
            tomorrowDate = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
            new_date = moment(addDays(new Date(), 15)).format('YYYY-MM-DD');
        }
        latest_date=curr_date;
        var invalid= "Invalid date";
        if(curr_date !== invalid){
                $http({
                    method: 'GET',
                    url: getHost() + 'GetScheduledEntities?from=' + curr_date + '&to=' + new_date
                }).success(function (data) { 
                    var parseJSON=JSON.parse(data.d.details);
                    var entitySet = {};
                    $scope.entityS = JSON.parse(JSON.stringify(parseJSON));
                    $scope.today_date = moment(new Date()).format('YYYY-MM-DD');
                    $scope.tomorrow_date = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
                    $scope.entitySet = parseJSON.entitydata;
                    $scope.nodata = parseJSON.noactionsmessage;
                    $("#default").css("display", "block");
                    $('.page-background').animate({ scrollTop: 0 },500);
                    hideOverlay();
                }).error(function (data) {
                    hideOverlay();
                    alert(requesterror);
                });
        }
    };

    var millisToUTCDate = function (millis) {
        return toUTCDate(new Date(millis));
    };

    $scope.getRecurringMailDetails = function (schedule_id, template_status, schedule_time, entity_type, schedule_title, schedule_desc, action_status, marketingProgramName, days) {
          
            $slider=2;
            sliderDialog = "#recurring_preview";
            $('#slider-button').click();
            prevSliderDialog = "#recurring_preview";
            $("#recurring_preview_email").show();
            $("#recurring_edit_email_action").hide();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.body == undefined) {
                    $("#mailpreviewremove6").hide();
                    $('#mailremovedtemplate6').show();
                    $('#mailpreviewdecond5').hide();
                    $('.approve').hide();
                    $("#email_button_send").val(create_button_title);
                } else {
                    $('.approve').show();
                    $("#mailpreviewremove6").show();
                    $('#mailremovedtemplate6').hide();
                    $('#mailpreviewdecond5').show();
                    $('.content').show();
                    $('#mailimgprev').show();
                    $("#email_button_send").val("Send");
                }
                if(template_status=="complete")
                {
                    $("#recurringemailgreen").show();
                    $("#recurringemailred").hide();
                }
                else
                {
                    $("#recurringemailgreen").hide();
                    $("#recurringemailred").show();
                }
                var date = new Date(schedule_time);
                $(".content").empty();
                $(".content").append(data.body);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                $scope.schedule_desc = schedule_desc;
                $scope.recurring_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.user_marketing_program_id = action_status;
                $scope.recurring_action_status=action_status;
                $scope.marketingProgramName=marketingProgramName;
                $scope.days=days;
            }).error(function (data) {
                alert(requesterror);
            });
    };
    
    $scope.recurringApproval = function(entity_id, template_status){
        
        var approval_type = {"entity_id": entity_id, "template_status":template_status};
        
        $http({
            method: 'POST',
            url: 'approveStatusRecurring',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(approval_type)
        }).success(function (data, status, headers, config) {
          if (data == "true"){
            alert(templetestatussaved);
            window.open(getHost() + 'programactions.jsp?program_id='+program, "_self");
          }else {
              alert(savingrecordproblem);
          }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });      
        
    }; 

     $scope.Approval = function(entity_id, template_status, entity_type){
        
        var approval_type = {"entity_id": entity_id.toString(), 
                             "template_status":template_status, 
                             "entity_type": entity_type};
                        
        
        $http({
            method: 'POST',
            url: getHost()+'approveStatus',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(approval_type)
        }).success(function (data, status, headers, config) {
           
          if (data == "true"){
            alert(templetestatussaved);
            window.open(getHost() + 'user/marketing', "_self");
          }else {
              alert(savingrecordproblem);
          }
        }).error(function (data, status, headers, config) {
  
            alert(nodataerror);
        });      
        
    };


    $scope.editScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc,marketingName,is_today_active) {
      
        $edit=1;  
        if (entity_type == getemail()) {
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
                } else {
                    $('#maileditremove').show();
                    $('#mailremovedtemplate2').hide();
                    $('#mailpreviewdecond2').show();
                    $("#preview_email").hide();
                    $('#mailnotemplate1').hide();
                    $('#mailtemplatesaved1').show();
                    $("#edit_email").show();
                }
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $(".editcontent").empty();
                $(".editcontent").append(data.body);
                $(".editcontent").css("-webkit-transform", "scale(0.5,0.6)").css("margin-left", "-140px").css("margin-top", "-60px").css("margin-bottom", "-220px");
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.marketing_program_name = marketingName;
                $scope.is_today_active = is_today_active;
                $scope.showEmailList();
            }).error(function (data) {
                alert(requesterror);
            });

        } else if (entity_type == getfacebook()) {

            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $("#fbpostremove").hide();
                    $('#fbeditremove').hide();
                    $('#fbremovedtemplate2').show();
                    $('#fbpreviewdecond2').hide();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $('#fbnotemplate1').show();
                    $('#fbtemplatesaved1').hide();
                    $('#edtfbimg').hide();
                } else {
                    $("#fbpostremove").show();
                    $('#fbeditremove').show();
                    $('#fbremovedtemplate2').hide();
                    $('#fbpreviewdecond2').show();
                    $('#edtfbimg').show();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $('#fbnotemplate1').hide();
                    $('#fbtemplatesaved1').show();
                }
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
                $scope.is_today_active = is_today_active;
            }).error(function (data) {
                alert(requesterror);
            });

        } else if (entity_type == gettwitter()) {
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
                }
                
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
                $scope.is_today_active = is_today_active;
            }).error(function (data) {
                alert(requesterror);
            });

        } else if (entity_type == getnote()) {
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
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert(requesterror);
            });
        }
    };

    $scope.showScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc) {

        if (entity_type == getemail()) {
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
                alert(requesterror);
            });
        }         
        
        else if (entity_type == getfacebook()) {
            sliderDialog = "#facebooksection";
            prevSliderDialog = "#facebooksection";
            $('#edtfbimg').show();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $("#fbpostremove").hide();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").hide();
                    $("#edit_facebook_action").show();
                } else {
                    $("#fbpostremove").show();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $("#edit_facebook_action").hide();
                }
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert(requesterror);
            });

        } else if (entity_type == gettwitter()) {
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
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert(requesterror);
            });

        } else if (entity_type == getnote()) {
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
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
            }).error(function (data) {
                alert(requesterror);
            });

        }

    };

    $scope.ShowAddAction = function()
    { 
        $("#fade").show();
        $("#addAction").show();
        if(reSet()){ }
        $slider=1;
        $edit=1; 
        $http({
                method: 'GET',
                url: getHost() + 'getMarketingProgramName'
            }).success(function (data, status) {
                $scope.marketprogram = data.userProgramData;
                if (data.userProgramData.user_program_id == "") {                    
                } else {
                    $("dvPriorityDialog").show();
                }
            }).error(function (data) {
                alert("Request not successful!");
            });
    }

    $("#closenotepopup").click(function(){
        var change=$("#change").val();
        if(change === "1")
        {
            $("#change").val("0");
            window.open(getHost() + 'user/marketing', "_self");
        }
        $slider=2;
        sliderDialog = "#notediv";
        prevSliderDialog = "#notediv";
        $('#slider-button').click();
    });
    $scope.getScheduleDetails = function (schedule_id, template_status, schedule_time, entity_type, schedule_title, schedule_desc, marketingName, programId, days, is_today_active) {
       
        if (entity_type === getemail()) {
            $slider=2;
            sliderDialog = "#emailsection";
            
            $("#emailpostsection").show();
            $("#emailpostremove").show();
            $("#emailactionsection").hide();
            $("#emailnotesection").hide();
            $("#emailactionsave").hide();
            $("#emailnotesave").hide();

            $("#emailaction1").removeClass("top-subnav-link-active-detail");
            $("#emailaction1 a").removeAttr("class");
            $("#emailnote1").removeClass("top-subnav-link-active-detail");
            $("#emailnote1 a").removeAttr("class");
            $("#emailpost1").removeClass("top-subnav-link-active-detail");
            $("#emailpost1 a").removeAttr("class");

            $("#emailpost1").addClass("top-subnav-link-active-detail");
            $("#emailpost1 a").addClass("h3-subnav-subnav-active");
            $("#emailaction1").addClass("top-subnav-links-detail");
            $("#emailaction1 a").addClass("h3-subnav");
            $("#emailnote1").addClass("top-subnav-links-detail");
            $("#emailnote1 a").addClass("h3-subnav");
            
            $(".time_pick").width('100%');
            
            prevSliderDialog = "#emailsection";
             $("#emailpost").click();
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                var details=(JSON.parse((data.d.details)));
                var detailsBody =details.body;
               
                $scope.entitiesdetails = data;
                if (detailsBody == undefined) {
                    $("#emailapprove").hide();
                    $("#mailtemplatesaved1").hide();
                    $("#mailnotemplate1").show();
                    $('#emailpostremove').hide();
                    $("#noemailsdiv").show();
                    $("#savedemailsdiv").hide();
                } else {
                    $("#emailapprove").show();
                    $("#noemailsdiv").hide();
                    $("#savedemailsdiv").show();
                     $("#mailtemplatesaved1").show();
                    $("#mailnotemplate1").hide();
                    $('#emailpostremove').show();
                }
                var date = new Date(schedule_time);
                $('#emailcontentiframe').contents().find('html').html(detailsBody);                 
                $scope.entities_selected_time = date;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
           
                $scope.email_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = marketingName;
                $scope.user_marketing_program_id = programId;
                $scope.days = days;
                $scope.is_today_active = is_today_active;
            }).error(function (data) {
                alert(requesterror);
            });
            $('#slider-button').click();
        } 
        if (entity_type === getfacebook()) {
            $slider=2;
            sliderDialog = "#facebooksection";
            $("#facebookpostsection").show();
            $("#facebooknotesection").hide();
            $("#facebookactionsection").hide();
            
            $("#facebookaction1").removeClass("top-subnav-link-active-detail");
            $("#facebookaction1 a").removeAttr("class");
            $("#facebooknote1").removeClass("top-subnav-link-active-detail");
            $("#facebooknote1 a").removeAttr("class");
            $("#facebookpost1").removeClass("top-subnav-link-active-detail");
            $("#facebookpost1 a").removeAttr("class");

            $("#facebookpost1").addClass("top-subnav-link-active-detail");
            $("#facebookpost1 a").addClass("h3-subnav-subnav-active");
            $("#facebookaction1").addClass("top-subnav-links-detail");
            $("#facebookaction1 a").addClass("h3-subnav");
            $("#facebooknote1").addClass("top-subnav-links-detail");
            $("#facebooknote1 a").addClass("h3-subnav");

            prevSliderDialog = "#facebooksection";
            $(".time_pick").width('200%');
            $("#facebookpost").click(); 
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
               
                $scope.entitiesdetails = data;
                if (data.image_name === undefined) {
                    $('#fbactionsave').hide();
                    $('#fbpostremove').hide();
                    $('#nopostsaveddiv').show();
                    $('#savedpostdiv').hide();
                    $('#savedposthead').hide();
                    $('#fbpostremove').hide();
                    $('#fbnotemplate').show();
                    $('#fbtemplatesaved').hide();
                } else {
                    $(".Facebook-preview-image").css("background-color","#ffffff");
                    $('#fbactionsave').hide();
                    $('#savedposthead').show();
                    $('#nopostsaveddiv').hide();
                    $('#savedpostdiv').show();
                    $('#fbpostremove').show();
                    $('#fbnotemplate').hide();
                    $('#fbtemplatesaved').show();
                }
                
                var date = new Date(schedule_time);
                $scope.entities_selected_time = date;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                $scope.schedule_desc = schedule_desc;
                $scope.facebook_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = marketingName;
                $scope.user_marketing_program_id = programId;
                $scope.days = days;
                $scope.is_today_active = is_today_active;
                

            }).error(function (data) {
                alert(requesterror);
            });
            $('#slider-button').click();            
        } 
        if (entity_type === gettwitter()) {
            $slider=2;
            sliderDialog = "#twittersection";            
            $("#twittersection").show();
            $("#twitterpostsection").show();
            $("#twtpostremove").show();
            $("#twitteractionsection").hide();
            $("#twitternotesection").hide();
            $("#twactionsave").hide();
            $("#twnotesave").hide();
            
            $("#twitteraction1").removeClass("top-subnav-link-active-detail");
            $("#twitteraction1 a").removeAttr("class");
            $("#twitternote1").removeClass("top-subnav-link-active-detail");
            $("#twitternote1 a").removeAttr("class");
            $("#twitterpost1").removeClass("top-subnav-link-active-detail");
            $("#twitterpost1 a").removeAttr("class");

            $("#twitterpost1").addClass("top-subnav-link-active-detail");
            $("#twitterpost1 a").addClass("h3-subnav-subnav-active");
            $("#twitteraction1").addClass("top-subnav-links-detail");
            $("#twitteraction1 a").addClass("h3-subnav");
            $("#twitternote1").addClass("top-subnav-links-detail");
            $("#twitternote1 a").addClass("h3-subnav");
            
            prevSliderDialog = "#twittersection";
            $(".time_pick").width('100%');
             $("#twitterpost").click();
            $http({
                method: 'GET',
                url: getHost() + '/GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    
                    $('#twtnopostsaveddiv').show();
                    $('#twtsavedpostdiv').hide();
                    $('#twtsavedposthead').hide();
                    $('#twtpostremove').hide();
                    $('#twnotemplate').show();
                    $('#twtemplatesaved').hide();            
                }
                else 
                {     
                    $(".Facebook-preview-image").css("background-color","#ffffff");
                    $('#twtsavedposthead').show();
                    $('#twtnopostsaveddiv').hide();
                    $('#twtsavedpostdiv').show();
                    $('#twtpostremove').show();
                    $('#twnotemplate').hide();
                    $('#twtemplatesaved').show();
                }
               
                var date = new Date(schedule_time);
                $scope.entities_selected_time = date;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.twitter_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = marketingName;
                $scope.user_marketing_program_id = programId;
                $scope.days = days;
                $scope.is_today_active = is_today_active;
            }).error(function (data) {
                alert(requesterror);
            });
            $('#slider-button').click();
           
        } 
        if (entity_type == getnote()) {
            $slider=2;
            sliderDialog = "#notediv";
            prevSliderDialog = "#notediv";

            $("#reminderdetailsdiv").hide();
            $("#savedreminderdiv").show();
            $("#reminderactionsave").hide();
            $("#remindernotesave").show();

            $("#reminderdetailstab").removeClass("top-subnav-link-active-detail");
            $("#reminderdetailstab a").removeClass("h3-subnav-active");
            $("#reminderdetailstab a").addClass("h3-subnav");

            $("#savedremindertab a").removeClass("h3-subnav");
            $("#savedremindertab").addClass("top-subnav-link-active-detail");
            $("#savedremindertab a").addClass("h3-subnav-active");
        
            $scope.entities_selected_time = schedule_time;
            $scope.schedule_title = schedule_title;
            $scope.schedule_id = schedule_id;
            $scope.schedule_desc = schedule_desc;
            $scope.schedule_type = entity_type;
            $scope.note_template_status = template_status;
            $scope.marketing_program_name = marketingName;
            $scope.user_marketing_program_id = programId;
            $scope.days = days;
            $('#slider-button').click();
        }
    };


    $scope.AddAction = function () {
        $("#addActionYourPlan").unbind('click'); 
        var title = $("#addactiontitle").val();
        var actiontype = $("#actiontype").val();
        var marketingProgramType=$("#marketing_program").val();
        var description = "";
        var actiondate = $("#datepicker").val();
        var actionDateTime=$("#timepicker1").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var myDate = new Date(l);
        var days=0;
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
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
                $("#addActionYourPlan").bind('click');                
                $scope.status = data;
                if (data != "") {
                    alert(actionsaved);
                    window.open(getHost() + 'user/marketing', "_self");
                    $("#addActionYourPlan").bind('click');
                }
            }).error(function (data, status) {
                $("#addActionYourPlan").bind('click');
                alert(requesterror);
            });
        }
    };
    $scope.updateActionEmail = function () {
        
        var actiontype = $("#email_schedule_type").val();
        var schedule_id = $("#email_scheduleid").val();
        var title = $("#email_edit_title").val();
        var actiondate = $("#emaildatetime").val();
        var days=$("#emaildays").val();
        if(days!="0")
        {
            actiondate="Mon Jan 01 1970";
        }
        var actionDateTime=$("#timepickeremail").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
        var description = "";
        if (validateemailaction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
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
                    alert(actionsaved);
                    $("#change").val("1");
                    $scope.getCampaigns();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    $scope.updateActionEmailNote = function (schedule_id) {
        var id=$("#emailaction_id").val();
        var a= $("#emailnotes"+id).val();
        $("#emailnotes"+id).val(a);
        var actiontype = getfacebook();
        var schedule_id = $("#email_scheduleid").val();
        var description = $("#emailnotes"+schedule_id).val();
        if (validateemailDescription()) {
            var action = {
                "schedule_id": schedule_id, "type": "updatenotesyourplan","actiontype": actiontype,
                "description": description
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
                    alert(emailnotesaved);
                    $("#change").val("1");
                }
            }).error(function (data, status) {
                alert(reqemailnotesaved);
            });

        }
    };
    
    $scope.updateActionFacebook = function () {
        
        var actiontype = $("#fb_scheduletype").val();
        var schedule_id = $("#fb_scheduleid").val();
        var title = $("#fb_action_title").val();
        var actiondate = $("#datepickerfb").val();
        var days=$("#fbdays").val();
        if(days!="0")
        {
            actiondate="Mon Jan 01 1970";
        }
        var actionDateTime=$("#timepickerfb").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
        var description = "";
        if (validatefacebookaction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
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
                    alert(actionsaved);
                    $("#change").val("1");
                    $scope.getCampaigns();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });

        }
    };
    $scope.updateActionFacebookNote = function () {
        
        var actiontype = getfacebook();
        var schedule_id = $("#fb_scheduleid").val();
        var description = $("#fbnote"+schedule_id).val();        
            var action = {
                "schedule_id": schedule_id, "type": "updatenotesyourplan","actiontype": actiontype,
                "description": description
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
                    alert(facebooknotesaved);
                    $("#change").val("1");
                    $scope.getCampaigns();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
    };
    
    $scope.updateActionTwitter = function () {
        var actiontype = $("#twitter_action_type").val();
        var schedule_id = $("#twitter_scheduleid").val();
        var title = $("#edit_twitter_title").val();        
        var actiondate = $("#datepickertwitter").val();
        var days=$("#twdays").val();
        if(days!="0")
        {
            actiondate="Mon Jan 01 1970";
        }
        var actionDateTime=$("#timepickertwitter").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
        var description = "";

        if (validatetwitteraction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
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
                    alert(actionsaved);
                    $("#change").val("1");
                    $scope.getCampaigns();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    $scope.updateActionTwitterNote = function () {

        var actiontype = $("#twitter_action_type").val();
        var schedule_id = $("#twitter_scheduleid").val();
        var description = $("#twtnote").val();
        if (validatetwitteraction()) {
            var action = {
                "schedule_id": schedule_id, "type": "updatenotesyourplan",
                "actiontype": actiontype,"description": description
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
                    alert(twitternotesaved);
                    $("#change").val("1");
                    $scope.getCampaigns();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };

    $scope.addEditRecurringAction = function(type,program_id,entity_id){
            window.open(getHost() + 'emailautomation.jsp?type='+type+'&program_id='+program_id+'&entity_id='+entity_id, "_self");
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

    $scope.deleteSchedule = function (schedules_to_delete, type, section, isRecurring) {
        var message;
        var requestBody;
        var responseMessage;
        if (type == "deleteMultiple") {
            message = multideleteconfirm;
            requestBody = {"type": "deleteSelected",
                "schedule_ids": selected_schedules_to_delete, "entity_type": "null"};
            responseMessage =multideletesuccess;
        } else if (type == "delete") {
            message = singledeleteconfirm;
            requestBody = {"type": "delete",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = singledeletesuccess;
        } else if (type == "remove") {
            message = removecnfirm;
            requestBody = {"type": "removetemplate", 
                           "schedule_ids": schedules_to_delete, "entity_type": section, 
                           "isRecurring": isRecurring};
            responseMessage = multideletesuccess;
        }

        if (confirm(message)) {
            $http({
                method: 'POST',
                url: getHost() + 'ChangeSchedule',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== "") {
                    if(section == getfacebook())
                    {
                        $("#savedpostdiv").hide();
                        $("#emptynoteheader1").hide(); 
                        $("#nopostsaveddiv").show();  
                        $("#emptynoteheader2").show(); 
                    }
                    if(section == gettwitter())
                    {
                        $("#twtsavedpostdiv").hide();
                        $("#twtnopostsaveddiv").show();                     
                    }
                    if(section == getemail())
                    {
                        $("#savedemailsdiv").hide();
                        $("#noemailsdiv").show();                     
                    }
                    alert(responseMessage);
                    $("#emailpostremove").hide();
                    $("#savedemailsdiv").hide();
                    $("#noemailsdiv").show();
                    $("#change").val("1");                    
                    window.open(getHost() + 'user/marketing', "_self");
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };

    
    $scope.updateNote = function () {
        var message;
        var schedule_id = $("#note_scheduleid").val();
        var note_title = $("#edit_note_title").val();
        var status = "no_template";
        var note_desc = "";
        var message = "Do you wan't to update the record?";
        var actiondate = $("#datepickernote").val();
        var actionDateTime=$("#timepickernote").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;    
 
        var schedule_details = {
            "type": getnote(),
            "schedule_id": schedule_id,
            "schedule_title": note_title,
            "schedule_desc": note_desc,
            "status": status,
            "schedule_time": myEpoch
        };
       
        $http({
            method: 'POST',
            url: getHost() + 'ChangeSchedule',
            headers: {'Content-Type': 'application/json'},
            data: JSON.stringify(schedule_details)
        }).success(function (data)
        {
            $scope.status = data;
            if (data != "") {
                alert(detailssaved);
                $("#change").val("1");
            }
        }).error(function (data, status) {
            alert(requesterror);
        });        
    };
    $scope.updateNoteDescription = function (schedule_id) {
        var actiontype = getnote();
        var description = $("#reminderdesc"+schedule_id).val();
        
        if (validateemailDescription()) {
            var action = {
                "schedule_id": schedule_id, "type": "updatenotes","actiontype": actiontype,
                "description": description
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
                    alert(remindernotesaved);
                    $("#change").val("1");
                }
            }).error(function (data, status) {
                alert(requesterror);
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
        var myEpoch = schedule_time;        
        
        var chooseEmailList = $("#chooseEmailList").val();
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
            url: getHost() + 'ChangeSchedule',
            headers: {'Content-Type': 'application/json'},
            data: JSON.stringify(schedule_details)
        }).success(function (data)
        {
            $scope.status = data;
            if (data != "") {
                alert(detailssaved);
                window.open(getHost() + 'user/marketing', "_self");
            }
        }).error(function (data, status) {
            alert(requesterror);
        });

    };

    $scope.updateSocialSchedule = function () {
        var social_type = $("#social_type").val();
        if (social_type == getfacebook()) {
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
                var myEpoch = schedule_time;
               
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
                    url: getHost() + 'ChangeSchedule',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify(schedule_details)
                }).success(function (data)
                {
                    $scope.status = data;
                    if (data != "") {
                        alert(detailssaved);
                        window.open(getHost() + 'user/marketing', "_self");
                    }
                }).error(function (data, status) {
                    alert(requesterror);
                });

            }
        } else if (social_type == gettwitter()) {

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
                var myEpoch = schedule_time;
                
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
                    url: getHost() + 'ChangeSchedule',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify(schedule_details)
                }).success(function (data)
                {
                    $scope.status = data;
                    if (data != "") {
                        alert(detailssaved);
                        window.open(getHost() + 'user/marketing', "_self");
                    }
                }).error(function (data, status) {
                    alert(requesterror);
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
            alert(scheduletitleerror);
            $("#facebook_schedule_title").focus();
            return false;
        }
        if (facebook_schedule_Description == "") {
            alert(scheduledescriptionerror);
            $("#facebook_schedule_Description").focus();
            return false;
        }
        if (facebook_schedule_posttext == "") {
            alert(scheduleposttexterror);
            $("#facebook_schedule_posttext").focus();
            return false;
        }
        if (facebook_schedule_url == "") {
            alert(scheduleurlerror);
            $("#facebook_schedule_url").focus();
            return false;
        }
        if (facebook_schedule_description == "") {
            alert(scheduledescriptionerror);
            $("#facebook_schedule_description").focus();
            return false;
        }
        if (actiondate == "") {
            alert(scheduledateerror);
            $("#facebook_schedule_date").focus();
            return false;
        }
        if (actionDateTime == "") {
            alert(scheduletimeerror);
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
            alert(scheduletitleerror);
            $("#twitter_schedule_title").focus();
            return false;
        }
        if (schedule_Description == "") {
            alert(scheduledescriptionerror);
            $("#twitter_schedule_Description").focus();
            return false;
        }
        if (schedule_posttext == "") {
            alert(scheduleposttexterror);
            $("#twitter_schedule_post_text").focus();
            return false;
        }
        if (actiondate == "") {
            alert(scheduledateerror);
            $("#schedule_date").focus();
            return false;
        }
        if (actionDateTime == "") {
            alert(scheduletimeerror);
            $("#schedule_time").focus();
            return false;
        }
        return true;
    };
};
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
                        $.ajax({
                           url:'ChangeSchedule',
                           method:'Post',
                           dataType: 'json',
                           contentType: 'application/json',
                           mimeType: 'application/json',
                           data:JSON.stringify(schedule_data),
                           success: function (responseText) {
                               alert(responseText);
                               if (responseText == "true"){
                                 alert("Your email has been sent successfully.");
                               }
                            }
                        });
                        document.location.href = "user/marketing";
            },
            error: function () {
                alert(error_message);
            }
        });

    } else if (checkifcreatebutton($("#email_button_send").val())) {
        document.location.href = "dashboard";
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
                        $.ajax({
                           url:'ChangeSchedule',
                           method:'Post',
                           dataType: 'json',
                           contentType: 'application/json',
                           mimeType: 'application/json',
                           data:JSON.stringify(schedule_data),
                           success: function (responseText) {
                               alert(postsuccess);
                               document.location.href = "user/marketing";
                            },
                            error: function () {
                                alert(error_message);
                            }
                        });
                        document.location.href = "user/marketing";
                    },
                    error: function () {
                        alert(error_message);
                    }
                });
            }
        });

    } else if (checkifcreatebutton($("#fb_button_post").val()) || (checkifcreatebutton($("#twitter_button_post").val()))) {
        document.location.href = "dashboard";
    }
    function checkifcreatebutton(value) {
        if (value == create_button_title) {
            return true;
        } else {
            return false;
        }
    }

}