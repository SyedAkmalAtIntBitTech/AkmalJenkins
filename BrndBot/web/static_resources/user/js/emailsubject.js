            
            $(document).ready(function () {
                $("#emaillist").hide();
                $("#popup").hide();

                $("#emailSubjectContinueButton").click(function () {

        ///////////////////////////// Added by Satyajit Roy on 30th nov 2015 ///////////////////////////
                    var categoryId = $("#categoryIdTag").val();
                    var subCategoryId = $("#subCategoryIdTag").val();
                    var mindbodyid = $("#mindbodyId").val();
                    var LookupId = $("#LookupId").val();
                    var email_subject = $("#emailsubject").val();
                    if(email_subject==="")
                    {
                        alert(emailsubjecterror);
                        $("#emailsubject").focus();
                        return false;
                    }
                        else
                    {
                        document.title="BrndBot - Email List Selection"; 
                    }
                    document.location.href = "emaileditor?draftId=null&categoryId="+categoryId+"&subCategoryId="+subCategoryId+"&emailSubject="+email_subject+"&mindbodyId="+mindbodyid+"&LookupId="+LookupId;


        ////////////////////////////////////////// END ////////////////////////////////////////////////

                });
                
                $("#emailIdContinueButton").click(function () {
                    var selectedEmail=$("#chooseEmailList").val();
                    alert("..");
                    if(selectedEmail !== "1")
                    {
                        alert(selectedEmail);
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        
                        if(trim(email_addresses)!=="")
                        {
                            alert(email_addresses);
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#emailSettings").show();
                            $("#emaillistdiv").hide();
                            $("#emailSettings").show();
                            var email_list = $("#chooseEmailList").val();
                            $.ajax({
                                url: getHost() + "EmailTextDataServlet",
                                data: {
                                    email_subject: email_subject,
                                    email_addresses: email_addresses,
                                    email_list : email_list
                                },
                                success: function(result){
                                }
                            });
                        }
                        else
                        {
                            alert(selectemaillist);
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                }
                else{
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        if(trim(email_addresses)!=="")
                        {
                            alert(email_addresses);
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#toaddress").val(email_addresses);
                            $("#emailSettings").show();
                            var email_list = $("#chooseEmailList").val();
                            $.ajax({
                                url: getHost() + "EmailTextDataServlet",
                                data: {
                                    email_subject: email_subject,
                                    email_addresses: email_addresses,
                                    email_list : email_list
                                },
                                success: function(result){
                                }
                            });
                        }
                        else
                        {
                            alert(selectemaillist);
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                }
            });
              });
            function selectCsvFile(){
                $("#chooseEmailList").show();
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var list_name = document.getElementsByTagName("option")[x].value;
                if (list_name == 1){                   
                    $(function () {
                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";
                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");
                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;
                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }
                    }, true);
                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);
                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;
                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert(fileadded);
                    }, true);
                    });

                }else {
                   $("#emailIdContinueButton").show();
                    $("#entertext").show();
                    $("#dragtext").show();
                    $("#emailaddresses").show();
                    $("#drop-zone").show();
                    $("#clickHere").show();
                    $("#upload").show();
                    $("#emailIdContinueButton").css("top","-70px");
                    $(function () {
                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";
                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");
                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;
                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }
                    }, true);
                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);
                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;
                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }
                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert(cvsfileadded);
                    }, true);
                    });
                }
            }
            function fileselected(val){
                alert(val);
                alert($('input[type=file]').val());
                var text=$("#fileid").val();alert(text);
                $("#filetext").html(text);            
                var a = document.getElementById('file');
                if(a.value == "")
                {
                fileselect.innerHTML = "Choose file";
                }
                else
                    {
                        var theSplit = a.value.split('\\');
                        fileselect.innerHTML = theSplit[theSplit.length-1];
                    }
                }
                function upload() {
                var fileUpload = document.getElementById("file");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                             if ($('#emailaddresses').val() == "") {
                                $('#emailaddresses').val(rows);
                            } else {
                                $('#emailaddresses').val($('#emailaddresses').val() + ", " + rows);
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
            
                function EmailListController($scope, $http) {

                $scope.addEmailList = function () {
                    var email_list_name = $("#listname").val();
                    var email_list = $("#textArea").val();
                
                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list , "update": "addUpdateEmailList"};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert(datasaved);
                               window.open(getHost() + 'emailsubject.jsp', "_self");
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
                            url: getHost() + 'GetEmailLists?update=allEmailListNames'
                    }).success(function(data, status, headers, config) {
                            $scope.emailLists = data.user;
                            $scope.emailLists_mindbody = data.mindbody;
                            if (data === "true") {
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                };                
                $scope.clearfields = function() {
                    $("#email_list_name").val("");
                    $("#emailaddresses").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
            }  