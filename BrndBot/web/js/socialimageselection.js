            var facebook;
            var twitter;
            var number=0;
            var fb=0;
            var tw=0;
            if(facebook===true)
            {
               fb=1; 
            }
            if(twitter===true)
            {
               tw=1;
            }
            $(document).ready(function () {
                var isFacebook1 = $("#isFacebook").val();
                var isTwitter1 = $("#isTwitter").val();
                
                
                if(isFacebook1 === "true")
                {
                    number++;
                }
                if(isTwitter1 ==="true")
                {
                    number++;
                }
                //$("#number").val(count);
                facebook=$("#isFacebook").val();
                twitter=$("#isTwitter").val();
                $("#facebookimage").hide();
                $("#fabookpreviewdiv").hide();
                $(".forfb").hide();
                $("#twitterimage").hide();
                $("#twitterpreviewdiv").hide();
                    
                var isFacebook = $("#isFacebook").val();
                var isTwitter = $("#isTwitter").val();
                
                $("#title").keyup(function () {
                    $(".link_title").val($("#title").val());
                });
                $("#posttofb").click(function () {
                   
                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
                    var image_name = $("#imageToPost").val();
                    if(isFacebook === true)
                    {
                        if(image_name==="")
                        {
                            alert("Please choose an Image");
                            return false;
                        }
                        if($("#posttext").val()==="")
                        {
                            alert("Please enter Title for Facebook");
                            return false;
                        }
                        if($("#link_title").val()==="")
                        {
                            alert("Please enter Link Title");
                            return false;
                        }
                        if($(image_name===""))
                        {
                            alert("Please choose an Image");
                            return false;
                        }
                    }
                    if(isTwitter === true)
                    {
                        if($("#twittertext").val()==="")
                        {
                            alert("Please enter Title for Twitter");
                            return false;
                        }
                        if($("#link").val()==="")
                        {
                            alert("Please enter Title for Twitter");
                            return false;
                        }
                        if($(image_name===""))
                        {
                            alert("Please choose an Image");
                            return false;
                        }
                    }
                    
                    
                    var link = $("#link").val();
                    var f = link.startsWith("http");
                    if (!f)
                    {
                        link = "http://" + $("#url").val();
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
                            $("#sortLengthurl").val(bit_url);
                            if (isFacebook == "true" || isTwitter == "true") {
                                $.ajax({
                                    url: 'PostToSocial',
                                    method: 'post',
                                    data: {
                                        imageToPost: image_name,
                                        accesstoken: $("#accesstoken").val(),
                                        postText: $("#posttext").val(),
                                        title: $("#link_title").val(),
                                        url: $("#Linkurl").val(),
                                        twittweraccestoken: $("#twittweraccestoken").val(),
                                        twitterTokenSecret: $("#twitterTokenSecret").val(),
                                        text: $("#twittertext").val(),
                                        isFacebook: isFacebook,
                                        isTwitter: isTwitter,
                                        imagePost: image_name,
                                        shorturl: $("#sortLengthurl").val()
                                    },
                                    success: function (responseText) {
                                        $('#mask').hide();
                                        $('.window').hide();
                                        alert("Your post has been published successfully");

                                        document.location.href = "dashboard.jsp";
                                    }
                                });
                            }
                            else {
                                alert("select atleast one");
                            }
                        }
                    });
                });

                $("#socialscheduleid").click(function () {
                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
                    var schedule_date = $("#schedule_social_date").val();
                    var schedule_time = $("#schedule_social_time").val().replace(/ /g,'');  
                    var schedule_title = $("#schedule_title").val();
                    var schedule_desc = $("#schedule_desc").val();
                    var programs = $("#programs").val();
//                        var schedule = $("#schedule_time").val();
                    var facebookac=$("#facebookactions").val();
                    var twitterac=$("#twitteractions").val();
                    var imagetype = $("#gallery").val();
                    if(programs=== "0"){
                        if(schedule_date==="" && schedule_time==="" && schedule_title==="" && schedule_desc==="")
                        {
                            if(number===2)
                            {
                                if(facebookac==="0")
                                {
                                    alert("Please Choose The Facebook Action");
                                    $("#facebookactions").focus();
                                    return false;
                                }
                                if(twitterac==="0")
                                {
                                    alert("Please Choose The Twitter Action");
                                    $("#twitteractions").focus();
                                    return false;
                                }
                            }
                            if(number===1)
                            {
                                if(facebookac==="0" || twitterac==="0")
                                {
                                    if(fb===1 && facebookac==="0")
                                    {
                                        alert("Please Choose The Facebook Action");
                                        $("#facebookactions").focus();
                                        return false;
                                    }
                                    else if(tw===1 && twitterac==="0")
                                    {
                                        alert("Please Choose The Twitter Action");
                                        $("#twitteractions").focus();
                                        return false;
                                    }
                                }
                            }
                        }else{
                        if(schedule_title==="")
                        {
                            alert("Please Enter Title");
                            $("#schedule_title").focus();
                            return false;
                        }
                        if(schedule_desc==="")
                        {
                            alert("Please Enter Description");
                            $("#schedule_desc").focus();
                            return false;
                        }
                        if(schedule_date==="")
                        {
                            //alert("Please Choose Date");
                            //$("#schedule_social_date").focus();
                            //return false;
                        }
                        if(schedule_time==="")
                        {
                            alert("Please Choose Time");
                            $("#schedule_social_time").focus();
                            return false;
                        }
                    }
                    }else{
                        if(number===2)
                        {
                            if(facebookac==="0")
                            {
                                alert("Please Choose The Facebook Action");
                                $("#facebookactions").focus();
                                return false;
                            }
                            if(twitterac==="0")
                            {
                                alert("Please Choose The Twitter Action");
                                $("#twitteractions").focus();
                                return false;
                            }
                        }
                        if(number===1)
                        {
                            if(facebookac==="0" || twitterac==="0")
                            {
                                if(fb===1 && facebookac==="0")
                                {
                                    alert("Please Choose The Facebook Action");
                                    $("#facebookactions").focus();
                                    return false;
                                }
                                else if(tw===1 && twitterac==="0")
                                {
                                    alert("Please Choose The Twitter Action");
                                    $("#twitteractions").focus();
                                    return false;
                                }
                            }
                        }
                    }
                   
                    var image_name = $("#imageToPost").val();
                    var program_id = $("#programs").val();
                    var schedule_id_facebook = "0";
                    var schedule_id_twitter = "0";
                    var ManagedPage = "";
                    
                    if ((isFacebook == "true") && (isTwitter == "false")) {
                        schedule_id_facebook = $("#facebookactions").val();
                        ManagedPage = $("#pagenameSend").val();
                    } else if ((isFacebook == "false") && (isTwitter == "true")) {
                        schedule_id_twitter = $("#twitteractions").val();
                    } else if ((isFacebook == "true") && (isTwitter == "true")) {
                        schedule_id_facebook = $("#facebookactions").val();
                        schedule_id_twitter = $("#twitteractions").val();
                        ManagedPage = $("#pagenameSend").val();
                    }
                    
//                        var dateepoch = Date.parse(schedule);
                    alert(schedule_id_facebook);
                    console.log(schedule_id_facebook);
                    if ((schedule_id_facebook == "0") && (schedule_id_twitter == "0")) {
                        var schedule_date = $("#schedule_social_date").val();
                        var schedule_time = $("#schedule_social_time").val().replace(/ /g,'');  
                        var schedule_title = $("#schedule_title").val();
                        var schedule_desc = $("#schedule_desc").val();
//                        var schedule = $("#schedule_time").val();
//                        var dateepoch = Date.parse(schedule);
                        var l=schedule_date.toLocaleString() +" "+schedule_time.toLocaleString();
                        var schedule_time = Date.parse(l);
                        console.log("Epoch: " + schedule_time);
                        var myEpoch = schedule_time;
                        console.log("New Epoch: " + myEpoch);
//                        var newdate = new Date(dateepoch);
//
//                        console.log("new date:" + newdate);
//                        var schedule_hour = $("#hour").val();
//                        var schedule_minute = $("#minute").val();
//                        var schedule_AM = $("#AMPM").val();
//
//                        if (schedule_AM == "PM") {
//                            schedule_hour = parseInt(schedule_hour) + 12;
//                        }
//                        newdate.setHours(parseInt(schedule_hour));
//                        newdate.setMinutes(parseInt(schedule_minute));
//
//                        console.log("Value selected from Component: " + newdate);
//                        var schedule_time = Date.parse(newdate);
//
//                        console.log("Epoch: " + schedule_time);
//
//                        var dateObj = new Date(schedule_time);
//                        console.log(dateObj.getTimezoneOffset());
//
//                        var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;
//
//                        var newEpoch = schedule_time;
//                        console.log("New Epoch: " + newEpoch);
                        var social_schedule = "";
                        if (isFacebook == "true" && isTwitter == "false") {
                            
                            social_schedule = [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: program_id,
                                    schedule_desc: schedule_desc,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        post_text: '"' + $("#posttext").val() + '"',
                                        url: '"' + $("#url").val() + '"',
                                        ManagedPage: '"' + ManagedPage + '"',
                                        title:'"' + $("#link_title").val() + '"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "false" && isTwitter == "true") {
                            social_schedule = [
                                {
                                    type: gettwitter(),
                                    image_name: image_name,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: program_id,
                                    schedule_desc: schedule_desc,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                        "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                    },
                                    metadata: {
                                        text: '"' + $("#twittertext").val() + '"',
                                        shorturl:'"' + $("#url").val()+'"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "true" && isTwitter == "true") {
//                            alert(getfacebook());
//                            alert(image_name);
//                            alert(myEpoch);
//                            alert(schedule_title);
//                            alert(program_id);
//                            alert(schedule_desc);
//                            alert($("#accesstoken").val());
//                            alert($("#posttext").val());
//                            alert($("#url").val());
//                            alert(ManagedPage);
//                            alert($("#link_title").val());
                            
                    social_schedule =
                            [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: program_id,
                                    schedule_desc: schedule_desc,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        post_text: '"' + $("#posttext").val() + '"',
                                        url: '"' + $("#url").val() + '"',
                                        ManagedPage: '"' + ManagedPage + '"',
                                        title:'"' + $("#link_title").val() + '"'
                                    }
                                },
                                {
                                    type: gettwitter(),
                                    image_name: image_name,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: program_id,
                                    schedule_desc: schedule_desc,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                        "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                    },
                                    metadata: {
                                        text: '"' + $("#twittertext").val() + '"',
                                        shorturl:'"' + $("#url").val()+'"'
                                    }
                                }
                            ];
                        }
                        console.log(JSON.stringify(social_schedule));
                        $.ajax({
                            url: getHost() + 'ScheduleSocialPost',
                            method: 'post',
                            dataType: 'json',
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            data: JSON.stringify(social_schedule),
                            success: function (responseText) {
//                        alert();
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been Scheduled Successfully");
                                document.location.href = "dashboard.jsp";
                            }
                        });
                    }else {
                        if (isFacebook == "true" && isTwitter == "false") {
                            social_schedule = [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_facebook,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        post_text: '"' + $("#posttext").val() + '"',
                                        url: '"' + $("#url").val() + '"',
                                        ManagedPage: '"' + ManagedPage + '"',
                                        title:'"' + $("#link_title").val() + '"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "false" && isTwitter == "true") {
                            social_schedule = [
                                {
                                    type: gettwitter(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_twitter,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                        "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                    },
                                    metadata: {
                                        text: '"' + $("#twittertext").val() + '"',
                                        shorturl:'"' + $("#url").val()+'"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "true" && isTwitter == "true") {

                    social_schedule =
                            [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_facebook,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        post_text: '"' + $("#posttext").val() + '"',
                                        url: '"' + $("#url").val() + '"',
                                        ManagedPage: '"' + ManagedPage + '"',
                                        title:'"' + $("#link_title").val() + '"'
                                    }
                                },
                                {
                                    type: gettwitter(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_twitter,
                                    image_type:imagetype,
                                    token_data: {
                                        "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                        "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                    },
                                    metadata: {
                                        text: '"' + $("#twittertext").val() + '"',
                                        shorturl:'"' + $("#url").val()+'"'
                                    }
                                }
                            ];
                        }
                        console.log(JSON.stringify(social_schedule));
                        $.ajax({
                            url: getHost() + 'ScheduleSocialPostActions',
                            method: 'post',
                            dataType: 'json',
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            data: JSON.stringify(social_schedule),
                            success: function (responseText) {
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been Scheduled Successfully");
                                document.location.href = "dashboard.jsp";
                            }
                        });
                    }
                });

            });

            function fadepopup() {
                var id = '#dialog';

                //Get the screen height and width
                var maskHeight = $(document).height();
                var maskWidth = $(window).width();

                //Set heigth and width to mask to fill up the whole screen
                $('#mask').css({'width': maskWidth, 'height': maskHeight});

                //transition effect
                $('#mask').fadeIn(500);	
                $('#mask').fadeTo("slow",0.95);	

                //Get the window height and width
                var winH = $(window).height();
                var winW = $(window).width();

                //Set the popup window to center
                $(id).css('top', winH / 2 - $(id).height() / 2);
                $(id).css('left', winW / 2 - $(id).width() / 2);

                //transition effect
                $(id).fadeIn(2000);

                //if close button is clicked
                $('.window .close').click(function (e) {
                    //Cancel the link behavior
                    e.preventDefault();

                    $('#mask').hide();
                    $('.window').hide();
                });

                //if mask is clicked
                $('#mask').click(function () {
                    $(this).hide();
                    $('.window').hide();
                });

            }



function overlay() {
                document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
                document.body.style.overflow = 'hidden';
            }
            var program_id = '0';

            function displaySchedule() {
                $("#popupschedule").show();
                var isFB = $("#isFacebook").val();
                console.log("isFB" + isFB);
                var isTwitter = $("#isTwitter").val();
                console.log("istwitter" + isTwitter);
                if ((isFB == "true") && (isTwitter == "false")) {
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialFacebookActions(program_id);
                    $("#facebookactions").show();
                    $("#twitteractions").hide();
                    console.log("true");
                } else if ((isFB == "false") && (isTwitter == "true")) {
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialTwitterActions(program_id);
                    $("#facebookactions").hide();
                    $("#twitteractions").show();
                } else if ((isFB == "true") && (isTwitter == "true")) {
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialFacebookActions(program_id);
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialTwitterActions(program_id);
//                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialActions();
                    $("#facebookactions").show();
                    $("#twitteractions").show();
                }
            }
            
            $(document).ready(function ()
            {                
                var isFB = $("#isFacebook").val();
                var isTwitter = $("#isTwitter").val();
                if ((isFB == "true") && (isTwitter == "false")) {
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialFacebookActions(program_id);
                    console.log("true");
                } else if ((isFB == "false") && (isTwitter == "true")) {
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialTwitterActions(program_id);
                } else if ((isFB == "true") && (isTwitter == "true")) {
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialFacebookActions(program_id);
                    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialTwitterActions(program_id);
//                  angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialActions();
                }                
                $("#programs").change(function(){
                    
                    program_id = $("#programs").val();
                    if ( isFB == "true"){
                        angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialFacebookActions(program_id);
                    }
                    if ( isTwitter == "true"){
                        angular.element(document.getElementById('controllerMarketingCampaign')).scope().getSocialTwitterActions(program_id);
                    }
                    
                    if (parseInt(program_id) == 0){
                        $("#facebookactions").attr("disabled", false);
                        $("#twitteractions").attr("disabled", false);

                        document.getElementById('schedule_desc').disabled = false;
                        document.getElementById('schedule_title').disabled = false;
                        document.getElementById('schedule_social_date').disabled = false;
                        document.getElementById('schedule_social_time').disabled = false;
                    }else {
                        $("#facebookactions").attr("disabled", false);
                        $("#twitteractions").attr("disabled", false);

                        document.getElementById('schedule_desc').disabled = true;
                        document.getElementById('schedule_title').disabled = true;
                        document.getElementById('schedule_social_date').disabled = true;
                        document.getElementById('schedule_social_time').disabled = true;
                        
                    }
                });
                
            });
            function controllerMarketingCampaign($scope, $http) {
                
                $http({
                        method: 'GET',
                        url: getHost() + 'getAllUserMarketingProgramsByUserId.do'
                    }).success(function (data) {
                        $scope.urls = data;
                        console.log($scope.urls);
                    }).error(function (data) {
                        alert("request not successful...1");
                    });
                
                $scope.getSocialFacebookActions = function (program_id) {

                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?programid='+ program_id +'&type='+ getfacebook()
                    }).success(function (data) {
                        $scope.facebook_actions = data;
                        console.log($scope.facebook_actions);
                    }).error(function (data) {
                        alert("request not successful");
                    });
                };

                $scope.getSocialTwitterActions = function (program_id) {

                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?programid='+ program_id +'&type=' + gettwitter()
                    }).success(function (data) {
                        $scope.twitter_actions = data;
                    }).error(function (data) {
                        alert("request not successful");
                    });
                };
                
                $scope.getProgramNames = function() {
                    $http({
                       method: 'GET',
                       url:getHost() + 'getAllUserMarketingPrograms.do'
                    }).success(function (data){
//                        alert(JSON.stringify(data));
                        $scope.marketing_programs = data;
                    }).error(function (data){
                        alert("request not successful");
                    });
                };
                
                $scope.getSocialActions = function () {

                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?type=social'
                    }).success(function (data) {
                        $scope.social_actions = data;
                    }).error(function (data) {
                        alert("request not successful");
                    });
                };

            }
            
            function validateact() {
                var facebookactions = $("#facebookactions").val();
                var twitteractions = $("#twitteractions").val();
                var programs = $("#programs").val();
                console.log("FB" + facebookactions + 'TW' + twitteractions);
                if(programs === 0)
                {}
                else
                {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
                }
                    
                
                if ((parseInt(facebookactions) !== 0) && (parseInt(twitteractions) !== 0))
                {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
                 
                }
                else if ((parseInt(facebookactions) === 0) && (parseInt(twitteractions) !== 0)) {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
                }
                else if (((parseInt(facebookactions) !== 0) && (parseInt(twitteractions) === 0))) {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
                } else if (((parseInt(facebookactions) === 0) && (parseInt(twitteractions) === 0))) {
                    document.getElementById('schedule_title').disabled = false;
                    document.getElementById('schedule_social_date').disabled = false;
                    document.getElementById('schedule_social_time').disabled = false;
                    document.getElementById('schedule_desc').disabled = false;
                }
            }

function controllerMarketingCampaign($scope, $http) {
//    alert();
//     $scope.uploadFile = function () {
//         alert();
//                        var file = $scope.myFile;
//                        console.log('file is ' + JSON.stringify(file));
//                        var uploadUrl = global_host_address + 'UploadImages';
//                        fileUpload.uploadFileToUrl(file, uploadUrl);
//                    };
    
//    $scope.entities_selected_time = "";
//    $scope.master_facebook = getfacebook();
//    $scope.master_twitter = gettwitter();
//    $scope.master_email = getemail();
//    $scope.master_note = getnote();
//    $scope.getImageId = function(id,imagename)
//    {
//        alert(imageName);
//        $("#addimage").show();
//        $(".imageGallery-card").css("background-color", "#ffffff");
//        $(".imageGallery-card >div >div").css("color", "#5F6775");
//        $("#div"+id).css("background-color", "#5cc1a4");
//        $("#div"+id+" > div > div").css("color", "#ffffff");
//        $("#selectedimagename").val(imagename);
//        $("#selectedimageid").val(id);
//    };
//    
    $scope.getSocialFacebookActions = function (program_id) {
    $http({
        method: 'GET',
        url: getHost() + 'GetScheduledActions?programid='+ program_id +'&type='+ getfacebook()
        }).success(function (data) {
            $scope.facebook_actions = data;
            console.log($scope.facebook_actions);
        }).error(function (data) {
            alert("request not successful");
        });
    };

    $scope.getSocialTwitterActions = function (program_id) {
    $http({
        method: 'GET',
        url: getHost() + 'GetScheduledActions?programid='+ program_id +'&type=' + gettwitter()
        }).success(function (data) {
            $scope.twitter_actions = data;
        }).error(function (data) {
            alert("request not successful");
        });
    };
    
    $scope.getProgramNames = function() {
                    $http({
                       method: 'GET',
                       url:getHost() + 'getAllUserMarketingPrograms.do'
                    }).success(function (data){
//                        alert(JSON.stringify(data));
                        $scope.marketing_programs = data;
                    }).error(function (data){
                        alert("request not successful");
                    });
                };
    
    $scope.getLinks = function(){
        $http({
            method: 'GET',
            url: getHost() + 'getAllUserMarketingProgramsByUserId.do'
            }).success(function (data) {
                $scope.urls = data;
                console.log($scope.urls);
            }).error(function (data) {
            alert("request not successful...1");
        });
    };
    $scope.uploadFile = function(){alert("..");
        $("#myFile").upload("UploadImages",function(success){
        
           $("#fileuploaddiv").hide();
           $scope.showImages();
       });
    };
    $scope.showImages = function(){
                $("#addContact").hide();     
                $("#imageGalleryDiv").show();  
                $("#fade").show();
                //$("#imageGallerySection").show();
                $scope.curPage = 0;
                $scope.pageSize = 100;
                $http({
                    method : 'GET',
                    url : 'GetUserImages'
                }).success(function(data, status, headers, config) {
                //alert(JSON.stringify(data));
                  $scope.datalistimages = data;
//                $scope.numberOfPages = function() {
//                return Math.ceil($scope.datalistimages.length / $scope.pageSize);
//                };
//                if (data === error){
//                    alert(data);
//                }
        }).error(function(data, status, headers, config) {
        alert("No data available, problem fetching the data");
                // called asynchronously if an error occurs
                // or server returns response with an error status.
        });
        };
};
$("#Servicecontinue").hide();
        var rootApp = angular.module('rootApp', ['uploadModule','imagegallery']);
    
        var uploadModule = angular.module('uploadModule', []);
        
            uploadModule.directive('fileModel', ['$parse', function ($parse) {
                    return {
                        restrict: 'A',
                        link: function (scope, element, attrs) {
                            var model = $parse(attrs.fileModel);
                            var modelSetter = model.assign;

                            element.bind('change', function () {
                                scope.$apply(function () {
                                    modelSetter(scope, element[0].files[0]);
                                });
                            });
                        }
                    };
                }]);

            uploadModule.service('fileUpload', ['$http', function ($http) {
                    this.uploadFileToUrl = function (file, uploadUrl) {
                        var fd = new FormData();
                        fd.append('file', file);
                        $http.post(uploadUrl, fd, {
                            transformRequest: angular.identity,
                            headers: {'Content-Type': undefined}
                        })
                            .success(function (data) {
                                window.open(global_host_address + 'imagegallery.jsp', "_self");
                            })
                            .error(function () {
                                alert("request unsuccessful");
                            });
                    };
                }]);

            uploadModule.controller('myCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {

                    $scope.uploadFile = function () {
                        var file = $scope.myFile;
                        console.log('file is ' + JSON.stringify(file));
                        var uploadUrl = global_host_address + 'UploadImages';
                        fileUpload.uploadFileToUrl(file, uploadUrl);
                    };

              }]);
          
           var uploadModule = document.getElementById('uploadModule');

            angular.element(document).ready(function() {
                   angular.bootstrap(uploadModule, ['uploadModule']);
            });
           
            var imagegallery = angular.module('imagegallery', []);

                imagegallery.controller('samplecontoller', function ($scope,$http) {

                $scope.showData = function(){
//                     alert("showData");
                 $scope.curPage = 0;
                 $scope.pageSize = 1000;

                    $http({
                            method : 'GET',
                            url : 'GetImageGallery'
                    }).success(function(data, status, headers, config) {
                        $scope.datalists = data;
                        
                    $scope.numberOfPages = function() {
                                            return Math.ceil($scope.datalists.length / $scope.pageSize);
                                    };
                        if (data === error){
                            alert(data);
                        }
                    }).error(function(data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });

                    };
                    
                    $scope.deleteImage = function (image_id, user_id, image_name) {
                        var image = {"image_id": image_id, "user_id": user_id, "image_name": image_name};
                        
                        $http({
                            method: 'POST',
                            url: getHost() + 'DeleteGalleryImages',
                            headers: {'Content-Type': 'application/json'},
                            data: image
                        }).success(function (data)
                        {
                            $scope.status = data;
                            if (data === "true") {
                                alert("Image deleted successfully");
                                window.open(getHost() + 'imagegallery.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        })
                    };

                });
                
                angular.module('imagegallery').filter('pagination', function()
                {
                 return function(input, start)
                 {
                  start = +start;
                  return input.slice(start);
                 };
                });
            
        var fl = document.getElementById('filesToUpload');

        fl.onchange = function (e) {
            var ext = this.value.match(/\.(.+)$/)[1];
            switch (ext)
            {
                case 'jpg':
                case 'png':
                case 'jpeg':
                case 'JPG':
                case 'PNG':
                case 'JPEG':
                case 'svg':
                case 'SVG':
                break;
                default:
                    alert('This type of image is not allowed');
                    this.value = '';
            }
        };

        function fileSelect(evt) {
            if (window.File && window.FileReader && window.FileList && window.Blob) {
                var files = evt.target.files;

                var result = '';
                var file;
                for (var i = 0; file = files[i]; i++) {
                    // if the file is not an image, continue
                    if (!file.type.match('image.*')) {
                        continue;

                    }
                    //            alert("only .png file");

                    reader = new FileReader();
                    reader.onload = (function (tFile) {
                        return function (evt) {
                            var div = document.createElement('div');
                            div.innerHTML = '<img style="width: 90px;" src="' + evt.target.result + '" />';
                            document.getElementById('logoimage').src = evt.target.result;
//                            document.getElementById("Servicecontinue").disabled = false;
                            $("#Servicecontinue").show();
                        };
                    }(file));
                    reader.readAsDataURL(file);
                }
            } else {
                alert('The File APIs are not fully supported in this browser.');
            }
        }

        document.getElementById('filesToUpload').addEventListener('change', fileSelect, false);
