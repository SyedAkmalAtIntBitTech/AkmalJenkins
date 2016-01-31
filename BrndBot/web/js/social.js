
            $(document).ready(function () {
                
                $("#socialclick").click(function(){
                    $("#socialdropdown").css("display","block");
                });
                var myVar1 = '<%= code %>';    /* retrieve json from request attribute */
                var mytest = eval('(' + myVar1 + ')');

                var removecote = myVar1.replace("[", '').replace(/"/g, '').replace(']', '');
                var pages = removecote.split(",");

                if (myVar1 === "null") {
                    $("#popup").hide();
                }
                else {
                    $("#popup").show();

                    for (var i = 0; i < pages.length; i = i + 3) {
                        $("#fbmanagepages").append("<tr  id=page#" + i + "><td><p id=p"+i+">" + pages[i] + "</p></td><td><input type=hidden id=access" + i + " value=" + pages[i + 1] + "></td><td><img src=" + pages[i + 2] + "></td> </tr>");
                    }

                    $("#content").append(" <br><center><input id=isdefault name=isdefault type=checkbox class=btn btn-primary value=default>Default</input></center>");
                    $("#content").append(" <br><center><input id='facebookok' name='facebookok' type='button' class='btn btn-primary' value='ok' ng-click='getfacebookdetails()' >&nbsp;&nbsp;<input id=close name=close type=button class=btn btn-primary value=cancel></center>");
                }
 
                var managed_page = "";
                var default_access_token = "";
                var fb_user_profile_name = "";
                var check_default = "false";
                var check_default_managed_page;
                $("tr").click(function () {
                    var id = this.id.split("#");

                    var page = $("#p" + id[1]).text();
                    var accessToken = $("#access" + id[1]).val();
                    $("#pagenameSend").val(page);
                    $("#fbaccessTokenSend").val(accessToken);
                    $("#fbdefaultAccessToken").val("true");
                    check_default = $("#fbdefaultAccessToken").val();

                });

                $("#isdefault").click(function () {
                    if (check_default == "true"){
                        default_access_token = $("#fbaccessTokenSend").val();
                        managed_page = $("#pagenameSend").val();
                        fb_user_profile_name = $("#fbusername").val();
                    }
                    
                    });

                $("#facebookok").click(function () {
                    check_default_managed_page = document.getElementById("isdefault").checked;
                    
                    if ((check_default_managed_page === true) && (check_default === "true")){
                        $.ajax({
                                url: 'ServletUserPreferencesFacebook',
                                method: 'post',
                                data: {
                                   default_page_name: managed_page,
                                   fb_user_profile_name: fb_user_profile_name,
                                   access_token_method: "setAccessToken",
                                   access_token:default_access_token
                                },
                                success: function (responseText) {
                                    alert("sucess");
                                    $("#popup").hide(); 
                                    $("#submitbutton").prop("disabled",false);
                                    angular.element(document.getElementById('controllerSocial')).scope().getFacebookDetails();                                    
                            }
                            });
                    }else if((check_default_managed_page === false) && (check_default === "true")) { 
                        $("#popup").hide(); 
                        $("#submitbutton").prop("disabled",false);
                    }else {
                        alert("No default page selected");
                    }   
            });
            
            $("#close").click(function(){
                    $("#fbaccessTokenSend").val("") ;
                    $("#fbdefaultAccessToken").val("");
                    $("#isFacebook").val("false");
                    $("#facebook").prop("checked",false);
                    $("#popup").hide();
                    $("#twitterpopup").hide();
                    $("#submitbutton").prop("disabled",true);
            });   
           });
           
           function imgchng(){
               document.getElementById("fb").src="images/fb_icon.png";
               
           }
                    
                function controllerSocial($scope, $http) {
    
                    $scope.getFacebookDetails = function () {
                        $http({
                            method: 'GET',
                            url: 'ServletUserPreferencesFacebook?access_token_method=getAccessToken&settings=true'
                        }).success(function (data, status, headers, config) {
                            $scope.facebookPage = data;
                            if (data.FacebookLoggedIn == "true"){
                                $("#fbclear").show();
                            }else{
                                $("#fbclear").hide();
                            }
                            if (data === error) {
                                alert(data);
                            }
                        }).error(function (data, status, headers, config) {
                            alert("No data available! Problem fetching the data.");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                    };
                    
                    $scope.clearFacebookDetails = function () {

                    if(confirm('Do you want to really clear the details?')){
                        $http({
                            method: 'POST',
                            url: 'ServletUserPreferencesFacebook?access_token_method=clearFacebookDetails'
                        }).success(function (data, status, headers, config) {
                            $scope.getfacebookdetails();
                        }).error(function (data, status, headers, config) {
                            alert("No data available! Problem fetching the data.");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                        alert('Details cleared successfully!');
                    }
                    };
                    
                    $scope.getTwitterDetails = function () {
                        
                        $http({
                            method: 'GET',
                            url: 'ServletUserPreferencesTwitter?access_token_method=getAccessToken&settings=true'
                        }).success(function (data, status, headers, config) {
                            $scope.twitterPage = data;

                            if (data.TwitterLoggedIn == "true"){
                                $("#twitterclear").show();
                            }else{
                                $("#twitterclear").hide();
                            }
                        }).error(function (data, status, headers, config) {
                            alert("No data available! Problem fetching the data.");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                    };
                    
                    $scope.clearTwitterDetails = function () {

                    if(confirm('Do you want to really clear the details?')){
                        $http({
                            method: 'POST',
                            url: 'ServletUserPreferencesTwitter?access_token_method=clearTwitterDetails'
                        }).success(function (data, status, headers, config) {
                            $scope.getTwitterDetails();
                        }).error(function (data, status, headers, config) {
                            alert("No data available! Problem fetching the data.");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                        alert('Details cleared successfully!');
                    }
                    };
                 }    