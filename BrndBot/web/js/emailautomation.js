/* 
 * Created on : Oct 22, 2015, 11:10:00 AM
 * Author     : Satyajit Roy at IntBit Technologies
 */

    var emails = "";
    var schedule_time = "";
    var schedule_date = "";
    var email_list_name = "";
    var template_id = 0;
    var days = 0;
    var entity_no_email_template = "";
    var entity_id = 0;
    var type = "";
    var program_id = "";
    
    function emailautomation($scope, $http){
        $scope.getEntityDetails = function (){
            if (entity_id != "0"){
                var entity_details = {"entity_id": entity_id};
                $http({
                    method: 'POST',
                    url: getHost() + 'getRecuringEntity.do',
                    headers: {'Content-Type':'application/json'},
                    data: JSON.stringify(entity_details)
                }).success(function(data, status){
                    $scope.entity_details = data;
                    if (data.recuring_email_template_id != null){
                        template_id = data.recuring_email_template_id;
                    }else {
                        entity_no_email_template = "true";
                    }
                    html_data = data.recuring_email_body;
                    $('#edit').froalaEditor('html.set',''+html_data+'');

                    $scope.showEmailList();
                    showEmailListName(data.recuring_email_email_list_name);
                    days = data.recuring_email_days;
                }).error(function(){
                    alert("Problem fetching the data!");
                });
            }
        };
        /*
        * Bring all the email list from the database
        */
        $scope.showEmailList = function () {
            var emailids = {"update": "allEmailListNames"};
            $http({
                method: 'GET',
                url: getHost() + 'GetEmailLists?update=allEmailListNames'
            }).success(function(data, status, headers, config) {
                $scope.emailLists_user = data.user;
                $scope.emailLists_mindbody = data.mindbody;
            }).error(function(){
                alert("Problem fetching the data!");
            });
        };
        /*
         * Bring all the recuring email templates form the database
         */
         $scope.getEmailTemplates = function(){
                $("#emailautomationcontent").hide();
                $("#emlautomeditorcontainer").show();
                $http({
                    method: 'GET',
                    url: getHost() + 'getAllRecuringEmailTemplates.do'
                }).success(function(data, status){
                    $scope.recuring_email_templates = data;
                }).error(function(){
                    alert("Problem fetching the data!");
                });
        };
        $scope.addUpdateRecuringAction = function(){
            if (validate()){
                var days = $("#days").val();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();
                var till_date = $("#datepicker").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
                var till_date_epoch = Date.parse(till_date);
                var $iframe = $('.fr-iframe');
                var html_data = $('#edit').froalaEditor('html.get');
                if ( type == 'add'){
                    var recuring_action = {
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({
                        method: 'POST',
                        url: 'addRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if (data === "true") {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
                        }else {
                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                    });

                }else if((type == 'template') && (entity_no_email_template == "true")){
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({
                        method: 'POST',
                        url: 'addupdateRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if((data == "true") && (entity_no_email_template == "true")) {
                            alert("Details saved succesfully.");
                            $("#emailautomationcontent").hide();
                            entity_no_email_template = "false";
                            $("#emlautomeditorcontainer").show();
                        }else {
                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                    });
                }else if ((type == 'edit') && (entity_no_email_template == "true")){
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({
                        method: 'POST',
                        url: 'addupdateRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if ((data === "true")) {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
                        }else {
                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                    });
                }else if((type == 'edit')||(type == 'template')){
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "template_id" : template_id, "html_data": html_data,
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": emails,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recuring_email_title":recuring_email_title,
                        "recuring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({
                        method: 'POST',
                        url: 'updateRecuringAction.do',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if ((data === "true")) {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'programactions.jsp?program_id='+program_id, "_self");
                        }else {
                            alert("Problem saving the record!");
                        }
                        
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                    });
                }
            }
        };
        $scope.showHTMLData = function(html_data, id){
            var $iframe = $('.fr-iframe');
            $('#edit').froalaEditor('html.set',''+html_data+'');
            template_id = id;
        };
    }

   
            function validate(){
                var emlval = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
                var days = $("#days").val();
                var emaillisttext = $("#emaillist :selected").text();
                var emaillist = $("#emaillist").val();
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();
                var till_date = $("#datepicker").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
                if (recuring_email_title === ""){
                    alert("Enter the title");
                    $("#recuring_email_title").focus();
                    return false;
                }
                if (recuring_email_description === ""){
                    alert("Enter the description");
                    $("#recuring_email_description").focus();
                    return false;
                }
                if (days === "0") {
                    alert("please select the day");
                    $("#days").focus();
                    return false;
                }
                if (emaillisttext === "") {
                    alert("please select the email list text");
                    emaillisttext.focus();
                    return false;
                }
                 if (schedule_time === ""){
                    alert("select the time");
                    $("#timepicker1").focus();
                    return false;
                }
                 if (till_date === ""){
                    alert("till date not selected,please select the date");
                    $("#datepicker").focus();
                    return false;
                }
               
                if (emaillist === "0") {
                    alert("please select the email list");
                    $("#emaillist").focus();
                    return false;
                }
                if (subject === "") {
                    alert("Enter the subject");
                    $("#subject").focus();
                    return false;
                }
                if (from_name === ""){
                    alert("Enter the from name");
                    $("#from_name").focus();
                    return false;
                }        
                if((reply_to_address === "")||(!emlval.test(reply_to_address))){
                    alert("Enter Valid reply-to-address ");
                    $("#reply_to_address").focus();
                    return false;
                }
                return true;
            }
    $(function(){
      $('#edit').froalaEditor({
        key: FroalaLicenseKey
      });
       $("#templatetab").click(function (){
       $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
       });
       
       
    });