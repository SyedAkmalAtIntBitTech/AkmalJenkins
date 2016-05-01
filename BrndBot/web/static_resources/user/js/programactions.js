
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sliderDialog = "";
var prevSliderDialog = "";
var create_button_title = "Edit";
var program_status;
var end_date="";
$(document).ready(function ()
{    
    end_date=$("#program_end_date").val();
    $("#deleteonetimeact").hide();
    $("#removeactionbutton").hide();
    $("#deleterecurringemail").hide();
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
            if (confirm("Do you want to close it .. !"))
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
var selected_schedules_to_delete="";
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
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $("#removeactionbutton").show();
            }
            if(count==0)
            {
                $("#removeactionbutton").hide();
            }
        }
        
        var selected_schedules_to_delete = "";
        var selected_schedules_to_delete_recurring = "";
        var countota=0;
        function selcheckboxonetimeact(id){
            selected_schedules_to_delete=","+selected_schedules_to_delete;
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();            
            var selected_schedule_id=id;
            if(htm.contains('class="check-icon"')){
                selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
                countota-=1;
                $("#"+id).html(content);
            }
            else
            {
                selected_schedules_to_delete = selected_schedule_id + "," + selected_schedules_to_delete;
                countota+=1;
                $("#"+id).html(content+'<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(countota>0)
            {   
                $("#deleteonetimeact").show();
                $("#onetimeactbtn").hide();
            }
            if(countota==0)
            {
                $("#onetimeactbtn").show();
                $("#deleteonetimeact").hide();
            }
        }
//        function setSelectedIds(selectedid) {
//            var checked = document.getElementById(selectedid).checked;
//            var a = $("input:checked.chckbox").length;
//            if (checked && a != 0) {
//                $("#addemlactbtn").hide();
//                $("#delemlactbtn").show();
//                var selected_schedule_id1 = $("#" + selectedid).val();
//                alert(selected_schedule_id1);
//                selected_schedules_to_delete = selected_schedule_id1 + "," + selected_schedules_to_delete;
//        //        alert(selected_schedules_to_delete);
//            }
//            else if (!checked && a == 0)
//            {
//                var selected_schedule_id = $("#" + selectedid).val();
//                selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
//        //        alert(selected_schedules_to_delete);
//                $("#delemlactbtn").hide();
//                $("#addemlactbtn").show();
//            }
//        }

            function selcheckboxrecemail(id){
            selected_schedules_to_delete_recurring=","+selected_schedules_to_delete_recurring;
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();
            
            var selected_schedule_id=id;
            if(htm.contains('class="check-icon"')){
                selected_schedules_to_delete_recurring = selected_schedules_to_delete_recurring.replace(selected_schedule_id + ",", "");
                count-=1;
                $("#"+id).html(content);
            }
            else
            {
                selected_schedules_to_delete_recurring = selected_schedule_id + "," + selected_schedules_to_delete_recurring;
                count+=1;
                $("#"+id).html(content+'<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $("#addrecemail").hide();
                $("#deleterecurringemail").show();
            }
            if(count==0)
            {
                $("#deleterecurringemail").hide();
                $("#addrecemail").show();
                
            }
        }
               
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
$(document).ready(function ()
{
    $("#closerecurringpopup").click(function(){
        $slider=2;
        sliderDialog = "#recurringPopup";
        prevSliderDialog = "#recurringPopup";
        $('#slider-button').click();
    });
$(".close").click(function(){
        var program_id=$("#program_id").val();
        var change=$("#change").val();
        if( change === "0")
        {
            closeoverlay();
            $('#slider-button').click();
            $('.bottom-cta-bar').hide();
        }
        if( change !== "0")
        {
//            $("#change").val("0");
            window.open(getHost() + 'user/marketingprogramactions?program_id='+program_id+'&past=0', "_self");
            $('.bottom-cta-bar').hide();
        }
    });
});
function validateaction() {
    var marketing_program = $("#marketing_program").val();
    var title = $("#addactiontitle").val();
    var date=$("#jumptodatepicker").val();
    var actiontime = $("#timepicker1").val();
    var actiontype = $("#actiontype").val();
    var actiondate = $("#datepicker").val();
    
    if (title === "") {
        alert(titleerror);
        $("#addactiontitle").focus();
        return false;
    }
    if (date === "") {
        alert(dateerror);
        $("#jumptodatepicker").focus();
        return false;
    }
    if (actiontime === "") {
        alert(timeerror);
        $("#timepicker1").focus();
        return false;
    }    
    if (actiontype === '0') {
        alert(actiontypeerror);
        $("#actiontype").focus();
        return false;
    } 
    if (actiondate === "") {
    }
    return true;
}
function validateemailaction() {
    var schedule_id = $("#email_scheduleid").val();
    var title = $("#email_edit_title").val();

    var description = $("#email_description").val();
    var emaildays = $("#emaildays").val();
    var actionDateTime=$("#timepickeremail").val().replace(/ /g,'');

    if (title === "") {
        alert(titleerror);
        $("#email_edit_title").focus();
        return false;
    }
    if (description === "") {
        alert(descriptionerror);
        $("#email_description").focus();
        return false;
    }
    if (emaildays === "") {
        alert(dayserror);
        $("#emaildays").focus();
        return false;
    }
    if (actionDateTime === "") {
        alert(dateerror);
        $("#timepickeremail").focus();
        return false;
    }
    return true;
}

function reSet()
{
   $("#addactiontitle").val("");
   $("#days").val("");
   $("#actiontype").val("0");
   $("#description").val("");
   $("#datepicker").val("");
   $("#timepicker1").val("");
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
        alert(titleerror);
        $("#fb_action_title").focus();
        return false;
    }
    if (actiontype === "") {
        alert(actiontypeerror);
        $("#fb_scheduletype").focus();
        return false;
    }
    if (description === "") {
        alert(descriptionerror);
        $("#fb_description").focus();
        return false;
    }
    if (actiondate === "") {
        alert(dateerror);
        $("#datepicker2").focus();
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
    var title = $("#edit_twitter_title").val();
    var days = $("#twtdays").val();
    var actionDateTime=$("#timepickertwitter").val().replace(/ /g,'');

    if (title === "") {
        alert(titleerror);
        $("#edit_twitter_title").focus();
        return false;
    }
    if (days === "") {
        alert(dayserror);
        $("#twitter_action_type").focus();
        return false;
    }
    if (actionDateTime === "") {
        alert(timeerror);
        $("#timepickertwitter").focus();
        return false;
    }
    return true;
}
    

function setSelectedRecurringIds(selectedid) {
    var checked = document.getElementById(selectedid).checked;
    var a = $("input:checked.chckbox").length;
    if (checked && a!=0) {
          $("#addemlactbtn").hide();
        $("#delemlactbtn").show();
        var selected_schedule_id = $("#" + selectedid).val();
        selected_schedules_to_delete_recurring = selected_schedule_id + "," + selected_schedules_to_delete_recurring;
        console.log(selected_schedules_to_delete_recurring);
    }
    else if(!checked && a==0)
    {
        var selected_schedule_id = $("#" + selectedid).val();
        selected_schedules_to_delete_recurring = selected_schedules_to_delete_recurring.replace(selected_schedule_id + ",", "");
        console.log(selected_schedules_to_delete_recurring);
        $("#delemlactbtn").hide();
        $("#addemlactbtn").show();
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
function programactions($scope, $http, $window){
    
    
  


// $scope.setSelectedIds = function(selectedid) {
//    var checked = document.getElementById(selectedid).checked;
//    var a = $("input:checked.chckbox").length;
//    if (checked && a!=0) {
//          $("#addemlactbtn").hide();
//        $("#delemlactbtn").show();
//        var selected_schedule_id1 = $("#" + selectedid).val();alert(selected_schedule_id1);
//        selected_schedules_to_delete = selected_schedule_id1 + "," + selected_schedules_to_delete;
////        alert(selected_schedules_to_delete);
//    }
//    else if(!checked && a==0)
//    {
//        var selected_schedule_id = $("#" + selectedid).val();
//        selected_schedules_to_delete = selected_schedules_to_delete.replace(selected_schedule_id + ",", "");
////        alert(selected_schedules_to_delete);
//        $("#delemlactbtn").hide();
//        $("#addemlactbtn").show();
//    }
//};

    
    
            $scope.master_facebook = getfacebook();
            $scope.master_twitter = gettwitter();
            $scope.master_email = getemail();
            $scope.master_note = getnote();      
        
    $scope.endMarketingProgram = function(){
        var delconf=confirm("Do you really want to End this Program?");
        if(delconf===true){
      var program_id = {"program_id": program};      
        $http({
            method: 'POST',
            url: getHost()+'/endMarketingProgram.do',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(program_id)
        }).success(function (data, status, headers, config) {
            if (data == "true"){
                  window.open(getHost() + 'user/marketingprogramlists', "_self");                
            }else {
                alert(savingrecordproblem);
            }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });            
        }else{            
        }
    };
    
    $scope.Approval = function(entity_id, template_status, entity_type){        
        var approval_type = {"entity_id": entity_id, 
                             "template_status":template_status, 
                             "entity_type": entity_type};        
        $http({
            method: 'POST',
            url: 'approveStatus.do',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(approval_type)
        }).success(function (data, status, headers, config) {
          if (data == "true"){
            alert(templatestatuschange);
            window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program+'&past=0', "_self");
          }else {
              alert(savingrecordproblem);
          }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });              
    };
    $scope.recurringApproval = function(entity_id, template_status){
        var program=$("#program_id").val();
        var approval_type = {"entity_id": entity_id, "template_status":template_status};
        
        $http({
            method: 'POST',
            url: 'approveStatusRecurring.do',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(approval_type)
        }).success(function (data, status, headers, config) {
          if (data == "true"){
            alert(templatestatuschange);
            window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program+'&past=0', "_self");
          }else {
              alert(savingrecordproblem);
          }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });              
    };    
    
    $scope.validate_program_link_details = function(){
      var myRegExp =/^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;  
      var event_date = $("#progactdatepicker").val();
      var link_url = $("#link_url").val();
      var link_name = $("#link_name").val();
      
      if (event_date == ""){
          alert(dateerror);
          $("#progactdatepicker").focus();
          return false;
      }
      if (link_name == ""){
          alert(linknameerror);
          $("#link_name").focus();
          return false;
      }
      if((link_url == "") || (!myRegExp.test(link_url))){
          alert(linkurlerror);
          $("#link_url").focus();
          $("#link_url").val('http://');
          return false;
      }
      return true;
    };
    
    $scope.updateUserProgram = function(){
        var program=$("#program_id").val();
        if ($scope.validate_program_link_details()){          
        var event_date = $("#progactdatepicker").val();
        var event_date_epoch = Date.parse(event_date);
        var link_url = $("#link_url").val();
        var link_name = $("#link_name").val();
        var program_name = $("#program_name").val();

        var program_details = {"program_id": program, "date_of_event": event_date_epoch,
                          "link_url": link_url, "link_name": link_name, "program_name":program_name};
        $http({
            method: 'POST',
            url: getHost()+'/updateUserProgram.do',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(program_details)
        }).success(function (data, status, headers, config) {
          if (data == "true"){
            alert(programdetailssaved);
            window.open(getHost() + 'user/marketingprogramactions?program_id='+program+'&past=0'+'&program_date='+event_date, "_self");
          }else {
              alert(savingrecordproblem);
          }
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
        });                
      }     
    };
    
    $scope.getProgramActions = function(){
        $scope.showfieldstab = function (){};
        $scope.showactionstab = function (){
            $("#saveprogram").hide();
//            $("#removeactionbutton").show();
            $("#actionstab").show();
            $("#fieldstab").hide();
            $("#savefieldsbutton").hide();
            $("#fieldsli").removeClass("top-subnav-link-active");
            $("#fields").removeClass("h3-active-subnav");
            $("#actionsli").removeClass("top-subnav-links").addClass("top-subnav-link-active");
            $("#actions").removeClass("h3").addClass("h3-active-subnav");
            $("#fieldsli").addClass("top-subnav-links");
            $("#fields").addClass("h3");
        };
        $http({
            method: 'GET',
            url: getHost()+'/alluserMarketingProgramForDisplay.do?program_id='+program
        }).success(function (data, status, headers, config){
//            alert(JSON.stringify(data.emailautomation));
            $("#progname").show();
            document.getElementById("instancehidden").style.display="block";
            if(JSON.stringify(data.emailautomation)==='[]'){
            $("#noemailautomation").empty().append('No Recurring Emails');
            }
            if(JSON.stringify(data.programactions)==='[]'){
            $("#noota").empty().append('No One Time Actions');
            }
            $scope.programs = data;
            $scope.actionType="Email";
            program_status = data.programdetails.program_status;
            if(program_status === "Closed"){
                $(".endmrkprogbtn").hide();
                $(".addemlautombtn").hide();
                $(".otaddactbtn").hide(); 
                $("#checkBox").hide();
            }
            $scope.program_id = program;
            if (data === error) {
                alert(data);
            }
            $(".row").css("display","block");
        }).error(function (data, status, headers, config) {
            alert(nodataerror);
            $(".row").css("display","block");
        });
        $scope.calltoeditrecurring = function(progid,scheduleid){
            window.open(getHost() + 'user/emailautomation?type=edit&program_id='+progid+"&entity_id="+scheduleid, "_self");
        };
    };
    $scope.checkProgramStatus= function (){
       var status;
       if(program_status === "Closed"){
           status=true;
           $('.detail-overlay-content').find('input, textarea, button, select').attr('disabled','disabled');
           $('#facebooksection').find('input, textarea, button, select').attr('disabled','disabled');
           $('.savebutton').hide();
           $('.removebutton').hide();
           $('.delete-button-detail').hide();
           $('.edit-button-detail').hide();
           $('.remove-button-detail').hide();
           $('.approve-button-detail').hide();
       }
       else{
           status=false;
       }
      return  status;
   }; 
    $scope.addRecurringAction = function(program_id){
        window.open(getHost() + 'emailautomation.jsp?type=add&program_id='+program_id+'&entity_id=0', "_self");
    };    
    $scope.setEntityId = function(entity_list_Id, days){
        window.open(getHost() + 'emailautomation.jsp?entitylistid='+entity_list_Id+'&days='+days, "_self");
    };
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
            $scope.today_date = moment(new Date()).format('YYYY-MM-DD');
            $scope.tomorrow_date = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
            $scope.entitySet = data.entitydata;
            $scope.nodata = data.noactionsmessage;
            $("#default").css("display", "block");
        }).error(function (data) {
            alert(requesterror);
        });       
    };
    var millisToUTCDate = function (millis) {
        return toUTCDate(new Date(millis));
    };    
    $scope.addEditRecurringAction = function(type,program_id,entity_id){
        var program_end_date=$("#program_end_date").val();
        window.open(getHost() + 'user/emailautomation?type='+type+'&program_id='+program_id+'&entity_id='+entity_id+'&program_date='+program_end_date, "_self");
    };    
    $scope.createPost = function(){
        window.open(getHost() + 'dashboard.jsp', "_self");
    };
    $scope.editScheduleDetails = function (schedule_id, schedule_time, entity_type, schedule_title, schedule_desc,marketingName,days) {
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
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                $scope.marketing_program_name = marketingName;
                $scope.days = days;
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
                    $('#fbeditremove').hide();
                    $('#fbremovedtemplate2').show();
                    $('#fbpreviewdecond2').hide();
                    $("#preview_facebook").hide();
                    $("#edit_facebook").show();
                    $('#fbnotemplate1').show();
                    $('#fbtemplatesaved1').hide();
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
                }
                $scope.schedule_id = schedule_id;
                var date = new Date(schedule_time);
                $scope.entities_selected_time = schedule_time;
                $scope.schedule_title = schedule_title;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
                $scope.days = days;
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
                console.log("twitter description" + schedule_desc);
                console.log("twitter time" + schedule_time);
                $scope.schedule_desc = schedule_desc;
                $scope.schedule_type = entity_type;
                $scope.days = days;
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
                console.log(schedule_desc);
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
                console.log(schedule_desc);
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
                console.log(schedule_desc);
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
        $(".timepicker_wrap").css("width","57%");
        if(reSet()){ }
        $slider=1;
        $edit=1; 
        $http({
                method: 'GET',
                url: getHost() + 'getMarketingProgramName.do'
            }).success(function (data, status) {
                $scope.marketprogram = data.userProgramData;
                if (data.userProgramData.user_program_id == "") {                    
                } else {
                    $("dvPriorityDialog").show();
                }
            }).error(function (data) {
                alert(requesterror);
            });
    };
    
    $scope.getRecurringMailDetails = function (schedule_id, template_status, till_date, schedule_time, entity_type, schedule_title, schedule_desc, date_status,days) {
            $slider=2;
            sliderDialog = "#recurringPopup";
            prevSliderDialog = "#recurringPopup";
            var program_name=$("#program_name2").val();
            var program_id=$("#program_id").val();
            $http({
                method: 'GET',
                url: getHost() + '/GetScheduledEmailDetail.do?schedule_id=' + schedule_id
            }).success(function (data) {
                var recurringDetails=JSON.parse(data.d.details);
                $scope.entitiesdetails = recurringDetails;
                if (data.body == undefined) {
                      $("#recurringremovediv").hide();
                      $("#savedemailsdiv").hide();
                      $("#noemailsdiv").show();
                } else {
                      $("#recurringremovediv").show();
                      $("#savedemailsdiv").show();
                      $("#noemailsdiv").hide();
                }
                $("#recurringactiondiv").hide();
                $("#recurringnotediv").hide();
                $("#recurringtemplatediv").show();
                $("#recurringaction").removeClass("top-subnav-link-active-detail");
                $("#recurringaction a").removeAttr("class");
                $("#recurringnote").removeClass("top-subnav-link-active-detail");
                $("#recurringnote a").removeAttr("class");
                $("#recurringtemplate").removeClass("top-subnav-link-active-detail");
                $("#recurringtemplate a").removeAttr("class");

                $("#recurringtemplate").addClass("top-subnav-link-active-detail");
                $("#recurringtemplate a").addClass("h3-subnav-subnav-active");
                $("#recurringaction").addClass("top-subnav-links-detail");
                $("#recurringaction a").addClass("h3-subnav");
                $("#recurringnote").addClass("top-subnav-links-detail");
                $("#recurringnote a").addClass("h3-subnav");
                $scope.entities_subject = recurringDetails.subject;
                $scope.entities_from_name = recurringDetails.from_name;
                $scope.entities_reply_to_email_address = recurringDetails.reply_to_email_address;
                $scope.entities_list_name = recurringDetails.email_list_name;
                $scope.entities_selected_time = schedule_time;
                $scope.entities_till_date = till_date;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                console.log(schedule_desc);
                $scope.schedule_desc = schedule_desc;
                $scope.recurring_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.recurring_date_status=date_status;
                $scope.program_name=program_name;
                $scope.program_id=program_id;
                $scope.days=days;
                $('#recurringemailcontentiframe').contents().find('html').html(data.body);
            }).error(function (data) {
                alert(requesterror);
            });
            $('#slider-button').click();
    };   
 $scope.getScheduleDetails = function (schedule_id, template_status, 
                                          schedule_date, entity_type, 
                                          schedule_title, schedule_desc, 
                                          post_time, action_status,
                                          days,program_name,
                                          program_id) {           
        if (entity_type === getemail()) {
            $slider=2;
            sliderDialog = "#emailsection";            
            $("#emailsection").show();
            $("#emailactionsection").show();
            $("#emailactionsave").show();
            $("#emailpostsection").hide();
            $("#emailnotesection").hide();
            $("#emailpostremove").hide();
            $("#emailnotesave").hide();
            
            $("#emailaction").removeClass("top-subnav-link-active-detail");
            $("#emailaction a").removeAttr("class");
            $("#emailnote").removeClass("top-subnav-link-active-detail");
            $("#emailnote a").removeAttr("class");
            $("#emailpost").removeClass("top-subnav-link-active-detail");
            $("#emailpost a").removeAttr("class");

            $("#emailaction").addClass("top-subnav-link-active-detail");
            $("#emailaction a").addClass("h3-subnav-subnav-active");
            $("#emailpost").addClass("top-subnav-links-detail");
            $("#emailpost a").addClass("h3-subnav");
            $("#emailnote").addClass("top-subnav-links-detail");
            $("#emailnote a").addClass("h3-subnav");
            prevSliderDialog = "#emailsection";
            $("#emailpost").click();            
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $(".timepicker_wrap").css("width","27%");
                $(".arrow_top").hide();
                $scope.entitiesdetails = data;
                if (data.body == undefined) {
                } else {
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
                $("#emailcontentiframe").contents().find('html').html(data.body);
                $scope.entities_selected_time = post_time;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                $scope.email_template_status = template_status;
                $scope.schedule_desc = schedule_desc;
                $scope.email_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.marketing_program_name = program_name;
                $scope.user_marketing_program_id = program_id;
                $scope.days = days;
                $scope.is_today_active = is_today_active;alert();
            }).error(function (data) {
                alert(requesterror);
            });
            $("#slider-button").click();
        } 
        else if (entity_type == getfacebook()) {
            $slider=2;
            sliderDialog = "#facebooksection";
            prevSliderDialog = "#facebooksection";
            $("#facebooksection").show();
            $("#facebookpostsection").show();
            $("#facebookactionsection").hide();
            $("#facebooknotesection").hide();
            $("#fbactionsave").hide();
            $("#fbnotesave").hide();

            $("#facebookaction").removeClass("top-subnav-link-active-detail");
            $("#facebookaction a").removeAttr("class");
            $("#facebooknote").removeClass("top-subnav-link-active-detail");
            $("#facebooknote a").removeAttr("class");
            $("#facebookpost").removeClass("top-subnav-link-active-detail");
            $("#facebookpost a").removeAttr("class");

            $("#facebookpost").addClass("top-subnav-link-active-detail");
            $("#facebookpost a").addClass("h3-subnav-subnav-active");
            $("#facebookaction").addClass("top-subnav-links-detail");
            $("#facebookaction a").addClass("h3-subnav");
            $("#facebooknote").addClass("top-subnav-links-detail");
            $("#facebooknote a").addClass("h3-subnav");
            
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $('#nopostsaveddiv').show();
                    $('#savedpostdiv').hide();
                    $('#fbnotemplate').show();
                    $('#fbtemplatesaved').hide();
                } else {
                     $('#nopostsaveddiv').hide();
                    $('#savedpostdiv').show();
                    $('#fbnotemplate').hide();
                    $('#fbtemplatesaved').show();
                }                
                $scope.entities_selected_date = schedule_date;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                $scope.schedule_desc = schedule_desc;
                $scope.facebook_action_status=action_status;
                $scope.facebook_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.post_time=post_time;
                $scope.days=days;
            }).error(function (data) {
                alert(requesterror);
            });
            $('#slider-button').click();
        }
        else if (entity_type == gettwitter()) {
            $("#twitterpostsection").show();
            if( $('#twtsavedpostdiv').css('display') === 'block' ){
            $("#twtpostremove").show();
            }
            $("#twitteractionsection").hide();
            $("#twitternotesection").hide();
            $("#twactionsave").hide();
            $("#twnotesave").hide();

            $("#twitteraction").removeClass("top-subnav-link-active-detail");
            $("#twitteraction a").removeAttr("class");
            $("#twitternote").removeClass("top-subnav-link-active-detail");
            $("#twitternote a").removeAttr("class");
            $("#twitterpost").removeClass("top-subnav-link-active-detail");
            $("#twitterpost a").removeAttr("class");

            $("#twitterpost").addClass("top-subnav-link-active-detail");
            $("#twitterpost a").addClass("h3-subnav-subnav-active");
            $("#twitteraction").addClass("top-subnav-links-detail");
            $("#twitteraction a").addClass("h3-subnav");
            $("#twitternote").addClass("top-subnav-links-detail");
            $("#twitternote a").addClass("h3-subnav");
            $slider=2;
            sliderDialog = "#twittersection";
            prevSliderDialog = "#twittersection";
            $("#twittersection").show();
            $(".timepicker_wrap").css("width","27%");            
            $http({
                method: 'GET',
                url: getHost() + 'GetScheduledSocialPostDetail?schedule_id=' + schedule_id
            }).success(function (data) {
                $scope.entitiesdetails = data;
                if (data.image_name == undefined) {
                    $('#twtsavedpostdiv').hide();
                    $('#twtnopostsaveddiv').show();
                    $('#twtsavedposthead').hide();
                    $('#twtpostremove').hide();                    
                    $('#twnotemplate').show();
                    $('#twtemplatesaved').hide();
                } else {
                    $('#twtsavedpostdiv').show();
                    $('#twtnopostsaveddiv').hide();
                    $('#twtsavedposthead').show();
                    $('#twnotemplate').hide();
                    $('#twtemplatesaved').show();
                }
                $scope.entities_selected_date = schedule_date;
                $scope.schedule_title = schedule_title;
                $scope.schedule_id = schedule_id;
                $scope.twitter_action_status=action_status;
                $scope.schedule_desc = schedule_desc;
                $scope.twitter_template_status = template_status;
                $scope.schedule_type = entity_type;
                $scope.post_time=post_time;
                $scope.days=days;
            }).error(function (data) {
                alert(requesterror);
            });
            $('#slider-button').click();
        } 
        else if (entity_type == getnote()) {
            $slider=1;
            sliderDialog = "#previewNote";
            $('#slider-button').click();
            prevSliderDialog = "#previewNote";
            $("#noteprev").show();
            $("#noteedit").hide();
            $scope.entities_selected_date = schedule_date;
            $scope.schedule_title = schedule_title;
            $scope.schedule_id = schedule_id;
            $scope.schedule_desc = schedule_desc;
            $scope.schedule_type = entity_type;
            $scope.note_template_status = template_status;
            $scope.post_time=post_time;
        }
    };
    
    $scope.AddAction = function () {
        var program=$("#program_id").val();
        var program_end_date=$("#program_end_date").val();
        var title = $("#addactiontitle").val();
        var actiontype = $("#actiontype").val();
        var marketingProgramType=$("#marketing_program").val();
        var description = $("#description").val();
        var actiondate = "1970/01/01";
        var days=$("#days").val();          
        var actionDateTime=$("#timepicker1").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var myDate = new Date(l); // Your timezone!        
        var schedule_time = Date.parse(l);
        console.log("Epoch: " + schedule_time);        
        var myEpoch = schedule_time;        
        console.log("New Epoch: " + myEpoch);
        if (validateaction()) {
            var action = {"title": title, "actiontype": actiontype,
                "marketingType":marketingProgramType,
                "type": "save",
                "description": description,
                "action_date": myEpoch, "days":days
            };
            $http({
                method: 'POST',
                url: getHost() + '/AddAction.do',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(action)
            }).success(function (data)
            {
//                alert(JSON.stringify(data.d));
                $scope.status = data;
                alert("Action saved successfully.");
                window.open(getHost() + 'user/marketingprogramactions?program_id=' + program + '&past=0&program_date=' + program_end_date, "_self");

            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    function validaterecurringemailDescription(schedule_id)
    {
        var description = $("#email_description"+schedule_id).val();
         if (description === "") {
            alert("Please Enter The Description");
            $("#email_description"+schedule_id).val().focus();
            return false;
        }
        return true;
    }    
    $scope.updateActionEmail = function () {
        var program=$("#program_id").val();
        if (validateemailaction()) {
            var actiontype = getemail();
            var schedule_id = $("#email_scheduleid").val();
            var title = $("#email_edit_title").val();
            var actiondate = "1970/01/01";//$("#emaildatetime").val();
            var days=$("#emaildays").val();
            var actionDateTime=$("#timepickeremail").val().replace(/ /g,'');
            //var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
            var l="Sun Nov 01 1970 "+actionDateTime.toLocaleString();
            var myDate = new Date(l); // Your timezone!
            var schedule_time = Date.parse(l);
            console.log("Epoch: " + schedule_time);
            var myEpoch = schedule_time;
            var description = $("#emaildescription"+schedule_id).html();
            console.log(actiontype + "," + schedule_id + "," + title + "," + description);
            console.log("New Epoch: " + myEpoch);            
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
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };    
    $scope.updateActionEmailNote = function (schedule_id) {
        var actiontype = getemail();
        var description = $("#email_description"+schedule_id).val();
        if (validaterecurringemailDescription(schedule_id)) {
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
                    alert(emailnotesaved);
                    $("#change").val("1");
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    
    $scope.updateActionFacebook = function () {
        var program=$("#program_id").val();
        if (validatefacebookaction()) {            
            var actiontype = getfacebook();
            console.log("action type" + actiontype);
            var schedule_id = $("#fb_scheduleid").val();
            var title = $("#fb_action_title").val();        
            var actiondate = "1970/01/01";//$("#datepickerfb").val();
            var days=$("#fbdays").val();
            var actionDateTime=$("#timepickerfb").val().replace(/ /g,'');
            var l="Sun Jan 01 1970 "+actionDateTime.toLocaleString();
            var myDate = new Date(l); // Your timezone!
            var schedule_time = Date.parse(l);
            console.log("Epoch: " + schedule_time);
            var myEpoch = schedule_time;
            var description = $("#fb_description"+schedule_id).html();
            console.log(actiontype + "," + schedule_id + "," + title + "," + description);
            console.log("New Epoch: " + myEpoch);            
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
                }
            }).error(function (data, status) {
                alert(requesterror);
            });

        }
    };
    $scope.updateActionFacebookNote = function (schedule_id) {        
        var actiontype = getfacebook();
        var description = $("#fb_description"+schedule_id).val();
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
                    alert(facebooknotesaved);
                    $("#change").val("1");
                    $scope.getCampaigns();
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
    };
    
    $scope.updateActionTwitter = function () {
        var program=$("#program_id").val();
        var description = $("#twtnotetext").html();
        if (validatetwitteraction()) {
            var title = $("#edit_twitter_title").val(); 
            var actiontype = gettwitter();
            var days=$("#twtdays").val();
            var schedule_id = $("#twitter_scheduleid").val();                   
            var actiondate = "1970/01/01";//$("#datepickertwitter").val();            
            var actionDateTime=$("#timepickertwitter").val().replace(/ /g,'');
            var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
            l="Sun Jan 01 1970 "+actionDateTime.toLocaleString();
            var myDate = new Date(l); // Your timezone!
            var schedule_time = Date.parse(l);
            console.log("Epoch: " + schedule_time);
            var myEpoch = schedule_time;            
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
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    $scope.updateActionTwitterNote = function (schedule_id) {
        var actiontype = gettwitter();
        var description = $("#twitternote"+schedule_id).val();
        if (validatetwitteraction()) {
            var action = {
                "schedule_id": schedule_id, "type": "updatenotes",
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
    $scope.deleteAutomationSchedule = function (schedules_to_delete, type, section, isRecurring){
        var program=$("#program_id").val();
        var message;
        var requestBody;
        var responseMessage;
        if (type == "deleteMultiple") {
            message = multideleteconfirm;
            requestBody = {"type": "deleteSelected",
                           "schedule_ids": selected_schedules_to_delete_recurring, "entity_type": "null"};
            responseMessage = multideletesuccess;
        } else if (type == "delete") {
            message = singledeleteconfirm;
            requestBody = {"type": "delete",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = singledeletesuccess;
        } else if (type == "removetemplate") {
            message = removecnfirm;
            requestBody = {"type": "removetemplate",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = actiondelete;
        }
        if (confirm(message)) {
            $http({
                method: 'POST',
                url: getHost() + 'ChangeSchedule.do',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {   
                $scope.status = data;
                if (data !== "") {
                    if(section == getfacebook())
                    {
                        $("#fbpreviewdecond").hide();
                        $("#fbremovedtemplate").show();                        
                    }
                    if(section == gettwitter())
                    {
                        $("#twpreviewdecond").hide();
                        $("#twremovedtemplate").show();                     
                    }
                    if(section == getemail())
                    {
                        $("#mailpreviewdecond").hide();
                        $("#mailremovedtemplate").show();                     
                    }     
                    alert("Recurring Email deleted successfully.");
                    window.open(getHost() + 'user/marketingprogramactions?program_id='+program+'&past=0', "_self");
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
        
    $scope.deleteSchedule = function (schedules_to_delete, type, section, isRecurring){
        var data="";
        var program_end_date=$("#program_end_date").val();
        if(section==='recurring')
        {
            data=selected_schedules_to_delete_recurring;
        }
        if(section==='nonrecurring')
        {
            data=selected_schedules_to_delete;
        }        
        var program=$("#program_id").val();
        var message;
        var requestBody;
        var responseMessage;
        var program=$("#program_id").val();
        if (type == "deleteMultiple") {
            if(schedules_to_delete=="0"){
                message = multiemaildeleteconfirm;
            }
            if(schedules_to_delete=="1"){
                message = multideleteconfirm;
            }
            requestBody = {"type": "deleteSelected",
                           "schedule_ids": data, "entity_type": "null"};
            if(schedules_to_delete=="0"){
             responseMessage = emaildeletesuccess;
            }
            if(schedules_to_delete=="1"){
               responseMessage = actiondelete;
            }
        } else if (type == "delete") {
            if(isRecurring === "true"){
                message = recurringdeleteconfirm;
                requestBody = {"type": "delete",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = recurringdeletesuccess;
            }
            message = multideleteconfirm;
            requestBody = {"type": "delete",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = actiondelete;
        } else if (type == "remove") {
            message = removecnfirm;
            requestBody = {"type": "removetemplate",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = actiondelete;
        }
        if (confirm(message)) {
            $http({
                method: 'POST',
                url: getHost() + '/ChangeSchedule.do',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {  
                $scope.status = data;
                if (data !== "") {
                    if(section == getfacebook())
                    {
                        $("#fbpreviewdecond").hide();
                        $("#fbremovedtemplate").show();                        
                    }
                    if(section == gettwitter())
                    {
                        $("#twpreviewdecond").hide();
                        $("#twremovedtemplate").show();                     
                    }
                    if(section == getemail())
                    {
                        $("#mailpreviewdecond").hide();
                        $("#mailremovedtemplate").show();                     
                    }
                    alert(responseMessage);
                    window.open(getHost() + 'user/marketingprogramactions?program_id='+program+'&past=0&program_date='+program_end_date, "_self");
                }
            }).error(function (data, status) {
                alert(requesterror);
            });
        }
    };
    
    $scope.updateNote = function () {
        var program=$("#program_id").val();
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
            "type": "Reminder",
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
                    alert("Details saved successfully.");
                    $("#change").val("1");
                }
            }).error(function (data, status) {
                alert(requesterror);
            });            
        }
    };
    
    $scope.updateEmailSchedule = function () {
        var program=$("#program_id").val();
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
                alert(detailssaved);
                window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program+'&past=0', "_self");
            }
        }).error(function (data, status) {
            alert(requesterror);
        });
    };
    $scope.updateSocialSchedule = function () {
        var program=$("#program_id").val();
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
                        alert(detailssaved);
                        window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program+'&past=0', "_self");
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
                        alert(detailssaved);
                        window.open(getHost() + 'marketingprogramactions.jsp?program_id='+program+'&past=0', "_self");
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
            alert(scheduledateerror);
            $("#facebook_schedule_Description").focus();
            return false;
        }
        if (facebook_schedule_posttext == "") {
            alert(posttexterror);
            $("#facebook_schedule_posttext").focus();
            return false;
        }
        if (facebook_schedule_url == "") {
            alert(scheduleurlerror);
            $("#facebook_schedule_url").focus();
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
            alert(posttexterror);
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
    $scope.ShowAddAction = function()
    { 
        $("#fade").show();
        $("#addAction").show();
    };
            };     
        function overlay(){
                    document.getElementById('light').style.display = 'block';
                        document.getElementById('fade').style.display = 'block';
                        document.getElementById('slider-button').style.display = 'block';
                        document.body.style.overflow = 'hidden';
                }
        function closeoverlay(){
                document.getElementById('light').style.display = 'none';
                document.getElementById('fade').style.display = 'none';
                document.body.style.overflow = 'scroll';
        }
 