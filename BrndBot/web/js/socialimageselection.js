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
                $("#description").keyup(function () {
                    $(".link_description").val($("#description").val());
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
                        if($("#link_description").val()==="")
                        {
                            alert("Please enter LInk Description");
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
                                        description: $("#link_description").val(),
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

                $("#schedulethepost").click(function () {
                    var schedule_date = $("#schedule_social_date").val();
                    var schedule_time = $("#schedule_social_time").val().replace(/ /g,'');  
                    var schedule_title = $("#schedule_title").val();
                    var schedule_desc = $("#schedule_desc").val();
                    var programs = $("#programs").val();
//                        var schedule = $("#schedule_time").val();
                    var facebookac=$("#facebookactions").val();
                    var twitterac=$("#twitteractions").val();
                    if(programs=== "0")
                    {
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
                        }
                        else
                        {
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
                            alert("Please Choose Date");
                            $("#schedule_social_date").focus();
                            return false;
                        }
                        if(schedule_time==="")
                        {
                            alert("Please Choose Time");
                            $("#schedule_social_time").focus();
                            return false;
                        }
                    }
                    }
                    else
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
                    }
                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
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
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        description: '"' + $("#description").val() + '"',
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
                                            schedule_time: myEpoch,
                                            schedule_title: schedule_title,
                                            program_id: program_id,
                                            schedule_desc: schedule_desc,
                                            token_data: {
                                                "access_token": '"' + $("#accesstoken").val() + '"'
                                            },
                                            metadata: {
                                                description: '"' + $("#description").val() + '"',
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
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been Scheduled Successfully");
                                document.location.href = "dashboard.jsp";
                            }
                        });
                    } else {
                        if (isFacebook == "true" && isTwitter == "false") {
                            social_schedule = [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_facebook,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        description: '"' + $("#description").val() + '"',
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
                                            token_data: {
                                                "access_token": '"' + $("#accesstoken").val() + '"'
                                            },
                                            metadata: {
                                                description: '"' + $("#description").val() + '"',
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

$(document).ready(function () {
    $("#schedulethepost").click(function () {
    alert();
                    var schedule_date = $("#schedule_social_date").val();
                    var schedule_time = $("#schedule_social_time").val().replace(/ /g,'');  
                    var schedule_title = $("#schedule_title").val();
                    var schedule_desc = $("#schedule_desc").val();
                    var programs = $("#programs").val();
//                        var schedule = $("#schedule_time").val();
                    var facebookac=$("#facebookactions").val();
                    var twitterac=$("#twitteractions").val();
                    if(programs=== "0")
                    {
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
                        }
                        else
                        {
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
                            alert("Please Choose Date");
                            $("#schedule_social_date").focus();
                            return false;
                        }
                        if(schedule_time==="")
                        {
                            alert("Please Choose Time");
                            $("#schedule_social_time").focus();
                            return false;
                        }
                    }
                    }
                    else
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
                    }
                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
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
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        description: '"' + $("#description").val() + '"',
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
                                            schedule_time: myEpoch,
                                            schedule_title: schedule_title,
                                            program_id: program_id,
                                            schedule_desc: schedule_desc,
                                            token_data: {
                                                "access_token": '"' + $("#accesstoken").val() + '"'
                                            },
                                            metadata: {
                                                description: '"' + $("#description").val() + '"',
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
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been Scheduled Successfully");
                                document.location.href = "dashboard.jsp";
                            }
                        });
                    } else {
                        if (isFacebook == "true" && isTwitter == "false") {
                            social_schedule = [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_facebook,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        description: '"' + $("#description").val() + '"',
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
                                            token_data: {
                                                "access_token": '"' + $("#accesstoken").val() + '"'
                                            },
                                            metadata: {
                                                description: '"' + $("#description").val() + '"',
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
//    $("#upload").click(function(){
//        alert("1");
//       $("#uploadBtn").upload("UploadImages",function(success){
//           alert("2");
////           $("#image1").click();
////           $(".close-reveal-modal").click();
//       });
//   });
});

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
    $scope.uploadFile = function(){
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
