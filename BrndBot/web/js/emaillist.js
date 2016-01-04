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
        function selemlcheckbox(id){ 
//            alert(id+"--selected");
            content='<input type="checkbox" name="deleteid" value="'+id+'" hidden="" id="deleteid" checked>';
//            alert(content);
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
               count-=1;
                $("#"+id).html(content);
            }
            else
            {   count+=1;
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#"+id).toggleClass('selection-icon');
            $("#"+id).toggleClass('selection-icon-selected');
            if(count > 0)
            {
                $(".delete-button").show();
                 $(".gray-button").show();
                 $("#addcontact").hide();
                 $("#addcontacts").hide();
                 $(".add-action-button").hide();
            }
            if(count==0)
            {
                 $(".add-action-button").show();
                 $(".delete-button").hide();
                 $(".gray-button").hide();
                 $("#addcontact").show();
                 $("#addcontacts").show();
            }
        }
        function unsel(){
                    count=0;
                   var htm=$(".selection-icon-selected").html();   
//                   alert(htm);
                   if(htm.contains('class="check-icon"')){
                         $(".selection-icon-selected").html('');
//                         alert("count"+count);
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
        

 $("#fileUpload").change(function () {
    loadImageFile();
    // resets input file
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
                $("#emaildraftsdiv").hide();
                $(".delete-button").hide();
                $(".gray-button").hide();
                $("#list_name").focus(function (){$("#lstnm").css("left","-125px").css("font-size","13px").css("color","#999");});
                $("#list_name").focusout(function (){var emllist=$("#list_name").val();if(emllist===""){$("#lstnm").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#lstnm").css("left","-123px");}});
                $("#default_from_name").focus(function (){$("#deffrmnm").css("left","-157px").css("font-size","13px").css("color","#999");});
                $("#default_from_name").focusout(function (){var emllist=$("#default_from_name").val();if(emllist===""){$("#deffrmnm").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#deffrmnm").css("left","-157px");}});
                $("#list_description").focus(function (){$("#lstdesc").css("left","-145px").css("font-size","13px").css("color","#999");});
                $("#list_description").focusout(function (){var emllist=$("#list_description").val();if(emllist===""){$("#lstdesc").css("left","20px").css("font-size","12px").css("color","#2c4355");}if(emllist!==""){$("#lstdesc").css("left","-145px");}});
                
                $("#emldrftab").click(function (){
                    $("#emaillistsdiv").hide();
                    $("#emaildraftsdiv").show();
                    $("#addemlstbtn").hide();
                    $("#emldrftab").addClass("top-subnav-link-active");
                    $("#emldrftab a").addClass("h3-active-subnav");
                    $("#emllistab").removeClass("top-subnav-link-active");
                    $("#emllistab a").removeClass("h3-active-subnav");
                    $("#emllistab").addClass("top-subnav-links");
                    $("#emllistab a").addClass("h3"); 
                });
                
                $("#emllistab").click(function (){
                    $("#emaillistsdiv").show();
                    $("#emaildraftsdiv").hide();
                    $("#addemlstbtn").show();
                    $("#emllistab").addClass("top-subnav-link-active");
                    $("#emllistab a").addClass("h3-active-subnav");
                    $("#emldrftab").removeClass("top-subnav-link-active");
                    $("#emldrftab a").removeClass("h3-active-subnav");
                    $("#emldrftab").addClass("top-subnav-links");
                    $("#emldrftab a").addClass("h3"); 
                });
                
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

                        alert("This browser does not support HTML5!");
                    }
                } else {
                    alert("Please upload a valid CSV file!");
                }

            }

            function showListBox() {
                $(".emaillist").show();
                //$("#email_list_name").hide();

            }
            function validate() {
                    var regex=/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;

                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                
                if (emailListName === "") {
                    alert("Email list name not entered!  Please enter the from email list.");
                    $("#list_name").focus();
                    return false;
                }
                 if (listDescription === "") {
                    alert("List description not entered! Please enter the list description.");
                    $("#list_description").focus();
                    return false;
                }
                if ($.trim(defaultFromName).length == 0) {
                    alert('Please enter default from name');
                    $("#default_from_name").focus();
                    return false;
                }
                if(regex.test(defaultFromName)===false){
                    alert("Invalid Email Address!");
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

            function EmailListController($scope, $http) {


                $scope.createEmailList = function () {
                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                    if (validate()){
                        var Emails = {"emailListName": emailListName, "defaultFromName": defaultFromName, "listDescription":listDescription, "update": "addEmailList"};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data saved successfully.");
                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
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
                        alert("Please Enter Email Address!");
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
                            alert("Please Enter Valid Email Address!");
                            $("#emailId").focus();
                            return false;
                        }
                    }
                    if(email_first_name==="")
                    {
                        error++;
                        alert("Please Enter First Name!");
                        $("#firstName").focus();
                        return false;
                    }
                    if(email_last_name==="")
                    {
                        error++;
                        alert("Please Enter Last Name!");
                        $("#lastName").focus();
                        return false;
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
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: emaildetails
                        }).success(function (data)
                        {
                            if (data === "false") {


                                emaildetails = {"update":"addEmailID", "emailListName":email_list_name, 
                                            "emailAddress":email_address, "emailFirstName":email_first_name, 
                                            "emailLastName":email_last_name}
                                $http({
                                    method: 'POST',
                                    url: getHost() + 'SetEmailLists',
                                    headers: {'Content-Type': 'application/json'},
                                    data: emaildetails
                                }).success(function (data)
                                {
                                    if (data === "true") {
                                        alert("Data saved successfully.");

                                        window.open(getHost() + 'emaillistsdetails.jsp?list_name='+email_list_name+'&type='+type, "_self");
                                    }
                                });
                            }else if (data === "true"){
                                alert("Emailid already exist, please try with some other emailid.");
                            }
                        });
                    
                        }else if (type === "update"){
//                                        window.open(getHost() + 'emaillists.jsp', "_self");
//                                    }
//                                });
//                            }else if (data === "true"){
//                                alert("Email id already exist! Please try with some other emailid.");
//                            }
//                        });
//                    
//                        }else if (type == "update"){
                            var id = $("#uuid").val();
                            emaildetails = {"update":"updateEmailID", "emailUID":id, "emailListName":email_list_name, 
                                                "emailAddress":email_address, "emailFirstName":email_first_name, 
                                                "emailLastName":email_last_name}
                            $http({
                                method: 'POST',
                                url: getHost() + 'SetEmailLists',
                                headers: {'Content-Type': 'application/json'},
                                data: emaildetails
                            }).success(function (data)
                            {
                                if (data === "true") {
                                    alert("Data saved successfully.");
                                    window.open(getHost() + 'emaillistsdetails.jsp?list_name='+email_list_name+'&type='+type, "_self");

//                                    window.open(getHost() + 'emaillists.jsp', "_self");


                                } else if (data === error) {
                                    alert(data);
                                }
                            });
                        }
                    }            
                };
                
                $scope.updateEmailList = function () {
                    var email_list_name = $("#email_list_name").val();
                    var email_list = $("#textArea").val();

                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list, "update": "UpdateEmailList"};
                    $http({
                        method: 'POST',
                        url: getHost() + 'SetEmailLists',
                        headers: {'Content-Type': 'application/json'},
                        data: Emails
                    }).success(function (data)
                    {
                        if (data === "true") {
                            alert("Data saved successfully.");
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
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                };

                $scope.showEmailListWithContacts = function () {
                    $("#scrl").show();
                    $(".emaillist").show();
                    $("#email_list_name").hide();

                    var emailids = {"update": "allEmailListNames"};
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=allEmailListWithNoOfContacts',
                    }).success(function (data, status, headers, config) {              
                        $scope.emailLists = data.allEmailListWithNoOfContacts.user;
                        
                        $scope.emailListsMindbody = data.allEmailListWithNoOfContacts.mindbody;

                        if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                };

                $scope.clearfields = function () {
                    $("#email_list_name").val("");
                    $("#textArea").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
                
                $scope.updateList = function (list_name, type) {
                    $("#showList").show();
                    $("#importListli").removeClass("top-subnav-link-active");
                    $("#importList").removeClass("h3-active-subnav");
                    $("#emailListli").addClass("top-subnav-link-active");
                    $("#emailList").addClass("h3-active-subnav");
                    
//                    $("#emailListli").addClass("top-subnav-links");
//                    $("#emailList").addClass("h3");
                    
                    var list_name=$("#get_list_name").val();
                    var type=$("#get_type").val();
                    $("#tab4").hide();
                    $("#email_list_name").val(list_name);
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetEmailLists?update=emailsForEmailList&list_name='+list_name
                    }).success(function (data, status, headers, config) {
                        $scope.user_emailAddresses = data.user_emailAddresses;
                        $scope.mindbody_emailAddresses = data.mindbody_emailAddresses;
                        $scope.selected_email_listname = list_name;
                        $scope.type = type;
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
                                      //do something special
                                      $('input[type="checkbox"]').css("display","none");

                                    }, 100);
                                }

                            }
                        }else if (type == 'mindbody'){
                            
                            $("#tab3").show();
                            $("#tab1").hide();
//                            $("#addcontacts").attr("disabled", true);
                            $("#addcontacts").hide();
                            $("#deleteSelected").hide();
                            $("#emailaddrs").hide();
                            $(".head").css("padding-bottom","15%");
//                            
//                            $("#selectAll").attr("disabled", true);
//                            document.getElementById('email1').hide();
                            $("#email1").hide();
                            setTimeout(function() 
                            {
                              //do something special
                              $('input[type="checkbox"]').css("display","none");
                              
                            }, 100);
                        }
                        if (data === error) {
                            alert(data);
                        }
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
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });
                    
                };
                
//                $scope.selectCheckBox = function (){
//                    var selectAll = document.getElementById("selectAll").checked;
//                    if (selectAll == true){
//                        alert(selectAll);
//                         $(".email").attr("checked", true);
//                    }else if(selectAll == false) {
//                         $(".email").attr("checked", false);
//                    }
//
//                };
                
                $scope.deleteEmailList = function (){
                    var selected_email_lists="";
                    $("input[type=checkbox]:checked").each ( function() {
                        selected_email_lists +=$(this).val()+",";
                    });
                    //alert(selected_email_lists)
                    if (selected_email_lists){
                        var EmailLists = {"update":"deleteAllEmailLists", "emailListName": selected_email_lists};
                    }else {
                        var EmailLists = {"update":"deleteEmailLists", "emailListName": selected_email_lists};
                    }
                    $http({
                        method: 'POST',
                        url: getHost() + 'SetEmailLists',
                        headers: {'Content-Type': 'application/json'},
                        data: EmailLists
                    }).success(function (data)
                    {
                        if (data === "true") {
                            alert("Data deleted successfully");
//                                $scope.updateList(email_list_name);

                            window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    });                        
                };
                
                
                $scope.deleteSelected = function (){
                    //var selectAll = document.getElementById("selectAll").checked;
                    var email_list_name = "";
                    if (selectAll){
                        email_list_name = $("#email_list_name").val();
                        var Emails = {"update": "deleteAllEmailsFromList", "emailListName":email_list_name};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data deleted successfully.");
//                                $scope.updateList(email_list_name);
                                
                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                    }else if (selectedemailids != ""){
                        
                        email_list_name = $("#email_list_name").val();
                        var Emails = {"update": "deleteEmailInEmailList", "emailListName":email_list_name, "emailAddresses":selectedemailids};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data deleted successfully.");
                                $scope.updateList(email_list_name);
                                selectedemailids = "";
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                    }else {
                        alert("No email has been selected!");
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
                
            }


/////////Email drafts js///////
       
        
        
        var selected_draft="";
        var count=0;
        function selcheckbox(id){ 
              
//            alert(id+"--selected");
            content='<input type="checkbox" id="'+'draftId'+id+'" checked="true" hidden="">';
//            alert(content);
            var htm=$("#"+id).html();
            if(htm.contains('class="check-icon"')){
                count-=1;
                $("#"+id).html(content);
                selected_draft = selected_draft.replace(id + ",", "");
            }
            else
            { 
                count+=1;
                $("#"+id).html(content+'<img src="images/Icons/check.svg" class="check-icon" style="cursor:pointer;"/>');
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
        
        
        
        
        function emailDraftsController($http, $scope){
            
        $scope.deletedrafts = function (type) {
            
        var message;
        var requestBody;
        var responseMessage;
        if(selected_draft !==""){
           
        
        if (type == "deleteMultiple") {
            message = "Are you sure you want to delete these Draft(s)?";
//            alert("draft del ="+selected_draft);
            requestBody = {"type": "deleteSelected",
                "draft_ids": selected_draft, "entity_type": "null"};
            responseMessage = "Selected Drafts were deleted successfully";
//            alert("..."+requestBody.draft_ids);
        } else if (type == "delete") {
            message = "Are you sure you want to delete this Draft?";
            requestBody = {"type": "delete",
                            "draft_ids": selected_draft};
            responseMessage = "Selected Drafts were deleted successfully";
        }

        
        if (confirm(message)) {
            $http({
                method: 'POST',
                url:'deleteEmailDrafts.do',
                headers: {'Content-Type': 'application/json'},
                data: requestBody
            }).success(function (data)
            {
                $scope.status = data;
                if (data !== "") {
                    alert(responseMessage);
                    window.open(getHost() + 'emaildrafts.jsp', "_self");
                }
            }).error(function (data, status) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

                alert("Some Error occured! Try after some time.");
            });
        }
       }else{
            alert("No Drafts Selected!");
       }
    };
            
        $scope.unseldrafts = function (id) {

          var myarray = selected_draft.split(',');
          for(var i = 0; i < myarray.length; i++)
          {
               content='<input type="checkbox" id="'+'draftId'+myarray[i]+'" checked="false" hidden="">';
//                   alert(id);
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
            
            $http({
                method : 'GET',
                url : getHost() + 'displayAllEmailDrafts.do'
            }).success(function(data, status) {
                if (data.nodrafts == "yes"){
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                }else {
                    $scope.emaildrafts = data.emaildrafts;
                }
//                     alert(JSON.stringify(data));
            }).error(function(data, status) {
                alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
            });
        };

        $scope.editDrafts = function(draft_id, category_id,email_subject, sub_category_id, sub_category_name){

            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id, 
                        "sub_category_id": sub_category_id, 
                        "sub_category_name": sub_category_name};

            $http({
                method : 'POST',
                url : getHost() + 'saveEmailDraftSessionValues.do',
                headers: {'Content-Type':'application/json'},
                data: JSON.stringify(draftdetails)
            }).success(function(data, status) {
                if (data == "false"){
                    alert("There was a problem while saving the draft! Please try again later.")
                }else {
                    window.open(getHost() + 'emaileditor.jsp?id='+null+'&draftid='+draft_id, "_self");                    
                }

            }).error(function(data, status) {
                alert("No data available! Problem fetching the data.");
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
            });
        };
    };