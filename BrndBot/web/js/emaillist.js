/**
 *
 * Author: Satyajit Roy www.intbittech.com
 * 
 */
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
                        alert("This browser does not support HTML5.");
                    }
                } else {
                    alert("Please upload a valid CSV file.");
                }

            }

            function showListBox() {
                $(".emaillist").show();
                //$("#email_list_name").hide();

            }
            function validate() {
                    var emailListName = $("#list_name").val();
                    var defaultFromName = $("#default_from_name").val();
                    var listDescription = $("#list_description").val();
                
                if (emailListName === "") {
                    alert("email list name not entered, please enter the from email list");
                    $("#list_name").focus();
                    return false;
                }
                
                if ($.trim(defaultFromName).length == 0) {
                    alert('Please enter default from name');
                    $("#default_from_name").focus();
                    return false;
                }
                

                if (listDescription === "") {
                    alert("list description not entered, please enter the list description");
                    $("#reply_email_address").focus();
                    return false;
                }
                
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
                                alert("Data saved successfully");
                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                        
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
                            alert("Data saved successfully");
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
//                    alert(list_name);
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
                                if (emailadd.emailid == ""){
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
                                        if (data.user_emailAddresses[i].emailid != ""){
                                            emails = data.user_emailAddresses[i].emailid + "," + emails;
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
                
                $scope.selectCheckBox = function (){
                    var selectAll = document.getElementById("selectAll").checked;
                    if (selectAll){
                         $(".email").attr("checked", true);
                    }else {
                         $(".email").attr("checked", false);
                    }

                };
                
                $scope.deleteSelected = function (){
                    var selectAll = document.getElementById("selectAll").checked;
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
                                alert("Data deleted successfully");
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
                                alert("Data deleted successfully");
                                $scope.updateList(email_list_name);
                                selectedemailids = "";
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                    }else {
                        alert("no email has been selected");
                    }
                };
                $scope.showAddContacts = function (){
                    $("#tab3").hide();
                    $("#tab4").show();
                };
                
                $scope.showCreateContacts = function(){
                    $("#tab1").hide();
                    $("#tab2").show();
                    $("#tab3").hide();
                    $("#tab4").hide();
                };
            }

            $(".cross").hide();
            $(".menu").hide();
            $("#emaillist").hide();

           

            $("#fileUpload").change(function () {

                loadImageFile();
                // resets input file
                $('.fileUpload').wrap('<form>').closest('form').get(0).reset();
                $('.fileUpload').unwrap();
            });
