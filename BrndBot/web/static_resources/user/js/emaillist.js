/*
 * Description : emaillist.js
 * Date        : 8 Dec 2015    12:49:25 PM
 * Author      : Satyajit Roy At IntBit Technologied
 */
 $(".cross").hide();
 $(".menu").hide();
 $("#emaillist").hide();
 $("#addAction").hide();
 var count=0;
 var selected_emaildrafts_to_delete = "";
        function selemldrftcheckbox(id){ 
            content='<input type="checkbox" id="'+'entityid'+id+'" hidden="">';
            var htm=$("#"+id).html();
            var selected_schedule_id=id;
            if(htm.contains('class="check-icon"')){
                selected_emaildrafts_to_delete = selected_emaildrafts_to_delete.replace(selected_schedule_id + ",", "");
                count-=1;
                $("#"+id).html(content);
            }
            else
            {
                selected_emaildrafts_to_delete = selected_schedule_id + "," + selected_emaildrafts_to_delete;
                count+=1;
                $("#"+id).html(content+'<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {   
                $("#deleteEmaildraft").show();
            }
            if(count==0)
            {
                $("#deleteEmaildraft").hide();
            }
        }
    var count=0;var selectedemailids = "";

        function selemlcheckbox(id){ 
            var content='<input type="checkbox" name="deleteid" value="'+id+'" hidden="" id="deleteid"'+id+'" checked>';
            var content1='<input type="checkbox" name="deleteid" value="'+id+'" hidden="" id="deleteid"'+id+'">';
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                count-=1;
                $("#"+id).html(content1);
                selectedemailids = selectedemailids.replace(id+",","");
            }else{
                selectedemailids = id + "," + selectedemailids;
                count+=1;
                $("#"+id).html(content+'<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {
                 $("#removeselactions").show();
                 $("#delcontact").show();
                 $(".gray-button").show();
                 $("#addcontact").hide();
                 $("#addcontacts").hide();
            }
            if(count==0)
            {
                $("#removeselactions").hide();
                $("#delcontact").hide();
                 $(".gray-button").hide();
                 $("#addcontact").show();
                 $("#addcontacts").show();
            }
        }
        function unsel(){
                    count=0;
                   var htm=$(".selection-icon-selected").html();  
                   if(htm.contains('class="check-icon"')){
                         $(".selection-icon-selected").html('');
                   }
                    $(".selection-icon-selected").addClass('selection-icon'); 
                    $('.selection-icon').removeClass('selection-icon-selected');
                    if(count==0)
            {
                $(".delete-button").hide();
                 $(".gray-button").hide();
                 $("#addcontact").show();
                 $("#addcontacts").show();
            }
        }
         function funshowdropdown(){
           $(".dropdown-hub").show();           
        }        

 $("#fileUpload").change(function () {
    loadImageFile();
    $('.fileUpload').wrap('<form>').closest('form').get(0).reset();
    $('.fileUpload').unwrap();
});
            var update = "";
            var selectedlistname = "";
            var selectedemailids = "";
            
            function selectEmailId(id){                
                $("#selectAll").attr('checked',false);
                var selectedid  = document.getElementById(id).checked;                
                if (selectedid){
                    emailid = $("#"+id).val();
                    selectedemailids = emailid + "," + selectedemailids;
                }else{
                    emailid = $("#"+id).val();
                    selectedemailids = selectedemailids.replace(emailid+",","");
                }                
            }          

            $(document).ready(function () {                
                $("#savesetbtn").hide();
                $("#emaisetdiv").hide();
                $("#historydiv").hide();
                $("#emailhistorydiv").hide();
                $("#removeselactions").hide();
                $("#emaildraftsdiv").hide();
                $(".delete-button").hide();
                $(".gray-button").hide();
                $("#list_name").focus(function (){$("#lstnm").css("left","-125px").css("font-size","13px").css("color","#999");});
                $("#list_name").focusout(function (){var emllist=$("#list_name").val();if(emllist===""){$("#lstnm").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#lstnm").css("left","-123px");}});
                $("#default_from_name").focus(function (){$("#deffrmnm").css("left","-157px").css("font-size","13px").css("color","#999");});
                $("#default_from_name").focusout(function (){var emllist=$("#default_from_name").val();if(emllist===""){$("#deffrmnm").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#deffrmnm").css("left","-157px");}});
                $("#list_description").focus(function (){$("#lstdesc").css("left","-145px").css("font-size","13px").css("color","#999");});
                $("#list_description").focusout(function (){var emllist=$("#list_description").val();if(emllist===""){$("#lstdesc").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#lstdesc").css("left","-145px");}});
                
                $("#emlhistab").click(function (){
                    $("#savesetbtn").hide();
                    $("#emaisetdiv").hide();
                    $("#deleteEmaildraft").hide();
                    $("#emaillistsdiv").hide();
                    $("#emaildraftsdiv").hide();
                    $("#addemlstbtn").hide();
                    $("#footerdiv").hide();
                    $("#emailhistorydiv").show();
                    $("#emlhistab").addClass("top-subnav-link-active");
                    $("#emlhistab a").addClass("h3-active-subnav");
                    $("#emllistab").removeClass("top-subnav-link-active");
                    $("#emllistab a").removeClass("h3-active-subnav");
                    $("#emldrftab").removeClass("top-subnav-link-active");
                    $("#emldrftab a").removeClass("h3-active-subnav");
                    $("#emllistab").addClass("top-subnav-links");
                    $("#emllistab a").addClass("h3"); 
                    $("#emldrftab").addClass("top-subnav-links");
                    $("#emldrftab a").addClass("h3"); 
                    $("#emlsettab").removeClass("top-subnav-link-active");
                    $("#emlsettab a").removeClass("h3-active-subnav");
                    $("#emlsettab").addClass("top-subnav-links");
                    $("#emlsettab a").addClass("h3");
                    $("#footertab").removeClass("top-subnav-link-active").addClass("top-subnav-links");
                    $("#footertab a").removeClass("h3-active-subnav").addClass("h3");
                });
                 $("#emlsettab").click(function (){
                    $("#savesetbtn").show();
                    $("#emailhistorydiv").hide();
                    $("#deleteEmaildraft").hide();
                    $("#emaillistsdiv").hide();
                    $("#footerdiv").hide();
                    $("#emaisetdiv").show();
                    $("#emaildraftsdiv").hide();
                    $("#addemlstbtn").hide();
                    $("#emlhistab").removeClass("top-subnav-link-active");
                    $("#emlhistab a").removeClass("h3-active-subnav");
                    $("#emlhistab").addClass("top-subnav-links");
                    $("#emlhistab a").addClass("h3"); 
                    $("#emlsettab").addClass("top-subnav-link-active");
                    $("#emlsettab a").addClass("h3-active-subnav");
                    $("#emldrftab").removeClass("top-subnav-link-active");
                    $("#emldrftab a").removeClass("h3-active-subnav");
                    $("#emldrftab").addClass("top-subnav-links");
                    $("#emldrftab a").addClass("h3"); 
                    $("#emllistab").removeClass("top-subnav-link-active");
                    $("#emllistab a").removeClass("h3-active-subnav");
                    $("#emllistab").addClass("top-subnav-links");
                    $("#emllistab a").addClass("h3"); 
                    $("#footertab").removeClass("top-subnav-link-active").addClass("top-subnav-links");
                    $("#footertab a").removeClass("h3-active-subnav").addClass("h3");
                });
                   
                var mouse_is_inside = false;
                $('#emaildropdown').hover(function(){ 
                    mouse_is_inside=true; 
                }, function(){ 
                    mouse_is_inside=false; 
                });

                $("body").mouseover(function(){ 
                    if(! mouse_is_inside){ $('#emaildropdown').hide();}
                });
                                
                $("#emldrftab").click(function (){
                    $("#savesetbtn").hide();
                    $("#emaisetdiv").hide();
                    $("#emailhistorydiv").hide();
                    $("#emaillistsdiv").hide();
                    $("#footerdiv").hide();
                    $("#emaildraftsdiv").show();
                    $("#addemlstbtn").hide();
                    $("#emlhistab").removeClass("top-subnav-link-active");
                    $("#emlhistab a").removeClass("h3-active-subnav");
                    $("#emlhistab").addClass("top-subnav-links");
                    $("#emlhistab a").addClass("h3"); 
                    $("#emldrftab").addClass("top-subnav-link-active");
                    $("#emldrftab a").addClass("h3-active-subnav");
                    $("#emllistab").removeClass("top-subnav-link-active");
                    $("#emllistab a").removeClass("h3-active-subnav");
                    $("#emllistab").addClass("top-subnav-links");
                    $("#emllistab a").addClass("h3"); 
                    $("#emlsettab").removeClass("top-subnav-link-active");
                    $("#emlsettab a").removeClass("h3-active-subnav");
                    $("#emlsettab").addClass("top-subnav-links");
                    $("#emlsettab a").addClass("h3"); 
                    $("#footertab").removeClass("top-subnav-link-active").addClass("top-subnav-links");
                    $("#footertab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#removeselactions").hide();
                    getAllDrafts();
                });
                
                $("#emllistab").click(function (){
                    $("#savesetbtn").hide();
                    $("#emaisetdiv").hide();
                    $("#emailhistorydiv").hide();
                    $("#deleteEmaildraft").hide();
                    $("#footerdiv").hide();
                    $("#emaillistsdiv").show();
                    $("#emaildraftsdiv").hide();
                    $("#addemlstbtn").show();
                    $("#emlhistab").removeClass("top-subnav-link-active");
                    $("#emlhistab a").removeClass("h3-active-subnav");
                    $("#emlhistab").addClass("top-subnav-links");
                    $("#emlhistab a").addClass("h3"); 
                    $("#emllistab").addClass("top-subnav-link-active");
                    $("#emllistab a").addClass("h3-active-subnav");
                    $("#emldrftab").removeClass("top-subnav-link-active");
                    $("#emldrftab a").removeClass("h3-active-subnav");
                    $("#emldrftab").addClass("top-subnav-links");
                    $("#emldrftab a").addClass("h3"); 
                    $("#emlsettab").removeClass("top-subnav-link-active");
                    $("#emlsettab a").removeClass("h3-active-subnav");
                    $("#emlsettab").addClass("top-subnav-links");
                    $("#emlsettab a").addClass("h3");
                    $("#footertab").removeClass("top-subnav-link-active").addClass("top-subnav-links");
                    $("#footertab a").removeClass("h3-active-subnav").addClass("h3");
                });
               $("#footertab").click(function (){
                    $("#savesetbtn").hide();
                    $("#emaisetdiv").hide();
                    $("#deleteEmaildraft").hide();
                    $("#emaillistsdiv").hide();
                    $("#emaildraftsdiv").hide();
                    $("#addemlstbtn").hide();
                    $("#emailhistorydiv").hide();
                    $("#footerdiv").show();
                 
                    
                    $("#emlhistab").removeClass("top-subnav-link-active").addClass("top-subnav-links");
                    $("#emlhistab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#emllistab").removeClass("top-subnav-link-active").addClass("top-subnav-links");
                    $("#emllistab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#emldrftab").removeClass("top-subnav-link-active").addClass("top-subnav-links");;
                    $("#emldrftab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#emllistab").removeClass("top-subnav-link-active").addClass("top-subnav-links");;
                    $("#emllistab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#emldrftab").removeClass("top-subnav-link-active").addClass("top-subnav-links");;
                    $("#emldrftab a").removeClass("h3-active-subnav").addClass("h3"); 
                    $("#emlsettab").removeClass("top-subnav-link-active").addClass("top-subnav-links");;
                    $("#emlsettab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#emlsettab").removeClass("top-subnav-link-active").addClass("top-subnav-links");;
                    $("#emlsettab a").removeClass("h3-active-subnav").addClass("h3");
                    $("#footertab").removeClass("top-subnav-links").addClass("top-subnav-link-active");
                    $("#footertab a").removeClass("h3").addClass("h3-active-subnav");
                });
                
                if(location.search == "?emaildrafts=1"){
                    $("#emaillistsdiv").hide();
                    $("#emaildraftsdiv").show();
                    $("#addemlstbtn").hide();
                    $("#emldrftab").addClass("top-subnav-link-active");
                    $("#emldrftab a").addClass("h3-active-subnav");
                    $("#emllistab").removeClass("top-subnav-link-active");
                    $("#emllistab a").removeClass("h3-active-subnav");
                    $("#emllistab").addClass("top-subnav-links");
                    $("#emllistab a").addClass("h3"); 
                    $("#removeselactions").hide();
                    getAllDrafts();
                 }
                $("#close").click(function(){
                   $("#fade").hide();
                   $("#addContact").hide(); 
                });
                $("#chooseEmailList").change(function () {
                    var x = document.getElementById("chooseEmailList").selectedIndex;
                    var List_name = document.getElementsByTagName("option")[x].value;
                    $("#email_list_name").val(List_name);
                    $.ajax({
                        url: getHost() + "GetEmailLists",
                        data: {
                            update: "emailsForEmailList",
                            list_name: List_name
                        },
                        success: function (result) {
                            var email_addresses = JSON.stringify(result.emailAddresses);
                            var email_add = email_addresses.replace("\"", '');
                            var email_address = email_add.replace("\"", '');
                            $("#textArea").val(email_address);
                        }
                    });
                });
                
                $("#selectAll").click(function (){    
                    var selectAll = document.getElementById("selectAll").checked;
                    if (selectAll == true){                        
                        $(".email").prop("checked", true);
                    }else if(selectAll == false) {
                        $(".email").prop("checked", false);
                    }
                });
                $("#emailhubli").click(function (){$("#emlsclspanid").empty().append('Email');$(".dropdown-hub").hide();});
                $("#socialhubli").click(function (){$("#emlsclspanid").empty().append('Social');$(".dropdown-hub").hide();});
            });            
            function setSelectedlistName(listname){
                $("#email_list_name").val(listname);
            }            
            function showTextBox() {
                $(".emaillist").hide();
                $("#email_list_name").val("");
                $("#email_list_name").show();
                $("#email_list_name").focus();
            }
            function upload() {
                var fileUpload = document.getElementById("fileUpload");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                            if ($('#textArea').val() == "") {
                                $('#textArea').val(rows);
                            } else {
                                $('#textArea').val($('#textArea').val() + rows);
                            }
                        }
                        reader.readAsText(fileUpload.files[0]);
                    } else {
                        alert(browsernotsupporthtml5);
                    }
                } else {
                    alert(cvsfileerror);
                }
            }

            function showListBox() {
                $(".emaillist").show();
            }
            function validate() {
                    var regex=/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                
                if (emailListName === "") {
                    alert(emaillisterror);
                    $("#list_name").focus();
                    return false;
                }
                 if (listDescription === "") {
                    alert(listdescriptionerror);
                    $("#list_description").focus();
                    return false;
                }
                if ($.trim(defaultFromName).length == 0) {
                    alert(defaultfromnameerror);
                    $("#default_from_name").focus();
                    return false;
                }
                if(regex.test(defaultFromName)===false){
                    alert(wrongemail);
                    $("#default_from_name").focus();
                    return false;
                }
                return true;
            }
            function reSetemaillistpopup()
            {
               $("#list_name").val("");
               $("#list_description").val("");
               $("#default_from_name").val("");
               return true;
            }
            function emailHistory($scope, $http) {
               showOverlay();
               $scope.displayemailhistory = function (){
                   showOverlay();
                   $http({
                       method : 'GET',
                       url :getHost() + '/email/tags'
                   }).success(function(data, status, headers, config) { 
//                    alert(JSON.stringify(data.d.details[0]));
                   if (data.d.details[0] == "[]"){
                       
                       $scope.email_history = JSON.parse(data.d.details);
                       $("#nohistorydiv").empty().text(noemailhistory);
                       $(".nohiswid").css("width","250px");
                       $("#historydiv").hide();
                   }else {
                       $("#historydiv").show();
                       $scope.email_history = JSON.parse(data.d.details);
                   }
                   hideOverlay();
               }).error(function(data, status, headers, config) {
                   hideOverlay();
                   alert(nodataerror);
               });        
               };
           }
 
            function EmailListController($scope, $http) {
                $("#addemlstbtn").show();                   
                showOverlay();
                $scope.getEmailSettings = function(){     
                showOverlay();
                var email_settings = {"type": "get"};                
                $http({

                        method : 'GET',
                        url :getHost() + '/settings/getEmailSettings',
                        headers: {'Content-Type': 'application/json'}
//                        data: email_settings
                    }).success(function (data, status, headers, config) {
                        var parseData=JSON.parse(data.d.details);
//                        alert(JSON.stringify(parseData));
                        $scope.email_settings = parseData;
                        if (data === error) {
                            alert(data);
                        }
                        hideOverlay();
                    }).error(function (data, status, headers, config) {
                        hideOverlay();
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
                
                $scope.setEmailSettings = function () {
                    var from_address = $("#from_address").val();
                    var reply_email_address = $("#reply_email_address").val();
                    if (validateemailset()) {
                        var email_settings = {"from_address": from_address, "reply_email_address": reply_email_address, "type": "add"};
                        $http({
                            method: 'POST',
                            url: getHost() + '/settings/saveEmailSettings',
                            headers: {'Content-Type': 'application/json'},
                            data: email_settings
                        }).success(function (data)
                        {
                            alert(sessionsaved);
                        }).error(function (data, status) {
                            alert(requesterror);
                        });
                    }
                };
                function validateemailset() {
                var from_address = $("#from_address").val();
                var reply_email_address = $("#reply_email_address").val();                
                if (from_address === "") {
                    alert(fromaddresserror);
                    $("#from_address").focus();
                    return false;
                }                
                if ($.trim(from_address).length == 0) {
                    alert(emailerror);
                    $("#from_address").focus();
                    return false;
                }                
                if (!(validateEmail(from_address))) {
                    alert(wrongemail);
                    $("#from_address").focus();
                    return false;
                }
                if (reply_email_address === "") {
                    alert(replyemailerror);
                    $("#reply_email_address").focus();
                    return false;
                }                
                if ($.trim(reply_email_address).length == 0) {
                    alert(emailerror);
                    $("#reply_email_address").focus();
                    return false;
                }                
                if (!(validateEmail(reply_email_address))) {
                    alert(wrongemail);
                    $("#reply_email_address").focus();
                    return false;
                }                
                return true;
            }
                
 var sliderDialog="";
 var prevSliderDialog="";
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
                $scope.createEmailList = function () {
                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                    if (validate()){
                        var Emails = {"emailListName": emailListName, "defaultFromName": defaultFromName, "listDescription":listDescription, "update": "addEmailList"};
                        $http({
                            method: 'POST',
                            url: getHost() + '/emaillist/save',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            $("#createEmailListButton").unbind('click');
                            alert(datasaved);
                            window.open(getHost() + 'user/emaillists', "_self");
                            $("#createEmailListButton").bind('click');
                        });                        
                    }                    
                };
                
                function validateEmailListPopup() {
                    var email_address = $("#emailId").val();
                    var email_first_name = $("#firstName").val();
                    var email_last_name = $("#lastName").val();
                    var error=0;
                    if(email_address==="")
                    {
                        error++;
                        alert(noemail);
                        $("#emailId").focus();
                        return false;
                    }
                     if(email_address!=="")
                    {
                        var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                        if(re.test(email_address)){}
                        else
                        {
                            error++;
                            alert(emailerror);
                            $("#emailId").focus();
                            return false;
                        }
                    }
                    if(email_first_name!=="")
                    {
                        if(email_last_name==="")
                        {
                            error++;
                            alert(lastnameerror);
                            $("#lastName").focus();
                            return false;
                        }
                    }                    
                    if(error===0)
                    {
                        return true;   
                    }
                }                
                $scope.updateEmailID = function(){
                    var email_list_name = $("#email_list_name").val();
                    var email_address = $("#emailId").val();
                    var email_first_name = $("#firstName").val();
                    var email_last_name = $("#lastName").val();
                    var type = $("#type").val();
                    if (validateEmailListPopup()){
                        var emaildetails;
                    if (type === "add"){
                        emaildetails = {"update":"checkAvailability", "emailListName":email_list_name, 
                                            "emailAddress":email_address, "emailFirstName":email_first_name, 
                                            "emailLastName":email_last_name}
                        $http({
                            method: 'POST',
                            url: getHost() + '/emaillist/save',
                            headers: {'Content-Type': 'application/json'},
                            data: emaildetails
                        }).success(function (data)
                        {
                            $("#addContactButton").unbind('click');
                            var parseData=JSON.stringify(data.d.operationStatus.messages);
                            if (parseData === '["Email list could not be saved, please try again in sometime."]') {
                                emaildetails = {"update":"addEmailID", "emailListName":email_list_name, 
                                            "emailAddress":email_address, "emailFirstName":email_first_name, 
                                            "emailLastName":email_last_name}
                                $http({
                                    method: 'POST',
                                    url: getHost() + '/emaillist/save',
                                    headers: {'Content-Type': 'application/json'},
                                    data: emaildetails
                                }).success(function (data)
                                {
                                    alert(datasaved);
                                    window.open(getHost() + 'user/emaillistsdetails?list_name='+email_list_name+'&type='+type, "_self");

                                    $("#addContactButton").unbind('click');
                                });
                            }else if (parseData === '["Email list saved successfully."]'){
                                alert(emailexist);
                                $("#addContactButton").unbind('click');
                            }
                        });                    
                        }else if (type === "update"){
                            var id = $("#uuid").val();
                            emaildetails = {"update":"updateEmailID", "emailUID":id, "emailListName":email_list_name, 
                                                "emailAddress":email_address, "emailFirstName":email_first_name, 
                                                "emailLastName":email_last_name}
                            $http({
                                method: 'POST',
                                url: getHost() + '/emaillist/save',
                                headers: {'Content-Type': 'application/json'},
                                data: emaildetails
                            }).success(function (data)
                            {
                                alert(datasaved);
                                window.open(getHost() + 'user/emaillistsdetails?list_name='+email_list_name+'&type='+type, "_self");
                            });
                        }
                    }            
                };                
                $scope.updateEmailList = function () {
                    
                    var emailaddrestextarea=$("textArea").val();
                    var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                    var toemailvalid=reg.test(emailaddrestextarea);
                    if($("textArea").val()===''){alert("No Contacts to import!, Please Enter atleast One Contact.");$("#textArea").focus(); return false;}
                    if($("textArea").val() !== ""){

                        var split = emailaddrestextarea.split(",");
                        var lines = [];
                        $.each($('#textArea').val().split(/\n/), function(i, line){
                            if(line){
                                lines.push(line);
                            }
                        });
                        for (var i = 0; i < split.length; i++) {
                            //alert(split[i]+"  split length"+split.length);
                            var email=split[i].trim();
                            if(reg.test(email) !== "")
                            {
                                if(email !== "")
                                {
                                    if(reg.test(split[i]) === false){
                                        alert(" Contacts not Valid! Please Enter Valid Email Address \n\n'"+split[i]+"'\t is Invalid Email id.");
                                        $("#textArea").focus();
                                        return false;
                                    } 
                                }
                            }
                        }
                    }
                    var email_list_name = $("#email_list_name").val();
                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list, "update": "UpdateEmailList"};
                    $http({
                        method: 'POST',
                        url: getHost() + '/emaillist/save',
                        headers: {'Content-Type': 'application/json'},
                        data: Emails
                    }).success(function (data)
                    {
                        if (data === "true") {
                            alert(datasaved);
                            window.open(getHost() + 'emaillists.jsp', "_self");                            
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                };

                $scope.showEmailList = function () {
                    $(".emaillist").show();
                    $("#email_list_name").hide();
                    var emailids = {"update": "allEmailListNames"};
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=allEmailListNames',
                    }).success(function (data, status, headers, config) {
                        $scope.emailLists1 = data.allEmailListNames
                        if (data === "true") {
                        } else if (data === error) {
                            alert(data);
                        }
                    }).error(function(error){
                        alert("error");
                    });
                };
                $scope.showEmailListWithContacts = function () {
                    $("#addemlstbtn").show();
                    showOverlay();
                    $("#scrl").show();
                    $(".emaillist").show();
                    $("#email_list_name").hide();
                    $.ajax({
                        method: 'GET',
//                        url: getHost() + 'GetEmailLists?update=allEmailListWithNoOfContacts'
                        url: getHost() + '/emaillist/get?update=allEmailListWithNoOfContacts&emailListName=null',
                    }).success(function (data, status, headers, config) { 
                        if(location.search != "?emaildrafts=1"){
                            $("#addemlstbtn").show();
                        }//                        alert(JSON.stringify(JSON.parse(data.d.details)));
                        var parseData=JSON.parse(data.d.details);
                        $scope.emailLists = parseData.allEmailListWithNoOfContacts.user; 
                        $scope.emailListsMindbody = parseData.allEmailListWithNoOfContacts.mindbody;
                        hideOverlay();
                         if (data.d.operationStatus.statusCode === "Success") {
                        } else {
                            alert(data.d.operationStatus.messages[0]);
                        }
                        
                    }).error(function(error){
                        hideOverlay();
                        alert("error");
                    });
                };
                $scope.clearfields = function () {
                    $("#email_list_name").val("");
                    $("#textArea").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
                
                $scope.updateList = function () {
                    $("#showList").show();
                    $("#importListli").removeClass("top-subnav-link-active");
                    $("#importList").removeClass("h3-active-subnav");
                    $("#emailListli").addClass("top-subnav-link-active");
                    $("#emailList").addClass("h3-active-subnav");
                    $(".page-background").css("background-color","#fff");                    
                    var list_name=$("#get_list_name").val();
                    var type=$("#get_type").val();
                    $("#tab4").hide();
                    $("#email_list_name").val(list_name);
//                    $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position","absolute").css("top","300px").css("left","560px");
                    $http({
                        method: 'GET',
                        url: getHost() + '/emaillist/get?update=emailsForEmailList&emailListName='+list_name
                    }).success(function (data, status, headers, config) {
                        var parseData=JSON.parse(data.d.details);
                        $(".page-background").css("overflow","scroll");
//                        $('#loadingGif').remove();
                        $(".page-background").css("background-color","#EFF2F6");
                        $scope.user_emailAddresses = parseData.user_emailAddresses;
                        $scope.mindbody_emailAddresses = parseData.mindbody_emailAddresses;
                        $scope.selected_email_listname = list_name;
                        $scope.type = type;
                        hideOverlay();
                        if (type == 'user'){
                            $("#tab1").hide();
                            $("#tab2").hide();
                            $("#tab3").show();
                            $("#addcontacts").show();
                            $("#deleteSelected").show();
                            $("#selectAll").show();
                            for (var i = 0; i <= data.user_emailAddresses.length; i++){                                
                                var emailadd = data.user_emailAddresses[i];
                                if (emailadd.emailAddress == ""){
                                    $("#NoContacts").css("display","block");
                                    setTimeout(function() 
                                    {
                                      $('input[type="checkbox"]').css("display","none");

                                    }, 100);
                                }
                            }
                        }else if (type == 'mindbody'){
                            $("#addcontact").hide();
                            $("#email1").hide();
                            setTimeout(function() 
                            {
                              $('input[type="checkbox"]').css("display","none");                              
                            }, 100);
                        }
                        if (data === error) {
                            alert(data);
                        }                        
                    }).error(function(error){
//                        alert(JSON.stringify(error));
                    });
                };
                
                $scope.getEmailList = function () {
                    var list_name = $("#email_list_name").val();
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=emailsForEmailList&list_name='+list_name
                    }).success(function (data, status, headers, config) {                        
                        if (data.emailAddresses !== "") {
                            $("#tab4").show();
                            $("#tab1").hide();
                            var i = 0;
                            var emails = "";
                            for(i=0; i<data.user_emailAddresses.length; i++){
                                        if (data.user_emailAddresses[i].emailAddress != ""){
                                            emails = data.user_emailAddresses[i].emailAddress + "," + emails;
                                        }
                                    }
                            for(i=0; i<data.mindbody_emailAddresses.length; i++){
                                if (data.mindbody_emailAddresses[i] != ""){
                                    emails = data.mindbody_emailAddresses[i] + "," + emails;
                                }
                            }
                            $("#textArea").val(emails);
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                    
                };
            
                $scope.deleteEmailList = function (){
                    if (confirm("Are you sure, You want to Delete Email List?")){
                        var noofemaillist="";
                        var selected_email_lists="";
                        $("input[type=checkbox]:checked").each ( function() {
                            selected_email_lists +=$(this).val()+",";
                            noofemaillist=selected_email_lists.split(',');
                        });
                        if (noofemaillist.length>2){
                            var EmailLists = {"update":"deleteAllEmailLists", "emailListName": selected_email_lists};
                        }
                        else {
                            var EmailLists = {"update":"deleteEmailLists", "emailListName": selected_email_lists};
                        }
                        $http({
                            method: 'POST',
                            url: getHost() + '/emaillist/save',
                            headers: {'Content-Type': 'application/json'},
                            data: EmailLists
                        }).success(function (data)
                        {
                            if (data.d.operationStatus.statusCode === "Success") {
                                alert(deleteemaillist);
                                window.open(getHost() + 'user/emaillists', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                    }                        
                };                
                
                $scope.deleteSelected = function (){
                    if (confirm("Are you sure, You want to delete contact?")){
                        var email_list_name = "";
                        if (selectedemailids != ""){
                            email_list_name = $("#email_list_name").val();
                            var Emails = {"update": "deleteEmailInEmailList", "emailListName":email_list_name, "emailAddresses":selectedemailids};
                            $http({
                                method: 'POST',
                                url: getHost() + '/emaillist/save',
                                headers: {'Content-Type': 'application/json'},
                                data: Emails
                            }).success(function (data)
                            {
                                alert(contactdelete);
                                $scope.updateList(email_list_name);
                                selectedemailids = "";
                                window.open(getHost() + 'user/emaillistsdetails?list_name='+email_list_name+'&type=user', "_self");
                            }).error(function (error)
                            {
                                alert(JSON.stringify(error));
                            });
                        }else {
                            alert(emailnotselected);
                        }
                    }
                };
                $scope.showAddContacts = function (){
                    count=0;
                    $(".delete-button").hide();
                    $(".gray-button").hide();
                    $("#showList").hide();
                    $("#tab4").show();
                    $("#importListli").addClass("top-subnav-link-active");
                    $("#importList").addClass("h3-active-subnav");
                    $("#emailListli").removeClass("top-subnav-link-active");
                    $("#emailList").removeClass("h3-active-subnav");
                    $("#emailListli").addClass("top-subnav-links");
                    $("#emailList").addClass("h3");
                    $("#tab3").hide();
                    $("#tab4").show();
                };
                
                $scope.showCreateContacts = function(){
                    $("#tab1").hide();
                    $("#tab2").show();
                    $("#tab3").hide();
                    $("#tab4").hide();
                };
                
                $scope.addemaillist = function()
                {
                    reSetemaillistpopup();
                    $("#addActionemllist").show();
                    $("#fade").show();
                };
            
            $("#closedraftpopup").click(function(){
                sliderDialog = "#emaildraftpopup";
                prevSliderDialog = "#emaildraftpopup";
                $('#slider-button').click();
            });
                
            $scope.showdraftpopup = function (Id,categoryId,emailSubject,editdate,subCategoryId,mindbodyId,lookupId){
            $slider=2;
//            alert("draftid.. "+Id+" ..categoryId.. "+categoryId+" ..mindbodyId.. "+mindbodyId+" ..lookupId.. "+lookupId+" ..emailSubject.. "+emailSubject);
            sliderDialog = "#emaildraftpopup";
            prevSliderDialog = "#emaildraftpopup";
            $http({
                    method : 'GET',
                    url : getHost() + 'getEmailDraft?draftid='+Id
                }).success(function(data, status) {
                    if (data == ""){
                        $scope.emaildraftsstatus = noemaildraft;
                    }else {     
                        $scope.htmlbody = data.htmlbody;
                        $('#draftshow').empty().append(data.htmlbody);
                    }
                }).error(function(data, status) {
                    alert(nodataerror);
                }); 
//                alert(Id);
                $scope.id = Id;
                $scope.categoryid = categoryId;
                $scope.emailsubject = emailSubject;
                $scope.editdate = editdate;
                $scope.subcategoryid = subCategoryId;
                $scope.mindbodyId = mindbodyId;
                $scope.lookupId = lookupId;
                $('#slider-button').click();                    
           };
            
        $scope.deletedrafts = function (type,id) {
        var delid=id+",";
        var message;
        var requestBody;
        var responseMessage;         
        if (type == "deleteMultiple") {
            message = multidraftconfirm;
            requestBody = {"type": "deleteSelected",
                "draft_ids": selected_emaildrafts_to_delete, "entity_type": "null"};
            responseMessage = multidraftdeleted;
        } else if (type == "delete") {
            message = singledraftconfirm;
            requestBody = {"type": "delete",
                            "draft_ids": delid};
            responseMessage = singledraftdeleted;
        }
        
        if (confirm(message)) {
            $http({
                method: 'POST',
                url:getHost()+'deleteEmailDrafts',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== "") {
                    window.open(getHost() + 'user/emaillists?emaildrafts=1', "_self");
                }
            }).error(function (data, status) {
                alert(someerror);
            });
        }
    };
            
        $scope.unseldrafts = function (id) {
          var myarray = selected_draft.split(',');
          for(var i = 0; i < myarray.length; i++)
          {
                content='<input type="checkbox" id="'+'draftId'+myarray[i]+'" checked="false" hidden="">';
                var htm=$("#"+myarray[i]).html();
                if(htm.contains('class="check-icon"')){
                    count-=1;
                    $("#"+myarray[i]).html(content);
                }
                myarray[i] = myarray[i].replace(id + ",", "");
                $("#"+myarray[i]).removeClass('selection-icon-selected');
                $("#"+myarray[i]).addClass('selection-icon');
                $("#drftEmailDelete").hide();
                selected_draft="";
          }
         };
            
        $scope.getAllDrafts = function(){
            showOverlay();
            $("#addemlstbtn").hide();
            $("#emaildraftsection").hide();
            $http({
                method : 'GET',
                url : getHost() + 'displayAllEmailDrafts'
            }).success(function(data, status) {
//                alert(JSON.stringify(data.emaildrafts));
                if (data.nodrafts == "yes"){
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                    hideOverlay();
                }else {
                    $scope.emaildrafts = data.emaildrafts;
                }
            }).error(function(data, status) {
                hideOverlay();
                alert(nodataerror);
            });            
        };

        $scope.editDrafts = function(draft_id, category_id,email_subject, sub_category_id, mindbodyId, lookupId){
            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id, 
                        "sub_category_id": sub_category_id};
//                    alert(JSON.stringify(draftdetails));
            $http({

                method : 'GET',
                url : getHost() + '/getEmailDraft?draftid='+draft_id, // url : getHost() + '/saveEmailDraftSessionValue',
                headers: {'Content-Type':'application/json'}
//                data: JSON.stringify(draftdetails)
            }).success(function(data, status) {
                if (data == "false"){
                    alert(draftsavingerror)
                }else {
//                    draftId=null&categoryId=18&subCategoryId=7&emailSubject=sub&mindbodyId=75031&LookupId=4
                    window.open(getHost() + 'user/emaileditor?draftId='+draft_id+'&emailSubject='+email_subject+'&categoryId='+category_id+'&subCategoryId='+sub_category_id+'&mindbodyId='+mindbodyId+'&LookupId='+lookupId, "_self");                    

                }
            }).error(function(data, status) {
                window.open(getHost() + 'user/emaileditor?id='+null+'&draftid='+draft_id+'&subject='+email_subject, "_self");     
                alert(nodataerror);
            });
        };
       $scope.getFooterDetails = function (){
          $http({
                method : 'GET',
                url : getHost() + '/settings/getAllPreferences'
            }).success(function(data, status) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
            });
       };
      $scope.changeFooterDetails = function (){
      var address = $("#footerAddress").val();
      var websiteurl = $("#footerWebsiteUrl").val();;
      var facebookurl = $("#footerFacebookUrl").val();;
      var twitterUrl = $("#footerTwitterUrl").val();;
      var instagramUrl = $("#footerInstagramUrl").val();
      var footerData = '{"facebookUrl":"'+facebookurl+'","twitterUrl":"'+twitterUrl+'","instagramUrl":"'+instagramUrl+'","websiteUrl":"'+websiteurl+'","address":"'+address+'"}';
      if(address){
          $http({
                method: 'POST',
                url: getHost() + 'settings/setFooter',
                data: footerData
            }).success(function (data) {
                    alert(detailssaved);
                    $("#emailFooterPopup").hide();
                    $scope.getFooterDetails();
                    
            }).error(function (data, status) {
                alert(requesterror);
            });
      }
        else{
            alert("please enter the Address");
            $("#footerAddress").focus();
        }

    };
    $scope.uploadEmailListOnClick = function() {
        var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                var fileUpload = document.getElementById("fileid");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                $scope.unsubscribeEmailList = "";
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                            for(var j = 1; j< rows.length; j++)
                            {
                                var csvvalue=rows[j].split(",");

//                                for(var i=0;i<csvvalue.length;i++)
//                                {
                                    var temp=csvvalue[0];
                                    if(j==1)
                                        $scope.unsubscribeEmailList = temp;
                                    else
                                        $scope.unsubscribeEmailList = $scope.unsubscribeEmailList+","+temp;
//                                }
                            }
                            alert("Emails from CSV file read successfully, please click Unsubscribe Now button to process them.")
                        }
                        reader.readAsText(fileUpload.files[0]);

                    } else {

                        alert("This browser does not support HTML5!");
                    }
                } else {
                    alert("Please upload a valid CSV file!");
                }
                
    };
    $scope.unsubscribeNowOnClick = function () {
        //If no file is selected
        if (!$scope.unsubscribeEmailList)
        {
            alert("Please choose a valid csv file.");
            return false;
        }
        //if file is blank
        if ($scope.unsubscribeEmailList)
        {
            if ($scope.unsubscribeEmailList.length == 0)
            {
                alert("Please choose a valid csv file.");
                return false;
            }
        }
        var emailListData = $scope.unsubscribeEmailList.split(",");
        $http({
            method: 'POST',
            url: getHost() + 'settings/saveUnsubscribeEmails',
            data: emailListData
        }).success(function (data) {
            alert(data.d.operationStatus.messages[0]);
            hideUnsubscribeEmailsPopup();
        }).error(function (data, status) {
            alert(requesterror);
        });
    };   
 };

/////////Email drafts js///////
        
        var selected_draft="";
        var count=0;
        function selcheckbox(id){ 
            content='<input type="checkbox" id="'+'draftId'+id+'" checked="true" hidden="">';
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                count-=1;
                $("#"+id).html(content);
                selected_draft = selected_draft.replace(id + ",", "");
            }
            else
            { 
                count+=1;
                $("#"+id).html(content+'<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
                selected_draft = id+ "," + selected_draft;
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {
                $("#drftEmailDelete").show();
            }
            if(count==0)
            {
                $("#drftEmailDelete").hide();
            }
        }
        function openEmailFooterPopup(){
            $("#emailFooterPopup").show();
        }
        function hideEmailFooterPopup(){
            $("#emailFooterPopup").hide();
        }
       function openUnsubscribeEmailsPopup(){
           $("#unsubscribeEmailsPopup").show();
       }
       function hideUnsubscribeEmailsPopup(){
        $("#unsubscribeEmailsPopup").hide();
       }