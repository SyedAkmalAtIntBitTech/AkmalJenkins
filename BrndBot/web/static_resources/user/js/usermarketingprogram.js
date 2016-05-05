            function validateurl(){
                var myRegExp =/^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;
                   if($("#program_url").val()!==""){
                    if (!myRegExp.test($("#program_url").val())) {
//                        alert("Please enter valid Program URL");
//                        alert($("#program_url").val().indexOf("http://"));
                        if($("#program_url").val().indexOf("http://")<0) 
                        {
                           $("#program_url").val('http://'+$("#program_url").val());
//                           alert("index");
                        }
                        if(!myRegExp.test($("#program_url").val())){
                            $("#program_url").val('');
//                            alert("notvalid");
                        }
                        return false;
                        $("#program_url").focus();
                        $("#program_url").click();   
                    }
                }
//                    else
//                    {
//                        if(!$("#program_url").val().indexOf("http://") >= 0)
//                           $("#program_url").val('http://'+$("#program_url").val()); 
//                    }
                    return true;
            }
            function validate(){
                var program_name = $("#program_name").val();
                var program_date_time = $("#programdatetime").val();
                var program_url = $("#program_url").val();
                var program_urlname = $("#program_url_name").val();
                if (program_name === ""){
                    alert("Enter the program name!");
                    $("#program_name").focus();
                    return false;
                }
                if (program_date_time === ""){
                    alert("Enter the Program Date!");
                    $("#programdatetime").focus();
                    return false;
                }
               
//                if(program_url.length !== 0){
//                    alert("link URL not entered please enter valid Link");
//                    $("#program_url").focus();
//                    return false;
//                }
//                
                if((program_url === "http://")&&(program_urlname.length !== 0)){
                    alert("Please Enter Valid Link_Url!");
                    $("#program_url").focus();
                    return false;
                }
                if((program_url.length !== 0)&&(program_urlname.length === 0)){
                    alert("Link Name not entered! Please enter Link Name.");
                    $("#program_url_name").focus();
                    return false;
                }
                if((program_url.length === 0)&&(program_urlname.length !== 0)){
                    alert("Please Enter link URL!");
                    $("#program_url").focus();
                    return false;
                }
                 if((program_urlname.length === 0)&&(program_url.length === 0)){
//                    alert("optional");
                    return true;
                }
//                if (program_url === "") {
//                    alert("Enter the program url");
//                    $("#program_url").focus();
//                    return false;
//                }
                return true;
            }
            
            function usermarketingprogram($scope, $http){
               
                $scope.saveMarketingProgram = function(){
                    
                if (validate()){
//                  alert("enter");
                    var program_name = $("#program_name").val();
                    var program_date_time = $("#programdatetime").val();
                    var ss = program_date_time.split(" ");
                    var count=0;
                    var month="";
                    var day="";
                    var year="";
                    for (var i in ss) {
                        if(count == 0){}
                        if(count == 1)
                        {
                            var value=ss[i];
                            switch (value)
                            {
                               case 'Jan': month="01";
                               break;

                               case 'Feb': month="02";
                               break;

                               case 'Mar': month="03";
                               break;

                               case 'Apr': month="04";
                               break;

                               case 'May': month="05";
                               break;
                               
                               case 'Jun': month="06";
                               break;

                               case 'Jul': month="07";
                               break;

                               case 'Aug': month="08";
                               break;

                               case 'Sep': month="09";
                               break;

                               case 'Oct': month="10";
                               break;
                               
                               case 'Nov': month="11";
                               break;

                               case 'Dec': month="12";
                               break;

                               default:  month="00";
                            }
                        }
                        if(count == 2)
                        {
                            day=ss[i];;
                        }
                        if(count == 3)
                        {
                            year=ss[i];
                        }
                        count++;
                    }
                    var d=year+"-"+month+"-"+day;
                    var program_url = $("#program_url").val();
                    var program_url_name = $("#program_url_name").val();
                    if(program_url === "http://" || program_url_name === "")
                    {
                        program_url="";
                        program_url_name="";
                    }
                    var schedule_time = Date.parse(program_date_time);
                    
//                    if (program_name === "") {
//                        alert("Please enter the Program Name!");
//                        $("#program_name").focus();
//                        return false;
//                    }
                    //alert("program date time ...."+program_date_time);
                    //alert("date... "+d);
//                    if (program_date_time === "") {
//                        alert("Please select the date!");
//                        $("#programdatetime").focus();
//                        return false;
//                    }
                    
                    console.log("Epoch: " + schedule_time);

                    var myEpoch = schedule_time;

                    console.log("New Epoch: " + myEpoch);
                        
                    var marketing_category_id =$("#marketing_category_id").val();
                    var marketing_program_id =$("#marketing_program_id").val();
//                    alert("cat.."+marketing_category_id);
//                    alert("mar.."+marketing_program_id);
                    var program_details = {"program_name": program_name, 
                                           "program_date_time": program_date_time,
                                           "program_url":program_url,
                                           "program_url_name":program_url_name,
                                           "marketing_category_id":marketing_category_id,
                                           "marketing_program_id":marketing_program_id
                                          };
                    $http({
                        method: 'POST',
                        url: getHost() + 'setMarketingProgram',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(program_details)
                    }).success(function (data, status, headers, config) {
                        if (data !== null){
                            $("#saveMarketingProgram").unbind('click');
                            alert("Details saved successfully.");
                            var redirect="user/marketingprogramactions?past=0&program_id="+data+"&program_date="+program_date_time;
                            window.open(getHost() + redirect, "_self");
                            $("#saveMarketingProgram").bind('click');
                        }else {
                            alert("Problem saving the record!");
                        }
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                }
                };
            }